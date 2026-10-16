package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxd extends zzfhu<zzdxd, zza> implements zzfje {
    private static final zzdxd zzg;
    private static volatile zzfjl<zzdxd> zzh;
    private int zzd;
    private String zze = "";
    private zzfid<zzdwn> zzf = zzu();

    public static final class zza extends zzfhu.zza<zzdxd, zza> implements zzfje {
        private zza() {
            super(zzdxd.zzg);
        }

        /* synthetic */ zza(zzdxe zzdxeVar) {
            this();
        }

        public final zza zza(zzdwn zzdwnVar) {
            zzb();
            ((zzdxd) this.zza).zza(zzdwnVar);
            return this;
        }

        public final zza zza(String str) {
            zzb();
            ((zzdxd) this.zza).zza(str);
            return this;
        }
    }

    static {
        zzdxd zzdxdVar = new zzdxd();
        zzg = zzdxdVar;
        zzdxdVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdxdVar).zzb.zzc();
    }

    private zzdxd() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdwn zzdwnVar) {
        if (zzdwnVar == null) {
            throw null;
        }
        if (!this.zzf.zza()) {
            zzfid<zzdwn> zzfidVar = this.zzf;
            int size = zzfidVar.size();
            this.zzf = zzfidVar.zzd(size == 0 ? 10 : size << 1);
        }
        this.zzf.add(zzdwnVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str) {
        if (str == null) {
            throw null;
        }
        this.zze = str;
    }

    public static zza zzc() {
        return (zza) ((zzfhu.zza) zzg.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int iZzb = !this.zze.isEmpty() ? zzfhg.zzb(1, this.zze) + 0 : 0;
        for (int i2 = 0; i2 < this.zzf.size(); i2++) {
            iZzb += zzfhg.zzc(2, (zzfjc) this.zzf.get(i2));
        }
        int iZze = iZzb + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdxe zzdxeVar = null;
        switch (zzdxe.zza[i - 1]) {
            case 1:
                return new zzdxd();
            case 2:
                return zzg;
            case 3:
                this.zzf.zzb();
                return null;
            case 4:
                return new zza(zzdxeVar);
            case 5:
                zzfhu.zzf zzfVar = (zzfhu.zzh) obj;
                zzdxd zzdxdVar = (zzdxd) obj2;
                this.zze = zzfVar.zza(!this.zze.isEmpty(), this.zze, true ^ zzdxdVar.zze.isEmpty(), zzdxdVar.zze);
                this.zzf = zzfVar.zza(this.zzf, zzdxdVar.zzf);
                if (zzfVar == zzfhu.zzf.zza) {
                    this.zzd |= zzdxdVar.zzd;
                }
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                zzfhm zzfhmVar = (zzfhm) obj2;
                if (zzfhmVar == null) {
                    throw null;
                }
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int iZza = zzfhbVar.zza();
                            if (iZza != 0) {
                                int i2 = 10;
                                if (iZza == 10) {
                                    this.zze = zzfhbVar.zzk();
                                } else if (iZza == 18) {
                                    if (!this.zzf.zza()) {
                                        zzfid<zzdwn> zzfidVar = this.zzf;
                                        int size = zzfidVar.size();
                                        if (size != 0) {
                                            i2 = size << 1;
                                        }
                                        this.zzf = zzfidVar.zzd(i2);
                                    }
                                    this.zzf.add((zzdwn) zzfhbVar.zza(zzdwn.zzh(), zzfhmVar));
                                } else if (!zza(iZza, zzfhbVar)) {
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new RuntimeException((Throwable) new zzfie(e.getMessage()).zza(this));
                        }
                    } catch (zzfie e2) {
                        throw new RuntimeException((Throwable) e2.zza(this));
                    }
                }
                break;
                break;
            case 7:
                break;
            case 8:
                if (zzh == null) {
                    synchronized (zzdxd.class) {
                        if (zzh == null) {
                            zzh = new zzfhu.zzb(zzg);
                        }
                        break;
                    }
                }
                return zzh;
            case 9:
                return (byte) 1;
            case 10:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
        return zzg;
    }

    public final void zza(zzfhg zzfhgVar) throws IOException {
        if (!this.zze.isEmpty()) {
            zzfhgVar.zza(1, this.zze);
        }
        for (int i = 0; i < this.zzf.size(); i++) {
            zzfhgVar.zza(2, (zzfjc) this.zzf.get(i));
        }
        this.zzb.zza(zzfhgVar);
    }

    public final List<zzdwn> zzb() {
        return this.zzf;
    }
}
