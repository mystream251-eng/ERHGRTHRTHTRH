package com.dotscreen.tv.common.bokit;

import com.dotscreen.bokit.model.PlaybackInfo;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVPlaybackInfo;", "Lcom/dotscreen/bokit/model/PlaybackInfo;", ImagesContract.URL, "", "licenceUrl", "variant", "casToken", "watchedTime", "", "isToolboxContent", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Z)V", "getCasToken", "()Ljava/lang/String;", "()Z", "getLicenceUrl", "getUrl", "getVariant", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVPlaybackInfo extends PlaybackInfo {
    private final String casToken;
    private final boolean isToolboxContent;
    private final String licenceUrl;
    private final String url;
    private final String variant;

    public final String getUrl() {
        return this.url;
    }

    public final String getLicenceUrl() {
        return this.licenceUrl;
    }

    public final String getVariant() {
        return this.variant;
    }

    public /* synthetic */ RightVPlaybackInfo(String str, String str2, String str3, String str4, Long l, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (Long) null : l, (i & 32) != 0 ? false : z);
    }

    public final String getCasToken() {
        return this.casToken;
    }

    /* JADX INFO: renamed from: isToolboxContent, reason: from getter */
    public final boolean getIsToolboxContent() {
        return this.isToolboxContent;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RightVPlaybackInfo(String url, String licenceUrl, String variant, String str, Long l, boolean z) {
        super(l, null, 2, null);
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(licenceUrl, "licenceUrl");
        Intrinsics.checkParameterIsNotNull(variant, "variant");
        this.url = url;
        this.licenceUrl = licenceUrl;
        this.variant = variant;
        this.casToken = str;
        this.isToolboxContent = z;
    }

    @Override // com.dotscreen.bokit.model.PlaybackInfo
    public String toString() {
        return '[' + getClass().getSimpleName() + "] url: " + this.url + ", licenceUrl: " + this.licenceUrl + ", variant: " + this.variant + ", castToken: " + this.casToken;
    }
}
