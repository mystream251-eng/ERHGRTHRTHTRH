package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
@CheckReturnValue
public class GoogleSignatureVerifier {

    @Nullable
    private static GoogleSignatureVerifier zza;
    private final Context zzb;
    private volatile String zzc;

    private GoogleSignatureVerifier(Context context) {
        this.zzb = context.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zza == null) {
                zzc.zza(context);
                zza = new GoogleSignatureVerifier(context);
            }
        }
        return zza;
    }

    public boolean isUidGoogleSigned(int i) {
        zzl zzlVarZza;
        String[] strArrZza = Wrappers.packageManager(this.zzb).zza(i);
        if (strArrZza == null || strArrZza.length == 0) {
            zzlVarZza = zzl.zza("no pkgs");
        } else {
            zzlVarZza = null;
            for (String str : strArrZza) {
                zzlVarZza = zza(str, i);
                if (!zzlVarZza.zza) {
                }
            }
            zzlVarZza = (zzl) Preconditions.checkNotNull(zzlVarZza);
        }
        zzlVarZza.zzc();
        return zzlVarZza.zza;
    }

    public boolean isPackageGoogleSigned(String str) {
        zzl zzlVarZza = zza(str);
        zzlVarZza.zzc();
        return zzlVarZza.zza;
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z ? zza(packageInfo, zzi.zza) : zza(packageInfo, zzi.zza[0])) != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (zza(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    private final zzl zza(String str, int i) {
        try {
            PackageInfo packageInfoZza = Wrappers.packageManager(this.zzb).zza(str, 64, i);
            boolean zHonorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb);
            if (packageInfoZza == null) {
                return zzl.zza("null pkg");
            }
            if (packageInfoZza.signatures != null && packageInfoZza.signatures.length == 1) {
                zzg zzgVar = new zzg(packageInfoZza.signatures[0].toByteArray());
                String str2 = packageInfoZza.packageName;
                zzl zzlVarZza = zzc.zza(str2, zzgVar, zHonorsDebugCertificates, false);
                return (!zzlVarZza.zza || packageInfoZza.applicationInfo == null || (packageInfoZza.applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzgVar, false, true).zza) ? zzlVarZza : zzl.zza("debuggable release cert app rejected");
            }
            return zzl.zza("single cert required");
        } catch (PackageManager.NameNotFoundException unused) {
            String strValueOf = String.valueOf(str);
            return zzl.zza(strValueOf.length() != 0 ? "no pkg ".concat(strValueOf) : new String("no pkg "));
        }
    }

    private final zzl zza(String str) {
        zzl zzlVarZza;
        if (str == null) {
            return zzl.zza("null pkg");
        }
        if (str.equals(this.zzc)) {
            return zzl.zza();
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(this.zzb).getPackageInfo(str, 64);
            boolean zHonorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb);
            if (packageInfo == null) {
                zzlVarZza = zzl.zza("null pkg");
            } else if (packageInfo.signatures == null || packageInfo.signatures.length != 1) {
                zzlVarZza = zzl.zza("single cert required");
            } else {
                zzg zzgVar = new zzg(packageInfo.signatures[0].toByteArray());
                String str2 = packageInfo.packageName;
                zzl zzlVarZza2 = zzc.zza(str2, zzgVar, zHonorsDebugCertificates, false);
                zzlVarZza = (!zzlVarZza2.zza || packageInfo.applicationInfo == null || (packageInfo.applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzgVar, false, true).zza) ? zzlVarZza2 : zzl.zza("debuggable release cert app rejected");
            }
            if (zzlVarZza.zza) {
                this.zzc = str;
            }
            return zzlVarZza;
        } catch (PackageManager.NameNotFoundException unused) {
            String strValueOf = String.valueOf(str);
            return zzl.zza(strValueOf.length() != 0 ? "no pkg ".concat(strValueOf) : new String("no pkg "));
        }
    }

    @Nullable
    private static zzd zza(PackageInfo packageInfo, zzd... zzdVarArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzg zzgVar = new zzg(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzdVarArr.length; i++) {
            if (zzdVarArr[i].equals(zzgVar)) {
                return zzdVarArr[i];
            }
        }
        return null;
    }
}
