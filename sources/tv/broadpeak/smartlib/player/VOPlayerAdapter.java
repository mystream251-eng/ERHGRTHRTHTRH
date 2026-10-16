package tv.broadpeak.smartlib.player;

import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.smartlib.SmartLib;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;
import tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler;

/* JADX INFO: loaded from: classes3.dex */
public class VOPlayerAdapter extends PlayerAdapterHandler implements VOPlayer.OnErrorListener, VOPlayer.OnSeekCompleteListener, VOPlayer.OnBufferingUpdateListener, VOPlayerStateManager.IListener, VOPlayer.OnInformationListener {
    private static final String TAG = "BpkVOPlayerAdapter";
    private int mBitrate;
    private boolean mBuffering;
    private VOPlayer mPlayer;
    private boolean mPlaying;
    private boolean mStarted;
    private Pattern mVersionPattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)");
    private boolean mWasSeeking;

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public boolean attachPlayer(JSObject jSObject, Object obj, Object obj2) {
        if (!checkPlayer(obj, obj2)) {
            return false;
        }
        super.attachPlayer(jSObject, obj, obj2);
        VOPlayer vOPlayer = (VOPlayer) obj;
        this.mPlayer = vOPlayer;
        vOPlayer.setOnErrorListener(this);
        this.mPlayer.setOnSeekCompleteListener(this);
        this.mPlayer.setOnBufferingUpdateListener(this);
        this.mPlayer.setOnInformationListener(this);
        return true;
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public boolean checkPlayer(Object obj, Object obj2) {
        return obj instanceof VOPlayer;
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public void detachPlayer() {
        VOPlayerStateManager.getInstance().destroy();
        VOPlayer vOPlayer = this.mPlayer;
        if (vOPlayer != null) {
            vOPlayer.removeOnErrorListener(this);
            this.mPlayer.removeOnSeekCompleteListener(this);
            this.mPlayer.removeOnBufferingUpdateListener(this);
            this.mPlayer.removeOnInformationListener(this);
            this.mPlayer = null;
        }
        super.detachPlayer();
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public int getBitrate() {
        return this.mBitrate;
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public JSValue getCapabilities() {
        JSContext jSContext = CoreEngine.getInstance().getJSContext();
        JSObject jSObjectCreateJSObject = jSContext.createJSObject();
        jSObjectCreateJSObject.setProperty("adTracking", jSContext.createJSBoolean(true));
        return jSObjectCreateJSObject;
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public double getDuration() {
        VOPlayer vOPlayer = this.mPlayer;
        if (vOPlayer == null || vOPlayer.getState() == VOPlayer.PlayerState.RELEASED) {
            return 0.0d;
        }
        return this.mPlayer.getDuration();
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public String getName() {
        return "VOPlayer";
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public double getPosition() {
        VOPlayer vOPlayer = this.mPlayer;
        if (vOPlayer == null || vOPlayer.getState() == VOPlayer.PlayerState.RELEASED) {
            return 0.0d;
        }
        return this.mPlayer.getCurrentPTS() > 1639000000000L ? this.mPlayer.getCurrentPTS() : this.mPlayer.getCurrentPosition();
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public String getVersion() {
        try {
            String sdkVersion = VOPlayer.getSdkVersion(CoreEngine.getInstance().getContext());
            Matcher matcher = this.mVersionPattern.matcher(sdkVersion);
            if (matcher.find()) {
                sdkVersion = matcher.group(0);
            }
            return sdkVersion != null ? sdkVersion.substring(Math.max(0, sdkVersion.length() - 30)) : sdkVersion;
        } catch (Exception e) {
            e.printStackTrace();
            return "09.x.x";
        }
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public void initSessionPlayerObjects() {
        this.mBitrate = -1;
        this.mStarted = false;
        this.mPlaying = true;
        this.mBuffering = false;
        this.mWasSeeking = true;
        if (this.mPlayer != null) {
            VOPlayerStateManager.getInstance().init(this.mPlayer, this);
        }
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(VOPlayer vOPlayer, int i) {
        boolean z = this.mStarted;
        if (!z && i >= 100) {
            this.mStarted = true;
            notifyFirstImage();
            return;
        }
        if (z && i >= 100) {
            notifyStallEnd(true);
            VOPlayerStateManager.getInstance().forcePollPlayerPosition();
            this.mBuffering = false;
        } else {
            if (!z || this.mBuffering) {
                return;
            }
            notifyStallStart();
            VOPlayerStateManager.getInstance().forcePollPlayerPosition();
            this.mBuffering = true;
        }
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnErrorListener
    public boolean onError(VOPlayer vOPlayer, int i, int i2) {
        int i3;
        if (i == 4000 || i == 4002) {
            i3 = SmartLib.BPNetworkingError;
        } else {
            i3 = (i == 1000 && i2 == 1008) ? 3002 : 3005;
        }
        setStatusCode(i3);
        setPlayerErrorCode(i + "." + i2);
        return false;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnInformationListener
    public boolean onInformation(VOPlayer vOPlayer, int i, int i2, Object obj) {
        VOPlayer vOPlayer2;
        int httpStreamingAlternateBitrate;
        if (i == 8000 && i2 == 8008) {
            if (!this.mStarted || this.mPlayer.getState() == VOPlayer.PlayerState.RELEASED) {
                return false;
            }
            if (this.mPlayer.isPlaying()) {
                notifyResume();
                return false;
            }
            notifyPause();
            return false;
        }
        if (i != 1000 || i2 != 1001 || (vOPlayer2 = this.mPlayer) == null || vOPlayer2.getState() == VOPlayer.PlayerState.RELEASED || this.mBitrate == (httpStreamingAlternateBitrate = this.mPlayer.getHttpStreamingAlternateBitrate() / 1000)) {
            return false;
        }
        this.mBitrate = httpStreamingAlternateBitrate;
        notifyLayerSwitch(httpStreamingAlternateBitrate);
        return false;
    }

    @Override // tv.broadpeak.smartlib.player.VOPlayerStateManager.IListener
    public void onInit(VOPlayer vOPlayer) {
    }

    @Override // tv.broadpeak.smartlib.player.VOPlayerStateManager.IListener
    public void onSeek(VOPlayer vOPlayer, long j, long j2) {
        LoggerManager.getInstance().printDebugLogs(TAG, "Player is seeked (" + j2 + ":" + j + ")");
        notifySeek(j, j2);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnSeekCompleteListener
    public void onSeekComplete(VOPlayer vOPlayer) {
        this.mWasSeeking = true;
    }

    @Override // tv.broadpeak.smartlib.player.VOPlayerStateManager.IListener
    public void onStateChange(VOPlayer vOPlayer, boolean z, boolean z2) {
    }

    @Override // tv.broadpeak.smartlib.player.VOPlayerStateManager.IListener
    public void onUpdatePlayerPosition(VOPlayer vOPlayer, long j) {
    }

    @Override // tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler
    public void releaseSessionPlayerObjects() {
        VOPlayerStateManager.getInstance().destroy();
    }
}
