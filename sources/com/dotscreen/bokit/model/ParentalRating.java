package com.dotscreen.bokit.model;

import android.media.tv.TvContentRating;
import android.os.Build;
import com.dotscreen.bokit.services.BOService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParentalRating.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u0016B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0011\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/dotscreen/bokit/model/ParentalRating;", "Lcom/dotscreen/bokit/model/Item;", "", "boService", "Lcom/dotscreen/bokit/services/BOService;", "id", "", "title", "level", "", "isAdult", "", "(Lcom/dotscreen/bokit/services/BOService;Ljava/lang/String;Ljava/lang/String;IZ)V", "()Z", "getLevel", "()I", "compareTo", "other", "equals", "", "toTvContentRating", "Landroid/media/tv/TvContentRating;", "Companion", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class ParentalRating extends Item implements Comparable<ParentalRating> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BOService boService;
    private final boolean isAdult;
    private final int level;

    public final int getLevel() {
        return this.level;
    }

    public /* synthetic */ ParentalRating(BOService bOService, String str, String str2, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(bOService, str, str2, i, (i2 & 16) != 0 ? false : z);
    }

    /* JADX INFO: renamed from: isAdult, reason: from getter */
    public final boolean getIsAdult() {
        return this.isAdult;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ParentalRating(BOService boService, String id, String title, int i, boolean z) {
        super(id, title, null, false, null, 28, null);
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        this.boService = boService;
        this.level = i;
        this.isAdult = z;
    }

    /* JADX INFO: compiled from: ParentalRating.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/bokit/model/ParentalRating$Companion;", "", "()V", "forName", "Lcom/dotscreen/bokit/model/ParentalRating;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "name", "", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ParentalRating forName(BOService boService, String name) {
            Intrinsics.checkNotNullParameter(boService, "boService");
            Intrinsics.checkNotNullParameter(name, "name");
            return boService.getLiveTVService().parentalRatingForName(name);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(ParentalRating other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.level - other.level;
    }

    @Override // com.dotscreen.bokit.model.Item
    public boolean equals(Object other) {
        return (other instanceof ParentalRating) && this.level == ((ParentalRating) other).level;
    }

    public final TvContentRating toTvContentRating() {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        int i = this.level;
        if (i == 10) {
            return TvContentRating.createRating(this.boService.getContext().getPackageName(), "FR_DVB", "FR_DVB_10", new String[0]);
        }
        if (i == 12) {
            return TvContentRating.createRating(this.boService.getContext().getPackageName(), "FR_DVB", "FR_DVB_12", new String[0]);
        }
        if (i == 16) {
            return TvContentRating.createRating(this.boService.getContext().getPackageName(), "FR_DVB", "FR_DVB_16", new String[0]);
        }
        if (i != 18) {
            return null;
        }
        return TvContentRating.createRating(this.boService.getContext().getPackageName(), "FR_DVB", "FR_DVB_18", new String[0]);
    }
}
