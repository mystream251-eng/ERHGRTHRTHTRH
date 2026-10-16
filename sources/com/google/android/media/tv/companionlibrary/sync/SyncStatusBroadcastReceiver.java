package com.google.android.media.tv.companionlibrary.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
public final class SyncStatusBroadcastReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "SyncStatusBroadcastRcvr";
    private boolean mFinishedScan = false;
    private final String mInputId;
    private final SyncListener mSyncListener;

    public interface SyncListener {
        void onScanError(int i);

        void onScanFinished();

        void onScanStepCompleted(int i, int i2);

        void onScannedChannel(CharSequence charSequence, CharSequence charSequence2);
    }

    public SyncStatusBroadcastReceiver(String str, SyncListener syncListener) {
        this.mInputId = str;
        this.mSyncListener = syncListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        if (this.mFinishedScan || (stringExtra = intent.getStringExtra(EpgSyncJobService.BUNDLE_KEY_INPUT_ID)) == null || !stringExtra.equals(this.mInputId)) {
            return;
        }
        String stringExtra2 = intent.getStringExtra(EpgSyncJobService.SYNC_STATUS);
        if (stringExtra2.equals(EpgSyncJobService.SYNC_STARTED)) {
            return;
        }
        if (stringExtra2.equals(EpgSyncJobService.SYNC_SCANNED)) {
            this.mSyncListener.onScanStepCompleted(intent.getIntExtra(EpgSyncJobService.BUNDLE_KEY_CHANNELS_SCANNED, 0), intent.getIntExtra(EpgSyncJobService.BUNDLE_KEY_CHANNEL_COUNT, 0));
            this.mSyncListener.onScannedChannel(intent.getStringExtra(EpgSyncJobService.BUNDLE_KEY_SCANNED_CHANNEL_DISPLAY_NAME), intent.getStringExtra(EpgSyncJobService.BUNDLE_KEY_SCANNED_CHANNEL_DISPLAY_NUMBER));
        } else if (stringExtra2.equals(EpgSyncJobService.SYNC_FINISHED)) {
            this.mFinishedScan = true;
            this.mSyncListener.onScanFinished();
        } else if (stringExtra2.equals(EpgSyncJobService.SYNC_ERROR)) {
            this.mSyncListener.onScanError(intent.getIntExtra(EpgSyncJobService.BUNDLE_KEY_ERROR_REASON, 0));
        }
    }
}
