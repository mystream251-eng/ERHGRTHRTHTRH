package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzaw implements com.google.android.gms.ads.internal.gmsg.zzt<zzaof> {
    private /* synthetic */ zzwr zza;
    private /* synthetic */ zzab zzb;
    private /* synthetic */ zzwu zzc;

    zzaw(zzwr zzwrVar, zzab zzabVar, zzwu zzwuVar) {
        this.zza = zzwrVar;
        this.zzb = zzabVar;
        this.zzc = zzwuVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzaof zzaofVar2 = zzaofVar;
        View viewZzm = zzaofVar2.zzm();
        if (viewZzm != null) {
            try {
                if (this.zza != null) {
                    if (this.zza.zzk()) {
                        zzar.zzb(zzaofVar2);
                        return;
                    } else {
                        this.zza.zza(com.google.android.gms.dynamic.zzn.zza(viewZzm));
                        this.zzb.zza.onAdClicked();
                        return;
                    }
                }
                if (this.zzc != null) {
                    if (this.zzc.zzi()) {
                        zzar.zzb(zzaofVar2);
                    } else {
                        this.zzc.zza(com.google.android.gms.dynamic.zzn.zza(viewZzm));
                        this.zzb.zza.onAdClicked();
                    }
                }
            } catch (RemoteException e) {
                zzahw.zzc("Unable to call handleClick on mapper", e);
            }
        }
    }
}
