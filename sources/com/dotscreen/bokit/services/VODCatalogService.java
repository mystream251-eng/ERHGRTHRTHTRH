package com.dotscreen.bokit.services;

import com.apollographql.apollo.api.Response;
import com.dotscreen.bokit.VODCatalogSectionQuery;
import com.dotscreen.bokit.VODContentDetailsListQuery;
import com.dotscreen.bokit.VODContentDetailsQuery;
import com.dotscreen.bokit.VODContentSearchQuery;
import com.dotscreen.bokit.VODPlaybackInfosQuery;
import com.dotscreen.bokit.VODSearchAutocompleteQuery;
import com.dotscreen.bokit.VODTopLevelServicesQuery;
import com.dotscreen.bokit.fragment.CatalogSectionFragment;
import com.dotscreen.bokit.fragment.ContentFragmentBase;
import com.dotscreen.bokit.internal.parsers.VODParser;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.PlaybackInfo;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VODCatalogService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012H\u0016¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\r2\u0006\u0010\u0016\u001a\u00020\u0007H\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\r2\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180\r2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006!"}, d2 = {"Lcom/dotscreen/bokit/services/VODCatalogService;", "", "boService", "Lcom/dotscreen/bokit/services/BOService;", "(Lcom/dotscreen/bokit/services/BOService;)V", "availableCatalogIds", "Lio/reactivex/Observable;", "", "getAvailableCatalogIds", "()Lio/reactivex/Observable;", "getBoService", "()Lcom/dotscreen/bokit/services/BOService;", "itemById", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "itemId", "itemsByIds", "itemsIds", "", "([Ljava/lang/String;)Lio/reactivex/Observable;", "playbackInfos", "Lcom/dotscreen/bokit/model/PlaybackInfo;", "streamId", "rootSection", "Lcom/dotscreen/bokit/model/CatalogSection;", "catalogId", "search", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/services/VODCatalogSearchResponse;", SearchIntents.EXTRA_QUERY, "searchAutocomplete", "sectionById", "collectionId", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class VODCatalogService {
    private final BOService boService;

    public VODCatalogService(BOService boService) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        this.boService = boService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BOService getBoService() {
        return this.boService;
    }

    public Observable<String> getAvailableCatalogIds() {
        Observable observableFlatMapObservable = this.boService.fetchVODTopLevelServices$bokit_android_release().flatMapObservable(new Function<VODTopLevelServicesQuery.Data, ObservableSource<? extends String>>() { // from class: com.dotscreen.bokit.services.VODCatalogService$availableCatalogIds$1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends String> apply(VODTopLevelServicesQuery.Data it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODParser vODParser = VODParser.INSTANCE;
                List<VODTopLevelServicesQuery.ListTopLevelService> listListTopLevelServices = it.listTopLevelServices();
                Intrinsics.checkNotNullExpressionValue(listListTopLevelServices, "it.listTopLevelServices()");
                return vODParser.parseCatalogIds(listListTopLevelServices);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "boService.fetchVODTopLev…listTopLevelServices()) }");
        return observableFlatMapObservable;
    }

    public Maybe<CatalogSection> rootSection(final String catalogId) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Maybe<CatalogSection> maybeFlatMapMaybe = this.boService.fetchVODCatalogSection$bokit_android_release(catalogId).map(new Function<Response<VODCatalogSectionQuery.Data>, VODCatalogSectionQuery.Section>() { // from class: com.dotscreen.bokit.services.VODCatalogService.rootSection.1
            @Override // io.reactivex.functions.Function
            public final VODCatalogSectionQuery.Section apply(Response<VODCatalogSectionQuery.Data> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODCatalogSectionQuery.Data data = it.data();
                VODCatalogSectionQuery.LookupService lookupService = data != null ? data.lookupService() : null;
                if (lookupService != null) {
                    return ((VODCatalogSectionQuery.AsContentCatalog) lookupService).section();
                }
                throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODCatalogSectionQuery.AsContentCatalog");
            }
        }).flatMapMaybe(new Function<VODCatalogSectionQuery.Section, MaybeSource<? extends CatalogSection>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.rootSection.2
            @Override // io.reactivex.functions.Function
            public final MaybeSource<? extends CatalogSection> apply(VODCatalogSectionQuery.Section it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODParser vODParser = VODParser.INSTANCE;
                BOService boService = VODCatalogService.this.getBoService();
                CatalogSectionFragment catalogSectionFragment = it.fragments().catalogSectionFragment();
                Intrinsics.checkNotNullExpressionValue(catalogSectionFragment, "it.fragments().catalogSectionFragment()");
                return vODParser.parseCatalogSection(boService, catalogSectionFragment, catalogId);
            }
        });
        Intrinsics.checkNotNullExpressionValue(maybeFlatMapMaybe, "boService.fetchVODCatalo…nFragment(), catalogId) }");
        return maybeFlatMapMaybe;
    }

    public Maybe<CatalogSection> sectionById(final String catalogId, String collectionId) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(collectionId, "collectionId");
        Maybe<CatalogSection> maybeFlatMapMaybe = this.boService.fetchVODCatalogSection$bokit_android_release(catalogId, collectionId).map(new Function<Response<VODCatalogSectionQuery.Data>, VODCatalogSectionQuery.Section>() { // from class: com.dotscreen.bokit.services.VODCatalogService.sectionById.1
            @Override // io.reactivex.functions.Function
            public final VODCatalogSectionQuery.Section apply(Response<VODCatalogSectionQuery.Data> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODCatalogSectionQuery.Data data = it.data();
                VODCatalogSectionQuery.LookupService lookupService = data != null ? data.lookupService() : null;
                if (lookupService != null) {
                    return ((VODCatalogSectionQuery.AsContentCatalog) lookupService).section();
                }
                throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODCatalogSectionQuery.AsContentCatalog");
            }
        }).flatMapMaybe(new Function<VODCatalogSectionQuery.Section, MaybeSource<? extends CatalogSection>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.sectionById.2
            @Override // io.reactivex.functions.Function
            public final MaybeSource<? extends CatalogSection> apply(VODCatalogSectionQuery.Section it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODParser vODParser = VODParser.INSTANCE;
                BOService boService = VODCatalogService.this.getBoService();
                CatalogSectionFragment catalogSectionFragment = it.fragments().catalogSectionFragment();
                Intrinsics.checkNotNullExpressionValue(catalogSectionFragment, "it.fragments().catalogSectionFragment()");
                return vODParser.parseCatalogSection(boService, catalogSectionFragment, catalogId);
            }
        });
        Intrinsics.checkNotNullExpressionValue(maybeFlatMapMaybe, "boService.fetchVODCatalo…nFragment(), catalogId) }");
        return maybeFlatMapMaybe;
    }

    public Maybe<BrowsableItem> itemById(String itemId) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Maybe<BrowsableItem> maybeFlatMapMaybe = this.boService.fetchVODContentDetails$bokit_android_release(itemId).map(new Function<VODContentDetailsQuery.Data, VODContentDetailsQuery.LookupContent>() { // from class: com.dotscreen.bokit.services.VODCatalogService.itemById.1
            @Override // io.reactivex.functions.Function
            public final VODContentDetailsQuery.LookupContent apply(VODContentDetailsQuery.Data it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.lookupContent();
            }
        }).flatMapMaybe(new Function<VODContentDetailsQuery.LookupContent, MaybeSource<? extends BrowsableItem>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.itemById.2
            @Override // io.reactivex.functions.Function
            public final MaybeSource<? extends BrowsableItem> apply(VODContentDetailsQuery.LookupContent it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODParser vODParser = VODParser.INSTANCE;
                BOService boService = VODCatalogService.this.getBoService();
                ContentFragmentBase contentFragmentBase = it.fragments().contentFragmentBase();
                Intrinsics.checkNotNullExpressionValue(contentFragmentBase, "it.fragments().contentFragmentBase()");
                return Maybe.just(vODParser.parseItem(boService, contentFragmentBase, it.fragments().contentFragmentDetails()));
            }
        });
        Intrinsics.checkNotNullExpressionValue(maybeFlatMapMaybe, "boService.fetchVODConten…tentFragmentDetails())) }");
        return maybeFlatMapMaybe;
    }

    public Observable<BrowsableItem> itemsByIds(String[] itemsIds) {
        Intrinsics.checkNotNullParameter(itemsIds, "itemsIds");
        Observable<BrowsableItem> map = this.boService.fetchVODContentDetailsList$bokit_android_release(itemsIds).flatMapObservable(new Function<VODContentDetailsListQuery.Data, ObservableSource<? extends VODContentDetailsListQuery.LookupContentList>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.itemsByIds.1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends VODContentDetailsListQuery.LookupContentList> apply(VODContentDetailsListQuery.Data it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Observable.fromIterable(it.lookupContentList());
            }
        }).map(new Function<VODContentDetailsListQuery.LookupContentList, BrowsableItem>() { // from class: com.dotscreen.bokit.services.VODCatalogService.itemsByIds.2
            @Override // io.reactivex.functions.Function
            public final BrowsableItem apply(VODContentDetailsListQuery.LookupContentList it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODParser vODParser = VODParser.INSTANCE;
                BOService boService = VODCatalogService.this.getBoService();
                ContentFragmentBase contentFragmentBase = it.fragments().contentFragmentBase();
                Intrinsics.checkNotNullExpressionValue(contentFragmentBase, "it.fragments().contentFragmentBase()");
                return vODParser.parseItem(boService, contentFragmentBase, null);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "boService.fetchVODConten…ntFragmentBase(), null) }");
        return map;
    }

    public Single<VODCatalogSearchResponse> search(String catalogId, String query) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(query, "query");
        Single<VODCatalogSearchResponse> singleFlatMap = this.boService.fetchVODContentSearch$bokit_android_release(catalogId, query).map(new Function<Response<VODContentSearchQuery.Data>, List<VODContentSearchQuery.ContentSearch>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.search.1
            @Override // io.reactivex.functions.Function
            public final List<VODContentSearchQuery.ContentSearch> apply(Response<VODContentSearchQuery.Data> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODContentSearchQuery.Data data = it.data();
                VODContentSearchQuery.LookupService lookupService = data != null ? data.lookupService() : null;
                if (lookupService != null) {
                    return ((VODContentSearchQuery.AsContentCatalog) lookupService).contentSearch();
                }
                throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODContentSearchQuery.AsContentCatalog");
            }
        }).flatMap(new Function<List<VODContentSearchQuery.ContentSearch>, SingleSource<? extends VODCatalogSearchResponse>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.search.2
            @Override // io.reactivex.functions.Function
            public final SingleSource<? extends VODCatalogSearchResponse> apply(List<VODContentSearchQuery.ContentSearch> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return VODParser.INSTANCE.parseCatalogSearch(VODCatalogService.this.getBoService(), it);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFlatMap, "boService.fetchVODConten…ogSearch(boService, it) }");
        return singleFlatMap;
    }

    public Observable<String> searchAutocomplete(String catalogId, String query) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(query, "query");
        Observable<String> observableFlatMapObservable = this.boService.fetchVODSearchAutoComplete$bokit_android_release(catalogId, query).map(new Function<Response<VODSearchAutocompleteQuery.Data>, List<String>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.searchAutocomplete.1
            @Override // io.reactivex.functions.Function
            public final List<String> apply(Response<VODSearchAutocompleteQuery.Data> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VODSearchAutocompleteQuery.Data data = it.data();
                VODSearchAutocompleteQuery.LookupService lookupService = data != null ? data.lookupService() : null;
                if (lookupService != null) {
                    return ((VODSearchAutocompleteQuery.AsContentCatalog) lookupService).contentAutocomplete();
                }
                throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODSearchAutocompleteQuery.AsContentCatalog");
            }
        }).flatMapObservable(new Function<List<String>, ObservableSource<? extends String>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.searchAutocomplete.2
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends String> apply(List<String> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Observable.fromIterable(it);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "boService.fetchVODSearch…rvable.fromIterable(it) }");
        return observableFlatMapObservable;
    }

    public Maybe<PlaybackInfo> playbackInfos(String streamId) {
        Intrinsics.checkNotNullParameter(streamId, "streamId");
        Maybe<PlaybackInfo> maybeFlatMapMaybe = this.boService.fetchVODPlaybackInfos$bokit_android_release(streamId).map(new Function<VODPlaybackInfosQuery.Data, List<VODPlaybackInfosQuery.Option>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.playbackInfos.1
            @Override // io.reactivex.functions.Function
            public final List<VODPlaybackInfosQuery.Option> apply(VODPlaybackInfosQuery.Data it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.authorizeStream().options();
            }
        }).flatMapMaybe(new Function<List<VODPlaybackInfosQuery.Option>, MaybeSource<? extends PlaybackInfo>>() { // from class: com.dotscreen.bokit.services.VODCatalogService.playbackInfos.2
            @Override // io.reactivex.functions.Function
            public final MaybeSource<? extends PlaybackInfo> apply(List<VODPlaybackInfosQuery.Option> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Maybe.just(VODParser.INSTANCE.parsePlaybackInfos(it));
            }
        });
        Intrinsics.checkNotNullExpressionValue(maybeFlatMapMaybe, "boService.fetchVODPlayba…parsePlaybackInfos(it)) }");
        return maybeFlatMapMaybe;
    }
}
