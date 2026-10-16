package com.google.android.gms.ads;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zznr;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class VideoController {
    public static final int PLAYBACK_STATE_ENDED = 3;
    public static final int PLAYBACK_STATE_PAUSED = 2;
    public static final int PLAYBACK_STATE_PLAYING = 1;
    public static final int PLAYBACK_STATE_READY = 5;
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object zza = new Object();
    private zzmm zzb;
    private VideoLifecycleCallbacks zzc;

    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }

        public void onVideoMute(boolean z) {
        }

        public void onVideoPause() {
        }

        public void onVideoPlay() {
        }

        public void onVideoStart() {
        }
    }

    public final float getAspectRatio() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return 0.0f;
            }
            try {
                return this.zzb.zzg();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call getAspectRatio on video controller.", e);
                return 0.0f;
            }
        }
    }

    public final int getPlaybackState() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return 0;
            }
            try {
                return this.zzb.zzd();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call getPlaybackState on video controller.", e);
                return 0;
            }
        }
    }

    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.zza) {
            videoLifecycleCallbacks = this.zzc;
        }
        return videoLifecycleCallbacks;
    }

    public final boolean hasVideoContent() {
        boolean z;
        synchronized (this.zza) {
            z = this.zzb != null;
        }
        return z;
    }

    public final boolean isClickToExpandEnabled() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return false;
            }
            try {
                return this.zzb.zzj();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call isClickToExpandEnabled.", e);
                return false;
            }
        }
    }

    public final boolean isCustomControlsEnabled() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return false;
            }
            try {
                return this.zzb.zzh();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call isUsingCustomPlayerControls.", e);
                return false;
            }
        }
    }

    public final boolean isMuted() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return true;
            }
            try {
                return this.zzb.zzc();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call isMuted on video controller.", e);
                return true;
            }
        }
    }

    public final void mute(boolean z) {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return;
            }
            try {
                this.zzb.zza(z);
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call mute on video controller.", e);
            }
        }
    }

    public final void pause() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return;
            }
            try {
                this.zzb.zzb();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call pause on video controller.", e);
            }
        }
    }

    public final void play() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return;
            }
            try {
                this.zzb.zza();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call play on video controller.", e);
            }
        }
    }

    public final void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        zzbq.zza(videoLifecycleCallbacks, "VideoLifecycleCallbacks may not be null.");
        synchronized (this.zza) {
            this.zzc = videoLifecycleCallbacks;
            if (this.zzb == null) {
                return;
            }
            try {
                this.zzb.zza(new zznr(videoLifecycleCallbacks));
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
            }
        }
    }

    public final zzmm zza() {
        zzmm zzmmVar;
        synchronized (this.zza) {
            zzmmVar = this.zzb;
        }
        return zzmmVar;
    }

    public final void zza(zzmm zzmmVar) {
        synchronized (this.zza) {
            this.zzb = zzmmVar;
            if (this.zzc != null) {
                setVideoLifecycleCallbacks(this.zzc);
            }
        }
    }
}
