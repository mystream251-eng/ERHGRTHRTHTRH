package com.dotscreen.portal.ui.screens.player;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.leanback.app.PlaybackSupportFragment;
import androidx.leanback.app.PlaybackSupportFragmentGlueHost;
import androidx.leanback.app.ProgressBarManager;
import androidx.leanback.app.RowsSupportFragment;
import androidx.leanback.media.PlaybackGlue;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.PlaybackDiplomaticoRowPresenter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.PlaybackInfoMimeType;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.analytics.AnalyticsManager;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.PlaybackReplayType;
import com.dotscreen.portal.model.PlaybackState;
import com.dotscreen.portal.model.PlayerViewModel;
import com.dotscreen.portal.player.VOPlayerAdapter;
import com.dotscreen.portal.player.VideoPlayerGlue;
import com.dotscreen.tv.common.ISmartLibProvider;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.RightVPlaybackInfo;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.DSVOPlayerError;
import com.dotscreen.tv.ui.dialog.AudioSubListDialog;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import com.google.android.exoplayer2.C;
import com.viaccessorca.drm.VOWidevineAgent;
import com.viaccessorca.voplayer.VOAudioTrack;
import com.viaccessorca.voplayer.VOPlayer;
import com.viaccessorca.voplayer.VOSubtitleTrack;
import com.viaccessorca.voplayer.VOSurfaceView;
import com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import tv.broadpeak.smartlib.SmartLib;
import tv.broadpeak.smartlib.engine.CoreEngine;

