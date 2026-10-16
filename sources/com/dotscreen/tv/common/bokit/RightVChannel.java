package com.dotscreen.tv.common.bokit;

import android.content.Context;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.LiveStream;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.tv.common.utils.CommonUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010,\u001a\u00020-2\u0006\u0010\u0004\u001a\u00020\u0001H\u0002J\u0010\u0010.\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0001H\u0002J\b\u0010/\u001a\u0004\u0018\u00010\bJ\b\u00100\u001a\u00020\bH\u0016R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0018\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0019\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010%\u001a\u0004\u0018\u00010&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010)\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u00061"}, d2 = {"Lcom/dotscreen/tv/common/bokit/RightVChannel;", "Lcom/dotscreen/bokit/model/Channel;", "context", "Landroid/content/Context;", "channel", "boService", "Lcom/dotscreen/bokit/services/BOService;", "lineupId", "", "extras", "Lcom/dotscreen/tv/common/bokit/ChannelExtras;", "(Landroid/content/Context;Lcom/dotscreen/bokit/model/Channel;Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Lcom/dotscreen/tv/common/bokit/ChannelExtras;)V", "extraCatchupTime", "", "getExtraCatchupTime", "()I", "setExtraCatchupTime", "(I)V", "getExtras", "()Lcom/dotscreen/tv/common/bokit/ChannelExtras;", "isBrowsable", "", "()Z", "isHD", "isInteractive", "isLocked", "setLocked", "(Z)V", "isSD", "packages", "", "getPackages", "()Ljava/util/List;", "parentalRating", "Lcom/dotscreen/bokit/model/ParentalRating;", "getParentalRating", "()Lcom/dotscreen/bokit/model/ParentalRating;", "pipLiveStream", "Lcom/dotscreen/tv/common/bokit/PIPLiveStream;", "getPipLiveStream", "()Lcom/dotscreen/tv/common/bokit/PIPLiveStream;", "variantExternalId", "getVariantExternalId", "()Ljava/lang/String;", "extractPackages", "", "extractParentalRating", "getDVBTriplet", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RightVChannel extends Channel {
    private int extraCatchupTime;
    private final ChannelExtras extras;
    private final boolean isBrowsable;
    private final boolean isHD;

    /* JADX INFO: renamed from: isInteractive, reason: from kotlin metadata and from toString */
    private final boolean isInteracive;
    private boolean isLocked;
    private final boolean isSD;
    private final List<String> packages;
    private final ParentalRating parentalRating;
    private final PIPLiveStream pipLiveStream;
    private final String variantExternalId;

    public final ChannelExtras getExtras() {
        return this.extras;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00de  */
    /* JADX WARN: Code duplicated, block: B:8:0x0091  */
    /* JADX WARN: Code duplicated, block: B:91:0x01d5  */
    /* JADX WARN: Illegal instructions before constructor call */
    public RightVChannel(Context context, Channel channel, BOService boService, String lineupId, ChannelExtras extras) {
        Object next;
        boolean zStartsWith$default;
        Object next2;
        boolean zStartsWith$default2;
        Object next3;
        String strRemovePrefix;
        LiveStream liveStream;
        String strBlockingGet;
        BOService bOService;
        Object next4;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(lineupId, "lineupId");
        Intrinsics.checkParameterIsNotNull(extras, "extras");
        LiveTVService liveTVService = boService.getLiveTVService();
        String id = channel.getId();
        String title = channel.getTitle();
        String summary = channel.getSummary();
        List<Artwork> artworks = channel.getArtworks();
        String deepLink = channel.getDeepLink();
        int zappingNumber = channel.getZappingNumber();
        LiveTVService liveTVService2 = boService.getLiveTVService();
        if (liveTVService2 != null) {
            super(liveTVService, id, lineupId, title, summary, artworks, deepLink, zappingNumber, null, MegacableUtilsKt.rightVCapabilities((MegacableLiveTVService) liveTVService2, channel.getExtraKeys()), channel.getRatable(), channel.getGenres());
            this.extras = extras;
            this.packages = new ArrayList();
            this.extraCatchupTime = 48;
            if (!CommonUtils.isDVBCSTB(context) && !CommonUtils.isTypeSTB(context, "IPTV STB")) {
                zStartsWith$default = false;
            } else if (this.extras.getChannelVariantIdentifier() != null) {
                String channelVariantIdentifier = this.extras.getChannelVariantIdentifier();
                if (channelVariantIdentifier == null) {
                    Intrinsics.throwNpe();
                }
                zStartsWith$default = StringsKt.startsWith$default(channelVariantIdentifier, CommonUtils.KEY_RIGHTV_HD_EXTERNAL_CHANNEL_ID, false, 2, (Object) null);
            } else {
                Iterator<T> it = channel.getExtraKeys().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!StringsKt.startsWith$default((String) next, CommonUtils.KEY_RIGHTV_HD_EXTERNAL_CHANNEL_ID, false, 2, (Object) null));
                if (next != null) {
                    zStartsWith$default = true;
                } else {
                    zStartsWith$default = false;
                }
            }
            this.isSD = zStartsWith$default;
            if (!CommonUtils.isDVBCSTB(context) && !CommonUtils.isTypeSTB(context, "IPTV STB")) {
                zStartsWith$default2 = false;
            } else if (this.extras.getChannelVariantIdentifier() != null) {
                String channelVariantIdentifier2 = this.extras.getChannelVariantIdentifier();
                if (channelVariantIdentifier2 == null) {
                    Intrinsics.throwNpe();
                }
                zStartsWith$default2 = StringsKt.startsWith$default(channelVariantIdentifier2, CommonUtils.KEY_RIGHTV_SD_EXTERNAL_CHANNEL_ID, false, 2, (Object) null);
            } else {
                Iterator<T> it2 = channel.getExtraKeys().iterator();
                do {
                    if (!it2.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it2.next();
                } while (!StringsKt.startsWith$default((String) next2, CommonUtils.KEY_RIGHTV_SD_EXTERNAL_CHANNEL_ID, false, 2, (Object) null));
                if (next2 != null) {
                    zStartsWith$default2 = true;
                } else {
                    zStartsWith$default2 = false;
                }
            }
            this.isHD = zStartsWith$default2;
            this.isBrowsable = this.extras.getChannelIsBrowsable() != null ? !(this.isHD || !this.extras.getChannelIsBrowsable().booleanValue()) : !this.isHD;
            if (this.isSD) {
                if (this.extras.getChannelVariantIdentifier() != null) {
                    String channelVariantIdentifier3 = this.extras.getChannelVariantIdentifier();
                    if (channelVariantIdentifier3 == null) {
                        Intrinsics.throwNpe();
                    }
                    strRemovePrefix = StringsKt.removePrefix(channelVariantIdentifier3, (CharSequence) "rightv-hd-external-id:");
                } else {
                    Iterator<T> it3 = channel.getExtraKeys().iterator();
                    do {
                        if (!it3.hasNext()) {
                            next4 = null;
                            break;
                        }
                        next4 = it3.next();
                    } while (!StringsKt.startsWith$default((String) next4, CommonUtils.KEY_RIGHTV_HD_EXTERNAL_CHANNEL_ID, false, 2, (Object) null));
                    String str = (String) next4;
                    if (str != null) {
                        strRemovePrefix = StringsKt.removePrefix(str, (CharSequence) "rightv-hd-external-id:");
                    } else {
                        strRemovePrefix = null;
                    }
                }
            } else if (!this.isHD) {
                strRemovePrefix = null;
            } else if (this.extras.getChannelVariantIdentifier() != null) {
                String channelVariantIdentifier4 = this.extras.getChannelVariantIdentifier();
                if (channelVariantIdentifier4 == null) {
                    Intrinsics.throwNpe();
                }
                strRemovePrefix = StringsKt.removePrefix(channelVariantIdentifier4, (CharSequence) "rightv-sd-external-id:");
            } else {
                Iterator<T> it4 = channel.getExtraKeys().iterator();
                do {
                    if (!it4.hasNext()) {
                        next3 = null;
                        break;
                    }
                    next3 = it4.next();
                } while (!StringsKt.startsWith$default((String) next3, CommonUtils.KEY_RIGHTV_SD_EXTERNAL_CHANNEL_ID, false, 2, (Object) null));
                String str2 = (String) next3;
                if (str2 != null) {
                    strRemovePrefix = StringsKt.removePrefix(str2, (CharSequence) "rightv-sd-external-id:");
                } else {
                    strRemovePrefix = null;
                }
            }
            this.variantExternalId = strRemovePrefix;
            if (channel.getLiveStream() != null) {
                LiveStream liveStream2 = channel.getLiveStream();
                if (liveStream2 == null) {
                    Intrinsics.throwNpe();
                }
                String id2 = liveStream2.getId();
                LiveStream liveStream3 = channel.getLiveStream();
                if (liveStream3 == null) {
                    Intrinsics.throwNpe();
                }
                String title2 = liveStream3.getTitle();
                LiveStream liveStream4 = channel.getLiveStream();
                if (liveStream4 == null) {
                    Intrinsics.throwNpe();
                }
                String summary2 = liveStream4.getSummary();
                LiveStream liveStream5 = channel.getLiveStream();
                if (liveStream5 == null) {
                    Intrinsics.throwNpe();
                }
                List<Artwork> artworks2 = liveStream5.getArtworks();
                LiveStream liveStream6 = channel.getLiveStream();
                if (liveStream6 == null) {
                    Intrinsics.throwNpe();
                }
                String deepLink2 = liveStream6.getDeepLink();
                RightVChannel rightVChannel = this;
                LiveStream liveStream7 = channel.getLiveStream();
                if (liveStream7 == null) {
                    Intrinsics.throwNpe();
                }
                MediaMetadata metadata = liveStream7.getMetadata();
                LiveStream liveStream8 = channel.getLiveStream();
                if (liveStream8 == null) {
                    Intrinsics.throwNpe();
                }
                boolean ratable = liveStream8.getRatable();
                LiveStream liveStream9 = channel.getLiveStream();
                if (liveStream9 == null) {
                    Intrinsics.throwNpe();
                }
                liveStream = new LiveStream(boService, id2, title2, summary2, artworks2, deepLink2, rightVChannel, metadata, ratable, liveStream9.getOffers());
            } else {
                liveStream = null;
            }
            setLiveStream$bokit_android_release(liveStream);
            try {
                LiveTVService liveTVService3 = boService.getLiveTVService();
                if (liveTVService3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                }
                strBlockingGet = ((MegacableLiveTVService) liveTVService3).getModelExternalId().blockingGet();
                this.isInteracive = Intrinsics.areEqual(channel.extra("rightv-tag:" + strBlockingGet + ":interactive"), (Object) true);
                for (String str3 : channel.getExtraKeys()) {
                    String str4 = str3;
                    if (StringsKt.contains$default((CharSequence) str4, (CharSequence) ("rightv-tag:" + strBlockingGet + ":catchup-duration"), false, (int) r10, (Object) null)) {
                        try {
                            this.extraCatchupTime = Integer.parseInt(StringsKt.removePrefix(str3, (CharSequence) ("rightv-tag:" + strBlockingGet + ":catchup-duration:")));
                        } catch (Exception unused) {
                            this.extraCatchupTime = 48;
                        }
                    }
                    if (StringsKt.contains$default((CharSequence) str4, (CharSequence) CommonUtils.KEY_RIGHTV_SD_EXTERNAL_CHANNEL_ID, false, (int) r10, (Object) null) || StringsKt.contains$default((CharSequence) str4, (CharSequence) CommonUtils.KEY_RIGHTV_HD_EXTERNAL_CHANNEL_ID, false, 2, (Object) null)) {
                        this.extras.setChannelVariantIdentifier(str3);
                    }
                }
                PIPLiveStream pIPLiveStream = null;
                if (channel.getLiveStream() != null) {
                    LiveStream liveStream10 = channel.getLiveStream();
                    if (liveStream10 == null) {
                        Intrinsics.throwNpe();
                    }
                    String id3 = liveStream10.getId();
                    LiveStream liveStream11 = channel.getLiveStream();
                    if (liveStream11 == null) {
                        Intrinsics.throwNpe();
                    }
                    bOService = boService;
                    pIPLiveStream = new PIPLiveStream(bOService, id3, liveStream11.getTitle(), this);
                } else {
                    bOService = boService;
                }
                this.pipLiveStream = pIPLiveStream;
                extractPackages(channel);
                ParentalRating parentalRatingForName = ParentalRating.INSTANCE.forName(bOService, "RATING:" + extractParentalRating(channel));
                if (parentalRatingForName == null) {
                    Intrinsics.throwNpe();
                }
                this.parentalRating = parentalRatingForName;
            } catch (Exception unused2) {
                strBlockingGet = "";
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
    }

    /* JADX INFO: renamed from: isInteractive, reason: from getter */
    public final boolean getIsInteracive() {
        return this.isInteracive;
    }

    public final List<String> getPackages() {
        return this.packages;
    }

    public final PIPLiveStream getPipLiveStream() {
        return this.pipLiveStream;
    }

    public final int getExtraCatchupTime() {
        return this.extraCatchupTime;
    }

    public final void setExtraCatchupTime(int i) {
        this.extraCatchupTime = i;
    }

    /* JADX INFO: renamed from: isLocked, reason: from getter */
    public final boolean getIsLocked() {
        return this.isLocked;
    }

    public final void setLocked(boolean z) {
        this.isLocked = z;
    }

    /* JADX INFO: renamed from: isSD, reason: from getter */
    public final boolean getIsSD() {
        return this.isSD;
    }

    /* JADX INFO: renamed from: isHD, reason: from getter */
    public final boolean getIsHD() {
        return this.isHD;
    }

    /* JADX INFO: renamed from: isBrowsable, reason: from getter */
    public final boolean getIsBrowsable() {
        return this.isBrowsable;
    }

    public final String getVariantExternalId() {
        return this.variantExternalId;
    }

    public final ParentalRating getParentalRating() {
        return this.parentalRating;
    }

    public final String getDVBTriplet() {
        ChannelExtraCaster channelExtraCaster = this.extras.getCasters().get("dvb");
        if (channelExtraCaster != null) {
            return channelExtraCaster.getSrc();
        }
        return null;
    }

    private final void extractPackages(Channel channel) {
        for (String str : channel.getExtraKeys()) {
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "package", false, 2, (Object) null)) {
                this.packages.add((String) StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            }
        }
    }

    private final int extractParentalRating(Channel channel) {
        for (String str : channel.getExtraKeys()) {
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "rating", false, 2, (Object) null)) {
                return Integer.parseInt((String) StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            }
        }
        return 0;
    }

    @Override // com.dotscreen.bokit.model.Item
    public String toString() {
        return super.toString() + " - " + getZappingNumber() + ", isInteracive=" + this.isInteracive + ", extras=" + this.extras + ", isSD=" + this.isSD + ", isHD=" + this.isHD + ", variantExternalId=" + this.variantExternalId;
    }
}
