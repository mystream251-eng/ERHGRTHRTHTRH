package com.apollographql.apollo.subscription;

import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.Subscription;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.api.internal.json.JsonWriter;
import java.io.IOException;
import java.util.Map;
import okio.Buffer;

/* JADX INFO: loaded from: classes.dex */
public abstract class OperationClientMessage {
    static final String JSON_KEY_ID = "id";
    static final String JSON_KEY_PAYLOAD = "payload";
    static final String JSON_KEY_TYPE = "type";

    public abstract void writeToJson(JsonWriter jsonWriter) throws IOException;

    OperationClientMessage() {
    }

    public String toJsonString() {
        try {
            Buffer buffer = new Buffer();
            JsonWriter jsonWriterOf = JsonWriter.of(buffer);
            jsonWriterOf.beginObject();
            writeToJson(jsonWriterOf);
            jsonWriterOf.endObject();
            jsonWriterOf.close();
            return buffer.readUtf8();
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize to json", e);
        }
    }

    public static final class Init extends OperationClientMessage {
        private static final String TYPE = "connection_init";
        private final Map<String, Object> connectionParams;

        public Init(Map<String, Object> map) {
            this.connectionParams = (Map) Utils.checkNotNull(map, "connectionParams == null");
        }

        @Override // com.apollographql.apollo.subscription.OperationClientMessage
        public void writeToJson(JsonWriter jsonWriter) throws IOException {
            Utils.checkNotNull(jsonWriter, "writer == null");
            jsonWriter.name("type").value(TYPE);
            if (this.connectionParams.isEmpty()) {
                return;
            }
            jsonWriter.name(OperationClientMessage.JSON_KEY_PAYLOAD);
            com.apollographql.apollo.api.internal.json.Utils.writeToJson(this.connectionParams, jsonWriter);
        }
    }

    public static final class Start extends OperationClientMessage {
        private static final String JSON_KEY_EXTENSIONS = "extensions";
        private static final String JSON_KEY_EXTENSIONS_PERSISTED_QUERY = "persistedQuery";
        private static final String JSON_KEY_EXTENSIONS_PERSISTED_QUERY_HASH = "sha256Hash";
        private static final String JSON_KEY_EXTENSIONS_PERSISTED_QUERY_VERSION = "version";
        private static final String JSON_KEY_OPERATION_NAME = "operationName";
        private static final String JSON_KEY_QUERY = "query";
        private static final String JSON_KEY_VARIABLES = "variables";
        private static final String TYPE = "start";
        public final boolean autoPersistSubscription;
        private final ScalarTypeAdapters scalarTypeAdapters;
        public final boolean sendSubscriptionDocument;
        public final Subscription<?, ?, ?> subscription;
        public final String subscriptionId;

        public Start(String str, Subscription<?, ?, ?> subscription, ScalarTypeAdapters scalarTypeAdapters, boolean z, boolean z2) {
            this.subscriptionId = (String) Utils.checkNotNull(str, "subscriptionId == null");
            this.subscription = (Subscription) Utils.checkNotNull(subscription, "subscription == null");
            this.scalarTypeAdapters = (ScalarTypeAdapters) Utils.checkNotNull(scalarTypeAdapters, "scalarTypeAdapters == null");
            this.autoPersistSubscription = z;
            this.sendSubscriptionDocument = z2;
        }

        /* JADX WARN: Type inference failed for: r1v3, types: [com.apollographql.apollo.api.Operation$Variables] */
        @Override // com.apollographql.apollo.subscription.OperationClientMessage
        public void writeToJson(JsonWriter jsonWriter) throws IOException {
            Utils.checkNotNull(jsonWriter, "writer == null");
            jsonWriter.name("id").value(this.subscriptionId);
            jsonWriter.name("type").value("start");
            jsonWriter.name(OperationClientMessage.JSON_KEY_PAYLOAD).beginObject();
            jsonWriter.name(JSON_KEY_VARIABLES).jsonValue(this.subscription.variables().marshal(this.scalarTypeAdapters));
            jsonWriter.name(JSON_KEY_OPERATION_NAME).value(this.subscription.name().name());
            if (!this.autoPersistSubscription || this.sendSubscriptionDocument) {
                jsonWriter.name("query").value(this.subscription.queryDocument());
            }
            if (this.autoPersistSubscription) {
                jsonWriter.name(JSON_KEY_EXTENSIONS).beginObject().name(JSON_KEY_EXTENSIONS_PERSISTED_QUERY).beginObject().name(JSON_KEY_EXTENSIONS_PERSISTED_QUERY_VERSION).value(1L).name(JSON_KEY_EXTENSIONS_PERSISTED_QUERY_HASH).value(this.subscription.operationId()).endObject().endObject();
            }
            jsonWriter.endObject();
        }
    }

    public static final class Stop extends OperationClientMessage {
        private static final String TYPE = "stop";
        public final String subscriptionId;

        public Stop(String str) {
            this.subscriptionId = (String) Utils.checkNotNull(str, "subscriptionId == null");
        }

        @Override // com.apollographql.apollo.subscription.OperationClientMessage
        public void writeToJson(JsonWriter jsonWriter) throws IOException {
            Utils.checkNotNull(jsonWriter, "writer == null");
            jsonWriter.name("id").value(this.subscriptionId);
            jsonWriter.name("type").value(TYPE);
        }
    }

    public static final class Terminate extends OperationClientMessage {
        private static final String TYPE = "connection_terminate";

        @Override // com.apollographql.apollo.subscription.OperationClientMessage
        public void writeToJson(JsonWriter jsonWriter) throws IOException {
            Utils.checkNotNull(jsonWriter, "writer == null");
            jsonWriter.name("type").value(TYPE);
        }
    }
}
