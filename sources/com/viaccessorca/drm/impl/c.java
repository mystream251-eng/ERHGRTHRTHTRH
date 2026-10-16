package com.viaccessorca.drm.impl;

import com.viaccessorca.common.VOUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private JniEDxHttpMethod f37a;
    private String b;
    private Map<String, List<String>> c;
    private byte[] d;

    public c(byte[][] bArr) {
        this.f37a = new String(bArr[0]).equalsIgnoreCase(VOUtils.HTTP_GET) ? JniEDxHttpMethod.DX_HTTP_METHOD_GET : JniEDxHttpMethod.DX_HTTP_METHOD_POST;
        this.b = new String(bArr[1]);
        this.c = new HashMap();
        for (int i = 2; i < bArr.length - 1; i++) {
            String str = new String(bArr[i]);
            this.c.put(str.substring(0, str.indexOf(": ")), Arrays.asList(str.substring(str.indexOf(": ") + 2)));
        }
        this.d = bArr[bArr.length - 1];
    }

    public long a() {
        return this.d.length;
    }

    public void a(int i, StringBuffer stringBuffer, StringBuffer stringBuffer2) {
        Integer num = 0;
        for (Map.Entry<String, List<String>> entry : this.c.entrySet()) {
            Integer numValueOf = Integer.valueOf(num.intValue() + 1);
            if (num.intValue() == i) {
                stringBuffer.append(entry.getKey());
                stringBuffer2.append(entry.getValue().get(0));
                return;
            }
            num = numValueOf;
        }
    }

    public boolean a(byte[] bArr) {
        int i = 0;
        if (bArr.length < this.d.length) {
            return false;
        }
        while (true) {
            byte[] bArr2 = this.d;
            if (i >= bArr2.length) {
                return true;
            }
            bArr[i] = bArr2[i];
            i++;
        }
    }

    public Map<String, List<String>> b() {
        HashMap map = new HashMap();
        map.putAll(this.c);
        return map;
    }

    public JniEDxHttpMethod c() {
        return this.f37a;
    }

    public String d() {
        return this.b;
    }

    public int e() {
        return this.c.size();
    }
}
