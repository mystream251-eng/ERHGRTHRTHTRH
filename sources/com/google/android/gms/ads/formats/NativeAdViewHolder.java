package com.google.android.gms.ads.formats;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzrb;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class NativeAdViewHolder {
    public static WeakHashMap<View, NativeAdViewHolder> zza = new WeakHashMap<>();
    private zzrb zzb;
    private WeakReference<View> zzc;

    public NativeAdViewHolder(View view, Map<String, View> map, Map<String, View> map2) {
        zzbq.zza(view, "ContainerView must not be null");
        if (view instanceof NativeAdView) {
            zzaky.zzc("The provided containerView is of type NativeAdView. NativeAdView objects should not be used with NativeAdViewHolder.");
            return;
        }
        if (zza.get(view) != null) {
            zzaky.zzc("The provided containerView is already in use with another NativeAdViewHolder.");
            return;
        }
        zza.put(view, this);
        this.zzc = new WeakReference<>(view);
        this.zzb = zzlc.zzb().zza(view, zza(map), zza(map2));
    }

    private static HashMap<String, View> zza(Map<String, View> map) {
        return map == null ? new HashMap<>() : new HashMap<>(map);
    }

    public final void setNativeAd(NativeAd nativeAd) {
        WeakReference<View> weakReference = this.zzc;
        View view = weakReference != null ? weakReference.get() : null;
        if (view == null) {
            zzaky.zze("NativeAdViewHolder.setNativeAd containerView doesn't exist, returning");
            return;
        }
        if (!zza.containsKey(view)) {
            zza.put(view, this);
        }
        zzrb zzrbVar = this.zzb;
        if (zzrbVar != null) {
            try {
                zzrbVar.zza((IObjectWrapper) nativeAd.zza());
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call setNativeAd on delegate", e);
            }
        }
    }

    public final void unregisterNativeAd() {
        zzrb zzrbVar = this.zzb;
        if (zzrbVar != null) {
            try {
                zzrbVar.zza();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call unregisterNativeAd on delegate", e);
            }
        }
        WeakReference<View> weakReference = this.zzc;
        View view = weakReference != null ? weakReference.get() : null;
        if (view != null) {
            zza.remove(view);
        }
    }
}
