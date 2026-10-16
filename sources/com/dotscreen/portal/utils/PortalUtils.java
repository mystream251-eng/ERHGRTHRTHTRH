package com.dotscreen.portal.utils;

import android.content.Context;
import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class PortalUtils {
    public static final String TAG = PortalUtils.class.getSimpleName();
    private static boolean __sShouldCreatePinCode = true;

    public interface CheckAndCreatePinCodeListener {
        void onCheckDone(boolean z);
    }

    public static boolean shouldCreatePinCode() {
        return __sShouldCreatePinCode;
    }

    public static String getLanguageName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "UNK";
        }
        int identifier = context.getResources().getIdentifier("language_name_" + str, "string", context.getPackageName());
        if (identifier != 0) {
            return context.getString(identifier);
        }
        StringBuilder sb = new StringBuilder(new Locale(str).getDisplayName());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
}
