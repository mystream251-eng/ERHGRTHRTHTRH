package com.dotscreen.portal.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import kotlin.Metadata;

/* JADX INFO: compiled from: DebouncedLifecycleObserver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\t\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\fH\u0003J\b\u0010\u000e\u001a\u00020\fH&J\b\u0010\u000f\u001a\u00020\fH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/utils/DebouncedLifecycleObserver;", "Landroidx/lifecycle/LifecycleObserver;", "()V", "MSG_DO_PAUSE", "", "TAG", "", "kotlin.jvm.PlatformType", "handler", "com/dotscreen/portal/utils/DebouncedLifecycleObserver$handler$1", "Lcom/dotscreen/portal/utils/DebouncedLifecycleObserver$handler$1;", "onPause", "", "onPauseInternal", "onResume", "onResumeInternal", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class DebouncedLifecycleObserver implements LifecycleObserver {
    private final String TAG = DebouncedLifecycleObserver.class.getSimpleName();
    private final int MSG_DO_PAUSE = 1;
    private final DebouncedLifecycleObserver$handler$1 handler = new Handler() { // from class: com.dotscreen.portal.utils.DebouncedLifecycleObserver$handler$1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg == null || msg.what != this.this$0.MSG_DO_PAUSE) {
                return;
            }
            Log.v(this.this$0.TAG, "onPause");
            this.this$0.onPause();
        }
    };

    public abstract void onPause();

    public abstract void onResume();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private final void onPauseInternal() {
        Log.v(this.TAG, "onPauseInternal");
        sendEmptyMessageDelayed(this.MSG_DO_PAUSE, 200L);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private final void onResumeInternal() {
        Log.v(this.TAG, "onResumeInternal");
        if (!hasMessages(this.MSG_DO_PAUSE)) {
            Log.v(this.TAG, "onResume");
            onResume();
        }
        removeMessages(this.MSG_DO_PAUSE);
    }
}
