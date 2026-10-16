package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzaly;
import com.google.android.gms.internal.zzami;

/* JADX INFO: loaded from: classes2.dex */
final class zzah implements zzami<zzc> {
    final /* synthetic */ zzae zza;

    zzah(zzae zzaeVar) {
        this.zza = zzaeVar;
    }

    @Override // com.google.android.gms.internal.zzami
    public final /* synthetic */ void zza(zzc zzcVar) {
        final zzc zzcVar2 = zzcVar;
        zzaly.zza.execute(new Runnable(this, zzcVar2) { // from class: com.google.android.gms.ads.internal.js.zzai
            private final zzah zza;
            private final zzc zzb;

            {
                this.zza = this;
                this.zzb = zzcVar2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzah zzahVar = this.zza;
                zzc zzcVar3 = this.zzb;
                zzahVar.zza.zzb.zza(zzcVar3);
                zzcVar3.zza();
            }
        });
    }
}
