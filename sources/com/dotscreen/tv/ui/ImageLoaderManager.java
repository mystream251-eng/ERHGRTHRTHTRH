package com.dotscreen.tv.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.dotscreen.tv.common.utils.CommonUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ImageLoaderManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rJ~\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u00162\u0010\b\u0002\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001eJ\u0094\u0001\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u00162\u0010\b\u0002\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001e2\b\b\u0002\u0010!\u001a\u00020\u00142\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u000eJ,\u0010#\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u0016R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/dotscreen/tv/ui/ImageLoaderManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "cancelRequest", "", "request", "Lcom/bumptech/glide/RequestBuilder;", "Landroid/graphics/drawable/Drawable;", "loadImage", "requestUrl", "target", "Landroid/widget/ImageView;", "withCrossFade", "", "crossFadeDuration", "", "maxWidth", "maxHeight", "scaleType", "Landroid/widget/ImageView$ScaleType;", "placeholder", "fallback", "callback", "Lcom/bumptech/glide/request/RequestListener;", "loadImageAsBitmap", "Landroid/graphics/Bitmap;", "trim", "placeholderDrawable", "prefetchBitmap", "Lcom/bumptech/glide/request/target/Target;", "uri", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ImageLoaderManager {
    private final String TAG;
    private final Context context;

    public final void cancelRequest(RequestBuilder<Drawable> request) {
    }

    public ImageLoaderManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = getClass().getSimpleName();
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final RequestBuilder<Drawable> loadImage(String requestUrl, ImageView target, boolean withCrossFade, final int crossFadeDuration, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, int placeholder, int fallback, RequestListener<Drawable> callback) {
        if (requestUrl == null) {
            return null;
        }
        try {
            if ((this.context instanceof Activity) && ((Activity) this.context).isDestroyed()) {
                return null;
            }
            RequestBuilder<Drawable> requestBuilderLoad = Glide.with(this.context).load(StringsKt.contains$default((CharSequence) requestUrl, (CharSequence) "://10.9.9.200", false, 2, (Object) null) ? CommonUtils.getResizedImageUrl(requestUrl, maxWidth, maxHeight) : requestUrl);
            Intrinsics.checkExpressionValueIsNotNull(requestBuilderLoad, "Glide.with(context).load(image)");
            if (withCrossFade) {
                requestBuilderLoad = requestBuilderLoad.transition(DrawableTransitionOptions.with(new TransitionFactory<Drawable>() { // from class: com.dotscreen.tv.ui.ImageLoaderManager.loadImage.1
                    @Override // com.bumptech.glide.request.transition.TransitionFactory
                    public final Transition<Drawable> build(DataSource dataSource, boolean z) {
                        if (dataSource == DataSource.DATA_DISK_CACHE) {
                            return new DrawableCrossFadeFactory.Builder(0).build().build(dataSource, z);
                        }
                        return new DrawableCrossFadeFactory.Builder(crossFadeDuration).build().build(dataSource, z);
                    }
                }));
                Intrinsics.checkExpressionValueIsNotNull(requestBuilderLoad, "requestBuilder.transitio…     }\n                })");
            }
            if (callback != null) {
                requestBuilderLoad = requestBuilderLoad.listener(callback);
                Intrinsics.checkExpressionValueIsNotNull(requestBuilderLoad, "requestBuilder.listener(callback)");
            }
            if (placeholder > 0) {
                Cloneable cloneablePlaceholder = requestBuilderLoad.placeholder(placeholder);
                Intrinsics.checkExpressionValueIsNotNull(cloneablePlaceholder, "requestBuilder.placeholder(placeholder)");
                requestBuilderLoad = (RequestBuilder) cloneablePlaceholder;
            }
            if (fallback > 0) {
                Cloneable cloneableFallback = requestBuilderLoad.fallback(fallback);
                Intrinsics.checkExpressionValueIsNotNull(cloneableFallback, "requestBuilder.fallback(fallback)");
                requestBuilderLoad = (RequestBuilder) cloneableFallback;
            }
            if (target != null) {
                requestBuilderLoad.into(target).clearOnDetach();
            }
            return requestBuilderLoad;
        } catch (Exception unused) {
            Log.e(this.TAG, "loadImage: error while loading image with url : " + requestUrl);
            return null;
        }
    }

    public final RequestBuilder<Bitmap> loadImageAsBitmap(String requestUrl, ImageView target, boolean withCrossFade, final int crossFadeDuration, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, int placeholder, int fallback, RequestListener<Bitmap> callback, boolean trim, Drawable placeholderDrawable) {
        if (requestUrl == null) {
            return null;
        }
        try {
            if ((this.context instanceof Activity) && ((Activity) this.context).isDestroyed()) {
                return null;
            }
            RequestBuilder<Bitmap> requestBuilderLoad = Glide.with(this.context).asBitmap().load(StringsKt.contains$default((CharSequence) requestUrl, (CharSequence) "://10.9.9.200", false, 2, (Object) null) ? CommonUtils.getResizedImageUrl(requestUrl, maxWidth, maxHeight) : requestUrl);
            Intrinsics.checkExpressionValueIsNotNull(requestBuilderLoad, "Glide.with(context).asBitmap().load(image)");
            if (withCrossFade) {
                requestBuilderLoad = requestBuilderLoad.transition(BitmapTransitionOptions.withWrapped(new TransitionFactory<Drawable>() { // from class: com.dotscreen.tv.ui.ImageLoaderManager.loadImageAsBitmap.1
                    @Override // com.bumptech.glide.request.transition.TransitionFactory
                    public final Transition<Drawable> build(DataSource dataSource, boolean z) {
                        if (dataSource == DataSource.DATA_DISK_CACHE) {
                            return new DrawableCrossFadeFactory.Builder(0).build().build(dataSource, z);
                        }
                        return new DrawableCrossFadeFactory.Builder(crossFadeDuration).build().build(dataSource, z);
                    }
                }));
                Intrinsics.checkExpressionValueIsNotNull(requestBuilderLoad, "requestBuilder.transitio…     }\n                })");
            }
            if (callback != null) {
                requestBuilderLoad = requestBuilderLoad.listener(callback);
                Intrinsics.checkExpressionValueIsNotNull(requestBuilderLoad, "requestBuilder.listener(callback)");
            }
            if (placeholder > 0) {
                Cloneable cloneablePlaceholder = requestBuilderLoad.placeholder(placeholder);
                Intrinsics.checkExpressionValueIsNotNull(cloneablePlaceholder, "requestBuilder.placeholder(placeholder)");
                requestBuilderLoad = (RequestBuilder) cloneablePlaceholder;
            }
            if (placeholderDrawable != null) {
                Cloneable cloneablePlaceholder2 = requestBuilderLoad.placeholder(placeholderDrawable);
                Intrinsics.checkExpressionValueIsNotNull(cloneablePlaceholder2, "requestBuilder.placeholder(placeholderDrawable)");
                requestBuilderLoad = (RequestBuilder) cloneablePlaceholder2;
            }
            if (fallback > 0) {
                Cloneable cloneableFallback = requestBuilderLoad.fallback(fallback);
                Intrinsics.checkExpressionValueIsNotNull(cloneableFallback, "requestBuilder.fallback(fallback)");
                requestBuilderLoad = (RequestBuilder) cloneableFallback;
            }
            if (trim) {
                Cloneable cloneableTransform = requestBuilderLoad.transform(new TrimTransformation());
                Intrinsics.checkExpressionValueIsNotNull(cloneableTransform, "requestBuilder.transform(TrimTransformation())");
                requestBuilderLoad = (RequestBuilder) cloneableTransform;
            }
            if (target != null) {
                requestBuilderLoad.into(target);
            }
            return requestBuilderLoad;
        } catch (Exception unused) {
            Log.e(this.TAG, "loadImage: error while loading image with url : " + requestUrl);
            return null;
        }
    }

    public static /* synthetic */ Target prefetchBitmap$default(ImageLoaderManager imageLoaderManager, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return imageLoaderManager.prefetchBitmap(str, i, i2);
    }

    public final Target<Bitmap> prefetchBitmap(String uri, int maxWidth, int maxHeight) {
        String str = uri;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        Context context = this.context;
        if ((context instanceof Activity) && ((Activity) context).isDestroyed()) {
            return null;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "://10.9.9.200", false, 2, (Object) null)) {
            uri = CommonUtils.getResizedImageUrl(uri, maxWidth, maxHeight);
        }
        return Glide.with(this.context).asBitmap().load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).preload();
    }
}
