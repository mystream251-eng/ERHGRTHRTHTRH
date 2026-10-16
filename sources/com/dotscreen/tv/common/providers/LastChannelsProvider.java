package com.dotscreen.tv.common.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LastChannelsProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001f !B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0002J/\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016JK\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u001cJ9\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u001eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/dotscreen/tv/common/providers/LastChannelsProvider;", "Landroid/content/ContentProvider;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "db", "Landroid/database/sqlite/SQLiteDatabase;", "cleanupDbFromDuplicates", "", "delete", "", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", SearchIntents.EXTRA_QUERY, "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "DatabaseHelper", "RowData", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class LastChannelsProvider extends ContentProvider {
    private static final String COLUMN_CHANNEL_ID;
    private static final String COLUMN_INPUT_ID;
    private static final String COLUMN_PROFILE_ID;
    private static final Uri CONTENT_URI;
    private static final String CREATE_DB_TABLE;
    private static final String DATABASE_NAME;
    private static final int DATABASE_VERSION;
    private static final String TABLE_NAME;
    private static final String URL;
    private static final String _ID;
    private final String TAG = LastChannelsProvider.class.getSimpleName();
    private SQLiteDatabase db;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PROVIDER_NAME = "com.dotscreen.tv.LastChannelsProvider";

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        return "vnd.android.cursor.dir/vnd.diplomatico.lastchannels";
    }

    /* JADX INFO: compiled from: LastChannelsProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0019\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0006R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006¨\u0006\u001b"}, d2 = {"Lcom/dotscreen/tv/common/providers/LastChannelsProvider$Companion;", "", "()V", "COLUMN_CHANNEL_ID", "", "getCOLUMN_CHANNEL_ID", "()Ljava/lang/String;", "COLUMN_INPUT_ID", "getCOLUMN_INPUT_ID", "COLUMN_PROFILE_ID", "getCOLUMN_PROFILE_ID", "CONTENT_URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "getCONTENT_URI", "()Landroid/net/Uri;", "CREATE_DB_TABLE", "DATABASE_NAME", "DATABASE_VERSION", "", "PROVIDER_NAME", "getPROVIDER_NAME", "TABLE_NAME", "URL", "getURL", "_ID", "get_ID", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getPROVIDER_NAME() {
            return LastChannelsProvider.PROVIDER_NAME;
        }

        public final String getURL() {
            return LastChannelsProvider.URL;
        }

        public final Uri getCONTENT_URI() {
            return LastChannelsProvider.CONTENT_URI;
        }

        public final String get_ID() {
            return LastChannelsProvider._ID;
        }

        public final String getCOLUMN_CHANNEL_ID() {
            return LastChannelsProvider.COLUMN_CHANNEL_ID;
        }

        public final String getCOLUMN_INPUT_ID() {
            return LastChannelsProvider.COLUMN_INPUT_ID;
        }

        public final String getCOLUMN_PROFILE_ID() {
            return LastChannelsProvider.COLUMN_PROFILE_ID;
        }
    }

    static {
        String str = "content://" + PROVIDER_NAME + "/last";
        URL = str;
        CONTENT_URI = Uri.parse(str);
        _ID = "_id";
        COLUMN_CHANNEL_ID = "channel_id";
        COLUMN_INPUT_ID = "input_id";
        COLUMN_PROFILE_ID = "profile_id";
        DATABASE_NAME = "LastChannels";
        TABLE_NAME = "lastchannels";
        DATABASE_VERSION = 2;
        CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CHANNEL_ID + " INTEGER NOT NULL, " + COLUMN_INPUT_ID + " TEXT NOT NULL, " + COLUMN_PROFILE_ID + " TEXT NOT NULL);";
    }

    /* JADX INFO: compiled from: LastChannelsProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\"\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/tv/common/providers/LastChannelsProvider$DatabaseHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onCreate", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final class DatabaseHelper extends SQLiteOpenHelper {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DatabaseHelper(Context context) {
            super(context, LastChannelsProvider.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, LastChannelsProvider.DATABASE_VERSION);
            Intrinsics.checkParameterIsNotNull(context, "context");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            if (db != null) {
                db.execSQL(LastChannelsProvider.CREATE_DB_TABLE);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (db != null) {
                db.execSQL("DROP TABLE IF EXISTS " + LastChannelsProvider.TABLE_NAME);
            }
            onCreate(db);
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() throws IOException {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.db = new DatabaseHelper(context).getWritableDatabase();
        cleanupDbFromDuplicates();
        return this.db != null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        SQLiteDatabase sQLiteDatabase = this.db;
        Long lValueOf = sQLiteDatabase != null ? Long.valueOf(sQLiteDatabase.insert(TABLE_NAME, "", values)) : null;
        if (lValueOf != null && lValueOf.longValue() > 0) {
            Uri uriWithAppendedId = ContentUris.withAppendedId(CONTENT_URI, lValueOf.longValue());
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            context.getContentResolver().notifyChange(uriWithAppendedId, null);
            return uriWithAppendedId;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) throws IOException {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        cleanupDbFromDuplicates();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(TABLE_NAME);
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.db, projection, selection, selectionArgs, null, null, null);
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        cursorQuery.setNotificationUri(context.getContentResolver(), uri);
        return cursorQuery;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        try {
            SQLiteDatabase sQLiteDatabase = this.db;
            Integer numValueOf = sQLiteDatabase != null ? Integer.valueOf(sQLiteDatabase.delete(TABLE_NAME, selection, selectionArgs)) : null;
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            context.getContentResolver().notifyChange(uri, null);
            if (numValueOf != null) {
                return numValueOf.intValue();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LastChannelsProvider", "error delete database");
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        SQLiteDatabase sQLiteDatabase = this.db;
        Integer numValueOf = sQLiteDatabase != null ? Integer.valueOf(sQLiteDatabase.update(TABLE_NAME, values, selection, selectionArgs)) : null;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        context.getContentResolver().notifyChange(uri, null);
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    /* JADX INFO: compiled from: LastChannelsProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0082\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/dotscreen/tv/common/providers/LastChannelsProvider$RowData;", "", "channelId", "", "inputId", "", ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, "(ILjava/lang/String;Ljava/lang/String;)V", "getChannelId", "()I", "getInputId", "()Ljava/lang/String;", "getProfileId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class RowData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final int channelId;
        private final String inputId;
        private final String profileId;

        public static /* synthetic */ RowData copy$default(RowData rowData, int i, String str, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = rowData.channelId;
            }
            if ((i2 & 2) != 0) {
                str = rowData.inputId;
            }
            if ((i2 & 4) != 0) {
                str2 = rowData.profileId;
            }
            return rowData.copy(i, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getChannelId() {
            return this.channelId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getInputId() {
            return this.inputId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getProfileId() {
            return this.profileId;
        }

        public final RowData copy(int channelId, String inputId, String profileId) {
            Intrinsics.checkParameterIsNotNull(inputId, "inputId");
            Intrinsics.checkParameterIsNotNull(profileId, "profileId");
            return new RowData(channelId, inputId, profileId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RowData)) {
                return false;
            }
            RowData rowData = (RowData) other;
            return this.channelId == rowData.channelId && Intrinsics.areEqual(this.inputId, rowData.inputId) && Intrinsics.areEqual(this.profileId, rowData.profileId);
        }

        public String toString() {
            return "RowData(channelId=" + this.channelId + ", inputId=" + this.inputId + ", profileId=" + this.profileId + ")";
        }

        /* JADX INFO: compiled from: LastChannelsProvider.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/tv/common/providers/LastChannelsProvider$RowData$Companion;", "", "()V", "fromCursor", "Lcom/dotscreen/tv/common/providers/LastChannelsProvider$RowData;", "cursor", "Landroid/database/Cursor;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final RowData fromCursor(Cursor cursor) {
                Intrinsics.checkParameterIsNotNull(cursor, "cursor");
                int i = cursor.getInt(1);
                String string = cursor.getString(2);
                Intrinsics.checkExpressionValueIsNotNull(string, "cursor.getString(2)");
                String string2 = cursor.getString(3);
                Intrinsics.checkExpressionValueIsNotNull(string2, "cursor.getString(3)");
                return new RowData(i, string, string2);
            }
        }

        public RowData(int i, String inputId, String profileId) {
            Intrinsics.checkParameterIsNotNull(inputId, "inputId");
            Intrinsics.checkParameterIsNotNull(profileId, "profileId");
            this.channelId = i;
            this.inputId = inputId;
            this.profileId = profileId;
        }

        public final int getChannelId() {
            return this.channelId;
        }

        public final String getInputId() {
            return this.inputId;
        }

        public final String getProfileId() {
            return this.profileId;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.channelId), this.inputId, this.profileId);
        }
    }

    private final void cleanupDbFromDuplicates() throws IOException {
        Log.d(this.TAG, "cleanupDbFromDuplicates...");
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(TABLE_NAME);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.db, null, null, null, null, null, null);
        Throwable th = (Throwable) null;
        try {
            Cursor c = cursorQuery;
            while (c.moveToNext()) {
                RowData.Companion companion = RowData.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(c, "c");
                RowData rowDataFromCursor = companion.fromCursor(c);
                if (linkedHashSet.contains(rowDataFromCursor)) {
                    Log.d(this.TAG, "cleanupDbFromDuplicates: found duplicate #" + c.getInt(0) + " -> " + rowDataFromCursor);
                    arrayList.add(Integer.valueOf(c.getInt(0)));
                } else {
                    linkedHashSet.add(rowDataFromCursor);
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorQuery, th);
            Log.d(this.TAG, "cleanupDbFromDuplicates: to clean = " + arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                SQLiteDatabase sQLiteDatabase = this.db;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.delete(TABLE_NAME, "_ID = " + iIntValue, null);
                }
            }
            Log.d(this.TAG, "cleanupDbFromDuplicates: done");
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(cursorQuery, th2);
                throw th3;
            }
        }
    }
}
