package com.dotscreen.portal;

import androidx.lifecycle.LiveData;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.farkit.analytics.AnalyticsManager;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.updater.UpdaterInfo;
import com.dotscreen.portal.utils.ApplicationsManager;
import com.dotscreen.portal.utils.BootSequenceManager;
import com.dotscreen.portal.utils.FeaturesManager;
import com.dotscreen.portal.utils.MegacableErrorManager;
import com.dotscreen.tv.common.INetRequestProvider;
import com.dotscreen.tv.common.utils.Device;
import com.dotscreen.tv.common.utils.TimeshiftingHelper;
import com.dotscreen.tv.common.utils.notifications.NotificationManager;
import io.reactivex.Single;

/* JADX INFO: loaded from: classes.dex */
public interface PortalApplicationSingletons extends INetRequestProvider {
    /* JADX INFO: renamed from: bootWithoutInternet */
    boolean getBootWithoutInternet();

    void fetchCurrentDeviceInfo();

    AnalyticsManager getAnalyticsManager();

    ApplicationsManager getApplicationsManager();

    BOService getBOService();

    BootSequenceManager getBootSequenceManager();

    LiveData<PortalApplication.Config> getConfig();

    Device getCurrentDevice();

    Single<Lineup> getCurrentLineup();

    MegacableErrorManager getErrorManager();

    FeaturesManager getFeaturesManager();

    NetworkStateManager getNetworkStateManager();

    NotificationManager getNotificationManager();

    TimeshiftingHelper getTimeshiftingHelper();

    UpdaterInfo getUpdaterInfo();

    /* JADX INFO: renamed from: isDegradedMode */
    boolean getDegradedMode();
}
