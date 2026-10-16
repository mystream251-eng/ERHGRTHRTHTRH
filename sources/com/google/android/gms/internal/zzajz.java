package com.google.android.gms.internal;

import com.viaccessorca.common.VOUtils;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzajz extends zzr<zzp> {
    private final zzamd<zzp> zza;
    private final Map<String, String> zzb;
    private final zzaks zzc;

    public zzajz(String str, zzamd<zzp> zzamdVar) {
        this(str, null, zzamdVar);
    }

    private zzajz(String str, Map<String, String> map, zzamd<zzp> zzamdVar) {
        super(0, str, new zzaka(zzamdVar));
        this.zzb = null;
        this.zza = zzamdVar;
        zzaks zzaksVar = new zzaks();
        this.zzc = zzaksVar;
        zzaksVar.zza(str, VOUtils.HTTP_GET, null, null);
    }

    protected final zzx<zzp> zza(zzp zzpVar) {
        return zzx.zza(zzpVar, zzap.zza(zzpVar));
    }

    protected final /* synthetic */ void zza(Object obj) {
        zzp zzpVar = (zzp) obj;
        this.zzc.zza(zzpVar.zzc, zzpVar.zza);
        zzaks zzaksVar = this.zzc;
        byte[] bArr = zzpVar.zzb;
        if (zzaks.zzc() && bArr != null) {
            zzaksVar.zza(bArr);
        }
        this.zza.zzb(zzpVar);
    }
}
