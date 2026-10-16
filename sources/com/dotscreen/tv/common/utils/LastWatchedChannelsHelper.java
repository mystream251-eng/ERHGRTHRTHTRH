package com.dotscreen.tv.common.utils;

import android.content.Context;
import android.util.Log;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: LastWatchedChannelsHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\nJ\u001a\u0010\u001a\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001bj\n\u0012\u0006\u0012\u0004\u0018\u00010\n`\u001cJ\b\u0010\u001d\u001a\u0004\u0018\u00010\nJ\u0014\u0010\u001e\u001a\u00020\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0 R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Lcom/dotscreen/tv/common/utils/LastWatchedChannelsHelper;", "", "context", "Landroid/content/Context;", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/tv/common/bokit/RightVUser;", "(Landroid/content/Context;Lcom/dotscreen/tv/common/bokit/RightVUser;)V", "MAX_CHANNELS", "", "TAG", "", "kotlin.jvm.PlatformType", "<set-?>", "Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;", "sharedPreferencesHelper", "getSharedPreferencesHelper", "()Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;", "setSharedPreferencesHelper", "(Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;)V", "sharedPreferencesHelper$delegate", "Lkotlin/properties/ReadWriteProperty;", "getUser", "()Lcom/dotscreen/tv/common/bokit/RightVUser;", "addChannel", "", "channel", "getChannels", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getLastChannel", "saveChannels", "array", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class LastWatchedChannelsHelper {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(LastWatchedChannelsHelper.class), "sharedPreferencesHelper", "getSharedPreferencesHelper()Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;"))};
    private final int MAX_CHANNELS;
    private final String TAG;

    /* JADX INFO: renamed from: sharedPreferencesHelper$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty sharedPreferencesHelper;
    private final RightVUser user;

    private final SharedPreferencesHelper getSharedPreferencesHelper() {
        return (SharedPreferencesHelper) this.sharedPreferencesHelper.getValue(this, $$delegatedProperties[0]);
    }

    private final void setSharedPreferencesHelper(SharedPreferencesHelper sharedPreferencesHelper) {
        this.sharedPreferencesHelper.setValue(this, $$delegatedProperties[0], sharedPreferencesHelper);
    }

    public LastWatchedChannelsHelper(Context context, RightVUser rightVUser) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.user = rightVUser;
        this.TAG = LastWatchedChannelsHelper.class.getSimpleName();
        this.MAX_CHANNELS = 5;
        this.sharedPreferencesHelper = Delegates.INSTANCE.notNull();
        SharedPreferencesHelper companion = SharedPreferencesHelper.INSTANCE.getInstance(context);
        if (companion == null) {
            Intrinsics.throwNpe();
        }
        setSharedPreferencesHelper(companion);
    }

    public final RightVUser getUser() {
        return this.user;
    }

    public final String getLastChannel() {
        ArrayList<String> channels = getChannels();
        ArrayList<String> arrayList = channels;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return channels.get(0);
    }

    public final void addChannel(String channel) {
        ArrayList arrayList = new ArrayList();
        if (channel != null) {
            arrayList.add(channel);
        }
        for (String str : getChannels()) {
            if (str != null && (!Intrinsics.areEqual(str, channel))) {
                arrayList.add(str);
            }
        }
        saveChannels(CollectionsKt.take(arrayList, this.MAX_CHANNELS));
    }

    public final ArrayList<String> getChannels() {
        String compassId;
        RightVUser rightVUser = this.user;
        if (rightVUser == null || (compassId = rightVUser.getCompassId()) == null) {
            compassId = "UNKNOWN";
        }
        int iRetrieveInt = getSharedPreferencesHelper().retrieveInt(compassId + "_size");
        ArrayList<String> arrayList = new ArrayList<>(iRetrieveInt);
        for (int i = 0; i < iRetrieveInt; i++) {
            arrayList.add(getSharedPreferencesHelper().retrieveString(compassId + '_' + i));
        }
        return arrayList;
    }

    public final void saveChannels(List<String> array) {
        String compassId;
        Intrinsics.checkParameterIsNotNull(array, "array");
        RightVUser rightVUser = this.user;
        if (rightVUser == null || (compassId = rightVUser.getCompassId()) == null) {
            compassId = "UNKNOWN";
        }
        getSharedPreferencesHelper().store(compassId + "_size", Integer.valueOf(array.size()));
        int size = array.size();
        for (int i = 0; i < size; i++) {
            getSharedPreferencesHelper().store(compassId + '_' + i, array.get(i));
        }
        Log.i(this.TAG, "saveChannels()-> " + array);
    }
}
