package com.dotscreen.tv.common.utils;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SentryReportFlags.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006#"}, d2 = {"Lcom/dotscreen/tv/common/utils/SentryReportFlags;", "", "voPlayer", "", "uiOptimizer", "rightv", "updater", "degraded", "internet", "(DDDDDD)V", "getDegraded", "()D", "getInternet", "getRightv", "getUiOptimizer", "getUpdater", "getVoPlayer", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "filterEvent", "Lio/sentry/SentryEvent;", NotificationCompat.CATEGORY_EVENT, "hint", "hashCode", "", "toString", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final /* data */ class SentryReportFlags {
    private final double degraded;
    private final double internet;
    private final double rightv;
    private final double uiOptimizer;
    private final double updater;
    private final double voPlayer;

    public SentryReportFlags() {
        this(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 63, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final double getVoPlayer() {
        return this.voPlayer;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final double getUiOptimizer() {
        return this.uiOptimizer;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final double getRightv() {
        return this.rightv;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final double getUpdater() {
        return this.updater;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final double getDegraded() {
        return this.degraded;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final double getInternet() {
        return this.internet;
    }

    public final SentryReportFlags copy(double voPlayer, double uiOptimizer, double rightv, double updater, double degraded, double internet) {
        return new SentryReportFlags(voPlayer, uiOptimizer, rightv, updater, degraded, internet);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SentryReportFlags)) {
            return false;
        }
        SentryReportFlags sentryReportFlags = (SentryReportFlags) other;
        return Double.compare(this.voPlayer, sentryReportFlags.voPlayer) == 0 && Double.compare(this.uiOptimizer, sentryReportFlags.uiOptimizer) == 0 && Double.compare(this.rightv, sentryReportFlags.rightv) == 0 && Double.compare(this.updater, sentryReportFlags.updater) == 0 && Double.compare(this.degraded, sentryReportFlags.degraded) == 0 && Double.compare(this.internet, sentryReportFlags.internet) == 0;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.voPlayer);
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.uiOptimizer);
        int i = ((((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)))) * 31;
        long jDoubleToLongBits3 = Double.doubleToLongBits(this.rightv);
        int i2 = (i + ((int) (jDoubleToLongBits3 ^ (jDoubleToLongBits3 >>> 32)))) * 31;
        long jDoubleToLongBits4 = Double.doubleToLongBits(this.updater);
        int i3 = (i2 + ((int) (jDoubleToLongBits4 ^ (jDoubleToLongBits4 >>> 32)))) * 31;
        long jDoubleToLongBits5 = Double.doubleToLongBits(this.degraded);
        int i4 = (i3 + ((int) (jDoubleToLongBits5 ^ (jDoubleToLongBits5 >>> 32)))) * 31;
        long jDoubleToLongBits6 = Double.doubleToLongBits(this.internet);
        return i4 + ((int) ((jDoubleToLongBits6 >>> 32) ^ jDoubleToLongBits6));
    }

    public String toString() {
        return "SentryReportFlags(voPlayer=" + this.voPlayer + ", uiOptimizer=" + this.uiOptimizer + ", rightv=" + this.rightv + ", updater=" + this.updater + ", degraded=" + this.degraded + ", internet=" + this.internet + ")";
    }

    public SentryReportFlags(double d, double d2, double d3, double d4, double d5, double d6) {
        this.voPlayer = d;
        this.uiOptimizer = d2;
        this.rightv = d3;
        this.updater = d4;
        this.degraded = d5;
        this.internet = d6;
    }

    public final double getVoPlayer() {
        return this.voPlayer;
    }

    public final double getUiOptimizer() {
        return this.uiOptimizer;
    }

    public final double getRightv() {
        return this.rightv;
    }

    public final double getUpdater() {
        return this.updater;
    }

    public final double getDegraded() {
        return this.degraded;
    }

    public /* synthetic */ SentryReportFlags(double d, double d2, double d3, double d4, double d5, double d6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0.0d : d2, (i & 4) != 0 ? 0.0d : d3, (i & 8) != 0 ? 0.0d : d4, (i & 16) != 0 ? 0.0d : d5, (i & 32) == 0 ? d6 : 0.0d);
    }

    public final double getInternet() {
        return this.internet;
    }

    public final SentryEvent filterEvent(SentryEvent event, Object hint) {
        StringBuilder sb;
        String str;
        SentryEvent sentryEvent = null;
        if (hint != SentryReportHint.VOPlayer ? hint != SentryReportHint.UIOptimizer ? hint != SentryReportHint.RightV ? hint != SentryReportHint.Updater ? hint != SentryReportHint.Degraded ? hint != SentryReportHint.Internet || Math.random() < this.internet : Math.random() < this.degraded : Math.random() < this.updater : Math.random() < this.rightv : Math.random() < this.uiOptimizer : Math.random() < this.voPlayer) {
            sentryEvent = event;
        }
        Log.v("Sentry", "event = " + event);
        Log.v("Sentry", "hint = " + hint);
        if (sentryEvent == null) {
            sb = new StringBuilder();
            sb.append(event);
            str = " was filtered (2)";
        } else {
            sb = new StringBuilder();
            sb.append(event);
            str = " is fired (2)";
        }
        sb.append(str);
        Log.v("Sentry", sb.toString());
        return sentryEvent;
    }
}
