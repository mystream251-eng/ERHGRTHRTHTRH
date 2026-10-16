package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zzf;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
public class zzdm {
    private static final String zzb = zzdm.class.getSimpleName();
    protected Context zza;
    private ExecutorService zzc;
    private DexClassLoader zzd;
    private zzcx zze;
    private byte[] zzf;
    private boolean zzj;
    private zzcp zzm;
    private Map<Pair<String, String>, zzes> zzp;
    private volatile AdvertisingIdClient zzg = null;
    private volatile boolean zzh = false;
    private Future zzi = null;
    private volatile zzba zzk = null;
    private Future zzl = null;
    private boolean zzn = false;
    private boolean zzo = false;
    private boolean zzq = false;
    private boolean zzr = true;
    private boolean zzs = false;

    final class zza extends BroadcastReceiver {
        private zza() {
        }

        /* synthetic */ zza(zzdm zzdmVar, zzdn zzdnVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                zzdm.this.zzr = true;
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                zzdm.this.zzr = false;
            }
        }
    }

    private zzdm(Context context) {
        Context applicationContext = context.getApplicationContext();
        boolean z = applicationContext != null;
        this.zzj = z;
        this.zza = z ? applicationContext : context;
        this.zzp = new HashMap();
    }

    public static zzdm zza(Context context, String str, String str2, boolean z) throws Throwable {
        zzdm zzdmVar = new zzdm(context);
        try {
            zzdmVar.zzc = Executors.newCachedThreadPool();
            zzdmVar.zzh = z;
            if (z) {
                zzdmVar.zzi = zzdmVar.zzc.submit(new zzdn(zzdmVar));
            }
            zzdmVar.zzc.execute(new zzdp(zzdmVar));
            try {
                zzf zzfVarZza = zzf.zza();
                zzdmVar.zzn = zzf.zzd(zzdmVar.zza) > 0;
                zzdmVar.zzo = zzfVarZza.isGooglePlayServicesAvailable(zzdmVar.zza) == 0;
            } catch (Throwable unused) {
            }
            zzdmVar.zza(0, true);
            if (zzds.zza() && ((Boolean) zzlc.zzf().zza(zzoi.zzef)).booleanValue()) {
                throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
            }
            zzdn zzdnVar = null;
            zzcx zzcxVar = new zzcx(null);
            zzdmVar.zze = zzcxVar;
            try {
                zzdmVar.zzf = zzcxVar.zza(str);
                try {
                    try {
                        File cacheDir = zzdmVar.zza.getCacheDir();
                        if (cacheDir == null && (cacheDir = zzdmVar.zza.getDir("dex", 0)) == null) {
                            throw new zzdj();
                        }
                        File file = new File(String.format("%s/%s.jar", cacheDir, "1510898742191"));
                        if (!file.exists()) {
                            byte[] bArrZza = zzdmVar.zze.zza(zzdmVar.zzf, str2);
                            file.createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            fileOutputStream.write(bArrZza, 0, bArrZza.length);
                            fileOutputStream.close();
                        }
                        zzdmVar.zzb(cacheDir, "1510898742191");
                        try {
                            zzdmVar.zzd = new DexClassLoader(file.getAbsolutePath(), cacheDir.getAbsolutePath(), null, zzdmVar.zza.getClassLoader());
                            zza(file);
                            zzdmVar.zza(cacheDir, "1510898742191");
                            zza(String.format("%s/%s.dex", cacheDir, "1510898742191"));
                            if (((Boolean) zzlc.zzf().zza(zzoi.zzdx)).booleanValue() && !zzdmVar.zzs) {
                                IntentFilter intentFilter = new IntentFilter();
                                intentFilter.addAction("android.intent.action.USER_PRESENT");
                                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                                zzdmVar.zza.registerReceiver(new zza(zzdmVar, zzdnVar), intentFilter);
                                zzdmVar.zzs = true;
                            }
                            zzdmVar.zzm = new zzcp(zzdmVar);
                            zzdmVar.zzq = true;
                        } catch (Throwable th) {
                            zza(file);
                            zzdmVar.zza(cacheDir, "1510898742191");
                            zza(String.format("%s/%s.dex", cacheDir, "1510898742191"));
                            throw th;
                        }
                    } catch (zzcy e) {
                        throw new zzdj(e);
                    } catch (FileNotFoundException e2) {
                        throw new zzdj(e2);
                    }
                } catch (IOException e3) {
                    throw new zzdj(e3);
                } catch (NullPointerException e4) {
                    throw new zzdj(e4);
                }
            } catch (zzcy e5) {
                throw new zzdj(e5);
            }
        } catch (zzdj unused2) {
        }
        return zzdmVar;
    }

    private static void zza(File file) {
        if (file.exists()) {
            file.delete();
        } else {
            Log.d(zzb, String.format("File %s not found. No need for deletion", file.getAbsolutePath()));
        }
    }

    private final void zza(File file, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        File file2 = new File(String.format("%s/%s.tmp", file, str));
        if (file2.exists()) {
            return;
        }
        File file3 = new File(String.format("%s/%s.dex", file, str));
        if (file3.exists()) {
            long length = file3.length();
            if (length <= 0) {
                return;
            }
            byte[] bArr = new byte[(int) length];
            FileInputStream fileInputStream = null;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file3);
                try {
                    try {
                        if (fileInputStream2.read(bArr) <= 0) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException unused) {
                            }
                            zza(file3);
                            return;
                        }
                        zzbe zzbeVar = new zzbe();
                        zzbeVar.zzd = Build.VERSION.SDK.getBytes();
                        zzbeVar.zzc = str.getBytes();
                        byte[] bytes = this.zze.zza(this.zzf, bArr).getBytes();
                        zzbeVar.zza = bytes;
                        zzbeVar.zzb = zzbx.zza(bytes);
                        file2.createNewFile();
                        fileOutputStream = new FileOutputStream(file2);
                        try {
                            byte[] bArrZza = zzfls.zza(zzbeVar);
                            fileOutputStream.write(bArrZza, 0, bArrZza.length);
                            fileOutputStream.close();
                            try {
                                fileInputStream2.close();
                            } catch (IOException unused2) {
                            }
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused3) {
                            }
                            zza(file3);
                            return;
                        } catch (zzcy | IOException | NoSuchAlgorithmException unused4) {
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException unused5) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused6) {
                                }
                            }
                            zza(file3);
                            return;
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                    }
                } catch (zzcy | IOException | NoSuchAlgorithmException unused7) {
                    fileOutputStream = null;
                }
                fileInputStream = fileInputStream2;
            } catch (zzcy | IOException | NoSuchAlgorithmException unused8) {
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused9) {
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused10) {
                }
            }
            zza(file3);
            throw th;
        }
    }

    private static void zza(String str) {
        zza(new File(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzb(int i, zzba zzbaVar) {
        if (i >= 4) {
            return false;
        }
        if (zzbaVar == null) {
            return true;
        }
        if (((Boolean) zzlc.zzf().zza(zzoi.zzei)).booleanValue() && (zzbaVar.zzn == null || zzbaVar.zzn.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
            return true;
        }
        if (((Boolean) zzlc.zzf().zza(zzoi.zzej)).booleanValue()) {
            return zzbaVar.zzaw == null || zzbaVar.zzaw.zza == null || zzbaVar.zzaw.zza.longValue() == -2;
        }
        return false;
    }

    private final boolean zzb(File file, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        File file2 = new File(String.format("%s/%s.tmp", file, str));
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(String.format("%s/%s.dex", file, str));
        if (file3.exists()) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            long length = file2.length();
            if (length <= 0) {
                zza(file2);
                return false;
            }
            byte[] bArr = new byte[(int) length];
            FileInputStream fileInputStream2 = new FileInputStream(file2);
            try {
                try {
                    if (fileInputStream2.read(bArr) <= 0) {
                        Log.d(zzb, "Cannot read the cache data.");
                        zza(file2);
                        try {
                            fileInputStream2.close();
                        } catch (IOException unused) {
                        }
                        return false;
                    }
                    zzbe zzbeVarZza = zzfls.zza(new zzbe(), bArr);
                    if (str.equals(new String(zzbeVarZza.zzc)) && Arrays.equals(zzbeVarZza.zzb, zzbx.zza(zzbeVarZza.zza)) && Arrays.equals(zzbeVarZza.zzd, Build.VERSION.SDK.getBytes())) {
                        byte[] bArrZza = this.zze.zza(this.zzf, new String(zzbeVarZza.zza));
                        file3.createNewFile();
                        fileOutputStream = new FileOutputStream(file3);
                        try {
                            fileOutputStream.write(bArrZza, 0, bArrZza.length);
                            try {
                                fileInputStream2.close();
                            } catch (IOException unused2) {
                            }
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused3) {
                            }
                            return true;
                        } catch (zzcy | IOException | NoSuchAlgorithmException unused4) {
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException unused5) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused6) {
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException unused7) {
                                }
                            }
                            if (fileOutputStream == null) {
                                throw th;
                            }
                            try {
                                fileOutputStream.close();
                                throw th;
                            } catch (IOException unused8) {
                                throw th;
                            }
                        }
                    }
                    zza(file2);
                    try {
                        fileInputStream2.close();
                    } catch (IOException unused9) {
                    }
                    return false;
                } catch (zzcy | IOException | NoSuchAlgorithmException unused10) {
                    fileOutputStream = null;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (zzcy | IOException | NoSuchAlgorithmException unused11) {
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzo() {
        try {
            if (this.zzg == null && this.zzj) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zza);
                advertisingIdClient.start();
                this.zzg = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzg = null;
        }
    }

    private final zzba zzp() {
        try {
            return zzcda.zza(this.zza, this.zza.getPackageName(), Integer.toString(this.zza.getPackageManager().getPackageInfo(this.zza.getPackageName(), 0).versionCode));
        } catch (Throwable unused) {
            return null;
        }
    }

    public final Context zza() {
        return this.zza;
    }

    public final Method zza(String str, String str2) {
        zzes zzesVar = this.zzp.get(new Pair(str, str2));
        if (zzesVar == null) {
            return null;
        }
        return zzesVar.zza();
    }

    final void zza(int i, boolean z) {
        if (this.zzo) {
            Future<?> futureSubmit = this.zzc.submit(new zzdo(this, i, z));
            if (i == 0) {
                this.zzl = futureSubmit;
            }
        }
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zzp.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzp.put(new Pair<>(str, str2), new zzes(this, str, str2, clsArr));
        return true;
    }

    final zzba zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException unused) {
            }
        }
        return zzp();
    }

    public final boolean zzb() {
        return this.zzq;
    }

    public final ExecutorService zzc() {
        return this.zzc;
    }

    public final DexClassLoader zzd() {
        return this.zzd;
    }

    public final zzcx zze() {
        return this.zze;
    }

    public final byte[] zzf() {
        return this.zzf;
    }

    public final boolean zzg() {
        return this.zzn;
    }

    public final zzcp zzh() {
        return this.zzm;
    }

    public final boolean zzi() {
        return this.zzo;
    }

    public final boolean zzj() {
        return this.zzr;
    }

    public final zzba zzk() {
        return this.zzk;
    }

    public final Future zzl() {
        return this.zzl;
    }

    public final AdvertisingIdClient zzm() {
        if (!this.zzh) {
            return null;
        }
        if (this.zzg != null) {
            return this.zzg;
        }
        Future future = this.zzi;
        if (future != null) {
            try {
                future.get(AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, TimeUnit.MILLISECONDS);
                this.zzi = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzi.cancel(true);
            }
        }
        return this.zzg;
    }

    public final int zzn() {
        if (this.zzm != null) {
            return zzcp.zza();
        }
        return Integer.MIN_VALUE;
    }
}
