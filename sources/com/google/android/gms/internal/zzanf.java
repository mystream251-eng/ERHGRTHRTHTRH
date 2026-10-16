package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzanf extends zzana {
    @Override // com.google.android.gms.internal.zzana
    public final zzamz zza(Context context, zzann zzannVar, int i, boolean z, zzov zzovVar, zzanm zzanmVar) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null || applicationInfo.targetSdkVersion >= 11) {
            return new zzamp(context, z, zzannVar.zzt().zzd(), zzanmVar, new zzano(context, zzannVar.zzi(), zzannVar.zzg(), zzovVar, zzannVar.zzc()));
        }
        return null;
    }
}
