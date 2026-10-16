package com.google.android.gms.ads.mediation;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public interface MediationAdapter {

    public static class zza {
        private int zza;

        public final Bundle zza() {
            Bundle bundle = new Bundle();
            bundle.putInt("capabilities", this.zza);
            return bundle;
        }

        public final zza zza(int i) {
            this.zza = 1;
            return this;
        }
    }

    void onDestroy();

    void onPause();

    void onResume();
}
