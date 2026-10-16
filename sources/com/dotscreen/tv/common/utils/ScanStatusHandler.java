package com.dotscreen.tv.common.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.dotscreen.tv.common.providers.ScanStatusProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScanStatusHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/tv/common/utils/ScanStatusHandler;", "", "()V", "Companion", "ScanState", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ScanStatusHandler {
    public static final String TAG = "ScanStatusHandler";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String WHERE_LAST_STATUS = "_id=(SELECT max(_id) FROM " + ScanStatusProvider.INSTANCE.getTABLE_NAME() + ')';

    /* JADX INFO: compiled from: ScanStatusHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/tv/common/utils/ScanStatusHandler$ScanState;", "", "(Ljava/lang/String;I)V", "WAITING", "STARTED", "IN_PROGRESS", "FINISHED", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum ScanState {
        WAITING,
        STARTED,
        IN_PROGRESS,
        FINISHED
    }

    /* JADX INFO: compiled from: ScanStatusHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/tv/common/utils/ScanStatusHandler$Companion;", "", "()V", "TAG", "", "WHERE_LAST_STATUS", "getCurrentScanStatus", "Lcom/dotscreen/tv/common/utils/ScanStatusHandler$ScanState;", "context", "Landroid/content/Context;", "setCurrentScanStatus", "", NotificationCompat.CATEGORY_STATUS, "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ScanState.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[ScanState.STARTED.ordinal()] = 1;
                $EnumSwitchMapping$0[ScanState.IN_PROGRESS.ordinal()] = 2;
                $EnumSwitchMapping$0[ScanState.FINISHED.ordinal()] = 3;
                $EnumSwitchMapping$0[ScanState.WAITING.ordinal()] = 4;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void setCurrentScanStatus(Context context, ScanState status) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(status, "status");
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            Log.d(ScanStatusHandler.TAG, "set scan status to " + status);
            int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
            if (i == 1) {
                contentValues.put(ScanStatusProvider.COLUMN_STATUS, ScanStatusProvider.STATUS_STARTED);
                contentResolver.insert(ScanStatusProvider.INSTANCE.getURI(), contentValues);
                return;
            }
            if (i == 2) {
                contentValues.put(ScanStatusProvider.COLUMN_STATUS, ScanStatusProvider.STATUS_IN_PROGRESS);
                contentResolver.update(ScanStatusProvider.INSTANCE.getURI(), contentValues, ScanStatusHandler.WHERE_LAST_STATUS, null);
            } else if (i == 3) {
                contentValues.put(ScanStatusProvider.COLUMN_STATUS, ScanStatusProvider.STATUS_FINISHED);
                contentResolver.update(ScanStatusProvider.INSTANCE.getURI(), contentValues, ScanStatusHandler.WHERE_LAST_STATUS, null);
            } else {
                if (i != 4) {
                    return;
                }
                Log.v(ScanStatusProvider.DATABASE_NAME, "Waiting state");
                contentValues.put(ScanStatusProvider.COLUMN_STATUS, ScanStatusProvider.STATUS_WAITING);
                contentResolver.update(ScanStatusProvider.INSTANCE.getURI(), contentValues, ScanStatusHandler.WHERE_LAST_STATUS, null);
            }
        }

        public final ScanState getCurrentScanStatus(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            ContentResolver contentResolver = context.getContentResolver();
            ScanState scanState = ScanState.WAITING;
            Cursor cursorQuery = contentResolver.query(ScanStatusProvider.INSTANCE.getURI(), new String[]{ScanStatusProvider.COLUMN_STATUS}, null, null, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                Throwable th = (Throwable) null;
                try {
                    Cursor cursor2 = cursor;
                    if (cursor2.moveToLast()) {
                        String string = cursor2.getString(0);
                        if (string != null) {
                            int iHashCode = string.hashCode();
                            if (iHashCode != 393065274) {
                                if (iHashCode != 955642399) {
                                    if (iHashCode == 1203414324 && string.equals(ScanStatusProvider.STATUS_STARTED)) {
                                        scanState = ScanState.STARTED;
                                    }
                                } else if (string.equals(ScanStatusProvider.STATUS_FINISHED)) {
                                    scanState = ScanState.FINISHED;
                                }
                            } else if (string.equals(ScanStatusProvider.STATUS_IN_PROGRESS)) {
                                scanState = ScanState.IN_PROGRESS;
                            }
                        }
                        scanState = ScanState.WAITING;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, th);
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(cursor, th2);
                        throw th3;
                    }
                }
            }
            return scanState;
        }
    }
}
