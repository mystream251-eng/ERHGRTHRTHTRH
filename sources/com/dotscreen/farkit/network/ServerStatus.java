package com.dotscreen.farkit.network;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkStateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003JP\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\tHÖ\u0001J\b\u0010\"\u001a\u00020\u0003H\u0016R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lcom/dotscreen/farkit/network/ServerStatus;", "", ImagesContract.URL, "", "reachable", "", "pingData", "Lcom/dotscreen/farkit/network/PingData;", "statusCode", "", "extra", "", "(Ljava/lang/String;ZLcom/dotscreen/farkit/network/PingData;Ljava/lang/Integer;Ljava/util/Map;)V", "getExtra", "()Ljava/util/Map;", "getPingData", "()Lcom/dotscreen/farkit/network/PingData;", "getReachable", "()Z", "getStatusCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;ZLcom/dotscreen/farkit/network/PingData;Ljava/lang/Integer;Ljava/util/Map;)Lcom/dotscreen/farkit/network/ServerStatus;", "equals", "other", "hashCode", "toString", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class ServerStatus {
    private final Map<String, String> extra;
    private final PingData pingData;
    private final boolean reachable;
    private final Integer statusCode;
    private final String url;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ServerStatus copy$default(ServerStatus serverStatus, String str, boolean z, PingData pingData, Integer num, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serverStatus.url;
        }
        if ((i & 2) != 0) {
            z = serverStatus.reachable;
        }
        boolean z2 = z;
        if ((i & 4) != 0) {
            pingData = serverStatus.pingData;
        }
        PingData pingData2 = pingData;
        if ((i & 8) != 0) {
            num = serverStatus.statusCode;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            map = serverStatus.extra;
        }
        return serverStatus.copy(str, z2, pingData2, num2, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getReachable() {
        return this.reachable;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final PingData getPingData() {
        return this.pingData;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getStatusCode() {
        return this.statusCode;
    }

    public final Map<String, String> component5() {
        return this.extra;
    }

    public final ServerStatus copy(String url, boolean reachable, PingData pingData, Integer statusCode, Map<String, String> extra) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(extra, "extra");
        return new ServerStatus(url, reachable, pingData, statusCode, extra);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServerStatus)) {
            return false;
        }
        ServerStatus serverStatus = (ServerStatus) other;
        return Intrinsics.areEqual(this.url, serverStatus.url) && this.reachable == serverStatus.reachable && Intrinsics.areEqual(this.pingData, serverStatus.pingData) && Intrinsics.areEqual(this.statusCode, serverStatus.statusCode) && Intrinsics.areEqual(this.extra, serverStatus.extra);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    public int hashCode() {
        String str = this.url;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.reachable;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        PingData pingData = this.pingData;
        int iHashCode2 = (i + (pingData != null ? pingData.hashCode() : 0)) * 31;
        Integer num = this.statusCode;
        int iHashCode3 = (iHashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        Map<String, String> map = this.extra;
        return iHashCode3 + (map != null ? map.hashCode() : 0);
    }

    public ServerStatus(String url, boolean z, PingData pingData, Integer num, Map<String, String> extra) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(extra, "extra");
        this.url = url;
        this.reachable = z;
        this.pingData = pingData;
        this.statusCode = num;
        this.extra = extra;
    }

    public /* synthetic */ ServerStatus(String str, boolean z, PingData pingData, Integer num, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, (i & 4) != 0 ? (PingData) null : pingData, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, String> getExtra() {
        return this.extra;
    }

    public final PingData getPingData() {
        return this.pingData;
    }

    public final boolean getReachable() {
        return this.reachable;
    }

    public final Integer getStatusCode() {
        return this.statusCode;
    }

    public final String getUrl() {
        return this.url;
    }

    public String toString() {
        return "Server: " + this.url + ", reachable: " + this.reachable + ", pingData: " + this.pingData + ", statusCode: " + this.statusCode + ", extra: " + this.extra;
    }
}
