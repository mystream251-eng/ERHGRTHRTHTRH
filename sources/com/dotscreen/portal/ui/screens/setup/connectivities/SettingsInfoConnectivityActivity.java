package com.dotscreen.portal.ui.screens.setup.connectivities;

import android.os.Bundle;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.databinding.ActivitySettingsInfoConnectivityBinding;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsInfoConnectivityActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/connectivities/SettingsInfoConnectivityActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "binding", "Lcom/dotscreen/portal/databinding/ActivitySettingsInfoConnectivityBinding;", "getBinding", "()Lcom/dotscreen/portal/databinding/ActivitySettingsInfoConnectivityBinding;", "setBinding", "(Lcom/dotscreen/portal/databinding/ActivitySettingsInfoConnectivityBinding;)V", "viewModel", "Lcom/dotscreen/portal/ui/screens/setup/connectivities/ConnectivityInfoViewModel;", "getViewModel", "()Lcom/dotscreen/portal/ui/screens/setup/connectivities/ConnectivityInfoViewModel;", "setViewModel", "(Lcom/dotscreen/portal/ui/screens/setup/connectivities/ConnectivityInfoViewModel;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SettingsInfoConnectivityActivity extends BaseFragmentActivity {
    private HashMap _$_findViewCache;
    public ActivitySettingsInfoConnectivityBinding binding;
    public ConnectivityInfoViewModel viewModel;

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

    public final ActivitySettingsInfoConnectivityBinding getBinding() {
        ActivitySettingsInfoConnectivityBinding activitySettingsInfoConnectivityBinding = this.binding;
        if (activitySettingsInfoConnectivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return activitySettingsInfoConnectivityBinding;
    }

    public final void setBinding(ActivitySettingsInfoConnectivityBinding activitySettingsInfoConnectivityBinding) {
        Intrinsics.checkParameterIsNotNull(activitySettingsInfoConnectivityBinding, "<set-?>");
        this.binding = activitySettingsInfoConnectivityBinding;
    }

    public final ConnectivityInfoViewModel getViewModel() {
        ConnectivityInfoViewModel connectivityInfoViewModel = this.viewModel;
        if (connectivityInfoViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return connectivityInfoViewModel;
    }

    public final void setViewModel(ConnectivityInfoViewModel connectivityInfoViewModel) {
        Intrinsics.checkParameterIsNotNull(connectivityInfoViewModel, "<set-?>");
        this.viewModel = connectivityInfoViewModel;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_settings_info_connectivity);
        Intrinsics.checkExpressionValueIsNotNull(contentView, "DataBindingUtil.setConte…ttings_info_connectivity)");
        this.binding = (ActivitySettingsInfoConnectivityBinding) contentView;
        ViewModel viewModel = new ViewModelProvider(this).get(ConnectivityInfoViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this).…nfoViewModel::class.java)");
        this.viewModel = (ConnectivityInfoViewModel) viewModel;
        ActivitySettingsInfoConnectivityBinding activitySettingsInfoConnectivityBinding = this.binding;
        if (activitySettingsInfoConnectivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        activitySettingsInfoConnectivityBinding.setLifecycleOwner(this);
        ActivitySettingsInfoConnectivityBinding activitySettingsInfoConnectivityBinding2 = this.binding;
        if (activitySettingsInfoConnectivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        ConnectivityInfoViewModel connectivityInfoViewModel = this.viewModel;
        if (connectivityInfoViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        activitySettingsInfoConnectivityBinding2.setViewModel(connectivityInfoViewModel);
    }
}
