package com.dotscreen.portal;

import android.util.Log;
import com.dotscreen.bokit.OnRequestErrorListener;
import com.dotscreen.farkit.network.RequestManager;
import com.google.android.gms.common.internal.ImagesContract;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: PortalApplication.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/dotscreen/portal/PortalApplication$requestErrorListener$1", "Lcom/dotscreen/bokit/OnRequestErrorListener;", "Lcom/dotscreen/farkit/network/RequestManager$OnRequestErrorListener;", "onRequestError", "", "error", "", ImagesContract.URL, "Ljava/net/URL;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PortalApplication$requestErrorListener$1 implements OnRequestErrorListener, RequestManager.OnRequestErrorListener {
    final /* synthetic */ PortalApplication this$0;

    PortalApplication$requestErrorListener$1(PortalApplication portalApplication) {
        this.this$0 = portalApplication;
    }

    @Override // com.dotscreen.bokit.OnRequestErrorListener, com.dotscreen.farkit.network.RequestManager.OnRequestErrorListener
    public void onRequestError(Throwable error, URL url) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        Log.w(this.this$0.TAG, "OnRequestErrorListener: ba:" + this.this$0.backendAvailable + ", dm: " + this.this$0.degradedMode);
        if (this.this$0.ignoreRequestErrors()) {
            Log.w(this.this$0.TAG, "OnRequestErrorListener: Request error temporarily ignored (" + error + ", url: " + url + ')');
            PortalApplication portalApplication = this.this$0;
            StringBuilder sb = new StringBuilder();
            sb.append("Request error");
            sb.append(" (Ignore)");
            portalApplication.showDebugToastMessage(sb.toString());
            return;
        }
        if (this.this$0.getLoginErrorReason() != null || this.this$0.getErrorDuringBootSequence() != null || (this.this$0.backendAvailable && !this.this$0.degradedMode)) {
            Log.w(this.this$0.TAG, "OnRequestErrorListener: Request error: " + error + ", url: " + url, error);
            if (this.this$0.shouldTriggerLimitedMode(error, url)) {
                this.this$0.ignoreRequestErrors = true;
                int iRandom = RangesKt.random(new IntRange(0, 1000), Random.INSTANCE);
                try {
                    try {
                        if (!this.this$0.getConnectivityLock().tryLock(100L, TimeUnit.MILLISECONDS)) {
                            Log.e(this.this$0.TAG, "----Lock is already occupied, rejecting : " + error);
                        } else {
                            this.this$0.checkNetworkJob = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain())), null, null, new PortalApplication$requestErrorListener$1$onRequestError$1(this, iRandom, url, error, null), 3, null);
                        }
                    } catch (InterruptedException unused) {
                        Log.e(this.this$0.TAG, "---Thread was interrupted: " + error);
                    }
                    return;
                } finally {
                    Log.d(this.this$0.TAG, "---release connectivity lock (B)");
                    this.this$0.getConnectivityLock().unlock();
                }
            }
            Log.w(this.this$0.TAG, "Connectivity check ignored for known errors");
            return;
        }
        this.this$0.showDebugToastMessage("Request error(Ignored; Degraded mode)");
        Log.w(this.this$0.TAG, "OnRequestErrorListener: Request error ignored in degraded mode (" + error + ", url: " + url + ')');
    }
}
