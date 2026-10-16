package com.dotscreen.portal.model;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkWidget.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/model/NetworkWidget;", "Lcom/dotscreen/portal/model/BaseWidget;", "title", "", "intent", "Landroid/content/Intent;", "(Ljava/lang/String;Landroid/content/Intent;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NetworkWidget extends BaseWidget {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkWidget(String title, Intent intent) {
        super(title, intent, false, null, 12, null);
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
    }
}
