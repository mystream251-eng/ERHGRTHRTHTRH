package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahd;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzapu;
import com.google.android.gms.internal.zzapv;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpm;
import com.google.android.gms.internal.zzqs;
import com.google.android.gms.internal.zzqt;
import com.google.android.gms.internal.zzvw;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import com.viaccessorca.vodownloader.NanoHTTPD;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzar {
    public static View zza(zzahd zzahdVar) {
        if (zzahdVar == null) {
            zzahw.zzc("AdState is null");
            return null;
        }
        if (zzb(zzahdVar) && zzahdVar.zzb != null) {
            return zzahdVar.zzb.zzm();
        }
        try {
            IObjectWrapper iObjectWrapperZza = zzahdVar.zzo != null ? zzahdVar.zzo.zza() : null;
            if (iObjectWrapperZza != null) {
                return (View) com.google.android.gms.dynamic.zzn.zza(iObjectWrapperZza);
            }
            zzahw.zze("View in mediation adapter is null.");
            return null;
        } catch (RemoteException e) {
            zzahw.zzc("Could not get View from mediation adapter.", e);
            return null;
        }
    }

    static com.google.android.gms.ads.internal.gmsg.zzt<zzaof> zza(zzwr zzwrVar, zzwu zzwuVar, zzab zzabVar) {
        return new zzaw(zzwrVar, zzabVar, zzwuVar);
    }

    private static zzqs zza(Object obj) {
        if (obj instanceof IBinder) {
            return zzqt.zza((IBinder) obj);
        }
        return null;
    }

    private static String zza(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzahw.zze("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String strValueOf = String.valueOf(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        return strValueOf.length() != 0 ? "data:image/png;base64,".concat(strValueOf) : new String("data:image/png;base64,");
    }

    private static String zza(zzqs zzqsVar) {
        if (zzqsVar == null) {
            zzahw.zze("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uriZzb = zzqsVar.zzb();
            if (uriZzb != null) {
                return uriZzb.toString();
            }
        } catch (RemoteException unused) {
            zzahw.zze("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzqsVar);
    }

    private static JSONObject zza(Bundle bundle, String str) throws JSONException {
        String strValueOf;
        String str2;
        JSONObject jSONObject = new JSONObject();
        if (bundle != null && !TextUtils.isEmpty(str)) {
            JSONObject jSONObject2 = new JSONObject(str);
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (bundle.containsKey(next)) {
                    if (TtmlNode.TAG_IMAGE.equals(jSONObject2.getString(next))) {
                        Object obj = bundle.get(next);
                        if (obj instanceof Bitmap) {
                            strValueOf = zza((Bitmap) obj);
                            jSONObject.put(next, strValueOf);
                        } else {
                            str2 = "Invalid type. An image type extra should return a bitmap";
                            zzahw.zze(str2);
                        }
                    } else if (bundle.get(next) instanceof Bitmap) {
                        str2 = "Invalid asset type. Bitmap should be returned only for image type";
                        zzahw.zze(str2);
                    } else {
                        strValueOf = String.valueOf(bundle.get(next));
                        jSONObject.put(next, strValueOf);
                    }
                }
            }
        }
        return jSONObject;
    }

    static final /* synthetic */ void zza(zzpk zzpkVar, String str, zzaof zzaofVar, zzaof zzaofVar2, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzpkVar.zza());
            jSONObject.put(TtmlNode.TAG_BODY, zzpkVar.zzc());
            jSONObject.put("call_to_action", zzpkVar.zze());
            jSONObject.put("price", zzpkVar.zzh());
            jSONObject.put("star_rating", String.valueOf(zzpkVar.zzf()));
            jSONObject.put("store", zzpkVar.zzg());
            jSONObject.put("icon", zza(zzpkVar.zzd()));
            JSONArray jSONArray = new JSONArray();
            List listZzb = zzpkVar.zzb();
            if (listZzb != null) {
                Iterator it = listZzb.iterator();
                while (it.hasNext()) {
                    jSONArray.put(zza(zza(it.next())));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzpkVar.zzn(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", ExifInterface.GPS_MEASUREMENT_2D);
            zzaofVar.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzahw.zzc("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void zza(zzpm zzpmVar, String str, zzaof zzaofVar, zzaof zzaofVar2, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzpmVar.zza());
            jSONObject.put(TtmlNode.TAG_BODY, zzpmVar.zze());
            jSONObject.put("call_to_action", zzpmVar.zzg());
            jSONObject.put("advertiser", zzpmVar.zzh());
            jSONObject.put(TvContractCompat.Channels.Logo.CONTENT_DIRECTORY, zza(zzpmVar.zzf()));
            JSONArray jSONArray = new JSONArray();
            List listZzb = zzpmVar.zzb();
            if (listZzb != null) {
                Iterator it = listZzb.iterator();
                while (it.hasNext()) {
                    jSONArray.put(zza(zza(it.next())));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzpmVar.zzn(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", LocalSearchProvider.LIVE_CONTENTS);
            zzaofVar.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzahw.zzc("Exception occurred when loading assets", e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x013c  */
    public static boolean zza(final zzaof zzaofVar, zzvw zzvwVar, CountDownLatch countDownLatch) {
        String str;
        zzapu zzapuVarZzv;
        zzapv zzapvVar;
        boolean z = false;
        try {
            View viewZzm = zzaofVar.zzm();
            if (viewZzm == null) {
                str = "AdWebView is null";
            } else {
                viewZzm.setVisibility(4);
                List<String> list = zzvwVar.zzb.zzq;
                if (list != null && !list.isEmpty()) {
                    zzaofVar.zzv().zza("/nativeExpressAssetsLoaded", new zzau(countDownLatch));
                    zzaofVar.zzv().zza("/nativeExpressAssetsLoadingFailed", new zzav(countDownLatch));
                    zzwr zzwrVarZzh = zzvwVar.zzc.zzh();
                    zzwu zzwuVarZzi = zzvwVar.zzc.zzi();
                    if (list.contains(ExifInterface.GPS_MEASUREMENT_2D) && zzwrVarZzh != null) {
                        final zzpk zzpkVar = new zzpk(zzwrVarZzh.zza(), zzwrVarZzh.zzb(), zzwrVarZzh.zzc(), zzwrVarZzh.zzd(), zzwrVarZzh.zze(), zzwrVarZzh.zzf(), zzwrVarZzh.zzg(), zzwrVarZzh.zzh(), null, zzwrVarZzh.zzl(), null, zzwrVarZzh.zzp() != null ? (View) com.google.android.gms.dynamic.zzn.zza(zzwrVarZzh.zzp()) : null, zzwrVarZzh.zzq(), null);
                        final String str2 = zzvwVar.zzb.zzp;
                        zzapuVarZzv = zzaofVar.zzv();
                        zzapvVar = new zzapv(zzpkVar, str2, zzaofVar) { // from class: com.google.android.gms.ads.internal.zzas
                            private final zzpk zza;
                            private final String zzb;
                            private final zzaof zzc;

                            {
                                this.zza = zzpkVar;
                                this.zzb = str2;
                                this.zzc = zzaofVar;
                            }

                            @Override // com.google.android.gms.internal.zzapv
                            public final void zza(zzaof zzaofVar2, boolean z2) {
                                zzar.zza(this.zza, this.zzb, this.zzc, zzaofVar2, z2);
                            }
                        };
                    } else if (!list.contains(LocalSearchProvider.LIVE_CONTENTS) || zzwuVarZzi == null) {
                        str = "No matching template id and mapper";
                    } else {
                        final zzpm zzpmVar = new zzpm(zzwuVarZzi.zza(), zzwuVarZzi.zzb(), zzwuVarZzi.zzc(), zzwuVarZzi.zzd(), zzwuVarZzi.zze(), zzwuVarZzi.zzf(), null, zzwuVarZzi.zzj(), null, zzwuVarZzi.zzn() != null ? (View) com.google.android.gms.dynamic.zzn.zza(zzwuVarZzi.zzn()) : null, zzwuVarZzi.zzo(), null);
                        final String str3 = zzvwVar.zzb.zzp;
                        zzapuVarZzv = zzaofVar.zzv();
                        zzapvVar = new zzapv(zzpmVar, str3, zzaofVar) { // from class: com.google.android.gms.ads.internal.zzat
                            private final zzpm zza;
                            private final String zzb;
                            private final zzaof zzc;

                            {
                                this.zza = zzpmVar;
                                this.zzb = str3;
                                this.zzc = zzaofVar;
                            }

                            @Override // com.google.android.gms.internal.zzapv
                            public final void zza(zzaof zzaofVar2, boolean z2) {
                                zzar.zza(this.zza, this.zzb, this.zzc, zzaofVar2, z2);
                            }
                        };
                    }
                    zzapuVarZzv.zza(zzapvVar);
                    String str4 = zzvwVar.zzb.zzn;
                    String str5 = zzvwVar.zzb.zzo;
                    if (str5 != null) {
                        zzaofVar.loadDataWithBaseURL(str5, str4, NanoHTTPD.MIME_HTML, "UTF-8", null);
                    } else {
                        zzaofVar.loadData(str4, NanoHTTPD.MIME_HTML, "UTF-8");
                    }
                    z = true;
                    if (!z) {
                        countDownLatch.countDown();
                    }
                    return z;
                }
                str = "No template ids present in mediation response";
            }
            zzahw.zze(str);
        } catch (RemoteException e) {
            zzahw.zzc("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    private static String zzb(zzqs zzqsVar) {
        String str;
        try {
            IObjectWrapper iObjectWrapperZza = zzqsVar.zza();
            if (iObjectWrapperZza == null) {
                zzahw.zze("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) com.google.android.gms.dynamic.zzn.zza(iObjectWrapperZza);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            str = "Drawable is not an instance of BitmapDrawable. Returning empty string";
            zzahw.zze(str);
            return "";
        } catch (RemoteException unused) {
            str = "Unable to get drawable. Returning empty string";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(zzaof zzaofVar) {
        View.OnClickListener onClickListenerZzah = zzaofVar.zzah();
        if (onClickListenerZzah != null) {
            onClickListenerZzah.onClick(zzaofVar.zzm());
        }
    }

    public static boolean zzb(zzahd zzahdVar) {
        return (zzahdVar == null || !zzahdVar.zzm || zzahdVar.zzn == null || zzahdVar.zzn.zzn == null) ? false : true;
    }
}
