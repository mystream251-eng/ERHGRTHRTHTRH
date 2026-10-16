package com.dotscreen.bokit.model;

import android.graphics.Point;
import android.graphics.Rect;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtworkFormat.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ#\u0010\u000e\u001a\u00020\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0012R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/bokit/model/ArtworkFormat;", "", "(Ljava/lang/String;I)V", "aspectRatio", "", "getAspectRatio", "()D", "adjustSizeForBestFit", "Landroid/graphics/Point;", "size", "bestMatchForAspectRatio", "computeBestFitInSize", "Landroid/graphics/Rect;", "containingSize", "computeSize", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "(Ljava/lang/Integer;Ljava/lang/Integer;)Landroid/graphics/Point;", "Poster", "Still", "Square", "Banner", "ShowCard", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public enum ArtworkFormat {
    Poster,
    Still,
    Square,
    Banner,
    ShowCard;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ArtworkFormat.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ArtworkFormat.Poster.ordinal()] = 1;
            $EnumSwitchMapping$0[ArtworkFormat.Still.ordinal()] = 2;
            $EnumSwitchMapping$0[ArtworkFormat.Square.ordinal()] = 3;
            $EnumSwitchMapping$0[ArtworkFormat.Banner.ordinal()] = 4;
            $EnumSwitchMapping$0[ArtworkFormat.ShowCard.ordinal()] = 5;
        }
    }

    public final double getAspectRatio() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 0.75d;
        }
        if (i == 2) {
            return 1.7777777777777777d;
        }
        if (i == 3) {
            return 1.0d;
        }
        if (i == 4 || i == 5) {
            return 4.285714285714286d;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final ArtworkFormat bestMatchForAspectRatio(double aspectRatio) {
        ArtworkFormat[] artworkFormatArrValues = values();
        if (artworkFormatArrValues.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        ArtworkFormat artworkFormat = artworkFormatArrValues[0];
        if (1 > ArraysKt.getLastIndex(artworkFormatArrValues)) {
            return artworkFormat;
        }
        ArtworkFormat artworkFormat2 = artworkFormatArrValues[1];
        return Math.abs(artworkFormat2.getAspectRatio() - aspectRatio) < Math.abs(artworkFormat.getAspectRatio() - aspectRatio) ? artworkFormat2 : artworkFormat;
    }

    public static /* synthetic */ Point computeSize$default(ArtworkFormat artworkFormat, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        return artworkFormat.computeSize(num, num2);
    }

    public final Point computeSize(Integer width, Integer height) {
        if (width != null) {
            int iIntValue = width.intValue();
            return new Point(iIntValue, (int) (((double) iIntValue) / getAspectRatio()));
        }
        if (height != null) {
            int iIntValue2 = height.intValue();
            return new Point((int) (((double) iIntValue2) * getAspectRatio()), iIntValue2);
        }
        return new Point(0, 0);
    }

    public final Point adjustSizeForBestFit(Point size) {
        Intrinsics.checkNotNullParameter(size, "size");
        return getAspectRatio() >= 1.0d ? computeSize$default(this, Integer.valueOf(size.x), null, 2, null) : computeSize$default(this, null, Integer.valueOf(size.y), 1, null);
    }

    public final Rect computeBestFitInSize(Point containingSize) {
        Intrinsics.checkNotNullParameter(containingSize, "containingSize");
        Point pointAdjustSizeForBestFit = adjustSizeForBestFit(containingSize);
        int i = (containingSize.x - pointAdjustSizeForBestFit.x) / 2;
        int i2 = (containingSize.y - pointAdjustSizeForBestFit.y) / 2;
        return new Rect(i, i2, pointAdjustSizeForBestFit.x + i, pointAdjustSizeForBestFit.y + i2);
    }
}
