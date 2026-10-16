package com.dotscreen.portal.ui.screens.setup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.VerticalGridPresenter;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.updater.UpdateManager;
import com.dotscreen.farkit.updater.UpdateManagerKt;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.ui.cards.UpdateItemCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.utils.UpdaterWorker;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdaterActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 82\u00020\u00012\u00020\u0002:\u00018B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u001a\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\u001e\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u0018H\u0014J(\u0010\"\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$2\u000e\u0010%\u001a\n\u0018\u00010&j\u0004\u0018\u0001`'H\u0016J0\u0010(\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u000e\u0010,\u001a\n\u0018\u00010&j\u0004\u0018\u0001`'H\u0016J\u0010\u0010-\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J(\u0010.\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$2\u000e\u0010,\u001a\n\u0018\u00010&j\u0004\u0018\u0001`'H\u0016J\u0010\u0010/\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J\b\u00100\u001a\u00020\u0018H\u0014J\b\u00101\u001a\u00020\u0018H\u0014J\u0016\u00102\u001a\u00020\u00182\f\u00103\u001a\b\u0012\u0004\u0012\u00020504H\u0016J\b\u00106\u001a\u00020\u0018H\u0002J\u0010\u00107\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0004\u0012\u00020\u00050\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/UpdaterActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "Lcom/dotscreen/farkit/updater/UpdateManager$IUpdateManagerListener;", "()V", "DEBUG", "", "TAG", "", "kotlin.jvm.PlatformType", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "cancelable", "Lcom/dotscreen/farkit/updater/UpdateManager$ICancelable;", "defaultCriteria", "Lkotlin/Function1;", "", "", "progressDialog", "Landroid/app/AlertDialog;", "updateItem", "Lcom/dotscreen/farkit/updater/UpdateManager$UpdateItem;", "updateManager", "Lcom/dotscreen/farkit/updater/UpdateManager;", "cancelNotifyAppUpdated", "", "packageName", "onCheckFailed", "reason", "", "checkUrl", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDownloadFinished", NotificationCompat.CATEGORY_STATUS, "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onDownloadProgress", "downloaded", "", "total", "exception", "onDownloadStarted", "onInstallFinished", "onInstallStarted", "onPause", "onResume", "onUpdatesAvailable", "items", "", "Lcom/dotscreen/farkit/updater/UpdateManager$BundleItem;", "refreshList", "scheduleNotifyAppUpdated", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class UpdaterActivity extends BaseFragmentActivity implements UpdateManager.IUpdateManagerListener {
    private static final int ACTION_NOTIFY_APP_UPDATED = 4;
    private static final String EXTRA_ACTION = "com.dotscreen.megacable.portal.extra_action";
    private HashMap _$_findViewCache;
    private ArrayObjectAdapter adapter;
    private UpdateManager.ICancelable cancelable;
    private AlertDialog progressDialog;
    private UpdateManager.UpdateItem updateItem;
    private UpdateManager updateManager;
    private final boolean DEBUG = true;
    private final String TAG = UpdaterActivity.class.getSimpleName();
    private final Function1<Map<String, ? extends Object>, Boolean> defaultCriteria = new Function1<Map<String, ? extends Object>, Boolean>() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity$defaultCriteria$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Map<String, ? extends Object> map) {
            return Boolean.valueOf(invoke2(map));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(Map<String, ? extends Object> criteria) {
            Intrinsics.checkParameterIsNotNull(criteria, "criteria");
            if (!criteria.isEmpty()) {
                boolean z = Intrinsics.areEqual(criteria.get("oregan"), (Object) true) && CommonUtils.getCurrentPlatform() == 1;
                boolean z2 = Intrinsics.areEqual(criteria.get("zte"), (Object) true) && CommonUtils.getCurrentPlatform() == 3;
                boolean z3 = Intrinsics.areEqual(criteria.get("zte2"), (Object) true) && CommonUtils.getCurrentPlatform() == 5;
                boolean z4 = Intrinsics.areEqual(criteria.get("skyworth"), (Object) true) && CommonUtils.getCurrentPlatform() == 4;
                boolean z5 = Intrinsics.areEqual(criteria.get("technicolor"), (Object) true) && CommonUtils.getCurrentPlatform() == 2;
                boolean z6 = Intrinsics.areEqual(criteria.get("technicolor2"), (Object) true) && CommonUtils.getCurrentPlatform() == 6;
                Object obj = criteria.get("serialNumbers");
                if (!(obj instanceof List)) {
                    obj = null;
                }
                List list = (List) obj;
                boolean z7 = list != null && list.contains(Build.getSerial());
                if (this.this$0.DEBUG) {
                    Log.d(this.this$0.TAG, "    shouldUpdateOregan = " + z);
                    Log.d(this.this$0.TAG, "    shouldUpdateSkyworth  = " + z4);
                    Log.d(this.this$0.TAG, "    shouldUpdateZTE    = " + z2);
                    Log.d(this.this$0.TAG, "    shouldUpdateZTE2    = " + z3);
                    Log.d(this.this$0.TAG, "    shouldUpdateTCH    = " + z5);
                    Log.d(this.this$0.TAG, "    shouldUpdateTCH2    = " + z6);
                    Log.d(this.this$0.TAG, "    shouldUpdateSN     = " + z7);
                }
                if (!z && !z2 && !z3 && !z5 && !z6 && !z4 && !z7) {
                    return false;
                }
            }
            return true;
        }
    };

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public static final /* synthetic */ UpdateManager access$getUpdateManager$p(UpdaterActivity updaterActivity) {
        UpdateManager updateManager = updaterActivity.updateManager;
        if (updateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateManager");
        }
        return updateManager;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updater);
        this.updateManager = new UpdateManager(this);
        VerticalGridPresenter verticalGridPresenter = new VerticalGridPresenter(4, false);
        verticalGridPresenter.setNumberOfColumns(1);
        verticalGridPresenter.setShadowEnabled(true);
        verticalGridPresenter.setOnItemViewClickedListener(new UpdaterActivity$onCreate$listener$1(this));
        this.adapter = new ArrayObjectAdapter(new UpdateItemCardPresenter());
        VerticalGridPresenter.ViewHolder viewHolderOnCreateViewHolder = verticalGridPresenter.onCreateViewHolder((ViewGroup) _$_findCachedViewById(com.dotscreen.portal.R.id.browse_grid_dock));
        ((FrameLayout) _$_findCachedViewById(com.dotscreen.portal.R.id.browse_grid_dock)).addView(viewHolderOnCreateViewHolder.view);
        verticalGridPresenter.onBindViewHolder(viewHolderOnCreateViewHolder, this.adapter);
        UpdateManager updateManager = this.updateManager;
        if (updateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateManager");
        }
        updateManager.addUpdateManagerListener(this);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        UpdateManager.ICancelable iCancelable = this.cancelable;
        if (iCancelable != null) {
            iCancelable.cancel();
        }
        AlertDialog alertDialog = this.progressDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        UpdateManager updateManager = this.updateManager;
        if (updateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateManager");
        }
        updateManager.removeUpdateManagerListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshList() {
        setLoaderVisible(true);
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            arrayObjectAdapter.clear();
        }
        UpdateManager updateManager = this.updateManager;
        if (updateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateManager");
        }
        String str = CommonUtils.OTA_SERVER_URL;
        Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.OTA_SERVER_URL");
        updateManager.checkUpdates(str);
    }

    @Override // com.dotscreen.farkit.updater.UpdateManager.IUpdateManagerListener
    public void onUpdatesAvailable(List<UpdateManager.BundleItem> items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        setLoaderVisible(false);
        UpdateManager updateManager = this.updateManager;
        if (updateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateManager");
        }
        UpdateManager.BundleItem priorityBundleUpdate = updateManager.getPriorityBundleUpdate(this.defaultCriteria);
        if (priorityBundleUpdate != null) {
            UpdateManager updateManager2 = this.updateManager;
            if (updateManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("updateManager");
            }
            updateManager2.selectCurrentBundle(priorityBundleUpdate);
            List<UpdateManager.UpdateItem> apks = priorityBundleUpdate.getApks();
            if (!apks.isEmpty()) {
                ArrayObjectAdapter arrayObjectAdapter = this.adapter;
                if (arrayObjectAdapter != null) {
                    arrayObjectAdapter.addAll(0, CollectionsKt.sortedWith(apks, ComparisonsKt.compareBy(new Function1<UpdateManager.UpdateItem, Integer>() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity.onUpdatesAvailable.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Integer invoke(UpdateManager.UpdateItem updateItem) {
                            return Integer.valueOf(invoke2(updateItem));
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final int invoke2(UpdateManager.UpdateItem it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.getVersionCode();
                        }
                    }, new Function1<UpdateManager.UpdateItem, String>() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity.onUpdatesAvailable.2
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(UpdateManager.UpdateItem it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.getTitle();
                        }
                    })));
                    return;
                }
                return;
            }
            TextView information = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.information);
            Intrinsics.checkExpressionValueIsNotNull(information, "information");
            information.setVisibility(0);
            TextView information2 = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.information);
            Intrinsics.checkExpressionValueIsNotNull(information2, "information");
            information2.setText(getText(R.string.no_update_available));
            return;
        }
        TextView information3 = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.information);
        Intrinsics.checkExpressionValueIsNotNull(information3, "information");
        information3.setVisibility(0);
        TextView information4 = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.information);
        Intrinsics.checkExpressionValueIsNotNull(information4, "information");
        information4.setText(getText(R.string.no_update_available));
    }

    @Override // com.dotscreen.farkit.updater.UpdateManager.IUpdateManagerListener
    public void onCheckFailed(Throwable reason, String checkUrl) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        TextView information = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.information);
        Intrinsics.checkExpressionValueIsNotNull(information, "information");
        information.setVisibility(0);
        TextView information2 = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.information);
        Intrinsics.checkExpressionValueIsNotNull(information2, "information");
        information2.setText(getText(R.string.update_check_failed));
    }

    @Override // com.dotscreen.farkit.updater.UpdateManager.IUpdateManagerListener
    public void onDownloadStarted(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        UpdateManager.UpdateItem updateItem = this.updateItem;
        this.progressDialog = builder.setTitle(updateItem != null ? updateItem.getTitle() : null).setMessage("Downloading...").setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity.onDownloadStarted.1
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                UpdateManager.ICancelable iCancelable = UpdaterActivity.this.cancelable;
                if (iCancelable != null) {
                    iCancelable.cancel();
                }
            }
        }).show();
    }

    @Override // com.dotscreen.farkit.updater.UpdateManager.IUpdateManagerListener
    public void onDownloadProgress(String packageName, long downloaded, long total, Exception exception) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        AlertDialog alertDialog = this.progressDialog;
        if (alertDialog != null) {
            alertDialog.setMessage("Downloading... (" + ((((long) 100) * downloaded) / total) + "%)");
        }
    }

    @Override // com.dotscreen.farkit.updater.UpdateManager.IUpdateManagerListener
    public void onDownloadFinished(String packageName, int status, Exception e) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        if (status == 0) {
            UpdateManager updateManager = this.updateManager;
            if (updateManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("updateManager");
            }
            updateManager.install(packageName);
            return;
        }
        AlertDialog alertDialog = this.progressDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        UpdateManager.UpdateItem updateItem = this.updateItem;
        builder.setTitle(updateItem != null ? updateItem.getTitle() : null).setMessage("Download failed").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity.onDownloadFinished.1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UpdaterActivity.this.refreshList();
            }
        }).show();
    }

    @Override // com.dotscreen.farkit.updater.UpdateManager.IUpdateManagerListener
    public void onInstallStarted(String packageName) throws Throwable {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        AlertDialog alertDialog = this.progressDialog;
        if (alertDialog != null) {
            alertDialog.setMessage("Installing...");
        }
        scheduleNotifyAppUpdated(packageName);
    }

    @Override // com.dotscreen.farkit.updater.UpdateManager.IUpdateManagerListener
    public void onInstallFinished(final String packageName, final int status, Exception exception) {
        String string;
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        AlertDialog alertDialog = this.progressDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        UpdateManager.UpdateItem updateItem = this.updateItem;
        AlertDialog.Builder title = builder.setTitle(updateItem != null ? updateItem.getTitle() : null);
        if (status == 0) {
            string = "Update successful";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Update failed (");
            sb.append(exception != null ? exception.getMessage() : null);
            sb.append(')');
            string = sb.toString();
        }
        title.setMessage(string).setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity.onInstallFinished.1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                if (status != 0) {
                    if (Intrinsics.areEqual(packageName, UpdaterActivity.this.getPackageName())) {
                        UpdaterActivity.this.cancelNotifyAppUpdated(packageName);
                    }
                } else {
                    UserService userService = CommonUtils.getBOService(UpdaterActivity.this.getApplicationContext()).getUserService();
                    if (userService == null) {
                        Intrinsics.throwNpe();
                    }
                    if (userService == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(((MegacableUserService) userService).notifyApplicationsUpdated().subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity.onInstallFinished.1.1
                        @Override // io.reactivex.functions.Action
                        public final void run() {
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity.onInstallFinished.1.2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                        }
                    }), "(CommonUtils.getBOServic…       .subscribe({}, {})");
                }
                UpdaterActivity.this.refreshList();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelNotifyAppUpdated(String packageName) {
        if (this.DEBUG) {
            Log.d(this.TAG, "cancelNotifyAppUpdated(packageName: " + packageName + ')');
        }
        WorkManager.getInstance(this).cancelAllWorkByTag("notify_" + packageName);
    }

    private final void scheduleNotifyAppUpdated(String packageName) throws Throwable {
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(UpdaterWorker.class);
        Pair[] pairArr = {TuplesKt.to(UpdateManagerKt.EXTRA_PACKAGE_NAME, packageName)};
        Data.Builder builder2 = new Data.Builder();
        for (int i = 0; i < 1; i++) {
            Pair pair = pairArr[i];
            builder2.put((String) pair.getFirst(), pair.getSecond());
        }
        Data dataBuild = builder2.build();
        Intrinsics.checkNotNullExpressionValue(dataBuild, "dataBuilder.build()");
        OneTimeWorkRequest.Builder inputData = builder.setInputData(dataBuild);
        Intrinsics.checkExpressionValueIsNotNull(inputData, "OneTimeWorkRequestBuilde…AGE_NAME to packageName))");
        OneTimeWorkRequest.Builder builder3 = inputData;
        builder3.setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build());
        builder3.addTag("notify_" + packageName);
        WorkManager.getInstance(this).enqueue(builder3.build());
    }
}
