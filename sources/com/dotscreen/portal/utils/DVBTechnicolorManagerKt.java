package com.dotscreen.portal.utils;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DVBTechnicolorManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"PROVIDER_URL", "", "TAG", "getValue", "Landroid/database/Cursor;", "key", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class DVBTechnicolorManagerKt {
    public static final String PROVIDER_URL = "content://com.technicolor.android.dtvprovider/streamers";
    private static final String TAG = "DVBTechnicolorManager";

    public static final String getValue(Cursor getValue, String key) {
        Intrinsics.checkParameterIsNotNull(getValue, "$this$getValue");
        Intrinsics.checkParameterIsNotNull(key, "key");
        String string = getValue.getString(getValue.getColumnIndex(key));
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(getColumnIndex(key))");
        return string;
    }
}
