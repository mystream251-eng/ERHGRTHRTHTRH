package com.dotscreen.tv.common.utils;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: LoginHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R+\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/tv/common/utils/LoginHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "KEY_PASSWORD", "", "KEY_USERNAME", "<set-?>", "Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;", "sharedPreferencesHelper", "getSharedPreferencesHelper", "()Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;", "setSharedPreferencesHelper", "(Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;)V", "sharedPreferencesHelper$delegate", "Lkotlin/properties/ReadWriteProperty;", "getPassword", "getUsername", "removeUserCredentials", "", "storeUserCredentials", "username", "password", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class LoginHelper {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(LoginHelper.class), "sharedPreferencesHelper", "getSharedPreferencesHelper()Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;"))};
    private final String KEY_PASSWORD;
    private final String KEY_USERNAME;

    /* JADX INFO: renamed from: sharedPreferencesHelper$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty sharedPreferencesHelper;

    private final SharedPreferencesHelper getSharedPreferencesHelper() {
        return (SharedPreferencesHelper) this.sharedPreferencesHelper.getValue(this, $$delegatedProperties[0]);
    }

    private final void setSharedPreferencesHelper(SharedPreferencesHelper sharedPreferencesHelper) {
        this.sharedPreferencesHelper.setValue(this, $$delegatedProperties[0], sharedPreferencesHelper);
    }

    public LoginHelper(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.sharedPreferencesHelper = Delegates.INSTANCE.notNull();
        SharedPreferencesHelper companion = SharedPreferencesHelper.INSTANCE.getInstance(context);
        if (companion == null) {
            Intrinsics.throwNpe();
        }
        setSharedPreferencesHelper(companion);
        this.KEY_USERNAME = "login-username";
        this.KEY_PASSWORD = "login-password";
    }

    public final void storeUserCredentials(String username, String password) {
        Intrinsics.checkParameterIsNotNull(username, "username");
        Intrinsics.checkParameterIsNotNull(password, "password");
        removeUserCredentials();
        getSharedPreferencesHelper().store(this.KEY_USERNAME, username);
        getSharedPreferencesHelper().store(this.KEY_PASSWORD, password);
    }

    public final void removeUserCredentials() {
        getSharedPreferencesHelper().remove(this.KEY_USERNAME);
        getSharedPreferencesHelper().remove(this.KEY_PASSWORD);
    }

    public final String getUsername() {
        return getSharedPreferencesHelper().retrieveString(this.KEY_USERNAME);
    }

    public final String getPassword() {
        return getSharedPreferencesHelper().retrieveString(this.KEY_PASSWORD);
    }
}
