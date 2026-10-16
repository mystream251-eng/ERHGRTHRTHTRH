package com.google.android.gms.internal;

import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzajw extends zzav {
    private /* synthetic */ byte[] zza;
    private /* synthetic */ Map zzb;
    private /* synthetic */ zzaks zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzajw(zzajr zzajrVar, int i, String str, zzz zzzVar, zzy zzyVar, byte[] bArr, Map map, zzaks zzaksVar) {
        super(i, str, zzzVar, zzyVar);
        this.zza = bArr;
        this.zzb = map;
        this.zzc = zzaksVar;
    }

    protected final /* synthetic */ void zza(Object obj) {
        zzc((String) obj);
    }

    protected final void zzc(String str) {
        this.zzc.zza(str);
        super.zzc(str);
    }

    public final Map<String, String> zzf() throws zza {
        Map<String, String> map = this.zzb;
        return map == null ? super.zzf() : map;
    }

    public final byte[] zzg() throws zza {
        byte[] bArr = this.zza;
        return bArr == null ? super.zzg() : bArr;
    }
}
