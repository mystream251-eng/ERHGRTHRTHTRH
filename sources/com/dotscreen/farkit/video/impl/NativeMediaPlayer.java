package com.dotscreen.farkit.video.impl;

import android.content.Context;
import android.drm.DrmEvent;
import android.drm.DrmInfo;
import android.drm.DrmInfoRequest;
import android.drm.DrmInfoStatus;
import android.drm.DrmManagerClient;
import android.media.TimedText;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.ConditionVariable;
import android.util.Log;
import android.view.SurfaceView;
import androidx.work.WorkRequest;
import com.dotscreen.farkit.video.ISurfaceView;
import com.dotscreen.farkit.video.MediaPlayer;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.vodownloader.NanoHTTPD;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class NativeMediaPlayer extends MediaPlayer implements android.media.MediaPlayer.OnPreparedListener, android.media.MediaPlayer.OnBufferingUpdateListener, android.media.MediaPlayer.OnCompletionListener, android.media.MediaPlayer.OnErrorListener, android.media.MediaPlayer.OnInfoListener, android.media.MediaPlayer.OnVideoSizeChangedListener, android.media.MediaPlayer.OnSeekCompleteListener, android.media.MediaPlayer.OnTimedTextListener {
    private static final String PR_KEY_CONTENTS_PATH = "PRContentPath";
    private static final String PR_KEY_CUSTOM_DATA = "PRCustomData";
    private static final String PR_KEY_LICENSE_ACK_RESPONSE = "PRLicenseAckResponse";
    private static final String PR_KEY_LICENSE_RESPONSE = "PRLicenseResponse";
    private static final String PR_KEY_LICENSE_SERVER_URL = "PRLicenseServerUrl";
    private static final String PR_MIMETYPE = "application/vnd.ms-playready";
    private static final String PR_NO_DATA = "PRNoData";
    private static final String TAG = "FilmoMediaPlayer";
    private Uri mContentUri;
    private Context mContext;
    private String mCustomData;
    private DrmManagerClient mDrmManagerClient;
    private Uri mLicenceUri;
    private android.media.MediaPlayer mPlayer;

    public NativeMediaPlayer(Context context) {
        this.mContext = context;
        android.media.MediaPlayer mediaPlayer = new android.media.MediaPlayer();
        this.mPlayer = mediaPlayer;
        mediaPlayer.setOnPreparedListener(this);
        this.mPlayer.setOnBufferingUpdateListener(this);
        this.mPlayer.setOnCompletionListener(this);
        this.mPlayer.setOnErrorListener(this);
        this.mPlayer.setOnInfoListener(this);
        this.mPlayer.setOnVideoSizeChangedListener(this);
        this.mPlayer.setOnSeekCompleteListener(this);
        this.mPlayer.setOnTimedTextListener(this);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void setDisplay(ISurfaceView iSurfaceView) {
        super.setDisplay(iSurfaceView);
        this.mPlayer.setDisplay(((SurfaceView) iSurfaceView.getView()).getHolder());
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void setDataSource(Context context, Uri uri, Uri uri2, String str, Map<String, String> map) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Log.v(TAG, "setDataSource(");
        Log.v(TAG, "    uri = " + uri);
        Log.v(TAG, "    licenceUri = " + uri2);
        Log.v(TAG, "    customData = " + str);
        Log.v(TAG, "    headers = " + map);
        this.mContentUri = uri;
        this.mLicenceUri = uri2;
        this.mCustomData = str;
        this.mPlayer.setDataSource(context, uri, map);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void prepare() throws IllegalStateException, IOException {
        this.mPlayer.prepare();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void prepareAsync() throws IllegalStateException {
        this.mPlayer.prepareAsync();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void start() throws IllegalStateException {
        stayAwake(true);
        this.mPlayer.start();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void stop() throws IllegalStateException {
        stayAwake(false);
        this.mPlayer.stop();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void pause() throws IllegalStateException {
        stayAwake(false);
        this.mPlayer.pause();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public int getVideoWidth() {
        return this.mPlayer.getVideoWidth();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public int getVideoHeight() {
        return this.mPlayer.getVideoHeight();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public boolean isPlaying() {
        return this.mPlayer.isPlaying();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public boolean isCrypted() {
        return this.mLicenceUri != null;
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void seekTo(int i) throws IllegalStateException {
        this.mPlayer.seekTo(i);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public int getCurrentPosition() {
        return this.mPlayer.getCurrentPosition();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public int getDuration() {
        return this.mPlayer.getDuration();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void release() {
        stayAwake(false);
        this.mPlayer.release();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void reset() {
        stayAwake(false);
        this.mPlayer.reset();
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void setAudioStreamType(int i) {
        this.mPlayer.setAudioStreamType(i);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public MediaPlayer.TrackInfo[] getTrackInfo() throws IllegalStateException {
        android.media.MediaPlayer.TrackInfo[] trackInfo = this.mPlayer.getTrackInfo();
        if (trackInfo == null) {
            return new MediaPlayer.TrackInfo[0];
        }
        MediaPlayer.TrackInfo[] trackInfoArr = new MediaPlayer.TrackInfo[trackInfo.length];
        for (int i = 0; i < trackInfo.length; i++) {
            String language = trackInfo[i].getLanguage();
            int trackType = trackInfo[i].getTrackType();
            int i2 = 3;
            if (trackType == 1) {
                i2 = 1;
            } else if (trackType == 2) {
                i2 = 2;
            } else if (trackType != 3) {
                i2 = 0;
            }
            trackInfoArr[i] = new MediaPlayer.TrackInfo(i2, language);
        }
        return trackInfoArr;
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public int getSelectedTrack(int i) throws IllegalStateException {
        int i2 = 3;
        if (i == 1) {
            i2 = 1;
        } else if (i == 2) {
            i2 = 2;
        } else if (i != 3) {
            i2 = 0;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mPlayer.getSelectedTrack(i2);
        }
        return 0;
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void selectTrack(int i, int i2) throws IllegalStateException {
        this.mPlayer.selectTrack(i);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void deselectTrack(int i, int i2) throws IllegalStateException {
        this.mPlayer.deselectTrack(i);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void addTimedTextSource(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        this.mPlayer.addTimedTextSource(str, str2);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void addTimedTextSource(Context context, Uri uri, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.mPlayer.addTimedTextSource(context, uri, str);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void addTimedTextSource(FileDescriptor fileDescriptor, String str) throws IllegalStateException, IllegalArgumentException {
        this.mPlayer.addTimedTextSource(fileDescriptor, str);
    }

    @Override // com.dotscreen.farkit.video.MediaPlayer
    public void addTimedTextSource(FileDescriptor fileDescriptor, long j, long j2, String str) throws IllegalStateException, IllegalArgumentException {
        this.mPlayer.addTimedTextSource(fileDescriptor, j, j2, str);
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(android.media.MediaPlayer mediaPlayer, int i) {
        if (this.mOnBufferingUpdateListener != null) {
            this.mOnBufferingUpdateListener.onBufferingUpdate(this, i);
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(android.media.MediaPlayer mediaPlayer) {
        stayAwake(false);
        if (this.mOnCompletionListener != null) {
            this.mOnCompletionListener.onCompletion(this);
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(android.media.MediaPlayer mediaPlayer, int i, int i2) {
        stayAwake(false);
        if (this.mOnErrorListener != null) {
            return this.mOnErrorListener.onError(this, i, i2);
        }
        return false;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(android.media.MediaPlayer mediaPlayer, int i, int i2) {
        if (this.mOnInfoListener != null) {
            return this.mOnInfoListener.onInfo(this, i, i2);
        }
        return false;
    }

    @Override // android.media.MediaPlayer.OnTimedTextListener
    public void onTimedText(android.media.MediaPlayer mediaPlayer, TimedText timedText) {
        if (this.mOnTimedTextListener != null) {
            this.mOnTimedTextListener.onTimedText(this, timedText != null ? new com.dotscreen.farkit.video.TimedText(timedText.getText()) : null);
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(android.media.MediaPlayer mediaPlayer) {
        if (this.mLicenceUri != null) {
            DrmManagerClient drmManagerClient = new DrmManagerClient(this.mContext);
            String smoothStreamingBaseUrl = getSmoothStreamingBaseUrl(this.mContentUri.toString());
            int iCheckRightsStatus = drmManagerClient.checkRightsStatus(smoothStreamingBaseUrl);
            Log.i(TAG, "onPrepare: checkRights " + smoothStreamingBaseUrl + " - " + iCheckRightsStatus);
            if (iCheckRightsStatus == 0 || iCheckRightsStatus == 1) {
                Log.i(TAG, "rights allready ok");
                if (this.mOnPreparedListener != null) {
                    this.mOnPreparedListener.onPrepared(this);
                    return;
                }
                return;
            }
            if (iCheckRightsStatus == 2 || iCheckRightsStatus == 3) {
                this.mDrmManagerClient = drmManagerClient;
                new AcquireRightsTask().execute(smoothStreamingBaseUrl);
                return;
            }
            return;
        }
        if (this.mOnPreparedListener != null) {
            this.mOnPreparedListener.onPrepared(this);
        }
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(android.media.MediaPlayer mediaPlayer, int i, int i2) {
        if (this.mOnVideoSizeChangedListener != null) {
            this.mOnVideoSizeChangedListener.onVideoSizeChanged(this, i, i2);
        }
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(android.media.MediaPlayer mediaPlayer) {
        if (this.mOnSeekCompleteListener != null) {
            this.mOnSeekCompleteListener.onSeekComplete(this);
        }
    }

    private static String getSmoothStreamingBaseUrl(String str) {
        int iLastIndexOf = str.toLowerCase().lastIndexOf(".ism/manifest");
        return iLastIndexOf > 0 ? str.substring(0, iLastIndexOf + 4) : str;
    }

    private class AcquireRightsTask extends AsyncTask<String, String, Boolean> {
        private AcquireRightsTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(String... strArr) {
            if (strArr.length < 1) {
                return false;
            }
            String str = strArr[0];
            DrmInfoRequest drmInfoRequest = new DrmInfoRequest(3, NativeMediaPlayer.PR_MIMETYPE);
            drmInfoRequest.put(NativeMediaPlayer.PR_KEY_CONTENTS_PATH, str);
            drmInfoRequest.put("PRCustomData", NativeMediaPlayer.this.mCustomData);
            DrmInfo drmInfoAcquireDrmInfo = NativeMediaPlayer.this.mDrmManagerClient.acquireDrmInfo(drmInfoRequest);
            if (drmInfoAcquireDrmInfo == null) {
                Log.e(NativeMediaPlayer.TAG, "Failed to DrmManagerClient.acquireDrmInfo(TYPE_RIGHTS_ACQUISITION_INFO)");
                return false;
            }
            publishProgress("Sending license challenge");
            String string = NativeMediaPlayer.this.mLicenceUri.toString();
            drmInfoAcquireDrmInfo.put(NativeMediaPlayer.PR_KEY_LICENSE_RESPONSE, NativeMediaPlayer.sendSoapAction(string, new String(drmInfoAcquireDrmInfo.getData()), "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense"));
            if (NativeMediaPlayer.executeProcessDrmInfo(NativeMediaPlayer.this.mDrmManagerClient, drmInfoAcquireDrmInfo) == 2) {
                Log.e(NativeMediaPlayer.TAG, "Failed to DrmManagerClient.processDrmInfo(TYPE_RIGHTS_ACQUISITION_INFO)");
                return false;
            }
            DrmInfoRequest drmInfoRequest2 = new DrmInfoRequest(4, NativeMediaPlayer.PR_MIMETYPE);
            drmInfoRequest2.put(NativeMediaPlayer.PR_KEY_CONTENTS_PATH, str);
            DrmInfo drmInfoAcquireDrmInfo2 = NativeMediaPlayer.this.mDrmManagerClient.acquireDrmInfo(drmInfoRequest2);
            if (drmInfoAcquireDrmInfo2 == null) {
                Log.e(NativeMediaPlayer.TAG, "Failed to DrmManagerClient.acquireDrmInfo(TYPE_RIGHTS_ACQUISITION_PROGRESS_INFO)");
                return false;
            }
            String str2 = new String(drmInfoAcquireDrmInfo2.getData());
            if (!str2.isEmpty() && !str2.equals(NativeMediaPlayer.PR_NO_DATA)) {
                publishProgress("Sending license ACK");
                drmInfoAcquireDrmInfo2.put(NativeMediaPlayer.PR_KEY_LICENSE_ACK_RESPONSE, NativeMediaPlayer.sendSoapAction(string, str2, "http://schemas.microsoft.com/DRM/2007/03/protocols/AcknowledgeLicense"));
                if (NativeMediaPlayer.executeProcessDrmInfo(NativeMediaPlayer.this.mDrmManagerClient, drmInfoAcquireDrmInfo2) == 2) {
                    Log.e(NativeMediaPlayer.TAG, "Failed to DrmManagerClient.processDrmInfo(TYPE_RIGHTS_ACQUISITION_PROGRESS_INFO)");
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            Log.i(NativeMediaPlayer.TAG, bool.booleanValue() ? "Success in acquring rights" : "Failed in acquiring rights");
            if (bool.booleanValue()) {
                if (NativeMediaPlayer.this.mOnPreparedListener != null) {
                    NativeMediaPlayer.this.mOnPreparedListener.onPrepared(NativeMediaPlayer.this);
                }
            } else if (NativeMediaPlayer.this.mOnErrorListener != null) {
                NativeMediaPlayer.this.mOnErrorListener.onError(NativeMediaPlayer.this, -1, 0);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(String... strArr) {
            Log.i(NativeMediaPlayer.TAG, strArr[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v6 */
    public static String sendSoapAction(String str, String str2, String str3) throws Throwable {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        ?? r0 = 0;
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setUseCaches(false);
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setRequestMethod(VOUtils.HTTP_POST);
                    httpURLConnection2.setRequestProperty("Content-Type", NanoHTTPD.MIME_XML);
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection2.getOutputStream());
                    dataOutputStream.write(str2.getBytes());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection2.getInputStream());
                    while (true) {
                        i = bufferedInputStream.read();
                        if (i == -1) {
                            break;
                        }
                        stringBuffer.append((char) i);
                    }
                    bufferedInputStream.close();
                    r0 = i;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                        r0 = i;
                    }
                } catch (IOException e) {
                    e = e;
                    httpURLConnection = httpURLConnection2;
                    Log.e(TAG, "sendSoapAction: " + e);
                    e.printStackTrace();
                    r0 = httpURLConnection;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        r0 = httpURLConnection;
                    }
                } catch (Throwable th) {
                    th = th;
                    r0 = httpURLConnection2;
                    if (r0 != 0) {
                        r0.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int executeProcessDrmInfo(DrmManagerClient drmManagerClient, DrmInfo drmInfo) {
        final ConditionVariable conditionVariable = new ConditionVariable();
        final int[] iArr = {2};
        drmManagerClient.setOnEventListener(new DrmManagerClient.OnEventListener() { // from class: com.dotscreen.farkit.video.impl.NativeMediaPlayer.1
            @Override // android.drm.DrmManagerClient.OnEventListener
            public void onEvent(DrmManagerClient drmManagerClient2, DrmEvent drmEvent) {
                int type = drmEvent.getType();
                if (type == -1 || type == 1002) {
                    Log.d(NativeMediaPlayer.TAG, "processDrmInfo() completed");
                    iArr[0] = ((DrmInfoStatus) drmEvent.getAttribute("drm_info_status_object")).statusCode;
                } else {
                    Log.d(NativeMediaPlayer.TAG, "Unkown event: " + drmEvent.getType());
                }
                conditionVariable.open();
            }
        });
        if (drmManagerClient.processDrmInfo(drmInfo) != 0) {
            return 2;
        }
        conditionVariable.block(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
        return iArr[0];
    }
}
