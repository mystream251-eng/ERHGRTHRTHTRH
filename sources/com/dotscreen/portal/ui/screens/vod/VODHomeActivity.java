package com.dotscreen.portal.ui.screens.vod;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VODHomeActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u001a\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/portal/ui/screens/vod/VODHomeActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "_currentSelectLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "currentSelectLiveData", "Landroidx/lifecycle/LiveData;", "getCurrentSelectLiveData", "()Landroidx/lifecycle/LiveData;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "keyCode", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODHomeActivity extends BaseFragmentActivity {
    private HashMap _$_findViewCache;
    private final String TAG = VODHomeActivity.class.getSimpleName();
    private final MutableLiveData<Boolean> _currentSelectLiveData = new MutableLiveData<>(false);

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

    public final LiveData<Boolean> getCurrentSelectLiveData() {
        return this._currentSelectLiveData;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        PortalApplication.Config value;
        super.onCreate(savedInstanceState);
        DSLog dSLog = DSLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        dSLog.s(TAG);
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        final PortalApplication portalApplication = (PortalApplication) singleton;
        final LiveData<PortalApplication.Config> config = portalApplication.getConfig();
        if (config.getValue() == null) {
            setContentView(R.layout.activity_startup);
            config.observeForever(new Observer<PortalApplication.Config>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeActivity$onCreate$observer$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(PortalApplication.Config config2) {
                    PortalApplication.Config config3;
                    Log.d(this.this$0.TAG, "...config loaded");
                    this.this$0.finish();
                    if (portalApplication.getDegradedMode()) {
                        return;
                    }
                    PortalApplication.Config config4 = (PortalApplication.Config) config.getValue();
                    if ((config4 != null ? config4.getUser() : null) == null && ((config3 = (PortalApplication.Config) config.getValue()) == null || !config3.getLoginFailed())) {
                        this.this$0.startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=choose", 0));
                    } else {
                        VODHomeActivity vODHomeActivity = this.this$0;
                        vODHomeActivity.startActivity(vODHomeActivity.getIntent());
                    }
                    config.removeObserver(this);
                }
            });
            portalApplication.loadConfig();
            return;
        }
        PortalApplication.Config value2 = config.getValue();
        if ((value2 != null ? value2.getUser() : null) == null && (value = config.getValue()) != null && !value.getLoginFailed()) {
            finish();
        } else {
            setContentView(R.layout.activity_vod_home);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 19 || keyCode == 20 || keyCode == 22) {
            this._currentSelectLiveData.postValue(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
