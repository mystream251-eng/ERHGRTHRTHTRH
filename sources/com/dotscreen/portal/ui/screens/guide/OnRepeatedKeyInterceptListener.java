package com.dotscreen.portal.ui.screens.guide;

import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import androidx.leanback.widget.BaseGridView;
import androidx.leanback.widget.VerticalGridView;
import com.android.tv.common.WeakHandler;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;

/* JADX INFO: loaded from: classes.dex */
public class OnRepeatedKeyInterceptListener implements BaseGridView.OnKeyInterceptListener {
    private static final boolean DEBUG = false;
    private static final int MSG_MOVE_FOCUS = 1000;
    private static final String TAG = "OnRepeatedKeyListener";
    private int mDirection;
    private boolean mFocusAccelerated;
    private final MyHandler mHandler = new MyHandler();
    private long mRepeatedKeyInterval;
    private final VerticalGridView mView;
    private static final int[] THRESHOLD_FAST_FOCUS_CHANGE_TIME_MS = {2000, 5000};
    private static final int[] MAX_SKIPPED_VIEW_COUNT = {1, 4};

    public OnRepeatedKeyInterceptListener(VerticalGridView verticalGridView) {
        this.mView = verticalGridView;
    }

    public boolean isFocusAccelerated() {
        return this.mFocusAccelerated;
    }

    @Override // androidx.leanback.widget.BaseGridView.OnKeyInterceptListener
    public boolean onInterceptKeyEvent(KeyEvent keyEvent) {
        this.mHandler.removeMessages(1000);
        if (keyEvent.getKeyCode() != 19 && keyEvent.getKeyCode() != 20) {
            return false;
        }
        long eventTime = keyEvent.getEventTime() - keyEvent.getDownTime();
        if (eventTime < THRESHOLD_FAST_FOCUS_CHANGE_TIME_MS[0] || keyEvent.isCanceled()) {
            this.mFocusAccelerated = false;
            return false;
        }
        this.mDirection = keyEvent.getKeyCode() == 19 ? 33 : TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
        int i = MAX_SKIPPED_VIEW_COUNT[0];
        int i2 = 1;
        while (true) {
            int[] iArr = THRESHOLD_FAST_FOCUS_CHANGE_TIME_MS;
            if (i2 >= iArr.length || iArr[i2] >= eventTime) {
                break;
            }
            i = MAX_SKIPPED_VIEW_COUNT[i2];
            i2++;
        }
        if (keyEvent.getAction() == 0) {
            this.mRepeatedKeyInterval = eventTime / ((long) keyEvent.getRepeatCount());
            this.mFocusAccelerated = true;
        } else {
            this.mFocusAccelerated = false;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.mHandler.sendEmptyMessageDelayed(1000, (this.mRepeatedKeyInterval * ((long) i3)) / ((long) (i + 1)));
        }
        return false;
    }

    private static class MyHandler extends WeakHandler<OnRepeatedKeyInterceptListener> {
        private MyHandler(OnRepeatedKeyInterceptListener onRepeatedKeyInterceptListener) {
            super(onRepeatedKeyInterceptListener);
        }

        @Override // com.android.tv.common.WeakHandler
        public void handleMessage(Message message, OnRepeatedKeyInterceptListener onRepeatedKeyInterceptListener) {
            View viewFindFocus;
            View viewFocusSearch;
            if (message.what != 1000 || (viewFindFocus = onRepeatedKeyInterceptListener.mView.findFocus()) == null || (viewFocusSearch = viewFindFocus.focusSearch(onRepeatedKeyInterceptListener.mDirection)) == null || viewFocusSearch == viewFindFocus) {
                return;
            }
            viewFocusSearch.requestFocus(onRepeatedKeyInterceptListener.mDirection);
        }
    }
}
