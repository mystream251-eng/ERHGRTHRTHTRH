package com.google.android.gms.internal;

import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
public final class zzid implements Comparator<zzhr> {
    public zzid(zzic zzicVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzhr zzhrVar, zzhr zzhrVar2) {
        zzhr zzhrVar3 = zzhrVar;
        zzhr zzhrVar4 = zzhrVar2;
        if (zzhrVar3.zzb() < zzhrVar4.zzb()) {
            return -1;
        }
        if (zzhrVar3.zzb() > zzhrVar4.zzb()) {
            return 1;
        }
        if (zzhrVar3.zza() < zzhrVar4.zza()) {
            return -1;
        }
        if (zzhrVar3.zza() > zzhrVar4.zza()) {
            return 1;
        }
        float fZzd = (zzhrVar3.zzd() - zzhrVar3.zzb()) * (zzhrVar3.zzc() - zzhrVar3.zza());
        float fZzd2 = (zzhrVar4.zzd() - zzhrVar4.zzb()) * (zzhrVar4.zzc() - zzhrVar4.zza());
        if (fZzd > fZzd2) {
            return -1;
        }
        return fZzd < fZzd2 ? 1 : 0;
    }
}
