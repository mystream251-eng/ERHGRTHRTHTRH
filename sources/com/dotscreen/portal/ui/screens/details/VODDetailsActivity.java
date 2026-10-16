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
import androidx.tvprovider.media.tv.TvContractCompat;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.VODDetailsViewModel;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.ui.ImageLoaderManager;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VODDetailsActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0018\u0010\u0017\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002J\u0012\u0010\u0018\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/VODDetailsActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "audioTracks", "", "Landroid/media/tv/TvTrackInfo;", "captionTracks", "contentId", "", "entityType", "fromSearch", "", "searchResultId", "selectedAudioTrackId", "selectedCaptionTrackId", "createBackgroundBitmap", "Landroid/graphics/Bitmap;", "source", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "registerSearchEvent", "startActivity", "intent", "Landroid/content/Intent;", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODDetailsActivity extends BaseFragmentActivity {
    public static final String EXTRA_CALLING_INTENT = "extra_calling_intent";
    private static Bitmap placeholderBitmap;
    private HashMap _$_findViewCache;
    private List<TvTrackInfo> audioTracks;
    private List<TvTrackInfo> captionTracks;
    private String contentId = "";
    private String entityType;
    private boolean fromSearch;
    private String searchResultId;
    private String selectedAudioTrackId;
    private String selectedCaptionTrackId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = VODDetailsActivity.class.getSimpleName();

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

    /* JADX INFO: compiled from: VODDetailsActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/VODDetailsActivity$Companion;", "", "()V", "EXTRA_CALLING_INTENT", "", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "placeholderBitmap", "Landroid/graphics/Bitmap;", "getPlaceholderBitmap", "()Landroid/graphics/Bitmap;", "setPlaceholderBitmap", "(Landroid/graphics/Bitmap;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Bitmap getPlaceholderBitmap() {
            return VODDetailsActivity.placeholderBitmap;
        }

        public final void setPlaceholderBitmap(Bitmap bitmap) {
            VODDetailsActivity.placeholderBitmap = bitmap;
        }

        public final String getTAG() {
            return VODDetailsActivity.TAG;
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vod_details);
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
        if (intent != null) {
            Uri data = intent.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            String queryParameter = data.getQueryParameter(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
            if (queryParameter == null) {
                queryParameter = "";
            }
            boolean booleanQueryParameter = data.getBooleanQueryParameter("adult_browsing", false);
            boolean booleanQueryParameter2 = data.getBooleanQueryParameter("from_search", false);
            this.fromSearch = booleanQueryParameter2;
            if (booleanQueryParameter2) {
                this.entityType = data.getQueryParameter("entity_type");
                this.searchResultId = data.getQueryParameter("search_result_id");
            }
            this.contentId = queryParameter;
            setFromAdultBrowsing$MegaCable_Portal_0_75_1_396__prodRelease(booleanQueryParameter);
            this.audioTracks = intent.getParcelableArrayListExtra("extra_audio_tracks");
            this.selectedAudioTrackId = intent.getStringExtra("extra_selected_audio_track_id");
            this.captionTracks = intent.getParcelableArrayListExtra("extra_caption_tracks");
            this.selectedCaptionTrackId = intent.getStringExtra("extra_selected_caption_track_id");
        }
        int i = Build.VERSION.SDK_INT;
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_browse_fragment);
        if (frameLayout != null) {
            frameLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsActivity.onCreate.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Fragment fragmentFindFragmentById = VODDetailsActivity.this.getSupportFragmentManager().findFragmentById(R.id.browse_fragment);
                    if (fragmentFindFragmentById == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.VODDetailsFragment");
                    }
                    VODDetailsFragment vODDetailsFragment = (VODDetailsFragment) fragmentFindFragmentById;
                    vODDetailsFragment.setStartY(top);
                    vODDetailsFragment.setFromAdultBrowsing(VODDetailsActivity.this.getFromAdultBrowsing());
                    if (view != null) {
                        view.removeOnLayoutChangeListener(this);
                    }
                }
            });
        }
        setLoaderVisible(true);
        ViewModel viewModel = ViewModelProviders.of(this, new VODDetailsViewModel.Factory(this, this.contentId)).get(VODDetailsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ilsViewModel::class.java)");
        ((VODDetailsViewModel) viewModel).getValue().observe(this, new AnonymousClass2());
        View viewFindViewById = findViewById(R.id.root);
        if (viewFindViewById != null) {
            viewFindViewById.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.details.VODDetailsActivity$onCreate$2, reason: invalid class name */
    /* JADX INFO: compiled from: VODDetailsActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "data", "Lcom/dotscreen/portal/model/VODDetailsViewModel$Result;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass2<T> implements Observer<VODDetailsViewModel.Result> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(final VODDetailsViewModel.Result result) {
            if (result == null) {
                Toast.makeText(VODDetailsActivity.this, R.string.noContent, 0).show();
                VODDetailsActivity.this.finish();
            }
            if (result != null) {
                if (VODDetailsActivity.this.fromSearch && VODDetailsActivity.this.searchResultId != null && VODDetailsActivity.this.entityType != null) {
                    VODDetailsActivity vODDetailsActivity = VODDetailsActivity.this;
                    String str = vODDetailsActivity.entityType;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    String str2 = VODDetailsActivity.this.searchResultId;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    vODDetailsActivity.registerSearchEvent(str, str2);
                }
                BaseFragmentActivity.displayParentalPinCodeIfNeeded$default(VODDetailsActivity.this, result.getMedia(), null, new Function1<Boolean, Unit>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsActivity$onCreate$2$$special$$inlined$let$lambda$1
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
                        Fragment fragmentFindFragmentById = VODDetailsActivity.this.getSupportFragmentManager().findFragmentById(R.id.browse_fragment);
                        if (fragmentFindFragmentById == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.VODDetailsFragment");
                        }
                        ((VODDetailsFragment) fragmentFindFragmentById).setDetailsResult(result);
                        VODDetailsActivity.this.setLoaderVisible(false);
                        Artwork stillArtwork = result.getMedia().getStillArtwork();
                        final String strValueOf = String.valueOf(stillArtwork != null ? Artwork.urlWithSize$default(stillArtwork, new Point(VODDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_background_image_width), VODDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_background_image_height)), 0.0d, 2, null) : null);
                        final ImageView imageView = (ImageView) VODDetailsActivity.this.findViewById(R.id.background_image);
                        if (imageView != null) {
                            new ImageLoaderManager(VODDetailsActivity.this).loadImageAsBitmap(strValueOf, (3064 & 2) != 0 ? (ImageView) null : imageView, (3064 & 4) != 0 ? false : true, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : new RequestListener<Bitmap>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsActivity$onCreate$2$$special$$inlined$let$lambda$1.1
                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                    ImageView imageView2 = imageView;
                                    VODDetailsActivity vODDetailsActivity2 = VODDetailsActivity.this;
                                    Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(VODDetailsActivity.this.getResources(), R.drawable.ph_horizontal);
                                    Intrinsics.checkExpressionValueIsNotNull(bitmapDecodeResource, "BitmapFactory.decodeReso…R.drawable.ph_horizontal)");
                                    imageView2.setImageBitmap(vODDetailsActivity2.createBackgroundBitmap(bitmapDecodeResource));
                                    return true;
                                }

                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z2) {
                                    if (bitmap == null) {
                                        return true;
                                    }
                                    imageView.setImageBitmap(VODDetailsActivity.this.createBackgroundBitmap(bitmap));
                                    return true;
                                }
                            }, (3064 & 1024) == 0 ? false : false, (3064 & 2048) != 0 ? (Drawable) null : null);
                        }
                        TextView textView = (TextView) VODDetailsActivity.this.findViewById(R.id.details_title);
                        if (textView != null) {
                            textView.setText((!(result.getMedia() instanceof Episode) || result.getSeries() == null) ? result.getMedia().getTitle() : result.getSeries().getTitle());
                        }
                        StringBuilder sb = new StringBuilder();
                        if (result.getSeason() != null) {
                            Media media = result.getMedia();
                            if (media == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Episode");
                            }
                            Episode episode = (Episode) media;
                            sb.append(VODDetailsActivity.this.getString(R.string.details_subtitle_season, new Object[]{Integer.valueOf(result.getSeason().getSeasonNumber())}));
                            sb.append(VODDetailsActivity.this.getString(R.string.details_subtitle_separator));
                            sb.append(VODDetailsActivity.this.getString(R.string.details_subtitle_episode, new Object[]{Integer.valueOf(episode.getEpisodeNumber())}));
                            StringBuilder sb2 = sb;
                            if (sb2.length() > 0) {
                                sb.append(VODDetailsActivity.this.getString(R.string.details_subtitle_separator_episode));
                            }
                            sb.append(VODDetailsActivity.this.getString(R.string.details_subtitle_subtitle, new Object[]{episode.getTitle()}));
                            if (sb2.length() > 0) {
                                TextView textView2 = (TextView) VODDetailsActivity.this.findViewById(R.id.details_subTitle);
                                if (textView2 != null) {
                                    textView2.setVisibility(0);
                                    textView2.setText(sb.toString());
                                    return;
                                }
                                return;
                            }
                            TextView textView3 = (TextView) VODDetailsActivity.this.findViewById(R.id.details_subTitle);
                            if (textView3 != null) {
                                textView3.setVisibility(8);
                            }
                        }
                    }
                }, 2, null);
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
        ((MegacableUserService) userService).registerSearchEvent(entityType, searchResultId).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsActivity.registerSearchEvent.1
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsActivity.registerSearchEvent.2
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

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        Uri data;
        String string;
        if (intent != null && (data = intent.getData()) != null && (string = data.toString()) != null && StringsKt.contains$default((CharSequence) string, (CharSequence) "/details?", false, 2, (Object) null)) {
            intent.putExtra("extra_calling_intent", getIntent());
            finish();
        }
        super.startActivity(intent);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("extra_calling_intent")) {
            finish();
            Intent intent2 = getIntent();
            super.startActivity(intent2 != null ? (Intent) intent2.getParcelableExtra("extra_calling_intent") : null);
            return;
        }
        super.onBackPressed();
    }
}
