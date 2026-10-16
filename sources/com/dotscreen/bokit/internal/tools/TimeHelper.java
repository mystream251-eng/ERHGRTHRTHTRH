package com.dotscreen.bokit.internal.tools;

import androidx.room.RoomDatabase;
import java.util.Calendar;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimeHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/bokit/internal/tools/TimeHelper;", "", "()V", "mockedNow", "Ljava/util/Date;", "getMockedNow$bokit_android_release", "()Ljava/util/Date;", "setMockedNow$bokit_android_release", "(Ljava/util/Date;)V", "endOfDay", "date", "now", "startOfDay", "tomorrow", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class TimeHelper {
    public static final TimeHelper INSTANCE = new TimeHelper();
    private static Date mockedNow;

    private TimeHelper() {
    }

    public final Date getMockedNow$bokit_android_release() {
        return mockedNow;
    }

    public final void setMockedNow$bokit_android_release(Date date) {
        mockedNow = date;
    }

    public final Date now() {
        Date date = mockedNow;
        if (date != null) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "Calendar.getInstance()");
        Date time = calendar.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "Calendar.getInstance().time");
        return time;
    }

    public final Date startOfDay(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar cal = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(cal, "cal");
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        Date time = cal.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "cal.time");
        return time;
    }

    public final Date endOfDay(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar cal = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(cal, "cal");
        cal.setTime(date);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, RoomDatabase.MAX_BIND_PARAMETER_CNT);
        Date time = cal.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "cal.time");
        return time;
    }

    public final Date tomorrow(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar cal = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(cal, "cal");
        cal.setTime(date);
        cal.add(5, 1);
        Date time = cal.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "cal.time");
        return time;
    }
}
