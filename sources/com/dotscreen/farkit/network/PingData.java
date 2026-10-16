package com.dotscreen.farkit.network;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: compiled from: NetworkStateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003JA\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcom/dotscreen/farkit/network/PingData;", "", ImagesContract.URL, "", "success", "", "resolvedIp", "fromDnsServerName", "fromIp", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFromDnsServerName", "()Ljava/lang/String;", "getFromIp", "getResolvedIp", "getSuccess", "()Z", "getUrl", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toServerStatus", "Lcom/dotscreen/farkit/network/ServerStatus;", "toString", "Companion", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class PingData {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String fromDnsServerName;
    private final String fromIp;
    private final String resolvedIp;
    private final boolean success;
    private final String url;

    public static /* synthetic */ PingData copy$default(PingData pingData, String str, boolean z, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pingData.url;
        }
        if ((i & 2) != 0) {
            z = pingData.success;
        }
        boolean z2 = z;
        if ((i & 4) != 0) {
            str2 = pingData.resolvedIp;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = pingData.fromDnsServerName;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            str4 = pingData.fromIp;
        }
        return pingData.copy(str, z2, str5, str6, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getResolvedIp() {
        return this.resolvedIp;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getFromDnsServerName() {
        return this.fromDnsServerName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFromIp() {
        return this.fromIp;
    }

    public final PingData copy(String url, boolean success, String resolvedIp, String fromDnsServerName, String fromIp) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        return new PingData(url, success, resolvedIp, fromDnsServerName, fromIp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PingData)) {
            return false;
        }
        PingData pingData = (PingData) other;
        return Intrinsics.areEqual(this.url, pingData.url) && this.success == pingData.success && Intrinsics.areEqual(this.resolvedIp, pingData.resolvedIp) && Intrinsics.areEqual(this.fromDnsServerName, pingData.fromDnsServerName) && Intrinsics.areEqual(this.fromIp, pingData.fromIp);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    public int hashCode() {
        String str = this.url;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.success;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        String str2 = this.resolvedIp;
        int iHashCode2 = (i + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.fromDnsServerName;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.fromIp;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "PingData(url=" + this.url + ", success=" + this.success + ", resolvedIp=" + this.resolvedIp + ", fromDnsServerName=" + this.fromDnsServerName + ", fromIp=" + this.fromIp + ")";
    }

    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/dotscreen/farkit/network/PingData$Companion;", "", "()V", "parsePingOutput", "Lcom/dotscreen/farkit/network/PingData;", ImagesContract.URL, "", "exitCode", "", TvContractCompat.PARAM_INPUT, "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PingData parsePingOutput(String url, int exitCode, String input) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(input, "input");
            Regex regex = new Regex("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
            Regex regex2 = new Regex("(?<=from )([^\\s]+)");
            String str = input;
            MatchResult matchResultFind$default = Regex.find$default(regex, str, 0, 2, null);
            String value = matchResultFind$default != null ? matchResultFind$default.getValue() : null;
            MatchResult matchResultFind$default2 = Regex.find$default(regex2, str, 0, 2, null);
            return new PingData(url, exitCode == 0, value, matchResultFind$default2 != null ? matchResultFind$default2.getValue() : null, null, 16, null);
        }
    }

    public PingData(String url, boolean z, String str, String str2, String str3) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.url = url;
        this.success = z;
        this.resolvedIp = str;
        this.fromDnsServerName = str2;
        this.fromIp = str3;
    }

    public /* synthetic */ PingData(String str, boolean z, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4);
    }

    public final String getFromDnsServerName() {
        return this.fromDnsServerName;
    }

    public final String getFromIp() {
        return this.fromIp;
    }

    public final String getResolvedIp() {
        return this.resolvedIp;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final String getUrl() {
        return this.url;
    }

    public final ServerStatus toServerStatus() {
        return new ServerStatus(this.url, this.success, this, null, null, 24, null);
    }
}
