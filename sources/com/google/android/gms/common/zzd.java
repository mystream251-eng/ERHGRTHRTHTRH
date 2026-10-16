package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzd extends com.google.android.gms.common.internal.zzn {
    private int zza;

    protected zzd(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 25);
        this.zza = Arrays.hashCode(bArr);
    }

    abstract byte[] zza();

    public int hashCode() {
        return this.zza;
    }

    public boolean equals(Object obj) {
        IObjectWrapper iObjectWrapperZzb;
        if (obj != null && (obj instanceof com.google.android.gms.common.internal.zzl)) {
            try {
                com.google.android.gms.common.internal.zzl zzlVar = (com.google.android.gms.common.internal.zzl) obj;
                if (zzlVar.zzc() == hashCode() && (iObjectWrapperZzb = zzlVar.zzb()) != null) {
                    return Arrays.equals(zza(), (byte[]) ObjectWrapper.unwrap(iObjectWrapperZzb));
                }
                return false;
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            }
        }
        return false;
    }

    @Override // com.google.android.gms.common.internal.zzl
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(zza());
    }

    @Override // com.google.android.gms.common.internal.zzl
    public final int zzc() {
        return hashCode();
    }

    protected static byte[] zza(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
