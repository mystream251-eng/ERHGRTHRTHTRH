package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzlz;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class PublisherAdViewOptions extends zzbgl {
    public static final Parcelable.Creator<PublisherAdViewOptions> CREATOR = new zzc();
    private final boolean zza;
    private final zzly zzb;
    private AppEventListener zzc;

    public static final class Builder {
        private boolean zza = false;
        private AppEventListener zzb;

        public final PublisherAdViewOptions build() {
            return new PublisherAdViewOptions(this);
        }

        public final Builder setAppEventListener(AppEventListener appEventListener) {
            this.zzb = appEventListener;
            return this;
        }

        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zza = z;
            return this;
        }
    }

    private PublisherAdViewOptions(Builder builder) {
        this.zza = builder.zza;
        AppEventListener appEventListener = builder.zzb;
        this.zzc = appEventListener;
        this.zzb = appEventListener != null ? new zzkq(this.zzc) : null;
    }

    PublisherAdViewOptions(boolean z, IBinder iBinder) {
        this.zza = z;
        this.zzb = iBinder != null ? zzlz.zza(iBinder) : null;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzc;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zza;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, getManualImpressionsEnabled());
        zzly zzlyVar = this.zzb;
        zzbgo.zza(parcel, 2, zzlyVar == null ? null : zzlyVar.asBinder(), false);
        zzbgo.zza(parcel, iZza);
    }

    public final zzly zza() {
        return this.zzb;
    }
}
