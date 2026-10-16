package com.dotscreen.tv.tvlauncher;

import android.content.Context;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TVLauncherHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"isTVLauncherChannel", "", "Landroidx/tvprovider/media/tv/Channel;", "context", "Landroid/content/Context;", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class TVLauncherHelperKt {
    public static final boolean isTVLauncherChannel(Channel isTVLauncherChannel, Context context) {
        Intrinsics.checkParameterIsNotNull(isTVLauncherChannel, "$this$isTVLauncherChannel");
        Intrinsics.checkParameterIsNotNull(context, "context");
        return Intrinsics.areEqual(isTVLauncherChannel.getType(), TvContractCompat.Channels.TYPE_PREVIEW) && Intrinsics.areEqual(context.getPackageName(), isTVLauncherChannel.getPackageName());
    }
}
