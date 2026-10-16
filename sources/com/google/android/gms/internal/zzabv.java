package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzabv {
    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzb(Context context, boolean z) {
        if (!z) {
            return true;
        }
        int iZzb = DynamiteModule.zzb(context, ModuleDescriptor.MODULE_ID);
        return iZzb != 0 && iZzb <= DynamiteModule.zza(context, ModuleDescriptor.MODULE_ID);
    }
}
