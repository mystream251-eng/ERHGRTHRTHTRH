package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaoq extends FrameLayout implements zzaof {
    private final zzaof zza;
    private final zzane zzb;

    public zzaoq(zzaof zzaofVar) {
        super(zzaofVar.getContext());
        this.zza = zzaofVar;
        this.zzb = new zzane(zzaofVar.zzq(), this, this);
        zzapu zzapuVarZzv = this.zza.zzv();
        if (zzapuVarZzv != null) {
            zzapuVarZzv.zza(this);
        }
        addView(this.zza.zzm());
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void destroy() {
        this.zza.destroy();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void loadData(String str, String str2, String str3) {
        this.zza.loadData(str, str2, str3);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zza.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void loadUrl(String str) {
        this.zza.loadUrl(str);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void onPause() {
        this.zzb.zzb();
        this.zza.onPause();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void onResume() {
        this.zza.onResume();
    }

    @Override // android.view.View, com.google.android.gms.internal.zzaof
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zza.setOnClickListener(onClickListener);
    }

    @Override // android.view.View, com.google.android.gms.internal.zzaof
    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.zza.setOnTouchListener(onTouchListener);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zza.setWebChromeClient(webChromeClient);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void setWebViewClient(WebViewClient webViewClient) {
        this.zza.setWebViewClient(webViewClient);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void stopLoading() {
        this.zza.stopLoading();
    }

    @Override // com.google.android.gms.internal.zzann
    public final zzane zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(int i) {
        this.zza.zza(i);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(Context context) {
        this.zza.zza(context);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(zzc zzcVar) {
        this.zza.zza(zzcVar);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(zzd zzdVar) {
        this.zza.zza(zzdVar);
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final void zza(zzaou zzaouVar) {
        this.zza.zza(zzaouVar);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(zzaqa zzaqaVar) {
        this.zza.zza(zzaqaVar);
    }

    @Override // com.google.android.gms.internal.zzgv
    public final void zza(zzgu zzguVar) {
        this.zza.zza(zzguVar);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(zzpt zzptVar) {
        this.zza.zza(zzptVar);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(String str, zzt<? super zzaof> zztVar) {
        this.zza.zza(str, zztVar);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zza(String str, String str2, String str3) {
        this.zza.zza(str, str2, str3);
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.ads.internal.js.zza
    public final void zza(String str, Map<String, ?> map) {
        this.zza.zza(str, map);
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.ads.internal.js.zza
    public final void zza(String str, JSONObject jSONObject) {
        this.zza.zza(str, jSONObject);
    }

    @Override // com.google.android.gms.internal.zzann
    public final void zza(boolean z) {
        this.zza.zza(z);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i) {
        this.zza.zza(z, i);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i, String str) {
        this.zza.zza(z, i, str);
    }

    @Override // com.google.android.gms.internal.zzapo
    public final void zza(boolean z, int i, String str, String str2) {
        this.zza.zza(z, i, str, str2);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final boolean zzaa() {
        return this.zza.zzaa();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzab() {
        this.zzb.zzc();
        this.zza.zzab();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final boolean zzac() {
        return this.zza.zzac();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final boolean zzad() {
        return this.zza.zzad();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final boolean zzae() {
        return this.zza.zzae();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzaf() {
        this.zza.zzaf();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzag() {
        this.zza.zzag();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final View.OnClickListener zzah() {
        return this.zza.zzah();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final zzpt zzai() {
        return this.zza.zzai();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzaj() {
        setBackgroundColor(0);
        this.zza.setBackgroundColor(0);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzak() {
        TextView textView = new TextView(getContext());
        Resources resourcesZzh = zzbt.zzi().zzh();
        textView.setText(resourcesZzh != null ? resourcesZzh.getString(R.string.s7) : "Test Ad");
        textView.setTextSize(15.0f);
        textView.setTextColor(-1);
        textView.setPadding(5, 0, 5, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-12303292);
        gradientDrawable.setCornerRadius(8.0f);
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        addView(textView, new FrameLayout.LayoutParams(-2, -2, 49));
        bringChildToFront(textView);
    }

    @Override // com.google.android.gms.ads.internal.zzbm
    public final void zzao() {
        this.zza.zzao();
    }

    @Override // com.google.android.gms.ads.internal.zzbm
    public final void zzap() {
        this.zza.zzap();
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final zzaou zzb() {
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzb(int i) {
        this.zza.zzb(i);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzb(zzd zzdVar) {
        this.zza.zzb(zzdVar);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzb(String str, zzt<? super zzaof> zztVar) {
        this.zza.zzb(str, zztVar);
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.ads.internal.js.zzm
    public final void zzb(String str, JSONObject jSONObject) {
        this.zza.zzb(str, jSONObject);
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzb(boolean z) {
        this.zza.zzb(z);
    }

    @Override // com.google.android.gms.internal.zzann
    public final zzot zzc() {
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzc(boolean z) {
        this.zza.zzc(z);
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzapa
    public final Activity zzd() {
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzd(boolean z) {
        this.zza.zzd(z);
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final zzv zze() {
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zze(boolean z) {
        this.zza.zze(z);
    }

    @Override // com.google.android.gms.internal.zzann
    public final void zzf() {
        this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.zzann
    public final String zzg() {
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann
    public final zzou zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzann, com.google.android.gms.internal.zzaps
    public final zzala zzi() {
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.internal.zzann
    public final int zzj() {
        return getMeasuredHeight();
    }

    @Override // com.google.android.gms.internal.zzann
    public final int zzk() {
        return getMeasuredWidth();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final WebView zzl() {
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapt
    public final View zzm() {
        return this;
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzn() {
        this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzo() {
        this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final void zzp() {
        this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final Context zzq() {
        return this.zza.zzq();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final zzd zzr() {
        return this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final zzd zzs() {
        return this.zza.zzs();
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapq
    public final zzaqa zzt() {
        return this.zza.zzt();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final String zzu() {
        return this.zza.zzu();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final zzapu zzv() {
        return this.zza.zzv();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final boolean zzw() {
        return this.zza.zzw();
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapr
    public final zzcv zzx() {
        return this.zza.zzx();
    }

    @Override // com.google.android.gms.internal.zzaof, com.google.android.gms.internal.zzapb
    public final boolean zzy() {
        return this.zza.zzy();
    }

    @Override // com.google.android.gms.internal.zzaof
    public final int zzz() {
        return this.zza.zzz();
    }
}
