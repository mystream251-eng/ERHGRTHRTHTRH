package com.dotscreen.portal.utils.reporting;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.dotscreen.farkit.network.BackendReachability;
import com.dotscreen.farkit.network.IConnectivityStateChanged;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.network.NetworkStatus;
import com.google.android.gms.common.internal.ImagesContract;
import java.net.URL;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectivityStatusReportManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J \u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J2\u0010\u001c\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010$\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010%\u001a\u00020\u0015H\u0002J\b\u0010&\u001a\u00020\u0015H\u0002J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J*\u0010'\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0019\u001a\u00020!2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010(\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0010\u0010)\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0005H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006*"}, d2 = {"Lcom/dotscreen/portal/utils/reporting/ConnectivityStatusReportManager;", "Lcom/dotscreen/farkit/network/IConnectivityStateChanged;", "networkStateManager", "Lcom/dotscreen/farkit/network/NetworkStateManager;", "initialStatus", "Lcom/dotscreen/farkit/network/NetworkStatus;", "isBackendReachable", "", "voPlayerUniqueId", "", "serialNumber", "(Lcom/dotscreen/farkit/network/NetworkStateManager;Lcom/dotscreen/farkit/network/NetworkStatus;ZLjava/lang/String;Ljava/lang/String;)V", "isConnectedToInternet", "onGoingBackendIssueReport", "Lcom/dotscreen/portal/utils/reporting/NoBackendReport;", "onGoingNoInternetReport", "Lcom/dotscreen/portal/utils/reporting/NoInternetReport;", "getSerialNumber", "()Ljava/lang/String;", "getVoPlayerUniqueId", "doIfAllOperationsCancelled", "", "totalTimeElapsed", "", "doIfBackendOK", NotificationCompat.CATEGORY_STATUS, "doIfInternetAvailable", "isNetworkValidated", "doIfNoBackend", ImagesContract.URL, "Ljava/net/URL;", "error", "", "Lcom/dotscreen/farkit/network/NetworkStatus$ConnectedNoBackendAccess;", "reachability", "Lcom/dotscreen/farkit/network/BackendReachability;", "doIfNoInternet", "endNoBackendReport", "endNoInternetReport", "startNoBackendReport", "startNoInternetReport", "updateInternetStatus", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ConnectivityStatusReportManager implements IConnectivityStateChanged {
    private boolean isBackendReachable;
    private boolean isConnectedToInternet;
    private final NetworkStateManager networkStateManager;
    private NoBackendReport onGoingBackendIssueReport;
    private NoInternetReport onGoingNoInternetReport;
    private final String serialNumber;
    private final String voPlayerUniqueId;

    @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
    public void doIfAllOperationsCancelled(long totalTimeElapsed) {
    }

    public ConnectivityStatusReportManager(NetworkStateManager networkStateManager, NetworkStatus initialStatus, boolean z, String voPlayerUniqueId, String serialNumber) {
        Intrinsics.checkParameterIsNotNull(networkStateManager, "networkStateManager");
        Intrinsics.checkParameterIsNotNull(initialStatus, "initialStatus");
        Intrinsics.checkParameterIsNotNull(voPlayerUniqueId, "voPlayerUniqueId");
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        this.networkStateManager = networkStateManager;
        this.voPlayerUniqueId = voPlayerUniqueId;
        this.serialNumber = serialNumber;
        Log.v(ConnectivityStatusReportManagerKt.LOG_TAG, "Init with status: " + initialStatus + ", and backend reachability: " + z);
        boolean zIsConnectedToInternet = isConnectedToInternet(initialStatus);
        this.isConnectedToInternet = zIsConnectedToInternet;
        this.isBackendReachable = z;
        if (zIsConnectedToInternet) {
            return;
        }
        startNoInternetReport(initialStatus);
    }

    public final String getVoPlayerUniqueId() {
        return this.voPlayerUniqueId;
    }

    public final String getSerialNumber() {
        return this.serialNumber;
    }

    private final boolean isConnectedToInternet(NetworkStatus status) {
        if ((status instanceof NetworkStatus.Connected) || (status instanceof NetworkStatus.ConnectedNoBackendAccess)) {
            return true;
        }
        boolean z = status instanceof NetworkStatus.NotConnected;
        return false;
    }

    private final void updateInternetStatus(NetworkStatus status) {
        this.isConnectedToInternet = isConnectedToInternet(status);
    }

    private final void startNoInternetReport(NetworkStatus status) {
        NoInternetReport noInternetReport = new NoInternetReport(this.networkStateManager, this.voPlayerUniqueId, this.serialNumber, status);
        this.onGoingNoInternetReport = noInternetReport;
        if (noInternetReport == null) {
            Intrinsics.throwNpe();
        }
        noInternetReport.startReport(new Date());
    }

    private final void endNoInternetReport() {
        NoInternetReport noInternetReport = this.onGoingNoInternetReport;
        if (noInternetReport == null) {
            Intrinsics.throwNpe();
        }
        noInternetReport.endReport(new Date());
        this.onGoingNoInternetReport = (NoInternetReport) null;
    }

    private final void startNoBackendReport(URL url, NetworkStatus.ConnectedNoBackendAccess status, Throwable error, BackendReachability reachability) {
        String str = this.serialNumber;
        NoBackendReport noBackendReport = new NoBackendReport(this.networkStateManager, this.voPlayerUniqueId, str, status, url, error, reachability);
        this.onGoingBackendIssueReport = noBackendReport;
        if (noBackendReport == null) {
            Intrinsics.throwNpe();
        }
        noBackendReport.startReport(new Date());
    }

    private final void endNoBackendReport() {
        NoBackendReport noBackendReport = this.onGoingBackendIssueReport;
        if (noBackendReport == null) {
            Intrinsics.throwNpe();
        }
        noBackendReport.endReport(new Date());
        this.onGoingBackendIssueReport = (NoBackendReport) null;
    }

    @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
    public void doIfNoInternet(NetworkStatus status, long totalTimeElapsed) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        if (this.onGoingNoInternetReport == null) {
            startNoInternetReport(status);
        }
        updateInternetStatus(status);
    }

    @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
    public void doIfInternetAvailable(NetworkStatus status, boolean isNetworkValidated, long totalTimeElapsed) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        if (this.onGoingNoInternetReport != null) {
            endNoInternetReport();
        }
        updateInternetStatus(status);
    }

    @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
    public void doIfNoBackend(URL url, Throwable error, NetworkStatus.ConnectedNoBackendAccess status, long totalTimeElapsed, BackendReachability reachability) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(reachability, "reachability");
        if (this.onGoingBackendIssueReport == null) {
            startNoBackendReport(url, status, error, reachability);
        }
    }

    @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
    public void doIfBackendOK(NetworkStatus status, long totalTimeElapsed) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        if (this.onGoingBackendIssueReport != null) {
            endNoBackendReport();
        }
    }
}
