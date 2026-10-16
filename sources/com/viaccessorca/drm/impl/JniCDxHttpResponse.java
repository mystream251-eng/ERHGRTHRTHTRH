package com.viaccessorca.drm.impl;

import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class JniCDxHttpResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f16a = -1;
    private Map<String, List<String>> b = new HashMap();
    private ByteArrayOutputStream c;

    public int GetHttpBodySize() {
        ByteArrayOutputStream byteArrayOutputStream = this.c;
        if (byteArrayOutputStream == null) {
            return 0;
        }
        return byteArrayOutputStream.size();
    }

    public void ReadHttpBody(byte[] bArr) {
        byte[] byteArray = this.c.toByteArray();
        if (byteArray != null) {
            System.arraycopy(byteArray, 0, bArr, 0, byteArray.length);
        }
    }

    public void addHeader(String str, String str2) {
        this.b.put(str, Arrays.asList(str2));
    }

    public Map.Entry<String, List<String>> getHeaderAtIndex(int i) {
        int i2 = 0;
        for (Map.Entry<String, List<String>> entry : this.b.entrySet()) {
            if (i2 == i) {
                return entry;
            }
            i2++;
        }
        return null;
    }

    public String[] getHeadersAsArray() {
        String[] strArr = new String[getNumOfHttpHeaders()];
        Integer numValueOf = 0;
        for (Map.Entry<String, List<String>> entry : this.b.entrySet()) {
            strArr[numValueOf.intValue()] = entry.getKey();
            strArr[numValueOf.intValue()].concat(": ");
            strArr[numValueOf.intValue()].concat(entry.getValue().get(0));
            numValueOf = Integer.valueOf(numValueOf.intValue() + 1);
        }
        return strArr;
    }

    public int getHttpResult() {
        return this.f16a;
    }

    public int getNumOfHttpHeaders() {
        return this.b.size();
    }

    public boolean initwithHttpReader(JniHttpDataReaderJni jniHttpDataReaderJni) throws VOException {
        if (jniHttpDataReaderJni == null) {
            return false;
        }
        setResult(jniHttpDataReaderJni.getHttpResult());
        for (Map.Entry<String, List<String>> entry : jniHttpDataReaderJni.getHeaders().entrySet()) {
            addHeader(entry.getKey(), entry.getValue().get(0));
        }
        InputStream dataStream = jniHttpDataReaderJni.getDataStream();
        if (dataStream == null) {
            return true;
        }
        this.c = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int i = dataStream.read(bArr);
                if (i < 0) {
                    return true;
                }
                this.c.write(bArr, 0, i);
            } catch (IOException unused) {
                throw new VOException(VOStatusCode.ERROR_NETWORK_COMMUNICATION_FAILURE);
            }
        }
    }

    public void setResult(int i) {
        this.f16a = i;
    }
}
