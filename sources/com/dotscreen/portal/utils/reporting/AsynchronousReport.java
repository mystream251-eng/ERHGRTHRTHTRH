package com.dotscreen.portal.utils.reporting;

import android.util.Log;
import com.dotscreen.portal.utils.String_ExtensionKt;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsynchronousReport.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u0006H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0017\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\n¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/portal/utils/reporting/AsynchronousReport;", "", "reportName", "", "(Ljava/lang/String;)V", "endDate", "Ljava/util/Date;", "getEndDate", "()Ljava/util/Date;", "setEndDate", "(Ljava/util/Date;)V", "issueDuration", "", "getIssueDuration", "()Ljava/lang/Long;", "setIssueDuration", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "issueDurationHumanReadable", "getIssueDurationHumanReadable", "()Ljava/lang/String;", "setIssueDurationHumanReadable", "getReportName", "startDate", "getStartDate", "setStartDate", "endReport", "", "startReport", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class AsynchronousReport {
    public Date endDate;
    private Long issueDuration;
    public String issueDurationHumanReadable;
    private final String reportName;
    public Date startDate;

    public AsynchronousReport(String reportName) {
        Intrinsics.checkParameterIsNotNull(reportName, "reportName");
        this.reportName = reportName;
    }

    public final String getReportName() {
        return this.reportName;
    }

    public final Date getStartDate() {
        Date date = this.startDate;
        if (date == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startDate");
        }
        return date;
    }

    public final void setStartDate(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "<set-?>");
        this.startDate = date;
    }

    public final Date getEndDate() {
        Date date = this.endDate;
        if (date == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endDate");
        }
        return date;
    }

    public final void setEndDate(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "<set-?>");
        this.endDate = date;
    }

    public final Long getIssueDuration() {
        return this.issueDuration;
    }

    public final void setIssueDuration(Long l) {
        this.issueDuration = l;
    }

    public final String getIssueDurationHumanReadable() {
        String str = this.issueDurationHumanReadable;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("issueDurationHumanReadable");
        }
        return str;
    }

    public final void setIssueDurationHumanReadable(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.issueDurationHumanReadable = str;
    }

    public void startReport(Date startDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        this.startDate = startDate;
        StringBuilder sb = new StringBuilder();
        sb.append("Report start: ");
        sb.append(this.reportName);
        sb.append("{@");
        sb.append(hashCode());
        sb.append("} at ");
        Date date = this.startDate;
        if (date == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startDate");
        }
        sb.append(date);
        Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, sb.toString());
    }

    public void endReport(Date endDate) {
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        this.endDate = endDate;
        if (endDate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endDate");
        }
        long time = endDate.getTime();
        Date date = this.startDate;
        if (date == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startDate");
        }
        Long lValueOf = Long.valueOf(time - date.getTime());
        this.issueDuration = lValueOf;
        if (lValueOf == null) {
            Intrinsics.throwNpe();
        }
        this.issueDurationHumanReadable = String_ExtensionKt.convertMillisToDuration(lValueOf.longValue());
        StringBuilder sb = new StringBuilder();
        sb.append("Report end: ");
        sb.append(this.reportName);
        sb.append("@{");
        sb.append(hashCode());
        sb.append("} at ");
        Date date2 = this.endDate;
        if (date2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endDate");
        }
        sb.append(date2);
        Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("     durationOfTheIssue: ");
        String str = this.issueDurationHumanReadable;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("issueDurationHumanReadable");
        }
        sb2.append(str);
        Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, sb2.toString());
    }
}
