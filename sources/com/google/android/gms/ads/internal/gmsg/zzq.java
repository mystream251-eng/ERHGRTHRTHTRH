package com.google.android.gms.ads.internal.gmsg;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.internal.zzaof;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzq implements zzt<zzaof> {
    zzq() {
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzaof zzaofVar2 = zzaofVar;
        String str = (String) map.get("action");
        if (ToolboxAnalyticsModuleKt.EVENT_PAUSE.equals(str)) {
            zzaofVar2.zzao();
        } else if (ToolboxAnalyticsModuleKt.EVENT_RESUME.equals(str)) {
            zzaofVar2.zzap();
        }
    }
}
