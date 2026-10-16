package com.dotscreen.farkit.network;

import android.net.NetworkCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkStateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"DEBUG", "", "TAG", "", "getActiveNetworkType", "Lcom/dotscreen/farkit/network/NetworkType;", "Landroid/net/NetworkCapabilities;", "farkit_release"}, k = 2, mv = {1, 1, 16})
public final class NetworkStateManagerKt {
    private static final boolean DEBUG = true;
    private static final String TAG = "NetworkStateManager";

    public static final NetworkType getActiveNetworkType(NetworkCapabilities getActiveNetworkType) {
        Intrinsics.checkParameterIsNotNull(getActiveNetworkType, "$this$getActiveNetworkType");
        if (getActiveNetworkType.hasTransport(3)) {
            return NetworkType.Ethernet;
        }
        if (getActiveNetworkType.hasTransport(1)) {
            return NetworkType.Wifi;
        }
        return getActiveNetworkType.hasTransport(0) ? NetworkType.Mobile : NetworkType.Unknown;
    }
}
