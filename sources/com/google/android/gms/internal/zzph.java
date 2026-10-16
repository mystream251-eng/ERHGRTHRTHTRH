package com.google.android.gms.internal;

import android.graphics.Color;
import com.viaccessorca.vodownloader.VODownloader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzph extends zzqp {
    private static final int zza = Color.rgb(12, 174, VODownloader.DOWNLOADER_ERROR_CACHEALLOWFLAG_NO);
    private static final int zzb;
    private static int zzc;
    private static int zzd;
    private final String zze;
    private final List<zzpj> zzf = new ArrayList();
    private final List<zzqs> zzg = new ArrayList();
    private final int zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final boolean zzm;

    static {
        int iRgb = Color.rgb(VODownloader.DOWNLOADER_ERROR_LIVE_UNSUPPORTED, VODownloader.DOWNLOADER_ERROR_LIVE_UNSUPPORTED, VODownloader.DOWNLOADER_ERROR_LIVE_UNSUPPORTED);
        zzb = iRgb;
        zzc = iRgb;
        zzd = zza;
    }

    public zzph(String str, List<zzpj> list, Integer num, Integer num2, Integer num3, int i, int i2, boolean z) {
        this.zze = str;
        if (list != null) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                zzpj zzpjVar = list.get(i3);
                this.zzf.add(zzpjVar);
                this.zzg.add(zzpjVar);
            }
        }
        this.zzh = num != null ? num.intValue() : zzc;
        this.zzi = num2 != null ? num2.intValue() : zzd;
        this.zzj = num3 != null ? num3.intValue() : 12;
        this.zzk = i;
        this.zzl = i2;
        this.zzm = z;
    }

    @Override // com.google.android.gms.internal.zzqo
    public final String zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzqo
    public final List<zzqs> zzb() {
        return this.zzg;
    }

    public final List<zzpj> zzc() {
        return this.zzf;
    }

    public final int zzd() {
        return this.zzh;
    }

    public final int zze() {
        return this.zzi;
    }

    public final int zzf() {
        return this.zzj;
    }

    public final int zzg() {
        return this.zzk;
    }

    public final int zzh() {
        return this.zzl;
    }

    public final boolean zzi() {
        return this.zzm;
    }
}
