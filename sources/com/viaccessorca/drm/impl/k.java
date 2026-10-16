package com.viaccessorca.drm.impl;

import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import com.viaccessorca.common.VOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
final class k implements Closeable {
    private static final UUID b = new UUID(-1301668207276963122L, -6645017420763422227L);
    private static k c = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MediaDrm f41a = null;

    private k() {
        MediaDrm mediaDrm = null;
        if (VOUtils.SDK_INT < 18) {
            return;
        }
        try {
            if (mediaDrm.getPropertyString("securityLevel").equals("L3") && true == b()) {
                this.f41a.closeSession(this.f41a.openSession());
            }
        } catch (NotProvisionedException | Exception unused) {
        }
    }

    private VOUtils.RequestResponse a(String str, int i, int i2) {
        try {
            return VOUtils.sendHTTPRequestWithException(str, VOUtils.HTTP_POST, i, i2, null, null, false);
        } catch (Exception unused) {
            return null;
        }
    }

    public static synchronized k a() {
        if (c == null) {
            c = new k();
        }
        return c;
    }

    private void a(VOUtils.RequestResponse requestResponse) {
        MediaDrm mediaDrm = this.f41a;
        if (mediaDrm != null) {
            try {
                mediaDrm.provideProvisionResponse(requestResponse.data);
            } catch (DeniedByServerException | Exception unused) {
            }
        }
    }

    private boolean b() {
        if (this.f41a != null) {
            return true;
        }
        try {
            this.f41a = new MediaDrm(b);
        } catch (UnsupportedSchemeException unused) {
        }
        return this.f41a != null;
    }

    public void a(int i, int i2, int i3) {
        if (this.f41a != null) {
            boolean z = false;
            while (i > 0 && !z) {
                try {
                    MediaDrm.ProvisionRequest provisionRequest = this.f41a.getProvisionRequest();
                    VOUtils.RequestResponse requestResponseA = a(provisionRequest.getDefaultUrl() + "&signedRequest=" + new String(provisionRequest.getData(), Charset.defaultCharset()), i2, i3);
                    if (requestResponseA != null) {
                        z = true;
                        a(requestResponseA);
                    } else if (requestResponseA != null) {
                        byte[] bArr = requestResponseA.data;
                    }
                } catch (IllegalStateException | Exception unused) {
                }
                i--;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        MediaDrm mediaDrm = this.f41a;
        if (mediaDrm != null) {
            try {
                mediaDrm.release();
                this.f41a = null;
            } catch (Exception unused) {
                this.f41a = null;
            }
        }
    }
}
