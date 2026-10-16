package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzkn {
    public static final zzkn zza = new zzkn();

    protected zzkn() {
    }

    public static zzkk zza(Context context, zzmu zzmuVar) {
        String strZza;
        Date dateZza = zzmuVar.zza();
        long time = dateZza != null ? dateZza.getTime() : -1L;
        String strZzb = zzmuVar.zzb();
        int iZzc = zzmuVar.zzc();
        Set<String> setZzd = zzmuVar.zzd();
        List listUnmodifiableList = !setZzd.isEmpty() ? Collections.unmodifiableList(new ArrayList(setZzd)) : null;
        boolean zZza = zzmuVar.zza(context);
        int iZzl = zzmuVar.zzl();
        Location locationZze = zzmuVar.zze();
        Bundle bundleZzb = zzmuVar.zzb(AdMobAdapter.class);
        boolean zZzf = zzmuVar.zzf();
        String strZzg = zzmuVar.zzg();
        SearchAdRequest searchAdRequestZzi = zzmuVar.zzi();
        zzno zznoVar = searchAdRequestZzi != null ? new zzno(searchAdRequestZzi) : null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzlc.zza();
            strZza = zzako.zza(Thread.currentThread().getStackTrace(), packageName);
        } else {
            strZza = null;
        }
        return new zzkk(7, time, bundleZzb, iZzc, listUnmodifiableList, zZza, iZzl, zZzf, strZzg, zznoVar, locationZze, strZzb, zzmuVar.zzk(), zzmuVar.zzm(), Collections.unmodifiableList(new ArrayList(zzmuVar.zzn())), zzmuVar.zzh(), strZza, zzmuVar.zzo());
    }
}
