package a.a.a.a.a;

import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public final Object mLock = new Object();
    public boolean mNotified = false;

    public void notifyCallback() {
        synchronized (this.mLock) {
            this.mNotified = true;
            this.mLock.notify();
        }
    }

    public void waitCallback() {
        synchronized (this.mLock) {
            if (!this.mNotified) {
                try {
                    this.mLock.wait(DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
