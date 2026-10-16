package com.google.android.gms.internal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzs;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
final class zzaac implements zzajx<zzpj> {
    private /* synthetic */ boolean zza;
    private /* synthetic */ double zzb;
    private /* synthetic */ boolean zzc;
    private /* synthetic */ String zzd;
    private /* synthetic */ zzzy zze;

    zzaac(zzzy zzzyVar, boolean z, double d, boolean z2, String str) {
        this.zze = zzzyVar;
        this.zza = z;
        this.zzb = d;
        this.zzc = z2;
        this.zzd = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzajx
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzpj zza(InputStream inputStream) {
        Bitmap bitmapDecodeStream;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = (int) (this.zzb * 160.0d);
        if (!this.zzc) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        try {
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Exception e) {
            zzahw.zzb("Error grabbing image.", e);
            bitmapDecodeStream = null;
        }
        if (bitmapDecodeStream == null) {
            this.zze.zza(2, this.zza);
            return null;
        }
        long jUptimeMillis2 = SystemClock.uptimeMillis();
        if (zzs.zze() && zzahw.zza()) {
            int width = bitmapDecodeStream.getWidth();
            int height = bitmapDecodeStream.getHeight();
            int allocationByteCount = bitmapDecodeStream.getAllocationByteCount();
            long j = jUptimeMillis2 - jUptimeMillis;
            boolean z = Looper.getMainLooper().getThread() == Thread.currentThread();
            StringBuilder sb = new StringBuilder(108);
            sb.append("Decoded image w: ");
            sb.append(width);
            sb.append(" h:");
            sb.append(height);
            sb.append(" bytes: ");
            sb.append(allocationByteCount);
            sb.append(" time: ");
            sb.append(j);
            sb.append(" on ui thread: ");
            sb.append(z);
            zzahw.zza(sb.toString());
        }
        return new zzpj(new BitmapDrawable(Resources.getSystem(), bitmapDecodeStream), Uri.parse(this.zzd), this.zzb);
    }

    @Override // com.google.android.gms.internal.zzajx
    public final /* synthetic */ zzpj zza() {
        this.zze.zza(2, this.zza);
        return null;
    }
}
