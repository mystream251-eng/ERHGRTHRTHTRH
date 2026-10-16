package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.zzbt;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaaf {
    private final Context zzb;
    private final zzcv zzc;
    private final zzahe zzd;
    private final zzov zze;
    private final com.google.android.gms.ads.internal.zzbb zzf;
    private ViewTreeObserver.OnGlobalLayoutListener zzg;
    private ViewTreeObserver.OnScrollChangedListener zzh;
    private final DisplayMetrics zzi;
    private final Object zza = new Object();
    private int zzk = -1;
    private int zzl = -1;
    private zzake zzj = new zzake(200);

    public zzaaf(Context context, zzcv zzcvVar, zzahe zzaheVar, zzov zzovVar, com.google.android.gms.ads.internal.zzbb zzbbVar) {
        this.zzb = context;
        this.zzc = zzcvVar;
        this.zzd = zzaheVar;
        this.zze = zzovVar;
        this.zzf = zzbbVar;
        zzbt.zze();
        this.zzi = zzaij.zza((WindowManager) context.getSystemService("window"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(WeakReference<zzaof> weakReference, boolean z) {
        zzaof zzaofVar;
        if (weakReference == null || (zzaofVar = weakReference.get()) == null || zzaofVar.zzm() == null) {
            return;
        }
        if (!z || this.zzj.zza()) {
            int[] iArr = new int[2];
            zzaofVar.zzm().getLocationOnScreen(iArr);
            zzlc.zza();
            int iZzb = zzako.zzb(this.zzi, iArr[0]);
            zzlc.zza();
            int iZzb2 = zzako.zzb(this.zzi, iArr[1]);
            synchronized (this.zza) {
                if (this.zzk != iZzb || this.zzl != iZzb2) {
                    this.zzk = iZzb;
                    this.zzl = iZzb2;
                    zzaofVar.zzv().zza(this.zzk, this.zzl, z ? false : true);
                }
            }
        }
    }

    final /* synthetic */ void zza(zzamd zzamdVar, zzaof zzaofVar, boolean z) {
        this.zzf.zzba();
        zzamdVar.zzb(zzaofVar);
    }

    final /* synthetic */ void zza(final JSONObject jSONObject, final zzamd zzamdVar) {
        try {
            final zzaof zzaofVarZza = zzbt.zzf().zza(this.zzb, zzaqa.zza(), "native-video", false, false, this.zzc, this.zzd.zza.zzk, this.zze, null, this.zzf.zza(), this.zzd.zzi);
            zzaofVarZza.zza(zzaqa.zzb());
            this.zzf.zza(zzaofVarZza);
            WeakReference weakReference = new WeakReference(zzaofVarZza);
            zzapu zzapuVarZzv = zzaofVarZza.zzv();
            if (this.zzg == null) {
                this.zzg = new zzaal(this, weakReference);
            }
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzg;
            if (this.zzh == null) {
                this.zzh = new zzaam(this, weakReference);
            }
            zzapuVarZzv.zza(onGlobalLayoutListener, this.zzh);
            zzapu zzapuVarZzv2 = zzaofVarZza.zzv();
            zzapuVarZzv2.zza("/video", zzd.zzl);
            zzapuVarZzv2.zza("/videoMeta", zzd.zzm);
            zzapuVarZzv2.zza("/precache", new zzaoc());
            zzapuVarZzv2.zza("/delayPageLoaded", zzd.zzp);
            zzapuVarZzv2.zza("/instrument", zzd.zzn);
            zzapuVarZzv2.zza("/log", zzd.zzg);
            zzapuVarZzv2.zza("/videoClicked", zzd.zzh);
            zzapuVarZzv2.zza("/trackActiveViewUnit", new zzaaj(this));
            zzapuVarZzv2.zza("/untrackActiveViewUnit", new zzaak(this));
            zzaofVarZza.zzv().zza(new zzapw(zzaofVarZza, jSONObject) { // from class: com.google.android.gms.internal.zzaah
                private final zzaof zza;
                private final JSONObject zzb;

                {
                    this.zza = zzaofVarZza;
                    this.zzb = jSONObject;
                }

                @Override // com.google.android.gms.internal.zzapw
                public final void zza(zzaof zzaofVar) {
                    this.zza.zzb("google.afma.nativeAds.renderVideo", this.zzb);
                }
            });
            zzaofVarZza.zzv().zza(new zzapv(this, zzamdVar) { // from class: com.google.android.gms.internal.zzaai
                private final zzaaf zza;
                private final zzamd zzb;

                {
                    this.zza = this;
                    this.zzb = zzamdVar;
                }

                @Override // com.google.android.gms.internal.zzapv
                public final void zza(zzaof zzaofVar, boolean z) {
                    this.zza.zza(this.zzb, zzaofVar, z);
                }
            });
            zzaofVarZza.loadUrl((String) zzlc.zzf().zza(zzoi.zzer));
        } catch (Exception e) {
            zzahw.zzc("Exception occurred while getting video view", e);
            zzamdVar.zzb(null);
        }
    }
}
