package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.common.util.zzs;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaeb {
    private int zza;
    private String zzaa;
    private boolean zzab;
    private boolean zzb;
    private boolean zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private String zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private boolean zzk;
    private int zzl;
    private double zzm;
    private boolean zzn;
    private String zzo;
    private String zzp;
    private boolean zzq;
    private boolean zzr;
    private String zzs;
    private boolean zzt;
    private boolean zzu;
    private String zzv;
    private String zzw;
    private float zzx;
    private int zzy;
    private int zzz;

    public zzaeb(Context context) {
        DisplayMetrics displayMetrics;
        PackageManager packageManager = context.getPackageManager();
        zza(context);
        zzb(context);
        zzc(context);
        Locale locale = Locale.getDefault();
        this.zzq = zza(packageManager, "geo:0,0?q=donuts") != null;
        this.zzr = zza(packageManager, "http://www.google.com") != null;
        this.zzs = locale.getCountry();
        zzlc.zza();
        this.zzt = zzako.zza();
        this.zzu = zzj.zzc(context);
        this.zzv = locale.getLanguage();
        this.zzw = zzb(context, packageManager);
        this.zzaa = zza(context, packageManager);
        Resources resources = context.getResources();
        if (resources == null || (displayMetrics = resources.getDisplayMetrics()) == null) {
            return;
        }
        this.zzx = displayMetrics.density;
        this.zzy = displayMetrics.widthPixels;
        this.zzz = displayMetrics.heightPixels;
    }

    public zzaeb(Context context, zzaea zzaeaVar) {
        context.getPackageManager();
        zza(context);
        zzb(context);
        zzc(context);
        this.zzo = Build.FINGERPRINT;
        this.zzp = Build.DEVICE;
        this.zzab = zzs.zza() && zzpf.zza(context);
        this.zzq = zzaeaVar.zzb;
        this.zzr = zzaeaVar.zzc;
        this.zzs = zzaeaVar.zze;
        this.zzt = zzaeaVar.zzf;
        this.zzu = zzaeaVar.zzg;
        this.zzv = zzaeaVar.zzj;
        this.zzw = zzaeaVar.zzk;
        this.zzaa = zzaeaVar.zzl;
        this.zzx = zzaeaVar.zzs;
        this.zzy = zzaeaVar.zzt;
        this.zzz = zzaeaVar.zzu;
    }

    private static ResolveInfo zza(PackageManager packageManager, String str) {
        try {
            return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
        } catch (Throwable th) {
            zzbt.zzi().zza(th, "DeviceInfo.getResolveInfo");
            return null;
        }
    }

    private static String zza(Context context, PackageManager packageManager) {
        try {
            PackageInfo packageInfoZzb = zzbih.zza(context).zzb("com.android.vending", 128);
            if (packageInfoZzb != null) {
                int i = packageInfoZzb.versionCode;
                String str = packageInfoZzb.packageName;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
                sb.append(i);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private final void zza(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager != null) {
            try {
                this.zza = audioManager.getMode();
                this.zzb = audioManager.isMusicActive();
                this.zzc = audioManager.isSpeakerphoneOn();
                this.zzd = audioManager.getStreamVolume(3);
                this.zze = audioManager.getRingerMode();
                this.zzf = audioManager.getStreamVolume(2);
                return;
            } catch (Throwable th) {
                zzbt.zzi().zza(th, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.zza = -2;
        this.zzb = false;
        this.zzc = false;
        this.zzd = 0;
        this.zze = 0;
        this.zzf = 0;
    }

    private static String zzb(Context context, PackageManager packageManager) {
        ActivityInfo activityInfo;
        ResolveInfo resolveInfoZza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (resolveInfoZza == null || (activityInfo = resolveInfoZza.activityInfo) == null) {
            return null;
        }
        try {
            PackageInfo packageInfoZzb = zzbih.zza(context).zzb(activityInfo.packageName, 0);
            if (packageInfoZzb != null) {
                int i = packageInfoZzb.versionCode;
                String str = activityInfo.packageName;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
                sb.append(i);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    private final void zzb(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.zzg = telephonyManager.getNetworkOperator();
        this.zzi = telephonyManager.getNetworkType();
        this.zzj = telephonyManager.getPhoneType();
        this.zzh = -2;
        this.zzk = false;
        this.zzl = -1;
        zzbt.zze();
        if (zzaij.zza(context, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.zzh = activeNetworkInfo.getType();
                this.zzl = activeNetworkInfo.getDetailedState().ordinal();
            } else {
                this.zzh = -1;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                this.zzk = connectivityManager.isActiveNetworkMetered();
            }
        }
    }

    private final void zzc(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            this.zzm = -1.0d;
            this.zzn = false;
        } else {
            int intExtra = intentRegisterReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
            this.zzm = intentRegisterReceiver.getIntExtra("level", -1) / intentRegisterReceiver.getIntExtra("scale", -1);
            this.zzn = intExtra == 2 || intExtra == 5;
        }
    }

    public final zzaea zza() {
        return new zzaea(this.zza, this.zzq, this.zzr, this.zzg, this.zzs, this.zzt, this.zzu, this.zzb, this.zzc, this.zzv, this.zzw, this.zzaa, this.zzd, this.zzh, this.zzi, this.zzj, this.zze, this.zzf, this.zzx, this.zzy, this.zzz, this.zzm, this.zzn, this.zzk, this.zzl, this.zzo, this.zzab, this.zzp);
    }
}
