package com.dotscreen.tv.common.utils;

import android.graphics.Bitmap;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bitmap+Extension.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0003\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"trim", "Landroid/graphics/Bitmap;", TtmlNode.ATTR_TTS_COLOR, "", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class Bitmap_ExtensionKt {
    public static /* synthetic */ Bitmap trim$default(Bitmap bitmap, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return trim(bitmap, i);
    }

    public static final Bitmap trim(Bitmap trim, int i) {
        Intrinsics.checkParameterIsNotNull(trim, "$this$trim");
        int height = trim.getHeight();
        int width = trim.getWidth();
        int width2 = trim.getWidth();
        int[] iArr = new int[width2];
        int i2 = 0;
        for (int i3 = 0; i3 < width2; i3++) {
            iArr[i3] = i;
        }
        int[] iArr2 = new int[trim.getWidth()];
        int i4 = 0;
        while (true) {
            if (i4 >= height) {
                i4 = 0;
                break;
            }
            trim.getPixels(iArr2, 0, trim.getWidth(), 0, i4, trim.getWidth(), 1);
            if (!Arrays.equals(iArr, iArr2)) {
                break;
            }
            i4++;
        }
        int i5 = height - 1;
        if (i5 >= i4) {
            int i6 = i5;
            while (true) {
                trim.getPixels(iArr2, 0, trim.getWidth(), 0, i6, trim.getWidth(), 1);
                if (!Arrays.equals(iArr, iArr2)) {
                    height = i6;
                    break;
                }
                if (i6 == i4) {
                    break;
                }
                i6--;
            }
        }
        int i7 = height - i4;
        int[] iArr3 = new int[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            iArr3[i8] = i;
        }
        int[] iArr4 = new int[i7];
        for (int i9 = 0; i9 < width; i9++) {
            trim.getPixels(iArr4, 0, 1, i9, i4, 1, i7);
            if (!Arrays.equals(iArr3, iArr4)) {
                i2 = i9;
                break;
            }
        }
        int i10 = width - 1;
        if (i10 >= i2) {
            int i11 = i10;
            while (true) {
                trim.getPixels(iArr4, 0, 1, i11, i4, 1, i7);
                if (!Arrays.equals(iArr3, iArr4)) {
                    width = i11;
                    break;
                }
                if (i11 == i2) {
                    break;
                }
                i11--;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(trim, i2, i4, width - i2, i7);
        Intrinsics.checkExpressionValueIsNotNull(bitmapCreateBitmap, "Bitmap.createBitmap(this…ght - left, top - bottom)");
        return bitmapCreateBitmap;
    }
}
