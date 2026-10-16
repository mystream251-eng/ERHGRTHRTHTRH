package com.dotscreen.tv.common.bokit;

import android.content.Context;
import android.util.Log;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.Classifier;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.Person;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.TVSeries;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.broadpeak.smartlib.ad.AdInteractionType;

/* JADX INFO: compiled from: MegacableVODCatalogService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\r\u001a\u00020\u000eJP\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\n2\u0006\u0010\u0011\u001a\u00020\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0017J0\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000eJ&\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/dotscreen/tv/common/bokit/MegacableVODCatalogService;", "Lcom/dotscreen/bokit/services/VODCatalogService;", "context", "Landroid/content/Context;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "requestManager", "Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "(Landroid/content/Context;Lcom/dotscreen/bokit/services/BOService;Lcom/dotscreen/tv/common/bokit/RightVRequestManager;)V", "compassSearch", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/Content;", SearchIntents.EXTRA_QUERY, "", "getRecommendations", "Lcom/dotscreen/bokit/model/BrowsableItem;", "blend", "contentId", "contentType", "maxResult", "", "anonymous", "", "isAdult", "registerClick", "Lio/reactivex/Completable;", "item", "type", "bannerClick", "registerClickUpdated", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegacableVODCatalogService extends VODCatalogService {
    private final Context context;
    private final RightVRequestManager requestManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MegacableVODCatalogService(Context context, BOService boService, RightVRequestManager requestManager) {
        super(boService);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(requestManager, "requestManager");
        this.context = context;
        this.requestManager = requestManager;
    }

    public final Single<ItemCollection<BrowsableItem>> getRecommendations(String blend, String contentId, String contentType, int maxResult, boolean anonymous, boolean isAdult) {
        Intrinsics.checkParameterIsNotNull(blend, "blend");
        UserService userService = getBoService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        Maybe<User> activeUser = userService.getActiveUser();
        LiveTVService liveTVService = getBoService().getLiveTVService();
        if (liveTVService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
        Single<ItemCollection<BrowsableItem>> singleFlatMapSingle = Maybe.zip(activeUser, ((MegacableLiveTVService) liveTVService).getModelExternalId(), new C05631(maxResult, blend, contentType, contentId, isAdult, anonymous)).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.getRecommendations.2
            @Override // io.reactivex.functions.Function
            public final Single<ItemCollection<BrowsableItem>> apply(Single<ItemCollection<BrowsableItem>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMapSingle, "Maybe.zip(boService.user…  }).flatMapSingle { it }");
        return singleFlatMapSingle;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableVODCatalogService$getRecommendations$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MegacableVODCatalogService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "kotlin.jvm.PlatformType", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/bokit/model/User;", "modelexternalId", "", "apply"}, k = 3, mv = {1, 1, 16})
    static final class C05631<T1, T2, R> implements BiFunction<User, String, Single<ItemCollection<BrowsableItem>>> {
        final /* synthetic */ boolean $anonymous;
        final /* synthetic */ String $blend;
        final /* synthetic */ String $contentId;
        final /* synthetic */ String $contentType;
        final /* synthetic */ boolean $isAdult;
        final /* synthetic */ int $maxResult;

        C05631(int i, String str, String str2, String str3, boolean z, boolean z2) {
            this.$maxResult = i;
            this.$blend = str;
            this.$contentType = str2;
            this.$contentId = str3;
            this.$isAdult = z;
            this.$anonymous = z2;
        }

        @Override // io.reactivex.functions.BiFunction
        public final Single<ItemCollection<BrowsableItem>> apply(User user, String modelexternalId) {
            Intrinsics.checkParameterIsNotNull(user, "user");
            Intrinsics.checkParameterIsNotNull(modelexternalId, "modelexternalId");
            Map mapMutableMapOf = MapsKt.mutableMapOf(new Pair("max_results", Integer.valueOf(this.$maxResult)), new Pair("blend", this.$blend));
            String str = this.$contentType;
            if (str != null) {
                if (str == null) {
                    str = "";
                }
                mapMutableMapOf.put("content_type", str);
            }
            if (this.$contentId != null) {
                mapMutableMapOf.put("params", "video_id:" + this.$contentId);
            }
            if (this.$isAdult) {
                mapMutableMapOf.put("categories", "Adultos02");
            } else {
                mapMutableMapOf.put("max_pr_level", 5);
            }
            if (Intrinsics.areEqual(MegacableVODCatalogService.this.context.getPackageName(), "com.dotscreen.megacable.mobile")) {
                mapMutableMapOf.put("device_models", modelexternalId);
            }
            return RightVRequestManager.getCompassJSONArray$default(MegacableVODCatalogService.this.requestManager, "GetVideoRecommendationList", 0, mapMutableMapOf, this.$anonymous, false, 18, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.getRecommendations.1.1
                @Override // io.reactivex.functions.Function
                public final Single<ItemCollection<BrowsableItem>> apply(final JSONArray response) {
                    Intrinsics.checkParameterIsNotNull(response, "response");
                    return Single.just(new ItemCollection(MegacableVODCatalogService.this.getBoService(), "recoVOD", "recoVOD", null, CollectionsKt.emptyList(), null, false, null, Single.just(Integer.valueOf(response.length())), new Function1<ItemSortingOrder, Observable<BrowsableItem>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.getRecommendations.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlin.jvm.functions.Function1
                        public final Observable<BrowsableItem> invoke(ItemSortingOrder it) throws JSONException {
                            String string;
                            C01801 c01801 = this;
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            ArrayList arrayList = new ArrayList();
                            int length = response.length();
                            int i = 0;
                            int i2 = 0;
                            while (i2 < length) {
                                JSONObject jSONObject = response.getJSONObject(i2);
                                ArrayList arrayList2 = new ArrayList();
                                JSONArray jSONArray = jSONObject.getJSONArray("ContentProperties");
                                int length2 = jSONArray.length();
                                for (int i3 = i; i3 < length2; i3++) {
                                    Object obj = jSONArray.get(i3);
                                    if (obj == null) {
                                        throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                                    }
                                    JSONObject jSONObject2 = (JSONObject) obj;
                                    String string2 = jSONObject2.getString("name");
                                    Intrinsics.checkExpressionValueIsNotNull(string2, "image.getString(\"name\")");
                                    ArtworkFormat rightVArtworkFormat$default = MegacableUtilsKt.getRightVArtworkFormat$default(string2, null, 2, null);
                                    String url = jSONObject2.getString("value");
                                    BOService boService = MegacableVODCatalogService.this.getBoService();
                                    Intrinsics.checkExpressionValueIsNotNull(url, "url");
                                    arrayList2.add(new RightVArtwork(boService, url, rightVArtworkFormat$default));
                                }
                                String str2 = (String) null;
                                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("availabilities");
                                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                                    JSONArray jSONArrayOptJSONArray2 = jSONArrayOptJSONArray.getJSONObject(i).optJSONArray("categories");
                                    int length3 = jSONArrayOptJSONArray2.length();
                                    for (int i4 = i; i4 < length3; i4++) {
                                        if (Intrinsics.areEqual(jSONArrayOptJSONArray2.getJSONObject(i4).optString("categoryId"), "amazon")) {
                                            str2 = "amazon";
                                            break;
                                        }
                                    }
                                }
                                String str3 = str2;
                                String recoContentType = jSONObject.getString("contentType");
                                if (Intrinsics.areEqual(recoContentType, "Season")) {
                                    string = "rightv:series:" + jSONObject.getString("externalSeriesId");
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("rightv:");
                                    Intrinsics.checkExpressionValueIsNotNull(recoContentType, "recoContentType");
                                    Locale locale = Locale.getDefault();
                                    Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
                                    if (recoContentType == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                                    }
                                    String lowerCase = recoContentType.toLowerCase(locale);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                                    sb.append(lowerCase);
                                    sb.append(':');
                                    sb.append(jSONObject.getString("externalContentId"));
                                    string = sb.toString();
                                }
                                BOService boService2 = MegacableVODCatalogService.this.getBoService();
                                String string3 = jSONObject.getString("name");
                                Intrinsics.checkExpressionValueIsNotNull(string3, "reco.getString(\"name\")");
                                String string4 = jSONObject.getString("type");
                                Intrinsics.checkExpressionValueIsNotNull(string4, "reco.getString(\"type\")");
                                arrayList.add(new RecoContent(boService2, string, string3, null, str3, string4, arrayList2, null, new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ParentalRating.INSTANCE.forName(MegacableVODCatalogService.this.getBoService(), "RATING:" + jSONObject.optInt("prLevel", 5)), 32767, null), false, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 3584, 0 == true ? 1 : 0));
                                i2++;
                                i = 0;
                                c01801 = this;
                            }
                            Observable<BrowsableItem> observableFromIterable = Observable.fromIterable(arrayList);
                            Intrinsics.checkExpressionValueIsNotNull(observableFromIterable, "Observable.fromIterable(vods)");
                            return observableFromIterable;
                        }
                    }, null, null, 3072, null));
                }
            });
        }
    }

    public static /* synthetic */ Completable registerClickUpdated$default(MegacableVODCatalogService megacableVODCatalogService, BrowsableItem browsableItem, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return megacableVODCatalogService.registerClickUpdated(browsableItem, str, str2);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    public final Completable registerClickUpdated(final BrowsableItem item, final String blend, String bannerClick) {
        String id;
        Maybe maybeJust;
        Intrinsics.checkParameterIsNotNull(item, "item");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (String) 0;
        if (item instanceof TVAiring) {
            maybeJust = ((TVAiring) item).getTvProgram().flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService$registerClickUpdated$itemIdMaybe$1
                @Override // io.reactivex.functions.Function
                public final Maybe<Map<String, Object>> apply(TVProgram it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getOffers().firstElement();
                }
            }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService$registerClickUpdated$itemIdMaybe$2
                @Override // io.reactivex.functions.Function
                public final String apply(Map<String, ? extends Object> offer) {
                    Intrinsics.checkParameterIsNotNull(offer, "offer");
                    Log.v("CompassAnalytics", "Offers of attached program => " + offer);
                    Object obj = offer.get("rightv");
                    if (!(obj instanceof Map)) {
                        obj = null;
                    }
                    Map map = (Map) obj;
                    if (map == null || map.get("program_external_id") == null) {
                        return "";
                    }
                    Object obj2 = map.get("program_external_id");
                    if (obj2 != null) {
                        return (String) obj2;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
            });
        } else {
            if (StringsKt.startsWith$default(item.getId(), "rightv:", false, 2, (Object) null)) {
                if (Intrinsics.areEqual((String) StringsKt.split$default((CharSequence) item.getId(), new String[]{":"}, false, 0, 6, (Object) null).get(1), "series")) {
                    objectRef.element = "series";
                    id = (String) StringsKt.split$default((CharSequence) item.getId(), new String[]{":"}, false, 0, 6, (Object) null).get(2);
                } else {
                    id = (String) StringsKt.split$default((CharSequence) item.getId(), new String[]{":"}, false, 0, 6, (Object) null).get(2);
                }
            } else {
                RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(item.getId());
                if ((rightvContentIdDataExtractContentId != null ? rightvContentIdDataExtractContentId.getContentId() : null) != null) {
                    RightvContentIdData rightvContentIdDataExtractContentId2 = MegacableUtilsKt.extractContentId(item.getId());
                    id = rightvContentIdDataExtractContentId2 != null ? rightvContentIdDataExtractContentId2.getContentId() : null;
                    if (id == null) {
                        Intrinsics.throwNpe();
                    }
                } else {
                    id = item.getId();
                }
            }
            maybeJust = Maybe.just(id);
        }
        Intrinsics.checkExpressionValueIsNotNull(maybeJust, "when (item) {\n          …)\n            }\n        }");
        Maybe maybe = maybeJust;
        LiveTVService liveTVService = getBoService().getLiveTVService();
        if (liveTVService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
        Completable completableDoOnError = Maybe.zip(maybe, ((MegacableLiveTVService) liveTVService).getModelExternalId(), new BiFunction<String, String, Single<JSONObject>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.registerClickUpdated.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.BiFunction
            public final Single<JSONObject> apply(String itemId, String modelExternalId) {
                Intrinsics.checkParameterIsNotNull(itemId, "itemId");
                Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("type", AdInteractionType.CLICK));
                if (Intrinsics.areEqual((String) objectRef.element, "series") || (item instanceof TVSeries)) {
                    mapMutableMapOf.put("external_series_id", itemId);
                } else {
                    mapMutableMapOf.put("external_content_id", itemId);
                }
                if (blend != null) {
                    mapMutableMapOf.put("recommended", true);
                    mapMutableMapOf.put("blend", blend);
                    BrowsableItem browsableItem = item;
                    if (browsableItem instanceof RecoContent) {
                        mapMutableMapOf.put("engine", ((RecoContent) browsableItem).getType());
                    } else if (browsableItem instanceof CatalogSection) {
                        mapMutableMapOf.put("engine", "vod_category_banner");
                    }
                }
                for (Map.Entry entry : mapMutableMapOf.entrySet()) {
                    Log.e("RegisterEvent", ((String) entry.getKey()) + " : " + entry.getValue());
                }
                return RightVRequestManager.getCompassJSONObject$default(MegacableVODCatalogService.this.requestManager, "RegisterEvent", 0, mapMutableMapOf, 2, null);
            }
        }).flatMapCompletable(new Function<Single<JSONObject>, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.registerClickUpdated.2
            @Override // io.reactivex.functions.Function
            public final Completable apply(Single<JSONObject> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.ignoreElement();
            }
        }).doOnTerminate(new Action() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.registerClickUpdated.3
            @Override // io.reactivex.functions.Action
            public final void run() {
                Log.d("CompassAnalytics", "RegisterEvent Done");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.registerClickUpdated.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.d("CompassAnalytics", "RegisterEvent Failed " + th.getMessage());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableDoOnError, "Maybe.zip(\n            i… Failed ${it.message}\") }");
        return completableDoOnError;
    }

    public static /* synthetic */ Completable registerClick$default(MegacableVODCatalogService megacableVODCatalogService, BrowsableItem browsableItem, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str = AdInteractionType.CLICK;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        return megacableVODCatalogService.registerClick(browsableItem, str, str2, str3);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    public final Completable registerClick(final BrowsableItem item, final String type, final String blend, String bannerClick) {
        String id;
        Maybe maybeJust;
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(type, "type");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (String) 0;
        if (item instanceof TVAiring) {
            maybeJust = ((TVAiring) item).getTvProgram().flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService$registerClick$itemIdMaybe$1
                @Override // io.reactivex.functions.Function
                public final Maybe<Map<String, Object>> apply(TVProgram it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getOffers().firstElement();
                }
            }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService$registerClick$itemIdMaybe$2
                @Override // io.reactivex.functions.Function
                public final String apply(Map<String, ? extends Object> offer) {
                    Intrinsics.checkParameterIsNotNull(offer, "offer");
                    Log.v("CompassAnalytics", "Offers of attached program => " + offer);
                    Object obj = offer.get("rightv");
                    if (!(obj instanceof Map)) {
                        obj = null;
                    }
                    Map map = (Map) obj;
                    if (map == null || map.get("program_external_id") == null) {
                        return "";
                    }
                    Object obj2 = map.get("program_external_id");
                    if (obj2 != null) {
                        return (String) obj2;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
            });
        } else {
            if (StringsKt.startsWith$default(item.getId(), "rightv:", false, 2, (Object) null)) {
                if (Intrinsics.areEqual((String) StringsKt.split$default((CharSequence) item.getId(), new String[]{":"}, false, 0, 6, (Object) null).get(1), "series")) {
                    objectRef.element = "series";
                    id = (String) StringsKt.split$default((CharSequence) item.getId(), new String[]{":"}, false, 0, 6, (Object) null).get(2);
                } else {
                    id = (String) StringsKt.split$default((CharSequence) item.getId(), new String[]{":"}, false, 0, 6, (Object) null).get(2);
                }
            } else {
                RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(item.getId());
                if ((rightvContentIdDataExtractContentId != null ? rightvContentIdDataExtractContentId.getContentId() : null) != null) {
                    RightvContentIdData rightvContentIdDataExtractContentId2 = MegacableUtilsKt.extractContentId(item.getId());
                    id = rightvContentIdDataExtractContentId2 != null ? rightvContentIdDataExtractContentId2.getContentId() : null;
                    if (id == null) {
                        Intrinsics.throwNpe();
                    }
                } else {
                    id = item.getId();
                }
            }
            maybeJust = Maybe.just(id);
        }
        Intrinsics.checkExpressionValueIsNotNull(maybeJust, "when (item) {\n          …)\n            }\n        }");
        Maybe maybe = maybeJust;
        LiveTVService liveTVService = getBoService().getLiveTVService();
        if (liveTVService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
        Completable completableDoOnError = Maybe.zip(maybe, ((MegacableLiveTVService) liveTVService).getModelExternalId(), new BiFunction<String, String, Single<JSONObject>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.registerClick.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.BiFunction
            public final Single<JSONObject> apply(String itemId, String modelExternalId) {
                Intrinsics.checkParameterIsNotNull(itemId, "itemId");
                Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("type", type), TuplesKt.to("device_model", modelExternalId));
                BrowsableItem browsableItem = item;
                if (browsableItem instanceof RecoContent) {
                    mapMutableMapOf.put("engine", ((RecoContent) browsableItem).getType());
                }
                if (item instanceof CatalogSection) {
                    mapMutableMapOf.put("recommended", false);
                    mapMutableMapOf.put("engine", "vod_category_banner");
                } else if (blend != null) {
                    mapMutableMapOf.put("recommended", true);
                    mapMutableMapOf.put("blend", blend);
                }
                if (item instanceof TVAiring) {
                    mapMutableMapOf.put("program_id", itemId);
                } else if (Intrinsics.areEqual((String) objectRef.element, "series") || (item instanceof TVSeries)) {
                    mapMutableMapOf.put("external_series_id", itemId);
                } else {
                    mapMutableMapOf.put("external_content_id", itemId);
                }
                for (Map.Entry entry : mapMutableMapOf.entrySet()) {
                    Log.e("RegisterEvent", ((String) entry.getKey()) + " : " + entry.getValue());
                }
                return RightVRequestManager.getCompassJSONObject$default(MegacableVODCatalogService.this.requestManager, "RegisterEvent", 0, mapMutableMapOf, 2, null);
            }
        }).flatMapCompletable(new Function<Single<JSONObject>, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.registerClick.2
            @Override // io.reactivex.functions.Function
            public final Completable apply(Single<JSONObject> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.ignoreElement();
            }
        }).doOnTerminate(new Action() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.registerClick.3
            @Override // io.reactivex.functions.Action
            public final void run() {
                Log.d("CompassAnalytics", "RegisterEvent Done");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.registerClick.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.d("CompassAnalytics", "RegisterEvent Failed " + th.getMessage());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableDoOnError, "Maybe.zip(\n            i… Failed ${it.message}\") }");
        return completableDoOnError;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableVODCatalogService$compassSearch$1, reason: invalid class name */
    /* JADX INFO: compiled from: MegacableVODCatalogService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/Content;", "kotlin.jvm.PlatformType", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/bokit/model/User;", "apply"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1<T, R> implements Function<T, R> {
        final /* synthetic */ String $query;

        AnonymousClass1(String str) {
            this.$query = str;
        }

        @Override // io.reactivex.functions.Function
        public final Single<ItemCollection<Content>> apply(User user) {
            Intrinsics.checkParameterIsNotNull(user, "user");
            return RightVRequestManager.getSearchJSONArray$default(MegacableVODCatalogService.this.requestManager, "SearchVideos", 0, MapsKt.mapOf(TuplesKt.to("content_type", "series,movie"), TuplesKt.to("text", this.$query), TuplesKt.to("max_results", 5), TuplesKt.to("max_pr_level", 5), TuplesKt.to("fuzzy", true)), 2, null).flatMap(new C01751());
        }

        /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableVODCatalogService$compassSearch$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: MegacableVODCatalogService.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/Content;", "kotlin.jvm.PlatformType", "results", "Lorg/json/JSONArray;", "apply"}, k = 3, mv = {1, 1, 16})
        static final class C01751<T, R> implements Function<T, SingleSource<? extends R>> {
            C01751() {
            }

            @Override // io.reactivex.functions.Function
            public final Single<ItemCollection<Content>> apply(JSONArray results) throws JSONException {
                Intrinsics.checkParameterIsNotNull(results, "results");
                ArrayList arrayList = new ArrayList();
                int length = results.length();
                for (int i = 0; i < length; i++) {
                    JSONObject result = results.getJSONObject(i);
                    if (Intrinsics.areEqual(result.getString("entityType"), ToolboxAnalyticsModuleKt.KEY_CONTENT)) {
                        Intrinsics.checkExpressionValueIsNotNull(result, "result");
                        arrayList.add(result);
                    }
                }
                return Single.just(new ItemCollection(MegacableVODCatalogService.this.getBoService(), "search_vod_" + AnonymousClass1.this.$query, "", null, null, null, false, null, Single.just(Integer.valueOf(results.length())), new C01761(arrayList), null, null, 3320, null));
            }

            /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableVODCatalogService$compassSearch$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: MegacableVODCatalogService.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/Content;", "kotlin.jvm.PlatformType", "it", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "invoke"}, k = 3, mv = {1, 1, 16})
            static final class C01761 extends Lambda implements Function1<ItemSortingOrder, Observable<Content>> {
                final /* synthetic */ List $obsSearchResults;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01761(List list) {
                    super(1);
                    this.$obsSearchResults = list;
                }

                @Override // kotlin.jvm.functions.Function1
                public final Observable<Content> invoke(ItemSortingOrder it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Observable<Content> observableFlatMap = Observable.fromIterable(this.$obsSearchResults).flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.compassSearch.1.1.1.1
                        @Override // io.reactivex.functions.Function
                        public final Observable<Content> apply(JSONObject it2) throws JSONException {
                            Intrinsics.checkParameterIsNotNull(it2, "it");
                            String string = it2.getString("contentType");
                            StringBuilder sb = new StringBuilder();
                            sb.append("rightv:");
                            sb.append(Intrinsics.areEqual(string, "season") ? "series" : "movie");
                            sb.append(':');
                            sb.append(it2.getString(Intrinsics.areEqual(string, "season") ? "seriesExternalId" : "externalId"));
                            String string2 = sb.toString();
                            Log.d("SEARCH", "Look for " + string2);
                            return Observable.zip(Observable.just(it2), MegacableVODCatalogService.this.itemById(string2).toObservable().onErrorReturnItem(new Content(MegacableVODCatalogService.this.getBoService(), "dummyItem", "dummyItem", null, null, null, new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null), false, null, null, 944, null)), new BiFunction<JSONObject, BrowsableItem, Content>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.compassSearch.1.1.1.1.1
                                @Override // io.reactivex.functions.BiFunction
                                public final Content apply(JSONObject resultJson, BrowsableItem item) throws JSONException {
                                    int durationInMinutes;
                                    Intrinsics.checkParameterIsNotNull(resultJson, "resultJson");
                                    Intrinsics.checkParameterIsNotNull(item, "item");
                                    String title = resultJson.getString("name");
                                    String string3 = resultJson.getString("entityType");
                                    String searchId = resultJson.getString("id");
                                    MediaMetadata metadata = (MediaMetadata) null;
                                    if (item instanceof Movie) {
                                        metadata = ((Movie) item).getMetadata();
                                    } else if (item instanceof Episode) {
                                        metadata = ((Episode) item).getMetadata();
                                    }
                                    Person[] personArr = null;
                                    Person[] personArr2 = null;
                                    String[] strArr = null;
                                    ParentalRating parentalRatingForName = ParentalRating.INSTANCE.forName(MegacableVODCatalogService.this.getBoService(), "RATING:" + resultJson.optInt("prLevel", 5));
                                    String[] strArr2 = null;
                                    String[] strArr3 = null;
                                    String[] strArr4 = null;
                                    Integer numValueOf = Integer.valueOf(resultJson.optInt("year", 0));
                                    Classifier[] classifierArr = null;
                                    Artwork[] artworkArr = null;
                                    Observable observable = null;
                                    Observable observable2 = null;
                                    ItemCollection itemCollection = null;
                                    ItemCollection itemCollection2 = null;
                                    Double d = null;
                                    if (metadata == null || (durationInMinutes = metadata.getDurationInMinutes()) == null) {
                                        durationInMinutes = 0;
                                    }
                                    MediaMetadata mediaMetadata = new MediaMetadata(personArr, personArr2, strArr, durationInMinutes, strArr2, strArr3, strArr4, numValueOf, classifierArr, artworkArr, observable, observable2, itemCollection, itemCollection2, d, parentalRatingForName, 32631, null);
                                    if (string3 != null) {
                                        int iHashCode = string3.hashCode();
                                        if (iHashCode == -1544438277) {
                                            if (string3.equals("episode")) {
                                                BOService boService = MegacableVODCatalogService.this.getBoService();
                                                String id = item.getId();
                                                Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                                Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                                return new SearchEpisodeContent(boService, id, title, mediaMetadata, searchId, item.getArtworks());
                                            }
                                        } else if (iHashCode == -906335517 && string3.equals("season")) {
                                            BOService boService2 = MegacableVODCatalogService.this.getBoService();
                                            String id2 = item.getId();
                                            Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                            Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                            return new SearchSeriesContent(boService2, id2, title, mediaMetadata, searchId, item.getArtworks());
                                        }
                                    }
                                    BOService boService3 = MegacableVODCatalogService.this.getBoService();
                                    String id3 = item.getId();
                                    Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                    Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                    return new SearchMovieContent(boService3, id3, title, mediaMetadata, searchId, item.getArtworks());
                                }
                            });
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(observableFlatMap, "Observable\n             …                        }");
                    return observableFlatMap;
                }
            }
        }
    }

    public final Single<ItemCollection<Content>> compassSearch(String query) {
        Intrinsics.checkParameterIsNotNull(query, "query");
        UserService userService = getBoService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        Single<ItemCollection<Content>> singleFlatMapSingle = userService.getActiveUser().map(new AnonymousClass1(query)).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableVODCatalogService.compassSearch.2
            @Override // io.reactivex.functions.Function
            public final Single<ItemCollection<Content>> apply(Single<ItemCollection<Content>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMapSingle, "boService.userService!!.…   }.flatMapSingle { it }");
        return singleFlatMapSingle;
    }
}
