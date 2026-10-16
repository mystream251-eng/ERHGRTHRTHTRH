package com.dotscreen.tv.common.bokit;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u0000 \r2\u00020\u0001:\u0001\rB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/tv/common/bokit/Terminal;", "", "serialNumber", "", "name", "externalId", "categoryExternalId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCategoryExternalId", "()Ljava/lang/String;", "getExternalId", "getName", "getSerialNumber", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class Terminal {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String deviceModel = Build.MODEL;
    private final String categoryExternalId;
    private final String externalId;
    private final String name;
    private final String serialNumber;

    public Terminal(String serialNumber, String name, String externalId, String categoryExternalId) {
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Intrinsics.checkParameterIsNotNull(categoryExternalId, "categoryExternalId");
        this.serialNumber = serialNumber;
        this.name = name;
        this.externalId = externalId;
        this.categoryExternalId = categoryExternalId;
    }

    public final String getSerialNumber() {
        return this.serialNumber;
    }

    public final String getName() {
        return this.name;
    }

    public final String getExternalId() {
        return this.externalId;
    }

    public final String getCategoryExternalId() {
        return this.categoryExternalId;
    }

    /* JADX INFO: compiled from: MegacableUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/tv/common/bokit/Terminal$Companion;", "", "()V", "deviceModel", "", "kotlin.jvm.PlatformType", "deviceUniqueID", "context", "Landroid/content/Context;", "name", "parseTerminal", "Lcom/dotscreen/tv/common/bokit/Terminal;", "terminalJson", "Lorg/json/JSONObject;", "playerID", "serialNumber", "username", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Terminal parseTerminal(JSONObject terminalJson) throws JSONException {
            Intrinsics.checkParameterIsNotNull(terminalJson, "terminalJson");
            String string = terminalJson.getString("serialNumber");
            Intrinsics.checkExpressionValueIsNotNull(string, "terminalJson.getString(\"serialNumber\")");
            String string2 = terminalJson.getString("name");
            Intrinsics.checkExpressionValueIsNotNull(string2, "terminalJson.getString(\"name\")");
            String string3 = terminalJson.getJSONObject("model").getString("externalId");
            Intrinsics.checkExpressionValueIsNotNull(string3, "terminalJson.getJSONObje…).getString(\"externalId\")");
            String string4 = terminalJson.getJSONObject("model").getString("categoryExternalId");
            Intrinsics.checkExpressionValueIsNotNull(string4, "terminalJson.getJSONObje…ing(\"categoryExternalId\")");
            return new Terminal(string, string2, string3, string4);
        }

        private final String deviceUniqueID(Context context) {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            Intrinsics.checkExpressionValueIsNotNull(string, "Settings.Secure.getStrin…ttings.Secure.ANDROID_ID)");
            if (string == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String upperCase = string.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
            return upperCase;
        }

        private final String playerID(Context context) {
            String strDeviceUniqueID = deviceUniqueID(context);
            if (strDeviceUniqueID == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String upperCase = strDeviceUniqueID.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
            return upperCase;
        }

        public final String serialNumber(String username, Context context) {
            Intrinsics.checkParameterIsNotNull(username, "username");
            Intrinsics.checkParameterIsNotNull(context, "context");
            StringBuilder sb = new StringBuilder();
            String upperCase = username.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
            sb.append(upperCase);
            sb.append("_");
            sb.append(playerID(context));
            return sb.toString();
        }

        public final String name(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return Terminal.deviceModel + "_" + deviceUniqueID(context);
        }
    }
}
