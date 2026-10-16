package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzg implements Handler.Callback {
    private final /* synthetic */ zzf zza;

    private zzg(zzf zzfVar) {
        this.zza = zzfVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            synchronized (this.zza.zza) {
                GmsClientSupervisor.zza zzaVar = (GmsClientSupervisor.zza) message.obj;
                zzh zzhVar = (zzh) this.zza.zza.get(zzaVar);
                if (zzhVar != null && zzhVar.zzc()) {
                    if (zzhVar.zza()) {
                        zzhVar.zzb("GmsClientSupervisor");
                    }
                    this.zza.zza.remove(zzaVar);
                }
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        synchronized (this.zza.zza) {
            GmsClientSupervisor.zza zzaVar2 = (GmsClientSupervisor.zza) message.obj;
            zzh zzhVar2 = (zzh) this.zza.zza.get(zzaVar2);
            if (zzhVar2 != null && zzhVar2.zzb() == 3) {
                String strValueOf = String.valueOf(zzaVar2);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 47);
                sb.append("Timeout waiting for ServiceConnection callback ");
                sb.append(strValueOf);
                Log.e("GmsClientSupervisor", sb.toString(), new Exception());
                ComponentName componentNameZze = zzhVar2.zze();
                if (componentNameZze == null) {
                    componentNameZze = zzaVar2.zzb();
                }
                if (componentNameZze == null) {
                    componentNameZze = new ComponentName((String) Preconditions.checkNotNull(zzaVar2.zza()), EnvironmentCompat.MEDIA_UNKNOWN);
                }
                zzhVar2.onServiceDisconnected(componentNameZze);
            }
        }
        return true;
    }
}
