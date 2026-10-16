package com.dotscreen.portal.model;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.IntentCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.LauncherItemsViewModel.LoadApplicationsItemsTask;
import com.dotscreen.portal.model.LauncherItemsViewModel.LoadFeaturedApplicationsItemsTask;
import com.dotscreen.portal.model.LauncherItemsViewModel.LoadGamesItemsTask;
import com.dotscreen.portal.ui.screens.setup.SettingsActivityKt;
import com.dotscreen.portal.utils.ApplicationsManager;
import com.dotscreen.portal.utils.AudioZTEManager;
import com.dotscreen.portal.utils.MarketUpdateReceiver;
import com.dotscreen.tv.common.utils.CommonUtils;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LauncherItemsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\f\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b*\u0003\u0015&)\u0018\u00002\u00020\u0001:\u0007:;<=>?@B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u00103\u001a\u0004\u0018\u000104H\u0002J\u0010\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u000207H\u0002J\b\u00108\u001a\u000209H\u0014R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\rR\u001c\u0010\u001b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\rR\u001c\u0010\u001e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u000b0!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'R\u0010\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0004\n\u0002\u0010*R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\t8F¢\u0006\u0006\u001a\u0004\b-\u0010\rR\u0016\u0010.\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\n0\t8F¢\u0006\u0006\u001a\u0004\b1\u0010\rR\u001c\u00102\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\n\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/dotscreen/portal/model/LauncherItemsViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "applicationItems", "Landroidx/lifecycle/LiveData;", "", "Lcom/dotscreen/portal/model/ApplicationWidget;", "getApplicationItems", "()Landroidx/lifecycle/LiveData;", "applicationsItemsData", "Landroidx/lifecycle/MutableLiveData;", "applicationsList", "", "applicationsManager", "Lcom/dotscreen/portal/utils/ApplicationsManager;", "applicationsOrderChangedListener", "com/dotscreen/portal/model/LauncherItemsViewModel$applicationsOrderChangedListener$1", "Lcom/dotscreen/portal/model/LauncherItemsViewModel$applicationsOrderChangedListener$1;", "getContext", "()Landroid/content/Context;", "featuredApplicationItems", "getFeaturedApplicationItems", "featuredApplicationsItemsData", "gameItems", "getGameItems", "gamesItemsData", "gamesList", "installerSessionsMap", "", "", "marketUpdateReceiver", "Lcom/dotscreen/portal/utils/MarketUpdateReceiver;", "packageListener", "com/dotscreen/portal/model/LauncherItemsViewModel$packageListener$1", "Lcom/dotscreen/portal/model/LauncherItemsViewModel$packageListener$1;", "sessionCallback", "com/dotscreen/portal/model/LauncherItemsViewModel$sessionCallback$1", "Lcom/dotscreen/portal/model/LauncherItemsViewModel$sessionCallback$1;", "settingsItems", "Lcom/dotscreen/portal/model/LauncherItemsViewModel$SettingsItem;", "getSettingsItems", "settingsItemsData", "settingsPageItems", "Lcom/dotscreen/portal/model/BaseWidget;", "getSettingsPageItems", "settingsPageItemsData", "getSystemSettingsIntent", "Landroid/content/Intent;", "getValidName", "info", "Landroid/content/pm/ResolveInfo;", "onCleared", "", "Factory", "LoadApplicationsItemsTask", "LoadFeaturedApplicationsItemsTask", "LoadGamesItemsTask", "LoadSettingsPageItemsTask", "LoadSystemSettingsItemsTask", "SettingsItem", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class LauncherItemsViewModel extends ViewModel {
    private String TAG;
    private MutableLiveData<List<ApplicationWidget>> applicationsItemsData;
    private List<ApplicationWidget> applicationsList;
    private final ApplicationsManager applicationsManager;
    private final LauncherItemsViewModel$applicationsOrderChangedListener$1 applicationsOrderChangedListener;
    private final Context context;
    private MutableLiveData<List<ApplicationWidget>> featuredApplicationsItemsData;
    private MutableLiveData<List<ApplicationWidget>> gamesItemsData;
    private List<ApplicationWidget> gamesList;
    private Map<Integer, ApplicationWidget> installerSessionsMap;
    private final MarketUpdateReceiver marketUpdateReceiver;
    private final LauncherItemsViewModel$packageListener$1 packageListener;
    private final LauncherItemsViewModel$sessionCallback$1 sessionCallback;
    private MutableLiveData<SettingsItem> settingsItemsData;
    private MutableLiveData<List<BaseWidget>> settingsPageItemsData;

    /* JADX WARN: Type inference failed for: r2v12, types: [com.dotscreen.portal.model.LauncherItemsViewModel$sessionCallback$1] */
    /* JADX WARN: Type inference failed for: r2v13, types: [com.dotscreen.portal.model.LauncherItemsViewModel$packageListener$1] */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.dotscreen.portal.model.LauncherItemsViewModel$applicationsOrderChangedListener$1] */
    public LauncherItemsViewModel(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = LauncherItemsViewModel.class.getSimpleName();
        this.applicationsList = new ArrayList();
        this.gamesList = new ArrayList();
        this.installerSessionsMap = new LinkedHashMap();
        ApplicationsManager applicationsManager = PortalApplication.INSTANCE.getSingleton(this.context).getApplicationsManager();
        Intrinsics.checkExpressionValueIsNotNull(applicationsManager, "PortalApplication.getSin…text).applicationsManager");
        this.applicationsManager = applicationsManager;
        this.sessionCallback = new PackageInstaller.SessionCallback() { // from class: com.dotscreen.portal.model.LauncherItemsViewModel$sessionCallback$1
            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onActiveChanged(int sessionId, boolean active) {
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onBadgingChanged(int sessionId) {
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onProgressChanged(int sessionId, float progress) {
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onFinished(int sessionId, boolean success) {
                ApplicationWidget applicationWidget = (ApplicationWidget) this.this$0.installerSessionsMap.get(Integer.valueOf(sessionId));
                if (applicationWidget != null) {
                    this.this$0.applicationsList.remove(applicationWidget);
                    MutableLiveData mutableLiveData = this.this$0.applicationsItemsData;
                    if (mutableLiveData != null) {
                        mutableLiveData.postValue(this.this$0.applicationsList);
                    }
                    this.this$0.gamesList.remove(applicationWidget);
                    MutableLiveData mutableLiveData2 = this.this$0.gamesItemsData;
                    if (mutableLiveData2 != null) {
                        mutableLiveData2.postValue(this.this$0.gamesList);
                    }
                }
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onCreated(int sessionId) {
                this.this$0.new LoadApplicationsItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        };
        this.packageListener = new BroadcastReceiver() { // from class: com.dotscreen.portal.model.LauncherItemsViewModel$packageListener$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context c, Intent intent) {
                this.this$0.new LoadApplicationsItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                this.this$0.new LoadGamesItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                this.this$0.new LoadFeaturedApplicationsItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        };
        this.applicationsOrderChangedListener = new ApplicationsManager.OnApplicationsOrderChangedListener() { // from class: com.dotscreen.portal.model.LauncherItemsViewModel$applicationsOrderChangedListener$1
            @Override // com.dotscreen.portal.utils.ApplicationsManager.OnApplicationsOrderChangedListener
            public void onApplicationsOrderChanged() {
                this.this$0.new LoadApplicationsItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                this.this$0.new LoadGamesItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                this.this$0.new LoadFeaturedApplicationsItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        };
        this.marketUpdateReceiver = new MarketUpdateReceiver();
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: LauncherItemsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/model/LauncherItemsViewModel$SettingsItem;", "", "systemSettingsItem", "Lcom/dotscreen/portal/model/BaseWidget;", "networkSettingsItem", "(Lcom/dotscreen/portal/model/BaseWidget;Lcom/dotscreen/portal/model/BaseWidget;)V", "getNetworkSettingsItem", "()Lcom/dotscreen/portal/model/BaseWidget;", "getSystemSettingsItem", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class SettingsItem {
        private final BaseWidget networkSettingsItem;
        private final BaseWidget systemSettingsItem;

        public SettingsItem(BaseWidget baseWidget, BaseWidget baseWidget2) {
            this.systemSettingsItem = baseWidget;
            this.networkSettingsItem = baseWidget2;
        }

        public final BaseWidget getNetworkSettingsItem() {
            return this.networkSettingsItem;
        }

        public final BaseWidget getSystemSettingsItem() {
            return this.systemSettingsItem;
        }
    }

    /* JADX INFO: compiled from: LauncherItemsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/model/LauncherItemsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;

        public Factory(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
        }

        public final Context getContext() {
            return this.context;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
            return new LauncherItemsViewModel(this.context);
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        try {
            this.context.unregisterReceiver(this.packageListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.context.unregisterReceiver(this.marketUpdateReceiver);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            PackageManager packageManager = this.context.getPackageManager();
            Intrinsics.checkExpressionValueIsNotNull(packageManager, "context.packageManager");
            packageManager.getPackageInstaller().unregisterSessionCallback(this.sessionCallback);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.applicationsManager.removeListener(this.applicationsOrderChangedListener);
    }

    public final LiveData<List<ApplicationWidget>> getFeaturedApplicationItems() {
        if (this.featuredApplicationsItemsData == null) {
            this.featuredApplicationsItemsData = new MutableLiveData<>();
            new LoadFeaturedApplicationsItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        MutableLiveData<List<ApplicationWidget>> mutableLiveData = this.featuredApplicationsItemsData;
        if (mutableLiveData == null) {
            Intrinsics.throwNpe();
        }
        return mutableLiveData;
    }

    public final LiveData<List<ApplicationWidget>> getApplicationItems() {
        if (this.applicationsItemsData == null) {
            this.applicationsItemsData = new MutableLiveData<>();
            new LoadApplicationsItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        MutableLiveData<List<ApplicationWidget>> mutableLiveData = this.applicationsItemsData;
        if (mutableLiveData == null) {
            Intrinsics.throwNpe();
        }
        return mutableLiveData;
    }

    public final LiveData<List<ApplicationWidget>> getGameItems() {
        if (this.gamesItemsData == null) {
            this.gamesItemsData = new MutableLiveData<>();
            new LoadGamesItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        MutableLiveData<List<ApplicationWidget>> mutableLiveData = this.gamesItemsData;
        if (mutableLiveData == null) {
            Intrinsics.throwNpe();
        }
        return mutableLiveData;
    }

    public final LiveData<SettingsItem> getSettingsItems() {
        if (this.settingsItemsData == null) {
            this.settingsItemsData = new MutableLiveData<>();
            new LoadSystemSettingsItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        MutableLiveData<SettingsItem> mutableLiveData = this.settingsItemsData;
        if (mutableLiveData == null) {
            Intrinsics.throwNpe();
        }
        return mutableLiveData;
    }

    public final LiveData<List<BaseWidget>> getSettingsPageItems() {
        if (this.settingsPageItemsData == null) {
            this.settingsPageItemsData = new MutableLiveData<>();
            new LoadSettingsPageItemsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        MutableLiveData<List<BaseWidget>> mutableLiveData = this.settingsPageItemsData;
        if (mutableLiveData == null) {
            Intrinsics.throwNpe();
        }
        return mutableLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getValidName(ResolveInfo info) throws PackageManager.NameNotFoundException {
        int i;
        Resources resourcesForApplication = this.context.getPackageManager().getResourcesForApplication(info.activityInfo.applicationInfo);
        if (info.labelRes != 0) {
            i = info.labelRes;
        } else if (info.activityInfo.labelRes != 0) {
            i = info.activityInfo.labelRes;
        } else {
            i = info.activityInfo.applicationInfo.labelRes;
        }
        try {
            String string = resourcesForApplication.getString(i);
            Intrinsics.checkExpressionValueIsNotNull(string, "res.getString(resId)");
            return string;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: LauncherItemsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0092\u0004\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0005J+\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0016\u0010\f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\r\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0014J\u0016\u0010\u0012\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/portal/model/LauncherItemsViewModel$LoadApplicationsItemsTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "", "Lcom/dotscreen/portal/model/ApplicationWidget;", "(Lcom/dotscreen/portal/model/LauncherItemsViewModel;)V", "blacklistedPackages", "", "", "getBlacklistedPackages", "()Ljava/util/Set;", "doInBackground", "p0", "", "([Ljava/lang/Void;)Ljava/util/List;", "onPostExecute", "", "result", "postValue", "list", "shouldIncludeApplication", "", "info", "Landroid/content/pm/ResolveInfo;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    class LoadApplicationsItemsTask extends AsyncTask<Void, Void, List<? extends ApplicationWidget>> {
        private final Set<String> blacklistedPackages = SetsKt.setOf("com.android.traceur");

        public LoadApplicationsItemsTask() {
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(List<? extends ApplicationWidget> list) {
            onPostExecute2((List<ApplicationWidget>) list);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<ApplicationWidget> doInBackground(Void... p0) throws PackageManager.NameNotFoundException {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            ArrayList arrayList = new ArrayList();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (ResolveInfo info : LauncherItemsViewModel.this.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory(IntentCompat.CATEGORY_LEANBACK_LAUNCHER), 0)) {
                Intrinsics.checkExpressionValueIsNotNull(info, "info");
                if (shouldIncludeApplication(info)) {
                    String str = info.activityInfo.packageName;
                    Intrinsics.checkExpressionValueIsNotNull(str, "info.activityInfo.packageName");
                    linkedHashSet.add(str);
                    Intent leanbackLaunchIntentForPackage = LauncherItemsViewModel.this.getContext().getPackageManager().getLeanbackLaunchIntentForPackage(info.activityInfo.packageName);
                    if (leanbackLaunchIntentForPackage != null) {
                        String validName = LauncherItemsViewModel.this.getValidName(info);
                        String str2 = info.activityInfo.packageName;
                        Intrinsics.checkExpressionValueIsNotNull(str2, "info.activityInfo.packageName");
                        arrayList.add(new ApplicationWidget(validName, str2, "", leanbackLaunchIntentForPackage));
                    }
                }
            }
            return LauncherItemsViewModel.this.applicationsManager.sortApplicationWidget(arrayList);
        }

        protected final Set<String> getBlacklistedPackages() {
            return this.blacklistedPackages;
        }

        public boolean shouldIncludeApplication(ResolveInfo info) {
            Intrinsics.checkParameterIsNotNull(info, "info");
            return (info.activityInfo.applicationInfo.flags & 33554432) == 0 && (Intrinsics.areEqual(info.activityInfo.packageName, LauncherItemsViewModel.this.getContext().getPackageName()) ^ true) && !this.blacklistedPackages.contains(info.activityInfo.packageName);
        }

        public void postValue(List<ApplicationWidget> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            LauncherItemsViewModel.this.applicationsList.clear();
            LauncherItemsViewModel.this.applicationsList.addAll(0, list);
            MutableLiveData mutableLiveData = LauncherItemsViewModel.this.applicationsItemsData;
            if (mutableLiveData != null) {
                mutableLiveData.postValue(LauncherItemsViewModel.this.applicationsList);
            }
        }

        /* JADX INFO: renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        protected void onPostExecute2(List<ApplicationWidget> result) {
            Intrinsics.checkParameterIsNotNull(result, "result");
            super.onPostExecute(result);
            postValue(result);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addDataScheme("package");
            try {
                LauncherItemsViewModel.this.getContext().unregisterReceiver(LauncherItemsViewModel.this.packageListener);
            } catch (Exception unused) {
            }
            LauncherItemsViewModel.this.getContext().registerReceiver(LauncherItemsViewModel.this.packageListener, intentFilter);
            try {
                LauncherItemsViewModel.this.getContext().unregisterReceiver(LauncherItemsViewModel.this.marketUpdateReceiver);
            } catch (Exception unused2) {
            }
            LauncherItemsViewModel.this.getContext().registerReceiver(LauncherItemsViewModel.this.marketUpdateReceiver, MarketUpdateReceiver.getIntentFilter());
            LauncherItemsViewModel.this.applicationsManager.addListener(LauncherItemsViewModel.this.applicationsOrderChangedListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: LauncherItemsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/model/LauncherItemsViewModel$LoadGamesItemsTask;", "Lcom/dotscreen/portal/model/LauncherItemsViewModel$LoadApplicationsItemsTask;", "Lcom/dotscreen/portal/model/LauncherItemsViewModel;", "(Lcom/dotscreen/portal/model/LauncherItemsViewModel;)V", "postValue", "", "list", "", "Lcom/dotscreen/portal/model/ApplicationWidget;", "shouldIncludeApplication", "", "info", "Landroid/content/pm/ResolveInfo;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    final class LoadGamesItemsTask extends LoadApplicationsItemsTask {
        public LoadGamesItemsTask() {
            super();
        }

        @Override // com.dotscreen.portal.model.LauncherItemsViewModel.LoadApplicationsItemsTask
        public boolean shouldIncludeApplication(ResolveInfo info) {
            Intrinsics.checkParameterIsNotNull(info, "info");
            return ((info.activityInfo.applicationInfo.flags & 33554432) == 0 || !(Intrinsics.areEqual(info.activityInfo.packageName, LauncherItemsViewModel.this.getContext().getPackageName()) ^ true) || getBlacklistedPackages().contains(info.activityInfo.packageName)) ? false : true;
        }

        @Override // com.dotscreen.portal.model.LauncherItemsViewModel.LoadApplicationsItemsTask
        public void postValue(List<ApplicationWidget> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            LauncherItemsViewModel.this.gamesList.clear();
            LauncherItemsViewModel.this.gamesList.addAll(0, list);
            MutableLiveData mutableLiveData = LauncherItemsViewModel.this.gamesItemsData;
            if (mutableLiveData != null) {
                mutableLiveData.postValue(LauncherItemsViewModel.this.gamesList);
            }
        }
    }

    /* JADX INFO: compiled from: LauncherItemsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J'\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0006\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/dotscreen/portal/model/LauncherItemsViewModel$LoadSystemSettingsItemsTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "(Lcom/dotscreen/portal/model/LauncherItemsViewModel;)V", "doInBackground", "p0", "", "([Ljava/lang/Void;)Ljava/lang/Void;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class LoadSystemSettingsItemsTask extends AsyncTask<Void, Void, Void> {
        public LoadSystemSettingsItemsTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... p0) {
            boolean z;
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            List<ResolveInfo> listQueryIntentActivities = LauncherItemsViewModel.this.getContext().getPackageManager().queryIntentActivities(new Intent("android.settings.WIFI_SETTINGS").addCategory("android.intent.category.LEANBACK_SETTINGS"), 0);
            List<ResolveInfo> listQueryIntentActivities2 = LauncherItemsViewModel.this.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LEANBACK_SETTINGS"), 64);
            Log.d(LauncherItemsViewModel.this.TAG, "networkInfos.size = " + listQueryIntentActivities.size());
            Log.d(LauncherItemsViewModel.this.TAG, "settingsInfos.size = " + listQueryIntentActivities2.size());
            BaseWidget baseWidget = (BaseWidget) null;
            NetworkWidget networkWidget = baseWidget;
            for (ResolveInfo info : listQueryIntentActivities2) {
                if (info.activityInfo != null) {
                    Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        ResolveInfo next = it.next();
                        if (next.activityInfo != null && TextUtils.equals(next.activityInfo.name, info.activityInfo.name) && TextUtils.equals(next.activityInfo.packageName, info.activityInfo.packageName)) {
                            z = true;
                            break;
                        }
                    }
                    Intent intent = Intent.makeMainActivity(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
                    if (z) {
                        String str = LauncherItemsViewModel.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("network, ");
                        LauncherItemsViewModel launcherItemsViewModel = LauncherItemsViewModel.this;
                        Intrinsics.checkExpressionValueIsNotNull(info, "info");
                        sb.append(launcherItemsViewModel.getValidName(info));
                        sb.append(" : ");
                        sb.append(info);
                        Log.d(str, sb.toString());
                        if (networkWidget == null) {
                            String string = LauncherItemsViewModel.this.getContext().getString(R.string.home_item_setup_network);
                            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri….home_item_setup_network)");
                            Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
                            networkWidget = new NetworkWidget(string, intent);
                        }
                    } else {
                        String str2 = LauncherItemsViewModel.this.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("other, ");
                        LauncherItemsViewModel launcherItemsViewModel2 = LauncherItemsViewModel.this;
                        Intrinsics.checkExpressionValueIsNotNull(info, "info");
                        sb2.append(launcherItemsViewModel2.getValidName(info));
                        sb2.append(" : ");
                        sb2.append(info);
                        Log.d(str2, sb2.toString());
                        if (baseWidget == null) {
                            baseWidget = new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.home_item_setup_system), intent, false, null, 12, null);
                        }
                    }
                }
            }
            MutableLiveData mutableLiveData = LauncherItemsViewModel.this.settingsItemsData;
            if (mutableLiveData != null) {
                mutableLiveData.postValue(new SettingsItem(baseWidget, networkWidget));
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: LauncherItemsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J'\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0006\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/dotscreen/portal/model/LauncherItemsViewModel$LoadSettingsPageItemsTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "(Lcom/dotscreen/portal/model/LauncherItemsViewModel;)V", "doInBackground", "p0", "", "([Ljava/lang/Void;)Ljava/lang/Void;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class LoadSettingsPageItemsTask extends AsyncTask<Void, Void, Void> {
        public LoadSettingsPageItemsTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... p0) throws URISyntaxException {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            LauncherItemsViewModel.this.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LEANBACK_SETTINGS"), 64);
            ArrayList arrayList = new ArrayList();
            Context context = LauncherItemsViewModel.this.getContext();
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) context).getConfig().getValue();
            if (value != null && value.getUser() != null) {
                arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.setting_item_profiles), Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=manage", 0), false, null, 12, null));
                arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.setting_item_purchase_pin), Intent.parseUri("megacable://com.dotscreen.megacable.portal/settings?action=update_purchase", 0), false, null, 12, null));
            }
            arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.setting_item_packages), Intent.parseUri("megacable://com.dotscreen.megacable.portal/rightvpackages", 0), false, null, 12, null));
            arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.settings_info_signal_title), Intent.parseUri("megacable://com.dotscreen.megacable.portal/settings/signal_info", 0), false, null, 12, null));
            arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.settings_info_ip_title), Intent.parseUri("megacable://com.dotscreen.megacable.portal/settings/ip_info", 0), false, null, 12, null));
            arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.settings_info_stb_info_title), Intent.parseUri("megacable://com.dotscreen.megacable.portal/settings/stb_info", 0), false, null, 12, null));
            arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.settings_info_soft_title), Intent.parseUri("megacable://com.dotscreen.megacable.portal/settings/soft_info", 0), false, null, 12, null));
            arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.settings_info_connectivity_title), Intent.parseUri("megacable://com.dotscreen.megacable.portal/settings/connectivity_info", 0), false, null, 12, null));
            if (!((PortalApplication) LauncherItemsViewModel.this.getContext()).getDegradedMode()) {
                arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.setting_item_setup), Intent.parseUri("megacable://com.dotscreen.megacable.portal/settings?action=setup", 0), false, null, 12, null));
                arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.settings_rescan_channels_title), new Intent(SettingsActivityKt.ACTION_SCAN_CHANNELS), false, null, 12, null));
            }
            switch (CommonUtils.getCurrentPlatform()) {
                case 1:
                    Intent intent = new Intent("android.settings.SOUND_SETTINGS");
                    intent.setComponent(new ComponentName("com.android.tv.settings", "com.android.tv.settings.device.sound.SoundActivity"));
                    arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.audio_settings), intent, false, null, 12, null));
                    Intent intent2 = new Intent("android.settings.DISPLAY_SETTINGS");
                    intent2.setComponent(new ComponentName("com.droidlogic.tv.settings", "com.droidlogic.tv.settings.display.DisplayActivity"));
                    arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.video_settings), intent2, false, null, 12, null));
                    break;
                case 2:
                case 6:
                    arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.audio_settings), Intent.parseUri("app://android.tv.settings/sound", 0), false, null, 12, null));
                    arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.video_settings), Intent.parseUri("app://android.tv.settings/offset", 0), false, null, 12, null));
                    break;
                case 3:
                    if (AudioZTEManager.INSTANCE.isZtePhase2()) {
                        arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.audio_settings), new Intent(SettingsActivityKt.ACTION_AUDIO_ZTE), false, null, 12, null));
                        arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.video_settings), Intent.parseUri("zte://DisplayPositionActivity", 0), false, null, 12, null));
                    }
                    break;
                case 4:
                    Intent intent3 = new Intent();
                    intent3.setComponent(new ComponentName("com.android.tv.settings", "com.android.tv.settings.device.displaysound.AdvancedVolumeActivity"));
                    arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.audio_settings), intent3, false, null, 12, null));
                    Intent intent4 = new Intent();
                    intent4.setComponent(new ComponentName("com.android.tv.settings", "com.android.tv.settings.device.displaysound.DisplaySoundActivity"));
                    arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.video_settings), intent4, false, null, 12, null));
                    break;
                case 5:
                    arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.audio_settings), new Intent(SettingsActivityKt.ACTION_AUDIO_ZTE), false, null, 12, null));
                    arrayList.add(new BaseWidget(LauncherItemsViewModel.this.getContext().getString(R.string.video_settings), Intent.parseUri("zte://DisplayPositionActivity", 0), false, null, 12, null));
                    break;
            }
            MutableLiveData mutableLiveData = LauncherItemsViewModel.this.settingsPageItemsData;
            if (mutableLiveData == null) {
                return null;
            }
            mutableLiveData.postValue(arrayList);
            return null;
        }
    }

    private final Intent getSystemSettingsIntent() {
        for (ResolveInfo resolveInfo : this.context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LEANBACK_SETTINGS"), 64)) {
            if (resolveInfo.activityInfo != null) {
                return Intent.makeMainActivity(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: LauncherItemsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0005J+\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\b\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0014¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/model/LauncherItemsViewModel$LoadFeaturedApplicationsItemsTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "", "Lcom/dotscreen/portal/model/ApplicationWidget;", "(Lcom/dotscreen/portal/model/LauncherItemsViewModel;)V", "doInBackground", "p0", "", "([Ljava/lang/Void;)Ljava/util/List;", "onPostExecute", "", "result", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    final class LoadFeaturedApplicationsItemsTask extends AsyncTask<Void, Void, List<? extends ApplicationWidget>> {
        public LoadFeaturedApplicationsItemsTask() {
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(List<? extends ApplicationWidget> list) {
            onPostExecute2((List<ApplicationWidget>) list);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<ApplicationWidget> doInBackground(Void... p0) {
            List<PortalApplication.SwimLane> swimLanes;
            Object next;
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            ArrayList arrayList = new ArrayList();
            Context applicationContext = LauncherItemsViewModel.this.getContext().getApplicationContext();
            if (applicationContext != null) {
                PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
                if (value != null && (swimLanes = value.getSwimLanes()) != null) {
                    Iterator<T> it = swimLanes.iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Intrinsics.areEqual(((PortalApplication.SwimLane) next).getType(), "featured_apps"));
                    PortalApplication.SwimLane swimLane = (PortalApplication.SwimLane) next;
                    if (swimLane != null && (swimLane instanceof PortalApplication.ApplicationSwimLane)) {
                        for (PortalApplication.ApplicationSwimLane.Item item : ((PortalApplication.ApplicationSwimLane) swimLane).getItems()) {
                            if (item.getName() != null && item.getPackageName() != null && item.getBannerUrl() != null) {
                                PackageManager packageManager = LauncherItemsViewModel.this.getContext().getPackageManager();
                                arrayList.add(new ApplicationWidget(item.getName(), item.getPackageName(), item.getBannerUrl(), packageManager != null ? packageManager.getLeanbackLaunchIntentForPackage(item.getPackageName()) : null));
                            }
                        }
                    }
                }
                return LauncherItemsViewModel.this.applicationsManager.sortApplicationWidget(arrayList);
            }
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }

        /* JADX INFO: renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        protected void onPostExecute2(List<ApplicationWidget> result) {
            Intrinsics.checkParameterIsNotNull(result, "result");
            super.onPostExecute(result);
            MutableLiveData mutableLiveData = LauncherItemsViewModel.this.featuredApplicationsItemsData;
            if (mutableLiveData != null) {
                mutableLiveData.postValue(result);
            }
            LauncherItemsViewModel.this.applicationsManager.addListener(LauncherItemsViewModel.this.applicationsOrderChangedListener);
        }
    }
}
