package com.dotscreen.portal.ui.screens.setup;

import android.content.Context;
import android.media.tv.TvView;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.utils.DVBTechnicolorManager;
import com.dotscreen.portal.utils.String_ExtensionKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.DVBDevice;
import com.dotscreen.tv.common.utils.Measure;
import com.dotscreen.tv.common.utils.PhysicKt;
import com.dotscreen.tv.common.utils.PowerOfTen;
import com.dotscreen.tv.common.utils.SignalInfoHelper;
import com.dotscreen.tv.common.utils.TechnicolorDevice;
import com.dotscreen.tv.common.utils.TunerMetrics;
import com.dotscreen.tv.ui.views.SettingsInfoCellView;
import io.sentry.protocol.Device;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import net.oregan.sparq.dtvsupportlibrary.DVB;
import net.oregan.sparq.dtvsupportlibraryaidl.DVBTunerMetrics;

/* JADX INFO: compiled from: SettingsInfoActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u000f\u001a\u00020\u00102\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014J\u0012\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0010H\u0014J\b\u0010\u0019\u001a\u00020\u0010H\u0014J\b\u0010\u001a\u001a\u00020\u0010H\u0016J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoSignalActivity;", "Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoActivity;", "()V", Device.TYPE, "Lcom/dotscreen/tv/common/utils/DVBDevice;", "mHandler", "Landroid/os/Handler;", "mTvView", "Landroid/media/tv/TvView;", "oreganDVB", "Lnet/oregan/sparq/dtvsupportlibrary/DVB;", "tchDVB", "Lcom/dotscreen/portal/utils/DVBTechnicolorManager;", "updateMetrics", "Ljava/lang/Runnable;", "fillInfoCells", "", "infos", "Ljava/util/ArrayList;", "Lcom/dotscreen/tv/ui/views/SettingsInfoCellView;", "Lkotlin/collections/ArrayList;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStop", "populateTab", "toE6Number", "", "value", "", "updatefun", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SettingsInfoSignalActivity extends SettingsInfoActivity {
    private HashMap _$_findViewCache;
    private DVBDevice device;
    private TvView mTvView;
    private DVB oreganDVB;
    private DVBTechnicolorManager tchDVB;
    private final Runnable updateMetrics = new Runnable() { // from class: com.dotscreen.portal.ui.screens.setup.SettingsInfoSignalActivity$updateMetrics$1
        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.updatefun();
        }
    };
    private final Handler mHandler = new Handler();

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

    @Override // com.dotscreen.portal.ui.screens.setup.SettingsInfoActivity, com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View viewFindViewById = findViewById(R.id.tvView);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.tvView)");
        this.mTvView = (TvView) viewFindViewById;
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        com.dotscreen.tv.common.utils.Device currentDevice = ((PortalApplication) applicationContext).getCurrentDevice();
        if (currentDevice == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.utils.DVBDevice");
        }
        this.device = (DVBDevice) currentDevice;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        DVBTechnicolorManager dVBTechnicolorManager = this.tchDVB;
        if (dVBTechnicolorManager != null) {
            dVBTechnicolorManager.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatefun() {
        String string;
        DVB dvb;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String strValueOf;
        String strValueOf2;
        String strValueOf3;
        if (CommonUtils.getCurrentPlatform() == 1 && (dvb = this.oreganDVB) != null) {
            DVBTunerMetrics tunerSignalMetrics = dvb != null ? dvb.getTunerSignalMetrics(0) : null;
            SettingsInfoCellView settingsInfoCellView = getInfoCells().get(0);
            if (tunerSignalMetrics == null || (strValueOf3 = String.valueOf(tunerSignalMetrics.frequencyKHz / 1000)) == null || (string2 = String_ExtensionKt.toMHz(strValueOf3)) == null) {
                string2 = getString(R.string.settings_info_NA);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.settings_info_NA)");
            }
            settingsInfoCellView.setDetail(string2);
            SettingsInfoCellView settingsInfoCellView2 = getInfoCells().get(1);
            if (tunerSignalMetrics != null) {
                string3 = PhysicKt.round(PhysicKt.convertdBmtodBmV$default(tunerSignalMetrics.signalStrengthdBm, 0.0d, 2, null), 2) + " dBmV";
            } else {
                string3 = getString(R.string.settings_info_NA);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.settings_info_NA)");
            }
            settingsInfoCellView2.setDetail(string3);
            SettingsInfoCellView settingsInfoCellView3 = getInfoCells().get(2);
            if (tunerSignalMetrics == null || (strValueOf2 = String.valueOf(tunerSignalMetrics.signalQuality)) == null || (string4 = String_ExtensionKt.toPercent(strValueOf2)) == null) {
                string4 = getString(R.string.settings_info_NA);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.settings_info_NA)");
            }
            settingsInfoCellView3.setDetail(string4);
            SettingsInfoCellView settingsInfoCellView4 = getInfoCells().get(3);
            if (tunerSignalMetrics == null || (strValueOf = String.valueOf(((double) tunerSignalMetrics.SNR) / 10.0d)) == null || (string5 = String_ExtensionKt.toDb(strValueOf)) == null) {
                string5 = getString(R.string.settings_info_NA);
                Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.settings_info_NA)");
            }
            settingsInfoCellView4.setDetail(string5);
            SettingsInfoCellView settingsInfoCellView5 = getInfoCells().get(4);
            if (tunerSignalMetrics == null || (string6 = String.valueOf(tunerSignalMetrics.errorPackets)) == null) {
                string6 = getString(R.string.settings_info_NA);
                Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.settings_info_NA)");
            }
            settingsInfoCellView5.setDetail(string6);
            SettingsInfoCellView settingsInfoCellView6 = getInfoCells().get(5);
            if (tunerSignalMetrics == null || (string7 = toE6Number(tunerSignalMetrics.BER)) == null) {
                string7 = getString(R.string.settings_info_NA);
                Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.settings_info_NA)");
            }
            settingsInfoCellView6.setDetail(string7);
            this.mHandler.postDelayed(this.updateMetrics, 250L);
        }
        DVBDevice dVBDevice = this.device;
        if (dVBDevice == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Device.TYPE);
        }
        if (dVBDevice instanceof TechnicolorDevice) {
            DVBDevice dVBDevice2 = this.device;
            if (dVBDevice2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Device.TYPE);
            }
            dVBDevice2.refreshMetrics();
            DVBDevice dVBDevice3 = this.device;
            if (dVBDevice3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Device.TYPE);
            }
            TunerMetrics tunerMetrics = dVBDevice3.getTunerMetrics();
            getInfoCells().get(0).setDetail(tunerMetrics.getFrequency().getFormattedValue(PowerOfTen.MEGA));
            SettingsInfoCellView settingsInfoCellView7 = getInfoCells().get(1);
            if (tunerMetrics.getSignalStrength() instanceof SignalInfoHelper.RSSIMeasure) {
                StringBuilder sb = new StringBuilder();
                Measure signalStrength = tunerMetrics.getSignalStrength();
                if (signalStrength == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.utils.SignalInfoHelper.RSSIMeasure");
                }
                sb.append(((SignalInfoHelper.RSSIMeasure) signalStrength).formatTodBmV(2));
                sb.append(" dBmV");
                string = sb.toString();
            } else {
                string = getString(R.string.settings_info_NA);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.settings_info_NA)");
            }
            settingsInfoCellView7.setDetail(string);
            getInfoCells().get(2).setDetail(tunerMetrics.getQuality().toString());
            getInfoCells().get(3).setDetail(Measure.getFormattedValue$default(tunerMetrics.getSnr(), null, 1, null));
            getInfoCells().get(4).setDetail(tunerMetrics.getPaquetError().toString());
            SettingsInfoCellView settingsInfoCellView8 = getInfoCells().get(5);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%.2g", Arrays.copyOf(new Object[]{Double.valueOf(tunerMetrics.getBer().getMeasureValue())}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
            settingsInfoCellView8.setDetail(str);
            this.mHandler.postDelayed(this.updateMetrics, 250L);
        }
    }

    @Override // com.dotscreen.portal.ui.screens.setup.SettingsInfoActivity
    public void populateTab() {
        TextView title = getTitle();
        if (title != null) {
            title.setText(getString(R.string.settings_info_signal_title));
        }
        SettingsInfoCellView settingsInfoCellView = getInfoCells().get(0);
        String string = getString(R.string.settings_info_signal_frequency);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.settings_info_signal_frequency)");
        settingsInfoCellView.setTitle(string);
        SettingsInfoCellView settingsInfoCellView2 = getInfoCells().get(1);
        String string2 = getString(R.string.settings_info_signal_strength);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.settings_info_signal_strength)");
        settingsInfoCellView2.setTitle(string2);
        SettingsInfoCellView settingsInfoCellView3 = getInfoCells().get(2);
        String string3 = getString(R.string.settings_info_signal_quality);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.settings_info_signal_quality)");
        settingsInfoCellView3.setTitle(string3);
        SettingsInfoCellView settingsInfoCellView4 = getInfoCells().get(3);
        String string4 = getString(R.string.settings_info_signal_ratio);
        Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.settings_info_signal_ratio)");
        settingsInfoCellView4.setTitle(string4);
        SettingsInfoCellView settingsInfoCellView5 = getInfoCells().get(4);
        String string5 = getString(R.string.settings_info_signal_error_packet);
        Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.setti…info_signal_error_packet)");
        settingsInfoCellView5.setTitle(string5);
        SettingsInfoCellView settingsInfoCellView6 = getInfoCells().get(5);
        String string6 = getString(R.string.settings_info_signal_ber);
        Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.settings_info_signal_ber)");
        settingsInfoCellView6.setTitle(string6);
        if (CommonUtils.getCurrentPlatform() == 2) {
            try {
                DVBTechnicolorManager dVBTechnicolorManager = new DVBTechnicolorManager(this);
                this.tchDVB = dVBTechnicolorManager;
                if (dVBTechnicolorManager != null) {
                    TvView tvView = this.mTvView;
                    if (tvView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mTvView");
                    }
                    dVBTechnicolorManager.getAndTuneLastChannel(tvView);
                }
                updatefun();
            } catch (Exception unused) {
                fillInfoCells(getInfoCells());
            }
        }
        if (CommonUtils.getCurrentPlatform() == 1) {
            getInfoCells().get(4).setTitle(getString(R.string.settings_info_signal_error_packet) + ' ' + getString(R.string.settings_info_signal_error_packet_culm));
            try {
                this.oreganDVB = DVB.getInstance(this);
                updatefun();
            } catch (Exception unused2) {
                fillInfoCells(getInfoCells());
            }
        }
    }

    public final void fillInfoCells(ArrayList<SettingsInfoCellView> infos) {
        Intrinsics.checkParameterIsNotNull(infos, "infos");
        for (int i = 0; i <= 5; i++) {
            SettingsInfoCellView settingsInfoCellView = infos.get(i);
            String string = getString(R.string.settings_info_NA);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.settings_info_NA)");
            settingsInfoCellView.setDetail(string);
        }
    }

    public final String toE6Number(int value) {
        if (value == 0) {
            return String.valueOf(value);
        }
        BigDecimal bigDecimal = new BigDecimal(value + "E-6");
        String str = value < 10 ? "%.1g" : "%.2g";
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format(str, Arrays.copyOf(new Object[]{bigDecimal}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
        return str2;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mHandler.removeCallbacks(this.updateMetrics);
        TvView tvView = this.mTvView;
        if (tvView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTvView");
        }
        tvView.reset();
    }
}
