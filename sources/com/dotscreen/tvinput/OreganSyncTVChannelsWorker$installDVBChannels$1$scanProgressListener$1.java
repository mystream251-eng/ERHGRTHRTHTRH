package com.dotscreen.tvinput;

import android.content.Intent;
import android.util.Log;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.ScanStatusHandler;
import io.reactivex.CompletableEmitter;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import net.oregan.sparq.dtvsupportlibraryaidl.IDVBScanProgressListener;

/* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"com/dotscreen/tvinput/OreganSyncTVChannelsWorker$installDVBChannels$1$scanProgressListener$1", "Lnet/oregan/sparq/dtvsupportlibraryaidl/IDVBScanProgressListener$Stub;", "onScanComplete", "", "numChannelsFound", "", "statusCode", "onScanProgress", "percentComplete", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class OreganSyncTVChannelsWorker$installDVBChannels$1$scanProgressListener$1 extends IDVBScanProgressListener.Stub {
    final /* synthetic */ CompletableEmitter $emitter;
    final /* synthetic */ OreganSyncTVChannelsWorker.AnonymousClass1 this$0;

    OreganSyncTVChannelsWorker$installDVBChannels$1$scanProgressListener$1(OreganSyncTVChannelsWorker.AnonymousClass1 anonymousClass1, CompletableEmitter completableEmitter) {
        this.this$0 = anonymousClass1;
        this.$emitter = completableEmitter;
    }

    @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVBScanProgressListener
    public void onScanComplete(int numChannelsFound, int statusCode) {
        Log.d(SyncTVChannelsWorkerKt.TAG, "  onScanComplete(numChannelsFound:" + numChannelsFound + ", statusCode:" + statusCode + ')');
        if (statusCode == 0) {
            Log.v(SyncTVChannelsWorkerKt.TAG, "  Status OK waiting for TIF update...");
            OreganSyncTVChannelsWorker.this.handler.postDelayed(new Runnable() { // from class: com.dotscreen.tvinput.OreganSyncTVChannelsWorker$installDVBChannels$1$scanProgressListener$1$onScanComplete$1
                @Override // java.lang.Runnable
                public final void run() {
                    Log.v(SyncTVChannelsWorkerKt.TAG, "  Status OK");
                    OreganSyncTVChannelsWorker.this.makeAllChannelsBrowsable$tvinput_prodRelease();
                    SyncTVChannelsWorkerKt.saveLastDVBScanTime(OreganSyncTVChannelsWorker.this.getContext());
                    SyncTVChannelsWorkerKt.notifyDVBScanFinished(OreganSyncTVChannelsWorker.this.getContext());
                    this.this$0.$emitter.onComplete();
                }
            }, TimeUnit.SECONDS.toMillis(3L));
        } else if (statusCode == 1) {
            SyncTVChannelsWorkerKt.setNetworkIdChanged(OreganSyncTVChannelsWorker.this.getContext(), true);
            this.$emitter.onError(new NetworkIdConflictException("Network ID changed"));
        } else if (statusCode == 2) {
            this.$emitter.onError(new NoChannelFoundException("No channel found"));
        } else {
            this.$emitter.onError(new UnknownError("Unknown DVB scan status code: " + statusCode));
        }
        SyncTVChannelsWorkerKt.scheduleForceSyncCheck(OreganSyncTVChannelsWorker.this.getContext());
    }

    @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVBScanProgressListener
    public void onScanProgress(int percentComplete, int numChannelsFound) {
        Log.d(SyncTVChannelsWorkerKt.TAG, "  onScanProgress(percentComplete:" + percentComplete + ", numChannelsFound:" + numChannelsFound + ')');
        ScanStatusHandler.INSTANCE.setCurrentScanStatus(OreganSyncTVChannelsWorker.this.getContext(), ScanStatusHandler.ScanState.IN_PROGRESS);
        Intent intent = new Intent(CommonUtils.ACTION_SCAN_STATUS_CHANGED);
        intent.putExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS, CommonUtils.SCAN_STATUS_SCANNED);
        OreganSyncTVChannelsWorker.this.getContext().sendBroadcast(intent);
    }
}
