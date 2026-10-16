package com.viaccessorca.analytics.fma;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.UByte;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.broadpeak.smartlib.session.streaming.StreamingSessionOptions;

/* JADX INFO: loaded from: classes2.dex */
public class VoFmaDispatcher {
    private static final String CATCHUP_EVENTS_END_POINT = "catchupEvent";
    private static final int CONST_DATABASE_CHECK_PERIOD_MS = 60000;
    private static final String DOC_FOLDER = Environment.getExternalStorageDirectory() + "/Documents";
    private static final String DOWNLOAD_EVENTS_END_POINT = "downloadEvent";
    private static final String LIVE_EVENTS_END_POINT = "liveEvent";
    private static final String NPVR_EVENTS_END_POINT = "npvrEvent";
    private static final String PREVIEW_EVENTS_END_POINT = "previewEvent";
    private static final int QUEUE_CAPACITY = 512;
    private static final String STARTOVER_EVENTS_END_POINT = "startoverEvent";
    private static final String TAG = "VO_FMA";
    private static final String URL_PATTERN = "(?i)\\b(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10)(?:\\.\\d{1,3}){3})(?!(?:169\\.254)(?:\\.\\d{1,3}){2})(?!127\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?!192.168\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|21[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?\\b";
    private static final String VOD_EVENTS_END_POINT = "vodEvent";
    private VoFmaConfiguration mConfiguration;
    private Context mContext;
    protected FmaDatabaseHelper mDatabaseHelper;
    private long mDatabaseLastSavedBootTime;
    Timer mDatabaseOldEventsTimer;
    private boolean mDownload2goEnabled;
    private boolean mFmaMonitoringRequirementsAvailable;
    String mFmaVersion;
    Timer mGetConfigTimer;
    String mIpAddress;
    Timer mNetworkMonitoringTimer;
    public boolean mNetworkOn;
    private String mNetworkType;
    public int mOfflineHttpStatus;
    private LinkedBlockingQueue<VoFmaEvent> mOfflineQueue;
    private VoFmaInitParameters mParams;
    VoFmaController.VoFmaReturnCode mParsingRes;
    Timer mSendingFailureTimer;
    Timer mServiceMonitoringTimer;
    public boolean mServiceOn;
    private boolean mSuccess;
    private final int ATTEMPT_COUNT = DefaultOggSeeker.MATCH_BYTE_RANGE;
    protected VoFmaDispatcherListener mListener = null;
    protected boolean mContinue = true;
    protected Object mSync = new Object();
    protected Object mOfflineSync = new Object();
    private boolean NETWORK_OFF_DBG = true;
    private boolean SERVICE_OFF_DBG = true;
    private Tuple mHttpStatus = new Tuple(200, "");
    Boolean mIsServiceLimitedEventSend = false;
    Boolean mIsLoggingEventSend = false;
    Boolean mIsBootEventSend = false;
    Boolean mIsNetworkStSent = false;
    Boolean mIsConnected = true;
    Boolean mIsSendFailureExist = false;
    Boolean mIsConfigurationFromBE = false;
    Boolean mIsNetworkAvailable = false;
    Boolean mIsCheckCustomEventsCallCompleted = true;
    Boolean mIsMonitoringStartedAlready = false;

    private class AsyncTaskGetConfiguration extends AsyncTask<String, Void, Void> {
        private VoFmaConfiguration mConf;

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
        }

