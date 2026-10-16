package com.dotscreen.farkit.network;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NetworkTools.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkTools;", "", "()V", "getMACAddress", "", "interfaceName", "separator", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class NetworkTools {
    public static final NetworkTools INSTANCE = new NetworkTools();

    public final String getMACAddress(String str) {
        return getMACAddress$default(this, str, null, 2, null);
    }

    private NetworkTools() {
    }

    public static /* synthetic */ String getMACAddress$default(NetworkTools networkTools, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return networkTools.getMACAddress(str, str2);
    }

    public final String getMACAddress(String interfaceName, String separator) {
        Object next;
        byte[] hardwareAddress;
        List<Byte> list;
        NetworkInterface it;
        Intrinsics.checkParameterIsNotNull(separator, "separator");
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Intrinsics.checkExpressionValueIsNotNull(networkInterfaces, "NetworkInterface.getNetworkInterfaces()");
            ArrayList list2 = Collections.list(networkInterfaces);
            Intrinsics.checkExpressionValueIsNotNull(list2, "java.util.Collections.list(this)");
            Iterator it2 = list2.iterator();
            do {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
                it = (NetworkInterface) next;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
            } while (!StringsKt.equals(it.getName(), interfaceName, true));
            NetworkInterface networkInterface = (NetworkInterface) next;
            if (networkInterface == null || (hardwareAddress = networkInterface.getHardwareAddress()) == null || (list = ArraysKt.toList(hardwareAddress)) == null) {
                return null;
            }
            return CollectionsKt.joinToString$default(list, separator, null, null, 0, null, new Function1<Byte, String>() { // from class: com.dotscreen.farkit.network.NetworkTools.getMACAddress.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ String invoke(Byte b) {
                    return invoke(b.byteValue());
                }

                public final String invoke(byte b) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                    Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(format, *args)");
                    return str;
                }
            }, 30, null);
        } catch (Exception unused) {
            return null;
        }
    }
}
