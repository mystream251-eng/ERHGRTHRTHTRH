package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzhg {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final zzht zzd;
    private final zzic zze;
    private int zzm;
    private final Object zzf = new Object();
    private ArrayList<String> zzg = new ArrayList<>();
    private ArrayList<String> zzh = new ArrayList<>();
    private ArrayList<zzhr> zzi = new ArrayList<>();
    private int zzj = 0;
    private int zzk = 0;
    private int zzl = 0;
    private String zzn = "";
    private String zzo = "";
    private String zzp = "";

    public zzhg(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = new zzht(i4);
        this.zze = new zzic(i5, i6, i7);
    }

    private static String zza(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            String str = arrayList2.get(i2);
            i2++;
            sb.append(str);
            sb.append(' ');
            if (sb.length() > 100) {
                break;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        String string = sb.toString();
        return string.length() < 100 ? string : string.substring(0, 100);
    }

    private final void zzc(String str, boolean z, float f, float f2, float f3, float f4) {
        if (str == null || str.length() < this.zzc) {
            return;
        }
        synchronized (this.zzf) {
            this.zzg.add(str);
            this.zzj += str.length();
            if (z) {
                this.zzh.add(str);
                this.zzi.add(new zzhr(f, f2, f3, f4, this.zzh.size() - 1));
            }
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzhg)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        String str = ((zzhg) obj).zzn;
        return str != null && str.equals(this.zzn);
    }

    public final int hashCode() {
        return this.zzn.hashCode();
    }

    public final String toString() {
        int i = this.zzk;
        int i2 = this.zzm;
        int i3 = this.zzj;
        String strZza = zza(this.zzg, 100);
        String strZza2 = zza(this.zzh, 100);
        String str = this.zzn;
        String str2 = this.zzo;
        String str3 = this.zzp;
        StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 165 + String.valueOf(strZza2).length() + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append("ActivityContent fetchId: ");
        sb.append(i);
        sb.append(" score:");
        sb.append(i2);
        sb.append(" total_length:");
        sb.append(i3);
        sb.append("\n text: ");
        sb.append(strZza);
        sb.append("\n viewableText");
        sb.append(strZza2);
        sb.append("\n signture: ");
        sb.append(str);
        sb.append("\n viewableSignture: ");
        sb.append(str2);
        sb.append("\n viewableSignatureForVertical: ");
        sb.append(str3);
        return sb.toString();
    }

    public final void zza(int i) {
        this.zzk = i;
    }

    public final void zza(String str, boolean z, float f, float f2, float f3, float f4) {
        zzc(str, z, f, f2, f3, f4);
        synchronized (this.zzf) {
            if (this.zzl < 0) {
                zzahw.zzb("ActivityContent: negative number of WebViews.");
            }
            zzh();
        }
    }

    public final boolean zza() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzl == 0;
        }
        return z;
    }

    public final String zzb() {
        return this.zzn;
    }

    public final void zzb(String str, boolean z, float f, float f2, float f3, float f4) {
        zzc(str, z, f, f2, f3, f4);
    }

    public final String zzc() {
        return this.zzo;
    }

    public final String zzd() {
        return this.zzp;
    }

    public final void zze() {
        synchronized (this.zzf) {
            this.zzm -= 100;
        }
    }

    public final void zzf() {
        synchronized (this.zzf) {
            this.zzl--;
        }
    }

    public final void zzg() {
        synchronized (this.zzf) {
            this.zzl++;
        }
    }

    public final void zzh() {
        synchronized (this.zzf) {
            int i = (this.zzj * this.zza) + (this.zzk * this.zzb);
            if (i > this.zzm) {
                this.zzm = i;
                if (((Boolean) zzlc.zzf().zza(zzoi.zzas)).booleanValue() && !zzbt.zzi().zzl().zzb()) {
                    this.zzn = this.zzd.zza(this.zzg);
                    this.zzo = this.zzd.zza(this.zzh);
                }
                if (((Boolean) zzlc.zzf().zza(zzoi.zzau)).booleanValue() && !zzbt.zzi().zzl().zzd()) {
                    this.zzp = this.zze.zza(this.zzh, this.zzi);
                }
            }
        }
    }

    public final int zzi() {
        return this.zzm;
    }

    final int zzj() {
        return this.zzj;
    }
}
