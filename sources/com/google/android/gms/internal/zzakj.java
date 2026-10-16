package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzakj implements Callable<String> {
    private /* synthetic */ Context zza;
    private /* synthetic */ Context zzb;

    zzakj(zzaki zzakiVar, Context context, Context context2) {
        this.zza = context;
        this.zzb = context2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        SharedPreferences sharedPreferences;
        boolean z = false;
        if (this.zza != null) {
            zzahw.zza("Attempting to read user agent from Google Play Services.");
            sharedPreferences = this.zza.getSharedPreferences("admob_user_agent", 0);
        } else {
            zzahw.zza("Attempting to read user agent from local cache.");
            sharedPreferences = this.zzb.getSharedPreferences("admob_user_agent", 0);
            z = true;
        }
        String string = sharedPreferences.getString("user_agent", "");
        if (TextUtils.isEmpty(string)) {
            zzahw.zza("Reading user agent from WebSettings");
            string = WebSettings.getDefaultUserAgent(this.zzb);
            if (z) {
                sharedPreferences.edit().putString("user_agent", string).apply();
                zzahw.zza("Persisting user agent.");
            }
        }
        return string;
    }
}
