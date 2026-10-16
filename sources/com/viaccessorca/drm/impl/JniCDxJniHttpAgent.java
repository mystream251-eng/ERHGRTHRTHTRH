package com.viaccessorca.drm.impl;

import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class JniCDxJniHttpAgent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private JniCDxHttpResponse f18a;
    private byte[] b = null;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f19a;

        static {
            int[] iArr = new int[VOStatusCode.values().length];
            f19a = iArr;
            try {
                iArr[VOStatusCode.ERROR_BAD_ARGUMENTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f19a[VOStatusCode.ERROR_NETWORK_COMMUNICATION_FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f19a[VOStatusCode.ERROR_NETWORK_NO_INTERNET_CONNECTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f19a[VOStatusCode.ERROR_NETWORK_SERVER_NOT_REACHABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public JniCDxJniHttpAgent() {
        this.f18a = null;
        this.f18a = null;
    }

    public String m2yugdva7tpoe4A10(String str) {
        JniCDxHttpResponse jniCDxHttpResponse = this.f18a;
        if (jniCDxHttpResponse == null) {
            return null;
        }
        int numOfHttpHeaders = jniCDxHttpResponse.getNumOfHttpHeaders();
        for (int i = 0; i < numOfHttpHeaders; i++) {
            Map.Entry<String, List<String>> headerAtIndex = this.f18a.getHeaderAtIndex(i);
            String key = headerAtIndex.getKey();
            if (key != null && key.equals(str)) {
                return headerAtIndex.getValue().get(0);
            }
        }
        return null;
    }

    public int m2yugdva7tpoe4A11() {
        JniCDxHttpResponse jniCDxHttpResponse = this.f18a;
        if (jniCDxHttpResponse == null) {
            return 0;
        }
        return jniCDxHttpResponse.getNumOfHttpHeaders();
    }

    public String m2yugdva7tpoe4A12(int i) {
        JniCDxHttpResponse jniCDxHttpResponse = this.f18a;
        if (jniCDxHttpResponse != null && i < jniCDxHttpResponse.getNumOfHttpHeaders()) {
            return this.f18a.getHeaderAtIndex(i).getValue().get(0);
        }
        return null;
    }

    public byte[] m2yugdva7tpoe4A13() {
        return this.b;
    }

    public String m2yugdva7tpoe4A22(int i) {
        JniCDxHttpResponse jniCDxHttpResponse = this.f18a;
        if (jniCDxHttpResponse != null && i < jniCDxHttpResponse.getNumOfHttpHeaders()) {
            return this.f18a.getHeaderAtIndex(i).getKey();
        }
        return null;
    }

    public JniECspDrmStatus m2yugdva7tpoe4A8(byte[][] bArr) throws VOException {
        c cVar = new c(bArr);
        JniHttpDataReaderJni jniHttpDataReaderJni = new JniHttpDataReaderJni(cVar, cVar.b(), 60000);
        try {
            jniHttpDataReaderJni.init(cVar.b());
            JniCDxHttpResponse jniCDxHttpResponse = new JniCDxHttpResponse();
            this.f18a = jniCDxHttpResponse;
            try {
                jniCDxHttpResponse.initwithHttpReader(jniHttpDataReaderJni);
                int iGetHttpBodySize = this.f18a.GetHttpBodySize();
                if (iGetHttpBodySize > 0) {
                    byte[] bArr2 = new byte[iGetHttpBodySize];
                    this.b = bArr2;
                    this.f18a.ReadHttpBody(bArr2);
                }
                jniHttpDataReaderJni.close();
                return JniECspDrmStatus.CSP_DRM_SUCCESS;
            } catch (VOException unused) {
                return JniECspDrmStatus.CSP_ERROR_COMMUNICATION_FAILURE;
            }
        } catch (VOException e) {
            int i = a.f19a[e.getStatus().ordinal()];
            if (i == 1) {
                return JniECspDrmStatus.CSP_ERROR_BAD_ARGUMENTS;
            }
            if (i == 2) {
                return JniECspDrmStatus.CSP_ERROR_COMMUNICATION_FAILURE;
            }
            if (i != 3) {
                return i != 4 ? JniECspDrmStatus.CSP_ERROR_GENERAL_FAILURE : JniECspDrmStatus.CSP_ERROR_CANNOT_CONNECT_TO_SERVER;
            }
            return JniECspDrmStatus.CSP_ERROR_NO_INTERNET_CONNECTION;
        }
    }

    public int m2yugdva7tpoe4A9() {
        JniCDxHttpResponse jniCDxHttpResponse = this.f18a;
        if (jniCDxHttpResponse == null) {
            return 0;
        }
        return jniCDxHttpResponse.getHttpResult();
    }
}
