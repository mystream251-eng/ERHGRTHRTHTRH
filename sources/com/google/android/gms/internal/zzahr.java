package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.ads.internal.zzbt;
import java.math.BigInteger;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahr {
    private static final Object zza = new Object();
    private static String zzb;

    public static String zza() {
        String str;
        synchronized (zza) {
            str = zzb;
        }
        return str;
    }

    public static String zza(Context context, String str, String str2) {
        String str3;
        String str4;
        synchronized (zza) {
            if (zzb == null && !TextUtils.isEmpty(str)) {
                try {
                    ClassLoader classLoader = context.createPackageContext(str2, 3).getClassLoader();
                    Class<?> cls = Class.forName("com.google.ads.mediation.MediationAdapter", false, classLoader);
                    BigInteger bigInteger = new BigInteger(new byte[1]);
                    String[] strArrSplit = str.split(",");
                    for (int i = 0; i < strArrSplit.length; i++) {
                        zzbt.zze();
                        if (zzaij.zza(classLoader, cls, strArrSplit[i])) {
                            bigInteger = bigInteger.setBit(i);
                        }
                    }
                    str4 = String.format(Locale.US, "%X", bigInteger);
                } catch (Throwable unused) {
                    str4 = NotificationCompat.CATEGORY_ERROR;
                }
                zzb = str4;
            }
            str3 = zzb;
        }
        return str3;
    }
}
