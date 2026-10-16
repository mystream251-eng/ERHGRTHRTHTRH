package io.sentry;

import androidx.core.app.NotificationCompat;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.viaccessorca.analytics.fma.FmaDatabaseHelper;
import io.sentry.util.Objects;
import io.sentry.util.StringUtils;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public final class SessionAdapter extends TypeAdapter<Session> {
    private final SentryOptions options;

    public SessionAdapter(SentryOptions sentryOptions) {
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "The SentryOptions is required.");
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Session session) throws IOException {
        if (session == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        if (session.getSessionId() != null) {
            jsonWriter.name("sid").value(session.getSessionId().toString());
        }
        if (session.getDistinctId() != null) {
            jsonWriter.name("did").value(session.getDistinctId());
        }
        if (session.getInit() != null) {
            jsonWriter.name("init").value(session.getInit());
        }
        Date started = session.getStarted();
        if (started != null) {
            jsonWriter.name("started").value(DateUtils.getTimestamp(started));
        }
        Session.State status = session.getStatus();
        if (status != null) {
            jsonWriter.name(NotificationCompat.CATEGORY_STATUS).value(status.name().toLowerCase(Locale.ROOT));
        }
        if (session.getSequence() != null) {
            jsonWriter.name("seq").value(session.getSequence());
        }
        int iErrorCount = session.errorCount();
        if (iErrorCount > 0) {
            jsonWriter.name("errors").value(iErrorCount);
        }
        if (session.getDuration() != null) {
            jsonWriter.name("duration").value(session.getDuration());
        }
        if (session.getTimestamp() != null) {
            jsonWriter.name(FmaDatabaseHelper.CONST_TIMESTAMP).value(DateUtils.getTimestamp(session.getTimestamp()));
        }
        boolean zInitAttrs = initAttrs(jsonWriter, false);
        jsonWriter.name("release").value(session.getRelease());
        if (session.getEnvironment() != null) {
            zInitAttrs = initAttrs(jsonWriter, zInitAttrs);
            jsonWriter.name("environment").value(session.getEnvironment());
        }
        if (session.getIpAddress() != null) {
            zInitAttrs = initAttrs(jsonWriter, zInitAttrs);
            jsonWriter.name("ip_address").value(session.getIpAddress());
        }
        if (session.getUserAgent() != null) {
            zInitAttrs = initAttrs(jsonWriter, zInitAttrs);
            jsonWriter.name("user_agent").value(session.getUserAgent());
        }
        if (zInitAttrs) {
            jsonWriter.endObject();
        }
        jsonWriter.endObject();
    }

    private boolean initAttrs(JsonWriter jsonWriter, boolean z) throws IOException {
        if (z) {
            return true;
        }
        jsonWriter.name("attrs").beginObject();
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Session read(JsonReader jsonReader) throws IOException {
        String strNextString;
        String strCapitalize;
        byte b;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.beginObject();
        Session.State stateValueOf = null;
        Date dateConverTimeStamp = null;
        Date dateConverTimeStamp2 = null;
        String strNextString2 = null;
        UUID uuidFromString = null;
        Boolean boolValueOf = null;
        Long lValueOf = null;
        Double dValueOf = null;
        String strNextString3 = null;
        String str = null;
        String strNextString4 = null;
        String strNextString5 = null;
        int iNextInt = 0;
        while (jsonReader.hasNext()) {
            String strNextString6 = str;
            switch (jsonReader.nextName()) {
                case "sid":
                    try {
                        strNextString = jsonReader.nextString();
                        try {
                            uuidFromString = UUID.fromString(strNextString);
                        } catch (IllegalArgumentException unused) {
                            this.options.getLogger().log(SentryLevel.ERROR, "%s sid is not valid.", strNextString);
                        }
                        break;
                    } catch (IllegalArgumentException unused2) {
                        strNextString = null;
                    }
                    break;
                case "did":
                    strNextString2 = jsonReader.nextString();
                    break;
                case "init":
                    boolValueOf = Boolean.valueOf(jsonReader.nextBoolean());
                    break;
                case "started":
                    dateConverTimeStamp = converTimeStamp(jsonReader.nextString(), "started");
                    break;
                case "status":
                    try {
                        strCapitalize = StringUtils.capitalize(jsonReader.nextString());
                        if (strCapitalize != null) {
                            try {
                                stateValueOf = Session.State.valueOf(strCapitalize);
                            } catch (IllegalArgumentException unused3) {
                                this.options.getLogger().log(SentryLevel.ERROR, "%s status is not valid.", strCapitalize);
                            }
                        }
                        break;
                    } catch (IllegalArgumentException unused4) {
                        strCapitalize = null;
                    }
                    break;
                case "errors":
                    iNextInt = jsonReader.nextInt();
                    break;
                case "seq":
                    lValueOf = Long.valueOf(jsonReader.nextLong());
                    break;
                case "duration":
                    dValueOf = Double.valueOf(jsonReader.nextDouble());
                    break;
                case "timestamp":
                    dateConverTimeStamp2 = converTimeStamp(jsonReader.nextString(), FmaDatabaseHelper.CONST_TIMESTAMP);
                    break;
                case "attrs":
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        switch (jsonReader.nextName()) {
                            case "environment":
                                b = 1;
                                break;
                            case "release":
                                b = 0;
                                break;
                            case "ip_address":
                                b = 2;
                                break;
                            case "user_agent":
                                b = 3;
                                break;
                            default:
                                b = -1;
                                break;
                        }
                        if (b == 0) {
                            strNextString5 = jsonReader.nextString();
                        } else if (b == 1) {
                            strNextString4 = jsonReader.nextString();
                        } else if (b == 2) {
                            strNextString3 = jsonReader.nextString();
                        } else if (b == 3) {
                            strNextString6 = jsonReader.nextString();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
            str = strNextString6;
        }
        String str2 = str;
        jsonReader.endObject();
        if (stateValueOf == null || dateConverTimeStamp == null || strNextString5 == null || strNextString5.isEmpty()) {
            this.options.getLogger().log(SentryLevel.ERROR, "Session is gonna be dropped due to invalid fields.", new Object[0]);
            return null;
        }
        return new Session(stateValueOf, dateConverTimeStamp, dateConverTimeStamp2, iNextInt, strNextString2, uuidFromString, boolValueOf, lValueOf, dValueOf, strNextString3, str2, strNextString4, strNextString5);
    }

    private Date converTimeStamp(String str, String str2) {
        try {
            return DateUtils.getDateTime(str);
        } catch (IllegalArgumentException e) {
            this.options.getLogger().log(SentryLevel.ERROR, e, "Error converting session (%s) field.", str2);
            return null;
        }
    }
}
