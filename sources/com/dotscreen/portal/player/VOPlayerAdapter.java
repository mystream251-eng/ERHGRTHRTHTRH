package com.dotscreen.portal.player;

import android.content.Context;
import android.media.tv.TvTrackInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import androidx.leanback.media.PlaybackGlueHost;
import androidx.leanback.media.PlayerAdapter;
import com.dotscreen.farkit.analytics.AnalyticsManager;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.player.PlaybackFragment;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.BPKPlayerError;
import com.dotscreen.tv.common.utils.DSVOPlayerError;
import com.viaccessorca.voplayer.VOAudioTrack;
import com.viaccessorca.voplayer.VOPlayer;
import com.viaccessorca.voplayer.VOSubtitleTrack;
import io.sentry.cache.EnvelopeCache;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import tv.broadpeak.smartlib.session.streaming.StreamingSessionResult;

/* JADX INFO: compiled from: VOPlayerAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\u0001_BA\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\b\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u00020(H\u0016J\b\u00107\u001a\u00020(H\u0016J\u0016\u00108\u001a\u0004\u0018\u0001092\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010;\u001a\u00020(H\u0016J\u001a\u0010<\u001a\u0004\u0018\u00010\u000e2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>H\u0014J\b\u0010@\u001a\u00020\"H\u0016J\b\u0010A\u001a\u00020\"H\u0016J\u0012\u0010B\u001a\u0002052\b\u0010C\u001a\u0004\u0018\u00010DH\u0016J\u001a\u0010E\u001a\u0002052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010F\u001a\u00020>H\u0016J\u0012\u0010G\u001a\u0002052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010H\u001a\u000205H\u0016J\"\u0010I\u001a\u00020\"2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>H\u0016J,\u0010J\u001a\u00020\"2\b\u0010K\u001a\u0004\u0018\u00010\f2\u0006\u0010L\u001a\u00020>2\u0006\u0010?\u001a\u00020>2\b\u0010M\u001a\u0004\u0018\u00010NH\u0016J\u0012\u0010O\u001a\u0002052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010P\u001a\u0002052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010Q\u001a\u000205H\u0016J\b\u0010R\u001a\u000205H\u0016J\b\u0010S\u001a\u000205H\u0016J\u0010\u0010T\u001a\u0002052\u0006\u0010U\u001a\u00020(H\u0016J\u000e\u0010V\u001a\u0002052\u0006\u0010W\u001a\u000209J\u000e\u0010X\u001a\u0002052\u0006\u0010Y\u001a\u00020\"J\u0010\u0010Z\u001a\u0002052\u0006\u0010[\u001a\u00020\"H\u0016J\u0010\u0010\\\u001a\u0002052\b\u0010]\u001a\u0004\u0018\u00010^R\u0016\u0010\u0015\u001a\n \u0016*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010#\"\u0004\b$\u0010%R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0004\n\u0002\u0010)R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010+\"\u0004\b/\u0010-R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R$\u00101\u001a\u00020\"2\u0006\u00100\u001a\u00020\"@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010#\"\u0004\b3\u0010%¨\u0006`"}, d2 = {"Lcom/dotscreen/portal/player/VOPlayerAdapter;", "Landroidx/leanback/media/PlayerAdapter;", "Ljava/lang/Runnable;", "Lcom/viaccessorca/voplayer/VOPlayer$OnPreparedListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnBufferingUpdateListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnSeekCompleteListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnCompletionListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnInformationListener;", "Lcom/viaccessorca/voplayer/VOPlayer$OnErrorListener;", "context", "Landroid/content/Context;", "player", "Lcom/viaccessorca/voplayer/VOPlayer;", "selectedAudioTrackLanguage", "", "selectedSubtitleTrackLanguage", "playerInfoListener", "Lcom/dotscreen/portal/player/VOPlayerAdapter$PlayerInfoListener;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;", "(Landroid/content/Context;Lcom/viaccessorca/voplayer/VOPlayer;Ljava/lang/String;Ljava/lang/String;Lcom/dotscreen/portal/player/VOPlayerAdapter$PlayerInfoListener;Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;)V", "TAG", "kotlin.jvm.PlatformType", "analyticsManager", "Lcom/dotscreen/farkit/analytics/AnalyticsManager;", "fastForwardEnded", "Lcom/dotscreen/portal/player/VideoPlayerGlue$OnFastForwardEndedListener;", "getFastForwardEnded", "()Lcom/dotscreen/portal/player/VideoPlayerGlue$OnFastForwardEndedListener;", "setFastForwardEnded", "(Lcom/dotscreen/portal/player/VideoPlayerGlue$OnFastForwardEndedListener;)V", "handler", "Landroid/os/Handler;", "isFirstFrameDisplayed", "", "()Z", "setFirstFrameDisplayed", "(Z)V", "playerIsPrepared", "seekAfterPrepared", "", "Ljava/lang/Long;", "getSelectedAudioTrackLanguage", "()Ljava/lang/String;", "setSelectedAudioTrackLanguage", "(Ljava/lang/String;)V", "getSelectedSubtitleTrackLanguage", "setSelectedSubtitleTrackLanguage", "value", "subtitleVisibility", "getSubtitleVisibility", "setSubtitleVisibility", "getAllTracks", "", "getBufferedPosition", "getCurrentPosition", "getDefaultAudioTrack", "Lcom/viaccessorca/voplayer/VOAudioTrack;", "language", "getDuration", "getErrorMessage", "error", "", "extra", "isPlaying", "isPrepared", "onAttachedToHost", "host", "Landroidx/leanback/media/PlaybackGlueHost;", "onBufferingUpdate", "percent", "onCompletion", "onDetachedFromHost", "onError", "onInformation", "voPlayer", "what", "obj", "", "onPrepared", "onSeekComplete", ToolboxAnalyticsModuleKt.EVENT_PAUSE, "play", "run", "seekTo", "positionInMs", "setAudioTrack", "track", "setPlayerIsPrepared", "prepared", "setProgressUpdatingEnabled", "enable", "setSubtitleTrack", "voSubtitleTrack", "Lcom/viaccessorca/voplayer/VOSubtitleTrack;", "PlayerInfoListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class VOPlayerAdapter extends PlayerAdapter implements Runnable, VOPlayer.OnPreparedListener, VOPlayer.OnBufferingUpdateListener, VOPlayer.OnSeekCompleteListener, VOPlayer.OnCompletionListener, VOPlayer.OnInformationListener, VOPlayer.OnErrorListener {
    private final String TAG;
    private final AnalyticsManager analyticsManager;
    private final Context context;
    private VideoPlayerGlue.OnFastForwardEndedListener fastForwardEnded;
    private final Handler handler;
    private boolean isFirstFrameDisplayed;
    private final VOPlayer player;
    private final PlayerInfoListener playerInfoListener;
    private boolean playerIsPrepared;
    private Long seekAfterPrepared;
    private String selectedAudioTrackLanguage;
    private String selectedSubtitleTrackLanguage;
    private final PlaybackFragment session;
    private boolean subtitleVisibility;

    /* JADX INFO: compiled from: VOPlayerAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/portal/player/VOPlayerAdapter$PlayerInfoListener;", "", "onFirstFrameDisplayed", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface PlayerInfoListener {
        void onFirstFrameDisplayed();
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnSeekCompleteListener
    public void onSeekComplete(VOPlayer player) {
    }

    public /* synthetic */ VOPlayerAdapter(Context context, VOPlayer vOPlayer, String str, String str2, PlayerInfoListener playerInfoListener, PlaybackFragment playbackFragment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, vOPlayer, (i & 4) != 0 ? (String) null : str, (i & 8) != 0 ? (String) null : str2, (i & 16) != 0 ? (PlayerInfoListener) null : playerInfoListener, playbackFragment);
    }

    public final String getSelectedAudioTrackLanguage() {
        return this.selectedAudioTrackLanguage;
    }

    public final void setSelectedAudioTrackLanguage(String str) {
        this.selectedAudioTrackLanguage = str;
    }

    public final String getSelectedSubtitleTrackLanguage() {
        return this.selectedSubtitleTrackLanguage;
    }

    public final void setSelectedSubtitleTrackLanguage(String str) {
        this.selectedSubtitleTrackLanguage = str;
    }

    public VOPlayerAdapter(Context context, VOPlayer player, String str, String str2, PlayerInfoListener playerInfoListener, PlaybackFragment session) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(player, "player");
        Intrinsics.checkParameterIsNotNull(session, "session");
        this.context = context;
        this.player = player;
        this.selectedAudioTrackLanguage = str;
        this.selectedSubtitleTrackLanguage = str2;
        this.playerInfoListener = playerInfoListener;
        this.session = session;
        this.TAG = VOPlayerAdapter.class.getSimpleName();
        Context applicationContext = this.context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        this.analyticsManager = ((PortalApplication) applicationContext).getAnalyticsManager();
        this.handler = new Handler();
    }

    public final boolean getSubtitleVisibility() {
        return this.subtitleVisibility;
    }

    public final void setSubtitleVisibility(boolean z) {
        this.player.setSubtitleVisibility(z);
        this.subtitleVisibility = z;
    }

    /* JADX INFO: renamed from: isFirstFrameDisplayed, reason: from getter */
    public final boolean getIsFirstFrameDisplayed() {
        return this.isFirstFrameDisplayed;
    }

    public final void setFirstFrameDisplayed(boolean z) {
        this.isFirstFrameDisplayed = z;
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void pause() {
        if (this.playerIsPrepared) {
            this.player.pause();
            this.analyticsManager.onPause(this.player.getCurrentPosition());
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void play() {
        if (this.playerIsPrepared) {
            this.player.start();
            this.analyticsManager.onResume(this.player.getCurrentPosition());
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getBufferedPosition() {
        try {
            return this.player.getCurrentBufferLevel();
        } catch (Throwable unused) {
            return 0L;
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getCurrentPosition() {
        try {
            return this.player.getCurrentPosition();
        } catch (Throwable unused) {
            return 0L;
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getDuration() {
        try {
            return this.player.getDuration();
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public final VideoPlayerGlue.OnFastForwardEndedListener getFastForwardEnded() {
        return this.fastForwardEnded;
    }

    public final void setFastForwardEnded(VideoPlayerGlue.OnFastForwardEndedListener onFastForwardEndedListener) {
        this.fastForwardEnded = onFastForwardEndedListener;
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void seekTo(long positionInMs) {
        VideoPlayerGlue.OnFastForwardEndedListener onFastForwardEndedListener;
        if (this.playerIsPrepared) {
            if (positionInMs >= getDuration() && (onFastForwardEndedListener = this.fastForwardEnded) != null) {
                onFastForwardEndedListener.onFastForwardEnded();
            }
            this.player.seekTo((int) positionInMs, 1);
            return;
        }
        this.seekAfterPrepared = Long.valueOf(positionInMs);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void onAttachedToHost(PlaybackGlueHost host) {
        super.onAttachedToHost(host);
        this.player.setOnPreparedListener(this);
        this.player.setOnBufferingUpdateListener(this);
        this.player.setOnSeekCompleteListener(this);
        this.player.setOnCompletionListener(this);
        this.player.setOnInformationListener(this);
        this.player.setOnErrorListener(this);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void onDetachedFromHost() {
        super.onDetachedFromHost();
        this.player.removeOnPreparedListener(this);
        this.player.removeOnBufferingUpdateListener(this);
        VOPlayerAdapter vOPlayerAdapter = this;
        this.player.removeOnCompletionListener(vOPlayerAdapter);
        this.player.removeOnCompletionListener(vOPlayerAdapter);
        this.player.removeOnInformationListener(this);
        this.player.removeOnErrorListener(this);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnPreparedListener
    public void onPrepared(VOPlayer player) {
        VOSubtitleTrack[] subtitleTracks;
        VOSubtitleTrack track;
        VOAudioTrack[] audioTracks;
        VOSubtitleTrack[] subtitleTracks2;
        VOSubtitleTrack vOSubtitleTrack;
        VOSubtitleTrack[] subtitleTracks3;
        VOSubtitleTrack vOSubtitleTrack2;
        VOSubtitleTrack[] subtitleTracks4;
        VOSubtitleTrack[] subtitleTracks5;
        VOAudioTrack[] audioTracks2;
        VOAudioTrack track2;
        Log.d(this.TAG, "onPrepared selectedSubtitleTrackLanguage: " + this.selectedSubtitleTrackLanguage + ", selectedAudioTrackLanguage: " + this.selectedAudioTrackLanguage);
        this.playerIsPrepared = true;
        if (player != null) {
            player.changeDisplayMode(1);
        }
        Long l = this.seekAfterPrepared;
        String name = null;
        VOSubtitleTrack vOSubtitleTrack3 = null;
        name = null;
        name = null;
        if (l != null) {
            if (player != null) {
                if (l == null) {
                    Intrinsics.throwNpe();
                }
                player.startAt(l.longValue());
            }
            this.seekAfterPrepared = (Long) null;
        } else if (player != null) {
            player.start();
        }
        String str = this.selectedAudioTrackLanguage;
        if (str == null) {
            VOAudioTrack vOAudioTrack = (player == null || (audioTracks = player.getAudioTracks()) == null) ? null : audioTracks[0];
            if (vOAudioTrack != null && (!Intrinsics.areEqual(vOAudioTrack.getLanguage(), "es"))) {
                VOAudioTrack defaultAudioTrack$default = getDefaultAudioTrack$default(this, null, 1, null);
                if (defaultAudioTrack$default != null) {
                    player.setAudioTrack(defaultAudioTrack$default.getName());
                } else {
                    String str2 = this.selectedSubtitleTrackLanguage;
                    if ((str2 == null || Intrinsics.areEqual(str2, "")) && (subtitleTracks = player.getSubtitleTracks()) != null) {
                        int length = subtitleTracks.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                track = null;
                                break;
                            }
                            track = subtitleTracks[i];
                            String str3 = this.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("sub track language : ");
                            Intrinsics.checkExpressionValueIsNotNull(track, "track");
                            sb.append(track.getLanguage());
                            Log.d(str3, sb.toString());
                            if (StringsKt.equals(track.getLanguage(), "es", true)) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (track != null) {
                            this.selectedSubtitleTrackLanguage = track.getLanguage();
                            Log.d(this.TAG, "update selectedSubtitleTrackLanguage to " + this.selectedSubtitleTrackLanguage);
                        }
                    }
                }
            }
        } else if (player != null && (audioTracks2 = player.getAudioTracks()) != null) {
            int length2 = audioTracks2.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    track2 = null;
                    break;
                }
                track2 = audioTracks2[i2];
                Intrinsics.checkExpressionValueIsNotNull(track2, "track");
                if (StringsKt.equals(track2.getLanguage(), str, true)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (track2 != null) {
                player.setAudioTrack(track2.getName());
            }
        }
        String str4 = this.selectedSubtitleTrackLanguage;
        setSubtitleVisibility(str4 != null && (Intrinsics.areEqual(str4, "") ^ true));
        if (this.subtitleVisibility) {
            Log.d(this.TAG, "Subtitle visible");
            String str5 = this.selectedSubtitleTrackLanguage;
            if (str5 != null && player != null && (subtitleTracks5 = player.getSubtitleTracks()) != null) {
                for (VOSubtitleTrack track3 : subtitleTracks5) {
                    Intrinsics.checkExpressionValueIsNotNull(track3, "track");
                    if (StringsKt.equals(track3.getLanguage(), str5, true)) {
                        vOSubtitleTrack3 = track3;
                        break;
                    }
                }
                if (vOSubtitleTrack3 != null) {
                    player.setSubtitleTrack(vOSubtitleTrack3.getName(), vOSubtitleTrack3.getSubtitleType());
                }
            }
        } else if (((player == null || (subtitleTracks4 = player.getSubtitleTracks()) == null) ? 0 : subtitleTracks4.length) > 0) {
            String str6 = this.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("First track ");
            sb2.append((player == null || (subtitleTracks3 = player.getSubtitleTracks()) == null || (vOSubtitleTrack2 = subtitleTracks3[0]) == null) ? null : vOSubtitleTrack2.getLanguage());
            sb2.append(" : ");
            if (player != null && (subtitleTracks2 = player.getSubtitleTracks()) != null && (vOSubtitleTrack = subtitleTracks2[0]) != null) {
                name = vOSubtitleTrack.getName();
            }
            sb2.append(name);
            Log.d(str6, sb2.toString());
            if (player != null) {
                VOSubtitleTrack vOSubtitleTrack4 = player.getSubtitleTracks()[0];
                Intrinsics.checkExpressionValueIsNotNull(vOSubtitleTrack4, "player.subtitleTracks[0]");
                String name2 = vOSubtitleTrack4.getName();
                VOSubtitleTrack vOSubtitleTrack5 = player.getSubtitleTracks()[0];
                Intrinsics.checkExpressionValueIsNotNull(vOSubtitleTrack5, "player.subtitleTracks[0]");
                player.setSubtitleTrack(name2, vOSubtitleTrack5.getSubtitleType());
            }
        }
        getCallback().onPreparedStateChanged(this);
    }

    static /* synthetic */ VOAudioTrack getDefaultAudioTrack$default(VOPlayerAdapter vOPlayerAdapter, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getDefaultAudioTrack");
        }
        if ((i & 1) != 0) {
            str = "es";
        }
        return vOPlayerAdapter.getDefaultAudioTrack(str);
    }

    private final VOAudioTrack getDefaultAudioTrack(String language) {
        VOAudioTrack[] audioTracks = this.player.getAudioTracks();
        Intrinsics.checkExpressionValueIsNotNull(audioTracks, "player.audioTracks");
        for (VOAudioTrack it : audioTracks) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            if (Intrinsics.areEqual(it.getLanguage(), language)) {
                return it;
            }
        }
        return null;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(VOPlayer player, int percent) {
        VOPlayerAdapter vOPlayerAdapter = this;
        getCallback().onPlayStateChanged(vOPlayerAdapter);
        getCallback().onBufferingStateChanged(vOPlayerAdapter, percent < 100);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnInformationListener
    public boolean onInformation(VOPlayer voPlayer, int what, int extra, Object obj) {
        BPKPlayerError bPKPlayerError;
        if (what != 1000) {
            if (what != 3000) {
                if (what != 8000) {
                    if (what != 11000) {
                        if (what == 11003) {
                            this.session.canSkipAds(extra);
                        } else if (what == 25000) {
                            if (25001 == extra) {
                                if (obj != null) {
                                    StreamingSessionResult streamingSessionResult = (StreamingSessionResult) obj;
                                    Context context = this.context;
                                    if (context != null && (bPKPlayerError = BPKPlayerError.INSTANCE.getBPKPlayerError(extra)) != null) {
                                        Toast.makeText(context.getApplicationContext(), bPKPlayerError.getResId(), 1).show();
                                        Log.e(this.TAG, '[' + bPKPlayerError.getOwner() + "]ERROR NAME  : " + bPKPlayerError.name() + ", USER MESSAGE : " + bPKPlayerError.getResId());
                                    }
                                    Log.e(this.TAG, "Get URL return an error with code:" + streamingSessionResult.getErrorCode() + " and message:" + streamingSessionResult.getErrorMessage());
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type tv.broadpeak.smartlib.session.streaming.StreamingSessionResult");
                                }
                            } else if (25002 == extra) {
                                Log.i(this.TAG, "Smartlib get URL sucess");
                            }
                        }
                    } else {
                        if (extra == 11001) {
                            Log.d(this.TAG, "MEDIA_INFO_VAST_ADVERTISEMENT");
                            getCallback().onPlayStateChanged(this);
                            this.session.adsStarted();
                            return true;
                        }
                        if (extra == 11002) {
                            Log.d(this.TAG, "MEDIA_INFO_VAST_NOT_ADVERTISEMENT");
                            getCallback().onPlayStateChanged(this);
                            this.session.adsFinished();
                            return true;
                        }
                    }
                } else if (extra == 8003) {
                    Log.d(this.TAG, "onInfo: MEDIA_INFO_FIRST_FRAME_DISPLAYED");
                    this.isFirstFrameDisplayed = true;
                    PlayerInfoListener playerInfoListener = this.playerInfoListener;
                    if (playerInfoListener != null) {
                        playerInfoListener.onFirstFrameDisplayed();
                    }
                    return true;
                }
            } else if (extra == 3009) {
                Log.d(this.TAG, "onInfo: MEDIA_INFO_DRM_SESSION_WILL_EXPIRE");
                this.session.restartPlayer();
            }
        } else if (extra == 1013) {
            Log.d(this.TAG, "onInfo: MEDIA_INFO_SEEKABLE_RANGE_CHANGED");
            getCallback().onDurationChanged(this);
            return true;
        }
        return true;
    }

    @Override // java.lang.Runnable
    public void run() {
        VOPlayerAdapter vOPlayerAdapter = this;
        getCallback().onCurrentPositionChanged(vOPlayerAdapter);
        getCallback().onBufferedPositionChanged(vOPlayerAdapter);
        this.handler.postDelayed(this, 500L);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnCompletionListener
    public void onCompletion(VOPlayer player) {
        getCallback().onPlayCompleted(this);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void setProgressUpdatingEnabled(boolean enable) {
        VOPlayerAdapter vOPlayerAdapter = this;
        this.handler.removeCallbacks(vOPlayerAdapter);
        if (enable) {
            this.handler.post(vOPlayerAdapter);
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public boolean isPlaying() {
        try {
            return this.player.isPlaying() && this.isFirstFrameDisplayed;
        } catch (IllegalStateException e) {
            Log.e(this.TAG, "isPlaying() => " + e);
            return false;
        }
    }

    @Override // androidx.leanback.media.PlayerAdapter
    /* JADX INFO: renamed from: isPrepared, reason: from getter */
    public boolean getPlayerIsPrepared() {
        return this.playerIsPrepared;
    }

    public final void setPlayerIsPrepared(boolean prepared) {
        this.playerIsPrepared = prepared;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnErrorListener
    public boolean onError(VOPlayer player, int error, int extra) {
        Log.e(this.TAG, "onError(" + error + ", " + extra + ')');
        if (VOPlayerAdapterKt.RECOVERABLE_ERROR_CODES.contains(new Pair(Integer.valueOf(error), Integer.valueOf(extra)))) {
            Log.v(this.TAG, " -> Error recovered");
            this.session.restartPlayer();
        } else {
            Log.v(this.TAG, " -> Unrecoverable error");
        }
        getCallback().onError(this, error, getErrorMessage(error, extra));
        return true;
    }

    protected String getErrorMessage(int error, int extra) {
        DSVOPlayerError playerError = DSVOPlayerError.INSTANCE.getPlayerError(extra);
        if (playerError != null) {
            Log.e(this.TAG, "VOPlayer error(owner): " + playerError.getOwner());
            return this.context.getString(playerError.getResId());
        }
        return "Error: 0x" + Integer.toHexString(error) + ", 0x" + Integer.toHexString(extra);
    }

    private final void getAllTracks() {
        ArrayList arrayList = new ArrayList();
        String id = (String) null;
        VOAudioTrack[] audioTracks = this.player.getAudioTracks();
        Intrinsics.checkExpressionValueIsNotNull(audioTracks, "player.audioTracks");
        int length = audioTracks.length;
        int i = 0;
        String id2 = id;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            VOAudioTrack track = audioTracks[i2];
            int i4 = i3 + 1;
            TvTrackInfo.Builder builder = new TvTrackInfo.Builder(0, "AUDIO-" + i3);
            Intrinsics.checkExpressionValueIsNotNull(track, "track");
            builder.setDescription(track.getName());
            builder.setLanguage(track.getLanguage());
            TvTrackInfo trackInfo = builder.build();
            arrayList.add(trackInfo);
            if (track.IsSelected()) {
                Intrinsics.checkExpressionValueIsNotNull(trackInfo, "trackInfo");
                id2 = trackInfo.getId();
            }
            i2++;
            i3 = i4;
        }
        VOSubtitleTrack[] subtitleTracks = this.player.getSubtitleTracks();
        Intrinsics.checkExpressionValueIsNotNull(subtitleTracks, "player.subtitleTracks");
        int length2 = subtitleTracks.length;
        int i5 = 0;
        while (i < length2) {
            VOSubtitleTrack track2 = subtitleTracks[i];
            int i6 = i5 + 1;
            TvTrackInfo.Builder builder2 = new TvTrackInfo.Builder(2, "SUBTITLE-" + i5);
            Intrinsics.checkExpressionValueIsNotNull(track2, "track");
            builder2.setDescription(track2.getName());
            builder2.setLanguage(track2.getLanguage());
            TvTrackInfo trackInfo2 = builder2.build();
            arrayList.add(trackInfo2);
            if (track2.IsSelected()) {
                Intrinsics.checkExpressionValueIsNotNull(trackInfo2, "trackInfo");
                id = trackInfo2.getId();
            }
            i++;
            i5 = i6;
        }
        Log.d(this.TAG, ">>> tracks = " + arrayList);
        Log.d(this.TAG, ">>> selectedAudio = " + id2);
        Log.d(this.TAG, ">>> selectedSubtitle = " + id);
    }

    public final void setAudioTrack(VOAudioTrack track) {
        VOSubtitleTrack[] subtitleTracks;
        Intrinsics.checkParameterIsNotNull(track, "track");
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setAudioTrack => ");
        sb.append(track.getLanguage());
        sb.append(", number of available subtitles : ");
        VOSubtitleTrack[] subtitleTracks2 = this.player.getSubtitleTracks();
        VOSubtitleTrack vOSubtitleTrack = null;
        sb.append(subtitleTracks2 != null ? Integer.valueOf(subtitleTracks2.length) : null);
        Log.d(str, sb.toString());
        this.player.setAudioTrack(track.getName());
        this.selectedAudioTrackLanguage = track.getLanguage();
        String language = track.getLanguage();
        if (language == null) {
            return;
        }
        int iHashCode = language.hashCode();
        if (iHashCode == 3241) {
            if (!language.equals("en") || (subtitleTracks = this.player.getSubtitleTracks()) == null) {
                return;
            }
            for (VOSubtitleTrack voSubtitleTrack : subtitleTracks) {
                String str2 = this.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("setAudioTrack ===> subtitleTrack name: ");
                Intrinsics.checkExpressionValueIsNotNull(voSubtitleTrack, "voSubtitleTrack");
                sb2.append(voSubtitleTrack.getLanguage());
                Log.d(str2, sb2.toString());
                if (StringsKt.equals(voSubtitleTrack.getLanguage(), "es", true)) {
                    vOSubtitleTrack = voSubtitleTrack;
                    break;
                }
            }
            if (vOSubtitleTrack != null) {
                Log.d(this.TAG, "setAudioTrack ====> will set subtitle " + vOSubtitleTrack.getName());
                setSubtitleTrack(vOSubtitleTrack);
                return;
            }
            return;
        }
        if (iHashCode == 3246 && language.equals("es")) {
            setSubtitleTrack(null);
        }
    }

    public final void setSubtitleTrack(VOSubtitleTrack voSubtitleTrack) {
        boolean z;
        if (voSubtitleTrack != null) {
            this.player.setSubtitleTrack(voSubtitleTrack.getName(), voSubtitleTrack.getSubtitleType());
            this.selectedSubtitleTrackLanguage = voSubtitleTrack.getLanguage();
            z = true;
        } else {
            this.selectedSubtitleTrackLanguage = (String) null;
            z = false;
        }
        setSubtitleVisibility(z);
    }
}
