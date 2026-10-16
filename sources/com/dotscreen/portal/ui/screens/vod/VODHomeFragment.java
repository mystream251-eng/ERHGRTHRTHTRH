package com.dotscreen.portal.ui.screens.vod;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: VODHomeFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002:\u0002#$B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0014\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0012\u0010\u001d\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0016H\u0016J\b\u0010!\u001a\u00020\u0016H\u0002J\b\u0010\"\u001a\u00020\u0016H\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u001c\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006%"}, d2 = {"Lcom/dotscreen/portal/ui/screens/vod/VODHomeFragment;", "Landroidx/leanback/app/BrowseSupportFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "fragmentAlignment", "", "Ljava/lang/Integer;", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "createContentRows", "", "createRows", "isAmazonOrYoutubeContent", "", "value", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "setupEventListeners", "setupUIElements", "ItemViewClickedListener", "ItemViewSelectedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODHomeFragment extends BrowseSupportFragment implements ISwimlanesFragment {
    private HashMap _$_findViewCache;
    private Integer fragmentAlignment;
    private ArrayObjectAdapter rowsAdapter;
    private final String TAG = VODHomeFragment.class.getSimpleName();
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

    public final boolean isAmazonOrYoutubeContent(ItemCollection<BrowsableItem> value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        boolean z = value instanceof CatalogSection;
        return true;
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

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUIElements();
        setupEventListeners();
        createRows();
    }

    private final void createContentRows() {
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        if (companion.getSingleton(context).getDegradedMode()) {
            Context context2 = getContext();
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            Toast.makeText(context2, R.string.noAvailable, 1).show();
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
                return;
            }
            return;
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) getActivity();
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        Constants constants = Constants.INSTANCE;
        Context context3 = getContext();
        if (context3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
        final PresenterSelector presenterSelectorCreatePresenter$default = Constants.createPresenter$default(constants, context3, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), null, 4, null);
        View titleView = getTitleView();
        if (titleView == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HighlightsHeroZoneView");
        }
        final HighlightsHeroZoneView highlightsHeroZoneView = (HighlightsHeroZoneView) titleView;
        highlightsHeroZoneView.setTitle(getString(R.string.vod_title));
        Context context4 = getContext();
        Object applicationContext = context4 != null ? context4.getApplicationContext() : null;
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        Disposable disposableSubscribe = ((PortalApplicationSingletons) applicationContext).getBOService().getVodCatalogService().rootSection(Constants.INSTANCE.getVOD_ROOT_CATALOG_ID()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.1
            @Override // io.reactivex.functions.Function
            public final Observable<ItemCollection<BrowsableItem>> apply(CatalogSection it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getChildren();
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.2
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<BrowsableItem>, Integer>> apply(final ItemCollection<BrowsableItem> col) {
                Single<Integer> itemsCount;
                Intrinsics.checkParameterIsNotNull(col, "col");
                if (Constants.INSTANCE.collectionIsHighlights(col)) {
                    itemsCount = col.getItems().count().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.2.1
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
                return itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.2.2
                    @Override // io.reactivex.functions.Function
                    public final Pair<ItemCollection<BrowsableItem>, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(col, it);
                    }
                });
            }
        }).sorted(new Comparator<Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.3
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
        }).retry(new Predicate<Throwable>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.4
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                return error.getCause() instanceof InterruptedIOException;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.5
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer> pair) throws URISyntaxException {
                accept2((Pair<? extends ItemCollection<BrowsableItem>, Integer>) pair);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(final Pair<? extends ItemCollection<BrowsableItem>, Integer> pair) throws URISyntaxException {
                BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) VODHomeFragment.this.getActivity();
                if (baseFragmentActivity2 != null) {
                    baseFragmentActivity2.setLoaderVisible(false);
                }
                Log.d(VODHomeFragment.this.TAG, "pair = " + pair);
                Log.d(VODHomeFragment.this.TAG, "pair = " + pair.getFirst());
                Log.d(VODHomeFragment.this.TAG, "pair = " + pair.getFirst().getId());
                final int i = intRef.element;
                Constants constants2 = Constants.INSTANCE;
                ItemCollection<BrowsableItem> first = pair.getFirst();
                Intrinsics.checkExpressionValueIsNotNull(first, "pair.first");
                final boolean zCollectionIsHighlights = constants2.collectionIsHighlights(first);
                Constants constants3 = Constants.INSTANCE;
                ItemCollection<BrowsableItem> first2 = pair.getFirst();
                Intrinsics.checkExpressionValueIsNotNull(first2, "pair.first");
                final boolean zCollectionIsAdult = constants3.collectionIsAdult(first2);
                final Intent uri = Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + Constants.INSTANCE.getVOD_ROOT_CATALOG_ID() + "&collection_id=" + pair.getFirst().getId() + "&adult_browsing=" + zCollectionIsAdult, 0);
                if (zCollectionIsHighlights) {
                    Log.d(VODHomeFragment.this.TAG, "pair = " + pair.getSecond());
                    if (Intrinsics.compare(pair.getSecond().intValue(), 0) > 0 && !zCollectionIsAdult) {
                        highlightsHeroZoneView.setItemsCount(pair.getSecond());
                    }
                } else {
                    Log.d(VODHomeFragment.this.TAG, "pair = " + i);
                    VODHomeFragment vODHomeFragment = VODHomeFragment.this;
                    ItemCollection<BrowsableItem> first3 = pair.getFirst();
                    Intrinsics.checkExpressionValueIsNotNull(first3, "pair.first");
                    if (vODHomeFragment.isAmazonOrYoutubeContent(first3)) {
                        VODHomeFragment vODHomeFragment2 = VODHomeFragment.this;
                        int i2 = !zCollectionIsAdult ? 1 : 0;
                        ArrayList arrayList = new ArrayList(i2);
                        for (int i3 = 0; i3 < i2; i3++) {
                            arrayList.add(new LoadingWidget());
                        }
                        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(vODHomeFragment2, i, null, arrayList, new BaseWidget(pair.getFirst().getTitle(), uri, zCollectionIsAdult, null, 8, null), null, presenterSelectorCreatePresenter$default, false, null, 146, null);
                    }
                    intRef.element++;
                }
                if (!zCollectionIsAdult) {
                    Disposable disposableSubscribe2 = pair.getFirst().getItems().retry(new Predicate<Throwable>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.5.2
                        @Override // io.reactivex.functions.Predicate
                        public final boolean test(Throwable error) {
                            Intrinsics.checkParameterIsNotNull(error, "error");
                            return error.getCause() instanceof InterruptedIOException;
                        }
                    }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BrowsableItem>>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.5.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<BrowsableItem> list) {
                            Log.d(VODHomeFragment.this.TAG, "col = " + ((ItemCollection) pair.getFirst()) + ", items = " + list + ", currentIndex = " + i);
                            if (zCollectionIsHighlights) {
                                highlightsHeroZoneView.setItems(list);
                                return;
                            }
                            VODHomeFragment vODHomeFragment3 = VODHomeFragment.this;
                            Object first4 = pair.getFirst();
                            Intrinsics.checkExpressionValueIsNotNull(first4, "pair.first");
                            if (vODHomeFragment3.isAmazonOrYoutubeContent((ItemCollection) first4)) {
                                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(VODHomeFragment.this, i, null, list, new BaseWidget(((ItemCollection) pair.getFirst()).getTitle(), uri, zCollectionIsAdult, null, 8, null), null, presenterSelectorCreatePresenter$default, false, null, 146, null);
                            }
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.5.4
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            Log.w(VODHomeFragment.this.TAG, "Error loading content", th);
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe2, "pair.first.items\n       …loading content\", err) })");
                    FragmentActivity activity2 = VODHomeFragment.this.getActivity();
                    if (activity2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity2).getDisposable());
                }
                intRef.element++;
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(VODHomeFragment.this.TAG, "Error loading content", th);
            }
        }, new Action() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createContentRows.7
            @Override // io.reactivex.functions.Action
            public final void run() {
                if (highlightsHeroZoneView.getItemsCount() == null) {
                    VODHomeFragment vODHomeFragment = VODHomeFragment.this;
                    vODHomeFragment.fragmentAlignment = Integer.valueOf(vODHomeFragment.getResources().getDimensionPixelSize(R.dimen.browse_without_highlights_margin_top));
                    RowsSupportFragment rowsSupportFragment = VODHomeFragment.this.getRowsSupportFragment();
                    Integer num = VODHomeFragment.this.fragmentAlignment;
                    if (num == null) {
                        Intrinsics.throwNpe();
                    }
                    rowsSupportFragment.setAlignment(num.intValue());
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(context?.applicationCon…          }\n            )");
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity2).getDisposable());
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
        if (view != null) {
            view.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment.createRows.1
                @Override // java.lang.Runnable
                public void run() {
                    RowsSupportFragment rowsSupportFragment = VODHomeFragment.this.getRowsSupportFragment();
                    if (rowsSupportFragment != null && rowsSupportFragment.getVerticalGridView() != null) {
                        rowsSupportFragment.getVerticalGridView().requestFocus();
                        return;
                    }
                    View view2 = VODHomeFragment.this.getView();
                    if (view2 != null) {
                        view2.postDelayed(this, 10L);
                    }
                }
            });
        }
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

    /* JADX INFO: compiled from: VODHomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/vod/VODHomeFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/vod/VODHomeFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, final Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof BrowsableItem) {
                Context context = VODHomeFragment.this.getContext();
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
                MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, (BrowsableItem) item, null, null, null, 14, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment$ItemViewClickedListener$onItemClicked$1
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment$ItemViewClickedListener$onItemClicked$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                    }
                });
                PortalApplication.Companion companion = PortalApplication.INSTANCE;
                FragmentActivity activity = VODHomeFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
                UserService userService = companion.getSingleton(activity).getBOService().getUserService();
                if (userService == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment$ItemViewClickedListener$onItemClicked$3
                    @Override // io.reactivex.functions.Function
                    public final RightVUser apply(User it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return (RightVUser) it;
                    }
                }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment$ItemViewClickedListener$onItemClicked$4
                    @Override // io.reactivex.functions.Function
                    public final Single<List<Media>> apply(RightVUser user) {
                        Intrinsics.checkParameterIsNotNull(user, "user");
                        return user.nextEpisode(((BrowsableItem) item).getTitle(), false).toList();
                    }
                }).subscribe(new Consumer<List<Media>>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment$ItemViewClickedListener$onItemClicked$5
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(List<Media> list) {
                        VODHomeFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + ((list.size() <= 0 || list.get(0).getId() == null) ? ((BrowsableItem) item).getId() : list.get(0).getId()))));
                        DSLog.INSTANCE.a("Clicked on the " + ((BrowsableItem) item).getTitle() + " brosawbleItem");
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment$ItemViewClickedListener$onItemClicked$6
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                    }
                }), "PortalApplication.getSin…                   }, {})");
                return;
            }
            if (item instanceof BaseWidget) {
                DSLog dSLog = DSLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("Clicked on the ");
                BaseWidget baseWidget = (BaseWidget) item;
                sb.append(baseWidget.getTitle());
                sb.append(" BaseWidget");
                dSLog.a(sb.toString());
                if (baseWidget.getIntent() != null) {
                    if (!baseWidget.getPasswordProtected()) {
                        FragmentActivity activity2 = VODHomeFragment.this.getActivity();
                        if (activity2 != null) {
                            Activity_StartIntentKt.startIntent(activity2, baseWidget.getIntent());
                            return;
                        }
                        return;
                    }
                    Context context2 = VODHomeFragment.this.getContext();
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                    PinCodeDialog.Builder checkPinCode = new PinCodeDialog.Builder(context2).setCheckPinCode(true);
                    Context context3 = VODHomeFragment.this.getContext();
                    if (context3 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                    Context applicationContext2 = context3.getApplicationContext();
                    if (applicationContext2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    PortalApplication.Config value = ((PortalApplication) applicationContext2).getConfig().getValue();
                    if (value == null) {
                        Intrinsics.throwNpe();
                    }
                    RightVUser user = value.getUser();
                    if (user == null) {
                        Intrinsics.throwNpe();
                    }
                    checkPinCode.setUser(user).setPincodeType(PincodeType.Parental).setTitle(R.string.input_parental_pin_code).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.vod.VODHomeFragment$ItemViewClickedListener$onItemClicked$7
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
                            FragmentActivity activity3 = VODHomeFragment.this.getActivity();
                            if (activity3 != null) {
                                Activity_StartIntentKt.startIntent(activity3, ((BaseWidget) item).getIntent());
                            }
                        }
                    }).create().show();
                }
            }
        }
    }

    /* JADX INFO: compiled from: VODHomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/vod/VODHomeFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/vod/VODHomeFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        public ItemViewSelectedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            View titleView = VODHomeFragment.this.getTitleView();
            if (titleView == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HighlightsHeroZoneView");
            }
            Integer itemsCount = ((HighlightsHeroZoneView) titleView).getItemsCount();
            if ((itemsCount != null ? itemsCount.intValue() : 0) > 0) {
                VODHomeFragment vODHomeFragment = VODHomeFragment.this;
                Long lValueOf = row != null ? Long.valueOf(row.getId()) : null;
                vODHomeFragment.fragmentAlignment = ((lValueOf != null && lValueOf.longValue() == 0) || (lValueOf != null && lValueOf.longValue() == 1)) ? Integer.valueOf(VODHomeFragment.this.getResources().getDimensionPixelSize(R.dimen.browse_with_highlights_margin_top)) : Integer.valueOf(VODHomeFragment.this.getResources().getDimensionPixelSize(R.dimen.browse_without_highlights_margin_top));
                RowsSupportFragment rowsSupportFragment = VODHomeFragment.this.getRowsSupportFragment();
                Integer num = VODHomeFragment.this.fragmentAlignment;
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                rowsSupportFragment.setAlignment(num.intValue());
            }
        }
    }
}
