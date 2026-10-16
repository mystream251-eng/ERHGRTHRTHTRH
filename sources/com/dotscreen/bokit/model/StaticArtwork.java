package com.dotscreen.bokit.model;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.dotscreen.bokit.internal.network.RequestManager;
import com.dotscreen.bokit.services.BOService;
import com.google.android.gms.common.internal.ImagesContract;
import io.reactivex.Observable;
import java.net.URL;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Artwork.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J(\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u0005H\u0016J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/dotscreen/bokit/model/StaticArtwork;", "Lcom/dotscreen/bokit/model/Artwork;", "boService", "Lcom/dotscreen/bokit/services/BOService;", ImagesContract.URL, "", "format", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Lcom/dotscreen/bokit/model/ArtworkFormat;)V", "getBoService", "()Lcom/dotscreen/bokit/services/BOService;", "getUrl", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "imageWithSize", "Lio/reactivex/Observable;", "Landroid/graphics/Bitmap;", "size", "Landroid/graphics/Point;", "scale", "", "placeHolder", "toString", "urlWithSize", "Ljava/net/URL;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class StaticArtwork extends Artwork {
    private final BOService boService;
    private final String url;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StaticArtwork(BOService boService, String url, ArtworkFormat format) {
        super(format);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(format, "format");
        this.boService = boService;
        this.url = url;
    }

    protected final BOService getBoService() {
        return this.boService;
    }

    protected final String getUrl() {
        return this.url;
    }

    @Override // com.dotscreen.bokit.model.Artwork
    public boolean equals(Object other) {
        if (!(other instanceof StaticArtwork)) {
            return super.equals(other);
        }
        StaticArtwork staticArtwork = (StaticArtwork) other;
        return Intrinsics.areEqual(this.url, staticArtwork.url) && getFormat() == staticArtwork.getFormat();
    }

    @Override // com.dotscreen.bokit.model.Artwork
    public int hashCode() {
        return Objects.hash(getFormat(), this.url);
    }

    @Override // com.dotscreen.bokit.model.Artwork
    public String toString() {
        return getClass().getSimpleName() + '(' + getFormat() + ": " + this.url + ')';
    }

    @Override // com.dotscreen.bokit.model.Artwork
    public URL urlWithSize(Point size, double scale) {
        Intrinsics.checkNotNullParameter(size, "size");
        return this.boService.resolveUrl(this.url);
    }

    @Override // com.dotscreen.bokit.model.Artwork
    public Observable<Bitmap> imageWithSize(Point size, double scale, Bitmap placeHolder) {
        Intrinsics.checkNotNullParameter(size, "size");
        Point point = new Point((int) (((double) size.x) * scale), (int) (((double) size.y) * scale));
        Observable<Bitmap> observableImageWithSize = super.imageWithSize(size, scale, placeHolder);
        RequestManager requestManager = this.boService.getRequestManager();
        URL urlUrlWithSize = urlWithSize(size, scale);
        Intrinsics.checkNotNull(urlUrlWithSize);
        Observable<Bitmap> observableConcatWith = observableImageWithSize.concatWith(RequestManager.getBitmap$default(requestManager, 0, urlUrlWithSize, point.x, point.y, null, 16, null));
        Intrinsics.checkNotNullExpressionValue(observableConcatWith, "super.imageWithSize(size…adjustedSize.y)\n        )");
        return observableConcatWith;
    }
}
