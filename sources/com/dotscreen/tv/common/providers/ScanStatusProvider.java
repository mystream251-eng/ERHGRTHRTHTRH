package com.dotscreen.tv.common.providers;

import android.database.Cursor;
import android.net.Uri;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScanStatusProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lcom/dotscreen/tv/common/providers/ScanStatusProvider;", "Lcom/dotscreen/tv/common/providers/DotscreenProvider;", "()V", "getContentUri", "Landroid/net/Uri;", "getDatabaseName", "", "getDatabaseVersion", "", "getTableScheme", "Companion", "ScanStatutRowData", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ScanStatusProvider extends DotscreenProvider {
    public static final String COLUMN_STATUS = "scan_status";
    public static final String DATABASE_NAME = "ScanStatus";
    public static final String STATUS_FINISHED = "status_finished";
    public static final String STATUS_IN_PROGRESS = "status_in_progress";
    public static final String STATUS_STARTED = "status_started";
    public static final String STATUS_WAITING = "status_waiting";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Uri URI = Uri.parse("content://com.dotscreen.tv.ScanStatusProvider/status");
    private static final String TABLE_NAME = "scanstatus";

    @Override // com.dotscreen.tv.common.providers.DotscreenProvider
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override // com.dotscreen.tv.common.providers.DotscreenProvider
    public int getDatabaseVersion() {
        return 1;
    }

    /* JADX INFO: compiled from: ScanStatusProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/tv/common/providers/ScanStatusProvider$Companion;", "", "()V", "COLUMN_STATUS", "", "DATABASE_NAME", "STATUS_FINISHED", "STATUS_IN_PROGRESS", "STATUS_STARTED", "STATUS_WAITING", "TABLE_NAME", "getTABLE_NAME", "()Ljava/lang/String;", "URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "getURI", "()Landroid/net/Uri;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Uri getURI() {
            return ScanStatusProvider.URI;
        }

        public final String getTABLE_NAME() {
            return ScanStatusProvider.TABLE_NAME;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ScanStatusProvider() {
        super(new ScanStatutRowData(null, 1, 0 == true ? 1 : 0));
    }

    /* JADX INFO: compiled from: ScanStatusProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/tv/common/providers/ScanStatusProvider$ScanStatutRowData;", "Lcom/dotscreen/tv/common/providers/DotscreenProvider$RowData;", "scanStatus", "", "(Ljava/lang/String;)V", "equals", "", "other", "", "fromCursor", "cursor", "Landroid/database/Cursor;", "hashCode", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class ScanStatutRowData implements DotscreenProvider.RowData {
        private final String scanStatus;

        /* JADX WARN: Multi-variable type inference failed */
        public ScanStatutRowData() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public ScanStatutRowData(String scanStatus) {
            Intrinsics.checkParameterIsNotNull(scanStatus, "scanStatus");
            this.scanStatus = scanStatus;
        }

        public /* synthetic */ ScanStatutRowData(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ScanStatusProvider.STATUS_WAITING : str);
        }

        @Override // com.dotscreen.tv.common.providers.DotscreenProvider.RowData
        public ScanStatutRowData fromCursor(Cursor cursor) {
            Intrinsics.checkParameterIsNotNull(cursor, "cursor");
            String string = cursor.getString(1);
            Intrinsics.checkExpressionValueIsNotNull(string, "cursor.getString(1)");
            return new ScanStatutRowData(string);
        }

        public int hashCode() {
            return Objects.hash(this.scanStatus);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            if (other != null) {
                return !(Intrinsics.areEqual(this.scanStatus, ((ScanStatutRowData) other).scanStatus) ^ true);
            }
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.providers.ScanStatusProvider.ScanStatutRowData");
        }
    }

    @Override // com.dotscreen.tv.common.providers.DotscreenProvider
    public String getTableScheme() {
        return "CREATE TABLE " + getTableName() + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, scan_status TEXT NOT NULL);";
    }

    @Override // com.dotscreen.tv.common.providers.DotscreenProvider
    public Uri getContentUri() {
        return URI;
    }
}
