package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahy {
    SharedPreferences.Editor zza;
    private zzalt<?> zzc;
    private SharedPreferences zze;
    private String zzh;
    private String zzi;
    private final Object zzb = new Object();
    private CopyOnWriteArraySet<zzaic> zzd = new CopyOnWriteArraySet<>();
    private boolean zzf = false;
    private boolean zzg = true;
    private boolean zzj = false;
    private String zzk = "";
    private long zzl = 0;
    private long zzm = 0;
    private long zzn = 0;
    private int zzo = -1;
    private int zzp = 0;
    private Set<String> zzq = Collections.emptySet();
    private JSONObject zzr = new JSONObject();
    private boolean zzs = true;
    private boolean zzt = true;

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Bundle bundle) {
        new zzaia(this, bundle).zzc();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzn() {
        return Build.VERSION.SDK_INT >= 23 && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    private final void zzo() {
        zzalt<?> zzaltVar = this.zzc;
        if (zzaltVar == null || zzaltVar.isDone()) {
            return;
        }
        try {
            this.zzc.get(1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            zzahw.zzc("Interrupted while waiting for preferences loaded.", e);
        } catch (CancellationException e2) {
            e = e2;
            zzahw.zzb("Fail to initialize AdSharedPreferenceManager.", e);
        } catch (ExecutionException e3) {
            e = e3;
            zzahw.zzb("Fail to initialize AdSharedPreferenceManager.", e);
        } catch (TimeoutException e4) {
            e = e4;
            zzahw.zzb("Fail to initialize AdSharedPreferenceManager.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bundle zzp() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.zzb) {
            bundle.putBoolean("use_https", this.zzg);
            bundle.putBoolean("content_url_opted_out", this.zzs);
            bundle.putBoolean("content_vertical_opted_out", this.zzt);
            bundle.putBoolean("auto_collect_location", this.zzj);
            bundle.putInt("version_code", this.zzp);
            bundle.putStringArray("never_pool_slots", (String[]) this.zzq.toArray(new String[this.zzq.size()]));
            bundle.putString("app_settings_json", this.zzk);
            bundle.putLong("app_settings_last_update_ms", this.zzl);
            bundle.putLong("app_last_background_time_ms", this.zzm);
            bundle.putInt("request_in_session_count", this.zzo);
            bundle.putLong("first_ad_req_time_ms", this.zzn);
            bundle.putString("native_advanced_settings", this.zzr.toString());
            if (this.zzh != null) {
                bundle.putString("content_url_hashes", this.zzh);
            }
            if (this.zzi != null) {
                bundle.putString("content_vertical_hashes", this.zzi);
            }
        }
        return bundle;
    }

    public final void zza(int i) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzp == i) {
                return;
            }
            this.zzp = i;
            if (this.zza != null) {
                this.zza.putInt("version_code", i);
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putInt("version_code", i);
            zza(bundle);
        }
    }

    public final void zza(long j) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzm == j) {
                return;
            }
            this.zzm = j;
            if (this.zza != null) {
                this.zza.putLong("app_last_background_time_ms", j);
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putLong("app_last_background_time_ms", j);
            zza(bundle);
        }
    }

    public final void zza(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzc = (zzalt) new zzahz(this, context).zzc();
    }

    public final void zza(zzaic zzaicVar) {
        synchronized (this.zzb) {
            if (this.zzc != null && this.zzc.isDone()) {
                zzaicVar.zza(zzp());
            }
            this.zzd.add(zzaicVar);
        }
    }

    public final void zza(String str) {
        zzo();
        synchronized (this.zzb) {
            if (str != null) {
                if (!str.equals(this.zzh)) {
                    this.zzh = str;
                    if (this.zza != null) {
                        this.zza.putString("content_url_hashes", str);
                        this.zza.apply();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("content_url_hashes", str);
                    zza(bundle);
                }
            }
        }
    }

    public final void zza(String str, String str2, boolean z) {
        zzo();
        synchronized (this.zzb) {
            JSONArray jSONArrayOptJSONArray = this.zzr.optJSONArray(str);
            if (jSONArrayOptJSONArray == null) {
                jSONArrayOptJSONArray = new JSONArray();
            }
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject == null) {
                    return;
                }
                if (str2.equals(jSONObjectOptJSONObject.optString("template_id"))) {
                    if (!z || jSONObjectOptJSONObject.optBoolean("uses_media_view", false) != z) {
                        length = i;
                        break;
                    }
                    return;
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("template_id", str2);
                jSONObject.put("uses_media_view", z);
                jSONObject.put("timestamp_ms", zzbt.zzl().zza());
                jSONArrayOptJSONArray.put(length, jSONObject);
                this.zzr.put(str, jSONArrayOptJSONArray);
            } catch (JSONException e) {
                zzahw.zzc("Could not update native advanced settings", e);
            }
            if (this.zza != null) {
                this.zza.putString("native_advanced_settings", this.zzr.toString());
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", this.zzr.toString());
            zza(bundle);
        }
    }

    public final void zza(boolean z) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzg == z) {
                return;
            }
            this.zzg = z;
            if (this.zza != null) {
                this.zza.putBoolean("use_https", z);
                this.zza.apply();
            }
            if (!this.zzf) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("use_https", z);
                zza(bundle);
            }
        }
    }

    public final boolean zza() {
        boolean z;
        zzo();
        synchronized (this.zzb) {
            z = this.zzg || this.zzf;
        }
        return z;
    }

    public final void zzb(int i) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzo == i) {
                return;
            }
            this.zzo = i;
            if (this.zza != null) {
                this.zza.putInt("request_in_session_count", i);
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putInt("request_in_session_count", i);
            zza(bundle);
        }
    }

    public final void zzb(long j) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzn == j) {
                return;
            }
            this.zzn = j;
            if (this.zza != null) {
                this.zza.putLong("first_ad_req_time_ms", j);
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putLong("first_ad_req_time_ms", j);
            zza(bundle);
        }
    }

    public final void zzb(String str) {
        zzo();
        synchronized (this.zzb) {
            if (str != null) {
                if (!str.equals(this.zzi)) {
                    this.zzi = str;
                    if (this.zza != null) {
                        this.zza.putString("content_vertical_hashes", str);
                        this.zza.apply();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("content_vertical_hashes", str);
                    zza(bundle);
                }
            }
        }
    }

    public final void zzb(boolean z) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzs == z) {
                return;
            }
            this.zzs = z;
            if (this.zza != null) {
                this.zza.putBoolean("content_url_opted_out", z);
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", this.zzs);
            bundle.putBoolean("content_vertical_opted_out", this.zzt);
            zza(bundle);
        }
    }

    public final boolean zzb() {
        boolean z;
        zzo();
        synchronized (this.zzb) {
            z = this.zzs;
        }
        return z;
    }

    public final String zzc() {
        String str;
        zzo();
        synchronized (this.zzb) {
            str = this.zzh;
        }
        return str;
    }

    public final void zzc(String str) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzq.contains(str)) {
                return;
            }
            this.zzq.add(str);
            if (this.zza != null) {
                this.zza.putStringSet("never_pool_slots", this.zzq);
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putStringArray("never_pool_slots", (String[]) this.zzq.toArray(new String[this.zzq.size()]));
            zza(bundle);
        }
    }

    public final void zzc(boolean z) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzt == z) {
                return;
            }
            this.zzt = z;
            if (this.zza != null) {
                this.zza.putBoolean("content_vertical_opted_out", z);
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", this.zzs);
            bundle.putBoolean("content_vertical_opted_out", this.zzt);
            zza(bundle);
        }
    }

    public final void zzd(String str) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzq.contains(str)) {
                this.zzq.remove(str);
                if (this.zza != null) {
                    this.zza.putStringSet("never_pool_slots", this.zzq);
                    this.zza.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("never_pool_slots", (String[]) this.zzq.toArray(new String[this.zzq.size()]));
                zza(bundle);
            }
        }
    }

    public final void zzd(boolean z) {
        zzo();
        synchronized (this.zzb) {
            if (this.zzj == z) {
                return;
            }
            this.zzj = z;
            if (this.zza != null) {
                this.zza.putBoolean("auto_collect_location", z);
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("auto_collect_location", z);
            zza(bundle);
        }
    }

    public final boolean zzd() {
        boolean z;
        zzo();
        synchronized (this.zzb) {
            z = this.zzt;
        }
        return z;
    }

    public final String zze() {
        String str;
        zzo();
        synchronized (this.zzb) {
            str = this.zzi;
        }
        return str;
    }

    public final boolean zze(String str) {
        boolean zContains;
        zzo();
        synchronized (this.zzb) {
            zContains = this.zzq.contains(str);
        }
        return zContains;
    }

    public final void zzf(String str) {
        zzo();
        synchronized (this.zzb) {
            long jZza = zzbt.zzl().zza();
            this.zzl = jZza;
            if (str != null && !str.equals(this.zzk)) {
                this.zzk = str;
                if (this.zza != null) {
                    this.zza.putString("app_settings_json", str);
                    this.zza.putLong("app_settings_last_update_ms", jZza);
                    this.zza.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putString("app_settings_json", str);
                bundle.putLong("app_settings_last_update_ms", jZza);
                zza(bundle);
            }
        }
    }

    public final boolean zzf() {
        boolean z;
        zzo();
        synchronized (this.zzb) {
            z = this.zzj;
        }
        return z;
    }

    public final int zzg() {
        int i;
        zzo();
        synchronized (this.zzb) {
            i = this.zzp;
        }
        return i;
    }

    public final zzahh zzh() {
        zzahh zzahhVar;
        zzo();
        synchronized (this.zzb) {
            zzahhVar = new zzahh(this.zzk, this.zzl);
        }
        return zzahhVar;
    }

    public final long zzi() {
        long j;
        zzo();
        synchronized (this.zzb) {
            j = this.zzm;
        }
        return j;
    }

    public final int zzj() {
        int i;
        zzo();
        synchronized (this.zzb) {
            i = this.zzo;
        }
        return i;
    }

    public final long zzk() {
        long j;
        zzo();
        synchronized (this.zzb) {
            j = this.zzn;
        }
        return j;
    }

    public final JSONObject zzl() {
        JSONObject jSONObject;
        zzo();
        synchronized (this.zzb) {
            jSONObject = this.zzr;
        }
        return jSONObject;
    }

    public final void zzm() {
        zzo();
        synchronized (this.zzb) {
            this.zzr = new JSONObject();
            if (this.zza != null) {
                this.zza.remove("native_advanced_settings");
                this.zza.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            zza(bundle);
        }
    }
}
