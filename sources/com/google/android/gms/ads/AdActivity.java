package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzyq;

/* JADX INFO: loaded from: classes2.dex */
public class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzyq zza;

    private final void zza() {
        zzyq zzyqVar = this.zza;
        if (zzyqVar != null) {
            try {
                zzyqVar.zzl();
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward setContentViewSet to ad overlay:", e);
            }
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.zza.zza(i, i2, intent);
        } catch (Exception e) {
            zzaky.zzc("Could not forward onActivityResult to ad overlay:", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        boolean zZze = true;
        try {
            if (this.zza != null) {
                zZze = this.zza.zze();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onBackPressed to ad overlay:", e);
        }
        if (zZze) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zza.zza(zzn.zza(configuration));
        } catch (RemoteException e) {
            zzaky.zzc("Failed to wrap configuration.", e);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        zzyq zzyqVarZza = zzlc.zzb().zza(this);
        this.zza = zzyqVarZza;
        if (zzyqVarZza == null) {
            zzaky.zze("Could not create ad overlay.");
        } else {
            try {
                zzyqVarZza.zza(bundle);
                return;
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward onCreate to ad overlay:", e);
            }
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            if (this.zza != null) {
                this.zza.zzk();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onDestroy to ad overlay:", e);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        try {
            if (this.zza != null) {
                this.zza.zzi();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onPause to ad overlay:", e);
            finish();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.zza != null) {
                this.zza.zzf();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onRestart to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (this.zza != null) {
                this.zza.zzh();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onResume to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.zza != null) {
                this.zza.zzb(bundle);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onSaveInstanceState to ad overlay:", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        try {
            if (this.zza != null) {
                this.zza.zzg();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onStart to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        try {
            if (this.zza != null) {
                this.zza.zzj();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onStop to ad overlay:", e);
            finish();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        super.setContentView(i);
        zza();
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        super.setContentView(view);
        zza();
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zza();
    }
}
