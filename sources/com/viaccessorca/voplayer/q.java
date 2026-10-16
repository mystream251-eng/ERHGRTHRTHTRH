package com.viaccessorca.voplayer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
class q extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f167a;
    private HashMap<Integer, a> b;
    private int c;
    private int d;
    private float e;
    private float f;
    private int g;
    private int[] h;
    private int i;
    private int j;

    private class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Paint f168a;
        Bitmap b;
        Bitmap c;
        boolean d;
        b e;
    }

    private enum b {
        UNKNOWN,
        SHOW,
        HIDE,
        CLEAN
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Code duplicated, block: B:135:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x00b7 A[Catch: all -> 0x0136, TryCatch #1 {all -> 0x0136, blocks: (B:24:0x0039, B:25:0x0047, B:27:0x004d, B:30:0x0059, B:32:0x0065, B:34:0x0069, B:36:0x0075, B:52:0x00b2, B:55:0x00b7, B:57:0x00bb, B:61:0x00c5, B:63:0x00c9, B:65:0x00cd, B:67:0x00d1, B:68:0x00dd, B:70:0x00e1, B:73:0x00ed, B:75:0x00f7, B:77:0x00fe, B:37:0x0081, B:39:0x0085, B:43:0x0090, B:45:0x0096, B:47:0x009e, B:48:0x00a5, B:50:0x00a9, B:51:0x00b0, B:42:0x008c, B:78:0x010f), top: B:117:0x0039 }] */
    @Override // java.lang.Runnable
    public synchronized void run() {
        Canvas canvasLockCanvas;
        int length;
        long j = 0;
        boolean z = true;
        while (this.f167a) {
            if (!z) {
                try {
                    if (this.h == null || this.i <= 0) {
                        wait();
                    } else {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (j < jCurrentTimeMillis) {
                            j = ((long) this.i) + jCurrentTimeMillis;
                        }
                        if (jCurrentTimeMillis < j) {
                            wait(j - jCurrentTimeMillis);
                        }
                        j += (long) this.i;
                    }
                } catch (InterruptedException unused) {
                }
            }
            if (this.f167a) {
                try {
                    synchronized (this.b) {
                        try {
                            boolean z2 = false;
                            canvasLockCanvas = null;
                            length = -1;
                            for (Integer num : this.b.keySet()) {
                                if (!this.f167a) {
                                    break;
                                }
                                a aVar = this.b.get(num);
                                if (this.h == null || this.i <= 0) {
                                    if (true == aVar.d) {
                                        if (b.HIDE != aVar.e) {
                                            b bVar = b.CLEAN;
                                            b bVar2 = aVar.e;
                                        }
                                        if (b.CLEAN == aVar.e) {
                                            aVar.e = b.SHOW;
                                            if (aVar.b != null) {
                                                aVar.b.recycle();
                                                aVar.b = null;
                                            }
                                            if (aVar.c != null) {
                                                aVar.c.recycle();
                                                aVar.c = null;
                                            }
                                        }
                                        aVar.d = false;
                                    }
                                    if (!this.f167a) {
                                        break;
                                    }
                                    if (aVar.b == null && b.SHOW == aVar.e) {
                                        if (z2) {
                                            z2 = false;
                                        } else if (this.c > 0 && this.d > 0) {
                                            if (aVar.c == null) {
                                                aVar.c = Bitmap.createScaledBitmap(aVar.b, this.c, this.d, false);
                                            }
                                            if (this.g != 0 && this.g != aVar.c.getPixel(0, 0)) {
                                                if (canvasLockCanvas == null && (canvasLockCanvas = getHolder().lockCanvas()) != null) {
                                                    canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                                                }
                                                if (canvasLockCanvas != null) {
                                                    canvasLockCanvas.drawBitmap(aVar.c, this.e, this.f, aVar.f168a);
                                                    System.currentTimeMillis();
                                                    z2 = true;
                                                }
                                            }
                                        }
                                    }
                                } else if (num.intValue() == this.h[this.j]) {
                                    aVar.e = b.SHOW;
                                    length = (this.j + 1) % this.h.length;
                                    if (!this.f167a) {
                                        break;
                                        break;
                                    } else if (aVar.b == null) {
                                    }
                                } else {
                                    continue;
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    if (-1 != length) {
                        this.j = length;
                    }
                    if (canvasLockCanvas == null && (canvasLockCanvas = getHolder().lockCanvas()) != null) {
                        canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    }
                    if (canvasLockCanvas != null) {
                        getHolder().unlockCanvasAndPost(canvasLockCanvas);
                        getHolder().setSizeFromLayout();
                    }
                } catch (Exception unused2) {
                    continue;
                }
            }
            z = false;
        }
        synchronized (this.b) {
            Iterator<Integer> it = this.b.keySet().iterator();
            while (it.hasNext()) {
                a aVar2 = this.b.get(it.next());
                if (aVar2.b != null) {
                    aVar2.b.recycle();
                    aVar2.b = null;
                }
                if (aVar2.c != null) {
                    aVar2.c.recycle();
                    aVar2.c = null;
                }
            }
            this.b.clear();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.f167a) {
            return;
        }
        this.f167a = true;
        new Thread(this).start();
    }

    @Override // android.view.SurfaceHolder.Callback
    public synchronized void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f167a = false;
        notify();
    }
}
