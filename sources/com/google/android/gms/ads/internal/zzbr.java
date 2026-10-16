package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
final class zzbr extends AsyncTask<Void, Void, String> {
    private /* synthetic */ zzbn zza;

    private zzbr(zzbn zzbnVar) {
        this.zza = zzbnVar;
    }

    /* synthetic */ zzbr(zzbn zzbnVar, zzbo zzboVar) {
        this(zzbnVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final String doInBackground(Void... voidArr) {
        try {
            this.zza.zzh = (zzcv) this.zza.zzc.get(((Long) zzlc.zzf().zza(zzoi.zzfp)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e = e;
            zzahw.zzc("Failed to load ad data", e);
        } catch (ExecutionException e2) {
            e = e2;
            zzahw.zzc("Failed to load ad data", e);
        } catch (TimeoutException unused) {
            zzahw.zze("Timed out waiting for ad data");
        }
        return this.zza.zza();
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(String str) {
        String str2 = str;
        if (this.zza.zzf == null || str2 == null) {
            return;
        }
        this.zza.zzf.loadUrl(str2);
    }
}
