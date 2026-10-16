package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.ViewSwitcher;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzakn;
import com.google.android.gms.internal.zzaof;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbv extends ViewSwitcher {
    private final zzajc zza;
    private final zzakn zzb;
    private boolean zzc;

    public zzbv(Context context, String str, String str2, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        super(context);
        zzajc zzajcVar = new zzajc(context);
        this.zza = zzajcVar;
        zzajcVar.zza(str);
        this.zza.zzb(str2);
        this.zzc = true;
        if (context instanceof Activity) {
            this.zzb = new zzakn((Activity) context, this, onGlobalLayoutListener, onScrollChangedListener);
        } else {
            this.zzb = new zzakn(null, this, onGlobalLayoutListener, onScrollChangedListener);
        }
        this.zzb.zza();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        zzakn zzaknVar = this.zzb;
        if (zzaknVar != null) {
            zzaknVar.zzc();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        zzakn zzaknVar = this.zzb;
        if (zzaknVar != null) {
            zzaknVar.zzd();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.zzc) {
            return false;
        }
        this.zza.zza(motionEvent);
        return false;
    }

    @Override // android.widget.ViewAnimator, android.view.ViewGroup
    public final void removeAllViews() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            KeyEvent.Callback childAt = getChildAt(i2);
            if (childAt != null && (childAt instanceof zzaof)) {
                arrayList.add((zzaof) childAt);
            }
        }
        super.removeAllViews();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((zzaof) obj).destroy();
        }
    }

    public final zzajc zza() {
        return this.zza;
    }

    public final void zzb() {
        zzahw.zza("Disable position monitoring on adFrame.");
        zzakn zzaknVar = this.zzb;
        if (zzaknVar != null) {
            zzaknVar.zzb();
        }
    }

    public final void zzc() {
        zzahw.zza("Enable debug gesture detector on adFrame.");
        this.zzc = true;
    }

    public final void zzd() {
        zzahw.zza("Disable debug gesture detector on adFrame.");
        this.zzc = false;
    }
}
