package com.dotscreen.portal.ui.screens.setup;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemProperties;
import android.view.View;
import android.widget.TextView;
import com.android.tv.common.BuildConfig;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.views.SettingsInfoCellView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsInfoActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoSoftwareActivity;", "Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoActivity;", "()V", "getSystemPropertySummary", "", "property", "populateTab", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SettingsInfoSoftwareActivity extends SettingsInfoActivity {
    private HashMap _$_findViewCache;

    @Override // com.dotscreen.portal.ui.screens.setup.SettingsInfoActivity, com.dotscreen.portal.ui.screens.BaseFragmentActivity
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.setup.SettingsInfoActivity, com.dotscreen.portal.ui.screens.BaseFragmentActivity
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

    @Override // com.dotscreen.portal.ui.screens.setup.SettingsInfoActivity
    public void populateTab() throws PackageManager.NameNotFoundException {
        TextView title = getTitle();
        if (title != null) {
            title.setText(getString(R.string.settings_info_soft_title));
        }
        SettingsInfoCellView settingsInfoCellView = getInfoCells().get(0);
        String string = getString(R.string.settings_info_soft_vendor);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.settings_info_soft_vendor)");
        settingsInfoCellView.setTitle(string);
        String systemPropertySummary = (Intrinsics.areEqual("N/A", "N/A") || Intrinsics.areEqual("N/A", "")) ? getSystemPropertySummary("ro.build.version.incremental") : "N/A";
        if (Intrinsics.areEqual(systemPropertySummary, "N/A") || Intrinsics.areEqual(systemPropertySummary, "")) {
            systemPropertySummary = getSystemPropertySummary("ro.build.fingerprint");
        }
        if (Intrinsics.areEqual(systemPropertySummary, "N/A") || Intrinsics.areEqual(systemPropertySummary, "")) {
            systemPropertySummary = getSystemPropertySummary("ro.product.version.software");
        }
        SettingsInfoCellView settingsInfoCellView2 = getInfoCells().get(0);
        if (Intrinsics.areEqual(systemPropertySummary, "")) {
            systemPropertySummary = "N/A";
        }
        settingsInfoCellView2.setDetail(systemPropertySummary);
        SettingsInfoCellView settingsInfoCellView3 = getInfoCells().get(1);
        String string2 = getString(R.string.settings_info_soft_android_version);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.setti…nfo_soft_android_version)");
        settingsInfoCellView3.setTitle(string2);
        SettingsInfoCellView settingsInfoCellView4 = getInfoCells().get(1);
        String str = Build.VERSION.RELEASE;
        Intrinsics.checkExpressionValueIsNotNull(str, "android.os.Build.VERSION.RELEASE");
        settingsInfoCellView4.setDetail(str);
        PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "this.packageManager.getPackageInfo(packageName, 0)");
        String str2 = packageInfo.versionName + " (" + packageInfo.getLongVersionCode() + ")";
        if (CommonUtils.isProdConfig()) {
            SettingsInfoCellView settingsInfoCellView5 = getInfoCells().get(2);
            String string3 = getString(R.string.settings_info_soft_portal_version);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.setti…info_soft_portal_version)");
            settingsInfoCellView5.setTitle(string3);
            getInfoCells().get(2).setDetail(String.valueOf(str2));
        } else {
            SettingsInfoCellView settingsInfoCellView6 = getInfoCells().get(2);
            String string4 = getString(R.string.settings_info_soft_portal_version);
            Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.setti…info_soft_portal_version)");
            settingsInfoCellView6.setTitle(string4);
            getInfoCells().get(2).setDetail(str2 + " - prod");
        }
        SettingsInfoCellView settingsInfoCellView7 = getInfoCells().get(3);
        String string5 = getString(R.string.settings_info_soft_live_version);
        Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.setti…s_info_soft_live_version)");
        settingsInfoCellView7.setTitle(string5);
        try {
            PackageInfo packageInfoLive = getPackageManager().getPackageInfo("com.dotscreen.megacable.livetv", 0);
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfoLive.versionName);
            sb.append(" (");
            Intrinsics.checkExpressionValueIsNotNull(packageInfoLive, "packageInfoLive");
            sb.append(packageInfoLive.getLongVersionCode());
            sb.append(")");
            getInfoCells().get(3).setDetail(String.valueOf(sb.toString()));
        } catch (Exception unused) {
            getInfoCells().get(3).setDetail("N/A");
        }
        SettingsInfoCellView settingsInfoCellView8 = getInfoCells().get(4);
        String string6 = getString(R.string.settings_info_soft_updater_version);
        Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.setti…nfo_soft_updater_version)");
        settingsInfoCellView8.setTitle(string6);
        try {
            PackageInfo packageInfoUpdater = getPackageManager().getPackageInfo(BuildConfig.UPDATER_PACKAGE_NAME, 0);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(packageInfoUpdater.versionName);
            sb2.append(" (");
            Intrinsics.checkExpressionValueIsNotNull(packageInfoUpdater, "packageInfoUpdater");
            sb2.append(packageInfoUpdater.getLongVersionCode());
            sb2.append(")");
            getInfoCells().get(4).setDetail(String.valueOf(sb2.toString()));
        } catch (Exception unused2) {
            getInfoCells().get(4).setDetail("N/A");
        }
        if (!CommonUtils.isProdConfig()) {
            SettingsInfoCellView settingsInfoCellView9 = getInfoCells().get(5);
            String string7 = getString(R.string.settings_info_soft_core_version);
            Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.setti…s_info_soft_core_version)");
            settingsInfoCellView9.setTitle(string7);
            try {
                PackageInfo packageInfocore = getPackageManager().getPackageInfo(BuildConfig.CORE_PACKAGE_NAME, 0);
                StringBuilder sb3 = new StringBuilder();
                sb3.append(packageInfocore.versionName);
                sb3.append(" (");
                Intrinsics.checkExpressionValueIsNotNull(packageInfocore, "packageInfocore");
                sb3.append(packageInfocore.getLongVersionCode());
                sb3.append(")");
                getInfoCells().get(5).setDetail(String.valueOf(sb3.toString()));
            } catch (Exception unused3) {
                getInfoCells().get(5).setDetail("N/A");
            }
        }
        SettingsInfoCellView settingsInfoCellView10 = getInfoCells().get(7);
        String string8 = getString(R.string.moreInfo);
        Intrinsics.checkExpressionValueIsNotNull(string8, "getString(R.string.moreInfo)");
        settingsInfoCellView10.setDetail(string8);
        getInfoCells().get(7).setDetailBackground(R.drawable.button_bg);
        getInfoCells().get(7).setHasButton(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsInfoSoftwareActivity.populateTab.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsInfoSoftwareActivity.this.startActivityForResult(Intent.parseUri("megacable://com.dotscreen.megacable.updater/infos", 0), 0);
            }
        });
        getInfoCells().get(7).requestFocus();
    }

    private final String getSystemPropertySummary(String property) {
        String str = SystemProperties.get(property, "N/A");
        Intrinsics.checkExpressionValueIsNotNull(str, "SystemProperties.get(property, \"N/A\")");
        return str;
    }
}
