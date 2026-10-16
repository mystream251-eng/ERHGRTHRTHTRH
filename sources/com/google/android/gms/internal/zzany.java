package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.api.Releasable;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzany implements Releasable {
    protected Context zza;
    private String zzb;
    private WeakReference<zzann> zzc;

    public zzany(zzann zzannVar) {
        this.zza = zzannVar.getContext();
        this.zzb = zzbt.zze().zza(this.zza, zzannVar.zzi().zza);
        this.zzc = new WeakReference<>(zzannVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str, Map<String, String> map) {
        zzann zzannVar = this.zzc.get();
        if (zzannVar != null) {
            zzannVar.zza(str, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:35:0x006f  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String zzb(String str) {
        byte b;
        switch (str.hashCode()) {
            case -1396664534:
                if (!str.equals("badUrl")) {
                    b = -1;
                } else {
                    b = 6;
                }
                break;
            case -1347010958:
                if (!str.equals("inProgress")) {
                    b = -1;
                } else {
                    b = 2;
                }
                break;
            case -918817863:
                if (!str.equals("downloadTimeout")) {
                    b = -1;
                } else {
                    b = 7;
                }
                break;
            case -659376217:
                if (!str.equals("contentLengthMissing")) {
                    b = -1;
                } else {
                    b = 3;
                }
                break;
            case -642208130:
                if (!str.equals("playerFailed")) {
                    b = -1;
                } else {
                    b = 1;
                }
                break;
            case -354048396:
                if (!str.equals("sizeExceeded")) {
                    b = -1;
                } else {
                    b = 8;
                }
                break;
            case -32082395:
                if (!str.equals("externalAbort")) {
                    b = -1;
                } else {
                    b = 9;
                }
                break;
            case 96784904:
                if (!str.equals("error")) {
                    b = -1;
                } else {
                    b = 0;
                }
                break;
            case 580119100:
                if (!str.equals("expireFailed")) {
                    b = -1;
                } else {
                    b = 5;
                }
                break;
            case 725497484:
                if (!str.equals("noCacheDir")) {
                    b = -1;
                } else {
                    b = 4;
                }
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
            default:
                return "internal";
            case 4:
            case 5:
                return "io";
            case 6:
            case 7:
                return "network";
            case 8:
            case 9:
                return "policy";
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
    }

    public abstract void zza();

    protected final void zza(String str, String str2, int i) {
        zzako.zza.post(new zzaoa(this, str, str2, i));
    }

    public final void zza(String str, String str2, String str3, String str4) {
        zzako.zza.post(new zzaob(this, str, str2, str3, str4));
    }

    public abstract boolean zza(String str);
}
