package com.dotscreen.portal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.components.NotificationView;
import com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel;
import com.dotscreen.tv.ui.views.TimeView;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivitySettingsInfoConnectivityBinding extends ViewDataBinding {

    @Bindable
    protected ConnectivityInfoViewModel mViewModel;
    public final NotificationView notificationIcon;
    public final TimeView time;
    public final TextView title;
    public final TextView value1Status;
    public final TextView value2Status;
    public final TextView value3Status;
    public final TextView value4Status;
    public final TextView value5Status;
    public final TextView value6Status;
    public final TextView value7Status;
    public final TextView value8Status;
    public final TextView valueCompassStatus;
    public final TextView valueConnectivityType;
    public final TextView valueRightvStatus;
    public final TextView valueUiopStatus;

    public abstract void setViewModel(ConnectivityInfoViewModel connectivityInfoViewModel);

    protected ActivitySettingsInfoConnectivityBinding(Object obj, View view, int i, NotificationView notificationView, TimeView timeView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13) {
        super(obj, view, i);
        this.notificationIcon = notificationView;
        this.time = timeView;
        this.title = textView;
        this.value1Status = textView2;
        this.value2Status = textView3;
        this.value3Status = textView4;
        this.value4Status = textView5;
        this.value5Status = textView6;
        this.value6Status = textView7;
        this.value7Status = textView8;
        this.value8Status = textView9;
        this.valueCompassStatus = textView10;
        this.valueConnectivityType = textView11;
        this.valueRightvStatus = textView12;
        this.valueUiopStatus = textView13;
    }

    public ConnectivityInfoViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivitySettingsInfoConnectivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingsInfoConnectivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivitySettingsInfoConnectivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_settings_info_connectivity, viewGroup, z, obj);
    }

    public static ActivitySettingsInfoConnectivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingsInfoConnectivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivitySettingsInfoConnectivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_settings_info_connectivity, null, false, obj);
    }

    public static ActivitySettingsInfoConnectivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingsInfoConnectivityBinding bind(View view, Object obj) {
        return (ActivitySettingsInfoConnectivityBinding) bind(obj, view, R.layout.activity_settings_info_connectivity);
    }
}
