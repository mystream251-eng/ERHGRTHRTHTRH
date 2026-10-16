package com.viaccessorca.drm.impl;

import android.net.Uri;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class JniHttpDataReaderJni extends JniIDxDataReader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f23a;
    private JniCDxJavaHttpAgent b;
    private c c;
    private int d;
    private Map<String, List<String>> e;
    private String f;
    private String g;
    private String h;
    private int i;

    public JniHttpDataReaderJni(Uri uri, int i) {
        this.f23a = uri.toString();
        this.d = 0;
        this.c = null;
        this.e = new HashMap();
        this.i = i;
    }

    public JniHttpDataReaderJni(c cVar, Map<String, List<String>> map, int i) {
        this.c = cVar;
        this.d = 0;
        this.e = new HashMap(map);
        this.i = i;
    }

    @Override // com.viaccessorca.drm.impl.JniIDxDataReader
    public void close() {
        JniCDxJavaHttpAgent jniCDxJavaHttpAgent = this.b;
        if (jniCDxJavaHttpAgent != null) {
            jniCDxJavaHttpAgent.closeResponse();
        }
    }

    protected void finalize() {
        close();
    }

    @Override // com.viaccessorca.drm.impl.JniIDxDataReader
    public InputStream getDataStream() {
        return this.b.getResponseBody();
    }

    @Override // com.viaccessorca.drm.impl.JniIDxDataReader
    public Map<String, List<String>> getHeaders() {
        return this.b.getResponseHeaders();
    }

    @Override // com.viaccessorca.drm.impl.JniIDxDataReader
    public String getHost() {
        return this.b.getHost();
    }

    @Override // com.viaccessorca.drm.impl.JniIDxDataReader
    public int getHttpResult() {
        return this.d;
    }

    @Override // com.viaccessorca.drm.impl.JniIDxDataReader
    public String getMimeType() {
        if (this.b == null) {
            return null;
        }
        return getHeaderByName("Content-Type");
    }

    @Override // com.viaccessorca.drm.impl.JniIDxDataReader
    public String getRealm() {
        return this.b.getRealm();
    }

    @Override // com.viaccessorca.drm.impl.JniIDxDataReader
    public void init(Map<String, List<String>> map) throws VOException {
        String str;
        JniCDxJavaHttpAgent jniCDxJavaHttpAgent = new JniCDxJavaHttpAgent(this.i);
        this.b = jniCDxJavaHttpAgent;
        String str2 = this.g;
        if (str2 != null && (str = this.f) != null) {
            jniCDxJavaHttpAgent.setHttpCredentials(this.h, str, str2);
        }
        if (map != null) {
            this.e.putAll(map);
        }
        String str3 = this.f23a;
        if (str3 != null) {
            this.b.getUrl(str3, this.e);
        } else {
            c cVar = this.c;
            if (cVar == null) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            this.b.sendRequest(cVar, this.e);
        }
        this.d = this.b.getHttpResult();
    }

    public void setHttpCredentials(String str, String str2, String str3) {
        this.f = str2;
        this.g = str3;
        this.h = str;
    }
}
