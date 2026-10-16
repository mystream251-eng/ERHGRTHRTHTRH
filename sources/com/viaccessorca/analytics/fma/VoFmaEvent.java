package com.viaccessorca.analytics.fma;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VoFmaEvent {
    private static final String TAG = "VO_FMA";
    private static SimpleDateFormat stDateFormat;
    private String mCustomType;
    private Dictionary<String, String> mData;
    private JSONObject mJsonFmaEvent;
    private String mOfflineTableName;
    private long mTime;
    private int mType;

    public enum EventKey {
        ASSET_ID,
        CHANNEL_ID,
        CHANNEL_NAME,
        CONTENT_TYPE,
        EPISODE_ID,
        NAME,
        PROGRAM_ID,
        PROGRAM_NAME,
        PROGRAM_OFFSET,
        PROVIDER_ID,
        STREAM_OFFSET,
        STREAM_TIME
    }

    public enum EventType {
        OPEN,
        PAUSE,
        PLAY,
        RESUME,
        SEEK_END,
        SEEK_START,
        SET_CHANNEL,
        SET_EPISODE,
        SET_MEDIA,
        SET_MOVIE,
        SET_PREVIEW,
        SET_PROGRAM,
        STOP
    }

    public enum VOPlayerSeverity {
        SEVERITY_INFO,
        SEVERITY_WARNING,
        SEVERITY_ERROR
    }

    public VoFmaEvent() {
        this.mType = 0;
        this.mData = new Hashtable();
        this.mJsonFmaEvent = null;
        this.mOfflineTableName = null;
    }

    protected VoFmaEvent(VoFmaEvent voFmaEvent) {
        this.mType = voFmaEvent.mType;
        this.mTime = voFmaEvent.mTime;
        this.mData = new Hashtable();
        this.mJsonFmaEvent = null;
        this.mOfflineTableName = null;
        Enumeration<String> enumerationKeys = voFmaEvent.mData.keys();
        while (enumerationKeys.hasMoreElements()) {
            String str = new String(enumerationKeys.nextElement());
            setCustomParameter(str, new String(voFmaEvent.mData.get(str)));
        }
    }

    protected VoFmaEvent(JSONObject jSONObject, String str) {
        this.mJsonFmaEvent = jSONObject;
        this.mOfflineTableName = str;
    }

    public VoFmaEvent setType(EventType eventType) {
        switch (AnonymousClass1.$SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[eventType.ordinal()]) {
            case 1:
                this.mType = 1;
                setCustomParameter("event_type", "OPEN");
                return this;
            case 2:
                this.mType = 2;
                setCustomParameter("event_type", "PAUSE");
                return this;
            case 3:
                this.mType = 3;
                setCustomParameter("event_type", "PLAY");
                return this;
            case 4:
                this.mType = 4;
                setCustomParameter("event_type", "RESUME");
                return this;
            case 5:
                this.mType = 5;
                setCustomParameter("event_type", "SEEK_END");
                return this;
            case 6:
                this.mType = 6;
                setCustomParameter("event_type", "SEEK_START");
                return this;
            case 7:
                this.mType = 7;
                setCustomParameter("event_type", "SET_CHANNEL");
                return this;
            case 8:
                this.mType = 8;
                setCustomParameter("event_type", "SET_EPISODE");
                return this;
            case 9:
                this.mType = 9;
                setCustomParameter("event_type", "SET_MEDIA");
                return this;
            case 10:
                this.mType = 10;
                setCustomParameter("event_type", "SET_MOVIE");
                return this;
            case 11:
                this.mType = 11;
                setCustomParameter("event_type", "SET_PREVIEW");
                return this;
            case 12:
                this.mType = 12;
                setCustomParameter("event_type", "SET_PROGRAM");
                return this;
            case 13:
                this.mType = 13;
                setCustomParameter("event_type", "STOP");
                return this;
            default:
                this.mType = 0;
                return this;
        }
    }

    public VoFmaEvent setCustomType(String str) {
        this.mType = 0;
        this.mCustomType = str;
        return setCustomParameter("event_type", str);
    }

    public String getCustomType() {
        return this.mCustomType;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:44:0x00a1  */
    public void setTypeFromTypeString(String str) {
        switch (str) {
            case "OPEN":
                this.mType = 1;
                break;
            case "PAUSE":
                this.mType = 2;
                break;
            case "PLAY":
                this.mType = 3;
                break;
            case "RESUME":
                this.mType = 4;
                break;
            case "SEEK_END":
                this.mType = 5;
                break;
            case "SEEK_START":
                this.mType = 6;
                break;
            case "SET_CHANNEL":
                this.mType = 7;
                break;
            case "SET_EPISODE":
                this.mType = 8;
                break;
            case "SET_MEDIA":
                this.mType = 9;
                break;
            case "SET_MOVIE":
                this.mType = 10;
                break;
            case "SET_PREVIEW":
                this.mType = 11;
                break;
            case "SET_PROGRAM":
                this.mType = 12;
                break;
            case "STOP":
                this.mType = 13;
                break;
            default:
                this.mType = 0;
                break;
        }
    }

    /* JADX INFO: renamed from: com.viaccessorca.analytics.fma.VoFmaEvent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey;
        static final /* synthetic */ int[] $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType;

        static {
            int[] iArr = new int[EventKey.values().length];
            $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey = iArr;
            try {
                iArr[EventKey.ASSET_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.CHANNEL_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.CHANNEL_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.CONTENT_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.EPISODE_ID.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.NAME.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.PROGRAM_ID.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.PROGRAM_NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.PROGRAM_OFFSET.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.PROVIDER_ID.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.STREAM_OFFSET.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[EventKey.STREAM_TIME.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[EventType.values().length];
            $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType = iArr2;
            try {
                iArr2[EventType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.PLAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.RESUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.SEEK_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.SEEK_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.SET_CHANNEL.ordinal()] = 7;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.SET_EPISODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.SET_MEDIA.ordinal()] = 9;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.SET_MOVIE.ordinal()] = 10;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.SET_PREVIEW.ordinal()] = 11;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.SET_PROGRAM.ordinal()] = 12;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventType[EventType.STOP.ordinal()] = 13;
            } catch (NoSuchFieldError unused25) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public VoFmaEvent setParameter(EventKey eventKey, String str) {
        switch (AnonymousClass1.$SwitchMap$com$viaccessorca$analytics$fma$VoFmaEvent$EventKey[eventKey.ordinal()]) {
            case 1:
                setCustomParameter("asset_id", str);
                return this;
            case 2:
                setCustomParameter("channel_id", str);
                return this;
            case 3:
                setCustomParameter("channel_name", str);
                return this;
            case 4:
                setCustomParameter("content_type", str);
                return this;
            case 5:
                setCustomParameter("episode_id", str);
                return this;
            case 6:
                setCustomParameter("name", str);
                return this;
            case 7:
                setCustomParameter("program_id", str);
                return this;
            case 8:
                setCustomParameter("program_name", str);
                return this;
            case 9:
                setCustomParameter("program_offset", str);
                return this;
            case 10:
                setCustomParameter("provider_id", str);
                return this;
            case 11:
                setCustomParameter("stream_offset", str);
                return this;
            case 12:
                setCustomParameter("stream_time", str);
                return this;
            default:
                return this;
        }
    }

    public VoFmaEvent setCustomParameter(String str, String str2) {
        if (keyExists(str)) {
            keyDelete(str);
        }
        if (str2 != null) {
            this.mData.put(str, str2);
        }
        return this;
    }

    public static String generateTimeString(long j) {
        if (stDateFormat == null) {
            try {
                stDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stDateFormat.format(new Date(j));
    }

    public String getTime() {
        if (stDateFormat == null) {
            try {
                stDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stDateFormat.format(new Date(this.mTime));
    }

    protected void setCurrentTime() {
        this.mTime = System.currentTimeMillis();
    }

    public Dictionary<String, String> getData() {
        return this.mData;
    }

    protected long getCurrentTime() {
        return this.mTime;
    }

    protected JSONObject getJsonEvent() {
        return this.mJsonFmaEvent;
    }

    protected String getOfflineTableName() {
        return this.mOfflineTableName;
    }

    protected JSONObject getJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            Enumeration<String> enumerationKeys = this.mData.keys();
            while (enumerationKeys.hasMoreElements()) {
                String strNextElement = enumerationKeys.nextElement();
                if (strNextElement == "root_cause_analysis" || strNextElement == "test_url_status" || strNextElement == "log_event") {
                    jSONObject.put(strNextElement, new JSONObject(this.mData.get(strNextElement)));
                } else {
                    jSONObject.put(strNextElement, this.mData.get(strNextElement));
                }
            }
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getType() {
        return this.mType;
    }

    protected String getValue(String str) {
        return this.mData.get(str);
    }

    protected boolean keyExists(String str) {
        return (str == null || this.mData.get(str) == null) ? false : true;
    }

    protected void keyDelete(String str) {
        this.mData.remove(str);
    }
}
