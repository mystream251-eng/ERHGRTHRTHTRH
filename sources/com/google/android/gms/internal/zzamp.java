package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.google.android.gms.ads.internal.zzbt;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzamp extends zzamz implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map<Integer, String> zzc = new HashMap();
    private final zzano zzd;
    private final boolean zze;
    private int zzf;
    private int zzg;
    private MediaPlayer zzh;
    private Uri zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private zzanl zzo;
    private boolean zzp;
    private int zzq;
    private zzamy zzr;

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            zzc.put(-1004, "MEDIA_ERROR_IO");
            zzc.put(-1007, "MEDIA_ERROR_MALFORMED");
            zzc.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
            zzc.put(-110, "MEDIA_ERROR_TIMED_OUT");
            zzc.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzc.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzc.put(1, "MEDIA_ERROR_UNKNOWN");
        zzc.put(1, "MEDIA_INFO_UNKNOWN");
        zzc.put(Integer.valueOf(VOPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzc.put(701, "MEDIA_INFO_BUFFERING_START");
        zzc.put(702, "MEDIA_INFO_BUFFERING_END");
        zzc.put(Integer.valueOf(VOPlayer.MEDIA_INFO_BAD_INTERLEAVING), "MEDIA_INFO_BAD_INTERLEAVING");
        zzc.put(Integer.valueOf(VOPlayer.MEDIA_INFO_NOT_SEEKABLE), "MEDIA_INFO_NOT_SEEKABLE");
        zzc.put(Integer.valueOf(VOPlayer.MEDIA_INFO_METADATA_UPDATE), "MEDIA_INFO_METADATA_UPDATE");
        if (Build.VERSION.SDK_INT >= 19) {
            zzc.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzc.put(902, "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzamp(Context context, boolean z, boolean z2, zzanm zzanmVar, zzano zzanoVar) {
        super(context);
        this.zzf = 0;
        this.zzg = 0;
        setSurfaceTextureListener(this);
        this.zzd = zzanoVar;
        this.zzp = z;
        this.zze = z2;
        zzanoVar.zza(this);
    }

    private final void zza(float f) {
        MediaPlayer mediaPlayer = this.zzh;
        if (mediaPlayer == null) {
            zzahw.zze("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        } else {
            try {
                mediaPlayer.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        }
    }

    private final void zza(boolean z) {
        zzahw.zza("AdMediaPlayerView release");
        zzanl zzanlVar = this.zzo;
        if (zzanlVar != null) {
            zzanlVar.zzb();
            this.zzo = null;
        }
        MediaPlayer mediaPlayer = this.zzh;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.zzh.release();
            this.zzh = null;
            zzb(0);
            if (z) {
                this.zzg = 0;
                this.zzg = 0;
            }
        }
    }

    private final void zzb(int i) {
        if (i == 3) {
            this.zzd.zzc();
            this.zzb.zzb();
        } else if (this.zzf == 3) {
            this.zzd.zzd();
            this.zzb.zzc();
        }
        this.zzf = i;
    }

    private final void zzj() {
        zzahw.zza("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzi == null || surfaceTexture == null) {
            return;
        }
        zza(false);
        try {
            zzbt.zzu();
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.zzh = mediaPlayer;
            mediaPlayer.setOnBufferingUpdateListener(this);
            this.zzh.setOnCompletionListener(this);
            this.zzh.setOnErrorListener(this);
            this.zzh.setOnInfoListener(this);
            this.zzh.setOnPreparedListener(this);
            this.zzh.setOnVideoSizeChangedListener(this);
            this.zzl = 0;
            if (this.zzp) {
                zzanl zzanlVar = new zzanl(getContext());
                this.zzo = zzanlVar;
                zzanlVar.zza(surfaceTexture, getWidth(), getHeight());
                this.zzo.start();
                SurfaceTexture surfaceTextureZzc = this.zzo.zzc();
                if (surfaceTextureZzc != null) {
                    surfaceTexture = surfaceTextureZzc;
                } else {
                    this.zzo.zzb();
                    this.zzo = null;
                }
            }
            this.zzh.setDataSource(getContext(), this.zzi);
            zzbt.zzv();
            this.zzh.setSurface(new Surface(surfaceTexture));
            this.zzh.setAudioStreamType(3);
            this.zzh.setScreenOnWhilePlaying(true);
            this.zzh.prepareAsync();
            zzb(1);
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            String strValueOf = String.valueOf(this.zzi);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 36);
            sb.append("Failed to initialize MediaPlayer at ");
            sb.append(strValueOf);
            zzahw.zzc(sb.toString(), e);
            onError(this.zzh, 1, 0);
        }
    }

    private final void zzk() {
        if (this.zze && zzl() && this.zzh.getCurrentPosition() > 0 && this.zzg != 3) {
            zzahw.zza("AdMediaPlayerView nudging MediaPlayer");
            zza(0.0f);
            this.zzh.start();
            int currentPosition = this.zzh.getCurrentPosition();
            long jZza = zzbt.zzl().zza();
            while (zzl() && this.zzh.getCurrentPosition() == currentPosition && zzbt.zzl().zza() - jZza <= 250) {
            }
            this.zzh.pause();
            zzi();
        }
    }

    private final boolean zzl() {
        int i;
        return (this.zzh == null || (i = this.zzf) == -1 || i == 0 || i == 1) ? false : true;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzl = i;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzahw.zza("AdMediaPlayerView completion");
        zzb(5);
        this.zzg = 5;
        zzaij.zza.post(new zzamr(this));
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzc.get(Integer.valueOf(i));
        String str2 = zzc.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer error: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzahw.zze(sb.toString());
        zzb(-1);
        this.zzg = -1;
        zzaij.zza.post(new zzams(this, str, str2));
        return true;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzc.get(Integer.valueOf(i));
        String str2 = zzc.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer info: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzahw.zza(sb.toString());
        return true;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int defaultSize = getDefaultSize(this.zzj, i);
        int defaultSize2 = getDefaultSize(this.zzk, i2);
        if (this.zzj > 0 && this.zzk > 0 && this.zzo == null) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i4 = this.zzj;
                int i5 = i4 * size2;
                int i6 = this.zzk;
                if (i5 < size * i6) {
                    defaultSize = (i4 * size2) / i6;
                } else {
                    if (i4 * size2 > size * i6) {
                        defaultSize2 = (i6 * size) / i4;
                        defaultSize = size;
                    }
                }
                defaultSize2 = size2;
            } else if (mode == 1073741824) {
                int i7 = (this.zzk * size) / this.zzj;
                if (mode2 != Integer.MIN_VALUE || i7 <= size2) {
                    defaultSize2 = i7;
                    defaultSize = size;
                }
                defaultSize2 = size2;
            } else if (mode2 == 1073741824) {
                int i8 = (this.zzj * size2) / this.zzk;
                defaultSize = (mode != Integer.MIN_VALUE || i8 <= size) ? i8 : size;
                defaultSize2 = size2;
            } else {
                int i9 = this.zzj;
                int i10 = this.zzk;
                if (mode2 != Integer.MIN_VALUE || i10 <= size2) {
                    defaultSize2 = i10;
                } else {
                    i9 = (i9 * size2) / i10;
                    defaultSize2 = size2;
                }
                if (mode != Integer.MIN_VALUE || i9 <= size) {
                    defaultSize = i9;
                } else {
                    defaultSize2 = (this.zzk * size) / this.zzj;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        zzanl zzanlVar = this.zzo;
        if (zzanlVar != null) {
            zzanlVar.zza(defaultSize, defaultSize2);
        }
        if (Build.VERSION.SDK_INT == 16) {
            int i11 = this.zzm;
            if ((i11 > 0 && i11 != defaultSize) || ((i3 = this.zzn) > 0 && i3 != defaultSize2)) {
                zzk();
            }
            this.zzm = defaultSize;
            this.zzn = defaultSize2;
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzahw.zza("AdMediaPlayerView prepared");
        zzb(2);
        this.zzd.zza();
        zzaij.zza.post(new zzamq(this));
        this.zzj = mediaPlayer.getVideoWidth();
        this.zzk = mediaPlayer.getVideoHeight();
        int i = this.zzq;
        if (i != 0) {
            zza(i);
        }
        zzk();
        int i2 = this.zzj;
        int i3 = this.zzk;
        StringBuilder sb = new StringBuilder(62);
        sb.append("AdMediaPlayerView stream dimensions: ");
        sb.append(i2);
        sb.append(" x ");
        sb.append(i3);
        zzahw.zzd(sb.toString());
        if (this.zzg == 3) {
            zzc();
        }
        zzi();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzahw.zza("AdMediaPlayerView surface created");
        zzj();
        zzaij.zza.post(new zzamt(this));
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzahw.zza("AdMediaPlayerView surface destroyed");
        MediaPlayer mediaPlayer = this.zzh;
        if (mediaPlayer != null && this.zzq == 0) {
            this.zzq = mediaPlayer.getCurrentPosition();
        }
        zzanl zzanlVar = this.zzo;
        if (zzanlVar != null) {
            zzanlVar.zzb();
        }
        zzaij.zza.post(new zzamv(this));
        zza(true);
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzahw.zza("AdMediaPlayerView surface changed");
        boolean z = this.zzg == 3;
        boolean z2 = this.zzj == i && this.zzk == i2;
        if (this.zzh != null && z && z2) {
            int i3 = this.zzq;
            if (i3 != 0) {
                zza(i3);
            }
            zzc();
        }
        zzanl zzanlVar = this.zzo;
        if (zzanlVar != null) {
            zzanlVar.zza(i, i2);
        }
        zzaij.zza.post(new zzamu(this, i, i2));
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzd.zzb(this);
        this.zza.zza(surfaceTexture, this.zzr);
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdMediaPlayerView size changed: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        zzahw.zza(sb.toString());
        this.zzj = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.zzk = videoHeight;
        if (this.zzj == 0 || videoHeight == 0) {
            return;
        }
        requestLayout();
    }

    @Override // android.view.View
    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
        sb.append(name);
        sb.append("@");
        sb.append(hexString);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.zzamz
    public final String zza() {
        String str = this.zzp ? " spherical" : "";
        return str.length() != 0 ? "MediaPlayer".concat(str) : new String("MediaPlayer");
    }

    @Override // com.google.android.gms.internal.zzamz
    public final void zza(float f, float f2) {
        zzanl zzanlVar = this.zzo;
        if (zzanlVar != null) {
            zzanlVar.zza(f, f2);
        }
    }

    @Override // com.google.android.gms.internal.zzamz
    public final void zza(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("AdMediaPlayerView seek ");
        sb.append(i);
        zzahw.zza(sb.toString());
        if (!zzl()) {
            this.zzq = i;
        } else {
            this.zzh.seekTo(i);
            this.zzq = 0;
        }
    }

    @Override // com.google.android.gms.internal.zzamz
    public final void zza(zzamy zzamyVar) {
        this.zzr = zzamyVar;
    }

    @Override // com.google.android.gms.internal.zzamz
    public final void zza(String str) {
        Uri uri = Uri.parse(str);
        zzin zzinVarZza = zzin.zza(uri);
        if (zzinVarZza != null) {
            uri = Uri.parse(zzinVarZza.zza);
        }
        this.zzi = uri;
        this.zzq = 0;
        zzj();
        requestLayout();
        invalidate();
    }

    @Override // com.google.android.gms.internal.zzamz
    public final void zzb() {
        zzahw.zza("AdMediaPlayerView stop");
        MediaPlayer mediaPlayer = this.zzh;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.zzh.release();
            this.zzh = null;
            zzb(0);
            this.zzg = 0;
        }
        this.zzd.zzb();
    }

    @Override // com.google.android.gms.internal.zzamz
    public final void zzc() {
        zzahw.zza("AdMediaPlayerView play");
        if (zzl()) {
            this.zzh.start();
            zzb(3);
            this.zza.zza();
            zzaij.zza.post(new zzamw(this));
        }
        this.zzg = 3;
    }

    @Override // com.google.android.gms.internal.zzamz
    public final void zzd() {
        zzahw.zza("AdMediaPlayerView pause");
        if (zzl() && this.zzh.isPlaying()) {
            this.zzh.pause();
            zzb(4);
            zzaij.zza.post(new zzamx(this));
        }
        this.zzg = 4;
    }

    @Override // com.google.android.gms.internal.zzamz
    public final int zze() {
        if (zzl()) {
            return this.zzh.getDuration();
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.zzamz
    public final int zzf() {
        if (zzl()) {
            return this.zzh.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.zzamz
    public final int zzg() {
        MediaPlayer mediaPlayer = this.zzh;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoWidth();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.zzamz
    public final int zzh() {
        MediaPlayer mediaPlayer = this.zzh;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoHeight();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.zzamz, com.google.android.gms.internal.zzanr
    public final void zzi() {
        zza(this.zzb.zza());
    }
}
