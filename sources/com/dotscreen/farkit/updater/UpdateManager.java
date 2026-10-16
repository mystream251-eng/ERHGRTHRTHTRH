package com.dotscreen.farkit.updater;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.farkit.tools.JsonKt;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.sentry.cache.EnvelopeCache;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UpdateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0006DEFGHIB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u001bJ\u0010\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020\u0006H\u0007J\u0016\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020*J;\u00104\u001a\u0004\u0018\u00010\r21\b\u0002\u00105\u001a+\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000107¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b\u0018\u000106JX\u0010;\u001a\u0004\u0018\u00010<2\u0006\u00102\u001a\u00020\u00062F\b\u0002\u00105\u001a@\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(2\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000107¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b\u0018\u00010=J;\u0010>\u001a\u0004\u0018\u00010\r21\b\u0002\u00105\u001a+\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000107¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b\u0018\u000106J\u000e\u0010?\u001a\u00020,2\u0006\u00102\u001a\u00020\u0006J\u0006\u0010@\u001a\u00020,J\u000e\u0010A\u001a\u00020,2\u0006\u0010-\u001a\u00020\u001bJ\u000e\u0010B\u001a\u00020,2\u0006\u0010C\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u0004\u0018\u00010\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0018\u00010\u0018R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001b0\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020*0)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdateManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "ACTION_INSTALL_PROGRESS", "", "DEBUG", "", "TAG", "kotlin.jvm.PlatformType", "value", "", "Lcom/dotscreen/farkit/updater/UpdateManager$BundleItem;", "bundleItems", "setBundleItems", "(Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "<set-?>", "currentBundle", "getCurrentBundle", "()Lcom/dotscreen/farkit/updater/UpdateManager$BundleItem;", "installSessionReceiver", "Lcom/dotscreen/farkit/updater/UpdateManager$InstallSessionReceiver;", "listeners", "", "Lcom/dotscreen/farkit/updater/UpdateManager$IUpdateManagerListener;", "liteUpdateItems", "Ljava/util/ArrayList;", "Lcom/dotscreen/farkit/updater/UpdateManager$LiteUpdateItem;", "getLiteUpdateItems", "()Ljava/util/ArrayList;", "packageNames", "", "requestManager", "Lcom/dotscreen/farkit/network/RequestManager;", "safeListeners", "getSafeListeners", "()Ljava/util/List;", "sessionIds", "", "", "addUpdateManagerListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "checkUpdates", "updateFileUrl", "download", "Lcom/dotscreen/farkit/updater/UpdateManager$ICancelable;", "packageName", "versionCode", "getLatestBundleUpdate", "matchCriteria", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "criteria", "getLatestUpdate", "Lcom/dotscreen/farkit/updater/UpdateManager$UpdateItem;", "Lkotlin/Function2;", "getPriorityBundleUpdate", "install", "purgeInactiveSessions", "removeUpdateManagerListener", "selectCurrentBundle", "bundle", "BundleItem", "ICancelable", "IUpdateManagerListener", "InstallSessionReceiver", "LiteUpdateItem", "UpdateItem", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class UpdateManager {
    private final String ACTION_INSTALL_PROGRESS;
    private final boolean DEBUG;
    private final String TAG;
    private List<BundleItem> bundleItems;
    private final Context context;
    private BundleItem currentBundle;
    private InstallSessionReceiver installSessionReceiver;
    private final Set<IUpdateManagerListener> listeners;
    private final List<String> packageNames;
    private final RequestManager requestManager;
    private final Map<String, Integer> sessionIds;

    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdateManager$ICancelable;", "", "cancel", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public interface ICancelable {
        void cancel();
    }

    public UpdateManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = UpdateManager.class.getSimpleName();
        this.DEBUG = true;
        this.packageNames = new ArrayList();
        this.ACTION_INSTALL_PROGRESS = "com.dotscreen.farkit.ACTION_INSTALL_PROGRESS";
        this.listeners = new LinkedHashSet();
        this.sessionIds = new LinkedHashMap();
        this.requestManager = new RequestManager(this.context, null, false, 6, null);
        if (Build.VERSION.SDK_INT >= 21) {
            this.installSessionReceiver = new InstallSessionReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(this.ACTION_INSTALL_PROGRESS);
            this.context.registerReceiver(this.installSessionReceiver, intentFilter);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBundleItems(List<BundleItem> list) {
        this.bundleItems = list;
        Intent intent = new Intent();
        intent.setAction(UpdateManagerKt.ACTION_NOTIFY_CHECK_RESULT);
        intent.putExtra(UpdateManagerKt.EXTRA_CHECK_RESULT_DATA, getLiteUpdateItems());
        this.context.sendBroadcast(intent);
    }

    public final BundleItem getCurrentBundle() {
        return this.currentBundle;
    }

    public final void selectCurrentBundle(BundleItem bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        this.currentBundle = bundle;
    }

    protected final ArrayList<LiteUpdateItem> getLiteUpdateItems() {
        List<UpdateItem> apks;
        if (this.currentBundle != null) {
            ArrayList<LiteUpdateItem> arrayList = new ArrayList<>();
            BundleItem bundleItem = this.currentBundle;
            if (bundleItem == null || (apks = bundleItem.getApks()) == null) {
                return arrayList;
            }
            for (UpdateItem updateItem : apks) {
                arrayList.add(new LiteUpdateItem(updateItem.getPackageName(), updateItem.getVersionName(), updateItem.getVersionCode()));
            }
            return arrayList;
        }
        return new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<IUpdateManagerListener> getSafeListeners() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.listeners);
        return CollectionsKt.toList(linkedHashSet);
    }

    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u000bHÆ\u0003JG\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdateManager$BundleItem;", "", "expirationDate", "Ljava/util/Date;", "criteria", "", "", "apks", "", "Lcom/dotscreen/farkit/updater/UpdateManager$UpdateItem;", "priority", "", "(Ljava/util/Date;Ljava/util/Map;Ljava/util/List;I)V", "getApks", "()Ljava/util/List;", "getCriteria", "()Ljava/util/Map;", "getExpirationDate", "()Ljava/util/Date;", "getPriority", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class BundleItem {
        private final List<UpdateItem> apks;
        private final Map<String, Object> criteria;
        private final Date expirationDate;
        private final int priority;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BundleItem copy$default(BundleItem bundleItem, Date date, Map map, List list, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                date = bundleItem.expirationDate;
            }
            if ((i2 & 2) != 0) {
                map = bundleItem.criteria;
            }
            if ((i2 & 4) != 0) {
                list = bundleItem.apks;
            }
            if ((i2 & 8) != 0) {
                i = bundleItem.priority;
            }
            return bundleItem.copy(date, map, list, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Date getExpirationDate() {
            return this.expirationDate;
        }

        public final Map<String, Object> component2() {
            return this.criteria;
        }

        public final List<UpdateItem> component3() {
            return this.apks;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getPriority() {
            return this.priority;
        }

        public final BundleItem copy(Date expirationDate, Map<String, ? extends Object> criteria, List<UpdateItem> apks, int priority) {
            Intrinsics.checkParameterIsNotNull(apks, "apks");
            return new BundleItem(expirationDate, criteria, apks, priority);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BundleItem)) {
                return false;
            }
            BundleItem bundleItem = (BundleItem) other;
            return Intrinsics.areEqual(this.expirationDate, bundleItem.expirationDate) && Intrinsics.areEqual(this.criteria, bundleItem.criteria) && Intrinsics.areEqual(this.apks, bundleItem.apks) && this.priority == bundleItem.priority;
        }

        public int hashCode() {
            Date date = this.expirationDate;
            int iHashCode = (date != null ? date.hashCode() : 0) * 31;
            Map<String, Object> map = this.criteria;
            int iHashCode2 = (iHashCode + (map != null ? map.hashCode() : 0)) * 31;
            List<UpdateItem> list = this.apks;
            return ((iHashCode2 + (list != null ? list.hashCode() : 0)) * 31) + this.priority;
        }

        public String toString() {
            return "BundleItem(expirationDate=" + this.expirationDate + ", criteria=" + this.criteria + ", apks=" + this.apks + ", priority=" + this.priority + ")";
        }

        public BundleItem(Date date, Map<String, ? extends Object> map, List<UpdateItem> apks, int i) {
            Intrinsics.checkParameterIsNotNull(apks, "apks");
            this.expirationDate = date;
            this.criteria = map;
            this.apks = apks;
            this.priority = i;
        }

        public /* synthetic */ BundleItem(Date date, Map map, ArrayList arrayList, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? (Date) null : date, (i2 & 2) != 0 ? (Map) null : map, (i2 & 4) != 0 ? new ArrayList() : arrayList, i);
        }

        public final Date getExpirationDate() {
            return this.expirationDate;
        }

        public final Map<String, Object> getCriteria() {
            return this.criteria;
        }

        public final List<UpdateItem> getApks() {
            return this.apks;
        }

        public final int getPriority() {
            return this.priority;
        }
    }

    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000eHÆ\u0003Ji\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000eHÆ\u0001J\u0013\u0010&\u001a\u00020\f2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\bHÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006*"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdateManager$UpdateItem;", "", "title", "", "packageName", "apkUrl", "versionName", "versionCode", "", "expirationDate", "Ljava/util/Date;", "forced", "", "criteria", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/Date;ZLjava/util/Map;)V", "getApkUrl", "()Ljava/lang/String;", "getCriteria", "()Ljava/util/Map;", "getExpirationDate", "()Ljava/util/Date;", "getForced", "()Z", "getPackageName", "getTitle", "getVersionCode", "()I", "getVersionName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class UpdateItem {
        private final String apkUrl;
        private final Map<String, Object> criteria;
        private final Date expirationDate;
        private final boolean forced;
        private final String packageName;
        private final String title;
        private final int versionCode;
        private final String versionName;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getApkUrl() {
            return this.apkUrl;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getVersionName() {
            return this.versionName;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getVersionCode() {
            return this.versionCode;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Date getExpirationDate() {
            return this.expirationDate;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getForced() {
            return this.forced;
        }

        public final Map<String, Object> component8() {
            return this.criteria;
        }

        public final UpdateItem copy(String title, String packageName, String apkUrl, String versionName, int versionCode, Date expirationDate, boolean forced, Map<String, ? extends Object> criteria) {
            Intrinsics.checkParameterIsNotNull(title, "title");
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(apkUrl, "apkUrl");
            Intrinsics.checkParameterIsNotNull(versionName, "versionName");
            return new UpdateItem(title, packageName, apkUrl, versionName, versionCode, expirationDate, forced, criteria);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UpdateItem)) {
                return false;
            }
            UpdateItem updateItem = (UpdateItem) other;
            return Intrinsics.areEqual(this.title, updateItem.title) && Intrinsics.areEqual(this.packageName, updateItem.packageName) && Intrinsics.areEqual(this.apkUrl, updateItem.apkUrl) && Intrinsics.areEqual(this.versionName, updateItem.versionName) && this.versionCode == updateItem.versionCode && Intrinsics.areEqual(this.expirationDate, updateItem.expirationDate) && this.forced == updateItem.forced && Intrinsics.areEqual(this.criteria, updateItem.criteria);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v14, types: [int] */
        /* JADX WARN: Type inference failed for: r2v14, types: [int] */
        /* JADX WARN: Type inference failed for: r2v16 */
        /* JADX WARN: Type inference failed for: r2v21 */
        public int hashCode() {
            String str = this.title;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.packageName;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.apkUrl;
            int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.versionName;
            int iHashCode4 = (((iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.versionCode) * 31;
            Date date = this.expirationDate;
            int iHashCode5 = (iHashCode4 + (date != null ? date.hashCode() : 0)) * 31;
            boolean z = this.forced;
            ?? r2 = z;
            if (z) {
                r2 = 1;
            }
            int i = (iHashCode5 + r2) * 31;
            Map<String, Object> map = this.criteria;
            return i + (map != null ? map.hashCode() : 0);
        }

        public String toString() {
            return "UpdateItem(title=" + this.title + ", packageName=" + this.packageName + ", apkUrl=" + this.apkUrl + ", versionName=" + this.versionName + ", versionCode=" + this.versionCode + ", expirationDate=" + this.expirationDate + ", forced=" + this.forced + ", criteria=" + this.criteria + ")";
        }

        public UpdateItem(String title, String packageName, String apkUrl, String versionName, int i, Date date, boolean z, Map<String, ? extends Object> map) {
            Intrinsics.checkParameterIsNotNull(title, "title");
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(apkUrl, "apkUrl");
            Intrinsics.checkParameterIsNotNull(versionName, "versionName");
            this.title = title;
            this.packageName = packageName;
            this.apkUrl = apkUrl;
            this.versionName = versionName;
            this.versionCode = i;
            this.expirationDate = date;
            this.forced = z;
            this.criteria = map;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final String getApkUrl() {
            return this.apkUrl;
        }

        public final String getVersionName() {
            return this.versionName;
        }

        public final int getVersionCode() {
            return this.versionCode;
        }

        public /* synthetic */ UpdateItem(String str, String str2, String str3, String str4, int i, Date date, boolean z, Map map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, i, (i2 & 32) != 0 ? (Date) null : date, (i2 & 64) != 0 ? false : z, (i2 & 128) != 0 ? (Map) null : map);
        }

        public final Date getExpirationDate() {
            return this.expirationDate;
        }

        public final boolean getForced() {
            return this.forced;
        }

        public final Map<String, Object> getCriteria() {
            return this.criteria;
        }
    }

    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B!\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\tHÆ\u0003J+\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\b\u0010\u0014\u001a\u00020\tH\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\tHÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\tH\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdateManager$LiteUpdateItem;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "packageName", "", "versionName", "versionCode", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getPackageName", "()Ljava/lang/String;", "getVersionCode", "()I", "getVersionName", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", NotificationsContract.COLUMN_FLAGS, "CREATOR", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class LiteUpdateItem implements Parcelable {

        /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String packageName;
        private final int versionCode;
        private final String versionName;

        public static /* synthetic */ LiteUpdateItem copy$default(LiteUpdateItem liteUpdateItem, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = liteUpdateItem.packageName;
            }
            if ((i2 & 2) != 0) {
                str2 = liteUpdateItem.versionName;
            }
            if ((i2 & 4) != 0) {
                i = liteUpdateItem.versionCode;
            }
            return liteUpdateItem.copy(str, str2, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVersionName() {
            return this.versionName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getVersionCode() {
            return this.versionCode;
        }

        public final LiteUpdateItem copy(String packageName, String versionName, int versionCode) {
            return new LiteUpdateItem(packageName, versionName, versionCode);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LiteUpdateItem)) {
                return false;
            }
            LiteUpdateItem liteUpdateItem = (LiteUpdateItem) other;
            return Intrinsics.areEqual(this.packageName, liteUpdateItem.packageName) && Intrinsics.areEqual(this.versionName, liteUpdateItem.versionName) && this.versionCode == liteUpdateItem.versionCode;
        }

        public int hashCode() {
            String str = this.packageName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.versionName;
            return ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.versionCode;
        }

        public String toString() {
            return "LiteUpdateItem(packageName=" + this.packageName + ", versionName=" + this.versionName + ", versionCode=" + this.versionCode + ")";
        }

        public LiteUpdateItem(String str, String str2, int i) {
            this.packageName = str;
            this.versionName = str2;
            this.versionCode = i;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final int getVersionCode() {
            return this.versionCode;
        }

        public final String getVersionName() {
            return this.versionName;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public LiteUpdateItem(Parcel parcel) {
            this(parcel.readString(), parcel.readString(), parcel.readInt());
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            parcel.writeString(this.packageName);
            parcel.writeString(this.versionName);
            parcel.writeInt(this.versionCode);
        }

        /* JADX INFO: renamed from: com.dotscreen.farkit.updater.UpdateManager$LiteUpdateItem$CREATOR, reason: from kotlin metadata */
        /* JADX INFO: compiled from: UpdateManager.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdateManager$LiteUpdateItem$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/dotscreen/farkit/updater/UpdateManager$LiteUpdateItem;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/dotscreen/farkit/updater/UpdateManager$LiteUpdateItem;", "farkit_release"}, k = 1, mv = {1, 1, 16})
        public static final class Companion implements Parcelable.Creator<LiteUpdateItem> {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LiteUpdateItem createFromParcel(Parcel parcel) {
                Intrinsics.checkParameterIsNotNull(parcel, "parcel");
                return new LiteUpdateItem(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LiteUpdateItem[] newArray(int size) {
                return new LiteUpdateItem[size];
            }
        }
    }

    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J*\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000eH&J2\u0010\u000f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0010\b\u0002\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000eH&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J*\u0010\u0014\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000eH&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\u0016\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H&¨\u0006\u001a"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdateManager$IUpdateManagerListener;", "", "onCheckFailed", "", "reason", "", "checkUrl", "", "onDownloadFinished", "packageName", NotificationCompat.CATEGORY_STATUS, "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onDownloadProgress", "downloaded", "", "total", "onDownloadStarted", "onInstallFinished", "onInstallStarted", "onUpdatesAvailable", "items", "", "Lcom/dotscreen/farkit/updater/UpdateManager$BundleItem;", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public interface IUpdateManagerListener {
        void onCheckFailed(Throwable reason, String checkUrl);

        void onDownloadFinished(String packageName, int status, Exception exception);

        void onDownloadProgress(String packageName, long downloaded, long total, Exception exception);

        void onDownloadStarted(String packageName);

        void onInstallFinished(String packageName, int status, Exception exception);

        void onInstallStarted(String packageName);

        void onUpdatesAvailable(List<BundleItem> items);

        /* JADX INFO: compiled from: UpdateManager.kt */
        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public static final class DefaultImpls {
            public static /* synthetic */ void onDownloadProgress$default(IUpdateManagerListener iUpdateManagerListener, String str, long j, long j2, Exception exc, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onDownloadProgress");
                }
                if ((i & 8) != 0) {
                    exc = (Exception) null;
                }
                iUpdateManagerListener.onDownloadProgress(str, j, j2, exc);
            }

            public static /* synthetic */ void onDownloadFinished$default(IUpdateManagerListener iUpdateManagerListener, String str, int i, Exception exc, int i2, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onDownloadFinished");
                }
                if ((i2 & 4) != 0) {
                    exc = (Exception) null;
                }
                iUpdateManagerListener.onDownloadFinished(str, i, exc);
            }

            public static /* synthetic */ void onInstallFinished$default(IUpdateManagerListener iUpdateManagerListener, String str, int i, Exception exc, int i2, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onInstallFinished");
                }
                if ((i2 & 4) != 0) {
                    exc = (Exception) null;
                }
                iUpdateManagerListener.onInstallFinished(str, i, exc);
            }
        }
    }

    public final void purgeInactiveSessions() {
        if (this.DEBUG) {
            Log.d(this.TAG, "purgeInactiveSessions...");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            PackageManager packageManager = this.context.getPackageManager();
            Intrinsics.checkExpressionValueIsNotNull(packageManager, "context.packageManager");
            PackageInstaller packageInstaller = packageManager.getPackageInstaller();
            Intrinsics.checkExpressionValueIsNotNull(packageInstaller, "context.packageManager.packageInstaller");
            for (PackageInstaller.SessionInfo s : packageInstaller.getMySessions()) {
                if (this.DEBUG) {
                    String str = this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Session: ");
                    Intrinsics.checkExpressionValueIsNotNull(s, "s");
                    sb.append(s.getAppPackageName());
                    sb.append(':');
                    Log.d(str, sb.toString());
                    Log.d(this.TAG, "   installer: " + s.getInstallerPackageName());
                    Log.d(this.TAG, "   active: " + s.isActive());
                    if (Build.VERSION.SDK_INT >= 26) {
                        Log.d(this.TAG, "   sealed: " + s.isSealed());
                    }
                }
                Intrinsics.checkExpressionValueIsNotNull(s, "s");
                if (!s.isActive()) {
                    if (this.DEBUG) {
                        Log.d(this.TAG, "   purging...");
                    }
                    try {
                        packageInstaller.openSession(s.getSessionId()).abandon();
                        if (this.DEBUG) {
                            Log.d(this.TAG, "   purged");
                        }
                    } catch (Throwable th) {
                        if (this.DEBUG) {
                            Log.w(this.TAG, "   error: " + th);
                        }
                    }
                }
            }
        }
    }

    public final void addUpdateManagerListener(IUpdateManagerListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listeners.add(listener);
    }

    public final void removeUpdateManagerListener(IUpdateManagerListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listeners.remove(listener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BundleItem getLatestBundleUpdate$default(UpdateManager updateManager, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        return updateManager.getLatestBundleUpdate(function1);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0036  */
    public final BundleItem getLatestBundleUpdate(Function1<? super Map<String, ? extends Object>, Boolean> matchCriteria) {
        boolean zBooleanValue;
        List<BundleItem> list = this.bundleItems;
        Object next = null;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            Map<String, Object> criteria = ((BundleItem) obj).getCriteria();
            if (criteria == null) {
                zBooleanValue = true;
            } else {
                Boolean boolInvoke = matchCriteria != null ? matchCriteria.invoke(criteria) : null;
                if (boolInvoke != null) {
                    zBooleanValue = boolInvoke.booleanValue();
                } else {
                    zBooleanValue = true;
                }
            }
            if (zBooleanValue) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                Date expirationDate = ((BundleItem) next).getExpirationDate();
                long time = expirationDate != null ? expirationDate.getTime() : 0L;
                do {
                    Object next2 = it.next();
                    Date expirationDate2 = ((BundleItem) next2).getExpirationDate();
                    long time2 = expirationDate2 != null ? expirationDate2.getTime() : 0L;
                    if (time < time2) {
                        next = next2;
                        time = time2;
                    }
                } while (it.hasNext());
            }
        }
        return (BundleItem) next;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BundleItem getPriorityBundleUpdate$default(UpdateManager updateManager, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        return updateManager.getPriorityBundleUpdate(function1);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0036  */
    public final BundleItem getPriorityBundleUpdate(Function1<? super Map<String, ? extends Object>, Boolean> matchCriteria) {
        boolean zBooleanValue;
        List<BundleItem> list = this.bundleItems;
        Object next = null;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            Map<String, Object> criteria = ((BundleItem) obj).getCriteria();
            if (criteria == null) {
                zBooleanValue = true;
            } else {
                Boolean boolInvoke = matchCriteria != null ? matchCriteria.invoke(criteria) : null;
                if (boolInvoke != null) {
                    zBooleanValue = boolInvoke.booleanValue();
                } else {
                    zBooleanValue = true;
                }
            }
            if (zBooleanValue) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                int priority = ((BundleItem) next).getPriority();
                do {
                    Object next2 = it.next();
                    int priority2 = ((BundleItem) next2).getPriority();
                    if (priority > priority2) {
                        next = next2;
                        priority = priority2;
                    }
                } while (it.hasNext());
            }
        }
        return (BundleItem) next;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UpdateItem getLatestUpdate$default(UpdateManager updateManager, String str, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        return updateManager.getLatestUpdate(str, function2);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x004c  */
    /* JADX WARN: Code duplicated, block: B:21:0x0050  */
    public final UpdateItem getLatestUpdate(String packageName, Function2<? super String, ? super Map<String, ? extends Object>, Boolean> matchCriteria) {
        List<UpdateItem> apks;
        boolean z;
        boolean zBooleanValue;
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        BundleItem bundleItem = this.currentBundle;
        Object next = null;
        if (bundleItem == null || (apks = bundleItem.getApks()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : apks) {
            UpdateItem updateItem = (UpdateItem) obj;
            if (Intrinsics.areEqual(updateItem.getPackageName(), packageName)) {
                Map<String, Object> criteria = updateItem.getCriteria();
                if (criteria == null) {
                    zBooleanValue = true;
                } else {
                    Boolean boolInvoke = matchCriteria != null ? matchCriteria.invoke(packageName, criteria) : null;
                    if (boolInvoke != null) {
                        zBooleanValue = boolInvoke.booleanValue();
                    } else {
                        zBooleanValue = true;
                    }
                }
                z = zBooleanValue;
            }
            if (z) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                int versionCode = ((UpdateItem) next).getVersionCode();
                do {
                    Object next2 = it.next();
                    int versionCode2 = ((UpdateItem) next2).getVersionCode();
                    if (versionCode < versionCode2) {
                        next = next2;
                        versionCode = versionCode2;
                    }
                } while (it.hasNext());
            }
        }
        return (UpdateItem) next;
    }

    public final ICancelable download(String packageName, int versionCode) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        if (Build.VERSION.SDK_INT >= 21) {
            if (this.DEBUG) {
                Log.v(this.TAG, "start downloading " + packageName + ' ' + versionCode);
            }
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = false;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02661(packageName, versionCode, booleanRef, null), 2, null);
            return new ICancelable() { // from class: com.dotscreen.farkit.updater.UpdateManager.download.2
                @Override // com.dotscreen.farkit.updater.UpdateManager.ICancelable
                public void cancel() {
                    booleanRef.element = true;
                }
            };
        }
        if (this.DEBUG) {
            Log.w(this.TAG, "android version " + Build.VERSION.SDK_INT + " does not supports that feature");
        }
        if (this.DEBUG) {
            Log.w(this.TAG, "download failed for " + packageName + ' ' + versionCode);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02683(packageName, null), 2, null);
        return new ICancelable() { // from class: com.dotscreen.farkit.updater.UpdateManager.download.4
            @Override // com.dotscreen.farkit.updater.UpdateManager.ICancelable
            public void cancel() {
            }
        };
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.updater.UpdateManager$download$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.updater.UpdateManager$download$1", f = "UpdateManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {318}, m = "invokeSuspend", n = {"$this$launch", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "timeoutJob", "item", "packageInstaller", "params", "sessionId", "connection", "totalBytes", TvContractCompat.PARAM_INPUT, "retryCount", "output", "bytesCopied", "buffer", "bytesRead", "e", "retryDelay"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$6", "J$0", "L$7", "L$8", "L$11", "L$12", "L$13", "I$1", "L$14", "J$1"})
    static final class C02661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $canceled;
        final /* synthetic */ String $packageName;
        final /* synthetic */ int $versionCode;
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02661(String str, int i, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
            this.$versionCode = i;
            this.$canceled = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02661 c02661 = UpdateManager.this.new C02661(this.$packageName, this.$versionCode, this.$canceled, completion);
            c02661.p$ = (CoroutineScope) obj;
            return c02661;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:115:0x036f  */
        /* JADX WARN: Code duplicated, block: B:120:0x03b7 A[Catch: Exception -> 0x0409, all -> 0x0440, TryCatch #22 {all -> 0x0440, blocks: (B:118:0x03af, B:120:0x03b7, B:122:0x03c1, B:125:0x03c8, B:127:0x03ce, B:130:0x03d9, B:132:0x03e1, B:134:0x03e7, B:159:0x043a, B:165:0x0448, B:172:0x046c), top: B:358:0x03af }] */
        /* JADX WARN: Code duplicated, block: B:124:0x03c7  */
        /* JADX WARN: Code duplicated, block: B:127:0x03ce A[Catch: Exception -> 0x0405, all -> 0x0440, TryCatch #22 {all -> 0x0440, blocks: (B:118:0x03af, B:120:0x03b7, B:122:0x03c1, B:125:0x03c8, B:127:0x03ce, B:130:0x03d9, B:132:0x03e1, B:134:0x03e7, B:159:0x043a, B:165:0x0448, B:172:0x046c), top: B:358:0x03af }] */
        /* JADX WARN: Code duplicated, block: B:129:0x03d7 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:130:0x03d9 A[Catch: Exception -> 0x0405, all -> 0x0440, TryCatch #22 {all -> 0x0440, blocks: (B:118:0x03af, B:120:0x03b7, B:122:0x03c1, B:125:0x03c8, B:127:0x03ce, B:130:0x03d9, B:132:0x03e1, B:134:0x03e7, B:159:0x043a, B:165:0x0448, B:172:0x046c), top: B:358:0x03af }] */
        /* JADX WARN: Code duplicated, block: B:131:0x03e0  */
        /* JADX WARN: Code duplicated, block: B:138:0x03ec  */
        /* JADX WARN: Code duplicated, block: B:191:0x0597 A[Catch: all -> 0x0654, TryCatch #33 {all -> 0x0654, blocks: (B:189:0x055b, B:191:0x0597, B:193:0x059f, B:194:0x05c5, B:196:0x05ff, B:198:0x0606, B:199:0x060b, B:201:0x0611, B:202:0x0616), top: B:370:0x055b }] */
        /* JADX WARN: Code duplicated, block: B:193:0x059f A[Catch: all -> 0x0654, TryCatch #33 {all -> 0x0654, blocks: (B:189:0x055b, B:191:0x0597, B:193:0x059f, B:194:0x05c5, B:196:0x05ff, B:198:0x0606, B:199:0x060b, B:201:0x0611, B:202:0x0616), top: B:370:0x055b }] */
        /* JADX WARN: Code duplicated, block: B:196:0x05ff A[Catch: all -> 0x0654, TryCatch #33 {all -> 0x0654, blocks: (B:189:0x055b, B:191:0x0597, B:193:0x059f, B:194:0x05c5, B:196:0x05ff, B:198:0x0606, B:199:0x060b, B:201:0x0611, B:202:0x0616), top: B:370:0x055b }] */
        /* JADX WARN: Code duplicated, block: B:198:0x0606 A[Catch: all -> 0x0654, TryCatch #33 {all -> 0x0654, blocks: (B:189:0x055b, B:191:0x0597, B:193:0x059f, B:194:0x05c5, B:196:0x05ff, B:198:0x0606, B:199:0x060b, B:201:0x0611, B:202:0x0616), top: B:370:0x055b }] */
        /* JADX WARN: Code duplicated, block: B:201:0x0611 A[Catch: all -> 0x0654, TryCatch #33 {all -> 0x0654, blocks: (B:189:0x055b, B:191:0x0597, B:193:0x059f, B:194:0x05c5, B:196:0x05ff, B:198:0x0606, B:199:0x060b, B:201:0x0611, B:202:0x0616), top: B:370:0x055b }] */
        /* JADX WARN: Code duplicated, block: B:206:0x0621 A[Catch: Exception -> 0x0627, TRY_LEAVE, TryCatch #16 {Exception -> 0x0627, blocks: (B:204:0x061b, B:206:0x0621), top: B:348:0x061b }] */
        /* JADX WARN: Code duplicated, block: B:212:0x0638  */
        /* JADX WARN: Code duplicated, block: B:340:0x02fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:370:0x055b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:85:0x02d9  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v64, types: [T, java.net.URLConnection] */
        /* JADX WARN: Type inference failed for: r0v74, types: [T, java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r0v79, types: [T, kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r10v11, types: [T, java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r11v5, types: [T, java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r12v4, types: [T, java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r6v1, types: [T, android.content.pm.PackageInstaller$Session] */
        /* JADX WARN: Type inference failed for: r6v3, types: [T, kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r8v6, types: [T, android.content.pm.PackageInstaller$Session] */
        /* JADX WARN: Type inference failed for: r9v4, types: [T, java.net.URLConnection] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:185:0x04e6 -> B:328:0x0503). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r49) {
            /*
                Method dump skipped, instruction units count: 2098
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.farkit.updater.UpdateManager.C02661.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.dotscreen.farkit.updater.UpdateManager$download$1$5, reason: invalid class name */
        /* JADX INFO: compiled from: UpdateManager.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.farkit.updater.UpdateManager$download$1$5", f = "UpdateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Exception $e;
            final /* synthetic */ int $status;
            int label;
            private CoroutineScope p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass5(int i, Exception exc, Continuation continuation) {
                super(2, continuation);
                this.$status = i;
                this.$e = exc;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass5 anonymousClass5 = C02661.this.new AnonymousClass5(this.$status, this.$e, completion);
                anonymousClass5.p$ = (CoroutineScope) obj;
                return anonymousClass5;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Iterator it = UpdateManager.this.getSafeListeners().iterator();
                while (it.hasNext()) {
                    ((IUpdateManagerListener) it.next()).onDownloadFinished(C02661.this.$packageName, this.$status, this.$e);
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.updater.UpdateManager$download$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.updater.UpdateManager$download$3", f = "UpdateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02683 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $packageName;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02683(String str, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02683 c02683 = UpdateManager.this.new C02683(this.$packageName, completion);
            c02683.p$ = (CoroutineScope) obj;
            return c02683;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02683) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            for (IUpdateManagerListener iUpdateManagerListener : UpdateManager.this.getSafeListeners()) {
                if (UpdateManager.this.DEBUG) {
                    Log.w(UpdateManager.this.TAG, iUpdateManagerListener + " is notify that download failed");
                }
                IUpdateManagerListener.DefaultImpls.onDownloadFinished$default(iUpdateManagerListener, this.$packageName, -3, null, 4, null);
            }
            return Unit.INSTANCE;
        }
    }

    public final void install(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                Integer num = this.sessionIds.get(packageName);
                if (num != null) {
                    int iIntValue = num.intValue();
                    if (this.DEBUG) {
                        Log.v(this.TAG, "start installing " + packageName);
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new UpdateManager$install$$inlined$let$lambda$1(null, this, packageName), 2, null);
                    PackageManager packageManager = this.context.getPackageManager();
                    Intrinsics.checkExpressionValueIsNotNull(packageManager, "context.packageManager");
                    PackageInstaller packageInstaller = packageManager.getPackageInstaller();
                    Intrinsics.checkExpressionValueIsNotNull(packageInstaller, "context.packageManager.packageInstaller");
                    PackageInstaller.Session sessionOpenSession = packageInstaller.openSession(iIntValue);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0, new Intent(this.ACTION_INSTALL_PROGRESS), 67108864);
                    Intrinsics.checkExpressionValueIsNotNull(pendingIntent, "pendingIntent");
                    sessionOpenSession.commit(pendingIntent.getIntentSender());
                    return;
                }
                throw new RuntimeException("No session found for package " + packageName);
            }
            throw new UnsupportedOperationException("Android version " + Build.VERSION.SDK_INT + " does not supports that feature");
        } catch (Exception e) {
            if (this.DEBUG) {
                Log.w(this.TAG, "exception during install", e);
            }
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02702(packageName, e, null), 2, null);
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.updater.UpdateManager$install$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.updater.UpdateManager$install$2", f = "UpdateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02702 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Exception $e;
        final /* synthetic */ String $packageName;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02702(String str, Exception exc, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
            this.$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02702 c02702 = UpdateManager.this.new C02702(this.$packageName, this.$e, completion);
            c02702.p$ = (CoroutineScope) obj;
            return c02702;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02702) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Iterator it = UpdateManager.this.getSafeListeners().iterator();
            while (it.hasNext()) {
                ((IUpdateManagerListener) it.next()).onInstallFinished(this.$packageName, -1, this.$e);
            }
            return Unit.INSTANCE;
        }
    }

    public final void checkUpdates(final String updateFileUrl) {
        Intrinsics.checkParameterIsNotNull(updateFileUrl, "updateFileUrl");
        Intent intent = new Intent();
        intent.setAction(UpdateManagerKt.ACTION_NOTIFY_UPDATE_CHECK);
        this.context.sendBroadcast(intent);
        RequestManager.getJSONObject$default(this.requestManager, 0, new URL(updateFileUrl), null, 4, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.farkit.updater.UpdateManager.checkUpdates.1
            @Override // io.reactivex.functions.Function
            public final Maybe<? extends List<BundleItem>> apply(JSONObject json) throws JSONException {
                Intrinsics.checkParameterIsNotNull(json, "json");
                int i = json.getInt("version");
                if (i == 2) {
                    JSONArray jSONArray = json.getJSONArray("bundles");
                    ArrayList arrayList = new ArrayList();
                    int length = jSONArray.length();
                    int i2 = 0;
                    while (i2 < length) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("criteria");
                        Map<String, Object> mapJsonObjectToMap = jSONObjectOptJSONObject != null ? JsonKt.jsonObjectToMap(jSONObjectOptJSONObject) : null;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String strOptString = jSONObject.optString("expirationDate", null);
                        Date date = strOptString != null ? simpleDateFormat.parse(strOptString) : null;
                        int iOptInt = jSONObject.optInt("priority", Integer.MAX_VALUE);
                        ArrayList arrayList2 = new ArrayList();
                        JSONArray jSONArray2 = jSONObject.getJSONArray("apks");
                        int length2 = jSONArray2.length();
                        int i3 = 0;
                        while (i3 < length2) {
                            JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                            String title = jSONObject2.getString("title");
                            JSONArray jSONArray3 = jSONArray;
                            String packageName = jSONObject2.getString("packageName");
                            int i4 = length;
                            JSONArray jSONArray4 = jSONArray2;
                            String url = jSONObject2.getString(ImagesContract.URL);
                            int i5 = length2;
                            int i6 = i2;
                            String versionName = jSONObject2.getString("versionName");
                            ArrayList arrayList3 = arrayList;
                            int i7 = jSONObject2.getInt("versionCode");
                            Map<String, Object> map = mapJsonObjectToMap;
                            boolean zOptBoolean = jSONObject2.optBoolean("forced");
                            try {
                                PackageInfo packageInfo = UpdateManager.this.getContext().getPackageManager().getPackageInfo(packageName, 0);
                                if (packageInfo.versionCode >= i7) {
                                    if (UpdateManager.this.DEBUG) {
                                        Log.v(UpdateManager.this.TAG, "package \"" + packageName + "\" is up-to-date (version code " + packageInfo.versionCode + " >= " + i7 + ')');
                                    }
                                } else {
                                    Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                    Intrinsics.checkExpressionValueIsNotNull(packageName, "packageName");
                                    Intrinsics.checkExpressionValueIsNotNull(url, "url");
                                    Intrinsics.checkExpressionValueIsNotNull(versionName, "versionName");
                                    arrayList2.add(new UpdateItem(title, packageName, url, versionName, i7, null, zOptBoolean, null, 160, null));
                                }
                            } catch (Exception unused) {
                            }
                            i3++;
                            jSONArray = jSONArray3;
                            length = i4;
                            jSONArray2 = jSONArray4;
                            length2 = i5;
                            i2 = i6;
                            arrayList = arrayList3;
                            mapJsonObjectToMap = map;
                        }
                        JSONArray jSONArray5 = jSONArray;
                        ArrayList arrayList4 = arrayList;
                        int i8 = length;
                        int i9 = i2;
                        Map<String, Object> map2 = mapJsonObjectToMap;
                        if (!arrayList2.isEmpty()) {
                            arrayList = arrayList4;
                            arrayList.add(new BundleItem(date, map2, arrayList2, iOptInt));
                        } else {
                            arrayList = arrayList4;
                        }
                        i2 = i9 + 1;
                        jSONArray = jSONArray5;
                        length = i8;
                    }
                    if (arrayList.isEmpty()) {
                        return Maybe.empty();
                    }
                    return Maybe.just(arrayList);
                }
                throw new IllegalArgumentException("unknown update file verison " + i);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<? extends BundleItem>>() { // from class: com.dotscreen.farkit.updater.UpdateManager.checkUpdates.2
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(List<? extends BundleItem> list) {
                accept2((List<BundleItem>) list);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(List<BundleItem> list) {
                Log.d(UpdateManager.this.TAG, "checkUpdates: done = " + list);
                UpdateManager.this.setBundleItems(list);
                Intent intent2 = new Intent();
                intent2.setAction(UpdateManagerKt.ACTION_NOTIFY_NEXT_CHECK);
                UpdateManager.this.getContext().sendBroadcast(intent2);
                for (IUpdateManagerListener iUpdateManagerListener : UpdateManager.this.getSafeListeners()) {
                    List<BundleItem> list2 = UpdateManager.this.bundleItems;
                    if (list2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iUpdateManagerListener.onUpdatesAvailable(list2);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.farkit.updater.UpdateManager.checkUpdates.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable error) {
                Log.w(UpdateManager.this.TAG, "checkUpdates: error ", error);
                Intent intent2 = new Intent();
                intent2.setAction(UpdateManagerKt.ACTION_NOTIFY_CHECK_FAILED);
                UpdateManager.this.getContext().sendBroadcast(intent2);
                for (IUpdateManagerListener iUpdateManagerListener : UpdateManager.this.getSafeListeners()) {
                    Intrinsics.checkExpressionValueIsNotNull(error, "error");
                    iUpdateManagerListener.onCheckFailed(error, updateFileUrl);
                }
            }
        }, new Action() { // from class: com.dotscreen.farkit.updater.UpdateManager.checkUpdates.4
            @Override // io.reactivex.functions.Action
            public final void run() {
                Log.w(UpdateManager.this.TAG, "checkUpdates: nothing");
                UpdateManager.this.setBundleItems(CollectionsKt.emptyList());
                Intent intent2 = new Intent();
                intent2.setAction(UpdateManagerKt.ACTION_NOTIFY_NEXT_CHECK);
                UpdateManager.this.getContext().sendBroadcast(intent2);
                for (IUpdateManagerListener iUpdateManagerListener : UpdateManager.this.getSafeListeners()) {
                    List<BundleItem> list = UpdateManager.this.bundleItems;
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    iUpdateManagerListener.onUpdatesAvailable(list);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: UpdateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0083\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/farkit/updater/UpdateManager$InstallSessionReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/farkit/updater/UpdateManager;)V", "onReceive", "", "ctx", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "farkit_release"}, k = 1, mv = {1, 1, 16})
    final class InstallSessionReceiver extends BroadcastReceiver {
        public InstallSessionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context ctx, Intent intent) {
            String action;
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            if (UpdateManager.this.DEBUG) {
                Log.v(UpdateManager.this.TAG, "InstallSessionReceiver: action = " + action);
            }
            Bundle extras = intent.getExtras();
            Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("android.content.pm.extra.SESSION_ID")) : null;
            Map map = UpdateManager.this.sessionIds;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator it = map.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                if (numValueOf != null && ((Number) entry.getValue()).intValue() == numValueOf.intValue()) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            LinkedHashMap linkedHashMap2 = linkedHashMap;
            ArrayList arrayList = new ArrayList(linkedHashMap2.size());
            Iterator it2 = linkedHashMap2.entrySet().iterator();
            while (it2.hasNext()) {
                arrayList.add((String) ((Map.Entry) it2.next()).getKey());
            }
            String str = (String) CollectionsKt.firstOrNull((List) arrayList);
            Bundle extras2 = intent.getExtras();
            Integer numValueOf2 = extras2 != null ? Integer.valueOf(extras2.getInt("android.content.pm.extra.STATUS")) : null;
            Bundle extras3 = intent.getExtras();
            String string = extras3 != null ? extras3.getString("android.content.pm.extra.STATUS_MESSAGE") : null;
            if (UpdateManager.this.DEBUG) {
                Log.v(UpdateManager.this.TAG, "                        packageName = " + str);
            }
            if (UpdateManager.this.DEBUG) {
                Log.v(UpdateManager.this.TAG, "                        status = " + numValueOf2);
            }
            if (UpdateManager.this.DEBUG) {
                Log.v(UpdateManager.this.TAG, "                        message = " + string);
            }
            if (numValueOf2 != null && numValueOf2.intValue() == -1) {
                if (UpdateManager.this.DEBUG) {
                    Log.v(UpdateManager.this.TAG, "InstallSessionReceiver: Pending user action");
                }
                if (UpdateManager.this.DEBUG) {
                    String str2 = UpdateManager.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("InstallSessionReceiver: intent = ");
                    Bundle extras4 = intent.getExtras();
                    sb.append(extras4 != null ? extras4.get("android.intent.extra.INTENT") : null);
                    Log.v(str2, sb.toString());
                }
                Bundle extras5 = intent.getExtras();
                Object obj = extras5 != null ? extras5.get("android.intent.extra.INTENT") : null;
                Intent intent2 = (Intent) (obj instanceof Intent ? obj : null);
                if (intent2 != null) {
                    UpdateManager.this.getContext().startActivity(intent2);
                    return;
                }
                return;
            }
            if (numValueOf2 != null && numValueOf2.intValue() == 0) {
                if (UpdateManager.this.DEBUG) {
                    Log.v(UpdateManager.this.TAG, "InstallSessionReceiver: Success");
                }
                Map map2 = UpdateManager.this.sessionIds;
                if (map2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
                }
                TypeIntrinsics.asMutableMap(map2).remove(str);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new UpdateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$1(str, null, this, intent), 2, null);
                return;
            }
            if ((numValueOf2 == null || numValueOf2.intValue() != 1) && ((numValueOf2 == null || numValueOf2.intValue() != 3) && ((numValueOf2 == null || numValueOf2.intValue() != 2) && ((numValueOf2 == null || numValueOf2.intValue() != 5) && ((numValueOf2 == null || numValueOf2.intValue() != 7) && ((numValueOf2 == null || numValueOf2.intValue() != 4) && (numValueOf2 == null || numValueOf2.intValue() != 6))))))) {
                Log.w(UpdateManager.this.TAG, "InstallSessionReceiver: Unrecognized status received from installer: " + numValueOf2);
                return;
            }
            if (UpdateManager.this.DEBUG) {
                Log.w(UpdateManager.this.TAG, "InstallSessionReceiver: Failure: " + numValueOf2 + " - " + string);
            }
            Map map3 = UpdateManager.this.sessionIds;
            if (map3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
            }
            TypeIntrinsics.asMutableMap(map3).remove(str);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new UpdateManager$InstallSessionReceiver$onReceive$$inlined$let$lambda$2(str, numValueOf2, string, null, this, intent), 2, null);
        }
    }
}
