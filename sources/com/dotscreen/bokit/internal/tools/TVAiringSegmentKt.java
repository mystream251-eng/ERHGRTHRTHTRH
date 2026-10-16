package com.dotscreen.bokit.internal.tools;

import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TVAiringSegment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"TVAIRING_SEGMENT_SIZE", "", "dateForSegmentId", "Ljava/util/Date;", "id", "segmentIdForDate", "date", "segmentIsCached", "", "segmentId", "bokit-android_release"}, k = 2, mv = {1, 4, 1})
public final class TVAiringSegmentKt {
    public static final long TVAIRING_SEGMENT_SIZE = 86400000;

    public static final long segmentIdForDate(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return (long) Math.floor(date.getTime() / TVAIRING_SEGMENT_SIZE);
    }

    public static final Date dateForSegmentId(long j) {
        return new Date(j * TVAIRING_SEGMENT_SIZE);
    }

    public static final boolean segmentIsCached(long j) {
        long jSegmentIdForDate = segmentIdForDate(TimeHelper.INSTANCE.now());
        return jSegmentIdForDate - 1 <= j && jSegmentIdForDate + ((long) 3) >= j;
    }
}
