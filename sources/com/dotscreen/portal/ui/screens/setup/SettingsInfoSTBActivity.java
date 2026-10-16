package com.dotscreen.portal.ui.screens.setup;

import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.network.NetworkTools;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.views.SettingsInfoCellView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsInfoActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoSTBActivity;", "Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoActivity;", "()V", "populateTab", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SettingsInfoSTBActivity extends SettingsInfoActivity {
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
    public void populateTab() {
        TextView title = getTitle();
        if (title != null) {
            title.setText(getString(R.string.settings_info_stb_info_title));
        }
        SettingsInfoCellView settingsInfoCellView = getInfoCells().get(0);
        String string = getString(R.string.settings_info_mac_eth);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.settings_info_mac_eth)");
        settingsInfoCellView.setTitle(string);
        SettingsInfoCellView settingsInfoCellView2 = getInfoCells().get(0);
        String mACAddress$default = NetworkTools.getMACAddress$default(NetworkTools.INSTANCE, "eth0", null, 2, null);
        if (mACAddress$default == null) {
            mACAddress$default = getString(R.string.settings_info_NA);
            Intrinsics.checkExpressionValueIsNotNull(mACAddress$default, "getString(R.string.settings_info_NA)");
        }
        settingsInfoCellView2.setDetail(mACAddress$default);
        SettingsInfoCellView settingsInfoCellView3 = getInfoCells().get(1);
        String string2 = getString(R.string.settings_info_mac_wifi);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.settings_info_mac_wifi)");
        settingsInfoCellView3.setTitle(string2);
        SettingsInfoCellView settingsInfoCellView4 = getInfoCells().get(1);
        String mACAddress$default2 = NetworkTools.getMACAddress$default(NetworkTools.INSTANCE, "wlan0", null, 2, null);
        if (mACAddress$default2 == null) {
            mACAddress$default2 = getString(R.string.settings_info_NA);
            Intrinsics.checkExpressionValueIsNotNull(mACAddress$default2, "getString(R.string.settings_info_NA)");
        }
        settingsInfoCellView4.setDetail(mACAddress$default2);
        SettingsInfoCellView settingsInfoCellView5 = getInfoCells().get(2);
        String string3 = getString(R.string.settings_info_stb_reboot);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.settings_info_stb_reboot)");
        settingsInfoCellView5.setTitle(string3);
        getInfoCells().get(2).setDetail(String.valueOf(Settings.Global.getInt(getContentResolver(), "boot_count")));
        if (CommonUtils.isProdConfig()) {
            getInfoCells().get(3).setTitle("S/N");
            SettingsInfoCellView settingsInfoCellView6 = getInfoCells().get(3);
            UserService userService = PortalApplication.INSTANCE.getSingleton(this).getBOService().getUserService();
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            settingsInfoCellView6.setDetail(((MegacableUserService) userService).getSerialNumber());
            return;
        }
        getInfoCells().get(3).setTitle("S/N");
        SettingsInfoCellView settingsInfoCellView7 = getInfoCells().get(3);
        UserService userService2 = PortalApplication.INSTANCE.getSingleton(this).getBOService().getUserService();
        if (userService2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        settingsInfoCellView7.setDetail(((MegacableUserService) userService2).getSerialNumber());
        getInfoCells().get(4).setTitle("Real S/N");
        getInfoCells().get(4).setDetail("Real S/N");
        try {
            SettingsInfoCellView settingsInfoCellView8 = getInfoCells().get(4);
            String serial = Build.getSerial();
            Intrinsics.checkExpressionValueIsNotNull(serial, "Build.getSerial()");
            settingsInfoCellView8.setDetail(serial);
        } catch (Throwable th) {
            SettingsInfoCellView settingsInfoCellView9 = getInfoCells().get(4);
            String simpleName = th.getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "e.javaClass.simpleName");
            settingsInfoCellView9.setDetail(simpleName);
        }
    }
}
