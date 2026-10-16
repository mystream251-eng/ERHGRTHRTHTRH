package com.dotscreen.farkit.updater;

import android.content.Context;
import android.util.Log;
import com.dotscreen.farkit.tools.SharedPreferencesHelper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: UpdaterInfo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001?B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u001cR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R(\u0010\u0017\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u001d\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R,\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\u0010\u001a\u0004\u0018\u00010 8F@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R+\u0010-\u001a\u00020,2\u0006\u0010+\u001a\u00020,8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R.\u00104\u001a\u0016\u0012\u0004\u0012\u000206\u0018\u000105j\n\u0012\u0004\u0012\u000206\u0018\u0001`7X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006@"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdaterInfo;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "KEY_FIRST_CHECK", "", "KEY_LAST_CHECK", "KEY_NEXT_CHECK", "PERIODIC_INTERVAL_CHECK", "checkError", "", "getCheckError", "()Z", "setCheckError", "(Z)V", "value", "Ljava/util/Date;", "firstCheck", "getFirstCheck", "()Ljava/util/Date;", "setFirstCheck", "(Ljava/util/Date;)V", "lastCheck", "getLastCheck", "setLastCheck", "listeners", "", "Lcom/dotscreen/farkit/updater/UpdaterInfo$IUpdateInfoListener;", "nextRegularCheck", "getNextRegularCheck", "setNextRegularCheck", "", "periodicInterval", "getPeriodicInterval", "()Ljava/lang/Long;", "setPeriodicInterval", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "safeListeners", "", "getSafeListeners", "()Ljava/util/List;", "<set-?>", "Lcom/dotscreen/farkit/tools/SharedPreferencesHelper;", "sharedPreferencesHelper", "getSharedPreferencesHelper", "()Lcom/dotscreen/farkit/tools/SharedPreferencesHelper;", "setSharedPreferencesHelper", "(Lcom/dotscreen/farkit/tools/SharedPreferencesHelper;)V", "sharedPreferencesHelper$delegate", "Lkotlin/properties/ReadWriteProperty;", "updateItems", "Ljava/util/ArrayList;", "Lcom/dotscreen/farkit/updater/UpdateManager$LiteUpdateItem;", "Lkotlin/collections/ArrayList;", "getUpdateItems", "()Ljava/util/ArrayList;", "setUpdateItems", "(Ljava/util/ArrayList;)V", "addUpdateInfoListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "IUpdateInfoListener", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class UpdaterInfo {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(UpdaterInfo.class), "sharedPreferencesHelper", "getSharedPreferencesHelper()Lcom/dotscreen/farkit/tools/SharedPreferencesHelper;"))};
    private final String KEY_FIRST_CHECK;
    private final String KEY_LAST_CHECK;
    private final String KEY_NEXT_CHECK;
    private final String PERIODIC_INTERVAL_CHECK;
    private boolean checkError;
    private Date firstCheck;
    private Date lastCheck;
    private final Set<IUpdateInfoListener> listeners;
    private Date nextRegularCheck;
    private Long periodicInterval;

    /* JADX INFO: renamed from: sharedPreferencesHelper$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty sharedPreferencesHelper;
    private ArrayList<UpdateManager.LiteUpdateItem> updateItems;

    /* JADX INFO: compiled from: UpdaterInfo.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\b"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdaterInfo$IUpdateInfoListener;", "", "firstUpdaterCheck", "", "date", "Ljava/util/Date;", "lastUpdaterCheck", "nextUpdaterCheck", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public interface IUpdateInfoListener {
        void firstUpdaterCheck(Date date);

        void lastUpdaterCheck(Date date);

        void nextUpdaterCheck(Date date);
    }

    private final SharedPreferencesHelper getSharedPreferencesHelper() {
        return (SharedPreferencesHelper) this.sharedPreferencesHelper.getValue(this, $$delegatedProperties[0]);
    }

    private final void setSharedPreferencesHelper(SharedPreferencesHelper sharedPreferencesHelper) {
        this.sharedPreferencesHelper.setValue(this, $$delegatedProperties[0], sharedPreferencesHelper);
    }

    public UpdaterInfo(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.listeners = new LinkedHashSet();
        this.KEY_FIRST_CHECK = "first-check";
        this.KEY_NEXT_CHECK = "next-check";
        this.KEY_LAST_CHECK = "last-check";
        this.PERIODIC_INTERVAL_CHECK = "periodic-check";
        this.sharedPreferencesHelper = Delegates.INSTANCE.notNull();
        SharedPreferencesHelper companion = SharedPreferencesHelper.INSTANCE.getInstance(context);
        if (companion == null) {
            Intrinsics.throwNpe();
        }
        setSharedPreferencesHelper(companion);
    }

    public final Date getFirstCheck() {
        long jRetrieveLong = getSharedPreferencesHelper().retrieveLong(this.KEY_FIRST_CHECK);
        if (jRetrieveLong > 0) {
            return new Date(jRetrieveLong);
        }
        return null;
    }

    public final void setFirstCheck(Date date) {
        this.firstCheck = date;
        Iterator<T> it = getSafeListeners().iterator();
        while (it.hasNext()) {
            ((IUpdateInfoListener) it.next()).firstUpdaterCheck(date);
        }
        if (date != null) {
            getSharedPreferencesHelper().store(this.KEY_FIRST_CHECK, this.firstCheck);
        } else {
            getSharedPreferencesHelper().remove(this.KEY_FIRST_CHECK);
        }
    }

    public final Date getNextRegularCheck() {
        long jRetrieveLong = getSharedPreferencesHelper().retrieveLong(this.KEY_NEXT_CHECK);
        if (jRetrieveLong > 0) {
            return new Date(jRetrieveLong);
        }
        return null;
    }

    public final void setNextRegularCheck(Date date) {
        setFirstCheck((Date) null);
        Log.d("UpdaterApplication", "UpdaterInfo => period " + getPeriodicInterval());
        if (date == null) {
            Intrinsics.throwNpe();
        }
        long time = date.getTime();
        Long periodicInterval = getPeriodicInterval();
        this.nextRegularCheck = new Date(time + (periodicInterval != null ? periodicInterval.longValue() : 0L));
        getSharedPreferencesHelper().store(this.KEY_NEXT_CHECK, this.nextRegularCheck);
        Iterator<T> it = getSafeListeners().iterator();
        while (it.hasNext()) {
            ((IUpdateInfoListener) it.next()).nextUpdaterCheck(date);
        }
    }

    public final Date getLastCheck() {
        return this.lastCheck;
    }

    public final void setLastCheck(Date date) {
        this.lastCheck = date;
        getSharedPreferencesHelper().store(this.KEY_LAST_CHECK, this.lastCheck);
        Iterator<T> it = getSafeListeners().iterator();
        while (it.hasNext()) {
            ((IUpdateInfoListener) it.next()).lastUpdaterCheck(date);
        }
    }

    public final boolean getCheckError() {
        return this.checkError;
    }

    public final void setCheckError(boolean z) {
        this.checkError = z;
    }

    public final ArrayList<UpdateManager.LiteUpdateItem> getUpdateItems() {
        return this.updateItems;
    }

    public final void setUpdateItems(ArrayList<UpdateManager.LiteUpdateItem> arrayList) {
        this.updateItems = arrayList;
    }

    public final Long getPeriodicInterval() {
        long jRetrieveLong = getSharedPreferencesHelper().retrieveLong(this.PERIODIC_INTERVAL_CHECK);
        if (jRetrieveLong > 0) {
            return Long.valueOf(jRetrieveLong);
        }
        return null;
    }

    public final void setPeriodicInterval(Long l) {
        this.periodicInterval = l;
        Log.d("UpdaterApplication", "UpdaterInfo => period set to " + l);
        getSharedPreferencesHelper().store(this.PERIODIC_INTERVAL_CHECK, this.periodicInterval);
    }

    private final List<IUpdateInfoListener> getSafeListeners() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.listeners);
        return CollectionsKt.toList(linkedHashSet);
    }

    public final void addUpdateInfoListener(IUpdateInfoListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listeners.add(listener);
    }
}
