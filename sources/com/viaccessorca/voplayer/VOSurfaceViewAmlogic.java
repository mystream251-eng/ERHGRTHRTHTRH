package com.viaccessorca.voplayer;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* JADX INFO: loaded from: classes2.dex */
class VOSurfaceViewAmlogic extends SurfaceView implements SurfaceHolder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f100a;
    private int b;
    private int c;
    private int d;
    private int e;
    private float f;
    private int g;
    int h;

    protected VOSurfaceViewAmlogic(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f100a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 1.0f;
        this.g = 0;
        this.h = 0;
        a(context);
    }

    private native int _get_black_screen_policy();

    private native void _set_black_screen_policy(int i);

    private native void _set_screen_mode(int i);

    private native void _set_virtual_position(int i, int i2, int i3, int i4);

    private void a() {
        Rect rect = new Rect();
        getDrawingRect(rect);
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.b = rect.left + iArr[0];
        this.f100a = rect.top + iArr[1];
        this.c = rect.width();
        this.d = rect.height();
    }

    private void a(Context context) {
        getHolder().addCallback(this);
    }

    private void b() {
        a();
        _set_virtual_position(this.b, this.f100a, this.c, this.d);
        _set_black_screen_policy(1);
    }

    protected void a(float f) {
        this.f = f;
    }

    protected void a(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = this.g;
        double d = this.f;
        if (0.1d < d) {
            i6 = (d < 0.98d || 1.02d < d) ? 1 : 0;
            this.g = i6;
        }
        _set_screen_mode(i6);
        a();
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        if (i >= 0) {
            this.e = i;
        }
        int i7 = (int) (i2 * this.f);
        int i8 = this.e;
        if (i8 == 0) {
            int i9 = this.c;
            int i10 = i9 * i3;
            int i11 = this.d;
            int i12 = i7 * i11;
            if (i10 > i12) {
                int i13 = i12 / i3;
                _set_virtual_position(this.b + ((i9 - i13) / 2), this.f100a, i13, i11);
                return;
            } else {
                int i14 = i10 / i7;
                _set_virtual_position(this.b, this.f100a + ((i11 - i14) / 2), i9, i14);
                return;
            }
        }
        if (i8 == 1) {
            _set_virtual_position(this.b, this.f100a, this.c, this.d);
            return;
        }
        if (i8 != 2) {
            b();
            return;
        }
        int i15 = this.c;
        int i16 = i15 * i3;
        int i17 = this.d;
        int i18 = i7 * i17;
        if (i16 > i18) {
            int i19 = ((i16 / i7) - i17) / 2;
            i4 = this.b;
            i5 = this.f100a - i19;
            i17 += i19;
        } else {
            int i20 = ((i18 / i3) - i15) / 2;
            i4 = this.b - i20;
            i5 = this.f100a;
            i15 += i20;
        }
        _set_virtual_position(i4, i5, i15, i17);
    }

    public void onCreate() {
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        b();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.h = _get_black_screen_policy();
        b();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        _set_screen_mode(0);
        _set_black_screen_policy(this.h);
    }
}
