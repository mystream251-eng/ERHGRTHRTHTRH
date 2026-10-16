package com.dotscreen.tv.ui;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.dotscreen.tv.common.utils.Bitmap_ExtensionKt;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageLoaderManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0014J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/tv/ui/TrimTransformation;", "Lcom/bumptech/glide/load/resource/bitmap/BitmapTransformation;", "()V", "ID", "", "ID_BYTES", "", "equals", "", "other", "", "hashCode", "", "transform", "Landroid/graphics/Bitmap;", "pool", "Lcom/bumptech/glide/load/engine/bitmap_recycle/BitmapPool;", "toTransform", "outWidth", "outHeight", "updateDiskCacheKey", "", "messageDigest", "Ljava/security/MessageDigest;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TrimTransformation extends BitmapTransformation {
    private final String ID = "com.dotscreen.tv.ui.TrimTransformation";
    private final byte[] ID_BYTES;

    public TrimTransformation() {
        Charset charsetForName = Charset.forName("UTF-8");
        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(\"UTF-8\")");
        if ("com.dotscreen.tv.ui.TrimTransformation" == 0) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = "com.dotscreen.tv.ui.TrimTransformation".getBytes(charsetForName);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        this.ID_BYTES = bytes;
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        Intrinsics.checkParameterIsNotNull(messageDigest, "messageDigest");
        messageDigest.update(this.ID_BYTES);
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        Intrinsics.checkParameterIsNotNull(toTransform, "toTransform");
        return Bitmap_ExtensionKt.trim$default(toTransform, 0, 1, null);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object other) {
        return other instanceof TrimTransformation;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.ID.hashCode();
    }
}
