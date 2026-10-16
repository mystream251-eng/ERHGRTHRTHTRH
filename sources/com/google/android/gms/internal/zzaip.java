package com.google.android.gms.internal;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Process;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzaip {
    private zzaip() {
    }

    public static boolean zza(zzaof zzaofVar) {
        if (zzaofVar == null) {
            return false;
        }
        zzaofVar.onPause();
        return true;
    }

    public static boolean zzb(zzaof zzaofVar) {
        if (zzaofVar == null) {
            return false;
        }
        zzaofVar.onResume();
        return true;
    }

    public static boolean zze() {
        int iMyUid = Process.myUid();
        return iMyUid == 0 || iMyUid == 1000;
    }

    public int zza() {
        return 0;
    }

    public Drawable zza(Context context, Bitmap bitmap, boolean z, float f) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public zzaog zza(zzaof zzaofVar, boolean z) {
        return new zzaog(zzaofVar, z);
    }

    public String zza(Context context) {
        return "";
    }

    public String zza(SslError sslError) {
        return "";
    }

    public Set<String> zza(Uri uri) {
        String encodedQuery;
        if (!uri.isOpaque() && (encodedQuery = uri.getEncodedQuery()) != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            do {
                int iIndexOf = encodedQuery.indexOf(38, i);
                if (iIndexOf == -1) {
                    iIndexOf = encodedQuery.length();
                }
                int iIndexOf2 = encodedQuery.indexOf(61, i);
                if (iIndexOf2 > iIndexOf || iIndexOf2 == -1) {
                    iIndexOf2 = iIndexOf;
                }
                linkedHashSet.add(Uri.decode(encodedQuery.substring(i, iIndexOf2)));
                i = iIndexOf + 1;
            } while (i < encodedQuery.length());
            return Collections.unmodifiableSet(linkedHashSet);
        }
        return Collections.emptySet();
    }

    public void zza(View view, Drawable drawable) {
        view.setBackgroundDrawable(drawable);
    }

    public void zza(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
    }

    public boolean zza(DownloadManager.Request request) {
        return false;
    }

    public boolean zza(Context context, WebSettings webSettings) {
        return false;
    }

    public boolean zza(View view) {
        return (view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true;
    }

    public boolean zza(Window window) {
        return false;
    }

    public int zzb() {
        return 1;
    }

    public void zzb(Context context) {
        zzbt.zzi().zzi();
    }

    public boolean zzb(View view) {
        return false;
    }

    public int zzc() {
        return 5;
    }

    public CookieManager zzc(Context context) {
        if (zze()) {
            return null;
        }
        try {
            CookieSyncManager.createInstance(context);
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zzahw.zzb("Failed to obtain CookieManager.", th);
            zzbt.zzi().zza(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public WebChromeClient zzc(zzaof zzaofVar) {
        return null;
    }

    public boolean zzc(View view) {
        return false;
    }

    public ViewGroup.LayoutParams zzd() {
        return new ViewGroup.LayoutParams(-2, -2);
    }

    public int zzf() {
        return 0;
    }
}
