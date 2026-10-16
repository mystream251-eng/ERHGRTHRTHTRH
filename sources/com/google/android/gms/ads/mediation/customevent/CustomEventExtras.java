package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class CustomEventExtras implements NetworkExtras {
    private final HashMap<String, Object> zza = new HashMap<>();

    public final Object getExtra(String str) {
        return this.zza.get(str);
    }

    public final void setExtra(String str, Object obj) {
        this.zza.put(str, obj);
    }
}
