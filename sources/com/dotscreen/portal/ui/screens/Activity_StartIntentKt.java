package com.dotscreen.portal.ui.screens;

import android.app.Activity;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Activity+StartIntent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¨\u0006\u0005"}, d2 = {"startIntent", "", "Landroid/app/Activity;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class Activity_StartIntentKt {
    /* JADX WARN: Code duplicated, block: B:9:0x0025 A[Catch: all -> 0x0029, TRY_LEAVE, TryCatch #0 {all -> 0x0029, blocks: (B:4:0x0007, B:6:0x000d, B:8:0x0021, B:9:0x0025), top: B:15:0x0007 }] */
    public static final void startIntent(Activity startIntent, Intent intent) {
        Intrinsics.checkParameterIsNotNull(startIntent, "$this$startIntent");
        if (intent != null) {
            try {
                if (intent.getAction() != null) {
                    String action = intent.getAction();
                    Intrinsics.checkExpressionValueIsNotNull(action, "intent.action");
                    if (!StringsKt.startsWith$default(action, "com.dotscreen.megacable.portal", false, 2, (Object) null)) {
                        startIntent.startActivity(intent);
                    } else {
                        startIntent.sendBroadcast(intent);
                    }
                } else {
                    startIntent.sendBroadcast(intent);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
