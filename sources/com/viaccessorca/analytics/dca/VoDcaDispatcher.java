package com.viaccessorca.analytics.dca;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker;
import com.viaccessorca.common.VOUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VoDcaDispatcher {
    private static final String CATCHUP_EVENTS_END_POINT = "catchupEvent";
    private static final String DOC_FOLDER = Environment.getExternalStorageDirectory() + "/Documents";
    private static final String DOWNLOAD_EVENTS_END_POINT = "downloadEvent";
    private static final String LIVE_EVENTS_END_POINT = "liveEvent";
    private static final String NPVR_EVENTS_END_POINT = "npvrEvent";
    private static final String PREVIEW_EVENTS_END_POINT = "previewEvent";
    private static final int QUEUE_CAPACITY = 512;
    private static final String STARTOVER_EVENTS_END_POINT = "startoverEvent";
    private static final String TAG = "VO_DCA";
    private static final String URL_PATTERN = "(?i)\\b(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10)(?:\\.\\d{1,3}){3})(?!(?:169\\.254)(?:\\.\\d{1,3}){2})(?!127\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?!192.168\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|21[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?\\b";
    private static final String VOD_EVENTS_END_POINT = "vodEvent";
    private Context mContext;
    protected DcaDatabaseHelper mDatabaseHelper;
    private boolean mDownload2goEnabled;
    private List<JSONObject> mOfflineCatchupQueue;
    private List<JSONObject> mOfflineDownloadQueue;
    private List<JSONObject> mOfflineLiveQueue;
    private List<JSONObject> mOfflineNpvrQueue;
    private List<JSONObject> mOfflinePreviewQueue;
    private LinkedBlockingQueue<VoDcaEvent> mOfflineQueue;
    private List<JSONObject> mOfflineStartoverQueue;
    private List<JSONObject> mOfflineVodQueue;
    private final int ATTEMPT_COUNT = DefaultOggSeeker.MATCH_BYTE_RANGE;
    private boolean NETWORK_DBG = true;
    protected VoDcaDispatcherListener mListener = null;
    protected boolean mContinue = true;
    protected Object mSync = new Object();

    private class AsyncTaskGetConfiguration extends AsyncTask<String, Void, Void> {
        private VoDcaConfiguration mConf;
        private boolean mSuccess;

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
        /* JADX WARN: Code duplicated, block: B:24:0x00a1  */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            StringBuilder sb;
            this.mSuccess = true;
            try {
                String str = strArr[0];
                URL url = new URL(strArr[0]);
                String str2 = strArr[0];
                this.mConf = new VoDcaConfiguration();
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod(VOUtils.HTTP_GET);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setConnectTimeout(60000);
                    httpURLConnection.setReadTimeout(60000);
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
                        } catch (Exception e) {
                            e = e;
                            Log.e(VoDcaDispatcher.TAG, "AsyncTaskGetConfiguration - Failed to send message");
                            e.printStackTrace();
                            this.mSuccess = false;
                        }
                        if (sb == null && this.mSuccess) {
                            this.mConf.parse(sb.toString());
                        } else {
                            this.mSuccess = false;
                        }
                        return null;
                    }
                    Log.e(VoDcaDispatcher.TAG, "AsyncTaskGetConfiguration - HttpResult: " + responseCode);
                    this.mSuccess = false;
                    sb = null;
                } catch (Exception e2) {
                    e = e2;
                    sb = null;
                }
                if (sb == null) {
                    this.mSuccess = false;
                } else {
                    this.mSuccess = false;
                }
                return null;
            } catch (MalformedURLException e3) {
                e3.printStackTrace();
                this.mSuccess = false;
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            if (this.mSuccess) {
                VoDcaDispatcher.this.mListener.onConfigurationReceived(this.mConf);
            } else {
                VoDcaDispatcher.this.mListener.onError();
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

    private class AsyncTaskSendEvent extends AsyncTask<VoDcaEvent, Void, Void> {
        protected boolean mRetry;
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
        }

        public boolean toRetry() {
            return this.mRetry;
        }

        public void setUrl(String str) {
            this.mUrl = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(VoDcaEvent... voDcaEventArr) {
            VoDcaEvent voDcaEvent = voDcaEventArr[0];
            this.mRetry = false;
            JSONObject jSONObject = new JSONObject();
            try {
                JSONObject jsonObject = voDcaEvent.getJsonObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("value", jsonObject);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject2);
                jSONObject.put("records", jSONArray);
            } catch (JSONException e) {
                Log.e(VoDcaDispatcher.TAG, "AsyncTaskSendEvent - Failed to generate Json from event");
                e.printStackTrace();
            }
            Log.d(VoDcaDispatcher.TAG, "DCA ==> BE: " + this.mUrl);
            Log.d(VoDcaDispatcher.TAG, "DCA ==> BE: " + jSONObject.toString());
            try {
                URL url = new URL(this.mUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod(VOUtils.HTTP_POST);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(90000);
                httpURLConnection.setReadTimeout(90000);
                httpURLConnection.setRequestProperty("Content-Type", "application/vnd.kafka.json.v1+json");
                httpURLConnection.setRequestProperty("Host", url.getHost());
                httpURLConnection.connect();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                outputStreamWriter.write(jSONObject.toString());
                outputStreamWriter.close();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    VoDcaDispatcher.this.mListener.onEventSent(new VoDcaEvent(voDcaEvent));
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line + "\n");
                    }
                } else {
                    Log.e(VoDcaDispatcher.TAG, "AsyncTaskSendEvent - HttpResult: " + responseCode);
                }
            } catch (Exception e2) {
                Log.e(VoDcaDispatcher.TAG, "AsyncTaskSendEvent - Failed to send message");
                e2.printStackTrace();
                this.mRetry = true;
            }
            synchronized (VoDcaDispatcher.this.mSync) {
                VoDcaDispatcher.this.mSync.notify();
            }
            return null;
        }
    }

    protected VoDcaDispatcher(Context context) {
        this.mDownload2goEnabled = false;
        this.mContext = context;
        if (context == null || context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return;
        }
        this.mDownload2goEnabled = true;
        this.mDatabaseHelper = new DcaDatabaseHelper(context);
        this.mOfflineCatchupQueue = new ArrayList();
        this.mOfflineVodQueue = new ArrayList();
        this.mOfflinePreviewQueue = new ArrayList();
        this.mOfflineNpvrQueue = new ArrayList();
        this.mOfflineDownloadQueue = new ArrayList();
        this.mOfflineLiveQueue = new ArrayList();
        this.mOfflineStartoverQueue = new ArrayList();
    }

    protected void getConfiguration(String str) {
        new AsyncTaskGetConfiguration().execute(str);
    }

    protected void sendEvent(VoDcaEvent voDcaEvent, String str) {
        if (this.mDownload2goEnabled) {
            String[] strArrSplit = str.split("/");
            String str2 = strArrSplit[strArrSplit.length - 1];
            String strSubstring = str.substring(0, str.lastIndexOf(str2));
            if (isNetworkAvailable() && this.NETWORK_DBG) {
                this.mOfflineLiveQueue = this.mDatabaseHelper.getAllLiveEvents();
                this.mOfflineStartoverQueue = this.mDatabaseHelper.getAllStartoverEvents();
                this.mOfflineCatchupQueue = this.mDatabaseHelper.getAllCatchupEvents();
                this.mOfflineVodQueue = this.mDatabaseHelper.getAllVodEvents();
                this.mOfflinePreviewQueue = this.mDatabaseHelper.getAllPreviewEvents();
                this.mOfflineNpvrQueue = this.mDatabaseHelper.getAllNpvrEvents();
                this.mOfflineDownloadQueue = this.mDatabaseHelper.getAllDownloadEvents();
                sendOfflineEvents(this.mOfflineLiveQueue, strSubstring + LIVE_EVENTS_END_POINT);
                this.mDatabaseHelper.deleteAllLiveEvents();
                sendOfflineEvents(this.mOfflineStartoverQueue, strSubstring + STARTOVER_EVENTS_END_POINT);
                this.mDatabaseHelper.deleteAllStartoverEvents();
                sendOfflineEvents(this.mOfflineCatchupQueue, strSubstring + CATCHUP_EVENTS_END_POINT);
                this.mDatabaseHelper.deleteAllCatchupEvents();
                sendOfflineEvents(this.mOfflineVodQueue, strSubstring + VOD_EVENTS_END_POINT);
                this.mDatabaseHelper.deleteAllVodEvents();
                sendOfflineEvents(this.mOfflinePreviewQueue, strSubstring + PREVIEW_EVENTS_END_POINT);
                this.mDatabaseHelper.deleteAllPreviewEvents();
                sendOfflineEvents(this.mOfflineNpvrQueue, strSubstring + NPVR_EVENTS_END_POINT);
                this.mDatabaseHelper.deleteAllNpvrEvents();
                sendOfflineEvents(this.mOfflineDownloadQueue, strSubstring + DOWNLOAD_EVENTS_END_POINT);
                this.mDatabaseHelper.deleteAllDownloadEvents();
            } else if (str2.equals(LIVE_EVENTS_END_POINT)) {
                this.mDatabaseHelper.addLiveEvent(voDcaEvent);
            } else if (str2.equals(STARTOVER_EVENTS_END_POINT)) {
                this.mDatabaseHelper.addStartoverEvent(voDcaEvent);
            } else if (str2.equals(CATCHUP_EVENTS_END_POINT)) {
                this.mDatabaseHelper.addCatchupEvent(voDcaEvent);
            } else if (str2.equals(VOD_EVENTS_END_POINT)) {
                this.mDatabaseHelper.addVodEvent(voDcaEvent);
            } else if (str2.equals(PREVIEW_EVENTS_END_POINT)) {
                this.mDatabaseHelper.addPreviewEvent(voDcaEvent);
            } else if (str2.equals(NPVR_EVENTS_END_POINT)) {
                this.mDatabaseHelper.addNpvrEvent(voDcaEvent);
            } else if (str2.equals(DOWNLOAD_EVENTS_END_POINT)) {
                this.mDatabaseHelper.addDownloadEvent(voDcaEvent);
            } else {
                Log.v(TAG, "Endpoint: " + str2 + " not expected");
            }
        }
        if (this.NETWORK_DBG) {
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
                    Log.v(TAG, "DCA: Invalid URL: " + str);
                    return;
                }
                asyncTaskSendEvent = new AsyncTaskSendEvent();
                asyncTaskSendEvent.setUrl(str);
                synchronized (this.mSync) {
                    asyncTaskSendEvent.execute(voDcaEvent);
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

    protected void setListener(VoDcaDispatcherListener voDcaDispatcherListener) {
        this.mListener = voDcaDispatcherListener;
    }

    private int performPostRequest(String str, JSONObject jSONObject) {
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod(VOUtils.HTTP_POST);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(90000);
            httpURLConnection.setReadTimeout(90000);
            httpURLConnection.setRequestProperty("Content-Type", "application/vnd.kafka.json.v1+json");
            httpURLConnection.setRequestProperty("Host", url.getHost());
            httpURLConnection.connect();
            String string = jSONObject.toString();
            Log.v(TAG, "" + string);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            outputStreamWriter.write(string.toString());
            outputStreamWriter.close();
            return httpURLConnection.getResponseCode();
        } catch (Exception e) {
            Log.e(TAG, "Failed to send message");
            e.printStackTrace();
            return 500;
        }
    }

    private void sendOfflineEvents(List<JSONObject> list, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (list.size() > 0) {
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
                return;
            }
            return;
        }
        Log.v(TAG, "Queue empty, no offline events found ");
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

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void setNetworkAvailabilityDbg(boolean z) {
        this.NETWORK_DBG = z;
    }
}
