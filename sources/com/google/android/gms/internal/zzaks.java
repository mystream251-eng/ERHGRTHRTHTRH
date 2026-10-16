package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.JsonWriter;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.viaccessorca.analytics.fma.FmaDatabaseHelper;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaks {
    private static Object zza = new Object();
    private static boolean zzb = false;
    private static boolean zzc = false;
    private static zze zzd = zzi.zzd();
    private static final Set<String> zze = new HashSet(Arrays.asList(new String[0]));
    private final List<String> zzf;

    public zzaks() {
        this(null);
    }

    public zzaks(String str) {
        List<String> listAsList;
        if (zzc()) {
            String string = UUID.randomUUID().toString();
            if (str == null) {
                String[] strArr = new String[1];
                String strValueOf = String.valueOf(string);
                strArr[0] = strValueOf.length() != 0 ? "network_request_".concat(strValueOf) : new String("network_request_");
                listAsList = Arrays.asList(strArr);
            } else {
                String[] strArr2 = new String[2];
                String strValueOf2 = String.valueOf(str);
                strArr2[0] = strValueOf2.length() != 0 ? "ad_request_".concat(strValueOf2) : new String("ad_request_");
                String strValueOf3 = String.valueOf(string);
                strArr2[1] = strValueOf3.length() != 0 ? "network_request_".concat(strValueOf3) : new String("network_request_");
                listAsList = Arrays.asList(strArr2);
            }
        } else {
            listAsList = new ArrayList<>();
        }
        this.zzf = listAsList;
    }

    public static void zza() {
        synchronized (zza) {
            zzb = false;
            zzc = false;
            zzaky.zze("Ad debug logging enablement is out of date.");
        }
    }

    static final /* synthetic */ void zza(int i, Map map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("code").value(i);
        jsonWriter.endObject();
        zza(jsonWriter, (Map<String, ?>) map);
        jsonWriter.endObject();
    }

    private static void zza(JsonWriter jsonWriter, Map<String, ?> map) throws IOException {
        if (map == null) {
            return;
        }
        jsonWriter.name("headers").beginArray();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            if (!zze.contains(key)) {
                if (!(entry.getValue() instanceof List)) {
                    if (!(entry.getValue() instanceof String)) {
                        zzaky.zzc("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                        break;
                    }
                    jsonWriter.beginObject();
                    jsonWriter.name("name").value(key);
                    jsonWriter.name("value").value((String) entry.getValue());
                    jsonWriter.endObject();
                } else {
                    for (String str : (List) entry.getValue()) {
                        jsonWriter.beginObject();
                        jsonWriter.name("name").value(key);
                        jsonWriter.name("value").value(str);
                        jsonWriter.endObject();
                    }
                }
            }
        }
        jsonWriter.endArray();
    }

    static final /* synthetic */ void zza(String str, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        if (str != null) {
            jsonWriter.name("error_description").value(str);
        }
        jsonWriter.endObject();
    }

    private final void zza(String str, zzakx zzakxVar) {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            jsonWriter.beginObject();
            jsonWriter.name(FmaDatabaseHelper.CONST_TIMESTAMP).value(zzd.zza());
            jsonWriter.name(NotificationCompat.CATEGORY_EVENT).value(str);
            jsonWriter.name("components").beginArray();
            Iterator<String> it = this.zzf.iterator();
            while (it.hasNext()) {
                jsonWriter.value(it.next());
            }
            jsonWriter.endArray();
            zzakxVar.zza(jsonWriter);
            jsonWriter.endObject();
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            zzaky.zzb("unable to log", e);
        }
        zzc(stringWriter.toString());
    }

    static final /* synthetic */ void zza(String str, String str2, Map map, byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("uri").value(str);
        jsonWriter.name("verb").value(str2);
        jsonWriter.endObject();
        zza(jsonWriter, (Map<String, ?>) map);
        if (bArr != null) {
            jsonWriter.name(TtmlNode.TAG_BODY).value(zzc.zza(bArr));
        }
        jsonWriter.endObject();
    }

    public static void zza(boolean z) {
        synchronized (zza) {
            zzb = true;
            zzc = z;
        }
    }

    static final /* synthetic */ void zza(byte[] bArr, JsonWriter jsonWriter) throws IOException {
        String str;
        jsonWriter.name("params").beginObject();
        int length = bArr.length;
        String strZza = zzc.zza(bArr);
        if (length >= 10000) {
            strZza = zzako.zza(strZza);
            if (strZza != null) {
                str = "bodydigest";
            }
            jsonWriter.name("bodylength").value(length);
            jsonWriter.endObject();
        }
        str = TtmlNode.TAG_BODY;
        jsonWriter.name(str).value(strZza);
        jsonWriter.name("bodylength").value(length);
        jsonWriter.endObject();
    }

    public static boolean zza(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        return ((Boolean) zzlc.zzf().zza(zzoi.zzcz)).booleanValue() && Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0;
    }

    private final void zzb(final String str) {
        zza("onNetworkRequestError", new zzakx(str) { // from class: com.google.android.gms.internal.zzakw
            private final String zza;

            {
                this.zza = str;
            }

            @Override // com.google.android.gms.internal.zzakx
            public final void zza(JsonWriter jsonWriter) throws IOException {
                zzaks.zza(this.zza, jsonWriter);
            }
        });
    }

    private final void zzb(final String str, final String str2, final Map<String, ?> map, final byte[] bArr) {
        zza("onNetworkRequest", new zzakx(str, str2, map, bArr) { // from class: com.google.android.gms.internal.zzakt
            private final String zza;
            private final String zzb;
            private final Map zzc;
            private final byte[] zzd;

            {
                this.zza = str;
                this.zzb = str2;
                this.zzc = map;
                this.zzd = bArr;
            }

            @Override // com.google.android.gms.internal.zzakx
            public final void zza(JsonWriter jsonWriter) throws IOException {
                zzaks.zza(this.zza, this.zzb, this.zzc, this.zzd, jsonWriter);
            }
        });
    }

    private final void zzb(final Map<String, ?> map, final int i) {
        zza("onNetworkResponse", new zzakx(i, map) { // from class: com.google.android.gms.internal.zzaku
            private final int zza;
            private final Map zzb;

            {
                this.zza = i;
                this.zzb = map;
            }

            @Override // com.google.android.gms.internal.zzakx
            public final void zza(JsonWriter jsonWriter) throws IOException {
                zzaks.zza(this.zza, this.zzb, jsonWriter);
            }
        });
    }

    public static boolean zzb() {
        boolean z;
        synchronized (zza) {
            z = zzb;
        }
        return z;
    }

    private static synchronized void zzc(String str) {
        zzaky.zzd("GMA Debug BEGIN");
        int i = 0;
        while (i < str.length()) {
            int i2 = i + VOPlayer.MEDIA_INFO_NETWORK;
            String strValueOf = String.valueOf(str.substring(i, Math.min(i2, str.length())));
            zzaky.zzd(strValueOf.length() != 0 ? "GMA Debug CONTENT ".concat(strValueOf) : new String("GMA Debug CONTENT "));
            i = i2;
        }
        zzaky.zzd("GMA Debug FINISH");
    }

    public static boolean zzc() {
        boolean z;
        synchronized (zza) {
            z = zzb && zzc;
        }
        return z;
    }

    public final void zza(String str) {
        if (zzc() && str != null) {
            zza(str.getBytes());
        }
    }

    public final void zza(String str, String str2, Map<String, ?> map, byte[] bArr) {
        if (zzc()) {
            zzb(str, str2, map, bArr);
        }
    }

    public final void zza(HttpURLConnection httpURLConnection, int i) {
        if (zzc()) {
            String responseMessage = null;
            zzb(httpURLConnection.getHeaderFields() == null ? null : new HashMap(httpURLConnection.getHeaderFields()), i);
            if (i < 200 || i >= 300) {
                try {
                    responseMessage = httpURLConnection.getResponseMessage();
                } catch (IOException e) {
                    String strValueOf = String.valueOf(e.getMessage());
                    zzaky.zze(strValueOf.length() != 0 ? "Can not get error message from error HttpURLConnection\n".concat(strValueOf) : new String("Can not get error message from error HttpURLConnection\n"));
                }
                zzb(responseMessage);
            }
        }
    }

    public final void zza(HttpURLConnection httpURLConnection, byte[] bArr) {
        if (zzc()) {
            zzb(new String(httpURLConnection.getURL().toString()), new String(httpURLConnection.getRequestMethod()), httpURLConnection.getRequestProperties() == null ? null : new HashMap(httpURLConnection.getRequestProperties()), bArr);
        }
    }

    public final void zza(Map<String, ?> map, int i) {
        if (zzc()) {
            zzb(map, i);
            if (i < 200 || i >= 300) {
                zzb(null);
            }
        }
    }

    public final void zza(final byte[] bArr) {
        zza("onNetworkResponseBody", new zzakx(bArr) { // from class: com.google.android.gms.internal.zzakv
            private final byte[] zza;

            {
                this.zza = bArr;
            }

            @Override // com.google.android.gms.internal.zzakx
            public final void zza(JsonWriter jsonWriter) throws IOException {
                zzaks.zza(this.zza, jsonWriter);
            }
        });
    }
}
