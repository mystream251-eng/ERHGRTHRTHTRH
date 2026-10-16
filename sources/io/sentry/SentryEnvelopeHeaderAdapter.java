package io.sentry;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.sentry.protocol.SdkVersion;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryPackage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryEnvelopeHeaderAdapter extends TypeAdapter<SentryEnvelopeHeader> {
    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, SentryEnvelopeHeader sentryEnvelopeHeader) throws IOException {
        if (sentryEnvelopeHeader == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        if (sentryEnvelopeHeader.getEventId() != null) {
            jsonWriter.name("event_id");
            jsonWriter.value(sentryEnvelopeHeader.getEventId().toString());
        }
        SdkVersion sdkVersion = sentryEnvelopeHeader.getSdkVersion();
        if (sdkVersion != null && hasValidSdkVersion(sdkVersion)) {
            jsonWriter.name("sdk").beginObject();
            jsonWriter.name("name").value(sdkVersion.getName());
            jsonWriter.name("version").value(sdkVersion.getVersion());
            List<String> integrations = sdkVersion.getIntegrations();
            if (integrations != null) {
                jsonWriter.name("integrations").beginArray();
                Iterator<String> it = integrations.iterator();
                while (it.hasNext()) {
                    jsonWriter.value(it.next());
                }
                jsonWriter.endArray();
            }
            List<SentryPackage> packages = sdkVersion.getPackages();
            if (packages != null) {
                jsonWriter.name("packages").beginArray();
                for (SentryPackage sentryPackage : packages) {
                    jsonWriter.beginObject();
                    jsonWriter.name("name").value(sentryPackage.getName());
                    jsonWriter.name("version").value(sentryPackage.getVersion());
                    jsonWriter.endObject();
                }
                jsonWriter.endArray();
            }
            jsonWriter.endObject();
        }
        TraceState trace = sentryEnvelopeHeader.getTrace();
        if (trace != null) {
            jsonWriter.name(SpanContext.TYPE);
            jsonWriter.beginObject();
            jsonWriter.name("trace_id").value(trace.getTraceId().toString());
            jsonWriter.name("public_key").value(trace.getPublicKey());
            if (trace.getRelease() != null) {
                jsonWriter.name("release").value(trace.getRelease());
            }
            if (trace.getEnvironment() != null) {
                jsonWriter.name("environment").value(trace.getEnvironment());
            }
            if (trace.getTransaction() != null) {
                jsonWriter.name("transaction").value(trace.getTransaction());
            }
            if (trace.getUser() != null && (trace.getUser().getId() != null || trace.getUser().getSegment() != null)) {
                jsonWriter.name(ToolboxAnalyticsModuleKt.KEY_USER);
                jsonWriter.beginObject();
                if (trace.getUser().getId() != null) {
                    jsonWriter.name("id").value(trace.getUser().getId());
                }
                if (trace.getUser().getSegment() != null) {
                    jsonWriter.name("segment").value(trace.getUser().getSegment());
                }
                jsonWriter.endObject();
            }
            jsonWriter.endObject();
        }
        jsonWriter.endObject();
    }

    private boolean hasValidSdkVersion(SdkVersion sdkVersion) {
        return (sdkVersion.getName() == null || sdkVersion.getName().isEmpty() || sdkVersion.getVersion() == null || sdkVersion.getVersion().isEmpty()) ? false : true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Code duplicated, block: B:118:0x0192  */
    /* JADX WARN: Code duplicated, block: B:141:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:25:0x0051  */
    /* JADX WARN: Code duplicated, block: B:55:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:77:0x00fa  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v5 */
    @Override // com.google.gson.TypeAdapter
    public SentryEnvelopeHeader read(JsonReader jsonReader) throws IOException {
        byte b;
        byte b2;
        byte b3;
        byte b4;
        byte b5;
        String str = null;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.beginObject();
        SentryId sentryId = null;
        SdkVersion sdkVersion = null;
        TraceState traceState = null;
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            int iHashCode = strNextName.hashCode();
            if (iHashCode != 113722) {
                if (iHashCode != 110620997) {
                    if (iHashCode == 278118624 && strNextName.equals("event_id")) {
                        b = 0;
                    } else {
                        b = -1;
                    }
                } else if (strNextName.equals(SpanContext.TYPE)) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (strNextName.equals("sdk")) {
                b = 1;
            } else {
                b = -1;
            }
            if (b == 0) {
                sentryId = new SentryId(jsonReader.nextString());
            } else if (b == 1) {
                jsonReader.beginObject();
                sdkVersion = new SdkVersion();
                while (jsonReader.hasNext()) {
                    switch (jsonReader.nextName()) {
                        case "name":
                            b2 = 0;
                            break;
                        case "version":
                            b2 = 1;
                            break;
                        case "packages":
                            b2 = 3;
                            break;
                        case "integrations":
                            b2 = 2;
                            break;
                        default:
                            b2 = -1;
                            break;
                    }
                    if (b2 == 0) {
                        sdkVersion.setName(jsonReader.nextString());
                    } else if (b2 == 1) {
                        sdkVersion.setVersion(jsonReader.nextString());
                    } else if (b2 == 2) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            String strNextString = jsonReader.nextString();
                            if (strNextString != null) {
                                sdkVersion.addIntegration(strNextString);
                            }
                        }
                        jsonReader.endArray();
                    } else if (b2 == 3) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject();
                            String strNextString2 = null;
                            String strNextString3 = null;
                            while (jsonReader.hasNext()) {
                                String strNextName2 = jsonReader.nextName();
                                int iHashCode2 = strNextName2.hashCode();
                                if (iHashCode2 != 3373707) {
                                    if (iHashCode2 == 351608024 && strNextName2.equals("version")) {
                                        b3 = 1;
                                    } else {
                                        b3 = -1;
                                    }
                                } else if (strNextName2.equals("name")) {
                                    b3 = 0;
                                } else {
                                    b3 = -1;
                                }
                                if (b3 == 0) {
                                    strNextString2 = jsonReader.nextString();
                                } else if (b3 == 1) {
                                    strNextString3 = jsonReader.nextString();
                                } else {
                                    jsonReader.skipValue();
                                }
                            }
                            if (strNextString2 != null && strNextString3 != null) {
                                sdkVersion.addPackage(strNextString2, strNextString3);
                            }
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            } else if (b == 2) {
                jsonReader.beginObject();
                String strNextString4 = str;
                String strNextString5 = strNextString4;
                String str2 = strNextString5;
                String str3 = str2;
                String str4 = str3;
                String str5 = str4;
                String strNextString6 = str5;
                SentryId sentryId2 = str2;
                String strNextString7 = str3;
                String strNextString8 = str4;
                String strNextString9 = str5;
                while (jsonReader.hasNext()) {
                    switch (jsonReader.nextName()) {
                        case "environment":
                            b4 = 3;
                            break;
                        case "user":
                            b4 = 5;
                            break;
                        case "release":
                            b4 = 2;
                            break;
                        case "trace_id":
                            b4 = 0;
                            break;
                        case "public_key":
                            b4 = 1;
                            break;
                        case "transaction":
                            b4 = 4;
                            break;
                        default:
                            b4 = -1;
                            break;
                    }
                    if (b4 == 0) {
                        sentryId2 = new SentryId(jsonReader.nextString());
                    } else if (b4 == 1) {
                        strNextString7 = jsonReader.nextString();
                    } else if (b4 == 2) {
                        strNextString8 = jsonReader.nextString();
                    } else if (b4 == 3) {
                        strNextString9 = jsonReader.nextString();
                    } else if (b4 == 4) {
                        strNextString6 = jsonReader.nextString();
                    } else if (b4 == 5) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String strNextName3 = jsonReader.nextName();
                            int iHashCode3 = strNextName3.hashCode();
                            if (iHashCode3 != 3355) {
                                if (iHashCode3 == 1973722931 && strNextName3.equals("segment")) {
                                    b5 = 1;
                                } else {
                                    b5 = -1;
                                }
                            } else if (strNextName3.equals("id")) {
                                b5 = 0;
                            } else {
                                b5 = -1;
                            }
                            if (b5 == 0) {
                                strNextString4 = jsonReader.nextString();
                            } else if (b5 == 1) {
                                strNextString5 = jsonReader.nextString();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                    } else {
                        jsonReader.skipValue();
                    }
                    sentryId2 = sentryId2;
                    strNextString7 = strNextString7;
                    strNextString8 = strNextString8;
                    strNextString9 = strNextString9;
                    strNextString6 = strNextString6;
                }
                if (sentryId2 != 0 && strNextString7 != null) {
                    traceState = new TraceState(sentryId2, strNextString7, strNextString8, strNextString9, (strNextString4 == null && strNextString5 == null) ? null : new TraceState.TraceStateUser(strNextString4, strNextString5), strNextString6);
                }
                jsonReader.endObject();
            } else {
                jsonReader.skipValue();
            }
            str = null;
        }
        jsonReader.endObject();
        return new SentryEnvelopeHeader(sentryId, sdkVersion, traceState);
    }
}
