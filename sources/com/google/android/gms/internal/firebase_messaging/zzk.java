package com.google.android.gms.internal.firebase_messaging;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzk {
    private static final Logger zza = Logger.getLogger(zzk.class.getName());

    private zzk() {
    }

    public static void zza(@NullableDecl InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                try {
                    zza.logp(Level.WARNING, "com.google.common.io.Closeables", ToolboxAnalyticsModuleKt.EVENT_CLOSE, "IOException thrown while closing Closeable.", (Throwable) e);
                } catch (IOException e2) {
                    throw new AssertionError(e2);
                }
            }
        }
    }
}