/* JADX INFO: compiled from: PlaybackFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018\u00002\u00020\u0001:\u0005jklmnB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u000200J\b\u00102\u001a\u000200H\u0002J\u000e\u00103\u001a\u0002002\u0006\u00104\u001a\u000205J\u0006\u00106\u001a\u000200J\u000e\u00107\u001a\u0002082\u0006\u00109\u001a\u000208J\u0006\u0010:\u001a\u000200J\b\u0010;\u001a\u000200H\u0002J\b\u0010<\u001a\u00020=H\u0002J\u0010\u0010>\u001a\u0002002\u0006\u0010?\u001a\u00020\u000eH\u0014J$\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\u001a\u0010H\u001a\u0002002\u0006\u0010I\u001a\u0002052\b\u0010J\u001a\u0004\u0018\u00010KH\u0014J\u0010\u0010L\u001a\u0002002\u0006\u0010M\u001a\u00020\u000eH\u0002J\b\u0010N\u001a\u000200H\u0017J\u0010\u0010O\u001a\u0002002\u0006\u0010P\u001a\u00020QH\u0002J\b\u0010R\u001a\u000200H\u0016J\b\u0010S\u001a\u000200H\u0017J\u0006\u0010T\u001a\u000200J\b\u0010U\u001a\u000200H\u0002J\u0010\u0010V\u001a\u0002002\u0006\u0010W\u001a\u00020XH\u0002J\b\u0010Y\u001a\u000200H\u0002J\u0006\u0010Z\u001a\u000200J\u0006\u0010[\u001a\u000200J\u0006\u0010\\\u001a\u000200J\b\u0010]\u001a\u000200H\u0002J\u0014\u0010^\u001a\u0002002\n\b\u0002\u0010_\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010`\u001a\u0002002\u0006\u0010a\u001a\u00020\u0004H\u0002J\u001a\u0010b\u001a\u0002002\b\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010I\u001a\u000205H\u0002J\u0010\u0010c\u001a\u0002002\b\b\u0002\u0010M\u001a\u00020\u000eJ\u0010\u0010d\u001a\u0002002\b\b\u0002\u0010e\u001a\u00020\u000eJ\u0006\u0010f\u001a\u000200J\u0006\u0010g\u001a\u000200J\b\u0010h\u001a\u000200H\u0002J\b\u0010i\u001a\u000200H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0018\u00010\u000bR\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0018\u00010\u0014R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0018\u00010 R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0018\u00010\"R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b'\u0010(R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006o"}, d2 = {"Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;", "Landroidx/leanback/app/PlaybackSupportFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "analyticsManager", "Lcom/dotscreen/farkit/analytics/AnalyticsManager;", "dialogAudio", "Lcom/dotscreen/tv/ui/dialog/AudioSubListDialog;", "imageContainer", "Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "Lcom/android/volley/toolbox/ImageLoader;", "isFirstFrameDisplayed", "", "isPlayingAds", "()Z", "setPlayingAds", "(Z)V", "playbackInfoListener", "Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment$PlaybackInfoListener;", "player", "Lcom/viaccessorca/voplayer/VOPlayer;", "getPlayer", "()Lcom/viaccessorca/voplayer/VOPlayer;", "setPlayer", "(Lcom/viaccessorca/voplayer/VOPlayer;)V", "playerAdapter", "Lcom/dotscreen/portal/player/VOPlayerAdapter;", "playerGlue", "Lcom/dotscreen/portal/player/VideoPlayerGlue;", "playerInfoListener", "Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment$PlayerInfoListener;", "playlistActionListener", "Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment$PlaylistActionListener;", "portalDialog", "Landroid/app/Dialog;", "viewModel", "Lcom/dotscreen/portal/model/PlayerViewModel;", "getViewModel", "()Lcom/dotscreen/portal/model/PlayerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "voSurfaceView", "Lcom/viaccessorca/voplayer/VOSurfaceView;", "vodActivity", "Lcom/dotscreen/portal/ui/screens/player/VODPlaybackActivity;", "adsFinished", "", "adsStarted", "askCurrentPlayedPopup", "canSkipAds", "time", "", "fastForward", "getRemainingTime", "", "currentPosition", "goToLive", "initializePlayer", "initializeRelatedVideosRow", "Landroidx/leanback/widget/ArrayObjectAdapter;", "onBufferingStateChanged", "start", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onError", "errorCode", "errorMessage", "", "onFastForwardEnded", "returnToPrevious", "onPause", "onPlaybackRequested", "pi", "Lcom/dotscreen/tv/common/bokit/RightVPlaybackInfo;", "onStart", "onStop", "pausePlayer", "play", "prepareMediaForPlaying", "mediaSourceUri", "Landroid/net/Uri;", "releasePlayer", "restartPlayer", "resumePlayer", "rewind", "saveMediaPosition", "setCustomToolboxHeader", "token", "setSubtitleWithLanguage", "sbtLanguage", "showErrorMessage", "showReturnToLive", "showStillAlive", "autoHide", "skipToNext", "skipToPrevious", "startDetailsActivity", "tickle", "ItemViewClickedListener", "PlaybackInfoListener", "PlayerCallback", "PlayerInfoListener", "PlaylistActionListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PlaybackFragment extends PlaybackSupportFragment {
    private HashMap _$_findViewCache;
    private AnalyticsManager analyticsManager;
    private AudioSubListDialog dialogAudio;
    private ImageLoader.ImageContainer imageContainer;
    private boolean isFirstFrameDisplayed;
    private boolean isPlayingAds;
    private PlaybackInfoListener playbackInfoListener;
    private VOPlayer player;
    private VOPlayerAdapter playerAdapter;
    private VideoPlayerGlue playerGlue;
    private PlayerInfoListener playerInfoListener;
    private PlaylistActionListener playlistActionListener;
    private Dialog portalDialog;
    private VOSurfaceView voSurfaceView;
    private VODPlaybackActivity vodActivity;
    private final String TAG = PlaybackFragment.class.getSimpleName();

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PlayerViewModel.class), new Function0<ViewModelStore>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$$special$$inlined$activityViewModels$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
            ViewModelStore viewModelStore = fragmentActivityRequireActivity.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "requireActivity().viewModelStore");
            return viewModelStore;
        }
    }, (Function0) null);

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PlaybackReplayType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PlaybackReplayType.CATCHUP.ordinal()] = 1;
            $EnumSwitchMapping$0[PlaybackReplayType.STOV.ordinal()] = 2;
            int[] iArr2 = new int[PlaybackReplayType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[PlaybackReplayType.STOV.ordinal()] = 1;
            $EnumSwitchMapping$1[PlaybackReplayType.CATCHUP.ordinal()] = 2;
        }
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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public final PlayerViewModel getViewModel() {
        return (PlayerViewModel) this.viewModel.getValue();
    }

    @Override // androidx.leanback.app.PlaybackSupportFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final VOPlayer getPlayer() {
        return this.player;
    }

    public final void setPlayer(VOPlayer vOPlayer) {
        this.player = vOPlayer;
    }

    /* JADX INFO: renamed from: isPlayingAds, reason: from getter */
    public final boolean getIsPlayingAds() {
        return this.isPlayingAds;
    }

    public final void setPlayingAds(boolean z) {
        this.isPlayingAds = z;
    }

    @Override // androidx.leanback.app.PlaybackSupportFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        if (viewOnCreateView == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        ViewGroup viewGroup = (ViewGroup) viewOnCreateView;
        Log.v(this.TAG, "onCreateView: " + this);
        DSLog dSLog = DSLog.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this.javaClass.simpleName");
        dSLog.s(simpleName);
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.view_vo_surface, viewGroup, false);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.viaccessorca.voplayer.VOSurfaceView");
        }
        VOSurfaceView vOSurfaceView = (VOSurfaceView) viewInflate;
        this.voSurfaceView = vOSurfaceView;
        viewGroup.addView(vOSurfaceView, 0);
        setBackgroundType(0);
        return viewGroup;
    }

    @Override // androidx.leanback.app.PlaybackSupportFragment, androidx.fragment.app.Fragment
    public void onStart() {
        VerticalGridView verticalGridView;
        super.onStart();
        Log.d(this.TAG, "onStart(contentId): " + getViewModel().getCurrentContentIdLiveData().getValue());
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.player.VODPlaybackActivity");
        }
        this.vodActivity = (VODPlaybackActivity) activity;
        getViewModel().getCurrentErrorLiveData().observe(this, new Observer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.onStart.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Throwable th) {
                Log.e(PlaybackFragment.this.TAG, "A error happens", th);
                VODPlaybackActivity vODPlaybackActivity = PlaybackFragment.this.vodActivity;
                if (vODPlaybackActivity != null) {
                    vODPlaybackActivity.onError(th);
                }
            }
        });
        RowsSupportFragment rowsSupportFragment = (RowsSupportFragment) getChildFragmentManager().findFragmentById(R.id.playback_controls_dock);
        if (rowsSupportFragment != null && (verticalGridView = rowsSupportFragment.getVerticalGridView()) != null) {
            verticalGridView.setAnimateChildLayout(false);
        }
        if (this.player == null) {
            Log.e(this.TAG, "onStart: here player " + this);
            initializePlayer();
            Context context = getContext();
            Context applicationContext = context != null ? context.getApplicationContext() : null;
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            this.analyticsManager = ((PortalApplication) applicationContext).getAnalyticsManager();
        }
    }

    public final void adsStarted() {
        Log.d(this.TAG, "MEDIA_INFO_VAST_ADVERTISEMENT");
        getViewModel().setAdsState(true);
        this.isPlayingAds = true;
    }

    public final void canSkipAds(int time) {
        Log.d(this.TAG, "MEDIA_INFO_SKIP IN " + time);
        getViewModel().setCanSkipState(time);
    }

    public final void adsFinished() {
        Log.d(this.TAG, "MEDIA_INFO_VAST_NOT_ADVERTISEMENT");
        getViewModel().setAdsState(false);
        this.isPlayingAds = false;
    }

    public final void pausePlayer() {
        VOPlayer vOPlayer = this.player;
        if (vOPlayer != null) {
            vOPlayer.setVideoView(null);
        }
        VOPlayer vOPlayer2 = this.player;
        if (vOPlayer2 != null) {
            vOPlayer2.pause();
        }
    }

    public final void resumePlayer() {
        VOPlayer vOPlayer = this.player;
        if (vOPlayer != null) {
            vOPlayer.setVideoView(this.voSurfaceView);
        }
        tickle();
    }

    @Override // androidx.leanback.app.PlaybackSupportFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        saveMediaPosition();
    }

    @Override // androidx.leanback.app.PlaybackSupportFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Log.e(this.TAG, "onStop: here " + this);
        AnalyticsManager analyticsManager = this.analyticsManager;
        if (analyticsManager != null) {
            VOPlayer vOPlayer = this.player;
            analyticsManager.onClose(vOPlayer != null ? vOPlayer.getCurrentPosition() : -1);
        }
        releasePlayer();
        ImageLoader.ImageContainer imageContainer = this.imageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
        }
        this.imageContainer = (ImageLoader.ImageContainer) null;
        if (!getViewModel().getPlayingTrailer() && getViewModel().getCurrentMediaLiveData().getValue() != null && (getViewModel().getCurrentMediaLiveData().getValue() instanceof Media)) {
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            UserService userService = companion.getSingleton(context).getBOService().getUserService();
            MegacableUserService megacableUserService = (MegacableUserService) (userService instanceof MegacableUserService ? userService : null);
            if (megacableUserService == null) {
                Intrinsics.throwNpe();
            }
            BrowsableItem value = getViewModel().getCurrentMediaLiveData().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            if (value == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Media");
            }
            Intrinsics.checkExpressionValueIsNotNull(megacableUserService.closeVODPlaybackSession((Media) value).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.onStop.1
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Log.d(PlaybackFragment.this.TAG, "closeVODPlaybackSession done !");
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.onStop.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.w(PlaybackFragment.this.TAG, "closeVODPlaybackSession error:", th);
                    PlaybackFragment.this.vodActivity = (VODPlaybackActivity) null;
                }
            }), "userService.closeVODPlay…= null\n                })");
            return;
        }
        this.vodActivity = (VODPlaybackActivity) null;
    }

    private final void saveMediaPosition() {
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        if (videoPlayerGlue != null) {
            getViewModel().saveMediaPosition(videoPlayerGlue.getCurrentPosition(), videoPlayerGlue.getDuration());
        }
    }

    private final void initializePlayer() {
        VideoPlayerGlue videoPlayerGlue;
        VOPlayer vOPlayer;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        Object applicationContext = activity.getApplicationContext();
        if (!(applicationContext instanceof ISmartLibProvider)) {
            applicationContext = null;
        }
        ISmartLibProvider iSmartLibProvider = (ISmartLibProvider) applicationContext;
        if (iSmartLibProvider != null) {
            iSmartLibProvider.reinitSmartLibIfNeeded("Smartlib not init at player start");
            Object smartLibCoreEngine = iSmartLibProvider.getSmartLibCoreEngine();
            if (!(smartLibCoreEngine instanceof CoreEngine)) {
                smartLibCoreEngine = null;
            }
            CoreEngine coreEngine = (CoreEngine) smartLibCoreEngine;
            if (coreEngine != null) {
                CoreEngine.f187a = coreEngine;
            }
            Object smartLib = iSmartLibProvider.getSmartLib();
            SmartLib smartLib2 = (SmartLib) (smartLib instanceof SmartLib ? smartLib : null);
            if (smartLib2 != null) {
                SmartLib.f176a = smartLib2;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Is smartlib initialised = ");
        CoreEngine coreEngine2 = CoreEngine.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(coreEngine2, "CoreEngine.getInstance()");
        sb.append(coreEngine2.isInitialized());
        Log.e("SmartLib", sb.toString());
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity2, "activity!!");
        VOPlayerBpkExtended vOPlayerBpkExtended = new VOPlayerBpkExtended(activity2.getApplicationContext());
        this.player = vOPlayerBpkExtended;
        if (vOPlayerBpkExtended != null) {
            vOPlayerBpkExtended.setDebugOption(VOPlayer.DebugOption.DBG_OPT_SHOW_PLAYER_INFO, false);
        }
        VOPlayer vOPlayer2 = this.player;
        if (vOPlayer2 != null) {
            vOPlayer2.setDebugOption(VOPlayer.DebugOption.DBG_OPT_FORCE_MIN_INFO, true);
        }
        if (getViewModel().getReplayType() == PlaybackReplayType.STOV && (vOPlayer = this.player) != null) {
            vOPlayer.setHttpStreamingEventPlaylistEnabled(true);
        }
        VOPlayer vOPlayer3 = this.player;
        if (vOPlayer3 != null) {
            vOPlayer3.setScreenOnWhilePlaying(true);
        }
        VOPlayer vOPlayer4 = this.player;
        if (vOPlayer4 != null) {
            vOPlayer4.enableSubtitlesOSD(true);
        }
        VOPlayer vOPlayer5 = this.player;
        if (vOPlayer5 != null) {
            vOPlayer5.setVideoView(this.voSurfaceView);
        }
        VOSurfaceView vOSurfaceView = this.voSurfaceView;
        if (vOSurfaceView != null) {
            vOSurfaceView.setSubtitleCustomCSSStyling(".text-sub { color: white !important;font-size: x-large !important;-webkit-text-stroke: 1px black !important;font-family: Arial, Helvetica, sans-serif !important;font-weight: bold !important;height: auto !important;} .text-webvtt { background-color:transparent;}");
        }
        this.playerInfoListener = new PlayerInfoListener();
        final String value = getViewModel().getCurrentAudioLiveData().getValue();
        final String value2 = getViewModel().getCurrentSubtitleLiveData().getValue();
        FragmentActivity activity3 = getActivity();
        if (activity3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity3, "activity!!");
        FragmentActivity fragmentActivity = activity3;
        VOPlayer vOPlayer6 = this.player;
        if (vOPlayer6 == null) {
            Intrinsics.throwNpe();
        }
        this.playerAdapter = new VOPlayerAdapter(fragmentActivity, vOPlayer6, value, value2, this.playerInfoListener, this) { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.initializePlayer.2
            @Override // com.dotscreen.portal.player.VOPlayerAdapter
            protected String getErrorMessage(int error, int extra) {
                return String.valueOf(extra);
            }
        };
        this.playlistActionListener = new PlaylistActionListener();
        this.playbackInfoListener = new PlaybackInfoListener();
        FragmentActivity activity4 = getActivity();
        if (activity4 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity4, "activity!!");
        FragmentActivity fragmentActivity2 = activity4;
        VOPlayerAdapter vOPlayerAdapter = this.playerAdapter;
        if (vOPlayerAdapter == null) {
            Intrinsics.throwNpe();
        }
        PlaylistActionListener playlistActionListener = this.playlistActionListener;
        if (playlistActionListener == null) {
            Intrinsics.throwNpe();
        }
        PlaylistActionListener playlistActionListener2 = playlistActionListener;
        PlaybackInfoListener playbackInfoListener = this.playbackInfoListener;
        if (playbackInfoListener == null) {
            Intrinsics.throwNpe();
        }
        VideoPlayerGlue videoPlayerGlue2 = new VideoPlayerGlue(fragmentActivity2, vOPlayerAdapter, playlistActionListener2, playbackInfoListener);
        this.playerGlue = videoPlayerGlue2;
        if (videoPlayerGlue2 != null) {
            videoPlayerGlue2.setHost(new PlaybackSupportFragmentGlueHost(this));
        }
        VideoPlayerGlue videoPlayerGlue3 = this.playerGlue;
        if (videoPlayerGlue3 != null) {
            videoPlayerGlue3.setPlaybackRowPresenter(new PlaybackDiplomaticoRowPresenter());
        }
        Integer launchMode = getViewModel().getLaunchMode();
        if (getViewModel().getReplayType() == PlaybackReplayType.STOV && getContext() != null) {
            if (launchMode != null && (launchMode.intValue() == 85 || launchMode.intValue() == 127 || launchMode.intValue() == 89)) {
                VideoPlayerGlue videoPlayerGlue4 = this.playerGlue;
                if (videoPlayerGlue4 != null) {
                    videoPlayerGlue4.fastForwardEnded(new VideoPlayerGlue.OnFastForwardEndedListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.initializePlayer.3
                        @Override // com.dotscreen.portal.player.VideoPlayerGlue.OnFastForwardEndedListener
                        public void onFastForwardEnded() {
                            Log.v(PlaybackFragment.this.TAG, "PlayerViewModel will try to load next airing, playbackfragment will wait end of loading #1");
                            PlaybackFragment.this.onFastForwardEnded(false);
                        }
                    });
                }
            } else if (getViewModel().getChannelId() != null && (videoPlayerGlue = this.playerGlue) != null) {
                videoPlayerGlue.fastForwardEnded(new VideoPlayerGlue.OnFastForwardEndedListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.initializePlayer.4
                    @Override // com.dotscreen.portal.player.VideoPlayerGlue.OnFastForwardEndedListener
                    public void onFastForwardEnded() {
                        Log.v(PlaybackFragment.this.TAG, "PlayerViewModel will try to load next airing, playbackfragment will wait end of loading #2");
                        PlaybackFragment.this.onFastForwardEnded(true);
                    }
                });
            }
        }
        this.isFirstFrameDisplayed = false;
        VideoPlayerGlue videoPlayerGlue5 = this.playerGlue;
        if (videoPlayerGlue5 != null) {
            videoPlayerGlue5.playWhenPrepared();
        }
        VideoPlayerGlue videoPlayerGlue6 = this.playerGlue;
        if (videoPlayerGlue6 != null) {
            videoPlayerGlue6.addPlayerCallback(new PlayerCallback());
        }
        play();
        setAdapter(initializeRelatedVideosRow());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFastForwardEnded(final boolean returnToPrevious) {
        getViewModel().loadNextAiring();
        getViewModel().getNextAiringLoading().observe(this, new Observer<Boolean>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.onFastForwardEnded.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                if (bool.booleanValue() || PlaybackFragment.this.getViewModel().getNextTVAiringTimeStamp() != null) {
                    return;
                }
                PlaybackFragment.this.showReturnToLive(returnToPrevious);
            }
        });
    }

    static /* synthetic */ void setCustomToolboxHeader$default(PlaybackFragment playbackFragment, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        playbackFragment.setCustomToolboxHeader(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCustomToolboxHeader(String token) {
        VOWidevineAgent agentWidevine;
        Object[] array = StringsKt.split$default((CharSequence) ("dt-custom-data:#b64{tokenID=" + token + "|keyid=${KEYID}|operatorID=megacable|countryID=mx}#b64"), new String[]{";"}, false, 0, 6, (Object) null).toArray(new String[0]);
        if (array != null) {
            String[] strArr = (String[]) array;
            VOPlayer vOPlayer = this.player;
            if (vOPlayer == null || (agentWidevine = vOPlayer.getAgentWidevine()) == null) {
                return;
            }
            agentWidevine.setLicenseAcquisitionCustomHeaders(strArr);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void releasePlayer() {
        VOPlayer vOPlayer;
        VOPlayer vOPlayer2 = this.player;
        if ((vOPlayer2 != null ? vOPlayer2.getState() : null) != VOPlayer.PlayerState.RELEASED && (vOPlayer = this.player) != null) {
            vOPlayer.release();
        }
        this.player = (VOPlayer) null;
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        if (videoPlayerGlue != null) {
            videoPlayerGlue.fastForwardEnded(null);
        }
        this.playerGlue = (VideoPlayerGlue) null;
        this.playerAdapter = (VOPlayerAdapter) null;
        this.playlistActionListener = (PlaylistActionListener) null;
    }

    private final void play() {
        ProgressBarManager progressBarManager = getProgressBarManager();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        progressBarManager.setProgressBarView(activity.findViewById(R.id.loaderProgress));
        getProgressBarManager().show();
        Completable completableCreate = Completable.create(new CompletableOnSubscribe() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1

            /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: PlaybackFragment.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
            @DebugMetadata(c = "com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1$1", f = "PlaybackFragment.kt", i = {0, 0}, l = {366}, m = "invokeSuspend", n = {"$this$launch", "loop"}, s = {"L$0", "I$0"})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CompletableEmitter $emitter;
                int I$0;
                Object L$0;
                int label;
                private CoroutineScope p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(CompletableEmitter completableEmitter, Continuation continuation) {
                    super(2, continuation);
                    this.$emitter = completableEmitter;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = PlaybackFragment$play$waitForSessionObs$1.this.new AnonymousClass1(this.$emitter, completion);
                    anonymousClass1.p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:12:0x0032 A[Catch: all -> 0x008e, TryCatch #0 {all -> 0x008e, blocks: (B:10:0x0028, B:12:0x0032, B:15:0x003d, B:17:0x0065, B:18:0x0073, B:19:0x0086, B:20:0x008d), top: B:31:0x0028 }] */
                /* JADX WARN: Code duplicated, block: B:13:0x003a  */
                /* JADX WARN: Code duplicated, block: B:15:0x003d A[Catch: all -> 0x008e, TryCatch #0 {all -> 0x008e, blocks: (B:10:0x0028, B:12:0x0032, B:15:0x003d, B:17:0x0065, B:18:0x0073, B:19:0x0086, B:20:0x008d), top: B:31:0x0028 }] */
                /* JADX WARN: Code duplicated, block: B:17:0x0065 A[Catch: all -> 0x008e, TryCatch #0 {all -> 0x008e, blocks: (B:10:0x0028, B:12:0x0032, B:15:0x003d, B:17:0x0065, B:18:0x0073, B:19:0x0086, B:20:0x008d), top: B:31:0x0028 }] */
                /* JADX WARN: Code duplicated, block: B:18:0x0073 A[Catch: all -> 0x008e, TryCatch #0 {all -> 0x008e, blocks: (B:10:0x0028, B:12:0x0032, B:15:0x003d, B:17:0x0065, B:18:0x0073, B:19:0x0086, B:20:0x008d), top: B:31:0x0028 }] */
                /* JADX WARN: Code duplicated, block: B:25:0x0095  */
                /* JADX WARN: Code duplicated, block: B:27:0x00a3 A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:29:0x00a6  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00a1 -> B:28:0x00a4). Please report as a decompilation issue!!! */
                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:13:0x003a
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                    /*
                        r9 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r9.label
                        r2 = 1
                        if (r1 == 0) goto L1f
                        if (r1 != r2) goto L17
                        int r1 = r9.I$0
                        java.lang.Object r3 = r9.L$0
                        kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
                        kotlin.ResultKt.throwOnFailure(r10)
                        r10 = r9
                        goto La4
                    L17:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r0)
                        throw r10
                    L1f:
                        kotlin.ResultKt.throwOnFailure(r10)
                        kotlinx.coroutines.CoroutineScope r10 = r9.p$
                        r3 = r10
                        r1 = r2
                        r10 = r9
                    L27:
                        r4 = 0
                        com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1 r5 = com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1.this     // Catch: java.lang.Throwable -> L8e
                        com.dotscreen.portal.ui.screens.player.PlaybackFragment r5 = r5.this$0     // Catch: java.lang.Throwable -> L8e
                        android.content.Context r5 = r5.getContext()     // Catch: java.lang.Throwable -> L8e
                        if (r5 == 0) goto L3a
                        java.lang.String r6 = "tv_input"
                        java.lang.Object r5 = r5.getSystemService(r6)     // Catch: java.lang.Throwable -> L8e
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        if (r5 == 0) goto L86
                        android.media.tv.TvInputManager r5 = (android.media.tv.TvInputManager) r5     // Catch: java.lang.Throwable -> L8e
                        com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1 r6 = com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1.this     // Catch: java.lang.Throwable -> L8e
                        com.dotscreen.portal.ui.screens.player.PlaybackFragment r6 = r6.this$0     // Catch: java.lang.Throwable -> L8e
                        java.lang.String r6 = com.dotscreen.portal.ui.screens.player.PlaybackFragment.access$getTAG$p(r6)     // Catch: java.lang.Throwable -> L8e
                        java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e
                        r7.<init>()     // Catch: java.lang.Throwable -> L8e
                        java.lang.String r8 = "isSingleSessionActive = "
                        r7.append(r8)     // Catch: java.lang.Throwable -> L8e
                        boolean r8 = r5.isSingleSessionActive()     // Catch: java.lang.Throwable -> L8e
                        r7.append(r8)     // Catch: java.lang.Throwable -> L8e
                        java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L8e
                        android.util.Log.d(r6, r7)     // Catch: java.lang.Throwable -> L8e
                        boolean r5 = r5.isSingleSessionActive()     // Catch: java.lang.Throwable -> L8e
                        if (r5 == 0) goto L73
                        com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1 r5 = com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1.this     // Catch: java.lang.Throwable -> L8e
                        com.dotscreen.portal.ui.screens.player.PlaybackFragment r5 = r5.this$0     // Catch: java.lang.Throwable -> L8e
                        java.lang.String r5 = com.dotscreen.portal.ui.screens.player.PlaybackFragment.access$getTAG$p(r5)     // Catch: java.lang.Throwable -> L8e
                        java.lang.String r6 = "not ready to start playback"
                        android.util.Log.d(r5, r6)     // Catch: java.lang.Throwable -> L8e
                        goto L93
                    L73:
                        com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1 r1 = com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1.this     // Catch: java.lang.Throwable -> L8e
                        com.dotscreen.portal.ui.screens.player.PlaybackFragment r1 = r1.this$0     // Catch: java.lang.Throwable -> L8e
                        java.lang.String r1 = com.dotscreen.portal.ui.screens.player.PlaybackFragment.access$getTAG$p(r1)     // Catch: java.lang.Throwable -> L8e
                        java.lang.String r5 = "no active session -> start playback"
                        android.util.Log.d(r1, r5)     // Catch: java.lang.Throwable -> L8e
                        io.reactivex.CompletableEmitter r1 = r10.$emitter     // Catch: java.lang.Throwable -> L8e
                        r1.onComplete()     // Catch: java.lang.Throwable -> L8e
                        goto L92
                    L86:
                        kotlin.TypeCastException r1 = new kotlin.TypeCastException     // Catch: java.lang.Throwable -> L8e
                        java.lang.String r5 = "null cannot be cast to non-null type android.media.tv.TvInputManager"
                        r1.<init>(r5)     // Catch: java.lang.Throwable -> L8e
                        throw r1     // Catch: java.lang.Throwable -> L8e
                    L8e:
                        r1 = move-exception
                        r1.printStackTrace()
                    L92:
                        r1 = r4
                    L93:
                        if (r1 == 0) goto La4
                        r4 = 500(0x1f4, double:2.47E-321)
                        r10.L$0 = r3
                        r10.I$0 = r1
                        r10.label = r2
                        java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r10)
                        if (r4 != r0) goto La4
                        return r0
                    La4:
                        if (r1 != 0) goto L27
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$waitForSessionObs$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter emitter) {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(emitter, null), 2, null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableCreate, "Completable.create { emi…)\n            }\n        }");
        completableCreate.subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.play.1
            @Override // io.reactivex.functions.Action
            public final void run() {
                PlaybackFragment.this.getViewModel().loadMedia();
            }
        });
        PlaybackFragment playbackFragment = this;
        getViewModel().getCurrentMediaLiveData().removeObservers(playbackFragment);
        getViewModel().getCurrentPlaybackInfoLiveData().removeObservers(playbackFragment);
        getViewModel().getEpgDurationLiveData().removeObservers(playbackFragment);
        getViewModel().getTrailerTitleLiveData().removeObservers(playbackFragment);
        getViewModel().getCurrentMediaLiveData().observe(playbackFragment, new C04162());
        getViewModel().getCurrentPlaybackInfoLiveData().observe(playbackFragment, new Observer<RightVPlaybackInfo>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.play.3
            /* JADX WARN: Code duplicated, block: B:28:0x00a6  */
            /* JADX WARN: Code duplicated, block: B:30:0x00b2  */
            /* JADX WARN: Code duplicated, block: B:32:0x00b5  */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(RightVPlaybackInfo rightVPlaybackInfo) throws IOException {
                VOPlayer player;
                VOPlayer player2;
                VOPlayer player3;
                VOWidevineAgent agentWidevine;
                VOWidevineAgent agentWidevine2;
                VOWidevineAgent agentWidevine3;
                Long watchedTime;
                VOWidevineAgent agentWidevine4;
                VOWidevineAgent agentWidevine5;
                VOWidevineAgent agentWidevine6;
                Log.v(PlaybackFragment.this.TAG, "Receive new PI (" + rightVPlaybackInfo + ") from view model");
                if (rightVPlaybackInfo != null) {
                    VideoPlayerGlue videoPlayerGlue = PlaybackFragment.this.playerGlue;
                    if (videoPlayerGlue != null) {
                        PlaybackFragment.this.onPlaybackRequested(rightVPlaybackInfo);
                        String casToken = rightVPlaybackInfo.getCasToken();
                        if (casToken == null) {
                            if (rightVPlaybackInfo.getLicenceUrl().length() > 0) {
                                player = PlaybackFragment.this.getPlayer();
                                if (player != null && (agentWidevine3 = player.getAgentWidevine()) != null) {
                                    agentWidevine3.setLicenseAcquisitionURL(rightVPlaybackInfo.getLicenceUrl());
                                }
                                player2 = PlaybackFragment.this.getPlayer();
                                if (player2 != null && (agentWidevine2 = player2.getAgentWidevine()) != null) {
                                    agentWidevine2.setLicenseAcquisitionType(VOWidevineAgent.EDrmLicenseAcquisitionType.PERSISTENT);
                                }
                                player3 = PlaybackFragment.this.getPlayer();
                                if (player3 != null && (agentWidevine = player3.getAgentWidevine()) != null) {
                                    agentWidevine.setLicenseValidityThreshold(28800L);
                                }
                            }
                        } else {
                            if (casToken.length() > 0) {
                                PlaybackFragment.this.setCustomToolboxHeader(rightVPlaybackInfo.getCasToken());
                                VOPlayer player4 = PlaybackFragment.this.getPlayer();
                                if (player4 != null && (agentWidevine6 = player4.getAgentWidevine()) != null) {
                                    agentWidevine6.setLicenseAcquisitionURL(rightVPlaybackInfo.getLicenceUrl() + "?token=" + URLEncoder.encode(rightVPlaybackInfo.getCasToken(), "Utf-8"));
                                }
                                VOPlayer player5 = PlaybackFragment.this.getPlayer();
                                if (player5 != null && (agentWidevine5 = player5.getAgentWidevine()) != null) {
                                    agentWidevine5.setLicenseAcquisitionType(VOWidevineAgent.EDrmLicenseAcquisitionType.PERSISTENT);
                                }
                                VOPlayer player6 = PlaybackFragment.this.getPlayer();
                                if (player6 != null && (agentWidevine4 = player6.getAgentWidevine()) != null) {
                                    agentWidevine4.setLicenseValidityThreshold(28800L);
                                }
                            } else {
                                if (rightVPlaybackInfo.getLicenceUrl().length() > 0) {
                                    player = PlaybackFragment.this.getPlayer();
                                    if (player != null) {
                                        agentWidevine3.setLicenseAcquisitionURL(rightVPlaybackInfo.getLicenceUrl());
                                    }
                                    player2 = PlaybackFragment.this.getPlayer();
                                    if (player2 != null) {
                                        agentWidevine2.setLicenseAcquisitionType(VOWidevineAgent.EDrmLicenseAcquisitionType.PERSISTENT);
                                    }
                                    player3 = PlaybackFragment.this.getPlayer();
                                    if (player3 != null) {
                                        agentWidevine.setLicenseValidityThreshold(28800L);
                                    }
                                }
                            }
                        }
                        PlaybackFragment playbackFragment2 = PlaybackFragment.this;
                        Uri uri = Uri.parse(rightVPlaybackInfo.getUrl());
                        Intrinsics.checkExpressionValueIsNotNull(uri, "Uri.parse(pi.url)");
                        playbackFragment2.prepareMediaForPlaying(uri);
                        if (PlaybackFragment.this.getViewModel().getResume() && (watchedTime = rightVPlaybackInfo.getWatchedTime()) != null) {
                            long jLongValue = watchedTime.longValue();
                            if (jLongValue > 0) {
                                videoPlayerGlue.seekTo(jLongValue);
                            }
                        }
                        PlaybackReplayType replayType = PlaybackFragment.this.getViewModel().getReplayType();
                        Integer launchMode = PlaybackFragment.this.getViewModel().getLaunchMode();
                        if (replayType == PlaybackReplayType.STOV && (launchMode == null || launchMode.intValue() == 89)) {
                            videoPlayerGlue.seekTo(0L);
                        }
                        if (replayType == PlaybackReplayType.STOV && launchMode != null) {
                            if (launchMode.intValue() != 85 && launchMode.intValue() != 127) {
                                videoPlayerGlue.play();
                            }
                        } else {
                            videoPlayerGlue.play();
                        }
                    }
                    PlaybackFragment.this.getViewModel().invalidateCurrentPlaybackInfo();
                }
            }
        });
        getViewModel().getEpgDurationLiveData().observe(playbackFragment, new Observer<Long>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.play.4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Long it) {
                VideoPlayerGlue videoPlayerGlue = PlaybackFragment.this.playerGlue;
                if (videoPlayerGlue != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    videoPlayerGlue.setEPGDuration(it.longValue());
                }
            }
        });
        getViewModel().getTrailerTitleLiveData().observe(playbackFragment, new Observer<String>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.play.5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                VideoPlayerGlue videoPlayerGlue = PlaybackFragment.this.playerGlue;
                if (videoPlayerGlue != null) {
                    videoPlayerGlue.setTitle(str);
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaybackFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "item", "Lcom/dotscreen/bokit/model/BrowsableItem;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 1, 16})
    static final class C04162<T> implements Observer<BrowsableItem> {
        C04162() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(final BrowsableItem browsableItem) {
            String title;
            Log.v(PlaybackFragment.this.TAG, "Update displayed data with " + browsableItem);
            if (PlaybackFragment.this.playerGlue == null) {
                Log.w(PlaybackFragment.this.TAG, "Playback Glue error");
                VODPlaybackActivity vODPlaybackActivity = PlaybackFragment.this.vodActivity;
                if (vODPlaybackActivity != null) {
                    vODPlaybackActivity.onError(new Exception("Playback Glue error"));
                    return;
                }
                return;
            }
            final VideoPlayerGlue videoPlayerGlue = PlaybackFragment.this.playerGlue;
            if (videoPlayerGlue == null) {
                Intrinsics.throwNpe();
            }
            videoPlayerGlue.setTitle(browsableItem.getTitle());
            String string = "";
            MediaMetadata metadata = (MediaMetadata) null;
            if (browsableItem instanceof Movie) {
                metadata = ((Movie) browsableItem).getMetadata();
            } else if (browsableItem instanceof Episode) {
                metadata = ((Episode) browsableItem).getMetadata();
            }
            if (metadata != null) {
                if (!(metadata.getGenres().length == 0)) {
                    string = StringsKt.isBlank("") ^ true ? " | " : "";
                    for (String str : metadata.getGenres()) {
                        string = string + str;
                        if (!Intrinsics.areEqual((String) ArraysKt.last(metadata.getGenres()), str)) {
                            string = string + " | ";
                        }
                    }
                }
                Integer durationInMinutes = metadata.getDurationInMinutes();
                if ((durationInMinutes != null ? durationInMinutes.intValue() : 0) > 0) {
                    string = string + " | " + String.valueOf(metadata.getDurationInMinutes()) + "min";
                }
                ParentalRating parentalLevel = metadata.getParentalLevel();
                if (parentalLevel == null || (title = parentalLevel.getTitle()) == null) {
                    title = "NR";
                }
                if (!Intrinsics.areEqual("NR", title)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(" | CLASIFICACIÓN ");
                    ParentalRating parentalLevel2 = metadata.getParentalLevel();
                    sb.append(parentalLevel2 != null ? parentalLevel2.getTitle() : null);
                    string = sb.toString();
                }
            }
            videoPlayerGlue.setSubtitle(string);
            videoPlayerGlue.setBody(browsableItem.getSummary());
            videoPlayerGlue.setSeekEnabled(true);
            ImageLoader.ImageListener imageListener = new ImageLoader.ImageListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$play$2$$special$$inlined$with$lambda$1
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError error) {
                }

                @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                public void onResponse(ImageLoader.ImageContainer response, boolean z) {
                    Intrinsics.checkParameterIsNotNull(response, "response");
                    videoPlayerGlue.setArt(new BitmapDrawable(PlaybackFragment.this.getResources(), response.getBitmap()));
                }
            };
            if (browsableItem.getStillArtwork() != null) {
                PlaybackFragment playbackFragment = PlaybackFragment.this;
                PortalApplication.Companion companion = PortalApplication.INSTANCE;
                Context context = videoPlayerGlue.getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                ImageLoader imageLoader = companion.getSingleton(context).getImageLoader();
                Artwork stillArtwork = browsableItem.getStillArtwork();
                URL urlUrlWithSize$default = stillArtwork != null ? Artwork.urlWithSize$default(stillArtwork, new Point(PlaybackFragment.this.getResources().getDimensionPixelSize(R.dimen.player_content_still_width), PlaybackFragment.this.getResources().getDimensionPixelSize(R.dimen.player_content_still_height)), 0.0d, 2, null) : null;
                if (urlUrlWithSize$default == null) {
                    Intrinsics.throwNpe();
                }
                playbackFragment.imageContainer = imageLoader.get(urlUrlWithSize$default.toString(), imageListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPlaybackRequested(RightVPlaybackInfo pi) {
        String value = getViewModel().getCurrentContentIdLiveData().getValue();
        BrowsableItem value2 = getViewModel().getCurrentMediaLiveData().getValue();
        String variant = pi.getVariant();
        PlaybackInfoMimeType playbackInfoMimeType = (variant.hashCode() == 3075986 && variant.equals("dash")) ? PlaybackInfoMimeType.DASH : PlaybackInfoMimeType.DEFAULT;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("toolboxContent", Boolean.valueOf(pi.getIsToolboxContent()));
        String casToken = pi.getCasToken();
        if (casToken != null) {
            linkedHashMap.put("hbo", casToken);
        }
        AnalyticsManager analyticsManager = this.analyticsManager;
        if (analyticsManager != null) {
            analyticsManager.onPlaybackRequested(value, pi.getUrl(), playbackInfoMimeType.getMime(), "com.widevine.alpha", value2, linkedHashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:12:0x0026  */
    /* JADX WARN: Code duplicated, block: B:14:0x002a  */
    public final void prepareMediaForPlaying(Uri mediaSourceUri) throws IOException {
        VOPlayer vOPlayer;
        CoreEngine coreEngine = CoreEngine.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(coreEngine, "CoreEngine.getInstance()");
        if (coreEngine.isInitialized()) {
            VOPlayer vOPlayer2 = this.player;
            if (vOPlayer2 instanceof VOPlayerBpkExtended) {
                if (!(vOPlayer2 instanceof VOPlayerBpkExtended)) {
                    vOPlayer2 = null;
                }
                VOPlayerBpkExtended vOPlayerBpkExtended = (VOPlayerBpkExtended) vOPlayer2;
                if (vOPlayerBpkExtended != null) {
                    vOPlayerBpkExtended.setDataSource(mediaSourceUri.toString());
                }
            } else {
                vOPlayer = this.player;
                if (vOPlayer != null) {
                    vOPlayer.setDataSource(getContext(), mediaSourceUri);
                }
            }
        } else {
            vOPlayer = this.player;
            if (vOPlayer != null) {
                vOPlayer.setDataSource(getContext(), mediaSourceUri);
            }
        }
        VOPlayer vOPlayer3 = this.player;
        if (vOPlayer3 != null) {
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            UserService userService = companion.getSingleton(context).getBOService().getUserService();
            if (userService != null) {
                vOPlayer3.setAnalyticsMetadata("SERIAL_NUMBER_ID", ((MegacableUserService) userService).getSerialNumber());
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
        }
        VOPlayerAdapter vOPlayerAdapter = this.playerAdapter;
        if (vOPlayerAdapter != null) {
            vOPlayerAdapter.setPlayerIsPrepared(false);
        }
        VOPlayer vOPlayer4 = this.player;
        if (vOPlayer4 != null) {
            String value = getViewModel().getCurrentAudioLiveData().getValue();
            if (value == null) {
                value = "es";
            }
            vOPlayer4.setAudioTrack(value);
        }
        VOPlayer.AbrControls abrControls = new VOPlayer.AbrControls();
        abrControls.m_uiLiveTimelineDiscontinuityPolicy = 1;
        try {
            VOPlayer vOPlayer5 = this.player;
            if (vOPlayer5 == null) {
                Intrinsics.throwNpe();
            }
            vOPlayer5.setHttpStreamingAbrControlSettings(abrControls);
        } catch (IllegalArgumentException unused) {
            Log.w(this.TAG, "ABR controls are not in range : " + abrControls);
        } catch (UnsupportedOperationException unused2) {
            Log.w(this.TAG, "ABR control feature not available");
        }
        VOPlayer vOPlayer6 = this.player;
        if (vOPlayer6 != null) {
            vOPlayer6.prepareAsync();
        }
    }

    public final void restartPlayer() {
        VOPlayer vOPlayer = this.player;
        if (vOPlayer != null) {
            vOPlayer.reset();
        }
        VOPlayer vOPlayer2 = this.player;
        if (vOPlayer2 != null) {
            vOPlayer2.setVideoView(this.voSurfaceView);
        }
        play();
    }

    private final ArrayObjectAdapter initializeRelatedVideosRow() {
        ClassPresenterSelector classPresenterSelector = new ClassPresenterSelector();
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        if (videoPlayerGlue == null) {
            Intrinsics.throwNpe();
        }
        Class<?> cls = videoPlayerGlue.getControlsRow().getClass();
        VideoPlayerGlue videoPlayerGlue2 = this.playerGlue;
        if (videoPlayerGlue2 == null) {
            Intrinsics.throwNpe();
        }
        classPresenterSelector.addClassPresenter(cls, videoPlayerGlue2.getPlaybackRowPresenter());
        classPresenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(classPresenterSelector);
        VideoPlayerGlue videoPlayerGlue3 = this.playerGlue;
        if (videoPlayerGlue3 == null) {
            Intrinsics.throwNpe();
        }
        arrayObjectAdapter.add(videoPlayerGlue3.getControlsRow());
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnPlaybackItemViewClickedListener(new ItemViewClickedListener());
        return arrayObjectAdapter;
    }

    public final void skipToNext() {
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        if (videoPlayerGlue == null) {
            Intrinsics.throwNpe();
        }
        videoPlayerGlue.next();
    }

    public final void skipToPrevious() {
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        if (videoPlayerGlue == null) {
            Intrinsics.throwNpe();
        }
        videoPlayerGlue.previous();
    }

    public final void rewind() {
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        if (videoPlayerGlue == null) {
            Intrinsics.throwNpe();
        }
        videoPlayerGlue.rewind();
    }

    public final void fastForward() {
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        if (videoPlayerGlue == null) {
            Intrinsics.throwNpe();
        }
        videoPlayerGlue.fastForward();
    }

    /* JADX INFO: compiled from: PlaybackFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            Intrinsics.checkParameterIsNotNull(itemViewHolder, "itemViewHolder");
            Intrinsics.checkParameterIsNotNull(item, "item");
            Intrinsics.checkParameterIsNotNull(rowViewHolder, "rowViewHolder");
            Intrinsics.checkParameterIsNotNull(row, "row");
            DSLog.INSTANCE.a("Clicked to open the video details page of a related video");
            if (PlaybackFragment.this.isControlsOverlayVisible()) {
                PlaybackFragment.this.askCurrentPlayedPopup();
            } else {
                PlaybackFragment.this.tickle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void askCurrentPlayedPopup() {
        int iIndexOf;
        VOPlayerAdapter vOPlayerAdapter;
        VOSubtitleTrack[] subtitleTracks;
        VOAudioTrack[] audioTracks;
        AudioSubListDialog audioSubListDialog = this.dialogAudio;
        if (audioSubListDialog == null || !audioSubListDialog.isShowing()) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
            AudioSubListDialog.Builder builder = new AudioSubListDialog.Builder(activity);
            int iIndexOf2 = 0;
            AudioSubListDialog.Builder masInfobutton = builder.setMasInfobutton(false);
            VOPlayer vOPlayer = this.player;
            List<VOSubtitleTrack> list = null;
            List<VOAudioTrack> list2 = (vOPlayer == null || (audioTracks = vOPlayer.getAudioTracks()) == null) ? null : ArraysKt.toList(audioTracks);
            ArrayList arrayList = new ArrayList();
            if (list2 != null) {
                iIndexOf = 0;
                for (VOAudioTrack vOAudioTrack : list2) {
                    if (vOAudioTrack.IsSelected()) {
                        iIndexOf = list2.indexOf(vOAudioTrack);
                    }
                    arrayList.add(new Pair(CommonUtils.getLanguageName(getActivity(), vOAudioTrack.getLanguage()), false));
                }
            } else {
                arrayList.add(new Pair(getString(R.string.dialog_select_caption_none), false));
                iIndexOf = 0;
            }
            AudioSubListDialog.Builder itemAudioList = masInfobutton.setItemAudioList(arrayList, iIndexOf);
            VOPlayer vOPlayer2 = this.player;
            if (vOPlayer2 != null && (subtitleTracks = vOPlayer2.getSubtitleTracks()) != null) {
                list = ArraysKt.toList(subtitleTracks);
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(getString(R.string.dialog_select_caption_none));
            if (list != null) {
                for (VOSubtitleTrack vOSubtitleTrack : list) {
                    if (vOSubtitleTrack.IsSelected() && (vOPlayerAdapter = this.playerAdapter) != null && vOPlayerAdapter.getSubtitleVisibility()) {
                        iIndexOf2 = list.indexOf(vOSubtitleTrack) + 1;
                    }
                    FragmentActivity activity2 = getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList2.add(CommonUtils.getLanguageName(activity2, vOSubtitleTrack.getLanguage()));
                }
            }
            AudioSubListDialog.Builder itemSubtitleList = itemAudioList.setItemSubtitleList(arrayList2, iIndexOf2);
            itemSubtitleList.setOnItemClickedListener(new AudioSubListDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.askCurrentPlayedPopup.1
                @Override // com.dotscreen.tv.ui.dialog.AudioSubListDialog.OnItemClickedListener
                public void onItemClicked(AudioSubListDialog dialog, int item_type, int itemIndex) {
                    VOSubtitleTrack[] subtitleTracks2;
                    VOAudioTrack vOAudioTrack2;
                    VOAudioTrack[] audioTracks2;
                    int i;
                    String name;
                    Integer numValueOf;
                    VOSubtitleTrack[] subtitleTracks3;
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    if (item_type != 0) {
                        int subtitleType = 0;
                        if (item_type == 1) {
                            VOPlayer player = PlaybackFragment.this.getPlayer();
                            List list3 = (player == null || (audioTracks2 = player.getAudioTracks()) == null) ? null : ArraysKt.toList(audioTracks2);
                            String str = PlaybackFragment.this.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Audio track language: ");
                            sb.append((list3 == null || (vOAudioTrack2 = (VOAudioTrack) list3.get(itemIndex)) == null) ? null : vOAudioTrack2.getLanguage());
                            Log.d(str, sb.toString());
                            if (list3 != null && list3.size() > itemIndex) {
                                VOAudioTrack vOAudioTrack3 = (VOAudioTrack) list3.get(itemIndex);
                                VOPlayerAdapter vOPlayerAdapter2 = PlaybackFragment.this.playerAdapter;
                                if (vOPlayerAdapter2 != null) {
                                    vOPlayerAdapter2.setAudioTrack(vOAudioTrack3);
                                }
                                String language = vOAudioTrack3.getLanguage();
                                if (language != null) {
                                    int iHashCode = language.hashCode();
                                    if (iHashCode == 3241) {
                                        if (language.equals("en")) {
                                            VOPlayer player2 = PlaybackFragment.this.getPlayer();
                                            if (player2 != null && (subtitleTracks2 = player2.getSubtitleTracks()) != null) {
                                                int length = subtitleTracks2.length;
                                                while (true) {
                                                    if (subtitleType >= length) {
                                                        subtitleType = -1;
                                                        break;
                                                    }
                                                    VOSubtitleTrack it = subtitleTracks2[subtitleType];
                                                    Log.d(PlaybackFragment.this.TAG, "check " + it);
                                                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                                    if (Intrinsics.areEqual(it.getLanguage(), "es")) {
                                                        break;
                                                    } else {
                                                        subtitleType++;
                                                    }
                                                }
                                            } else {
                                                subtitleType = -1;
                                                break;
                                            }
                                            if (subtitleType != -1) {
                                                Log.d(PlaybackFragment.this.TAG, "spanishSubtitleIndex=" + subtitleType);
                                                dialog.setSelectedSubtitleItem(subtitleType + 1);
                                            }
                                        }
                                    } else if (iHashCode == 3246 && language.equals("es")) {
                                        dialog.setSelectedSubtitleItem(0);
                                    }
                                }
                                PlayerViewModel viewModel = PlaybackFragment.this.getViewModel();
                                VOPlayerAdapter vOPlayerAdapter3 = PlaybackFragment.this.playerAdapter;
                                viewModel.updateCurrentAudioTrack(vOPlayerAdapter3 != null ? vOPlayerAdapter3.getSelectedAudioTrackLanguage() : null);
                                PlayerViewModel viewModel2 = PlaybackFragment.this.getViewModel();
                                VOPlayerAdapter vOPlayerAdapter4 = PlaybackFragment.this.playerAdapter;
                                viewModel2.updateCurrentSubtitleTrack(vOPlayerAdapter4 != null ? vOPlayerAdapter4.getSelectedSubtitleTrackLanguage() : null);
                            }
                        } else if (item_type == 2) {
                            VOPlayer player3 = PlaybackFragment.this.getPlayer();
                            List list4 = (player3 == null || (subtitleTracks3 = player3.getSubtitleTracks()) == null) ? null : ArraysKt.toList(subtitleTracks3);
                            if (list4 != null && list4.size() > (i = itemIndex - 1)) {
                                VOSubtitleTrack vOSubtitleTrack2 = itemIndex == 0 ? null : (VOSubtitleTrack) list4.get(i);
                                if (itemIndex == 0) {
                                    name = "";
                                } else {
                                    name = vOSubtitleTrack2 != null ? vOSubtitleTrack2.getName() : null;
                                }
                                if (itemIndex == 0) {
                                    numValueOf = Integer.valueOf(subtitleType);
                                } else if (vOSubtitleTrack2 != null) {
                                    subtitleType = vOSubtitleTrack2.getSubtitleType();
                                    numValueOf = Integer.valueOf(subtitleType);
                                } else {
                                    numValueOf = null;
                                }
                                Log.d(PlaybackFragment.this.TAG, "TrackIdSub=" + name + " ; TrackSubType=" + numValueOf);
                                VOPlayerAdapter vOPlayerAdapter5 = PlaybackFragment.this.playerAdapter;
                                if (vOPlayerAdapter5 != null) {
                                    if (itemIndex == 0) {
                                        vOSubtitleTrack2 = null;
                                    }
                                    vOPlayerAdapter5.setSubtitleTrack(vOSubtitleTrack2);
                                }
                                PlayerViewModel viewModel3 = PlaybackFragment.this.getViewModel();
                                VOPlayerAdapter vOPlayerAdapter6 = PlaybackFragment.this.playerAdapter;
                                viewModel3.updateCurrentSubtitleTrack(vOPlayerAdapter6 != null ? vOPlayerAdapter6.getSelectedSubtitleTrackLanguage() : null);
                            }
                        }
                    } else {
                        PlaybackFragment.this.startDetailsActivity();
                        dialog.dismiss();
                    }
                    PlayerViewModel viewModel4 = PlaybackFragment.this.getViewModel();
                    VOPlayerAdapter vOPlayerAdapter7 = PlaybackFragment.this.playerAdapter;
                    String selectedAudioTrackLanguage = vOPlayerAdapter7 != null ? vOPlayerAdapter7.getSelectedAudioTrackLanguage() : null;
                    VOPlayerAdapter vOPlayerAdapter8 = PlaybackFragment.this.playerAdapter;
                    viewModel4.storePreferredLanguage(selectedAudioTrackLanguage, vOPlayerAdapter8 != null ? vOPlayerAdapter8.getSelectedSubtitleTrackLanguage() : null);
                }
            });
            Dialog dialogCreate = itemSubtitleList.create();
            if (dialogCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.ui.dialog.AudioSubListDialog");
            }
            AudioSubListDialog audioSubListDialog2 = (AudioSubListDialog) dialogCreate;
            this.dialogAudio = audioSubListDialog2;
            if (audioSubListDialog2 != null) {
                audioSubListDialog2.show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSubtitleWithLanguage(String sbtLanguage) {
        VOSubtitleTrack[] subtitleTracks;
        VOSubtitleTrack voSubtitleTrack;
        VOPlayerAdapter vOPlayerAdapter;
        getViewModel().setSubtitle(true);
        VOPlayer vOPlayer = this.player;
        if (vOPlayer == null || (subtitleTracks = vOPlayer.getSubtitleTracks()) == null) {
            return;
        }
        int length = subtitleTracks.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                voSubtitleTrack = null;
                break;
            }
            voSubtitleTrack = subtitleTracks[i];
            Intrinsics.checkExpressionValueIsNotNull(voSubtitleTrack, "voSubtitleTrack");
            if (StringsKt.equals(voSubtitleTrack.getLanguage(), sbtLanguage, true)) {
                break;
            } else {
                i++;
            }
        }
        if (voSubtitleTrack == null || (vOPlayerAdapter = this.playerAdapter) == null) {
            return;
        }
        vOPlayerAdapter.setSubtitleTrack(voSubtitleTrack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDetailsActivity() {
        String value = getViewModel().getCurrentContentIdLiveData().getValue();
        Long value2 = getViewModel().getCurrentProgramTimestampLiveData().getValue();
        String channelId = getViewModel().getChannelId();
        String recordingId = getViewModel().getRecordingId();
        if (recordingId != null && (!Intrinsics.areEqual(recordingId, ""))) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?recording_id=" + recordingId + "&adult_browsing=false"));
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            intent.setFlags(134217728);
            intent.putExtra("extra_started_from_live_tv", true);
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            activity.startActivity(intent);
            return;
        }
        if (channelId != null && (!Intrinsics.areEqual(channelId, ""))) {
            if ((value2 != null ? value2.longValue() : 0L) > 0) {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + channelId + "&start=" + value2 + "&adult_browsing=false"));
                intent2.setFlags(C.ENCODING_PCM_MU_LAW);
                intent2.setFlags(134217728);
                intent2.putExtra("extra_started_from_live_tv", true);
                FragmentActivity activity2 = getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                activity2.startActivity(intent2);
                return;
            }
        }
        if (value == null || !(!Intrinsics.areEqual(value, ""))) {
            return;
        }
        if (getViewModel().getCatchupPlayback()) {
            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/catchup/details?content_id=" + value + "&adult_browsing=false"));
            intent3.setFlags(C.ENCODING_PCM_MU_LAW);
            intent3.setFlags(134217728);
            intent3.putExtra("extra_started_from_live_tv", true);
            FragmentActivity activity3 = getActivity();
            if (activity3 == null) {
                Intrinsics.throwNpe();
            }
            activity3.startActivity(intent3);
            return;
        }
        Intent intent4 = new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + value + "&adult_browsing=false"));
        intent4.setFlags(C.ENCODING_PCM_MU_LAW);
        intent4.setFlags(134217728);
        intent4.putExtra("extra_started_from_live_tv", true);
        FragmentActivity activity4 = getActivity();
        if (activity4 == null) {
            Intrinsics.throwNpe();
        }
        activity4.startActivity(intent4);
    }

    /* JADX INFO: compiled from: PlaybackFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment$PlaylistActionListener;", "Lcom/dotscreen/portal/player/VideoPlayerGlue$OnActionClickedListener;", "(Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;)V", "onNext", "", "onPrevious", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class PlaylistActionListener implements VideoPlayerGlue.OnActionClickedListener {
        @Override // com.dotscreen.portal.player.VideoPlayerGlue.OnActionClickedListener
        public void onNext() {
        }

        @Override // com.dotscreen.portal.player.VideoPlayerGlue.OnActionClickedListener
        public void onPrevious() {
        }

        public PlaylistActionListener() {
        }
    }

    /* JADX INFO: compiled from: PlaybackFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment$PlaybackInfoListener;", "Lcom/dotscreen/portal/player/VideoPlayerGlue$PlaybackInfoListener;", "(Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;)V", "onCurrentPositionChanged", "", "currentPosition", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class PlaybackInfoListener implements VideoPlayerGlue.PlaybackInfoListener {
        public PlaybackInfoListener() {
        }

        /* JADX WARN: Code duplicated, block: B:30:0x0089  */
        /* JADX WARN: Code duplicated, block: B:32:0x00a0  */
        @Override // com.dotscreen.portal.player.VideoPlayerGlue.PlaybackInfoListener
        public void onCurrentPositionChanged(long currentPosition) {
            AnalyticsManager analyticsManager;
            if (PlaybackFragment.this.getViewModel().getCurrentContentIdLiveData().getValue() != null && (analyticsManager = PlaybackFragment.this.analyticsManager) != null) {
                analyticsManager.onCurrentPositionChanged(currentPosition);
            }
            VideoPlayerGlue videoPlayerGlue = PlaybackFragment.this.playerGlue;
            if (videoPlayerGlue != null && !PlaybackFragment.this.getIsPlayingAds()) {
                if (videoPlayerGlue.isPrepared()) {
                    PlaybackFragment.this.getViewModel().updatePlaybackState(videoPlayerGlue.isPlaying() ? PlaybackState.PLAYING : PlaybackState.PAUSE);
                } else {
                    PlaybackFragment.this.getViewModel().updatePlaybackState(PlaybackState.PLAYING);
                }
            }
            long remainingTime = PlaybackFragment.this.getRemainingTime(currentPosition);
            VideoPlayerGlue videoPlayerGlue2 = PlaybackFragment.this.playerGlue;
            if (videoPlayerGlue2 == null || !videoPlayerGlue2.isPlaying()) {
                return;
            }
            if (PlaybackFragment.this.getPlayer() != null) {
                double d = currentPosition;
                VOPlayer player = PlaybackFragment.this.getPlayer();
                if (player == null) {
                    Intrinsics.throwNpe();
                }
                if (d >= 0.95d * ((double) player.getDuration())) {
                    PlaybackFragment.this.getViewModel().loadNextAiring();
                } else if (remainingTime < PlaybackFragment.this.getViewModel().getCountdownTrigger() + TimeUnit.SECONDS.toMillis(3L)) {
                    PlaybackFragment.this.getViewModel().loadNextAiring();
                }
            } else if (remainingTime < PlaybackFragment.this.getViewModel().getCountdownTrigger() + TimeUnit.SECONDS.toMillis(3L)) {
                PlaybackFragment.this.getViewModel().loadNextAiring();
            }
            if (PlaybackFragment.this.getViewModel().getReplayType() == null || remainingTime >= PlaybackFragment.this.getViewModel().getCountdownTrigger()) {
                return;
            }
            PlaybackFragment.this.getViewModel().startCountDown();
        }
    }

    /* JADX INFO: compiled from: PlaybackFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment$PlayerInfoListener;", "Lcom/dotscreen/portal/player/VOPlayerAdapter$PlayerInfoListener;", "(Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;)V", "onFirstFrameDisplayed", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class PlayerInfoListener implements VOPlayerAdapter.PlayerInfoListener {
        public PlayerInfoListener() {
        }

        @Override // com.dotscreen.portal.player.VOPlayerAdapter.PlayerInfoListener
        public void onFirstFrameDisplayed() {
            Class<?> cls;
            FragmentActivity activity = PlaybackFragment.this.getActivity();
            if (activity == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.player.VODPlaybackActivity");
            }
            if (((VODPlaybackActivity) activity).getIsFirstPlaybackOfInstance()) {
                FragmentActivity activity2 = PlaybackFragment.this.getActivity();
                if (activity2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.player.VODPlaybackActivity");
                }
                ((VODPlaybackActivity) activity2).setFirstPlaybackOfInstance(false);
                DSLog dSLog = DSLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                FragmentActivity activity3 = PlaybackFragment.this.getActivity();
                sb.append((activity3 == null || (cls = activity3.getClass()) == null) ? null : cls.getSimpleName());
                sb.append('@');
                FragmentActivity activity4 = PlaybackFragment.this.getActivity();
                sb.append(activity4 != null ? activity4.hashCode() : 0);
                dSLog.vodPlaybackTimeStamp(sb.toString(), true);
            }
            DSLog dSLog2 = DSLog.INSTANCE;
            FragmentActivity activity5 = PlaybackFragment.this.getActivity();
            dSLog2.printCurrentRAMUsage((ActivityManager) (activity5 != null ? activity5.getSystemService("activity") : null));
            Log.v(PlaybackFragment.this.TAG, "onFirstFrameDisplayed: autoPlay=" + PlaybackFragment.this.getViewModel().getAutoPlay() + ", state=" + PlaybackFragment.this.getViewModel().getCurrentPlaybackStateLiveData().getValue());
            if (!PlaybackFragment.this.getViewModel().getAutoPlay() && !PlaybackFragment.this.isFirstFrameDisplayed) {
                VideoPlayerGlue videoPlayerGlue = PlaybackFragment.this.playerGlue;
                if (videoPlayerGlue != null) {
                    videoPlayerGlue.pause();
                }
                PlaybackFragment.this.getViewModel().updatePlaybackState(PlaybackState.PAUSE);
            } else if (PlaybackFragment.this.getViewModel().getAutoPlay() && PlaybackFragment.this.getViewModel().getCurrentPlaybackStateLiveData().getValue() != PlaybackState.IS_FINISHED && !PlaybackFragment.this.isFirstFrameDisplayed) {
                VideoPlayerGlue videoPlayerGlue2 = PlaybackFragment.this.playerGlue;
                if (videoPlayerGlue2 != null) {
                    videoPlayerGlue2.play();
                }
                PlaybackFragment.this.getViewModel().updatePlaybackState(PlaybackState.PLAYING);
            }
            PlaybackFragment.this.isFirstFrameDisplayed = true;
        }
    }

    /* JADX INFO: compiled from: PlaybackFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment$PlayerCallback;", "Landroidx/leanback/media/PlaybackGlue$PlayerCallback;", "(Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;)V", "onPlayCompleted", "", "glue", "Landroidx/leanback/media/PlaybackGlue;", "onPlayStateChanged", "onPreparedStateChanged", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class PlayerCallback extends PlaybackGlue.PlayerCallback {
        public PlayerCallback() {
        }

        @Override // androidx.leanback.media.PlaybackGlue.PlayerCallback
        public void onPreparedStateChanged(PlaybackGlue glue) {
            super.onPreparedStateChanged(glue);
            AnalyticsManager analyticsManager = PlaybackFragment.this.analyticsManager;
            if (analyticsManager != null) {
                VOPlayer player = PlaybackFragment.this.getPlayer();
                int currentPosition = player != null ? player.getCurrentPosition() : -1;
                VOPlayer player2 = PlaybackFragment.this.getPlayer();
                analyticsManager.onPlaybackStart(currentPosition, player2 != null ? player2.getDuration() : -1, null);
            }
        }

        @Override // androidx.leanback.media.PlaybackGlue.PlayerCallback
        public void onPlayStateChanged(PlaybackGlue glue) {
            PlaybackState playbackState;
            super.onPlayStateChanged(glue);
            Log.v(PlaybackFragment.this.TAG, "onPlayStateChanged");
            VideoPlayerGlue videoPlayerGlue = PlaybackFragment.this.playerGlue;
            if (videoPlayerGlue != null && videoPlayerGlue.isPrepared() && PlaybackFragment.this.isFirstFrameDisplayed) {
                PlayerViewModel viewModel = PlaybackFragment.this.getViewModel();
                if (videoPlayerGlue.isPlaying()) {
                    playbackState = PlaybackState.PLAYING;
                } else {
                    playbackState = PlaybackState.PAUSE;
                }
                viewModel.updatePlaybackState(playbackState);
            }
            String it = PlaybackFragment.this.getViewModel().getCurrentSubtitleLiveData().getValue();
            if (it != null) {
                if (!PlaybackFragment.this.getViewModel().getSubtitleHasBeenSet()) {
                    PlaybackFragment playbackFragment = PlaybackFragment.this;
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    playbackFragment.setSubtitleWithLanguage(it);
                }
                Log.v(PlaybackFragment.this.TAG, "Subtitle was already set");
            }
        }

        @Override // androidx.leanback.media.PlaybackGlue.PlayerCallback
        public void onPlayCompleted(PlaybackGlue glue) {
            PlayerViewModel viewModel;
            PlayerViewModel viewModel2;
            super.onPlayCompleted(glue);
            AnalyticsManager analyticsManager = PlaybackFragment.this.analyticsManager;
            if (analyticsManager != null) {
                VOPlayer player = PlaybackFragment.this.getPlayer();
                analyticsManager.onPlaybackEnd(player != null ? player.getCurrentPosition() : -1);
            }
            PlaybackReplayType replayType = PlaybackFragment.this.getViewModel().getReplayType();
            Integer launchMode = PlaybackFragment.this.getViewModel().getLaunchMode();
            String channelId = PlaybackFragment.this.getViewModel().getChannelId();
            BrowsableItem value = PlaybackFragment.this.getViewModel().getCurrentMediaLiveData().getValue();
            if (replayType == PlaybackReplayType.STOV && PlaybackFragment.this.getContext() != null && PlaybackFragment.this.getViewModel().getNextTVAiringTimeStamp() == null) {
                if (launchMode != null && (launchMode.intValue() == 85 || launchMode.intValue() == 127 || launchMode.intValue() == 89)) {
                    PlaybackFragment.this.showReturnToLive(false);
                    return;
                }
                if (channelId != null) {
                    PlaybackFragment.this.showReturnToLive(true);
                    return;
                }
                FragmentActivity activity = PlaybackFragment.this.getActivity();
                if (activity != null) {
                    activity.finish();
                    return;
                }
                return;
            }
            if ((value instanceof Episode) && PlaybackFragment.this.getViewModel().getNextEpisode() != null) {
                VODPlaybackActivity vODPlaybackActivity = PlaybackFragment.this.vodActivity;
                Integer numValueOf = (vODPlaybackActivity == null || (viewModel2 = vODPlaybackActivity.getViewModel()) == null) ? null : Integer.valueOf(viewModel2.getNewEpisodeStartedCount());
                if ((numValueOf != null && numValueOf.intValue() == 0) || (numValueOf != null && numValueOf.intValue() == 1)) {
                    VODPlaybackActivity vODPlaybackActivity2 = PlaybackFragment.this.vodActivity;
                    if (vODPlaybackActivity2 == null || (viewModel = vODPlaybackActivity2.getViewModel()) == null) {
                        return;
                    }
                    viewModel.startCountDown();
                    return;
                }
                if (numValueOf != null && numValueOf.intValue() == 2) {
                    PlaybackFragment.this.pausePlayer();
                    PlaybackFragment.showStillAlive$default(PlaybackFragment.this, false, 1, null);
                    return;
                }
                PlaybackFragment.this.getViewModel().updatePlaybackState(PlaybackState.IS_FINISHED);
                FragmentActivity activity2 = PlaybackFragment.this.getActivity();
                if (activity2 != null) {
                    activity2.finish();
                    return;
                }
                return;
            }
            PlaybackFragment.this.getViewModel().updatePlaybackState(PlaybackState.IS_FINISHED);
            FragmentActivity activity3 = PlaybackFragment.this.getActivity();
            if (activity3 != null) {
                activity3.finish();
            }
        }
    }

    public final long getRemainingTime(long currentPosition) {
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        Long lValueOf = videoPlayerGlue != null ? Long.valueOf(videoPlayerGlue.getDuration()) : null;
        if (lValueOf != null) {
            return lValueOf.longValue() - currentPosition;
        }
        return 100000000L;
    }

    public static /* synthetic */ void showReturnToLive$default(PlaybackFragment playbackFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        playbackFragment.showReturnToLive(z);
    }

    public final void showReturnToLive(boolean returnToPrevious) {
        Dialog dialog = this.portalDialog;
        if (dialog != null && dialog.isShowing()) {
            Log.e(this.TAG, "Prevent return to live popup being called if a popup is already visible");
            return;
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        PortalDialog.Builder positiveButton = new PortalDialog.Builder(context).setTitle(R.string.timeshift_ended_title).setMessage(R.string.timeshift_ended_message).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$showReturnToLive$t$1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.this$0.portalDialog = (Dialog) null;
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$showReturnToLive$t$2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.this$0.portalDialog = (Dialog) null;
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$showReturnToLive$t$3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) throws URISyntaxException {
                dialogInterface.dismiss();
                this.this$0.goToLive();
                FragmentActivity activity = this.this$0.getActivity();
                if (activity != null) {
                    activity.finish();
                }
            }
        });
        if (returnToPrevious) {
            Context context2 = getContext();
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
            String string = context2.getResources().getString(R.string.timeshift_ended_button);
            Intrinsics.checkExpressionValueIsNotNull(string, "context!!.resources.getS…g.timeshift_ended_button)");
            positiveButton.setNegativeButton(string, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.showReturnToLive.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    FragmentActivity activity = PlaybackFragment.this.getActivity();
                    if (activity != null) {
                        activity.finish();
                    }
                }
            });
        }
        positiveButton.setBackMessage(true);
        Dialog dialogCreate = positiveButton.create();
        this.portalDialog = dialogCreate;
        if (dialogCreate != null) {
            dialogCreate.show();
        }
    }

    public final void goToLive() throws URISyntaxException {
        Context context = getContext();
        String channelId = getViewModel().getChannelId();
        if (channelId == null) {
            Intrinsics.throwNpe();
        }
        Uri liveTvDeeplink = CommonUtils.getLiveTvDeeplink(context, channelId, (Boolean) true);
        if (liveTvDeeplink != null) {
            Intent uri = Intent.parseUri(liveTvDeeplink.toString(), 0);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.startActivity(uri);
            }
        }
    }

    public static /* synthetic */ void showStillAlive$default(PlaybackFragment playbackFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        playbackFragment.showStillAlive(z);
    }

    public final void showStillAlive(boolean autoHide) {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        PortalDialog.Builder negativeButton = new PortalDialog.Builder(context).setTitle(R.string.dialog_keep_watching).setMessage("").setPositiveButton(R.string.dialog_reboot_yes, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$showStillAlive$builder$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (this.this$0.getViewModel().getReplayType() != null) {
                    VODPlaybackActivity vODPlaybackActivity = this.this$0.vodActivity;
                    if (vODPlaybackActivity != null) {
                        vODPlaybackActivity.startNextMediaWhenAllowed();
                        return;
                    }
                    return;
                }
                VideoPlayerGlue videoPlayerGlue = this.this$0.playerGlue;
                if (videoPlayerGlue != null) {
                    videoPlayerGlue.playWhenPrepared();
                }
                this.this$0.getViewModel().resetNewEpisodeStartedCount();
                this.this$0.getViewModel().startCountDown();
                this.this$0.getViewModel().resetUserInteraction();
            }
        }).setNegativeButton(R.string.dialog_reboot_no, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$showStillAlive$builder$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) throws URISyntaxException {
                int i2;
                PlaybackReplayType replayType = this.this$0.getViewModel().getReplayType();
                if (replayType != null && ((i2 = PlaybackFragment.WhenMappings.$EnumSwitchMapping$0[replayType.ordinal()]) == 1 || i2 == 2)) {
                    this.this$0.goToLive();
                }
                dialogInterface.dismiss();
                FragmentActivity activity = this.this$0.getActivity();
                if (activity != null) {
                    activity.finish();
                }
            }
        });
        negativeButton.setBackMessage(false);
        final Dialog dialogCreate = negativeButton.create();
        dialogCreate.show();
        if (autoHide) {
            final Handler handler = new Handler(Looper.getMainLooper());
            final Runnable runnable = new Runnable() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment$showStillAlive$runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    Date startDate;
                    if (dialogCreate.isShowing()) {
                        PlaybackReplayType replayType = this.this$0.getViewModel().getReplayType();
                        if (replayType != null) {
                            int i = PlaybackFragment.WhenMappings.$EnumSwitchMapping$1[replayType.ordinal()];
                            if (i == 1) {
                                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/guide"));
                                intent.setFlags(67108864);
                                this.this$0.startActivity(intent);
                            } else if (i == 2) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=");
                                sb.append(this.this$0.getViewModel().getChannelId());
                                sb.append("&start=");
                                TVAiring value = this.this$0.getViewModel().getCurrentAiringLiveData().getValue();
                                sb.append((value == null || (startDate = value.getStartDate()) == null) ? null : Long.valueOf(startDate.getTime()));
                                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
                                intent2.setFlags(67108864);
                                this.this$0.startActivity(intent2);
                            }
                        }
                        dialogCreate.dismiss();
                    }
                }
            };
            dialogCreate.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackFragment.showStillAlive.1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    handler.removeCallbacks(runnable);
                }
            });
            handler.postDelayed(runnable, 10000L);
        }
    }

    @Override // androidx.leanback.app.PlaybackSupportFragment
    public void tickle() {
        super.tickle();
        setControlsOverlayAutoHideEnabled(false);
        setControlsOverlayAutoHideEnabled(true);
    }

    @Override // androidx.leanback.app.PlaybackSupportFragment
    protected void onError(int errorCode, CharSequence errorMessage) {
        super.onError(errorCode, errorMessage);
        AnalyticsManager analyticsManager = this.analyticsManager;
        if (analyticsManager != null) {
            analyticsManager.onPlaybackError(String.valueOf(errorMessage), Integer.valueOf(errorCode));
        }
        showErrorMessage(errorMessage, errorCode);
    }

    private final void showErrorMessage(CharSequence errorMessage, int errorCode) {
        String string;
        String string2 = null;
        Integer numValueOf = (errorMessage == null || (string = errorMessage.toString()) == null) ? null : Integer.valueOf(Integer.parseInt(string));
        DSVOPlayerError playerError = numValueOf != null ? DSVOPlayerError.INSTANCE.getPlayerError(numValueOf.intValue()) : null;
        if (playerError != null) {
            Log.e(this.TAG, "VOPlayer error(owner): " + playerError.getOwner());
            Context context = getContext();
            if (context != null) {
                string2 = context.getString(playerError.getResId());
            }
        } else {
            string2 = getString(R.string.error_player_popup_title) + " VP:" + errorCode + ':' + errorMessage;
        }
        Toast.makeText(getContext(), string2, 1).show();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override // androidx.leanback.app.PlaybackSupportFragment
    protected void onBufferingStateChanged(boolean start) {
        VideoPlayerGlue videoPlayerGlue = this.playerGlue;
        if (videoPlayerGlue != null) {
            videoPlayerGlue.isLoading = start;
        }
        super.onBufferingStateChanged(start);
        AnalyticsManager analyticsManager = this.analyticsManager;
        if (analyticsManager != null) {
            analyticsManager.onBufferingStateChanged(start);
        }
    }
}
