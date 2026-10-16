package com.dotscreen.portal.ui.views;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.ui.screens.setup.SettingsInfoIPActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DebugInfos.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0014\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/dotscreen/portal/ui/views/DebugInfos;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "TAG", "", "app_versionTextview", "Landroid/widget/TextView;", "compagnyLogo", "Landroid/widget/ImageView;", "hardwareModelTextview", "ip_addressTextview", "networkStateImg", "networkStateManager", "Lcom/dotscreen/farkit/network/NetworkStateManager;", "networkStateTextview", "snTextview", "getLocalIpAddress", "Ljava/util/HashMap;", "Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoIPActivity$ConnectionType;", "onAttachedToWindow", "", "updateNetworkStatus", "networkStatus", "Lcom/dotscreen/farkit/network/NetworkStatus;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DebugInfos extends LinearLayout {
    private String TAG;
    private HashMap _$_findViewCache;
    private final TextView app_versionTextview;
    private final ImageView compagnyLogo;
    private final TextView hardwareModelTextview;
    private final TextView ip_addressTextview;
    private final ImageView networkStateImg;
    private NetworkStateManager networkStateManager;
    private final TextView networkStateTextview;
    private final TextView snTextview;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DebugInfos(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "Debug_Main_View";
        Log.d("Debug_Main_View", "Init debug view");
        Log.d(this.TAG, "Debug view should be false");
        View viewInflate = View.inflate(getContext(), R.layout.debug_view, this);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        View viewFindViewById = findViewById(R.id.debug_ip_adress);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.debug_ip_adress)");
        this.ip_addressTextview = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.debug_portal_version);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.debug_portal_version)");
        this.app_versionTextview = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.debug_network_state);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.debug_network_state)");
        this.networkStateTextview = (TextView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.debug_model);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById(R.id.debug_model)");
        this.hardwareModelTextview = (TextView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.debug_network_state_img);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "findViewById(R.id.debug_network_state_img)");
        this.networkStateImg = (ImageView) viewFindViewById5;
        View viewFindViewById6 = findViewById(R.id.debug_real_sn);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "findViewById(R.id.debug_real_sn)");
        this.snTextview = (TextView) viewFindViewById6;
        View viewFindViewById7 = findViewById(R.id.debug_company_logo);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "findViewById(R.id.debug_company_logo)");
        this.compagnyLogo = (ImageView) viewFindViewById7;
    }

    public /* synthetic */ DebugInfos(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DebugInfos(Context context) {
        this(context, null, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DebugInfos(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DebugInfos(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() throws PackageManager.NameNotFoundException {
        super.onAttachedToWindow();
        this.compagnyLogo.setBackground(getContext().getDrawable(R.drawable.dotscreen));
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "getContext()");
        PackageManager packageManager = context.getPackageManager();
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "getContext()");
        PackageInfo packageInfo = packageManager.getPackageInfo(context2.getPackageName(), 0);
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "getContext().packageMana…Context().packageName, 0)");
        this.app_versionTextview.setText("Portal Version: " + packageInfo.versionName + " (" + packageInfo.getLongVersionCode() + ')');
        Context context3 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "getContext()");
        NetworkStateManager networkStateManager = new NetworkStateManager(context3, false, 2, null);
        this.networkStateManager = networkStateManager;
        if (networkStateManager == null) {
            Intrinsics.throwNpe();
        }
        networkStateManager.getDeviceStatus().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NetworkStatus>() { // from class: com.dotscreen.portal.ui.views.DebugInfos$onAttachedToWindow$networkStateDisposable$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(NetworkStatus status) {
                Intrinsics.checkParameterIsNotNull(status, "status");
                this.this$0.updateNetworkStatus(status);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.views.DebugInfos$onAttachedToWindow$networkStateDisposable$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                Log.w(this.this$0.TAG, "Network state error", error);
            }
        }, new Action() { // from class: com.dotscreen.portal.ui.views.DebugInfos$onAttachedToWindow$networkStateDisposable$3
            @Override // io.reactivex.functions.Action
            public final void run() {
                Log.w(this.this$0.TAG, "Network state finished");
            }
        });
        this.hardwareModelTextview.setText("STB: " + Build.MANUFACTURER + " - " + SystemProperties.get("ro.build.version.incremental", "N/A"));
        try {
            this.snTextview.setText("SN: " + Build.getSerial());
        } catch (Throwable th) {
            this.snTextview.setText("SN: " + th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNetworkStatus(NetworkStatus networkStatus) {
        int i;
        Log.d(this.TAG, "Network status is " + networkStatus);
        HashMap<String, SettingsInfoIPActivity.ConnectionType> localIpAddress = getLocalIpAddress();
        SettingsInfoIPActivity.ConnectionType connectionType = localIpAddress.get("ipv4");
        if (networkStatus instanceof NetworkStatus.Connected) {
            i = R.drawable.green_round;
        } else {
            i = ((networkStatus instanceof NetworkStatus.ConnectedNoInternet) || (networkStatus instanceof NetworkStatus.ConnectedNoBackendAccess)) ? R.drawable.yellow_round : R.drawable.red_round;
        }
        this.networkStateTextview.setText("N:");
        if ((connectionType != null ? connectionType.getIp() : null) != null) {
            TextView textView = this.ip_addressTextview;
            StringBuilder sb = new StringBuilder();
            sb.append("Ip: ");
            SettingsInfoIPActivity.ConnectionType connectionType2 = localIpAddress.get("ipv4");
            sb.append(connectionType2 != null ? connectionType2.getIp() : null);
            textView.setText(sb.toString());
        } else {
            this.ip_addressTextview.setText("Error getting ipv4");
        }
        this.networkStateImg.setBackground(getContext().getDrawable(i));
    }

    private final HashMap<String, SettingsInfoIPActivity.ConnectionType> getLocalIpAddress() {
        HashMap<String, SettingsInfoIPActivity.ConnectionType> map = new HashMap<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Intrinsics.checkExpressionValueIsNotNull(networkInterfaces, "NetworkInterface.getNetworkInterfaces()");
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                Intrinsics.checkExpressionValueIsNotNull(networkInterfaceNextElement, "en.nextElement()");
                NetworkInterface networkInterface = networkInterfaceNextElement;
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                Intrinsics.checkExpressionValueIsNotNull(inetAddresses, "intf.inetAddresses");
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    Intrinsics.checkExpressionValueIsNotNull(inetAddressNextElement, "enumIpAddr.nextElement()");
                    InetAddress inetAddress = inetAddressNextElement;
                    Log.i(this.TAG, "inetAddress: " + inetAddress);
                    if (!inetAddress.isLoopbackAddress()) {
                        if ((inetAddress instanceof Inet4Address) && !map.containsKey("ipv4")) {
                            String name = networkInterface.getName();
                            Intrinsics.checkExpressionValueIsNotNull(name, "intf.name");
                            String hostAddress = inetAddress.getHostAddress();
                            Intrinsics.checkExpressionValueIsNotNull(hostAddress, "inetAddress.getHostAddress()");
                            map.put("ipv4", new SettingsInfoIPActivity.ConnectionType(name, hostAddress));
                            if (map.containsKey("ipv6")) {
                                return map;
                            }
                        } else if ((inetAddress instanceof Inet6Address) && !map.containsKey("ipv6")) {
                            String name2 = networkInterface.getName();
                            Intrinsics.checkExpressionValueIsNotNull(name2, "intf.name");
                            String hostAddress2 = inetAddress.getHostAddress();
                            Intrinsics.checkExpressionValueIsNotNull(hostAddress2, "inetAddress.getHostAddress()");
                            map.put("ipv6", new SettingsInfoIPActivity.ConnectionType(name2, hostAddress2));
                            if (map.containsKey("ipv4")) {
                                return map;
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return map;
    }
}
