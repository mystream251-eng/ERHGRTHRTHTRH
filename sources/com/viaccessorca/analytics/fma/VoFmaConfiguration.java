package com.viaccessorca.analytics.fma;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VoFmaConfiguration {
    private static final String TAG = "VO_FMA";
    public boolean mAnalyticsRequired;
    public String mApplicationName;
    public String mApplicationVersion;
    public String mBootReportUrl;
    public int mCatchupPauseResume_StateConfirmationPeriod;
    public int mCatchupPlayStop_StateConfirmationPeriod;
    public boolean mCatchupSeek_SendDuringPause;
    public int mCatchupSeek_StateConfirmationPeriod;
    public String mCheckTestUrl;
    public String mDeviceCategory;
    public String mDeviceID;
    public String mDeviceModel;
    public int mDownloadPauseResume_StateConfirmationPeriod;
    public int mDownloadPlayStop_StateConfirmationPeriod;
    public boolean mDownloadSeek_SendDuringPause;
    public int mDownloadSeek_StateConfirmationPeriod;
    public String[] mEventFieldValidateNames;
    public String[][] mEventFieldsValidatePerEvent;
    public String mFirmwareVersion;
    public int mFlushPeriod;
    public String mFmaVersion;
    public int mLifeSpan;
    public int mLivePauseResume_StateConfirmationPeriod;
    public int mLivePlayStop_StateConfirmationPeriod;
    public boolean mLiveSeek_SendDuringPause;
    public int mLiveSeek_StateConfirmationPeriod;
    public String mLoggingReportUrl;
    public String[] mMappingEventTypes;
    public String[] mMappingEventUrls;
    public int mMaxStackSize;
    public int mMax_StateConfirmationPeriod;
    public HashMap<String, String> mMonitoredUrlsList;
    public boolean mMonitoringBootTimeRequired;
    public boolean mMonitoringLoggingRequired;
    public boolean mMonitoringNetworkRequired;
    public boolean mMonitoringServiceRequired;
    public int mNetworkPeriodOff_MaxPeriod;
    public int mNetworkPeriodOff_MinPeriod;
    public int mNetworkPeriodOn;
    public String mNetworkReportUrl;
    public int mNpvrPauseResume_StateConfirmationPeriod;
    public int mNpvrPlayStop_StateConfirmationPeriod;
    public boolean mNpvrSeek_SendDuringPause;
    public int mNpvrSeek_StateConfirmationPeriod;
    public String mOperatorName;
    public boolean mParsingSuccess;
    public String mPlayerId;
    public int mPreviewPauseResume_StateConfirmationPeriod;
    public int mPreviewPlayStop_StateConfirmationPeriod;
    public boolean mPreviewSeek_SendDuringPause;
    public int mPreviewSeek_StateConfirmationPeriod;
    public int mReloadConfigurationPeriod;
    public String mSerialNumber;
    public int mServicePeriodOff_MaxPeriod;
    public int mServicePeriodOff_MinPeriod;
    public int mServicePeriodOn;
    public String mServiceReportUrl;
    public int mStartoverPauseResume_StateConfirmationPeriod;
    public int mStartoverPlayStop_StateConfirmationPeriod;
    public boolean mStartoverSeek_SendDuringPause;
    public int mStartoverSeek_StateConfirmationPeriod;
    public String mTerminalId;
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

    protected boolean eventFieldValidate(VoFmaEvent voFmaEvent) {
        String value = voFmaEvent.getValue("event_type");
        if (value == null) {
            Log.v(TAG, "FMA: No eventType field found in input event");
        } else {
            if (this.mEventFieldValidateNames == null) {
                Log.v(TAG, "FMA: Error in the given configuration, eventName field is null ");
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
                Log.v(TAG, "FMA: Error in the given configuration, mandatory field is null ");
                return false;
            }
            String[] strArr3 = strArr2[i];
            for (int i2 = 0; i2 < strArr3.length; i2++) {
                if (!voFmaEvent.keyExists(strArr3[i2])) {
                    Log.e(TAG, "FMA event of type " + value + " has the missing mandatory field: " + strArr3[i2]);
                    return false;
                }
            }
        }
        return true;
    }

    VoFmaController.VoFmaReturnCode parse(String str) {
        Log.v(TAG, "Configuration : " + str);
        this.mOperatorName = VOPlaybackSessionReport.UNKNOWN_SVALUE;
        this.mReloadConfigurationPeriod = 86400;
        this.mFlushPeriod = 60;
        this.mMaxStackSize = 1000;
        this.mLifeSpan = 604800;
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
        this.mParsingSuccess = true;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.isNull("operatorName")) {
                    Log.v(TAG, "FMA: Error parsing configuration, No OperatorName field found");
                } else {
                    String string = jSONObject.getString("operatorName");
                    if (string != null) {
                        this.mOperatorName = string;
                    }
                }
                if (jSONObject.isNull(VoFmaConfigurationKey.RELOAD_CONFIG_PERIOD)) {
                    Log.v(TAG, "FMA: No reloadConfigPeriod field found, using default : 24 hours");
                } else {
                    this.mReloadConfigurationPeriod = jSONObject.getInt(VoFmaConfigurationKey.RELOAD_CONFIG_PERIOD);
                }
                if (jSONObject.isNull("monitoring")) {
                    Log.v(TAG, "FMA: No monitoring field found, monitoring not activated");
                    return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("monitoring");
                if (jSONObject.isNull("flushPeriod")) {
                    Log.v(TAG, "FMA: No flushPeriod field found, using default : 60s");
                } else {
                    this.mFlushPeriod = jSONObject.getInt("flushPeriod");
                }
                if (jSONObject.isNull("maxStackSize")) {
                    Log.v(TAG, "FMA: No maxStackSize field found, using default : 1000");
                } else {
                    this.mMaxStackSize = jSONObject.getInt("maxStackSize");
                }
                if (jSONObject.isNull("lifeSpan")) {
                    Log.v(TAG, "FMA: No lifeSpan field found, using default : 1 week");
                } else {
                    this.mLifeSpan = jSONObject.getInt("lifeSpan");
                }
                String str2 = "maxPeriod";
                String str3 = "minPeriod";
                if (jSONObject2.isNull(NotificationCompat.CATEGORY_SERVICE)) {
                    Log.v(TAG, "FMA: No service field found, Service monitoring not required");
                    this.mMonitoringServiceRequired = false;
                } else {
                    this.mMonitoringServiceRequired = true;
                    JSONObject jSONObject3 = jSONObject2.getJSONObject(NotificationCompat.CATEGORY_SERVICE);
                    if (!jSONObject3.keys().hasNext()) {
                        Log.v(TAG, "FMA: Error parsing configuration, service is empty");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    if (jSONObject3.isNull("reportUrl")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No reportUrl field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mServiceReportUrl = jSONObject3.getString("reportUrl");
                    if (jSONObject3.isNull("periodOn")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No periodOn field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mServicePeriodOn = jSONObject3.getInt("periodOn");
                    if (jSONObject3.isNull("periodOff")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No periodOff field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("periodOff");
                    if (!jSONObject4.keys().hasNext()) {
                        Log.v(TAG, "FMA: Error parsing configuration, periodOff is empty");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    if (jSONObject4.isNull("minPeriod")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No minPeriod field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mServicePeriodOff_MinPeriod = jSONObject4.getInt("minPeriod");
                    if (jSONObject4.isNull("maxPeriod")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No maxPeriod field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mServicePeriodOff_MaxPeriod = jSONObject4.getInt("maxPeriod");
                    if (jSONObject3.isNull("monitoredUrls")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No monitoredUrls field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    JSONArray jSONArray = jSONObject3.getJSONArray("monitoredUrls");
                    if (jSONArray.length() == 0) {
                        Log.v(TAG, "FMA: Error parsing configuration, monitoredUrls is empty");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mMonitoredUrlsList = new HashMap<>();
                    int i = 0;
                    while (i < jSONArray.length()) {
                        JSONObject jSONObject5 = jSONArray.getJSONObject(i);
                        JSONArray jSONArray2 = jSONArray;
                        if (jSONObject5.isNull(ImagesContract.URL)) {
                            Log.v(TAG, "FMA: Error parsing configuration, No url field found in monitoredUrls list");
                            return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                        }
                        if (jSONObject5.isNull("serviceName")) {
                            Log.v(TAG, "FMA: Error parsing configuration, No serviceName field found in monitoredUrls list");
                            return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                        }
                        this.mMonitoredUrlsList.put(jSONObject5.getString(ImagesContract.URL), jSONObject5.getString("serviceName"));
                        i++;
                        jSONArray = jSONArray2;
                        str2 = str2;
                        str3 = str3;
                    }
                }
                String str4 = str2;
                String str5 = str3;
                if (jSONObject2.isNull("boot")) {
                    Log.v(TAG, "FMA: No boot field found, boot monitoring not required");
                    this.mMonitoringBootTimeRequired = false;
                } else {
                    Log.v(TAG, "monitoring boot is required");
                    this.mMonitoringBootTimeRequired = true;
                    JSONObject jSONObject6 = jSONObject2.getJSONObject("boot");
                    if (!jSONObject6.keys().hasNext()) {
                        Log.v(TAG, "FMA: Error parsing configuration, boot is empty");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    if (jSONObject6.isNull("reportUrl")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No reportUrl field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mBootReportUrl = jSONObject6.getString("reportUrl");
                }
                if (jSONObject2.isNull(VoFmaConfigurationKey.LOGGING)) {
                    Log.v(TAG, "FMA: No logging field found, Sending crash reports not activated");
                    this.mMonitoringLoggingRequired = false;
                } else {
                    Log.v(TAG, "monitoring crash report is activated");
                    this.mMonitoringLoggingRequired = true;
                    JSONObject jSONObject7 = jSONObject2.getJSONObject(VoFmaConfigurationKey.LOGGING);
                    if (!jSONObject7.keys().hasNext()) {
                        Log.v(TAG, "FMA: Error parsing configuration, logging is empty");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    if (jSONObject7.isNull("reportUrl")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No reportUrl field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mLoggingReportUrl = jSONObject7.getString("reportUrl");
                }
                if (jSONObject2.isNull("network")) {
                    Log.v(TAG, "FMA: No network field found, Network monitoring not required");
                    this.mMonitoringNetworkRequired = false;
                } else {
                    this.mMonitoringNetworkRequired = true;
                    JSONObject jSONObject8 = jSONObject2.getJSONObject("network");
                    if (!jSONObject8.keys().hasNext()) {
                        Log.v(TAG, "FMA: Error parsing configuration, service is empty");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    if (jSONObject8.isNull("reportUrl")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No reportUrl field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mNetworkReportUrl = jSONObject8.getString("reportUrl");
                    if (jSONObject8.isNull("periodOn")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No periodOn field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mNetworkPeriodOn = jSONObject8.getInt("periodOn");
                    if (jSONObject8.isNull("testUrl")) {
                        Log.v(TAG, "FMA: parsing configuration, No testUrl field found, w.w.w.google.com will be used for checking");
                        this.mCheckTestUrl = "www.google.com";
                    } else {
                        this.mCheckTestUrl = jSONObject8.getString("testUrl");
                    }
                    if (jSONObject8.isNull("periodOff")) {
                        Log.v(TAG, "FMA: Error parsing configuration, No periodOff field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    JSONObject jSONObject9 = jSONObject8.getJSONObject("periodOff");
                    if (!jSONObject9.keys().hasNext()) {
                        Log.v(TAG, "FMA: Error parsing configuration, periodOff is empty");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    if (jSONObject9.isNull(str5)) {
                        Log.v(TAG, "FMA: Error parsing configuration, No minPeriod field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mNetworkPeriodOff_MinPeriod = jSONObject9.getInt(str5);
                    if (jSONObject9.isNull(str4)) {
                        Log.v(TAG, "FMA: Error parsing configuration, No maxPeriod field found");
                        return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
                    }
                    this.mNetworkPeriodOff_MaxPeriod = jSONObject9.getInt(str4);
                }
            } catch (JSONException e) {
                Log.e(TAG, "AsyncTaskGetConfiguration - Failed to parse configuration json");
                Log.e(TAG, str);
                e.printStackTrace();
                return VoFmaController.VoFmaReturnCode.ConfigurationParsingFailed;
            }
        }
        return VoFmaController.VoFmaReturnCode.Success;
    }
}
