package com.dotscreen.portal.ui.screens.setup;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.LinkProperties;
import android.net.RouteInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.network.NetworkType;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.ui.views.SettingsInfoCellView;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsInfoActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007`\b2\u0006\u0010\t\u001a\u00020\u0004H\u0002J$\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007`\bH\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoIPActivity;", "Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoActivity;", "()V", "getIPdetails", "", "co", "Ljava/util/HashMap;", "Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoIPActivity$ConnectionType;", "Lkotlin/collections/HashMap;", "type", "getLocalIpAddress", "intToInetAddress", "Ljava/net/InetAddress;", "hostAddress", "", "populateTab", "", "ConnectionType", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SettingsInfoIPActivity extends SettingsInfoActivity {
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
        String string;
        String string2;
        String string3;
        String string4;
        String hostAddress;
        String string5;
        TextView title = getTitle();
        if (title != null) {
            title.setText(getString(R.string.settings_info_ip_title));
        }
        HashMap<String, ConnectionType> localIpAddress = getLocalIpAddress();
        SettingsInfoCellView settingsInfoCellView = getInfoCells().get(0);
        String string6 = getString(R.string.settings_info_ip_eth_ipv4);
        Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.settings_info_ip_eth_ipv4)");
        settingsInfoCellView.setTitle(string6);
        SettingsInfoCellView settingsInfoCellView2 = getInfoCells().get(0);
        ConnectionType connectionType = localIpAddress.get("ipv4");
        if (Intrinsics.areEqual(connectionType != null ? connectionType.getType() : null, "eth0")) {
            string = getIPdetails(localIpAddress, "ipv4");
        } else {
            string = getString(R.string.setting_not_connected);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.setting_not_connected)");
        }
        settingsInfoCellView2.setDetail(string);
        SettingsInfoCellView settingsInfoCellView3 = getInfoCells().get(1);
        String string7 = getString(R.string.settings_info_ip_eth_ipv6);
        Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.settings_info_ip_eth_ipv6)");
        settingsInfoCellView3.setTitle(string7);
        SettingsInfoCellView settingsInfoCellView4 = getInfoCells().get(1);
        ConnectionType connectionType2 = localIpAddress.get("ipv4");
        if (Intrinsics.areEqual(connectionType2 != null ? connectionType2.getType() : null, "eth0")) {
            string2 = getIPdetails(localIpAddress, "ipv6");
        } else {
            string2 = getString(R.string.setting_not_connected);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.setting_not_connected)");
        }
        settingsInfoCellView4.setDetail(string2);
        SettingsInfoCellView settingsInfoCellView5 = getInfoCells().get(2);
        String string8 = getString(R.string.settings_info_ip_wifi_ipv4);
        Intrinsics.checkExpressionValueIsNotNull(string8, "getString(R.string.settings_info_ip_wifi_ipv4)");
        settingsInfoCellView5.setTitle(string8);
        SettingsInfoCellView settingsInfoCellView6 = getInfoCells().get(2);
        ConnectionType connectionType3 = localIpAddress.get("ipv4");
        if (Intrinsics.areEqual(connectionType3 != null ? connectionType3.getType() : null, "wlan0")) {
            string3 = getIPdetails(localIpAddress, "ipv4");
        } else {
            string3 = getString(R.string.setting_not_connected);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.setting_not_connected)");
        }
        settingsInfoCellView6.setDetail(string3);
        SettingsInfoCellView settingsInfoCellView7 = getInfoCells().get(3);
        String string9 = getString(R.string.settings_info_ip_wifi_ipv6);
        Intrinsics.checkExpressionValueIsNotNull(string9, "getString(R.string.settings_info_ip_wifi_ipv6)");
        settingsInfoCellView7.setTitle(string9);
        SettingsInfoCellView settingsInfoCellView8 = getInfoCells().get(3);
        ConnectionType connectionType4 = localIpAddress.get("ipv4");
        if (Intrinsics.areEqual(connectionType4 != null ? connectionType4.getType() : null, "wlan0")) {
            string4 = getIPdetails(localIpAddress, "ipv6");
        } else {
            string4 = getString(R.string.setting_not_connected);
            Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.setting_not_connected)");
        }
        settingsInfoCellView8.setDetail(string4);
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        NetworkStateManager networkStateManager = ((PortalApplication) applicationContext).getNetworkStateManager();
        Object systemService = getSystemService("wifi");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }
        WifiManager wifiManager = (WifiManager) systemService;
        SettingsInfoCellView settingsInfoCellView9 = getInfoCells().get(4);
        String string10 = getString(R.string.settings_info_default_gateway);
        Intrinsics.checkExpressionValueIsNotNull(string10, "getString(R.string.settings_info_default_gateway)");
        settingsInfoCellView9.setTitle(string10);
        SettingsInfoCellView settingsInfoCellView10 = getInfoCells().get(5);
        String string11 = getString(R.string.settings_info_DNS);
        Intrinsics.checkExpressionValueIsNotNull(string11, "getString(R.string.settings_info_DNS)");
        settingsInfoCellView10.setTitle(string11);
        try {
            if (networkStateManager.getActiveNetworkType() == NetworkType.Wifi) {
                DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
                Intrinsics.checkExpressionValueIsNotNull(dhcpInfo, "wifi.dhcpInfo");
                SettingsInfoCellView settingsInfoCellView11 = getInfoCells().get(4);
                InetAddress inetAddressIntToInetAddress = intToInetAddress(dhcpInfo.gateway);
                if (inetAddressIntToInetAddress == null || (string5 = inetAddressIntToInetAddress.getHostAddress()) == null) {
                    string5 = getString(R.string.settings_info_NA);
                    Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.settings_info_NA)");
                }
                settingsInfoCellView11.setDetail(string5);
                getInfoCells().get(5).setDetail(intToInetAddress(dhcpInfo.dns1) + " - " + intToInetAddress(dhcpInfo.dns2));
                return;
            }
            String route = getString(R.string.settings_info_NA);
            LinkProperties currentLinkProperties = networkStateManager.getCurrentLinkProperties();
            List<RouteInfo> routes = currentLinkProperties != null ? currentLinkProperties.getRoutes() : null;
            if (routes == null) {
                Intrinsics.throwNpe();
            }
            Iterator<RouteInfo> it = routes.iterator();
            while (it.hasNext()) {
                RouteInfo next = it.next();
                if (Intrinsics.areEqual(next != null ? next.getInterface() : null, "eth0")) {
                    InetAddress gateway = next.getGateway();
                    Intrinsics.checkExpressionValueIsNotNull(gateway, "rt.gateway");
                    if (!Intrinsics.areEqual(gateway.getHostAddress(), "::")) {
                        InetAddress gateway2 = next.getGateway();
                        Intrinsics.checkExpressionValueIsNotNull(gateway2, "rt.gateway");
                        route = gateway2.getHostAddress();
                    }
                }
            }
            SettingsInfoCellView settingsInfoCellView12 = getInfoCells().get(4);
            Intrinsics.checkExpressionValueIsNotNull(route, "route");
            settingsInfoCellView12.setDetail(route);
            LinkProperties currentLinkProperties2 = networkStateManager.getCurrentLinkProperties();
            List<InetAddress> dnsServers = currentLinkProperties2 != null ? currentLinkProperties2.getDnsServers() : null;
            if (dnsServers == null) {
                Intrinsics.throwNpe();
            }
            int size = dnsServers.size();
            SettingsInfoCellView settingsInfoCellView13 = getInfoCells().get(5);
            if (size == 0) {
                hostAddress = getString(R.string.settings_info_NA);
                Intrinsics.checkExpressionValueIsNotNull(hostAddress, "getString(R.string.settings_info_NA)");
            } else {
                LinkProperties currentLinkProperties3 = networkStateManager.getCurrentLinkProperties();
                List<InetAddress> dnsServers2 = currentLinkProperties3 != null ? currentLinkProperties3.getDnsServers() : null;
                if (dnsServers2 == null) {
                    Intrinsics.throwNpe();
                }
                InetAddress inetAddress = dnsServers2.get(size - 1);
                Intrinsics.checkExpressionValueIsNotNull(inetAddress, "connectivityManager.getC…?.dnsServers!![count - 1]");
                hostAddress = inetAddress.getHostAddress();
                Intrinsics.checkExpressionValueIsNotNull(hostAddress, "connectivityManager.getC…!![count - 1].hostAddress");
            }
            settingsInfoCellView13.setDetail(hostAddress);
        } catch (Exception unused) {
            SettingsInfoCellView settingsInfoCellView14 = getInfoCells().get(4);
            String string12 = getString(R.string.settings_info_connectivity_network_unconnected);
            Intrinsics.checkExpressionValueIsNotNull(string12, "getString(R.string.setti…vity_network_unconnected)");
            settingsInfoCellView14.setDetail(string12);
            SettingsInfoCellView settingsInfoCellView15 = getInfoCells().get(5);
            String string13 = getString(R.string.settings_info_connectivity_network_unconnected);
            Intrinsics.checkExpressionValueIsNotNull(string13, "getString(R.string.setti…vity_network_unconnected)");
            settingsInfoCellView15.setDetail(string13);
        }
    }

    private final String getIPdetails(HashMap<String, ConnectionType> co, String type) {
        if ("ipv4".equals(type)) {
            ConnectionType connectionType = co.get("ipv4");
            if (connectionType == null) {
                Intrinsics.throwNpe();
            }
            return connectionType.getIp();
        }
        if (!"ipv6".equals(type)) {
            return "";
        }
        ConnectionType connectionType2 = co.get("ipv6");
        String ip = connectionType2 != null ? connectionType2.getIp() : null;
        if (ip == null) {
            Intrinsics.throwNpe();
        }
        return StringsKt.substringBefore(ip, "%", "");
    }

    /* JADX INFO: compiled from: SettingsInfoActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoIPActivity$ConnectionType;", "", "type", "", "ip", "(Ljava/lang/String;Ljava/lang/String;)V", "getIp", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class ConnectionType {
        private final String ip;
        private final String type;

        public static /* synthetic */ ConnectionType copy$default(ConnectionType connectionType, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = connectionType.type;
            }
            if ((i & 2) != 0) {
                str2 = connectionType.ip;
            }
            return connectionType.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getIp() {
            return this.ip;
        }

        public final ConnectionType copy(String type, String ip) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(ip, "ip");
            return new ConnectionType(type, ip);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ConnectionType)) {
                return false;
            }
            ConnectionType connectionType = (ConnectionType) other;
            return Intrinsics.areEqual(this.type, connectionType.type) && Intrinsics.areEqual(this.ip, connectionType.ip);
        }

        public int hashCode() {
            String str = this.type;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.ip;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "ConnectionType(type=" + this.type + ", ip=" + this.ip + ")";
        }

        public ConnectionType(String type, String ip) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(ip, "ip");
            this.type = type;
            this.ip = ip;
        }

        public final String getIp() {
            return this.ip;
        }

        public final String getType() {
            return this.type;
        }
    }

    private final InetAddress intToInetAddress(int hostAddress) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) (hostAddress & 255), (byte) ((hostAddress >> 8) & 255), (byte) ((hostAddress >> 16) & 255), (byte) ((hostAddress >> 24) & 255)});
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    private final HashMap<String, ConnectionType> getLocalIpAddress() {
        HashMap<String, ConnectionType> map = new HashMap<>();
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
                    Log.i("SettingsInfoActivity", "inetAddress: " + inetAddress);
                    if (!inetAddress.isLoopbackAddress()) {
                        if ((inetAddress instanceof Inet4Address) && !map.containsKey("ipv4")) {
                            String name = networkInterface.getName();
                            Intrinsics.checkExpressionValueIsNotNull(name, "intf.name");
                            String hostAddress = inetAddress.getHostAddress();
                            Intrinsics.checkExpressionValueIsNotNull(hostAddress, "inetAddress.getHostAddress()");
                            map.put("ipv4", new ConnectionType(name, hostAddress));
                            if (map.containsKey("ipv6")) {
                                return map;
                            }
                        } else if ((inetAddress instanceof Inet6Address) && !map.containsKey("ipv6")) {
                            String name2 = networkInterface.getName();
                            Intrinsics.checkExpressionValueIsNotNull(name2, "intf.name");
                            String hostAddress2 = inetAddress.getHostAddress();
                            Intrinsics.checkExpressionValueIsNotNull(hostAddress2, "inetAddress.getHostAddress()");
                            map.put("ipv6", new ConnectionType(name2, hostAddress2));
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
