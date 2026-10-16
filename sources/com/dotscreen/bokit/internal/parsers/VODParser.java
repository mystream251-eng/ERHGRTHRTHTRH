package com.dotscreen.bokit.internal.parsers;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.Response;
import com.dotscreen.bokit.NotImplementedException;
import com.dotscreen.bokit.ParseException;
import com.dotscreen.bokit.VODContentOffersQuery;
import com.dotscreen.bokit.VODContentSearchQuery;
import com.dotscreen.bokit.VODContentSeasonsQuery;
import com.dotscreen.bokit.VODPlaybackInfosQuery;
import com.dotscreen.bokit.VODSectionItemsQuery;
import com.dotscreen.bokit.VODSectionSubsectionsQuery;
import com.dotscreen.bokit.VODTopLevelServicesQuery;
import com.dotscreen.bokit.fragment.CatalogSectionFragment;
import com.dotscreen.bokit.fragment.ContentFragmentBase;
import com.dotscreen.bokit.fragment.ContentFragmentDetails;
import com.dotscreen.bokit.model.AmazinArtwork;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.Classifier;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.ContentRatingClassifier;
import com.dotscreen.bokit.model.CountryClassifier;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.FormatClassifier;
import com.dotscreen.bokit.model.GenreClassifier;
import com.dotscreen.bokit.model.Item;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.model.ItemSortingOrder;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.model.OfferType;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.Person;
import com.dotscreen.bokit.model.PlaybackInfo;
import com.dotscreen.bokit.model.PlaybackInfoMimeType;
import com.dotscreen.bokit.model.SourceClassifier;
import com.dotscreen.bokit.model.SubjectClassifier;
import com.dotscreen.bokit.model.TVSeason;
import com.dotscreen.bokit.model.TVSeries;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogSearchResponse;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.bokit.type.CrewDepartment;
import com.dotscreen.bokit.type.LanguageCode;
import com.dotscreen.bokit.type.RoleCategory;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VODParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J(\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0015\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004J\"\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0014\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0011J \u0010#\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00072\u0006\u0010&\u001a\u00020'J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00072\u0006\u0010&\u001a\u00020\u001cH\u0002J.\u0010(\u001a\b\u0012\u0004\u0012\u0002H)0\u0007\"\b\b\u0000\u0010)*\u00020*2\u0006\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H)0\u0007H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/dotscreen/bokit/internal/parsers/VODParser;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "parseCatalogIds", "Lio/reactivex/Observable;", "listTopLevelService", "", "Lcom/dotscreen/bokit/VODTopLevelServicesQuery$ListTopLevelService;", "parseCatalogSearch", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/services/VODCatalogSearchResponse;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "results", "", "Lcom/dotscreen/bokit/VODContentSearchQuery$ContentSearch;", "parseCatalogSection", "Lio/reactivex/MaybeSource;", "Lcom/dotscreen/bokit/model/CatalogSection;", "section", "Lcom/dotscreen/bokit/fragment/CatalogSectionFragment;", "catalogId", "parseItem", "Lcom/dotscreen/bokit/model/BrowsableItem;", "contentBase", "Lcom/dotscreen/bokit/fragment/ContentFragmentBase;", "contentDetails", "Lcom/dotscreen/bokit/fragment/ContentFragmentDetails;", "parsePlaybackInfos", "Lcom/dotscreen/bokit/model/PlaybackInfo;", "options", "Lcom/dotscreen/bokit/VODPlaybackInfosQuery$Option;", "parseSection", "parseVODOffers", "Lcom/dotscreen/bokit/model/Offer;", "item", "Lcom/dotscreen/bokit/VODContentOffersQuery$LookupContent;", "sortItems", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/dotscreen/bokit/model/Item;", "order", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "obs", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class VODParser {
    public static final VODParser INSTANCE = new VODParser();
    private static final String TAG = VODParser.class.getSimpleName();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ItemSortingOrder.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ItemSortingOrder.Descending.ordinal()] = 1;
            $EnumSwitchMapping$0[ItemSortingOrder.Alphabetical.ordinal()] = 2;
            $EnumSwitchMapping$0[ItemSortingOrder.AlphabeticalInverted.ordinal()] = 3;
        }
    }

    private VODParser() {
    }

    public static final /* synthetic */ String access$getTAG$p(VODParser vODParser) {
        return TAG;
    }

    public final Observable<String> parseCatalogIds(List<VODTopLevelServicesQuery.ListTopLevelService> listTopLevelService) {
        Intrinsics.checkNotNullParameter(listTopLevelService, "listTopLevelService");
        Observable<String> observableFlatMap = Observable.fromIterable(listTopLevelService).flatMap(new Function<VODTopLevelServicesQuery.ListTopLevelService, ObservableSource<? extends String>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.parseCatalogIds.1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends String> apply(VODTopLevelServicesQuery.ListTopLevelService it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Observable.just(it.id());
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "Observable.fromIterable(…bservable.just(it.id()) }");
        return observableFlatMap;
    }

    public final MaybeSource<? extends CatalogSection> parseCatalogSection(final BOService boService, final CatalogSectionFragment section, final String catalogId) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(section, "section");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        return Maybe.create(new MaybeOnSubscribe<CatalogSection>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.parseCatalogSection.1
            @Override // io.reactivex.MaybeOnSubscribe
            public final void subscribe(MaybeEmitter<CatalogSection> emitter) {
                Intrinsics.checkNotNullParameter(emitter, "emitter");
                try {
                    emitter.onSuccess(VODParser.INSTANCE.parseSection(boService, catalogId, section));
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.tryOnError(new ParseException("No Catalog found: " + e, null, 2, null));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final CatalogSection parseSection(final BOService boService, final String catalogId, CatalogSectionFragment section) throws NotImplementedException {
        boolean zIsParentalRatingLevelAdult;
        String strId = section.id();
        Intrinsics.checkNotNullExpressionValue(strId, "section.id()");
        Observable<R> observableFlatMapObservable = boService.fetchVODSectionSubSection$bokit_android_release(catalogId, strId).flatMapObservable(new Function<Response<VODSectionSubsectionsQuery.Data>, ObservableSource<? extends ItemCollection<BrowsableItem>>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseSection$children$1
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends ItemCollection<BrowsableItem>> apply(final Response<VODSectionSubsectionsQuery.Data> subSections) {
                Intrinsics.checkNotNullParameter(subSections, "subSections");
                return Observable.create(new ObservableOnSubscribe<ItemCollection<BrowsableItem>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseSection$children$1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<ItemCollection<BrowsableItem>> observer) {
                        Intrinsics.checkNotNullParameter(observer, "observer");
                        Object objData = subSections.data();
                        if (objData == null) {
                            throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODSectionSubsectionsQuery.Data");
                        }
                        VODSectionSubsectionsQuery.LookupService lookupService = ((VODSectionSubsectionsQuery.Data) objData).lookupService();
                        Intrinsics.checkNotNullExpressionValue(lookupService, "(subSections.data() as V…ery.Data).lookupService()");
                        if (lookupService != null) {
                            List<VODSectionSubsectionsQuery.Subsection> listSubsections = ((VODSectionSubsectionsQuery.AsContentCatalog) lookupService).section().subsections();
                            Intrinsics.checkNotNullExpressionValue(listSubsections, "(lookup as VODSectionSub…).section().subsections()");
                            int size = listSubsections.size();
                            for (int i = 0; i < size; i++) {
                                VODParser vODParser = VODParser.INSTANCE;
                                BOService bOService = boService;
                                String str = catalogId;
                                CatalogSectionFragment catalogSectionFragment = listSubsections.get(i).fragments().catalogSectionFragment();
                                Intrinsics.checkNotNullExpressionValue(catalogSectionFragment, "innerSubSections[i].frag….catalogSectionFragment()");
                                observer.onNext(vODParser.parseSection(bOService, str, catalogSectionFragment));
                            }
                            observer.onComplete();
                            return;
                        }
                        throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODSectionSubsectionsQuery.AsContentCatalog");
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "boService.fetchVODSectio…      }\n                }");
        ArrayList arrayList = new ArrayList();
        List<CatalogSectionFragment.Artwork> listArtworks = section.artworks();
        Intrinsics.checkNotNullExpressionValue(listArtworks, "section.artworks()");
        for (CatalogSectionFragment.Artwork artwork : listArtworks) {
            String strSource = artwork.source();
            Intrinsics.checkNotNullExpressionValue(strSource, "artwork.source()");
            String strReplace$default = StringsKt.replace$default(strSource, "1234x1234", "{width}x{height}", false, 4, (Object) null);
            String str__typename = artwork.__typename();
            switch (str__typename.hashCode()) {
                case -2119087293:
                    if (str__typename.equals("ShowcardAsset")) {
                        arrayList.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.ShowCard));
                    } else {
                        continue;
                    }
                    break;
                case -1025986261:
                    if (str__typename.equals("StillFrameAsset")) {
                        arrayList.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.Still));
                    } else {
                        continue;
                    }
                    break;
                case -985797245:
                    if (str__typename.equals("PosterAsset")) {
                        arrayList.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.Poster));
                    } else {
                        continue;
                    }
                    break;
                case -151484390:
                    if (str__typename.equals("BackdropAsset")) {
                        arrayList.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.Banner));
                    } else {
                        continue;
                    }
                    break;
                case 415836133:
                    if (str__typename.equals("LogoAsset")) {
                        break;
                    }
                    break;
                case 1125401794:
                    if (str__typename.equals("TileAsset")) {
                        break;
                    }
                    break;
                default:
                    continue;
            }
            arrayList.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.Square));
        }
        Integer num = null;
        ParentalRating parentalRating = (ParentalRating) null;
        ArrayList arrayList2 = new ArrayList();
        List<CatalogSectionFragment.Classifier> listClassifiers = section.classifiers();
        if (!(listClassifiers == null || listClassifiers.isEmpty())) {
            List<CatalogSectionFragment.Classifier> listClassifiers2 = section.classifiers();
            Intrinsics.checkNotNull(listClassifiers2);
            int size = listClassifiers2.size();
            int i = 0;
            while (i < size) {
                List<CatalogSectionFragment.Classifier> listClassifiers3 = section.classifiers();
                Intrinsics.checkNotNull(listClassifiers3);
                CatalogSectionFragment.Classifier classifier = listClassifiers3.get(i);
                String str__typename2 = classifier.__typename();
                switch (str__typename2.hashCode()) {
                    case -1812638661:
                        if (str__typename2.equals("Source")) {
                            String strId2 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId2, "classifier.id()");
                            String strName = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName, "classifier.name()");
                            arrayList2.add(new SourceClassifier(strId2, strName, null, false, null, 28, null));
                        }
                        break;
                    case -1672482954:
                        if (str__typename2.equals("Country")) {
                            String strId3 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId3, "classifier.id()");
                            String strName2 = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName2, "classifier.name()");
                            arrayList2.add(new CountryClassifier(strId3, strName2, null, false, null, 28, null));
                        }
                        break;
                    case -203231988:
                        if (str__typename2.equals("Subject")) {
                            String strId4 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId4, "classifier.id()");
                            String strName3 = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName3, "classifier.name()");
                            arrayList2.add(new SubjectClassifier(strId4, strName3, null, false, null, 28, null));
                        }
                        break;
                    case 68688227:
                        if (str__typename2.equals("Genre")) {
                            String strId5 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId5, "classifier.id()");
                            String strName4 = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName4, "classifier.name()");
                            arrayList2.add(new GenreClassifier(strId5, strName4, null, false, null, 28, null));
                        }
                        break;
                    case 426509142:
                        if (str__typename2.equals("ContentRating")) {
                            Integer numValueOf = classifier instanceof CatalogSectionFragment.AsContentRating ? Integer.valueOf(((CatalogSectionFragment.AsContentRating) classifier).level()) : num;
                            if (numValueOf != null) {
                                int iIntValue = numValueOf.intValue();
                                if (boService.getUserService() != null) {
                                    UserService userService = boService.getUserService();
                                    Intrinsics.checkNotNull(userService);
                                    zIsParentalRatingLevelAdult = userService.isParentalRatingLevelAdult(iIntValue);
                                } else {
                                    zIsParentalRatingLevelAdult = false;
                                }
                                String strName5 = classifier.name();
                                Intrinsics.checkNotNullExpressionValue(strName5, "classifier.name()");
                                String strName6 = classifier.name();
                                Intrinsics.checkNotNullExpressionValue(strName6, "classifier.name()");
                                ParentalRating parentalRating2 = new ParentalRating(boService, strName5, strName6, iIntValue, zIsParentalRatingLevelAdult);
                                String strId6 = classifier.id();
                                Intrinsics.checkNotNullExpressionValue(strId6, "classifier.id()");
                                String strName7 = classifier.name();
                                Intrinsics.checkNotNullExpressionValue(strName7, "classifier.name()");
                                arrayList2.add(new ContentRatingClassifier(strId6, strName7, null, iIntValue, false, null, 48, null));
                                parentalRating = parentalRating2;
                            }
                        }
                        break;
                    case 2110055447:
                        if (str__typename2.equals("Format")) {
                            String strId7 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId7, "classifier.id()");
                            String strName8 = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName8, "classifier.name()");
                            arrayList2.add(new FormatClassifier(strId7, strName8, null, false, null, 28, null));
                        }
                        break;
                }
                i++;
                num = null;
            }
        }
        ParentalRating parentalRating3 = parentalRating;
        String strId8 = section.id();
        Intrinsics.checkNotNullExpressionValue(strId8, "section.id()");
        String strName9 = section.name();
        Intrinsics.checkNotNullExpressionValue(strName9, "section.name()");
        ArrayList arrayList3 = arrayList;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(boService, catalogId, section);
        Object[] array = arrayList2.toArray(new Classifier[0]);
        if (array != null) {
            return new CatalogSection(boService, strId8, strName9, null, arrayList3, null, false, parentalRating3, observableFlatMapObservable, null, anonymousClass3, (Classifier[]) array, null, 4096, null);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: renamed from: com.dotscreen.bokit.internal.parsers.VODParser$parseSection$3, reason: invalid class name */
    /* JADX INFO: compiled from: VODParser.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "order", "Lcom/dotscreen/bokit/model/ItemSortingOrder;", "invoke"}, k = 3, mv = {1, 4, 1})
    static final class AnonymousClass3 extends Lambda implements Function1<ItemSortingOrder, Observable<BrowsableItem>> {
        final /* synthetic */ BOService $boService;
        final /* synthetic */ String $catalogId;
        final /* synthetic */ CatalogSectionFragment $section;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(BOService bOService, String str, CatalogSectionFragment catalogSectionFragment) {
            super(1);
            this.$boService = bOService;
            this.$catalogId = str;
            this.$section = catalogSectionFragment;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Observable<BrowsableItem> invoke(ItemSortingOrder order) {
            Intrinsics.checkNotNullParameter(order, "order");
            VODParser vODParser = VODParser.INSTANCE;
            BOService bOService = this.$boService;
            String str = this.$catalogId;
            String strId = this.$section.id();
            Intrinsics.checkNotNullExpressionValue(strId, "section.id()");
            Observable<R> observableFlatMapObservable = bOService.fetchVODSectionItems$bokit_android_release(str, strId).flatMapObservable(new Function<Response<VODSectionItemsQuery.Data>, ObservableSource<? extends BrowsableItem>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.parseSection.3.1
                @Override // io.reactivex.functions.Function
                public final ObservableSource<? extends BrowsableItem> apply(final Response<VODSectionItemsQuery.Data> sectionItems) {
                    Intrinsics.checkNotNullParameter(sectionItems, "sectionItems");
                    return Observable.create(new ObservableOnSubscribe<BrowsableItem>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.parseSection.3.1.1
                        @Override // io.reactivex.ObservableOnSubscribe
                        public final void subscribe(ObservableEmitter<BrowsableItem> it) throws NotImplementedException {
                            Intrinsics.checkNotNullParameter(it, "it");
                            VODSectionItemsQuery.Data data = (VODSectionItemsQuery.Data) sectionItems.data();
                            VODSectionItemsQuery.LookupService lookupService = data != null ? data.lookupService() : null;
                            if (lookupService != null) {
                                List<VODSectionItemsQuery.Item> listItems = ((VODSectionItemsQuery.AsContentCatalog) lookupService).section().contents().items();
                                Intrinsics.checkNotNullExpressionValue(listItems, "(lookup as VODSectionIte…tion().contents().items()");
                                int size = listItems.size();
                                for (int i = 0; i < size; i++) {
                                    VODParser vODParser2 = VODParser.INSTANCE;
                                    BOService bOService2 = AnonymousClass3.this.$boService;
                                    ContentFragmentBase contentFragmentBase = listItems.get(i).fragments().contentFragmentBase();
                                    Intrinsics.checkNotNullExpressionValue(contentFragmentBase, "items[i].fragments().contentFragmentBase()");
                                    BrowsableItem item = vODParser2.parseItem(bOService2, contentFragmentBase, null);
                                    if (item != null) {
                                        it.onNext(item);
                                    }
                                }
                                it.onComplete();
                                return;
                            }
                            throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODSectionItemsQuery.AsContentCatalog");
                        }
                    });
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "boService.fetchVODSectio…                        }");
            return vODParser.sortItems(order, observableFlatMapObservable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T extends Item> Observable<T> sortItems(ItemSortingOrder order, Observable<T> obs) {
        int i = WhenMappings.$EnumSwitchMapping$0[order.ordinal()];
        if (i == 1) {
            Observable<T> observableFlatMapObservable = obs.toList().map(new Function<List<T>, List<? extends T>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.sortItems.1
                @Override // io.reactivex.functions.Function
                public final List<T> apply(List<T> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return CollectionsKt.reversed(it);
                }
            }).flatMapObservable(new Function<List<? extends T>, ObservableSource<? extends T>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.sortItems.2
                @Override // io.reactivex.functions.Function
                public final ObservableSource<? extends T> apply(List<? extends T> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Observable.fromIterable(it);
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable, "obs\n                    …rvable.fromIterable(it) }");
            return observableFlatMapObservable;
        }
        if (i == 2) {
            Observable<T> observableSorted = obs.sorted(new Comparator<T>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.sortItems.3
                /* JADX WARN: Incorrect types in method signature: (TT;TT;)I */
                @Override // java.util.Comparator
                public final int compare(Item item, Item item2) {
                    return item.getTitle().compareTo(item2.getTitle());
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableSorted, "obs.sorted { item1, item….compareTo(item2.title) }");
            return observableSorted;
        }
        if (i != 3) {
            return obs;
        }
        Observable<T> observableFlatMapObservable2 = obs.sorted(new Comparator<T>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.sortItems.4
            /* JADX WARN: Incorrect types in method signature: (TT;TT;)I */
            @Override // java.util.Comparator
            public final int compare(Item item, Item item2) {
                return item.getTitle().compareTo(item2.getTitle());
            }
        }).toList().map(new Function<List<T>, List<? extends T>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.sortItems.5
            @Override // io.reactivex.functions.Function
            public final List<T> apply(List<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return CollectionsKt.reversed(it);
            }
        }).flatMapObservable(new Function<List<? extends T>, ObservableSource<? extends T>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.sortItems.6
            @Override // io.reactivex.functions.Function
            public final ObservableSource<? extends T> apply(List<? extends T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Observable.fromIterable(it);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapObservable2, "obs.sorted { item1, item…rvable.fromIterable(it) }");
        return observableFlatMapObservable2;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0390  */
    /* JADX WARN: Code duplicated, block: B:102:0x0394  */
    /* JADX WARN: Code duplicated, block: B:104:0x039e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:105:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:106:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:108:0x03af  */
    /* JADX WARN: Code duplicated, block: B:111:0x03b6  */
    /* JADX WARN: Code duplicated, block: B:115:0x03cd  */
    /* JADX WARN: Code duplicated, block: B:117:0x03dc  */
    /* JADX WARN: Code duplicated, block: B:118:0x03de  */
    /* JADX WARN: Code duplicated, block: B:124:0x0405 A[LOOP:3: B:122:0x03fb->B:124:0x0405, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:127:0x0448  */
    /* JADX WARN: Code duplicated, block: B:130:0x044f  */
    /* JADX WARN: Code duplicated, block: B:134:0x0466  */
    /* JADX WARN: Code duplicated, block: B:136:0x0477  */
    /* JADX WARN: Code duplicated, block: B:137:0x0479  */
    /* JADX WARN: Code duplicated, block: B:139:0x047d  */
    /* JADX WARN: Code duplicated, block: B:144:0x049e A[LOOP:5: B:142:0x0498->B:144:0x049e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:147:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:150:0x04ea  */
    /* JADX WARN: Code duplicated, block: B:154:0x0508  */
    /* JADX WARN: Code duplicated, block: B:157:0x0520  */
    /* JADX WARN: Code duplicated, block: B:161:0x0598  */
    /* JADX WARN: Code duplicated, block: B:166:0x05a9  */
    /* JADX WARN: Code duplicated, block: B:169:0x05b0 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:192:0x0613 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Code duplicated, block: B:195:0x061a  */
    /* JADX WARN: Code duplicated, block: B:200:0x062b  */
    /* JADX WARN: Code duplicated, block: B:202:0x062e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:224:0x068c  */
    /* JADX WARN: Code duplicated, block: B:227:0x069c  */
    /* JADX WARN: Code duplicated, block: B:229:0x06ac  */
    /* JADX WARN: Code duplicated, block: B:231:0x06bc  */
    /* JADX WARN: Code duplicated, block: B:234:0x06c3  */
    /* JADX WARN: Code duplicated, block: B:238:0x06cc  */
    /* JADX WARN: Code duplicated, block: B:241:0x06dc  */
    /* JADX WARN: Code duplicated, block: B:242:0x06e8  */
    /* JADX WARN: Code duplicated, block: B:245:0x06f0  */
    /* JADX WARN: Code duplicated, block: B:246:0x06f7  */
    /* JADX WARN: Code duplicated, block: B:249:0x070f  */
    /* JADX WARN: Code duplicated, block: B:252:0x0738  */
    /* JADX WARN: Code duplicated, block: B:254:0x0746  */
    /* JADX WARN: Code duplicated, block: B:256:0x075a  */
    /* JADX WARN: Code duplicated, block: B:261:0x077a  */
    /* JADX WARN: Code duplicated, block: B:263:0x0782  */
    /* JADX WARN: Code duplicated, block: B:264:0x07ba  */
    /* JADX WARN: Code duplicated, block: B:266:0x07c4  */
    /* JADX WARN: Code duplicated, block: B:268:0x07df  */
    /* JADX WARN: Code duplicated, block: B:271:0x07e5  */
    /* JADX WARN: Code duplicated, block: B:274:0x07ec  */
    /* JADX WARN: Code duplicated, block: B:276:0x081e  */
    /* JADX WARN: Code duplicated, block: B:278:0x0828  */
    /* JADX WARN: Code duplicated, block: B:279:0x085e  */
    /* JADX WARN: Code duplicated, block: B:281:0x0868  */
    /* JADX WARN: Code duplicated, block: B:282:0x08a6  */
    /* JADX WARN: Code duplicated, block: B:284:0x08b0  */
    /* JADX WARN: Code duplicated, block: B:286:0x08cb  */
    /* JADX WARN: Code duplicated, block: B:289:0x08d1  */
    /* JADX WARN: Code duplicated, block: B:292:0x08d8  */
    /* JADX WARN: Code duplicated, block: B:295:0x0909  */
    /* JADX WARN: Code duplicated, block: B:297:0x090f  */
    /* JADX WARN: Code duplicated, block: B:299:0x0915  */
    /* JADX WARN: Code duplicated, block: B:301:0x091b  */
    /* JADX WARN: Code duplicated, block: B:341:0x03e2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:343:0x03c7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:345:0x0442 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:348:0x0480 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:352:0x0522 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:361:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:363:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:365:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:367:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:369:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:370:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:80:0x0308  */
    /* JADX WARN: Code duplicated, block: B:81:0x032b  */
    /* JADX WARN: Code duplicated, block: B:84:0x0333  */
    /* JADX WARN: Code duplicated, block: B:85:0x034c  */
    /* JADX WARN: Code duplicated, block: B:87:0x0350  */
    /* JADX WARN: Code duplicated, block: B:89:0x0361  */
    /* JADX WARN: Code duplicated, block: B:90:0x0364  */
    /* JADX WARN: Code duplicated, block: B:93:0x0371  */
    /* JADX WARN: Code duplicated, block: B:95:0x0375  */
    /* JADX WARN: Code duplicated, block: B:98:0x0387  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final BrowsableItem parseItem(final BOService boService, ContentFragmentBase contentBase, ContentFragmentDetails contentDetails) throws NotImplementedException {
        Maybe maybe;
        Maybe map;
        boolean z;
        Maybe map2;
        boolean z2;
        String str;
        Observable observableFlatMapObservable;
        boolean z3;
        Single single;
        int i;
        Single singleJust;
        VODParser$parseItem$episodes$3 vODParser$parseItem$episodes$2;
        Function1 function1;
        List<? extends ContentFragmentDetails.Crew> listEmptyList;
        ArrayList arrayList;
        ArrayList arrayList2;
        Iterator it;
        String str2;
        List<? extends ContentFragmentDetails.Cast> listEmptyList2;
        ArrayList arrayList3;
        Iterator it2;
        ArrayList arrayList4;
        Iterator it3;
        ArrayList arrayList5;
        List<? extends ContentFragmentDetails.Preview> listEmptyList3;
        ArrayList arrayList6;
        String str3;
        boolean z4;
        boolean z5;
        ArrayList arrayList7;
        ArrayList arrayList8;
        ArrayList arrayList9;
        ArrayList arrayList10;
        int size;
        int i2;
        int i3;
        String[] strArr;
        int size2;
        String[] strArr2;
        MediaMetadata mediaMetadata;
        Object[] array;
        Person[] personArr;
        Object[] array2;
        Person[] personArr2;
        Object[] array3;
        String[] strArr3;
        String[] strArr4;
        String[] strArr5;
        String[] strArr6;
        Observable observableFromIterable;
        Double dOriginalDuration;
        Integer numValueOf;
        Integer numYear;
        Artwork[] artworkArr;
        Observable observable;
        ItemCollection itemCollection;
        ItemCollection itemCollection2;
        Double d;
        Object[] array4;
        LinkedHashMap linkedHashMap;
        ContentFragmentDetails.AsSeason asSeason;
        Integer numValueOf2;
        ContentFragmentDetails.AsEpisode asEpisode;
        Integer numValueOf3;
        ItemExternalIdScheme[] itemExternalIdSchemeArrValues;
        int length;
        int i4;
        ItemExternalIdScheme itemExternalIdScheme;
        ContentFragmentDetails.Offers offers;
        List<? extends ContentFragmentDetails.Item> listItems;
        ContentFragmentDetails.Item item;
        List<LanguageCode> listSubtitlesLanguages;
        ContentFragmentDetails.Offers offers2;
        List<? extends ContentFragmentDetails.Item> listItems2;
        ContentFragmentDetails.Offers offers3;
        List<? extends ContentFragmentDetails.Item> listItems3;
        List<LanguageCode> listAudioLanguages;
        ContentFragmentDetails.Offers offers4;
        List<? extends ContentFragmentDetails.Item> listItems4;
        String strDescription;
        Object next;
        boolean z6;
        boolean z7;
        int i5;
        ContentFragmentDetails.AsEpisode asEpisode2;
        int i6;
        boolean zIsParentalRatingLevelAdult;
        ContentFragmentDetails contentFragmentDetails = contentDetails;
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(contentBase, "contentBase");
        ArrayList arrayList11 = new ArrayList();
        List<ContentFragmentBase.Artwork> listArtworks = contentBase.artworks();
        Intrinsics.checkNotNullExpressionValue(listArtworks, "contentBase.artworks()");
        for (ContentFragmentBase.Artwork artwork : listArtworks) {
            String strSource = artwork.source();
            Intrinsics.checkNotNullExpressionValue(strSource, "artwork.source()");
            String strReplace$default = StringsKt.replace$default(strSource, "1234x1234", "{width}x{height}", false, 4, (Object) null);
            String str__typename = artwork.__typename();
            switch (str__typename.hashCode()) {
                case -2119087293:
                    if (str__typename.equals("ShowcardAsset")) {
                        arrayList11.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.ShowCard));
                    } else {
                        continue;
                    }
                    break;
                case -1025986261:
                    if (str__typename.equals("StillFrameAsset")) {
                        arrayList11.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.Still));
                    } else {
                        continue;
                    }
                    break;
                case -985797245:
                    if (str__typename.equals("PosterAsset")) {
                        arrayList11.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.Poster));
                    } else {
                        continue;
                    }
                    break;
                case -151484390:
                    if (str__typename.equals("BackdropAsset")) {
                        arrayList11.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.Banner));
                    } else {
                        continue;
                    }
                    break;
                case 415836133:
                    if (str__typename.equals("LogoAsset")) {
                        break;
                    }
                    break;
                case 1125401794:
                    if (str__typename.equals("TileAsset")) {
                        break;
                    }
                    break;
                default:
                    continue;
            }
            arrayList11.add(new AmazinArtwork(boService, strReplace$default, ArtworkFormat.Square));
        }
        ArrayList arrayList12 = new ArrayList();
        ParentalRating parentalRating = (ParentalRating) null;
        ArrayList arrayList13 = new ArrayList();
        List<ContentFragmentBase.Classifier> listClassifiers = contentBase.classifiers();
        if (!(listClassifiers == null || listClassifiers.isEmpty())) {
            List<ContentFragmentBase.Classifier> listClassifiers2 = contentBase.classifiers();
            Intrinsics.checkNotNull(listClassifiers2);
            int size3 = listClassifiers2.size();
            int i7 = 0;
            while (i7 < size3) {
                List<ContentFragmentBase.Classifier> listClassifiers3 = contentBase.classifiers();
                Intrinsics.checkNotNull(listClassifiers3);
                ContentFragmentBase.Classifier classifier = listClassifiers3.get(i7);
                String str__typename2 = classifier.__typename();
                switch (str__typename2.hashCode()) {
                    case -1812638661:
                        i6 = i7;
                        if (str__typename2.equals("Source")) {
                            String strId = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId, "classifier.id()");
                            String strName = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName, "classifier.name()");
                            arrayList13.add(new SourceClassifier(strId, strName, null, false, null, 28, null));
                            continue;
                        }
                        i7 = i6 + 1;
                        break;
                    case -1672482954:
                        i6 = i7;
                        if (str__typename2.equals("Country")) {
                            String strId2 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId2, "classifier.id()");
                            String strName2 = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName2, "classifier.name()");
                            arrayList13.add(new CountryClassifier(strId2, strName2, null, false, null, 28, null));
                        } else {
                            continue;
                        }
                        i7 = i6 + 1;
                        break;
                    case -203231988:
                        i6 = i7;
                        if (str__typename2.equals("Subject")) {
                            String strId3 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId3, "classifier.id()");
                            String strName3 = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName3, "classifier.name()");
                            arrayList13.add(new SubjectClassifier(strId3, strName3, null, false, null, 28, null));
                        } else {
                            continue;
                        }
                        i7 = i6 + 1;
                        break;
                    case 68688227:
                        i6 = i7;
                        if (str__typename2.equals("Genre")) {
                            String strId4 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId4, "classifier.id()");
                            String strName4 = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName4, "classifier.name()");
                            arrayList13.add(new GenreClassifier(strId4, strName4, null, false, null, 28, null));
                            arrayList12.add(classifier.name());
                        } else {
                            continue;
                        }
                        i7 = i6 + 1;
                        break;
                    case 426509142:
                        if (str__typename2.equals("ContentRating")) {
                            Integer numValueOf4 = classifier instanceof ContentFragmentBase.AsContentRating ? Integer.valueOf(((ContentFragmentBase.AsContentRating) classifier).level()) : null;
                            if (numValueOf4 != null) {
                                int iIntValue = numValueOf4.intValue();
                                if (boService.getUserService() != null) {
                                    UserService userService = boService.getUserService();
                                    Intrinsics.checkNotNull(userService);
                                    zIsParentalRatingLevelAdult = userService.isParentalRatingLevelAdult(iIntValue);
                                } else {
                                    zIsParentalRatingLevelAdult = false;
                                }
                                String strName5 = classifier.name();
                                Intrinsics.checkNotNullExpressionValue(strName5, "classifier.name()");
                                String strName6 = classifier.name();
                                Intrinsics.checkNotNullExpressionValue(strName6, "classifier.name()");
                                i6 = i7;
                                ParentalRating parentalRating2 = new ParentalRating(boService, strName5, strName6, iIntValue, zIsParentalRatingLevelAdult);
                                String strId5 = classifier.id();
                                Intrinsics.checkNotNullExpressionValue(strId5, "classifier.id()");
                                String strName7 = classifier.name();
                                Intrinsics.checkNotNullExpressionValue(strName7, "classifier.name()");
                                Boolean.valueOf(arrayList13.add(new ContentRatingClassifier(strId5, strName7, null, iIntValue, false, null, 48, null)));
                                parentalRating = parentalRating2;
                            }
                        }
                        i7 = i6 + 1;
                        break;
                    case 2110055447:
                        if (str__typename2.equals("Format")) {
                            String strId6 = classifier.id();
                            Intrinsics.checkNotNullExpressionValue(strId6, "classifier.id()");
                            String strName8 = classifier.name();
                            Intrinsics.checkNotNullExpressionValue(strName8, "classifier.name()");
                            arrayList13.add(new FormatClassifier(strId6, strName8, null, false, null, 28, null));
                        }
                        break;
                }
                i6 = i7;
                i7 = i6 + 1;
            }
        }
        ParentalRating parentalRating3 = parentalRating;
        boolean z8 = contentFragmentDetails instanceof ContentFragmentDetails.AsSeason;
        if (z8) {
            VODCatalogService vodCatalogService = boService.getVodCatalogService();
            String strId7 = ((ContentFragmentDetails.AsSeason) contentFragmentDetails).series().id();
            Intrinsics.checkNotNullExpressionValue(strId7, "contentDetails.series().id()");
            map = vodCatalogService.itemById(strId7).map(new Function<BrowsableItem, TVSeries>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$series$1
                @Override // io.reactivex.functions.Function
                public final TVSeries apply(BrowsableItem it4) {
                    Intrinsics.checkNotNullParameter(it4, "it");
                    return (TVSeries) it4;
                }
            });
        } else {
            if (contentFragmentDetails instanceof ContentFragmentDetails.AsEpisode) {
                VODCatalogService vodCatalogService2 = boService.getVodCatalogService();
                String strId8 = ((ContentFragmentDetails.AsEpisode) contentFragmentDetails).series().id();
                Intrinsics.checkNotNullExpressionValue(strId8, "contentDetails.series().id()");
                map = vodCatalogService2.itemById(strId8).map(new Function<BrowsableItem, TVSeries>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$series$2
                    @Override // io.reactivex.functions.Function
                    public final TVSeries apply(BrowsableItem it4) {
                        Intrinsics.checkNotNullParameter(it4, "it");
                        return (TVSeries) it4;
                    }
                });
            } else {
                maybe = null;
            }
            z = contentFragmentDetails instanceof ContentFragmentDetails.AsEpisode;
            if (z) {
                asEpisode2 = (ContentFragmentDetails.AsEpisode) contentFragmentDetails;
                if (asEpisode2.season() != null) {
                    VODCatalogService vodCatalogService3 = boService.getVodCatalogService();
                    ContentFragmentDetails.Season season = asEpisode2.season();
                    Intrinsics.checkNotNull(season);
                    String strId9 = season.id();
                    Intrinsics.checkNotNullExpressionValue(strId9, "contentDetails.season()!!.id()");
                    map2 = vodCatalogService3.itemById(strId9).map(new Function<BrowsableItem, TVSeason>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$season$1
                        @Override // io.reactivex.functions.Function
                        public final TVSeason apply(BrowsableItem it4) {
                            Intrinsics.checkNotNullParameter(it4, "it");
                            return (TVSeason) it4;
                        }
                    });
                } else {
                    map2 = null;
                }
            } else {
                map2 = null;
            }
            z2 = contentFragmentDetails instanceof ContentFragmentDetails.AsSeries;
            str = "contentBase.id()";
            if (z2) {
                String strId10 = contentBase.id();
                Intrinsics.checkNotNullExpressionValue(strId10, "contentBase.id()");
                observableFlatMapObservable = boService.fetchVODContentSeasons$bokit_android_release(strId10).flatMapObservable(new Function<VODContentSeasonsQuery.Data, ObservableSource<? extends TVSeason>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$seasons$1
                    @Override // io.reactivex.functions.Function
                    public final ObservableSource<? extends TVSeason> apply(final VODContentSeasonsQuery.Data data) {
                        Intrinsics.checkNotNullParameter(data, "data");
                        return Observable.create(new ObservableOnSubscribe<TVSeason>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$seasons$1.1
                            @Override // io.reactivex.ObservableOnSubscribe
                            public final void subscribe(ObservableEmitter<TVSeason> observer) throws NotImplementedException {
                                Intrinsics.checkNotNullParameter(observer, "observer");
                                VODContentSeasonsQuery.LookupContent lookupContent = data.lookupContent();
                                if (lookupContent == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODContentSeasonsQuery.AsSeries");
                                }
                                List<VODContentSeasonsQuery.Item> listItems5 = ((VODContentSeasonsQuery.AsSeries) lookupContent).seasons().items();
                                Intrinsics.checkNotNullExpressionValue(listItems5, "(data.lookupContent() as…Series).seasons().items()");
                                for (VODContentSeasonsQuery.Item item2 : listItems5) {
                                    VODParser vODParser = VODParser.INSTANCE;
                                    BOService bOService = boService;
                                    ContentFragmentBase contentFragmentBase = item2.fragments().contentFragmentBase();
                                    Intrinsics.checkNotNull(contentFragmentBase);
                                    Intrinsics.checkNotNullExpressionValue(contentFragmentBase, "item.fragments().contentFragmentBase()!!");
                                    BrowsableItem item3 = vODParser.parseItem(bOService, contentFragmentBase, item2.fragments().contentFragmentDetails());
                                    if (item3 == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.model.TVSeason");
                                    }
                                    observer.onNext((TVSeason) item3);
                                }
                                observer.onComplete();
                            }
                        });
                    }
                });
            } else {
                observableFlatMapObservable = null;
            }
            if (z2) {
                i5 = ((ContentFragmentDetails.AsSeries) contentFragmentDetails).episodes().pageInfo().total();
                if (i5 != null) {
                    z3 = false;
                } else {
                    z3 = false;
                    i5 = 0;
                }
                singleJust = Single.just(i5);
            } else {
                z3 = false;
                if (z8) {
                    i = ((ContentFragmentDetails.AsSeason) contentFragmentDetails).episodes().pageInfo().total();
                    if (i == null) {
                        i = 0;
                    }
                    singleJust = Single.just(i);
                } else {
                    single = null;
                }
                if (z2) {
                    vODParser$parseItem$episodes$2 = new VODParser$parseItem$episodes$1(boService, contentBase);
                } else if (z8) {
                    vODParser$parseItem$episodes$2 = new VODParser$parseItem$episodes$2(boService, contentBase);
                } else {
                    vODParser$parseItem$episodes$2 = new Function1<ItemSortingOrder, Observable<Episode>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$episodes$3
                        @Override // kotlin.jvm.functions.Function1
                        public final Observable<Episode> invoke(ItemSortingOrder it4) {
                            Intrinsics.checkNotNullParameter(it4, "it");
                            Observable<Episode> observableEmpty = Observable.empty();
                            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
                            return observableEmpty;
                        }
                    };
                }
                function1 = vODParser$parseItem$episodes$2;
                if (contentFragmentDetails != null || (listEmptyList = contentDetails.crew()) == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                arrayList = new ArrayList();
                for (Object obj : listEmptyList) {
                    if (((ContentFragmentDetails.Crew) obj).department() == CrewDepartment.DIRECTORS) {
                        z7 = true;
                    } else {
                        z7 = z3;
                    }
                    if (z7) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList14 = arrayList;
                arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList14, 10));
                it = arrayList14.iterator();
                while (true) {
                    str2 = "it.person().id()";
                    if (it.hasNext()) {
                        ContentFragmentDetails.Crew crew = (ContentFragmentDetails.Crew) it.next();
                        Iterator it4 = it;
                        String strId11 = crew.person().id();
                        Intrinsics.checkNotNullExpressionValue(strId11, "it.person().id()");
                        String strName9 = crew.person().name();
                        Intrinsics.checkNotNullExpressionValue(strName9, "it.person().name()");
                        arrayList2.add(new Person(strId11, strName9, crew.task(), null, false, 24, null));
                        it = it4;
                    } else {
                        ArrayList arrayList15 = arrayList2;
                        if (contentFragmentDetails != null || (listEmptyList2 = contentDetails.cast()) == null) {
                            listEmptyList2 = CollectionsKt.emptyList();
                        }
                        arrayList3 = new ArrayList();
                        it2 = listEmptyList2.iterator();
                        while (it2.hasNext()) {
                            next = it2.next();
                            Iterator it5 = it2;
                            if (((ContentFragmentDetails.Cast) next).roleType() == RoleCategory.ACTOR) {
                                z6 = true;
                            } else {
                                z6 = z3;
                            }
                            if (z6) {
                                arrayList3.add(next);
                            }
                            it2 = it5;
                        }
                        ArrayList arrayList16 = arrayList3;
                        arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList16, 10));
                        it3 = arrayList16.iterator();
                        while (it3.hasNext()) {
                            ContentFragmentDetails.Cast cast = (ContentFragmentDetails.Cast) it3.next();
                            Iterator it6 = it3;
                            String strId12 = cast.person().id();
                            Intrinsics.checkNotNullExpressionValue(strId12, str2);
                            String str4 = str2;
                            String strName10 = cast.person().name();
                            Intrinsics.checkNotNullExpressionValue(strName10, "it.person().name()");
                            arrayList4.add(new Person(strId12, strName10, cast.role(), null, false, 24, null));
                            it3 = it6;
                            str2 = str4;
                        }
                        arrayList5 = arrayList4;
                        if (contentFragmentDetails != null || (listEmptyList3 = contentDetails.previews()) == null) {
                            listEmptyList3 = CollectionsKt.emptyList();
                        }
                        List<? extends ContentFragmentDetails.Preview> list = listEmptyList3;
                        arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                        for (ContentFragmentDetails.Preview preview : list) {
                            String strStreamId = preview.streamId();
                            Intrinsics.checkNotNullExpressionValue(strStreamId, "preview.streamId()");
                            strDescription = preview.description();
                            if (strDescription != null) {
                                strDescription = "";
                            }
                            String str5 = strDescription;
                            Intrinsics.checkNotNullExpressionValue(str5, "preview.description() ?: \"\"");
                            Observable observableEmpty = Observable.empty();
                            Intrinsics.checkNotNullExpressionValue(observableEmpty, "Observable.empty()");
                            ArrayList arrayList17 = arrayList6;
                            arrayList17.add(new Media(boService, strStreamId, str5, null, arrayList11, null, false, null, observableEmpty, 232, null));
                            arrayList6 = arrayList17;
                            z8 = z8;
                            z = z;
                            arrayList13 = arrayList13;
                            arrayList12 = arrayList12;
                            str = str;
                            arrayList11 = arrayList11;
                        }
                        str3 = str;
                        z4 = z;
                        z5 = z8;
                        arrayList7 = arrayList13;
                        arrayList8 = arrayList12;
                        arrayList9 = arrayList11;
                        arrayList10 = arrayList6;
                        if (contentFragmentDetails != null || (offers4 = contentDetails.offers()) == null || (listItems4 = offers4.items()) == null) {
                            size = 0;
                        } else {
                            size = listItems4.size();
                        }
                        if (size <= 0 && contentFragmentDetails != null && (offers3 = contentDetails.offers()) != null && (listItems3 = offers3.items()) != null) {
                            i2 = 0;
                            ContentFragmentDetails.Item item2 = listItems3.get(0);
                            if (item2 != null && (listAudioLanguages = item2.audioLanguages()) != null) {
                                List<LanguageCode> list2 = listAudioLanguages;
                                i3 = 10;
                                ArrayList arrayList18 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                                Iterator<T> it7 = list2.iterator();
                                while (it7.hasNext()) {
                                    String strRawValue = ((LanguageCode) it7.next()).rawValue();
                                    if (strRawValue == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.model.LanguageCode /* = kotlin.String */");
                                    }
                                    arrayList18.add(strRawValue);
                                }
                                Object[] array5 = arrayList18.toArray(new String[0]);
                                if (array5 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                                }
                                strArr = (String[]) array5;
                            }
                            if (contentFragmentDetails != null || (offers2 = contentDetails.offers()) == null || (listItems2 = offers2.items()) == null) {
                                size2 = i2;
                            } else {
                                size2 = listItems2.size();
                            }
                            if (size2 > 0 || contentFragmentDetails == null || (offers = contentDetails.offers()) == null || (listItems = offers.items()) == null || (item = listItems.get(i2)) == null || (listSubtitlesLanguages = item.subtitlesLanguages()) == null) {
                                strArr2 = null;
                            } else {
                                List<LanguageCode> list3 = listSubtitlesLanguages;
                                ArrayList arrayList19 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, i3));
                                Iterator<T> it8 = list3.iterator();
                                while (it8.hasNext()) {
                                    String strRawValue2 = ((LanguageCode) it8.next()).rawValue();
                                    if (strRawValue2 == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.model.LanguageCode /* = kotlin.String */");
                                    }
                                    arrayList19.add(strRawValue2);
                                }
                                Object[] array6 = arrayList19.toArray(new String[i2]);
                                if (array6 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                                }
                                strArr2 = (String[]) array6;
                            }
                            array = arrayList15.toArray(new Person[i2]);
                            if (array == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            personArr = (Person[]) array;
                            array2 = arrayList5.toArray(new Person[i2]);
                            if (array2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            personArr2 = (Person[]) array2;
                            array3 = arrayList8.toArray(new String[i2]);
                            if (array3 != null) {
                                strArr3 = (String[]) array3;
                                if (strArr == null) {
                                    strArr = new String[i2];
                                }
                                strArr4 = strArr;
                                strArr5 = null;
                                if (strArr2 == null) {
                                    strArr2 = new String[i2];
                                }
                                strArr6 = strArr2;
                                observableFromIterable = Observable.fromIterable(arrayList10);
                                dOriginalDuration = contentBase.originalDuration();
                                if (dOriginalDuration != null) {
                                    numValueOf = Integer.valueOf((int) dOriginalDuration.doubleValue());
                                } else {
                                    numValueOf = null;
                                }
                                if (contentBase.year() != null) {
                                    numYear = contentBase.year();
                                } else {
                                    numYear = null;
                                }
                                artworkArr = null;
                                observable = null;
                                itemCollection = null;
                                itemCollection2 = null;
                                d = null;
                                array4 = arrayList7.toArray(new Classifier[i2]);
                                if (array4 != null) {
                                    mediaMetadata = new MediaMetadata(personArr, personArr2, strArr3, numValueOf, strArr5, strArr4, strArr6, numYear, (Classifier[]) array4, artworkArr, observableFromIterable, observable, itemCollection, itemCollection2, d, parentalRating3, 31248, null);
                                    linkedHashMap = new LinkedHashMap();
                                    List<ContentFragmentBase.ExternalId> listExternalIds = contentBase.externalIds();
                                    Intrinsics.checkNotNullExpressionValue(listExternalIds, "contentBase.externalIds()");
                                    for (ContentFragmentBase.ExternalId externalId : listExternalIds) {
                                        itemExternalIdSchemeArrValues = ItemExternalIdScheme.values();
                                        length = itemExternalIdSchemeArrValues.length;
                                        for (i4 = i2; i4 < length; i4++) {
                                            itemExternalIdScheme = itemExternalIdSchemeArrValues[i4];
                                            if (Intrinsics.areEqual(itemExternalIdScheme.getScheme(), externalId.scheme().rawValue())) {
                                                String strIdentifier = externalId.identifier();
                                                Intrinsics.checkNotNullExpressionValue(strIdentifier, "externalId.identifier()");
                                                linkedHashMap.put(itemExternalIdScheme, strIdentifier);
                                            }
                                        }
                                    }
                                    switch (contentBase.__typename()) {
                                        case "Season":
                                            String strId13 = contentBase.id();
                                            Intrinsics.checkNotNullExpressionValue(strId13, str3);
                                            String strTitle = contentBase.title();
                                            Intrinsics.checkNotNullExpressionValue(strTitle, "contentBase.title()");
                                            String strDescription2 = contentBase.description();
                                            ArrayList arrayList20 = arrayList9;
                                            if (!z5) {
                                                contentFragmentDetails = null;
                                            }
                                            asSeason = (ContentFragmentDetails.AsSeason) contentFragmentDetails;
                                            if (asSeason != null || (numValueOf2 = asSeason.seasonNumber()) == null) {
                                                numValueOf2 = Integer.valueOf(i2);
                                            }
                                            Intrinsics.checkNotNullExpressionValue(numValueOf2, "(contentDetails as? Cont…                     ?: 0");
                                            return new TVSeason(boService, strId13, strTitle, strDescription2, arrayList20, null, numValueOf2.intValue(), mediaMetadata, maybe, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 2080, null);
                                        case "Series":
                                            String strId14 = contentBase.id();
                                            Intrinsics.checkNotNullExpressionValue(strId14, str3);
                                            String strTitle2 = contentBase.title();
                                            Intrinsics.checkNotNullExpressionValue(strTitle2, "contentBase.title()");
                                            return new TVSeries(boService, strId14, strTitle2, contentBase.description(), arrayList9, null, mediaMetadata, observableFlatMapObservable, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                                        case "Movie":
                                            String strId15 = contentBase.id();
                                            Intrinsics.checkNotNullExpressionValue(strId15, str3);
                                            String strTitle3 = contentBase.title();
                                            Intrinsics.checkNotNullExpressionValue(strTitle3, "contentBase.title()");
                                            return new Movie(boService, strId15, strTitle3, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                                        case "Episode":
                                            String strId16 = contentBase.id();
                                            Intrinsics.checkNotNullExpressionValue(strId16, str3);
                                            String strTitle4 = contentBase.title();
                                            Intrinsics.checkNotNullExpressionValue(strTitle4, "contentBase.title()");
                                            String strDescription3 = contentBase.description();
                                            ArrayList arrayList21 = arrayList9;
                                            if (!z4) {
                                                contentFragmentDetails = null;
                                            }
                                            asEpisode = (ContentFragmentDetails.AsEpisode) contentFragmentDetails;
                                            if (asEpisode != null || (numValueOf3 = asEpisode.episodeNumber()) == null) {
                                                numValueOf3 = Integer.valueOf(i2);
                                            }
                                            Intrinsics.checkNotNullExpressionValue(numValueOf3, "(contentDetails as? Cont…                     ?: 0");
                                            return new Episode(boService, strId16, strTitle4, strDescription3, arrayList21, null, numValueOf3.intValue(), mediaMetadata, maybe, map2, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                                        case "GenericContent":
                                            String strId17 = contentBase.id();
                                            Intrinsics.checkNotNullExpressionValue(strId17, str3);
                                            String strTitle5 = contentBase.title();
                                            Intrinsics.checkNotNullExpressionValue(strTitle5, "contentBase.title()");
                                            return new Content(boService, strId17, strTitle5, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                                        default:
                                            return null;
                                    }
                                }
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        i2 = 0;
                        i3 = 10;
                        strArr = null;
                        if (contentFragmentDetails != null) {
                            size2 = i2;
                        } else {
                            size2 = i2;
                        }
                        if (size2 > 0) {
                            strArr2 = null;
                        } else {
                            strArr2 = null;
                        }
                        array = arrayList15.toArray(new Person[i2]);
                        if (array == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        personArr = (Person[]) array;
                        array2 = arrayList5.toArray(new Person[i2]);
                        if (array2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        personArr2 = (Person[]) array2;
                        array3 = arrayList8.toArray(new String[i2]);
                        if (array3 != null) {
                            strArr3 = (String[]) array3;
                            if (strArr == null) {
                                strArr = new String[i2];
                            }
                            strArr4 = strArr;
                            strArr5 = null;
                            if (strArr2 == null) {
                                strArr2 = new String[i2];
                            }
                            strArr6 = strArr2;
                            observableFromIterable = Observable.fromIterable(arrayList10);
                            dOriginalDuration = contentBase.originalDuration();
                            if (dOriginalDuration != null) {
                                numValueOf = Integer.valueOf((int) dOriginalDuration.doubleValue());
                            } else {
                                numValueOf = null;
                            }
                            if (contentBase.year() != null) {
                                numYear = contentBase.year();
                            } else {
                                numYear = null;
                            }
                            artworkArr = null;
                            observable = null;
                            itemCollection = null;
                            itemCollection2 = null;
                            d = null;
                            array4 = arrayList7.toArray(new Classifier[i2]);
                            if (array4 != null) {
                                mediaMetadata = new MediaMetadata(personArr, personArr2, strArr3, numValueOf, strArr5, strArr4, strArr6, numYear, (Classifier[]) array4, artworkArr, observableFromIterable, observable, itemCollection, itemCollection2, d, parentalRating3, 31248, null);
                                linkedHashMap = new LinkedHashMap();
                                List<ContentFragmentBase.ExternalId> listExternalIds2 = contentBase.externalIds();
                                Intrinsics.checkNotNullExpressionValue(listExternalIds2, "contentBase.externalIds()");
                                while (r2.hasNext()) {
                                    itemExternalIdSchemeArrValues = ItemExternalIdScheme.values();
                                    length = itemExternalIdSchemeArrValues.length;
                                    while (i4 < length) {
                                        itemExternalIdScheme = itemExternalIdSchemeArrValues[i4];
                                        if (Intrinsics.areEqual(itemExternalIdScheme.getScheme(), externalId.scheme().rawValue())) {
                                            String strIdentifier2 = externalId.identifier();
                                            Intrinsics.checkNotNullExpressionValue(strIdentifier2, "externalId.identifier()");
                                            linkedHashMap.put(itemExternalIdScheme, strIdentifier2);
                                        }
                                    }
                                }
                                switch (contentBase.__typename()) {
                                    case -1822468349:
                                        String strId18 = contentBase.id();
                                        Intrinsics.checkNotNullExpressionValue(strId18, str3);
                                        String strTitle6 = contentBase.title();
                                        Intrinsics.checkNotNullExpressionValue(strTitle6, "contentBase.title()");
                                        String strDescription4 = contentBase.description();
                                        ArrayList arrayList22 = arrayList9;
                                        if (!z5) {
                                            contentFragmentDetails = null;
                                        }
                                        asSeason = (ContentFragmentDetails.AsSeason) contentFragmentDetails;
                                        if (asSeason != null) {
                                            numValueOf2 = Integer.valueOf(i2);
                                        } else {
                                            numValueOf2 = Integer.valueOf(i2);
                                        }
                                        Intrinsics.checkNotNullExpressionValue(numValueOf2, "(contentDetails as? Cont…                     ?: 0");
                                        return new TVSeason(boService, strId18, strTitle6, strDescription4, arrayList22, null, numValueOf2.intValue(), mediaMetadata, maybe, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 2080, null);
                                    case -1821971817:
                                        String strId19 = contentBase.id();
                                        Intrinsics.checkNotNullExpressionValue(strId19, str3);
                                        String strTitle7 = contentBase.title();
                                        Intrinsics.checkNotNullExpressionValue(strTitle7, "contentBase.title()");
                                        return new TVSeries(boService, strId19, strTitle7, contentBase.description(), arrayList9, null, mediaMetadata, observableFlatMapObservable, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                                    case 74534672:
                                        String strId110 = contentBase.id();
                                        Intrinsics.checkNotNullExpressionValue(strId110, str3);
                                        String strTitle8 = contentBase.title();
                                        Intrinsics.checkNotNullExpressionValue(strTitle8, "contentBase.title()");
                                        return new Movie(boService, strId110, strTitle8, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                                    case 120215003:
                                        String strId111 = contentBase.id();
                                        Intrinsics.checkNotNullExpressionValue(strId111, str3);
                                        String strTitle9 = contentBase.title();
                                        Intrinsics.checkNotNullExpressionValue(strTitle9, "contentBase.title()");
                                        String strDescription5 = contentBase.description();
                                        ArrayList arrayList23 = arrayList9;
                                        if (!z4) {
                                            contentFragmentDetails = null;
                                        }
                                        asEpisode = (ContentFragmentDetails.AsEpisode) contentFragmentDetails;
                                        if (asEpisode != null) {
                                            numValueOf3 = Integer.valueOf(i2);
                                        } else {
                                            numValueOf3 = Integer.valueOf(i2);
                                        }
                                        Intrinsics.checkNotNullExpressionValue(numValueOf3, "(contentDetails as? Cont…                     ?: 0");
                                        return new Episode(boService, strId111, strTitle9, strDescription5, arrayList23, null, numValueOf3.intValue(), mediaMetadata, maybe, map2, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                                    case 730734274:
                                        String strId112 = contentBase.id();
                                        Intrinsics.checkNotNullExpressionValue(strId112, str3);
                                        String strTitle10 = contentBase.title();
                                        Intrinsics.checkNotNullExpressionValue(strTitle10, "contentBase.title()");
                                        return new Content(boService, strId112, strTitle10, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                                    default:
                                        return null;
                                }
                            }
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            }
            single = singleJust;
            if (z2) {
                vODParser$parseItem$episodes$2 = new VODParser$parseItem$episodes$1(boService, contentBase);
            } else if (z8) {
                vODParser$parseItem$episodes$2 = new VODParser$parseItem$episodes$2(boService, contentBase);
            } else {
                vODParser$parseItem$episodes$2 = new Function1<ItemSortingOrder, Observable<Episode>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$episodes$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Observable<Episode> invoke(ItemSortingOrder it9) {
                        Intrinsics.checkNotNullParameter(it9, "it");
                        Observable<Episode> observableEmpty2 = Observable.empty();
                        Intrinsics.checkNotNullExpressionValue(observableEmpty2, "Observable.empty()");
                        return observableEmpty2;
                    }
                };
            }
            function1 = vODParser$parseItem$episodes$2;
            if (contentFragmentDetails != null) {
                listEmptyList = CollectionsKt.emptyList();
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            arrayList = new ArrayList();
            while (r1.hasNext()) {
                if (((ContentFragmentDetails.Crew) obj).department() == CrewDepartment.DIRECTORS) {
                    z7 = true;
                } else {
                    z7 = z3;
                }
                if (z7) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList110 = arrayList;
            arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList110, 10));
            it = arrayList110.iterator();
            while (true) {
                str2 = "it.person().id()";
                if (it.hasNext()) {
                    ContentFragmentDetails.Crew crew2 = (ContentFragmentDetails.Crew) it.next();
                    Iterator it9 = it;
                    String strId113 = crew2.person().id();
                    Intrinsics.checkNotNullExpressionValue(strId113, "it.person().id()");
                    String strName11 = crew2.person().name();
                    Intrinsics.checkNotNullExpressionValue(strName11, "it.person().name()");
                    arrayList2.add(new Person(strId113, strName11, crew2.task(), null, false, 24, null));
                    it = it9;
                } else {
                    ArrayList arrayList111 = arrayList2;
                    if (contentFragmentDetails != null) {
                        listEmptyList2 = CollectionsKt.emptyList();
                    } else {
                        listEmptyList2 = CollectionsKt.emptyList();
                    }
                    arrayList3 = new ArrayList();
                    it2 = listEmptyList2.iterator();
                    while (it2.hasNext()) {
                        next = it2.next();
                        Iterator it10 = it2;
                        if (((ContentFragmentDetails.Cast) next).roleType() == RoleCategory.ACTOR) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        if (z6) {
                            arrayList3.add(next);
                        }
                        it2 = it10;
                    }
                    ArrayList arrayList112 = arrayList3;
                    arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList112, 10));
                    it3 = arrayList112.iterator();
                    while (it3.hasNext()) {
                        ContentFragmentDetails.Cast cast2 = (ContentFragmentDetails.Cast) it3.next();
                        Iterator it11 = it3;
                        String strId114 = cast2.person().id();
                        Intrinsics.checkNotNullExpressionValue(strId114, str2);
                        String str6 = str2;
                        String strName12 = cast2.person().name();
                        Intrinsics.checkNotNullExpressionValue(strName12, "it.person().name()");
                        arrayList4.add(new Person(strId114, strName12, cast2.role(), null, false, 24, null));
                        it3 = it11;
                        str2 = str6;
                    }
                    arrayList5 = arrayList4;
                    if (contentFragmentDetails != null) {
                        listEmptyList3 = CollectionsKt.emptyList();
                    } else {
                        listEmptyList3 = CollectionsKt.emptyList();
                    }
                    List<? extends ContentFragmentDetails.Preview> list4 = listEmptyList3;
                    arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
                    while (r25.hasNext()) {
                        String strStreamId2 = preview.streamId();
                        Intrinsics.checkNotNullExpressionValue(strStreamId2, "preview.streamId()");
                        strDescription = preview.description();
                        if (strDescription != null) {
                            strDescription = "";
                        }
                        String str7 = strDescription;
                        Intrinsics.checkNotNullExpressionValue(str7, "preview.description() ?: \"\"");
                        Observable observableEmpty2 = Observable.empty();
                        Intrinsics.checkNotNullExpressionValue(observableEmpty2, "Observable.empty()");
                        ArrayList arrayList113 = arrayList6;
                        arrayList113.add(new Media(boService, strStreamId2, str7, null, arrayList11, null, false, null, observableEmpty2, 232, null));
                        arrayList6 = arrayList113;
                        z8 = z8;
                        z = z;
                        arrayList13 = arrayList13;
                        arrayList12 = arrayList12;
                        str = str;
                        arrayList11 = arrayList11;
                    }
                    str3 = str;
                    z4 = z;
                    z5 = z8;
                    arrayList7 = arrayList13;
                    arrayList8 = arrayList12;
                    arrayList9 = arrayList11;
                    arrayList10 = arrayList6;
                    if (contentFragmentDetails != null) {
                        size = 0;
                    } else {
                        size = 0;
                    }
                    if (size <= 0) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                    }
                    i3 = 10;
                    strArr = null;
                    if (contentFragmentDetails != null) {
                        size2 = i2;
                    } else {
                        size2 = i2;
                    }
                    if (size2 > 0) {
                        strArr2 = null;
                    } else {
                        strArr2 = null;
                    }
                    array = arrayList111.toArray(new Person[i2]);
                    if (array == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    personArr = (Person[]) array;
                    array2 = arrayList5.toArray(new Person[i2]);
                    if (array2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    personArr2 = (Person[]) array2;
                    array3 = arrayList8.toArray(new String[i2]);
                    if (array3 != null) {
                        strArr3 = (String[]) array3;
                        if (strArr == null) {
                            strArr = new String[i2];
                        }
                        strArr4 = strArr;
                        strArr5 = null;
                        if (strArr2 == null) {
                            strArr2 = new String[i2];
                        }
                        strArr6 = strArr2;
                        observableFromIterable = Observable.fromIterable(arrayList10);
                        dOriginalDuration = contentBase.originalDuration();
                        if (dOriginalDuration != null) {
                            numValueOf = Integer.valueOf((int) dOriginalDuration.doubleValue());
                        } else {
                            numValueOf = null;
                        }
                        if (contentBase.year() != null) {
                            numYear = contentBase.year();
                        } else {
                            numYear = null;
                        }
                        artworkArr = null;
                        observable = null;
                        itemCollection = null;
                        itemCollection2 = null;
                        d = null;
                        array4 = arrayList7.toArray(new Classifier[i2]);
                        if (array4 != null) {
                            mediaMetadata = new MediaMetadata(personArr, personArr2, strArr3, numValueOf, strArr5, strArr4, strArr6, numYear, (Classifier[]) array4, artworkArr, observableFromIterable, observable, itemCollection, itemCollection2, d, parentalRating3, 31248, null);
                            linkedHashMap = new LinkedHashMap();
                            List<ContentFragmentBase.ExternalId> listExternalIds3 = contentBase.externalIds();
                            Intrinsics.checkNotNullExpressionValue(listExternalIds3, "contentBase.externalIds()");
                            while (r2.hasNext()) {
                                itemExternalIdSchemeArrValues = ItemExternalIdScheme.values();
                                length = itemExternalIdSchemeArrValues.length;
                                while (i4 < length) {
                                    itemExternalIdScheme = itemExternalIdSchemeArrValues[i4];
                                    if (Intrinsics.areEqual(itemExternalIdScheme.getScheme(), externalId.scheme().rawValue())) {
                                        String strIdentifier3 = externalId.identifier();
                                        Intrinsics.checkNotNullExpressionValue(strIdentifier3, "externalId.identifier()");
                                        linkedHashMap.put(itemExternalIdScheme, strIdentifier3);
                                    }
                                }
                            }
                            switch (contentBase.__typename()) {
                                case -1822468349:
                                    String strId115 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId115, str3);
                                    String strTitle11 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle11, "contentBase.title()");
                                    String strDescription6 = contentBase.description();
                                    ArrayList arrayList24 = arrayList9;
                                    if (!z5) {
                                        contentFragmentDetails = null;
                                    }
                                    asSeason = (ContentFragmentDetails.AsSeason) contentFragmentDetails;
                                    if (asSeason != null) {
                                        numValueOf2 = Integer.valueOf(i2);
                                    } else {
                                        numValueOf2 = Integer.valueOf(i2);
                                    }
                                    Intrinsics.checkNotNullExpressionValue(numValueOf2, "(contentDetails as? Cont…                     ?: 0");
                                    return new TVSeason(boService, strId115, strTitle11, strDescription6, arrayList24, null, numValueOf2.intValue(), mediaMetadata, maybe, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 2080, null);
                                case -1821971817:
                                    String strId116 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId116, str3);
                                    String strTitle12 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle12, "contentBase.title()");
                                    return new TVSeries(boService, strId116, strTitle12, contentBase.description(), arrayList9, null, mediaMetadata, observableFlatMapObservable, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                                case 74534672:
                                    String strId117 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId117, str3);
                                    String strTitle13 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle13, "contentBase.title()");
                                    return new Movie(boService, strId117, strTitle13, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                                case 120215003:
                                    String strId118 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId118, str3);
                                    String strTitle14 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle14, "contentBase.title()");
                                    String strDescription7 = contentBase.description();
                                    ArrayList arrayList25 = arrayList9;
                                    if (!z4) {
                                        contentFragmentDetails = null;
                                    }
                                    asEpisode = (ContentFragmentDetails.AsEpisode) contentFragmentDetails;
                                    if (asEpisode != null) {
                                        numValueOf3 = Integer.valueOf(i2);
                                    } else {
                                        numValueOf3 = Integer.valueOf(i2);
                                    }
                                    Intrinsics.checkNotNullExpressionValue(numValueOf3, "(contentDetails as? Cont…                     ?: 0");
                                    return new Episode(boService, strId118, strTitle14, strDescription7, arrayList25, null, numValueOf3.intValue(), mediaMetadata, maybe, map2, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                                case 730734274:
                                    String strId119 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId119, str3);
                                    String strTitle15 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle15, "contentBase.title()");
                                    return new Content(boService, strId119, strTitle15, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                                default:
                                    return null;
                            }
                        }
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
        }
        maybe = map;
        z = contentFragmentDetails instanceof ContentFragmentDetails.AsEpisode;
        if (z) {
            asEpisode2 = (ContentFragmentDetails.AsEpisode) contentFragmentDetails;
            if (asEpisode2.season() != null) {
                VODCatalogService vodCatalogService4 = boService.getVodCatalogService();
                ContentFragmentDetails.Season season2 = asEpisode2.season();
                Intrinsics.checkNotNull(season2);
                String strId20 = season2.id();
                Intrinsics.checkNotNullExpressionValue(strId20, "contentDetails.season()!!.id()");
                map2 = vodCatalogService4.itemById(strId20).map(new Function<BrowsableItem, TVSeason>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$season$1
                    @Override // io.reactivex.functions.Function
                    public final TVSeason apply(BrowsableItem it12) {
                        Intrinsics.checkNotNullParameter(it12, "it");
                        return (TVSeason) it12;
                    }
                });
            } else {
                map2 = null;
            }
        } else {
            map2 = null;
        }
        z2 = contentFragmentDetails instanceof ContentFragmentDetails.AsSeries;
        str = "contentBase.id()";
        if (z2) {
            String strId120 = contentBase.id();
            Intrinsics.checkNotNullExpressionValue(strId120, "contentBase.id()");
            observableFlatMapObservable = boService.fetchVODContentSeasons$bokit_android_release(strId120).flatMapObservable(new Function<VODContentSeasonsQuery.Data, ObservableSource<? extends TVSeason>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$seasons$1
                @Override // io.reactivex.functions.Function
                public final ObservableSource<? extends TVSeason> apply(final VODContentSeasonsQuery.Data data) {
                    Intrinsics.checkNotNullParameter(data, "data");
                    return Observable.create(new ObservableOnSubscribe<TVSeason>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$seasons$1.1
                        @Override // io.reactivex.ObservableOnSubscribe
                        public final void subscribe(ObservableEmitter<TVSeason> observer) throws NotImplementedException {
                            Intrinsics.checkNotNullParameter(observer, "observer");
                            VODContentSeasonsQuery.LookupContent lookupContent = data.lookupContent();
                            if (lookupContent == null) {
                                throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.VODContentSeasonsQuery.AsSeries");
                            }
                            List<VODContentSeasonsQuery.Item> listItems5 = ((VODContentSeasonsQuery.AsSeries) lookupContent).seasons().items();
                            Intrinsics.checkNotNullExpressionValue(listItems5, "(data.lookupContent() as…Series).seasons().items()");
                            for (VODContentSeasonsQuery.Item item3 : listItems5) {
                                VODParser vODParser = VODParser.INSTANCE;
                                BOService bOService = boService;
                                ContentFragmentBase contentFragmentBase = item3.fragments().contentFragmentBase();
                                Intrinsics.checkNotNull(contentFragmentBase);
                                Intrinsics.checkNotNullExpressionValue(contentFragmentBase, "item.fragments().contentFragmentBase()!!");
                                BrowsableItem item4 = vODParser.parseItem(bOService, contentFragmentBase, item3.fragments().contentFragmentDetails());
                                if (item4 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type com.dotscreen.bokit.model.TVSeason");
                                }
                                observer.onNext((TVSeason) item4);
                            }
                            observer.onComplete();
                        }
                    });
                }
            });
        } else {
            observableFlatMapObservable = null;
        }
        if (z2) {
            i5 = ((ContentFragmentDetails.AsSeries) contentFragmentDetails).episodes().pageInfo().total();
            if (i5 != null) {
                z3 = false;
            } else {
                z3 = false;
                i5 = 0;
            }
            singleJust = Single.just(i5);
        } else {
            z3 = false;
            if (z8) {
                i = ((ContentFragmentDetails.AsSeason) contentFragmentDetails).episodes().pageInfo().total();
                if (i == null) {
                    i = 0;
                }
                singleJust = Single.just(i);
            } else {
                single = null;
            }
            if (z2) {
                vODParser$parseItem$episodes$2 = new VODParser$parseItem$episodes$1(boService, contentBase);
            } else if (z8) {
                vODParser$parseItem$episodes$2 = new VODParser$parseItem$episodes$2(boService, contentBase);
            } else {
                vODParser$parseItem$episodes$2 = new Function1<ItemSortingOrder, Observable<Episode>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$episodes$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Observable<Episode> invoke(ItemSortingOrder it12) {
                        Intrinsics.checkNotNullParameter(it12, "it");
                        Observable<Episode> observableEmpty3 = Observable.empty();
                        Intrinsics.checkNotNullExpressionValue(observableEmpty3, "Observable.empty()");
                        return observableEmpty3;
                    }
                };
            }
            function1 = vODParser$parseItem$episodes$2;
            if (contentFragmentDetails != null) {
                listEmptyList = CollectionsKt.emptyList();
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            arrayList = new ArrayList();
            while (r1.hasNext()) {
                if (((ContentFragmentDetails.Crew) obj).department() == CrewDepartment.DIRECTORS) {
                    z7 = true;
                } else {
                    z7 = z3;
                }
                if (z7) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList114 = arrayList;
            arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList114, 10));
            it = arrayList114.iterator();
            while (true) {
                str2 = "it.person().id()";
                if (it.hasNext()) {
                    ContentFragmentDetails.Crew crew3 = (ContentFragmentDetails.Crew) it.next();
                    Iterator it12 = it;
                    String strId1110 = crew3.person().id();
                    Intrinsics.checkNotNullExpressionValue(strId1110, "it.person().id()");
                    String strName13 = crew3.person().name();
                    Intrinsics.checkNotNullExpressionValue(strName13, "it.person().name()");
                    arrayList2.add(new Person(strId1110, strName13, crew3.task(), null, false, 24, null));
                    it = it12;
                } else {
                    ArrayList arrayList115 = arrayList2;
                    if (contentFragmentDetails != null) {
                        listEmptyList2 = CollectionsKt.emptyList();
                    } else {
                        listEmptyList2 = CollectionsKt.emptyList();
                    }
                    arrayList3 = new ArrayList();
                    it2 = listEmptyList2.iterator();
                    while (it2.hasNext()) {
                        next = it2.next();
                        Iterator it13 = it2;
                        if (((ContentFragmentDetails.Cast) next).roleType() == RoleCategory.ACTOR) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        if (z6) {
                            arrayList3.add(next);
                        }
                        it2 = it13;
                    }
                    ArrayList arrayList116 = arrayList3;
                    arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList116, 10));
                    it3 = arrayList116.iterator();
                    while (it3.hasNext()) {
                        ContentFragmentDetails.Cast cast3 = (ContentFragmentDetails.Cast) it3.next();
                        Iterator it14 = it3;
                        String strId1111 = cast3.person().id();
                        Intrinsics.checkNotNullExpressionValue(strId1111, str2);
                        String str8 = str2;
                        String strName14 = cast3.person().name();
                        Intrinsics.checkNotNullExpressionValue(strName14, "it.person().name()");
                        arrayList4.add(new Person(strId1111, strName14, cast3.role(), null, false, 24, null));
                        it3 = it14;
                        str2 = str8;
                    }
                    arrayList5 = arrayList4;
                    if (contentFragmentDetails != null) {
                        listEmptyList3 = CollectionsKt.emptyList();
                    } else {
                        listEmptyList3 = CollectionsKt.emptyList();
                    }
                    List<? extends ContentFragmentDetails.Preview> list5 = listEmptyList3;
                    arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
                    while (r25.hasNext()) {
                        String strStreamId3 = preview.streamId();
                        Intrinsics.checkNotNullExpressionValue(strStreamId3, "preview.streamId()");
                        strDescription = preview.description();
                        if (strDescription != null) {
                            strDescription = "";
                        }
                        String str9 = strDescription;
                        Intrinsics.checkNotNullExpressionValue(str9, "preview.description() ?: \"\"");
                        Observable observableEmpty3 = Observable.empty();
                        Intrinsics.checkNotNullExpressionValue(observableEmpty3, "Observable.empty()");
                        ArrayList arrayList117 = arrayList6;
                        arrayList117.add(new Media(boService, strStreamId3, str9, null, arrayList11, null, false, null, observableEmpty3, 232, null));
                        arrayList6 = arrayList117;
                        z8 = z8;
                        z = z;
                        arrayList13 = arrayList13;
                        arrayList12 = arrayList12;
                        str = str;
                        arrayList11 = arrayList11;
                    }
                    str3 = str;
                    z4 = z;
                    z5 = z8;
                    arrayList7 = arrayList13;
                    arrayList8 = arrayList12;
                    arrayList9 = arrayList11;
                    arrayList10 = arrayList6;
                    if (contentFragmentDetails != null) {
                        size = 0;
                    } else {
                        size = 0;
                    }
                    if (size <= 0) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                    }
                    i3 = 10;
                    strArr = null;
                    if (contentFragmentDetails != null) {
                        size2 = i2;
                    } else {
                        size2 = i2;
                    }
                    if (size2 > 0) {
                        strArr2 = null;
                    } else {
                        strArr2 = null;
                    }
                    array = arrayList115.toArray(new Person[i2]);
                    if (array == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    personArr = (Person[]) array;
                    array2 = arrayList5.toArray(new Person[i2]);
                    if (array2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    personArr2 = (Person[]) array2;
                    array3 = arrayList8.toArray(new String[i2]);
                    if (array3 != null) {
                        strArr3 = (String[]) array3;
                        if (strArr == null) {
                            strArr = new String[i2];
                        }
                        strArr4 = strArr;
                        strArr5 = null;
                        if (strArr2 == null) {
                            strArr2 = new String[i2];
                        }
                        strArr6 = strArr2;
                        observableFromIterable = Observable.fromIterable(arrayList10);
                        dOriginalDuration = contentBase.originalDuration();
                        if (dOriginalDuration != null) {
                            numValueOf = Integer.valueOf((int) dOriginalDuration.doubleValue());
                        } else {
                            numValueOf = null;
                        }
                        if (contentBase.year() != null) {
                            numYear = contentBase.year();
                        } else {
                            numYear = null;
                        }
                        artworkArr = null;
                        observable = null;
                        itemCollection = null;
                        itemCollection2 = null;
                        d = null;
                        array4 = arrayList7.toArray(new Classifier[i2]);
                        if (array4 != null) {
                            mediaMetadata = new MediaMetadata(personArr, personArr2, strArr3, numValueOf, strArr5, strArr4, strArr6, numYear, (Classifier[]) array4, artworkArr, observableFromIterable, observable, itemCollection, itemCollection2, d, parentalRating3, 31248, null);
                            linkedHashMap = new LinkedHashMap();
                            List<ContentFragmentBase.ExternalId> listExternalIds4 = contentBase.externalIds();
                            Intrinsics.checkNotNullExpressionValue(listExternalIds4, "contentBase.externalIds()");
                            while (r2.hasNext()) {
                                itemExternalIdSchemeArrValues = ItemExternalIdScheme.values();
                                length = itemExternalIdSchemeArrValues.length;
                                while (i4 < length) {
                                    itemExternalIdScheme = itemExternalIdSchemeArrValues[i4];
                                    if (Intrinsics.areEqual(itemExternalIdScheme.getScheme(), externalId.scheme().rawValue())) {
                                        String strIdentifier4 = externalId.identifier();
                                        Intrinsics.checkNotNullExpressionValue(strIdentifier4, "externalId.identifier()");
                                        linkedHashMap.put(itemExternalIdScheme, strIdentifier4);
                                    }
                                }
                            }
                            switch (contentBase.__typename()) {
                                case -1822468349:
                                    String strId1112 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId1112, str3);
                                    String strTitle16 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle16, "contentBase.title()");
                                    String strDescription8 = contentBase.description();
                                    ArrayList arrayList26 = arrayList9;
                                    if (!z5) {
                                        contentFragmentDetails = null;
                                    }
                                    asSeason = (ContentFragmentDetails.AsSeason) contentFragmentDetails;
                                    if (asSeason != null) {
                                        numValueOf2 = Integer.valueOf(i2);
                                    } else {
                                        numValueOf2 = Integer.valueOf(i2);
                                    }
                                    Intrinsics.checkNotNullExpressionValue(numValueOf2, "(contentDetails as? Cont…                     ?: 0");
                                    return new TVSeason(boService, strId1112, strTitle16, strDescription8, arrayList26, null, numValueOf2.intValue(), mediaMetadata, maybe, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 2080, null);
                                case -1821971817:
                                    String strId1113 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId1113, str3);
                                    String strTitle17 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle17, "contentBase.title()");
                                    return new TVSeries(boService, strId1113, strTitle17, contentBase.description(), arrayList9, null, mediaMetadata, observableFlatMapObservable, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                                case 74534672:
                                    String strId1114 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId1114, str3);
                                    String strTitle18 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle18, "contentBase.title()");
                                    return new Movie(boService, strId1114, strTitle18, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                                case 120215003:
                                    String strId1115 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId1115, str3);
                                    String strTitle19 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle19, "contentBase.title()");
                                    String strDescription9 = contentBase.description();
                                    ArrayList arrayList27 = arrayList9;
                                    if (!z4) {
                                        contentFragmentDetails = null;
                                    }
                                    asEpisode = (ContentFragmentDetails.AsEpisode) contentFragmentDetails;
                                    if (asEpisode != null) {
                                        numValueOf3 = Integer.valueOf(i2);
                                    } else {
                                        numValueOf3 = Integer.valueOf(i2);
                                    }
                                    Intrinsics.checkNotNullExpressionValue(numValueOf3, "(contentDetails as? Cont…                     ?: 0");
                                    return new Episode(boService, strId1115, strTitle19, strDescription9, arrayList27, null, numValueOf3.intValue(), mediaMetadata, maybe, map2, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                                case 730734274:
                                    String strId1116 = contentBase.id();
                                    Intrinsics.checkNotNullExpressionValue(strId1116, str3);
                                    String strTitle110 = contentBase.title();
                                    Intrinsics.checkNotNullExpressionValue(strTitle110, "contentBase.title()");
                                    return new Content(boService, strId1116, strTitle110, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                                default:
                                    return null;
                            }
                        }
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
        }
        single = singleJust;
        if (z2) {
            vODParser$parseItem$episodes$2 = new VODParser$parseItem$episodes$1(boService, contentBase);
        } else if (z8) {
            vODParser$parseItem$episodes$2 = new VODParser$parseItem$episodes$2(boService, contentBase);
        } else {
            vODParser$parseItem$episodes$2 = new Function1<ItemSortingOrder, Observable<Episode>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseItem$episodes$3
                @Override // kotlin.jvm.functions.Function1
                public final Observable<Episode> invoke(ItemSortingOrder it15) {
                    Intrinsics.checkNotNullParameter(it15, "it");
                    Observable<Episode> observableEmpty4 = Observable.empty();
                    Intrinsics.checkNotNullExpressionValue(observableEmpty4, "Observable.empty()");
                    return observableEmpty4;
                }
            };
        }
        function1 = vODParser$parseItem$episodes$2;
        if (contentFragmentDetails != null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        arrayList = new ArrayList();
        while (r1.hasNext()) {
            if (((ContentFragmentDetails.Crew) obj).department() == CrewDepartment.DIRECTORS) {
                z7 = true;
            } else {
                z7 = z3;
            }
            if (z7) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList118 = arrayList;
        arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList118, 10));
        it = arrayList118.iterator();
        while (true) {
            str2 = "it.person().id()";
            if (it.hasNext()) {
                ContentFragmentDetails.Crew crew4 = (ContentFragmentDetails.Crew) it.next();
                Iterator it15 = it;
                String strId1117 = crew4.person().id();
                Intrinsics.checkNotNullExpressionValue(strId1117, "it.person().id()");
                String strName15 = crew4.person().name();
                Intrinsics.checkNotNullExpressionValue(strName15, "it.person().name()");
                arrayList2.add(new Person(strId1117, strName15, crew4.task(), null, false, 24, null));
                it = it15;
            } else {
                ArrayList arrayList119 = arrayList2;
                if (contentFragmentDetails != null) {
                    listEmptyList2 = CollectionsKt.emptyList();
                } else {
                    listEmptyList2 = CollectionsKt.emptyList();
                }
                arrayList3 = new ArrayList();
                it2 = listEmptyList2.iterator();
                while (it2.hasNext()) {
                    next = it2.next();
                    Iterator it16 = it2;
                    if (((ContentFragmentDetails.Cast) next).roleType() == RoleCategory.ACTOR) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    if (z6) {
                        arrayList3.add(next);
                    }
                    it2 = it16;
                }
                ArrayList arrayList1110 = arrayList3;
                arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList1110, 10));
                it3 = arrayList1110.iterator();
                while (it3.hasNext()) {
                    ContentFragmentDetails.Cast cast4 = (ContentFragmentDetails.Cast) it3.next();
                    Iterator it17 = it3;
                    String strId1118 = cast4.person().id();
                    Intrinsics.checkNotNullExpressionValue(strId1118, str2);
                    String str10 = str2;
                    String strName16 = cast4.person().name();
                    Intrinsics.checkNotNullExpressionValue(strName16, "it.person().name()");
                    arrayList4.add(new Person(strId1118, strName16, cast4.role(), null, false, 24, null));
                    it3 = it17;
                    str2 = str10;
                }
                arrayList5 = arrayList4;
                if (contentFragmentDetails != null) {
                    listEmptyList3 = CollectionsKt.emptyList();
                } else {
                    listEmptyList3 = CollectionsKt.emptyList();
                }
                List<? extends ContentFragmentDetails.Preview> list6 = listEmptyList3;
                arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list6, 10));
                while (r25.hasNext()) {
                    String strStreamId4 = preview.streamId();
                    Intrinsics.checkNotNullExpressionValue(strStreamId4, "preview.streamId()");
                    strDescription = preview.description();
                    if (strDescription != null) {
                        strDescription = "";
                    }
                    String str11 = strDescription;
                    Intrinsics.checkNotNullExpressionValue(str11, "preview.description() ?: \"\"");
                    Observable observableEmpty4 = Observable.empty();
                    Intrinsics.checkNotNullExpressionValue(observableEmpty4, "Observable.empty()");
                    ArrayList arrayList1111 = arrayList6;
                    arrayList1111.add(new Media(boService, strStreamId4, str11, null, arrayList11, null, false, null, observableEmpty4, 232, null));
                    arrayList6 = arrayList1111;
                    z8 = z8;
                    z = z;
                    arrayList13 = arrayList13;
                    arrayList12 = arrayList12;
                    str = str;
                    arrayList11 = arrayList11;
                }
                str3 = str;
                z4 = z;
                z5 = z8;
                arrayList7 = arrayList13;
                arrayList8 = arrayList12;
                arrayList9 = arrayList11;
                arrayList10 = arrayList6;
                if (contentFragmentDetails != null) {
                    size = 0;
                } else {
                    size = 0;
                }
                if (size <= 0) {
                    i2 = 0;
                } else {
                    i2 = 0;
                }
                i3 = 10;
                strArr = null;
                if (contentFragmentDetails != null) {
                    size2 = i2;
                } else {
                    size2 = i2;
                }
                if (size2 > 0) {
                    strArr2 = null;
                } else {
                    strArr2 = null;
                }
                array = arrayList119.toArray(new Person[i2]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                personArr = (Person[]) array;
                array2 = arrayList5.toArray(new Person[i2]);
                if (array2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                personArr2 = (Person[]) array2;
                array3 = arrayList8.toArray(new String[i2]);
                if (array3 != null) {
                    strArr3 = (String[]) array3;
                    if (strArr == null) {
                        strArr = new String[i2];
                    }
                    strArr4 = strArr;
                    strArr5 = null;
                    if (strArr2 == null) {
                        strArr2 = new String[i2];
                    }
                    strArr6 = strArr2;
                    observableFromIterable = Observable.fromIterable(arrayList10);
                    dOriginalDuration = contentBase.originalDuration();
                    if (dOriginalDuration != null) {
                        numValueOf = Integer.valueOf((int) dOriginalDuration.doubleValue());
                    } else {
                        numValueOf = null;
                    }
                    if (contentBase.year() != null) {
                        numYear = contentBase.year();
                    } else {
                        numYear = null;
                    }
                    artworkArr = null;
                    observable = null;
                    itemCollection = null;
                    itemCollection2 = null;
                    d = null;
                    array4 = arrayList7.toArray(new Classifier[i2]);
                    if (array4 != null) {
                        mediaMetadata = new MediaMetadata(personArr, personArr2, strArr3, numValueOf, strArr5, strArr4, strArr6, numYear, (Classifier[]) array4, artworkArr, observableFromIterable, observable, itemCollection, itemCollection2, d, parentalRating3, 31248, null);
                        linkedHashMap = new LinkedHashMap();
                        List<ContentFragmentBase.ExternalId> listExternalIds5 = contentBase.externalIds();
                        Intrinsics.checkNotNullExpressionValue(listExternalIds5, "contentBase.externalIds()");
                        while (r2.hasNext()) {
                            itemExternalIdSchemeArrValues = ItemExternalIdScheme.values();
                            length = itemExternalIdSchemeArrValues.length;
                            while (i4 < length) {
                                itemExternalIdScheme = itemExternalIdSchemeArrValues[i4];
                                if (Intrinsics.areEqual(itemExternalIdScheme.getScheme(), externalId.scheme().rawValue())) {
                                    String strIdentifier5 = externalId.identifier();
                                    Intrinsics.checkNotNullExpressionValue(strIdentifier5, "externalId.identifier()");
                                    linkedHashMap.put(itemExternalIdScheme, strIdentifier5);
                                }
                            }
                        }
                        switch (contentBase.__typename()) {
                            case -1822468349:
                                String strId1119 = contentBase.id();
                                Intrinsics.checkNotNullExpressionValue(strId1119, str3);
                                String strTitle111 = contentBase.title();
                                Intrinsics.checkNotNullExpressionValue(strTitle111, "contentBase.title()");
                                String strDescription10 = contentBase.description();
                                ArrayList arrayList28 = arrayList9;
                                if (!z5) {
                                    contentFragmentDetails = null;
                                }
                                asSeason = (ContentFragmentDetails.AsSeason) contentFragmentDetails;
                                if (asSeason != null) {
                                    numValueOf2 = Integer.valueOf(i2);
                                } else {
                                    numValueOf2 = Integer.valueOf(i2);
                                }
                                Intrinsics.checkNotNullExpressionValue(numValueOf2, "(contentDetails as? Cont…                     ?: 0");
                                return new TVSeason(boService, strId1119, strTitle111, strDescription10, arrayList28, null, numValueOf2.intValue(), mediaMetadata, maybe, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 2080, null);
                            case -1821971817:
                                String strId11110 = contentBase.id();
                                Intrinsics.checkNotNullExpressionValue(strId11110, str3);
                                String strTitle112 = contentBase.title();
                                Intrinsics.checkNotNullExpressionValue(strTitle112, "contentBase.title()");
                                return new TVSeries(boService, strId11110, strTitle112, contentBase.description(), arrayList9, null, mediaMetadata, observableFlatMapObservable, single, function1, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                            case 74534672:
                                String strId11111 = contentBase.id();
                                Intrinsics.checkNotNullExpressionValue(strId11111, str3);
                                String strTitle113 = contentBase.title();
                                Intrinsics.checkNotNullExpressionValue(strTitle113, "contentBase.title()");
                                return new Movie(boService, strId11111, strTitle113, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                            case 120215003:
                                String strId11112 = contentBase.id();
                                Intrinsics.checkNotNullExpressionValue(strId11112, str3);
                                String strTitle114 = contentBase.title();
                                Intrinsics.checkNotNullExpressionValue(strTitle114, "contentBase.title()");
                                String strDescription11 = contentBase.description();
                                ArrayList arrayList29 = arrayList9;
                                if (!z4) {
                                    contentFragmentDetails = null;
                                }
                                asEpisode = (ContentFragmentDetails.AsEpisode) contentFragmentDetails;
                                if (asEpisode != null) {
                                    numValueOf3 = Integer.valueOf(i2);
                                } else {
                                    numValueOf3 = Integer.valueOf(i2);
                                }
                                Intrinsics.checkNotNullExpressionValue(numValueOf3, "(contentDetails as? Cont…                     ?: 0");
                                return new Episode(boService, strId11112, strTitle114, strDescription11, arrayList29, null, numValueOf3.intValue(), mediaMetadata, maybe, map2, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 1056, null);
                            case 730734274:
                                String strId11113 = contentBase.id();
                                Intrinsics.checkNotNullExpressionValue(strId11113, str3);
                                String strTitle115 = contentBase.title();
                                Intrinsics.checkNotNullExpressionValue(strTitle115, "contentBase.title()");
                                return new Content(boService, strId11113, strTitle115, contentBase.description(), arrayList9, null, mediaMetadata, false, MapsKt.toMap(linkedHashMap), parseVODOffers(contentBase), 160, null);
                            default:
                                return null;
                        }
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
    }

    public final Single<VODCatalogSearchResponse> parseCatalogSearch(final BOService boService, final List<? extends VODContentSearchQuery.ContentSearch> results) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        Intrinsics.checkNotNullParameter(results, "results");
        Single<VODCatalogSearchResponse> singleCreate = Single.create(new SingleOnSubscribe<VODCatalogSearchResponse>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.parseCatalogSearch.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<VODCatalogSearchResponse> it) throws NotImplementedException {
                Intrinsics.checkNotNullParameter(it, "it");
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                final ArrayList arrayList3 = new ArrayList();
                final ArrayList arrayList4 = new ArrayList();
                for (VODContentSearchQuery.ContentSearch contentSearch : results) {
                    VODParser vODParser = VODParser.INSTANCE;
                    BOService bOService = boService;
                    ContentFragmentBase contentFragmentBase = contentSearch.fragments().contentFragmentBase();
                    Intrinsics.checkNotNullExpressionValue(contentFragmentBase, "result.fragments().contentFragmentBase()");
                    BrowsableItem item = vODParser.parseItem(bOService, contentFragmentBase, null);
                    if (item instanceof Movie) {
                        arrayList.add(item);
                    } else if (item instanceof TVSeries) {
                        arrayList2.add(item);
                    } else if (item instanceof TVSeason) {
                        arrayList3.add(item);
                    } else if (item instanceof Episode) {
                        arrayList4.add(item);
                    }
                }
                it.onSuccess(new VODCatalogSearchResponse(new ItemCollection(boService, "movies", "movies", null, CollectionsKt.emptyList(), null, false, null, Single.just(Integer.valueOf(arrayList.size())), new Function1<ItemSortingOrder, Observable<Movie>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseCatalogSearch$1$moviesCollection$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Observable<Movie> invoke(ItemSortingOrder order) {
                        Intrinsics.checkNotNullParameter(order, "order");
                        VODParser vODParser2 = VODParser.INSTANCE;
                        Observable observableFromIterable = Observable.fromIterable(arrayList);
                        Intrinsics.checkNotNullExpressionValue(observableFromIterable, "Observable.fromIterable(movies)");
                        return vODParser2.sortItems(order, observableFromIterable);
                    }
                }, null, null, 3072, null), new ItemCollection(boService, "tvSeries", "tvSeries", null, CollectionsKt.emptyList(), null, false, null, Single.just(Integer.valueOf(arrayList2.size())), new Function1<ItemSortingOrder, Observable<TVSeries>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseCatalogSearch$1$tvSeriesCollection$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Observable<TVSeries> invoke(ItemSortingOrder order) {
                        Intrinsics.checkNotNullParameter(order, "order");
                        VODParser vODParser2 = VODParser.INSTANCE;
                        Observable observableFromIterable = Observable.fromIterable(arrayList2);
                        Intrinsics.checkNotNullExpressionValue(observableFromIterable, "Observable.fromIterable(tvSeries)");
                        return vODParser2.sortItems(order, observableFromIterable);
                    }
                }, null, null, 3072, null), new ItemCollection(boService, "tvSeasons", "tvSeasons", null, CollectionsKt.emptyList(), null, false, null, Single.just(Integer.valueOf(arrayList3.size())), new Function1<ItemSortingOrder, Observable<TVSeason>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseCatalogSearch$1$tvSeasonsCollection$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Observable<TVSeason> invoke(ItemSortingOrder order) {
                        Intrinsics.checkNotNullParameter(order, "order");
                        VODParser vODParser2 = VODParser.INSTANCE;
                        Observable observableFromIterable = Observable.fromIterable(arrayList3);
                        Intrinsics.checkNotNullExpressionValue(observableFromIterable, "Observable.fromIterable(tvSeasons)");
                        return vODParser2.sortItems(order, observableFromIterable);
                    }
                }, null, null, 3072, null), new ItemCollection(boService, "episodes", "episodes", null, CollectionsKt.emptyList(), null, false, null, Single.just(Integer.valueOf(arrayList4.size())), new Function1<ItemSortingOrder, Observable<Episode>>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser$parseCatalogSearch$1$episodesCollection$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Observable<Episode> invoke(ItemSortingOrder order) {
                        Intrinsics.checkNotNullParameter(order, "order");
                        VODParser vODParser2 = VODParser.INSTANCE;
                        Observable observableFromIterable = Observable.fromIterable(arrayList4);
                        Intrinsics.checkNotNullExpressionValue(observableFromIterable, "Observable.fromIterable(episodes)");
                        return vODParser2.sortItems(order, observableFromIterable);
                    }
                }, null, null, 3072, null)));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create<VODCatalog…desCollection))\n        }");
        return singleCreate;
    }

    public final Observable<Offer> parseVODOffers(final VODContentOffersQuery.LookupContent item) {
        Intrinsics.checkNotNullParameter(item, "item");
        Observable<Offer> observableCreate = Observable.create(new ObservableOnSubscribe<Offer>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.parseVODOffers.1
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter<Offer> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                List<VODContentOffersQuery.Item> listItems = item.offers().items();
                Intrinsics.checkNotNullExpressionValue(listItems, "item.offers().items()");
                if (listItems != null) {
                    for (VODContentOffersQuery.Item item2 : listItems) {
                        if (item2 instanceof VODContentOffersQuery.AsTransactionalVOD) {
                            VODContentOffersQuery.AsTransactionalVOD asTransactionalVOD = (VODContentOffersQuery.AsTransactionalVOD) item2;
                            String strStreamId = asTransactionalVOD.streamId();
                            Intrinsics.checkNotNullExpressionValue(strStreamId, "offer.streamId()");
                            String strName = asTransactionalVOD.name();
                            Intrinsics.checkNotNullExpressionValue(strName, "offer.name()");
                            it.onNext(new Offer(strStreamId, strName, null, CollectionsKt.emptyList(), null, OfferType.Transactional, asTransactionalVOD.price().amount(), asTransactionalVOD.price().currency().rawValue()));
                        } else if (item2 instanceof VODContentOffersQuery.AsSubscriptionVOD) {
                            VODContentOffersQuery.AsSubscriptionVOD asSubscriptionVOD = (VODContentOffersQuery.AsSubscriptionVOD) item2;
                            String strStreamId2 = asSubscriptionVOD.streamId();
                            Intrinsics.checkNotNullExpressionValue(strStreamId2, "offer.streamId()");
                            String strName2 = asSubscriptionVOD.name();
                            Intrinsics.checkNotNullExpressionValue(strName2, "offer.name()");
                            it.onNext(new Offer(strStreamId2, strName2, null, CollectionsKt.emptyList(), null, OfferType.Subscription, 0.0d, null, PsExtractor.AUDIO_STREAM, null));
                        } else if (item2 instanceof VODContentOffersQuery.AsCatchUpTV) {
                            VODContentOffersQuery.AsCatchUpTV asCatchUpTV = (VODContentOffersQuery.AsCatchUpTV) item2;
                            String strStreamId3 = asCatchUpTV.streamId();
                            Intrinsics.checkNotNullExpressionValue(strStreamId3, "offer.streamId()");
                            String strName3 = asCatchUpTV.name();
                            Intrinsics.checkNotNullExpressionValue(strName3, "offer.name()");
                            it.onNext(new Offer(strStreamId3, strName3, null, CollectionsKt.emptyList(), null, OfferType.CatchupTV, 0.0d, null, PsExtractor.AUDIO_STREAM, null));
                        } else if (item2 instanceof VODContentOffersQuery.AsExternalOffer) {
                            String str__typename = item2.__typename();
                            Intrinsics.checkNotNullExpressionValue(str__typename, "offer.__typename()");
                            VODContentOffersQuery.AsExternalOffer asExternalOffer = (VODContentOffersQuery.AsExternalOffer) item2;
                            String strName4 = asExternalOffer.name();
                            Intrinsics.checkNotNullExpressionValue(strName4, "offer.name()");
                            it.onNext(new Offer(str__typename, strName4, null, CollectionsKt.emptyList(), asExternalOffer.location(), OfferType.ExternalOffer, 0.0d, null, PsExtractor.AUDIO_STREAM, null));
                        } else {
                            Log.e(VODParser.access$getTAG$p(VODParser.INSTANCE), "Unknown type of offer");
                        }
                    }
                }
                it.onComplete();
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "Observable.create<Offer>…t.onComplete()\n\n        }");
        return observableCreate;
    }

    private final Observable<Offer> parseVODOffers(final ContentFragmentBase item) {
        Observable<Offer> observableCreate = Observable.create(new ObservableOnSubscribe<Offer>() { // from class: com.dotscreen.bokit.internal.parsers.VODParser.parseVODOffers.2
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter<Offer> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                List<ContentFragmentBase.Item> listItems = item.offers().items();
                Intrinsics.checkNotNullExpressionValue(listItems, "item.offers().items()");
                if (listItems != null) {
                    for (ContentFragmentBase.Item item2 : listItems) {
                        if (item2 instanceof ContentFragmentBase.AsTransactionalVOD) {
                            ContentFragmentBase.AsTransactionalVOD asTransactionalVOD = (ContentFragmentBase.AsTransactionalVOD) item2;
                            String strStreamId = asTransactionalVOD.streamId();
                            Intrinsics.checkNotNullExpressionValue(strStreamId, "offer.streamId()");
                            String strName = asTransactionalVOD.name();
                            Intrinsics.checkNotNullExpressionValue(strName, "offer.name()");
                            it.onNext(new Offer(strStreamId, strName, null, CollectionsKt.emptyList(), null, OfferType.Transactional, asTransactionalVOD.price().amount(), asTransactionalVOD.price().currency().rawValue()));
                        } else if (item2 instanceof ContentFragmentBase.AsSubscriptionVOD) {
                            ContentFragmentBase.AsSubscriptionVOD asSubscriptionVOD = (ContentFragmentBase.AsSubscriptionVOD) item2;
                            String strStreamId2 = asSubscriptionVOD.streamId();
                            Intrinsics.checkNotNullExpressionValue(strStreamId2, "offer.streamId()");
                            String strName2 = asSubscriptionVOD.name();
                            Intrinsics.checkNotNullExpressionValue(strName2, "offer.name()");
                            it.onNext(new Offer(strStreamId2, strName2, null, CollectionsKt.emptyList(), null, OfferType.Subscription, 0.0d, null, PsExtractor.AUDIO_STREAM, null));
                        } else if (item2 instanceof ContentFragmentBase.AsCatchUpTV) {
                            ContentFragmentBase.AsCatchUpTV asCatchUpTV = (ContentFragmentBase.AsCatchUpTV) item2;
                            String strStreamId3 = asCatchUpTV.streamId();
                            Intrinsics.checkNotNullExpressionValue(strStreamId3, "offer.streamId()");
                            String strName3 = asCatchUpTV.name();
                            Intrinsics.checkNotNullExpressionValue(strName3, "offer.name()");
                            it.onNext(new Offer(strStreamId3, strName3, null, CollectionsKt.emptyList(), null, OfferType.CatchupTV, 0.0d, null, PsExtractor.AUDIO_STREAM, null));
                        } else if (item2 instanceof ContentFragmentBase.AsExternalOffer) {
                            ContentFragmentBase.AsExternalOffer asExternalOffer = (ContentFragmentBase.AsExternalOffer) item2;
                            String strName4 = asExternalOffer.name();
                            Intrinsics.checkNotNullExpressionValue(strName4, "offer.name()");
                            String strName5 = asExternalOffer.name();
                            Intrinsics.checkNotNullExpressionValue(strName5, "offer.name()");
                            it.onNext(new Offer(strName4, strName5, null, CollectionsKt.emptyList(), asExternalOffer.location(), OfferType.ExternalOffer, 0.0d, null, PsExtractor.AUDIO_STREAM, null));
                        } else {
                            Log.e(VODParser.access$getTAG$p(VODParser.INSTANCE), "Unknown type of offer");
                        }
                    }
                }
                it.onComplete();
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "Observable.create<Offer>…t.onComplete()\n\n        }");
        return observableCreate;
    }

    public final PlaybackInfo parsePlaybackInfos(List<? extends VODPlaybackInfosQuery.Option> options) {
        Intrinsics.checkNotNullParameter(options, "options");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (VODPlaybackInfosQuery.Option option : options) {
            String strContentType = option.contentType();
            if (Intrinsics.areEqual(strContentType, PlaybackInfoMimeType.DASH.getMime())) {
                PlaybackInfoMimeType playbackInfoMimeType = PlaybackInfoMimeType.DASH;
                String strSource = option.source();
                Intrinsics.checkNotNullExpressionValue(strSource, "option.source()");
                linkedHashMap.put(playbackInfoMimeType, strSource);
            } else if (Intrinsics.areEqual(strContentType, PlaybackInfoMimeType.HLS.getMime())) {
                PlaybackInfoMimeType playbackInfoMimeType2 = PlaybackInfoMimeType.HLS;
                String strSource2 = option.source();
                Intrinsics.checkNotNullExpressionValue(strSource2, "option.source()");
                linkedHashMap.put(playbackInfoMimeType2, strSource2);
            } else if (Intrinsics.areEqual(strContentType, PlaybackInfoMimeType.SMOOTHSTREAMING.getMime())) {
                PlaybackInfoMimeType playbackInfoMimeType3 = PlaybackInfoMimeType.SMOOTHSTREAMING;
                String strSource3 = option.source();
                Intrinsics.checkNotNullExpressionValue(strSource3, "option.source()");
                linkedHashMap.put(playbackInfoMimeType3, strSource3);
            }
        }
        return new PlaybackInfo(0L, linkedHashMap);
    }
}
