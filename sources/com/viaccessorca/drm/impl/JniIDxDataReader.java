package com.viaccessorca.drm.impl;

import com.viaccessorca.exceptions.VOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
abstract class JniIDxDataReader {
    JniIDxDataReader() {
    }

    public static String concatenateHeaders(List<String> list) {
        String str = new String("");
        Iterator<String> it = list.iterator();
        if (it.hasNext()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(it.next());
            str = sb.toString();
        }
        while (it.hasNext()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(";");
            sb2.append(it.next());
            str = sb2.toString();
        }
        return str;
    }

    public abstract void close();

    public abstract InputStream getDataStream();

    public String getHeaderByName(String str) {
        if (str == null) {
            throw new IllegalStateException();
        }
        List<String> list = getHeaders().get(str);
        if (list != null) {
            return concatenateHeaders(list);
        }
        return null;
    }

    public abstract Map<String, List<String>> getHeaders();

    public String getHost() {
        return null;
    }

    public int getHttpResult() {
        return 200;
    }

    public abstract String getMimeType();

    public String getRealm() {
        return null;
    }

    public abstract void init(Map<String, List<String>> map) throws VOException;
}
