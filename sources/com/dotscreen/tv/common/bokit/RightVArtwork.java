package com.dotscreen.tv.common.bokit;

import android.graphics.Point;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.StaticArtwork;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.gms.common.internal.ImagesContract;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVArtwork;", "Lcom/dotscreen/bokit/model/StaticArtwork;", "boService", "Lcom/dotscreen/bokit/services/BOService;", ImagesContract.URL, "", "format", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Lcom/dotscreen/bokit/model/ArtworkFormat;)V", "equals", "", "other", "", "urlWithSize", "Ljava/net/URL;", "size", "Landroid/graphics/Point;", "scale", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVArtwork extends StaticArtwork {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RightVArtwork(BOService boService, String url, ArtworkFormat format) {
        super(boService, url, format);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(format, "format");
    }

    @Override // com.dotscreen.bokit.model.StaticArtwork, com.dotscreen.bokit.model.Artwork
    public boolean equals(Object other) {
        if (!(other instanceof RightVArtwork)) {
            return super.equals(other);
        }
        RightVArtwork rightVArtwork = (RightVArtwork) other;
        return Intrinsics.areEqual(getUrl(), rightVArtwork.getUrl()) && getFormat() == rightVArtwork.getFormat();
    }

    @Override // com.dotscreen.bokit.model.StaticArtwork, com.dotscreen.bokit.model.Artwork
    public URL urlWithSize(Point size, double scale) {
        String url;
        Intrinsics.checkParameterIsNotNull(size, "size");
        Point point = new Point((int) (((double) size.x) * scale), (int) (((double) size.y) * scale));
        String url2 = getUrl();
        String str = CommonUtils.OPTIMIZER_URL;
        Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.OPTIMIZER_URL");
        if (!StringsKt.startsWith$default(url2, str, false, 2, (Object) null)) {
            String resizedImageUrl = CommonUtils.getResizedImageUrl(getUrl(), 1234, 1234);
            Intrinsics.checkExpressionValueIsNotNull(resizedImageUrl, "CommonUtils.getResizedIm…       1234\n            )");
            url = StringsKt.replace$default(resizedImageUrl, "1234x1234", "{width}x{height}", false, 4, (Object) null);
        } else {
            url = getUrl();
        }
        return getBoService().resolveUrl(StringsKt.replace$default(StringsKt.replace$default(url, "{width}", String.valueOf(point.x), false, 4, (Object) null), "{height}", String.valueOf(point.y), false, 4, (Object) null));
    }
}
