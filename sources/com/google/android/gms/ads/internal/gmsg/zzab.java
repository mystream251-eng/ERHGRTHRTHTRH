package com.google.android.gms.ads.internal.gmsg;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagx;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzapa;
import com.google.android.gms.internal.zzapb;
import com.google.android.gms.internal.zzapo;
import com.google.android.gms.internal.zzapr;
import com.google.android.gms.internal.zzapt;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzcw;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzyd;
import io.sentry.protocol.App;
import java.net.URISyntaxException;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzab<T extends zzapa & zzapb & zzapo & zzapr & zzapt> implements zzt<T> {
    private final Context zza;
    private final zzcv zzb;
    private zzala zzc;
    private com.google.android.gms.ads.internal.overlay.zzt zzd;
    private zzkf zze;
    private com.google.android.gms.ads.internal.overlay.zzn zzf;
    private zzb zzg;
    private com.google.android.gms.ads.internal.zzw zzh;
    private zzyd zzi;
    private zzaof zzj = null;

    public zzab(Context context, zzala zzalaVar, zzcv zzcvVar, com.google.android.gms.ads.internal.overlay.zzt zztVar, zzkf zzkfVar, zzb zzbVar, com.google.android.gms.ads.internal.overlay.zzn zznVar, com.google.android.gms.ads.internal.zzw zzwVar, zzyd zzydVar) {
        this.zza = context;
        this.zzc = zzalaVar;
        this.zzb = zzcvVar;
        this.zzd = zztVar;
        this.zze = zzkfVar;
        this.zzg = zzbVar;
        this.zzh = zzwVar;
        this.zzi = zzydVar;
        this.zzf = zznVar;
    }

    static String zza(Context context, zzcv zzcvVar, String str, View view, Activity activity) {
        if (zzcvVar == null) {
            return str;
        }
        try {
            Uri uriZza = Uri.parse(str);
            if (zzcvVar.zzb(uriZza)) {
                uriZza = zzcvVar.zza(uriZza, context, view, activity);
            }
            return uriZza.toString();
        } catch (zzcw unused) {
            return str;
        } catch (Exception e) {
            zzbt.zzi().zza(e, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            return str;
        }
    }

    private final void zza(boolean z) {
        zzyd zzydVar = this.zzi;
        if (zzydVar != null) {
            zzydVar.zza(z);
        }
    }

    private static boolean zza(Map<String, String> map) {
        return LocalSearchProvider.LIVE_CONTENTS.equals(map.get("custom_close"));
    }

    private static int zzb(Map<String, String> map) {
        String str = map.get("o");
        if (str == null) {
            return -1;
        }
        if (TtmlNode.TAG_P.equalsIgnoreCase(str)) {
            return zzbt.zzg().zzb();
        }
        if ("l".equalsIgnoreCase(str)) {
            return zzbt.zzg().zza();
        }
        if ("c".equalsIgnoreCase(str)) {
            return zzbt.zzg().zzc();
        }
        return -1;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzapa zzapaVar = (zzapa) obj;
        String strZza = zzagx.zza((String) map.get("u"), zzapaVar.getContext());
        String str = (String) map.get("a");
        if (str == null) {
            zzahw.zze("Action missing from an open GMSG.");
            return;
        }
        com.google.android.gms.ads.internal.zzw zzwVar = this.zzh;
        if (zzwVar != null && !zzwVar.zzb()) {
            this.zzh.zza(strZza);
            return;
        }
        if ("expand".equalsIgnoreCase(str)) {
            if (((zzapb) zzapaVar).zzy()) {
                zzahw.zze("Cannot expand WebView that is already expanded.");
                return;
            } else {
                zza(false);
                ((zzapo) zzapaVar).zza(zza((Map<String, String>) map), zzb(map));
                return;
            }
        }
        if ("webapp".equalsIgnoreCase(str)) {
            zza(false);
            zzapo zzapoVar = (zzapo) zzapaVar;
            boolean zZza = zza((Map<String, String>) map);
            if (strZza != null) {
                zzapoVar.zza(zZza, zzb(map), strZza);
                return;
            } else {
                zzapoVar.zza(zZza, zzb(map), (String) map.get("html"), (String) map.get("baseurl"));
                return;
            }
        }
        if (App.TYPE.equalsIgnoreCase(str) && "true".equalsIgnoreCase((String) map.get("system_browser"))) {
            zza(true);
            zzapaVar.getContext();
            if (TextUtils.isEmpty(strZza)) {
                zzahw.zze("Destination url cannot be empty.");
                return;
            }
            try {
                ((zzapo) zzapaVar).zza(new com.google.android.gms.ads.internal.overlay.zzc(new zzac(zzapaVar.getContext(), ((zzapr) zzapaVar).zzx(), ((zzapt) zzapaVar).zzm()).zza((Map<String, String>) map)));
                return;
            } catch (ActivityNotFoundException e) {
                zzahw.zze(e.getMessage());
                return;
            }
        }
        zza(true);
        String str2 = (String) map.get("intent_url");
        Intent uri = null;
        if (!TextUtils.isEmpty(str2)) {
            try {
                uri = Intent.parseUri(str2, 0);
            } catch (URISyntaxException e2) {
                String strValueOf = String.valueOf(str2);
                zzahw.zzb(strValueOf.length() != 0 ? "Error parsing the url: ".concat(strValueOf) : new String("Error parsing the url: "), e2);
            }
        }
        if (uri != null && uri.getData() != null) {
            Uri data = uri.getData();
            String string = data.toString();
            if (!TextUtils.isEmpty(string)) {
                try {
                    string = zza(zzapaVar.getContext(), ((zzapr) zzapaVar).zzx(), string, ((zzapt) zzapaVar).zzm(), zzapaVar.zzd());
                } catch (Exception e3) {
                    zzahw.zzb("Error occurred while adding signals.", e3);
                    zzbt.zzi().zza(e3, "OpenGmsgHandler.onGmsg");
                }
                try {
                    data = Uri.parse(string);
                } catch (Exception e4) {
                    String strValueOf2 = String.valueOf(string);
                    zzahw.zzb(strValueOf2.length() != 0 ? "Error parsing the uri: ".concat(strValueOf2) : new String("Error parsing the uri: "), e4);
                    zzbt.zzi().zza(e4, "OpenGmsgHandler.onGmsg");
                }
            }
            uri.setData(data);
        }
        if (uri != null) {
            ((zzapo) zzapaVar).zza(new com.google.android.gms.ads.internal.overlay.zzc(uri));
            return;
        }
        if (!TextUtils.isEmpty(strZza)) {
            strZza = zza(zzapaVar.getContext(), ((zzapr) zzapaVar).zzx(), strZza, ((zzapt) zzapaVar).zzm(), zzapaVar.zzd());
        }
        ((zzapo) zzapaVar).zza(new com.google.android.gms.ads.internal.overlay.zzc((String) map.get("i"), strZza, (String) map.get("m"), (String) map.get(TtmlNode.TAG_P), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
    }
}
