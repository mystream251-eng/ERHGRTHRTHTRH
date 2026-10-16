package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzs;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzhm extends Thread {
    private final zzhh zze;
    private final zzabf zzf;
    private boolean zza = false;
    private boolean zzb = false;
    private boolean zzc = false;
    private final Object zzd = new Object();
    private final int zzh = ((Integer) zzlc.zzf().zza(zzoi.zzan)).intValue();
    private final int zzi = ((Integer) zzlc.zzf().zza(zzoi.zzao)).intValue();
    private final int zzj = ((Integer) zzlc.zzf().zza(zzoi.zzap)).intValue();
    private final int zzk = ((Integer) zzlc.zzf().zza(zzoi.zzaq)).intValue();
    private final int zzl = ((Integer) zzlc.zzf().zza(zzoi.zzat)).intValue();
    private final int zzm = ((Integer) zzlc.zzf().zza(zzoi.zzav)).intValue();
    private final int zzn = ((Integer) zzlc.zzf().zza(zzoi.zzaw)).intValue();
    private final int zzg = ((Integer) zzlc.zzf().zza(zzoi.zzar)).intValue();
    private final String zzo = (String) zzlc.zzf().zza(zzoi.zzay);
    private final boolean zzp = ((Boolean) zzlc.zzf().zza(zzoi.zzba)).booleanValue();

    public zzhm(zzhh zzhhVar, zzabf zzabfVar) {
        this.zze = zzhhVar;
        this.zzf = zzabfVar;
        setName("ContentFetchTask");
    }

    private final zzhq zza(View view, zzhg zzhgVar) {
        boolean z;
        if (view == null) {
            return new zzhq(this, 0, 0);
        }
        Context contextZzb = zzbt.zzh().zzb();
        if (contextZzb != null) {
            String str = (String) view.getTag(contextZzb.getResources().getIdentifier((String) zzlc.zzf().zza(zzoi.zzax), "id", contextZzb.getPackageName()));
            if (!TextUtils.isEmpty(this.zzo) && str != null && str.equals(this.zzo)) {
                return new zzhq(this, 0, 0);
            }
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zzhq(this, 0, 0);
            }
            zzhgVar.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), view.getWidth(), view.getHeight());
            return new zzhq(this, 1, 0);
        }
        if ((view instanceof WebView) && !(view instanceof zzaof)) {
            zzhgVar.zzg();
            WebView webView = (WebView) view;
            if (zzs.zze()) {
                zzhgVar.zzg();
                webView.post(new zzho(this, zzhgVar, webView, globalVisibleRect));
                z = true;
            } else {
                z = false;
            }
            return z ? new zzhq(this, 0, 1) : new zzhq(this, 0, 0);
        }
        if (!(view instanceof ViewGroup)) {
            return new zzhq(this, 0, 0);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
            zzhq zzhqVarZza = zza(viewGroup.getChildAt(i3), zzhgVar);
            i += zzhqVarZza.zza;
            i2 += zzhqVarZza.zzb;
        }
        return new zzhq(this, i, i2);
    }

    private static boolean zze() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            Context contextZzb = zzbt.zzh().zzb();
            if (contextZzb == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) contextZzb.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) contextZzb.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return false;
                    }
                    PowerManager powerManager = (PowerManager) contextZzb.getSystemService("power");
                    return powerManager == null ? false : powerManager.isScreenOn();
                }
            }
            return false;
        } catch (Throwable th) {
            zzbt.zzi().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    private final void zzf() {
        synchronized (this.zzd) {
            this.zzb = true;
            StringBuilder sb = new StringBuilder(42);
            sb.append("ContentFetchThread: paused, mPause = ");
            sb.append(true);
            zzahw.zzb(sb.toString());
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0091 */
    /* JADX WARN: Code duplicated, block: B:43:0x007f A[EXC_TOP_SPLITTER, LOOP:1: B:43:0x007f->B:50:0x007f, LOOP_START, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:47:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r4 = this;
        L0:
            boolean r0 = zze()     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            if (r0 == 0) goto L59
            com.google.android.gms.internal.zzhi r0 = com.google.android.gms.ads.internal.zzbt.zzh()     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            android.app.Activity r0 = r0.zza()     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            if (r0 != 0) goto L19
            java.lang.String r0 = "ContentFetchThread: no activity. Sleeping."
            com.google.android.gms.internal.zzahw.zzb(r0)     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
        L15:
            r4.zzf()     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            goto L5f
        L19:
            if (r0 == 0) goto L5f
            r1 = 0
            android.view.Window r2 = r0.getWindow()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L76
            if (r2 == 0) goto L4c
            android.view.Window r2 = r0.getWindow()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L76
            android.view.View r2 = r2.getDecorView()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L76
            if (r2 == 0) goto L4c
            android.view.Window r0 = r0.getWindow()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L76
            android.view.View r0 = r0.getDecorView()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L76
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r2)     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L76
            r1 = r0
            goto L4c
        L3d:
            r0 = move-exception
            com.google.android.gms.internal.zzahi r2 = com.google.android.gms.ads.internal.zzbt.zzi()     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            java.lang.String r3 = "ContentFetchTask.extractContent"
            r2.zza(r0, r3)     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            java.lang.String r0 = "Failed getting root view of activity. Content not extracted."
            com.google.android.gms.internal.zzahw.zzb(r0)     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
        L4c:
            if (r1 == 0) goto L5f
            if (r1 == 0) goto L5f
            com.google.android.gms.internal.zzhn r0 = new com.google.android.gms.internal.zzhn     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            r0.<init>(r4, r1)     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            r1.post(r0)     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            goto L5f
        L59:
            java.lang.String r0 = "ContentFetchTask: sleeping"
            com.google.android.gms.internal.zzahw.zzb(r0)     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            goto L15
        L5f:
            int r0 = r4.zzg     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            int r0 = r0 * 1000
            long r0 = (long) r0     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            java.lang.Thread.sleep(r0)     // Catch: java.lang.Exception -> L68 java.lang.InterruptedException -> L76
            goto L7c
        L68:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.zzahw.zzb(r1, r0)
            com.google.android.gms.internal.zzabf r1 = r4.zzf
            java.lang.String r2 = "ContentFetchTask.run"
            r1.zza(r0, r2)
            goto L7c
        L76:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.zzahw.zzb(r1, r0)
        L7c:
            java.lang.Object r0 = r4.zzd
            monitor-enter(r0)
        L7f:
            boolean r1 = r4.zzb     // Catch: java.lang.Throwable -> L91
            if (r1 == 0) goto L8e
            java.lang.String r1 = "ContentFetchTask: waiting"
            com.google.android.gms.internal.zzahw.zzb(r1)     // Catch: java.lang.InterruptedException -> L7f java.lang.Throwable -> L91
            java.lang.Object r1 = r4.zzd     // Catch: java.lang.InterruptedException -> L7f java.lang.Throwable -> L91
            r1.wait()     // Catch: java.lang.InterruptedException -> L7f java.lang.Throwable -> L91
            goto L7f
        L8e:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L91
            goto L0
        L91:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L91
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhm.run():void");
    }

    public final void zza() {
        synchronized (this.zzd) {
            if (this.zza) {
                zzahw.zzb("Content hash thread already started, quiting...");
            } else {
                this.zza = true;
                start();
            }
        }
    }

    final void zza(View view) {
        try {
            zzhg zzhgVar = new zzhg(this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn);
            zzhq zzhqVarZza = zza(view, zzhgVar);
            zzhgVar.zzh();
            if (zzhqVarZza.zza == 0 && zzhqVarZza.zzb == 0) {
                return;
            }
            if (zzhqVarZza.zzb == 0 && zzhgVar.zzj() == 0) {
                return;
            }
            if (zzhqVarZza.zzb == 0 && this.zze.zza(zzhgVar)) {
                return;
            }
            this.zze.zzc(zzhgVar);
        } catch (Exception e) {
            zzahw.zzb("Exception in fetchContentOnUIThread", e);
            this.zzf.zza(e, "ContentFetchTask.fetchContent");
        }
    }

    final void zza(zzhg zzhgVar, WebView webView, String str, boolean z) {
        zzhgVar.zzf();
        try {
            if (!TextUtils.isEmpty(str)) {
                String strOptString = new JSONObject(str).optString("text");
                if (this.zzp || TextUtils.isEmpty(webView.getTitle())) {
                    zzhgVar.zza(strOptString, z, webView.getX(), webView.getY(), webView.getWidth(), webView.getHeight());
                } else {
                    String title = webView.getTitle();
                    StringBuilder sb = new StringBuilder(String.valueOf(title).length() + 1 + String.valueOf(strOptString).length());
                    sb.append(title);
                    sb.append("\n");
                    sb.append(strOptString);
                    zzhgVar.zza(sb.toString(), z, webView.getX(), webView.getY(), webView.getWidth(), webView.getHeight());
                }
            }
            if (zzhgVar.zza()) {
                this.zze.zzb(zzhgVar);
            }
        } catch (JSONException unused) {
            zzahw.zzb("Json string may be malformed.");
        } catch (Throwable th) {
            zzahw.zza("Failed to get webview content.", th);
            this.zzf.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final zzhg zzb() {
        return this.zze.zza();
    }

    public final void zzc() {
        synchronized (this.zzd) {
            this.zzb = false;
            this.zzd.notifyAll();
            zzahw.zzb("ContentFetchThread: wakeup");
        }
    }

    public final boolean zzd() {
        return this.zzb;
    }
}
