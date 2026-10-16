package com.dotscreen.portal.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.dotscreen.portal.ui.screens.ads.ImageAdsActivity;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Activity+Extension.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001b\u0010\u0007\u001a\u00020\u0001*\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"launchAds", "", "Landroid/content/Context;", TtmlNode.TAG_IMAGE, "", "timeToHide", "", "launchHome", "Landroid/app/Activity;", NotificationsContract.COLUMN_FLAGS, "(Landroid/app/Activity;Ljava/lang/Integer;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class Activity_ExtensionKt {
    public static /* synthetic */ void launchHome$default(Activity activity, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        launchHome(activity, num);
    }

    public static final void launchHome(Activity launchHome, Integer num) {
        Intrinsics.checkParameterIsNotNull(launchHome, "$this$launchHome");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addFlags(num != null ? num.intValue() : 270532608);
        intent.addFlags(67108864);
        intent.addCategory("android.intent.category.HOME");
        launchHome.startActivity(intent);
    }

    public static final void launchAds(Context launchAds, String image, int i) {
        Intrinsics.checkParameterIsNotNull(launchAds, "$this$launchAds");
        Intrinsics.checkParameterIsNotNull(image, "image");
        Intent intent = new Intent(launchAds, (Class<?>) ImageAdsActivity.class);
        intent.putExtra(ImagesContract.URL, image);
        intent.putExtra("time", i);
        launchAds.startActivity(intent);
    }
}
