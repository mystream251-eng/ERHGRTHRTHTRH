package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzf extends zzd {
    private static final WeakReference<byte[]> zzb = new WeakReference<>(null);
    private WeakReference<byte[]> zza;

    zzf(byte[] bArr) {
        super(bArr);
        this.zza = zzb;
    }

    protected abstract byte[] zzd();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.common.zzd
    public final byte[] zza() {
        byte[] bArrZzd;
        synchronized (this) {
            bArrZzd = this.zza.get();
            if (bArrZzd == null) {
                bArrZzd = zzd();
                this.zza = new WeakReference<>(bArrZzd);
            }
        }
        return bArrZzd;
    }
}
