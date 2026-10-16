package com.dotscreen.tv.common.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import com.dotscreen.tv.common.utils.DatabaseHelper;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DotscreenProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 &2\u00020\u0001:\u0002&'B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0002J/\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013J\n\u0010\u0014\u001a\u0004\u0018\u00010\u000fH&J\b\u0010\u0015\u001a\u00020\u0006H&J\b\u0010\u0016\u001a\u00020\rH&J\u0006\u0010\u0017\u001a\u00020\u0006J\b\u0010\u0018\u001a\u00020\u0006H&J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016JK\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00122\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00122\b\u0010\"\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010#J9\u0010$\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010%R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/dotscreen/tv/common/providers/DotscreenProvider;", "Landroid/content/ContentProvider;", "rowData", "Lcom/dotscreen/tv/common/providers/DotscreenProvider$RowData;", "(Lcom/dotscreen/tv/common/providers/DotscreenProvider$RowData;)V", "TAG", "", "kotlin.jvm.PlatformType", "db", "Landroid/database/sqlite/SQLiteDatabase;", "cleanupDbFromDuplicates", "", "delete", "", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getContentUri", "getDatabaseName", "getDatabaseVersion", "getTableName", "getTableScheme", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", SearchIntents.EXTRA_QUERY, "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "RowData", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class DotscreenProvider extends ContentProvider {
    public static final String _ID = "_id";
    private final String TAG;
    private SQLiteDatabase db;
    private final RowData rowData;

    /* JADX INFO: compiled from: DotscreenProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004H&¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/tv/common/providers/DotscreenProvider$RowData;", "", "fromCursor", "cursor", "Landroid/database/Cursor;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface RowData {
        RowData fromCursor(Cursor cursor);
    }

    public abstract Uri getContentUri();

    public abstract String getDatabaseName();

    public abstract int getDatabaseVersion();

    public abstract String getTableScheme();

    public DotscreenProvider(RowData rowData) {
        Intrinsics.checkParameterIsNotNull(rowData, "rowData");
        this.rowData = rowData;
        this.TAG = getClass().getSimpleName();
    }

    public final String getTableName() {
        String databaseName = getDatabaseName();
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (databaseName == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = databaseName.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() throws IOException {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.db = new DatabaseHelper(context, getDatabaseName(), getDatabaseVersion(), getTableScheme(), getTableName()).getWritableDatabase();
        cleanupDbFromDuplicates();
        return this.db != null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        SQLiteDatabase sQLiteDatabase = this.db;
        Long lValueOf = sQLiteDatabase != null ? Long.valueOf(sQLiteDatabase.insert(getTableName(), "", values)) : null;
        if (lValueOf != null && lValueOf.longValue() > 0) {
            Uri uriWithAppendedId = ContentUris.withAppendedId(getContentUri(), lValueOf.longValue());
            Context context = getContext();
            if (context != null) {
                context.getContentResolver().notifyChange(uriWithAppendedId, null);
            }
            return uriWithAppendedId;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) throws IOException {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        cleanupDbFromDuplicates();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(getTableName());
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.db, projection, selection, selectionArgs, null, null, null);
        Context context = getContext();
        if (context != null) {
            cursorQuery.setNotificationUri(context.getContentResolver(), uri);
        }
        return cursorQuery;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        try {
            SQLiteDatabase sQLiteDatabase = this.db;
            Integer numValueOf = sQLiteDatabase != null ? Integer.valueOf(sQLiteDatabase.delete(getTableName(), selection, selectionArgs)) : null;
            Context context = getContext();
            if (context != null) {
                context.getContentResolver().notifyChange(uri, null);
            }
            if (numValueOf != null) {
                return numValueOf.intValue();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.TAG, "error delete database");
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        SQLiteDatabase sQLiteDatabase = this.db;
        Integer numValueOf = sQLiteDatabase != null ? Integer.valueOf(sQLiteDatabase.update(getTableName(), values, selection, selectionArgs)) : null;
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        return "vnd.android.cursor.dir/vnd.diplomatico." + getTableName();
    }

    private final void cleanupDbFromDuplicates() throws IOException {
        Log.d(this.TAG, "cleanupDbFromDuplicates...");
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(getTableName());
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.db, null, null, null, null, null, null);
        Throwable th = (Throwable) null;
        try {
            Cursor c = cursorQuery;
            while (c.moveToNext()) {
                RowData rowData = this.rowData;
                Intrinsics.checkExpressionValueIsNotNull(c, "c");
                RowData rowDataFromCursor = rowData.fromCursor(c);
                if (linkedHashSet.contains(rowDataFromCursor)) {
                    Log.d(this.TAG, "cleanupDbFromDuplicates: found duplicate #" + c.getInt(0) + " -> " + rowDataFromCursor + '(' + rowDataFromCursor.getClass().getSimpleName() + ')');
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
                    sQLiteDatabase.delete(getTableName(), "_ID = " + iIntValue, null);
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
