package com.dotscreen.tv.common.providers;

import android.content.ContentProvider;
import android.content.ContentResolver;
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
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChannelLanguageProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001f !B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0002J1\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016JO\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\f\u001a\u00020\r2\u0010\u0010\u001a\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u001cJ;\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u001eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/dotscreen/tv/common/providers/ChannelLanguageProvider;", "Landroid/content/ContentProvider;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "db", "Landroid/database/sqlite/SQLiteDatabase;", "cleanupDbFromDuplicates", "", "delete", "", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", SearchIntents.EXTRA_QUERY, "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "DatabaseHelper", "RowData", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ChannelLanguageProvider extends ContentProvider {
    public static final String COLUMN_CHANNEL_ID = "channel_id";
    public static final String COLUMN_PREFERRED_AUDIO = "pref_audio";
    public static final String COLUMN_PREFERRED_SUBTITLE = "pref_subtitle";
    public static final String COLUMN_PROFILE_ID = "profile_id";
    private static final Uri CONTENT_URI;
    private static final String CREATE_DB_TABLE = "CREATE TABLE channellanguage (_id INTEGER PRIMARY KEY AUTOINCREMENT, channel_id TEXT NOT NULL,pref_audio TEXT NOT NULL,pref_subtitle TEXT NOT NULL,profile_id TEXT NOT NULL);";
    private static final String DATABASE_NAME = "ChannelLanguage";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "channellanguage";
    private static final String URL;
    public static final String _ID = "_id";
    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase db;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PROVIDER_NAME = "com.dotscreen.tv.ChannelLanguageProvider";

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        return "vnd.android.cursor.dir/vnd.diplomatico.channellanguage";
    }

    /* JADX INFO: compiled from: ChannelLanguageProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/tv/common/providers/ChannelLanguageProvider$Companion;", "", "()V", "COLUMN_CHANNEL_ID", "", "COLUMN_PREFERRED_AUDIO", "COLUMN_PREFERRED_SUBTITLE", "COLUMN_PROFILE_ID", "CONTENT_URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "getCONTENT_URI", "()Landroid/net/Uri;", "CREATE_DB_TABLE", "DATABASE_NAME", "DATABASE_VERSION", "", "PROVIDER_NAME", "getPROVIDER_NAME", "()Ljava/lang/String;", "TABLE_NAME", "URL", "getURL", "_ID", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getPROVIDER_NAME() {
            return ChannelLanguageProvider.PROVIDER_NAME;
        }

        public final String getURL() {
            return ChannelLanguageProvider.URL;
        }

        public final Uri getCONTENT_URI() {
            return ChannelLanguageProvider.CONTENT_URI;
        }
    }

    static {
        String str = "content://" + PROVIDER_NAME + "/language";
        URL = str;
        CONTENT_URI = Uri.parse(str);
    }

    /* JADX INFO: compiled from: ChannelLanguageProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\"\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/tv/common/providers/ChannelLanguageProvider$DatabaseHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onCreate", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final class DatabaseHelper extends SQLiteOpenHelper {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DatabaseHelper(Context context) {
            super(context, ChannelLanguageProvider.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
            Intrinsics.checkParameterIsNotNull(context, "context");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            if (db != null) {
                db.execSQL(ChannelLanguageProvider.CREATE_DB_TABLE);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (db != null) {
                db.execSQL("DROP TABLE IF EXISTS channellanguage");
            }
            onCreate(db);
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() throws IOException {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.db = new DatabaseHelper(context).getWritableDatabase();
        cleanupDbFromDuplicates();
        return this.db != null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) throws IOException {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        cleanupDbFromDuplicates();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(TABLE_NAME);
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.db, projection, selection, selectionArgs, null, null, null);
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        cursorQuery.setNotificationUri(context.getContentResolver(), uri);
        return cursorQuery;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        ContentResolver contentResolver;
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        SQLiteDatabase sQLiteDatabase = this.db;
        Long lValueOf = sQLiteDatabase != null ? Long.valueOf(sQLiteDatabase.insert(TABLE_NAME, "", values)) : null;
        if (lValueOf != null && lValueOf.longValue() > 0) {
            Uri uriWithAppendedId = ContentUris.withAppendedId(CONTENT_URI, lValueOf.longValue());
            Context context = getContext();
            if (context != null && (contentResolver = context.getContentResolver()) != null) {
                contentResolver.notifyChange(uriWithAppendedId, null);
            }
            return uriWithAppendedId;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        ContentResolver contentResolver;
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        try {
            SQLiteDatabase sQLiteDatabase = this.db;
            Integer numValueOf = sQLiteDatabase != null ? Integer.valueOf(sQLiteDatabase.delete(TABLE_NAME, selection, selectionArgs)) : null;
            Context context = getContext();
            if (context != null && (contentResolver = context.getContentResolver()) != null) {
                contentResolver.notifyChange(uri, null);
            }
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
        ContentResolver contentResolver;
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        SQLiteDatabase sQLiteDatabase = this.db;
        Integer numValueOf = sQLiteDatabase != null ? Integer.valueOf(sQLiteDatabase.update(TABLE_NAME, values, selection, selectionArgs)) : null;
        Context context = getContext();
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            contentResolver.notifyChange(uri, null);
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    /* JADX INFO: compiled from: ChannelLanguageProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/dotscreen/tv/common/providers/ChannelLanguageProvider$RowData;", "", "channelId", "", "preferredAudio", "preferredSubtitle", ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannelId", "()Ljava/lang/String;", "getPreferredAudio", "getPreferredSubtitle", "getProfileId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class RowData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String channelId;
        private final String preferredAudio;
        private final String preferredSubtitle;
        private final String profileId;

        public static /* synthetic */ RowData copy$default(RowData rowData, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = rowData.channelId;
            }
            if ((i & 2) != 0) {
                str2 = rowData.preferredAudio;
            }
            if ((i & 4) != 0) {
                str3 = rowData.preferredSubtitle;
            }
            if ((i & 8) != 0) {
                str4 = rowData.profileId;
            }
            return rowData.copy(str, str2, str3, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getChannelId() {
            return this.channelId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPreferredAudio() {
            return this.preferredAudio;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPreferredSubtitle() {
            return this.preferredSubtitle;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getProfileId() {
            return this.profileId;
        }

        public final RowData copy(String channelId, String preferredAudio, String preferredSubtitle, String profileId) {
            Intrinsics.checkParameterIsNotNull(channelId, "channelId");
            Intrinsics.checkParameterIsNotNull(preferredAudio, "preferredAudio");
            Intrinsics.checkParameterIsNotNull(preferredSubtitle, "preferredSubtitle");
            Intrinsics.checkParameterIsNotNull(profileId, "profileId");
            return new RowData(channelId, preferredAudio, preferredSubtitle, profileId);
        }

        public String toString() {
            return "RowData(channelId=" + this.channelId + ", preferredAudio=" + this.preferredAudio + ", preferredSubtitle=" + this.preferredSubtitle + ", profileId=" + this.profileId + ")";
        }

        /* JADX INFO: compiled from: ChannelLanguageProvider.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/tv/common/providers/ChannelLanguageProvider$RowData$Companion;", "", "()V", "fromCursor", "Lcom/dotscreen/tv/common/providers/ChannelLanguageProvider$RowData;", "cursor", "Landroid/database/Cursor;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final RowData fromCursor(Cursor cursor) {
                Intrinsics.checkParameterIsNotNull(cursor, "cursor");
                String string = cursor.getString(1);
                Intrinsics.checkExpressionValueIsNotNull(string, "cursor.getString(1)");
                String string2 = cursor.getString(2);
                Intrinsics.checkExpressionValueIsNotNull(string2, "cursor.getString(2)");
                String string3 = cursor.getString(3);
                Intrinsics.checkExpressionValueIsNotNull(string3, "cursor.getString(3)");
                String string4 = cursor.getString(4);
                Intrinsics.checkExpressionValueIsNotNull(string4, "cursor.getString(4)");
                return new RowData(string, string2, string3, string4);
            }
        }

        public RowData(String channelId, String preferredAudio, String preferredSubtitle, String profileId) {
            Intrinsics.checkParameterIsNotNull(channelId, "channelId");
            Intrinsics.checkParameterIsNotNull(preferredAudio, "preferredAudio");
            Intrinsics.checkParameterIsNotNull(preferredSubtitle, "preferredSubtitle");
            Intrinsics.checkParameterIsNotNull(profileId, "profileId");
            this.channelId = channelId;
            this.preferredAudio = preferredAudio;
            this.preferredSubtitle = preferredSubtitle;
            this.profileId = profileId;
        }

        public final String getChannelId() {
            return this.channelId;
        }

        public final String getPreferredAudio() {
            return this.preferredAudio;
        }

        public final String getPreferredSubtitle() {
            return this.preferredSubtitle;
        }

        public final String getProfileId() {
            return this.profileId;
        }

        public int hashCode() {
            return Objects.hash(this.channelId, this.profileId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            if (other != null) {
                RowData rowData = (RowData) other;
                return ((Intrinsics.areEqual(this.channelId, rowData.channelId) ^ true) || (Intrinsics.areEqual(this.profileId, rowData.profileId) ^ true)) ? false : true;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.providers.ChannelLanguageProvider.RowData");
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
