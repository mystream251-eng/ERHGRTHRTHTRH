package com.google.android.exoplayer2.util;

/* JADX INFO: loaded from: classes2.dex */
public final class ConditionVariable {
    private boolean isOpen;

    public synchronized boolean open() {
        if (this.isOpen) {
            return false;
        }
        this.isOpen = true;
        notifyAll();
        return true;
    }

    public synchronized boolean close() {
        boolean z;
        z = this.isOpen;
        this.isOpen = false;
        return z;
    }

    public synchronized void block() throws InterruptedException {
        while (!this.isOpen) {
            wait();
        }
    }

    public synchronized boolean block(long j) throws InterruptedException {
        long jElapsedRealtime = android.os.SystemClock.elapsedRealtime();
        long j2 = j + jElapsedRealtime;
        while (!this.isOpen && jElapsedRealtime < j2) {
            wait(j2 - jElapsedRealtime);
            jElapsedRealtime = android.os.SystemClock.elapsedRealtime();
        }
        return this.isOpen;
    }
}
