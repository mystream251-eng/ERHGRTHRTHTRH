package com.dotscreen.portal.ads;

import android.content.Context;
import android.util.Log;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.utils.String_ExtensionKt;
import com.dotscreen.tv.common.bokit.RightVRequestManager;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: AdRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0083\u0001\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001426\u0010\u0015\u001a2\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000e0\u00162!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u000e0\u001dH\u0007J \u0001\u0010 \u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\"2K\u0010\u0015\u001aG\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000e0#2!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u000e0\u001dH\u0007Jk\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u001026\u0010\u0015\u001a2\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000e0\u00162!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u000e0\u001dH\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006'"}, d2 = {"Lcom/dotscreen/portal/ads/AdsHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "requestManager", "Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "getRequestManager", "()Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "setRequestManager", "(Lcom/dotscreen/tv/common/bokit/RightVRequestManager;)V", "getFloatingAdsOfPage", "", "className", "", "classHashCode", "", "voAdUnitIds", "", "onSuccess", "Lkotlin/Function2;", "Lcom/dotscreen/portal/ads/Ad;", "Lkotlin/ParameterName;", "name", "ad", "message", "onFailure", "Lkotlin/Function1;", "", "error", "getNativeAdsOfPage", "nativeAdUnitIdsMap", "", "Lkotlin/Function3;", "dsAdUnitId", "requestAds", "adUnitId", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class AdsHelper {
    private final Context context;
    private RightVRequestManager requestManager;

    public AdsHelper(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        RightVRequestManager rightVRequestManager = CommonUtils.getRightVRequestManager(context);
        Intrinsics.checkExpressionValueIsNotNull(rightVRequestManager, "CommonUtils.getRightVRequestManager(context)");
        this.requestManager = rightVRequestManager;
    }

    public final Context getContext() {
        return this.context;
    }

    public final RightVRequestManager getRequestManager() {
        return this.requestManager;
    }

    public final void setRequestManager(RightVRequestManager rightVRequestManager) {
        Intrinsics.checkParameterIsNotNull(rightVRequestManager, "<set-?>");
        this.requestManager = rightVRequestManager;
    }

    public final void getNativeAdsOfPage(final String className, int classHashCode, Map<String, String> nativeAdUnitIdsMap, final Function3<? super String, ? super Ad, ? super String, Unit> onSuccess, final Function1<? super Throwable, Unit> onFailure) {
        Intrinsics.checkParameterIsNotNull(className, "className");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onFailure, "onFailure");
        if (nativeAdUnitIdsMap == null || nativeAdUnitIdsMap.isEmpty()) {
            onFailure.invoke(new Throwable("[GET ADS FOR PAGE][NATIVE][" + className + "] No ads"));
            return;
        }
        for (final String str : nativeAdUnitIdsMap.keySet()) {
            if (nativeAdUnitIdsMap.get(str) == null) {
                onFailure.invoke(new Throwable("[GET ADS FOR PAGE][NATIVE][" + className + "] No matching VO adUnitId for [DS-" + str + ']'));
            } else {
                String str2 = nativeAdUnitIdsMap.get(str);
                if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                final String str3 = str2;
                requestAds(str3, new Function2<Ad, String, Unit>() { // from class: com.dotscreen.portal.ads.AdsHelper.getNativeAdsOfPage.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Ad ad, String str4) {
                        invoke2(ad, str4);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Ad ad, String message) {
                        Intrinsics.checkParameterIsNotNull(ad, "ad");
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        if (!Intrinsics.areEqual(ad.getAdUnitId(), str3)) {
                            onFailure.invoke(new Throwable("[GET ADS FOR PAGE][NATIVE][" + className + "] VO-" + ad.getAdUnitId() + " (from response) != VO-" + str3 + " (from config)"));
                            return;
                        }
                        if (ad.getImageResources().isEmpty()) {
                            onFailure.invoke(new Throwable("[GET ADS FOR PAGE][NATIVE][" + className + "] No imageResources were provided for " + ad.getAdUnitId()));
                            return;
                        }
                        if (ad.getImageResources().size() > 2) {
                            onFailure.invoke(new Throwable("[GET ADS FOR PAGE][NATIVE][" + className + "] Too many imageResources in VO-" + ad.getAdUnitId() + " (found " + ad.getImageResources().size() + " but limit is 2)"));
                            return;
                        }
                        onSuccess.invoke(str, ad, message);
                    }
                }, new Function1<Throwable, Unit>() { // from class: com.dotscreen.portal.ads.AdsHelper.getNativeAdsOfPage.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable error) {
                        Intrinsics.checkParameterIsNotNull(error, "error");
                        onFailure.invoke(error);
                    }
                });
            }
        }
    }

    public final void getFloatingAdsOfPage(final String className, int classHashCode, List<String> voAdUnitIds, final Function2<? super Ad, ? super String, Unit> onSuccess, final Function1<? super Throwable, Unit> onFailure) {
        Intrinsics.checkParameterIsNotNull(className, "className");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onFailure, "onFailure");
        if (voAdUnitIds == null || voAdUnitIds.isEmpty()) {
            onFailure.invoke(new Throwable("[GET ADS FOR PAGE][FLOATING][" + className + "] No ads"));
            return;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        for (final String str : voAdUnitIds) {
            String str2 = str;
            requestAds(!StringsKt.contains$default((CharSequence) str2, (CharSequence) ":", false, 2, (Object) null) ? str : (String) StringsKt.split$default((CharSequence) str2, new String[]{":"}, false, 0, 6, (Object) null).get(1), new Function2<Ad, String, Unit>() { // from class: com.dotscreen.portal.ads.AdsHelper.getFloatingAdsOfPage.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Ad ad, String str3) {
                    invoke2(ad, str3);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v24, types: [T, java.lang.String] */
                /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
                /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.String] */
                /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.String] */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Ad ad, String message) {
                    Intrinsics.checkParameterIsNotNull(ad, "ad");
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    objectRef.element = (String) 0;
                    objectRef2.element = str;
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) ":", false, 2, (Object) null)) {
                        List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
                        objectRef.element = (String) listSplit$default.get(0);
                        objectRef2.element = (String) listSplit$default.get(1);
                    }
                    Log.d("////////////////////", str + ", " + ((String) objectRef.element) + ", " + ((String) objectRef2.element) + ", " + ad.getAdUnitId());
                    if (!Intrinsics.areEqual(ad.getAdUnitId(), !StringsKt.contains$default((CharSequence) str, (CharSequence) ":", false, 2, (Object) null) ? str : (String) objectRef2.element)) {
                        Function1 function1 = onFailure;
                        StringBuilder sb = new StringBuilder();
                        sb.append("[GET ADS FOR PAGE][FLOATING][");
                        sb.append(className);
                        sb.append("] VO-");
                        sb.append(ad.getAdUnitId());
                        sb.append(" (from response) != VO-");
                        sb.append(((String) objectRef.element) == null ? str : (String) objectRef2.element);
                        sb.append(" (from config)");
                        function1.invoke(new Throwable(sb.toString()));
                        return;
                    }
                    if (ad.getImageResources().isEmpty()) {
                        onFailure.invoke(new Throwable("[GET ADS FOR PAGE][FLOATING][" + className + "] No imageResources were provided for " + ad.getAdUnitId()));
                        return;
                    }
                    if (ad.getImageResources().size() > 2) {
                        onFailure.invoke(new Throwable("[GET ADS FOR PAGE][FLOATING][" + className + "] Too many imageResources in VO-" + ad.getAdUnitId() + " (found " + ad.getImageResources().size() + " but limit is 2)"));
                        return;
                    }
                    ad.setDsAdUnitId(str);
                    onSuccess.invoke(ad, message);
                }
            }, new Function1<Throwable, Unit>() { // from class: com.dotscreen.portal.ads.AdsHelper.getFloatingAdsOfPage.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable error) {
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    onFailure.invoke(error);
                }
            });
        }
    }

    private final void requestAds(final String adUnitId, final Function2<? super Ad, ? super String, Unit> onSuccess, final Function1<? super Throwable, Unit> onFailure) {
        Maybe<User> activeUser;
        Maybe<R> map;
        Single singleFlatMapSingle;
        Single singleSubscribeOn;
        Single singleObserveOn;
        Observable observable;
        UserService userService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getUserService();
        if (userService == null || (activeUser = userService.getActiveUser()) == null || (map = activeUser.map(new Function<T, R>() { // from class: com.dotscreen.portal.ads.AdsHelper.requestAds.1
            @Override // io.reactivex.functions.Function
            public final RightVUser apply(User it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return (RightVUser) it;
            }
        })) == 0 || (singleFlatMapSingle = map.flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ads.AdsHelper.requestAds.2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(RightVUser user) {
                boolean z;
                Intrinsics.checkParameterIsNotNull(user, "user");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (TargetedAdsConfigProvider.INSTANCE.shouldOverrideRequestParams()) {
                    TargetAdvertisingConfig.AdRequestParamOverride adRequestParamOverride = TargetedAdsConfigProvider.INSTANCE.getAdRequestParamOverride();
                    if (adRequestParamOverride == null) {
                        Intrinsics.throwNpe();
                    }
                    String accountId = adRequestParamOverride.getAccountId();
                    if (accountId == null) {
                        accountId = user.getId();
                    }
                    linkedHashMap.put("accountId", accountId);
                    String terminalModelId = adRequestParamOverride.getTerminalModelId();
                    linkedHashMap.put("terminalModelId", terminalModelId != null ? terminalModelId : "AndroidTV");
                    String adUnitId2 = adRequestParamOverride.getAdUnitId();
                    if (adUnitId2 == null) {
                        adUnitId2 = adUnitId;
                    }
                    linkedHashMap.put("adUnitId", adUnitId2);
                    String profileId = adRequestParamOverride.getProfileId();
                    if (profileId == null) {
                        profileId = user.getCompassId();
                    }
                    linkedHashMap.put(ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, profileId);
                    z = true;
                } else {
                    linkedHashMap.put("accountId", user.getId());
                    linkedHashMap.put("terminalModelId", "AndroidTV");
                    linkedHashMap.put("adUnitId", adUnitId);
                    linkedHashMap.put(ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, user.getCompassId());
                    z = false;
                }
                String adProviderBaseURL = TargetedAdsConfigProvider.INSTANCE.getAdProviderBaseURL();
                String str = adProviderBaseURL + '?' + String_ExtensionKt.requestParamsToURLString(linkedHashMap);
                DSLog dSLog = DSLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("[REQUEST AD][VO-");
                sb.append(adUnitId);
                sb.append("] fetch ");
                sb.append(z ? "(debug) " : "");
                sb.append(' ');
                sb.append(str);
                dSLog.adLogM(sb.toString());
                RightVRequestManager requestManager = AdsHelper.this.getRequestManager();
                if (adProviderBaseURL == null) {
                    Intrinsics.throwNpe();
                }
                return RightVRequestManager.getAdsJSONObject$default(requestManager, adProviderBaseURL, 0, linkedHashMap, 2, null);
            }
        })) == null || (singleSubscribeOn = singleFlatMapSingle.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (observable = singleObserveOn.toObservable()) == null) {
            return;
        }
        observable.subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.ads.AdsHelper.requestAds.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                System.out.println(jSONObject);
                Object objFromJson = new Gson().fromJson(jSONObject.toString(), (Class<Object>) Ad.class);
                Intrinsics.checkExpressionValueIsNotNull(objFromJson, "Gson().fromJson(response…String(), Ad::class.java)");
                onSuccess.invoke((Ad) objFromJson, "[REQUEST AD][VO-" + adUnitId + "] -> OK");
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ads.AdsHelper.requestAds.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                String string = th.toString();
                if (th instanceof JsonSyntaxException) {
                    string = "Malformed json";
                }
                onFailure.invoke(new Throwable("[REQUEST AD][VO-" + adUnitId + "] -> KO | " + string, th.getCause()));
            }
        });
    }
}
