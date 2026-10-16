package com.dotscreen.tv.common.bokit;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/tv/common/bokit/ChannelExtraCaster;", "", "src", "", "banner", "background", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBackground", "()Ljava/lang/String;", "getBanner", "getSrc", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class ChannelExtraCaster {
    private final String background;
    private final String banner;
    private final String src;

    public static /* synthetic */ ChannelExtraCaster copy$default(ChannelExtraCaster channelExtraCaster, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = channelExtraCaster.src;
        }
        if ((i & 2) != 0) {
            str2 = channelExtraCaster.banner;
        }
        if ((i & 4) != 0) {
            str3 = channelExtraCaster.background;
        }
        return channelExtraCaster.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSrc() {
        return this.src;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getBanner() {
        return this.banner;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBackground() {
        return this.background;
    }

    public final ChannelExtraCaster copy(String src, String banner, String background) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        return new ChannelExtraCaster(src, banner, background);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChannelExtraCaster)) {
            return false;
        }
        ChannelExtraCaster channelExtraCaster = (ChannelExtraCaster) other;
        return Intrinsics.areEqual(this.src, channelExtraCaster.src) && Intrinsics.areEqual(this.banner, channelExtraCaster.banner) && Intrinsics.areEqual(this.background, channelExtraCaster.background);
    }

    public int hashCode() {
        String str = this.src;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.banner;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.background;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "ChannelExtraCaster(src=" + this.src + ", banner=" + this.banner + ", background=" + this.background + ")";
    }

    public ChannelExtraCaster(String src, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        this.src = src;
        this.banner = str;
        this.background = str2;
    }

    public final String getSrc() {
        return this.src;
    }

    public /* synthetic */ ChannelExtraCaster(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3);
    }

    public final String getBanner() {
        return this.banner;
    }

    public final String getBackground() {
        return this.background;
    }
}
