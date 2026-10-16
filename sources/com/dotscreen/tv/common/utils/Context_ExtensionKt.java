package com.dotscreen.tv.common.utils;

import android.content.Context;
import android.content.Intent;
import com.dotscreen.farkit.tools.DSLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Context+Extension.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¨\u0006\u0003"}, d2 = {"restartNanoCdnSession", "", "Landroid/content/Context;", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class Context_ExtensionKt {
    public static final void restartNanoCdnSession(Context restartNanoCdnSession) {
        Intrinsics.checkParameterIsNotNull(restartNanoCdnSession, "$this$restartNanoCdnSession");
        DSLog.INSTANCE.m("startForegroundService -> tv.broadpeak.NanoCDNWrapper.NanoServiceForegroundProd");
        Intent intent = new Intent();
        intent.setAction("tv.broadpeak.NanoCDNWrapper.NanoServiceForegroundProd");
        intent.setPackage("tv.broadpeak.nanocdnwrapper");
        restartNanoCdnSession.startForegroundService(intent);
    }
}
