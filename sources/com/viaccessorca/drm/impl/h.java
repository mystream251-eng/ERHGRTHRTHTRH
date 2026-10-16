package com.viaccessorca.drm.impl;

/* JADX INFO: loaded from: classes2.dex */
public class h extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f39a = false;
    private boolean b = false;
    protected long c;
    protected long d;
    protected VOMediaDrmSession e;

    public h(long j, long j2, VOMediaDrmSession vOMediaDrmSession) {
        this.c = -1L;
        this.d = 60L;
        this.e = null;
        this.c = j;
        this.d = j2;
        this.e = vOMediaDrmSession;
    }

    private synchronized boolean b() {
        return this.f39a;
    }

    private void c() {
        long jCurrentTimeMillis = 0;
        while ((this.c - this.d) - jCurrentTimeMillis > 0) {
            if (b()) {
                return;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException unused) {
            }
            jCurrentTimeMillis += (System.currentTimeMillis() - jCurrentTimeMillis2) / 1000;
        }
        VOMediaDrmSession vOMediaDrmSession = this.e;
        if (vOMediaDrmSession != null) {
            vOMediaDrmSession.a(8);
        }
        this.b = false;
    }

    public void a() {
        this.b = true;
    }

    public synchronized void cancel() {
        this.f39a = true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!this.b) {
            long jCurrentTimeMillis = 0;
            while (jCurrentTimeMillis < 1000) {
                if (b()) {
                    return;
                }
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException unused) {
                }
                jCurrentTimeMillis += System.currentTimeMillis() - jCurrentTimeMillis2;
            }
        }
        if (b()) {
            return;
        }
        c();
    }
}
