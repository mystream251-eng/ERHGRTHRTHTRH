package com.dotscreen.portal.ui.screens.browse;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.app.RowsSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.model.LoadingWidget;
import com.dotscreen.portal.ui.RoundListRowPresenter;
import com.dotscreen.portal.ui.screens.Activity_StartIntentKt;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.ISwimlanesFragment;
import com.dotscreen.portal.ui.screens.profile.PinCodeDialog;
import com.dotscreen.portal.ui.views.HighlightsHeroZoneView;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.bokit.RightVUser;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.InterruptedIOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: BrowseCollectionFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 %2\u00020\u00012\u00020\u0002:\u0003%&'B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\u0012\u0010\u001e\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010!\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010\"\u001a\u00020\u001cH\u0016J\b\u0010#\u001a\u00020\u001cH\u0002J\b\u0010$\u001a\u00020\u001cH\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/dotscreen/portal/ui/screens/browse/BrowseCollectionFragment;", "Landroidx/leanback/app/BrowseSupportFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "adultBrowsing", "", "catalogId", "collectionId", "fragmentAlignment", "", "Ljava/lang/Integer;", "handler", "Landroid/os/Handler;", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "createContentRows", "", "createRows", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onStart", "setupEventListeners", "setupUIElements", "Companion", "ItemViewClickedListener", "ItemViewSelectedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class BrowseCollectionFragment extends BrowseSupportFragment implements ISwimlanesFragment {
    public static final String ADULT_BROWSING = "arg_adult_browsing";
    public static final String CATALOG_ID = "arg_catalog_id";
    public static final String COLLECTION_ID = "arg_collection_id";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap _$_findViewCache;
    private boolean adultBrowsing;
    private String catalogId;
    private String collectionId;
    private Integer fragmentAlignment;
    private ArrayObjectAdapter rowsAdapter;
    private final String TAG = BrowseCollectionFragment.class.getSimpleName();
    private final Handler handler = new Handler();
    private final SparseArray<ListRow> listRows = new SparseArray<>();

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // androidx.leanback.app.BrowseSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.dotscreen.portal.ui.screens.ISwimlanesFragment
    public void addRowSorted(Row newRow) {
        Intrinsics.checkParameterIsNotNull(newRow, "newRow");
        ISwimlanesFragment.DefaultImpls.addRowSorted(this, newRow);
    }

    @Override // com.dotscreen.portal.ui.screens.ISwimlanesFragment
    public <T> void createOrUpdateRow(int i, HeaderItem headerItem, List<? extends T> list, BaseWidget baseWidget, Presenter presenter, PresenterSelector presenterSelector, boolean z, Class<? extends ListRow> listRowClass) {
        Intrinsics.checkParameterIsNotNull(listRowClass, "listRowClass");
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow(this, i, headerItem, list, baseWidget, presenter, presenterSelector, z, listRowClass);
    }

    @Override // com.dotscreen.portal.ui.screens.ISwimlanesFragment
    public ArrayObjectAdapter getRowsAdapter() {
        return this.rowsAdapter;
    }

    @Override // com.dotscreen.portal.ui.screens.ISwimlanesFragment
    public void setRowsAdapter(ArrayObjectAdapter arrayObjectAdapter) {
        this.rowsAdapter = arrayObjectAdapter;
    }

    @Override // com.dotscreen.portal.ui.screens.ISwimlanesFragment
    public SparseArray<ListRow> getListRows() {
        return this.listRows;
    }

    /* JADX INFO: compiled from: BrowseCollectionFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/browse/BrowseCollectionFragment$Companion;", "", "()V", "ADULT_BROWSING", "", "CATALOG_ID", "COLLECTION_ID", "newInstance", "Lcom/dotscreen/portal/ui/screens/browse/BrowseCollectionFragment;", "catalogId", "collectionId", "adultBrowsing", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ BrowseCollectionFragment newInstance$default(Companion companion, String str, String str2, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = (String) null;
            }
            return companion.newInstance(str, str2, z);
        }

        public final BrowseCollectionFragment newInstance(String catalogId, String collectionId, boolean adultBrowsing) {
            Intrinsics.checkParameterIsNotNull(catalogId, "catalogId");
            BrowseCollectionFragment browseCollectionFragment = new BrowseCollectionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("arg_catalog_id", catalogId);
            bundle.putString("arg_collection_id", collectionId);
            bundle.putBoolean("arg_adult_browsing", adultBrowsing);
            browseCollectionFragment.setArguments(bundle);
            return browseCollectionFragment;
        }
    }

    @Override // androidx.leanback.app.BrowseSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Integer num;
        super.onStart();
        RowsSupportFragment rowsSupportFragment = getRowsSupportFragment();
        if (rowsSupportFragment == null || (num = this.fragmentAlignment) == null) {
            return;
        }
        rowsSupportFragment.setAlignment(num.intValue());
    }

    @Override // androidx.leanback.app.BrowseSupportFragment, androidx.leanback.app.BaseSupportFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.catalogId = arguments != null ? arguments.getString("arg_catalog_id") : null;
        Bundle arguments2 = getArguments();
        this.collectionId = arguments2 != null ? arguments2.getString("arg_collection_id") : null;
        Bundle arguments3 = getArguments();
        this.adultBrowsing = arguments3 != null ? arguments3.getBoolean("arg_adult_browsing") : false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUIElements();
        setupEventListeners();
        createRows();
    }

    private final void createContentRows() {
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) getActivity();
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        Constants constants = Constants.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        String str = this.catalogId;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        final PresenterSelector presenterSelectorCreatePresenter = constants.createPresenter(context, str, Boolean.valueOf(this.adultBrowsing));
        View titleView = getTitleView();
        if (titleView == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HighlightsHeroZoneView");
        }
        final HighlightsHeroZoneView highlightsHeroZoneView = (HighlightsHeroZoneView) titleView;
        highlightsHeroZoneView.setAdultBrowsing(this.adultBrowsing);
        Context context2 = getContext();
        Object applicationContext = context2 != null ? context2.getApplicationContext() : null;
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        VODCatalogService vodCatalogService = ((PortalApplicationSingletons) applicationContext).getBOService().getVodCatalogService();
        String str2 = this.catalogId;
        if (str2 == null) {
            str2 = "";
        }
        String str3 = this.collectionId;
        Disposable disposableSubscribe = vodCatalogService.sectionById(str2, str3 != null ? str3 : "").doAfterSuccess(new Consumer<CatalogSection>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(final CatalogSection catalogSection) {
                BrowseCollectionFragment.this.handler.postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (BrowseCollectionFragment.this.getActivity() != null) {
                            FragmentActivity activity = BrowseCollectionFragment.this.getActivity();
                            if (activity == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
                            }
                            BrowseCollectionActivity browseCollectionActivity = (BrowseCollectionActivity) activity;
                            String title = catalogSection.getTitle();
                            if (title == null) {
                                Intrinsics.throwNpe();
                            }
                            browseCollectionActivity.setTitle(title.length() > 0 ? catalogSection.getTitle() : "");
                        }
                    }
                }, 100L);
            }
        }).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.2
            @Override // io.reactivex.functions.Function
            public final Observable<ItemCollection<BrowsableItem>> apply(CatalogSection it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getChildren();
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.3
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<BrowsableItem>, Integer>> apply(final ItemCollection<BrowsableItem> col) {
                Single<Integer> itemsCount;
                Intrinsics.checkParameterIsNotNull(col, "col");
                if (Constants.INSTANCE.collectionIsHighlights(col)) {
                    itemsCount = col.getItems().count().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.3.1
                        @Override // io.reactivex.functions.Function
                        public /* bridge */ /* synthetic */ Object apply(Object obj) {
                            return Integer.valueOf(apply((Long) obj));
                        }

                        public final int apply(Long it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return (int) it.longValue();
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(itemsCount, "col.items.count().map { it.toInt() }");
                } else {
                    itemsCount = col.getItemsCount();
                    if (itemsCount == null) {
                        itemsCount = Single.just(0);
                        Intrinsics.checkExpressionValueIsNotNull(itemsCount, "Single.just(0)");
                    }
                }
                return itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.3.2
                    @Override // io.reactivex.functions.Function
                    public final Pair<ItemCollection<BrowsableItem>, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(col, it);
                    }
                });
            }
        }).sorted(new Comparator<Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.4
            @Override // java.util.Comparator
            public /* bridge */ /* synthetic */ int compare(Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer> pair, Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer> pair2) {
                return compare2((Pair<? extends ItemCollection<BrowsableItem>, Integer>) pair, (Pair<? extends ItemCollection<BrowsableItem>, Integer>) pair2);
            }

            /* JADX INFO: renamed from: compare, reason: avoid collision after fix types in other method */
            public final int compare2(Pair<? extends ItemCollection<BrowsableItem>, Integer> pair, Pair<? extends ItemCollection<BrowsableItem>, Integer> pair2) {
                Constants constants2 = Constants.INSTANCE;
                ItemCollection<BrowsableItem> first = pair.getFirst();
                Intrinsics.checkExpressionValueIsNotNull(first, "collection1.first");
                boolean zCollectionIsAdult = constants2.collectionIsAdult(first);
                Constants constants3 = Constants.INSTANCE;
                ItemCollection<BrowsableItem> first2 = pair2.getFirst();
                Intrinsics.checkExpressionValueIsNotNull(first2, "collection2.first");
                boolean zCollectionIsAdult2 = constants3.collectionIsAdult(first2);
                if (!zCollectionIsAdult || zCollectionIsAdult2) {
                    return (zCollectionIsAdult || zCollectionIsAdult2) ? -1 : 0;
                }
                return 1;
            }
        }).retry(new Predicate<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.5
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                return error.getCause() instanceof InterruptedIOException;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.6
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer> pair) throws URISyntaxException {
                accept2((Pair<? extends ItemCollection<BrowsableItem>, Integer>) pair);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(final Pair<? extends ItemCollection<BrowsableItem>, Integer> pair) throws URISyntaxException {
                BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) BrowseCollectionFragment.this.getActivity();
                if (baseFragmentActivity2 != null) {
                    baseFragmentActivity2.setLoaderVisible(false);
                }
                Log.d(BrowseCollectionFragment.this.TAG, "pair = " + pair);
                final int i = intRef.element;
                Constants constants2 = Constants.INSTANCE;
                ItemCollection<BrowsableItem> first = pair.getFirst();
                Intrinsics.checkExpressionValueIsNotNull(first, "pair.first");
                final boolean zCollectionIsHighlights = constants2.collectionIsHighlights(first);
                Constants constants3 = Constants.INSTANCE;
                ItemCollection<BrowsableItem> first2 = pair.getFirst();
                Intrinsics.checkExpressionValueIsNotNull(first2, "pair.first");
                boolean z = (!constants3.collectionIsAdult(first2) || BrowseCollectionFragment.this.adultBrowsing) ? 0 : 1;
                Log.d(BrowseCollectionFragment.this.TAG, "isAdult: " + z + " -> " + ((ItemCollection) pair.getFirst()));
                StringBuilder sb = new StringBuilder();
                sb.append("megacable://com.dotscreen.megacable.portal");
                sb.append("/collection?catalog_id=");
                String str4 = BrowseCollectionFragment.this.catalogId;
                if (str4 == null) {
                    str4 = "";
                }
                sb.append(str4);
                sb.append("&collection_id=");
                sb.append(pair.getFirst().getId());
                sb.append("&adult_browsing=");
                sb.append(z != 0 || BrowseCollectionFragment.this.adultBrowsing);
                final Intent uri = Intent.parseUri(sb.toString(), 0);
                if (zCollectionIsHighlights) {
                    if (Intrinsics.compare(pair.getSecond().intValue(), 0) > 0 && z == 0) {
                        highlightsHeroZoneView.setItemsCount(pair.getSecond());
                    }
                } else {
                    BrowseCollectionFragment browseCollectionFragment = BrowseCollectionFragment.this;
                    int i2 = !z;
                    ArrayList arrayList = new ArrayList(i2);
                    for (int i3 = 0; i3 < i2; i3++) {
                        arrayList.add(new LoadingWidget());
                    }
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(browseCollectionFragment, i, null, arrayList, new BaseWidget(pair.getFirst().getTitle(), uri, z, null, 8, null), null, presenterSelectorCreatePresenter, false, null, 146, null);
                }
                if (z == 0) {
                    final boolean z2 = z;
                    Disposable disposableSubscribe2 = pair.getFirst().getItems().retry(new Predicate<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.6.2
                        @Override // io.reactivex.functions.Predicate
                        public final boolean test(Throwable error) {
                            Intrinsics.checkParameterIsNotNull(error, "error");
                            return error.getCause() instanceof InterruptedIOException;
                        }
                    }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BrowsableItem>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.6.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<BrowsableItem> list) {
                            Log.d(BrowseCollectionFragment.this.TAG, "col = " + ((ItemCollection) pair.getFirst()) + ", items = " + list + ", currentIndex = " + i);
                            if (zCollectionIsHighlights) {
                                if (Intrinsics.areEqual(BrowseCollectionFragment.this.catalogId, Constants.INSTANCE.getCATCHUP_ROOT_CATALOG_ID())) {
                                    highlightsHeroZoneView.setCatalogType("catchup");
                                }
                                highlightsHeroZoneView.setItems(list);
                                return;
                            }
                            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(BrowseCollectionFragment.this, i, null, list, new BaseWidget(((ItemCollection) pair.getFirst()).getTitle(), uri, z2, null, 8, null), null, presenterSelectorCreatePresenter, false, null, 146, null);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.6.4
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            Log.w(BrowseCollectionFragment.this.TAG, "Error loading content", th);
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe2, "pair.first.items\n       …                       })");
                    FragmentActivity activity = BrowseCollectionFragment.this.getActivity();
                    if (activity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity).getDisposable());
                }
                intRef.element++;
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(BrowseCollectionFragment.this.TAG, "Error loading content(row)", th);
                Toast.makeText(BrowseCollectionFragment.this.getActivity(), BrowseCollectionFragment.this.getString(R.string.technical_error), 1).show();
                FragmentActivity activity = BrowseCollectionFragment.this.getActivity();
                if (activity != null) {
                    activity.finish();
                }
            }
        }, new Action() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createContentRows.8
            @Override // io.reactivex.functions.Action
            public final void run() {
                if (highlightsHeroZoneView.getItemsCount() == null) {
                    BrowseCollectionFragment browseCollectionFragment = BrowseCollectionFragment.this;
                    browseCollectionFragment.fragmentAlignment = Integer.valueOf(browseCollectionFragment.getResources().getDimensionPixelSize(R.dimen.browse_without_highlights_margin_top));
                    RowsSupportFragment rowsSupportFragment = BrowseCollectionFragment.this.getRowsSupportFragment();
                    Integer num = BrowseCollectionFragment.this.fragmentAlignment;
                    if (num == null) {
                        Intrinsics.throwNpe();
                    }
                    rowsSupportFragment.setAlignment(num.intValue());
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(context?.applicationCon…      }\n                )");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
    }

    private final void createRows() {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        RoundListRowPresenter roundListRowPresenter = new RoundListRowPresenter(context, 4, false, 4, null);
        roundListRowPresenter.setSelectEffectEnabled(false);
        roundListRowPresenter.setKeepChildForeground(false);
        roundListRowPresenter.setShadowEnabled(true);
        setRowsAdapter(new ArrayObjectAdapter(roundListRowPresenter));
        setAdapter(getRowsAdapter());
        setSelectedPosition(0);
        View view = getView();
        if (view == null) {
            Intrinsics.throwNpe();
        }
        view.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment.createRows.1
            @Override // java.lang.Runnable
            public void run() {
                RowsSupportFragment rowsSupportFragment = BrowseCollectionFragment.this.getRowsSupportFragment();
                if (rowsSupportFragment != null && rowsSupportFragment.getVerticalGridView() != null) {
                    rowsSupportFragment.getVerticalGridView().requestFocus();
                    return;
                }
                View view2 = BrowseCollectionFragment.this.getView();
                if (view2 == null) {
                    Intrinsics.throwNpe();
                }
                view2.postDelayed(this, 10L);
            }
        });
        createContentRows();
    }

    private final void setupUIElements() {
        setHeadersState(3);
        setHeadersTransitionOnBackEnabled(false);
    }

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    /* JADX INFO: compiled from: BrowseCollectionFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/browse/BrowseCollectionFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/browse/BrowseCollectionFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "registerClick", "Lcom/dotscreen/bokit/model/BrowsableItem;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v9, types: [T, android.content.Intent] */
        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, final Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof BrowsableItem) {
                BrowsableItem browsableItem = (BrowsableItem) item;
                registerClick(browsableItem);
                String str = Intrinsics.areEqual(BrowseCollectionFragment.this.catalogId, Constants.INSTANCE.getCATCHUP_ROOT_CATALOG_ID()) ? "catchup" : "vod";
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (Intrinsics.areEqual(str, "catchup")) {
                    objectRef.element = new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/" + str + "/details?content_id=" + browsableItem.getId() + "&adult_browsing=" + BrowseCollectionFragment.this.adultBrowsing));
                    BrowseCollectionFragment.this.startActivity((Intent) objectRef.element);
                } else {
                    PortalApplication.Companion companion = PortalApplication.INSTANCE;
                    FragmentActivity activity = BrowseCollectionFragment.this.getActivity();
                    if (activity == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
                    UserService userService = companion.getSingleton(activity).getBOService().getUserService();
                    if (userService == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment$ItemViewClickedListener$onItemClicked$1
                        @Override // io.reactivex.functions.Function
                        public final RightVUser apply(User it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return (RightVUser) it;
                        }
                    }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment$ItemViewClickedListener$onItemClicked$2
                        @Override // io.reactivex.functions.Function
                        public final Single<List<Media>> apply(RightVUser user) {
                            Intrinsics.checkParameterIsNotNull(user, "user");
                            return user.nextEpisode(((BrowsableItem) item).getTitle(), false).toList();
                        }
                    }).subscribe(new Consumer<List<Media>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment$ItemViewClickedListener$onItemClicked$3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<Media> list) {
                            String id = (list.size() <= 0 || list.get(0).getId() == null) ? ((BrowsableItem) item).getId() : list.get(0).getId();
                            objectRef.element = (T) new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + id + "&adult_browsing=" + BrowseCollectionFragment.this.adultBrowsing));
                            BrowseCollectionFragment.this.startActivity((Intent) objectRef.element);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment$ItemViewClickedListener$onItemClicked$4
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                        }
                    }), "PortalApplication.getSin…                   }, {})");
                }
                DSLog.INSTANCE.a("Clicked on the " + browsableItem.getTitle() + " brosawbleItem");
                return;
            }
            if (item instanceof BaseWidget) {
                BaseWidget baseWidget = (BaseWidget) item;
                if (baseWidget.getIntent() != null) {
                    if (!baseWidget.getPasswordProtected()) {
                        FragmentActivity activity2 = BrowseCollectionFragment.this.getActivity();
                        if (activity2 != null) {
                            Activity_StartIntentKt.startIntent(activity2, baseWidget.getIntent());
                            return;
                        }
                        return;
                    }
                    Context context = BrowseCollectionFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                    PinCodeDialog.Builder checkPinCode = new PinCodeDialog.Builder(context).setCheckPinCode(true);
                    Context context2 = BrowseCollectionFragment.this.getContext();
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                    Context applicationContext = context2.getApplicationContext();
                    if (applicationContext == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
                    if (value == null) {
                        Intrinsics.throwNpe();
                    }
                    RightVUser user = value.getUser();
                    if (user == null) {
                        Intrinsics.throwNpe();
                    }
                    checkPinCode.setUser(user).setPincodeType(PincodeType.Parental).setTitle(R.string.input_parental_pin_code).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment$ItemViewClickedListener$onItemClicked$5
                        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                        public void onPinCodeCorrect(PinCodeDialog dialog, List<String> pinCodes) {
                            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                            Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
                            PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeCorrect(this, dialog, pinCodes);
                        }

                        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                        public void onPinCodeInvalid(PinCodeDialog dialog, String pinCode) {
                            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                            PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCode);
                        }

                        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                        public void onPinCodeInvalid(PinCodeDialog dialog, List<String> pinCodes, Exception exc) {
                            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                            Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
                            PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCodes, exc);
                        }

                        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                        public void onPinCodeCorrect(PinCodeDialog dialog, String pinCode) {
                            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                            dialog.dismiss();
                            FragmentActivity activity3 = BrowseCollectionFragment.this.getActivity();
                            if (activity3 != null) {
                                Activity_StartIntentKt.startIntent(activity3, ((BaseWidget) item).getIntent());
                            }
                        }
                    }).create().show();
                }
            }
        }

        private final void registerClick(BrowsableItem item) {
            Context context = BrowseCollectionFragment.this.getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            VODCatalogService vodCatalogService = ((PortalApplication) applicationContext).getBOService().getVodCatalogService();
            if (vodCatalogService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
            }
            MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, item, null, null, null, 14, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment$ItemViewClickedListener$registerClick$1
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionFragment$ItemViewClickedListener$registerClick$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            });
        }
    }

    /* JADX INFO: compiled from: BrowseCollectionFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/browse/BrowseCollectionFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/browse/BrowseCollectionFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        public ItemViewSelectedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            View titleView = BrowseCollectionFragment.this.getTitleView();
            if (titleView == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HighlightsHeroZoneView");
            }
            Integer itemsCount = ((HighlightsHeroZoneView) titleView).getItemsCount();
            if ((itemsCount != null ? itemsCount.intValue() : 0) > 0) {
                BrowseCollectionFragment browseCollectionFragment = BrowseCollectionFragment.this;
                Long lValueOf = row != null ? Long.valueOf(row.getId()) : null;
                browseCollectionFragment.fragmentAlignment = ((lValueOf != null && lValueOf.longValue() == 0) || (lValueOf != null && lValueOf.longValue() == 1)) ? Integer.valueOf(BrowseCollectionFragment.this.getResources().getDimensionPixelSize(R.dimen.browse_with_highlights_margin_top)) : Integer.valueOf(BrowseCollectionFragment.this.getResources().getDimensionPixelSize(R.dimen.browse_without_highlights_margin_top));
                RowsSupportFragment rowsSupportFragment = BrowseCollectionFragment.this.getRowsSupportFragment();
                Integer num = BrowseCollectionFragment.this.fragmentAlignment;
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                rowsSupportFragment.setAlignment(num.intValue());
            }
        }
    }
}
