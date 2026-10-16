package com.dotscreen.portal.ui.cards;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.tv.TvContentRating;
import android.media.tv.TvTrackInfo;
import android.media.tv.TvView;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.bumptech.glide.request.RequestListener;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.farkit.tools.LanguagePreferences;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.cards.LiveCardView.TunerLockChangedRunnable;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.utils.DebouncedLifecycleObserver;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.Context_ExtensionKt;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import net.oregan.sparq.dtvsupportlibrary.DVB;
import net.oregan.sparq.dtvsupportlibraryaidl.DVBTunerMetrics;
import net.oregan.sparq.dtvsupportlibraryaidl.IDVBTunerLockChangedListener;

/* JADX INFO: compiled from: LiveCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000×\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\r\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001/\u0018\u00002\u00020\u0001:\u0002\u008c\u0001B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010[\u001a\u00020\u001cH\u0002J\u001f\u0010\\\u001a\u00020]2\u0006\u0010\\\u001a\u00020\u001c2\n\b\u0002\u0010^\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010_J\u0018\u0010\\\u001a\u00020]2\u0006\u0010\\\u001a\u00020\u001c2\b\u0010`\u001a\u0004\u0018\u00010\fJ\u0006\u0010a\u001a\u00020]J\u0010\u0010b\u001a\u00020]2\b\b\u0002\u0010c\u001a\u00020\u001cJ\b\u0010d\u001a\u00020]H\u0014J\b\u0010e\u001a\u00020]H\u0014J0\u0010f\u001a\u00020]2\u0006\u0010g\u001a\u00020\u001c2\u0006\u0010h\u001a\u00020\u00072\u0006\u0010i\u001a\u00020\u00072\u0006\u0010j\u001a\u00020\u00072\u0006\u0010k\u001a\u00020\u0007H\u0014J\b\u0010l\u001a\u00020]H\u0002J\u0010\u0010m\u001a\u00020]2\b\u0010n\u001a\u0004\u0018\u00010\fJ\u0010\u0010o\u001a\u00020]2\b\u0010p\u001a\u0004\u0018\u00010qJ\u0010\u0010r\u001a\u00020]2\b\u0010p\u001a\u0004\u0018\u00010qJ\u000e\u0010r\u001a\u00020]2\u0006\u0010^\u001a\u00020\u0007J\u000e\u0010s\u001a\u00020]2\u0006\u0010t\u001a\u00020\u001cJ\u000e\u0010u\u001a\u00020]2\u0006\u0010v\u001a\u00020\u0007J\u0016\u0010w\u001a\u00020]2\u0006\u0010x\u001a\u00020\u00072\u0006\u0010y\u001a\u00020\u0007J\u0010\u0010z\u001a\u00020]2\b\u0010p\u001a\u0004\u0018\u00010qJ\u0016\u0010{\u001a\u00020]2\u0006\u0010|\u001a\u00020\f2\u0006\u0010}\u001a\u00020\fJ\u0010\u0010~\u001a\u00020]2\b\u0010p\u001a\u0004\u0018\u00010qJ\u001a\u0010\u007f\u001a\u00020]2\u0007\u0010\u0080\u0001\u001a\u00020\u001c2\t\b\u0002\u0010\u0081\u0001\u001a\u00020\u001cJ\u0007\u0010\u0082\u0001\u001a\u00020]J\u0007\u0010\u0083\u0001\u001a\u00020]J\u0013\u0010\u0084\u0001\u001a\u00020]2\n\b\u0002\u0010\u0085\u0001\u001a\u00030\u0086\u0001J$\u0010\u0087\u0001\u001a\u00020]2\u0007\u0010\u0088\u0001\u001a\u00020\f2\u0007\u0010\u0089\u0001\u001a\u00020B2\t\b\u0002\u0010\u008a\u0001\u001a\u00020EJ\t\u0010\u008b\u0001\u001a\u00020\u001cH\u0002R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020$¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0004\n\u0002\u00100R\u0010\u00101\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u00010(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u00010(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020GX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020?X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020KX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020MX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010N\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0014\u0010S\u001a\b\u0018\u00010TR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010U\u001a\u0004\u0018\u00010VX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010Z¨\u0006\u008d\u0001"}, d2 = {"Lcom/dotscreen/portal/ui/cards/LiveCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "refresher", "Lcom/dotscreen/portal/ui/cards/RefreshCard;", "(Landroid/content/Context;Landroid/util/AttributeSet;ILcom/dotscreen/portal/ui/cards/RefreshCard;)V", "TAG", "", "airing", "Lcom/dotscreen/bokit/model/TVAiring;", "getAiring", "()Lcom/dotscreen/bokit/model/TVAiring;", "setAiring", "(Lcom/dotscreen/bokit/model/TVAiring;)V", "audioChangedCallback", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "getAudioChangedCallback", "()Landroid/media/AudioManager$OnAudioFocusChangeListener;", "audioFocusRequest", "Landroid/media/AudioFocusRequest;", "audioManager", "Landroid/media/AudioManager;", "blocked", "", "channel", "Lcom/dotscreen/tv/common/bokit/RightVChannel;", "getChannel", "()Lcom/dotscreen/tv/common/bokit/RightVChannel;", "setChannel", "(Lcom/dotscreen/tv/common/bokit/RightVChannel;)V", "channelLogoImageView", "Landroid/widget/ImageView;", "getChannelLogoImageView", "()Landroid/widget/ImageView;", "error_message", "Landroid/widget/TextView;", "languagePreferences", "Lcom/dotscreen/farkit/tools/LanguagePreferences;", "lastAudioFocus", "lastException", "", "lifecycleObserver", "com/dotscreen/portal/ui/cards/LiveCardView$lifecycleObserver$1", "Lcom/dotscreen/portal/ui/cards/LiveCardView$lifecycleObserver$1;", "mAiringChannelLogoUrl", "mAiringChannelNumber", "mAiringImgUrl", "mAiringOverlay", "Landroid/view/View;", "mAiringParentalRatingImg", "mAiringSubTitle", "mAiringTime", "mAiringTitle", "mAttachedToWindow", "mBlockScreen", "mDVBTunerLockChangedListener", "Lnet/oregan/sparq/dtvsupportlibraryaidl/IDVBTunerLockChangedListener;", "mProgressBar", "Landroid/widget/ProgressBar;", "mTag", "mTuneChannelUri", "Landroid/net/Uri;", "mTuneInputId", "mTuneParams", "Landroid/os/Bundle;", "mTvView", "Landroid/media/tv/TvView;", "mVideoAvailable", "messageProgress", "scanStatusReceiver", "Landroid/content/BroadcastReceiver;", "tifSessionConnectionTimeout", "Ljava/lang/Runnable;", "tuned", "getTuned", "()Z", "setTuned", "(Z)V", "tunerLockChangedRunnable", "Lcom/dotscreen/portal/ui/cards/LiveCardView$TunerLockChangedRunnable;", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/tv/common/bokit/RightVUser;", "getUser", "()Lcom/dotscreen/tv/common/bokit/RightVUser;", "setUser", "(Lcom/dotscreen/tv/common/bokit/RightVUser;)V", "allowedToPlay", "block", "", "res", "(ZLjava/lang/Integer;)V", "string", "checkDVBCableStatus", "doTune", "force", "onAttachedToWindow", "onDetachedFromWindow", "onLayout", "changed", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "retrieveAndSetPreferredAudio", "setChannelLogoImage", ImagesContract.URL, "setChannelNumber", "text", "", "setErrorMessage", "setInteractiveTag", "visible", "setParentalRatingLogo", "id", "setProgress", "duration", "livePosition", "setSubTitle", "setTime", "start", TtmlNode.END, "setTitle", "showOverlay", "show", "lite", "startLoading", "stopLoading", "stopTune", "reason", "Lcom/dotscreen/portal/ui/cards/StopTuneReason;", "tune", "inputId", "channelUri", "params", "validateScreenOnOrStopTune", "TunerLockChangedRunnable", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class LiveCardView extends CardView {
    private final String TAG;
    private HashMap _$_findViewCache;
    private TVAiring airing;
    private final AudioManager.OnAudioFocusChangeListener audioChangedCallback;
    private AudioFocusRequest audioFocusRequest;
    private final AudioManager audioManager;
    private boolean blocked;
    private RightVChannel channel;
    private final ImageView channelLogoImageView;
    private final TextView error_message;
    private LanguagePreferences languagePreferences;
    private int lastAudioFocus;
    private Throwable lastException;
    private final LiveCardView$lifecycleObserver$1 lifecycleObserver;
    private String mAiringChannelLogoUrl;
    private final TextView mAiringChannelNumber;
    private String mAiringImgUrl;
    private final View mAiringOverlay;
    private final ImageView mAiringParentalRatingImg;
    private final TextView mAiringSubTitle;
    private final TextView mAiringTime;
    private final TextView mAiringTitle;
    private boolean mAttachedToWindow;
    private final View mBlockScreen;
    private final IDVBTunerLockChangedListener mDVBTunerLockChangedListener;
    private final ProgressBar mProgressBar;
    private TextView mTag;
    private Uri mTuneChannelUri;
    private String mTuneInputId;
    private Bundle mTuneParams;
    private TvView mTvView;
    private boolean mVideoAvailable;
    private final ProgressBar messageProgress;
    private final RefreshCard refresher;
    private final BroadcastReceiver scanStatusReceiver;
    private final Runnable tifSessionConnectionTimeout;
    private boolean tuned;
    private TunerLockChangedRunnable tunerLockChangedRunnable;
    private RightVUser user;

    public LiveCardView(Context context, AttributeSet attributeSet, RefreshCard refreshCard) {
        this(context, attributeSet, 0, refreshCard, 4, null);
    }

    public LiveCardView(Context context, RefreshCard refreshCard) {
        this(context, null, 0, refreshCard, 6, null);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public /* synthetic */ LiveCardView(Context context, AttributeSet attributeSet, int i, RefreshCard refreshCard, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i, refreshCard);
    }

    public static final /* synthetic */ TvView access$getMTvView$p(LiveCardView liveCardView) {
        TvView tvView = liveCardView.mTvView;
        if (tvView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTvView");
        }
        return tvView;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.dotscreen.portal.ui.cards.LiveCardView$lifecycleObserver$1] */
    public LiveCardView(final Context context, AttributeSet attributeSet, int i, RefreshCard refresher) {
        RightVUser user;
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(refresher, "refresher");
        this.refresher = refresher;
        this.TAG = LiveCardView.class.getSimpleName();
        this.tifSessionConnectionTimeout = new Runnable() { // from class: com.dotscreen.portal.ui.cards.LiveCardView$tifSessionConnectionTimeout$1
            @Override // java.lang.Runnable
            public final void run() {
                LiveCardView liveCardView = this.this$0;
                liveCardView.block(true, CommonUtils.isDVBCSTB(liveCardView.getContext()) ? this.this$0.getResources().getString(R.string.tvview_msg_unknown_dvb_error, "TIF-NOT_RESPONDING") : "TIF-NOT_RESPONDING");
            }
        };
        this.scanStatusReceiver = new BroadcastReceiver() { // from class: com.dotscreen.portal.ui.cards.LiveCardView$scanStatusReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action;
                Intrinsics.checkParameterIsNotNull(context2, "context");
                Intrinsics.checkParameterIsNotNull(intent, "intent");
                Log.d(this.this$0.TAG, "ScanStatusReceiver: onReceive(" + intent + ')');
                if (intent.getAction() == null || (action = intent.getAction()) == null) {
                    return;
                }
                int iHashCode = action.hashCode();
                if (iHashCode != -1027985502) {
                    if (iHashCode == 1970710321 && action.equals(CommonUtils.ACTION_DVB_SCAN_STARTED) && CommonUtils.getCurrentPlatform() == 2) {
                        this.this$0.stopTune(StopTuneReason.ScanInProgress);
                        return;
                    }
                    return;
                }
                if (action.equals(CommonUtils.ACTION_DVB_SCAN_FINISHED)) {
                    this.this$0.refresher.refresh();
                }
            }
        };
        Log.d(this.TAG, "init");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(getContext())");
        layoutInflaterFrom.inflate(R.layout.card_live, this);
        this.mAiringOverlay = findViewById(R.id.airing_overlay);
        this.mAiringTitle = (TextView) findViewById(R.id.airing_title);
        this.mAiringSubTitle = (TextView) findViewById(R.id.airing_subtitle);
        View viewFindViewById = findViewById(R.id.airing_time);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.airing_time)");
        this.mAiringTime = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.tvView);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.tvView)");
        this.mTvView = (TvView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.live_progress);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.live_progress)");
        this.mProgressBar = (ProgressBar) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.loading_message);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById(R.id.loading_message)");
        this.messageProgress = (ProgressBar) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.error_message);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "findViewById(R.id.error_message)");
        this.error_message = (TextView) viewFindViewById5;
        View viewFindViewById6 = findViewById(R.id.airing_channel_logo);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "findViewById(R.id.airing_channel_logo)");
        this.channelLogoImageView = (ImageView) viewFindViewById6;
        View viewFindViewById7 = findViewById(R.id.airing_channel_number);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "findViewById(R.id.airing_channel_number)");
        this.mAiringChannelNumber = (TextView) viewFindViewById7;
        View viewFindViewById8 = findViewById(R.id.parental_rating);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "findViewById(R.id.parental_rating)");
        this.mAiringParentalRatingImg = (ImageView) viewFindViewById8;
        View viewFindViewById9 = findViewById(R.id.blockScreen);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "findViewById(R.id.blockScreen)");
        this.mBlockScreen = viewFindViewById9;
        this.error_message.setText("");
        setFocusable(true);
        setFocusableInTouchMode(true);
        LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(context).getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(context).config");
        PortalApplication.Config value = config.getValue();
        IDVBTunerLockChangedListener.Stub stub = null;
        this.languagePreferences = new LanguagePreferences(context, (value == null || (user = value.getUser()) == null) ? null : user.getCompassId());
        this.audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.mTag = (TextView) findViewById(R.id.tag);
        TvView tvView = this.mTvView;
        if (tvView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTvView");
        }
        tvView.setCallback(new TvView.TvInputCallback() { // from class: com.dotscreen.portal.ui.cards.LiveCardView.1
            @Override // android.media.tv.TvView.TvInputCallback
            public void onConnectionFailed(String inputId) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                super.onConnectionFailed(inputId);
                Log.w(LiveCardView.this.TAG, "onConnectionFailed(" + inputId + ')');
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onDisconnected(String inputId) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                super.onDisconnected(inputId);
                Log.d(LiveCardView.this.TAG, "onDisconnected(" + inputId + ')');
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onChannelRetuned(String inputId, Uri channelUri) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                Intrinsics.checkParameterIsNotNull(channelUri, "channelUri");
                super.onChannelRetuned(inputId, channelUri);
                Log.d(LiveCardView.this.TAG, "onChannelRetuned(" + inputId + ", " + channelUri + ')');
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onTracksChanged(String inputId, List<TvTrackInfo> tracks) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                Intrinsics.checkParameterIsNotNull(tracks, "tracks");
                super.onTracksChanged(inputId, tracks);
                Log.d(LiveCardView.this.TAG, "onTracksChanged(" + inputId + ", " + tracks + ')');
                LiveCardView.this.retrieveAndSetPreferredAudio();
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onTrackSelected(String inputId, int type, String trackId) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                Intrinsics.checkParameterIsNotNull(trackId, "trackId");
                super.onTrackSelected(inputId, type, trackId);
                Log.d(LiveCardView.this.TAG, "onTrackSelected(" + inputId + ", " + type + ", " + trackId + ')');
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onVideoSizeChanged(String inputId, int width, int height) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                super.onVideoSizeChanged(inputId, width, height);
                Log.d(LiveCardView.this.TAG, "onVideoSizeChanged(" + inputId + ", " + width + ", " + height + ')');
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onVideoAvailable(String inputId) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                super.onVideoAvailable(inputId);
                Handler handler = LiveCardView.this.getHandler();
                if (handler != null) {
                    handler.removeCallbacks(LiveCardView.this.tifSessionConnectionTimeout);
                }
                Log.d(LiveCardView.this.TAG, "onVideoAvailable(" + inputId + ')');
                if (LiveCardView.this.validateScreenOnOrStopTune()) {
                    LiveCardView.this.stopLoading();
                    LiveCardView.this.mVideoAvailable = true;
                    Log.d(LiveCardView.this.TAG, "mVideoAvailable = " + LiveCardView.this.mVideoAvailable + "  | LiveCardView = " + LiveCardView.this);
                    if (LiveCardView.this.blocked) {
                        return;
                    }
                    Log.d(LiveCardView.this.TAG, "onVideoAvailable - not blocked");
                    LiveCardView.this.mBlockScreen.setVisibility(8);
                    LiveCardView.this.setErrorMessage("");
                }
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onVideoUnavailable(String inputId, int reason) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                super.onVideoUnavailable(inputId, reason);
                Handler handler = LiveCardView.this.getHandler();
                if (handler != null) {
                    handler.removeCallbacks(LiveCardView.this.tifSessionConnectionTimeout);
                }
                Log.w(LiveCardView.this.TAG, "onVideoUnavailable(" + inputId + ", " + reason + ')');
                if (LiveCardView.this.validateScreenOnOrStopTune()) {
                    LiveCardView.this.checkDVBCableStatus();
                    if (LiveCardView.this.mVideoAvailable && reason == 3) {
                        return;
                    }
                    Log.d(LiveCardView.this.TAG, "onVideoUnavailable - blocked");
                    LiveCardView.this.mBlockScreen.setVisibility(0);
                    LiveCardView.this.setErrorMessage(CommonUtils.getVideoUnavailableMessage(context, reason, LiveCardView.this.lastException));
                    if (reason == 0) {
                        LiveCardView.access$getMTvView$p(LiveCardView.this).setStreamVolume(0.0f);
                    }
                }
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onContentAllowed(String inputId) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                super.onContentAllowed(inputId);
                LiveCardView.this.setErrorMessage("");
                Log.d(LiveCardView.this.TAG, "onContentAllowed(" + inputId + ')');
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onContentBlocked(String inputId, TvContentRating rating) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                Intrinsics.checkParameterIsNotNull(rating, "rating");
                super.onContentBlocked(inputId, rating);
                LiveCardView.this.setErrorMessage(R.string.shrunken_tvview_content_locked);
                Log.w(LiveCardView.this.TAG, "onContentBlocked(" + inputId + ", " + rating + ')');
            }

            @Override // android.media.tv.TvView.TvInputCallback
            public void onTimeShiftStatusChanged(String inputId, int status) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                super.onTimeShiftStatusChanged(inputId, status);
                Log.d(LiveCardView.this.TAG, "onTimeShiftStatusChanged(" + inputId + ", " + status + ')');
            }

            public void onEvent(String inputId, String eventType, Bundle bundle) {
                Intrinsics.checkParameterIsNotNull(inputId, "inputId");
                Intrinsics.checkParameterIsNotNull(eventType, "eventType");
                Log.d(LiveCardView.this.TAG, "onEvent(" + inputId + ", " + eventType + ", " + bundle + ')');
                if (Intrinsics.areEqual(eventType, CommonUtils.TIS_EVENT_EXTRA_ERROR)) {
                    LiveCardView liveCardView = LiveCardView.this;
                    Serializable serializable = bundle != null ? bundle.getSerializable("error") : null;
                    liveCardView.lastException = (Throwable) (serializable instanceof Throwable ? serializable : null);
                } else if (Intrinsics.areEqual(eventType, "scanStatus")) {
                    Log.d(LiveCardView.this.TAG, "Oregan scan in progress");
                }
            }
        });
        View viewFindViewById10 = findViewById(R.id.root);
        if (viewFindViewById10 != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value2 = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value2 != null) {
                DesignManager designManager = value2.getDesignManager();
                Drawable foreground = viewFindViewById10.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById10.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
        }
        if (CommonUtils.getCurrentPlatform() == 1 && CommonUtils.isDVBCSTB(context)) {
            stub = new IDVBTunerLockChangedListener.Stub() { // from class: com.dotscreen.portal.ui.cards.LiveCardView$mDVBTunerLockChangedListener$1
                @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVBTunerLockChangedListener
                public void onTunerLockChanged(boolean locked) throws RemoteException {
                    Log.d(this.this$0.TAG, "DVBCable - onTunerLockChanged(" + locked + ")  | LiveCardView = " + this.this$0);
                    this.this$0.tunerLockChangedRunnable = this.this$0.new TunerLockChangedRunnable(locked);
                    Handler handler = this.this$0.getHandler();
                    if (handler != null) {
                        handler.post(this.this$0.tunerLockChangedRunnable);
                    }
                }
            };
        }
        this.mDVBTunerLockChangedListener = stub;
        this.lifecycleObserver = new DebouncedLifecycleObserver() { // from class: com.dotscreen.portal.ui.cards.LiveCardView$lifecycleObserver$1
            @Override // com.dotscreen.portal.utils.DebouncedLifecycleObserver
            public void onPause() {
                Log.d(this.this$0.TAG, "lifecycleObserver::onPause");
                LiveCardView.stopTune$default(this.this$0, null, 1, null);
                this.this$0.stopLoading();
            }

            @Override // com.dotscreen.portal.utils.DebouncedLifecycleObserver
            public void onResume() {
                Log.d(this.this$0.TAG, "lifecycleObserver::onResume");
                Handler handler = this.this$0.getHandler();
                if (handler != null) {
                    handler.removeCallbacks(this.this$0.tunerLockChangedRunnable);
                }
                LiveCardView.doTune$default(this.this$0, false, 1, null);
            }
        };
        this.audioChangedCallback = new AudioManager.OnAudioFocusChangeListener() { // from class: com.dotscreen.portal.ui.cards.LiveCardView$audioChangedCallback$1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i2) {
                if (!this.this$0.blocked) {
                    if (i2 == -3) {
                        ((TvView) this.this$0._$_findCachedViewById(com.dotscreen.portal.R.id.tvView)).setStreamVolume(0.3f);
                    } else if (i2 == -2 || i2 == -1) {
                        ((TvView) this.this$0._$_findCachedViewById(com.dotscreen.portal.R.id.tvView)).setStreamVolume(0.0f);
                    } else if (i2 == 1) {
                        ((TvView) this.this$0._$_findCachedViewById(com.dotscreen.portal.R.id.tvView)).setStreamVolume(1.0f);
                    }
                }
                this.this$0.lastAudioFocus = i2;
            }
        };
    }

    public final RightVChannel getChannel() {
        return this.channel;
    }

    public final void setChannel(RightVChannel rightVChannel) {
        this.channel = rightVChannel;
    }

    public final TVAiring getAiring() {
        return this.airing;
    }

    public final void setAiring(TVAiring tVAiring) {
        this.airing = tVAiring;
    }

    public final RightVUser getUser() {
        return this.user;
    }

    public final void setUser(RightVUser rightVUser) {
        this.user = rightVUser;
    }

    public final ImageView getChannelLogoImageView() {
        return this.channelLogoImageView;
    }

    public final boolean getTuned() {
        return this.tuned;
    }

    public final void setTuned(boolean z) {
        this.tuned = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean validateScreenOnOrStopTune() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (((PortalApplication) applicationContext).isScreenOn()) {
            return true;
        }
        Log.e(this.TAG, "Screen is off -> stopTune()");
        stopTune$default(this, null, 1, null);
        return false;
    }

    public final void startLoading() {
        this.messageProgress.setVisibility(0);
    }

    public final void stopLoading() {
        Log.d(this.TAG, "Stop loading");
        this.messageProgress.setVisibility(8);
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.cards.LiveCardView$checkDVBCableStatus$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LiveCardView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.ui.cards.LiveCardView$checkDVBCableStatus$1", f = "LiveCardView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03351(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03351 c03351 = LiveCardView.this.new C03351(completion);
            c03351.p$ = (CoroutineScope) obj;
            return c03351;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DVB dvb = DVB.getInstance(LiveCardView.this.getContext());
            if (dvb != null) {
                DVBTunerMetrics tunerSignalMetrics = dvb.getTunerSignalMetrics(0);
                Log.w(LiveCardView.this.TAG, "DVBCable - signalLocked tm - " + tunerSignalMetrics);
                if (tunerSignalMetrics != null) {
                    Log.w(LiveCardView.this.TAG, "DVBCable - signalLocked - " + tunerSignalMetrics.signalLocked);
                    if (!tunerSignalMetrics.signalLocked) {
                        LiveCardView.this.mVideoAvailable = false;
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01181(null), 2, null);
                    }
                } else {
                    LiveCardView.this.mVideoAvailable = false;
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass2(null), 2, null);
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.cards.LiveCardView$checkDVBCableStatus$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LiveCardView.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.portal.ui.cards.LiveCardView$checkDVBCableStatus$1$1", f = "LiveCardView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01181(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01181 c01181 = C03351.this.new C01181(completion);
                c01181.p$ = (CoroutineScope) obj;
                return c01181;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                LiveCardView.this.block(true, Boxing.boxInt(R.string.tvview_msg_weak_signal));
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.cards.LiveCardView$checkDVBCableStatus$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: LiveCardView.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.portal.ui.cards.LiveCardView$checkDVBCableStatus$1$2", f = "LiveCardView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C03351.this.new AnonymousClass2(completion);
                anonymousClass2.p$ = (CoroutineScope) obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                LiveCardView.this.block(true, Boxing.boxInt(R.string.tvview_msg_weak_signal));
                return Unit.INSTANCE;
            }
        }
    }

    public final void checkDVBCableStatus() {
        if (CommonUtils.getCurrentPlatform() == 1 && CommonUtils.isDVBCSTB(getContext())) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C03351(null), 2, null);
        }
    }

    public final void setTitle(CharSequence text) {
        TextView textView = this.mAiringTitle;
        if (textView == null) {
            return;
        }
        textView.setText(text);
    }

    public final void setSubTitle(CharSequence text) {
        TextView textView = this.mAiringSubTitle;
        if (textView == null) {
            return;
        }
        textView.setText(text);
    }

    public final void setErrorMessage(int res) {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        CharSequence text = context.getResources().getText(res);
        Log.d(this.TAG, "Message to display :" + text);
        this.error_message.setText(text);
    }

    public final void setErrorMessage(CharSequence text) {
        Log.d(this.TAG, "Message to display :" + text);
        if (!Intrinsics.areEqual(text, "")) {
            stopLoading();
        }
        TextView textView = this.error_message;
        if (text == null) {
        }
        textView.setText(text);
    }

    public final void setChannelNumber(CharSequence text) {
        this.mAiringChannelNumber.setText(text);
    }

    public final void setTime(String start, String end) {
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(end, "end");
        this.mAiringTime.setText(start + " - " + end);
    }

    public final void setInteractiveTag(boolean visible) {
        if (visible) {
            TextView textView = this.mTag;
            if (textView != null) {
                textView.setVisibility(0);
                return;
            }
            return;
        }
        TextView textView2 = this.mTag;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
    }

    public final void setProgress(int duration, int livePosition) {
        this.mProgressBar.setVisibility(0);
        this.mProgressBar.setMax(duration);
        this.mProgressBar.setProgress(livePosition);
    }

    public final void setChannelLogoImage(String url) {
        if (url == null || (!Intrinsics.areEqual(url, this.mAiringChannelLogoUrl))) {
            this.mAiringChannelLogoUrl = url;
            this.channelLogoImageView.setImageBitmap(null);
            if (url != null) {
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                new ImageLoaderManager(context).loadImageAsBitmap(url, (3064 & 2) != 0 ? (ImageView) null : this.channelLogoImageView, (3064 & 4) != 0 ? false : true, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : null, (3064 & 1024) == 0, (3064 & 2048) != 0 ? (Drawable) null : null);
            }
        }
    }

    public final void setParentalRatingLogo(int id) {
        if (id > 0) {
            this.mAiringParentalRatingImg.setVisibility(0);
            this.mAiringParentalRatingImg.setImageResource(id);
        } else {
            this.mAiringParentalRatingImg.setVisibility(8);
        }
    }

    public static /* synthetic */ void showOverlay$default(LiveCardView liveCardView, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        liveCardView.showOverlay(z, z2);
    }

    public final void showOverlay(boolean show, boolean lite) {
        List<View> listMutableListOf = CollectionsKt.mutableListOf(this.mAiringOverlay, this.channelLogoImageView, this.mAiringChannelNumber, this.mAiringParentalRatingImg);
        View[] viewArr = {this.mAiringTitle, this.mAiringSubTitle, this.mAiringTime, this.mProgressBar};
        if (!lite) {
            CollectionsKt.addAll(listMutableListOf, viewArr);
        } else {
            for (int i = 0; i < 4; i++) {
                View view = viewArr[i];
                if (view != null) {
                    view.setVisibility(8);
                }
            }
            this.mAiringChannelNumber.setText("");
            this.channelLogoImageView.setImageBitmap(null);
        }
        for (View view2 : listMutableListOf) {
            if (Intrinsics.areEqual(view2, this.mAiringOverlay) && lite) {
                if (view2 != null) {
                    view2.setVisibility(8);
                    return;
                }
                return;
            } else if (view2 != null) {
                view2.setVisibility(show ? 0 : 8);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(this.TAG, "onLayout changed: " + changed + ", left:" + left + ", right:" + right + ", top:" + top + ", bottom:" + bottom);
        if (changed) {
            TvView tvView = this.mTvView;
            if (tvView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTvView");
            }
            View childAt = tvView.getChildAt(0);
            if (childAt == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.SurfaceView");
            }
            ((SurfaceView) childAt).getHolder().setFixedSize(right - left, bottom - top);
        }
    }

    public static /* synthetic */ void tune$default(LiveCardView liveCardView, String str, Uri uri, Bundle bundle, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = new Bundle();
        }
        liveCardView.tune(str, uri, bundle);
    }

    public final void tune(String inputId, Uri channelUri, Bundle params) {
        Intrinsics.checkParameterIsNotNull(inputId, "inputId");
        Intrinsics.checkParameterIsNotNull(channelUri, "channelUri");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Log.d(this.TAG, "tune(" + inputId + ", " + channelUri + ", " + params + ')');
        startLoading();
        TvView tvView = this.mTvView;
        if (tvView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTvView");
        }
        if (tvView.getVisibility() != 0) {
            TvView tvView2 = this.mTvView;
            if (tvView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTvView");
            }
            tvView2.setVisibility(0);
        }
        if (!Intrinsics.areEqual(this.mTuneChannelUri, channelUri)) {
            this.mTuneInputId = inputId;
            this.mTuneChannelUri = channelUri;
            this.mTuneParams = params;
            stopTune$default(this, null, 1, null);
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Current state => ");
            Context context = getContext();
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
            Lifecycle lifecycle = ((BaseFragmentActivity) context).getLifecycle();
            Intrinsics.checkExpressionValueIsNotNull(lifecycle, "(context as BaseFragmentActivity).lifecycle");
            sb.append(lifecycle.getCurrentState());
            Log.d(str, sb.toString());
            Context context2 = getContext();
            if (context2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
            Lifecycle lifecycle2 = ((BaseFragmentActivity) context2).getLifecycle();
            Intrinsics.checkExpressionValueIsNotNull(lifecycle2, "(context as BaseFragmentActivity).lifecycle");
            if (lifecycle2.getCurrentState() == Lifecycle.State.RESUMED) {
                TvView tvView3 = this.mTvView;
                if (tvView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTvView");
                }
                View childAt = tvView3.getChildAt(0);
                if (childAt == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.view.SurfaceView");
                }
                ((SurfaceView) childAt).getHolder().setFixedSize(getRight() - getLeft(), getBottom() - getTop());
                this.mTuneInputId = inputId;
                this.mTuneChannelUri = channelUri;
                this.mTuneParams = params;
                doTune$default(this, false, 1, null);
            }
        }
        Context context3 = getContext();
        if (context3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        ((BaseFragmentActivity) context3).getLifecycle().addObserver(this.lifecycleObserver);
        try {
            getContext().unregisterReceiver(this.scanStatusReceiver);
        } catch (Exception unused) {
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CommonUtils.ACTION_DVB_SCAN_STARTED);
        intentFilter.addAction(CommonUtils.ACTION_DVB_SCAN_FINISHED);
        getContext().registerReceiver(this.scanStatusReceiver, intentFilter);
    }

    public static /* synthetic */ void stopTune$default(LiveCardView liveCardView, StopTuneReason stopTuneReason, int i, Object obj) {
        if ((i & 1) != 0) {
            stopTuneReason = StopTuneReason.PlaybackFinished;
        }
        liveCardView.stopTune(stopTuneReason);
    }

    public final void stopTune(StopTuneReason reason) {
        AudioManager audioManager;
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        Log.d(this.TAG, "stopTune(), tuned = " + this.tuned);
        if (this.tuned || reason == StopTuneReason.NotSubscribed || reason == StopTuneReason.NotAllowed || reason == StopTuneReason.ScanInProgress) {
            if (CommonUtils.isDVBCSTB(getContext()) && this.mDVBTunerLockChangedListener != null && CommonUtils.getCurrentPlatform() == 1) {
                Log.d(this.TAG, "Set tuner lock listener to null");
                DVB.getInstance(getContext()).setTunerLockChangedListener(null);
            }
            TvView tvView = this.mTvView;
            if (tvView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTvView");
            }
            tvView.reset();
            this.tuned = false;
            if (reason == StopTuneReason.NotSubscribed || reason == StopTuneReason.NotAllowed || reason == StopTuneReason.ScanInProgress) {
                this.tuned = true;
                this.mVideoAvailable = false;
            }
            if (reason != StopTuneReason.ScanInProgress) {
                this.mTuneChannelUri = (Uri) null;
            }
            AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
            if (audioFocusRequest != null && (audioManager = this.audioManager) != null) {
                audioManager.abandonAudioFocusRequest(audioFocusRequest);
            }
        }
        if (reason != StopTuneReason.ScanInProgress) {
            Context context = getContext();
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
            ((BaseFragmentActivity) context).getLifecycle().removeObserver(this.lifecycleObserver);
            try {
                getContext().unregisterReceiver(this.scanStatusReceiver);
            } catch (Exception unused) {
            }
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.tifSessionConnectionTimeout);
        }
    }

    public static /* synthetic */ void block$default(LiveCardView liveCardView, boolean z, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        liveCardView.block(z, num);
    }

    public final void block(boolean block, Integer res) {
        Log.w(this.TAG, "Block miniplayer: " + block + " ; mVideoAvailable: " + this.mVideoAvailable + "  | LiveCardView = " + this);
        if (block) {
            this.mBlockScreen.setVisibility(0);
            setErrorMessage(res != null ? res.intValue() : R.string.tvview_msg_unknown_error_fallback);
            TvView tvView = this.mTvView;
            if (tvView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTvView");
            }
            tvView.setStreamVolume(0.0f);
        } else if (this.mVideoAvailable) {
            this.mBlockScreen.setVisibility(8);
            setErrorMessage("");
            this.audioChangedCallback.onAudioFocusChange(this.lastAudioFocus);
        }
        stopLoading();
        this.blocked = block;
    }

    public final void block(boolean block, String string) {
        Log.w(this.TAG, "Block miniplayer: " + block + " ; mVideoAvailable: " + this.mVideoAvailable + "  | LiveCardView = " + this);
        if (block) {
            this.mBlockScreen.setVisibility(0);
            if (string == null) {
                string = getContext().getString(R.string.tvview_msg_unknown_error_fallback);
            }
            setErrorMessage(string);
            TvView tvView = this.mTvView;
            if (tvView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTvView");
            }
            tvView.setStreamVolume(0.0f);
        } else if (this.mVideoAvailable) {
            this.mBlockScreen.setVisibility(8);
            setErrorMessage("");
            this.audioChangedCallback.onAudioFocusChange(this.lastAudioFocus);
        }
        stopLoading();
        this.blocked = block;
    }

    public static /* synthetic */ void doTune$default(LiveCardView liveCardView, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        liveCardView.doTune(z);
    }

    public final void doTune(boolean force) {
        Log.d(this.TAG, "doTune(), tuned = " + this.tuned + ", force = " + force);
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Context_ExtensionKt.restartNanoCdnSession(context);
        if (CommonUtils.getCurrentPlatform() == 1 && CommonUtils.isDVBCSTB(getContext()) && this.mDVBTunerLockChangedListener != null) {
            Log.d(this.TAG, "Set listener " + this.mDVBTunerLockChangedListener);
            DVB.getInstance(getContext()).setTunerLockChangedListener(this.mDVBTunerLockChangedListener);
        }
        if (!this.tuned || force) {
            Integer numValueOf = null;
            if (!allowedToPlay()) {
                Log.d(this.TAG, "doTune(), not allowed to play");
                this.mVideoAvailable = false;
                this.lastException = (Throwable) null;
                Handler handler = getHandler();
                if (handler != null) {
                    handler.removeCallbacks(this.tifSessionConnectionTimeout);
                }
                stopTune(StopTuneReason.NotAllowed);
                setErrorMessage(R.string.shrunken_tvview_content_locked);
                return;
            }
            this.mVideoAvailable = false;
            this.lastException = (Throwable) null;
            Handler handler2 = getHandler();
            if (handler2 != null) {
                handler2.removeCallbacks(this.tifSessionConnectionTimeout);
            }
            Handler handler3 = getHandler();
            if (handler3 != null) {
                handler3.postDelayed(this.tifSessionConnectionTimeout, 10000L);
            }
            TvView tvView = this.mTvView;
            if (tvView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTvView");
            }
            tvView.tune(this.mTuneInputId, this.mTuneChannelUri, this.mTuneParams);
            TvView tvView2 = this.mTvView;
            if (tvView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTvView");
            }
            tvView2.setZOrderMediaOverlay(true);
            TvView tvView3 = this.mTvView;
            if (tvView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTvView");
            }
            tvView3.setMain();
            this.tuned = true;
            this.mBlockScreen.setVisibility(0);
            if (this.audioFocusRequest == null) {
                this.audioFocusRequest = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(3).build()).setOnAudioFocusChangeListener(this.audioChangedCallback).build();
            }
            AudioManager audioManager = this.audioManager;
            if (audioManager != null) {
                AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
                if (audioFocusRequest == null) {
                    Intrinsics.throwNpe();
                }
                numValueOf = Integer.valueOf(audioManager.requestAudioFocus(audioFocusRequest));
            }
            this.audioChangedCallback.onAudioFocusChange((numValueOf == null || numValueOf.intValue() != 1) ? -1 : 1);
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.cards.LiveCardView$retrieveAndSetPreferredAudio$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LiveCardView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.ui.cards.LiveCardView$retrieveAndSetPreferredAudio$1", f = "LiveCardView.kt", i = {0}, l = {589}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C03361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        C03361(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03361 c03361 = LiveCardView.this.new C03361(completion);
            c03361.p$ = (CoroutineScope) obj;
            return c03361;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03361) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                CoroutineDispatcher io2 = Dispatchers.getIO();
                LiveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1 liveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1 = new LiveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1(this, null);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = BuildersKt.withContext(io2, liveCardView$retrieveAndSetPreferredAudio$1$preferredLanguages$1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Pair pair = (Pair) obj;
            List<TvTrackInfo> tracks = LiveCardView.access$getMTvView$p(LiveCardView.this).getTracks(0);
            if (tracks != null && (true ^ tracks.isEmpty())) {
                for (TvTrackInfo tvTrackInfo : tracks) {
                    if (Intrinsics.areEqual(tvTrackInfo.getLanguage(), pair != null ? (String) pair.getFirst() : null)) {
                        LiveCardView.access$getMTvView$p(LiveCardView.this).selectTrack(0, tvTrackInfo.getId());
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retrieveAndSetPreferredAudio() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C03361(null), 2, null);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0093  */
    /* JADX WARN: Code duplicated, block: B:38:0x0097  */
    /* JADX WARN: Code duplicated, block: B:40:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:46:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:49:0x00da  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:55:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:61:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:62:? A[RETURN, SYNTHETIC] */
    private final boolean allowedToPlay() {
        RightVUser rightVUser;
        RightVChannel rightVChannel;
        RightVUser rightVUser2;
        RightVUser rightVUser3;
        ParentalRating parentalLevel;
        RightVChannel rightVChannel2;
        LiveData<PortalApplication.Config> config;
        PortalApplication.Config value;
        Log.v(this.TAG, "Check if allowed to play channel:" + this.channel + ", user: " + this.user + ", airing: " + this.airing);
        Context context = getContext();
        RightVUser user = null;
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        if (!(applicationContext instanceof PortalApplication)) {
            applicationContext = null;
        }
        PortalApplication portalApplication = (PortalApplication) applicationContext;
        if (portalApplication != null && (config = portalApplication.getConfig()) != null && (value = config.getValue()) != null) {
            user = value.getUser();
        }
        this.user = user;
        if (user != null) {
            TVAiring tVAiring = this.airing;
            if (tVAiring != null) {
                if (tVAiring == null) {
                    Intrinsics.throwNpe();
                }
                if (tVAiring.getMetadata().getParentalLevel() != null) {
                    RightVUser rightVUser4 = this.user;
                    if (rightVUser4 == null) {
                        Intrinsics.throwNpe();
                    }
                    ParentalRating parentalLevel2 = rightVUser4.getParentalLevel();
                    TVAiring tVAiring2 = this.airing;
                    if (tVAiring2 == null) {
                        Intrinsics.throwNpe();
                    }
                    ParentalRating parentalLevel3 = tVAiring2.getMetadata().getParentalLevel();
                    if (parentalLevel3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (parentalLevel2.compareTo(parentalLevel3) < 0) {
                        return false;
                    }
                } else if (this.channel != null) {
                    String str = this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Check on channel PR ");
                    rightVChannel = this.channel;
                    if (rightVChannel == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(rightVChannel.getParentalRating());
                    sb.append(", user PR ");
                    rightVUser2 = this.user;
                    if (rightVUser2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(rightVUser2.getParentalLevel());
                    Log.v(str, sb.toString());
                    rightVUser3 = this.user;
                    if (rightVUser3 == null) {
                        Intrinsics.throwNpe();
                    }
                    parentalLevel = rightVUser3.getParentalLevel();
                    rightVChannel2 = this.channel;
                    if (rightVChannel2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (parentalLevel.compareTo(rightVChannel2.getParentalRating()) < 0) {
                        return false;
                    }
                } else {
                    rightVUser = this.user;
                    if (rightVUser == null) {
                        Intrinsics.throwNpe();
                    }
                    if (rightVUser.getParentalLevel().getLevel() != 6) {
                        return false;
                    }
                }
            } else if (this.channel != null) {
                String str2 = this.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Check on channel PR ");
                rightVChannel = this.channel;
                if (rightVChannel == null) {
                    Intrinsics.throwNpe();
                }
                sb2.append(rightVChannel.getParentalRating());
                sb2.append(", user PR ");
                rightVUser2 = this.user;
                if (rightVUser2 == null) {
                    Intrinsics.throwNpe();
                }
                sb2.append(rightVUser2.getParentalLevel());
                Log.v(str2, sb2.toString());
                rightVUser3 = this.user;
                if (rightVUser3 == null) {
                    Intrinsics.throwNpe();
                }
                parentalLevel = rightVUser3.getParentalLevel();
                rightVChannel2 = this.channel;
                if (rightVChannel2 == null) {
                    Intrinsics.throwNpe();
                }
                if (parentalLevel.compareTo(rightVChannel2.getParentalRating()) < 0) {
                    return false;
                }
            } else {
                rightVUser = this.user;
                if (rightVUser == null) {
                    Intrinsics.throwNpe();
                }
                if (rightVUser.getParentalLevel().getLevel() != 6) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: LiveCardView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/ui/cards/LiveCardView$TunerLockChangedRunnable;", "Ljava/lang/Runnable;", TvContractCompat.Channels.COLUMN_LOCKED, "", "(Lcom/dotscreen/portal/ui/cards/LiveCardView;Z)V", "run", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class TunerLockChangedRunnable implements Runnable {
        private final boolean locked;

        public TunerLockChangedRunnable(boolean z) {
            this.locked = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.locked) {
                LiveCardView.this.mVideoAvailable = false;
                LiveCardView.this.block(true, Integer.valueOf(R.string.tvview_msg_weak_signal));
            } else {
                LiveCardView.this.mVideoAvailable = true;
                Log.d(LiveCardView.this.TAG, " DVBCable - mVideoAvailable = " + LiveCardView.this.mVideoAvailable + "  | LiveCardView = " + LiveCardView.this);
                LiveCardView.block$default(LiveCardView.this, false, null, 2, null);
            }
            LiveCardView.this.stopLoading();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(this.TAG, "onAttachedToWindow");
        this.mAttachedToWindow = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Log.d(this.TAG, "onDetachedFromWindow");
        this.mAttachedToWindow = false;
        stopTune$default(this, null, 1, null);
        this.mBlockScreen.setVisibility(8);
        setErrorMessage("");
        this.blocked = false;
        super.onDetachedFromWindow();
    }

    public final AudioManager.OnAudioFocusChangeListener getAudioChangedCallback() {
        return this.audioChangedCallback;
    }
}
