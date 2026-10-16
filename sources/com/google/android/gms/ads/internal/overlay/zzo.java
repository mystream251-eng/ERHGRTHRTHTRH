package com.google.android.gms.ads.internal.overlay;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzlc;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzo extends FrameLayout implements View.OnClickListener {
    private final ImageButton zza;
    private final zzw zzb;

    public zzo(Context context, zzp zzpVar, zzw zzwVar) {
        super(context);
        this.zzb = zzwVar;
        setOnClickListener(this);
        ImageButton imageButton = new ImageButton(context);
        this.zza = imageButton;
        imageButton.setImageResource(R.drawable.btn_dialog);
        this.zza.setBackgroundColor(0);
        this.zza.setOnClickListener(this);
        ImageButton imageButton2 = this.zza;
        zzlc.zza();
        int iZza = zzako.zza(context, zzpVar.zza);
        zzlc.zza();
        int iZza2 = zzako.zza(context, 0);
        zzlc.zza();
        int iZza3 = zzako.zza(context, zzpVar.zzb);
        zzlc.zza();
        imageButton2.setPadding(iZza, iZza2, iZza3, zzako.zza(context, zzpVar.zzd));
        this.zza.setContentDescription("Interstitial close button");
        zzlc.zza();
        zzako.zza(context, zzpVar.zze);
        ImageButton imageButton3 = this.zza;
        zzlc.zza();
        int iZza4 = zzako.zza(context, zzpVar.zze + zzpVar.zza + zzpVar.zzb);
        zzlc.zza();
        addView(imageButton3, new FrameLayout.LayoutParams(iZza4, zzako.zza(context, zzpVar.zze + zzpVar.zzd), 17));
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        zzw zzwVar = this.zzb;
        if (zzwVar != null) {
            zzwVar.zzc();
        }
    }

    public final void zza(boolean z, boolean z2) {
        ImageButton imageButton;
        int i;
        if (!z2) {
            imageButton = this.zza;
            i = 0;
        } else if (z) {
            imageButton = this.zza;
            i = 4;
        } else {
            imageButton = this.zza;
            i = 8;
        }
        imageButton.setVisibility(i);
    }
}
