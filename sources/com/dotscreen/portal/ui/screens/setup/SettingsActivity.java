package com.dotscreen.portal.ui.screens.setup;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.OnChildSelectedListener;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.farkit.debug.CheatCodeHelper;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.components.NotificationView;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.model.LauncherItemsViewModel;
import com.dotscreen.portal.ui.cards.TextCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.home.HomeActivity;
import com.dotscreen.portal.ui.screens.onboarding.ChannelScanActivity;
import com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog;
import com.dotscreen.portal.ui.screens.profile.PinCodeDialog;
import com.dotscreen.portal.utils.AudioZTEManager;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.gms.common.Scopes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u00012\u00020\u0002:\u0001.B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\"\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0013H\u0016J\u0012\u0010\u001c\u001a\u00020\u00132\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u001a\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u001a\u0010$\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010%\u001a\u00020\u00132\b\u0010&\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010'\u001a\u00020\u0013H\u0014J\b\u0010(\u001a\u00020\u0013H\u0014J\u0010\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020\u0007H\u0002J\u0006\u0010+\u001a\u00020\u0013J\u0010\u0010,\u001a\u00020\u00132\b\b\u0002\u0010-\u001a\u00020 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "Lcom/dotscreen/farkit/debug/CheatCodeHelper$OnTriggerListener;", "()V", "REQUEST_SCAN_CHANNELS", "", "TAG", "", "kotlin.jvm.PlatformType", "actionBroadcastReceiver", "Lcom/dotscreen/portal/ui/screens/setup/SettingsActivity$ActionBroadcastReceiver;", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "audioZTEManager", "Lcom/dotscreen/portal/utils/AudioZTEManager;", "cheatCodeHelper", "Lcom/dotscreen/farkit/debug/CheatCodeHelper;", "numberOfColumn", "handleNotificationFocus", "", "gridViewHolder", "Landroidx/leanback/widget/VerticalGridPresenter$ViewHolder;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCheatCodetriggered", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyUp", "onNewIntent", "intent", "onStart", "onStop", "pathRequiredNetwork", "path", "showAudioScreenZTE", "updateAdapter", "isDVB", "ActionBroadcastReceiver", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SettingsActivity extends BaseFragmentActivity implements CheatCodeHelper.OnTriggerListener {
    private HashMap _$_findViewCache;
    private ActionBroadcastReceiver actionBroadcastReceiver;
    private ArrayObjectAdapter adapter;
    private AudioZTEManager audioZTEManager;
    private final String TAG = SettingsActivity.class.getSimpleName();
    private final int REQUEST_SCAN_CHANNELS = 1;
    private final int numberOfColumn = 5;
    private final CheatCodeHelper cheatCodeHelper = new CheatCodeHelper(new Integer[]{36, 37, 44, 44, 43, 40, 53, 48, 33}, this);

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

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        VerticalGridPresenter verticalGridPresenter = new VerticalGridPresenter(0, false);
        verticalGridPresenter.setNumberOfColumns(this.numberOfColumn);
        verticalGridPresenter.setShadowEnabled(true);
        OnItemViewClickedListener onItemViewClickedListener = new OnItemViewClickedListener() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsActivity$onCreate$listener$1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                if (obj instanceof BaseWidget) {
                    BaseWidget baseWidget = (BaseWidget) obj;
                    if (baseWidget.getIntent() != null) {
                        Uri data = baseWidget.getIntent().getData();
                        PortalApplication.Companion companion = PortalApplication.INSTANCE;
                        Context applicationContext = this.this$0.getApplicationContext();
                        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                        if (companion.getSingleton(applicationContext).getDegradedMode() && data != null) {
                            SettingsActivity settingsActivity = this.this$0;
                            List<String> pathSegments = data.getPathSegments();
                            Intrinsics.checkExpressionValueIsNotNull(pathSegments, "uri.pathSegments");
                            String str = (String) CollectionsKt.lastOrNull((List) pathSegments);
                            if (str == null) {
                                str = "";
                            }
                            if (settingsActivity.pathRequiredNetwork(str)) {
                                Toast.makeText(this.this$0.getApplicationContext(), R.string.noAvailable, 1).show();
                                return;
                            }
                        }
                        if (baseWidget.getIntent().getAction() == null) {
                            this.this$0.startActivity(baseWidget.getIntent());
                            return;
                        }
                        String action = baseWidget.getIntent().getAction();
                        if (action != null) {
                            if (StringsKt.startsWith$default(action, "com.dotscreen.megacable.portal", false, 2, (Object) null)) {
                                LocalBroadcastManager.getInstance(this.this$0).sendBroadcast(baseWidget.getIntent());
                                return;
                            }
                            if (Intrinsics.areEqual(action, SettingsActivityKt.ACTION_CLEAR_DATA)) {
                                Object systemService = this.this$0.getSystemService("activity");
                                if (systemService == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
                                }
                                ((ActivityManager) systemService).clearApplicationUserData();
                                return;
                            }
                            this.this$0.startActivity(baseWidget.getIntent());
                            return;
                        }
                        return;
                    }
                    Toast.makeText(this.this$0, R.string.noAvailable, 0).show();
                }
            }
        };
        if ((AudioZTEManager.INSTANCE.isZtePlatform() && AudioZTEManager.INSTANCE.isZtePhase2()) || AudioZTEManager.INSTANCE.isZtePlatform2()) {
            System.out.println((Object) "IS_ZTE");
            AudioZTEManager audioZTEManager = new AudioZTEManager(this);
            System.out.println((Object) "LAUNCH_ZTE");
            audioZTEManager.connectToAudioService();
            this.audioZTEManager = audioZTEManager;
        }
        verticalGridPresenter.setOnItemViewClickedListener(onItemViewClickedListener);
        this.adapter = new ArrayObjectAdapter(new TextCardPresenter(Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.settings_page_card_width)), Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.settings_page_card_width))));
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.browse_grid_dock);
        VerticalGridPresenter.ViewHolder gridViewHolder = verticalGridPresenter.onCreateViewHolder(viewGroup);
        viewGroup.addView(gridViewHolder.view);
        verticalGridPresenter.onBindViewHolder(gridViewHolder, this.adapter);
        Intrinsics.checkExpressionValueIsNotNull(gridViewHolder, "gridViewHolder");
        handleNotificationFocus(gridViewHolder);
        updateAdapter(CommonUtils.isDVBCSTB(this));
    }

    private final void handleNotificationFocus(VerticalGridPresenter.ViewHolder gridViewHolder) {
        gridViewHolder.getGridView().setOnChildSelectedListener(new OnChildSelectedListener() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsActivity.handleNotificationFocus.1
            @Override // androidx.leanback.widget.OnChildSelectedListener
            public final void onChildSelected(ViewGroup viewGroup, View view, int i, long j) {
                NotificationView notificationView = (NotificationView) SettingsActivity.this.findViewById(R.id.notification_icon);
                if (notificationView != null) {
                    if (i >= SettingsActivity.this.numberOfColumn) {
                        notificationView.setFocusable(0);
                    } else {
                        notificationView.setFocusable(1);
                    }
                }
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.actionBroadcastReceiver == null) {
            this.actionBroadcastReceiver = new ActionBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SettingsActivityKt.ACTION_SCAN_CHANNELS);
            intentFilter.addAction(SettingsActivityKt.ACTION_AUDIO_ZTE);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
            ActionBroadcastReceiver actionBroadcastReceiver = this.actionBroadcastReceiver;
            if (actionBroadcastReceiver == null) {
                Intrinsics.throwNpe();
            }
            localBroadcastManager.registerReceiver(actionBroadcastReceiver, intentFilter);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ActionBroadcastReceiver actionBroadcastReceiver = this.actionBroadcastReceiver;
        if (actionBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(actionBroadcastReceiver);
        }
        this.actionBroadcastReceiver = (ActionBroadcastReceiver) null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.REQUEST_SCAN_CHANNELS && resultCode == -1) {
            Intent intent = new Intent(this, (Class<?>) HomeActivity.class);
            intent.addFlags(268468224);
            startActivity(intent);
            Intent intent2 = new Intent();
            intent2.setAction(CommonUtils.ACTION_CLOSE_LIVE_APP);
            sendBroadcast(intent2);
            Runtime.getRuntime().exit(0);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            Uri data = intent.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            String queryParameter = data.getQueryParameter("action");
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            if (companion.getSingleton(applicationContext).getDegradedMode()) {
                Toast.makeText(getApplicationContext(), R.string.noAvailable, 1).show();
                return;
            }
            if (Intrinsics.areEqual(queryParameter, "update_purchase")) {
                SettingsActivity settingsActivity = this;
                LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(settingsActivity).getConfig();
                Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(this).config");
                PortalApplication.Config value = config.getValue();
                RightVUser user = value != null ? value.getUser() : null;
                if (user == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
                }
                if (!user.getIsMain()) {
                    Toast.makeText(settingsActivity, getString(R.string.main_only_edit_purchase_pin), 0).show();
                    return;
                } else {
                    new MultiplePinCodeDialog.Builder(settingsActivity).setEditPinCodeType(MultiplePinCodeDialog.EditPincodeType.UpdatePurchase).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsActivity.onNewIntent.1
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
                    return;
                }
            }
            if (Intrinsics.areEqual(queryParameter, "setup")) {
                final SettingsActivity settingsActivity2 = this;
                PinCodeDialog.Builder pincodeType = new PinCodeDialog.Builder(this).setTitle(R.string.service_pin_code).setPincodeType(PincodeType.General);
                Context applicationContext2 = getApplicationContext();
                if (applicationContext2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                PortalApplication.Config value2 = ((PortalApplication) applicationContext2).getConfig().getValue();
                if (value2 == null) {
                    Intrinsics.throwNpe();
                }
                RightVUser user2 = value2.getUser();
                if (user2 == null) {
                    Intrinsics.throwNpe();
                }
                pincodeType.setUser(user2).hideDefaultMessage().setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsActivity.onNewIntent.2
                    @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                    public void onPinCodeCorrect(PinCodeDialog dialog, List<String> pinCodes) {
                        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                        Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
                        PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeCorrect(this, dialog, pinCodes);
                    }

                    @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                    public void onPinCodeInvalid(PinCodeDialog dialog, String pinCode) {
                        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                        Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                        PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCode);
                    }

                    @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                    public void onPinCodeInvalid(PinCodeDialog dialog, List<String> pinCodes, Exception exc) {
                        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                        Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
                        PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCodes, exc);
                    }

                    @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                    public void onPinCodeCorrect(PinCodeDialog dialog, String pinCode) {
                        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                        Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                        SettingsActivity.this.startActivity(new Intent(settingsActivity2, (Class<?>) UpdaterActivity.class));
                        dialog.dismiss();
                    }
                }).create().show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean pathRequiredNetwork(String path) {
        Log.d(this.TAG, "Check path : " + path);
        return ArraysKt.indexOf(new String[]{"rightvpackages", Scopes.PROFILE, "authorized_info"}, path) != -1;
    }

    public static /* synthetic */ void updateAdapter$default(SettingsActivity settingsActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        settingsActivity.updateAdapter(z);
    }

    public final void updateAdapter(final boolean isDVB) {
        final ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            Context applicationContext = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            ViewModel viewModel = ViewModelProviders.of(this, new LauncherItemsViewModel.Factory(applicationContext)).get(LauncherItemsViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…emsViewModel::class.java)");
            ((LauncherItemsViewModel) viewModel).getSettingsPageItems().observe(this, new Observer<List<? extends BaseWidget>>() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsActivity$updateAdapter$$inlined$let$lambda$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(List<? extends BaseWidget> list) {
                    ArrayList arrayList;
                    ArrayObjectAdapter arrayObjectAdapter2 = arrayObjectAdapter;
                    if (list != null) {
                        ArrayList arrayList2 = new ArrayList();
                        for (T t : list) {
                            BaseWidget baseWidget = (BaseWidget) t;
                            boolean z = true;
                            if (!isDVB && !(!Intrinsics.areEqual(baseWidget.getTitle(), this.getString(R.string.settings_info_signal_title)))) {
                                z = false;
                            }
                            if (z) {
                                arrayList2.add(t);
                            }
                        }
                        arrayList = arrayList2;
                    } else {
                        arrayList = null;
                    }
                    arrayObjectAdapter2.addAll(0, arrayList);
                    new Handler().postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsActivity$updateAdapter$$inlined$let$lambda$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.browse_grid_dock);
                            if (viewGroup != null) {
                                viewGroup.requestFocus();
                            }
                        }
                    }, 100L);
                }
            });
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.cheatCodeHelper.onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        this.cheatCodeHelper.onKeyUp(keyCode, event);
        return super.onKeyUp(keyCode, event);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, com.dotscreen.farkit.debug.CheatCodeHelper.OnTriggerListener
    public void onCheatCodetriggered() {
        Log.d(this.TAG, "onCheatCodetriggered() !!!!");
        startActivity(new Intent(this, (Class<?>) UpdaterActivity.class));
    }

    /* JADX INFO: compiled from: SettingsActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsActivity$ActionBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/portal/ui/screens/setup/SettingsActivity;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ActionBroadcastReceiver extends BroadcastReceiver {
        public ActionBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            int iHashCode = action.hashCode();
            if (iHashCode != -1336786000) {
                if (iHashCode == 1112538048 && action.equals(SettingsActivityKt.ACTION_SCAN_CHANNELS)) {
                    PortalApplication.Companion companion = PortalApplication.INSTANCE;
                    Context applicationContext = SettingsActivity.this.getApplicationContext();
                    Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                    if (!companion.getSingleton(applicationContext).getDegradedMode()) {
                        SettingsActivity.this.startActivityForResult(new Intent(context, (Class<?>) ChannelScanActivity.class), SettingsActivity.this.REQUEST_SCAN_CHANNELS);
                        return;
                    } else {
                        Toast.makeText(SettingsActivity.this.getApplicationContext(), R.string.noAvailable, 1).show();
                        return;
                    }
                }
                return;
            }
            if (action.equals(SettingsActivityKt.ACTION_AUDIO_ZTE)) {
                System.out.println((Object) "ACTION_AUDIO_ZTE");
                SettingsActivity.this.showAudioScreenZTE();
            }
        }
    }

    public final void showAudioScreenZTE() {
        AudioZTEManager audioZTEManager = this.audioZTEManager;
        if (audioZTEManager != null) {
            new AudioDialogZTE.Builder(this, audioZTEManager).setTitle(R.string.audioZTESubtitles).create(new Function0<Unit>() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsActivity$showAudioScreenZTE$$inlined$let$lambda$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AudioZTEManager audioZTEManager2 = this.this$0.audioZTEManager;
                    if (audioZTEManager2 != null) {
                        audioZTEManager2.connectToAudioService();
                    }
                }
            }).show();
        }
    }
}
