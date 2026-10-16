package com.google.android.gms.internal;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class zzaab implements zzale<List<zzpj>, zzph> {
    private /* synthetic */ String zza;
    private /* synthetic */ Integer zzb;
    private /* synthetic */ Integer zzc;
    private /* synthetic */ int zzd;
    private /* synthetic */ int zze;
    private /* synthetic */ int zzf;
    private /* synthetic */ int zzg;
    private /* synthetic */ boolean zzh;

    zzaab(zzzy zzzyVar, String str, Integer num, Integer num2, int i, int i2, int i3, int i4, boolean z) {
        this.zza = str;
        this.zzb = num;
        this.zzc = num2;
        this.zzd = i;
        this.zze = i2;
        this.zzf = i3;
        this.zzg = i4;
        this.zzh = z;
    }

    @Override // com.google.android.gms.internal.zzale
    public final /* synthetic */ zzph zza(List<zzpj> list) {
        List<zzpj> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        String str = this.zza;
        Integer num = this.zzb;
        Integer num2 = this.zzc;
        int i = this.zzd;
        return new zzph(str, list2, num, num2, i > 0 ? Integer.valueOf(i) : null, this.zze + this.zzf, this.zzg, this.zzh);
    }
}
