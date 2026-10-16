package com.dotscreen.portal.ui.screens.search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
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
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.ui.RoundListRowPresenter;
import com.dotscreen.portal.ui.cards.HomePresenterSelector;
import com.dotscreen.portal.ui.screens.ISwimlanesFragment;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.SearchableContent;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0002+,B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020#H\u0002J\b\u0010%\u001a\u00020#H\u0002J\b\u0010&\u001a\u00020#H\u0002J\u0012\u0010'\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020#H\u0002R4\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR4\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00052\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR4\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00052\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\t\"\u0004\b!\u0010\u000b¨\u0006-"}, d2 = {"Lcom/dotscreen/portal/ui/screens/search/SearchFragment;", "Landroidx/leanback/app/RowsSupportFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "()V", "value", "", "Lcom/dotscreen/bokit/model/TVAiring;", "airings", "getAirings", "()Ljava/util/List;", "setAirings", "(Ljava/util/List;)V", "Lcom/dotscreen/bokit/model/BrowsableItem;", "catchups", "getCatchups", "setCatchups", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "vods", "getVods", "setVods", "createAiringsRow", "", "createCatchupsRow", "createRows", "createVODsRow", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "setupEventListeners", "ItemViewClickedListener", "ItemViewSelectedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SearchFragment extends RowsSupportFragment implements ISwimlanesFragment {
    private HashMap _$_findViewCache;
    private List<? extends TVAiring> airings;
    private List<? extends BrowsableItem> catchups;
    private final Handler handler = new Handler();
    private final SparseArray<ListRow> listRows = new SparseArray<>();
    private ArrayObjectAdapter rowsAdapter;
    private List<? extends BrowsableItem> vods;

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

    public final Handler getHandler() {
        return this.handler;
    }

    public final List<TVAiring> getAirings() {
        return this.airings;
    }

    public final void setAirings(List<? extends TVAiring> list) {
        this.airings = list;
        this.handler.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.search.SearchFragment$airings$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.createAiringsRow();
            }
        });
    }

    public final List<BrowsableItem> getCatchups() {
        return this.catchups;
    }

    public final void setCatchups(List<? extends BrowsableItem> list) {
        this.catchups = list;
        this.handler.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.search.SearchFragment$catchups$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.createCatchupsRow();
            }
        });
    }

    public final List<BrowsableItem> getVods() {
        return this.vods;
    }

    public final void setVods(List<? extends BrowsableItem> list) {
        this.vods = list;
        this.handler.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.search.SearchFragment$vods$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.createVODsRow();
            }
        });
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

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createAiringsRow() {
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, 0, new HeaderItem(getString(R.string.search_swmilane_airings)), this.airings, null, null, new HomePresenterSelector(null, null, null, null, null, null, null, 127, null), true, null, 152, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createCatchupsRow() {
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, 1, new HeaderItem(getString(R.string.search_swmilane_catchups)), this.catchups, null, null, new HomePresenterSelector(null, null, null, null, null, null, null, 127, null), true, null, 152, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createVODsRow() {
        HeaderItem headerItem = new HeaderItem(getString(R.string.search_swmilane_vods));
        List<? extends BrowsableItem> list = this.vods;
        Constants constants = Constants.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, 2, headerItem, list, null, null, Constants.createPresenter$default(constants, context, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), null, 4, null), true, null, 152, null);
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
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        if (companion.getSingleton(context2).getDegradedMode()) {
            Context context3 = getContext();
            if (context3 == null) {
                Intrinsics.throwNpe();
            }
            Toast.makeText(context3, R.string.noAvailable, 1).show();
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /* JADX INFO: compiled from: SearchFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/search/SearchFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/search/SearchFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof SearchableContent) {
                Context context = SearchFragment.this.getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                UserService userService = ((PortalApplication) applicationContext).getBOService().getUserService();
                if (userService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                ((MegacableUserService) userService).registerSearchEvent((SearchableContent) item).subscribe();
            }
            if (item instanceof TVAiring) {
                FragmentActivity activity = SearchFragment.this.getActivity();
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
            if (item instanceof Media) {
                SearchFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + ((Media) item).getId())));
            }
        }
    }

    /* JADX INFO: compiled from: SearchFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/search/SearchFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/search/SearchFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        }

        public ItemViewSelectedListener() {
        }
    }
}
