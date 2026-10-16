package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzov;
import com.viaccessorca.analytics.fma.FmaDatabaseHelper;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzc implements zzt<zzaof> {
    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzaof zzaofVar2 = zzaofVar;
        String str = (String) map.get("action");
        if ("tick".equals(str)) {
            String str2 = (String) map.get("label");
            String str3 = (String) map.get("start_label");
            String str4 = (String) map.get(FmaDatabaseHelper.CONST_TIMESTAMP);
            if (TextUtils.isEmpty(str2)) {
                zzahw.zze("No label given for CSI tick.");
                return;
            }
            if (TextUtils.isEmpty(str4)) {
                zzahw.zze("No timestamp given for CSI tick.");
                return;
            }
            try {
                long jZzb = zzbt.zzl().zzb() + (Long.parseLong(str4) - zzbt.zzl().zza());
                if (TextUtils.isEmpty(str3)) {
                    str3 = "native:view_load";
                }
                zzaofVar2.zzh().zza(str2, str3, jZzb);
                return;
            } catch (NumberFormatException e) {
                zzahw.zzc("Malformed timestamp for CSI tick.", e);
                return;
            }
        }
        if ("experiment".equals(str)) {
            String str5 = (String) map.get("value");
            if (TextUtils.isEmpty(str5)) {
                zzahw.zze("No value given for CSI experiment.");
                return;
            }
            zzov zzovVarZza = zzaofVar2.zzh().zza();
            if (zzovVarZza == null) {
                zzahw.zze("No ticker for WebView, dropping experiment ID.");
                return;
            } else {
                zzovVarZza.zza("e", str5);
                return;
            }
        }
        if ("extra".equals(str)) {
            String str6 = (String) map.get("name");
            String str7 = (String) map.get("value");
            if (TextUtils.isEmpty(str7)) {
                zzahw.zze("No value given for CSI extra.");
                return;
            }
            if (TextUtils.isEmpty(str6)) {
                zzahw.zze("No name given for CSI extra.");
                return;
            }
            zzov zzovVarZza2 = zzaofVar2.zzh().zza();
            if (zzovVarZza2 == null) {
                zzahw.zze("No ticker for WebView, dropping extra parameter.");
            } else {
                zzovVarZza2.zza(str6, str7);
            }
        }
    }
}
