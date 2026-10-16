package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaff implements RewardItem {
    private final zzaeu zza;

    public zzaff(zzaeu zzaeuVar) {
        this.zza = zzaeuVar;
    }

    @Override // com.google.android.gms.ads.reward.RewardItem
    public final int getAmount() {
        zzaeu zzaeuVar = this.zza;
        if (zzaeuVar == null) {
            return 0;
        }
        try {
            return zzaeuVar.zzb();
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward getAmount to RewardItem", e);
            return 0;
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardItem
    public final String getType() {
        zzaeu zzaeuVar = this.zza;
        if (zzaeuVar == null) {
            return null;
        }
        try {
            return zzaeuVar.zza();
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward getType to RewardItem", e);
            return null;
        }
    }
}
