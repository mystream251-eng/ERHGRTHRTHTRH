package com.dotscreen.farkit.network;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackendReachability.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0014B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/farkit/network/BackendReachability;", "", "backends", "", "Lcom/dotscreen/farkit/network/BackendReachability$BackendStatus;", "(Ljava/util/List;)V", "getBackends", "()Ljava/util/List;", "reachable", "", "getReachable", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "BackendStatus", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class BackendReachability {
    private final List<BackendStatus> backends;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BackendReachability copy$default(BackendReachability backendReachability, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = backendReachability.backends;
        }
        return backendReachability.copy(list);
    }

    public final List<BackendStatus> component1() {
        return this.backends;
    }

    public final BackendReachability copy(List<BackendStatus> backends) {
        Intrinsics.checkParameterIsNotNull(backends, "backends");
        return new BackendReachability(backends);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof BackendReachability) && Intrinsics.areEqual(this.backends, ((BackendReachability) other).backends);
        }
        return true;
    }

    public int hashCode() {
        List<BackendStatus> list = this.backends;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "BackendReachability(backends=" + this.backends + ")";
    }

    public BackendReachability(List<BackendStatus> backends) {
        Intrinsics.checkParameterIsNotNull(backends, "backends");
        this.backends = backends;
    }

    public final List<BackendStatus> getBackends() {
        return this.backends;
    }

    public final boolean getReachable() {
        boolean z;
        if (!this.backends.isEmpty()) {
            List<BackendStatus> list = this.backends;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    if (!((BackendStatus) it.next()).getReachable()) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
                break;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: BackendReachability.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J+\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/dotscreen/farkit/network/BackendReachability$BackendStatus;", "", "name", "", "response", "error", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "getName", "()Ljava/lang/String;", "reachable", "", "getReachable", "()Z", "getResponse", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class BackendStatus {
        private final Throwable error;
        private final String name;
        private final String response;

        public static /* synthetic */ BackendStatus copy$default(BackendStatus backendStatus, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = backendStatus.name;
            }
            if ((i & 2) != 0) {
                str2 = backendStatus.response;
            }
            if ((i & 4) != 0) {
                th = backendStatus.error;
            }
            return backendStatus.copy(str, str2, th);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getResponse() {
            return this.response;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Throwable getError() {
            return this.error;
        }

        public final BackendStatus copy(String name, String response, Throwable error) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new BackendStatus(name, response, error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BackendStatus)) {
                return false;
            }
            BackendStatus backendStatus = (BackendStatus) other;
            return Intrinsics.areEqual(this.name, backendStatus.name) && Intrinsics.areEqual(this.response, backendStatus.response) && Intrinsics.areEqual(this.error, backendStatus.error);
        }

        public int hashCode() {
            String str = this.name;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.response;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            Throwable th = this.error;
            return iHashCode2 + (th != null ? th.hashCode() : 0);
        }

        public String toString() {
            return "BackendStatus(name=" + this.name + ", response=" + this.response + ", error=" + this.error + ")";
        }

        public BackendStatus(String name, String str, Throwable th) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.name = name;
            this.response = str;
            this.error = th;
        }

        public /* synthetic */ BackendStatus(String str, String str2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (Throwable) null : th);
        }

        public final Throwable getError() {
            return this.error;
        }

        public final String getName() {
            return this.name;
        }

        public final String getResponse() {
            return this.response;
        }

        public final boolean getReachable() {
            return this.error == null;
        }
    }
}
