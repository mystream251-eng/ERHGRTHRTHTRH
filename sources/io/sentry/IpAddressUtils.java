package io.sentry;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class IpAddressUtils {
    static final String DEFAULT_IP_ADDRESS = "{{auto}}";
    private static final List<String> DEFAULT_IP_ADDRESS_VALID_VALUES = Arrays.asList(DEFAULT_IP_ADDRESS, "{{ auto }}");

    private IpAddressUtils() {
    }

    public static boolean isDefault(String str) {
        return str != null && DEFAULT_IP_ADDRESS_VALID_VALUES.contains(str);
    }
}
