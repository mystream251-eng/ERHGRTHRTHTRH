package com.dotscreen.tv.common.utils;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableDevices.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"getFormattedValue", "", "Landroid/database/Cursor;", "key", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class MegacableDevicesKt {
    public static final String getFormattedValue(Cursor getFormattedValue, String key) {
        Intrinsics.checkParameterIsNotNull(getFormattedValue, "$this$getFormattedValue");
        Intrinsics.checkParameterIsNotNull(key, "key");
        String string = getFormattedValue.getString(getFormattedValue.getColumnIndex(key));
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(getColumnIndex(key))");
        return string;
    }
}
