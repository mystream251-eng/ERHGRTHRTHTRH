package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class zzhv {
    private ByteArrayOutputStream zza = new ByteArrayOutputStream(4096);
    private Base64OutputStream zzb = new Base64OutputStream(this.zza, 10);

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String string;
        try {
            this.zzb.close();
        } catch (IOException e) {
            zzahw.zzb("HashManager: Unable to convert to Base64.", e);
        }
        try {
            try {
                this.zza.close();
                string = this.zza.toString();
            } catch (IOException e2) {
                zzahw.zzb("HashManager: Unable to convert to Base64.", e2);
                string = "";
            }
            return string;
        } finally {
            this.zza = null;
            this.zzb = null;
        }
    }

    public final void zza(byte[] bArr) throws IOException {
        this.zzb.write(bArr);
    }
}
