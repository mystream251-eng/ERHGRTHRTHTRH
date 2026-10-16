package com.viaccessorca.analytics.fma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class FmaDatabaseHelper extends SQLiteOpenHelper {
    public static final String CONST_TIMESTAMP = "timestamp";
    public static final String DATABASE_NAME = "FMA_DATABASE";
    public static final String LAST_BOOT_TIME = "lastBootTime";
    public static final String OFFLINE_BOOT_EVENTS_TABLE_NAME = "OfflineBootEvents";
    public static final String OFFLINE_CATCHUP_EVENTS_TABLE_NAME = "OfflineCatchupEvents";
    public static final String OFFLINE_CONFIG_TABLE_NAME = "OfflineConfiguration";
    public static final String OFFLINE_DOWNLOAD_EVENTS_TABLE_NAME = "OfflineDownloadEvents";
    public static final String OFFLINE_EVENTS_TABLE_NAME = "OfflineEvents";
    public static final String OFFLINE_LIVE_EVENTS_TABLE_NAME = "OfflineLiveEvents";
    public static final String OFFLINE_LOGGING_EVENTS_TABLE_NAME = "OfflineLoggingEvents";
    public static final String OFFLINE_NETWORK_EVENTS_TABLE_NAME = "OfflineNetworkStEvents";
    public static final String OFFLINE_NPVR_EVENTS_TABLE_NAME = "OfflineNpvrEvents";
    public static final String OFFLINE_PREVIEW_EVENTS_TABLE_NAME = "OfflinePreviewEvents";
    public static final String OFFLINE_SERVICE_EVENTS_TABLE_NAME = "OfflineServiceEvents";
    public static final String OFFLINE_STARTOVER_EVENTS_TABLE_NAME = "OfflineStartoverEvents";
    public static final String OFFLINE_VOD_EVENTS_TABLE_NAME = "OfflineVodEvents";
    public static final String ON_FAILURE_LOGGING_EVENTS_TABLE_NAME = "OnFailureLoggingEvents";
    private static final String TAG = "VO_FMA";

    public FmaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            Log.v(TAG, "Creating database");
            sQLiteDatabase.execSQL("create table OfflineConfiguration(id integer primary key, config text)");
            sQLiteDatabase.execSQL("create table lastBootTime(id integer primary key, event bigint)");
            sQLiteDatabase.execSQL("create table OfflineLoggingEvents(id integer primary key, event text, timestamp long)");
            sQLiteDatabase.execSQL("create table OfflineBootEvents(id integer primary key, event text, timestamp long)");
            sQLiteDatabase.execSQL("create table OfflineServiceEvents(id integer primary key, event text, timestamp long)");
            sQLiteDatabase.execSQL("create table OfflineNetworkStEvents(id integer primary key, event text, timestamp long)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            Log.v(TAG, "Updating database");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS lastBootTime");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS OfflineLoggingEvents");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS OfflineServiceEvents");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS OfflineBootEvents");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS OfflineNetworkStEvents");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS OfflineConfiguration");
            onCreate(sQLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upgradeDatabaseIfNeeded() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.execSQL("create table IF NOT EXISTS OfflineConfiguration(id integer primary key, config text)");
            writableDatabase.execSQL("create table IF NOT EXISTS lastBootTime(id integer primary key, event bigint)");
            writableDatabase.execSQL("create table IF NOT EXISTS OfflineLoggingEvents(id integer primary key, event text, timestamp long)");
            writableDatabase.execSQL("create table IF NOT EXISTS OfflineBootEvents(id integer primary key, event text, timestamp long)");
            writableDatabase.execSQL("create table IF NOT EXISTS OfflineServiceEvents(id integer primary key, event text, timestamp long)");
            writableDatabase.execSQL("create table IF NOT EXISTS OfflineNetworkStEvents(id integer primary key, event text, timestamp long)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addEvent(VoFmaEvent voFmaEvent, String str) {
        try {
            Log.d(TAG, "Adding event : " + voFmaEvent.getCustomType() + " to database");
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            voFmaEvent.setCustomParameter("offline_event", "true");
            contentValues.put(NotificationCompat.CATEGORY_EVENT, voFmaEvent.getJsonObject().toString());
            writableDatabase.insert(str, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean addLastBootTime(long j) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotificationCompat.CATEGORY_EVENT, Long.valueOf(j));
            writableDatabase.insert(LAST_BOOT_TIME, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public long getLastBootTimeFromDatabase() {
        long j = 0;
        try {
            Cursor cursorRawQuery = getWritableDatabase().rawQuery("select * from lastBootTime", null);
            if (cursorRawQuery.getCount() == 0) {
                Log.v(TAG, "Nothing found in the table : lastBootTime");
            }
            while (cursorRawQuery.moveToNext()) {
                try {
                    j = cursorRawQuery.getLong(1);
                } catch (Exception e) {
                    Log.d("Error", e.toString());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return j;
    }

    public boolean updateLastBootTime(long j) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotificationCompat.CATEGORY_EVENT, Long.valueOf(j));
            writableDatabase.replace(LAST_BOOT_TIME, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public void deleteBootTimeTable() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.delete(LAST_BOOT_TIME, null, null);
                writableDatabase.setTransactionSuccessful();
            } catch (Exception unused) {
                Log.d(TAG, "Error while trying to delete all elements from table: lastBootTime");
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public boolean addLoggingEvent(VoFmaEvent voFmaEvent, int i, int i2) {
        if (getTableElementsCount(OFFLINE_LOGGING_EVENTS_TABLE_NAME) >= i2) {
            deleteOldestEventsFromTable(OFFLINE_LOGGING_EVENTS_TABLE_NAME, i2);
        }
        return addEvent(voFmaEvent, OFFLINE_LOGGING_EVENTS_TABLE_NAME);
    }

    public boolean addNetworkStatusEvent(VoFmaEvent voFmaEvent) {
        List<JSONObject> allNetworkStatusEvents = getAllNetworkStatusEvents();
        if (allNetworkStatusEvents == null || allNetworkStatusEvents.size() <= 0) {
            return addEvent(voFmaEvent, OFFLINE_NETWORK_EVENTS_TABLE_NAME);
        }
        return true;
    }

    public boolean addServiceEvent(VoFmaEvent voFmaEvent) {
        List<JSONObject> allServicesEvents = getAllServicesEvents();
        if (allServicesEvents == null || allServicesEvents.size() <= 0) {
            return addEvent(voFmaEvent, OFFLINE_SERVICE_EVENTS_TABLE_NAME);
        }
        return true;
    }

    public boolean addBootStatusEvent(VoFmaEvent voFmaEvent) {
        return addEvent(voFmaEvent, OFFLINE_BOOT_EVENTS_TABLE_NAME);
    }

    public boolean addLiveEvent(VoFmaEvent voFmaEvent) {
        return addEvent(voFmaEvent, "OfflineLiveEvents");
    }

    public boolean addStartoverEvent(VoFmaEvent voFmaEvent) {
        return addEvent(voFmaEvent, "OfflineStartoverEvents");
    }

    public boolean addCatchupEvent(VoFmaEvent voFmaEvent) {
        return addEvent(voFmaEvent, "OfflineCatchupEvents");
    }

    public boolean addVodEvent(VoFmaEvent voFmaEvent) {
        return addEvent(voFmaEvent, "OfflineVodEvents");
    }

    public boolean addPreviewEvent(VoFmaEvent voFmaEvent) {
        return addEvent(voFmaEvent, "OfflinePreviewEvents");
    }

    public boolean addNpvrEvent(VoFmaEvent voFmaEvent) {
        return addEvent(voFmaEvent, "OfflineNpvrEvents");
    }

    public boolean addDownloadEvent(VoFmaEvent voFmaEvent) {
        return addEvent(voFmaEvent, "OfflineDownloadEvents");
    }

    public List<JSONObject> getAllEvents(String str) {
        Log.d(TAG, "Retrieving events from table: " + str);
        ArrayList arrayList = new ArrayList();
        try {
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
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public List<JSONObject> getAllLoggingEvents() {
        return getAllEvents(OFFLINE_LOGGING_EVENTS_TABLE_NAME);
    }

    public List<JSONObject> getAllServicesEvents() {
        return getAllEvents(OFFLINE_SERVICE_EVENTS_TABLE_NAME);
    }

    public List<JSONObject> getAllNetworkStatusEvents() {
        return getAllEvents(OFFLINE_NETWORK_EVENTS_TABLE_NAME);
    }

    public List<JSONObject> getAllBootStatusEvents() {
        return getAllEvents(OFFLINE_BOOT_EVENTS_TABLE_NAME);
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
        Log.d(TAG, "deleting all events from table: " + str);
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

    public void deleteAllLoggingEvents() {
        deleteAllEvents(OFFLINE_LOGGING_EVENTS_TABLE_NAME);
    }

    public void deleteAllServicesEvents() {
        deleteAllEvents(OFFLINE_SERVICE_EVENTS_TABLE_NAME);
    }

    public void deleteAllNetworkStatusEvents() {
        deleteAllEvents(OFFLINE_NETWORK_EVENTS_TABLE_NAME);
    }

    public void deleteAllBootStatusEvents() {
        deleteAllEvents(OFFLINE_BOOT_EVENTS_TABLE_NAME);
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

    public void deleteOldestEventsFromTable(String str, int i) {
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            readableDatabase.execSQL("DELETE FROM " + str + " WHERE id IN (SELECT id FROM " + str + " ORDER BY " + CONST_TIMESTAMP + " DESC LIMIT -1 OFFSET " + i + " )");
            readableDatabase.close();
        } catch (Exception e) {
            Log.v(TAG, "Failed to delete the older then " + i + " elements from " + str);
            e.printStackTrace();
        }
    }

    public void deleteOldEventsFromTable(String str, long j) {
        Log.d(TAG, "Deleting old events from table: " + str);
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            readableDatabase.execSQL("DELETE FROM " + str + " WHERE " + CONST_TIMESTAMP + " >=" + j);
            readableDatabase.close();
        } catch (Exception e) {
            Log.v(TAG, "Failed to delete events older then " + j + " from the table " + str);
            e.printStackTrace();
        }
    }

    public long getTableElementsCount(String str) {
        long jQueryNumEntries = 0;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            jQueryNumEntries = DatabaseUtils.queryNumEntries(readableDatabase, str);
            readableDatabase.close();
            return jQueryNumEntries;
        } catch (Exception e) {
            Log.v(TAG, "Failed to get the count of elements from " + str);
            e.printStackTrace();
            return jQueryNumEntries;
        }
    }

    public boolean storeLastReceivedConfiguration(String str) {
        Log.d(TAG, "Adding config : " + str + " to database");
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("config", str);
            writableDatabase.insert(OFFLINE_CONFIG_TABLE_NAME, null, contentValues);
            Log.v(TAG, "Configuration is Stored");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public String getStoredConfiguration() {
        String string = null;
        try {
            Cursor cursorRawQuery = getWritableDatabase().rawQuery("select * from OfflineConfiguration", null);
            if (cursorRawQuery.getCount() == 0) {
                Log.v(TAG, "Nothing configuration found in the table : OfflineConfiguration");
            }
            while (cursorRawQuery.moveToNext()) {
                try {
                    string = cursorRawQuery.getString(1);
                } catch (Exception e) {
                    Log.d("Error", e.toString());
                }
            }
            Log.d(TAG, "Retrieved config from database: " + string);
            return string;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void deleteStoredConfiguration() {
        Log.d(TAG, "Deleting last stored configuration in database");
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.delete(OFFLINE_CONFIG_TABLE_NAME, null, null);
                writableDatabase.setTransactionSuccessful();
            } catch (Exception unused) {
                Log.d(TAG, "Error while trying to delete last stored configuration");
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }
}
