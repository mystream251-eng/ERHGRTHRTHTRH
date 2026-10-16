package com.dotscreen.portal.ui.screens.player;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.leanback.widget.SeekBar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelStore;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.bumptech.glide.request.RequestListener;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.farkit.tools.LanguagePreferences;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.PlaybackReplayType;
import com.dotscreen.portal.model.PlaybackState;
import com.dotscreen.portal.model.PlayerViewModel;
import com.dotscreen.portal.model.PlayerViewModelFactory;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.google.android.exoplayer2.util.MimeTypes;
import com.viaccessorca.analytics.fma.FmaDatabaseHelper;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaybackActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000bH\u0002J\b\u0010 \u001a\u00020\u001eH\u0002J\b\u0010!\u001a\u00020\u001eH\u0016J\u0012\u0010\"\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0017J\b\u0010%\u001a\u00020\u001eH\u0014J\u0010\u0010&\u001a\u00020\u001e2\b\u0010'\u001a\u0004\u0018\u00010(J\u0010\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020+H\u0016J\u001a\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020.2\b\u0010*\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\u001eH\u0014J\b\u00101\u001a\u00020\u001eH\u0014J\b\u00102\u001a\u00020\u001eH\u0016J\b\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u00020\u001eH\u0002J\u0006\u00106\u001a\u00020\u001eJ\b\u00107\u001a\u00020\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001a\u0010\u001b¨\u00068"}, d2 = {"Lcom/dotscreen/portal/ui/screens/player/VODPlaybackActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "GAMEPAD_TRIGGER_INTENSITY_OFF", "", "GAMEPAD_TRIGGER_INTENSITY_ON", "backgroundDesignPath", "", "getBackgroundDesignPath", "()Ljava/lang/String;", "gamepadTriggerPressed", "", "isFirstPlaybackOfInstance", "()Z", "setFirstPlaybackOfInstance", "(Z)V", "languagePreferences", "Lcom/dotscreen/farkit/tools/LanguagePreferences;", "getLanguagePreferences", "()Lcom/dotscreen/farkit/tools/LanguagePreferences;", "languagePreferences$delegate", "Lkotlin/Lazy;", "mPlaybackFragment", "Lcom/dotscreen/portal/ui/screens/player/PlaybackFragment;", "viewModel", "Lcom/dotscreen/portal/model/PlayerViewModel;", "getViewModel", "()Lcom/dotscreen/portal/model/PlayerViewModel;", "viewModel$delegate", "hidePlaybackStatusOverlay", "", "visibility", "interruptSkipNext", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onError", NotificationCompat.CATEGORY_ERROR, "", "onGenericMotionEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onKeyDown", "keyCode", "", "Landroid/view/KeyEvent;", "onPause", "onResume", "onUserInteraction", "parsePlaybackDeeplink", "Lcom/dotscreen/portal/model/PlayerViewModelFactory$Companion$PlayerViewModelParams;", "startNextMedia", "startNextMediaWhenAllowed", "stopCurrentMedia", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODPlaybackActivity extends BaseFragmentActivity {
    private HashMap _$_findViewCache;
    private final String backgroundDesignPath;
    private boolean gamepadTriggerPressed;
    private PlaybackFragment mPlaybackFragment;
    private final float GAMEPAD_TRIGGER_INTENSITY_ON = 0.5f;
    private final float GAMEPAD_TRIGGER_INTENSITY_OFF = 0.45f;

    /* JADX INFO: renamed from: languagePreferences$delegate, reason: from kotlin metadata */
    private final Lazy languagePreferences = LazyKt.lazy(new Function0<LanguagePreferences>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity$languagePreferences$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LanguagePreferences invoke() {
            PortalApplication.Config value;
            RightVUser user;
            VODPlaybackActivity vODPlaybackActivity = this.this$0;
            LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(this.this$0).getConfig();
            return new LanguagePreferences(vODPlaybackActivity, (config == null || (value = config.getValue()) == null || (user = value.getUser()) == null) ? null : user.getCompassId());
        }
    });

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(PlayerViewModel.class), new Function0<ViewModelStore>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity$$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = this.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<PlayerViewModelFactory>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity$viewModel$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PlayerViewModelFactory invoke() {
            return new PlayerViewModelFactory(this.this$0.parsePlaybackDeeplink(), this.this$0.getLanguagePreferences(), PortalApplication.INSTANCE.getSingleton(this.this$0));
        }
    });
    private boolean isFirstPlaybackOfInstance = true;

    /* JADX INFO: Access modifiers changed from: private */
    public final LanguagePreferences getLanguagePreferences() {
        return (LanguagePreferences) this.languagePreferences.getValue();
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
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

    public final PlayerViewModel getViewModel() {
        return (PlayerViewModel) this.viewModel.getValue();
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    protected String getBackgroundDesignPath() {
        return this.backgroundDesignPath;
    }

    /* JADX INFO: renamed from: isFirstPlaybackOfInstance, reason: from getter */
    public final boolean getIsFirstPlaybackOfInstance() {
        return this.isFirstPlaybackOfInstance;
    }

    public final void setFirstPlaybackOfInstance(boolean z) {
        this.isFirstPlaybackOfInstance = z;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isFirstPlaybackOfInstance = true;
        DSLog.INSTANCE.vodPlaybackTimeStamp(PlaybackActivityKt.TAG + '@' + hashCode(), false);
        setContentView(R.layout.activity_vod_playback);
        hidePlaybackStatusOverlay(getViewModel().getHideStatusOnScreen());
        Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag("VOD_PLAYBACK_TAG");
        if (fragmentFindFragmentByTag instanceof PlaybackFragment) {
            this.mPlaybackFragment = (PlaybackFragment) fragmentFindFragmentByTag;
        }
        VODPlaybackActivity vODPlaybackActivity = this;
        getViewModel().getOnAdsDetectedLiveData().observe(vODPlaybackActivity, new Observer<Boolean>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.onCreate.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean adsDetected) {
                TextView textView;
                SeekBar seekBar;
                Button skip_ads = (Button) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.skip_ads);
                Intrinsics.checkExpressionValueIsNotNull(skip_ads, "skip_ads");
                Intrinsics.checkExpressionValueIsNotNull(adsDetected, "adsDetected");
                skip_ads.setVisibility(adsDetected.booleanValue() ? 0 : 8);
                PlaybackFragment playbackFragment = VODPlaybackActivity.this.mPlaybackFragment;
                if (playbackFragment != null && (seekBar = (SeekBar) playbackFragment._$_findCachedViewById(com.dotscreen.portal.R.id.playback_progress)) != null) {
                    seekBar.setProgressColor(ContextCompat.getColor(VODPlaybackActivity.this, adsDetected.booleanValue() ? R.color.ads_progress_color_vod : R.color.progress_color_vod));
                    seekBar.setFocusable(!adsDetected.booleanValue());
                    seekBar.setFocusableInTouchMode(!adsDetected.booleanValue());
                    seekBar.setEnabled(!adsDetected.booleanValue());
                }
                PlaybackFragment playbackFragment2 = VODPlaybackActivity.this.mPlaybackFragment;
                if (playbackFragment2 != null && (textView = (TextView) playbackFragment2._$_findCachedViewById(com.dotscreen.portal.R.id.body)) != null) {
                    ViewKt.setVisible(textView, !adsDetected.booleanValue());
                }
                PlaybackFragment playbackFragment3 = VODPlaybackActivity.this.mPlaybackFragment;
                if (playbackFragment3 != null) {
                    playbackFragment3.setControlsOverlayAutoHideEnabled(!adsDetected.booleanValue());
                }
                ProgressBar loaderProgress = (ProgressBar) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.loaderProgress);
                Intrinsics.checkExpressionValueIsNotNull(loaderProgress, "loaderProgress");
                loaderProgress.setVisibility(8);
            }
        });
        getViewModel().getOnAdsCanBeSkippedLiveData().observe(vODPlaybackActivity, new Observer<Integer>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.onCreate.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer num) {
                if (Intrinsics.compare(num.intValue(), 0) > 0) {
                    Button skip_ads = (Button) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.skip_ads);
                    Intrinsics.checkExpressionValueIsNotNull(skip_ads, "skip_ads");
                    skip_ads.setEnabled(false);
                    Button skip_ads2 = (Button) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.skip_ads);
                    Intrinsics.checkExpressionValueIsNotNull(skip_ads2, "skip_ads");
                    skip_ads2.setFocusable(true);
                    Button skip_ads3 = (Button) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.skip_ads);
                    Intrinsics.checkExpressionValueIsNotNull(skip_ads3, "skip_ads");
                    skip_ads3.setText("Skip In " + num + " s");
                    return;
                }
                if (num != null && num.intValue() == 0) {
                    ((Button) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.skip_ads)).requestFocus();
                    Button skip_ads4 = (Button) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.skip_ads);
                    Intrinsics.checkExpressionValueIsNotNull(skip_ads4, "skip_ads");
                    skip_ads4.setEnabled(true);
                    Button skip_ads5 = (Button) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.skip_ads);
                    Intrinsics.checkExpressionValueIsNotNull(skip_ads5, "skip_ads");
                    skip_ads5.setText("Skip Ad");
                }
            }
        });
        getViewModel().getCountDownIsOn().observe(vODPlaybackActivity, new Observer<Boolean>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.onCreate.3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean countdownIsOn) {
                String string;
                Intrinsics.checkExpressionValueIsNotNull(countdownIsOn, "countdownIsOn");
                if (countdownIsOn.booleanValue()) {
                    VODPlaybackActivity.this.stopCurrentMedia();
                    if (VODPlaybackActivity.this.getViewModel().getReplayType() == null) {
                        ImageView imageView = (ImageView) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.episode_background_image);
                        imageView.setAlpha(0.0f);
                        imageView.setVisibility(0);
                        imageView.animate().alpha(1.0f).setDuration(imageView.getResources().getInteger(android.R.integer.config_mediumAnimTime)).setListener(null);
                        LinearLayout linearLayout = (LinearLayout) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.layout_skip_next);
                        TextView textView = (TextView) linearLayout.findViewById(R.id.textview_skipNext);
                        if (textView != null) {
                            if (VODPlaybackActivity.this.getViewModel().getReplayType() == null) {
                                string = VODPlaybackActivity.this.getString(R.string.playback_next_episode_start);
                            } else {
                                string = VODPlaybackActivity.this.getString(R.string.playback_next_program_start);
                            }
                            textView.setText(string);
                        }
                        linearLayout.setAlpha(0.0f);
                        linearLayout.setVisibility(0);
                        linearLayout.animate().alpha(1.0f).setDuration(linearLayout.getResources().getInteger(android.R.integer.config_mediumAnimTime)).setListener(null);
                        return;
                    }
                    return;
                }
                LinearLayout layout_skip_next = (LinearLayout) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.layout_skip_next);
                Intrinsics.checkExpressionValueIsNotNull(layout_skip_next, "layout_skip_next");
                layout_skip_next.setVisibility(8);
                ((ImageView) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.episode_background_image)).setImageDrawable(null);
                ImageView episode_background_image = (ImageView) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.episode_background_image);
                Intrinsics.checkExpressionValueIsNotNull(episode_background_image, "episode_background_image");
                episode_background_image.setVisibility(8);
            }
        });
        getViewModel().getNextEpisodeLoaded().observe(vODPlaybackActivity, new Observer<Boolean>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.onCreate.4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean nextEpisodeLoaded) {
                Artwork stillArtwork;
                Intrinsics.checkExpressionValueIsNotNull(nextEpisodeLoaded, "nextEpisodeLoaded");
                if (!nextEpisodeLoaded.booleanValue() || VODPlaybackActivity.this.getViewModel().getNextEpisode() == null) {
                    return;
                }
                ImageLoaderManager imageLoaderManager = new ImageLoaderManager(VODPlaybackActivity.this);
                Episode nextEpisode = VODPlaybackActivity.this.getViewModel().getNextEpisode();
                imageLoaderManager.loadImage(String.valueOf((nextEpisode == null || (stillArtwork = nextEpisode.getStillArtwork()) == null) ? null : stillArtwork.urlWithSize(new Point(1920, 1080), 1.0d)), (1016 & 2) != 0 ? (ImageView) null : (ImageView) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.episode_background_image), (1016 & 4) != 0 ? false : false, (1016 & 8) != 0 ? 300 : 0, (1016 & 16) != 0 ? 0 : 1920, (1016 & 32) != 0 ? 0 : 1080, (1016 & 64) != 0 ? (ImageView.ScaleType) null : null, (1016 & 128) != 0 ? 0 : 0, (1016 & 256) == 0 ? 0 : 0, (1016 & 512) != 0 ? (RequestListener) null : null);
            }
        });
        getViewModel().getNextReplayMedia().observe(vODPlaybackActivity, new Observer<Media>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.onCreate.5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Media media) {
            }
        });
        getViewModel().getCurrentTime().observe(vODPlaybackActivity, new Observer<Long>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.onCreate.6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Long l) {
                View viewFindViewById = VODPlaybackActivity.this.findViewById(R.id.textview_remaining_seconds);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<TextView>(R…xtview_remaining_seconds)");
                ((TextView) viewFindViewById).setText(VODPlaybackActivity.this.getString(R.string.playback_seconds, new Object[]{l}));
            }
        });
        getViewModel().getCountDownFinished().observe(vODPlaybackActivity, new Observer<Boolean>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.onCreate.7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean countdownFinished) {
                Intrinsics.checkExpressionValueIsNotNull(countdownFinished, "countdownFinished");
                if (countdownFinished.booleanValue()) {
                    if (VODPlaybackActivity.this.getViewModel().getReplayType() != null && VODPlaybackActivity.this.getViewModel().shouldDisplayStillAlive()) {
                        PlaybackFragment playbackFragment = VODPlaybackActivity.this.mPlaybackFragment;
                        if (playbackFragment != null) {
                            playbackFragment.showStillAlive(true);
                            return;
                        }
                        return;
                    }
                    VODPlaybackActivity.this.startNextMediaWhenAllowed();
                }
            }
        });
        getViewModel().getCurrentPlaybackStateLiveData().observe(vODPlaybackActivity, new Observer<PlaybackState>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.onCreate.8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(PlaybackState playbackState) {
                if (VODPlaybackActivity.this.getViewModel().getHideStatusOnScreen()) {
                    return;
                }
                TextView status_player = (TextView) VODPlaybackActivity.this._$_findCachedViewById(com.dotscreen.portal.R.id.status_player);
                Intrinsics.checkExpressionValueIsNotNull(status_player, "status_player");
                StringBuilder sb = new StringBuilder();
                sb.append("status : ");
                String strName = playbackState.name();
                if (strName == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase = strName.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
                sb.append(lowerCase);
                status_player.setText(sb.toString());
            }
        });
    }

    public final void startNextMediaWhenAllowed() {
        if (getViewModel().isAllowedToPlay(true)) {
            startNextMedia();
        } else {
            displayParentalPinCode(new Function0<Unit>() { // from class: com.dotscreen.portal.ui.screens.player.VODPlaybackActivity.startNextMediaWhenAllowed.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    VODPlaybackActivity.this.startNextMedia();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlayerViewModelFactory.Companion.PlayerViewModelParams parsePlaybackDeeplink() {
        PlaybackReplayType playbackReplayTypeValueOf;
        Uri data;
        Uri data2;
        Uri data3;
        String queryParameter;
        Uri data4;
        Uri data5;
        Uri data6;
        Uri data7;
        String queryParameter2;
        Uri data8;
        Uri data9;
        Uri data10;
        Intent intent = getIntent();
        String queryParameter3 = null;
        String queryParameter4 = (intent == null || (data10 = intent.getData()) == null) ? null : data10.getQueryParameter("type");
        if (queryParameter4 != null) {
            String upperCase = queryParameter4.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
            playbackReplayTypeValueOf = PlaybackReplayType.valueOf(upperCase);
        } else {
            playbackReplayTypeValueOf = null;
        }
        Intent intent2 = getIntent();
        String queryParameter5 = (intent2 == null || (data9 = intent2.getData()) == null) ? null : data9.getQueryParameter(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
        Intent intent3 = getIntent();
        String queryParameter6 = (intent3 == null || (data8 = intent3.getData()) == null) ? null : data8.getQueryParameter("channel_id");
        Intent intent4 = getIntent();
        Long lValueOf = (intent4 == null || (data7 = intent4.getData()) == null || (queryParameter2 = data7.getQueryParameter(FmaDatabaseHelper.CONST_TIMESTAMP)) == null) ? null : Long.valueOf(Long.parseLong(queryParameter2));
        Intent intent5 = getIntent();
        String queryParameter7 = (intent5 == null || (data6 = intent5.getData()) == null) ? null : data6.getQueryParameter("recording_id");
        Intent intent6 = getIntent();
        boolean zAreEqual = Intrinsics.areEqual((intent6 == null || (data5 = intent6.getData()) == null) ? null : data5.getQueryParameter(ToolboxAnalyticsModuleKt.EVENT_RESUME), "true");
        Intent intent7 = getIntent();
        boolean zAreEqual2 = Intrinsics.areEqual((intent7 == null || (data4 = intent7.getData()) == null) ? null : data4.getQueryParameter("trailer"), "true");
        Intent intent8 = getIntent();
        Integer numValueOf = (intent8 == null || (data3 = intent8.getData()) == null || (queryParameter = data3.getQueryParameter("launchMode")) == null) ? null : Integer.valueOf(Integer.parseInt(queryParameter));
        Intent intent9 = getIntent();
        String queryParameter8 = (intent9 == null || (data2 = intent9.getData()) == null) ? null : data2.getQueryParameter(MimeTypes.BASE_TYPE_AUDIO);
        Intent intent10 = getIntent();
        if (intent10 != null && (data = intent10.getData()) != null) {
            queryParameter3 = data.getQueryParameter("subtitle");
        }
        return new PlayerViewModelFactory.Companion.PlayerViewModelParams(queryParameter5, queryParameter6, lValueOf, queryParameter7, playbackReplayTypeValueOf, zAreEqual, zAreEqual2, numValueOf, queryParameter8, queryParameter3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopCurrentMedia() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        Fragment fragmentFindFragmentById = supportFragmentManager.findFragmentById(R.id.playback_controls_fragment);
        if (fragmentFindFragmentById != null) {
            supportFragmentManager.beginTransaction().detach(fragmentFindFragmentById).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startNextMedia() {
        PlaybackFragment playbackFragment = new PlaybackFragment();
        this.mPlaybackFragment = playbackFragment;
        if (getViewModel().getHasNextMediaToPlay()) {
            getViewModel().selectNextMedia();
            getSupportFragmentManager().beginTransaction().replace(R.id.playback_controls_fragment, playbackFragment, PlaybackFragment.class.getSimpleName()).commit();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        PlaybackFragment playbackFragment = this.mPlaybackFragment;
        if (playbackFragment != null) {
            playbackFragment.resumePlayer();
        }
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        ((PortalApplication) applicationContext).setActiveOnDemandPlaybackSession(true);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        PlaybackFragment playbackFragment = this.mPlaybackFragment;
        if (playbackFragment != null) {
            playbackFragment.pausePlayer();
        }
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        ((PortalApplication) applicationContext).setActiveOnDemandPlaybackSession(false);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mPlaybackFragment = (PlaybackFragment) null;
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        PlaybackFragment playbackFragment;
        VOPlayer player;
        if (keyCode == 23) {
            Button skip_ads = (Button) _$_findCachedViewById(com.dotscreen.portal.R.id.skip_ads);
            Intrinsics.checkExpressionValueIsNotNull(skip_ads, "skip_ads");
            if (skip_ads.isEnabled() && (playbackFragment = this.mPlaybackFragment) != null && (player = playbackFragment.getPlayer()) != null) {
                player.cancelSecondaryContent();
            }
            return true;
        }
        if (keyCode != 86) {
            switch (keyCode) {
                case 102:
                    PlaybackFragment playbackFragment2 = this.mPlaybackFragment;
                    if (playbackFragment2 == null) {
                        Intrinsics.throwNpe();
                    }
                    playbackFragment2.skipToPrevious();
                    return true;
                case 103:
                    PlaybackFragment playbackFragment3 = this.mPlaybackFragment;
                    if (playbackFragment3 == null) {
                        Intrinsics.throwNpe();
                    }
                    playbackFragment3.skipToNext();
                    return true;
                case 104:
                    PlaybackFragment playbackFragment4 = this.mPlaybackFragment;
                    if (playbackFragment4 == null) {
                        Intrinsics.throwNpe();
                    }
                    playbackFragment4.rewind();
                    break;
                case 105:
                    PlaybackFragment playbackFragment5 = this.mPlaybackFragment;
                    if (playbackFragment5 == null) {
                        Intrinsics.throwNpe();
                    }
                    playbackFragment5.fastForward();
                    break;
            }
        } else {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.app.Activity
    public boolean onGenericMotionEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getAxisValue(17) > this.GAMEPAD_TRIGGER_INTENSITY_ON && !this.gamepadTriggerPressed) {
            PlaybackFragment playbackFragment = this.mPlaybackFragment;
            if (playbackFragment == null) {
                Intrinsics.throwNpe();
            }
            playbackFragment.rewind();
            this.gamepadTriggerPressed = true;
        } else if (event.getAxisValue(18) > this.GAMEPAD_TRIGGER_INTENSITY_ON && !this.gamepadTriggerPressed) {
            PlaybackFragment playbackFragment2 = this.mPlaybackFragment;
            if (playbackFragment2 == null) {
                Intrinsics.throwNpe();
            }
            playbackFragment2.fastForward();
            this.gamepadTriggerPressed = true;
        } else if (event.getAxisValue(17) < this.GAMEPAD_TRIGGER_INTENSITY_OFF && event.getAxisValue(18) < this.GAMEPAD_TRIGGER_INTENSITY_OFF) {
            this.gamepadTriggerPressed = false;
        }
        return super.onGenericMotionEvent(event);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Integer launchMode;
        Integer launchMode2;
        Integer launchMode3;
        LinearLayout layout_skip_next = (LinearLayout) _$_findCachedViewById(com.dotscreen.portal.R.id.layout_skip_next);
        Intrinsics.checkExpressionValueIsNotNull(layout_skip_next, "layout_skip_next");
        if (layout_skip_next.getVisibility() == 0) {
            interruptSkipNext();
            return;
        }
        if (getViewModel().getReplayType() == PlaybackReplayType.STOV) {
            if (getViewModel().getLaunchMode() != null && (((launchMode = getViewModel().getLaunchMode()) != null && launchMode.intValue() == 85) || (((launchMode2 = getViewModel().getLaunchMode()) != null && launchMode2.intValue() == 127) || ((launchMode3 = getViewModel().getLaunchMode()) != null && launchMode3.intValue() == 89)))) {
                PlaybackFragment playbackFragment = this.mPlaybackFragment;
                if (playbackFragment != null) {
                    playbackFragment.showReturnToLive(false);
                    return;
                }
                return;
            }
            if (getViewModel().getChannelId() != null) {
                PlaybackFragment playbackFragment2 = this.mPlaybackFragment;
                if (playbackFragment2 != null) {
                    playbackFragment2.showReturnToLive(true);
                    return;
                }
                return;
            }
            super.onBackPressed();
            return;
        }
        super.onBackPressed();
    }

    private final void interruptSkipNext() {
        getViewModel().userInterruptedCountdown();
        finish();
    }

    @Override // android.app.Activity
    public void onUserInteraction() {
        super.onUserInteraction();
        getViewModel().userInteracted();
    }

    private final void hidePlaybackStatusOverlay(boolean visibility) {
        if (visibility) {
            TextView status_player = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.status_player);
            Intrinsics.checkExpressionValueIsNotNull(status_player, "status_player");
            status_player.setVisibility(8);
        }
    }

    public final void onError(Throwable err) {
        if (err != null) {
            BaseFragmentActivity.displayErrorMessage$default(this, (Exception) err, 0, 2, null);
            finish();
        }
    }
}
