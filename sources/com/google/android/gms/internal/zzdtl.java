package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdtl<P> {
    private static final Charset zza = Charset.forName("UTF-8");
    private ConcurrentMap<String, List<zzdtm<P>>> zzb = new ConcurrentHashMap();
    private zzdtm<P> zzc;

    public final zzdtm<P> zza() {
        return this.zzc;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0070  */
    protected final zzdtm<P> zza(P p, zzdwp.zzb zzbVar) throws GeneralSecurityException {
        zzdtm<P> zzdtmVar;
        ByteBuffer byteBufferPut;
        byte[] bArrArray;
        String str;
        List<zzdtm<P>> listPut;
        int i = zzdtc.zza[zzbVar.zzf().ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3) {
                byteBufferPut = ByteBuffer.allocate(5).put((byte) 1);
            } else {
                if (i != 4) {
                    throw new GeneralSecurityException("unknown output prefix type");
                }
                bArrArray = zzdtb.zza;
            }
            zzdtmVar = new zzdtm<>(p, bArrArray, zzbVar.zzd(), zzbVar.zzf());
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzdtmVar);
            str = new String(zzdtmVar.zzb(), zza);
            listPut = this.zzb.put(str, Collections.unmodifiableList(arrayList));
            if (listPut != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(listPut);
                arrayList2.add(zzdtmVar);
                this.zzb.put(str, Collections.unmodifiableList(arrayList2));
            }
            return zzdtmVar;
        }
        byteBufferPut = ByteBuffer.allocate(5).put((byte) 0);
        bArrArray = byteBufferPut.putInt(zzbVar.zze()).array();
        zzdtmVar = new zzdtm<>(p, bArrArray, zzbVar.zzd(), zzbVar.zzf());
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(zzdtmVar);
        str = new String(zzdtmVar.zzb(), zza);
        listPut = this.zzb.put(str, Collections.unmodifiableList(arrayList3));
        if (listPut != null) {
            ArrayList arrayList4 = new ArrayList();
            arrayList4.addAll(listPut);
            arrayList4.add(zzdtmVar);
            this.zzb.put(str, Collections.unmodifiableList(arrayList4));
        }
        return zzdtmVar;
    }

    protected final void zza(zzdtm<P> zzdtmVar) {
        this.zzc = zzdtmVar;
    }
}
