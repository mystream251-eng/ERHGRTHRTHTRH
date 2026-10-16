package com.dotscreen.portal.ui.screens.browse;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.VerticalGridSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.DiffCallback;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;
import androidx.leanback.widget.VerticalGridView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Classifier;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.PurchasableItem;
import com.dotscreen.bokit.model.SubjectClassifier;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.model.LastChannelsViewModel;
import com.dotscreen.portal.model.MyContentsViewModel;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.utils.CommonUtils;
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
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: BrowseMosaicFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 ,2\u00020\u0001:\u0002,-B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u001a\u0010\u0013\u001a\u00020\u00122\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0002J\b\u0010\u001f\u001a\u00020\u0012H\u0002J\b\u0010 \u001a\u00020\u0012H\u0002J\b\u0010!\u001a\u00020\u0012H\u0002J\b\u0010\"\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0012H\u0002J\u0012\u0010$\u001a\u00020\u00122\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010'\u001a\u00020\u00122\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010(\u001a\u00020\u0012H\u0016J\b\u0010)\u001a\u00020\u0012H\u0016J\b\u0010*\u001a\u00020\u0012H\u0002J\b\u0010+\u001a\u00020\u0012H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/dotscreen/portal/ui/screens/browse/BrowseMosaicFragment;", "Landroidx/leanback/app/VerticalGridSupportFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "adultBrowsing", "", "catalogId", "collectionId", "gridView", "Landroidx/leanback/widget/VerticalGridView;", "handler", "Landroid/os/Handler;", "itemsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "shouldUpdatePageTitle", "catchupChannels", "", "displayItems", "items", "", "Lcom/dotscreen/bokit/model/BrowsableItem;", "isChannelSection", "section", "Lcom/dotscreen/bokit/model/CatalogSection;", "launcherChannels", "launcherLive", "loadCatalog", "loadMyContentsBookmarks", "loadMyContentsChannels", "loadMyContentsPurchased", "loadMyContentsRecordings", "loadMyContentsReminders", "loadMyContentsVOD", "loadVodCatalog", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onResume", "onStop", "setupEventListeners", "setupUIElements", "Companion", "ItemViewClickedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class BrowseMosaicFragment extends VerticalGridSupportFragment {
    public static final String ADULT_BROWSING = "arg_adult_browsing";
    public static final String CATALOG_ID = "arg_catalog_id";
    public static final String COLLECTION_ID = "arg_collection_id";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap _$_findViewCache;
    private boolean adultBrowsing;
    private String catalogId;
    private String collectionId;
    private VerticalGridView gridView;
    private ArrayObjectAdapter itemsAdapter;
    private final String TAG = BrowseMosaicFragment.class.getSimpleName();
    private final Handler handler = new Handler();
    private boolean shouldUpdatePageTitle = true;

    private final void setupUIElements() {
    }

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

    @Override // androidx.leanback.app.VerticalGridSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX INFO: compiled from: BrowseMosaicFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/browse/BrowseMosaicFragment$Companion;", "", "()V", "ADULT_BROWSING", "", "CATALOG_ID", "COLLECTION_ID", "newInstance", "Lcom/dotscreen/portal/ui/screens/browse/BrowseMosaicFragment;", "catalogId", "collectionId", "adultBrowsing", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ BrowseMosaicFragment newInstance$default(Companion companion, String str, String str2, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = (String) null;
            }
            if ((i & 2) != 0) {
                str2 = (String) null;
            }
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.newInstance(str, str2, z);
        }

        public final BrowseMosaicFragment newInstance(String catalogId, String collectionId, boolean adultBrowsing) {
            BrowseMosaicFragment browseMosaicFragment = new BrowseMosaicFragment();
            Bundle bundle = new Bundle();
            bundle.putString("arg_catalog_id", catalogId);
            bundle.putString("arg_collection_id", collectionId);
            bundle.putBoolean("arg_adult_browsing", adultBrowsing);
            browseMosaicFragment.setArguments(bundle);
            return browseMosaicFragment;
        }
    }

    /* JADX WARN: Type inference failed for: r5v8, types: [com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$onCreate$presenter$1] */
    @Override // androidx.leanback.app.BaseSupportFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.catalogId = arguments != null ? arguments.getString("arg_catalog_id") : null;
        Bundle arguments2 = getArguments();
        this.collectionId = arguments2 != null ? arguments2.getString("arg_collection_id") : null;
        Bundle arguments3 = getArguments();
        final boolean z = false;
        this.adultBrowsing = arguments3 != null ? arguments3.getBoolean("arg_adult_browsing") : false;
        final int i = 4;
        final ?? r5 = new VerticalGridPresenter(i, z) { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$onCreate$presenter$1
            @Override // androidx.leanback.widget.VerticalGridPresenter
            protected void initializeGridViewHolder(VerticalGridPresenter.ViewHolder vh) {
                super.initializeGridViewHolder(vh);
                this.this$0.gridView = vh != null ? vh.getGridView() : null;
            }
        };
        r5.setShadowEnabled(true);
        r5.setNumberOfColumns(Constants.INSTANCE.mosaicColumnsCount(this.catalogId));
        setGridPresenter((VerticalGridPresenter) r5);
        r5.setOnItemViewSelectedListener(new OnItemViewSelectedListener() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.onCreate.1
            @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
            public final void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                VerticalGridView verticalGridView = BrowseMosaicFragment.this.gridView;
                if (verticalGridView != null) {
                    ObjectAdapter adapter = BrowseMosaicFragment.this.getAdapter();
                    if ((adapter != null ? adapter.size() : 0) > getNumberOfColumns() * 2) {
                        BrowseMosaicFragment.this.showTitle(verticalGridView.getSelectedPosition() < getNumberOfColumns());
                    }
                }
            }
        });
        Constants constants = Constants.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(constants.createPresenter(context, this.catalogId, Boolean.valueOf(this.adultBrowsing)));
        this.itemsAdapter = arrayObjectAdapter;
        setAdapter(arrayObjectAdapter);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUIElements();
        setupEventListeners();
    }

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        loadCatalog();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.shouldUpdatePageTitle = false;
    }

    private final void loadCatalog() {
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) getActivity();
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        if (this.collectionId != null) {
            loadVodCatalog();
            return;
        }
        String str = this.catalogId;
        if (Intrinsics.areEqual(str, Constants.INSTANCE.getLAUNCHER_LIVE_ROOT_CATALOG_ID())) {
            launcherLive();
            return;
        }
        if (Intrinsics.areEqual(str, Constants.INSTANCE.getLAUNCHER_CHANNELS_ROOT_CATALOG_ID())) {
            launcherChannels();
            return;
        }
        if (Intrinsics.areEqual(str, Constants.INSTANCE.getCATCHUP_CHANNELS_ROOT_CATALOG_ID())) {
            catchupChannels();
            return;
        }
        if (Intrinsics.areEqual(str, Constants.INSTANCE.getMY_CONTENTS_BOOKMARKS_CATALOG_ID())) {
            loadMyContentsBookmarks();
            return;
        }
        if (Intrinsics.areEqual(str, Constants.INSTANCE.getMY_CONTENTS_CHANNELS_CATALOG_ID())) {
            loadMyContentsChannels();
            return;
        }
        if (Intrinsics.areEqual(str, Constants.INSTANCE.getMY_CONTENTS_RECORDING_CATALOG_ID())) {
            loadMyContentsRecordings();
            return;
        }
        if (Intrinsics.areEqual(str, Constants.INSTANCE.getMY_CONTENTS_VOD_CATALOG_ID())) {
            loadMyContentsVOD();
            return;
        }
        if (Intrinsics.areEqual(str, Constants.INSTANCE.getMY_CONTENTS_PURCHASED_CATALOG_ID())) {
            loadMyContentsPurchased();
        } else if (Intrinsics.areEqual(str, Constants.INSTANCE.getMY_CONTENTS_REMINDERS_CATALOG_ID())) {
            loadMyContentsReminders();
        } else {
            loadVodCatalog();
        }
    }

    private final void loadVodCatalog() {
        Observable observableFlatMapObservable;
        Context context = getContext();
        Object applicationContext = context != null ? context.getApplicationContext() : null;
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        VODCatalogService vodCatalogService = ((PortalApplicationSingletons) applicationContext).getBOService().getVodCatalogService();
        if (vodCatalogService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
        }
        MegacableVODCatalogService megacableVODCatalogService = (MegacableVODCatalogService) vodCatalogService;
        if (this.collectionId == null) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
            }
            ((BrowseCollectionActivity) activity).setTitle(getString(R.string.home_lane_vod_recommendations));
            String str = this.catalogId;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            observableFlatMapObservable = megacableVODCatalogService.getRecommendations(str, (26 & 2) != 0 ? (String) null : null, (26 & 4) != 0 ? (String) null : null, (26 & 8) != 0 ? 10 : 50, (26 & 16) != 0 ? false : false, (26 & 32) == 0 ? this.adultBrowsing : false).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadVodCatalog.1
                @Override // io.reactivex.functions.Function
                public final Observable<BrowsableItem> apply(ItemCollection<BrowsableItem> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getItems();
                }
            });
        } else {
            String str2 = this.catalogId;
            if (str2 == null) {
                str2 = "";
            }
            String str3 = this.collectionId;
            observableFlatMapObservable = megacableVODCatalogService.sectionById(str2, str3 != null ? str3 : "").doAfterSuccess(new Consumer<CatalogSection>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadVodCatalog.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(final CatalogSection catalogSection) {
                    BrowseMosaicFragment.this.handler.postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadVodCatalog.2.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (BrowseMosaicFragment.this.shouldUpdatePageTitle) {
                                FragmentActivity activity2 = BrowseMosaicFragment.this.getActivity();
                                if (!(activity2 instanceof BrowseCollectionActivity)) {
                                    activity2 = null;
                                }
                                BrowseCollectionActivity browseCollectionActivity = (BrowseCollectionActivity) activity2;
                                if (browseCollectionActivity != null) {
                                    String title = catalogSection.getTitle();
                                    if (title == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    browseCollectionActivity.setTitle(title.length() > 0 ? catalogSection.getTitle() : "");
                                }
                            }
                        }
                    }, 100L);
                }
            }).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadVodCatalog.3
                @Override // io.reactivex.functions.Function
                public final Observable<BrowsableItem> apply(CatalogSection it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getItems();
                }
            });
        }
        Disposable disposableSubscribe = observableFlatMapObservable.retry(new Predicate<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadVodCatalog.4
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                return error.getCause() instanceof InterruptedIOException;
            }
        }).filter(new Predicate<BrowsableItem>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadVodCatalog.5
            @Override // io.reactivex.functions.Predicate
            public final boolean test(BrowsableItem it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.externalId(ItemExternalIdScheme.Netflix) == null;
            }
        }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BrowsableItem>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadVodCatalog.6
            @Override // io.reactivex.functions.Consumer
            public final void accept(List<BrowsableItem> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadVodCatalog.7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(BrowseMosaicFragment.this.TAG, "Error loading content", th);
                Toast.makeText(BrowseMosaicFragment.this.getContext(), BrowseMosaicFragment.this.getString(R.string.technical_error), 1).show();
                FragmentActivity activity2 = BrowseMosaicFragment.this.getActivity();
                if (activity2 != null) {
                    activity2.finish();
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "if (collectionId == null…      }\n                )");
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity2).getDisposable());
    }

    private final void launcherLive() {
        Context context = getContext();
        Object applicationContext = context != null ? context.getApplicationContext() : null;
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        LiveTVService liveTVService = ((PortalApplicationSingletons) applicationContext).getBOService().getLiveTVService();
        if (liveTVService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
        MegacableLiveTVService megacableLiveTVService = (MegacableLiveTVService) liveTVService;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
        }
        ((BrowseCollectionActivity) activity).setTitle(getString(R.string.home_lane_tv_recommendations));
        String str = this.catalogId;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        Disposable disposableSubscribe = MegacableLiveTVService.getRecommendations$default(megacableLiveTVService, str, null, this.adultBrowsing, 2, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.launcherLive.1
            @Override // io.reactivex.functions.Function
            public final Observable<TVAiring> apply(ItemCollection<TVAiring> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).take(50L).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.launcherLive.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(List<TVAiring> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.launcherLive.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(BrowseMosaicFragment.this.TAG, "Error loading content", th);
                Toast.makeText(BrowseMosaicFragment.this.getContext(), BrowseMosaicFragment.this.getString(R.string.technical_error), 1).show();
                FragmentActivity activity2 = BrowseMosaicFragment.this.getActivity();
                if (activity2 != null) {
                    activity2.finish();
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "vodCatalogService\n      …      }\n                )");
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity2).getDisposable());
    }

    private final void launcherChannels() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
        }
        ((BrowseCollectionActivity) activity).setTitle(getString(R.string.home_lane_last_channels));
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(activity2, new LastChannelsViewModel.Factory(context)).get(LastChannelsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac…elsViewModel::class.java]");
        LiveData<List<? extends Channel>> value = ((LastChannelsViewModel) viewModel).getValue();
        FragmentActivity activity3 = getActivity();
        if (activity3 == null) {
            Intrinsics.throwNpe();
        }
        value.observe(activity3, new Observer<List<? extends Channel>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.launcherChannels.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends Channel> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        });
    }

    private final void catchupChannels() {
        Context context = getContext();
        Object applicationContext = context != null ? context.getApplicationContext() : null;
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        Disposable disposableSubscribe = ((PortalApplicationSingletons) applicationContext).getBOService().getVodCatalogService().rootSection(Constants.INSTANCE.getCATCHUP_ROOT_CATALOG_ID()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.catchupChannels.1
            @Override // io.reactivex.functions.Function
            public final Observable<ItemCollection<BrowsableItem>> apply(CatalogSection it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getChildren();
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.catchupChannels.2
            @Override // io.reactivex.functions.Function
            public final Single<Pair<CatalogSection, Integer>> apply(final ItemCollection<BrowsableItem> col) {
                Single<R> map;
                Intrinsics.checkParameterIsNotNull(col, "col");
                Single<Integer> itemsCount = col.getItemsCount();
                return (itemsCount == null || (map = itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.catchupChannels.2.1
                    @Override // io.reactivex.functions.Function
                    public final Pair<CatalogSection, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        ItemCollection itemCollection = col;
                        if (itemCollection != null) {
                            return new Pair<>((CatalogSection) itemCollection, it);
                        }
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.CatalogSection");
                    }
                })) == null) ? Single.just(new Pair((CatalogSection) col, 0)) : map;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends CatalogSection, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.catchupChannels.3
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Pair<? extends CatalogSection, ? extends Integer> pair) {
                accept2((Pair<? extends CatalogSection, Integer>) pair);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(Pair<? extends CatalogSection, Integer> pair) {
                BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) BrowseMosaicFragment.this.getActivity();
                if (baseFragmentActivity != null) {
                    baseFragmentActivity.setLoaderVisible(false);
                }
                Log.d(BrowseMosaicFragment.this.TAG, "pair = " + pair);
                Log.d(BrowseMosaicFragment.this.TAG, "collection.classifiers = " + ArraysKt.toList(pair.getFirst().getClassifiers()));
                if (BrowseMosaicFragment.this.isChannelSection(pair.getFirst())) {
                    FragmentActivity activity = BrowseMosaicFragment.this.getActivity();
                    if (activity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
                    }
                    ((BrowseCollectionActivity) activity).setTitle(pair.getFirst().getTitle());
                    Observable<ItemCollection<BrowsableItem>> children = pair.getFirst().getChildren();
                    if (children == null) {
                        Intrinsics.throwNpe();
                    }
                    Disposable disposableSubscribe2 = children.toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<ItemCollection<BrowsableItem>>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.catchupChannels.3.1
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<ItemCollection<BrowsableItem>> list) {
                            BrowseMosaicFragment.this.displayItems(list);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.catchupChannels.3.2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            Log.w(BrowseMosaicFragment.this.TAG, "Error loading content", th);
                            Toast.makeText(BrowseMosaicFragment.this.getContext(), BrowseMosaicFragment.this.getString(R.string.technical_error), 1).show();
                            FragmentActivity activity2 = BrowseMosaicFragment.this.getActivity();
                            if (activity2 != null) {
                                activity2.finish();
                            }
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe2, "pair.first.children!!.to…                       })");
                    FragmentActivity activity2 = BrowseMosaicFragment.this.getActivity();
                    if (activity2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity2).getDisposable());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.catchupChannels.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(BrowseMosaicFragment.this.TAG, "Error loading content", th);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(context?.applicationCon…      }\n                )");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
    }

    private final void loadMyContentsBookmarks() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
        }
        ((BrowseCollectionActivity) activity).setTitle(getString(R.string.my_content_bookmarks));
        BrowseMosaicFragment browseMosaicFragment = this;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(browseMosaicFragment, new MyContentsViewModel.Factory(context, this.adultBrowsing)).get(MyContentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ntsViewModel::class.java]");
        MyContentsViewModel myContentsViewModel = (MyContentsViewModel) viewModel;
        myContentsViewModel.getBookmarks().observe(getViewLifecycleOwner(), new Observer<List<? extends Media>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadMyContentsBookmarks.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends Media> list) {
                onChanged2((List<? extends BrowsableItem>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<? extends BrowsableItem> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        });
        myContentsViewModel.invalidateBookmarks();
    }

    private final void loadMyContentsChannels() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
        }
        ((BrowseCollectionActivity) activity).setTitle(getString(R.string.my_content_favorites_channels));
        BrowseMosaicFragment browseMosaicFragment = this;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(browseMosaicFragment, new MyContentsViewModel.Factory(context, this.adultBrowsing)).get(MyContentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ntsViewModel::class.java]");
        MyContentsViewModel myContentsViewModel = (MyContentsViewModel) viewModel;
        myContentsViewModel.getFavoriteChannels().observe(getViewLifecycleOwner(), new Observer<List<? extends Channel>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadMyContentsChannels.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends Channel> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        });
        myContentsViewModel.invalidateFavoriteChannels();
    }

    private final void loadMyContentsRecordings() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
        }
        ((BrowseCollectionActivity) activity).setTitle(getString(R.string.my_content_recordings));
        BrowseMosaicFragment browseMosaicFragment = this;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(browseMosaicFragment, new MyContentsViewModel.Factory(context, this.adultBrowsing)).get(MyContentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ntsViewModel::class.java]");
        MyContentsViewModel myContentsViewModel = (MyContentsViewModel) viewModel;
        myContentsViewModel.getRecordings().observe(getViewLifecycleOwner(), new Observer<List<? extends TVRecording>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadMyContentsRecordings.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends TVRecording> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        });
        myContentsViewModel.invalidateRecordings();
    }

    private final void loadMyContentsVOD() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
        }
        ((BrowseCollectionActivity) activity).setTitle(getString(R.string.my_content_favorites_VOD));
        BrowseMosaicFragment browseMosaicFragment = this;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(browseMosaicFragment, new MyContentsViewModel.Factory(context, this.adultBrowsing)).get(MyContentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ntsViewModel::class.java]");
        MyContentsViewModel myContentsViewModel = (MyContentsViewModel) viewModel;
        myContentsViewModel.getFavoriteVOD().observe(getViewLifecycleOwner(), new Observer<List<? extends BrowsableItem>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadMyContentsVOD.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends BrowsableItem> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        });
        myContentsViewModel.invalidateFavoriteVOD();
    }

    private final void loadMyContentsPurchased() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
        }
        ((BrowseCollectionActivity) activity).setTitle(getString(R.string.my_content_shopping));
        BrowseMosaicFragment browseMosaicFragment = this;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(browseMosaicFragment, new MyContentsViewModel.Factory(context, this.adultBrowsing)).get(MyContentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ntsViewModel::class.java]");
        MyContentsViewModel myContentsViewModel = (MyContentsViewModel) viewModel;
        myContentsViewModel.getCompras().observe(getViewLifecycleOwner(), new Observer<List<? extends PurchasableItem>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadMyContentsPurchased.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends PurchasableItem> list) {
                onChanged2((List<? extends BrowsableItem>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<? extends BrowsableItem> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        });
        myContentsViewModel.invalidateCompras();
    }

    private final void loadMyContentsReminders() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity");
        }
        ((BrowseCollectionActivity) activity).setTitle(getString(R.string.my_content_reminders));
        BrowseMosaicFragment browseMosaicFragment = this;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(browseMosaicFragment, new MyContentsViewModel.Factory(context, this.adultBrowsing)).get(MyContentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ntsViewModel::class.java]");
        MyContentsViewModel myContentsViewModel = (MyContentsViewModel) viewModel;
        myContentsViewModel.getReminders().observe(getViewLifecycleOwner(), new Observer<List<? extends TVAiring>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.loadMyContentsReminders.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends TVAiring> list) {
                onChanged2((List<? extends BrowsableItem>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<? extends BrowsableItem> list) {
                BrowseMosaicFragment.this.displayItems(list);
            }
        });
        myContentsViewModel.invalidateReminders();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void displayItems$default(BrowseMosaicFragment browseMosaicFragment, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        browseMosaicFragment.displayItems(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayItems(List<? extends BrowsableItem> items) {
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) getActivity();
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(false);
        }
        Log.d(this.TAG, "items = " + items);
        ArrayObjectAdapter arrayObjectAdapter = this.itemsAdapter;
        if (arrayObjectAdapter != null) {
            arrayObjectAdapter.setItems(items, new DiffCallback<BrowsableItem>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.displayItems.1
                @Override // androidx.leanback.widget.DiffCallback
                public boolean areItemsTheSame(BrowsableItem oldItem, BrowsableItem newItem) {
                    Intrinsics.checkParameterIsNotNull(oldItem, "oldItem");
                    Intrinsics.checkParameterIsNotNull(newItem, "newItem");
                    return Intrinsics.areEqual(oldItem.getId(), newItem.getId());
                }

                @Override // androidx.leanback.widget.DiffCallback
                public boolean areContentsTheSame(BrowsableItem oldItem, BrowsableItem newItem) {
                    Intrinsics.checkParameterIsNotNull(oldItem, "oldItem");
                    Intrinsics.checkParameterIsNotNull(newItem, "newItem");
                    return Intrinsics.areEqual(oldItem, newItem);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isChannelSection(CatalogSection section) {
        Classifier classifier;
        Classifier[] classifiers = section.getClassifiers();
        int length = classifiers.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                classifier = null;
                break;
            }
            classifier = classifiers[i];
            if (classifier instanceof SubjectClassifier) {
                break;
            }
            i++;
        }
        return classifier != null && Intrinsics.areEqual(classifier.getId(), "CHANNEL_LIST");
    }

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        BrowseMosaicFragment browseMosaicFragment = this;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(browseMosaicFragment, new MyContentsViewModel.Factory(context, this.adultBrowsing)).get(MyContentsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ntsViewModel::class.java]");
        ((MyContentsViewModel) viewModel).getError().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment.setupEventListeners.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    Log.w(BrowseMosaicFragment.this.TAG, "Error loading content");
                    Toast.makeText(BrowseMosaicFragment.this.getContext(), BrowseMosaicFragment.this.getString(R.string.technical_error), 1).show();
                    FragmentActivity activity = BrowseMosaicFragment.this.getActivity();
                    if (activity != null) {
                        activity.finish();
                    }
                }
            }
        });
    }

    /* JADX INFO: compiled from: BrowseMosaicFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/browse/BrowseMosaicFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/browse/BrowseMosaicFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "registerClick", "Lcom/dotscreen/bokit/model/BrowsableItem;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r9v5, types: [T, android.content.Intent] */
        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, final Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof Channel) {
                BrowseMosaicFragment.this.startActivity(new Intent("android.intent.action.VIEW", CommonUtils.getLiveTvDeeplink(BrowseMosaicFragment.this.getContext(), (Channel) item, (Boolean) true)));
                return;
            }
            if (item instanceof CatalogSection) {
                BrowseMosaicFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + Constants.INSTANCE.getCATCHUP_ROOT_CATALOG_ID() + "&collection_id=" + ((CatalogSection) item).getId())));
                return;
            }
            if (item instanceof TVRecording) {
                BrowseMosaicFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?recording_id=" + ((TVRecording) item).getId() + "&adult_browsing=" + BrowseMosaicFragment.this.adultBrowsing)));
                return;
            }
            if (item instanceof TVAiring) {
                Context context = BrowseMosaicFragment.this.getContext();
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
                MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, (BrowsableItem) item, null, null, null, 14, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$ItemViewClickedListener$onItemClicked$1
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$ItemViewClickedListener$onItemClicked$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                    }
                });
                FragmentActivity activity = BrowseMosaicFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=");
                TVAiring tVAiring = (TVAiring) item;
                sb.append(tVAiring.getChannel().getId());
                sb.append("&start=");
                sb.append(tVAiring.getStartDate().getTime());
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
                return;
            }
            if (item instanceof BrowsableItem) {
                BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) BrowseMosaicFragment.this.getActivity();
                if (baseFragmentActivity != null) {
                    baseFragmentActivity.setLoaderVisible(true);
                }
                BrowsableItem browsableItem = (BrowsableItem) item;
                registerClick(browsableItem);
                String str = Intrinsics.areEqual(BrowseMosaicFragment.this.catalogId, Constants.INSTANCE.getCATCHUP_ROOT_CATALOG_ID()) ? "catchup" : "vod";
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (Intrinsics.areEqual(str, "catchup")) {
                    objectRef.element = new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/" + str + "/details?content_id=" + browsableItem.getId() + "&adult_browsing=" + BrowseMosaicFragment.this.adultBrowsing));
                    BrowseMosaicFragment.this.startActivity((Intent) objectRef.element);
                } else {
                    PortalApplication.Companion companion = PortalApplication.INSTANCE;
                    FragmentActivity activity2 = BrowseMosaicFragment.this.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(activity2, "activity!!");
                    UserService userService = companion.getSingleton(activity2).getBOService().getUserService();
                    if (userService == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$ItemViewClickedListener$onItemClicked$3
                        @Override // io.reactivex.functions.Function
                        public final RightVUser apply(User it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return (RightVUser) it;
                        }
                    }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$ItemViewClickedListener$onItemClicked$4
                        @Override // io.reactivex.functions.Function
                        public final Single<List<Media>> apply(RightVUser user) {
                            Intrinsics.checkParameterIsNotNull(user, "user");
                            return user.nextEpisode(((BrowsableItem) item).getTitle(), false).toList();
                        }
                    }).subscribe(new Consumer<List<Media>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$ItemViewClickedListener$onItemClicked$5
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<Media> list) {
                            String id = (list.size() <= 0 || list.get(0).getId() == null) ? ((BrowsableItem) item).getId() : list.get(0).getId();
                            objectRef.element = (T) new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + id));
                            BrowseMosaicFragment.this.startActivity((Intent) objectRef.element);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$ItemViewClickedListener$onItemClicked$6
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                        }
                    }), "PortalApplication.getSin…                   }, {})");
                }
                DSLog.INSTANCE.a("Clicked on the " + browsableItem.getTitle() + " brosawbleItem");
            }
        }

        private final void registerClick(BrowsableItem item) {
            Context context = BrowseMosaicFragment.this.getContext();
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
            MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, item, null, null, null, 14, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$ItemViewClickedListener$registerClick$1
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseMosaicFragment$ItemViewClickedListener$registerClick$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            });
        }
    }
}
