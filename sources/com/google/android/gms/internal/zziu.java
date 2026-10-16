package com.google.android.gms.internal;

import android.os.Environment;
import com.google.android.gms.ads.internal.zzbt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zziu {
    private final zziz zza;
    private final zzjk zzb;
    private final boolean zzc;

    private zziu() {
        this.zzc = false;
        this.zza = new zziz();
        this.zzb = new zzjk();
        zzb();
    }

    public zziu(zziz zzizVar) {
        this.zza = zzizVar;
        this.zzc = ((Boolean) zzlc.zzf().zza(zzoi.zzgp)).booleanValue();
        this.zzb = new zzjk();
        zzb();
    }

    public static zziu zza() {
        return new zziu();
    }

    private final synchronized void zzb() {
        this.zzb.zzd = new zzjd();
        this.zzb.zzd.zzb = new zzjg();
        this.zzb.zzc = new zzji();
    }

    private final synchronized void zzb(zziw.zza.zzb zzbVar) {
        this.zzb.zzb = zzc();
        this.zza.zza(zzfls.zza(this.zzb)).zzb(zzbVar.zza()).zza();
        String strValueOf = String.valueOf(Integer.toString(zzbVar.zza(), 10));
        zzahw.zza(strValueOf.length() != 0 ? "Logging Event with event code : ".concat(strValueOf) : new String("Logging Event with event code : "));
    }

    private final synchronized void zzc(zziw.zza.zzb zzbVar) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            return;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(externalStorageDirectory, "clearcut_events.txt"), true);
            try {
                fileOutputStream.write(zzd(zzbVar).getBytes());
                fileOutputStream.write(10);
            } catch (IOException unused) {
                zzahw.zza("Could not write Clearcut to file.");
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException unused2) {
                    zzahw.zza("Could not close Clearcut output stream.");
                }
            }
        } catch (FileNotFoundException unused3) {
            zzahw.zza("Could not find file for Clearcut");
        }
    }

    private static long[] zzc() {
        int i;
        List<String> listZzb = zzoi.zzb();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = listZzb.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            String[] strArrSplit = it.next().split(",");
            int length = strArrSplit.length;
            while (i < length) {
                try {
                    arrayList.add(Long.valueOf(strArrSplit[i]));
                } catch (NumberFormatException unused) {
                    zzahw.zza("Experiment ID is not a number");
                }
                i++;
            }
        }
        long[] jArr = new long[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            jArr[i2] = ((Long) obj).longValue();
            i2++;
        }
        return jArr;
    }

    private final synchronized String zzd(zziw.zza.zzb zzbVar) {
        return String.format("id=%s,timestamp=%s,event=%s", this.zzb.zza, Long.valueOf(zzbt.zzl().zzb()), Integer.valueOf(zzbVar.zza()));
    }

    public final synchronized void zza(zziv zzivVar) {
        if (this.zzc) {
            try {
                zzivVar.zza(this.zzb);
            } catch (NullPointerException e) {
                zzbt.zzi().zza(e, "AdMobClearcutLogger.modify");
            }
        }
    }

    public final synchronized void zza(zziw.zza.zzb zzbVar) {
        if (this.zzc) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzgq)).booleanValue()) {
                zzc(zzbVar);
            } else {
                zzb(zzbVar);
            }
        }
    }
}
