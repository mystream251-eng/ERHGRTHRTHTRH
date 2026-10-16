package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzqr extends NativeAd.AdChoicesInfo {
    private final zzqo zza;
    private final List<NativeAd.Image> zzb = new ArrayList();
    private String zzc;

    public zzqr(zzqo zzqoVar) {
        zzqs zzquVar;
        IBinder iBinder;
        this.zza = zzqoVar;
        try {
            this.zzc = zzqoVar.zza();
        } catch (RemoteException e) {
            zzaky.zzb("Error while obtaining attribution text.", e);
            this.zzc = "";
        }
        try {
            for (zzqs zzqsVar : zzqoVar.zzb()) {
                if (!(zzqsVar instanceof IBinder) || (iBinder = (IBinder) zzqsVar) == null) {
                    zzquVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    zzquVar = iInterfaceQueryLocalInterface instanceof zzqs ? (zzqs) iInterfaceQueryLocalInterface : new zzqu(iBinder);
                }
                if (zzquVar != null) {
                    this.zzb.add(new zzqv(zzquVar));
                }
            }
        } catch (RemoteException e2) {
            zzaky.zzb("Error while obtaining image.", e2);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo
    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo
    public final CharSequence getText() {
        return this.zzc;
    }
}
