package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpi extends RelativeLayout {
    private static final float[] zza = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    private AnimationDrawable zzb;

    public zzpi(Context context, zzph zzphVar, RelativeLayout.LayoutParams layoutParams) {
        super(context);
        zzbq.zza(zzphVar);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zza, null, null));
        shapeDrawable.getPaint().setColor(zzphVar.zzd());
        setLayoutParams(layoutParams);
        zzbt.zzg().zza(this, shapeDrawable);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(zzphVar.zza())) {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(zzphVar.zza());
            textView.setTextColor(zzphVar.zze());
            textView.setTextSize(zzphVar.zzf());
            zzlc.zza();
            int iZza = zzako.zza(context, 4);
            zzlc.zza();
            textView.setPadding(iZza, 0, zzako.zza(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<zzpj> listZzc = zzphVar.zzc();
        if (listZzc != null && listZzc.size() > 1) {
            this.zzb = new AnimationDrawable();
            Iterator<zzpj> it = listZzc.iterator();
            while (it.hasNext()) {
                try {
                    this.zzb.addFrame((Drawable) zzn.zza(it.next().zza()), zzphVar.zzg());
                } catch (Exception e) {
                    zzahw.zzb("Error while getting drawable.", e);
                }
            }
            zzbt.zzg().zza(imageView, this.zzb);
        } else if (listZzc.size() == 1) {
            try {
                imageView.setImageDrawable((Drawable) zzn.zza(listZzc.get(0).zza()));
            } catch (Exception e2) {
                zzahw.zzb("Error while getting drawable.", e2);
            }
        }
        addView(imageView);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        AnimationDrawable animationDrawable = this.zzb;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        super.onAttachedToWindow();
    }
}
