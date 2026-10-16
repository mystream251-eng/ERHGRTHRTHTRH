package com.dotscreen.portal.ui.screens.onboarding;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.oregan.sparq.dtvsupportlibrary.TV;

/* JADX INFO: compiled from: FWUpdateCheckActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0014R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/ui/screens/onboarding/FWUpdateCheckActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "", "keyCode", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onStart", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class FWUpdateCheckActivity extends BaseFragmentActivity {
    private final String TAG = FWUpdateCheckActivity.class.getSimpleName();
    private HashMap _$_findViewCache;

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

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) applicationContext;
        portalApplication.setMustShowChannelScanning(false);
        portalApplication.setChannelScanning(true);
        setContentView(R.layout.activity_fw_update_check);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        String string;
        String version;
        super.onStart();
        int i = SystemProperties.getInt("ro.boot.update_flag", -1);
        Log.d(this.TAG, "updateFlag = " + i);
        if (i != 1) {
            if (i == 2) {
                View viewFindViewById = findViewById(R.id.root);
                if (viewFindViewById != null) {
                    viewFindViewById.setBackgroundColor((int) 4284874752L);
                }
                TextView textView = (TextView) findViewById(R.id.message);
                if (textView != null) {
                    textView.setText("Error al actualizar el firmware\n (Presione cualquier tecla para continuar)");
                    return;
                }
                return;
            }
            setResult(-1);
            finish();
            return;
        }
        View viewFindViewById2 = findViewById(R.id.root);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setBackgroundColor((int) 4278216192L);
        }
        try {
            TV tv2 = TV.getInstance(this);
            if (tv2 == null || (version = tv2.getVersion()) == null || (string = version.toString()) == null) {
                string = "N/A";
            }
        } catch (Exception unused) {
        }
        if (Intrinsics.areEqual(string, "N/A") || Intrinsics.areEqual(string, "")) {
            string = SystemProperties.get("ro.build.version.incremental", "N/A");
            Intrinsics.checkExpressionValueIsNotNull(string, "SystemProperties.get(\"ro…sion.incremental\", \"N/A\")");
        }
        if (Intrinsics.areEqual(string, "N/A") || Intrinsics.areEqual(string, "")) {
            string = SystemProperties.get("ro.build.fingerprint", "N/A");
            Intrinsics.checkExpressionValueIsNotNull(string, "SystemProperties.get(\"ro…uild.fingerprint\", \"N/A\")");
        }
        if (Intrinsics.areEqual(string, "N/A") || Intrinsics.areEqual(string, "")) {
            string = SystemProperties.get("ro.product.version.software", "N/A");
            Intrinsics.checkExpressionValueIsNotNull(string, "SystemProperties.get(\"ro…version.software\", \"N/A\")");
        }
        TextView textView2 = (TextView) findViewById(R.id.message);
        if (textView2 != null) {
            textView2.setText("Firmware " + string + " ha sido exitosamente actualizado\n (Presione cualquier tecla para continuar)");
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        setResult(-1);
        finish();
        return true;
    }
}
