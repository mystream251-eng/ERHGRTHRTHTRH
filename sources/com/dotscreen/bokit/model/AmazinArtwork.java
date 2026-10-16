package com.dotscreen.bokit.model;

import android.graphics.Point;
import com.dotscreen.bokit.services.BOService;
import com.google.android.gms.common.internal.ImagesContract;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Artwork.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/bokit/model/AmazinArtwork;", "Lcom/dotscreen/bokit/model/StaticArtwork;", "boService", "Lcom/dotscreen/bokit/services/BOService;", ImagesContract.URL, "", "format", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Lcom/dotscreen/bokit/model/ArtworkFormat;)V", "equals", "", "other", "", "urlWithSize", "Ljava/net/URL;", "size", "Landroid/graphics/Point;", "scale", "", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class AmazinArtwork extends StaticArtwork {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmazinArtwork(BOService boService, String url, ArtworkFormat format) {
        super(boService, url, format);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(format, "format");
    }

    @Override // com.dotscreen.bokit.model.StaticArtwork, com.dotscreen.bokit.model.Artwork
    public boolean equals(Object other) {
        if (!(other instanceof AmazinArtwork)) {
            return super.equals(other);
        }
        AmazinArtwork amazinArtwork = (AmazinArtwork) other;
        return Intrinsics.areEqual(getUrl(), amazinArtwork.getUrl()) && getFormat() == amazinArtwork.getFormat();
    }

    @Override // com.dotscreen.bokit.model.StaticArtwork, com.dotscreen.bokit.model.Artwork
    public URL urlWithSize(Point size, double scale) {
        Intrinsics.checkNotNullParameter(size, "size");
        Point point = new Point((int) (((double) size.x) * scale), (int) (((double) size.y) * scale));
        return getBoService().resolveUrl(StringsKt.replace$default(StringsKt.replace$default(getUrl(), "{width}", String.valueOf(point.x), false, 4, (Object) null), "{height}", String.valueOf(point.y), false, 4, (Object) null));
    }
}
