package com.dotscreen.tv.common.bokit;

import android.content.Context;
import com.android.tv.common.BuildConfig;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MegacableUserService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0002¨\u0006\u0006"}, d2 = {"getAppsVersion", "", "Landroid/content/Context;", "matchAppsVersion", "", "context", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class MegacableUserServiceKt {
    public static final String getAppsVersion(Context getAppsVersion) {
        String str;
        String str2;
        String str3;
        String str4;
        Intrinsics.checkParameterIsNotNull(getAppsVersion, "$this$getAppsVersion");
        String str5 = null;
        try {
            str = getAppsVersion.getPackageManager().getPackageInfo("com.dotscreen.megacable.portal", 0).versionName;
        } catch (Exception unused) {
            str = null;
        }
        try {
            str2 = getAppsVersion.getPackageManager().getPackageInfo("com.dotscreen.megacable.livetv", 0).versionName;
        } catch (Exception unused2) {
            str2 = null;
        }
        try {
            str3 = getAppsVersion.getPackageManager().getPackageInfo(BuildConfig.UPDATER_PACKAGE_NAME, 0).versionName;
        } catch (Exception unused3) {
            str3 = null;
        }
        try {
            str4 = getAppsVersion.getPackageManager().getPackageInfo(BuildConfig.CORE_PACKAGE_NAME, 0).versionName;
        } catch (Exception unused4) {
            str4 = null;
        }
        try {
            Context applicationContext = getAppsVersion.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "this.applicationContext");
            str5 = applicationContext.getPackageManager().getPackageInfo("tv.broadpeak.nanocdnwrapper", 0).versionName;
        } catch (Exception unused5) {
        }
        return str + '|' + str2 + '|' + str3 + '|' + str4 + '|' + str5 + '|' + System.currentTimeMillis();
    }

    public static final boolean matchAppsVersion(String matchAppsVersion, Context context) {
        String str;
        String str2;
        String str3;
        String str4;
        Intrinsics.checkParameterIsNotNull(matchAppsVersion, "$this$matchAppsVersion");
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str5 = null;
        try {
            str = context.getPackageManager().getPackageInfo("com.dotscreen.megacable.portal", 0).versionName;
        } catch (Exception unused) {
            str = null;
        }
        try {
            str2 = context.getPackageManager().getPackageInfo("com.dotscreen.megacable.livetv", 0).versionName;
        } catch (Exception unused2) {
            str2 = null;
        }
        try {
            str3 = context.getPackageManager().getPackageInfo(BuildConfig.UPDATER_PACKAGE_NAME, 0).versionName;
        } catch (Exception unused3) {
            str3 = null;
        }
        try {
            str4 = context.getPackageManager().getPackageInfo(BuildConfig.CORE_PACKAGE_NAME, 0).versionName;
        } catch (Exception unused4) {
            str4 = null;
        }
        try {
            str5 = context.getPackageManager().getPackageInfo("tv.broadpeak.nanocdnwrapper", 0).versionName;
        } catch (Exception unused5) {
        }
        try {
            List listSplit$default = StringsKt.split$default((CharSequence) matchAppsVersion, new String[]{"|"}, false, 0, 6, (Object) null);
            if (listSplit$default.isEmpty()) {
                return false;
            }
            if ((!listSplit$default.isEmpty()) && (!Intrinsics.areEqual((String) listSplit$default.get(0), str))) {
                return false;
            }
            if (listSplit$default.size() > 1 && (!Intrinsics.areEqual((String) listSplit$default.get(1), str2))) {
                return false;
            }
            if (listSplit$default.size() > 2 && (!Intrinsics.areEqual((String) listSplit$default.get(2), str3))) {
                return false;
            }
            if (listSplit$default.size() <= 3 || !(!Intrinsics.areEqual((String) listSplit$default.get(3), str4))) {
                return listSplit$default.size() <= 4 || !(Intrinsics.areEqual((String) listSplit$default.get(4), str5) ^ true);
            }
            return false;
        } catch (Exception unused6) {
            return false;
        }
    }
}
