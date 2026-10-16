package com.dotscreen.tv.common.bokit;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0015\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003JP\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/dotscreen/tv/common/bokit/ChannelExtras;", "", "channelExternalId", "", "channelVariantIdentifier", "channelIsBrowsable", "", "casters", "", "Lcom/dotscreen/tv/common/bokit/ChannelExtraCaster;", "playableOnDevice", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/Map;Z)V", "getCasters", "()Ljava/util/Map;", "getChannelExternalId", "()Ljava/lang/String;", "getChannelIsBrowsable", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getChannelVariantIdentifier", "setChannelVariantIdentifier", "(Ljava/lang/String;)V", "getPlayableOnDevice", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/Map;Z)Lcom/dotscreen/tv/common/bokit/ChannelExtras;", "equals", "other", "hashCode", "", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class ChannelExtras {
    private final Map<String, ChannelExtraCaster> casters;
    private final String channelExternalId;
    private final Boolean channelIsBrowsable;
    private String channelVariantIdentifier;
    private final boolean playableOnDevice;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ChannelExtras copy$default(ChannelExtras channelExtras, String str, String str2, Boolean bool, Map map, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = channelExtras.channelExternalId;
        }
        if ((i & 2) != 0) {
            str2 = channelExtras.channelVariantIdentifier;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            bool = channelExtras.channelIsBrowsable;
        }
        Boolean bool2 = bool;
        if ((i & 8) != 0) {
            map = channelExtras.casters;
        }
        Map map2 = map;
        if ((i & 16) != 0) {
            z = channelExtras.playableOnDevice;
        }
        return channelExtras.copy(str, str3, bool2, map2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getChannelExternalId() {
        return this.channelExternalId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getChannelVariantIdentifier() {
        return this.channelVariantIdentifier;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Boolean getChannelIsBrowsable() {
        return this.channelIsBrowsable;
    }

    public final Map<String, ChannelExtraCaster> component4() {
        return this.casters;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getPlayableOnDevice() {
        return this.playableOnDevice;
    }

    public final ChannelExtras copy(String channelExternalId, String channelVariantIdentifier, Boolean channelIsBrowsable, Map<String, ChannelExtraCaster> casters, boolean playableOnDevice) {
        Intrinsics.checkParameterIsNotNull(channelExternalId, "channelExternalId");
        Intrinsics.checkParameterIsNotNull(casters, "casters");
        return new ChannelExtras(channelExternalId, channelVariantIdentifier, channelIsBrowsable, casters, playableOnDevice);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChannelExtras)) {
            return false;
        }
        ChannelExtras channelExtras = (ChannelExtras) other;
        return Intrinsics.areEqual(this.channelExternalId, channelExtras.channelExternalId) && Intrinsics.areEqual(this.channelVariantIdentifier, channelExtras.channelVariantIdentifier) && Intrinsics.areEqual(this.channelIsBrowsable, channelExtras.channelIsBrowsable) && Intrinsics.areEqual(this.casters, channelExtras.casters) && this.playableOnDevice == channelExtras.playableOnDevice;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public int hashCode() {
        String str = this.channelExternalId;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.channelVariantIdentifier;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Boolean bool = this.channelIsBrowsable;
        int iHashCode3 = (iHashCode2 + (bool != null ? bool.hashCode() : 0)) * 31;
        Map<String, ChannelExtraCaster> map = this.casters;
        int iHashCode4 = (iHashCode3 + (map != null ? map.hashCode() : 0)) * 31;
        boolean z = this.playableOnDevice;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode4 + r1;
    }

    public String toString() {
        return "ChannelExtras(channelExternalId=" + this.channelExternalId + ", channelVariantIdentifier=" + this.channelVariantIdentifier + ", channelIsBrowsable=" + this.channelIsBrowsable + ", casters=" + this.casters + ", playableOnDevice=" + this.playableOnDevice + ")";
    }

    public ChannelExtras(String channelExternalId, String str, Boolean bool, Map<String, ChannelExtraCaster> casters, boolean z) {
        Intrinsics.checkParameterIsNotNull(channelExternalId, "channelExternalId");
        Intrinsics.checkParameterIsNotNull(casters, "casters");
        this.channelExternalId = channelExternalId;
        this.channelVariantIdentifier = str;
        this.channelIsBrowsable = bool;
        this.casters = casters;
        this.playableOnDevice = z;
    }

    public final String getChannelExternalId() {
        return this.channelExternalId;
    }

    public final String getChannelVariantIdentifier() {
        return this.channelVariantIdentifier;
    }

    public final void setChannelVariantIdentifier(String str) {
        this.channelVariantIdentifier = str;
    }

    public final Boolean getChannelIsBrowsable() {
        return this.channelIsBrowsable;
    }

    public final Map<String, ChannelExtraCaster> getCasters() {
        return this.casters;
    }

    public final boolean getPlayableOnDevice() {
        return this.playableOnDevice;
    }
}
