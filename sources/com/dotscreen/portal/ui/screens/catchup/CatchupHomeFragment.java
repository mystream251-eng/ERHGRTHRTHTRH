package com.dotscreen.portal.ui.screens.catchup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.VerticalGridSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;
import androidx.leanback.widget.VerticalGridView;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.cards.ChannelCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.views.FilterHeroZoneView;
import com.dotscreen.tv.common.bokit.RightVChannel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatchupHomeFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0014\u001a\u00020\rH\u0002J\u0016\u0010\u0015\u001a\u00020\r2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/portal/ui/screens/catchup/CatchupHomeFragment;", "Landroidx/leanback/app/VerticalGridSupportFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "fragmentAlignment", "", "Ljava/lang/Integer;", "loadData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "setupEventListeners", "updateAdapter", "CustomVerticalGridPresenter", "ItemViewClickedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupHomeFragment extends VerticalGridSupportFragment {
    private final String TAG = CatchupHomeFragment.class.getSimpleName();
    private HashMap _$_findViewCache;
    private List<? extends Channel> channels;
    private Integer fragmentAlignment;

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

    /* JADX INFO: compiled from: CatchupHomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/portal/ui/screens/catchup/CatchupHomeFragment$CustomVerticalGridPresenter;", "Landroidx/leanback/widget/VerticalGridPresenter;", "zoom", "", "value", "", "(IZ)V", "gridView", "Landroidx/leanback/widget/VerticalGridView;", "getGridView", "()Landroidx/leanback/widget/VerticalGridView;", "setGridView", "(Landroidx/leanback/widget/VerticalGridView;)V", "initializeGridViewHolder", "", "vh", "Landroidx/leanback/widget/VerticalGridPresenter$ViewHolder;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class CustomVerticalGridPresenter extends VerticalGridPresenter {
        private VerticalGridView gridView;

        public CustomVerticalGridPresenter(int i, boolean z) {
            super(i, z);
        }

        public final VerticalGridView getGridView() {
            return this.gridView;
        }

        public final void setGridView(VerticalGridView verticalGridView) {
            this.gridView = verticalGridView;
        }

        @Override // androidx.leanback.widget.VerticalGridPresenter
        protected void initializeGridViewHolder(VerticalGridPresenter.ViewHolder vh) {
            Intrinsics.checkParameterIsNotNull(vh, "vh");
            super.initializeGridViewHolder(vh);
            VerticalGridView gridView = vh.getGridView();
            this.gridView = gridView;
            if (gridView == null) {
                Intrinsics.throwNpe();
            }
            int paddingBottom = gridView.getPaddingBottom();
            VerticalGridView verticalGridView = this.gridView;
            if (verticalGridView == null) {
                Intrinsics.throwNpe();
            }
            int paddingRight = verticalGridView.getPaddingRight();
            VerticalGridView verticalGridView2 = this.gridView;
            if (verticalGridView2 == null) {
                Intrinsics.throwNpe();
            }
            verticalGridView2.setPadding(105, 250, paddingRight, paddingBottom);
            VerticalGridView verticalGridView3 = this.gridView;
            if (verticalGridView3 == null) {
                Intrinsics.throwNpe();
            }
            verticalGridView3.setGravity(1);
            VerticalGridView verticalGridView4 = this.gridView;
            if (verticalGridView4 == null) {
                Intrinsics.throwNpe();
            }
            ViewGroup.LayoutParams layoutParams = verticalGridView4.getLayoutParams();
            Intrinsics.checkExpressionValueIsNotNull(layoutParams, "gridView!!.layoutParams");
            layoutParams.width = -1;
            VerticalGridView verticalGridView5 = this.gridView;
            if (verticalGridView5 == null) {
                Intrinsics.throwNpe();
            }
            verticalGridView5.setLayoutParams(layoutParams);
        }
    }

    @Override // androidx.leanback.app.BaseSupportFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupEventListeners();
        setAdapter(new ArrayObjectAdapter(new ChannelCardPresenter()));
        CustomVerticalGridPresenter customVerticalGridPresenter = new CustomVerticalGridPresenter(0, false);
        customVerticalGridPresenter.setNumberOfColumns(8);
        customVerticalGridPresenter.setShadowEnabled(true);
        setGridPresenter(customVerticalGridPresenter);
        loadData();
    }

    @Override // androidx.leanback.app.BaseSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        View titleView = getTitleView();
        if (titleView != null) {
            if (titleView != null) {
                FilterHeroZoneView filterHeroZoneView = (FilterHeroZoneView) titleView;
                filterHeroZoneView.setTitle(getString(R.string.home_item_catchup));
                filterHeroZoneView.setChangeListener(new FilterHeroZoneView.Companion.OnGenreChangedListener() { // from class: com.dotscreen.portal.ui.screens.catchup.CatchupHomeFragment$onViewCreated$$inlined$let$lambda$1
                    @Override // com.dotscreen.portal.ui.views.FilterHeroZoneView.Companion.OnGenreChangedListener
                    public void onGenreChanged(String newGenre) {
                        if (newGenre != null) {
                            CatchupHomeFragment catchupHomeFragment = this.this$0;
                            List list = catchupHomeFragment.channels;
                            if (list == null) {
                                Intrinsics.throwNpe();
                            }
                            ArrayList arrayList = new ArrayList();
                            for (Object obj : list) {
                                if (((Channel) obj).getGenres().contains(newGenre)) {
                                    arrayList.add(obj);
                                }
                            }
                            catchupHomeFragment.updateAdapter(arrayList);
                            return;
                        }
                        CatchupHomeFragment catchupHomeFragment2 = this.this$0;
                        List list2 = catchupHomeFragment2.channels;
                        if (list2 == null) {
                            Intrinsics.throwNpe();
                        }
                        catchupHomeFragment2.updateAdapter(list2);
                    }
                });
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.FilterHeroZoneView");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAdapter(List<? extends Channel> channels) {
        ObjectAdapter adapter = getAdapter();
        if (adapter == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.ArrayObjectAdapter");
        }
        ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) adapter;
        int size = channels.size();
        for (int i = 0; i < size; i++) {
            Channel channel = channels.get(i);
            if (i < arrayObjectAdapter.size()) {
                if (arrayObjectAdapter.get(i).hashCode() != channel.hashCode()) {
                    arrayObjectAdapter.replace(i, channel);
                }
            } else {
                arrayObjectAdapter.add(channel);
            }
        }
        if (arrayObjectAdapter.size() > channels.size()) {
            arrayObjectAdapter.removeItems(channels.size(), arrayObjectAdapter.size());
        }
    }

    private final void loadData() {
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
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) getActivity();
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        PortalApplication.Companion companion2 = PortalApplication.INSTANCE;
        Context context3 = getContext();
        if (context3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
        Disposable disposableSubscribe = companion2.getSingleton(context3).getCurrentLineup().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.catchup.CatchupHomeFragment.loadData.1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Channel>>() { // from class: com.dotscreen.portal.ui.screens.catchup.CatchupHomeFragment.loadData.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(List<Channel> channel) {
                BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) CatchupHomeFragment.this.getActivity();
                if (baseFragmentActivity2 != null) {
                    baseFragmentActivity2.setLoaderVisible(false);
                }
                CatchupHomeFragment catchupHomeFragment = CatchupHomeFragment.this;
                Intrinsics.checkExpressionValueIsNotNull(channel, "channel");
                ArrayList arrayList = new ArrayList();
                for (T t : channel) {
                    Channel channel2 = (Channel) t;
                    if (channel2.getCapabilities().getCatchup() && channel2.getCapabilities().getStartover()) {
                        arrayList.add(t);
                    }
                }
                catchupHomeFragment.channels = arrayList;
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                List list = CatchupHomeFragment.this.channels;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        linkedHashSet.addAll(((Channel) it.next()).getGenres());
                    }
                    FilterHeroZoneView filterHeroZoneView = (FilterHeroZoneView) CatchupHomeFragment.this.getTitleView();
                    if (filterHeroZoneView != null) {
                        filterHeroZoneView.setChannelGenres(CollectionsKt.sorted(linkedHashSet));
                    }
                }
                CatchupHomeFragment catchupHomeFragment2 = CatchupHomeFragment.this;
                List list2 = catchupHomeFragment2.channels;
                if (list2 == null) {
                    Intrinsics.throwNpe();
                }
                catchupHomeFragment2.updateAdapter(list2);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.catchup.CatchupHomeFragment.loadData.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) CatchupHomeFragment.this.getActivity();
                if (baseFragmentActivity2 != null) {
                    baseFragmentActivity2.setLoaderVisible(false);
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "PortalApplication.getSin… false\n                })");
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity2).getDisposable());
    }

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
    }

    /* JADX INFO: compiled from: CatchupHomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/catchup/CatchupHomeFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/catchup/CatchupHomeFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            DSLog.INSTANCE.a("Clicked on an item ");
            StringBuilder sb = new StringBuilder();
            sb.append("megacable://com.dotscreen.megacable.portal/catchup/details_channel?channelId=");
            if (item == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
            }
            RightVChannel rightVChannel = (RightVChannel) item;
            sb.append(rightVChannel.getId());
            sb.append("&channelDuration=");
            sb.append(rightVChannel.getExtraCatchupTime());
            CatchupHomeFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
        }
    }
}
