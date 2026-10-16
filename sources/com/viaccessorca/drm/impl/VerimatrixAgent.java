package com.viaccessorca.drm.impl;

import android.content.Context;
import com.viaccessorca.drm.VOVerimatrixAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import com.viaccessorca.voplayer.VOPlayer;

/* JADX INFO: loaded from: classes2.dex */
public class VerimatrixAgent implements VOVerimatrixAgent {
    private static VerimatrixAgent c;
    private static String d;
    private static String e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f34a = null;
    private String b = null;

    private VerimatrixAgent(Context context) {
    }

    private VerimatrixAgent(VOPlayer vOPlayer) {
    }

    private final native String EOTA0NDhGNEU5MzczMzkQ0E2NEZCQkVFMkMxNDNDMzh();

    private native void EOTA0NDhGNEU5MzczMzkQ0E2NEZCQkVFMkMxNDNDMzi(boolean z);

    private native void JarSoAlignedMDRDNkRFMjMxMzE1NEQ4RUJDMEI1MEU5NzVFNjlBODk090210(String str, String str2, String str3, String str4);

    private native void Q0E2NEZCQkVFMkMxNDNDMzhEOTA0NDhGNEU5MzczMzk();

    public static synchronized VerimatrixAgent a(Context context) {
        if (c == null) {
            c = new VerimatrixAgent(context);
        }
        return c;
    }

    public static synchronized VerimatrixAgent a(VOPlayer vOPlayer, String str, String str2) {
        if (c == null) {
            c = new VerimatrixAgent(vOPlayer);
        }
        d = str;
        e = str2;
        return c;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.f34a;
    }

    @Override // com.viaccessorca.drm.VOVerimatrixAgent
    public synchronized void createClient() throws VOException {
        String str;
        String str2;
        String str3;
        String str4 = this.f34a;
        if (str4 == null || (str = this.b) == null || (str2 = d) == null || (str3 = e) == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
        }
        JarSoAlignedMDRDNkRFMjMxMzE1NEQ4RUJDMEI1MEU5NzVFNjlBODk090210(str4, str, str2, str3);
    }

    @Override // com.viaccessorca.drm.VOVerimatrixAgent
    public String getVersion() throws VOException {
        return EOTA0NDhGNEU5MzczMzkQ0E2NEZCQkVFMkMxNDNDMzh();
    }

    @Override // com.viaccessorca.drm.VOVerimatrixAgent
    public synchronized void releaseClient() {
        Q0E2NEZCQkVFMkMxNDNDMzhEOTA0NDhGNEU5MzczMzk();
    }

    @Override // com.viaccessorca.drm.VOVerimatrixAgent
    public void setBootAddress(String str) {
        this.b = str;
    }

    @Override // com.viaccessorca.drm.VOVerimatrixAgent
    public void setCompanyName(String str) {
        this.f34a = str;
    }

    @Override // com.viaccessorca.drm.VOVerimatrixAgent
    public void setOfflineMode(boolean z) {
        EOTA0NDhGNEU5MzczMzkQ0E2NEZCQkVFMkMxNDNDMzi(z);
    }
}
