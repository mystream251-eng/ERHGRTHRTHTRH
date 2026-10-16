package com.dotscreen.portal.ui.screens.onboarding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tvinput.SyncTVChannelsWorkerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChannelScanActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"com/dotscreen/portal/ui/screens/onboarding/ChannelScanActivity$scanStatusReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ChannelScanActivity$scanStatusReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ ChannelScanActivity this$0;

    ChannelScanActivity$scanStatusReceiver$1(ChannelScanActivity channelScanActivity) {
        this.this$0 = channelScanActivity;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        if (!Intrinsics.areEqual(intent != null ? intent.getAction() : null, CommonUtils.ACTION_SCAN_STATUS_CHANGED) || (stringExtra = intent.getStringExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS)) == null) {
            return;
        }
        switch (stringExtra.hashCode()) {
            case -1915011149:
                if (stringExtra.equals(CommonUtils.SCAN_STATUS_STARTED)) {
                    Log.d(this.this$0.TAG, "SCAN_STATUS_STARTED");
                    ChannelScanActivity.access$getMessageTextView$p(this.this$0).setText(this.this$0.getString(R.string.channel_scan_message_scanning));
                    ChannelScanActivity.access$getProgressBar$p(this.this$0).setVisibility(0);
                }
                break;
            case -1766918726:
                if (stringExtra.equals(CommonUtils.SCAN_STATUS_ERROR)) {
                    String stringExtra2 = intent.getStringExtra(SyncTVChannelsWorkerKt.getBUNDLE_KEY_SCAN_ERROR_REASON());
                    Log.d(this.this$0.TAG, "SCAN_STATUS_ERROR => " + stringExtra2);
                    ChannelScanActivity.onError$default(this.this$0, false, 1, null);
                }
                break;
            case -1226266752:
                if (stringExtra.equals(CommonUtils.SCAN_STATUS_FINISHED)) {
                    Log.d(this.this$0.TAG, "SCAN_STATUS_FINISHED");
                    ChannelScanActivity.access$getMessageTextView$p(this.this$0).setText(this.this$0.getString(R.string.channel_scan_message_scan_done));
                    ChannelScanActivity.access$getProgressBar$p(this.this$0).setVisibility(4);
                    this.this$0.handler.postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.onboarding.ChannelScanActivity$scanStatusReceiver$1$onReceive$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.this$0.this$0.setResult(-1);
                            ChannelScanActivity.access$getApp$p(this.this$0.this$0).setChannelScanning(false);
                            this.this$0.this$0.finish();
                        }
                    }, 3000L);
                }
                break;
            case 1893135650:
                if (stringExtra.equals(CommonUtils.SCAN_STATUS_SCANNED)) {
                    Log.d(this.this$0.TAG, "SCAN_STATUS_SCANNED");
                }
                break;
        }
    }
}
