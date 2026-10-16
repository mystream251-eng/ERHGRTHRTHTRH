package com.viaccessorca.analytics.dca;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VoDcaConfiguration {
    private static final String TAG = "VO_DCA";
    public String mApplicationName;
    public String mApplicationVersion;
    public int mCatchupPauseResume_StateConfirmationPeriod;
    public int mCatchupPlayStop_StateConfirmationPeriod;
    public boolean mCatchupSeek_SendDuringPause;
    public int mCatchupSeek_StateConfirmationPeriod;
    public String mDcaVersion;
    public String mDeviceCategory;
    public String mDeviceID;
    public String mDeviceModel;
    public int mDownloadPauseResume_StateConfirmationPeriod;
    public int mDownloadPlayStop_StateConfirmationPeriod;
    public boolean mDownloadSeek_SendDuringPause;
    public int mDownloadSeek_StateConfirmationPeriod;
    public String[] mEventFieldValidateNames;
    public String[][] mEventFieldsValidatePerEvent;
    public int mLivePauseResume_StateConfirmationPeriod;
    public int mLivePlayStop_StateConfirmationPeriod;
    public boolean mLiveSeek_SendDuringPause;
    public int mLiveSeek_StateConfirmationPeriod;
    public String[] mMappingEventTypes;
    public String[] mMappingEventUrls;
    public int mMax_StateConfirmationPeriod;
    public int mNpvrPauseResume_StateConfirmationPeriod;
    public int mNpvrPlayStop_StateConfirmationPeriod;
    public boolean mNpvrSeek_SendDuringPause;
    public int mNpvrSeek_StateConfirmationPeriod;
    public String mOperatorName;
    public boolean mParsingSuccess;
    public int mPreviewPauseResume_StateConfirmationPeriod;
    public int mPreviewPlayStop_StateConfirmationPeriod;
    public boolean mPreviewSeek_SendDuringPause;
    public int mPreviewSeek_StateConfirmationPeriod;
    public int mStartoverPauseResume_StateConfirmationPeriod;
    public int mStartoverPlayStop_StateConfirmationPeriod;
    public boolean mStartoverSeek_SendDuringPause;
    public int mStartoverSeek_StateConfirmationPeriod;
    public int mVodPauseResume_StateConfirmationPeriod;
    public int mVodPlayStop_StateConfirmationPeriod;
    public boolean mVodSeek_SendDuringPause;
    public int mVodSeek_StateConfirmationPeriod;

    protected void calcMaxconfirmationPeriod() {
        this.mMax_StateConfirmationPeriod = 0;
        int i = this.mLivePlayStop_StateConfirmationPeriod;
        if (i > 0) {
            this.mMax_StateConfirmationPeriod = i;
        }
        int i2 = this.mLivePauseResume_StateConfirmationPeriod;
        if (i2 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i2;
        }
        int i3 = this.mLiveSeek_StateConfirmationPeriod;
        if (i3 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i3;
        }
        int i4 = this.mStartoverPlayStop_StateConfirmationPeriod;
        if (i4 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i4;
        }
        int i5 = this.mStartoverPauseResume_StateConfirmationPeriod;
        if (i5 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i5;
        }
        int i6 = this.mStartoverSeek_StateConfirmationPeriod;
        if (i6 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i6;
        }
        int i7 = this.mCatchupPlayStop_StateConfirmationPeriod;
        if (i7 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i7;
        }
        int i8 = this.mCatchupPauseResume_StateConfirmationPeriod;
        if (i8 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i8;
        }
        int i9 = this.mCatchupSeek_StateConfirmationPeriod;
        if (i9 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i9;
        }
        int i10 = this.mVodPlayStop_StateConfirmationPeriod;
        if (i10 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i10;
        }
        int i11 = this.mVodPauseResume_StateConfirmationPeriod;
        if (i11 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i11;
        }
        int i12 = this.mVodSeek_StateConfirmationPeriod;
        if (i12 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i12;
        }
        int i13 = this.mPreviewPlayStop_StateConfirmationPeriod;
        if (i13 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i13;
        }
        int i14 = this.mPreviewPauseResume_StateConfirmationPeriod;
        if (i14 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i14;
        }
        int i15 = this.mPreviewSeek_StateConfirmationPeriod;
        if (i15 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i15;
        }
        int i16 = this.mNpvrPlayStop_StateConfirmationPeriod;
        if (i16 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i16;
        }
        int i17 = this.mNpvrPauseResume_StateConfirmationPeriod;
        if (i17 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i17;
        }
        int i18 = this.mNpvrSeek_StateConfirmationPeriod;
        if (i18 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i18;
        }
        int i19 = this.mDownloadPlayStop_StateConfirmationPeriod;
        if (i19 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i19;
        }
        int i20 = this.mDownloadPauseResume_StateConfirmationPeriod;
        if (i20 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i20;
        }
        int i21 = this.mDownloadSeek_StateConfirmationPeriod;
        if (i21 > this.mMax_StateConfirmationPeriod) {
            this.mMax_StateConfirmationPeriod = i21;
        }
    }

    protected String getUrlByEventType(String str) {
        int i = 0;
        while (true) {
            String[] strArr = this.mMappingEventTypes;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return this.mMappingEventUrls[i];
            }
            i++;
        }
    }

    protected boolean getConfParsingResult() {
        return this.mParsingSuccess;
    }

    protected boolean eventFieldValidate(VoDcaEvent voDcaEvent) {
        String value = voDcaEvent.getValue("eventType");
        if (value == null) {
            Log.v(TAG, "DCA: No eventType field found in input event");
        } else {
            if (this.mEventFieldValidateNames == null) {
                Log.v(TAG, "DCA: Error in the given configuration, eventName field is null ");
                return false;
            }
            int i = 0;
            while (true) {
                String[] strArr = this.mEventFieldValidateNames;
                if (i >= strArr.length || value.equals(strArr[i])) {
                    break;
                }
                i++;
            }
            if (i == this.mEventFieldValidateNames.length) {
                return true;
            }
            String[][] strArr2 = this.mEventFieldsValidatePerEvent;
            if (strArr2[i] == null) {
                Log.v(TAG, "DCA: Error in the given configuration, mandatory field is null ");
                return false;
            }
            String[] strArr3 = strArr2[i];
            for (int i2 = 0; i2 < strArr3.length; i2++) {
                if (!voDcaEvent.keyExists(strArr3[i2])) {
                    Log.e(TAG, "DCA event of type " + value + " has the missing mandatory field: " + strArr3[i2]);
                    return false;
                }
            }
        }
        return true;
    }

    VoDcaController.VoDcaReturnCode parse(String str) {
        int i;
        String str2;
        this.mOperatorName = VOPlaybackSessionReport.UNKNOWN_SVALUE;
        this.mLivePlayStop_StateConfirmationPeriod = 0;
        this.mLivePauseResume_StateConfirmationPeriod = 0;
        this.mLiveSeek_StateConfirmationPeriod = 0;
        this.mStartoverPlayStop_StateConfirmationPeriod = 0;
        this.mStartoverPauseResume_StateConfirmationPeriod = 0;
        this.mStartoverSeek_StateConfirmationPeriod = 0;
        this.mCatchupPlayStop_StateConfirmationPeriod = 0;
        this.mCatchupPauseResume_StateConfirmationPeriod = 0;
        this.mCatchupSeek_StateConfirmationPeriod = 0;
        this.mVodPlayStop_StateConfirmationPeriod = 0;
        this.mVodPauseResume_StateConfirmationPeriod = 0;
        this.mVodSeek_StateConfirmationPeriod = 0;
        this.mPreviewPlayStop_StateConfirmationPeriod = 0;
        this.mPreviewPauseResume_StateConfirmationPeriod = 0;
        this.mPreviewSeek_StateConfirmationPeriod = 0;
        this.mNpvrPlayStop_StateConfirmationPeriod = 0;
        this.mNpvrPauseResume_StateConfirmationPeriod = 0;
        this.mNpvrSeek_StateConfirmationPeriod = 0;
        this.mDownloadPlayStop_StateConfirmationPeriod = 0;
        this.mDownloadPauseResume_StateConfirmationPeriod = 0;
        this.mDownloadSeek_StateConfirmationPeriod = 0;
        this.mLiveSeek_SendDuringPause = false;
        this.mStartoverSeek_SendDuringPause = false;
        this.mCatchupSeek_SendDuringPause = false;
        this.mVodSeek_SendDuringPause = false;
        this.mPreviewSeek_SendDuringPause = false;
        this.mNpvrSeek_SendDuringPause = false;
        this.mDownloadSeek_SendDuringPause = false;
        Boolean bool = true;
        this.mParsingSuccess = true;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.isNull("operatorName")) {
                    Log.v(TAG, "DCA: Error parsing configuration, No OperatorName field found");
                } else {
                    String string = jSONObject.getString("operatorName");
                    if (string != null) {
                        this.mOperatorName = string;
                    }
                }
                if (jSONObject.isNull("dispatcher")) {
                    Log.v(TAG, "DCA: Error parsing configuration, No dispatcher field found");
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("dispatcher");
                if (jSONObject2.isNull("backend")) {
                    Log.v(TAG, "DCA: Error parsing configuration, No backend field found");
                    this.mParsingSuccess = false;
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                JSONArray jSONArray = jSONObject2.getJSONArray("backend");
                int length = jSONArray.length();
                String[] strArr = new String[length];
                if (jSONArray.length() == 0) {
                    Log.v(TAG, "DCA: Error parsing configuration, backend is empty");
                    this.mParsingSuccess = false;
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                String[] strArr2 = new String[jSONArray.length()];
                int i2 = 0;
                while (i2 < jSONArray.length()) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                    strArr[i2] = jSONObject3.keys().next();
                    strArr2[i2] = jSONObject3.getString(strArr[i2]);
                    i2++;
                    jSONArray = jSONArray;
                }
                if (jSONObject2.isNull("eventMapping")) {
                    Log.v(TAG, "DCA: Error parsing configuration, No eventMapping field found");
                    this.mParsingSuccess = false;
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                JSONArray jSONArray2 = jSONObject2.getJSONArray("eventMapping");
                if (jSONArray2.length() == 0) {
                    Log.v(TAG, "DCA: Error parsing configuration, eventMapping is empty");
                    this.mParsingSuccess = false;
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                this.mMappingEventTypes = new String[jSONArray2.length()];
                this.mMappingEventUrls = new String[jSONArray2.length()];
                int i3 = 0;
                while (i3 < jSONArray2.length()) {
                    JSONObject jSONObject4 = jSONArray2.getJSONObject(i3);
                    if (jSONObject4.isNull(NotificationCompat.CATEGORY_EVENT)) {
                        Log.v(TAG, "DCA: Error parsing configuration, No event field found in eventMapping list");
                        this.mParsingSuccess = false;
                        return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mMappingEventTypes[i3] = jSONObject4.getString(NotificationCompat.CATEGORY_EVENT);
                    if (jSONObject4.isNull("backend")) {
                        Log.v(TAG, "DCA: Error parsing configuration, No backend field found in eventMapping list");
                        this.mParsingSuccess = false;
                        return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                    }
                    String string2 = jSONObject4.getString("backend");
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length) {
                            i = length;
                            str2 = null;
                            break;
                        }
                        i = length;
                        if (strArr[i4].equals(string2)) {
                            str2 = new String(strArr2[i4]);
                            break;
                        }
                        i4++;
                        length = i;
                    }
                    if (!jSONObject4.isNull("topic")) {
                        str2 = str2 + "/" + jSONObject4.getString("topic");
                    }
                    this.mMappingEventUrls[i3] = str2;
                    i3++;
                    length = i;
                }
                if (jSONObject.isNull("eventFieldValidator")) {
                    Log.v(TAG, "DCA: Error parsing configuration, No eventFieldValidator field found");
                    this.mParsingSuccess = false;
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                JSONArray jSONArray3 = jSONObject.getJSONArray("eventFieldValidator");
                if (jSONArray3.length() == 0) {
                    Log.v(TAG, "DCA: Error parsing configuration, eventFieldValidator is empty");
                    this.mParsingSuccess = false;
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                this.mEventFieldValidateNames = new String[jSONArray3.length()];
                this.mEventFieldsValidatePerEvent = new String[jSONArray3.length()][];
                for (int i5 = 0; i5 < jSONArray3.length(); i5++) {
                    JSONObject jSONObject5 = jSONArray3.getJSONObject(i5);
                    if (jSONObject5.isNull(NotificationCompat.CATEGORY_EVENT)) {
                        Log.v(TAG, "DCA: Error parsing configuration, No event field found in eventFieldValidator");
                        this.mParsingSuccess = false;
                        return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mEventFieldValidateNames[i5] = jSONObject5.getString(NotificationCompat.CATEGORY_EVENT);
                    if (jSONObject5.isNull("mandatory")) {
                        Log.v(TAG, "DCA: Error parsing configuration, No mandatory field found in eventFieldValidator");
                        this.mParsingSuccess = false;
                        return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                    }
                    JSONArray jSONArray4 = jSONObject5.getJSONArray("mandatory");
                    this.mEventFieldsValidatePerEvent[i5] = new String[jSONArray4.length()];
                    for (int i6 = 0; i6 < jSONArray4.length(); i6++) {
                        this.mEventFieldsValidatePerEvent[i5][i6] = jSONArray4.getString(i6);
                    }
                }
                if (jSONObject.isNull("eventHandlers")) {
                    Log.v(TAG, "DCA: Error parsing configuration, No eventHandlers field found");
                    this.mParsingSuccess = false;
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                Boolean bool2 = false;
                JSONObject jSONObject6 = jSONObject.getJSONObject("eventHandlers");
                if (!jSONObject6.keys().hasNext()) {
                    Log.v(TAG, "DCA: Error parsing configuration, eventHandlers is empty");
                    this.mParsingSuccess = false;
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
                if (jSONObject6.isNull("livePlayStop")) {
                    this.mLivePlayStop_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.livePlayStop not defined");
                } else {
                    JSONObject jSONObject7 = jSONObject6.getJSONObject("livePlayStop");
                    if (jSONObject7.isNull("stateConfirmationPeriod")) {
                        this.mLivePlayStop_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: No stateConfirmationPeriod field found for livePlayStop");
                    } else {
                        this.mLivePlayStop_StateConfirmationPeriod = jSONObject7.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("livePauseResume")) {
                    this.mLivePauseResume_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.livePauseResume not defined");
                } else {
                    JSONObject jSONObject8 = jSONObject6.getJSONObject("livePauseResume");
                    if (jSONObject8.isNull("stateConfirmationPeriod")) {
                        this.mLivePauseResume_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.livePauseResume.stateConfimationPeriod not defined");
                    } else {
                        this.mLivePauseResume_StateConfirmationPeriod = jSONObject8.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("liveSeek")) {
                    this.mLiveSeek_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.liveSeek not defined");
                } else {
                    JSONObject jSONObject9 = jSONObject6.getJSONObject("liveSeek");
                    if (jSONObject9.isNull("stateConfirmationPeriod")) {
                        this.mLiveSeek_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.liveSeek.stateConfimationPeriod not defined");
                    } else {
                        this.mLiveSeek_StateConfirmationPeriod = jSONObject9.getInt("stateConfirmationPeriod");
                    }
                    if (jSONObject9.isNull("sendDuringPause")) {
                        this.mLiveSeek_SendDuringPause = false;
                        Log.v(TAG, "DCA: confData.eventHandler.liveSeek.sendDuringPause not defined");
                    } else {
                        this.mLiveSeek_SendDuringPause = jSONObject9.getBoolean("sendDuringPause");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("startoverPlayStop")) {
                    this.mStartoverPlayStop_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.startoverPlayStop not defined");
                } else {
                    JSONObject jSONObject10 = jSONObject6.getJSONObject("startoverPlayStop");
                    if (jSONObject10.isNull("stateConfirmationPeriod")) {
                        this.mStartoverPlayStop_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: No stateConfirmationPeriod field found for startoverPlayStop");
                    } else {
                        this.mStartoverPlayStop_StateConfirmationPeriod = jSONObject10.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("startoverPauseResume")) {
                    this.mStartoverPauseResume_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.startoverPauseResume not defined");
                } else {
                    JSONObject jSONObject11 = jSONObject6.getJSONObject("startoverPauseResume");
                    if (jSONObject11.isNull("stateConfirmationPeriod")) {
                        this.mStartoverPauseResume_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.startoverPauseResume.stateConfimationPeriod not defined");
                    } else {
                        this.mStartoverPauseResume_StateConfirmationPeriod = jSONObject11.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("startoverSeek")) {
                    this.mStartoverSeek_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.startoverSeek not defined");
                } else {
                    JSONObject jSONObject12 = jSONObject6.getJSONObject("startoverSeek");
                    if (jSONObject12.isNull("stateConfirmationPeriod")) {
                        this.mStartoverSeek_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.startoverSeek.stateConfimationPeiod not defined");
                    } else {
                        this.mStartoverSeek_StateConfirmationPeriod = jSONObject12.getInt("stateConfirmationPeriod");
                    }
                    if (jSONObject12.isNull("sendDuringPause")) {
                        this.mStartoverSeek_SendDuringPause = false;
                        Log.v(TAG, "DCA: confData.eventHandler.liveSeek.sendDuringPause not defined");
                    } else {
                        this.mStartoverSeek_SendDuringPause = jSONObject12.getBoolean("sendDuringPause");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("catchupPlayStop")) {
                    this.mCatchupPlayStop_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.catchupPlayStop not defined");
                } else {
                    JSONObject jSONObject13 = jSONObject6.getJSONObject("catchupPlayStop");
                    if (jSONObject13.isNull("stateConfirmationPeriod")) {
                        this.mCatchupPlayStop_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.catchupPlayStop not defined");
                    } else {
                        this.mCatchupPlayStop_StateConfirmationPeriod = jSONObject13.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("catchupPauseResume")) {
                    this.mCatchupPauseResume_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.catchupPauseResume not defined");
                } else {
                    JSONObject jSONObject14 = jSONObject6.getJSONObject("catchupPauseResume");
                    if (jSONObject14.isNull("stateConfirmationPeriod")) {
                        this.mCatchupPauseResume_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.catchupPauseResume.stateConfimationPeriod not defined");
                    } else {
                        this.mCatchupPauseResume_StateConfirmationPeriod = jSONObject14.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("catchupSeek")) {
                    this.mCatchupSeek_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.catchupSeek not defined");
                } else {
                    JSONObject jSONObject15 = jSONObject6.getJSONObject("catchupSeek");
                    if (jSONObject15.isNull("stateConfirmationPeriod")) {
                        this.mCatchupSeek_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.catchupSeek.stateConfimationPeiod not defined");
                    } else {
                        this.mCatchupSeek_StateConfirmationPeriod = jSONObject15.getInt("stateConfirmationPeriod");
                    }
                    if (jSONObject15.isNull("sendDuringPause")) {
                        this.mCatchupSeek_SendDuringPause = false;
                        Log.v(TAG, "DCA: confData.eventHandler.startoverSeek.sendDuringPause not defined");
                    } else {
                        this.mCatchupSeek_SendDuringPause = jSONObject15.getBoolean("sendDuringPause");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("vodPlayStop")) {
                    this.mVodPlayStop_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.vodPlayStop not defined");
                } else {
                    JSONObject jSONObject16 = jSONObject6.getJSONObject("vodPlayStop");
                    if (jSONObject16.isNull("stateConfirmationPeriod")) {
                        this.mVodPlayStop_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.vodPlayStop not defined");
                    } else {
                        this.mVodPlayStop_StateConfirmationPeriod = jSONObject16.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("vodPauseResume")) {
                    this.mVodPauseResume_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.vodPauseResume not defined");
                } else {
                    JSONObject jSONObject17 = jSONObject6.getJSONObject("vodPauseResume");
                    if (jSONObject17.isNull("stateConfirmationPeriod")) {
                        this.mVodPauseResume_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.vodPauseResume.stateConfimationPeriod not defined");
                    } else {
                        this.mVodPauseResume_StateConfirmationPeriod = jSONObject17.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("vodSeek")) {
                    this.mVodSeek_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.vodSeek not defined");
                } else {
                    JSONObject jSONObject18 = jSONObject6.getJSONObject("vodSeek");
                    if (jSONObject18.isNull("stateConfirmationPeriod")) {
                        this.mVodSeek_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.vodSeek.stateConfimationPeiod not defined");
                    } else {
                        this.mVodSeek_StateConfirmationPeriod = jSONObject18.getInt("stateConfirmationPeriod");
                    }
                    if (jSONObject18.isNull("sendDuringPause")) {
                        this.mVodSeek_SendDuringPause = false;
                        Log.v(TAG, "DCA: confData.eventHandler.vodSeek.sendDuringPause not defined");
                    } else {
                        this.mVodSeek_SendDuringPause = jSONObject18.getBoolean("sendDuringPause");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("previewPlayStop")) {
                    this.mPreviewPlayStop_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.previewPlayStop not defined");
                } else {
                    JSONObject jSONObject19 = jSONObject6.getJSONObject("previewPlayStop");
                    if (jSONObject19.isNull("stateConfirmationPeriod")) {
                        this.mPreviewPlayStop_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.previewPlayStop not defined");
                    } else {
                        this.mPreviewPlayStop_StateConfirmationPeriod = jSONObject19.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("previewPauseResume")) {
                    this.mPreviewPauseResume_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.previewPauseResume not defined");
                } else {
                    JSONObject jSONObject20 = jSONObject6.getJSONObject("previewPauseResume");
                    if (jSONObject20.isNull("stateConfirmationPeriod")) {
                        this.mPreviewPauseResume_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.previewPauseResume.stateConfimationPeriod not defined");
                    } else {
                        this.mPreviewPauseResume_StateConfirmationPeriod = jSONObject20.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("previewSeek")) {
                    this.mPreviewSeek_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.previewSeek not defined");
                } else {
                    JSONObject jSONObject21 = jSONObject6.getJSONObject("previewSeek");
                    if (jSONObject21.isNull("stateConfirmationPeriod")) {
                        this.mPreviewSeek_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.previewSeek.stateConfimationPeiod not defined");
                    } else {
                        this.mPreviewSeek_StateConfirmationPeriod = jSONObject21.getInt("stateConfirmationPeriod");
                    }
                    if (jSONObject21.isNull("sendDuringPause")) {
                        this.mPreviewSeek_SendDuringPause = false;
                        Log.v(TAG, "DCA: confData.eventHandler.previewSeek.sendDuringPause not defined");
                    } else {
                        this.mPreviewSeek_SendDuringPause = jSONObject21.getBoolean("sendDuringPause");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("npvrPlayStop")) {
                    this.mNpvrPlayStop_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.npvrPlayStop not defined");
                } else {
                    JSONObject jSONObject22 = jSONObject6.getJSONObject("npvrPlayStop");
                    if (jSONObject22.isNull("stateConfirmationPeriod")) {
                        this.mNpvrPlayStop_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.npvrPlayStop not defined");
                    } else {
                        this.mNpvrPlayStop_StateConfirmationPeriod = jSONObject22.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("npvrPauseResume")) {
                    this.mNpvrPauseResume_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.npvrPauseResume not defined");
                } else {
                    JSONObject jSONObject23 = jSONObject6.getJSONObject("npvrPauseResume");
                    if (jSONObject23.isNull("stateConfirmationPeriod")) {
                        this.mNpvrPauseResume_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.npvrPauseResume.stateConfimationPeriod not defined");
                    } else {
                        this.mNpvrPauseResume_StateConfirmationPeriod = jSONObject23.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("npvrSeek")) {
                    this.mNpvrSeek_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.npvrSeek not defined");
                } else {
                    JSONObject jSONObject24 = jSONObject6.getJSONObject("npvrSeek");
                    if (jSONObject24.isNull("stateConfirmationPeriod")) {
                        this.mNpvrSeek_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.npvrSeek.stateConfimationPeiod not defined");
                    } else {
                        this.mNpvrSeek_StateConfirmationPeriod = jSONObject24.getInt("stateConfirmationPeriod");
                    }
                    if (jSONObject24.isNull("sendDuringPause")) {
                        this.mNpvrSeek_SendDuringPause = false;
                        Log.v(TAG, "DCA: confData.eventHandler.npvrSeek.sendDuringPause not defined");
                    } else {
                        this.mNpvrSeek_SendDuringPause = jSONObject24.getBoolean("sendDuringPause");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("downloadPlayStop")) {
                    this.mDownloadPlayStop_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.downloadPlayStop not defined");
                } else {
                    JSONObject jSONObject25 = jSONObject6.getJSONObject("downloadPlayStop");
                    if (jSONObject25.isNull("stateConfirmationPeriod")) {
                        this.mDownloadPlayStop_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.downloadPlayStop not defined");
                    } else {
                        this.mDownloadPlayStop_StateConfirmationPeriod = jSONObject25.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("downloadPauseResume")) {
                    this.mDownloadPauseResume_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.downloadPauseResume not defined");
                } else {
                    JSONObject jSONObject26 = jSONObject6.getJSONObject("downloadPauseResume");
                    if (jSONObject26.isNull("stateConfirmationPeriod")) {
                        this.mDownloadPauseResume_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.downloadPauseResume.stateConfimationPeriod not defined");
                    } else {
                        this.mDownloadPauseResume_StateConfirmationPeriod = jSONObject26.getInt("stateConfirmationPeriod");
                    }
                    bool2 = bool;
                }
                if (jSONObject6.isNull("downloadSeek")) {
                    this.mDownloadSeek_StateConfirmationPeriod = -1;
                    Log.v(TAG, "DCA: confData.eventHandler.downloadSeek not defined");
                    bool = bool2;
                } else {
                    JSONObject jSONObject27 = jSONObject6.getJSONObject("downloadSeek");
                    if (jSONObject27.isNull("stateConfirmationPeriod")) {
                        this.mDownloadSeek_StateConfirmationPeriod = 0;
                        Log.v(TAG, "DCA: confData.eventHandler.downloadSeek.stateConfimationPeiod not defined");
                    } else {
                        this.mDownloadSeek_StateConfirmationPeriod = jSONObject27.getInt("stateConfirmationPeriod");
                    }
                    if (jSONObject27.isNull("sendDuringPause")) {
                        this.mDownloadSeek_SendDuringPause = false;
                        Log.v(TAG, "DCA: confData.eventHandler.downloadSeek.sendDuringPause not defined");
                    } else {
                        this.mDownloadSeek_SendDuringPause = jSONObject27.getBoolean("sendDuringPause");
                    }
                }
                if (!bool.booleanValue()) {
                    return VoDcaController.VoDcaReturnCode.ConfigurationParsingFailed;
                }
            } catch (JSONException e) {
                Log.e(TAG, "AsyncTaskGetConfiguration - Failed to parse configuration json");
                Log.e(TAG, str);
                e.printStackTrace();
            }
        }
        return null;
    }
}
