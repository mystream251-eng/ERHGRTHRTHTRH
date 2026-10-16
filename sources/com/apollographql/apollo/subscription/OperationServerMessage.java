package com.apollographql.apollo.subscription;

import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.api.internal.json.BufferedSourceJsonReader;
import com.apollographql.apollo.api.internal.json.JsonReader;
import com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okio.Buffer;

/* JADX INFO: loaded from: classes.dex */
public abstract class OperationServerMessage {
    static final String JSON_KEY_ID = "id";
    static final String JSON_KEY_PAYLOAD = "payload";
    static final String JSON_KEY_TYPE = "type";

    public static final class ConnectionAcknowledge extends OperationServerMessage {
        public static final String TYPE = "connection_ack";
    }

    public static final class ConnectionKeepAlive extends OperationServerMessage {
        public static final String TYPE = "ka";
    }

    OperationServerMessage() {
    }

    public static OperationServerMessage fromJsonString(String str) {
        Utils.checkNotNull(str, "json == null");
        try {
            Buffer buffer = new Buffer();
            buffer.writeUtf8(str);
            return readFromJson(new BufferedSourceJsonReader(buffer));
        } catch (Exception unused) {
            return new Unsupported(str);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x0068  */
    private static OperationServerMessage readFromJson(JsonReader jsonReader) throws IOException {
        byte b;
        Utils.checkNotNull(jsonReader, "reader == null");
        Map<String, Object> map = new ResponseJsonStreamReader(jsonReader).toMap();
        String str = (String) map.get("id");
        switch ((String) map.get("type")) {
            case "complete":
                b = 4;
                break;
            case "ka":
                b = 5;
                break;
            case "data":
                b = 2;
                break;
            case "error":
                b = 3;
                break;
            case "connection_error":
                b = 0;
                break;
            case "connection_ack":
                b = 1;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return new ConnectionError(messagePayload(map));
        }
        if (b == 1) {
            return new ConnectionAcknowledge();
        }
        if (b == 2) {
            return new Data(str, messagePayload(map));
        }
        if (b == 3) {
            return new Error(str, messagePayload(map));
        }
        if (b == 4) {
            return new Complete(str);
        }
        if (b == 5) {
            return new ConnectionKeepAlive();
        }
        throw new IOException("Unsupported message");
    }

    private static Map<String, Object> messagePayload(Map<String, Object> map) {
        Map map2 = (Map) map.get(JSON_KEY_PAYLOAD);
        return map2 == null ? Collections.emptyMap() : Collections.unmodifiableMap(map2);
    }

    public static final class ConnectionError extends OperationServerMessage {
        public static final String TYPE = "connection_error";
        public final Map<String, Object> payload;

        public ConnectionError(Map<String, Object> map) {
            this.payload = map;
        }
    }

    public static final class Data extends OperationServerMessage {
        public static final String TYPE = "data";
        public final String id;
        public final Map<String, Object> payload;

        public Data(String str, Map<String, Object> map) {
            this.id = str;
            this.payload = map;
        }
    }

    public static final class Error extends OperationServerMessage {
        public static final String TYPE = "error";
        public final String id;
        public final Map<String, Object> payload;

        public Error(String str, Map<String, Object> map) {
            this.id = str;
            this.payload = map;
        }
    }

    public static final class Complete extends OperationServerMessage {
        public static final String TYPE = "complete";
        public final String id;

        public Complete(String str) {
            this.id = str;
        }
    }

    public static final class Unsupported extends OperationServerMessage {
        public final String rawMessage;

        public Unsupported(String str) {
            this.rawMessage = str;
        }
    }
}
