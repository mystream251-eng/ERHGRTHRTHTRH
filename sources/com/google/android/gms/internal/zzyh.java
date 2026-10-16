package com.google.android.gms.internal;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzyh implements DialogInterface.OnClickListener {
    private /* synthetic */ String zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ zzyg zzc;

    zzyh(zzyg zzygVar, String str, String str2) {
        this.zzc = zzygVar;
        this.zza = str;
        this.zzb = str2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        DownloadManager downloadManager = (DownloadManager) this.zzc.zzb.getSystemService("download");
        try {
            String str = this.zza;
            String str2 = this.zzb;
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
            zzbt.zzg().zza(request);
            downloadManager.enqueue(request);
        } catch (IllegalStateException unused) {
            this.zzc.zza("Could not store picture.");
        }
    }
}
