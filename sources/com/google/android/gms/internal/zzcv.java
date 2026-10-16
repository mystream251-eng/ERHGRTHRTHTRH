package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcv {
    private static final String[] zze = {"/aclk", "/pcs/click"};
    private String zza = "googleads.g.doubleclick.net";
    private String zzb = "/pagead/ads";
    private String zzc = "ad.doubleclick.net";
    private String[] zzd = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzcr zzf;

    public zzcv(zzcr zzcrVar) {
        this.zzf = zzcrVar;
    }

    private final Uri zza(Uri uri, Context context, String str, boolean z, View view, Activity activity) throws zzcw {
        try {
            boolean zZzc = zzc(uri);
            if (zZzc) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzcw("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzcw("Query parameter already exists: ms");
            }
            String strZza = z ? this.zzf.zza(context, str, view, activity) : this.zzf.zza(context);
            if (!zZzc) {
                String string = uri.toString();
                int iIndexOf = string.indexOf("&adurl");
                if (iIndexOf == -1) {
                    iIndexOf = string.indexOf("?adurl");
                }
                if (iIndexOf == -1) {
                    return uri.buildUpon().appendQueryParameter("ms", strZza).build();
                }
                int i = iIndexOf + 1;
                return Uri.parse(string.substring(0, i) + "ms=" + strZza + "&" + string.substring(i));
            }
            String string2 = uri.toString();
            int iIndexOf2 = string2.indexOf(";adurl");
            if (iIndexOf2 != -1) {
                int i2 = iIndexOf2 + 1;
                return Uri.parse(string2.substring(0, i2) + "dc_ms=" + strZza + ";" + string2.substring(i2));
            }
            String encodedPath = uri.getEncodedPath();
            int iIndexOf3 = string2.indexOf(encodedPath);
            return Uri.parse(string2.substring(0, encodedPath.length() + iIndexOf3) + ";dc_ms=" + strZza + ";" + string2.substring(iIndexOf3 + encodedPath.length()));
        } catch (UnsupportedOperationException unused) {
            throw new zzcw("Provided Uri is not in a valid state");
        }
    }

    private final boolean zzc(Uri uri) {
        if (uri == null) {
            throw null;
        }
        try {
            return uri.getHost().equals(this.zzc);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final Uri zza(Uri uri, Context context) throws zzcw {
        return zza(uri, context, null, false, null, null);
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzcw {
        try {
            return zza(uri, context, uri.getQueryParameter("ai"), true, view, activity);
        } catch (UnsupportedOperationException unused) {
            throw new zzcw("Provided Uri is not in a valid state");
        }
    }

    public final zzcr zza() {
        return this.zzf;
    }

    public final void zza(MotionEvent motionEvent) {
        this.zzf.zza(motionEvent);
    }

    public final boolean zza(Uri uri) {
        if (uri == null) {
            throw null;
        }
        try {
            String host = uri.getHost();
            for (String str : this.zzd) {
                if (host.endsWith(str)) {
                    return true;
                }
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }

    public final boolean zzb(Uri uri) {
        if (zza(uri)) {
            for (String str : zze) {
                if (uri.getPath().endsWith(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
