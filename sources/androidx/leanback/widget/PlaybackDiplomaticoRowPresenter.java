package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.work.WorkRequest;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackDiplomaticoRowPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0010\b\u0017\u0018\u0000 G2\u00020\u0001:\u0002GHB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0014J\u001c\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\n\u0010)\u001a\u00060*j\u0002`+H\u0002J\u0010\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020.H\u0002J\u0006\u0010/\u001a\u00020\u0004J\b\u00100\u001a\u0004\u0018\u00010\nJ\b\u00101\u001a\u00020\u0010H\u0007J\u0010\u00102\u001a\u00020\u00102\u0006\u0010-\u001a\u00020.H\u0002J\u0014\u00103\u001a\u00020&2\n\u00104\u001a\u000605R\u00020\u0000H\u0002J\u0018\u00106\u001a\u00020&2\u0006\u00107\u001a\u00020\"2\u0006\u00108\u001a\u000209H\u0014J\u0016\u0010:\u001a\u00020&2\f\u00104\u001a\b\u0018\u000105R\u00020\u0000H\u0004J\u0010\u0010;\u001a\u00020&2\u0006\u0010<\u001a\u00020\"H\u0016J\u0018\u0010=\u001a\u00020&2\u0006\u00104\u001a\u00020\"2\u0006\u0010>\u001a\u00020\u0016H\u0014J\u0010\u0010?\u001a\u00020&2\u0006\u00107\u001a\u00020\"H\u0014J\u000e\u0010@\u001a\u00020&2\u0006\u0010A\u001a\u00020\u0004J\u0010\u0010B\u001a\u00020&2\b\u0010C\u001a\u0004\u0018\u00010\nJ\u0010\u0010D\u001a\u00020&2\b\b\u0001\u0010E\u001a\u00020\u0010J\u0010\u0010F\u001a\u00020&2\b\b\u0001\u0010E\u001a\u00020\u0010R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u001a\u0010\u001e\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001a¨\u0006I"}, d2 = {"Landroidx/leanback/widget/PlaybackDiplomaticoRowPresenter;", "Landroidx/leanback/widget/PlaybackRowPresenter;", "()V", "mDefaultSeekIncrement", "", "getMDefaultSeekIncrement", "()F", "setMDefaultSeekIncrement", "(F)V", "mOnActionClickedListener", "Landroidx/leanback/widget/OnActionClickedListener;", "getMOnActionClickedListener", "()Landroidx/leanback/widget/OnActionClickedListener;", "setMOnActionClickedListener", "(Landroidx/leanback/widget/OnActionClickedListener;)V", "mProgressColor", "", "getMProgressColor", "()I", "setMProgressColor", "(I)V", "mProgressColorSet", "", "getMProgressColorSet", "()Z", "setMProgressColorSet", "(Z)V", "mProgressSecondaryColor", "getMProgressSecondaryColor", "setMProgressSecondaryColor", "mProgressSecondaryColorSet", "getMProgressSecondaryColorSet", "setMProgressSecondaryColorSet", "createRowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "formatTime", "", "aMs", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getDefaultProgressColor", "context", "Landroid/content/Context;", "getDefaultSeekIncrement", "getOnActionClickedListener", "getProgressColor", "getSecondaryProgressColor", "initRow", "vh", "Landroidx/leanback/widget/PlaybackDiplomaticoRowPresenter$ViewHolder;", "onBindRowViewHolder", "holder", "item", "", "onProgressBarClicked", "onReappear", "rowViewHolder", "onRowViewSelected", "selected", "onUnbindRowViewHolder", "setDefaultSeekIncrement", "ratio", "setOnActionClickedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setProgressColor", TtmlNode.ATTR_TTS_COLOR, "setSecondaryProgressColor", "Companion", "ViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class PlaybackDiplomaticoRowPresenter extends PlaybackRowPresenter {
    public static final int PLAYBACK_ICON_FAST_FORWARD = 6;
    public static final int PLAYBACK_ICON_FAST_REWIND = 3;
    public static final int PLAYBACK_ICON_FORWARD_1X = 8;
    public static final int PLAYBACK_ICON_FORWARD_2X = 7;
    public static final int PLAYBACK_ICON_PAUSE = 2;
    public static final int PLAYBACK_ICON_PLAY = 1;
    public static final int PLAYBACK_ICON_REWIND_1X = 5;
    public static final int PLAYBACK_ICON_REWIND_2X = 4;
    private float mDefaultSeekIncrement;
    private OnActionClickedListener mOnActionClickedListener;
    private int mProgressColor;
    private boolean mProgressColorSet;
    private int mProgressSecondaryColor;
    private boolean mProgressSecondaryColorSet;

    public PlaybackDiplomaticoRowPresenter() {
        setHeaderPresenter((RowHeaderPresenter) null);
        setSelectEffectEnabled(false);
        this.mDefaultSeekIncrement = 0.01f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void formatTime(long aMs, StringBuilder sb) {
        sb.setLength(0);
        if (aMs < 0) {
            sb.append("-");
            aMs = -aMs;
        }
        long j = aMs / ((long) 1000);
        long j2 = 60;
        long j3 = j / j2;
        long j4 = j3 / j2;
        long j5 = j - (j3 * j2);
        long j6 = j3 - (j2 * j4);
        if (j4 > 0) {
            sb.append(j4);
            sb.append(':');
            if (j6 < 10) {
                sb.append('0');
            }
        }
        sb.append(j6);
        sb.append(':');
        if (j5 < 10) {
            sb.append('0');
        }
        sb.append(j5);
    }

    /* JADX INFO: compiled from: PlaybackDiplomaticoRowPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010w\u001a\u00020xH\u0000¢\u0006\u0002\byJ\u0010\u0010z\u001a\u00020x2\u0006\u0010{\u001a\u00020kH\u0002J\u0017\u0010|\u001a\u0004\u0018\u00010}2\u0006\u0010~\u001a\u00020 H\u0000¢\u0006\u0002\b\u007fJ\u000f\u0010\u0080\u0001\u001a\u00020 H\u0000¢\u0006\u0003\b\u0081\u0001J\u000f\u0010\u0082\u0001\u001a\u00020 H\u0000¢\u0006\u0003\b\u0083\u0001J\u0012\u0010\u0084\u0001\u001a\u00020x2\u0007\u0010\u0085\u0001\u001a\u00020\u0007H\u0004J\u0012\u0010\u0086\u0001\u001a\u00020x2\u0007\u0010\u0087\u0001\u001a\u00020\u0007H\u0004J\u0007\u0010\u0088\u0001\u001a\u00020xJ\u0018\u0010\u0089\u0001\u001a\u00020x2\u0007\u0010\u008a\u0001\u001a\u00020\u0007H\u0000¢\u0006\u0003\b\u008b\u0001J\u0018\u0010\u008c\u0001\u001a\u00020x2\u0007\u0010\u0085\u0001\u001a\u00020\u0007H\u0000¢\u0006\u0003\b\u008d\u0001J\u0010\u0010\u008e\u0001\u001a\u00020x2\u0007\u0010\u008f\u0001\u001a\u00020\u0007J\u0012\u0010\u0090\u0001\u001a\u00020x2\u0007\u0010\u0091\u0001\u001a\u00020LH\u0016J\u0018\u0010\u0092\u0001\u001a\u00020x2\u0007\u0010\u008f\u0001\u001a\u00020\u0007H\u0000¢\u0006\u0003\b\u0093\u0001J\u000f\u0010\u0094\u0001\u001a\u00020 H\u0000¢\u0006\u0003\b\u0095\u0001J\u0018\u0010\u0096\u0001\u001a\u00020x2\u0007\u0010\u0097\u0001\u001a\u00020 H\u0000¢\u0006\u0003\b\u0098\u0001J\u001b\u0010\u0099\u0001\u001a\u00020x2\u0007\u0010\u009a\u0001\u001a\u00020 2\u0007\u0010\u009b\u0001\u001a\u00020 H\u0002R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u0015R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$R\u001a\u0010(\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\"\"\u0004\b*\u0010$R\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010/\u001a\u000200¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u001c\u00103\u001a\u0004\u0018\u000104X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0011\u00109\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001eR\u0011\u0010;\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0011\u0010>\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0011R\u0011\u0010@\u001a\u00020A¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0011\u0010D\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010=R\u0011\u0010F\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0011R\u001a\u0010H\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\t\"\u0004\bJ\u0010\u0015R\u001c\u0010K\u001a\u0004\u0018\u00010LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001c\u0010Q\u001a\u0004\u0018\u00010RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001c\u0010W\u001a\u0004\u0018\u00010XX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001c\u0010]\u001a\u0004\u0018\u00010^X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u0011\u0010c\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0011R\u0015\u0010e\u001a\u00060fj\u0002`g¢\u0006\b\n\u0000\u001a\u0004\bh\u0010iR\u001a\u0010j\u001a\u00020kX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\u0011\u0010p\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bq\u0010\u0011R\u001a\u0010r\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\t\"\u0004\bt\u0010\u0015R\u000e\u0010u\u001a\u00020kX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u009c\u0001"}, d2 = {"Landroidx/leanback/widget/PlaybackDiplomaticoRowPresenter$ViewHolder;", "Landroidx/leanback/widget/PlaybackRowPresenter$ViewHolder;", "Landroidx/leanback/widget/PlaybackSeekUi;", "rootView", "Landroid/view/View;", "(Landroidx/leanback/widget/PlaybackDiplomaticoRowPresenter;Landroid/view/View;)V", "endingTimeMs", "", "getEndingTimeMs", "()J", "handler", "Landroid/os/Handler;", "hideRunnable", "Ljava/lang/Runnable;", "mBodyTextView", "Landroid/widget/TextView;", "getMBodyTextView", "()Landroid/widget/TextView;", "mCurrentTimeInMs", "getMCurrentTimeInMs", "setMCurrentTimeInMs", "(J)V", "mCurrentTimeTextView", "getMCurrentTimeTextView", "mForcedTotalTimeInMs", "getMForcedTotalTimeInMs", "setMForcedTotalTimeInMs", "mImageView", "Landroid/widget/ImageView;", "getMImageView", "()Landroid/widget/ImageView;", "mInFastSeek", "", "getMInFastSeek", "()Z", "setMInFastSeek", "(Z)V", "mInSeek", "getMInSeek", "setMInSeek", "mKeyIsPressed", "getMKeyIsPressed", "setMKeyIsPressed", "mListener", "Landroidx/leanback/widget/PlaybackControlsRow$OnPlaybackProgressCallback;", "getMListener", "()Landroidx/leanback/widget/PlaybackControlsRow$OnPlaybackProgressCallback;", "mListener2", "Landroidx/leanback/widget/PlaybackDiplomaticoControlsRow$OnPlaybackStateCallback;", "getMListener2", "()Landroidx/leanback/widget/PlaybackDiplomaticoControlsRow$OnPlaybackStateCallback;", "mPlayPauseAction", "Landroidx/leanback/widget/PlaybackControlsRow$PlayPauseAction;", "getMPlayPauseAction", "()Landroidx/leanback/widget/PlaybackControlsRow$PlayPauseAction;", "setMPlayPauseAction", "(Landroidx/leanback/widget/PlaybackControlsRow$PlayPauseAction;)V", "mPlaybackIcon", "getMPlaybackIcon", "mPlaybackIconBg", "getMPlaybackIconBg", "()Landroid/view/View;", "mPlaybackIconText", "getMPlaybackIconText", "mProgressBar", "Landroidx/leanback/widget/SeekBar;", "getMProgressBar", "()Landroidx/leanback/widget/SeekBar;", "mProgressGuideView", "getMProgressGuideView", "mRemainingTimeTextView", "getMRemainingTimeTextView", "mSecondaryProgressInMs", "getMSecondaryProgressInMs", "setMSecondaryProgressInMs", "mSeekClient", "Landroidx/leanback/widget/PlaybackSeekUi$Client;", "getMSeekClient", "()Landroidx/leanback/widget/PlaybackSeekUi$Client;", "setMSeekClient", "(Landroidx/leanback/widget/PlaybackSeekUi$Client;)V", "mSeekDataProvider", "Landroidx/leanback/widget/PlaybackSeekDataProvider;", "getMSeekDataProvider", "()Landroidx/leanback/widget/PlaybackSeekDataProvider;", "setMSeekDataProvider", "(Landroidx/leanback/widget/PlaybackSeekDataProvider;)V", "mSelectedItem", "", "getMSelectedItem", "()Ljava/lang/Object;", "setMSelectedItem", "(Ljava/lang/Object;)V", "mSelectedViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "getMSelectedViewHolder", "()Landroidx/leanback/widget/Presenter$ViewHolder;", "setMSelectedViewHolder", "(Landroidx/leanback/widget/Presenter$ViewHolder;)V", "mSubTitleTextView", "getMSubTitleTextView", "mTempBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getMTempBuilder", "()Ljava/lang/StringBuilder;", "mThumbHeroIndex", "", "getMThumbHeroIndex", "()I", "setMThumbHeroIndex", "(I)V", "mTitleTextView", "getMTitleTextView", "mTotalTimeInMs", "getMTotalTimeInMs", "setMTotalTimeInMs", "previousPlaybackIcon", "stopSeekRunnable", "dispatchItemSelection", "", "dispatchItemSelection$MegaCable_Portal_0_75_1_396__prodRelease", "displayPlaybackIcon", "icon", "getPresenter", "Landroidx/leanback/widget/Presenter;", "primary", "getPresenter$MegaCable_Portal_0_75_1_396__prodRelease", "onBackward", "onBackward$MegaCable_Portal_0_75_1_396__prodRelease", "onForward", "onForward$MegaCable_Portal_0_75_1_396__prodRelease", "onSetCurrentPositionLabel", "currentTimeMs", "onSetRemainingLabel", "remainingTimeMs", "reset", "setBufferedPosition", "progressMs", "setBufferedPosition$MegaCable_Portal_0_75_1_396__prodRelease", "setCurrentPosition", "setCurrentPosition$MegaCable_Portal_0_75_1_396__prodRelease", "setForcedTotalTime", "totalTimeMs", "setPlaybackSeekUiClient", "client", "setTotalTime", "setTotalTime$MegaCable_Portal_0_75_1_396__prodRelease", "startSeek", "startSeek$MegaCable_Portal_0_75_1_396__prodRelease", "stopSeek", "cancelled", "stopSeek$MegaCable_Portal_0_75_1_396__prodRelease", "updateProgressInSeek", "forward", "inSeek", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends PlaybackRowPresenter.ViewHolder implements PlaybackSeekUi {
        private final Handler handler;
        private final Runnable hideRunnable;
        private final TextView mBodyTextView;
        private long mCurrentTimeInMs;
        private final TextView mCurrentTimeTextView;
        private long mForcedTotalTimeInMs;
        private final ImageView mImageView;
        private boolean mInFastSeek;
        private boolean mInSeek;
        private boolean mKeyIsPressed;
        private final PlaybackControlsRow.OnPlaybackProgressCallback mListener;
        private final PlaybackDiplomaticoControlsRow.OnPlaybackStateCallback mListener2;
        private PlaybackControlsRow.PlayPauseAction mPlayPauseAction;
        private final ImageView mPlaybackIcon;
        private final View mPlaybackIconBg;
        private final TextView mPlaybackIconText;
        private final SeekBar mProgressBar;
        private final View mProgressGuideView;
        private final TextView mRemainingTimeTextView;
        private long mSecondaryProgressInMs;
        private PlaybackSeekUi.Client mSeekClient;
        private PlaybackSeekDataProvider mSeekDataProvider;
        private Object mSelectedItem;
        private Presenter.ViewHolder mSelectedViewHolder;
        private final TextView mSubTitleTextView;
        private final StringBuilder mTempBuilder;
        private int mThumbHeroIndex;
        private final TextView mTitleTextView;
        private long mTotalTimeInMs;
        private int previousPlaybackIcon;
        private final Runnable stopSeekRunnable;
        final /* synthetic */ PlaybackDiplomaticoRowPresenter this$0;

        public final void setBufferedPosition$MegaCable_Portal_0_75_1_396__prodRelease(long progressMs) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(PlaybackDiplomaticoRowPresenter playbackDiplomaticoRowPresenter, View rootView) {
            super(rootView);
            Intrinsics.checkParameterIsNotNull(rootView, "rootView");
            this.this$0 = playbackDiplomaticoRowPresenter;
            this.mTotalTimeInMs = Long.MIN_VALUE;
            this.mForcedTotalTimeInMs = Long.MIN_VALUE;
            this.mCurrentTimeInMs = Long.MIN_VALUE;
            this.mTempBuilder = new StringBuilder();
            this.mThumbHeroIndex = -1;
            this.mListener = new PlaybackControlsRow.OnPlaybackProgressCallback() { // from class: androidx.leanback.widget.PlaybackDiplomaticoRowPresenter$ViewHolder$mListener$1
                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onCurrentPositionChanged(PlaybackControlsRow row, long ms) {
                    this.this$0.setCurrentPosition$MegaCable_Portal_0_75_1_396__prodRelease(ms);
                }

                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onDurationChanged(PlaybackControlsRow row, long ms) {
                    this.this$0.setTotalTime$MegaCable_Portal_0_75_1_396__prodRelease(ms);
                }

                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onBufferedPositionChanged(PlaybackControlsRow row, long ms) {
                    this.this$0.setBufferedPosition$MegaCable_Portal_0_75_1_396__prodRelease(ms);
                }
            };
            this.mListener2 = new PlaybackDiplomaticoControlsRow.OnPlaybackStateCallback() { // from class: androidx.leanback.widget.PlaybackDiplomaticoRowPresenter$ViewHolder$mListener2$1
                @Override // androidx.leanback.widget.PlaybackDiplomaticoControlsRow.OnPlaybackStateCallback
                public void onPlay(PlaybackDiplomaticoControlsRow row) {
                    this.this$0.displayPlaybackIcon(1);
                }

                @Override // androidx.leanback.widget.PlaybackDiplomaticoControlsRow.OnPlaybackStateCallback
                public void onPause(PlaybackDiplomaticoControlsRow row) {
                    this.this$0.displayPlaybackIcon(2);
                }
            };
            this.handler = new Handler();
            this.stopSeekRunnable = new Runnable() { // from class: androidx.leanback.widget.PlaybackDiplomaticoRowPresenter$ViewHolder$stopSeekRunnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (this.this$0.getMInSeek()) {
                        this.this$0.stopSeek$MegaCable_Portal_0_75_1_396__prodRelease(false);
                    }
                }
            };
            View viewFindViewById = rootView.findViewById(R.id.image);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "rootView.findViewById(R.id.image)");
            this.mImageView = (ImageView) viewFindViewById;
            View viewFindViewById2 = rootView.findViewById(R.id.playback_progress);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "rootView.findViewById(R.id.playback_progress)");
            this.mProgressBar = (SeekBar) viewFindViewById2;
            View viewFindViewById3 = rootView.findViewById(R.id.playback_icon_background);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "rootView.findViewById(R.…playback_icon_background)");
            this.mPlaybackIconBg = viewFindViewById3;
            View viewFindViewById4 = rootView.findViewById(R.id.playback_icon);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "rootView.findViewById(R.id.playback_icon)");
            this.mPlaybackIcon = (ImageView) viewFindViewById4;
            View viewFindViewById5 = rootView.findViewById(R.id.playback_icon_text);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "rootView.findViewById(R.id.playback_icon_text)");
            this.mPlaybackIconText = (TextView) viewFindViewById5;
            View viewFindViewById6 = rootView.findViewById(R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "rootView.findViewById(R.id.title)");
            this.mTitleTextView = (TextView) viewFindViewById6;
            View viewFindViewById7 = rootView.findViewById(R.id.subtitle);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "rootView.findViewById(R.id.subtitle)");
            this.mSubTitleTextView = (TextView) viewFindViewById7;
            View viewFindViewById8 = rootView.findViewById(R.id.body);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "rootView.findViewById(R.id.body)");
            this.mBodyTextView = (TextView) viewFindViewById8;
            View viewFindViewById9 = rootView.findViewById(R.id.remaining_time);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "rootView.findViewById(R.id.remaining_time)");
            this.mRemainingTimeTextView = (TextView) viewFindViewById9;
            View viewFindViewById10 = rootView.findViewById(R.id.current_time);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById10, "rootView.findViewById(R.id.current_time)");
            this.mCurrentTimeTextView = (TextView) viewFindViewById10;
            View viewFindViewById11 = rootView.findViewById(R.id.progress_guide);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById11, "rootView.findViewById(R.id.progress_guide)");
            this.mProgressGuideView = viewFindViewById11;
            this.mProgressBar.setOnClickListener(new View.OnClickListener() { // from class: androidx.leanback.widget.PlaybackDiplomaticoRowPresenter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewHolder.this.this$0.onProgressBarClicked(ViewHolder.this);
                }
            });
            this.mProgressBar.setOnKeyListener(new View.OnKeyListener() { // from class: androidx.leanback.widget.PlaybackDiplomaticoRowPresenter.ViewHolder.2
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (i != 69) {
                        if (i != 81) {
                            if (i != 89) {
                                if (i != 90) {
                                    switch (i) {
                                        case 19:
                                        case 20:
                                            return ViewHolder.this.getMInSeek();
                                        case 21:
                                            break;
                                        case 22:
                                            break;
                                        default:
                                            return false;
                                    }
                                }
                            }
                        }
                        Intrinsics.checkExpressionValueIsNotNull(keyEvent, "keyEvent");
                        int action = keyEvent.getAction();
                        if (action == 0) {
                            if (ViewHolder.this.getMKeyIsPressed()) {
                                ViewHolder.this.setMInFastSeek(true);
                                ViewHolder.this.onForward$MegaCable_Portal_0_75_1_396__prodRelease();
                            }
                            ViewHolder.this.setMKeyIsPressed(true);
                        } else if (action == 1) {
                            ViewHolder.this.setMKeyIsPressed(false);
                            if (!ViewHolder.this.getMInFastSeek()) {
                                ViewHolder.this.onForward$MegaCable_Portal_0_75_1_396__prodRelease();
                            }
                            ViewHolder.this.setMInFastSeek(false);
                            ViewHolder.this.handler.removeCallbacks(ViewHolder.this.stopSeekRunnable);
                            ViewHolder.this.handler.postDelayed(ViewHolder.this.stopSeekRunnable, 500L);
                        }
                        return true;
                    }
                    Intrinsics.checkExpressionValueIsNotNull(keyEvent, "keyEvent");
                    int action2 = keyEvent.getAction();
                    if (action2 == 0) {
                        if (ViewHolder.this.getMKeyIsPressed()) {
                            ViewHolder.this.setMInFastSeek(true);
                            ViewHolder.this.onBackward$MegaCable_Portal_0_75_1_396__prodRelease();
                        }
                        ViewHolder.this.setMKeyIsPressed(true);
                    } else if (action2 == 1) {
                        ViewHolder.this.setMKeyIsPressed(false);
                        if (!ViewHolder.this.getMInFastSeek()) {
                            ViewHolder.this.onBackward$MegaCable_Portal_0_75_1_396__prodRelease();
                        }
                        ViewHolder.this.setMInFastSeek(false);
                        ViewHolder.this.handler.removeCallbacks(ViewHolder.this.stopSeekRunnable);
                        ViewHolder.this.handler.postDelayed(ViewHolder.this.stopSeekRunnable, 500L);
                    }
                    return true;
                }
            });
            this.mProgressBar.setAccessibilitySeekListener(new SeekBar.AccessibilitySeekListener() { // from class: androidx.leanback.widget.PlaybackDiplomaticoRowPresenter.ViewHolder.3
                @Override // androidx.leanback.widget.SeekBar.AccessibilitySeekListener
                public boolean onAccessibilitySeekForward() {
                    return ViewHolder.this.onForward$MegaCable_Portal_0_75_1_396__prodRelease();
                }

                @Override // androidx.leanback.widget.SeekBar.AccessibilitySeekListener
                public boolean onAccessibilitySeekBackward() {
                    return ViewHolder.this.onBackward$MegaCable_Portal_0_75_1_396__prodRelease();
                }
            });
            this.mProgressBar.setMax(Integer.MAX_VALUE);
            Context context = this.mProgressBar.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "mProgressBar.context");
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null) {
                PlaybackDiplomaticoRowPresenter playbackDiplomaticoRowPresenter2 = this.this$0;
                Integer color = value.getDesignManager().getColor("standard.item.selected.outline.color");
                if (color == null) {
                    Intrinsics.throwNpe();
                }
                playbackDiplomaticoRowPresenter2.setProgressColor(color.intValue());
                this.this$0.setSecondaryProgressColor(Color.parseColor("#BC2169"));
            }
            this.hideRunnable = new Runnable() { // from class: androidx.leanback.widget.PlaybackDiplomaticoRowPresenter$ViewHolder$hideRunnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.displayPlaybackIcon(0);
                }
            };
        }

        public final ImageView getMImageView() {
            return this.mImageView;
        }

        public final SeekBar getMProgressBar() {
            return this.mProgressBar;
        }

        public final long getMTotalTimeInMs() {
            return this.mTotalTimeInMs;
        }

        public final void setMTotalTimeInMs(long j) {
            this.mTotalTimeInMs = j;
        }

        public final long getMForcedTotalTimeInMs() {
            return this.mForcedTotalTimeInMs;
        }

        public final void setMForcedTotalTimeInMs(long j) {
            this.mForcedTotalTimeInMs = j;
        }

        public final long getEndingTimeMs() {
            return Math.max(this.mTotalTimeInMs, this.mForcedTotalTimeInMs);
        }

        public final long getMCurrentTimeInMs() {
            return this.mCurrentTimeInMs;
        }

        public final void setMCurrentTimeInMs(long j) {
            this.mCurrentTimeInMs = j;
        }

        public final long getMSecondaryProgressInMs() {
            return this.mSecondaryProgressInMs;
        }

        public final void setMSecondaryProgressInMs(long j) {
            this.mSecondaryProgressInMs = j;
        }

        public final StringBuilder getMTempBuilder() {
            return this.mTempBuilder;
        }

        public final Presenter.ViewHolder getMSelectedViewHolder() {
            return this.mSelectedViewHolder;
        }

        public final void setMSelectedViewHolder(Presenter.ViewHolder viewHolder) {
            this.mSelectedViewHolder = viewHolder;
        }

        public final Object getMSelectedItem() {
            return this.mSelectedItem;
        }

        public final void setMSelectedItem(Object obj) {
            this.mSelectedItem = obj;
        }

        public final PlaybackControlsRow.PlayPauseAction getMPlayPauseAction() {
            return this.mPlayPauseAction;
        }

        public final void setMPlayPauseAction(PlaybackControlsRow.PlayPauseAction playPauseAction) {
            this.mPlayPauseAction = playPauseAction;
        }

        public final int getMThumbHeroIndex() {
            return this.mThumbHeroIndex;
        }

        public final void setMThumbHeroIndex(int i) {
            this.mThumbHeroIndex = i;
        }

        public final PlaybackSeekUi.Client getMSeekClient() {
            return this.mSeekClient;
        }

        public final void setMSeekClient(PlaybackSeekUi.Client client) {
            this.mSeekClient = client;
        }

        public final boolean getMInSeek() {
            return this.mInSeek;
        }

        public final void setMInSeek(boolean z) {
            this.mInSeek = z;
        }

        public final PlaybackSeekDataProvider getMSeekDataProvider() {
            return this.mSeekDataProvider;
        }

        public final void setMSeekDataProvider(PlaybackSeekDataProvider playbackSeekDataProvider) {
            this.mSeekDataProvider = playbackSeekDataProvider;
        }

        public final View getMPlaybackIconBg() {
            return this.mPlaybackIconBg;
        }

        public final ImageView getMPlaybackIcon() {
            return this.mPlaybackIcon;
        }

        public final TextView getMPlaybackIconText() {
            return this.mPlaybackIconText;
        }

        public final TextView getMTitleTextView() {
            return this.mTitleTextView;
        }

        public final TextView getMSubTitleTextView() {
            return this.mSubTitleTextView;
        }

        public final TextView getMBodyTextView() {
            return this.mBodyTextView;
        }

        public final TextView getMRemainingTimeTextView() {
            return this.mRemainingTimeTextView;
        }

        public final TextView getMCurrentTimeTextView() {
            return this.mCurrentTimeTextView;
        }

        public final View getMProgressGuideView() {
            return this.mProgressGuideView;
        }

        public final PlaybackControlsRow.OnPlaybackProgressCallback getMListener() {
            return this.mListener;
        }

        public final PlaybackDiplomaticoControlsRow.OnPlaybackStateCallback getMListener2() {
            return this.mListener2;
        }

        public final boolean getMKeyIsPressed() {
            return this.mKeyIsPressed;
        }

        public final void setMKeyIsPressed(boolean z) {
            this.mKeyIsPressed = z;
        }

        public final boolean getMInFastSeek() {
            return this.mInFastSeek;
        }

        public final void setMInFastSeek(boolean z) {
            this.mInFastSeek = z;
        }

        private final void updateProgressInSeek(boolean forward, boolean inSeek) {
            long j;
            long j2 = this.mCurrentTimeInMs;
            if (forward) {
                if (this.mKeyIsPressed) {
                    j = (this.mTotalTimeInMs * ((long) 2)) / ((long) 100);
                    displayPlaybackIcon(6);
                } else if (inSeek) {
                    j = WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS;
                    displayPlaybackIcon(7);
                } else {
                    j = 10000;
                    displayPlaybackIcon(8);
                }
            } else if (this.mKeyIsPressed) {
                j = ((-this.mTotalTimeInMs) * ((long) 2)) / ((long) 100);
                displayPlaybackIcon(3);
            } else if (inSeek) {
                j = -30000;
                displayPlaybackIcon(4);
            } else {
                j = -10000;
                displayPlaybackIcon(5);
            }
            long j3 = j2 + j;
            long j4 = this.mTotalTimeInMs;
            if (j3 > j4) {
                j3 = j4;
            } else if (j3 < 0) {
                j3 = 0;
            }
            double endingTimeMs = j3 / getEndingTimeMs();
            this.mProgressBar.setProgress((int) (((double) Integer.MAX_VALUE) * endingTimeMs));
            ViewGroup.LayoutParams layoutParams = this.mProgressGuideView.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            }
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.horizontalBias = (float) endingTimeMs;
            this.mProgressGuideView.setLayoutParams(layoutParams2);
            PlaybackSeekUi.Client client = this.mSeekClient;
            if (client == null) {
                Intrinsics.throwNpe();
            }
            client.onSeekPositionChanged(j3);
        }

        public final boolean onForward$MegaCable_Portal_0_75_1_396__prodRelease() {
            boolean z = this.mInSeek;
            if (!startSeek$MegaCable_Portal_0_75_1_396__prodRelease()) {
                return false;
            }
            updateProgressInSeek(true, z);
            return true;
        }

        public final boolean onBackward$MegaCable_Portal_0_75_1_396__prodRelease() {
            boolean z = this.mInSeek;
            if (!startSeek$MegaCable_Portal_0_75_1_396__prodRelease()) {
                return false;
            }
            updateProgressInSeek(false, z);
            return true;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi
        public void setPlaybackSeekUiClient(PlaybackSeekUi.Client client) {
            Intrinsics.checkParameterIsNotNull(client, "client");
            this.mSeekClient = client;
        }

        public final boolean startSeek$MegaCable_Portal_0_75_1_396__prodRelease() {
            if (this.mInSeek) {
                return true;
            }
            PlaybackSeekUi.Client client = this.mSeekClient;
            if (client == null) {
                return false;
            }
            if (client == null) {
                Intrinsics.throwNpe();
            }
            if (!client.isSeekEnabled() || this.mTotalTimeInMs <= 0) {
                return false;
            }
            this.mInSeek = true;
            PlaybackSeekUi.Client client2 = this.mSeekClient;
            if (client2 == null) {
                Intrinsics.throwNpe();
            }
            client2.onSeekStarted();
            PlaybackSeekUi.Client client3 = this.mSeekClient;
            if (client3 == null) {
                Intrinsics.throwNpe();
            }
            this.mSeekDataProvider = client3.getPlaybackSeekDataProvider();
            return true;
        }

        public final void stopSeek$MegaCable_Portal_0_75_1_396__prodRelease(boolean cancelled) {
            if (this.mInSeek) {
                this.mInSeek = false;
                PlaybackSeekUi.Client client = this.mSeekClient;
                if (client == null) {
                    Intrinsics.throwNpe();
                }
                client.onSeekFinished(cancelled);
                PlaybackSeekDataProvider playbackSeekDataProvider = this.mSeekDataProvider;
                if (playbackSeekDataProvider != null) {
                    if (playbackSeekDataProvider == null) {
                        Intrinsics.throwNpe();
                    }
                    playbackSeekDataProvider.reset();
                }
                this.mThumbHeroIndex = -1;
                this.mSeekDataProvider = (PlaybackSeekDataProvider) null;
            }
        }

        public final void dispatchItemSelection$MegaCable_Portal_0_75_1_396__prodRelease() {
            if (isSelected()) {
                if (this.mSelectedViewHolder == null) {
                    if (getOnItemViewSelectedListener() != null) {
                        getOnItemViewSelectedListener().onItemSelected(null, null, this, getRow());
                    }
                } else if (getOnItemViewSelectedListener() != null) {
                    getOnItemViewSelectedListener().onItemSelected(this.mSelectedViewHolder, this.mSelectedItem, this, getRow());
                }
            }
        }

        public final Presenter getPresenter$MegaCable_Portal_0_75_1_396__prodRelease(boolean primary) {
            ObjectAdapter secondaryActionsAdapter;
            if (primary) {
                Row row = getRow();
                if (row == null) {
                    throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.PlaybackControlsRow");
                }
                secondaryActionsAdapter = ((PlaybackControlsRow) row).getPrimaryActionsAdapter();
            } else {
                Row row2 = getRow();
                if (row2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.PlaybackControlsRow");
                }
                secondaryActionsAdapter = ((PlaybackControlsRow) row2).getSecondaryActionsAdapter();
            }
            if (secondaryActionsAdapter == null) {
                return null;
            }
            if (!(secondaryActionsAdapter.getPresenterSelector() instanceof ControlButtonPresenterSelector)) {
                return secondaryActionsAdapter.getPresenter(secondaryActionsAdapter.size() > 0 ? secondaryActionsAdapter.get(0) : null);
            }
            PresenterSelector presenterSelector = secondaryActionsAdapter.getPresenterSelector();
            if (presenterSelector == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.ControlButtonPresenterSelector");
            }
            return ((ControlButtonPresenterSelector) presenterSelector).getSecondaryPresenter();
        }

        protected final void onSetRemainingLabel(long remainingTimeMs) {
            this.this$0.formatTime(remainingTimeMs, this.mTempBuilder);
            this.mRemainingTimeTextView.setText(this.mTempBuilder.toString());
        }

        public final void setTotalTime$MegaCable_Portal_0_75_1_396__prodRelease(long totalTimeMs) {
            if (this.mTotalTimeInMs != totalTimeMs) {
                this.mTotalTimeInMs = totalTimeMs;
                if (this.mForcedTotalTimeInMs != Long.MIN_VALUE) {
                    double endingTimeMs = totalTimeMs / getEndingTimeMs();
                    if (endingTimeMs > 1) {
                        endingTimeMs = 1.0d;
                        this.mTotalTimeInMs = this.mForcedTotalTimeInMs;
                    }
                    this.mProgressBar.setSecondaryProgress((int) (endingTimeMs * ((double) Integer.MAX_VALUE)));
                }
                onSetRemainingLabel(this.mCurrentTimeInMs - getEndingTimeMs());
            }
        }

        public final void setForcedTotalTime(long totalTimeMs) {
            if (this.mForcedTotalTimeInMs != totalTimeMs) {
                this.mForcedTotalTimeInMs = totalTimeMs;
                double endingTimeMs = this.mTotalTimeInMs / getEndingTimeMs();
                if (endingTimeMs > 1) {
                    endingTimeMs = 1.0d;
                    this.mForcedTotalTimeInMs = this.mTotalTimeInMs;
                }
                double d = Integer.MAX_VALUE;
                this.mProgressBar.setSecondaryProgress((int) (endingTimeMs * d));
                this.mProgressBar.setProgress((int) ((this.mCurrentTimeInMs / getEndingTimeMs()) * d));
            }
        }

        protected final void onSetCurrentPositionLabel(long currentTimeMs) {
            this.this$0.formatTime(currentTimeMs, this.mTempBuilder);
            this.mCurrentTimeTextView.setText(this.mTempBuilder.toString());
        }

        public final void setCurrentPosition$MegaCable_Portal_0_75_1_396__prodRelease(long currentTimeMs) {
            if (currentTimeMs != this.mCurrentTimeInMs) {
                this.mCurrentTimeInMs = currentTimeMs;
                onSetCurrentPositionLabel(currentTimeMs);
                onSetRemainingLabel(this.mCurrentTimeInMs - getEndingTimeMs());
            }
            if (this.mInSeek) {
                return;
            }
            double endingTimeMs = getEndingTimeMs() > 0 ? this.mCurrentTimeInMs / getEndingTimeMs() : 0.0d;
            ViewGroup.LayoutParams layoutParams = this.mProgressGuideView.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            }
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.horizontalBias = (float) endingTimeMs;
            this.mProgressGuideView.setLayoutParams(layoutParams2);
            this.mProgressBar.setProgress((int) (endingTimeMs * ((double) Integer.MAX_VALUE)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void displayPlaybackIcon(int icon) {
            this.handler.removeCallbacks(this.hideRunnable);
            if (icon > 0) {
                this.handler.postDelayed(this.hideRunnable, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
            }
            this.mPlaybackIconBg.setVisibility(0);
            this.mPlaybackIcon.setVisibility(0);
            this.mPlaybackIconText.setVisibility(0);
            switch (icon) {
                case 0:
                    this.mPlaybackIconBg.setVisibility(8);
                    this.mPlaybackIcon.setVisibility(8);
                    this.mPlaybackIconText.setVisibility(8);
                    break;
                case 1:
                    int i = this.previousPlaybackIcon;
                    if (i == 0 || i == 2) {
                        ImageView imageView = this.mPlaybackIcon;
                        imageView.setImageDrawable(imageView.getResources().getDrawable(R.drawable.ic_player_play, null));
                        this.mPlaybackIconText.setText("");
                    }
                    break;
                case 2:
                    ImageView imageView2 = this.mPlaybackIcon;
                    imageView2.setImageDrawable(imageView2.getResources().getDrawable(R.drawable.ic_player_pause, null));
                    this.mPlaybackIconText.setText("");
                    this.handler.removeCallbacks(this.hideRunnable);
                    break;
                case 3:
                    ImageView imageView3 = this.mPlaybackIcon;
                    imageView3.setImageDrawable(imageView3.getResources().getDrawable(R.drawable.ic_player_rwd, null));
                    this.mPlaybackIconText.setText("");
                    break;
                case 4:
                    ImageView imageView4 = this.mPlaybackIcon;
                    imageView4.setImageDrawable(imageView4.getResources().getDrawable(R.drawable.ic_player_rwd, null));
                    this.mPlaybackIconText.setText("-30s");
                    break;
                case 5:
                    ImageView imageView5 = this.mPlaybackIcon;
                    imageView5.setImageDrawable(imageView5.getResources().getDrawable(R.drawable.ic_player_rwd, null));
                    this.mPlaybackIconText.setText("-10s");
                    break;
                case 6:
                    ImageView imageView6 = this.mPlaybackIcon;
                    imageView6.setImageDrawable(imageView6.getResources().getDrawable(R.drawable.ic_player_ffw, null));
                    break;
                case 7:
                    ImageView imageView7 = this.mPlaybackIcon;
                    imageView7.setImageDrawable(imageView7.getResources().getDrawable(R.drawable.ic_player_ffw, null));
                    this.mPlaybackIconText.setText("+30s");
                    break;
                case 8:
                    ImageView imageView8 = this.mPlaybackIcon;
                    imageView8.setImageDrawable(imageView8.getResources().getDrawable(R.drawable.ic_player_ffw, null));
                    this.mPlaybackIconText.setText("+10s");
                    break;
            }
            this.previousPlaybackIcon = icon;
        }

        public final void reset() {
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    public final float getMDefaultSeekIncrement() {
        return this.mDefaultSeekIncrement;
    }

    public final void setMDefaultSeekIncrement(float f) {
        this.mDefaultSeekIncrement = f;
    }

    public final int getMProgressColor() {
        return this.mProgressColor;
    }

    public final void setMProgressColor(int i) {
        this.mProgressColor = i;
    }

    public final boolean getMProgressColorSet() {
        return this.mProgressColorSet;
    }

    public final void setMProgressColorSet(boolean z) {
        this.mProgressColorSet = z;
    }

    public final int getMProgressSecondaryColor() {
        return this.mProgressSecondaryColor;
    }

    public final void setMProgressSecondaryColor(int i) {
        this.mProgressSecondaryColor = i;
    }

    public final boolean getMProgressSecondaryColorSet() {
        return this.mProgressSecondaryColorSet;
    }

    public final void setMProgressSecondaryColorSet(boolean z) {
        this.mProgressSecondaryColorSet = z;
    }

    public final OnActionClickedListener getMOnActionClickedListener() {
        return this.mOnActionClickedListener;
    }

    public final void setMOnActionClickedListener(OnActionClickedListener onActionClickedListener) {
        this.mOnActionClickedListener = onActionClickedListener;
    }

    public final void setOnActionClickedListener(OnActionClickedListener listener) {
        this.mOnActionClickedListener = listener;
    }

    public final OnActionClickedListener getOnActionClickedListener() {
        return this.mOnActionClickedListener;
    }

    public final void setProgressColor(int color) {
        this.mProgressColor = color;
        this.mProgressColorSet = true;
    }

    public final void setSecondaryProgressColor(int color) {
        this.mProgressSecondaryColor = color;
        this.mProgressSecondaryColorSet = true;
    }

    public final int getProgressColor() {
        return this.mProgressColor;
    }

    @Override // androidx.leanback.widget.PlaybackRowPresenter
    public void onReappear(RowPresenter.ViewHolder rowViewHolder) {
        Intrinsics.checkParameterIsNotNull(rowViewHolder, "rowViewHolder");
        ViewHolder viewHolder = (ViewHolder) rowViewHolder;
        if (viewHolder.view.hasFocus()) {
            viewHolder.getMProgressBar().requestFocus();
        }
    }

    private final int getDefaultProgressColor(Context context) {
        TypedValue typedValue = new TypedValue();
        return ContextCompat.getColor(context, context.getTheme().resolveAttribute(R.attr.playbackProgressPrimaryColor, typedValue, true) ? typedValue.resourceId : R.color.lb_playback_progress_color_no_theme);
    }

    private final int getSecondaryProgressColor(Context context) {
        TypedValue typedValue = new TypedValue();
        return ContextCompat.getColor(context, context.getTheme().resolveAttribute(R.attr.playbackProgressSecondaryColor, typedValue, true) ? typedValue.resourceId : R.color.lb_playback_progress_color_no_theme);
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lb_playback_transport_controls_row, parent, false);
        if (viewInflate == null) {
            Intrinsics.throwNpe();
        }
        ViewHolder viewHolder = new ViewHolder(this, viewInflate);
        initRow(viewHolder);
        return viewHolder;
    }

    private final void initRow(final ViewHolder vh) {
        int defaultProgressColor;
        int secondaryProgressColor;
        SeekBar mProgressBar = vh.getMProgressBar();
        if (this.mProgressColorSet) {
            defaultProgressColor = this.mProgressColor;
        } else {
            Context context = vh.getMProgressBar().getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "vh.mProgressBar.context");
            defaultProgressColor = getDefaultProgressColor(context);
        }
        mProgressBar.setProgressColor(defaultProgressColor);
        SeekBar mProgressBar2 = vh.getMProgressBar();
        if (this.mProgressSecondaryColorSet) {
            secondaryProgressColor = this.mProgressSecondaryColor;
        } else {
            Context context2 = vh.getMProgressBar().getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "vh.mProgressBar.context");
            secondaryProgressColor = getSecondaryProgressColor(context2);
        }
        mProgressBar2.setSecondaryProgressColor(secondaryProgressColor);
        View viewFindViewById = vh.view.findViewById(R.id.transport_row);
        if (viewFindViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.PlaybackTransportRowView");
        }
        ((PlaybackTransportRowView) viewFindViewById).setOnUnhandledKeyListener(new PlaybackTransportRowView.OnUnhandledKeyListener() { // from class: androidx.leanback.widget.PlaybackDiplomaticoRowPresenter.initRow.1
            @Override // androidx.leanback.widget.PlaybackTransportRowView.OnUnhandledKeyListener
            public final boolean onUnhandledKey(KeyEvent event) {
                if (vh.getOnKeyListener() == null) {
                    return false;
                }
                View.OnKeyListener onKeyListener = vh.getOnKeyListener();
                View view = vh.view;
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                return onKeyListener.onKey(view, event.getKeyCode(), event);
            }
        });
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        super.onBindRowViewHolder(holder, item);
        ViewHolder viewHolder = (ViewHolder) holder;
        Row row = viewHolder.getRow();
        if (row == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.PlaybackDiplomaticoControlsRow");
        }
        PlaybackDiplomaticoControlsRow playbackDiplomaticoControlsRow = (PlaybackDiplomaticoControlsRow) row;
        if (playbackDiplomaticoControlsRow.getImageDrawable() == null) {
            viewHolder.getMImageView().setVisibility(8);
        } else {
            viewHolder.getMImageView().setVisibility(0);
        }
        viewHolder.getMImageView().setImageDrawable(playbackDiplomaticoControlsRow.getImageDrawable());
        viewHolder.setTotalTime$MegaCable_Portal_0_75_1_396__prodRelease(playbackDiplomaticoControlsRow.getDuration());
        viewHolder.setCurrentPosition$MegaCable_Portal_0_75_1_396__prodRelease(playbackDiplomaticoControlsRow.getCurrentPosition());
        playbackDiplomaticoControlsRow.setOnPlaybackProgressChangedListener(viewHolder.getMListener());
        playbackDiplomaticoControlsRow.setOnPlaybackStateChangedListener(viewHolder.getMListener2());
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onUnbindRowViewHolder(RowPresenter.ViewHolder holder) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        ViewHolder viewHolder = (ViewHolder) holder;
        Row row = viewHolder.getRow();
        if (row == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.PlaybackDiplomaticoControlsRow");
        }
        PlaybackDiplomaticoControlsRow playbackDiplomaticoControlsRow = (PlaybackDiplomaticoControlsRow) row;
        viewHolder.reset();
        playbackDiplomaticoControlsRow.setOnPlaybackProgressChangedListener(null);
        playbackDiplomaticoControlsRow.setOnPlaybackStateChangedListener(null);
        super.onUnbindRowViewHolder(holder);
    }

    protected final void onProgressBarClicked(ViewHolder vh) {
        if (vh != null) {
            if (vh.getMPlayPauseAction() == null) {
                View view = vh.view;
                Intrinsics.checkExpressionValueIsNotNull(view, "vh.view");
                vh.setMPlayPauseAction(new PlaybackControlsRow.PlayPauseAction(view.getContext()));
            }
            if (vh.getOnItemViewClickedListener() != null) {
                vh.getOnItemViewClickedListener().onItemClicked(vh, vh.getMPlayPauseAction(), vh, vh.getRow());
            }
        }
    }

    public final void setDefaultSeekIncrement(float ratio) {
        this.mDefaultSeekIncrement = ratio;
    }

    /* JADX INFO: renamed from: getDefaultSeekIncrement, reason: from getter */
    public final float getMDefaultSeekIncrement() {
        return this.mDefaultSeekIncrement;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewSelected(RowPresenter.ViewHolder vh, boolean selected) {
        Intrinsics.checkParameterIsNotNull(vh, "vh");
        super.onRowViewSelected(vh, selected);
        if (selected) {
            ((ViewHolder) vh).dispatchItemSelection$MegaCable_Portal_0_75_1_396__prodRelease();
        }
    }
}
