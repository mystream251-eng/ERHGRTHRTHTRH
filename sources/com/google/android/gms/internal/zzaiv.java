package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.zzs;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class zzaiv extends zzaix {
    @Override // com.google.android.gms.internal.zzaip
    public final Drawable zza(Context context, Bitmap bitmap, boolean z, float f) {
        if (!z || f <= 0.0f || f > 25.0f) {
            return new BitmapDrawable(context.getResources(), bitmap);
        }
        try {
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateScaledBitmap);
            RenderScript renderScriptCreate = RenderScript.create(context);
            ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
            Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
            Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateBitmap);
            scriptIntrinsicBlurCreate.setRadius(f);
            scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
            scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
            allocationCreateFromBitmap2.copyTo(bitmapCreateBitmap);
            return new BitmapDrawable(context.getResources(), bitmapCreateBitmap);
        } catch (RuntimeException unused) {
            return new BitmapDrawable(context.getResources(), bitmap);
        }
    }

    @Override // com.google.android.gms.internal.zzaip
    public final String zza(Context context) {
        zzaki zzakiVarZza = zzaki.zza();
        if (TextUtils.isEmpty(zzakiVarZza.zza)) {
            zzakiVarZza.zza = (String) zzakg.zza(context, new zzakj(zzakiVarZza, zzs.getRemoteContext(context), context));
        }
        return zzakiVarZza.zza;
    }

    @Override // com.google.android.gms.internal.zzaix, com.google.android.gms.internal.zzais, com.google.android.gms.internal.zzaip
    public final boolean zza(Context context, WebSettings webSettings) {
        super.zza(context, webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        return true;
    }

    @Override // com.google.android.gms.internal.zzaip
    public final void zzb(Context context) {
        zzaki zzakiVarZza = zzaki.zza();
        zzahw.zza("Updating user agent.");
        String defaultUserAgent = WebSettings.getDefaultUserAgent(context);
        if (!defaultUserAgent.equals(zzakiVarZza.zza)) {
            Context remoteContext = zzs.getRemoteContext(context);
            if (remoteContext == null) {
                SharedPreferences.Editor editorPutString = context.getSharedPreferences("admob_user_agent", 0).edit().putString("user_agent", WebSettings.getDefaultUserAgent(context));
                if (remoteContext == null) {
                    editorPutString.apply();
                } else {
                    File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
                    File parentFile = file.getParentFile();
                    if (parentFile != null) {
                        parentFile.setExecutable(true, false);
                    }
                    file.setExecutable(true, false);
                    editorPutString.commit();
                    new File(file, "admob_user_agent".concat(".xml")).setReadable(true, false);
                }
            }
            zzakiVarZza.zza = defaultUserAgent;
        }
        zzahw.zza("User agent is updated.");
        zzbt.zzi().zzi();
    }
}
