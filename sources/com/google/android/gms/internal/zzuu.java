package com.google.android.gms.internal;

import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbt;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzuu {
    final zzkk zza;
    final String zzb;
    final int zzc;

    private zzuu(zzkk zzkkVar, String str, int i) {
        this.zza = zzkkVar;
        this.zzb = str;
        this.zzc = i;
    }

    zzuu(zzuq zzuqVar) {
        this(zzuqVar.zza(), zzuqVar.zzc(), zzuqVar.zzb());
    }

    static zzuu zza(String str) throws IOException {
        String[] strArrSplit = str.split("\u0000");
        if (strArrSplit.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                String str2 = new String(Base64.decode(strArrSplit[0], 0), "UTF-8");
                int i = Integer.parseInt(strArrSplit[1]);
                byte[] bArrDecode = Base64.decode(strArrSplit[2], 0);
                parcelObtain.unmarshall(bArrDecode, 0, bArrDecode.length);
                parcelObtain.setDataPosition(0);
                zzuu zzuuVar = new zzuu(zzkk.CREATOR.createFromParcel(parcelObtain), str2, i);
                parcelObtain.recycle();
                return zzuuVar;
            } catch (Throwable th) {
                parcelObtain.recycle();
                throw th;
            }
        } catch (IllegalArgumentException | zzbgn | IllegalStateException e) {
            zzbt.zzi().zza(e, "QueueSeed.decode");
            throw new IOException("Malformed QueueSeed encoding.", e);
        }
    }

    final String zza() {
        Parcel parcelObtain = Parcel.obtain();
        try {
            String strEncodeToString = Base64.encodeToString(this.zzb.getBytes("UTF-8"), 0);
            String string = Integer.toString(this.zzc);
            this.zza.writeToParcel(parcelObtain, 0);
            String strEncodeToString2 = Base64.encodeToString(parcelObtain.marshall(), 0);
            StringBuilder sb = new StringBuilder(String.valueOf(strEncodeToString).length() + 2 + String.valueOf(string).length() + String.valueOf(strEncodeToString2).length());
            sb.append(strEncodeToString);
            sb.append("\u0000");
            sb.append(string);
            sb.append("\u0000");
            sb.append(strEncodeToString2);
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            zzahw.zzc("QueueSeed encode failed because UTF-8 is not available.");
            return "";
        } finally {
            parcelObtain.recycle();
        }
    }
}
