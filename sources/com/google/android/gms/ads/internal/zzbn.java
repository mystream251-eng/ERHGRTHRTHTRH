package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzafc;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzcw;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzms;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzyx;
import com.google.android.gms.internal.zzzd;
import java.util.Map;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzbn extends zzlu {
    private final zzala zza;
    private final zzko zzb;
    private final Future<zzcv> zzc = zzaid.zza(zzaid.zza, new zzbq(this));
    private final Context zzd;
    private final zzbs zze;
    private WebView zzf;
    private zzli zzg;
    private zzcv zzh;
    private AsyncTask<Void, Void, String> zzi;

    public zzbn(Context context, zzko zzkoVar, String str, zzala zzalaVar) {
        this.zzd = context;
        this.zza = zzalaVar;
        this.zzb = zzkoVar;
        this.zzf = new WebView(this.zzd);
        this.zze = new zzbs(str);
        zza(0);
        this.zzf.setVerticalScrollBarEnabled(false);
        this.zzf.getSettings().setJavaScriptEnabled(true);
        this.zzf.setWebViewClient(new zzbo(this));
        this.zzf.setOnTouchListener(new zzbp(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzc(String str) {
        if (this.zzh == null) {
            return str;
        }
        Uri uriZza = Uri.parse(str);
        try {
            uriZza = this.zzh.zza(uriZza, this.zzd, null, null);
        } catch (zzcw e) {
            zzahw.zzc("Unable to process ad data", e);
        }
        return uriZza.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.zzd.startActivity(intent);
    }

    final String zza() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https://").appendEncodedPath((String) zzlc.zzf().zza(zzoi.zzfn));
        builder.appendQueryParameter(SearchIntents.EXTRA_QUERY, this.zze.zzb());
        builder.appendQueryParameter("pubId", this.zze.zzc());
        Map<String, String> mapZzd = this.zze.zzd();
        for (String str : mapZzd.keySet()) {
            builder.appendQueryParameter(str, mapZzd.get(str));
        }
        Uri uriBuild = builder.build();
        zzcv zzcvVar = this.zzh;
        if (zzcvVar != null) {
            try {
                uriBuild = zzcvVar.zza(uriBuild, this.zzd);
            } catch (zzcw e) {
                zzahw.zzc("Unable to process ad data", e);
            }
        }
        String strZzf = zzf();
        String encodedQuery = uriBuild.getEncodedQuery();
        StringBuilder sb = new StringBuilder(String.valueOf(strZzf).length() + 1 + String.valueOf(encodedQuery).length());
        sb.append(strZzf);
        sb.append("#");
        sb.append(encodedQuery);
        return sb.toString();
    }

    final void zza(int i) {
        if (this.zzf == null) {
            return;
        }
        this.zzf.setLayoutParams(new ViewGroup.LayoutParams(-1, i));
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzafc zzafcVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzko zzkoVar) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzlf zzlfVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzli zzliVar) throws RemoteException {
        this.zzg = zzliVar;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzly zzlyVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzme zzmeVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzms zzmsVar) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzns zznsVar) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzpb zzpbVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzyx zzyxVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(zzzd zzzdVar, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zza(boolean z) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzal() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzam() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzan() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    final int zzb(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter(ToolboxAnalyticsModuleKt.M_HEIGHT);
        if (TextUtils.isEmpty(queryParameter)) {
            return 0;
        }
        try {
            zzlc.zza();
            return zzako.zza(this.zzd, Integer.parseInt(queryParameter));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzb() throws RemoteException {
        com.google.android.gms.common.internal.zzbq.zzb("destroy must be called on the main UI thread.");
        this.zzi.cancel(true);
        this.zzc.cancel(true);
        this.zzf.destroy();
        this.zzf = null;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzb(boolean z) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zzb(zzkk zzkkVar) throws RemoteException {
        com.google.android.gms.common.internal.zzbq.zza(this.zzf, "This Search Ad has already been torn down");
        this.zze.zza(zzkkVar, this.zza);
        this.zzi = new zzbr(this, null).execute(new Void[0]);
        return true;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final IObjectWrapper zzc() throws RemoteException {
        com.google.android.gms.common.internal.zzbq.zzb("getAdFrame must be called on the main UI thread.");
        return com.google.android.gms.dynamic.zzn.zza(this.zzf);
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzko zzd() throws RemoteException {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zze() throws RemoteException {
        return false;
    }

    final String zzf() {
        String strZza = this.zze.zza();
        if (TextUtils.isEmpty(strZza)) {
            strZza = "www.google.com";
        }
        String str = (String) zzlc.zzf().zza(zzoi.zzfn);
        StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 8 + String.valueOf(str).length());
        sb.append("https://");
        sb.append(strZza);
        sb.append(str);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzg() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzh() throws RemoteException {
        com.google.android.gms.common.internal.zzbq.zzb("pause must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzi() throws RemoteException {
        com.google.android.gms.common.internal.zzbq.zzb("resume must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final void zzj() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzlt
    public final boolean zzk() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzmm zzl() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzlt
    public final String zzv() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzly zzw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    @Override // com.google.android.gms.internal.zzlt
    public final zzli zzx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }
}
