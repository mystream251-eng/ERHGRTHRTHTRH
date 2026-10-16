package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import com.google.android.exoplayer2.C;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzapz extends MutableContextWrapper {
    private Activity zza;
    private Context zzb;
    private Context zzc;

    public zzapz(Context context) {
        super(context);
        setBaseContext(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        return this.zzc.getSystemService(str);
    }

    @Override // android.content.MutableContextWrapper
    public final void setBaseContext(Context context) {
        this.zzb = context.getApplicationContext();
        this.zza = context instanceof Activity ? (Activity) context : null;
        this.zzc = context;
        super.setBaseContext(this.zzb);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void startActivity(Intent intent) {
        Activity activity = this.zza;
        if (activity != null) {
            activity.startActivity(intent);
        } else {
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            this.zzb.startActivity(intent);
        }
    }

    public final Activity zza() {
        return this.zza;
    }

    public final Context zzb() {
        return this.zzc;
    }
}