        private AsyncTaskGetConfiguration() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:63:0x02eb  */
        /* JADX WARN: Code duplicated, block: B:65:0x02fd  */
        /* JADX WARN: Code duplicated, block: B:66:0x030e  */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            StringBuilder sb;
            String storedConfiguration;
            HashMap map = new HashMap();
            map.put("device_model", "Android");
            map.put("application_version", VoFmaDispatcher.this.mParams.mApplicationVersion);
            map.put(MimeTypes.BASE_TYPE_APPLICATION, VoFmaDispatcher.this.mParams.mApplicationName);
            if (VoFmaDispatcher.this.mParams.mDeviceCategory != null) {
                map.put("device_category", VoFmaDispatcher.this.mParams.mDeviceCategory);
            } else {
                map.put("device_category", "null");
            }
            if (VoFmaDispatcher.this.mFmaVersion == null) {
                map.put("fma_version", "null");
            } else {
                map.put("fma_version", VoFmaDispatcher.this.mFmaVersion);
            }
            if (VoFmaDispatcher.this.mParams.mPlayerId != null) {
                map.put("vo_player_id", VoFmaDispatcher.this.mParams.mPlayerId);
            } else {
                map.put("vo_player_id", "null");
            }
            if (VoFmaDispatcher.this.mParams.mSerialNumber != null) {
                map.put("serial_number", VoFmaDispatcher.this.mParams.mSerialNumber);
            } else {
                map.put("serial_number", "null");
            }
            if (VoFmaDispatcher.this.mParams.mTerminalId != null) {
                map.put("terminal_id", VoFmaDispatcher.this.mParams.mTerminalId);
            } else {
                map.put("terminal_id", "null");
            }
            if (VoFmaDispatcher.this.mParams.mFirmwareVersion != null) {
                map.put("firmware_version", VoFmaDispatcher.this.mParams.mFirmwareVersion);
            } else {
                map.put("firmware_version", "null");
            }
            if (VoFmaDispatcher.this.isNetworkAvailable()) {
                map.put("ip_address", VoFmaDispatcher.sha256(VoFmaDispatcher.this.mIpAddress));
            } else {
                map.put("ip_address", "null");
            }
            map.put("time_zone", VoFmaDispatcher.this.getTimeZoneOffset());
            Log.i(VoFmaDispatcher.TAG, "Init fma with parameters :DeviceId=" + VoFmaDispatcher.this.mParams.mDeviceID + "\nApplicationName=" + VoFmaDispatcher.this.mParams.mApplicationName + "\nApplicationVersion=" + VoFmaDispatcher.this.mParams.mApplicationVersion + "\nFmaVersion=" + VoFmaDispatcher.this.mFmaVersion + "\nDeviceCategory=" + VoFmaDispatcher.this.mParams.mDeviceCategory + "\nFirmwareVersion=" + VoFmaDispatcher.this.mParams.mFirmwareVersion + "\nSerialNumber=" + VoFmaDispatcher.this.mParams.mSerialNumber + "\nPlayerId=" + VoFmaDispatcher.this.mParams.mPlayerId + "\nTerminalId=" + VoFmaDispatcher.this.mParams.mTerminalId + "\nTimezoneOffset=" + VoFmaDispatcher.this.getTimeZoneOffset() + "\nBackendUrl=" + VoFmaDispatcher.this.mParams.mConfigurationUrl);
            StringBuilder sb2 = new StringBuilder();
            int i = 0;
            for (String str : map.keySet()) {
                if (i != 0) {
                    try {
                        sb2.append("&");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                sb2.append(str);
                sb2.append("=");
                sb2.append(URLEncoder.encode((String) map.get(str), "UTF-8"));
                i++;
            }
            VoFmaDispatcher.this.mSuccess = true;
            try {
                String str2 = strArr[0] + "?" + ((Object) sb2);
                Log.v(VoFmaDispatcher.TAG, "Getting FMA configuration from BE, CONFIG_URL: " + str2);
                URL url = new URL(str2);
                String str3 = strArr[0];
                this.mConf = new VoFmaConfiguration();
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod(VOUtils.HTTP_GET);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        sb = new StringBuilder();
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb.append(line + "\n");
                            }
                        } catch (Exception e2) {
                            e = e2;
                            Log.e(VoFmaDispatcher.TAG, "AsyncTaskGetConfiguration - Failed to send message");
                            e.printStackTrace();
                            VoFmaDispatcher.this.mSuccess = false;
                        }
                        if (sb == null && VoFmaDispatcher.this.mSuccess) {
                            Log.d(VoFmaDispatcher.TAG, "Parsing FMA configuration, received: " + sb.toString());
                            VoFmaDispatcher.this.mDatabaseHelper.deleteStoredConfiguration();
                            Log.v(VoFmaDispatcher.TAG, "Store the configuration in the database");
                            VoFmaDispatcher.this.mDatabaseHelper.storeLastReceivedConfiguration(sb.toString());
                            VoFmaDispatcher.this.mParsingRes = this.mConf.parse(sb.toString());
                            VoFmaDispatcher.this.mIsConfigurationFromBE = true;
                        } else {
                            VoFmaDispatcher.this.mIsConfigurationFromBE = false;
                            storedConfiguration = VoFmaDispatcher.this.mDatabaseHelper.getStoredConfiguration();
                            if (storedConfiguration == null) {
                                VoFmaDispatcher.this.mSuccess = false;
                                VoFmaDispatcher.this.getConfigurationAfterFailure(VOUtils.TIMEOUT_CONNECT, url.toString());
                            } else {
                                Log.v(VoFmaDispatcher.TAG, "Initializing FMA with Stored configuration from database");
                                VoFmaDispatcher.this.mParsingRes = this.mConf.parse(storedConfiguration);
                                VoFmaDispatcher.this.mSuccess = true;
                            }
                        }
                        return null;
                    }
                    Log.e(VoFmaDispatcher.TAG, "AsyncTaskGetConfiguration - HttpResult: " + responseCode);
                    VoFmaDispatcher.this.mSuccess = false;
                    sb = null;
                } catch (Exception e3) {
                    e = e3;
                    sb = null;
                }
                if (sb == null) {
                    VoFmaDispatcher.this.mIsConfigurationFromBE = false;
                    storedConfiguration = VoFmaDispatcher.this.mDatabaseHelper.getStoredConfiguration();
                    if (storedConfiguration == null) {
                        VoFmaDispatcher.this.mSuccess = false;
                        VoFmaDispatcher.this.getConfigurationAfterFailure(VOUtils.TIMEOUT_CONNECT, url.toString());
                    } else {
                        Log.v(VoFmaDispatcher.TAG, "Initializing FMA with Stored configuration from database");
                        VoFmaDispatcher.this.mParsingRes = this.mConf.parse(storedConfiguration);
                        VoFmaDispatcher.this.mSuccess = true;
                    }
                } else {
                    VoFmaDispatcher.this.mIsConfigurationFromBE = false;
                    storedConfiguration = VoFmaDispatcher.this.mDatabaseHelper.getStoredConfiguration();
                    if (storedConfiguration == null) {
                        VoFmaDispatcher.this.mSuccess = false;
                        VoFmaDispatcher.this.getConfigurationAfterFailure(VOUtils.TIMEOUT_CONNECT, url.toString());
                    } else {
                        Log.v(VoFmaDispatcher.TAG, "Initializing FMA with Stored configuration from database");
                        VoFmaDispatcher.this.mParsingRes = this.mConf.parse(storedConfiguration);
                        VoFmaDispatcher.this.mSuccess = true;
                    }
                }
                return null;
            } catch (MalformedURLException e4) {
                e4.printStackTrace();
                VoFmaDispatcher.this.mSuccess = false;
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            if (VoFmaDispatcher.this.mSuccess) {
                VoFmaDispatcher.this.mListener.onConfigurationReceived(this.mConf);
            } else {
                VoFmaDispatcher.this.mListener.onError();
            }
        }
    }

    private boolean isUrlValid(String str) {
        try {
            new URL(str).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException unused) {
            return false;
        }
    }

    private class AsyncTaskSendEvent extends AsyncTask<VoFmaEvent, Void, Void> {
        protected boolean mRetry;
        protected int mRetryCount;
        private String mUrl;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
        }

        private AsyncTaskSendEvent() {
            this.mRetryCount = 0;
        }

        public boolean toRetry() {
            return this.mRetry;
        }

        public void setUrl(String str) {
            this.mUrl = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(VoFmaEvent... voFmaEventArr) {
            VoFmaEvent voFmaEvent = voFmaEventArr[0];
            this.mRetry = false;
            JSONObject jSONObject = new JSONObject();
            if (voFmaEvent.getJsonEvent() != null) {
                jSONObject = voFmaEvent.getJsonEvent();
            } else {
                try {
                    JSONObject jsonObject = voFmaEvent.getJsonObject();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("value", jsonObject);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(jSONObject2);
                    jSONObject.put("records", jSONArray);
                } catch (JSONException e) {
                    Log.e(VoFmaDispatcher.TAG, "AsyncTaskSendEvent - Failed to generate Json from event");
                    e.printStackTrace();
                }
            }
            Log.d(VoFmaDispatcher.TAG, "FMA ==> BE: " + this.mUrl);
            Log.d(VoFmaDispatcher.TAG, "FMA ==> BE: " + jSONObject.toString());
            try {
                URL url = new URL(this.mUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod(VOUtils.HTTP_POST);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(2000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setRequestProperty("Content-Type", "application/vnd.kafka.json.v1+json");
                httpURLConnection.setRequestProperty("Host", url.getHost());
                httpURLConnection.connect();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                outputStreamWriter.write(jSONObject.toString());
                outputStreamWriter.close();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    if (voFmaEvent.getJsonEvent() != null) {
                        VoFmaDispatcher.this.mDatabaseHelper.deleteAllEvents(voFmaEvent.getOfflineTableName());
                    } else {
                        VoFmaDispatcher.this.mListener.onEventSent(new VoFmaEvent(voFmaEvent));
                        StringBuilder sb = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line + "\n");
                        }
                    }
                    if (VoFmaDispatcher.this.mIsSendFailureExist.booleanValue()) {
                        VoFmaDispatcher.this.monitorCustomEvents();
                    }
                } else {
                    Log.e(VoFmaDispatcher.TAG, "AsyncTaskSendEvent - HttpResult: " + responseCode);
                }
            } catch (Exception e2) {
                Log.e(VoFmaDispatcher.TAG, "AsyncTaskSendEvent - Failed to send message");
                e2.printStackTrace();
                if (voFmaEvent.getJsonEvent() == null) {
                    this.mRetry = false;
                    if (voFmaEvent.getCustomType().equals("LOG_EVENT")) {
                        VoFmaDispatcher.this.mDatabaseHelper.addLoggingEvent(voFmaEvent, VoFmaDispatcher.this.mConfiguration.mLifeSpan, VoFmaDispatcher.this.mConfiguration.mMaxStackSize);
                    }
                    if (voFmaEvent.getCustomType().equals("BOOT_STATUS")) {
                        VoFmaDispatcher.this.mDatabaseHelper.addBootStatusEvent(voFmaEvent);
                    }
                    if (voFmaEvent.getCustomType().equals("SERVICE_LIMITED") || voFmaEvent.getCustomType().equals("SERVICE_ON")) {
                        VoFmaDispatcher.this.mDatabaseHelper.addServiceEvent(voFmaEvent);
                    }
                    if (voFmaEvent.getCustomType().equals("NETWORK_STATUS")) {
                        VoFmaDispatcher.this.mDatabaseHelper.addNetworkStatusEvent(voFmaEvent);
                    }
                    if (!VoFmaDispatcher.this.isNetworkAvailable()) {
                        Log.v(VoFmaDispatcher.TAG, "Network is not available");
                        VoFmaDispatcher.this.mIsSendFailureExist = true;
                    } else if (!VoFmaDispatcher.this.mIsConfigurationFromBE.booleanValue()) {
                        Log.v(VoFmaDispatcher.TAG, "Configuration not from BE, getting the right configuration");
                        VoFmaDispatcher voFmaDispatcher = VoFmaDispatcher.this;
                        voFmaDispatcher.getConfiguration(voFmaDispatcher.mParams.mConfigurationUrl);
                        VoFmaDispatcher.this.mIsConfigurationFromBE = true;
                    }
                    VoFmaDispatcher.this.monitorCustomEvents();
                }
            }
            synchronized (VoFmaDispatcher.this.mSync) {
                VoFmaDispatcher.this.mSync.notify();
            }
            return null;
        }
    }

    protected VoFmaDispatcher(Context context) {
        this.mDownload2goEnabled = false;
        this.mFmaMonitoringRequirementsAvailable = false;
        this.mContext = context;
        if (context == null || !checkNetworkRequestPermissions()) {
            return;
        }
        this.mDownload2goEnabled = true;
        this.mFmaMonitoringRequirementsAvailable = true;
        FmaDatabaseHelper fmaDatabaseHelper = new FmaDatabaseHelper(context);
        this.mDatabaseHelper = fmaDatabaseHelper;
        fmaDatabaseHelper.upgradeDatabaseIfNeeded();
    }

    protected VoFmaController.VoFmaReturnCode getConfiguration(String str) {
        new AsyncTaskGetConfiguration().execute(str);
        return this.mParsingRes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getConfigurationAfterFailure(int i, final String str) {
        Log.d(TAG, "Getting configuration is failed, retrying after :" + i + " ms");
        if (this.mGetConfigTimer != null) {
            Log.d(TAG, "Get config Timer is already started");
            return;
        }
        Timer timer = new Timer();
        this.mGetConfigTimer = timer;
        timer.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaDispatcher.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                VoFmaDispatcher.this.getConfiguration(str);
                if (!VoFmaDispatcher.this.mSuccess) {
                    Log.v(VoFmaDispatcher.TAG, "Failed to get configuration");
                    return;
                }
                Log.v(VoFmaDispatcher.TAG, "AsyncTask get Configuration success");
                VoFmaDispatcher.this.mGetConfigTimer.cancel();
                VoFmaDispatcher.this.mGetConfigTimer = null;
            }
        }, 0L, i);
    }

    protected void sendEvent(VoFmaEvent voFmaEvent, String str) {
        if (this.NETWORK_OFF_DBG) {
            AsyncTaskSendEvent asyncTaskSendEvent = null;
            for (int i = 0; i < 100000; i++) {
                if (asyncTaskSendEvent != null) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!isUrlValid(str)) {
                    Log.v(TAG, "FMA: Invalid URL: " + str);
                    return;
                }
                asyncTaskSendEvent = new AsyncTaskSendEvent();
                asyncTaskSendEvent.setUrl(str);
                synchronized (this.mSync) {
                    asyncTaskSendEvent.execute(voFmaEvent);
                    try {
                        this.mSync.wait();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                if (!this.mContinue || !asyncTaskSendEvent.toRetry()) {
                    return;
                }
            }
        }
    }

    protected void stop() {
        this.mContinue = false;
    }

    protected void setListener(VoFmaDispatcherListener voFmaDispatcherListener) {
        this.mListener = voFmaDispatcherListener;
    }

    private int performPostRequest(String str, JSONObject jSONObject) {
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod(VOUtils.HTTP_POST);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestProperty("Content-Type", "application/vnd.kafka.json.v1+json");
            httpURLConnection.setRequestProperty("Host", url.getHost());
            httpURLConnection.connect();
            String string = jSONObject.toString();
            Log.d(TAG, "FMA ==> BE: " + str);
            Log.d(TAG, "FMA ==> BE: " + string);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            outputStreamWriter.write(string);
            outputStreamWriter.close();
            return httpURLConnection.getResponseCode();
        } catch (Exception e) {
            Log.e(TAG, "Failed to send message");
            e.printStackTrace();
            return 500;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean sendOfflineEvents(List<JSONObject> list, String str) {
        if (str != null && !str.equals("")) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (JSONObject jSONObject2 : list) {
                    try {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("value", jSONObject2);
                        jSONArray.put(jSONObject3);
                        jSONObject.put("records", jSONArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (performPostRequest(str, jSONObject) == 200) {
                    list.clear();
                } else {
                    return false;
                }
            } else {
                Log.v(TAG, "Queue empty, no offline events found ");
            }
        }
        return true;
    }

    private Boolean sendOfflineEventsAfterReboot(String str, String str2) {
        List<JSONObject> allEvents = this.mDatabaseHelper.getAllEvents(str);
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (allEvents != null && allEvents.size() > 0) {
            for (JSONObject jSONObject2 : allEvents) {
                try {
                    jSONObject2.accumulate("timestamp_on", VoFmaEvent.generateTimeString(System.currentTimeMillis()));
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("value", jSONObject2);
                    jSONArray.put(jSONObject3);
                    jSONObject.put("records", jSONArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sendEvent(new VoFmaEvent(jSONObject, str), str2);
        } else {
            Log.v(TAG, "Queue empty, no offline events found ");
        }
        return true;
    }

    public boolean isNetworkAvailable(String str) {
        try {
            URL url = new URL(str);
            new Socket(url.getHost(), url.getPort());
            Log.v(TAG, "Connected to the internet");
            return true;
        } catch (Exception unused) {
            Log.v(TAG, "Not connected to the internet");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNetworkAvailable() {
        boolean zBooleanValue = performSecondConnectivityCheck().booleanValue();
        boolean z = false;
        if (checkNetworkRequestPermissions()) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.mIsNetworkAvailable = Boolean.valueOf(activeNetworkInfo.isConnected());
                StringBuilder sb = new StringBuilder();
                sb.append("Android system - Network status :");
                sb.append(this.mIsNetworkAvailable.booleanValue() ? "OK" : "KO");
                Log.d(TAG, sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Ping to test url status: ");
                sb2.append(zBooleanValue ? "OK" : "KO");
                Log.d(TAG, sb2.toString());
                if (this.mIsNetworkAvailable.booleanValue() && (zBooleanValue || this.mConfiguration == null)) {
                    int type = activeNetworkInfo.getType();
                    if (type == 0) {
                        this.mNetworkType = VOPlaybackSessionReport.NETWORK_TYPE_CELLULAR;
                        this.mIpAddress = getLocalIpAddress(true);
                    } else if (type == 1) {
                        this.mNetworkType = VOPlaybackSessionReport.NETWORK_TYPE_WIFI;
                        this.mIpAddress = getWifiIPAddress();
                    } else if (type == 9) {
                        this.mNetworkType = VOPlaybackSessionReport.NETWORK_TYPE_ETHERNET;
                        this.mIpAddress = getLocalIpAddress(true);
                    } else {
                        this.mNetworkType = "OTHER";
                    }
                    Log.d(TAG, "Network available, Connected via: " + this.mNetworkType + ", ip address : " + this.mIpAddress);
                    z = true;
                }
            } else {
                Log.d(TAG, "Error, Network interface is not active");
                this.mIsNetworkAvailable = false;
            }
            this.mIsConnected = Boolean.valueOf(z);
            return z;
        }
        Log.e(TAG, "Networking permission not granted, failed to check network status");
        this.mIsConnected = false;
        return false;
    }

    private Boolean performSecondConnectivityCheck() {
        VoFmaConfiguration voFmaConfiguration = this.mConfiguration;
        if (voFmaConfiguration == null) {
            return false;
        }
        Tuple tupleCheckUrlStatus = checkUrlStatus(voFmaConfiguration.mCheckTestUrl);
        this.mHttpStatus = tupleCheckUrlStatus;
        if (tupleCheckUrlStatus.statusCode >= 200 && this.mHttpStatus.statusCode < 299) {
            return true;
        }
        Log.d(TAG, "Failed to ping test url: " + this.mConfiguration.mCheckTestUrl + ", status code: " + this.mHttpStatus.statusCode);
        return false;
    }

    public void appendLog(String str) {
        File file = new File("/sdcard/log.file");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.append((CharSequence) str);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static String getLocalIpAddress(boolean z) {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                for (InetAddress inetAddress : Collections.list(((NetworkInterface) it.next()).getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        boolean z2 = hostAddress.indexOf(58) < 0;
                        if (z) {
                            if (z2) {
                                return hostAddress;
                            }
                        } else if (!z2) {
                            int iIndexOf = hostAddress.indexOf(37);
                            return iIndexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, iIndexOf).toUpperCase();
                        }
                    }
                }
            }
            return "No IP Available";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR Obtaining IP";
        }
    }

    private String getWifiIPAddress() {
        return Formatter.formatIpAddress(((WifiManager) this.mContext.getSystemService("wifi")).getConnectionInfo().getIpAddress());
    }

    public void stopRunningTimers() {
        if (this.mServiceMonitoringTimer != null) {
            Log.v(TAG, "Cancelling the service timer");
            this.mServiceMonitoringTimer.cancel();
        }
        if (this.mNetworkMonitoringTimer != null) {
            Log.v(TAG, "Cancelling the network timer.");
            this.mNetworkMonitoringTimer.cancel();
        }
        if (this.mDatabaseOldEventsTimer != null) {
            Log.v(TAG, "Cancelling the database check timer.");
            this.mDatabaseOldEventsTimer.cancel();
        }
        if (this.mSendingFailureTimer != null) {
            Log.v(TAG, "Cancelling the failure sending check timer.");
            this.mSendingFailureTimer.cancel();
        }
    }

    void startMonitoring(VoFmaConfiguration voFmaConfiguration) {
        if (!this.mIsMonitoringStartedAlready.booleanValue()) {
            sendOfflineEventsAfterReboot(FmaDatabaseHelper.OFFLINE_LOGGING_EVENTS_TABLE_NAME, this.mConfiguration.mLoggingReportUrl);
            sendOfflineEventsAfterReboot(FmaDatabaseHelper.OFFLINE_SERVICE_EVENTS_TABLE_NAME, this.mConfiguration.mServiceReportUrl);
            sendOfflineEventsAfterReboot(FmaDatabaseHelper.OFFLINE_BOOT_EVENTS_TABLE_NAME, this.mConfiguration.mBootReportUrl);
            sendOfflineEventsAfterReboot(FmaDatabaseHelper.OFFLINE_NETWORK_EVENTS_TABLE_NAME, this.mConfiguration.mNetworkReportUrl);
        }
        if (!this.mFmaMonitoringRequirementsAvailable) {
            Log.v(TAG, "Context missing or required permissions not granted, service and network monitoring is disabled");
            return;
        }
        this.mNetworkOn = true;
        this.mServiceOn = true;
        stopRunningTimers();
        if (this.mConfiguration.mMonitoringNetworkRequired || this.mConfiguration.mMonitoringServiceRequired || this.mConfiguration.mMonitoringBootTimeRequired || this.mConfiguration.mMonitoringLoggingRequired) {
            this.mConfiguration.mPlayerId = this.mParams.mPlayerId;
            this.mConfiguration.mFirmwareVersion = this.mParams.mFirmwareVersion;
            this.mConfiguration.mTerminalId = this.mParams.mTerminalId;
            if (this.mParams.mSerialNumber == null) {
                Log.v(TAG, "Mandatory param serialNumber is missing, Starting Monitoring failed.");
                Log.v(TAG, "Please recheck the FMA configuration.");
                return;
            } else {
                this.mConfiguration.mSerialNumber = this.mParams.mSerialNumber;
            }
        }
        Log.v(TAG, "monitoring boot: " + this.mConfiguration.mMonitoringBootTimeRequired);
        if (this.mConfiguration.mMonitoringBootTimeRequired) {
            monitorBootTime();
        }
        if (this.mConfiguration.mMonitoringServiceRequired) {
            monitor_service(this.mConfiguration);
        } else {
            Log.d(TAG, "Service monitoring not required");
        }
        if (this.mConfiguration.mMonitoringNetworkRequired) {
            monitor_network(this.mConfiguration, new VoFmaEvent());
        } else {
            Log.d(TAG, "Network monitoring not required");
        }
        monitorDatabaseOldEvents(CONST_DATABASE_CHECK_PERIOD_MS);
        this.mIsMonitoringStartedAlready = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void monitor_service(final VoFmaConfiguration voFmaConfiguration) {
        Log.d(TAG, "Starting service monitoring");
        final Timer timer = new Timer();
        this.mServiceMonitoringTimer = timer;
        timer.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaDispatcher.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                final HashMap mapCheckUrlsStatus = VoFmaDispatcher.this.checkUrlsStatus();
                if ((mapCheckUrlsStatus != null && mapCheckUrlsStatus.size() > 0) || !VoFmaDispatcher.this.SERVICE_OFF_DBG) {
                    timer.cancel();
                    final Timer timer2 = new Timer();
                    VoFmaDispatcher.this.mServiceMonitoringTimer = timer2;
                    timer2.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaDispatcher.2.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            if (!VoFmaDispatcher.this.mServiceOn) {
                                HashMap mapCheckUrlsStatus2 = VoFmaDispatcher.this.checkUrlsStatus();
                                if ((mapCheckUrlsStatus2 == null || mapCheckUrlsStatus2.size() == 0) && VoFmaDispatcher.this.SERVICE_OFF_DBG) {
                                    timer2.cancel();
                                    VoFmaDispatcher.this.monitor_service(voFmaConfiguration);
                                    return;
                                }
                                return;
                            }
                            VoFmaDispatcher.this.mServiceOn = false;
                            Log.d(VoFmaDispatcher.TAG, "Status update: service ON mode ==> service limited mode");
                            VoFmaEvent voFmaEvent = new VoFmaEvent();
                            voFmaEvent.setCustomType("SERVICE_LIMITED");
                            voFmaEvent.setCustomParameter("network_type", VoFmaDispatcher.this.mNetworkType);
                            voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, VoFmaEvent.generateTimeString(System.currentTimeMillis()));
                            voFmaEvent.setCurrentTime();
                            voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, voFmaEvent.getTime());
                            voFmaEvent.setCustomParameter("operator_name", voFmaConfiguration.mOperatorName);
                            voFmaEvent.setCustomParameter("origin_version", voFmaConfiguration.mFmaVersion);
                            voFmaEvent.setCustomParameter(MimeTypes.BASE_TYPE_APPLICATION, voFmaConfiguration.mApplicationName);
                            voFmaEvent.setCustomParameter("application_version", voFmaConfiguration.mApplicationVersion);
                            voFmaEvent.setCustomParameter("device_category", voFmaConfiguration.mDeviceCategory);
                            voFmaEvent.setCustomParameter("origin_platform", "Android");
                            voFmaEvent.setCustomParameter("device_id", voFmaConfiguration.mDeviceID);
                            voFmaEvent.setCustomParameter("vo_player_id", voFmaConfiguration.mPlayerId);
                            voFmaEvent.setCustomParameter("serial_number", voFmaConfiguration.mSerialNumber);
                            voFmaEvent.setCustomParameter("firmware_version", voFmaConfiguration.mFirmwareVersion);
                            voFmaEvent.setCustomParameter("terminal_id", voFmaConfiguration.mTerminalId);
                            voFmaEvent.setCustomParameter("ip_address", VoFmaDispatcher.this.mIpAddress);
                            JSONObject jSONObject = new JSONObject();
                            JSONArray jSONArray = new JSONArray();
                            try {
                                Log.v(VoFmaDispatcher.TAG, "size of checkUrlStatus list" + mapCheckUrlsStatus.size());
                                for (String str : mapCheckUrlsStatus.keySet()) {
                                    Log.v(VoFmaDispatcher.TAG, "Url : " + str);
                                    Log.v(VoFmaDispatcher.TAG, "statusCode: " + ((Tuple) mapCheckUrlsStatus.get(str)).statusCode);
                                    Log.v(VoFmaDispatcher.TAG, "error message: " + ((Tuple) mapCheckUrlsStatus.get(str)).error);
                                    JSONObject jSONObject2 = new JSONObject();
                                    new JSONObject();
                                    jSONObject2.put("url_path", str);
                                    new JSONObject();
                                    jSONObject2.put("service_name", VoFmaDispatcher.this.mConfiguration.mMonitoredUrlsList.get(str));
                                    new JSONObject();
                                    jSONObject2.put("status_code", ((Tuple) mapCheckUrlsStatus.get(str)).statusCode);
                                    new JSONObject();
                                    jSONObject2.put(TvContractCompat.Channels.COLUMN_DESCRIPTION, ((Tuple) mapCheckUrlsStatus.get(str)).error);
                                    jSONArray.put(jSONObject2);
                                }
                                jSONObject.put("error_triggers", jSONArray);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            voFmaEvent.setCustomParameter("root_cause_analysis", jSONObject.toString());
                            Log.d(VoFmaDispatcher.TAG, "Sending a service limited event");
                            VoFmaDispatcher.this.sendEvent(voFmaEvent, voFmaConfiguration.mServiceReportUrl);
                        }
                    }, 0L, VoFmaDispatcher.this.getRandomNumber(voFmaConfiguration.mServicePeriodOff_MinPeriod, voFmaConfiguration.mServicePeriodOff_MaxPeriod) * 1000);
                    return;
                }
                if (VoFmaDispatcher.this.mServiceOn) {
                    return;
                }
                Log.d(VoFmaDispatcher.TAG, "Status update: service limited mode ==> service on mode");
                VoFmaDispatcher.this.mServiceOn = true;
                VoFmaEvent voFmaEvent = new VoFmaEvent();
                voFmaEvent.setCustomType("SERVICE_ON");
                voFmaEvent.setCustomParameter("network_type", VoFmaDispatcher.this.mNetworkType);
                voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, VoFmaEvent.generateTimeString(System.currentTimeMillis()));
                voFmaEvent.setCurrentTime();
                voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, voFmaEvent.getTime());
                voFmaEvent.setCustomParameter("operator_name", voFmaConfiguration.mOperatorName);
                voFmaEvent.setCustomParameter("origin_version", voFmaConfiguration.mFmaVersion);
                voFmaEvent.setCustomParameter(MimeTypes.BASE_TYPE_APPLICATION, voFmaConfiguration.mApplicationName);
                voFmaEvent.setCustomParameter("application_version", voFmaConfiguration.mApplicationVersion);
                voFmaEvent.setCustomParameter("device_category", voFmaConfiguration.mDeviceCategory);
                voFmaEvent.setCustomParameter("origin_platform", "Android");
                voFmaEvent.setCustomParameter("device_id", voFmaConfiguration.mDeviceID);
                voFmaEvent.setCustomParameter("vo_player_id", voFmaConfiguration.mPlayerId);
                voFmaEvent.setCustomParameter("serial_number", voFmaConfiguration.mSerialNumber);
                voFmaEvent.setCustomParameter("firmware_version", voFmaConfiguration.mFirmwareVersion);
                voFmaEvent.setCustomParameter("terminal_id", voFmaConfiguration.mTerminalId);
                voFmaEvent.setCustomParameter("ip_address", VoFmaDispatcher.this.mIpAddress);
                VoFmaDispatcher.this.sendEvent(voFmaEvent, voFmaConfiguration.mServiceReportUrl);
            }
        }, 0L, voFmaConfiguration.mServicePeriodOn * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void monitorCustomEvents() {
        Boolean bool;
        Boolean bool2;
        try {
            if (this.mIsCheckCustomEventsCallCompleted.booleanValue()) {
                this.mIsCheckCustomEventsCallCompleted = false;
                if (!sendOfflineEvents(this.mDatabaseHelper.getAllLoggingEvents(), this.mConfiguration.mLoggingReportUrl).booleanValue()) {
                    bool = false;
                } else {
                    this.mDatabaseHelper.deleteAllLoggingEvents();
                    bool = true;
                }
                if (sendOfflineEvents(this.mDatabaseHelper.getAllServicesEvents(), this.mConfiguration.mServiceReportUrl).booleanValue()) {
                    this.mDatabaseHelper.deleteAllServicesEvents();
                    this.mIsServiceLimitedEventSend = true;
                }
                if (!sendOfflineEvents(this.mDatabaseHelper.getAllBootStatusEvents(), this.mConfiguration.mBootReportUrl).booleanValue()) {
                    bool2 = false;
                } else {
                    this.mDatabaseHelper.deleteAllBootStatusEvents();
                    bool2 = true;
                }
                if (sendOfflineEvents(this.mDatabaseHelper.getAllNetworkStatusEvents(), this.mConfiguration.mNetworkReportUrl).booleanValue()) {
                    this.mDatabaseHelper.deleteAllNetworkStatusEvents();
                    this.mIsNetworkStSent = true;
                }
                if (bool.booleanValue() && bool2.booleanValue() && this.mIsServiceLimitedEventSend.booleanValue() && this.mIsNetworkStSent.booleanValue()) {
                    this.mIsSendFailureExist = false;
                    this.mIsCheckCustomEventsCallCompleted = true;
                    Log.v(TAG, "All offline events are sent successfully");
                } else {
                    if (this.mConfiguration.mMonitoringNetworkRequired) {
                        return;
                    }
                    if (this.mConfiguration.mMonitoringLoggingRequired || this.mConfiguration.mMonitoringBootTimeRequired) {
                        if (isNetworkAvailable()) {
                            monitorNetworkStatus(this.mConfiguration.mFlushPeriod * 1000);
                        } else {
                            monitorOnFailure(this.mConfiguration.mFlushPeriod * 1000);
                        }
                        this.mIsCheckCustomEventsCallCompleted = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.mIsCheckCustomEventsCallCompleted = true;
        }
    }

    private void monitorOnFailure(int i) {
        Log.d(TAG, "Monitor sending events failure");
        if (this.mSendingFailureTimer != null) {
            Log.d(TAG, "Monitoring of failed to send events is already started");
            return;
        }
        Timer timer = new Timer();
        this.mSendingFailureTimer = timer;
        timer.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaDispatcher.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                VoFmaDispatcher voFmaDispatcher = VoFmaDispatcher.this;
                if (voFmaDispatcher.sendOfflineEvents(voFmaDispatcher.mDatabaseHelper.getAllServicesEvents(), VoFmaDispatcher.this.mConfiguration.mServiceReportUrl).booleanValue()) {
                    VoFmaDispatcher voFmaDispatcher2 = VoFmaDispatcher.this;
                    if (voFmaDispatcher2.sendOfflineEvents(voFmaDispatcher2.mDatabaseHelper.getAllLoggingEvents(), VoFmaDispatcher.this.mConfiguration.mLoggingReportUrl).booleanValue()) {
                        VoFmaDispatcher voFmaDispatcher3 = VoFmaDispatcher.this;
                        if (voFmaDispatcher3.sendOfflineEvents(voFmaDispatcher3.mDatabaseHelper.getAllBootStatusEvents(), VoFmaDispatcher.this.mConfiguration.mBootReportUrl).booleanValue()) {
                            Log.d(VoFmaDispatcher.TAG, "Offline events sent successfully, stopping checking timer");
                            VoFmaDispatcher.this.mSendingFailureTimer.cancel();
                            VoFmaDispatcher.this.mSendingFailureTimer = null;
                            return;
                        }
                    }
                }
                Log.d(VoFmaDispatcher.TAG, "Sending offline events failed, keep checking...");
            }
        }, 0L, i);
    }

    private void monitorNetworkStatus(int i) {
        Log.d(TAG, "NNM Network monitoring not required, Checking network status for logging feature");
        Timer timer = this.mNetworkMonitoringTimer;
        if (timer != null) {
            Log.d(TAG, "NNM, Network Monitoring timer is already started");
        } else {
            timer.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaDispatcher.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (!VoFmaDispatcher.this.isNetworkAvailable()) {
                        Log.d(VoFmaDispatcher.TAG, "NNM, Network status : OFF");
                        return;
                    }
                    Log.d(VoFmaDispatcher.TAG, "NNM, Network status update : OFF ==> ON");
                    Log.v(VoFmaDispatcher.TAG, "NNM, Network is available, Restarting services and customs events monitoring");
                    VoFmaDispatcher.this.mNetworkMonitoringTimer.cancel();
                    VoFmaDispatcher.this.mNetworkMonitoringTimer = null;
                    VoFmaDispatcher.this.monitorCustomEvents();
                }
            }, 0L, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void monitor_network(final VoFmaConfiguration voFmaConfiguration, final VoFmaEvent voFmaEvent) {
        Log.d(TAG, "Starting network monitoring");
        final Timer timer = new Timer();
        this.mNetworkMonitoringTimer = timer;
        timer.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaDispatcher.5
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (!VoFmaDispatcher.this.isNetworkAvailable() || !VoFmaDispatcher.this.NETWORK_OFF_DBG) {
                    timer.cancel();
                    final Timer timer2 = new Timer();
                    VoFmaDispatcher.this.mNetworkMonitoringTimer = timer2;
                    int randomNumber = VoFmaDispatcher.this.getRandomNumber(voFmaConfiguration.mNetworkPeriodOff_MinPeriod, voFmaConfiguration.mNetworkPeriodOff_MaxPeriod) * 1000;
                    if (VoFmaDispatcher.this.mConfiguration.mMonitoringLoggingRequired) {
                        randomNumber = Math.min(randomNumber, VoFmaDispatcher.this.mConfiguration.mFlushPeriod * 1000);
                    }
                    timer2.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaDispatcher.5.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            if (!VoFmaDispatcher.this.mNetworkOn) {
                                if (VoFmaDispatcher.this.isNetworkAvailable() && VoFmaDispatcher.this.NETWORK_OFF_DBG) {
                                    timer2.cancel();
                                    VoFmaDispatcher.this.monitor_network(voFmaConfiguration, voFmaEvent);
                                    return;
                                }
                                return;
                            }
                            Log.d(VoFmaDispatcher.TAG, "Status update: Network ON mode ==> Network OFF mode");
                            VoFmaDispatcher.this.mNetworkOn = false;
                            voFmaEvent.setCustomType("NETWORK_STATUS");
                            voFmaEvent.setCustomParameter("network_type", VoFmaDispatcher.this.mNetworkType);
                            VoFmaEvent voFmaEvent2 = voFmaEvent;
                            VoFmaEvent voFmaEvent3 = voFmaEvent;
                            voFmaEvent2.setCustomParameter("timestamp_off", VoFmaEvent.generateTimeString(System.currentTimeMillis()));
                            voFmaEvent.setCurrentTime();
                            voFmaEvent.setCustomParameter("network_status", VoFmaDispatcher.this.mIsNetworkAvailable.booleanValue() ? "ON" : "OFF");
                            voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, voFmaEvent.getTime());
                            voFmaEvent.setCustomParameter("operator_name", voFmaConfiguration.mOperatorName);
                            voFmaEvent.setCustomParameter(TtmlNode.ATTR_TTS_ORIGIN, "FMA");
                            voFmaEvent.setCustomParameter("origin_version", voFmaConfiguration.mFmaVersion);
                            voFmaEvent.setCustomParameter(MimeTypes.BASE_TYPE_APPLICATION, voFmaConfiguration.mApplicationName);
                            voFmaEvent.setCustomParameter("application_version", voFmaConfiguration.mApplicationVersion);
                            voFmaEvent.setCustomParameter("device_category", voFmaConfiguration.mDeviceCategory);
                            voFmaEvent.setCustomParameter("origin_platform", "Android");
                            voFmaEvent.setCustomParameter("device_id", voFmaConfiguration.mDeviceID);
                            voFmaEvent.setCustomParameter("vo_player_id", voFmaConfiguration.mPlayerId);
                            voFmaEvent.setCustomParameter("serial_number", voFmaConfiguration.mSerialNumber);
                            voFmaEvent.setCustomParameter("firmware_version", voFmaConfiguration.mFirmwareVersion);
                            voFmaEvent.setCustomParameter("terminal_id", voFmaConfiguration.mTerminalId);
                            voFmaEvent.setCustomParameter("ip_address", VoFmaDispatcher.this.mIpAddress);
                            new JSONObject();
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("url_path", VoFmaDispatcher.this.mConfiguration.mCheckTestUrl);
                                jSONObject.put("status_code", VoFmaDispatcher.this.mHttpStatus.statusCode);
                                jSONObject.put(TvContractCompat.Channels.COLUMN_DESCRIPTION, VoFmaDispatcher.this.mHttpStatus.error);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            voFmaEvent.setCustomParameter("test_url_status", jSONObject.toString());
                            VoFmaDispatcher.this.mDatabaseHelper.addNetworkStatusEvent(voFmaEvent);
                        }
                    }, 0L, randomNumber);
                    return;
                }
                if (!VoFmaDispatcher.this.mIsConfigurationFromBE.booleanValue()) {
                    VoFmaDispatcher voFmaDispatcher = VoFmaDispatcher.this;
                    voFmaDispatcher.getConfiguration(voFmaDispatcher.mParams.mConfigurationUrl);
                    VoFmaDispatcher.this.mIsConfigurationFromBE = true;
                }
                if (VoFmaDispatcher.this.mNetworkOn) {
                    return;
                }
                VoFmaDispatcher.this.mNetworkOn = true;
                Log.d(VoFmaDispatcher.TAG, "Status update: Network OFF mode ==> Network ON mode");
                VoFmaDispatcher.this.monitorCustomEvents();
            }
        }, 0L, voFmaConfiguration.mNetworkPeriodOn * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRandomNumber(int i, int i2) {
        return (int) ((Math.random() * ((double) (i2 - i))) + ((double) i));
    }

    void setParamsForMonitoring(VoFmaInitParameters voFmaInitParameters, String str) {
        this.mParams = voFmaInitParameters;
        this.mFmaVersion = str;
    }

    void setConfForMonitoring(VoFmaConfiguration voFmaConfiguration) {
        this.mConfiguration = voFmaConfiguration;
        if (this.mParsingRes == VoFmaController.VoFmaReturnCode.Success || this.mParsingRes == VoFmaController.VoFmaReturnCode.AlreadyInitialized) {
            startMonitoring(this.mConfiguration);
            return;
        }
        Log.d(TAG, "Failed to start FMA monitoring, Parsing of FMA configuration status code : " + this.mParsingRes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, Tuple> checkUrlsStatus() {
        HashMap<String, Tuple> map = new HashMap<>();
        if (this.mConfiguration.mMonitoredUrlsList != null && this.mConfiguration.mMonitoredUrlsList.size() != 0) {
            for (String str : this.mConfiguration.mMonitoredUrlsList.keySet()) {
                Tuple tupleCheckUrlStatus = checkUrlStatus(str);
                if (tupleCheckUrlStatus.statusCode == 200 || tupleCheckUrlStatus.statusCode == 302) {
                    Log.v(TAG, " GET Request to: " + str + " OK");
                } else {
                    Log.v(TAG, " GET Request: " + str + " FAILED");
                    map.put(str, tupleCheckUrlStatus);
                }
            }
        }
        return map;
    }

    private Tuple checkUrlStatus(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod(VOUtils.HTTP_GET);
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.connect();
            return new Tuple(httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage());
        } catch (MalformedURLException e) {
            return new Tuple(StreamingSessionOptions.CDN_EDGE_PARSER, e.getMessage());
        } catch (NoRouteToHostException e2) {
            return new Tuple(502, e2.getMessage());
        } catch (UnknownHostException e3) {
            return new Tuple(504, e3.getMessage());
        } catch (ConnectTimeoutException e4) {
            return new Tuple(408, e4.getMessage());
        } catch (Exception e5) {
            return new Tuple(500, e5.getMessage());
        }
    }

    private class Tuple {
        final String error;
        final int statusCode;

        public Tuple(int i, String str) {
            this.statusCode = i;
            this.error = str;
        }
    }

    private class OfflineTuple {
        final VoFmaEvent event;
        final Boolean parsingRes;

        public OfflineTuple(Boolean bool, VoFmaEvent voFmaEvent) {
            this.parsingRes = bool;
            this.event = voFmaEvent;
        }
    }

    public void setServiceStatusDbg(boolean z) {
        this.SERVICE_OFF_DBG = z;
    }

    public void setNetworkAvailabilityDbg(boolean z) {
        this.NETWORK_OFF_DBG = z;
    }

    public void rebootDeviceDbg() {
        this.mDatabaseHelper.addLastBootTime(System.currentTimeMillis());
    }

    public static String sha256(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getTimeZoneOffset() {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                return OffsetDateTime.now().getOffset().toString();
            }
            TimeZone timeZone = TimeZone.getDefault();
            return Integer.toString(timeZone.getOffset(GregorianCalendar.getInstance(timeZone).getTimeInMillis()));
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    private void monitorBootTime() {
        if (checkStorageRequestPermissions()) {
            this.mDatabaseLastSavedBootTime = this.mDatabaseHelper.getLastBootTimeFromDatabase();
            long jCurrentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            Log.v(TAG, "Last reboot time: " + jCurrentTimeMillis);
            Log.v(TAG, "Last reboot time stored in database: " + this.mDatabaseLastSavedBootTime);
            long j = this.mDatabaseLastSavedBootTime;
            if (jCurrentTimeMillis <= j) {
                Log.d(TAG, "Device didn't boot since last check");
            } else {
                if (0 == j) {
                    this.mDatabaseLastSavedBootTime = jCurrentTimeMillis;
                    this.mDatabaseHelper.addLastBootTime(jCurrentTimeMillis);
                }
                if (jCurrentTimeMillis > this.mDatabaseLastSavedBootTime + 10000) {
                    this.mDatabaseLastSavedBootTime = jCurrentTimeMillis;
                    this.mDatabaseHelper.updateLastBootTime(jCurrentTimeMillis);
                }
            }
            Log.d(TAG, "Last device boot time: " + this.mDatabaseLastSavedBootTime);
            sendBootedEvent(this.mDatabaseLastSavedBootTime);
            return;
        }
        Log.e(TAG, "FMA not able to extract the previous boot time from database due to missing permissions authorizations. \nPlease add the STORAGE read and write required permissions in the manifest file. Monitoring booting time failed.");
    }

    private void sendBootedEvent(long j) {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setCustomType("BOOT_STATUS");
        voFmaEvent.setCustomParameter("network_type", this.mNetworkType);
        voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, VoFmaEvent.generateTimeString(System.currentTimeMillis()));
        voFmaEvent.setCustomParameter("boot_timestamp", VoFmaEvent.generateTimeString(j));
        voFmaEvent.setCurrentTime();
        voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, voFmaEvent.getTime());
        voFmaEvent.setCustomParameter("operator_name", this.mConfiguration.mOperatorName);
        voFmaEvent.setCustomParameter(TtmlNode.ATTR_TTS_ORIGIN, "FMA");
        voFmaEvent.setCustomParameter("origin_version", this.mConfiguration.mFmaVersion);
        voFmaEvent.setCustomParameter(MimeTypes.BASE_TYPE_APPLICATION, this.mConfiguration.mApplicationName);
        voFmaEvent.setCustomParameter("application_version", this.mConfiguration.mApplicationVersion);
        voFmaEvent.setCustomParameter("device_category", this.mConfiguration.mDeviceCategory);
        voFmaEvent.setCustomParameter("origin_platform", "Android");
        voFmaEvent.setCustomParameter("device_id", this.mConfiguration.mDeviceID);
        voFmaEvent.setCustomParameter("vo_player_id", this.mConfiguration.mPlayerId);
        voFmaEvent.setCustomParameter("serial_number", this.mConfiguration.mSerialNumber);
        voFmaEvent.setCustomParameter("firmware_version", this.mConfiguration.mFirmwareVersion);
        voFmaEvent.setCustomParameter("terminal_id", this.mConfiguration.mTerminalId);
        voFmaEvent.setCustomParameter("ip_address", this.mIpAddress);
        Log.d(TAG, "Sending a BOOT_STATUS event");
        sendEvent(voFmaEvent, this.mConfiguration.mBootReportUrl);
    }

    private boolean checkNetworkRequestPermissions() {
        return this.mContext.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0 && this.mContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0 && this.mContext.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
    }

    private boolean checkStorageRequestPermissions() {
        if (Build.VERSION.SDK_INT < 19) {
            return this.mContext.checkCallingOrSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0 && this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        }
        return true;
    }

    protected VoFmaController.VoFmaReturnCode logCrEvent(VoFmaEvent.VOPlayerSeverity vOPlayerSeverity, String str, String str2) {
        VoFmaEvent voFmaEvent = new VoFmaEvent();
        voFmaEvent.setCustomType("LOG_EVENT");
        voFmaEvent.setCustomParameter("network_type", this.mNetworkType);
        voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, VoFmaEvent.generateTimeString(System.currentTimeMillis()));
        voFmaEvent.setCurrentTime();
        voFmaEvent.setCustomParameter(FmaDatabaseHelper.CONST_TIMESTAMP, voFmaEvent.getTime());
        voFmaEvent.setCustomParameter("operator_name", this.mConfiguration.mOperatorName);
        voFmaEvent.setCustomParameter(TtmlNode.ATTR_TTS_ORIGIN, "FMA");
        voFmaEvent.setCustomParameter("origin_version", this.mConfiguration.mFmaVersion);
        voFmaEvent.setCustomParameter(MimeTypes.BASE_TYPE_APPLICATION, this.mConfiguration.mApplicationName);
        voFmaEvent.setCustomParameter("application_version", this.mConfiguration.mApplicationVersion);
        voFmaEvent.setCustomParameter("device_category", this.mConfiguration.mDeviceCategory);
        voFmaEvent.setCustomParameter("origin_platform", "Android");
        voFmaEvent.setCustomParameter("device_id", this.mConfiguration.mDeviceID);
        voFmaEvent.setCustomParameter("vo_player_id", this.mConfiguration.mPlayerId);
        voFmaEvent.setCustomParameter("serial_number", this.mConfiguration.mSerialNumber);
        voFmaEvent.setCustomParameter("firmware_version", this.mConfiguration.mFirmwareVersion);
        voFmaEvent.setCustomParameter("terminal_id", this.mConfiguration.mTerminalId);
        voFmaEvent.setCustomParameter("ip_address", this.mIpAddress);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("severity", vOPlayerSeverity);
            jSONObject.put("title", str);
            jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        voFmaEvent.setCustomParameter("log_event", jSONObject.toString());
        sendEvent(voFmaEvent, this.mConfiguration.mLoggingReportUrl);
        return VoFmaController.VoFmaReturnCode.Success;
    }

    private void monitorDatabaseOldEvents(int i) {
        Log.d(TAG, "Monitoring Database old events timer");
        if (this.mDatabaseOldEventsTimer != null) {
            Log.d(TAG, "Monitoring of Database old events timer is already started");
            return;
        }
        Timer timer = new Timer();
        this.mDatabaseOldEventsTimer = timer;
        timer.schedule(new TimerTask() { // from class: com.viaccessorca.analytics.fma.VoFmaDispatcher.6
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                FmaDatabaseHelper fmaDatabaseHelper = VoFmaDispatcher.this.mDatabaseHelper;
                FmaDatabaseHelper fmaDatabaseHelper2 = VoFmaDispatcher.this.mDatabaseHelper;
                fmaDatabaseHelper.deleteOldEventsFromTable(FmaDatabaseHelper.OFFLINE_LOGGING_EVENTS_TABLE_NAME, System.currentTimeMillis() + ((long) VoFmaDispatcher.this.mConfiguration.mLifeSpan));
            }
        }, 0L, this.mConfiguration.mLifeSpan * 1000);
    }
}
