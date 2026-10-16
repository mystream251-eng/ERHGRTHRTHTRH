package com.dotscreen.bokit.internal.network;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.bokit.BOKitKt;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BitmapLruCache.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0003H\u0016J\u001a\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0014¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/bokit/internal/network/BitmapLruCache;", "Landroid/util/LruCache;", "", "Landroid/graphics/Bitmap;", "Lcom/android/volley/toolbox/ImageLoader$ImageCache;", "c", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getBitmap", ImagesContract.URL, "putBitmap", "", "bitmap", "sizeOf", "", "key", "value", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    /* JADX WARN: Illegal instructions before constructor call */
    public BitmapLruCache(Context c) {
        Intrinsics.checkNotNullParameter(c, "c");
        Resources resources = c.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "c.resources");
        int i = resources.getDisplayMetrics().widthPixels;
        Resources resources2 = c.getResources();
        Intrinsics.checkNotNullExpressionValue(resources2, "c.resources");
        super(i * resources2.getDisplayMetrics().heightPixels * 4 * BOKitKt.getDefaultBitmapLRUCacheFactor());
        Log.i("BOKIT-BMPLRU", "New cache size: " + maxSize());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.util.LruCache
    public int sizeOf(String key, Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return value.getByteCount();
    }

    @Override // com.android.volley.toolbox.ImageLoader.ImageCache
    public Bitmap getBitmap(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return get(url);
    }

    @Override // com.android.volley.toolbox.ImageLoader.ImageCache
    public void putBitmap(String url, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        put(url, bitmap);
    }
}
