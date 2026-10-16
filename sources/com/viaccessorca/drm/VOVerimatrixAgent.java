package com.viaccessorca.drm;

import com.viaccessorca.exceptions.VOException;

/* JADX INFO: loaded from: classes2.dex */
public interface VOVerimatrixAgent {
    void createClient() throws VOException;

    String getVersion() throws VOException;

    void releaseClient();

    void setBootAddress(String str);

    void setCompanyName(String str);

    void setOfflineMode(boolean z);
}
