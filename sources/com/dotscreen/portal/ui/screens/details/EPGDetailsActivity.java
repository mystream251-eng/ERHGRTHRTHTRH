package com.dotscreen.portal.ui.screens.details;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.tv.TvTrackInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.ImageLoaderManager;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: EPGDetailsActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0018\u0010#\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006%"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "askPinCodeToAccessDetailsIfNeeded", "", "audioTracks", "", "Landroid/media/tv/TvTrackInfo;", "backgroundDesignPath", "", "getBackgroundDesignPath", "()Ljava/lang/String;", "captionTracks", "channelID", "entityType", "fromSearch", "recordingId", "searchResultId", "selectedAudioTrackId", "selectedCaptionTrackId", "startDate", "", "Ljava/lang/Long;", "startedFromLiveTv", "getStartedFromLiveTv", "()Z", "setStartedFromLiveTv", "(Z)V", "createBackgroundBitmap", "Landroid/graphics/Bitmap;", "source", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "registerSearchEvent", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class EPGDetailsActivity extends BaseFragmentActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = EPGDetailsActivity.class.getSimpleName();
    private static Bitmap placeholderBitmap;
    private HashMap _$_findViewCache;
    private boolean askPinCodeToAccessDetailsIfNeeded = true;
    private List<TvTrackInfo> audioTracks;
    private final String backgroundDesignPath;
    private List<TvTrackInfo> captionTracks;
    private String channelID;
    private String entityType;
    private boolean fromSearch;
    private String recordingId;
    private String searchResultId;
    private String selectedAudioTrackId;
    private String selectedCaptionTrackId;
    private Long startDate;
    private boolean startedFromLiveTv;

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

    /* JADX INFO: compiled from: EPGDetailsActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsActivity$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "placeholderBitmap", "Landroid/graphics/Bitmap;", "getPlaceholderBitmap", "()Landroid/graphics/Bitmap;", "setPlaceholderBitmap", "(Landroid/graphics/Bitmap;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Bitmap getPlaceholderBitmap() {
            return EPGDetailsActivity.placeholderBitmap;
        }

        public final void setPlaceholderBitmap(Bitmap bitmap) {
            EPGDetailsActivity.placeholderBitmap = bitmap;
        }

        public final String getTAG() {
            return EPGDetailsActivity.TAG;
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    protected String getBackgroundDesignPath() {
        return this.backgroundDesignPath;
    }

    public final boolean getStartedFromLiveTv() {
        return this.startedFromLiveTv;
    }

    public final void setStartedFromLiveTv(boolean z) {
        this.startedFromLiveTv = z;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        View viewFindViewById;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (placeholderBitmap == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ph_horizontal, null);
            if (drawable == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            }
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "(resources.getDrawable(R…as BitmapDrawable).bitmap");
            placeholderBitmap = createBackgroundBitmap(bitmap);
        }
        Intent intent = getIntent();
        this.startedFromLiveTv = false;
        if (intent != null) {
            Uri data = intent.getData();
            String queryParameter = data != null ? data.getQueryParameter("start") : null;
            this.channelID = data != null ? data.getQueryParameter("channel_id") : null;
            this.recordingId = data != null ? data.getQueryParameter("recording_id") : null;
            setFromAdultBrowsing$MegaCable_Portal_0_75_1_396__prodRelease(data != null ? data.getBooleanQueryParameter("adult_browsing", false) : false);
            this.askPinCodeToAccessDetailsIfNeeded = data != null ? data.getBooleanQueryParameter("askPinCodeToAccessDetailsIfNeeded", true) : true;
            boolean booleanQueryParameter = data.getBooleanQueryParameter("from_search", false);
            this.fromSearch = booleanQueryParameter;
            if (booleanQueryParameter) {
                this.entityType = data.getQueryParameter("entity_type");
                this.searchResultId = data.getQueryParameter("search_result_id");
            }
            this.startDate = queryParameter != null ? Long.valueOf(queryParameter) : null;
            Log.d(TAG, "Show media channel id #" + this.channelID);
            Log.d(TAG, "Show media start #" + this.startDate);
            boolean booleanExtra = intent.getBooleanExtra("extra_started_from_live_tv", false);
            this.startedFromLiveTv = booleanExtra;
            if (booleanExtra) {
                setTheme(2131951630);
            } else {
                setTheme(2131951631);
            }
            this.audioTracks = intent.getParcelableArrayListExtra("extra_audio_tracks");
            this.selectedAudioTrackId = intent.getStringExtra("extra_selected_audio_track_id");
            this.captionTracks = intent.getParcelableArrayListExtra("extra_caption_tracks");
            this.selectedCaptionTrackId = intent.getStringExtra("extra_selected_caption_track_id");
        }
        int i = Build.VERSION.SDK_INT;
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_browse_fragment);
        if (frameLayout != null) {
            frameLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsActivity.onCreate.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Fragment fragmentFindFragmentById = EPGDetailsActivity.this.getSupportFragmentManager().findFragmentById(R.id.browse_fragment);
                    if (fragmentFindFragmentById == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.EPGDetailsFragment");
                    }
                    ((EPGDetailsFragment) fragmentFindFragmentById).setStartY(top);
                    if (view != null) {
                        view.removeOnLayoutChangeListener(this);
                    }
                }
            });
        }
        setLoaderVisible(true);
        ViewModel viewModel = ViewModelProviders.of(this, new EPGDetailsViewModel.Factory(this, this.channelID, this.startDate, this.recordingId)).get(EPGDetailsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ilsViewModel::class.java)");
        ((EPGDetailsViewModel) viewModel).getValue().observe(this, new AnonymousClass2());
        if (this.startedFromLiveTv || (viewFindViewById = findViewById(R.id.root)) == null) {
            return;
        }
        viewFindViewById.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.details.EPGDetailsActivity$onCreate$2, reason: invalid class name */
    /* JADX INFO: compiled from: EPGDetailsActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "result", "Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Result;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass2<T> implements Observer<EPGDetailsViewModel.Result> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(final EPGDetailsViewModel.Result result) {
            Log.d(EPGDetailsActivity.INSTANCE.getTAG(), "result: " + result);
            if (result == null) {
                Toast.makeText(EPGDetailsActivity.this, R.string.noContent, 0).show();
                EPGDetailsActivity.this.finish();
            }
            if (result != null) {
                if (EPGDetailsActivity.this.fromSearch && EPGDetailsActivity.this.searchResultId != null && EPGDetailsActivity.this.entityType != null) {
                    EPGDetailsActivity ePGDetailsActivity = EPGDetailsActivity.this;
                    String str = ePGDetailsActivity.entityType;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    String str2 = EPGDetailsActivity.this.searchResultId;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    ePGDetailsActivity.registerSearchEvent(str, str2);
                }
                EPGDetailsActivity.this.displayParentalPinCodeIfNeeded(result.getProgram(), Boolean.valueOf(EPGDetailsActivity.this.askPinCodeToAccessDetailsIfNeeded), new Function1<Boolean, Unit>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsActivity$onCreate$2$$special$$inlined$let$lambda$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        EPGDetailsActivity.this.setFromAdultBrowsing$MegaCable_Portal_0_75_1_396__prodRelease(z);
                        if (result.getAiring().isLive()) {
                            Intent intent = new Intent();
                            intent.setAction(CommonUtils.ACTION_UNLOCK_SCREEN);
                            EPGDetailsActivity.this.sendBroadcast(intent);
                        }
                        Fragment fragmentFindFragmentById = EPGDetailsActivity.this.getSupportFragmentManager().findFragmentById(R.id.browse_fragment);
                        if (fragmentFindFragmentById == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.EPGDetailsFragment");
                        }
                        EPGDetailsFragment ePGDetailsFragment = (EPGDetailsFragment) fragmentFindFragmentById;
                        ePGDetailsFragment.setDetailsResult(result);
                        ePGDetailsFragment.setAudioTracks(EPGDetailsActivity.this.audioTracks);
                        ePGDetailsFragment.setSelectedAudioTrackId(EPGDetailsActivity.this.selectedAudioTrackId);
                        ePGDetailsFragment.setCaptionTracks(EPGDetailsActivity.this.captionTracks);
                        ePGDetailsFragment.setSelectedCaptionTrackId(EPGDetailsActivity.this.selectedCaptionTrackId);
                        ePGDetailsFragment.setFromAdultBrowsing(EPGDetailsActivity.this.getFromAdultBrowsing());
                        EPGDetailsActivity.this.setLoaderVisible(false);
                        Artwork squareArtwork = result.getAiring().getChannel().getSquareArtwork();
                        String strValueOf = String.valueOf(squareArtwork != null ? Artwork.urlWithSize$default(squareArtwork, new Point(EPGDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_channel_logo_width), EPGDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_channel_logo_width)), 0.0d, 2, null) : null);
                        ImageView imageView = (ImageView) EPGDetailsActivity.this.findViewById(R.id.channel_logo);
                        if (imageView != null) {
                            new ImageLoaderManager(EPGDetailsActivity.this).loadImageAsBitmap(strValueOf, (3064 & 2) != 0 ? (ImageView) null : imageView, (3064 & 4) != 0 ? false : true, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : null, (3064 & 1024) == 0, (3064 & 2048) != 0 ? (Drawable) null : null);
                        }
                        Artwork stillArtwork = result.getProgram().getStillArtwork();
                        final String strValueOf2 = String.valueOf(stillArtwork != null ? Artwork.urlWithSize$default(stillArtwork, new Point(EPGDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_background_image_width), EPGDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_background_image_height)), 0.0d, 2, null) : null);
                        final ImageView imageView2 = (ImageView) EPGDetailsActivity.this.findViewById(R.id.background_image);
                        if (imageView2 != null) {
                            EPGDetailsActivity ePGDetailsActivity2 = EPGDetailsActivity.this;
                            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(EPGDetailsActivity.this.getResources(), R.drawable.ph_horizontal);
                            Intrinsics.checkExpressionValueIsNotNull(bitmapDecodeResource, "BitmapFactory.decodeReso…R.drawable.ph_horizontal)");
                            imageView2.setImageBitmap(ePGDetailsActivity2.createBackgroundBitmap(bitmapDecodeResource));
                            new ImageLoaderManager(EPGDetailsActivity.this).loadImageAsBitmap(strValueOf2, (3064 & 2) != 0 ? (ImageView) null : imageView2, (3064 & 4) != 0 ? false : false, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : new RequestListener<Bitmap>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsActivity$onCreate$2$$special$$inlined$let$lambda$1.1
                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z2) {
                                    if (bitmap == null) {
                                        return true;
                                    }
                                    imageView2.setImageBitmap(EPGDetailsActivity.this.createBackgroundBitmap(bitmap));
                                    return true;
                                }
                            }, (3064 & 1024) == 0 ? false : false, (3064 & 2048) != 0 ? (Drawable) null : null);
                        }
                        TextView textView = (TextView) EPGDetailsActivity.this.findViewById(R.id.channel_number);
                        if (textView != null) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            String str3 = String.format("%04d", Arrays.copyOf(new Object[]{Integer.valueOf(result.getAiring().getChannel().getZappingNumber())}, 1));
                            Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
                            textView.setText(str3);
                        }
                        TextView textView2 = (TextView) EPGDetailsActivity.this.findViewById(R.id.channel_name);
                        if (textView2 != null) {
                            textView2.setText(result.getAiring().getChannel().getTitle());
                        }
                        TextView textView3 = (TextView) EPGDetailsActivity.this.findViewById(R.id.details_title);
                        if (textView3 != null) {
                            textView3.setText(result.getProgram().getTitle());
                        }
                        TextView textView4 = (TextView) EPGDetailsActivity.this.findViewById(R.id.tag);
                        if (textView4 != null) {
                            if (result.getAiring().getChannel().getCapabilities().getCatchup() && result.getAiring().getChannel().getCapabilities().getStartover()) {
                                textView4.setVisibility(0);
                            } else {
                                textView4.setVisibility(8);
                            }
                        }
                        StringBuilder sb = new StringBuilder();
                        if (result.getProgram().getSeasonNumber() != null && result.getProgram().getEpisodeNumber() != null) {
                            sb.append(EPGDetailsActivity.this.getString(R.string.details_subtitle_season, new Object[]{result.getProgram().getSeasonNumber()}));
                            sb.append(EPGDetailsActivity.this.getString(R.string.details_subtitle_separator));
                            sb.append(EPGDetailsActivity.this.getString(R.string.details_subtitle_episode, new Object[]{result.getProgram().getEpisodeNumber()}));
                        }
                        String episodeTitle = result.getProgram().getEpisodeTitle();
                        if (episodeTitle != null) {
                            if (episodeTitle.length() > 0) {
                                if (sb.length() > 0) {
                                    sb.append(EPGDetailsActivity.this.getString(R.string.details_subtitle_separator));
                                }
                                sb.append(EPGDetailsActivity.this.getString(R.string.details_subtitle_subtitle, new Object[]{result.getProgram().getEpisodeTitle()}));
                            }
                        }
                        if (sb.length() > 0) {
                            TextView textView5 = (TextView) EPGDetailsActivity.this.findViewById(R.id.details_subTitle);
                            if (textView5 != null) {
                                textView5.setVisibility(0);
                                textView5.setText(sb.toString());
                                return;
                            }
                            return;
                        }
                        TextView textView6 = (TextView) EPGDetailsActivity.this.findViewById(R.id.details_subTitle);
                        if (textView6 != null) {
                            textView6.setVisibility(8);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerSearchEvent(String entityType, String searchResultId) {
        Application application = getApplication();
        if (application == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        UserService userService = ((PortalApplication) application).getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        ((MegacableUserService) userService).registerSearchEvent(entityType, searchResultId).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsActivity.registerSearchEvent.1
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsActivity.registerSearchEvent.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap createBackgroundBitmap(Bitmap source) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.details_bg_mask), source.getWidth(), source.getHeight(), true);
        Bitmap result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(result);
        Paint paint = new Paint(1);
        paint.setColorFilter(new LightingColorFilter((int) 4286545791L, 0));
        Paint paint2 = new Paint(1);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(source, 0.0f, 0.0f, paint);
        canvas.drawBitmap(bitmapCreateScaledBitmap, 0.0f, 0.0f, paint2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }
}
