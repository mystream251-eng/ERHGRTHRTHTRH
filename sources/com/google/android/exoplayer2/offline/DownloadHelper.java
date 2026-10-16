package com.google.android.exoplayer2.offline;

import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.source.TrackGroupArray;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class DownloadHelper {

    public interface Callback {
        void onPrepareError(DownloadHelper downloadHelper, IOException iOException);

        void onPrepared(DownloadHelper downloadHelper);
    }

    public abstract DownloadAction getDownloadAction(byte[] bArr, List<TrackKey> list);

    public abstract int getPeriodCount();

    public abstract DownloadAction getRemoveAction(byte[] bArr);

    public abstract TrackGroupArray getTrackGroups(int i);

    protected abstract void prepareInternal() throws IOException;

    public void prepare(Callback callback) {
        new AnonymousClass1(new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()), callback).start();
    }

    /* JADX INFO: renamed from: com.google.android.exoplayer2.offline.DownloadHelper$1, reason: invalid class name */
    class AnonymousClass1 extends Thread {
        final /* synthetic */ Callback val$callback;
        final /* synthetic */ Handler val$handler;

        AnonymousClass1(Handler handler, Callback callback) {
            this.val$handler = handler;
            this.val$callback = callback;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                DownloadHelper.this.prepareInternal();
                Handler handler = this.val$handler;
                final Callback callback = this.val$callback;
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.offline.-$$Lambda$DownloadHelper$1$NJSYwfqmwQZzGoahNhNZN-HV0Ik
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$run$0$DownloadHelper$1(callback);
                    }
                });
            } catch (IOException e) {
                Handler handler2 = this.val$handler;
                final Callback callback2 = this.val$callback;
                handler2.post(new Runnable() { // from class: com.google.android.exoplayer2.offline.-$$Lambda$DownloadHelper$1$wpC3MI-R3oiOTWYi5AbWzFrvt8g
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$run$1$DownloadHelper$1(callback2, e);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$run$0$DownloadHelper$1(Callback callback) {
            callback.onPrepared(DownloadHelper.this);
        }

        public /* synthetic */ void lambda$run$1$DownloadHelper$1(Callback callback, IOException iOException) {
            callback.onPrepareError(DownloadHelper.this, iOException);
        }
    }
}
