package io.sentry;

import java.net.URI;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class TracingOrigins {
    public static boolean contain(List<String> list, String str) {
        if (list.isEmpty()) {
            return true;
        }
        for (String str2 : list) {
            if (str.contains(str2) || str.matches(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contain(List<String> list, URI uri) {
        return contain(list, uri.toString());
    }
}
