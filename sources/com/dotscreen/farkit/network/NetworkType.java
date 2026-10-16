package com.dotscreen.farkit.network;

import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkStateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkType;", "", "voName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getVoName", "()Ljava/lang/String;", "setVoName", "(Ljava/lang/String;)V", VOPlaybackSessionReport.UNKNOWN_SVALUE, "Ethernet", "Wifi", "Mobile", "farkit_release"}, k = 1, mv = {1, 1, 16})
public enum NetworkType {
    Unknown("OTHER"),
    Ethernet(VOPlaybackSessionReport.NETWORK_TYPE_ETHERNET),
    Wifi(VOPlaybackSessionReport.NETWORK_TYPE_WIFI),
    Mobile(VOPlaybackSessionReport.NETWORK_TYPE_CELLULAR);

    private String voName;

    NetworkType(String str) {
        this.voName = str;
    }

    public final String getVoName() {
        return this.voName;
    }

    public final void setVoName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.voName = str;
    }
}
