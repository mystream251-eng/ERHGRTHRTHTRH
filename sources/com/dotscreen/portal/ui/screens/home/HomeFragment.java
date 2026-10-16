package com.dotscreen.portal.ui.screens.home;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.UserStatus;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ads.Ad;
import com.dotscreen.portal.ads.INativeAdListener;
import com.dotscreen.portal.ads.TargetedAdsConfigProvider;
import com.dotscreen.portal.components.NotificationView;
import com.dotscreen.portal.model.ApplicationWidget;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.model.HomeTVRecommendationsViewModel;
import com.dotscreen.portal.model.HomeVODRecommendationsViewModel;
import com.dotscreen.portal.model.HomeWidget;
import com.dotscreen.portal.model.LastChannelsViewModel;
import com.dotscreen.portal.model.LauncherItemsViewModel;
import com.dotscreen.portal.model.LoadingWidget;
import com.dotscreen.portal.model.MyContentsViewModel;
import com.dotscreen.portal.ui.LauncherTVPersoListRow;
import com.dotscreen.portal.ui.LauncherVODPersoListRow;
import com.dotscreen.portal.ui.RoundListRowPresenter;
import com.dotscreen.portal.ui.cards.HomePresenterSelector;
import com.dotscreen.portal.ui.cards.HomeWidgetCardPresenter;
import com.dotscreen.portal.ui.screens.Activity_StartIntentKt;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.ISwimlanesFragment;
import com.dotscreen.portal.ui.views.HomeHeroZoneView;
import com.dotscreen.portal.utils.ApplicationsManager;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: HomeFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009b\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u001c\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0004cdefB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020AH\u0002J\b\u0010C\u001a\u00020AH\u0002J\b\u0010D\u001a\u00020AH\u0002J\b\u0010E\u001a\u00020AH\u0002J\b\u0010F\u001a\u00020AH\u0002J\b\u0010G\u001a\u00020AH\u0002J\b\u0010H\u001a\u00020AH\u0002J\b\u0010I\u001a\u00020AH\u0002J\b\u0010J\u001a\u00020AH\u0002J\b\u0010K\u001a\u00020AH\u0002J\u0012\u0010L\u001a\u00020A2\b\u0010M\u001a\u0004\u0018\u00010NH\u0016J\u0006\u0010O\u001a\u00020AJ\u0006\u0010P\u001a\u00020AJ\b\u0010Q\u001a\u00020AH\u0016J\b\u0010R\u001a\u00020AH\u0016J\b\u0010S\u001a\u00020AH\u0016J\b\u0010T\u001a\u00020AH\u0016J\b\u0010U\u001a\u00020AH\u0002J\b\u0010V\u001a\u00020AH\u0002J\u0010\u0010W\u001a\u00020A2\u0006\u0010X\u001a\u00020\u0012H\u0016J&\u0010Y\u001a\u00020A2\u0006\u0010Z\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\u00062\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u001fH\u0002J\b\u0010^\u001a\u00020AH\u0002J\b\u0010_\u001a\u00020AH\u0002J\b\u0010`\u001a\u00020AH\u0002J\b\u0010a\u001a\u00020AH\u0002J\b\u0010b\u001a\u00020AH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n \u0015*\u0004\u0018\u00010\u00140\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010,0+X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00102\u001a\u000203¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0010\u00106\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u00107\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"R\u000e\u00108\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00109\u001a\u0004\u0018\u00010:X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u000e\u0010?\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006g"}, d2 = {"Lcom/dotscreen/portal/ui/screens/home/HomeFragment;", "Landroidx/leanback/app/BrowseSupportFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "Lcom/dotscreen/portal/ads/INativeAdListener;", "()V", "MSG_DO_PAUSE", "", "ROW_AD", "ROW_APPLICATIONS", "ROW_CHANNELS", "ROW_MENU", "ROW_MY_CONTENTS", "ROW_SETTINGS", "ROW_TV_RECOMMENDATIONS", "ROW_VOD_RECOMMENDATIONS", "TAG", "", "adRowCreated", "", "allAppsIntent", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "backendCheckerStateDisposable", "Lio/reactivex/disposables/Disposable;", "blend_TV_reco", "blend_VOD_reco", "blend_channels", "debouncedPauseResumeHandler", "com/dotscreen/portal/ui/screens/home/HomeFragment$debouncedPauseResumeHandler$1", "Lcom/dotscreen/portal/ui/screens/home/HomeFragment$debouncedPauseResumeHandler$1;", "featuredApplications", "", "Lcom/dotscreen/portal/model/ApplicationWidget;", "fragmentAlignment", "Ljava/lang/Integer;", "homeTVRecommendationsViewModel", "Lcom/dotscreen/portal/model/HomeTVRecommendationsViewModel;", "homeVODRecommendationsViewModel", "Lcom/dotscreen/portal/model/HomeVODRecommendationsViewModel;", "isNetworkStatusConnected", "lastChannelsViewModel", "Lcom/dotscreen/portal/model/LastChannelsViewModel;", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "myContentViewModel", "Lcom/dotscreen/portal/model/MyContentsViewModel;", "networkStateDisposable", "notifHandler", "Landroid/os/Handler;", "getNotifHandler", "()Landroid/os/Handler;", "notificationCountDisposable", "notificationsCount", "resetLastChannelFocusOnResume", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "swimLanesJustCreated", "clearSwimlaneNativeAds", "", "createAdRow", "createApplicationsRow", "createLastChannelsRow", "createMenuRow", "createMyContentsRow", "createRows", "createSettingsRow", "createTVRecommendationsRow", "createVODRecommendationsRow", "invalidateRows", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onDebouncedPause", "onDebouncedResume", "onNativeAdReceived", "onPause", "onResume", "onStart", "setupEventListeners", "setupUIElements", "startHeadersTransition", "withHeaders", "tryAddingNativeAdToRecommendations", "naturalPosition", "dsAdUnitIdResId", "recommendations", "Lcom/dotscreen/bokit/model/BrowsableItem;", "updateAdRow", "updateApplicationsRow", "updateMenuRow", "updateSettingsRow", "updateTVRecommendationsRow", "HomeSelector", "ItemViewClickedListener", "ItemViewSelectedListener", "NoShadowListRow", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HomeFragment extends BrowseSupportFragment implements ISwimlanesFragment, INativeAdListener {
    private final int MSG_DO_PAUSE;
    private final int ROW_AD;
    private int ROW_APPLICATIONS;
    private int ROW_CHANNELS;
    private final int ROW_MENU;
    private int ROW_MY_CONTENTS;
    private final int ROW_SETTINGS;
    private int ROW_TV_RECOMMENDATIONS;
    private int ROW_VOD_RECOMMENDATIONS;
    private final String TAG;
    private HashMap _$_findViewCache;
    private boolean adRowCreated;
    private final Intent allAppsIntent;
    private Disposable backendCheckerStateDisposable;
    private String blend_TV_reco;
    private String blend_VOD_reco;
    private String blend_channels;
    private final HomeFragment$debouncedPauseResumeHandler$1 debouncedPauseResumeHandler;
    private final List<ApplicationWidget> featuredApplications;
    private Integer fragmentAlignment;
    private HomeTVRecommendationsViewModel homeTVRecommendationsViewModel;
    private HomeVODRecommendationsViewModel homeVODRecommendationsViewModel;
    private boolean isNetworkStatusConnected;
    private LastChannelsViewModel lastChannelsViewModel;
    private final SparseArray<ListRow> listRows;
    private MyContentsViewModel myContentViewModel;
    private Disposable networkStateDisposable;
    private final Handler notifHandler;
    private Disposable notificationCountDisposable;
    private Integer notificationsCount;
    private boolean resetLastChannelFocusOnResume;
    private ArrayObjectAdapter rowsAdapter;
    private boolean swimLanesJustCreated;

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

    @Override // androidx.leanback.app.BrowseSupportFragment
    public void startHeadersTransition(boolean withHeaders) {
    }

    /* JADX WARN: Type inference failed for: r0v18, types: [com.dotscreen.portal.ui.screens.home.HomeFragment$debouncedPauseResumeHandler$1] */
    public HomeFragment() {
        String simpleName = HomeFragment.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "HomeFragment::class.java.simpleName");
        this.TAG = simpleName;
        this.ROW_CHANNELS = -1;
        this.ROW_TV_RECOMMENDATIONS = -1;
        this.ROW_VOD_RECOMMENDATIONS = -1;
        this.ROW_MY_CONTENTS = -1;
        this.ROW_APPLICATIONS = -1;
        this.ROW_SETTINGS = 6;
        this.ROW_AD = 7;
        this.allAppsIntent = Intent.parseUri("megacable://com.dotscreen.megacable.portal/applications", 0);
        this.featuredApplications = new ArrayList();
        this.listRows = new SparseArray<>();
        this.blend_channels = Constants.INSTANCE.getLAUNCHER_CHANNELS_ROOT_CATALOG_ID();
        this.blend_TV_reco = Constants.INSTANCE.getLAUNCHER_LIVE_ROOT_CATALOG_ID();
        this.blend_VOD_reco = Constants.INSTANCE.getLAUNCHER_VOD_ROOT_CATALOG_ID();
        this.notifHandler = new Handler();
        this.MSG_DO_PAUSE = 1;
        this.debouncedPauseResumeHandler = new Handler() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment$debouncedPauseResumeHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg == null || msg.what != this.this$0.MSG_DO_PAUSE) {
                    return;
                }
                this.this$0.onDebouncedPause();
            }
        };
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

    @Override // androidx.leanback.app.BrowseSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Integer num;
        super.onStart();
        RowsSupportFragment rowsSupportFragment = getRowsSupportFragment();
        if (rowsSupportFragment == null || (num = this.fragmentAlignment) == null) {
            return;
        }
        int iIntValue = num.intValue();
        Log.d(this.TAG, "fragmentAligment value : " + iIntValue);
        rowsSupportFragment.setAlignment(iIntValue);
    }

    private final void createMenuRow() {
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new HomeWidgetCardPresenter());
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (((PortalApplication) applicationContext).getDegradedMode() && CommonUtils.isDVBCSTB(requireContext())) {
            String string = getString(R.string.home_item_epg);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.home_item_epg)");
            arrayObjectAdapter.add(new HomeWidget(string, R.drawable.menu_epg, Intent.parseUri("megacable://com.dotscreen.megacable.portal/epg/guide", 0), 0, 8, null));
        } else {
            String string2 = getString(R.string.home_item_epg);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.home_item_epg)");
            arrayObjectAdapter.add(new HomeWidget(string2, R.drawable.menu_epg, Intent.parseUri("megacable://com.dotscreen.megacable.portal/epg?section=now", 0), 0, 8, null));
        }
        String string3 = getString(R.string.home_item_catchup);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.home_item_catchup)");
        arrayObjectAdapter.add(new HomeWidget(string3, R.drawable.menu_replay, Intent.parseUri("megacable://com.dotscreen.megacable.portal/catchup", 0), 0, 8, null));
        String string4 = getString(R.string.home_item_vod);
        Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.home_item_vod)");
        arrayObjectAdapter.add(new HomeWidget(string4, R.drawable.menu_vod, Intent.parseUri("megacable://com.dotscreen.megacable.portal/vod", 0), 0, 8, null));
        String string5 = getString(R.string.home_item_my_content);
        Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.home_item_my_content)");
        arrayObjectAdapter.add(new HomeWidget(string5, R.drawable.menu_mycontent, Intent.parseUri("megacable://com.dotscreen.megacable.portal/mycontent", 0), 0, 8, null));
        String string6 = getString(R.string.home_item_search);
        Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.home_item_search)");
        arrayObjectAdapter.add(new HomeWidget(string6, R.drawable.menu_search, new Intent("android.intent.action.ASSIST").putExtra("search_type", 2), 0, 8, null));
        String string7 = getString(R.string.home_item_apps);
        Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.home_item_apps)");
        arrayObjectAdapter.add(new HomeWidget(string7, R.drawable.menu_apps, this.allAppsIntent, 0, 8, null));
        getListRows().put(this.ROW_MENU, new NoShadowListRow(this.ROW_MENU, null, arrayObjectAdapter));
        ArrayObjectAdapter rowsAdapter = getRowsAdapter();
        if (rowsAdapter == null) {
            Intrinsics.throwNpe();
        }
        rowsAdapter.add(getListRows().get(this.ROW_MENU));
    }

    private final void updateMenuRow() {
        ListRow listRow = getListRows().get(this.ROW_MENU);
        ObjectAdapter adapter = listRow != null ? listRow.getAdapter() : null;
        ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) (adapter instanceof ArrayObjectAdapter ? adapter : null);
        if (arrayObjectAdapter != null) {
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            if (((PortalApplication) applicationContext).getDegradedMode() && CommonUtils.isDVBCSTB(requireContext())) {
                String string = getString(R.string.home_item_epg);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.home_item_epg)");
                arrayObjectAdapter.replace(0, new HomeWidget(string, R.drawable.menu_epg, Intent.parseUri("megacable://com.dotscreen.megacable.portal/epg/guide", 0), 0, 8, null));
            } else {
                String string2 = getString(R.string.home_item_epg);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.home_item_epg)");
                arrayObjectAdapter.replace(0, new HomeWidget(string2, R.drawable.menu_epg, Intent.parseUri("megacable://com.dotscreen.megacable.portal/epg?section=now", 0), 0, 8, null));
            }
        }
    }

    private final void createLastChannelsRow() {
        LastChannelsViewModel lastChannelsViewModel = this.lastChannelsViewModel;
        if (lastChannelsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<? extends Channel>> value = lastChannelsViewModel.getValue();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        value.observe(activity, new Observer<List<? extends Channel>>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.createLastChannelsRow.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends Channel> list) {
                RowsSupportFragment rowsSupportFragment;
                HomeFragment homeFragment = HomeFragment.this;
                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(homeFragment, homeFragment.ROW_CHANNELS, null, list, new BaseWidget(HomeFragment.this.getString(R.string.home_lane_last_channels), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + HomeFragment.this.blend_channels, 0), false, null, 12, null), null, new HomePresenterSelector(Integer.valueOf(HomeFragment.this.getResources().getDimensionPixelSize(R.dimen.home_more_channel_card_width)), Integer.valueOf(HomeFragment.this.getResources().getDimensionPixelSize(R.dimen.home_more_channel_card_height)), null, null, null, null, null, 124, null), false, null, 210, null);
                if (HomeFragment.this.resetLastChannelFocusOnResume && (rowsSupportFragment = HomeFragment.this.getRowsSupportFragment()) != null && rowsSupportFragment.getSelectedPosition() == HomeFragment.this.ROW_CHANNELS) {
                    HomeFragment homeFragment2 = HomeFragment.this;
                    homeFragment2.setSelectedPosition(homeFragment2.ROW_CHANNELS, true, new ListRowPresenter.SelectItemViewHolderTask(1));
                }
                HomeFragment.this.resetLastChannelFocusOnResume = false;
            }
        });
    }

    private final void createTVRecommendationsRow() {
        DSLog.INSTANCE.adLogM("createTVRecommendationsRow");
        int i = this.ROW_TV_RECOMMENDATIONS;
        ArrayList arrayList = new ArrayList(6);
        for (int i2 = 0; i2 < 6; i2++) {
            arrayList.add(new LoadingWidget());
        }
        ArrayList arrayList2 = arrayList;
        BaseWidget baseWidget = new BaseWidget(getString(R.string.home_lane_tv_recommendations), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + this.blend_TV_reco, 0), false, null, 12, null);
        Constants constants = Constants.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, i, null, arrayList2, baseWidget, null, Constants.createPresenter$default(constants, context, Constants.INSTANCE.getLAUNCHER_CHANNELS_ROOT_CATALOG_ID(), null, 4, null), false, LauncherTVPersoListRow.class, 82, null);
        HomeTVRecommendationsViewModel homeTVRecommendationsViewModel = this.homeTVRecommendationsViewModel;
        if (homeTVRecommendationsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<? extends BrowsableItem>> value = homeTVRecommendationsViewModel.getValue();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        value.observe(activity, new Observer<List<? extends BrowsableItem>>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.createTVRecommendationsRow.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends BrowsableItem> recos) {
                Intrinsics.checkParameterIsNotNull(recos, "recos");
                List mutableList = CollectionsKt.toMutableList((Collection) recos);
                int size = mutableList.size();
                HomeFragment.this.tryAddingNativeAdToRecommendations(1, R.string.DS_HOME_PAGE_IN_SWIMLANE_TV_RECOMMENDATION_POSITION_1, mutableList);
                HomeFragment.this.tryAddingNativeAdToRecommendations(2, R.string.DS_HOME_PAGE_IN_SWIMLANE_TV_RECOMMENDATION_POSITION_2, mutableList);
                HomeFragment.this.tryAddingNativeAdToRecommendations(3, R.string.DS_HOME_PAGE_IN_SWIMLANE_TV_RECOMMENDATION_POSITION_3, mutableList);
                HomeFragment.this.tryAddingNativeAdToRecommendations(4, R.string.DS_HOME_PAGE_IN_SWIMLANE_TV_RECOMMENDATION_POSITION_4, mutableList);
                DSLog.INSTANCE.adLogM("HomePage -- [HANDLE AD][NATIVE][IN SWIMLANE]: " + (mutableList.size() - size) + " ads were added to the recommendation row");
                HomeFragment homeFragment = HomeFragment.this;
                int i3 = homeFragment.ROW_TV_RECOMMENDATIONS;
                BaseWidget baseWidget2 = new BaseWidget(HomeFragment.this.getString(R.string.home_lane_tv_recommendations), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + HomeFragment.this.blend_TV_reco, 0), false, null, 12, null);
                Constants constants2 = Constants.INSTANCE;
                Context context2 = HomeFragment.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(homeFragment, i3, null, mutableList, baseWidget2, null, Constants.createPresenter$default(constants2, context2, Constants.INSTANCE.getLAUNCHER_CHANNELS_ROOT_CATALOG_ID(), null, 4, null), true, null, 146, null);
            }
        });
    }

    private final void updateTVRecommendationsRow() {
        try {
            ListRow listRow = getListRows().get(this.ROW_TV_RECOMMENDATIONS);
            ObjectAdapter adapter = listRow != null ? listRow.getAdapter() : null;
            if (!(adapter instanceof ArrayObjectAdapter)) {
                adapter = null;
            }
            ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) adapter;
            if (arrayObjectAdapter != null) {
                FragmentActivity activity = getActivity();
                Application application = activity != null ? activity.getApplication() : null;
                if (application == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                ((PortalApplication) application).getCurrentNativeAdList();
                DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][IN SWIMLANE] trying to update TV recommendations row");
                arrayObjectAdapter.notifyItemRangeChanged(0, arrayObjectAdapter.size());
            }
        } catch (Exception e) {
            DSLog.INSTANCE.adLogE("[HANDLE AD][NATIVE][IN SWIMLANE] Failed to update TV recommendations row: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tryAddingNativeAdToRecommendations(int naturalPosition, int dsAdUnitIdResId, List<BrowsableItem> recommendations) {
        if (recommendations.size() == 0) {
            return;
        }
        int i = naturalPosition - 1;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        Application application = activity.getApplication();
        if (application == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        String string = getString(dsAdUnitIdResId);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(dsAdUnitIdResId)");
        Ad adFromCurrentNativeAdList = ((PortalApplication) application).getAdFromCurrentNativeAdList(string);
        if (recommendations.size() > i) {
            if (adFromCurrentNativeAdList != null) {
                recommendations.add(i, adFromCurrentNativeAdList);
            }
        } else if (adFromCurrentNativeAdList != null) {
            recommendations.add(adFromCurrentNativeAdList);
        }
    }

    private final void createVODRecommendationsRow() {
        final BaseWidget baseWidget = new BaseWidget(getString(R.string.home_lane_vod_recommendations), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?collection_id=" + Constants.INSTANCE.getHOME_HIGHLIGHT_CATALOG_ID() + "&catalog_id=" + Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), 0), false, null, 12, null);
        int i = this.ROW_VOD_RECOMMENDATIONS;
        ArrayList arrayList = new ArrayList(6);
        for (int i2 = 0; i2 < 6; i2++) {
            arrayList.add(new LoadingWidget());
        }
        ArrayList arrayList2 = arrayList;
        Constants constants = Constants.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, i, null, arrayList2, baseWidget, null, Constants.createPresenter$default(constants, context, Constants.INSTANCE.getLAUNCHER_VOD_ROOT_CATALOG_ID(), null, 4, null), false, LauncherVODPersoListRow.class, 82, null);
        HomeVODRecommendationsViewModel homeVODRecommendationsViewModel = this.homeVODRecommendationsViewModel;
        if (homeVODRecommendationsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<? extends BrowsableItem>> value = homeVODRecommendationsViewModel.getValue();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        value.observe(activity, new Observer<List<? extends BrowsableItem>>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.createVODRecommendationsRow.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends BrowsableItem> recos) {
                Intrinsics.checkParameterIsNotNull(recos, "recos");
                Log.d(HomeFragment.this.TAG, "VOD Recommendation list: " + recos);
                HomeFragment homeFragment = HomeFragment.this;
                int i3 = homeFragment.ROW_VOD_RECOMMENDATIONS;
                BaseWidget baseWidget2 = baseWidget;
                Constants constants2 = Constants.INSTANCE;
                Context context2 = HomeFragment.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(homeFragment, i3, null, recos, baseWidget2, null, Constants.createPresenter$default(constants2, context2, Constants.INSTANCE.getLAUNCHER_VOD_ROOT_CATALOG_ID(), null, 4, null), true, null, 146, null);
            }
        });
    }

    private final void createMyContentsRow() {
        int i = this.ROW_MY_CONTENTS;
        List listListOf = CollectionsKt.listOf(new LoadingWidget());
        BaseWidget baseWidget = new BaseWidget(getString(R.string.home_lane_my_content), Intent.parseUri("megacable://com.dotscreen.megacable.portal/mycontent", 0), false, null, 12, null);
        Constants constants = Constants.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, i, null, listListOf, baseWidget, null, Constants.createPresenter$default(constants, context, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), null, 4, null), false, null, 210, null);
        MyContentsViewModel myContentsViewModel = this.myContentViewModel;
        if (myContentsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<Media>> bookmarks = myContentsViewModel.getBookmarks();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        bookmarks.observe(activity, new Observer<List<? extends Media>>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.createMyContentsRow.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends Media> list) {
                onChanged2((List<? extends BrowsableItem>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<? extends BrowsableItem> list) {
                Log.d(HomeFragment.this.TAG, "bookmarks list: " + list);
                HomeFragment homeFragment = HomeFragment.this;
                int i2 = homeFragment.ROW_MY_CONTENTS;
                BaseWidget baseWidget2 = new BaseWidget(HomeFragment.this.getString(R.string.home_lane_my_content), Intent.parseUri("megacable://com.dotscreen.megacable.portal/mycontent", 0), false, null, 12, null);
                Constants constants2 = Constants.INSTANCE;
                Context context2 = HomeFragment.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(homeFragment, i2, null, list, baseWidget2, null, Constants.createPresenter$default(constants2, context2, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), null, 4, null), true, null, 146, null);
            }
        });
    }

    private final void createApplicationsRow() {
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, this.ROW_APPLICATIONS, null, this.featuredApplications, new BaseWidget(getString(R.string.home_lane_featured_apps), this.allAppsIntent, false, null, 12, null), null, new HomePresenterSelector(Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.home_more_apps_card_width)), Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.home_more_apps_card_height)), null, null, null, null, null, 124, null), false, null, 210, null);
    }

    private final void updateApplicationsRow() {
        ObjectAdapter adapter;
        ListRow listRow = getListRows().get(this.ROW_APPLICATIONS);
        if (listRow == null || (adapter = listRow.getAdapter()) == null) {
            return;
        }
        adapter.notifyItemRangeChanged(0, this.featuredApplications.size() + 1);
    }

    private final void createSettingsRow() {
        PortalApplication.Config value;
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new HomePresenterSelector(Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.settings_card_width)), Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.settings_card_height)), null, null, null, null, null, 124, null));
        arrayObjectAdapter.add(new BaseWidget(getString(R.string.home_item_setup_settings), Intent.parseUri("megacable://com.dotscreen.megacable.portal/settings", 0), false, null, 12, null));
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) applicationContext;
        if (!portalApplication.getDegradedMode() && (value = portalApplication.getConfig().getValue()) != null && value.getUser() != null) {
            arrayObjectAdapter.add(1, new BaseWidget(getString(R.string.setting_item_profiles), Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=manage", 0), false, null, 12, null));
            arrayObjectAdapter.add(2, new BaseWidget(getString(R.string.setting_item_purchase_pin), Intent.parseUri("megacable://com.dotscreen.megacable.portal/home?action=update_purchase", 0), false, null, 12, null));
        }
        getListRows().put(this.ROW_SETTINGS, new ListRow(this.ROW_SETTINGS, null, arrayObjectAdapter));
        ArrayObjectAdapter rowsAdapter = getRowsAdapter();
        if (rowsAdapter == null) {
            Intrinsics.throwNpe();
        }
        rowsAdapter.add(getListRows().get(this.ROW_SETTINGS));
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        ViewModel viewModel = ViewModelProviders.of(activity, new LauncherItemsViewModel.Factory(context2)).get(LauncherItemsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac…emsViewModel::class.java)");
        LiveData<LauncherItemsViewModel.SettingsItem> settingsItems = ((LauncherItemsViewModel) viewModel).getSettingsItems();
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        settingsItems.observe(activity2, new Observer<LauncherItemsViewModel.SettingsItem>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.createSettingsRow.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LauncherItemsViewModel.SettingsItem settingsItem) {
                ListRow listRow = HomeFragment.this.getListRows().get(HomeFragment.this.ROW_SETTINGS);
                if (listRow == null) {
                    Intrinsics.throwNpe();
                }
                ObjectAdapter adapter = listRow.getAdapter();
                if (adapter == null) {
                    throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.ArrayObjectAdapter");
                }
                ArrayObjectAdapter arrayObjectAdapter2 = (ArrayObjectAdapter) adapter;
                int size = arrayObjectAdapter2.size();
                if (settingsItem == null) {
                    Intrinsics.throwNpe();
                }
                if (settingsItem.getSystemSettingsItem() != null) {
                    BaseWidget systemSettingsItem = settingsItem.getSystemSettingsItem();
                    if (systemSettingsItem == null) {
                        Intrinsics.throwNpe();
                    }
                    systemSettingsItem.setTitle(HomeFragment.this.getString(R.string.home_item_setup_system));
                    arrayObjectAdapter2.add(settingsItem.getSystemSettingsItem());
                }
                arrayObjectAdapter2.notifyArrayItemRangeChanged(size, 2);
            }
        });
    }

    private final void updateSettingsRow() {
        try {
            ListRow listRow = getListRows().get(this.ROW_SETTINGS);
            ObjectAdapter adapter = listRow != null ? listRow.getAdapter() : null;
            if (!(adapter instanceof ArrayObjectAdapter)) {
                adapter = null;
            }
            ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) adapter;
            if (arrayObjectAdapter != null) {
                Context context = getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                PortalApplication portalApplication = (PortalApplication) applicationContext;
                Object obj = arrayObjectAdapter.get(1);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.model.BaseWidget");
                }
                BaseWidget baseWidget = (BaseWidget) obj;
                Object obj2 = arrayObjectAdapter.get(2);
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.model.BaseWidget");
                }
                BaseWidget baseWidget2 = (BaseWidget) obj2;
                if (!portalApplication.getDegradedMode()) {
                    PortalApplication.Config value = portalApplication.getConfig().getValue();
                    if (value != null && value.getUser() != null) {
                        if (!Intrinsics.areEqual(baseWidget.getTitle(), getString(R.string.setting_item_profiles))) {
                            arrayObjectAdapter.add(1, new BaseWidget(getString(R.string.setting_item_profiles), Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=manage", 0), false, null, 12, null));
                        }
                        if (!Intrinsics.areEqual(baseWidget2.getTitle(), getString(R.string.setting_item_purchase_pin))) {
                            arrayObjectAdapter.add(2, new BaseWidget(getString(R.string.setting_item_purchase_pin), Intent.parseUri("megacable://com.dotscreen.megacable.portal/home?action=update_purchase", 0), false, null, 12, null));
                        }
                    }
                } else {
                    PortalApplication.Config value2 = portalApplication.getConfig().getValue();
                    if (value2 != null && value2.getUser() != null) {
                        if (Intrinsics.areEqual(baseWidget.getTitle(), getString(R.string.setting_item_profiles))) {
                            arrayObjectAdapter.remove(baseWidget);
                        }
                        if (Intrinsics.areEqual(baseWidget2.getTitle(), getString(R.string.setting_item_purchase_pin))) {
                            arrayObjectAdapter.remove(baseWidget2);
                        }
                    }
                }
                TargetedAdsConfigProvider.Companion companion = TargetedAdsConfigProvider.INSTANCE;
                String string = getString(R.string.DS_HOME_PAGE_CUSTOM_IN_SETTINGS_SWIMLANE);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.DS_HO…TOM_IN_SETTINGS_SWIMLANE)");
                if (companion.existsVOAdUnitIdForCustomAd(string)) {
                    FragmentActivity activity = getActivity();
                    Application application = activity != null ? activity.getApplication() : null;
                    if (application == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    for (Ad ad : ((PortalApplication) application).getCurrentNativeAdList()) {
                        if (Intrinsics.areEqual(ad.getDsAdUnitId(), getString(R.string.DS_HOME_PAGE_CUSTOM_IN_SETTINGS_SWIMLANE))) {
                            DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][CUSTOM][VO-" + ad.getAdUnitId() + "] -> [DS-" + ad.getDsAdUnitId() + "] OK");
                            if (!(arrayObjectAdapter.get(arrayObjectAdapter.size() - 1) instanceof Ad)) {
                                arrayObjectAdapter.add(ad);
                            } else {
                                arrayObjectAdapter.replace(arrayObjectAdapter.size() - 1, ad);
                            }
                        }
                    }
                }
                arrayObjectAdapter.notifyItemRangeChanged(0, arrayObjectAdapter.size());
            }
        } catch (Exception e) {
            Log.e(this.TAG, "Error: " + e.getMessage());
        }
    }

    private final void createAdRow() {
        if (this.adRowCreated) {
            return;
        }
        this.adRowCreated = true;
        DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][CUSTOM] Ad row created in home activity");
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new HomePresenterSelector(null, null, null, null, null, null, null, 127, null));
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        getListRows().put(this.ROW_AD, new ListRow(this.ROW_AD, null, arrayObjectAdapter));
        ArrayObjectAdapter rowsAdapter = getRowsAdapter();
        if (rowsAdapter == null) {
            Intrinsics.throwNpe();
        }
        rowsAdapter.add(getListRows().get(this.ROW_AD));
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        ViewModel viewModel = ViewModelProviders.of(activity, new LauncherItemsViewModel.Factory(context2)).get(LauncherItemsViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac…emsViewModel::class.java)");
        LiveData<LauncherItemsViewModel.SettingsItem> settingsItems = ((LauncherItemsViewModel) viewModel).getSettingsItems();
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        settingsItems.observe(activity2, new Observer<LauncherItemsViewModel.SettingsItem>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.createAdRow.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LauncherItemsViewModel.SettingsItem settingsItem) {
                ListRow listRow = HomeFragment.this.getListRows().get(HomeFragment.this.ROW_AD);
                if (listRow == null) {
                    Intrinsics.throwNpe();
                }
                ObjectAdapter adapter = listRow.getAdapter();
                if (adapter == null) {
                    throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.ArrayObjectAdapter");
                }
                ArrayObjectAdapter arrayObjectAdapter2 = (ArrayObjectAdapter) adapter;
                arrayObjectAdapter2.notifyArrayItemRangeChanged(arrayObjectAdapter2.size(), 1);
            }
        });
    }

    private final void updateAdRow() {
        try {
            ListRow listRow = getListRows().get(this.ROW_AD);
            ObjectAdapter adapter = listRow != null ? listRow.getAdapter() : null;
            if (!(adapter instanceof ArrayObjectAdapter)) {
                adapter = null;
            }
            ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) adapter;
            if (arrayObjectAdapter != null) {
                FragmentActivity activity = getActivity();
                Application application = activity != null ? activity.getApplication() : null;
                if (application == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                List<Ad> currentNativeAdList = ((PortalApplication) application).getCurrentNativeAdList();
                DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][CUSTOM] trying to update ad swimlane");
                for (Ad ad : currentNativeAdList) {
                    if (Intrinsics.areEqual(ad.getDsAdUnitId(), getString(R.string.DS_HOME_PAGE_CUSTOM_IN_AD_SWIMLANE))) {
                        if (arrayObjectAdapter.size() == 0 || !(arrayObjectAdapter.get(arrayObjectAdapter.size() - 1) instanceof Ad)) {
                            DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][CUSTOM][VO-" + ad.getAdUnitId() + "] -> [DS-" + ad.getDsAdUnitId() + "] Ad added");
                            arrayObjectAdapter.add(ad);
                        } else {
                            DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][CUSTOM][VO-" + ad.getAdUnitId() + "] -> [DS-" + ad.getDsAdUnitId() + "] Ad replaced");
                            arrayObjectAdapter.replace(arrayObjectAdapter.size() - 1, ad);
                        }
                    }
                }
                arrayObjectAdapter.notifyItemRangeChanged(arrayObjectAdapter.size() - 1, 1);
            }
        } catch (Exception e) {
            DSLog.INSTANCE.adLogE("[HANDLE AD][NATIVE][CUSTOM] Failed to update ad row: " + e);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        List<PortalApplication.SwimLane> swimLanes;
        PackageManager packageManager;
        super.onActivityCreated(savedInstanceState);
        Log.i(this.TAG, "onCreate");
        setupUIElements();
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) applicationContext;
        portalApplication.addNativeAdAddedListener(this);
        createRows();
        PortalApplication.Config value = portalApplication.getConfig().getValue();
        if (value != null && (swimLanes = value.getSwimLanes()) != null) {
            int i = 0;
            int i2 = 0;
            for (Object obj : swimLanes) {
                int i3 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                PortalApplication.SwimLane swimLane = (PortalApplication.SwimLane) obj;
                String type = swimLane.getType();
                switch (type.hashCode()) {
                    case -1822967846:
                        if (type.equals("recommendations")) {
                            this.ROW_TV_RECOMMENDATIONS = i3;
                            this.ROW_VOD_RECOMMENDATIONS = i + 2;
                            i2 = 1;
                        }
                        break;
                    case -1045885981:
                        if (type.equals("featured_apps")) {
                            this.ROW_APPLICATIONS = i3 + i2;
                            if (swimLane instanceof PortalApplication.ApplicationSwimLane) {
                                for (PortalApplication.ApplicationSwimLane.Item item : ((PortalApplication.ApplicationSwimLane) swimLane).getItems()) {
                                    if (item.getName() != null && item.getPackageName() != null && item.getBannerUrl() != null) {
                                        Context context2 = getContext();
                                        this.featuredApplications.add(new ApplicationWidget(item.getName(), item.getPackageName(), item.getBannerUrl(), (context2 == null || (packageManager = context2.getPackageManager()) == null) ? null : packageManager.getLeanbackLaunchIntentForPackage(item.getPackageName())));
                                    }
                                }
                            }
                        }
                        break;
                    case -589005594:
                        if (type.equals("my_content")) {
                            this.ROW_MY_CONTENTS = i3 + i2;
                        }
                        break;
                    case 2019773273:
                        if (type.equals("last_channels")) {
                            this.ROW_CHANNELS = i3 + i2;
                        }
                        break;
                }
                i = i3;
            }
            if (!portalApplication.getDegradedMode()) {
                FragmentActivity activity = getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                Context context3 = getContext();
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                FragmentActivity activity2 = getActivity();
                if (activity2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                }
                this.myContentViewModel = (MyContentsViewModel) ViewModelProviders.of(activity, new MyContentsViewModel.Factory(context3, ((BaseFragmentActivity) activity2).isAdultBrowsing())).get(MyContentsViewModel.class);
                FragmentActivity activity3 = getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                Context context4 = getContext();
                if (context4 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context4, "context!!");
                this.homeTVRecommendationsViewModel = (HomeTVRecommendationsViewModel) ViewModelProviders.of(activity3, new HomeTVRecommendationsViewModel.Factory(context4, this.blend_TV_reco)).get(HomeTVRecommendationsViewModel.class);
                FragmentActivity activity4 = getActivity();
                if (activity4 == null) {
                    Intrinsics.throwNpe();
                }
                Context context5 = getContext();
                if (context5 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context5, "context!!");
                this.homeVODRecommendationsViewModel = (HomeVODRecommendationsViewModel) ViewModelProviders.of(activity4, new HomeVODRecommendationsViewModel.Factory(context5, this.blend_VOD_reco)).get(HomeVODRecommendationsViewModel.class);
                FragmentActivity activity5 = getActivity();
                if (activity5 == null) {
                    Intrinsics.throwNpe();
                }
                Context context6 = getContext();
                if (context6 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context6, "context!!");
                this.lastChannelsViewModel = (LastChannelsViewModel) ViewModelProviders.of(activity5, new LastChannelsViewModel.Factory(context6)).get(LastChannelsViewModel.class);
                createLastChannelsRow();
                createTVRecommendationsRow();
                createVODRecommendationsRow();
                createMyContentsRow();
                this.swimLanesJustCreated = true;
            }
            createApplicationsRow();
        }
        setupEventListeners();
    }

    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/ui/screens/home/HomeFragment$NoShadowListRow;", "Landroidx/leanback/widget/ListRow;", "id", "", "header", "Landroidx/leanback/widget/HeaderItem;", "adapter", "Landroidx/leanback/widget/ObjectAdapter;", "(JLandroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final class NoShadowListRow extends ListRow {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoShadowListRow(long j, HeaderItem headerItem, ObjectAdapter adapter) {
            super(j, headerItem, adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        }
    }

    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u0016¢\u0006\u0002\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/screens/home/HomeFragment$HomeSelector;", "Landroidx/leanback/widget/PresenterSelector;", "(Lcom/dotscreen/portal/ui/screens/home/HomeFragment;)V", "listRowPresenter", "Landroidx/leanback/widget/ListRowPresenter;", "getListRowPresenter", "()Landroidx/leanback/widget/ListRowPresenter;", "setListRowPresenter", "(Landroidx/leanback/widget/ListRowPresenter;)V", "noShadowListRowPresenter", "getNoShadowListRowPresenter", "setNoShadowListRowPresenter", "getPresenter", "Landroidx/leanback/widget/Presenter;", "item", "", "getPresenters", "", "()[Landroidx/leanback/widget/Presenter;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class HomeSelector extends PresenterSelector {
        private ListRowPresenter listRowPresenter;
        private ListRowPresenter noShadowListRowPresenter;

        public HomeSelector() {
            Context context = HomeFragment.this.getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            this.listRowPresenter = new RoundListRowPresenter(context, 4, false, 4, null);
            Context context2 = HomeFragment.this.getContext();
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

    private final void createRows() {
        setRowsAdapter(new ArrayObjectAdapter(new HomeSelector()));
        createMenuRow();
        createSettingsRow();
        setAdapter(getRowsAdapter());
        setSelectedPosition(0);
        View view = getView();
        if (view == null) {
            Intrinsics.throwNpe();
        }
        view.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.createRows.1
            @Override // java.lang.Runnable
            public void run() {
                Resources resources;
                int i;
                RowsSupportFragment rowsSupportFragment = HomeFragment.this.getRowsSupportFragment();
                if (rowsSupportFragment != null && rowsSupportFragment.getVerticalGridView() != null) {
                    PortalApplication.Companion companion = PortalApplication.INSTANCE;
                    Context context = HomeFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                    if (companion.getSingleton(context).getDegradedMode()) {
                        Log.d(HomeFragment.this.TAG, "createRows() => set fragment aligment");
                        HomeFragment homeFragment = HomeFragment.this;
                        if (CommonUtils.isDVBCSTB(homeFragment.getContext())) {
                            resources = HomeFragment.this.getResources();
                            i = R.dimen.home_with_herozone_margin_top;
                        } else {
                            resources = HomeFragment.this.getResources();
                            i = R.dimen.browse_without_highlights_margin_top;
                        }
                        homeFragment.fragmentAlignment = Integer.valueOf(resources.getDimensionPixelSize(i));
                        Integer num = HomeFragment.this.fragmentAlignment;
                        if (num == null) {
                            Intrinsics.throwNpe();
                        }
                        rowsSupportFragment.setAlignment(num.intValue());
                    }
                    rowsSupportFragment.getVerticalGridView().requestFocus();
                    return;
                }
                View view2 = HomeFragment.this.getView();
                if (view2 != null) {
                    view2.postDelayed(this, 10L);
                }
            }
        });
    }

    private final void setupUIElements() {
        setHeadersState(3);
        setHeadersTransitionOnBackEnabled(false);
    }

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
        View titleView = getTitleView();
        if (!(titleView instanceof HomeHeroZoneView)) {
            titleView = null;
        }
        HomeHeroZoneView homeHeroZoneView = (HomeHeroZoneView) titleView;
        if (homeHeroZoneView != null) {
            homeHeroZoneView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment$setupEventListeners$$inlined$let$lambda$1
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    RowsSupportFragment rowsSupportFragment;
                    VerticalGridView verticalGridView;
                    if (!z || (rowsSupportFragment = this.this$0.getRowsSupportFragment()) == null || (verticalGridView = rowsSupportFragment.getVerticalGridView()) == null) {
                        return;
                    }
                    verticalGridView.scrollToPosition(0);
                }
            });
        }
    }

    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/home/HomeFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/home/HomeFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) throws URISyntaxException {
            String str;
            Intrinsics.checkParameterIsNotNull(itemViewHolder, "itemViewHolder");
            Intrinsics.checkParameterIsNotNull(item, "item");
            Intrinsics.checkParameterIsNotNull(rowViewHolder, "rowViewHolder");
            Intrinsics.checkParameterIsNotNull(row, "row");
            Log.d(HomeFragment.this.TAG, "onItemClicked: " + item);
            if (item instanceof TVAiring) {
                DSLog dSLog = DSLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("Clicked on the ");
                TVAiring tVAiring = (TVAiring) item;
                sb.append(tVAiring.getTitle());
                sb.append(" TVAiring");
                dSLog.a(sb.toString());
                Context context = HomeFragment.this.getContext();
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
                MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, (BrowsableItem) item, null, null, null, 14, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment$ItemViewClickedListener$onItemClicked$1
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment$ItemViewClickedListener$onItemClicked$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                    }
                });
                FragmentActivity activity = HomeFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + tVAiring.getChannel().getId() + "&start=" + tVAiring.getStartDate().getTime())));
                return;
            }
            if (item instanceof Channel) {
                DSLog dSLog2 = DSLog.INSTANCE;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Clicked on the ");
                Channel channel = (Channel) item;
                sb2.append(channel.getTitle());
                sb2.append("Channel");
                dSLog2.a(sb2.toString());
                HomeFragment.this.resetLastChannelFocusOnResume = true;
                FragmentActivity activity2 = HomeFragment.this.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                activity2.startActivity(new Intent("android.intent.action.VIEW", CommonUtils.getLiveTvDeeplink(HomeFragment.this.getContext(), channel, (Boolean) true)));
                return;
            }
            if (item instanceof HomeWidget) {
                DSLog dSLog3 = DSLog.INSTANCE;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Clicked on the ");
                HomeWidget homeWidget = (HomeWidget) item;
                sb3.append(homeWidget.getTitle());
                sb3.append(" HomeWidget");
                dSLog3.a(sb3.toString());
                if (homeWidget.getIntent() != null) {
                    FragmentActivity activity3 = HomeFragment.this.getActivity();
                    if (activity3 != null) {
                        Activity_StartIntentKt.startIntent(activity3, homeWidget.getIntent());
                        return;
                    }
                    return;
                }
                Toast.makeText(HomeFragment.this.getActivity(), R.string.noAvailable, 0).show();
                return;
            }
            if (item instanceof ApplicationWidget) {
                DSLog dSLog4 = DSLog.INSTANCE;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Clicked on the ");
                ApplicationWidget applicationWidget = (ApplicationWidget) item;
                sb4.append(applicationWidget.getTitle());
                sb4.append(" ApplicationWidget");
                dSLog4.a(sb4.toString());
                PortalApplication.Companion companion = PortalApplication.INSTANCE;
                Context context2 = HomeFragment.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                ApplicationsManager applicationsManager = companion.getSingleton(context2).getApplicationsManager();
                FragmentActivity activity4 = HomeFragment.this.getActivity();
                if (activity4 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(activity4, "activity!!");
                applicationsManager.launchApplication(activity4, applicationWidget);
                return;
            }
            if (item instanceof BaseWidget) {
                DSLog dSLog5 = DSLog.INSTANCE;
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Clicked on the ");
                BaseWidget baseWidget = (BaseWidget) item;
                sb5.append(baseWidget.getTitle());
                sb5.append(" BaseWidget");
                dSLog5.a(sb5.toString());
                if (baseWidget.getIntent() != null) {
                    Uri data = baseWidget.getIntent().getData();
                    if (data != null && Intrinsics.areEqual(data.getQueryParameter("action"), "update_purchase")) {
                        FragmentActivity activity5 = HomeFragment.this.getActivity();
                        if (activity5 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.home.HomeActivity");
                        }
                        ((HomeActivity) activity5).openConfigDialog();
                        return;
                    }
                    FragmentActivity activity6 = HomeFragment.this.getActivity();
                    if (activity6 != null) {
                        Activity_StartIntentKt.startIntent(activity6, baseWidget.getIntent());
                        return;
                    }
                    return;
                }
                return;
            }
            if (item instanceof Ad) {
                DSLog dSLog6 = DSLog.INSTANCE;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("Clicked on the ");
                Ad ad = (Ad) item;
                sb6.append(ad.getAdUnitId());
                sb6.append(" Native Ad");
                dSLog6.a(sb6.toString());
                Context context3 = HomeFragment.this.getContext();
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                Context applicationContext2 = context3.getApplicationContext();
                if (applicationContext2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                ((PortalApplication) applicationContext2).handleInSwimlaneNativeAdClick(ad);
                return;
            }
            if (item instanceof BrowsableItem) {
                DSLog dSLog7 = DSLog.INSTANCE;
                StringBuilder sb7 = new StringBuilder();
                sb7.append("Clicked on the ");
                BrowsableItem browsableItem = (BrowsableItem) item;
                sb7.append(browsableItem.getTitle());
                sb7.append(" BrowsableItem");
                dSLog7.a(sb7.toString());
                Log.d("CompassAnalytics", " rowVH => " + rowViewHolder + ", row => " + row);
                Context context4 = HomeFragment.this.getContext();
                if (context4 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context4, "context!!");
                Context applicationContext3 = context4.getApplicationContext();
                if (applicationContext3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                VODCatalogService vodCatalogService2 = ((PortalApplication) applicationContext3).getBOService().getVodCatalogService();
                if (vodCatalogService2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
                }
                MegacableVODCatalogService megacableVODCatalogService = (MegacableVODCatalogService) vodCatalogService2;
                if (row instanceof LauncherTVPersoListRow) {
                    str = HomeFragment.this.blend_TV_reco;
                } else {
                    str = row instanceof LauncherVODPersoListRow ? HomeFragment.this.blend_VOD_reco : null;
                }
                MegacableVODCatalogService.registerClick$default(megacableVODCatalogService, browsableItem, null, str, null, 10, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment$ItemViewClickedListener$onItemClicked$3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment$ItemViewClickedListener$onItemClicked$4
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                    }
                });
                Intent uri = Intent.parseUri("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + browsableItem.getId(), 0);
                FragmentActivity activity7 = HomeFragment.this.getActivity();
                if (activity7 != null) {
                    activity7.startActivity(uri);
                }
            }
        }
    }

    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/home/HomeFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/home/HomeFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        public ItemViewSelectedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            Integer numValueOf;
            String str = HomeFragment.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onItemSelected: ");
            sb.append(item);
            sb.append(", ");
            if (row == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.ListRow");
            }
            ListRow listRow = (ListRow) row;
            sb.append(listRow.getId());
            Log.d(str, sb.toString());
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = HomeFragment.this.getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            if (companion.getSingleton(context).getDegradedMode()) {
                Context context2 = HomeFragment.this.getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!CommonUtils.isDVBCSTB(context2)) {
                    return;
                }
            }
            Log.d(HomeFragment.this.TAG, "onItemSelected => " + listRow.getId());
            HomeFragment homeFragment = HomeFragment.this;
            if (listRow.getId() == 0) {
                numValueOf = Integer.valueOf(HomeFragment.this.getResources().getDimensionPixelSize(R.dimen.home_with_herozone_margin_top));
            } else {
                numValueOf = Integer.valueOf(HomeFragment.this.getResources().getDimensionPixelSize(R.dimen.browse_without_highlights_margin_top));
            }
            homeFragment.fragmentAlignment = numValueOf;
            RowsSupportFragment rowsSupportFragment = HomeFragment.this.getRowsSupportFragment();
            if (rowsSupportFragment != null) {
                Integer num = HomeFragment.this.fragmentAlignment;
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                rowsSupportFragment.setAlignment(num.intValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:39:0x00a4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:41:0x00b9  */
    public final void invalidateRows() {
        int dimensionPixelSize;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (!((PortalApplication) applicationContext).getDegradedMode()) {
            if (!this.swimLanesJustCreated) {
                LastChannelsViewModel lastChannelsViewModel = this.lastChannelsViewModel;
                if (lastChannelsViewModel != null) {
                    lastChannelsViewModel.invalidateData();
                }
                HomeTVRecommendationsViewModel homeTVRecommendationsViewModel = this.homeTVRecommendationsViewModel;
                if (homeTVRecommendationsViewModel != null) {
                    homeTVRecommendationsViewModel.invalidateData();
                }
                HomeVODRecommendationsViewModel homeVODRecommendationsViewModel = this.homeVODRecommendationsViewModel;
                if (homeVODRecommendationsViewModel != null) {
                    homeVODRecommendationsViewModel.invalidateData();
                }
            }
            this.swimLanesJustCreated = false;
            MyContentsViewModel myContentsViewModel = this.myContentViewModel;
            if (myContentsViewModel != null) {
                myContentsViewModel.invalidateBookmarks();
            }
        } else {
            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, this.ROW_CHANNELS, null, null, null, null, null, false, null, 250, null);
            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, this.ROW_TV_RECOMMENDATIONS, null, null, null, null, null, false, null, 250, null);
            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, this.ROW_VOD_RECOMMENDATIONS, null, null, null, null, null, false, null, 250, null);
            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, this.ROW_MY_CONTENTS, null, null, null, null, null, false, null, 250, null);
        }
        updateMenuRow();
        updateSettingsRow();
        updateApplicationsRow();
        HomeHeroZoneView homeHeroZoneView = (HomeHeroZoneView) getTitleView();
        if (homeHeroZoneView != null) {
            homeHeroZoneView.invalidateData();
        }
        RowsSupportFragment rowsSupportFragment = getRowsSupportFragment();
        if (rowsSupportFragment != null) {
            int selectedPosition = rowsSupportFragment.getSelectedPosition();
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
                if (!CommonUtils.isDVBCSTB(context3)) {
                    Log.d(this.TAG, "invalidateRows() => set without herozone");
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.browse_without_highlights_margin_top);
                } else if (selectedPosition != 0) {
                    Log.d(this.TAG, "invalidateRows() => set without herozone");
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.browse_without_highlights_margin_top);
                } else {
                    Log.d(this.TAG, "invalidateRows() => set with herozone");
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.home_with_herozone_margin_top);
                }
            } else if (selectedPosition != 0) {
                Log.d(this.TAG, "invalidateRows() => set without herozone");
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.browse_without_highlights_margin_top);
            } else {
                Log.d(this.TAG, "invalidateRows() => set with herozone");
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.home_with_herozone_margin_top);
            }
            Integer num = this.fragmentAlignment;
            if (num != null && num.intValue() == dimensionPixelSize) {
                return;
            }
            Log.d(this.TAG, "invalidateRows() => set fragment aligment");
            Integer numValueOf = Integer.valueOf(dimensionPixelSize);
            this.fragmentAlignment = numValueOf;
            if (numValueOf == null) {
                Intrinsics.throwNpe();
            }
            rowsSupportFragment.setAlignment(numValueOf.intValue());
        }
    }

    public final Handler getNotifHandler() {
        return this.notifHandler;
    }

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onPause() {
        Log.v(this.TAG, "onPause()");
        sendEmptyMessageDelayed(this.MSG_DO_PAUSE, 200L);
        super.onPause();
    }

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onResume() {
        Application application;
        Application application2;
        Class<?> cls;
        super.onResume();
        Log.v(this.TAG, "onResume()");
        FragmentActivity activity = getActivity();
        Application application3 = activity != null ? activity.getApplication() : null;
        if (application3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (!((PortalApplication) application3).getIsSartupScreenAlreadyDisplayed()) {
            FragmentActivity activity2 = getActivity();
            Application application4 = activity2 != null ? activity2.getApplication() : null;
            if (application4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            ((PortalApplication) application4).setSartupScreenAlreadyDisplayed(true);
            DSLog dSLog = DSLog.INSTANCE;
            StringBuilder sb = new StringBuilder();
            FragmentActivity activity3 = getActivity();
            sb.append((activity3 == null || (application2 = activity3.getApplication()) == null || (cls = application2.getClass()) == null) ? null : cls.getSimpleName());
            sb.append('@');
            FragmentActivity activity4 = getActivity();
            sb.append((activity4 == null || (application = activity4.getApplication()) == null) ? null : Integer.valueOf(application.hashCode()));
            sb.append(" > ");
            sb.append(this.TAG);
            dSLog.appStartTimeStamp(sb.toString(), true);
        }
        DSLog dSLog2 = DSLog.INSTANCE;
        FragmentActivity activity5 = getActivity();
        dSLog2.printCurrentRAMUsage((ActivityManager) (activity5 != null ? activity5.getSystemService("activity") : null));
        FragmentActivity activity6 = getActivity();
        Application application5 = activity6 != null ? activity6.getApplication() : null;
        if (application5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        ((PortalApplication) application5).addNativeAdAddedListener(this);
        if (getActivity() instanceof HomeActivity) {
            FragmentActivity activity7 = getActivity();
            if (activity7 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.home.HomeActivity");
            }
            if (((HomeActivity) activity7).getIsHomePressed()) {
                FragmentActivity activity8 = getActivity();
                if (activity8 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.home.HomeActivity");
                }
                ((HomeActivity) activity8).backHomeDone();
                setSelectedPosition(this.ROW_MENU, true, new ListRowPresenter.SelectItemViewHolderTask(0));
                createApplicationsRow();
            }
        }
        if (!hasMessages(this.MSG_DO_PAUSE)) {
            onDebouncedResume();
        }
        removeMessages(this.MSG_DO_PAUSE);
    }

    public final void onDebouncedResume() {
        BehaviorSubject<UserStatus> status;
        Log.d(this.TAG, "onDebouncedResume()");
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("   isDegradedMode = ");
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        sb.append(companion.getSingleton(context).getDegradedMode());
        Log.d(str, sb.toString());
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("   userStatus = ");
        PortalApplication.Companion companion2 = PortalApplication.INSTANCE;
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        UserService userService = companion2.getSingleton(context2).getBOService().getUserService();
        sb2.append((userService == null || (status = userService.getStatus()) == null) ? null : status.getValue());
        Log.d(str2, sb2.toString());
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        Context context3 = getContext();
        if (context3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
        Context applicationContext = context3.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) applicationContext;
        NetworkStatus value = portalApplication.getNetworkStateManager().getAppStatus().getValue();
        if (value != null) {
            Log.d(this.TAG, "Instant NetworkStatus = " + value);
            if (value instanceof NetworkStatus.Connected) {
                invalidateRows();
                booleanRef.element = false;
                this.isNetworkStatusConnected = true;
            } else if (this.isNetworkStatusConnected) {
                invalidateRows();
                booleanRef.element = true ^ portalApplication.getDegradedMode();
                this.isNetworkStatusConnected = false;
            }
        }
        this.networkStateDisposable = portalApplication.getNetworkStateManager().getAppStatus().distinctUntilChanged().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NetworkStatus>() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.onDebouncedResume.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(NetworkStatus networkStatus) {
                Log.d(HomeFragment.this.TAG, "NetworkStatus = " + networkStatus);
                if (!(networkStatus instanceof NetworkStatus.Connected)) {
                    if (HomeFragment.this.isNetworkStatusConnected || booleanRef.element) {
                        HomeFragment.this.invalidateRows();
                        booleanRef.element = false;
                        HomeFragment.this.isNetworkStatusConnected = false;
                        return;
                    }
                    return;
                }
                if (booleanRef.element) {
                    HomeFragment.this.invalidateRows();
                    booleanRef.element = false;
                    HomeFragment.this.isNetworkStatusConnected = true;
                }
            }
        });
        this.notifHandler.postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.home.HomeFragment.onDebouncedResume.3
            @Override // java.lang.Runnable
            public final void run() {
                NotificationView notifView;
                HomeHeroZoneView homeHeroZoneView = (HomeHeroZoneView) HomeFragment.this.getTitleView();
                if (homeHeroZoneView == null || (notifView = homeHeroZoneView.getNotifView()) == null) {
                    return;
                }
                notifView.setFocusableLocal(true);
            }
        }, 400L);
    }

    public final void onDebouncedPause() {
        NotificationView notifView;
        Log.d(this.TAG, "onDebouncedPause()");
        HomeHeroZoneView homeHeroZoneView = (HomeHeroZoneView) getTitleView();
        if (homeHeroZoneView != null && (notifView = homeHeroZoneView.getNotifView()) != null) {
            notifView.setFocusableLocal(false);
        }
        Disposable disposable = this.networkStateDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0058  */
    /* JADX WARN: Code duplicated, block: B:17:0x005c  */
    @Override // com.dotscreen.portal.ads.INativeAdListener
    public void onNativeAdReceived() {
        HomeTVRecommendationsViewModel homeTVRecommendationsViewModel;
        LiveData<List<? extends BrowsableItem>> value;
        List<? extends BrowsableItem> value2;
        FragmentActivity activity = getActivity();
        Application application = activity != null ? activity.getApplication() : null;
        if (application == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) application;
        String string = getString(R.string.DS_HOME_PAGE_IN_SWIMLANE_TV_RECOMMENDATION_POSITION_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.DS_HO…ECOMMENDATION_POSITION_1)");
        if (portalApplication.getAdFromCurrentNativeAdList(string) == null) {
            String string2 = getString(R.string.DS_HOME_PAGE_IN_SWIMLANE_TV_RECOMMENDATION_POSITION_2);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.DS_HO…ECOMMENDATION_POSITION_2)");
            if (portalApplication.getAdFromCurrentNativeAdList(string2) == null) {
                String string3 = getString(R.string.DS_HOME_PAGE_IN_SWIMLANE_TV_RECOMMENDATION_POSITION_3);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.DS_HO…ECOMMENDATION_POSITION_3)");
                if (portalApplication.getAdFromCurrentNativeAdList(string3) == null) {
                    String string4 = getString(R.string.DS_HOME_PAGE_IN_SWIMLANE_TV_RECOMMENDATION_POSITION_4);
                    Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.DS_HO…ECOMMENDATION_POSITION_4)");
                    if (portalApplication.getAdFromCurrentNativeAdList(string4) != null) {
                        homeTVRecommendationsViewModel = this.homeTVRecommendationsViewModel;
                        if (homeTVRecommendationsViewModel != null && (value = homeTVRecommendationsViewModel.getValue()) != null && (value2 = value.getValue()) != null && (!value2.isEmpty())) {
                            createTVRecommendationsRow();
                        }
                    }
                } else {
                    homeTVRecommendationsViewModel = this.homeTVRecommendationsViewModel;
                    if (homeTVRecommendationsViewModel != null) {
                        createTVRecommendationsRow();
                    }
                }
            } else {
                homeTVRecommendationsViewModel = this.homeTVRecommendationsViewModel;
                if (homeTVRecommendationsViewModel != null) {
                    createTVRecommendationsRow();
                }
            }
        } else {
            homeTVRecommendationsViewModel = this.homeTVRecommendationsViewModel;
            if (homeTVRecommendationsViewModel != null) {
                createTVRecommendationsRow();
            }
        }
        String string5 = getString(R.string.DS_HOME_PAGE_CUSTOM_IN_SETTINGS_SWIMLANE);
        Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.DS_HO…TOM_IN_SETTINGS_SWIMLANE)");
        if (portalApplication.getAdFromCurrentNativeAdList(string5) != null) {
            updateSettingsRow();
        }
        String string6 = getString(R.string.DS_HOME_PAGE_CUSTOM_IN_AD_SWIMLANE);
        Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.DS_HO…GE_CUSTOM_IN_AD_SWIMLANE)");
        if (portalApplication.getAdFromCurrentNativeAdList(string6) != null) {
            if (!this.adRowCreated) {
                createAdRow();
            } else {
                updateAdRow();
            }
        }
    }

    @Override // com.dotscreen.portal.ads.INativeAdListener
    public void clearSwimlaneNativeAds() {
        try {
            DSLog.INSTANCE.adLogM("[HIDE BANNER][NATIVE](delegated) removing TV RECOMMENDATION ads");
            createTVRecommendationsRow();
            ListRow listRow = getListRows().get(this.ROW_SETTINGS);
            ObjectAdapter objectAdapter = null;
            ObjectAdapter adapter = listRow != null ? listRow.getAdapter() : null;
            if (!(adapter instanceof ArrayObjectAdapter)) {
                adapter = null;
            }
            ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) adapter;
            if (arrayObjectAdapter != null) {
                DSLog.INSTANCE.adLogM("[HIDE BANNER][NATIVE](delegated) removing SETTINGS ROW ads");
                if (arrayObjectAdapter.get(arrayObjectAdapter.size() - 1) instanceof Ad) {
                    arrayObjectAdapter.removeItems(arrayObjectAdapter.size() - 1, 1);
                }
                arrayObjectAdapter.notifyItemRangeChanged(0, arrayObjectAdapter.size());
            }
            ListRow listRow2 = getListRows().get(this.ROW_AD);
            ObjectAdapter adapter2 = listRow2 != null ? listRow2.getAdapter() : null;
            if (adapter2 instanceof ArrayObjectAdapter) {
                objectAdapter = adapter2;
            }
            ArrayObjectAdapter arrayObjectAdapter2 = (ArrayObjectAdapter) objectAdapter;
            if (arrayObjectAdapter2 != null) {
                DSLog.INSTANCE.adLogM("[HIDE BANNER][NATIVE](delegated) removing AD ROW ads");
                arrayObjectAdapter2.clear();
                arrayObjectAdapter2.notifyItemRangeChanged(0, arrayObjectAdapter2.size());
            }
        } catch (Exception e) {
            DSLog.INSTANCE.adLogE("[HIDE BANNER][NATIVE](delegated) Failed : " + e);
        }
    }
}
