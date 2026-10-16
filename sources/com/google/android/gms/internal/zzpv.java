package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface zzpv {
    View zza(View.OnClickListener onClickListener, boolean z);

    void zza(MotionEvent motionEvent);

    void zza(View view);

    void zza(View view, zzpt zzptVar);

    void zza(View view, String str, Bundle bundle, Map<String, WeakReference<View>> map, View view2);

    void zza(View view, Map<String, WeakReference<View>> map);

    void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2);

    boolean zza();

    boolean zza(Bundle bundle);

    void zzb(Bundle bundle);

    void zzb(View view);

    void zzb(View view, Map<String, WeakReference<View>> map);

    boolean zzb();

    void zzc(Bundle bundle);

    void zzc(View view, Map<String, WeakReference<View>> map);

    void zzf();

    void zzg();

    View zzh();

    Context zzi();
}
