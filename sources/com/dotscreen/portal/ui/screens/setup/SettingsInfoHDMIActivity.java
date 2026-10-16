package com.dotscreen.portal.ui.screens.setup;

import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.view.View;
import android.widget.TextView;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.ui.views.SettingsInfoCellView;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import java.lang.reflect.Method;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsInfoActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoHDMIActivity;", "Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoActivity;", "()V", "gcd", "", TtmlNode.TAG_P, "q", "getSystemResolution", "", "populateTab", "", "ratio", "a", "b", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SettingsInfoHDMIActivity extends SettingsInfoActivity {
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
            title.setText(getString(R.string.settings_info_hdmi_title));
        }
        SettingsInfoCellView settingsInfoCellView = getInfoCells().get(0);
        Intrinsics.checkExpressionValueIsNotNull(settingsInfoCellView, "infoCells[0]");
        settingsInfoCellView.setVisibility(8);
        SettingsInfoCellView settingsInfoCellView2 = getInfoCells().get(1);
        String string = getString(R.string.settings_info_hdmi_aspect);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.settings_info_hdmi_aspect)");
        settingsInfoCellView2.setTitle(string);
        SettingsInfoCellView settingsInfoCellView3 = getInfoCells().get(1);
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        int i = resources.getDisplayMetrics().widthPixels;
        Resources resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        settingsInfoCellView3.setDetail(ratio(i, resources2.getDisplayMetrics().heightPixels));
        SettingsInfoCellView settingsInfoCellView4 = getInfoCells().get(2);
        String string2 = getString(R.string.settings_info_hdmi_audio);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.settings_info_hdmi_audio)");
        settingsInfoCellView4.setTitle(string2);
        Object systemService = getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
        }
        AudioManager audioManager = (AudioManager) systemService;
        Object systemService2 = getSystemService("media_router");
        if (systemService2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.media.MediaRouter");
        }
        audioManager.getDevices(2);
        audioManager.getActivePlaybackConfigurations();
        SettingsInfoCellView settingsInfoCellView5 = getInfoCells().get(2);
        MediaRouter.RouteInfo selectedRoute = ((MediaRouter) systemService2).getSelectedRoute(1);
        Intrinsics.checkExpressionValueIsNotNull(selectedRoute, "audioRouter.getSelectedR…te(ROUTE_TYPE_LIVE_AUDIO)");
        settingsInfoCellView5.setDetail(selectedRoute.getName().toString());
    }

    public final String getSystemResolution() throws ClassNotFoundException {
        Class<?> cls = Class.forName("android.os.SystemProperties");
        Method method = cls != null ? cls.getMethod("get", String.class, String.class) : null;
        return String.valueOf(method != null ? method.invoke(null, "config.HDMIMode", null) : null);
    }

    public final int gcd(int p, int q) {
        return q == 0 ? p : gcd(q, p % q);
    }

    public final String ratio(int a2, int b) {
        int iGcd = gcd(a2, b);
        if (a2 > b) {
            StringBuilder sb = new StringBuilder();
            sb.append(a2 / iGcd);
            sb.append('/');
            sb.append(b / iGcd);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(b / iGcd);
        sb2.append('/');
        sb2.append(a2 / iGcd);
        return sb2.toString();
    }
}
