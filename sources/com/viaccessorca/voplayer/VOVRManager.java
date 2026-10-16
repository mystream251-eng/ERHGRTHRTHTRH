package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOVRManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AudioMode f110a;
    private Mode b;
    private Layout c;
    private boolean d = false;
    private int e;
    private float f;
    private int g;
    private int h;
    private int i;
    private VOPlayer j;

    public enum AudioMode {
        OFF,
        BINAURALIZER
    }

    public enum Layout {
        NONE(0),
        MONO_360(1),
        STEREO_360(2),
        STEREO_180(3);

        public final int value;

        Layout(int i) {
            this.value = i;
        }
    }

    public enum Mode {
        OFF(0),
        MONO(1),
        STEREO(2);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f113a;

        Mode(int i) {
            this.f113a = i;
        }
    }

    protected VOVRManager(VOPlayer vOPlayer) {
        a(vOPlayer);
        this.f110a = AudioMode.OFF;
        this.b = Mode.OFF;
        this.c = Layout.NONE;
        this.e = 70;
        this.f = 1.7777778f;
        this.g = 0;
        this.h = 0;
        this.i = 0;
    }

    private void a() {
        VOPlayer vOPlayer = this.j;
        if (vOPlayer != null) {
            vOPlayer.a(this.e, this.f, this.g, this.h, this.i);
        }
    }

    protected void a(VOPlayer vOPlayer) {
        this.j = vOPlayer;
    }

    protected void forceExceptionsForTest(boolean z) {
        this.d = z;
    }

    public Layout geLayout() {
        return this.c;
    }

    public float getAspectRatio() {
        return this.f;
    }

    public AudioMode getAudioMode() {
        return this.f110a;
    }

    public Mode getMode() {
        return this.b;
    }

    public int getPupillaryDistance() {
        return this.g;
    }

    public int getStereoViewportOffsetX() {
        return this.h;
    }

    public int getStereoViewportOffsetY() {
        return this.i;
    }

    public int getZoom() {
        return this.e;
    }

    public boolean isEnabled() {
        return (this.f110a == AudioMode.OFF && this.b == Mode.OFF) ? false : true;
    }

    public void setAspectRatio(float f) {
        this.f = f;
        a();
    }

    public void setAudioMode(AudioMode audioMode) {
        VOPlayer vOPlayer = this.j;
        if (vOPlayer == null) {
            return;
        }
        try {
            if (AudioMode.BINAURALIZER == audioMode) {
                vOPlayer.a(true);
            } else {
                vOPlayer.a(false);
            }
            this.f110a = audioMode;
        } catch (UnsupportedOperationException unused) {
            if (this.d) {
                throw new UnsupportedOperationException("exception vrsamns");
            }
        }
    }

    public void setLayout(Layout layout) {
        if (this.j != null) {
            if (layout == null) {
                layout = Layout.NONE;
            }
            this.c = layout;
            this.j.a(layout.value);
        }
    }

    public void setMode(Mode mode) {
        if (this.j != null) {
            if (mode == null) {
                mode = Mode.OFF;
            }
            this.b = mode;
            this.j.b(mode.f113a);
            if (Mode.STEREO == this.b) {
                a();
            }
        }
    }

    public void setOrientation(float f, float f2, float f3) {
        VOPlayer vOPlayer = this.j;
        if (vOPlayer == null) {
            return;
        }
        if (AudioMode.BINAURALIZER == this.f110a) {
            vOPlayer.a(f, f2, f3);
        }
        if (Mode.OFF != this.b) {
            this.j.b(f, f2, f3);
        }
    }

    public void setPupillaryDistance(int i) {
        this.g = i;
        a();
    }

    public void setStereoViewportOffset(int i, int i2) {
        this.h = i;
        this.i = i2;
        a();
    }

    public void setZoom(int i) {
        this.e = i;
        a();
    }
}
