package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzp;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
final class zzacw implements Runnable {
    private /* synthetic */ OutputStream zza;
    private /* synthetic */ byte[] zzb;

    zzacw(zzacv zzacvVar, OutputStream outputStream, byte[] bArr) {
        this.zza = outputStream;
        this.zzb = bArr;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x003f  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.Closeable, java.io.DataOutputStream] */
    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        ?? dataOutputStream;
        Throwable th;
        IOException e;
        try {
            dataOutputStream = new DataOutputStream(this.zza);
            try {
                try {
                    dataOutputStream.writeInt(this.zzb.length);
                    dataOutputStream.write(this.zzb);
                    zzp.zza((Closeable) dataOutputStream);
                } catch (IOException e2) {
                    e = e2;
                    zzahw.zzb("Error transporting the ad response", e);
                    zzbt.zzi().zza(e, "LargeParcelTeleporter.pipeData.1");
                    if (dataOutputStream == 0) {
                        zzp.zza(this.zza);
                    } else {
                        zzp.zza((Closeable) dataOutputStream);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (dataOutputStream == 0) {
                    dataOutputStream = this.zza;
                }
                zzp.zza((Closeable) dataOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            dataOutputStream = 0;
            e = e3;
        } catch (Throwable th3) {
            dataOutputStream = 0;
            th = th3;
            if (dataOutputStream == 0) {
                dataOutputStream = this.zza;
            }
            zzp.zza((Closeable) dataOutputStream);
            throw th;
        }
    }
}
