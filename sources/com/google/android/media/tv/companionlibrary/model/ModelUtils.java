package com.google.android.media.tv.companionlibrary.model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.media.tv.companionlibrary.utils.TvContractUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ModelUtils {
    private static final boolean DEBUG = false;
    private static final String TAG = "ModelUtils";

    public interface OnChannelDeletedCallback {
        void onChannelDeleted(long j);
    }

    public static void updateChannels(Context context, String str, List<Channel> list, OnChannelDeletedCallback onChannelDeletedCallback) throws Throwable {
        int i;
        Uri uriBuildChannelUri;
        LongSparseArray longSparseArray = new LongSparseArray();
        Uri uriBuildChannelsUriForInput = TvContract.buildChannelsUriForInput(str);
        String[] strArr = {"_id", TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            Cursor cursorQuery = contentResolver.query(uriBuildChannelsUriForInput, strArr, null, null, null);
            while (cursorQuery != null) {
                try {
                    if (!cursorQuery.moveToNext()) {
                        break;
                    } else {
                        longSparseArray.put(cursorQuery.getLong(1), Long.valueOf(cursorQuery.getLong(0)));
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            HashMap map = new HashMap();
            int i2 = 0;
            int i3 = 0;
            for (Channel channel : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("input_id", str);
                contentValues.putAll(channel.toContentValues());
                if (channel.getPackageName() == null) {
                    contentValues.put("package_name", context.getPackageName());
                }
                if (channel.getInputId() == null) {
                    contentValues.put("input_id", str);
                }
                if (channel.getType() == null) {
                    contentValues.put("type", TvContractCompat.Channels.TYPE_OTHER);
                }
                Long l = (Long) longSparseArray.get(channel.getOriginalNetworkId());
                if (l == null) {
                    uriBuildChannelUri = contentResolver.insert(TvContract.Channels.CONTENT_URI, contentValues);
                    i3++;
                } else {
                    contentValues.put("_id", l);
                    uriBuildChannelUri = TvContract.buildChannelUri(l.longValue());
                    contentResolver.update(uriBuildChannelUri, contentValues, null, null);
                    i2++;
                    longSparseArray.remove(channel.getOriginalNetworkId());
                }
                if (channel.getChannelLogo() != null && !TextUtils.isEmpty(channel.getChannelLogo())) {
                    map.put(TvContract.buildChannelLogoUri(uriBuildChannelUri), channel.getChannelLogo());
                }
            }
            if (map.isEmpty()) {
                i = 0;
            } else {
                i = 0;
                new TvContractUtils.InsertLogosTask(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, map);
            }
            int size = longSparseArray.size();
            for (int i4 = i; i4 < size; i4++) {
                Long l2 = (Long) longSparseArray.valueAt(i4);
                contentResolver.delete(TvContract.buildChannelUri(l2.longValue()), null, null);
                if (onChannelDeletedCallback != null) {
                    onChannelDeletedCallback.onChannelDeleted(l2.longValue());
                }
            }
            Log.i(TAG, str + " sync " + list.size() + " channels. Deleted " + size + " updated " + i2 + " added " + i3);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0069  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r6v2 */
    public static LongSparseArray<Channel> buildChannelMap(ContentResolver contentResolver, String str) throws Throwable {
        Cursor cursorQuery;
        Uri uriBuildChannelsUriForInput = TvContract.buildChannelsUriForInput(str);
        LongSparseArray<Channel> longSparseArray = new LongSparseArray<>();
        ?? r6 = 0;
        try {
            try {
                cursorQuery = contentResolver.query(uriBuildChannelsUriForInput, Channel.PROJECTION, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() != 0) {
                            while (cursorQuery.moveToNext()) {
                                Channel channelFromCursor = Channel.fromCursor(cursorQuery);
                                longSparseArray.put(channelFromCursor.getId(), channelFromCursor);
                            }
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return longSparseArray;
                        }
                    } catch (Exception e) {
                        e = e;
                        Log.d(TAG, "Content provider query: " + Arrays.toString(e.getStackTrace()));
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            } catch (Throwable th) {
                th = th;
                r6 = contentResolver;
                if (r6 != 0) {
                    r6.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r6 != 0) {
                r6.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x003e A[DONT_GENERATE, PHI: r1
  0x003e: PHI (r1v3 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v4 android.database.Cursor) binds: [B:21:0x003c, B:12:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
    public static List<Channel> getChannels(ContentResolver contentResolver) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = contentResolver.query(TvContract.Channels.CONTENT_URI, Channel.PROJECTION, null, null, null);
                if (cursorQuery != null && cursorQuery.getCount() != 0) {
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(Channel.fromCursor(cursorQuery));
                    }
                    return arrayList;
                }
                return arrayList;
            } catch (Exception e) {
                Log.w(TAG, "Unable to get channels", e);
            }
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0062  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v2 */
    public static Channel getChannel(ContentResolver contentResolver, Uri uri) throws Throwable {
        Cursor cursorQuery;
        ?? r1 = 0;
        try {
            try {
                cursorQuery = contentResolver.query(uri, Channel.PROJECTION, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() != 0) {
                            cursorQuery.moveToNext();
                            Channel channelFromCursor = Channel.fromCursor(cursorQuery);
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return channelFromCursor;
                        }
                    } catch (Exception e) {
                        e = e;
                        Log.w(TAG, "Unable to get the channel with URI " + uri, e);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                }
                Log.w(TAG, "No channel matches " + uri);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            } catch (Throwable th) {
                th = th;
                r1 = contentResolver;
                if (r1 != 0) {
                    r1.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r1 != 0) {
                r1.close();
            }
            throw th;
        }
    }

    public static List<Program> getPrograms(ContentResolver contentResolver, Uri uri) {
        Cursor cursorQuery = null;
        if (uri == null) {
            return null;
        }
        Uri uriBuildProgramsUriForChannel = TvContract.buildProgramsUriForChannel(uri);
        ArrayList arrayList = new ArrayList();
        try {
            try {
                cursorQuery = contentResolver.query(uriBuildProgramsUriForChannel, Program.PROJECTION, null, null, null);
                if (cursorQuery != null && cursorQuery.getCount() != 0) {
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(Program.fromCursor(cursorQuery));
                    }
                    return arrayList;
                }
                return arrayList;
            } catch (Exception e) {
                Log.w(TAG, "Unable to get programs for " + uri, e);
            }
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    public static Program getCurrentProgram(ContentResolver contentResolver, Uri uri) {
        List<Program> programs = getPrograms(contentResolver, uri);
        if (programs == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (Program program : programs) {
            if (program.getStartTimeUtcMillis() <= jCurrentTimeMillis && program.getEndTimeUtcMillis() > jCurrentTimeMillis) {
                return program;
            }
        }
        return null;
    }

    public static Program getNextProgram(ContentResolver contentResolver, Uri uri, Program program) {
        int iIndexOf;
        if (program == null) {
            return getCurrentProgram(contentResolver, uri);
        }
        List<Program> programs = getPrograms(contentResolver, uri);
        if (programs != null && (iIndexOf = programs.indexOf(program) + 1) < programs.size()) {
            return programs.get(iIndexOf);
        }
        return null;
    }

    private ModelUtils() {
    }
}
