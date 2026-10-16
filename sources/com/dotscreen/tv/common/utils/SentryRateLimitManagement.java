package com.dotscreen.tv.common.utils;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import io.sentry.SentryEvent;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SentryRateLimitManagement.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÂ\u0003J\u0019\u0010\u0007\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\fJ\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/tv/common/utils/SentryRateLimitManagement;", "", "snLastDigitsList", "", "", "(Ljava/util/List;)V", "component1", "copy", "equals", "", "other", "filterEvent", "Lio/sentry/SentryEvent;", "serialNumber", NotificationCompat.CATEGORY_EVENT, "hashCode", "", "toString", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class SentryRateLimitManagement {
    private final List<String> snLastDigitsList;

    /* JADX WARN: Multi-variable type inference failed */
    public SentryRateLimitManagement() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private final List<String> component1() {
        return this.snLastDigitsList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SentryRateLimitManagement copy$default(SentryRateLimitManagement sentryRateLimitManagement, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = sentryRateLimitManagement.snLastDigitsList;
        }
        return sentryRateLimitManagement.copy(list);
    }

    public final SentryRateLimitManagement copy(List<String> snLastDigitsList) {
        Intrinsics.checkParameterIsNotNull(snLastDigitsList, "snLastDigitsList");
        return new SentryRateLimitManagement(snLastDigitsList);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof SentryRateLimitManagement) && Intrinsics.areEqual(this.snLastDigitsList, ((SentryRateLimitManagement) other).snLastDigitsList);
        }
        return true;
    }

    public int hashCode() {
        List<String> list = this.snLastDigitsList;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "SentryRateLimitManagement(snLastDigitsList=" + this.snLastDigitsList + ")";
    }

    public SentryRateLimitManagement(List<String> snLastDigitsList) {
        Intrinsics.checkParameterIsNotNull(snLastDigitsList, "snLastDigitsList");
        this.snLastDigitsList = snLastDigitsList;
    }

    public /* synthetic */ SentryRateLimitManagement(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.listOf((Object[]) new String[]{"0204", "0158", "0089"}) : list);
    }

    public final SentryEvent filterEvent(String serialNumber, SentryEvent event) throws IOException {
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        for (String str : this.snLastDigitsList) {
            int length = str.length();
            String str2 = serialNumber;
            StringBuilder sb = new StringBuilder();
            int length2 = str2.length();
            for (int i = 0; i < length2; i++) {
                char cCharAt = str2.charAt(i);
                if (Character.isDigit(cCharAt)) {
                    sb.append(cCharAt);
                }
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "filterTo(StringBuilder(), predicate).toString()");
            if (StringsKt.endsWith$default(StringsKt.takeLast(string, length), str, false, 2, (Object) null)) {
                return event;
            }
        }
        Log.v("Sentry", event + " was filtered");
        return null;
    }
}
