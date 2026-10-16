package com.dotscreen.portal.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import com.dotscreen.portal.model.ApplicationWidget;
import com.dotscreen.portal.ui.screens.Activity_StartIntentKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ApplicationsManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016J\u0016\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010 \u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016J\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001e0\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \r*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/dotscreen/portal/utils/ApplicationsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "DEBUG", "", "OUT_OF_THE_BOX_APP_ORDER", "", "", "PREF_KEY_APP_ORDER", "PREF_NAME", "TAG", "kotlin.jvm.PlatformType", "value", "appOrder", "getAppOrder", "()Ljava/util/List;", "setAppOrder", "(Ljava/util/List;)V", "onApplicationsOrderChangedListeners", "", "Lcom/dotscreen/portal/utils/ApplicationsManager$OnApplicationsOrderChangedListener;", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "launchApplication", "activity", "Landroid/app/Activity;", "widget", "Lcom/dotscreen/portal/model/ApplicationWidget;", "registerAppLaunched", "removeListener", "sortApplicationWidget", "apps", "OnApplicationsOrderChangedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ApplicationsManager {
    private final boolean DEBUG;
    private final List<String> OUT_OF_THE_BOX_APP_ORDER;
    private final String PREF_KEY_APP_ORDER;
    private final String PREF_NAME;
    private final String TAG;
    private final Context context;
    private final Set<OnApplicationsOrderChangedListener> onApplicationsOrderChangedListeners;

    /* JADX INFO: compiled from: ApplicationsManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/portal/utils/ApplicationsManager$OnApplicationsOrderChangedListener;", "", "onApplicationsOrderChanged", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnApplicationsOrderChangedListener {
        void onApplicationsOrderChanged();
    }

    public ApplicationsManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = ApplicationsManager.class.getSimpleName();
        this.DEBUG = true;
        this.PREF_NAME = "application_manager";
        this.PREF_KEY_APP_ORDER = "app_order";
        this.OUT_OF_THE_BOX_APP_ORDER = CollectionsKt.listOf((Object[]) new String[]{"com.android.vending", "com.google.android.videos", "com.google.android.youtube.tv", "com.google.android.music", "com.google.android.youtube.tvmusic", GooglePlayServicesUtilLight.GOOGLE_PLAY_GAMES_PACKAGE});
        this.onApplicationsOrderChangedListeners = new LinkedHashSet();
    }

    private final List<String> getAppOrder() {
        ComponentName component;
        String string = this.context.getSharedPreferences(this.PREF_NAME, 0).getString(this.PREF_KEY_APP_ORDER, null);
        if (this.DEBUG) {
            Log.d(this.TAG, "get appOrder: pref = " + string);
        }
        if (string == null) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = this.OUT_OF_THE_BOX_APP_ORDER.iterator();
            while (it.hasNext()) {
                Intent leanbackLaunchIntentForPackage = this.context.getPackageManager().getLeanbackLaunchIntentForPackage(it.next());
                if (leanbackLaunchIntentForPackage != null && (component = leanbackLaunchIntentForPackage.getComponent()) != null) {
                    String strFlattenToString = component.flattenToString();
                    Intrinsics.checkExpressionValueIsNotNull(strFlattenToString, "it.flattenToString()");
                    arrayList.add(strFlattenToString);
                }
            }
            if (this.DEBUG) {
                Log.d(this.TAG, "              return default " + arrayList);
            }
            return arrayList;
        }
        List<String> listSplit$default = StringsKt.split$default((CharSequence) string, new String[]{";"}, false, 0, 6, (Object) null);
        if (this.DEBUG) {
            Log.d(this.TAG, "              return " + listSplit$default);
        }
        return listSplit$default;
    }

    private final void setAppOrder(List<String> list) {
        Iterator<T> it = list.iterator();
        if (!it.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = ((String) next) + ';' + ((String) it.next());
        }
        this.context.getSharedPreferences(this.PREF_NAME, 0).edit().putString(this.PREF_KEY_APP_ORDER, (String) next).apply();
        Iterator<OnApplicationsOrderChangedListener> it2 = this.onApplicationsOrderChangedListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onApplicationsOrderChanged();
        }
    }

    public final List<ApplicationWidget> sortApplicationWidget(List<ApplicationWidget> apps) {
        ComponentName component;
        Intrinsics.checkParameterIsNotNull(apps, "apps");
        List mutableList = CollectionsKt.toMutableList((Collection) getAppOrder());
        List mutableList2 = CollectionsKt.toMutableList((Collection) apps);
        int size = mutableList.size();
        for (int i = 0; i < size; i++) {
            int size2 = mutableList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String str = (String) mutableList.get(i);
                Intent intent = ((ApplicationWidget) mutableList2.get(i2)).getIntent();
                if (Intrinsics.areEqual(str, (intent == null || (component = intent.getComponent()) == null) ? null : component.flattenToString())) {
                    ApplicationWidget applicationWidget = (ApplicationWidget) mutableList2.remove(i2);
                    if (i < mutableList2.size()) {
                        mutableList2.add(i, applicationWidget);
                    } else {
                        mutableList2.add(applicationWidget);
                    }
                }
            }
        }
        return CollectionsKt.toList(mutableList2);
    }

    private final void registerAppLaunched(ApplicationWidget widget) {
        ComponentName component;
        ComponentName component2;
        if (this.DEBUG) {
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("registerAppLaunched : ");
            Intent intent = widget.getIntent();
            sb.append((intent == null || (component2 = intent.getComponent()) == null) ? null : component2.flattenToString());
            Log.d(str, sb.toString());
        }
        List<String> mutableList = CollectionsKt.toMutableList((Collection) getAppOrder());
        Intent intent2 = widget.getIntent();
        if (intent2 != null && (component = intent2.getComponent()) != null) {
            mutableList.remove(component.flattenToString());
            String strFlattenToString = component.flattenToString();
            Intrinsics.checkExpressionValueIsNotNull(strFlattenToString, "it.flattenToString()");
            mutableList.add(0, strFlattenToString);
        }
        setAppOrder(mutableList);
    }

    public final void launchApplication(Activity activity, ApplicationWidget widget) {
        String str;
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(widget, "widget");
        try {
            Intent intent = widget.getIntent();
            if (intent == null) {
                PackageManager packageManager = activity.getPackageManager();
                intent = packageManager != null ? packageManager.getLeanbackLaunchIntentForPackage(widget.getPackageName()) : null;
            }
            if (intent != null) {
                registerAppLaunched(widget);
                if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.MAIN") && (str = intent.getPackage()) != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "netflix", false, 2, (Object) null)) {
                    Activity_StartIntentKt.startIntent(activity, CommonUtils.patchInteractiveIntent(Intent.parseUri("http://www.netflix.com/browse?iid=0f42f23c", 0)));
                    return;
                } else {
                    Activity_StartIntentKt.startIntent(activity, intent);
                    return;
                }
            }
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse("market://details?id=" + widget.getPackageName()));
            Activity_StartIntentKt.startIntent(activity, intent2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void addListener(OnApplicationsOrderChangedListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onApplicationsOrderChangedListeners.add(listener);
    }

    public final void removeListener(OnApplicationsOrderChangedListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onApplicationsOrderChangedListeners.remove(listener);
    }
}
