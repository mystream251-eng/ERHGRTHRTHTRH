package com.dotscreen.portal.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.media.tv.TvView;
import android.net.Uri;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.providers.LastChannelsProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import java.io.IOException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONObject;

/* JADX INFO: compiled from: DVBTechnicolorManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/utils/DVBTechnicolorManager;", "", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "getActivity", "()Landroid/app/Activity;", "scanStatusReceiver", "Lcom/dotscreen/portal/utils/DVBTechnicolorManager$ScanBroadcastReceiver;", ToolboxAnalyticsModuleKt.EVENT_CLOSE, "", "getAndTuneLastChannel", "tvView", "Landroid/media/tv/TvView;", "getData", "Lcom/dotscreen/portal/utils/DVBTechnicolorManager$DVBtechnicolorMetrics;", "DVBtechnicolorMetrics", "ScanBroadcastReceiver", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DVBTechnicolorManager {
    private final Activity activity;
    private ScanBroadcastReceiver scanStatusReceiver;

    public DVBTechnicolorManager(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.activity = activity;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final DVBtechnicolorMetrics getData() throws IOException {
        Cursor cursorQuery;
        Uri uri = Uri.parse(DVBTechnicolorManagerKt.PROVIDER_URL);
        ContentResolver contentResolver = this.activity.getContentResolver();
        if (contentResolver != null && (cursorQuery = contentResolver.query(uri, null, null, null, null)) != null) {
            Cursor cursor = cursorQuery;
            Throwable th = (Throwable) null;
            try {
                Cursor cursor2 = cursor;
                if (cursor2.moveToFirst()) {
                    DVBtechnicolorMetrics dVBtechnicolorMetrics = new DVBtechnicolorMetrics(new JSONObject(DVBTechnicolorManagerKt.getValue(cursor2, "parameters")), new JSONObject(DVBTechnicolorManagerKt.getValue(cursor2, "stats")));
                    CloseableKt.closeFinally(cursor, th);
                    return dVBtechnicolorMetrics;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, th);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(cursor, th2);
                    throw th3;
                }
            }
        }
        return new DVBtechnicolorMetrics();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DVBTechnicolorManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/utils/DVBTechnicolorManager$ScanBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "tvView", "Landroid/media/tv/TvView;", "(Lcom/dotscreen/portal/utils/DVBTechnicolorManager;Landroid/media/tv/TvView;)V", "getTvView", "()Landroid/media/tv/TvView;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    final class ScanBroadcastReceiver extends BroadcastReceiver {
        final /* synthetic */ DVBTechnicolorManager this$0;
        private final TvView tvView;

        public ScanBroadcastReceiver(DVBTechnicolorManager dVBTechnicolorManager, TvView tvView) {
            Intrinsics.checkParameterIsNotNull(tvView, "tvView");
            this.this$0 = dVBTechnicolorManager;
            this.tvView = tvView;
        }

        public final TvView getTvView() {
            return this.tvView;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws IOException {
            String action;
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            Log.d("DVBTechnicolorManager", "ScanBroadcastReceiver: onReceive(" + intent + ')');
            if (intent.getAction() == null || (action = intent.getAction()) == null) {
                return;
            }
            int iHashCode = action.hashCode();
            if (iHashCode != -1027985502) {
                if (iHashCode == 1970710321 && action.equals(CommonUtils.ACTION_DVB_SCAN_STARTED)) {
                    this.tvView.reset();
                    return;
                }
                return;
            }
            if (action.equals(CommonUtils.ACTION_DVB_SCAN_FINISHED)) {
                this.this$0.getAndTuneLastChannel(this.tvView);
            }
        }
    }

    public final void getAndTuneLastChannel(TvView tvView) throws IOException {
        Intrinsics.checkParameterIsNotNull(tvView, "tvView");
        LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(this.activity).getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(activity).config");
        PortalApplication.Config value = config.getValue();
        if (value == null || value.getUser() == null) {
            return;
        }
        ContentResolver contentResolver = this.activity.getContentResolver();
        Uri content_uri = LastChannelsProvider.INSTANCE.getCONTENT_URI();
        String[] strArr = {LastChannelsProvider.INSTANCE.getCOLUMN_CHANNEL_ID(), LastChannelsProvider.INSTANCE.getCOLUMN_INPUT_ID()};
        String str = LastChannelsProvider.INSTANCE.getCOLUMN_PROFILE_ID() + "=?";
        String[] strArr2 = new String[1];
        RightVUser user = value.getUser();
        if (user != null) {
            strArr2[0] = user.getCompassId();
            Cursor cursorQuery = contentResolver.query(content_uri, strArr, str, strArr2, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                Throwable th = (Throwable) null;
                try {
                    Cursor cursor2 = cursor;
                    if (cursor2.moveToLast()) {
                        long j = cursor2.getLong(0);
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DVBTechnicolorManager$getAndTuneLastChannel$$inlined$let$lambda$1(j, TvContract.buildChannelUri(j), cursor2.getString(1), null, this, tvView), 2, null);
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, th);
                    return;
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(cursor, th2);
                        throw th3;
                    }
                }
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
    }

    public final void close() {
        ScanBroadcastReceiver scanBroadcastReceiver = this.scanStatusReceiver;
        if (scanBroadcastReceiver != null) {
            scanBroadcastReceiver.getTvView().getContext().unregisterReceiver(scanBroadcastReceiver);
        }
    }

    /* JADX INFO: compiled from: DVBTechnicolorManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u0005¢\u0006\u0002\u0010\u0006J0\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001dH\u0002J\u0010\u0010#\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001dH\u0002R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0013\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR\u001a\u0010\u0016\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\n\"\u0004\b\u0018\u0010\fR\u001a\u0010\u0019\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\n\"\u0004\b\u001b\u0010\f¨\u0006$"}, d2 = {"Lcom/dotscreen/portal/utils/DVBTechnicolorManager$DVBtechnicolorMetrics;", "", "parameters", "Lorg/json/JSONObject;", "stats", "(Lorg/json/JSONObject;Lorg/json/JSONObject;)V", "()V", "ber", "", "getBer", "()Ljava/lang/String;", "setBer", "(Ljava/lang/String;)V", "frequency", "getFrequency", "setFrequency", "paquetError", "getPaquetError", "setPaquetError", "quality", "getQuality", "setQuality", "signal", "getSignal", "setSignal", "snr", "getSnr", "setSnr", "getLinearInterpolation", "", "xp", "x0", "x1", "y0", "y1", "getQualityFromSnr", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class DVBtechnicolorMetrics {
        private String ber;
        private String frequency;
        private String paquetError;
        private String quality;
        private String signal;
        private String snr;

        public DVBtechnicolorMetrics() {
            this.frequency = "N/A";
            this.signal = "N/A";
            this.quality = "N/A";
            this.snr = "N/A";
            this.paquetError = "N/A";
            this.ber = "N/A";
        }

        public final String getFrequency() {
            return this.frequency;
        }

        public final void setFrequency(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.frequency = str;
        }

        public final String getSignal() {
            return this.signal;
        }

        public final void setSignal(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.signal = str;
        }

        public final String getQuality() {
            return this.quality;
        }

        public final void setQuality(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.quality = str;
        }

        public final String getSnr() {
            return this.snr;
        }

        public final void setSnr(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.snr = str;
        }

        public final String getPaquetError() {
            return this.paquetError;
        }

        public final void setPaquetError(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.paquetError = str;
        }

        public final String getBer() {
            return this.ber;
        }

        public final void setBer(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.ber = str;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public DVBtechnicolorMetrics(JSONObject parameters, JSONObject stats) {
            this();
            Intrinsics.checkParameterIsNotNull(parameters, "parameters");
            Intrinsics.checkParameterIsNotNull(stats, "stats");
            this.frequency = String.valueOf(parameters.optLong("frequency") / ((long) 1000000));
            this.signal = String.valueOf(stats.optInt("rssi"));
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%.2g", Arrays.copyOf(new Object[]{Double.valueOf(stats.optDouble("ber"))}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
            this.ber = str;
            this.snr = String.valueOf(stats.optInt("snr"));
            this.paquetError = String.valueOf(stats.optInt("lost-packets"));
            this.quality = String.valueOf(getQualityFromSnr(stats.optInt("snr")));
        }

        private final int getQualityFromSnr(int snr) {
            int linearInterpolation;
            int i = snr * 10;
            if (i < 300) {
                linearInterpolation = 0;
            } else {
                linearInterpolation = i >= 390 ? 100 : getLinearInterpolation(i, 300, 390, 10, 100);
            }
            return (linearInterpolation / 10) * 10;
        }

        private final int getLinearInterpolation(int xp, int x0, int x1, int y0, int y1) {
            return y0 + (((y1 - y0) / (x1 - x0)) * (xp - x0));
        }
    }
}
