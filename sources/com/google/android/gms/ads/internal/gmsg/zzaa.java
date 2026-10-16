package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzya;
import com.google.android.gms.internal.zzyd;
import com.google.android.gms.internal.zzyf;
import com.google.android.gms.internal.zzyg;
import com.google.android.gms.internal.zzyo;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaa implements zzt<zzaof> {
    private static Map<String, Integer> zzd = com.google.android.gms.common.util.zzf.zza(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
    private final com.google.android.gms.ads.internal.zzw zza;
    private final zzyd zzb;
    private final zzyo zzc;

    public zzaa(com.google.android.gms.ads.internal.zzw zzwVar, zzyd zzydVar, zzyo zzyoVar) {
        this.zza = zzwVar;
        this.zzb = zzydVar;
        this.zzc = zzyoVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        com.google.android.gms.ads.internal.zzw zzwVar;
        zzaof zzaofVar2 = zzaofVar;
        int iIntValue = zzd.get((String) map.get("a")).intValue();
        if (iIntValue != 5 && iIntValue != 7 && (zzwVar = this.zza) != null && !zzwVar.zzb()) {
            this.zza.zza(null);
            return;
        }
        if (iIntValue == 1) {
            this.zzb.zza((Map<String, String>) map);
            return;
        }
        if (iIntValue == 3) {
            new zzyg(zzaofVar2, map).zza();
            return;
        }
        if (iIntValue == 4) {
            new zzya(zzaofVar2, map).zza();
            return;
        }
        if (iIntValue == 5) {
            new zzyf(zzaofVar2, map).zza();
            return;
        }
        if (iIntValue == 6) {
            this.zzb.zza(true);
        } else if (iIntValue != 7) {
            zzahw.zzd("Unknown MRAID command called.");
        } else if (((Boolean) zzlc.zzf().zza(zzoi.zzai)).booleanValue()) {
            this.zzc.zzat();
        }
    }
}
