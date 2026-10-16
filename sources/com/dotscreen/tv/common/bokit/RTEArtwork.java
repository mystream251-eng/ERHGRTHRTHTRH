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

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RTEArtwork;", "Lcom/dotscreen/bokit/model/StaticArtwork;", "boService", "Lcom/dotscreen/bokit/services/BOService;", ImagesContract.URL, "", "format", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Lcom/dotscreen/bokit/model/ArtworkFormat;)V", "urlWithSize", "Ljava/net/URL;", "size", "Landroid/graphics/Point;", "scale", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RTEArtwork extends StaticArtwork {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RTEArtwork(BOService boService, String url, ArtworkFormat format) {
        super(boService, url, format);
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(format, "format");
    }

    @Override // com.dotscreen.bokit.model.StaticArtwork, com.dotscreen.bokit.model.Artwork
    public URL urlWithSize(Point size, double scale) {
        Intrinsics.checkParameterIsNotNull(size, "size");
        return new URL(CommonUtils.RIGHTV_URL + "/images" + getUrl() + "?width=" + size.x + "&height=" + size.y);
    }
}
