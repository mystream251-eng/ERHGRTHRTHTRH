package com.viaccessorca.analytics.dca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class DcaDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DCA_DATABASE";
    public static final String OFFLINE_CATCHUP_EVENTS_TABLE_NAME = "OfflineCatchupEvents";
    public static final String OFFLINE_DOWNLOAD_EVENTS_TABLE_NAME = "OfflineDownloadEvents";
    public static final String OFFLINE_EVENTS_TABLE_NAME = "OfflineEvents";
    public static final String OFFLINE_LIVE_EVENTS_TABLE_NAME = "OfflineLiveEvents";
    public static final String OFFLINE_NPVR_EVENTS_TABLE_NAME = "OfflineNpvrEvents";
    public static final String OFFLINE_PREVIEW_EVENTS_TABLE_NAME = "OfflinePreviewEvents";
    public static final String OFFLINE_STARTOVER_EVENTS_TABLE_NAME = "OfflineStartoverEvents";
    public static final String OFFLINE_VOD_EVENTS_TABLE_NAME = "OfflineVodEvents";
    private static final String TAG = "VO_DCA";

    public DcaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table OfflineEvents(id integer primary key, event text)");
        sQLiteDatabase.execSQL("create table OfflineLiveEvents(id integer primary key, event text)");
        sQLiteDatabase.execSQL("create table OfflineStartoverEvents(id integer primary key, event text)");
        sQLiteDatabase.execSQL("create table OfflineCatchupEvents(id integer primary key, event text)");
        sQLiteDatabase.execSQL("create table OfflineVodEvents(id integer primary key, event text)");
        sQLiteDatabase.execSQL("create table OfflinePreviewEvents(id integer primary key, event text)");
        sQLiteDatabase.execSQL("create table OfflineNpvrEvents(id integer primary key, event text)");
        sQLiteDatabase.execSQL("create table OfflineDownloadEvents(id integer primary key, event text)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS OfflineEvents");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS OfflineLiveEvents");
        onCreate(sQLiteDatabase);
    }

    public boolean addEvent(VoDcaEvent voDcaEvent, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotificationCompat.CATEGORY_EVENT, voDcaEvent.getJsonObject().toString());
        writableDatabase.insert(str, null, contentValues);
        return true;
    }

    public boolean addLiveEvent(VoDcaEvent voDcaEvent) {
        return addEvent(voDcaEvent, "OfflineLiveEvents");
    }

    public boolean addStartoverEvent(VoDcaEvent voDcaEvent) {
        return addEvent(voDcaEvent, "OfflineStartoverEvents");
    }

    public boolean addCatchupEvent(VoDcaEvent voDcaEvent) {
        return addEvent(voDcaEvent, "OfflineCatchupEvents");
    }

    public boolean addVodEvent(VoDcaEvent voDcaEvent) {
        return addEvent(voDcaEvent, "OfflineVodEvents");
    }

    public boolean addPreviewEvent(VoDcaEvent voDcaEvent) {
        return addEvent(voDcaEvent, "OfflinePreviewEvents");
    }

    public boolean addNpvrEvent(VoDcaEvent voDcaEvent) {
        return addEvent(voDcaEvent, "OfflineNpvrEvents");
    }

    public boolean addDownloadEvent(VoDcaEvent voDcaEvent) {
        return addEvent(voDcaEvent, "OfflineDownloadEvents");
    }

    public List<JSONObject> getAllEvents(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = getWritableDatabase().rawQuery("select * from " + str, null);
        if (cursorRawQuery.getCount() == 0) {
            Log.v(TAG, "Nothing found in the table : " + str);
        }
        while (cursorRawQuery.moveToNext()) {
            try {
                arrayList.add(new JSONObject(cursorRawQuery.getString(1)));
            } catch (JSONException e) {
                Log.d("Error", e.toString());
            }
        }
        return arrayList;
    }

    public List<JSONObject> getAllLiveEvents() {
        return getAllEvents("OfflineLiveEvents");
    }

    public List<JSONObject> getAllStartoverEvents() {
        return getAllEvents("OfflineStartoverEvents");
    }

    public List<JSONObject> getAllCatchupEvents() {
        return getAllEvents("OfflineCatchupEvents");
    }

    public List<JSONObject> getAllVodEvents() {
        return getAllEvents("OfflineVodEvents");
    }

    public List<JSONObject> getAllPreviewEvents() {
        return getAllEvents("OfflinePreviewEvents");
    }

    public List<JSONObject> getAllDownloadEvents() {
        return getAllEvents("OfflineDownloadEvents");
    }

    public List<JSONObject> getAllNpvrEvents() {
        return getAllEvents("OfflineNpvrEvents");
    }

    public void deleteAllEvents(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.delete(str, null, null);
                writableDatabase.setTransactionSuccessful();
            } catch (Exception unused) {
                Log.d(TAG, "Error while trying to delete all events from table: " + str);
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public void deleteAllLiveEvents() {
        deleteAllEvents("OfflineLiveEvents");
    }

    public void deleteAllStartoverEvents() {
        deleteAllEvents("OfflineStartoverEvents");
    }

    public void deleteAllCatchupEvents() {
        deleteAllEvents("OfflineCatchupEvents");
    }

    public void deleteAllVodEvents() {
        deleteAllEvents("OfflineVodEvents");
    }

    public void deleteAllPreviewEvents() {
        deleteAllEvents("OfflinePreviewEvents");
    }

    public void deleteAllNpvrEvents() {
        deleteAllEvents("OfflineNpvrEvents");
    }

    public void deleteAllDownloadEvents() {
        deleteAllEvents("OfflineDownloadEvents");
    }
}
