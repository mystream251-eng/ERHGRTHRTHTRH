package io.sentry;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.sentry.util.StringUtils;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryEnvelopeItemHeaderAdapter extends TypeAdapter<SentryEnvelopeItemHeader> {
    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, SentryEnvelopeItemHeader sentryEnvelopeItemHeader) throws IOException {
        if (sentryEnvelopeItemHeader == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        if (sentryEnvelopeItemHeader.getContentType() != null) {
            jsonWriter.name("content_type");
            jsonWriter.value(sentryEnvelopeItemHeader.getContentType());
        }
        if (sentryEnvelopeItemHeader.getFileName() != null) {
            jsonWriter.name("filename");
            jsonWriter.value(sentryEnvelopeItemHeader.getFileName());
        }
        if (!SentryItemType.Unknown.equals(sentryEnvelopeItemHeader.getType())) {
            jsonWriter.name("type");
            jsonWriter.value(sentryEnvelopeItemHeader.getType().getItemType().toLowerCase(Locale.ROOT));
        }
        if (sentryEnvelopeItemHeader.getAttachmentType() != null) {
            jsonWriter.name("attachment_type");
            jsonWriter.value(sentryEnvelopeItemHeader.getAttachmentType());
        }
        jsonWriter.name("length");
        jsonWriter.value(sentryEnvelopeItemHeader.getLength());
        jsonWriter.endObject();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.gson.TypeAdapter
    public SentryEnvelopeItemHeader read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        SentryItemType sentryItemType = SentryItemType.Unknown;
        jsonReader.beginObject();
        SentryItemType sentryItemTypeValueOf = sentryItemType;
        int iNextInt = 0;
        String strNextString = null;
        String strNextString2 = null;
        String strNextString3 = null;
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            byte b = -1;
            switch (strNextName.hashCode()) {
                case -1106363674:
                    if (strNextName.equals("length")) {
                        b = 3;
                    }
                    break;
                case -734768633:
                    if (strNextName.equals("filename")) {
                        b = 1;
                    }
                    break;
                case -672977706:
                    if (strNextName.equals("attachment_type")) {
                        b = 4;
                    }
                    break;
                case 3575610:
                    if (strNextName.equals("type")) {
                        b = 2;
                    }
                    break;
                case 831846208:
                    if (strNextName.equals("content_type")) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                strNextString = jsonReader.nextString();
            } else if (b == 1) {
                strNextString2 = jsonReader.nextString();
            } else if (b == 2) {
                try {
                    String strNextString4 = jsonReader.nextString();
                    if (strNextString4 != null) {
                        if (strNextString4.equalsIgnoreCase("user_report")) {
                            sentryItemTypeValueOf = SentryItemType.UserFeedback;
                        } else {
                            String strCapitalize = StringUtils.capitalize(strNextString4);
                            if (strCapitalize != null) {
                                sentryItemTypeValueOf = SentryItemType.valueOf(strCapitalize);
                            }
                        }
                    }
                } catch (IllegalArgumentException unused) {
                }
            } else if (b == 3) {
                iNextInt = jsonReader.nextInt();
            } else if (b == 4) {
                strNextString3 = jsonReader.nextString();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return new SentryEnvelopeItemHeader(sentryItemTypeValueOf, iNextInt, strNextString, strNextString2, strNextString3);
    }
}
