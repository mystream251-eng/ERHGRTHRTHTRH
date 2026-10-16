package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzahz extends zzaib {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzahy zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzahz(zzahy zzahyVar, Context context) {
        super(null);
        this.zzb = zzahyVar;
        this.zza = context;
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        SharedPreferences sharedPreferences = this.zza.getSharedPreferences("admob", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        synchronized (this.zzb.zzb) {
            this.zzb.zze = sharedPreferences;
            this.zzb.zza = editorEdit;
            zzahy zzahyVar = this.zzb;
            zzahy zzahyVar2 = this.zzb;
            zzahyVar.zzf = zzahy.zzn();
            this.zzb.zzg = this.zzb.zze.getBoolean("use_https", this.zzb.zzg);
            this.zzb.zzs = this.zzb.zze.getBoolean("content_url_opted_out", this.zzb.zzs);
            this.zzb.zzh = this.zzb.zze.getString("content_url_hashes", this.zzb.zzh);
            this.zzb.zzj = this.zzb.zze.getBoolean("auto_collect_location", this.zzb.zzj);
            this.zzb.zzt = this.zzb.zze.getBoolean("content_vertical_opted_out", this.zzb.zzt);
            this.zzb.zzi = this.zzb.zze.getString("content_vertical_hashes", this.zzb.zzi);
            this.zzb.zzp = this.zzb.zze.getInt("version_code", this.zzb.zzp);
            this.zzb.zzk = this.zzb.zze.getString("app_settings_json", this.zzb.zzk);
            this.zzb.zzl = this.zzb.zze.getLong("app_settings_last_update_ms", this.zzb.zzl);
            this.zzb.zzm = this.zzb.zze.getLong("app_last_background_time_ms", this.zzb.zzm);
            this.zzb.zzo = this.zzb.zze.getInt("request_in_session_count", this.zzb.zzo);
            this.zzb.zzn = this.zzb.zze.getLong("first_ad_req_time_ms", this.zzb.zzn);
            this.zzb.zzq = this.zzb.zze.getStringSet("never_pool_slots", this.zzb.zzq);
            try {
                this.zzb.zzr = new JSONObject(this.zzb.zze.getString("native_advanced_settings", "{}"));
            } catch (JSONException e) {
                zzahw.zzc("Could not convert native advanced settings to json object", e);
            }
            this.zzb.zza(this.zzb.zzp());
        }
    }
}
