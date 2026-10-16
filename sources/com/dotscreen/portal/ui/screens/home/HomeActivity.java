package com.dotscreen.portal.ui.screens.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationKt;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog;
import com.dotscreen.portal.ui.screens.profile.PinCodeDialog;
import com.dotscreen.portal.utils.Activity_ExtensionKt;
import com.dotscreen.portal.utils.BootSequenceListener;
import com.dotscreen.portal.utils.BootSequenceManager;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.utils.PermissionUtils;
import com.dotscreen.tvinput.SyncTVChannelsWorkerKt;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u0012\u0010\u0018\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0016H\u0014J\u0012\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J+\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u00052\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\"2\u0006\u0010#\u001a\u00020$H\u0016¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020\u0016H\u0014J\b\u0010'\u001a\u00020\u0016H\u0014J\b\u0010(\u001a\u00020\u0016H\u0014J\u0018\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u0005H\u0016J\u0006\u0010/\u001a\u00020\u0016J\b\u00100\u001a\u000201H\u0002J\u0006\u00102\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/dotscreen/portal/ui/screens/home/HomeActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "Lcom/dotscreen/portal/utils/BootSequenceListener;", "()V", "PERMISSIONS_REQUEST_READ_TV_LISTINGS", "", "PERMISSION_READ_TV_LISTINGS", "", "TAG", "kotlin.jvm.PlatformType", "elapsedTime", "isHomePressed", "", "mHandler", "Landroid/os/Handler;", "retryCount", "getRetryCount", "()I", "setRetryCount", "(I)V", "waitingTime", "backHomeDone", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "intent", "Landroid/content/Intent;", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onRestart", "onResume", "onStart", "onStepFailed", "step", "Lcom/dotscreen/portal/utils/BootSequenceManager$Steps;", "state", "Lcom/dotscreen/portal/utils/BootSequenceManager$State;", "delay", "openConfigDialog", "updateUnavailabilityMessage", "Ljava/lang/Runnable;", "validateThatAProfileIsLoggedInOrRidirectToLogin", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HomeActivity extends BaseFragmentActivity implements BootSequenceListener {
    private HashMap _$_findViewCache;
    private int elapsedTime;
    private boolean isHomePressed;
    private int retryCount;
    private int waitingTime;
    private final String TAG = HomeActivity.class.getSimpleName();
    private final int PERMISSIONS_REQUEST_READ_TV_LISTINGS = 1;
    private final String PERMISSION_READ_TV_LISTINGS = TvContractCompat.PERMISSION_READ_TV_LISTINGS;
    private final Handler mHandler = new Handler();

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

    @Override // com.dotscreen.portal.utils.BootSequenceListener
    public void onStepFailed(BootSequenceManager.Steps step, BootSequenceManager.State state) {
        Intrinsics.checkParameterIsNotNull(step, "step");
        Intrinsics.checkParameterIsNotNull(state, "state");
    }

    public final int getRetryCount() {
        return this.retryCount;
    }

    public final void setRetryCount(int i) {
        this.retryCount = i;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Set<String> setKeySet;
        super.onCreate(savedInstanceState);
        Log.d(this.TAG, "onCreate()");
        validateThatAProfileIsLoggedInOrRidirectToLogin();
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        final PortalApplication portalApplication = (PortalApplication) singleton;
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("   extras = ");
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        sb.append(intent.getExtras());
        Log.d(str, sb.toString());
        Intent intent2 = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent2, "intent");
        Bundle extras = intent2.getExtras();
        if (extras != null && (setKeySet = extras.keySet()) != null) {
            for (String str2 : setKeySet) {
                String str3 = this.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("       [");
                sb2.append(str2);
                sb2.append("] : ");
                Intent intent3 = getIntent();
                Intrinsics.checkExpressionValueIsNotNull(intent3, "intent");
                sb2.append(intent3.getExtras().get(str2));
                Log.d(str3, sb2.toString());
            }
        }
        if (displaySerialNumberEditDialogIfNeeded()) {
            return;
        }
        portalApplication.getBootSequenceManager().registerBootSequenceListener(this);
        final LiveData<PortalApplication.Config> config = portalApplication.getConfig();
        if (portalApplication.getChannelScanning()) {
            Log.d(this.TAG, "Still Scanning...");
            setContentView(R.layout.activity_startup);
        } else {
            if (config.getValue() == null) {
                setContentView(R.layout.activity_startup);
                Log.d(this.TAG, "Loading Config...");
                config.observeForever(new Observer<PortalApplication.Config>() { // from class: com.dotscreen.portal.ui.screens.home.HomeActivity$onCreate$observer$1
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(PortalApplication.Config config2) {
                        PortalApplication.Config config3;
                        Log.d(this.this$0.TAG, "...config loaded");
                        PortalApplication.Config config4 = (PortalApplication.Config) config.getValue();
                        if ((config4 != null ? config4.getUser() : null) == null && !portalApplication.getDegradedMode() && (config3 = (PortalApplication.Config) config.getValue()) != null && !config3.getLoginFailed()) {
                            String str4 = this.this$0.TAG;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("---1 : configLiveData\n");
                            PortalApplication.Config config5 = (PortalApplication.Config) config.getValue();
                            sb3.append(config5 != null ? config5.getUser() : null);
                            sb3.append('\n');
                            PortalApplication.Config config6 = (PortalApplication.Config) config.getValue();
                            sb3.append(config6 != null ? Boolean.valueOf(config6.getLoginFailed()) : null);
                            Log.e(str4, sb3.toString());
                            this.this$0.finish();
                            this.this$0.startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=choose", 0));
                        } else if (!portalApplication.getDegradedMode()) {
                            Log.e(this.this$0.TAG, "---2 : configLiveData\n" + ((PortalApplication.Config) config.getValue()));
                            this.this$0.recreate();
                        }
                        config.removeObserver(this);
                    }
                });
                portalApplication.loadConfig();
                return;
            }
            setContentView(R.layout.activity_main);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) singleton;
        LiveData<PortalApplication.Config> config = portalApplication.getConfig();
        PortalApplication.Config value = config.getValue();
        if ((value != null ? value.getUser() : null) == null && !portalApplication.getDegradedMode() && portalApplication.getBootComplete()) {
            PortalApplication.Config value2 = config.getValue();
            if (value2 == null || !value2.getLoginFailed()) {
                finish();
                startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=choose", 0));
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        validateThatAProfileIsLoggedInOrRidirectToLogin();
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        ((PortalApplication) singleton).getBootSequenceManager().unregisterBootSequenceListener(this);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        validateThatAProfileIsLoggedInOrRidirectToLogin();
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.post(updateUnavailabilityMessage());
        HomeActivity homeActivity = this;
        PortalApplicationKt.scheduleUpdaterCheck(homeActivity);
        PortalApplicationKt.scheduleCoreCheck(homeActivity);
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        SyncTVChannelsWorkerKt.scheduleSyncingTVChannels$default(applicationContext, Duration.ofMinutes(50L).plusMinutes((long) (Math.random() * ((double) 20))), false, false, 12, null);
        requestVisibleBehind(true);
        if (PermissionUtils.hasAccessAllEpg(homeActivity) || checkSelfPermission(this.PERMISSION_READ_TV_LISTINGS) == 0) {
            return;
        }
        Log.d(this.TAG, "LUDO: will ask permission");
        requestPermissions(new String[]{this.PERMISSION_READ_TV_LISTINGS}, this.PERMISSIONS_REQUEST_READ_TV_LISTINGS);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        validateThatAProfileIsLoggedInOrRidirectToLogin();
        if (Intrinsics.areEqual("android.intent.action.MAIN", intent != null ? intent.getAction() : null)) {
            this.isHomePressed = true;
        }
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) singleton;
        LiveData<PortalApplication.Config> config = portalApplication.getConfig();
        PortalApplication.Config value = config.getValue();
        if ((value != null ? value.getUser() : null) != null || portalApplication.getDegradedMode()) {
            return;
        }
        PortalApplication.Config value2 = config.getValue();
        if (value2 == null || !value2.getLoginFailed()) {
            finish();
            startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=choose", 0));
        }
    }

    public final void validateThatAProfileIsLoggedInOrRidirectToLogin() {
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) singleton;
        PortalApplication.Config value = portalApplication.getConfig().getValue();
        if ((value != null ? value.getUser() : null) == null && !portalApplication.getDegradedMode() && portalApplication.getBootComplete()) {
            Log.d(this.TAG, "home page w/o profile => finish activity");
            finish();
            startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=choose", 0));
        }
    }

    /* JADX INFO: renamed from: isHomePressed, reason: from getter */
    public final boolean getIsHomePressed() {
        return this.isHomePressed;
    }

    public final void backHomeDone() {
        this.isHomePressed = false;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        if (requestCode == this.PERMISSIONS_REQUEST_READ_TV_LISTINGS) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                finish();
                Activity_ExtensionKt.launchHome$default(this, null, 1, null);
            } else {
                Toast.makeText(this, R.string.msg_read_tv_listing_permission_denied, 1).show();
                finish();
            }
        }
    }

    public final void openConfigDialog() {
        HomeActivity homeActivity = this;
        LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(homeActivity).getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(this).config");
        PortalApplication.Config value = config.getValue();
        RightVUser user = value != null ? value.getUser() : null;
        if (user == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
        }
        if (!user.getIsMain()) {
            Toast.makeText(homeActivity, getString(R.string.main_only_edit_purchase_pin), 0).show();
        } else {
            new MultiplePinCodeDialog.Builder(homeActivity).setEditPinCodeType(MultiplePinCodeDialog.EditPincodeType.UpdatePurchase).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.home.HomeActivity.openConfigDialog.1
                @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                public void onPinCodeCorrect(PinCodeDialog dialog, String pinCode) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                }

                @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                public void onPinCodeInvalid(PinCodeDialog dialog, List<String> pinCodes, Exception error) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
                }

                @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                public void onPinCodeInvalid(PinCodeDialog dialog, String pinCode) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                    PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCode);
                }

                @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                public void onPinCodeCorrect(PinCodeDialog dialog, List<String> pinCodes) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
                    dialog.dismiss();
                    Toast.makeText(dialog.getContext(), R.string.pin_code_update_success, 0).show();
                }
            }).create().show();
        }
    }

    @Override // com.dotscreen.portal.utils.BootSequenceListener
    public void onStepFailed(int delay) {
        this.waitingTime = delay;
        this.elapsedTime = 0;
        this.retryCount++;
        this.mHandler.post(updateUnavailabilityMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Runnable updateUnavailabilityMessage() {
        return new Runnable() { // from class: com.dotscreen.portal.ui.screens.home.HomeActivity.updateUnavailabilityMessage.1
            @Override // java.lang.Runnable
            public final void run() {
                TextView textView = (TextView) HomeActivity.this.findViewById(R.id.message);
                if (textView != null) {
                    StringBuilder sb = new StringBuilder();
                    HomeActivity homeActivity = HomeActivity.this;
                    sb.append(homeActivity.getString(R.string.technical_error_retry_count, new Object[]{Integer.valueOf(homeActivity.getRetryCount())}));
                    sb.append(" \n ");
                    sb.append(HomeActivity.this.getString(R.string.technical_error));
                    sb.append(" \n");
                    HomeActivity homeActivity2 = HomeActivity.this;
                    sb.append(homeActivity2.getString(R.string.technical_error_retry_join_server_in, new Object[]{Integer.valueOf(homeActivity2.waitingTime - HomeActivity.this.elapsedTime)}));
                    textView.setText(sb.toString());
                    HomeActivity.this.elapsedTime++;
                }
                if (HomeActivity.this.waitingTime - HomeActivity.this.elapsedTime >= 0) {
                    HomeActivity.this.mHandler.postDelayed(HomeActivity.this.updateUnavailabilityMessage(), 1000L);
                    return;
                }
                if (textView != null) {
                    textView.setText("");
                }
                HomeActivity.this.mHandler.removeCallbacksAndMessages(null);
            }
        };
    }
}
