package com.dotscreen.farkit.network;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import java.net.URL;
import kotlin.Metadata;

/* JADX INFO: compiled from: IConnectivityStateChanged.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&J2\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/farkit/network/IConnectivityStateChanged;", "", "doIfAllOperationsCancelled", "", "totalTimeElapsed", "", "doIfBackendOK", NotificationCompat.CATEGORY_STATUS, "Lcom/dotscreen/farkit/network/NetworkStatus;", "doIfInternetAvailable", "isNetworkValidated", "", "doIfNoBackend", ImagesContract.URL, "Ljava/net/URL;", "error", "", "Lcom/dotscreen/farkit/network/NetworkStatus$ConnectedNoBackendAccess;", "reachability", "Lcom/dotscreen/farkit/network/BackendReachability;", "doIfNoInternet", "farkit_release"}, k = 1, mv = {1, 1, 16})
public interface IConnectivityStateChanged {
    void doIfAllOperationsCancelled(long totalTimeElapsed);

    void doIfBackendOK(NetworkStatus status, long totalTimeElapsed);

    void doIfInternetAvailable(NetworkStatus status, boolean isNetworkValidated, long totalTimeElapsed);

    void doIfNoBackend(URL url, Throwable error, NetworkStatus.ConnectedNoBackendAccess status, long totalTimeElapsed, BackendReachability reachability);

    void doIfNoInternet(NetworkStatus status, long totalTimeElapsed);
}
