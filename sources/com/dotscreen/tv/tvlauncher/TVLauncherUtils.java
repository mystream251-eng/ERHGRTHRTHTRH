package com.dotscreen.tv.tvlauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class TVLauncherUtils {
    private static final String TAG = TVLauncherUtils.class.getSimpleName();
    public static final String[] TVLAUNCHER_CHANNELS_PROJECTION = {"_id", "package_name", TvContractCompat.Channels.COLUMN_DISPLAY_NAME, "type", "browsable"};

    public static void scheduleSyncingChannel() {
        Log.d(TAG, "scheduleSyncingChannel...");
        if (Build.VERSION.SDK_INT >= 26) {
            OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(SyncChannelsWorker.class);
            builder.setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build());
            builder.addTag("sync_channels");
            WorkManager workManager = WorkManager.getInstance();
            workManager.cancelAllWorkByTag("sync_channels");
            OneTimeWorkRequest oneTimeWorkRequestBuild = builder.build();
            Log.d(TAG, "scheduleSyncingChannel: enqueue request " + oneTimeWorkRequestBuild.getId());
            workManager.enqueue(oneTimeWorkRequestBuild);
        }
    }

    public static void scheduleSyncingPrograms(long j, Duration duration) {
        if (Build.VERSION.SDK_INT >= 26) {
            OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(SyncProgramsWorker.class);
            if (duration != null) {
                builder.setInitialDelay(duration);
            }
            builder.setInputData(new Data.Builder().putLong(SyncProgramsWorkerKt.DATA_CHANNEL_ID_KEY, j).build());
            builder.setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build());
            String str = "sync_programs_" + j;
            builder.addTag(str);
            WorkManager workManager = WorkManager.getInstance();
            workManager.cancelAllWorkByTag(str);
            workManager.enqueue(builder.build());
        }
    }

    public static void scheduleSyncingProgramsFast(long j, Duration duration) {
        if (Build.VERSION.SDK_INT >= 26) {
            OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(SyncProgramsFastWorker.class);
            if (duration != null) {
                builder.setInitialDelay(duration);
            }
            builder.setInputData(new Data.Builder().putLong(SyncProgramsWorkerKt.DATA_CHANNEL_ID_KEY, j).build());
            builder.setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build());
            String str = "sync_programs_fast_" + j;
            builder.addTag(str);
            WorkManager workManager = WorkManager.getInstance();
            workManager.cancelAllWorkByTag(str);
            workManager.enqueue(builder.build());
        }
    }

    public static void cancelScheduleSyncingProgramsFast(long j) {
        if (Build.VERSION.SDK_INT >= 26) {
            WorkManager.getInstance().cancelAllWorkByTag("sync_programs_fast_" + j);
        }
    }

    public static int getNumberOfTVLauncherChannels(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, TVLAUNCHER_CHANNELS_PROJECTION, null, null, null);
        int i = 0;
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            do {
                Channel channelFromCursor = Channel.fromCursor(cursorQuery);
                if (TvContractCompat.Channels.TYPE_PREVIEW.equals(channelFromCursor.getType()) && context.getPackageName().equals(channelFromCursor.getPackageName())) {
                    Log.d(TAG, "found: " + channelFromCursor);
                    i++;
                }
            } while (cursorQuery.moveToNext());
        }
        return i;
    }

    public static final void saveTVLauncherProgramIds(Context context, long j, Map<Long, Long> map) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("tvlauncher_channels", 0).edit();
        JSONObject jSONObject = new JSONObject();
        for (Long l : map.keySet()) {
            try {
                jSONObject.put("" + l, map.get(l));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        editorEdit.putString("program_ids_" + j, jSONObject.toString());
        editorEdit.commit();
    }

    public static final Map<Long, Long> getTVLauncherProgramIds(Context context, long j) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("tvlauncher_channels", 0);
        HashMap map = new HashMap();
        String string = sharedPreferences.getString("program_ids_" + j, null);
        if (string != null) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    try {
                        map.put(Long.valueOf(Long.parseLong(next)), Long.valueOf(jSONObject.getLong(next)));
                    } catch (JSONException unused) {
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
