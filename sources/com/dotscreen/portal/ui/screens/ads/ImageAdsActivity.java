package com.dotscreen.portal.ui.screens.ads;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.utils.String_ExtensionKt;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: ImageAdsActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/ui/screens/ads/ImageAdsActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "runnable", "Ljava/lang/Runnable;", "getRunnable", "()Ljava/lang/Runnable;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ImageAdsActivity extends BaseFragmentActivity {
    private HashMap _$_findViewCache;
    private final String TAG = ImageAdsActivity.class.getSimpleName();
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() { // from class: com.dotscreen.portal.ui.screens.ads.ImageAdsActivity$runnable$1
        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.finish();
        }
    };

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

    public final Runnable getRunnable() {
        return this.runnable;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_image);
        String url = getIntent().getStringExtra(ImagesContract.URL);
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = getIntent().getIntExtra("time", 7);
        if (intRef.element < 0) {
            intRef.element = 7;
        }
        RequestManager requestManagerWith = Glide.with(getApplicationContext());
        Intrinsics.checkExpressionValueIsNotNull(requestManagerWith, "Glide.with(applicationContext)");
        Intrinsics.checkExpressionValueIsNotNull(url, "url");
        if (String_ExtensionKt.isGif(url)) {
            requestManagerWith.asGif();
        }
        requestManagerWith.load(url).listener(new RequestListener<Drawable>() { // from class: com.dotscreen.portal.ui.screens.ads.ImageAdsActivity.onCreate.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                ImageAdsActivity.this.handler.postDelayed(ImageAdsActivity.this.getRunnable(), ((long) intRef.element) * 1000);
                return false;
            }
        }).into((ImageView) _$_findCachedViewById(com.dotscreen.portal.R.id.image_ads));
    }
}
