package com.dotscreen.portal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.util.Log;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tvinput.SyncTVChannelsWorkerKt;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PortalApplication.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/CompletableEmitter;", "subscribe"}, k = 3, mv = {1, 1, 16})
final class PortalApplication$loadConfig$scanChannelsIfNecessary$1 implements CompletableOnSubscribe {
    final /* synthetic */ PortalApplication this$0;

    PortalApplication$loadConfig$scanChannelsIfNecessary$1(PortalApplication portalApplication) {
        this.this$0 = portalApplication;
    }

    @Override // io.reactivex.CompletableOnSubscribe
    public final void subscribe(final CompletableEmitter emitter) {
        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
        Log.w(this.this$0.TAG, "scanChannelsIfNecessary...");
        try {
            Cursor cursorQuery = this.this$0.getContentResolver().query(TvContract.Channels.CONTENT_URI, new String[]{"_id", "package_name", "input_id"}, "type=? OR type=?", new String[]{TvContractCompat.Channels.TYPE_OTHER, TvContractCompat.Channels.TYPE_DVB_C}, null);
            Throwable th = (Throwable) null;
            try {
                Cursor cursor = cursorQuery;
                Intrinsics.checkExpressionValueIsNotNull(cursor, "cursor");
                if (cursor.getCount() == 0) {
                    Log.w(this.this$0.TAG, "No channel detected, forcing a new rescan...");
                    this.this$0.getApplicationContext().registerReceiver(new BroadcastReceiver() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$scanChannelsIfNecessary$1$$special$$inlined$use$lambda$1
                        @Override // android.content.BroadcastReceiver
                        public void onReceive(Context context, Intent intent) {
                            String stringExtra;
                            if (!Intrinsics.areEqual(intent != null ? intent.getAction() : null, CommonUtils.ACTION_SCAN_STATUS_CHANGED) || (stringExtra = intent.getStringExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS)) == null) {
                                return;
                            }
                            int iHashCode = stringExtra.hashCode();
                            if (iHashCode != -1766918726) {
                                if (iHashCode == -1226266752 && stringExtra.equals(CommonUtils.SCAN_STATUS_FINISHED)) {
                                    Log.d(this.this$0.this$0.TAG, "  SCAN_STATUS_FINISHED");
                                    this.this$0.this$0.getApplicationContext().unregisterReceiver(this);
                                    emitter.onComplete();
                                    return;
                                }
                                return;
                            }
                            if (stringExtra.equals(CommonUtils.SCAN_STATUS_ERROR)) {
                                String stringExtra2 = intent.getStringExtra(SyncTVChannelsWorkerKt.getBUNDLE_KEY_SCAN_ERROR_REASON());
                                Log.d(this.this$0.this$0.TAG, "  SCAN_STATUS_ERROR => " + stringExtra2);
                                this.this$0.this$0.getApplicationContext().unregisterReceiver(this);
                                emitter.onComplete();
                            }
                        }
                    }, new IntentFilter(CommonUtils.ACTION_SCAN_STATUS_CHANGED));
                    SyncTVChannelsWorkerKt.scheduleSyncingTVChannels$default(this.this$0, null, true, false, 10, null);
                } else {
                    emitter.onComplete();
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorQuery, th);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(cursorQuery, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            Log.w(this.this$0.TAG, "error", th4);
            emitter.onComplete();
        }
    }
}
