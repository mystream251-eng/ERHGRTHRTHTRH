package com.dotscreen.portal.ui.screens.applications;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.RowsSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.ApplicationWidget;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.model.LauncherItemsViewModel;
import com.dotscreen.portal.ui.RoundListRowPresenter;
import com.dotscreen.portal.ui.cards.AppCardPresenter;
import com.dotscreen.portal.ui.cards.PlayStoreCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.ISwimlanesFragment;
import com.dotscreen.portal.utils.ApplicationsManager;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApplicationsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002:\u0004\u001c\u001d\u001e\u001fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0002J\u0012\u0010\u0018\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006 "}, d2 = {"Lcom/dotscreen/portal/ui/screens/applications/ApplicationsFragment;", "Landroidx/leanback/app/RowsSupportFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "()V", "PLAYGAMES_PACKAGE_NAME", "", "PLAYSTORE_PACKAGE_NAME", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "createApplicationsRow", "", "createFeaturedApplicationsRow", "createGamesRow", "createPlaystoreRow", "createRows", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "setupEventListeners", "ApplicationLaneSelector", "ItemViewClickedListener", "ItemViewSelectedListener", "NoShadowListRow", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ApplicationsFragment extends RowsSupportFragment implements ISwimlanesFragment {
    private HashMap _$_findViewCache;
    private ArrayObjectAdapter rowsAdapter;
    private final SparseArray<ListRow> listRows = new SparseArray<>();
    private final String PLAYSTORE_PACKAGE_NAME = "com.android.vending";
    private final String PLAYGAMES_PACKAGE_NAME = GooglePlayServicesUtilLight.GOOGLE_PLAY_GAMES_PACKAGE;

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

    @Override // androidx.leanback.app.RowsSupportFragment, androidx.leanback.app.BaseRowSupportFragment, androidx.fragment.app.Fragment
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

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEventListeners();
        createRows();
    }

    private final void createPlaystoreRow() {
        PackageManager packageManager;
        PackageManager packageManager2;
        ApplicationWidget[] applicationWidgetArr = new ApplicationWidget[2];
        String string = getString(R.string.applications_play_store);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.applications_play_store)");
        String str = this.PLAYSTORE_PACKAGE_NAME;
        Context context = getContext();
        Intent leanbackLaunchIntentForPackage = null;
        applicationWidgetArr[0] = new ApplicationWidget(string, str, (context == null || (packageManager2 = context.getPackageManager()) == null) ? null : packageManager2.getLeanbackLaunchIntentForPackage(this.PLAYSTORE_PACKAGE_NAME));
        String string2 = getString(R.string.applications_play_games);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.applications_play_games)");
        String str2 = this.PLAYGAMES_PACKAGE_NAME;
        Context context2 = getContext();
        if (context2 != null && (packageManager = context2.getPackageManager()) != null) {
            leanbackLaunchIntentForPackage = packageManager.getLeanbackLaunchIntentForPackage(this.PLAYGAMES_PACKAGE_NAME);
        }
        applicationWidgetArr[1] = new ApplicationWidget(string2, str2, leanbackLaunchIntentForPackage);
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, 0, null, CollectionsKt.listOf((Object[]) applicationWidgetArr), null, new PlayStoreCardPresenter(), null, false, NoShadowListRow.class, 42, null);
    }

    private final void createFeaturedApplicationsRow() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        ((BaseFragmentActivity) activity).setLoaderVisible(true);
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(activity2, new LauncherItemsViewModel.Factory(context)).get(LauncherItemsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac…emsViewModel::class.java)");
        LiveData<List<ApplicationWidget>> featuredApplicationItems = ((LauncherItemsViewModel) viewModel).getFeaturedApplicationItems();
        FragmentActivity activity3 = getActivity();
        if (activity3 == null) {
            Intrinsics.throwNpe();
        }
        featuredApplicationItems.observe(activity3, new Observer<List<? extends ApplicationWidget>>() { // from class: com.dotscreen.portal.ui.screens.applications.ApplicationsFragment.createFeaturedApplicationsRow.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends ApplicationWidget> list) {
                onChanged2((List<ApplicationWidget>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<ApplicationWidget> list) {
                FragmentActivity activity4 = ApplicationsFragment.this.getActivity();
                if (activity4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                }
                ((BaseFragmentActivity) activity4).setLoaderVisible(false);
                ListRow listRow = ApplicationsFragment.this.getListRows().get(1);
                ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) (listRow != null ? listRow.getAdapter() : null);
                if (arrayObjectAdapter != null) {
                    arrayObjectAdapter.clear();
                }
                ApplicationsFragment applicationsFragment = ApplicationsFragment.this;
                HeaderItem headerItem = new HeaderItem(ApplicationsFragment.this.getString(R.string.applications_featured));
                Context context2 = ApplicationsFragment.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                Integer numValueOf = Integer.valueOf(context2.getResources().getDimensionPixelSize(R.dimen.applications_apps_card_width));
                Context context3 = ApplicationsFragment.this.getContext();
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(applicationsFragment, 1, headerItem, list, null, new AppCardPresenter(numValueOf, Integer.valueOf(context3.getResources().getDimensionPixelSize(R.dimen.applications_apps_card_height))), null, false, null, 232, null);
            }
        });
    }

    private final void createApplicationsRow() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(activity, new LauncherItemsViewModel.Factory(context)).get(LauncherItemsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac…emsViewModel::class.java)");
        LiveData<List<ApplicationWidget>> applicationItems = ((LauncherItemsViewModel) viewModel).getApplicationItems();
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        applicationItems.observe(activity2, new Observer<List<? extends ApplicationWidget>>() { // from class: com.dotscreen.portal.ui.screens.applications.ApplicationsFragment.createApplicationsRow.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends ApplicationWidget> list) {
                onChanged2((List<ApplicationWidget>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<ApplicationWidget> list) {
                ListRow listRow = ApplicationsFragment.this.getListRows().get(2);
                ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) (listRow != null ? listRow.getAdapter() : null);
                if (arrayObjectAdapter != null) {
                    arrayObjectAdapter.clear();
                }
                ApplicationsFragment applicationsFragment = ApplicationsFragment.this;
                HeaderItem headerItem = new HeaderItem(ApplicationsFragment.this.getString(R.string.applications_apps));
                Context context2 = ApplicationsFragment.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                Integer numValueOf = Integer.valueOf(context2.getResources().getDimensionPixelSize(R.dimen.applications_apps_card_width));
                Context context3 = ApplicationsFragment.this.getContext();
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(applicationsFragment, 2, headerItem, list, null, new AppCardPresenter(numValueOf, Integer.valueOf(context3.getResources().getDimensionPixelSize(R.dimen.applications_apps_card_height))), null, false, null, 232, null);
            }
        });
    }

    private final void createGamesRow() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ViewModel viewModel = ViewModelProviders.of(activity, new LauncherItemsViewModel.Factory(context)).get(LauncherItemsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac…emsViewModel::class.java)");
        LiveData<List<ApplicationWidget>> gameItems = ((LauncherItemsViewModel) viewModel).getGameItems();
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        gameItems.observe(activity2, new Observer<List<? extends ApplicationWidget>>() { // from class: com.dotscreen.portal.ui.screens.applications.ApplicationsFragment.createGamesRow.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends ApplicationWidget> list) {
                onChanged2((List<ApplicationWidget>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<ApplicationWidget> list) {
                ListRow listRow = ApplicationsFragment.this.getListRows().get(3);
                ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) (listRow != null ? listRow.getAdapter() : null);
                if (arrayObjectAdapter != null) {
                    arrayObjectAdapter.clear();
                }
                ApplicationsFragment applicationsFragment = ApplicationsFragment.this;
                HeaderItem headerItem = new HeaderItem(ApplicationsFragment.this.getString(R.string.applications_games));
                Context context2 = ApplicationsFragment.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                Integer numValueOf = Integer.valueOf(context2.getResources().getDimensionPixelSize(R.dimen.applications_apps_card_width));
                Context context3 = ApplicationsFragment.this.getContext();
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(applicationsFragment, 3, headerItem, list, null, new AppCardPresenter(numValueOf, Integer.valueOf(context3.getResources().getDimensionPixelSize(R.dimen.applications_apps_card_height))), null, false, null, 232, null);
            }
        });
    }

    private final void createRows() {
        setRowsAdapter(new ArrayObjectAdapter(new ApplicationLaneSelector()));
        setAdapter(getRowsAdapter());
        setSelectedPosition(0);
        View view = getView();
        if (view == null) {
            Intrinsics.throwNpe();
        }
        view.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.applications.ApplicationsFragment.createRows.1
            @Override // java.lang.Runnable
            public void run() {
                if (ApplicationsFragment.this.getVerticalGridView() != null) {
                    ApplicationsFragment.this.getVerticalGridView().requestFocus();
                    return;
                }
                View view2 = ApplicationsFragment.this.getView();
                if (view2 == null) {
                    Intrinsics.throwNpe();
                }
                view2.postDelayed(this, 10L);
            }
        });
        createPlaystoreRow();
        createFeaturedApplicationsRow();
        createApplicationsRow();
        createGamesRow();
    }

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    /* JADX INFO: compiled from: ApplicationsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/applications/ApplicationsFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/applications/ApplicationsFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            DSLog.INSTANCE.a("Clicked on an Application Widget");
            if (item instanceof ApplicationWidget) {
                PortalApplication.Companion companion = PortalApplication.INSTANCE;
                Context context = ApplicationsFragment.this.getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                ApplicationsManager applicationsManager = companion.getSingleton(context).getApplicationsManager();
                FragmentActivity activity = ApplicationsFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
                applicationsManager.launchApplication(activity, (ApplicationWidget) item);
            }
        }
    }

    /* JADX INFO: compiled from: ApplicationsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/applications/ApplicationsFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/applications/ApplicationsFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        }

        public ItemViewSelectedListener() {
        }
    }

    /* JADX INFO: compiled from: ApplicationsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/ui/screens/applications/ApplicationsFragment$NoShadowListRow;", "Landroidx/leanback/widget/ListRow;", "id", "", "header", "Landroidx/leanback/widget/HeaderItem;", "adapter", "Landroidx/leanback/widget/ObjectAdapter;", "(JLandroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class NoShadowListRow extends ListRow {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoShadowListRow(long j, HeaderItem headerItem, ObjectAdapter adapter) {
            super(j, headerItem, adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        }
    }

    /* JADX INFO: compiled from: ApplicationsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u0016¢\u0006\u0002\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/screens/applications/ApplicationsFragment$ApplicationLaneSelector;", "Landroidx/leanback/widget/PresenterSelector;", "(Lcom/dotscreen/portal/ui/screens/applications/ApplicationsFragment;)V", "listRowPresenter", "Landroidx/leanback/widget/ListRowPresenter;", "getListRowPresenter", "()Landroidx/leanback/widget/ListRowPresenter;", "setListRowPresenter", "(Landroidx/leanback/widget/ListRowPresenter;)V", "noShadowListRowPresenter", "getNoShadowListRowPresenter", "setNoShadowListRowPresenter", "getPresenter", "Landroidx/leanback/widget/Presenter;", "item", "", "getPresenters", "", "()[Landroidx/leanback/widget/Presenter;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ApplicationLaneSelector extends PresenterSelector {
        private ListRowPresenter listRowPresenter;
        private ListRowPresenter noShadowListRowPresenter;

        public ApplicationLaneSelector() {
            Context context = ApplicationsFragment.this.getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            this.listRowPresenter = new RoundListRowPresenter(context, 4, false, 4, null);
            Context context2 = ApplicationsFragment.this.getContext();
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
            this.noShadowListRowPresenter = new RoundListRowPresenter(context2, 4, false, 4, null);
            this.listRowPresenter.setSelectEffectEnabled(false);
            this.listRowPresenter.setKeepChildForeground(false);
            this.listRowPresenter.setShadowEnabled(true);
            this.noShadowListRowPresenter.setSelectEffectEnabled(false);
            this.noShadowListRowPresenter.setKeepChildForeground(false);
            this.noShadowListRowPresenter.setShadowEnabled(false);
        }

        public final ListRowPresenter getListRowPresenter() {
            return this.listRowPresenter;
        }

        public final void setListRowPresenter(ListRowPresenter listRowPresenter) {
            Intrinsics.checkParameterIsNotNull(listRowPresenter, "<set-?>");
            this.listRowPresenter = listRowPresenter;
        }

        public final ListRowPresenter getNoShadowListRowPresenter() {
            return this.noShadowListRowPresenter;
        }

        public final void setNoShadowListRowPresenter(ListRowPresenter listRowPresenter) {
            Intrinsics.checkParameterIsNotNull(listRowPresenter, "<set-?>");
            this.noShadowListRowPresenter = listRowPresenter;
        }

        @Override // androidx.leanback.widget.PresenterSelector
        public Presenter getPresenter(Object item) {
            Intrinsics.checkParameterIsNotNull(item, "item");
            return item instanceof NoShadowListRow ? this.noShadowListRowPresenter : this.listRowPresenter;
        }

        @Override // androidx.leanback.widget.PresenterSelector
        public Presenter[] getPresenters() {
            return new Presenter[]{this.listRowPresenter};
        }
    }
}
