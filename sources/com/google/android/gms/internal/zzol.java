package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzol {
    private BlockingQueue<zzov> zza;
    private ExecutorService zzb;
    private LinkedHashMap<String, String> zzc = new LinkedHashMap<>();
    private Map<String, zzop> zzd = new HashMap();
    private String zze;
    private Context zzf;
    private String zzg;
    private AtomicBoolean zzh;
    private File zzi;

    public zzol(Context context, String str, String str2, Map<String, String> map) {
        File externalStorageDirectory;
        this.zzf = context;
        this.zzg = str;
        this.zze = str2;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.zzh = atomicBoolean;
        atomicBoolean.set(((Boolean) zzlc.zzf().zza(zzoi.zzal)).booleanValue());
        if (this.zzh.get() && (externalStorageDirectory = Environment.getExternalStorageDirectory()) != null) {
            this.zzi = new File(externalStorageDirectory, "sdk_csi_data.txt");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.zzc.put(entry.getKey(), entry.getValue());
        }
        this.zza = new ArrayBlockingQueue(30);
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.zzb = executorServiceNewSingleThreadExecutor;
        executorServiceNewSingleThreadExecutor.execute(new zzom(this));
        this.zzd.put("action", zzop.zzb);
        this.zzd.put("ad_format", zzop.zzb);
        this.zzd.put("e", zzop.zzc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza() throws Throwable {
        while (true) {
            try {
                zzov zzovVarTake = this.zza.take();
                String strZzb = zzovVarTake.zzb();
                if (!TextUtils.isEmpty(strZzb)) {
                    Map<String, String> mapZza = zza(this.zzc, zzovVarTake.zzc());
                    Uri.Builder builderBuildUpon = Uri.parse(this.zze).buildUpon();
                    for (Map.Entry<String, String> entry : mapZza.entrySet()) {
                        builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
                    }
                    String str = builderBuildUpon.build().toString() + "&it=" + strZzb;
                    if (this.zzh.get()) {
                        File file = this.zzi;
                        if (file != null) {
                            FileOutputStream fileOutputStream = null;
                            try {
                                try {
                                    FileOutputStream fileOutputStream2 = new FileOutputStream(file, true);
                                    try {
                                        fileOutputStream2.write(str.getBytes());
                                        fileOutputStream2.write(10);
                                        try {
                                            fileOutputStream2.close();
                                        } catch (IOException e) {
                                            zzahw.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e);
                                        }
                                    } catch (IOException e2) {
                                        e = e2;
                                        fileOutputStream = fileOutputStream2;
                                        zzahw.zzc("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e);
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        fileOutputStream = fileOutputStream2;
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (IOException e3) {
                                                zzahw.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e3);
                                            }
                                        }
                                        throw th;
                                    }
                                } catch (IOException e4) {
                                    e = e4;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } else {
                            zzahw.zze("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
                        }
                    } else {
                        zzbt.zze();
                        zzaij.zzb(this.zzf, this.zzg, str);
                    }
                }
            } catch (InterruptedException e5) {
                zzahw.zzc("CsiReporter:reporter interrupted", e5);
                return;
            }
        }
    }

    public final zzop zza(String str) {
        zzop zzopVar = this.zzd.get(str);
        return zzopVar != null ? zzopVar : zzop.zza;
    }

    final Map<String, String> zza(Map<String, String> map, Map<String, String> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            linkedHashMap.put(key, zza(key).zza((String) linkedHashMap.get(key), value));
        }
        return linkedHashMap;
    }

    public final void zza(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.zzc.put("e", TextUtils.join(",", list));
    }

    public final boolean zza(zzov zzovVar) {
        return this.zza.offer(zzovVar);
    }
}
