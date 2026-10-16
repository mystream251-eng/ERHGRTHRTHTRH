package com.dotscreen.portal.ui.screens.mycontents;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.PurchasableItem;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.model.MyContentsViewModel;
import com.dotscreen.portal.ui.RoundListRowPresenter;
import com.dotscreen.portal.ui.cards.HomePresenterSelector;
import com.dotscreen.portal.ui.screens.Activity_StartIntentKt;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.ISwimlanesFragment;
import com.dotscreen.portal.ui.screens.profile.PinCodeDialog;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.bokit.RightVServiceSubscriptionInfo;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyContentsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002:\u000212B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0002J\b\u0010$\u001a\u00020\"H\u0002J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020\"H\u0002J\b\u0010'\u001a\u00020\"H\u0002J\b\u0010(\u001a\u00020\"H\u0002J\u0012\u0010)\u001a\u00020\"2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\"H\u0016J\b\u0010-\u001a\u00020\"H\u0016J\b\u0010.\u001a\u00020\"H\u0016J\b\u0010/\u001a\u00020\"H\u0002J\b\u00100\u001a\u00020\"H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/dotscreen/portal/ui/screens/mycontents/MyContentsFragment;", "Landroidx/leanback/app/BrowseSupportFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "()V", "MAX_ITEMS_TO_SHOW", "", "ROW_BOOKMARKS", "ROW_CHANNELS", "ROW_PURCHASED", "ROW_RECORDINGS", "ROW_REMINDERS", "ROW_VOD", "TAG", "", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "isAdultBrowsing", "", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "rowsToLoad", "viewModel", "Lcom/dotscreen/portal/model/MyContentsViewModel;", "createBookmarksRow", "", "createComprasRow", "createFavoriteChannelsRow", "createFavoritesVODRow", "createRecordingsRow", "createRemindersRow", "createScreen", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onStart", "setupEventListeners", "setupUIElements", "ItemViewClickedListener", "ItemViewSelectedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MyContentsFragment extends BrowseSupportFragment implements ISwimlanesFragment {
    private HashMap _$_findViewCache;
    private boolean isAdultBrowsing;
    private ArrayObjectAdapter rowsAdapter;
    private int rowsToLoad;
    private MyContentsViewModel viewModel;
    private final String TAG = MyContentsFragment.class.getSimpleName();
    private final int ROW_BOOKMARKS = 1;
    private int ROW_CHANNELS = 2;
    private int ROW_RECORDINGS = 3;
    private int ROW_VOD = 4;
    private int ROW_PURCHASED = 5;
    private int ROW_REMINDERS = 6;
    private final int MAX_ITEMS_TO_SHOW = 10;
    private final SparseArray<ListRow> listRows = new SparseArray<>();
    private Handler handler = new Handler();

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

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(this.TAG, "onActivityCreated");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        this.isAdultBrowsing = ((BaseFragmentActivity) activity).isAdultBrowsing();
        setupUIElements();
        setupEventListeners();
        createScreen();
    }

    @Override // androidx.leanback.app.BrowseSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Log.d(this.TAG, "onStart");
    }

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d(this.TAG, "onResume");
        this.rowsToLoad = 6;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        ((BaseFragmentActivity) activity).setLoaderVisible(true);
        MyContentsViewModel myContentsViewModel = this.viewModel;
        if (myContentsViewModel == null) {
            Intrinsics.throwNpe();
        }
        myContentsViewModel.invalidateFavoriteChannels();
        MyContentsViewModel myContentsViewModel2 = this.viewModel;
        if (myContentsViewModel2 == null) {
            Intrinsics.throwNpe();
        }
        myContentsViewModel2.invalidateRecordings();
        MyContentsViewModel myContentsViewModel3 = this.viewModel;
        if (myContentsViewModel3 == null) {
            Intrinsics.throwNpe();
        }
        myContentsViewModel3.invalidateBookmarks();
        MyContentsViewModel myContentsViewModel4 = this.viewModel;
        if (myContentsViewModel4 == null) {
            Intrinsics.throwNpe();
        }
        myContentsViewModel4.invalidateReminders();
        MyContentsViewModel myContentsViewModel5 = this.viewModel;
        if (myContentsViewModel5 == null) {
            Intrinsics.throwNpe();
        }
        myContentsViewModel5.invalidateFavoriteVOD();
        MyContentsViewModel myContentsViewModel6 = this.viewModel;
        if (myContentsViewModel6 == null) {
            Intrinsics.throwNpe();
        }
        myContentsViewModel6.invalidateCompras();
    }

    private final void createBookmarksRow() {
        MyContentsViewModel myContentsViewModel = this.viewModel;
        if (myContentsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<Media>> bookmarks = myContentsViewModel.getBookmarks();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        bookmarks.observe(activity, new Observer<List<? extends Media>>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createBookmarksRow.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends Media> list) {
                onChanged2((List<? extends BrowsableItem>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<? extends BrowsableItem> list) {
                Log.d(MyContentsFragment.this.TAG, "new bookmarks list: " + list);
                MyContentsFragment myContentsFragment = MyContentsFragment.this;
                myContentsFragment.rowsToLoad = myContentsFragment.rowsToLoad + (-1);
                FragmentActivity activity2 = MyContentsFragment.this.getActivity();
                if (activity2 != null) {
                    ((BaseFragmentActivity) activity2).setLoaderVisible(MyContentsFragment.this.rowsToLoad > 0);
                    MyContentsFragment myContentsFragment2 = MyContentsFragment.this;
                    int i = myContentsFragment2.ROW_BOOKMARKS;
                    List listTake = list != null ? CollectionsKt.take(list, MyContentsFragment.this.MAX_ITEMS_TO_SHOW) : null;
                    BaseWidget baseWidget = new BaseWidget(MyContentsFragment.this.getString(R.string.my_content_bookmarks), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + Constants.INSTANCE.getMY_CONTENTS_BOOKMARKS_CATALOG_ID() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing, 0), false, null, 12, null);
                    Constants constants = Constants.INSTANCE;
                    Context context = MyContentsFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(myContentsFragment2, i, null, listTake, baseWidget, null, constants.createPresenter(context, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), Boolean.valueOf(MyContentsFragment.this.isAdultBrowsing)), false, null, 210, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
        });
    }

    private final void createFavoriteChannelsRow() {
        MyContentsViewModel myContentsViewModel = this.viewModel;
        if (myContentsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<Channel>> favoriteChannels = myContentsViewModel.getFavoriteChannels();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        favoriteChannels.observe(activity, new Observer<List<? extends Channel>>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createFavoriteChannelsRow.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends Channel> list) {
                Log.d(MyContentsFragment.this.TAG, "new channel list: " + list);
                MyContentsFragment myContentsFragment = MyContentsFragment.this;
                myContentsFragment.rowsToLoad = myContentsFragment.rowsToLoad + (-1);
                FragmentActivity activity2 = MyContentsFragment.this.getActivity();
                if (activity2 != null) {
                    ((BaseFragmentActivity) activity2).setLoaderVisible(MyContentsFragment.this.rowsToLoad > 0);
                    MyContentsFragment myContentsFragment2 = MyContentsFragment.this;
                    int i = myContentsFragment2.ROW_CHANNELS;
                    List listTake = list != null ? CollectionsKt.take(list, MyContentsFragment.this.MAX_ITEMS_TO_SHOW) : null;
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(myContentsFragment2, i, null, listTake, new BaseWidget(MyContentsFragment.this.getString(R.string.my_content_favorites_channels), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + Constants.INSTANCE.getMY_CONTENTS_CHANNELS_CATALOG_ID() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing, 0), false, null, 12, null), null, new HomePresenterSelector(Integer.valueOf(MyContentsFragment.this.getResources().getDimensionPixelSize(R.dimen.my_contents_more_channel_card_width)), Integer.valueOf(MyContentsFragment.this.getResources().getDimensionPixelSize(R.dimen.my_contents_more_channel_card_height)), null, null, null, null, null, 124, null), false, null, 210, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
        });
    }

    private final void createRecordingsRow() {
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        UserService userService = companion.getSingleton(activity).getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        ((MegacableUserService) userService).getServiceSubscriptionsInfo().filter(new Predicate<RightVServiceSubscriptionInfo>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createRecordingsRow.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(RightVServiceSubscriptionInfo rightVServiceSubscriptionInfo) {
                Intrinsics.checkParameterIsNotNull(rightVServiceSubscriptionInfo, "<name for destructuring parameter 0>");
                return Intrinsics.areEqual("NPVR", rightVServiceSubscriptionInfo.getModel());
            }
        }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<RightVServiceSubscriptionInfo>>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createRecordingsRow.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(List<RightVServiceSubscriptionInfo> ls) {
                Intrinsics.checkExpressionValueIsNotNull(ls, "ls");
                if (!ls.isEmpty()) {
                    MyContentsViewModel myContentsViewModel = MyContentsFragment.this.viewModel;
                    if (myContentsViewModel == null) {
                        Intrinsics.throwNpe();
                    }
                    LiveData<List<TVRecording>> recordings = myContentsViewModel.getRecordings();
                    FragmentActivity activity2 = MyContentsFragment.this.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    recordings.observe(activity2, new Observer<List<? extends TVRecording>>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createRecordingsRow.2.1
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(List<? extends TVRecording> list) {
                            Log.d(MyContentsFragment.this.TAG, "new recordings list: " + list);
                            MyContentsFragment myContentsFragment = MyContentsFragment.this;
                            myContentsFragment.rowsToLoad = myContentsFragment.rowsToLoad + (-1);
                            FragmentActivity activity3 = MyContentsFragment.this.getActivity();
                            if (activity3 != null) {
                                ((BaseFragmentActivity) activity3).setLoaderVisible(MyContentsFragment.this.rowsToLoad > 0);
                                MyContentsFragment myContentsFragment2 = MyContentsFragment.this;
                                int i = MyContentsFragment.this.ROW_RECORDINGS;
                                List listReversed = list != null ? CollectionsKt.reversed(list) : null;
                                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(myContentsFragment2, i, null, listReversed, new BaseWidget(MyContentsFragment.this.getString(R.string.my_content_recordings), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + Constants.INSTANCE.getMY_CONTENTS_RECORDING_CATALOG_ID() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing, 0), false, null, 12, null), null, new HomePresenterSelector(Integer.valueOf(MyContentsFragment.this.getResources().getDimensionPixelSize(R.dimen.my_contents_more_still_card_width)), Integer.valueOf(MyContentsFragment.this.getResources().getDimensionPixelSize(R.dimen.my_contents_more_still_card_height)), null, null, null, Boolean.valueOf(MyContentsFragment.this.isAdultBrowsing), null, 92, null), false, null, 210, null);
                                return;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                        }
                    });
                    return;
                }
                Log.d(MyContentsFragment.this.TAG, "user is not allowed to see NPVR");
                MyContentsFragment.this.rowsToLoad--;
                FragmentActivity activity3 = MyContentsFragment.this.getActivity();
                if (activity3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                }
                ((BaseFragmentActivity) activity3).setLoaderVisible(MyContentsFragment.this.rowsToLoad > 0);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createRecordingsRow.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.d(MyContentsFragment.this.TAG, "user is not allowed to see NPVR");
                MyContentsFragment.this.rowsToLoad--;
                FragmentActivity activity2 = MyContentsFragment.this.getActivity();
                if (activity2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                }
                ((BaseFragmentActivity) activity2).setLoaderVisible(MyContentsFragment.this.rowsToLoad > 0);
            }
        });
    }

    private final void createFavoritesVODRow() {
        MyContentsViewModel myContentsViewModel = this.viewModel;
        if (myContentsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<BrowsableItem>> favoriteVOD = myContentsViewModel.getFavoriteVOD();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        favoriteVOD.observe(activity, new Observer<List<? extends BrowsableItem>>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createFavoritesVODRow.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends BrowsableItem> list) {
                Log.d(MyContentsFragment.this.TAG, "new VOD favorite list: " + list);
                MyContentsFragment myContentsFragment = MyContentsFragment.this;
                myContentsFragment.rowsToLoad = myContentsFragment.rowsToLoad + (-1);
                FragmentActivity activity2 = MyContentsFragment.this.getActivity();
                if (activity2 != null) {
                    ((BaseFragmentActivity) activity2).setLoaderVisible(MyContentsFragment.this.rowsToLoad > 0);
                    MyContentsFragment myContentsFragment2 = MyContentsFragment.this;
                    int i = myContentsFragment2.ROW_VOD;
                    List listTake = list != null ? CollectionsKt.take(list, MyContentsFragment.this.MAX_ITEMS_TO_SHOW) : null;
                    BaseWidget baseWidget = new BaseWidget(MyContentsFragment.this.getString(R.string.my_content_favorites_movies), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + Constants.INSTANCE.getMY_CONTENTS_VOD_CATALOG_ID() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing, 0), false, null, 12, null);
                    Constants constants = Constants.INSTANCE;
                    Context context = MyContentsFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(myContentsFragment2, i, null, listTake, baseWidget, null, constants.createPresenter(context, Constants.INSTANCE.getMY_CONTENTS_VOD_CATALOG_ID(), Boolean.valueOf(MyContentsFragment.this.isAdultBrowsing)), false, null, 210, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
        });
    }

    private final void createComprasRow() {
        MyContentsViewModel myContentsViewModel = this.viewModel;
        if (myContentsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<PurchasableItem>> compras = myContentsViewModel.getCompras();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        compras.observe(activity, new Observer<List<? extends PurchasableItem>>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createComprasRow.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends PurchasableItem> list) {
                onChanged2((List<? extends BrowsableItem>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<? extends BrowsableItem> list) {
                Log.d(MyContentsFragment.this.TAG, "new compras list: " + list);
                MyContentsFragment myContentsFragment = MyContentsFragment.this;
                myContentsFragment.rowsToLoad = myContentsFragment.rowsToLoad + (-1);
                FragmentActivity activity2 = MyContentsFragment.this.getActivity();
                if (activity2 != null) {
                    ((BaseFragmentActivity) activity2).setLoaderVisible(MyContentsFragment.this.rowsToLoad > 0);
                    MyContentsFragment myContentsFragment2 = MyContentsFragment.this;
                    int i = myContentsFragment2.ROW_PURCHASED;
                    List listTake = list != null ? CollectionsKt.take(list, MyContentsFragment.this.MAX_ITEMS_TO_SHOW) : null;
                    BaseWidget baseWidget = new BaseWidget(MyContentsFragment.this.getString(R.string.my_content_shopping), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + Constants.INSTANCE.getMY_CONTENTS_PURCHASED_CATALOG_ID() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing, 0), false, null, 12, null);
                    Constants constants = Constants.INSTANCE;
                    Context context = MyContentsFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(myContentsFragment2, i, null, listTake, baseWidget, null, constants.createPresenter(context, Constants.INSTANCE.getMY_CONTENTS_PURCHASED_CATALOG_ID(), Boolean.valueOf(MyContentsFragment.this.isAdultBrowsing)), false, null, 210, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
        });
    }

    private final void createRemindersRow() {
        MyContentsViewModel myContentsViewModel = this.viewModel;
        if (myContentsViewModel == null) {
            Intrinsics.throwNpe();
        }
        LiveData<List<TVAiring>> reminders = myContentsViewModel.getReminders();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        reminders.observe(activity, new Observer<List<? extends TVAiring>>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createRemindersRow.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends TVAiring> list) {
                onChanged2((List<? extends BrowsableItem>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<? extends BrowsableItem> list) {
                Log.d(MyContentsFragment.this.TAG, "new reminder list: " + list);
                MyContentsFragment myContentsFragment = MyContentsFragment.this;
                myContentsFragment.rowsToLoad = myContentsFragment.rowsToLoad + (-1);
                FragmentActivity activity2 = MyContentsFragment.this.getActivity();
                if (activity2 != null) {
                    ((BaseFragmentActivity) activity2).setLoaderVisible(MyContentsFragment.this.rowsToLoad > 0);
                    MyContentsFragment myContentsFragment2 = MyContentsFragment.this;
                    int i = myContentsFragment2.ROW_REMINDERS;
                    List listTake = list != null ? CollectionsKt.take(list, MyContentsFragment.this.MAX_ITEMS_TO_SHOW) : null;
                    BaseWidget baseWidget = new BaseWidget(MyContentsFragment.this.getString(R.string.my_content_reminders), Intent.parseUri("megacable://com.dotscreen.megacable.portal/collection?catalog_id=" + Constants.INSTANCE.getMY_CONTENTS_REMINDERS_CATALOG_ID() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing, 0), false, null, 12, null);
                    Constants constants = Constants.INSTANCE;
                    Context context = MyContentsFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(myContentsFragment2, i, null, listTake, baseWidget, null, constants.createPresenter(context, Constants.INSTANCE.getMY_CONTENTS_REMINDERS_CATALOG_ID(), Boolean.valueOf(MyContentsFragment.this.isAdultBrowsing)), false, null, 210, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
        });
    }

    private final void createScreen() {
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
        view.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment.createScreen.1
            @Override // java.lang.Runnable
            public void run() {
                RowsSupportFragment rowsSupportFragment = MyContentsFragment.this.getRowsSupportFragment();
                if (rowsSupportFragment != null && rowsSupportFragment.getVerticalGridView() != null) {
                    rowsSupportFragment.getVerticalGridView().requestFocus();
                    return;
                }
                View view2 = MyContentsFragment.this.getView();
                if (view2 != null) {
                    view2.postDelayed(this, 10L);
                }
            }
        });
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        this.viewModel = (MyContentsViewModel) ViewModelProviders.of(activity, new MyContentsViewModel.Factory(context2, ((BaseFragmentActivity) activity2).isAdultBrowsing())).get(MyContentsViewModel.class);
        createFavoriteChannelsRow();
        createRecordingsRow();
        createBookmarksRow();
        createRemindersRow();
        createFavoritesVODRow();
        createComprasRow();
    }

    private final void setupUIElements() {
        setHeadersState(3);
        setHeadersTransitionOnBackEnabled(false);
    }

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    /* JADX INFO: compiled from: MyContentsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/mycontents/MyContentsFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/mycontents/MyContentsFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "registerClick", "Lcom/dotscreen/bokit/model/BrowsableItem;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, final Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof BrowsableItem) {
                DSLog dSLog = DSLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("Clicked on the ");
                BrowsableItem browsableItem = (BrowsableItem) item;
                sb.append(browsableItem.getTitle());
                sb.append(" BrowsableItem");
                dSLog.a(sb.toString());
                registerClick(browsableItem);
            }
            if (item instanceof TVRecording) {
                DSLog dSLog2 = DSLog.INSTANCE;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Clicked on the ");
                TVRecording tVRecording = (TVRecording) item;
                sb2.append(tVRecording.getTitle());
                sb2.append(" TVRecording");
                dSLog2.a(sb2.toString());
                MyContentsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?recording_id=" + tVRecording.getId() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing)));
                return;
            }
            if (item instanceof TVAiring) {
                DSLog dSLog3 = DSLog.INSTANCE;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Clicked on the ");
                TVAiring tVAiring = (TVAiring) item;
                sb3.append(tVAiring.getTitle());
                sb3.append(" TVAiring");
                dSLog3.a(sb3.toString());
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + tVAiring.getChannel().getId() + "&start=" + tVAiring.getStartDate().getTime() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing));
                FragmentActivity activity = MyContentsFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                activity.startActivity(intent);
                return;
            }
            if (item instanceof Channel) {
                DSLog dSLog4 = DSLog.INSTANCE;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Clicked on the ");
                Channel channel = (Channel) item;
                sb4.append(channel.getTitle());
                sb4.append(" Channel");
                dSLog4.a(sb4.toString());
                MyContentsFragment.this.startActivity(new Intent("android.intent.action.VIEW", CommonUtils.getLiveTvDeeplink(MyContentsFragment.this.getContext(), channel, (Boolean) true)));
                return;
            }
            if (item instanceof Content) {
                DSLog dSLog5 = DSLog.INSTANCE;
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Clicked on the ");
                Content content = (Content) item;
                sb5.append(content.getTitle());
                sb5.append(" Content");
                dSLog5.a(sb5.toString());
                MyContentsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + content.getId() + "&adult_browsing=" + MyContentsFragment.this.isAdultBrowsing)));
                return;
            }
            if (item instanceof BaseWidget) {
                DSLog dSLog6 = DSLog.INSTANCE;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("Clicked on the ");
                BaseWidget baseWidget = (BaseWidget) item;
                sb6.append(baseWidget.getTitle());
                sb6.append(" BaseWidget");
                dSLog6.a(sb6.toString());
                if (baseWidget.getIntent() != null) {
                    if (!baseWidget.getPasswordProtected()) {
                        FragmentActivity activity2 = MyContentsFragment.this.getActivity();
                        if (activity2 != null) {
                            Activity_StartIntentKt.startIntent(activity2, baseWidget.getIntent());
                            return;
                        }
                        return;
                    }
                    Context context = MyContentsFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                    PinCodeDialog.Builder checkPinCode = new PinCodeDialog.Builder(context).setCheckPinCode(true);
                    Context context2 = MyContentsFragment.this.getContext();
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
                    checkPinCode.setUser(user).setPincodeType(PincodeType.Parental).setTitle(R.string.input_parental_pin_code).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment$ItemViewClickedListener$onItemClicked$1
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
                            FragmentActivity activity3 = MyContentsFragment.this.getActivity();
                            if (activity3 != null) {
                                Activity_StartIntentKt.startIntent(activity3, ((BaseWidget) item).getIntent());
                            }
                        }
                    }).create().show();
                }
            }
        }

        private final void registerClick(BrowsableItem item) {
            Context context = MyContentsFragment.this.getContext();
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
            MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, item, null, null, null, 14, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment$ItemViewClickedListener$registerClick$1
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.mycontents.MyContentsFragment$ItemViewClickedListener$registerClick$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            });
        }
    }

    /* JADX INFO: compiled from: MyContentsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/mycontents/MyContentsFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/mycontents/MyContentsFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        }

        public ItemViewSelectedListener() {
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.handler.removeCallbacksAndMessages(null);
    }
}
