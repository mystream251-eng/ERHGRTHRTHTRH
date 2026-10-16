package com.android.tv.common;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

/* JADX INFO: loaded from: classes.dex */
public final class SharedPreferencesUtils {
    public static final String SHARED_PREF_AUDIO_CAPABILITIES = "com.android.tv.audio_capabilities";
    public static final String SHARED_PREF_BROWSABLE = "browsable_shared_preference";
    public static final String SHARED_PREF_FEATURES = "sharePreferencesFeatures";
    public static final String SHARED_PREF_NOTIFICATION_NUMBER = "number_last_update_preference";
    public static final String SHARED_PREF_RECURRING_RUNNER = "sharedPreferencesRecurringRunner";
    public static final String SHARED_PREF_WATCHED_HISTORY = "watched_history_shared_preference";
    private static boolean sInitializeCalled;

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.tv.common.SharedPreferencesUtils$1] */
    public static synchronized void initialize(final Context context) {
        if (!sInitializeCalled) {
            sInitializeCalled = true;
            new AsyncTask<Void, Void, Void>() { // from class: com.android.tv.common.SharedPreferencesUtils.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    PreferenceManager.getDefaultSharedPreferences(context);
                    context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_FEATURES, 0);
                    context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_BROWSABLE, 0);
                    context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_WATCHED_HISTORY, 0);
                    context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_AUDIO_CAPABILITIES, 0);
                    context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_RECURRING_RUNNER, 0);
                    context.getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_NOTIFICATION_NUMBER, 0);
                    return null;
                }
            }.execute(new Void[0]);
        }
    }

    private SharedPreferencesUtils() {
    }
}
