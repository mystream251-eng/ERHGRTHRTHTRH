package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzp;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzadn extends zzaco {
    private static final Object zza = new Object();
    private static zzadn zzb;
    private final Context zzc;
    private final zzadm zzd;
    private final ScheduledExecutorService zze = Executors.newSingleThreadScheduledExecutor();

    private zzadn(Context context, zzadm zzadmVar) {
        this.zzc = context;
        this.zzd = zzadmVar;
    }

    private static zzacj zza(Context context, zzadm zzadmVar, zzacf zzacfVar, ScheduledExecutorService scheduledExecutorService) {
        String string;
        zzahw.zzb("Starting ad request from service using: google.afma.request.getAdDictionary");
        zzov zzovVar = new zzov(((Boolean) zzlc.zzf().zza(zzoi.zzaj)).booleanValue(), "load_ad", zzacfVar.zzd.zza);
        if (zzacfVar.zza > 10 && zzacfVar.zzaa != -1) {
            zzovVar.zza(zzovVar.zza(zzacfVar.zzaa), "cts");
        }
        zzot zzotVarZza = zzovVar.zza();
        zzalt zzaltVarZza = zzali.zza(zzadmVar.zzi.zza(context), ((Long) zzlc.zzf().zza(zzoi.zzfq)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        zzalt zzaltVarZza2 = zzali.zza(zzadmVar.zzh.zza(context), ((Long) zzlc.zzf().zza(zzoi.zzdn)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        zzalt<String> zzaltVarZza3 = zzadmVar.zzc.zza(zzacfVar.zzg.packageName);
        zzalt<String> zzaltVarZza4 = zzadmVar.zzj.zza(zzacfVar.zzh, zzacfVar.zzg);
        Future<zzaea> futureZza = zzbt.zzp().zza(context);
        zzalt<Location> zzaltVarZza5 = zzali.zza((Object) null);
        Bundle bundle = zzacfVar.zzc.zzc;
        boolean z = (bundle == null || bundle.getString("_ad") == null) ? false : true;
        if (zzacfVar.zzag && !z) {
            zzaltVarZza5 = zzadmVar.zzf.zza(zzacfVar.zzf);
        }
        zzalt zzaltVarZza6 = zzali.zza(zzaltVarZza5, ((Long) zzlc.zzf().zza(zzoi.zzfh)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        Future futureZza2 = zzali.zza((Object) null);
        if (((Boolean) zzlc.zzf().zza(zzoi.zzcc)).booleanValue()) {
            futureZza2 = zzali.zza(zzadmVar.zzj.zza(context), ((Long) zzlc.zzf().zza(zzoi.zzcd)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        }
        Bundle bundle2 = (zzacfVar.zza < 4 || zzacfVar.zzo == null) ? null : zzacfVar.zzo;
        ((Boolean) zzlc.zzf().zza(zzoi.zzaz)).booleanValue();
        zzbt.zze();
        if (zzaij.zza(context, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            zzahw.zzb("Device is offline.");
        }
        String string2 = zzacfVar.zza >= 7 ? zzacfVar.zzv : UUID.randomUUID().toString();
        new zzadt(context, string2, zzacfVar.zzf.packageName);
        if (zzacfVar.zzc.zzc != null && (string = zzacfVar.zzc.zzc.getString("_ad")) != null) {
            return zzads.zza(context, zzacfVar, string);
        }
        List<String> listZza = zzadmVar.zzd.zza(zzacfVar.zzw);
        String str = string2;
        Bundle bundle3 = (Bundle) zzali.zza(zzaltVarZza, (Object) null, ((Long) zzlc.zzf().zza(zzoi.zzfq)).longValue(), TimeUnit.MILLISECONDS);
        zzaek zzaekVar = (zzaek) zzali.zza(zzaltVarZza2, (Object) null);
        Location location = (Location) zzali.zza(zzaltVarZza6, (Object) null);
        AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) zzali.zza((Future<Object>) futureZza2, (Object) null);
        String str2 = (String) zzali.zza(zzaltVarZza4, (Object) null);
        String str3 = (String) zzali.zza(zzaltVarZza3, (Object) null);
        zzaea zzaeaVar = (zzaea) zzali.zza(futureZza, (Object) null);
        if (zzaeaVar == null) {
            zzahw.zze("Error fetching device info. This is not recoverable.");
            return new zzacj(0);
        }
        zzadl zzadlVar = new zzadl();
        zzadlVar.zzi = zzacfVar;
        zzadlVar.zzj = zzaeaVar;
        zzadlVar.zze = zzaekVar;
        zzadlVar.zzd = location;
        zzadlVar.zzb = bundle3;
        zzadlVar.zzg = str2;
        zzadlVar.zzh = info;
        if (listZza == null) {
            zzadlVar.zzc.clear();
        }
        zzadlVar.zzc = listZza;
        zzadlVar.zza = bundle2;
        zzadlVar.zzf = str3;
        zzadlVar.zzk = zzadmVar.zzb.zza(context);
        zzadlVar.zzl = zzadmVar.zzk;
        JSONObject jSONObjectZza = zzads.zza(context, zzadlVar);
        if (jSONObjectZza == null) {
            return new zzacj(0);
        }
        if (zzacfVar.zza < 7) {
            try {
                jSONObjectZza.put("request_id", str);
            } catch (JSONException unused) {
            }
        }
        jSONObjectZza.toString();
        zzovVar.zza(zzotVarZza, "arc");
        zzovVar.zza();
        zzalt zzaltVarZza7 = zzali.zza(zzali.zza(zzadmVar.zzl.zza().zzb(jSONObjectZza), zzado.zza, scheduledExecutorService), 10L, TimeUnit.SECONDS, scheduledExecutorService);
        zzalt<Void> zzaltVarZza8 = zzadmVar.zze.zza();
        if (zzaltVarZza8 != null) {
            zzalg.zza(zzaltVarZza8, "AdRequestServiceImpl.loadAd.flags");
        }
        zzadz zzadzVar = (zzadz) zzali.zza(zzaltVarZza7, (Object) null);
        if (zzadzVar == null) {
            return new zzacj(0);
        }
        if (zzadzVar.zza() != -2) {
            return new zzacj(zzadzVar.zza());
        }
        zzovVar.zzd();
        zzacj zzacjVarZza = !TextUtils.isEmpty(zzadzVar.zzi()) ? zzads.zza(context, zzacfVar, zzadzVar.zzi()) : null;
        if (zzacjVarZza == null && !TextUtils.isEmpty(zzadzVar.zze())) {
            zzacjVarZza = zza(zzacfVar, context, zzacfVar.zzk.zza, zzadzVar.zze(), str3, zzadzVar, zzovVar, zzadmVar);
        }
        if (zzacjVarZza == null) {
            zzacjVarZza = new zzacj(0);
        }
        zzovVar.zza(zzotVarZza, "tts");
        zzacjVarZza.zzw = zzovVar.zzb();
        return zzacjVarZza;
    }

    public static zzacj zza(zzacf zzacfVar, Context context, String str, String str2, String str3, zzadz zzadzVar, zzov zzovVar, zzadm zzadmVar) {
        byte[] bytes;
        InputStreamReader inputStreamReader;
        BufferedOutputStream bufferedOutputStream;
        zzacf zzacfVar2 = zzacfVar;
        zzot zzotVarZza = zzovVar != null ? zzovVar.zza() : null;
        try {
            zzadx zzadxVar = new zzadx(zzacfVar2, zzadzVar.zzc());
            String strValueOf = String.valueOf(str2);
            zzahw.zzb(strValueOf.length() != 0 ? "AdRequestServiceImpl: Sending request: ".concat(strValueOf) : new String("AdRequestServiceImpl: Sending request: "));
            URL url = new URL(str2);
            long jZzb = zzbt.zzl().zzb();
            boolean z = false;
            int i = 0;
            while (true) {
                if (zzadmVar != null) {
                    zzadmVar.zzg.zza();
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    zzbt.zze().zza(context, str, z, httpURLConnection);
                    if (!TextUtils.isEmpty(str3) && zzadzVar.zzg()) {
                        httpURLConnection.addRequestProperty("x-afma-drt-cookie", str3);
                    }
                    String str4 = zzacfVar2.zzah;
                    if (!TextUtils.isEmpty(str4)) {
                        zzahw.zzb("Sending webview cookie in ad request header.");
                        httpURLConnection.addRequestProperty("Cookie", str4);
                    }
                    if (zzadzVar == null || TextUtils.isEmpty(zzadzVar.zzd())) {
                        bytes = null;
                    } else {
                        httpURLConnection.setDoOutput(true);
                        bytes = zzadzVar.zzd().getBytes();
                        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                        try {
                            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(httpURLConnection.getOutputStream());
                            try {
                                bufferedOutputStream2.write(bytes);
                                zzp.zza(bufferedOutputStream2);
                            } catch (Throwable th) {
                                th = th;
                                bufferedOutputStream = bufferedOutputStream2;
                                zzp.zza(bufferedOutputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedOutputStream = null;
                        }
                    }
                    zzaks zzaksVar = new zzaks(zzacfVar2.zzv);
                    zzaksVar.zza(httpURLConnection, bytes);
                    int responseCode = httpURLConnection.getResponseCode();
                    Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                    zzaksVar.zza(httpURLConnection, responseCode);
                    if (responseCode < 200 || responseCode >= 300) {
                        zza(url.toString(), headerFields, (String) null, responseCode);
                        if (responseCode < 300 || responseCode >= 400) {
                            StringBuilder sb = new StringBuilder(46);
                            sb.append("Received error HTTP response code: ");
                            sb.append(responseCode);
                            zzahw.zze(sb.toString());
                            zzacj zzacjVar = new zzacj(0);
                            httpURLConnection.disconnect();
                            if (zzadmVar != null) {
                                zzadmVar.zzg.zzb();
                            }
                            return zzacjVar;
                        }
                        String headerField = httpURLConnection.getHeaderField("Location");
                        if (TextUtils.isEmpty(headerField)) {
                            zzahw.zze("No location header to follow redirect.");
                            zzacj zzacjVar2 = new zzacj(0);
                            httpURLConnection.disconnect();
                            if (zzadmVar != null) {
                                zzadmVar.zzg.zzb();
                            }
                            return zzacjVar2;
                        }
                        url = new URL(headerField);
                        i++;
                        if (i > ((Integer) zzlc.zzf().zza(zzoi.zzgs)).intValue()) {
                            zzahw.zze("Too many redirects.");
                            zzacj zzacjVar3 = new zzacj(0);
                            httpURLConnection.disconnect();
                            if (zzadmVar != null) {
                                zzadmVar.zzg.zzb();
                            }
                            return zzacjVar3;
                        }
                        zzadxVar.zza(headerFields);
                        httpURLConnection.disconnect();
                        if (zzadmVar != null) {
                            zzadmVar.zzg.zzb();
                        }
                        zzacfVar2 = zzacfVar;
                        z = false;
                    } else {
                        String string = url.toString();
                        try {
                            InputStreamReader inputStreamReader2 = new InputStreamReader(httpURLConnection.getInputStream());
                            try {
                                zzbt.zze();
                                String strZza = zzaij.zza(inputStreamReader2);
                                zzp.zza(inputStreamReader2);
                                zzaksVar.zza(strZza);
                                zza(string, headerFields, strZza, responseCode);
                                zzadxVar.zza(string, headerFields, strZza);
                                if (zzovVar != null) {
                                    zzovVar.zza(zzotVarZza, "ufe");
                                }
                                zzacj zzacjVarZza = zzadxVar.zza(jZzb, zzadzVar.zzj());
                                httpURLConnection.disconnect();
                                if (zzadmVar != null) {
                                    zzadmVar.zzg.zzb();
                                }
                                return zzacjVarZza;
                            } catch (Throwable th3) {
                                th = th3;
                                inputStreamReader = inputStreamReader2;
                                zzp.zza(inputStreamReader);
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            inputStreamReader = null;
                        }
                    }
                } catch (Throwable th5) {
                    httpURLConnection.disconnect();
                    if (zzadmVar != null) {
                        zzadmVar.zzg.zzb();
                    }
                    throw th5;
                }
            }
        } catch (IOException e) {
            String strValueOf2 = String.valueOf(e.getMessage());
            zzahw.zze(strValueOf2.length() != 0 ? "Error while connecting to ad server: ".concat(strValueOf2) : new String("Error while connecting to ad server: "));
            return new zzacj(2);
        }
    }

    public static zzadn zza(Context context, zzadm zzadmVar) {
        zzadn zzadnVar;
        synchronized (zza) {
            if (zzb == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzoi.zza(context);
                zzb = new zzadn(context, zzadmVar);
                if (context.getApplicationContext() != null) {
                    zzbt.zze().zzc(context);
                }
                zzahu.zza(context);
            }
            zzadnVar = zzb;
        }
        return zzadnVar;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzahw.zza(2)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39);
            sb.append("Http Response: {\n  URL:\n    ");
            sb.append(str);
            sb.append("\n  Headers:");
            zzahw.zza(sb.toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 5);
                    sb2.append("    ");
                    sb2.append(str3);
                    sb2.append(":");
                    zzahw.zza(sb2.toString());
                    Iterator<String> it = map.get(str3).iterator();
                    while (it.hasNext()) {
                        String strValueOf = String.valueOf(it.next());
                        zzahw.zza(strValueOf.length() != 0 ? "      ".concat(strValueOf) : new String("      "));
                    }
                }
            }
            zzahw.zza("  Body:");
            if (str2 != null) {
                int i2 = 0;
                while (i2 < Math.min(str2.length(), DefaultOggSeeker.MATCH_BYTE_RANGE)) {
                    int i3 = i2 + 1000;
                    zzahw.zza(str2.substring(i2, Math.min(str2.length(), i3)));
                    i2 = i3;
                }
            } else {
                zzahw.zza("    null");
            }
            StringBuilder sb3 = new StringBuilder(34);
            sb3.append("  Response Code:\n    ");
            sb3.append(i);
            sb3.append("\n}");
            zzahw.zza(sb3.toString());
        }
    }

    @Override // com.google.android.gms.internal.zzacn
    public final zzacj zza(zzacf zzacfVar) {
        return zza(this.zzc, this.zzd, zzacfVar, this.zze);
    }

    @Override // com.google.android.gms.internal.zzacn
    public final void zza(zzacf zzacfVar, zzacq zzacqVar) {
        zzbt.zzi().zza(this.zzc, zzacfVar.zzk);
        zzalt<Void> zzaltVarZza = zzaid.zza(new zzadp(this, zzacfVar, zzacqVar));
        zzbt.zzt().zza();
        zzbt.zzt().zzb().postDelayed(new zzadq(this, zzaltVarZza), DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
    }

    @Override // com.google.android.gms.internal.zzacn
    public final void zza(zzacy zzacyVar, zzact zzactVar) {
        zzahw.zza("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }
}
