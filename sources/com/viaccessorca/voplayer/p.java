package com.viaccessorca.voplayer;

import android.graphics.Bitmap;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
class p extends ImageView implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f163a;
    private HashMap<Integer, b> b;
    private int c;
    private int d;
    private int e;
    private int[] f;
    private int g;
    private int h;

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Bitmap f164a;

        a(Bitmap bitmap) {
            this.f164a = bitmap;
        }

        @Override // java.lang.Runnable
        public void run() {
            p.this.setImageBitmap(this.f164a);
            p.this.e = (int) System.currentTimeMillis();
        }
    }

    private class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Bitmap f165a;
        Bitmap b;
        boolean c;
        c d;
    }

    private enum c {
        UNKNOWN,
        SHOW,
        HIDE,
        CLEAN
    }

    private void a(Bitmap bitmap, int i) {
        post(new a(bitmap));
    }

    /* JADX WARN: Code duplicated, block: B:119:0x00b5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x00b6 A[Catch: all -> 0x00ee, TryCatch #3 {all -> 0x00ee, blocks: (B:24:0x0039, B:25:0x0046, B:27:0x004c, B:30:0x0058, B:32:0x0064, B:34:0x0068, B:36:0x0074, B:52:0x00b1, B:55:0x00b6, B:57:0x00ba, B:61:0x00c4, B:63:0x00c8, B:65:0x00cc, B:67:0x00d0, B:68:0x00dc, B:37:0x0080, B:39:0x0084, B:43:0x008f, B:45:0x0095, B:47:0x009d, B:48:0x00a4, B:50:0x00a8, B:51:0x00af, B:42:0x008b, B:69:0x00e8), top: B:105:0x0039 }] */
    @Override // java.lang.Runnable
    public synchronized void run() {
        int length;
        long j = 0;
        boolean z = true;
        while (this.f163a) {
            if (!z) {
                try {
                    if (this.f == null || this.g <= 0) {
                        wait();
                    } else {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (j < jCurrentTimeMillis) {
                            j = ((long) this.g) + jCurrentTimeMillis;
                        }
                        if (jCurrentTimeMillis < j) {
                            wait(j - jCurrentTimeMillis);
                        }
                        j += (long) this.g;
                    }
                } catch (InterruptedException unused) {
                }
            }
            if (this.f163a) {
                try {
                    synchronized (this.b) {
                        try {
                            boolean z2 = false;
                            length = -1;
                            for (Integer num : this.b.keySet()) {
                                if (!this.f163a) {
                                    break;
                                }
                                b bVar = this.b.get(num);
                                if (this.f == null || this.g <= 0) {
                                    if (true == bVar.c) {
                                        if (c.HIDE != bVar.d) {
                                            c cVar = c.CLEAN;
                                            c cVar2 = bVar.d;
                                        }
                                        if (c.CLEAN == bVar.d) {
                                            bVar.d = c.SHOW;
                                            if (bVar.f165a != null) {
                                                bVar.f165a.recycle();
                                                bVar.f165a = null;
                                            }
                                            if (bVar.b != null) {
                                                bVar.b.recycle();
                                                bVar.b = null;
                                            }
                                        }
                                        bVar.c = false;
                                    }
                                    if (!this.f163a) {
                                        break;
                                    }
                                    if (bVar.f165a == null && c.SHOW == bVar.d) {
                                        if (z2) {
                                            z2 = false;
                                        } else if (this.c > 0 && this.d > 0) {
                                            if (bVar.b == null) {
                                                bVar.b = Bitmap.createScaledBitmap(bVar.f165a, this.c, this.d, false);
                                            }
                                            a(bVar.b, num.intValue());
                                            z2 = true;
                                        }
                                    }
                                } else if (num.intValue() == this.f[this.h]) {
                                    bVar.d = c.SHOW;
                                    length = (this.h + 1) % this.f.length;
                                    if (!this.f163a) {
                                        break;
                                        break;
                                    } else if (bVar.f165a == null) {
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
                        this.h = length;
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
                b bVar2 = this.b.get(it.next());
                if (bVar2.f165a != null) {
                    bVar2.f165a.recycle();
                    bVar2.f165a = null;
                }
                if (bVar2.b != null) {
                    bVar2.b.recycle();
                    bVar2.b = null;
                }
            }
            this.b.clear();
        }
    }
}
