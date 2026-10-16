package com.google.android.gms.internal;

import android.text.TextUtils;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzaos implements zzt<zzaof> {
    private /* synthetic */ zzaor zza;

    zzaos(zzaor zzaorVar) {
        this.zza = zzaorVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        if (map != null) {
            String str = (String) map.get(ToolboxAnalyticsModuleKt.M_HEIGHT);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                int i = Integer.parseInt(str);
                synchronized (this.zza) {
                    if (this.zza.zzz != i) {
                        this.zza.zzz = i;
                        this.zza.requestLayout();
                    }
                }
            } catch (Exception e) {
                zzahw.zzc("Exception occurred while getting webview content height", e);
            }
        }
    }
}
