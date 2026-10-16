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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
import com.dotscreen.bokit.model.TVSeason;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.CatchupDetailsViewModel;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.ui.ImageLoaderManager;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: CatchupDetailsActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "backgroundDesignPath", "", "getBackgroundDesignPath", "()Ljava/lang/String;", "channelID", "contentID", "entityType", "fromSearch", "", "searchResultId", "startDate", "", "createBackgroundBitmap", "Landroid/graphics/Bitmap;", "source", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "registerSearchEvent", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupDetailsActivity extends BaseFragmentActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CatchupDetailsActivity.class.getSimpleName();
    private static Bitmap placeholderBitmap;
    private HashMap _$_findViewCache;
    private final String backgroundDesignPath;
    private String entityType;
    private boolean fromSearch;
    private String searchResultId;
    private long startDate;
    private String contentID = "";
    private String channelID = "";

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

    /* JADX INFO: compiled from: CatchupDetailsActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsActivity$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "placeholderBitmap", "Landroid/graphics/Bitmap;", "getPlaceholderBitmap", "()Landroid/graphics/Bitmap;", "setPlaceholderBitmap", "(Landroid/graphics/Bitmap;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Bitmap getPlaceholderBitmap() {
            return CatchupDetailsActivity.placeholderBitmap;
        }

        public final void setPlaceholderBitmap(Bitmap bitmap) {
            CatchupDetailsActivity.placeholderBitmap = bitmap;
        }

        public final String getTAG() {
            return CatchupDetailsActivity.TAG;
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    protected String getBackgroundDesignPath() {
        return this.backgroundDesignPath;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catchup_details);
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
            Intrinsics.checkExpressionValueIsNotNull(queryParameter, "uri!!.getQueryParameter(\"content_id\")");
            this.contentID = queryParameter;
            setFromAdultBrowsing$MegaCable_Portal_0_75_1_396__prodRelease(data.getBooleanQueryParameter("adult_browsing", false));
            boolean booleanQueryParameter = data.getBooleanQueryParameter("from_search", false);
            this.fromSearch = booleanQueryParameter;
            if (booleanQueryParameter) {
                this.entityType = data.getQueryParameter("entity_type");
                this.searchResultId = data.getQueryParameter("search_result_id");
            }
            Log.d(TAG, "Show media content id #" + this.contentID);
        }
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_browse_fragment);
        if (frameLayout != null) {
            frameLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsActivity.onCreate.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Fragment fragmentFindFragmentById = CatchupDetailsActivity.this.getSupportFragmentManager().findFragmentById(R.id.browse_fragment);
                    if (fragmentFindFragmentById == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment");
                    }
                    CatchupDetailsFragment catchupDetailsFragment = (CatchupDetailsFragment) fragmentFindFragmentById;
                    catchupDetailsFragment.setStartY(top);
                    catchupDetailsFragment.setFromAdultBrowsing(CatchupDetailsActivity.this.getFromAdultBrowsing());
                    if (view != null) {
                        view.removeOnLayoutChangeListener(this);
                    }
                }
            });
        }
        setLoaderVisible(true);
        ViewModel viewModel = ViewModelProviders.of(this, new CatchupDetailsViewModel.Factory(this, this.contentID)).get(CatchupDetailsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ilsViewModel::class.java)");
        ((CatchupDetailsViewModel) viewModel).getValue().observe(this, new AnonymousClass2());
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.details.CatchupDetailsActivity$onCreate$2, reason: invalid class name */
    /* JADX INFO: compiled from: CatchupDetailsActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "result", "Lcom/dotscreen/portal/model/CatchupDetailsViewModel$Result;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass2<T> implements Observer<CatchupDetailsViewModel.Result> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(final CatchupDetailsViewModel.Result result) {
            if (result != null) {
                if (CatchupDetailsActivity.this.fromSearch && CatchupDetailsActivity.this.searchResultId != null && CatchupDetailsActivity.this.entityType != null) {
                    CatchupDetailsActivity catchupDetailsActivity = CatchupDetailsActivity.this;
                    String str = catchupDetailsActivity.entityType;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    String str2 = CatchupDetailsActivity.this.searchResultId;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    catchupDetailsActivity.registerSearchEvent(str, str2);
                }
                BaseFragmentActivity.displayParentalPinCodeIfNeeded$default(CatchupDetailsActivity.this, result.getMedia(), null, new Function1<Boolean, Unit>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsActivity$onCreate$2$$special$$inlined$let$lambda$1
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
                        Maybe<TVSeason> maybeSubscribeOn;
                        Maybe<TVSeason> maybeObserveOn;
                        Disposable disposableSubscribe;
                        Fragment fragmentFindFragmentById = CatchupDetailsActivity.this.getSupportFragmentManager().findFragmentById(R.id.browse_fragment);
                        if (fragmentFindFragmentById == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment");
                        }
                        ((CatchupDetailsFragment) fragmentFindFragmentById).setDetailsResult(result);
                        CatchupDetailsActivity.this.setLoaderVisible(false);
                        Artwork squareArtwork = result.getChannel().getSquareArtwork();
                        String strValueOf = String.valueOf(squareArtwork != null ? Artwork.urlWithSize$default(squareArtwork, new Point(CatchupDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_channel_logo_width), CatchupDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_channel_logo_width)), 0.0d, 2, null) : null);
                        ImageView imageView = (ImageView) CatchupDetailsActivity.this.findViewById(R.id.channel_logo);
                        if (imageView != null) {
                            new ImageLoaderManager(CatchupDetailsActivity.this).loadImageAsBitmap(strValueOf, (3064 & 2) != 0 ? (ImageView) null : imageView, (3064 & 4) != 0 ? false : false, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : null, (3064 & 1024) == 0, (3064 & 2048) != 0 ? (Drawable) null : null);
                        }
                        Artwork stillArtwork = result.getMedia().getStillArtwork();
                        final String strValueOf2 = String.valueOf(stillArtwork != null ? Artwork.urlWithSize$default(stillArtwork, new Point(CatchupDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_background_image_width), CatchupDetailsActivity.this.getResources().getDimensionPixelSize(R.dimen.details_background_image_height)), 0.0d, 2, null) : null);
                        final ImageView imageView2 = (ImageView) CatchupDetailsActivity.this.findViewById(R.id.background_image);
                        if (imageView2 != null) {
                            CatchupDetailsActivity catchupDetailsActivity2 = CatchupDetailsActivity.this;
                            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(CatchupDetailsActivity.this.getResources(), R.drawable.ph_horizontal);
                            Intrinsics.checkExpressionValueIsNotNull(bitmapDecodeResource, "BitmapFactory.decodeReso…R.drawable.ph_horizontal)");
                            imageView2.setImageBitmap(catchupDetailsActivity2.createBackgroundBitmap(bitmapDecodeResource));
                            new ImageLoaderManager(CatchupDetailsActivity.this).loadImageAsBitmap(strValueOf2, (3064 & 2) != 0 ? (ImageView) null : null, (3064 & 4) != 0 ? false : true, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : new RequestListener<Bitmap>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsActivity$onCreate$2$$special$$inlined$let$lambda$1.1
                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z2) {
                                    if (bitmap == null) {
                                        return false;
                                    }
                                    imageView2.setImageBitmap(CatchupDetailsActivity.this.createBackgroundBitmap(bitmap));
                                    return false;
                                }
                            }, (3064 & 1024) == 0 ? false : false, (3064 & 2048) != 0 ? (Drawable) null : null);
                        }
                        TextView textView = (TextView) CatchupDetailsActivity.this.findViewById(R.id.channel_number);
                        if (textView != null) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            String str3 = String.format("%04d", Arrays.copyOf(new Object[]{Integer.valueOf(result.getChannel().getZappingNumber())}, 1));
                            Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
                            textView.setText(str3);
                        }
                        TextView textView2 = (TextView) CatchupDetailsActivity.this.findViewById(R.id.channel_name);
                        if (textView2 != null) {
                            textView2.setText(result.getChannel().getTitle());
                        }
                        TextView textView3 = (TextView) CatchupDetailsActivity.this.findViewById(R.id.details_title);
                        if (textView3 != null) {
                            textView3.setText(result.getMedia().getTitle());
                        }
                        final StringBuilder sb = new StringBuilder();
                        if (result.getMedia() instanceof Episode) {
                            final Media media = result.getMedia();
                            Maybe<TVSeason> season = ((Episode) media).getSeason();
                            if (season != null && (maybeSubscribeOn = season.subscribeOn(Schedulers.io())) != null && (maybeObserveOn = maybeSubscribeOn.observeOn(AndroidSchedulers.mainThread())) != null && (disposableSubscribe = maybeObserveOn.subscribe(new Consumer<TVSeason>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsActivity$onCreate$2$$special$$inlined$let$lambda$1.2
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(TVSeason tVSeason) {
                                    StringBuilder sb2 = sb;
                                    sb2.append(CatchupDetailsActivity.this.getString(R.string.details_subtitle_season, new Object[]{Integer.valueOf(tVSeason.getSeasonNumber())}));
                                    sb2.append(CatchupDetailsActivity.this.getString(R.string.details_subtitle_separator));
                                    sb2.append(CatchupDetailsActivity.this.getString(R.string.details_subtitle_episode, new Object[]{Integer.valueOf(((Episode) media).getEpisodeNumber())}));
                                }
                            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsActivity$onCreate$2$1$1$4
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Throwable th) {
                                    th.printStackTrace();
                                }
                            })) != null) {
                                LifecycleAwareDisposableKt.addTo(disposableSubscribe, CatchupDetailsActivity.this.getDisposable());
                            }
                            String title = media.getTitle();
                            if (title == null) {
                                Intrinsics.throwNpe();
                            }
                            if (title.length() > 0) {
                                if (sb.length() > 0) {
                                    sb.append(CatchupDetailsActivity.this.getString(R.string.details_subtitle_separator));
                                }
                                sb.append(CatchupDetailsActivity.this.getString(R.string.details_subtitle_subtitle, new Object[]{media.getTitle()}));
                            }
                        }
                        if (sb.length() > 0) {
                            TextView textView4 = (TextView) CatchupDetailsActivity.this.findViewById(R.id.details_subTitle);
                            if (textView4 != null) {
                                textView4.setVisibility(0);
                                textView4.setText(sb.toString());
                                return;
                            }
                            return;
                        }
                        TextView textView5 = (TextView) CatchupDetailsActivity.this.findViewById(R.id.details_subTitle);
                        if (textView5 != null) {
                            textView5.setVisibility(8);
                        }
                    }
                }, 2, null);
                return;
            }
            Toast.makeText(CatchupDetailsActivity.this, R.string.noContent, 0).show();
            CatchupDetailsActivity.this.finish();
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
        ((MegacableUserService) userService).registerSearchEvent(entityType, searchResultId).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsActivity.registerSearchEvent.1
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsActivity.registerSearchEvent.2
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
