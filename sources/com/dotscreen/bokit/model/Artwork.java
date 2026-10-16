package com.dotscreen.bokit.model;

import android.graphics.Bitmap;
import android.graphics.Point;
import io.reactivex.Observable;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Artwork.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J,\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/bokit/model/Artwork;", "", "format", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "(Lcom/dotscreen/bokit/model/ArtworkFormat;)V", "getFormat", "()Lcom/dotscreen/bokit/model/ArtworkFormat;", "equals", "", "other", "hashCode", "", "imageWithSize", "Lio/reactivex/Observable;", "Landroid/graphics/Bitmap;", "size", "Landroid/graphics/Point;", "scale", "", "placeHolder", "toString", "", "urlWithSize", "Ljava/net/URL;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class Artwork {
    private final ArtworkFormat format;

    public URL urlWithSize(Point size, double scale) {
        Intrinsics.checkNotNullParameter(size, "size");
        return null;
    }

    public Artwork(ArtworkFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        this.format = format;
    }

    public final ArtworkFormat getFormat() {
        return this.format;
    }

    public boolean equals(Object other) {
        if (other instanceof Artwork) {
            return this.format == ((Artwork) other).format;
        }
        return super.equals(other);
    }

    public int hashCode() {
        return this.format.hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.format + ')';
    }

    public static /* synthetic */ URL urlWithSize$default(Artwork artwork, Point point, double d, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: urlWithSize");
        }
        if ((i & 2) != 0) {
            d = 1.0d;
        }
        return artwork.urlWithSize(point, d);
    }

    public static /* synthetic */ Observable imageWithSize$default(Artwork artwork, Point point, double d, Bitmap bitmap, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: imageWithSize");
        }
        if ((i & 2) != 0) {
            d = 1.0d;
        }
        if ((i & 4) != 0) {
            bitmap = (Bitmap) null;
        }
        return artwork.imageWithSize(point, d, bitmap);
    }

    public Observable<Bitmap> imageWithSize(Point size, double scale, Bitmap placeHolder) {
        Intrinsics.checkNotNullParameter(size, "size");
        if (placeHolder != null) {
            Observable<Bitmap> observableJust = Observable.just(placeHolder);
            Intrinsics.checkNotNullExpressionValue(observableJust, "Observable.just(placeHolder)");
            return observableJust;
        }
        Observable<Bitmap> observableEmpty = Observable.empty();
        Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
        return observableEmpty;
    }
}
