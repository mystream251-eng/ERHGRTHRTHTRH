package com.dotscreen.bokit.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/bokit/model/ChannelCapabilities;", "", "catchup", "", "startover", "networkPVR", "hddPVR", "(ZZZZ)V", "getCatchup", "()Z", "getHddPVR", "getNetworkPVR", "getStartover", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final /* data */ class ChannelCapabilities {
    private final boolean catchup;
    private final boolean hddPVR;
    private final boolean networkPVR;
    private final boolean startover;

    public ChannelCapabilities() {
        this(false, false, false, false, 15, null);
    }

    public static /* synthetic */ ChannelCapabilities copy$default(ChannelCapabilities channelCapabilities, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = channelCapabilities.catchup;
        }
        if ((i & 2) != 0) {
            z2 = channelCapabilities.startover;
        }
        if ((i & 4) != 0) {
            z3 = channelCapabilities.networkPVR;
        }
        if ((i & 8) != 0) {
            z4 = channelCapabilities.hddPVR;
        }
        return channelCapabilities.copy(z, z2, z3, z4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getCatchup() {
        return this.catchup;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getStartover() {
        return this.startover;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getNetworkPVR() {
        return this.networkPVR;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getHddPVR() {
        return this.hddPVR;
    }

    public final ChannelCapabilities copy(boolean catchup, boolean startover, boolean networkPVR, boolean hddPVR) {
        return new ChannelCapabilities(catchup, startover, networkPVR, hddPVR);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChannelCapabilities)) {
            return false;
        }
        ChannelCapabilities channelCapabilities = (ChannelCapabilities) other;
        return this.catchup == channelCapabilities.catchup && this.startover == channelCapabilities.startover && this.networkPVR == channelCapabilities.networkPVR && this.hddPVR == channelCapabilities.hddPVR;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5, types: [int] */
    /* JADX WARN: Type inference failed for: r0v7, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public int hashCode() {
        boolean z = this.catchup;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.startover;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (i + r2) * 31;
        boolean z3 = this.networkPVR;
        ?? r3 = z3;
        if (z3) {
            r3 = 1;
        }
        int i3 = (i2 + r3) * 31;
        boolean z4 = this.hddPVR;
        return i3 + (z4 ? 1 : z4);
    }

    public String toString() {
        return "ChannelCapabilities(catchup=" + this.catchup + ", startover=" + this.startover + ", networkPVR=" + this.networkPVR + ", hddPVR=" + this.hddPVR + ")";
    }

    public ChannelCapabilities(boolean z, boolean z2, boolean z3, boolean z4) {
        this.catchup = z;
        this.startover = z2;
        this.networkPVR = z3;
        this.hddPVR = z4;
    }

    public final boolean getCatchup() {
        return this.catchup;
    }

    public final boolean getStartover() {
        return this.startover;
    }

    public final boolean getNetworkPVR() {
        return this.networkPVR;
    }

    public /* synthetic */ ChannelCapabilities(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4);
    }

    public final boolean getHddPVR() {
        return this.hddPVR;
    }
}
