package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.js.zza;
import com.google.android.gms.ads.internal.js.zzm;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzv;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public interface zzaof extends zza, zzm, zzbm, zzann, zzapa, zzapb, zzapo, zzapr, zzaps, zzapt, zzgv {
    void destroy();

    @Override // com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzapa
    Context getContext();

    int getHeight();

    ViewGroup.LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    ViewParent getParent();

    int getWidth();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5);

    void loadUrl(String str);

    void measure(int i, int i2);

    void onPause();

    void onResume();

    @Override // com.google.android.gms.internal.zzann
    void setBackgroundColor(int i);

    void setOnClickListener(View.OnClickListener onClickListener);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void stopLoading();

    void zza(int i);

    void zza(Context context);

    void zza(zzd zzdVar);

    @Override // com.google.android.gms.internal.zzann
    void zza(zzaou zzaouVar);

    void zza(zzaqa zzaqaVar);

    void zza(zzpt zzptVar);

    void zza(String str);

    void zza(String str, zzt<? super zzaof> zztVar);

    void zza(String str, String str2, String str3);

    @Override // com.google.android.gms.ads.internal.js.zza
    void zza(String str, Map<String, ?> map);

    @Override // com.google.android.gms.ads.internal.js.zza
    void zza(String str, JSONObject jSONObject);

    boolean zzaa();

    void zzab();

    boolean zzac();

    boolean zzad();

    boolean zzae();

    void zzaf();

    void zzag();

    View.OnClickListener zzah();

    zzpt zzai();

    void zzaj();

    void zzak();

    @Override // com.google.android.gms.internal.zzann
    zzaou zzb();

    void zzb(int i);

    void zzb(zzd zzdVar);

    void zzb(String str, zzt<? super zzaof> zztVar);

    @Override // com.google.android.gms.ads.internal.js.zzm
    void zzb(String str, JSONObject jSONObject);

    void zzb(boolean z);

    void zzc(boolean z);

    @Override // com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzapa
    Activity zzd();

    void zzd(boolean z);

    @Override // com.google.android.gms.internal.zzann
    zzv zze();

    void zze(boolean z);

    @Override // com.google.android.gms.internal.zzann
    zzou zzh();

    @Override // com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzaps
    zzala zzi();

    WebView zzl();

    @Override // com.google.android.gms.internal.zzapt
    View zzm();

    void zzn();

    void zzo();

    void zzp();

    Context zzq();

    zzd zzr();

    zzd zzs();

    @Override // com.google.android.gms.internal.zzapq
    zzaqa zzt();

    String zzu();

    zzapu zzv();

    boolean zzw();

    @Override // com.google.android.gms.internal.zzapr
    zzcv zzx();

    @Override // com.google.android.gms.internal.zzapb
    boolean zzy();

    int zzz();
}
