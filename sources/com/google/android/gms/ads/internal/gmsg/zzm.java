package com.google.android.gms.ads.internal.gmsg;

import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.internal.zzaof;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzm implements zzt<zzaof> {
    zzm() {
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzaofVar.zzc(LocalSearchProvider.LIVE_CONTENTS.equals(map.get("custom_close")));
    }
}
