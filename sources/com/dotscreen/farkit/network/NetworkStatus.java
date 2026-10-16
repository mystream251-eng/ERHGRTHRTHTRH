package com.dotscreen.farkit.network;

import android.util.Log;
import com.android.volley.NoConnectionError;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLHandshakeException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkStateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\b\t\n\u000bB\u0017\b\u0002\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkStatus;", "", "serverStatus", "", "Lcom/dotscreen/farkit/network/ServerStatus;", "(Ljava/util/List;)V", "getServerStatus", "()Ljava/util/List;", "Connected", "ConnectedNoBackendAccess", "ConnectedNoInternet", "NotConnected", "Lcom/dotscreen/farkit/network/NetworkStatus$NotConnected;", "Lcom/dotscreen/farkit/network/NetworkStatus$Connected;", "Lcom/dotscreen/farkit/network/NetworkStatus$ConnectedNoInternet;", "farkit_release"}, k = 1, mv = {1, 1, 16})
public abstract class NetworkStatus {
    private final List<ServerStatus> serverStatus;

    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0096\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkStatus$NotConnected;", "Lcom/dotscreen/farkit/network/NetworkStatus;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final class NotConnected extends NetworkStatus {
        public static final NotConnected INSTANCE = new NotConnected();

        public String toString() {
            return "{NotConnected}";
        }

        /* JADX WARN: Multi-variable type inference failed */
        private NotConnected() {
            super(null, 1, 0 == true ? 1 : 0);
        }

        public boolean equals(Object other) {
            return other instanceof NotConnected;
        }

        public int hashCode() {
            return Objects.hashCode(NotConnected.class);
        }
    }

    private NetworkStatus(List<ServerStatus> list) {
        this.serverStatus = list;
    }

    /* synthetic */ NetworkStatus(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
    }

    public /* synthetic */ NetworkStatus(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    public List<ServerStatus> getServerStatus() {
        return this.serverStatus;
    }

    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkStatus$Connected;", "Lcom/dotscreen/farkit/network/NetworkStatus;", "type", "Lcom/dotscreen/farkit/network/NetworkType;", "serverStatus", "", "Lcom/dotscreen/farkit/network/ServerStatus;", "(Lcom/dotscreen/farkit/network/NetworkType;Ljava/util/List;)V", "getServerStatus", "()Ljava/util/List;", "getType", "()Lcom/dotscreen/farkit/network/NetworkType;", "equals", "", "other", "", "hashCode", "", "toString", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final class Connected extends NetworkStatus {
        private final List<ServerStatus> serverStatus;
        private final NetworkType type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Connected(NetworkType type, List<ServerStatus> serverStatus) {
            super(serverStatus, null);
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(serverStatus, "serverStatus");
            this.type = type;
            this.serverStatus = serverStatus;
        }

        public /* synthetic */ Connected(NetworkType networkType, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(networkType, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
        }

        @Override // com.dotscreen.farkit.network.NetworkStatus
        public List<ServerStatus> getServerStatus() {
            return this.serverStatus;
        }

        public final NetworkType getType() {
            return this.type;
        }

        public boolean equals(Object other) {
            return other instanceof Connected;
        }

        public int hashCode() {
            return Objects.hash(Connected.class, getServerStatus());
        }

        public String toString() {
            return "{Connected: type: " + this.type + '}';
        }
    }

    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkStatus$ConnectedNoInternet;", "Lcom/dotscreen/farkit/network/NetworkStatus;", "type", "Lcom/dotscreen/farkit/network/NetworkType;", "serverStatus", "", "Lcom/dotscreen/farkit/network/ServerStatus;", "(Lcom/dotscreen/farkit/network/NetworkType;Ljava/util/List;)V", "getServerStatus", "()Ljava/util/List;", "getType", "()Lcom/dotscreen/farkit/network/NetworkType;", "equals", "", "other", "", "hashCode", "", "toString", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static class ConnectedNoInternet extends NetworkStatus {
        private final List<ServerStatus> serverStatus;
        private final NetworkType type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public ConnectedNoInternet(NetworkType type, List<ServerStatus> serverStatus) {
            super(null, 1, 0 == true ? 1 : 0);
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(serverStatus, "serverStatus");
            this.type = type;
            this.serverStatus = serverStatus;
        }

        public /* synthetic */ ConnectedNoInternet(NetworkType networkType, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(networkType, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
        }

        @Override // com.dotscreen.farkit.network.NetworkStatus
        public List<ServerStatus> getServerStatus() {
            return this.serverStatus;
        }

        public final NetworkType getType() {
            return this.type;
        }

        public boolean equals(Object other) {
            return !(other instanceof ConnectedNoInternet);
        }

        public int hashCode() {
            return Objects.hash(ConnectedNoInternet.class, getServerStatus());
        }

        public String toString() {
            return "{ConnectedNoInternet: type: " + this.type + '}';
        }
    }

    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkStatus$ConnectedNoBackendAccess;", "Lcom/dotscreen/farkit/network/NetworkStatus$ConnectedNoInternet;", "type", "Lcom/dotscreen/farkit/network/NetworkType;", "error", "", "(Lcom/dotscreen/farkit/network/NetworkType;Ljava/lang/Throwable;)V", "reason", "Lcom/dotscreen/farkit/network/UnreachabilityReason;", "getReason", "()Lcom/dotscreen/farkit/network/UnreachabilityReason;", "equals", "", "other", "", "hashCode", "", "toString", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final class ConnectedNoBackendAccess extends ConnectedNoInternet {
        private final UnreachabilityReason reason;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public ConnectedNoBackendAccess(NetworkType type, Throwable th) {
            UnreachabilityReason unreachabilityReason;
            super(type, null, 2, 0 == true ? 1 : 0);
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (th instanceof NoConnectionError) {
                if (th.getCause() instanceof SSLHandshakeException) {
                    unreachabilityReason = UnreachabilityReason.SSLCannotBeValidated;
                } else {
                    Log.v("NetworkStateManager", "ConnectedNoBackendAccess: cause is " + th.getCause());
                    unreachabilityReason = UnreachabilityReason.Unknown;
                }
            } else {
                unreachabilityReason = UnreachabilityReason.Unknown;
            }
            this.reason = unreachabilityReason;
        }

        public /* synthetic */ ConnectedNoBackendAccess(NetworkType networkType, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(networkType, (i & 2) != 0 ? (Throwable) null : th);
        }

        public final UnreachabilityReason getReason() {
            return this.reason;
        }

        @Override // com.dotscreen.farkit.network.NetworkStatus.ConnectedNoInternet
        public boolean equals(Object other) {
            return (other instanceof ConnectedNoBackendAccess) && this.reason == ((ConnectedNoBackendAccess) other).reason;
        }

        @Override // com.dotscreen.farkit.network.NetworkStatus.ConnectedNoInternet
        public int hashCode() {
            return Objects.hash(ConnectedNoBackendAccess.class, getServerStatus(), this.reason);
        }

        @Override // com.dotscreen.farkit.network.NetworkStatus.ConnectedNoInternet
        public String toString() {
            return "{ConnectedNoBackendAccess: type: " + getType() + ", reason: " + this.reason + '}';
        }
    }
}
