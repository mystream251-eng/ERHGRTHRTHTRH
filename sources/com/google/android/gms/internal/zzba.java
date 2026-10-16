package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzba extends zzflm<zzba> {
    public Integer zzae;
    public Integer zzaf;
    public Integer zzaj;
    public Integer zzat;
    public String zza = null;
    public String zzb = null;
    public Long zzc = null;
    private Long zzaz = null;
    public Long zzd = null;
    public Long zze = null;
    private Long zzba = null;
    private Long zzbb = null;
    private Long zzbc = null;
    private Long zzbd = null;
    private Long zzbe = null;
    public Long zzf = null;
    private String zzbf = null;
    public Long zzg = null;
    public Long zzh = null;
    public Long zzi = null;
    public Long zzj = null;
    private Long zzbg = null;
    private Long zzbh = null;
    public Long zzk = null;
    public Long zzl = null;
    public Long zzm = null;
    public String zzn = null;
    public String zzo = null;
    public Long zzp = null;
    public Long zzq = null;
    public Long zzr = null;
    public String zzs = null;
    public Long zzt = null;
    public Long zzu = null;
    public Long zzv = null;
    private zzbd zzbi = null;
    public Long zzw = null;
    public Long zzx = null;
    public Long zzy = null;
    public Long zzz = null;
    public Long zzaa = null;
    public Long zzab = null;
    public String zzac = null;
    public String zzad = null;
    public Long zzag = null;
    public Long zzah = null;
    public Long zzai = null;
    private Long zzbj = null;
    private Long zzbk = null;
    public zzbb zzak = null;
    public zzbb[] zzal = zzbb.zzb();
    public zzbc zzam = null;
    private Long zzbl = null;
    public Long zzan = null;
    public Long zzao = null;
    public Long zzap = null;
    public Long zzaq = null;
    public Long zzar = null;
    private Long zzbm = null;
    private Long zzbn = null;
    public String zzas = null;
    public Boolean zzau = null;
    private String zzbo = null;
    public Long zzav = null;
    public zzbf zzaw = null;

    public zzba() {
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzba zza(zzflj zzfljVar) throws IOException {
        int iZzm;
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 10:
                    this.zza = zzfljVar.zze();
                    break;
                case 18:
                    this.zzb = zzfljVar.zze();
                    break;
                case 24:
                    this.zzc = Long.valueOf(zzfljVar.zzi());
                    break;
                case 32:
                    this.zzaz = Long.valueOf(zzfljVar.zzi());
                    break;
                case 40:
                    this.zzd = Long.valueOf(zzfljVar.zzi());
                    break;
                case 48:
                    this.zze = Long.valueOf(zzfljVar.zzi());
                    break;
                case 56:
                    this.zzba = Long.valueOf(zzfljVar.zzi());
                    break;
                case 64:
                    this.zzbb = Long.valueOf(zzfljVar.zzi());
                    break;
                case 72:
                    this.zzbc = Long.valueOf(zzfljVar.zzi());
                    break;
                case 80:
                    this.zzbd = Long.valueOf(zzfljVar.zzi());
                    break;
                case 88:
                    this.zzbe = Long.valueOf(zzfljVar.zzi());
                    break;
                case 96:
                    this.zzf = Long.valueOf(zzfljVar.zzi());
                    break;
                case 106:
                    this.zzbf = zzfljVar.zze();
                    break;
                case 112:
                    this.zzg = Long.valueOf(zzfljVar.zzi());
                    break;
                case 120:
                    this.zzh = Long.valueOf(zzfljVar.zzi());
                    break;
                case 128:
                    this.zzi = Long.valueOf(zzfljVar.zzi());
                    break;
                case 136:
                    this.zzj = Long.valueOf(zzfljVar.zzi());
                    break;
                case 144:
                    this.zzbg = Long.valueOf(zzfljVar.zzi());
                    break;
                case 152:
                    this.zzbh = Long.valueOf(zzfljVar.zzi());
                    break;
                case 160:
                    this.zzk = Long.valueOf(zzfljVar.zzi());
                    break;
                case 168:
                    this.zzbn = Long.valueOf(zzfljVar.zzi());
                    break;
                case 176:
                    this.zzl = Long.valueOf(zzfljVar.zzi());
                    break;
                case 184:
                    this.zzm = Long.valueOf(zzfljVar.zzi());
                    break;
                case 194:
                    this.zzas = zzfljVar.zze();
                    break;
                case 200:
                    this.zzav = Long.valueOf(zzfljVar.zzi());
                    break;
                case 208:
                    iZzm = zzfljVar.zzm();
                    int iZzh = zzfljVar.zzh();
                    switch (iZzh) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            this.zzat = Integer.valueOf(iZzh);
                            continue;
                        default:
                            StringBuilder sb = new StringBuilder(44);
                            sb.append(iZzh);
                            sb.append(" is not a valid enum DeviceIdType");
                            throw new IllegalArgumentException(sb.toString());
                    }
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                    break;
                case 218:
                    this.zzn = zzfljVar.zze();
                    break;
                case 224:
                    this.zzau = Boolean.valueOf(zzfljVar.zzd());
                    break;
                case 234:
                    this.zzo = zzfljVar.zze();
                    break;
                case 242:
                    this.zzbo = zzfljVar.zze();
                    break;
                case 248:
                    this.zzp = Long.valueOf(zzfljVar.zzi());
                    break;
                case 256:
                    this.zzq = Long.valueOf(zzfljVar.zzi());
                    break;
                case 264:
                    this.zzr = Long.valueOf(zzfljVar.zzi());
                    break;
                case 274:
                    this.zzs = zzfljVar.zze();
                    break;
                case 280:
                    this.zzt = Long.valueOf(zzfljVar.zzi());
                    break;
                case 288:
                    this.zzu = Long.valueOf(zzfljVar.zzi());
                    break;
                case 296:
                    this.zzv = Long.valueOf(zzfljVar.zzi());
                    break;
                case 306:
                    if (this.zzbi == null) {
                        this.zzbi = new zzbd();
                    }
                    zzflsVar = this.zzbi;
                    zzfljVar.zza(zzflsVar);
                    break;
                case 312:
                    this.zzw = Long.valueOf(zzfljVar.zzi());
                    break;
                case 320:
                    this.zzx = Long.valueOf(zzfljVar.zzi());
                    break;
                case 328:
                    this.zzy = Long.valueOf(zzfljVar.zzi());
                    break;
                case 336:
                    this.zzz = Long.valueOf(zzfljVar.zzi());
                    break;
                case 346:
                    int iZza2 = zzflv.zza(zzfljVar, 346);
                    zzbb[] zzbbVarArr = this.zzal;
                    int length = zzbbVarArr == null ? 0 : zzbbVarArr.length;
                    int i = iZza2 + length;
                    zzfls[] zzflsVarArr = new zzbb[i];
                    if (length != 0) {
                        System.arraycopy(this.zzal, 0, zzflsVarArr, 0, length);
                    }
                    while (length < i - 1) {
                        zzflsVarArr[length] = new zzbb();
                        zzfljVar.zza(zzflsVarArr[length]);
                        zzfljVar.zza();
                        length++;
                    }
                    zzflsVarArr[length] = new zzbb();
                    zzfljVar.zza(zzflsVarArr[length]);
                    this.zzal = zzflsVarArr;
                    break;
                case 352:
                    this.zzaa = Long.valueOf(zzfljVar.zzi());
                    break;
                case 360:
                    this.zzab = Long.valueOf(zzfljVar.zzi());
                    break;
                case 370:
                    this.zzac = zzfljVar.zze();
                    break;
                case 378:
                    this.zzad = zzfljVar.zze();
                    break;
                case 384:
                    zzfljVar.zzm();
                    this.zzae = Integer.valueOf(zzaz.zza(zzfljVar.zzh()));
                    break;
                case 392:
                    zzfljVar.zzm();
                    this.zzaf = Integer.valueOf(zzaz.zza(zzfljVar.zzh()));
                    break;
                case 402:
                    if (this.zzak == null) {
                        this.zzak = new zzbb();
                    }
                    zzflsVar = this.zzak;
                    zzfljVar.zza(zzflsVar);
                    break;
                case 408:
                    this.zzag = Long.valueOf(zzfljVar.zzi());
                    break;
                case 416:
                    this.zzah = Long.valueOf(zzfljVar.zzi());
                    break;
                case 424:
                    this.zzai = Long.valueOf(zzfljVar.zzi());
                    break;
                case 432:
                    this.zzbj = Long.valueOf(zzfljVar.zzi());
                    break;
                case 440:
                    this.zzbk = Long.valueOf(zzfljVar.zzi());
                    break;
                case 448:
                    iZzm = zzfljVar.zzm();
                    try {
                        this.zzaj = Integer.valueOf(zzaz.zza(zzfljVar.zzh()));
                    } catch (IllegalArgumentException unused) {
                        zzfljVar.zze(iZzm);
                        zza(zzfljVar, iZza);
                    }
                    break;
                case 458:
                    if (this.zzam == null) {
                        this.zzam = new zzbc();
                    }
                    zzflsVar = this.zzam;
                    zzfljVar.zza(zzflsVar);
                    break;
                case 464:
                    this.zzbl = Long.valueOf(zzfljVar.zzi());
                    break;
                case 472:
                    this.zzan = Long.valueOf(zzfljVar.zzi());
                    break;
                case 480:
                    this.zzao = Long.valueOf(zzfljVar.zzi());
                    break;
                case 488:
                    this.zzap = Long.valueOf(zzfljVar.zzi());
                    break;
                case 496:
                    this.zzaq = Long.valueOf(zzfljVar.zzi());
                    break;
                case 504:
                    this.zzar = Long.valueOf(zzfljVar.zzi());
                    break;
                case 512:
                    this.zzbm = Long.valueOf(zzfljVar.zzi());
                    break;
                case 1610:
                    if (this.zzaw == null) {
                        this.zzaw = new zzbf();
                    }
                    zzflsVar = this.zzaw;
                    zzfljVar.zza(zzflsVar);
                    break;
                default:
                    if (!super.zza(zzfljVar, iZza)) {
                        return this;
                    }
                    break;
                    break;
            }
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        String str = this.zza;
        if (str != null) {
            iZza += zzflk.zzb(1, str);
        }
        String str2 = this.zzb;
        if (str2 != null) {
            iZza += zzflk.zzb(2, str2);
        }
        Long l = this.zzc;
        if (l != null) {
            iZza += zzflk.zze(3, l.longValue());
        }
        Long l2 = this.zzaz;
        if (l2 != null) {
            iZza += zzflk.zze(4, l2.longValue());
        }
        Long l3 = this.zzd;
        if (l3 != null) {
            iZza += zzflk.zze(5, l3.longValue());
        }
        Long l4 = this.zze;
        if (l4 != null) {
            iZza += zzflk.zze(6, l4.longValue());
        }
        Long l5 = this.zzba;
        if (l5 != null) {
            iZza += zzflk.zze(7, l5.longValue());
        }
        Long l6 = this.zzbb;
        if (l6 != null) {
            iZza += zzflk.zze(8, l6.longValue());
        }
        Long l7 = this.zzbc;
        if (l7 != null) {
            iZza += zzflk.zze(9, l7.longValue());
        }
        Long l8 = this.zzbd;
        if (l8 != null) {
            iZza += zzflk.zze(10, l8.longValue());
        }
        Long l9 = this.zzbe;
        if (l9 != null) {
            iZza += zzflk.zze(11, l9.longValue());
        }
        Long l10 = this.zzf;
        if (l10 != null) {
            iZza += zzflk.zze(12, l10.longValue());
        }
        String str3 = this.zzbf;
        if (str3 != null) {
            iZza += zzflk.zzb(13, str3);
        }
        Long l11 = this.zzg;
        if (l11 != null) {
            iZza += zzflk.zze(14, l11.longValue());
        }
        Long l12 = this.zzh;
        if (l12 != null) {
            iZza += zzflk.zze(15, l12.longValue());
        }
        Long l13 = this.zzi;
        if (l13 != null) {
            iZza += zzflk.zze(16, l13.longValue());
        }
        Long l14 = this.zzj;
        if (l14 != null) {
            iZza += zzflk.zze(17, l14.longValue());
        }
        Long l15 = this.zzbg;
        if (l15 != null) {
            iZza += zzflk.zze(18, l15.longValue());
        }
        Long l16 = this.zzbh;
        if (l16 != null) {
            iZza += zzflk.zze(19, l16.longValue());
        }
        Long l17 = this.zzk;
        if (l17 != null) {
            iZza += zzflk.zze(20, l17.longValue());
        }
        Long l18 = this.zzbn;
        if (l18 != null) {
            iZza += zzflk.zze(21, l18.longValue());
        }
        Long l19 = this.zzl;
        if (l19 != null) {
            iZza += zzflk.zze(22, l19.longValue());
        }
        Long l20 = this.zzm;
        if (l20 != null) {
            iZza += zzflk.zze(23, l20.longValue());
        }
        String str4 = this.zzas;
        if (str4 != null) {
            iZza += zzflk.zzb(24, str4);
        }
        Long l21 = this.zzav;
        if (l21 != null) {
            iZza += zzflk.zze(25, l21.longValue());
        }
        Integer num = this.zzat;
        if (num != null) {
            iZza += zzflk.zzb(26, num.intValue());
        }
        String str5 = this.zzn;
        if (str5 != null) {
            iZza += zzflk.zzb(27, str5);
        }
        Boolean bool = this.zzau;
        if (bool != null) {
            bool.booleanValue();
            iZza += zzflk.zzb(28) + 1;
        }
        String str6 = this.zzo;
        if (str6 != null) {
            iZza += zzflk.zzb(29, str6);
        }
        String str7 = this.zzbo;
        if (str7 != null) {
            iZza += zzflk.zzb(30, str7);
        }
        Long l22 = this.zzp;
        if (l22 != null) {
            iZza += zzflk.zze(31, l22.longValue());
        }
        Long l23 = this.zzq;
        if (l23 != null) {
            iZza += zzflk.zze(32, l23.longValue());
        }
        Long l24 = this.zzr;
        if (l24 != null) {
            iZza += zzflk.zze(33, l24.longValue());
        }
        String str8 = this.zzs;
        if (str8 != null) {
            iZza += zzflk.zzb(34, str8);
        }
        Long l25 = this.zzt;
        if (l25 != null) {
            iZza += zzflk.zze(35, l25.longValue());
        }
        Long l26 = this.zzu;
        if (l26 != null) {
            iZza += zzflk.zze(36, l26.longValue());
        }
        Long l27 = this.zzv;
        if (l27 != null) {
            iZza += zzflk.zze(37, l27.longValue());
        }
        zzbd zzbdVar = this.zzbi;
        if (zzbdVar != null) {
            iZza += zzflk.zzb(38, zzbdVar);
        }
        Long l28 = this.zzw;
        if (l28 != null) {
            iZza += zzflk.zze(39, l28.longValue());
        }
        Long l29 = this.zzx;
        if (l29 != null) {
            iZza += zzflk.zze(40, l29.longValue());
        }
        Long l30 = this.zzy;
        if (l30 != null) {
            iZza += zzflk.zze(41, l30.longValue());
        }
        Long l31 = this.zzz;
        if (l31 != null) {
            iZza += zzflk.zze(42, l31.longValue());
        }
        zzbb[] zzbbVarArr = this.zzal;
        if (zzbbVarArr != null && zzbbVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zzal;
                if (i >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i];
                if (zzflsVar != null) {
                    iZza += zzflk.zzb(43, zzflsVar);
                }
                i++;
            }
        }
        Long l32 = this.zzaa;
        if (l32 != null) {
            iZza += zzflk.zze(44, l32.longValue());
        }
        Long l33 = this.zzab;
        if (l33 != null) {
            iZza += zzflk.zze(45, l33.longValue());
        }
        String str9 = this.zzac;
        if (str9 != null) {
            iZza += zzflk.zzb(46, str9);
        }
        String str10 = this.zzad;
        if (str10 != null) {
            iZza += zzflk.zzb(47, str10);
        }
        Integer num2 = this.zzae;
        if (num2 != null) {
            iZza += zzflk.zzb(48, num2.intValue());
        }
        Integer num3 = this.zzaf;
        if (num3 != null) {
            iZza += zzflk.zzb(49, num3.intValue());
        }
        zzbb zzbbVar = this.zzak;
        if (zzbbVar != null) {
            iZza += zzflk.zzb(50, zzbbVar);
        }
        Long l34 = this.zzag;
        if (l34 != null) {
            iZza += zzflk.zze(51, l34.longValue());
        }
        Long l35 = this.zzah;
        if (l35 != null) {
            iZza += zzflk.zze(52, l35.longValue());
        }
        Long l36 = this.zzai;
        if (l36 != null) {
            iZza += zzflk.zze(53, l36.longValue());
        }
        Long l37 = this.zzbj;
        if (l37 != null) {
            iZza += zzflk.zze(54, l37.longValue());
        }
        Long l38 = this.zzbk;
        if (l38 != null) {
            iZza += zzflk.zze(55, l38.longValue());
        }
        Integer num4 = this.zzaj;
        if (num4 != null) {
            iZza += zzflk.zzb(56, num4.intValue());
        }
        zzbc zzbcVar = this.zzam;
        if (zzbcVar != null) {
            iZza += zzflk.zzb(57, zzbcVar);
        }
        Long l39 = this.zzbl;
        if (l39 != null) {
            iZza += zzflk.zze(58, l39.longValue());
        }
        Long l40 = this.zzan;
        if (l40 != null) {
            iZza += zzflk.zze(59, l40.longValue());
        }
        Long l41 = this.zzao;
        if (l41 != null) {
            iZza += zzflk.zze(60, l41.longValue());
        }
        Long l42 = this.zzap;
        if (l42 != null) {
            iZza += zzflk.zze(61, l42.longValue());
        }
        Long l43 = this.zzaq;
        if (l43 != null) {
            iZza += zzflk.zze(62, l43.longValue());
        }
        Long l44 = this.zzar;
        if (l44 != null) {
            iZza += zzflk.zze(63, l44.longValue());
        }
        Long l45 = this.zzbm;
        if (l45 != null) {
            iZza += zzflk.zze(64, l45.longValue());
        }
        zzbf zzbfVar = this.zzaw;
        return zzbfVar != null ? iZza + zzflk.zzb(201, zzbfVar) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zza;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        String str2 = this.zzb;
        if (str2 != null) {
            zzflkVar.zza(2, str2);
        }
        Long l = this.zzc;
        if (l != null) {
            zzflkVar.zzb(3, l.longValue());
        }
        Long l2 = this.zzaz;
        if (l2 != null) {
            zzflkVar.zzb(4, l2.longValue());
        }
        Long l3 = this.zzd;
        if (l3 != null) {
            zzflkVar.zzb(5, l3.longValue());
        }
        Long l4 = this.zze;
        if (l4 != null) {
            zzflkVar.zzb(6, l4.longValue());
        }
        Long l5 = this.zzba;
        if (l5 != null) {
            zzflkVar.zzb(7, l5.longValue());
        }
        Long l6 = this.zzbb;
        if (l6 != null) {
            zzflkVar.zzb(8, l6.longValue());
        }
        Long l7 = this.zzbc;
        if (l7 != null) {
            zzflkVar.zzb(9, l7.longValue());
        }
        Long l8 = this.zzbd;
        if (l8 != null) {
            zzflkVar.zzb(10, l8.longValue());
        }
        Long l9 = this.zzbe;
        if (l9 != null) {
            zzflkVar.zzb(11, l9.longValue());
        }
        Long l10 = this.zzf;
        if (l10 != null) {
            zzflkVar.zzb(12, l10.longValue());
        }
        String str3 = this.zzbf;
        if (str3 != null) {
            zzflkVar.zza(13, str3);
        }
        Long l11 = this.zzg;
        if (l11 != null) {
            zzflkVar.zzb(14, l11.longValue());
        }
        Long l12 = this.zzh;
        if (l12 != null) {
            zzflkVar.zzb(15, l12.longValue());
        }
        Long l13 = this.zzi;
        if (l13 != null) {
            zzflkVar.zzb(16, l13.longValue());
        }
        Long l14 = this.zzj;
        if (l14 != null) {
            zzflkVar.zzb(17, l14.longValue());
        }
        Long l15 = this.zzbg;
        if (l15 != null) {
            zzflkVar.zzb(18, l15.longValue());
        }
        Long l16 = this.zzbh;
        if (l16 != null) {
            zzflkVar.zzb(19, l16.longValue());
        }
        Long l17 = this.zzk;
        if (l17 != null) {
            zzflkVar.zzb(20, l17.longValue());
        }
        Long l18 = this.zzbn;
        if (l18 != null) {
            zzflkVar.zzb(21, l18.longValue());
        }
        Long l19 = this.zzl;
        if (l19 != null) {
            zzflkVar.zzb(22, l19.longValue());
        }
        Long l20 = this.zzm;
        if (l20 != null) {
            zzflkVar.zzb(23, l20.longValue());
        }
        String str4 = this.zzas;
        if (str4 != null) {
            zzflkVar.zza(24, str4);
        }
        Long l21 = this.zzav;
        if (l21 != null) {
            zzflkVar.zzb(25, l21.longValue());
        }
        Integer num = this.zzat;
        if (num != null) {
            zzflkVar.zza(26, num.intValue());
        }
        String str5 = this.zzn;
        if (str5 != null) {
            zzflkVar.zza(27, str5);
        }
        Boolean bool = this.zzau;
        if (bool != null) {
            zzflkVar.zza(28, bool.booleanValue());
        }
        String str6 = this.zzo;
        if (str6 != null) {
            zzflkVar.zza(29, str6);
        }
        String str7 = this.zzbo;
        if (str7 != null) {
            zzflkVar.zza(30, str7);
        }
        Long l22 = this.zzp;
        if (l22 != null) {
            zzflkVar.zzb(31, l22.longValue());
        }
        Long l23 = this.zzq;
        if (l23 != null) {
            zzflkVar.zzb(32, l23.longValue());
        }
        Long l24 = this.zzr;
        if (l24 != null) {
            zzflkVar.zzb(33, l24.longValue());
        }
        String str8 = this.zzs;
        if (str8 != null) {
            zzflkVar.zza(34, str8);
        }
        Long l25 = this.zzt;
        if (l25 != null) {
            zzflkVar.zzb(35, l25.longValue());
        }
        Long l26 = this.zzu;
        if (l26 != null) {
            zzflkVar.zzb(36, l26.longValue());
        }
        Long l27 = this.zzv;
        if (l27 != null) {
            zzflkVar.zzb(37, l27.longValue());
        }
        zzbd zzbdVar = this.zzbi;
        if (zzbdVar != null) {
            zzflkVar.zza(38, zzbdVar);
        }
        Long l28 = this.zzw;
        if (l28 != null) {
            zzflkVar.zzb(39, l28.longValue());
        }
        Long l29 = this.zzx;
        if (l29 != null) {
            zzflkVar.zzb(40, l29.longValue());
        }
        Long l30 = this.zzy;
        if (l30 != null) {
            zzflkVar.zzb(41, l30.longValue());
        }
        Long l31 = this.zzz;
        if (l31 != null) {
            zzflkVar.zzb(42, l31.longValue());
        }
        zzbb[] zzbbVarArr = this.zzal;
        if (zzbbVarArr != null && zzbbVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zzal;
                if (i >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i];
                if (zzflsVar != null) {
                    zzflkVar.zza(43, zzflsVar);
                }
                i++;
            }
        }
        Long l32 = this.zzaa;
        if (l32 != null) {
            zzflkVar.zzb(44, l32.longValue());
        }
        Long l33 = this.zzab;
        if (l33 != null) {
            zzflkVar.zzb(45, l33.longValue());
        }
        String str9 = this.zzac;
        if (str9 != null) {
            zzflkVar.zza(46, str9);
        }
        String str10 = this.zzad;
        if (str10 != null) {
            zzflkVar.zza(47, str10);
        }
        Integer num2 = this.zzae;
        if (num2 != null) {
            zzflkVar.zza(48, num2.intValue());
        }
        Integer num3 = this.zzaf;
        if (num3 != null) {
            zzflkVar.zza(49, num3.intValue());
        }
        zzbb zzbbVar = this.zzak;
        if (zzbbVar != null) {
            zzflkVar.zza(50, zzbbVar);
        }
        Long l34 = this.zzag;
        if (l34 != null) {
            zzflkVar.zzb(51, l34.longValue());
        }
        Long l35 = this.zzah;
        if (l35 != null) {
            zzflkVar.zzb(52, l35.longValue());
        }
        Long l36 = this.zzai;
        if (l36 != null) {
            zzflkVar.zzb(53, l36.longValue());
        }
        Long l37 = this.zzbj;
        if (l37 != null) {
            zzflkVar.zzb(54, l37.longValue());
        }
        Long l38 = this.zzbk;
        if (l38 != null) {
            zzflkVar.zzb(55, l38.longValue());
        }
        Integer num4 = this.zzaj;
        if (num4 != null) {
            zzflkVar.zza(56, num4.intValue());
        }
        zzbc zzbcVar = this.zzam;
        if (zzbcVar != null) {
            zzflkVar.zza(57, zzbcVar);
        }
        Long l39 = this.zzbl;
        if (l39 != null) {
            zzflkVar.zzb(58, l39.longValue());
        }
        Long l40 = this.zzan;
        if (l40 != null) {
            zzflkVar.zzb(59, l40.longValue());
        }
        Long l41 = this.zzao;
        if (l41 != null) {
            zzflkVar.zzb(60, l41.longValue());
        }
        Long l42 = this.zzap;
        if (l42 != null) {
            zzflkVar.zzb(61, l42.longValue());
        }
        Long l43 = this.zzaq;
        if (l43 != null) {
            zzflkVar.zzb(62, l43.longValue());
        }
        Long l44 = this.zzar;
        if (l44 != null) {
            zzflkVar.zzb(63, l44.longValue());
        }
        Long l45 = this.zzbm;
        if (l45 != null) {
            zzflkVar.zzb(64, l45.longValue());
        }
        zzbf zzbfVar = this.zzaw;
        if (zzbfVar != null) {
            zzflkVar.zza(201, zzbfVar);
        }
        super.zza(zzflkVar);
    }
}
