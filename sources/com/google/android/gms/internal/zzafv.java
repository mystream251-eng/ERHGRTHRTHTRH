package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzafv extends zzahs implements zzafu {
    private final zzahe zza;
    private final Context zzb;
    private final ArrayList<zzafl> zzc;
    private final List<zzafo> zzd;
    private final HashSet<String> zze;
    private final Object zzf;
    private final zzaeo zzg;
    private final long zzh;

    public zzafv(Context context, zzahe zzaheVar, zzaeo zzaeoVar) {
        this(context, zzaheVar, zzaeoVar, ((Long) zzlc.zzf().zza(zzoi.zzby)).longValue());
    }

    private zzafv(Context context, zzahe zzaheVar, zzaeo zzaeoVar, long j) {
        this.zzc = new ArrayList<>();
        this.zzd = new ArrayList();
        this.zze = new HashSet<>();
        this.zzf = new Object();
        this.zzb = context;
        this.zza = zzaheVar;
        this.zzg = zzaeoVar;
        this.zzh = j;
    }

    private final zzahd zza(int i, String str, zzvp zzvpVar) {
        return new zzahd(this.zza.zza.zzc, null, this.zza.zzb.zzc, i, this.zza.zzb.zze, this.zza.zzb.zzi, this.zza.zzb.zzk, this.zza.zzb.zzj, this.zza.zza.zzi, this.zza.zzb.zzg, zzvpVar, null, str, this.zza.zzc, null, this.zza.zzb.zzh, this.zza.zzd, this.zza.zzb.zzf, this.zza.zzf, this.zza.zzb.zzm, this.zza.zzb.zzn, this.zza.zzh, null, this.zza.zzb.zzaa, this.zza.zzb.zzab, this.zza.zzb.zzac, this.zza.zzb.zzad, this.zza.zzb.zzae, zzd(), this.zza.zzb.zzah, this.zza.zzb.zzal, this.zza.zzi, this.zza.zzb.zzao, this.zza.zzj);
    }

    private final String zzd() {
        StringBuilder sb = new StringBuilder("");
        List<zzafo> list = this.zzd;
        if (list == null) {
            return sb.toString();
        }
        Iterator<zzafo> it = list.iterator();
        while (true) {
            int i = 1;
            if (!it.hasNext()) {
                return sb.substring(0, Math.max(0, sb.length() - 1));
            }
            zzafo next = it.next();
            if (next != null && !TextUtils.isEmpty(next.zza)) {
                String str = next.zza;
                int i2 = next.zzb;
                if (i2 != 3) {
                    if (i2 == 4) {
                        i = 2;
                    } else if (i2 == 5) {
                        i = 4;
                    } else if (i2 != 6) {
                        i = i2 != 7 ? 6 : 3;
                    } else {
                        i = 0;
                    }
                }
                long j = next.zzc;
                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 33);
                sb2.append(str);
                sb2.append(".");
                sb2.append(i);
                sb2.append(".");
                sb2.append(j);
                sb.append(String.valueOf(sb2.toString()).concat("_"));
            }
        }
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:119:? -> B:35:0x00ac). Please report as a decompilation issue!!! */
    @Override // com.google.android.gms.internal.zzahs
    public final void zza() throws Throwable {
        Object obj;
        for (zzvp zzvpVar : this.zza.zzc.zza) {
            String str = zzvpVar.zzj;
            for (String string : zzvpVar.zzc) {
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(string) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(string)) {
                    try {
                        string = new JSONObject(str).getString("class_name");
                    } catch (JSONException e) {
                        zzahw.zzb("Unable to determine custom event class name, skipping...", e);
                    }
                }
                String str2 = string;
                Object obj2 = this.zzf;
                synchronized (obj2) {
                    try {
                        zzafy zzafyVarZza = this.zzg.zza(str2);
                        if (zzafyVarZza == null || zzafyVarZza.zzb() == null || zzafyVarZza.zza() == null) {
                            obj = obj2;
                            this.zzd.add(new zzafq().zzb(zzvpVar.zzd).zza(str2).zza(0L).zza(7).zza());
                        } else {
                            obj = obj2;
                            try {
                                this.zzc.add(new zzafl(this.zzb, str2, str, zzvpVar, this.zza, zzafyVarZza, this, this.zzh));
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        obj = obj2;
                        throw th;
                    }
                }
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList<zzafl> arrayList = this.zzc;
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            zzafl zzaflVar = arrayList.get(i2);
            i2++;
            zzafl zzaflVar2 = zzaflVar;
            if (hashSet.add(zzaflVar2.zza)) {
                zzaflVar2.zzd();
            }
        }
        ArrayList<zzafl> arrayList2 = this.zzc;
        int size2 = arrayList2.size();
        while (i < size2) {
            zzafl zzaflVar3 = arrayList2.get(i);
            i++;
            zzafl zzaflVar4 = zzaflVar3;
            try {
                try {
                    zzaflVar4.zzd().get();
                    synchronized (this.zzf) {
                        if (!TextUtils.isEmpty(zzaflVar4.zza)) {
                            this.zzd.add(zzaflVar4.zze());
                        }
                    }
                    synchronized (this.zzf) {
                        if (this.zze.contains(zzaflVar4.zza)) {
                            final zzahd zzahdVarZza = zza(-2, zzaflVar4.zza, zzaflVar4.zzf());
                            zzako.zza.post(new Runnable(this, zzahdVarZza) { // from class: com.google.android.gms.internal.zzafw
                                private final zzafv zza;
                                private final zzahd zzb;

                                {
                                    this.zza = this;
                                    this.zzb = zzahdVarZza;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.zza.zzb(this.zzb);
                                }
                            });
                            return;
                        }
                    }
                } catch (Throwable th3) {
                    synchronized (this.zzf) {
                        if (!TextUtils.isEmpty(zzaflVar4.zza)) {
                            this.zzd.add(zzaflVar4.zze());
                        }
                        throw th3;
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                synchronized (this.zzf) {
                    if (!TextUtils.isEmpty(zzaflVar4.zza)) {
                        this.zzd.add(zzaflVar4.zze());
                    }
                    final zzahd zzahdVarZza2 = zza(3, null, null);
                    zzako.zza.post(new Runnable(this, zzahdVarZza2) { // from class: com.google.android.gms.internal.zzafx
                        private final zzafv zza;
                        private final zzahd zzb;

                        {
                            this.zza = this;
                            this.zzb = zzahdVarZza2;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zza.zza(this.zzb);
                        }
                    });
                }
            } catch (Exception e2) {
                zzahw.zzc("Unable to resolve rewarded adapter.", e2);
                synchronized (this.zzf) {
                    if (!TextUtils.isEmpty(zzaflVar4.zza)) {
                        this.zzd.add(zzaflVar4.zze());
                    }
                }
            }
        }
        final zzahd zzahdVarZza3 = zza(3, null, null);
        zzako.zza.post(new Runnable(this, zzahdVarZza3) { // from class: com.google.android.gms.internal.zzafx
            private final zzafv zza;
            private final zzahd zzb;

            {
                this.zza = this;
                this.zzb = zzahdVarZza3;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb);
            }
        });
    }

    final /* synthetic */ void zza(zzahd zzahdVar) {
        this.zzg.zza().zzb(zzahdVar);
    }

    @Override // com.google.android.gms.internal.zzafu
    public final void zza(String str) {
        synchronized (this.zzf) {
            this.zze.add(str);
        }
    }

    @Override // com.google.android.gms.internal.zzafu
    public final void zza(String str, int i) {
    }

    final /* synthetic */ void zzb(zzahd zzahdVar) {
        this.zzg.zza().zzb(zzahdVar);
    }
}
