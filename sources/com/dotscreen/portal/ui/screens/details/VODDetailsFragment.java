package com.dotscreen.portal.ui.screens.details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.RowsSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DividerPresenter;
import androidx.leanback.widget.DividerRow;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowHeaderPresenter;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SectionRow;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.android.volley.VolleyError;
import com.dotscreen.bokit.BackendErrorException;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.model.OfferType;
import com.dotscreen.bokit.model.PlaybackRight;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVSeason;
import com.dotscreen.bokit.model.TVSeries;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.model.HomeWidget;
import com.dotscreen.portal.model.LoadingWidget;
import com.dotscreen.portal.model.VODDetailsViewModel;
import com.dotscreen.portal.ui.RecommendationListRow;
import com.dotscreen.portal.ui.VODPersoListRow;
import com.dotscreen.portal.ui.VODRelatedContentListRow;
import com.dotscreen.portal.ui.ViewRow;
import com.dotscreen.portal.ui.cards.DetailsActionCardPresenter;
import com.dotscreen.portal.ui.cards.EpisodePresenterSelector;
import com.dotscreen.portal.ui.screens.BaseDetailsFragment;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.ISwimlanesFragment;
import com.dotscreen.portal.ui.screens.player.PlaybackActivityKt;
import com.dotscreen.portal.ui.screens.profile.PinCodeDialog;
import com.dotscreen.portal.utils.PortalUtils;
import com.dotscreen.tv.common.bokit.MegacableUtilsKt;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.bokit.RecoContent;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.bokit.RightvVideoOfferData;
import com.dotscreen.tv.ui.dialog.CheckListDialog;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: VODDetailsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002:\u0005\\]^_`B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\fH\u0002J\u0019\u0010E\u001a\u00020C2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010GJ \u0010H\u001a\u00020C2\u0006\u0010D\u001a\u00020\f2\u0006\u0010I\u001a\u00020\u00052\u0006\u0010J\u001a\u00020\u0005H\u0002J\b\u0010K\u001a\u00020CH\u0002J\b\u0010L\u001a\u00020CH\u0002J\u000e\u0010M\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u0005J\b\u0010O\u001a\u00020!H\u0014J\b\u0010P\u001a\u00020!H\u0014J\u0012\u0010Q\u001a\u00020C2\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\b\u0010T\u001a\u00020CH\u0016J\b\u0010U\u001a\u00020CH\u0016J\u0010\u0010V\u001a\u00020C2\u0006\u0010W\u001a\u00020XH\u0002J\b\u0010Y\u001a\u00020CH\u0002J\b\u0010Z\u001a\u00020CH\u0002J\u000e\u0010[\u001a\u00020C2\u0006\u0010W\u001a\u00020XR\u001c\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0012\u0010\u000e\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\b\u0012\u00060\u0005j\u0002`\u0013\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0018\u00010\u0017R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0012\u0010\u001f\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010*0)X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0010\u0010-\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010.\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u00103\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\b\"\u0004\b5\u00106R\"\u00107\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\b\"\u0004\b9\u00106R\u001a\u0010:\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001d\u0010?\u001a\u000e\u0012\b\u0012\u00060\u0005j\u0002`\u0013\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b@\u0010\u0015R\u0010\u0010A\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment;", "Lcom/dotscreen/portal/ui/screens/BaseDetailsFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "actionBroadcastReceiver", "Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment$ActionBroadcastReceiver;", "actionFavoriteIndex", "", "Ljava/lang/Integer;", "actionOfferIndex", "adapterButton", "Landroidx/leanback/widget/ArrayObjectAdapter;", "audioLanguages", "", "Lcom/dotscreen/bokit/model/LanguageCode;", "getAudioLanguages", "()[Ljava/lang/String;", "detailSelector", "Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment$MySelector;", "value", "Lcom/dotscreen/portal/model/VODDetailsViewModel$Result;", "detailsResult", "getDetailsResult", "()Lcom/dotscreen/portal/model/VODDetailsViewModel$Result;", "setDetailsResult", "(Lcom/dotscreen/portal/model/VODDetailsViewModel$Result;)V", "fragmentAlignment", "fromAdultBrowsing", "", "getFromAdultBrowsing", "()Z", "setFromAdultBrowsing", "(Z)V", "handler", "Landroid/os/Handler;", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "primeAiringsListRow", "rowsAdapter", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "selectedAudioLanguage", "getSelectedAudioLanguage", "setSelectedAudioLanguage", "(Ljava/lang/String;)V", "selectedSubtitlesLanguage", "getSelectedSubtitlesLanguage", "setSelectedSubtitlesLanguage", "startY", "getStartY", "()I", "setStartY", "(I)V", "subtitlesLanguages", "getSubtitlesLanguages", "topMoviesListRow", "createRecommendation", "", "position", "createRelatedAndRecommendationRows", "startIndex", "(Ljava/lang/Integer;)V", "createRelatedContent", "contentType", "title", "createRows", "createSeasonsRow", "decodeToken", "contentId", "hasMultipleAudios", "hasMultipleSubtitles", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onStop", "requestPurchase", "item", "Lcom/dotscreen/bokit/model/Offer;", "setupEventListeners", "setupUIElements", "showPinCode", "ActionBroadcastReceiver", "ActionListRow", "ItemViewClickedListener", "ItemViewSelectedListener", "MySelector", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODDetailsFragment extends BaseDetailsFragment implements ISwimlanesFragment {
    private HashMap _$_findViewCache;
    private ActionBroadcastReceiver actionBroadcastReceiver;
    private Integer actionFavoriteIndex;
    private Integer actionOfferIndex;
    private MySelector detailSelector;
    private VODDetailsViewModel.Result detailsResult;
    private Integer fragmentAlignment;
    private boolean fromAdultBrowsing;
    private ListRow primeAiringsListRow;
    private ArrayObjectAdapter rowsAdapter;
    private String selectedAudioLanguage;
    private String selectedSubtitlesLanguage;
    private int startY;
    private ListRow topMoviesListRow;
    private final String TAG = VODDetailsFragment.class.getSimpleName();
    private final SparseArray<ListRow> listRows = new SparseArray<>();
    private Handler handler = new Handler();
    private ArrayObjectAdapter adapterButton = new ArrayObjectAdapter(new DetailsActionCardPresenter());

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OfferType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[OfferType.Transactional.ordinal()] = 1;
            $EnumSwitchMapping$0[OfferType.Subscription.ordinal()] = 2;
            $EnumSwitchMapping$0[OfferType.ExternalOffer.ordinal()] = 3;
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseDetailsFragment
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseDetailsFragment
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

    @Override // com.dotscreen.portal.ui.screens.BaseDetailsFragment, androidx.leanback.app.BrowseSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$createSeasonsRow$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VODDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "p1", "", "invoke"}, k = 3, mv = {1, 1, 16})
    static final /* synthetic */ class C03763 extends FunctionReference implements Function1<Throwable, Unit> {
        public static final C03763 INSTANCE = new C03763();

        C03763() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "printStackTrace";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(Throwable.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "printStackTrace()V";
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable p1) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            p1.printStackTrace();
        }
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

    @Override // com.dotscreen.portal.ui.screens.BaseDetailsFragment
    public String getTAG() {
        return this.TAG;
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

    public final boolean getFromAdultBrowsing() {
        return this.fromAdultBrowsing;
    }

    public final void setFromAdultBrowsing(boolean z) {
        this.fromAdultBrowsing = z;
    }

    public final VODDetailsViewModel.Result getDetailsResult() {
        return this.detailsResult;
    }

    public final void setDetailsResult(VODDetailsViewModel.Result result) {
        this.detailsResult = result;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$detailsResult$1
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    this.this$0.createRows();
                    VODDetailsViewModel.Result detailsResult = this.this$0.getDetailsResult();
                    if ((detailsResult != null ? detailsResult.getMedia() : null) instanceof Episode) {
                        this.this$0.createSeasonsRow();
                    } else {
                        VODDetailsFragment.createRelatedAndRecommendationRows$default(this.this$0, null, 1, null);
                    }
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUIElements();
        setupEventListeners();
        if (this.detailsResult != null) {
            createRows();
        }
    }

    @Override // androidx.leanback.app.BrowseSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Integer num;
        super.onStart();
        if (this.actionBroadcastReceiver == null) {
            this.actionBroadcastReceiver = new ActionBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(DetailsActionsKt.ACTION_SYNOPSIS);
            intentFilter.addAction(DetailsActionsKt.ACTION_AUDIO);
            intentFilter.addAction(DetailsActionsKt.ACTION_SUBTITLES);
            intentFilter.addAction(DetailsActionsKt.ACTION_PLAYBACK_VOD);
            intentFilter.addAction(DetailsActionsKt.ACTION_PLAYBACK_TRAILER);
            intentFilter.addAction(DetailsActionsKt.ACTION_FAVORITES_ADD);
            intentFilter.addAction(DetailsActionsKt.ACTION_FAVORITES_REMOVE);
            intentFilter.addAction(DetailsActionsKt.ACTION_ORDER_OFFER);
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
            ActionBroadcastReceiver actionBroadcastReceiver = this.actionBroadcastReceiver;
            if (actionBroadcastReceiver == null) {
                Intrinsics.throwNpe();
            }
            localBroadcastManager.registerReceiver(actionBroadcastReceiver, intentFilter);
        }
        RowsSupportFragment rowsSupportFragment = getRowsSupportFragment();
        if (rowsSupportFragment == null || (num = this.fragmentAlignment) == null) {
            return;
        }
        rowsSupportFragment.setAlignment(num.intValue());
    }

    @Override // androidx.leanback.app.BrowseSupportFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        ActionBroadcastReceiver actionBroadcastReceiver = this.actionBroadcastReceiver;
        if (actionBroadcastReceiver != null) {
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            LocalBroadcastManager.getInstance(context).unregisterReceiver(actionBroadcastReceiver);
        }
        this.actionBroadcastReceiver = (ActionBroadcastReceiver) null;
    }

    private final void setupUIElements() {
        setHeadersState(3);
        setHeadersTransitionOnBackEnabled(false);
        setHeaderPresenterSelector(new ClassPresenterSelector().addClassPresenter(DividerRow.class, new DividerPresenter()).addClassPresenter(SectionRow.class, new RowHeaderPresenter(R.layout.lb_section_header, true)).addClassPresenter(Row.class, new RowHeaderPresenter(R.layout.lb_header)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createRows() {
        if (getRowsAdapter() != null) {
            return;
        }
        this.detailSelector = new MySelector();
        setRowsAdapter(new ArrayObjectAdapter(this.detailSelector));
        ArrayObjectAdapter rowsAdapter = getRowsAdapter();
        if (rowsAdapter != null) {
            rowsAdapter.add(new ViewRow());
        }
        this.actionOfferIndex = Integer.valueOf(this.adapterButton.size());
        VODDetailsViewModel.Result result = this.detailsResult;
        if (result == null) {
            Intrinsics.throwNpe();
        }
        if (result.getRight() != null) {
            ArrayObjectAdapter arrayObjectAdapter = this.adapterButton;
            String string = getString(R.string.details_action_play);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_play)");
            arrayObjectAdapter.add(new HomeWidget(string, R.drawable.ic_picto_pg_play, new Intent(DetailsActionsKt.ACTION_PLAYBACK_VOD), 0, 8, null));
        } else {
            VODDetailsViewModel.Result result2 = this.detailsResult;
            if (result2 == null) {
                Intrinsics.throwNpe();
            }
            if (!result2.getOffers().isEmpty()) {
                VODDetailsViewModel.Result result3 = this.detailsResult;
                if (result3 == null) {
                    Intrinsics.throwNpe();
                }
                Offer offer = result3.getOffers().get(0);
                int i = WhenMappings.$EnumSwitchMapping$0[offer.getType().ordinal()];
                if (i != 1) {
                    if (i == 2) {
                        RightvVideoOfferData rightvVideoOfferDataExtractVideoOfferData = MegacableUtilsKt.extractVideoOfferData(offer);
                        StringBuilder sb = new StringBuilder();
                        sb.append("megacable://com.dotscreen.megacable.portal/rightvpackages?service_id=");
                        sb.append(rightvVideoOfferDataExtractVideoOfferData != null ? rightvVideoOfferDataExtractVideoOfferData.getServiceExternalId() : null);
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
                        ArrayObjectAdapter arrayObjectAdapter2 = this.adapterButton;
                        String string2 = getString(R.string.details_action_subscribe);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.details_action_subscribe)");
                        arrayObjectAdapter2.add(new HomeWidget(string2, R.drawable.ic_picto_rent, intent, 0, 8, null));
                    } else if (i == 3) {
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(offer.getDeepLink()));
                        ArrayObjectAdapter arrayObjectAdapter3 = this.adapterButton;
                        String string3 = getString(R.string.details_action_play);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.details_action_play)");
                        arrayObjectAdapter3.add(new HomeWidget(string3, R.drawable.ic_picto_pg_play, intent2, 0, 8, null));
                        VODDetailsViewModel.Result result4 = this.detailsResult;
                        if (result4 == null) {
                            Intrinsics.throwNpe();
                        }
                        TVSeries series = result4.getSeries();
                        String strExternalId = series != null ? series.externalId(ItemExternalIdScheme.AmazonPrime) : null;
                        if (strExternalId != null) {
                            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(strExternalId));
                            ArrayObjectAdapter arrayObjectAdapter4 = this.adapterButton;
                            String string4 = getString(R.string.details_action_go_serie);
                            Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.details_action_go_serie)");
                            arrayObjectAdapter4.add(new HomeWidget(string4, R.drawable.ic_picto_pg_go_serie, intent3, 0, 8, null));
                        }
                    }
                } else if (offer.getPrice() == 0.0d) {
                    ArrayObjectAdapter arrayObjectAdapter5 = this.adapterButton;
                    String string5 = getString(R.string.details_action_rent_free);
                    Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.details_action_rent_free)");
                    arrayObjectAdapter5.add(new HomeWidget(string5, R.drawable.ic_picto_pg_play, new Intent(DetailsActionsKt.ACTION_PLAYBACK_VOD), 0, 8, null));
                } else {
                    ArrayObjectAdapter arrayObjectAdapter6 = this.adapterButton;
                    Object[] objArr = new Object[1];
                    VODDetailsViewModel.Result result5 = this.detailsResult;
                    if (result5 == null) {
                        Intrinsics.throwNpe();
                    }
                    objArr[0] = Float.valueOf((float) result5.getOffers().get(0).getPrice());
                    String string6 = getString(R.string.details_action_rent, objArr);
                    Intrinsics.checkExpressionValueIsNotNull(string6, "getString(\n             …                        )");
                    arrayObjectAdapter6.add(new HomeWidget(string6, R.drawable.ic_picto_rent, new Intent(DetailsActionsKt.ACTION_ORDER_OFFER), 0, 8, null));
                }
            }
        }
        VODDetailsViewModel.Result result6 = this.detailsResult;
        if (result6 == null) {
            Intrinsics.throwNpe();
        }
        if (!result6.getTrailers().isEmpty()) {
            ArrayObjectAdapter arrayObjectAdapter7 = this.adapterButton;
            String string7 = getString(R.string.details_action_trailer);
            Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.details_action_trailer)");
            arrayObjectAdapter7.add(new HomeWidget(string7, R.drawable.ic_picto_pg_trailer, new Intent(DetailsActionsKt.ACTION_PLAYBACK_TRAILER), 0, 8, null));
        }
        ArrayObjectAdapter arrayObjectAdapter8 = this.adapterButton;
        String string8 = getString(R.string.details_action_synopsis);
        Intrinsics.checkExpressionValueIsNotNull(string8, "getString(R.string.details_action_synopsis)");
        arrayObjectAdapter8.add(new HomeWidget(string8, R.drawable.ic_synopsis, new Intent(DetailsActionsKt.ACTION_SYNOPSIS), 0, 8, null));
        this.actionFavoriteIndex = Integer.valueOf(this.adapterButton.size());
        VODDetailsViewModel.Result result7 = this.detailsResult;
        if (result7 == null) {
            Intrinsics.throwNpe();
        }
        if (result7.getVODIsInFavorites()) {
            ArrayObjectAdapter arrayObjectAdapter9 = this.adapterButton;
            String string9 = getString(R.string.details_action_remove_VOD_to_fav);
            Intrinsics.checkExpressionValueIsNotNull(string9, "getString(R.string.detai…action_remove_VOD_to_fav)");
            arrayObjectAdapter9.add(new HomeWidget(string9, R.drawable.ic_remove_from_favorites, new Intent(DetailsActionsKt.ACTION_FAVORITES_REMOVE), R.color.red));
        } else {
            ArrayObjectAdapter arrayObjectAdapter10 = this.adapterButton;
            String string10 = getString(R.string.details_action_add_VOD_to_fav);
            Intrinsics.checkExpressionValueIsNotNull(string10, "getString(R.string.details_action_add_VOD_to_fav)");
            arrayObjectAdapter10.add(new HomeWidget(string10, R.drawable.ic_add_to_favorite, new Intent(DetailsActionsKt.ACTION_FAVORITES_ADD), R.color.white));
        }
        ArrayObjectAdapter rowsAdapter2 = getRowsAdapter();
        if (rowsAdapter2 != null) {
            rowsAdapter2.add(new ActionListRow(this, null, this.adapterButton));
        }
        setAdapter(getRowsAdapter());
        setSelectedPosition(1);
    }

    public final String getSelectedAudioLanguage() {
        return this.selectedAudioLanguage;
    }

    public final void setSelectedAudioLanguage(String str) {
        this.selectedAudioLanguage = str;
    }

    public final String[] getAudioLanguages() {
        VODDetailsViewModel.Result result = this.detailsResult;
        Media media = result != null ? result.getMedia() : null;
        if (media instanceof Movie) {
            return ((Movie) media).getMetadata().getAudioLanguages();
        }
        if (media instanceof Episode) {
            return ((Episode) media).getMetadata().getAudioLanguages();
        }
        return null;
    }

    public final String getSelectedSubtitlesLanguage() {
        return this.selectedSubtitlesLanguage;
    }

    public final void setSelectedSubtitlesLanguage(String str) {
        this.selectedSubtitlesLanguage = str;
    }

    public final String[] getSubtitlesLanguages() {
        VODDetailsViewModel.Result result = this.detailsResult;
        Media media = result != null ? result.getMedia() : null;
        if (media instanceof Movie) {
            return ((Movie) media).getMetadata().getSubtitlesLanguages();
        }
        if (media instanceof Episode) {
            return ((Episode) media).getMetadata().getSubtitlesLanguages();
        }
        return null;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseDetailsFragment
    protected boolean hasMultipleAudios() {
        String[] audioLanguages = getAudioLanguages();
        return (audioLanguages != null ? audioLanguages.length : 0) > 1;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseDetailsFragment
    protected boolean hasMultipleSubtitles() {
        String[] subtitlesLanguages = getSubtitlesLanguages();
        return (subtitlesLanguages != null ? subtitlesLanguages.length : 0) > 0;
    }

    public final int getStartY() {
        return this.startY;
    }

    public final void setStartY(int i) {
        this.startY = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createSeasonsRow() throws JSONException {
        Observable<R> observableFlatMapSingle;
        Observable observableSubscribeOn;
        Observable observableObserveOn;
        Object obj;
        VODDetailsViewModel.Result result = this.detailsResult;
        if (result == null) {
            Intrinsics.throwNpe();
        }
        if (result.getSeries() != null) {
            final Ref.IntRef intRef = new Ref.IntRef();
            ArrayObjectAdapter rowsAdapter = getRowsAdapter();
            intRef.element = Math.min(rowsAdapter != null ? rowsAdapter.size() : -1, 0);
            VODDetailsViewModel.Result result2 = this.detailsResult;
            if (result2 == null) {
                Intrinsics.throwNpe();
            }
            TVSeries series = result2.getSeries();
            if (series == null) {
                Intrinsics.throwNpe();
            }
            Observable<TVSeason> seasons = series.getSeasons();
            if (seasons == null || (observableFlatMapSingle = seasons.flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createSeasonsRow.1
                @Override // io.reactivex.functions.Function
                public final Single<Pair<TVSeason, Integer>> apply(final TVSeason season) {
                    Single<R> map;
                    Intrinsics.checkParameterIsNotNull(season, "season");
                    Single<Integer> episodesCount = season.getEpisodesCount();
                    return (episodesCount == null || (map = episodesCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createSeasonsRow.1.1
                        @Override // io.reactivex.functions.Function
                        public final Pair<TVSeason, Integer> apply(Integer it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new Pair<>(season, it);
                        }
                    })) == null) ? Single.just(new Pair(season, 0)) : map;
                }
            })) == 0 || (observableSubscribeOn = observableFlatMapSingle.subscribeOn(AndroidSchedulers.mainThread())) == null || (observableObserveOn = observableSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) {
                return;
            }
            Consumer<Pair<? extends TVSeason, ? extends Integer>> consumer = new Consumer<Pair<? extends TVSeason, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createSeasonsRow.2
                @Override // io.reactivex.functions.Consumer
                public /* bridge */ /* synthetic */ void accept(Pair<? extends TVSeason, ? extends Integer> pair) {
                    accept2((Pair<TVSeason, Integer>) pair);
                }

                /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
                public final void accept2(final Pair<TVSeason, Integer> pair) {
                    final int i = intRef.element;
                    VODDetailsFragment vODDetailsFragment = VODDetailsFragment.this;
                    String string = vODDetailsFragment.getString(R.string.details_subtitle_season, Integer.valueOf(pair.getFirst().getSeasonNumber()));
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(\n             …                        )");
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
                    if (string != null) {
                        String upperCase = string.toUpperCase(locale);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
                        HeaderItem headerItem = new HeaderItem(0L, upperCase);
                        Integer second = pair.getSecond();
                        Intrinsics.checkExpressionValueIsNotNull(second, "pair.second");
                        int iIntValue = second.intValue();
                        ArrayList arrayList = new ArrayList(iIntValue);
                        for (int i2 = 0; i2 < iIntValue; i2++) {
                            arrayList.add(new LoadingWidget());
                        }
                        ArrayList arrayList2 = arrayList;
                        Context context = VODDetailsFragment.this.getContext();
                        if (context == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.vod_content_card_width);
                        Context context2 = VODDetailsFragment.this.getContext();
                        if (context2 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(vODDetailsFragment, i, headerItem, arrayList2, null, null, new EpisodePresenterSelector(dimensionPixelSize, context2.getResources().getDimensionPixelSize(R.dimen.vod_content_card_height)), true, null, 152, null);
                        intRef.element++;
                        pair.getFirst().getEpisodes().toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Episode>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createSeasonsRow.2.2
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(List<Episode> list) {
                                Log.d(VODDetailsFragment.this.getTAG(), "col = " + ((TVSeason) pair.getFirst()) + ", items = " + list + ", currentIndex = " + i);
                                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(VODDetailsFragment.this, i, null, list, null, null, null, true, null, 186, null);
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createSeasonsRow.2.3
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                                Log.w(VODDetailsFragment.this.getTAG(), "Error loading content", th);
                            }
                        });
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            };
            final C03763 c03763 = C03763.INSTANCE;
            if (c03763 != null) {
                obj = c03763;
                obj = new Consumer() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$sam$io_reactivex_functions_Consumer$0
                    @Override // io.reactivex.functions.Consumer
                    public final /* synthetic */ void accept(Object obj2) {
                        Intrinsics.checkExpressionValueIsNotNull(c03763.invoke(obj2), "invoke(...)");
                    }
                };
            }
            obj = c03763;
            Disposable disposableSubscribe = observableObserveOn.subscribe(consumer, (Consumer) obj, new Action() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createSeasonsRow.4
                @Override // io.reactivex.functions.Action
                public final void run() throws JSONException {
                    VODDetailsFragment.this.createRelatedAndRecommendationRows(Integer.valueOf(intRef.element));
                }
            });
            if (disposableSubscribe != null) {
                FragmentActivity activity = getActivity();
                if (activity == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                }
                LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
                return;
            }
            return;
        }
        createRelatedAndRecommendationRows$default(this, null, 1, null);
    }

    static /* synthetic */ void createRelatedAndRecommendationRows$default(VODDetailsFragment vODDetailsFragment, Integer num, int i, Object obj) throws JSONException {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        vODDetailsFragment.createRelatedAndRecommendationRows(num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createRelatedAndRecommendationRows(Integer startIndex) throws JSONException {
        Integer numValueOf;
        VODDetailsViewModel.Result result = this.detailsResult;
        if (result == null) {
            Intrinsics.throwNpe();
        }
        Object obj = result.getMedia() instanceof Movie ? "movie" : "season";
        Integer numValueOf2 = (Integer) null;
        if (Intrinsics.areEqual(obj, "movie")) {
            numValueOf2 = Integer.valueOf(startIndex != null ? startIndex.intValue() : 1);
            numValueOf = startIndex != null ? Integer.valueOf(startIndex.intValue() + 1) : 2;
        } else if (Intrinsics.areEqual(obj, "season")) {
            Integer numValueOf3 = Integer.valueOf(startIndex != null ? startIndex.intValue() : 1);
            numValueOf2 = startIndex != null ? Integer.valueOf(startIndex.intValue() + 1) : 2;
            numValueOf = numValueOf3;
        } else {
            numValueOf = numValueOf2;
        }
        int iIntValue = startIndex != null ? startIndex.intValue() + 2 : 3;
        if (numValueOf2 != null) {
            numValueOf2.intValue();
            int iIntValue2 = numValueOf2.intValue();
            String string = getString(R.string.details_movies_related_reco_swimlanes);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.detai…s_related_reco_swimlanes)");
            createRelatedContent(iIntValue2, "movie", string);
        }
        if (numValueOf != null) {
            numValueOf.intValue();
            int iIntValue3 = numValueOf.intValue();
            String string2 = getString(R.string.details_series_related_reco_swimlanes);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.detai…s_related_reco_swimlanes)");
            createRelatedContent(iIntValue3, "season", string2);
        }
        createRecommendation(iIntValue);
    }

    private final void createRelatedContent(final int position, String contentType, final String title) throws JSONException {
        VODDetailsViewModel.Result result = this.detailsResult;
        if (result == null) {
            Intrinsics.throwNpe();
        }
        String strDecodeToken = decodeToken(result.getMedia().getId());
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        VODCatalogService vodCatalogService = companion.getSingleton(context).getBOService().getVodCatalogService();
        if (vodCatalogService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
        }
        Disposable disposableSubscribe = ((MegacableVODCatalogService) vodCatalogService).getRecommendations("VOD_Related", (26 & 2) != 0 ? (String) null : strDecodeToken, (26 & 4) != 0 ? (String) null : contentType, (26 & 8) != 0 ? 10 : 0, (26 & 16) != 0 ? false : false, (26 & 32) == 0 ? this.fromAdultBrowsing : false).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRelatedContent.1
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<BrowsableItem>, Integer>> apply(final ItemCollection<BrowsableItem> col) {
                Single<R> map;
                Intrinsics.checkParameterIsNotNull(col, "col");
                Single<Integer> itemsCount = col.getItemsCount();
                return (itemsCount == null || (map = itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRelatedContent.1.1
                    @Override // io.reactivex.functions.Function
                    public final Pair<ItemCollection<BrowsableItem>, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(col, it);
                    }
                })) == null) ? Single.just(new Pair(col, 0)) : map;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRelatedContent.2
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer> pair) {
                accept2((Pair<? extends ItemCollection<BrowsableItem>, Integer>) pair);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(Pair<? extends ItemCollection<BrowsableItem>, Integer> pair) {
                Single<List<T>> singleSubscribeOn;
                Single<List<T>> singleObserveOn;
                Disposable disposableSubscribe2;
                if (Intrinsics.compare(pair.getSecond().intValue(), 0) > 0) {
                    VODDetailsFragment vODDetailsFragment = VODDetailsFragment.this;
                    int i = position;
                    HeaderItem headerItem = new HeaderItem(0L, title);
                    Integer second = pair.getSecond();
                    Intrinsics.checkExpressionValueIsNotNull(second, "pair.second");
                    int iIntValue = second.intValue();
                    ArrayList arrayList = new ArrayList(iIntValue);
                    for (int i2 = 0; i2 < iIntValue; i2++) {
                        arrayList.add(new LoadingWidget());
                    }
                    ArrayList arrayList2 = arrayList;
                    Constants constants = Constants.INSTANCE;
                    Context context2 = VODDetailsFragment.this.getContext();
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(vODDetailsFragment, i, headerItem, arrayList2, null, null, constants.createPresenter(context2, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), Boolean.valueOf(VODDetailsFragment.this.getFromAdultBrowsing())), true, VODRelatedContentListRow.class, 24, null);
                    Single<List<T>> list = pair.getFirst().getItems().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRelatedContent.2.2
                        @Override // io.reactivex.functions.Function
                        public final RecoContent apply(BrowsableItem it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return (RecoContent) it;
                        }
                    }).toList();
                    if (list == null || (singleSubscribeOn = list.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe2 = singleObserveOn.subscribe(new Consumer<List<RecoContent>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRelatedContent.2.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<RecoContent> list2) {
                            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(VODDetailsFragment.this, position, new HeaderItem(0L, title), list2, null, null, null, true, null, 184, null);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRelatedContent.2.4
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            th.printStackTrace();
                        }
                    })) == null) {
                        return;
                    }
                    FragmentActivity activity = VODDetailsFragment.this.getActivity();
                    if (activity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity).getDisposable());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRelatedContent.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                th.printStackTrace();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(PortalApplication.getSi…ackTrace()\n            })");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
    }

    private final void createRecommendation(final int position) {
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        VODCatalogService vodCatalogService = companion.getSingleton(context).getBOService().getVodCatalogService();
        if (vodCatalogService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
        }
        Disposable disposableSubscribe = ((MegacableVODCatalogService) vodCatalogService).getRecommendations("VOD_VOD_Perso", (26 & 2) != 0 ? (String) null : null, (26 & 4) != 0 ? (String) null : "movie", (26 & 8) != 0 ? 10 : 0, (26 & 16) != 0 ? false : false, (26 & 32) == 0 ? this.fromAdultBrowsing : false).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRecommendation.1
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<BrowsableItem>, Integer>> apply(final ItemCollection<BrowsableItem> col) {
                Single<R> map;
                Intrinsics.checkParameterIsNotNull(col, "col");
                Single<Integer> itemsCount = col.getItemsCount();
                return (itemsCount == null || (map = itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRecommendation.1.1
                    @Override // io.reactivex.functions.Function
                    public final Pair<ItemCollection<BrowsableItem>, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(col, it);
                    }
                })) == null) ? Single.just(new Pair(col, 0)) : map;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRecommendation.2
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Pair<? extends ItemCollection<BrowsableItem>, ? extends Integer> pair) {
                accept2((Pair<? extends ItemCollection<BrowsableItem>, Integer>) pair);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(Pair<? extends ItemCollection<BrowsableItem>, Integer> pair) {
                Single<List<T>> singleSubscribeOn;
                Single<List<T>> singleObserveOn;
                Disposable disposableSubscribe2;
                if (Intrinsics.compare(pair.getSecond().intValue(), 0) > 0) {
                    VODDetailsFragment vODDetailsFragment = VODDetailsFragment.this;
                    int i = position;
                    HeaderItem headerItem = new HeaderItem(0L, VODDetailsFragment.this.getString(R.string.details_vod_perso_reco_swimlanes));
                    Integer second = pair.getSecond();
                    Intrinsics.checkExpressionValueIsNotNull(second, "pair.second");
                    int iIntValue = second.intValue();
                    ArrayList arrayList = new ArrayList(iIntValue);
                    for (int i2 = 0; i2 < iIntValue; i2++) {
                        arrayList.add(new LoadingWidget());
                    }
                    ArrayList arrayList2 = arrayList;
                    Constants constants = Constants.INSTANCE;
                    Context context2 = VODDetailsFragment.this.getContext();
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(vODDetailsFragment, i, headerItem, arrayList2, null, null, constants.createPresenter(context2, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), Boolean.valueOf(VODDetailsFragment.this.getFromAdultBrowsing())), false, VODPersoListRow.class, 88, null);
                    Single<List<T>> list = pair.getFirst().getItems().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRecommendation.2.2
                        @Override // io.reactivex.functions.Function
                        public final RecoContent apply(BrowsableItem it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return (RecoContent) it;
                        }
                    }).toList();
                    if (list == null || (singleSubscribeOn = list.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe2 = singleObserveOn.subscribe(new Consumer<List<RecoContent>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRecommendation.2.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<RecoContent> list2) {
                            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(VODDetailsFragment.this, position, new HeaderItem(0L, VODDetailsFragment.this.getString(R.string.details_vod_perso_reco_swimlanes)), list2, null, null, null, false, null, 248, null);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRecommendation.2.4
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            th.printStackTrace();
                        }
                    })) == null) {
                        return;
                    }
                    FragmentActivity activity = VODDetailsFragment.this.getActivity();
                    if (activity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity).getDisposable());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.createRecommendation.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                th.printStackTrace();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(PortalApplication.getSi…ackTrace()\n            })");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
    }

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    /* JADX INFO: compiled from: VODDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "registerClick", "Lcom/dotscreen/bokit/model/BrowsableItem;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof BrowsableItem) {
                registerClick((BrowsableItem) item, row);
            }
            if (item instanceof TVAiring) {
                StringBuilder sb = new StringBuilder();
                sb.append("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=");
                TVAiring tVAiring = (TVAiring) item;
                sb.append(tVAiring.getChannel().getId());
                sb.append("&start=");
                sb.append(tVAiring.getStartDate().getTime());
                sb.append("&adult_browsing=");
                sb.append(VODDetailsFragment.this.getFromAdultBrowsing());
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
                FragmentActivity activity = VODDetailsFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                activity.startActivity(intent);
                return;
            }
            if (item instanceof HomeWidget) {
                HomeWidget homeWidget = (HomeWidget) item;
                if (homeWidget.getIntent() != null) {
                    if (homeWidget.getIntent().getAction() == null) {
                        VODDetailsFragment.this.startActivity(homeWidget.getIntent());
                    }
                    String action = homeWidget.getIntent().getAction();
                    if (action != null) {
                        if (StringsKt.startsWith$default(action, "com.dotscreen.megacable.portal", false, 2, (Object) null)) {
                            Context context = VODDetailsFragment.this.getContext();
                            if (context == null) {
                                Intrinsics.throwNpe();
                            }
                            LocalBroadcastManager.getInstance(context).sendBroadcast(homeWidget.getIntent());
                            return;
                        }
                        VODDetailsFragment.this.startActivity(homeWidget.getIntent());
                        return;
                    }
                    return;
                }
                return;
            }
            if (item instanceof BaseWidget) {
                BaseWidget baseWidget = (BaseWidget) item;
                if (baseWidget.getIntent() != null) {
                    VODDetailsFragment.this.startActivity(baseWidget.getIntent());
                    return;
                }
                return;
            }
            if ((item instanceof Movie) || (item instanceof Episode) || (item instanceof TVSeries) || (item instanceof Content)) {
                VODDetailsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + ((BrowsableItem) item).getId() + "&adult_browsing=" + VODDetailsFragment.this.getFromAdultBrowsing())));
            }
        }

        private final void registerClick(BrowsableItem item, Row row) {
            String blend = row instanceof RecommendationListRow ? ((RecommendationListRow) row).getBlend() : null;
            Context context = VODDetailsFragment.this.getContext();
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
            MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, item, null, blend, null, 10, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ItemViewClickedListener$registerClick$1
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ItemViewClickedListener$registerClick$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            });
        }
    }

    /* JADX INFO: compiled from: VODDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        }

        public ItemViewSelectedListener() {
        }
    }

    /* JADX INFO: compiled from: VODDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B!\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment$ActionListRow;", "Landroidx/leanback/widget/ListRow;", "header", "Landroidx/leanback/widget/HeaderItem;", "adapter", "Landroidx/leanback/widget/ObjectAdapter;", "(Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment;Landroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "id", "", "(Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment;JLandroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "(Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment;Landroidx/leanback/widget/ObjectAdapter;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ActionListRow extends ListRow {
        final /* synthetic */ VODDetailsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(VODDetailsFragment vODDetailsFragment, HeaderItem headerItem, ObjectAdapter adapter) {
            super(headerItem, adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = vODDetailsFragment;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(VODDetailsFragment vODDetailsFragment, long j, HeaderItem headerItem, ObjectAdapter adapter) {
            super(j, headerItem, adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = vODDetailsFragment;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(VODDetailsFragment vODDetailsFragment, ObjectAdapter adapter) {
            super(adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = vODDetailsFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: VODDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\t\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment$MySelector;", "Landroidx/leanback/widget/PresenterSelector;", "(Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment;)V", "actionListRowPresenter", "Landroidx/leanback/widget/ListRowPresenter;", "listRowPresenter", "synopsisView", "Lcom/dotscreen/portal/ui/screens/details/ProgramDetailsView;", "viewPresenter", "com/dotscreen/portal/ui/screens/details/VODDetailsFragment$MySelector$viewPresenter$1", "Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment$MySelector$viewPresenter$1;", "getPresenter", "Landroidx/leanback/widget/Presenter;", "item", "", "getPresenters", "", "()[Landroidx/leanback/widget/Presenter;", "updateRentData", "", TtmlNode.RIGHT, "Lcom/dotscreen/bokit/model/PlaybackRight;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    final class MySelector extends PresenterSelector {
        private final ListRowPresenter actionListRowPresenter;
        private ProgramDetailsView synopsisView;
        private final ListRowPresenter listRowPresenter = new ListRowPresenter(4);
        private final VODDetailsFragment$MySelector$viewPresenter$1 viewPresenter = new VODDetailsFragment$MySelector$viewPresenter$1(this, R.layout.row_program_details);

        public MySelector() {
            final int i = 4;
            this.actionListRowPresenter = new ListRowPresenter(i) { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$MySelector$actionListRowPresenter$1
                @Override // androidx.leanback.widget.ListRowPresenter, androidx.leanback.widget.RowPresenter
                protected void onSelectLevelChanged(RowPresenter.ViewHolder holder) {
                    if (VODDetailsFragment.this.getContext() == null) {
                        return;
                    }
                    float fraction = VODDetailsFragment.this.getResources().getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1);
                    super.onSelectLevelChanged(holder);
                    ListRowPresenter.ViewHolder viewHolder = (ListRowPresenter.ViewHolder) holder;
                    if (viewHolder != null) {
                        HorizontalGridView gridView = viewHolder.getGridView();
                        Intrinsics.checkExpressionValueIsNotNull(gridView, "vh.gridView");
                        int childCount = gridView.getChildCount();
                        for (int i2 = 0; i2 < childCount; i2++) {
                            float selectLevel = (viewHolder.getSelectLevel() * (1.0f - fraction)) + fraction;
                            View childAt = viewHolder.getGridView().getChildAt(i2);
                            Intrinsics.checkExpressionValueIsNotNull(childAt, "vh.gridView.getChildAt(i)");
                            childAt.setAlpha(selectLevel);
                            ProgramDetailsView programDetailsView = this.this$0.synopsisView;
                            if (programDetailsView != null) {
                                programDetailsView.setAlpha(selectLevel);
                            }
                        }
                    }
                }
            };
            this.listRowPresenter.setShadowEnabled(false);
            this.listRowPresenter.enableChildRoundedCorners(true);
            this.listRowPresenter.setSelectEffectEnabled(true);
            this.actionListRowPresenter.setShadowEnabled(false);
            this.actionListRowPresenter.enableChildRoundedCorners(false);
            this.actionListRowPresenter.setSelectEffectEnabled(false);
        }

        @Override // androidx.leanback.widget.PresenterSelector
        public Presenter getPresenter(Object item) {
            ListRowPresenter listRowPresenter;
            Intrinsics.checkParameterIsNotNull(item, "item");
            if (item instanceof ActionListRow) {
                return this.actionListRowPresenter;
            }
            if (item instanceof ViewRow) {
                listRowPresenter = this.viewPresenter;
            } else {
                listRowPresenter = this.listRowPresenter;
            }
            return listRowPresenter;
        }

        @Override // androidx.leanback.widget.PresenterSelector
        public Presenter[] getPresenters() {
            return new Presenter[]{this.listRowPresenter, this.actionListRowPresenter, this.viewPresenter};
        }

        public final void updateRentData(PlaybackRight right) {
            Intrinsics.checkParameterIsNotNull(right, "right");
            Log.d(VODDetailsFragment.this.getTAG(), "Will update rent data: " + this.synopsisView);
            ProgramDetailsView programDetailsView = this.synopsisView;
            if (programDetailsView != null) {
                programDetailsView.updateRentInfo(right);
            }
        }
    }

    /* JADX INFO: compiled from: VODDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment$ActionBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/portal/ui/screens/details/VODDetailsFragment;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class ActionBroadcastReceiver extends BroadcastReceiver {
        public ActionBroadcastReceiver() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            String action;
            Maybe<User> activeUser;
            Maybe<R> map;
            Completable completableFlatMapCompletable;
            Completable completableSubscribeOn;
            Completable completableObserveOn;
            Maybe<User> activeUser2;
            Maybe<R> map2;
            Completable completableFlatMapCompletable2;
            Completable completableSubscribeOn2;
            Completable completableObserveOn2;
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            switch (action.hashCode()) {
                case -2113038868:
                    if (action.equals(DetailsActionsKt.ACTION_PLAYBACK_VOD)) {
                        FragmentActivity activity = VODDetailsFragment.this.getActivity();
                        if (activity == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
                        FragmentActivity fragmentActivity = activity;
                        VODDetailsViewModel.Result detailsResult = VODDetailsFragment.this.getDetailsResult();
                        if (detailsResult == null) {
                            Intrinsics.throwNpe();
                        }
                        Media media = detailsResult.getMedia();
                        VODDetailsViewModel.Result detailsResult2 = VODDetailsFragment.this.getDetailsResult();
                        if (detailsResult2 == null) {
                            Intrinsics.throwNpe();
                        }
                        PlaybackActivityKt.displayResumePopupOrStartVODPlayback$default(fragmentActivity, media, detailsResult2.getRight(), VODDetailsFragment.this.getSelectedAudioLanguage(), VODDetailsFragment.this.getSelectedSubtitlesLanguage(), false, 32, null);
                        Unit unit = Unit.INSTANCE;
                        return;
                    }
                    break;
                case -1887247916:
                    if (action.equals(DetailsActionsKt.ACTION_PLAYBACK_TRAILER)) {
                        FragmentActivity activity2 = VODDetailsFragment.this.getActivity();
                        if (activity2 == null) {
                            Intrinsics.throwNpe();
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("megacable://com.dotscreen.megacable.portal/player?content_id=");
                        VODDetailsViewModel.Result detailsResult3 = VODDetailsFragment.this.getDetailsResult();
                        if (detailsResult3 == null) {
                            Intrinsics.throwNpe();
                        }
                        sb.append(detailsResult3.getMedia().getId());
                        sb.append("&trailer=true");
                        activity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
                        Unit unit2 = Unit.INSTANCE;
                        return;
                    }
                    break;
                case -1149279759:
                    if (action.equals(DetailsActionsKt.ACTION_AUDIO)) {
                        ArrayList arrayList = new ArrayList();
                        String[] audioLanguages = VODDetailsFragment.this.getAudioLanguages();
                        if (audioLanguages != null) {
                            for (String str : audioLanguages) {
                                if (context == null) {
                                    Intrinsics.throwNpe();
                                }
                                String languageName = PortalUtils.getLanguageName(context, str);
                                Intrinsics.checkExpressionValueIsNotNull(languageName, "PortalUtils.getLanguageN…                        )");
                                arrayList.add(languageName);
                            }
                            Unit unit3 = Unit.INSTANCE;
                        }
                        FragmentActivity activity3 = VODDetailsFragment.this.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(activity3, "activity!!");
                        CheckListDialog.Builder itemList = new CheckListDialog.Builder(activity3).setTitle(R.string.dialog_select_audio_track_title).setItemList(arrayList);
                        String[] audioLanguages2 = VODDetailsFragment.this.getAudioLanguages();
                        itemList.setSelectedItem(Math.max(0, audioLanguages2 != null ? ArraysKt.indexOf(audioLanguages2, VODDetailsFragment.this.getSelectedAudioLanguage()) : 0)).setOnItemClickedListener(new CheckListDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$1
                            @Override // com.dotscreen.tv.ui.dialog.CheckListDialog.OnItemClickedListener
                            public void onItemClicked(CheckListDialog dialog, int itemIndex) {
                                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                                VODDetailsFragment vODDetailsFragment = VODDetailsFragment.this;
                                String[] audioLanguages3 = VODDetailsFragment.this.getAudioLanguages();
                                vODDetailsFragment.setSelectedAudioLanguage(audioLanguages3 != null ? audioLanguages3[itemIndex] : null);
                            }
                        }).create().show();
                        Unit unit4 = Unit.INSTANCE;
                        return;
                    }
                    break;
                case -1048509004:
                    if (action.equals(DetailsActionsKt.ACTION_FAVORITES_ADD)) {
                        FragmentActivity activity4 = VODDetailsFragment.this.getActivity();
                        if (!(activity4 instanceof BaseFragmentActivity)) {
                            activity4 = null;
                        }
                        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) activity4;
                        if (baseFragmentActivity != null) {
                            baseFragmentActivity.setLoaderVisible(true);
                        }
                        FragmentActivity activity5 = VODDetailsFragment.this.getActivity();
                        BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) (activity5 instanceof BaseFragmentActivity ? activity5 : null);
                        if (baseFragmentActivity2 != null) {
                            baseFragmentActivity2.setBlockInput(true);
                        }
                        PortalApplication.Companion companion = PortalApplication.INSTANCE;
                        if (context == null) {
                            Intrinsics.throwNpe();
                        }
                        UserService userService = companion.getSingleton(context).getBOService().getUserService();
                        if (userService == null || (activeUser = userService.getActiveUser()) == null || (map = activeUser.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$1$5
                            @Override // io.reactivex.functions.Function
                            public final RightVUser apply(User it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                return (RightVUser) it;
                            }
                        })) == 0 || (completableFlatMapCompletable = map.flatMapCompletable(new Function<RightVUser, CompletableSource>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$3
                            @Override // io.reactivex.functions.Function
                            public final Completable apply(RightVUser it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                VODDetailsFragment vODDetailsFragment = VODDetailsFragment.this;
                                VODDetailsViewModel.Result detailsResult4 = VODDetailsFragment.this.getDetailsResult();
                                if (detailsResult4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                return it.addVODToFavorites(vODDetailsFragment.decodeToken(detailsResult4.getMedia().getId()));
                            }
                        })) == null || (completableSubscribeOn = completableFlatMapCompletable.subscribeOn(Schedulers.io())) == null || (completableObserveOn = completableSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) {
                            return;
                        }
                        completableObserveOn.subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                FragmentActivity activity6 = VODDetailsFragment.this.getActivity();
                                if (!(activity6 instanceof BaseFragmentActivity)) {
                                    activity6 = null;
                                }
                                BaseFragmentActivity baseFragmentActivity3 = (BaseFragmentActivity) activity6;
                                if (baseFragmentActivity3 != null) {
                                    baseFragmentActivity3.setLoaderVisible(false);
                                }
                                FragmentActivity activity7 = VODDetailsFragment.this.getActivity();
                                BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) (activity7 instanceof BaseFragmentActivity ? activity7 : null);
                                if (baseFragmentActivity4 != null) {
                                    baseFragmentActivity4.setBlockInput(false);
                                }
                                VODDetailsViewModel.Result detailsResult4 = VODDetailsFragment.this.getDetailsResult();
                                if (detailsResult4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                detailsResult4.setVODIsInFavorites(true);
                                ArrayObjectAdapter arrayObjectAdapter = VODDetailsFragment.this.adapterButton;
                                Integer num = VODDetailsFragment.this.actionFavoriteIndex;
                                if (num == null) {
                                    Intrinsics.throwNpe();
                                }
                                int iIntValue = num.intValue();
                                String string = VODDetailsFragment.this.getString(R.string.details_action_remove_VOD_to_fav);
                                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.detai…action_remove_VOD_to_fav)");
                                arrayObjectAdapter.replace(iIntValue, new HomeWidget(string, R.drawable.ic_remove_from_favorites, new Intent(DetailsActionsKt.ACTION_FAVORITES_REMOVE), R.color.red));
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable error) {
                                FragmentActivity activity6 = VODDetailsFragment.this.getActivity();
                                if (!(activity6 instanceof BaseFragmentActivity)) {
                                    activity6 = null;
                                }
                                BaseFragmentActivity baseFragmentActivity3 = (BaseFragmentActivity) activity6;
                                if (baseFragmentActivity3 != null) {
                                    baseFragmentActivity3.setLoaderVisible(false);
                                }
                                FragmentActivity activity7 = VODDetailsFragment.this.getActivity();
                                if (!(activity7 instanceof BaseFragmentActivity)) {
                                    activity7 = null;
                                }
                                BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) activity7;
                                if (baseFragmentActivity4 != null) {
                                    baseFragmentActivity4.setBlockInput(false);
                                }
                                FragmentActivity activity8 = VODDetailsFragment.this.getActivity();
                                if (!(activity8 instanceof BaseFragmentActivity)) {
                                    activity8 = null;
                                }
                                BaseFragmentActivity baseFragmentActivity5 = (BaseFragmentActivity) activity8;
                                if (baseFragmentActivity5 != null) {
                                    Intrinsics.checkExpressionValueIsNotNull(error, "error");
                                    BaseFragmentActivity.displayErrorMessage$default(baseFragmentActivity5, error, 0, 2, null);
                                }
                            }
                        });
                        return;
                    }
                    break;
                case -690331674:
                    if (action.equals(DetailsActionsKt.ACTION_ORDER_OFFER)) {
                        FragmentActivity activity6 = VODDetailsFragment.this.getActivity();
                        if (!(activity6 instanceof BaseFragmentActivity)) {
                            activity6 = null;
                        }
                        BaseFragmentActivity baseFragmentActivity3 = (BaseFragmentActivity) activity6;
                        if (baseFragmentActivity3 != null) {
                            baseFragmentActivity3.setLoaderVisible(true);
                        }
                        FragmentActivity activity7 = VODDetailsFragment.this.getActivity();
                        BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) (activity7 instanceof BaseFragmentActivity ? activity7 : null);
                        if (baseFragmentActivity4 != null) {
                            baseFragmentActivity4.setBlockInput(true);
                        }
                        VODDetailsFragment vODDetailsFragment = VODDetailsFragment.this;
                        VODDetailsViewModel.Result detailsResult4 = vODDetailsFragment.getDetailsResult();
                        if (detailsResult4 == null) {
                            Intrinsics.throwNpe();
                        }
                        vODDetailsFragment.showPinCode(detailsResult4.getOffers().get(0));
                    }
                    break;
                case 1573285878:
                    if (action.equals(DetailsActionsKt.ACTION_SUBTITLES)) {
                        ArrayList arrayList2 = new ArrayList();
                        String string = VODDetailsFragment.this.getString(R.string.dialog_select_caption_none);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.dialog_select_caption_none)");
                        arrayList2.add(string);
                        String[] subtitlesLanguages = VODDetailsFragment.this.getSubtitlesLanguages();
                        if (subtitlesLanguages != null) {
                            for (String str2 : subtitlesLanguages) {
                                if (context == null) {
                                    Intrinsics.throwNpe();
                                }
                                String languageName2 = PortalUtils.getLanguageName(context, str2);
                                Intrinsics.checkExpressionValueIsNotNull(languageName2, "PortalUtils.getLanguageN…                        )");
                                arrayList2.add(languageName2);
                            }
                            Unit unit5 = Unit.INSTANCE;
                        }
                        FragmentActivity activity8 = VODDetailsFragment.this.getActivity();
                        if (activity8 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(activity8, "activity!!");
                        CheckListDialog.Builder itemList2 = new CheckListDialog.Builder(activity8).setTitle(R.string.dialog_select_caption_track_title).setItemList(arrayList2);
                        String[] subtitlesLanguages2 = VODDetailsFragment.this.getSubtitlesLanguages();
                        itemList2.setSelectedItem(Math.max(0, (subtitlesLanguages2 != null ? ArraysKt.indexOf(subtitlesLanguages2, VODDetailsFragment.this.getSelectedSubtitlesLanguage()) : -1) + 1)).setOnItemClickedListener(new CheckListDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$2
                            @Override // com.dotscreen.tv.ui.dialog.CheckListDialog.OnItemClickedListener
                            public void onItemClicked(CheckListDialog dialog, int itemIndex) {
                                String[] subtitlesLanguages3;
                                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                                VODDetailsFragment vODDetailsFragment2 = VODDetailsFragment.this;
                                String str3 = null;
                                if (itemIndex != 0 && (subtitlesLanguages3 = VODDetailsFragment.this.getSubtitlesLanguages()) != null) {
                                    str3 = subtitlesLanguages3[itemIndex - 1];
                                }
                                vODDetailsFragment2.setSelectedSubtitlesLanguage(str3);
                            }
                        }).create().show();
                        Unit unit6 = Unit.INSTANCE;
                        return;
                    }
                    break;
                case 1653371505:
                    if (action.equals(DetailsActionsKt.ACTION_FAVORITES_REMOVE)) {
                        FragmentActivity activity9 = VODDetailsFragment.this.getActivity();
                        if (!(activity9 instanceof BaseFragmentActivity)) {
                            activity9 = null;
                        }
                        BaseFragmentActivity baseFragmentActivity5 = (BaseFragmentActivity) activity9;
                        if (baseFragmentActivity5 != null) {
                            baseFragmentActivity5.setLoaderVisible(true);
                        }
                        FragmentActivity activity10 = VODDetailsFragment.this.getActivity();
                        BaseFragmentActivity baseFragmentActivity6 = (BaseFragmentActivity) (activity10 instanceof BaseFragmentActivity ? activity10 : null);
                        if (baseFragmentActivity6 != null) {
                            baseFragmentActivity6.setBlockInput(true);
                        }
                        PortalApplication.Companion companion2 = PortalApplication.INSTANCE;
                        if (context == null) {
                            Intrinsics.throwNpe();
                        }
                        UserService userService2 = companion2.getSingleton(context).getBOService().getUserService();
                        if (userService2 == null || (activeUser2 = userService2.getActiveUser()) == null || (map2 = activeUser2.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$1$9
                            @Override // io.reactivex.functions.Function
                            public final RightVUser apply(User it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                return (RightVUser) it;
                            }
                        })) == 0 || (completableFlatMapCompletable2 = map2.flatMapCompletable(new Function<RightVUser, CompletableSource>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$6
                            @Override // io.reactivex.functions.Function
                            public final Completable apply(RightVUser it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                VODDetailsFragment vODDetailsFragment2 = VODDetailsFragment.this;
                                VODDetailsViewModel.Result detailsResult5 = VODDetailsFragment.this.getDetailsResult();
                                if (detailsResult5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                return it.removeVODFromFavorites(vODDetailsFragment2.decodeToken(detailsResult5.getMedia().getId()));
                            }
                        })) == null || (completableSubscribeOn2 = completableFlatMapCompletable2.subscribeOn(Schedulers.io())) == null || (completableObserveOn2 = completableSubscribeOn2.observeOn(AndroidSchedulers.mainThread())) == null) {
                            return;
                        }
                        completableObserveOn2.subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$7
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                FragmentActivity activity11 = VODDetailsFragment.this.getActivity();
                                if (!(activity11 instanceof BaseFragmentActivity)) {
                                    activity11 = null;
                                }
                                BaseFragmentActivity baseFragmentActivity7 = (BaseFragmentActivity) activity11;
                                if (baseFragmentActivity7 != null) {
                                    baseFragmentActivity7.setLoaderVisible(false);
                                }
                                FragmentActivity activity12 = VODDetailsFragment.this.getActivity();
                                BaseFragmentActivity baseFragmentActivity8 = (BaseFragmentActivity) (activity12 instanceof BaseFragmentActivity ? activity12 : null);
                                if (baseFragmentActivity8 != null) {
                                    baseFragmentActivity8.setBlockInput(false);
                                }
                                VODDetailsViewModel.Result detailsResult5 = VODDetailsFragment.this.getDetailsResult();
                                if (detailsResult5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                detailsResult5.setVODIsInFavorites(false);
                                ArrayObjectAdapter arrayObjectAdapter = VODDetailsFragment.this.adapterButton;
                                Integer num = VODDetailsFragment.this.actionFavoriteIndex;
                                if (num == null) {
                                    Intrinsics.throwNpe();
                                }
                                int iIntValue = num.intValue();
                                String string2 = VODDetailsFragment.this.getString(R.string.details_action_add_VOD_to_fav);
                                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.details_action_add_VOD_to_fav)");
                                arrayObjectAdapter.replace(iIntValue, new HomeWidget(string2, R.drawable.ic_add_to_favorite, new Intent(DetailsActionsKt.ACTION_FAVORITES_ADD), R.color.white));
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$8
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable error) {
                                FragmentActivity activity11 = VODDetailsFragment.this.getActivity();
                                if (!(activity11 instanceof BaseFragmentActivity)) {
                                    activity11 = null;
                                }
                                BaseFragmentActivity baseFragmentActivity7 = (BaseFragmentActivity) activity11;
                                if (baseFragmentActivity7 != null) {
                                    baseFragmentActivity7.setLoaderVisible(false);
                                }
                                FragmentActivity activity12 = VODDetailsFragment.this.getActivity();
                                if (!(activity12 instanceof BaseFragmentActivity)) {
                                    activity12 = null;
                                }
                                BaseFragmentActivity baseFragmentActivity8 = (BaseFragmentActivity) activity12;
                                if (baseFragmentActivity8 != null) {
                                    baseFragmentActivity8.setBlockInput(false);
                                }
                                FragmentActivity activity13 = VODDetailsFragment.this.getActivity();
                                if (!(activity13 instanceof BaseFragmentActivity)) {
                                    activity13 = null;
                                }
                                BaseFragmentActivity baseFragmentActivity9 = (BaseFragmentActivity) activity13;
                                if (baseFragmentActivity9 != null) {
                                    Intrinsics.checkExpressionValueIsNotNull(error, "error");
                                    BaseFragmentActivity.displayErrorMessage$default(baseFragmentActivity9, error, 0, 2, null);
                                }
                            }
                        });
                        return;
                    }
                    break;
                case 2138790265:
                    if (action.equals(DetailsActionsKt.ACTION_SYNOPSIS)) {
                        FragmentActivity activity11 = VODDetailsFragment.this.getActivity();
                        if (activity11 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(activity11, "activity!!");
                        CastingDialog.Builder builder = new CastingDialog.Builder(activity11);
                        VODDetailsViewModel.Result detailsResult5 = VODDetailsFragment.this.getDetailsResult();
                        if (detailsResult5 == null) {
                            Intrinsics.throwNpe();
                        }
                        builder.setContent(detailsResult5.getMedia()).create().show();
                        Unit unit7 = Unit.INSTANCE;
                        return;
                    }
                    break;
            }
            Unit unit8 = Unit.INSTANCE;
        }
    }

    public final void showPinCode(Offer item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        PinCodeDialog.Builder message = new PinCodeDialog.Builder(context).setCheckPinCode(false).setTitle(R.string.VOD_pin_purchase_popup_title).setMessage(R.string.VOD_pin_purchase_popup_subtitle);
        StringBuilder sb = new StringBuilder();
        VODDetailsViewModel.Result result = this.detailsResult;
        if (result == null) {
            Intrinsics.throwNpe();
        }
        sb.append(result.getMedia().getTitle());
        sb.append("\n$");
        sb.append(item.getPrice());
        message.setInformation(sb.toString()).setOnPinCodeListener((PinCodeDialog.OnPinCodeListener) new C03801(item)).create().show();
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$showPinCode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VODDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/dotscreen/portal/ui/screens/details/VODDetailsFragment$showPinCode$1", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;", "onPinCodeCorrect", "", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "pinCode", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class C03801 implements PinCodeDialog.OnPinCodeListener {
        final /* synthetic */ Offer $item;

        C03801(Offer offer) {
            this.$item = offer;
        }

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
        public void onPinCodeCorrect(final PinCodeDialog dialog, String pinCode) {
            Single<Boolean> singleCheckPincode;
            Single<Boolean> singleSubscribeOn;
            Single<Boolean> singleObserveOn;
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = dialog.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "dialog.context");
            UserService userService = companion.getSingleton(context).getBOService().getUserService();
            if (userService == null || (singleCheckPincode = userService.checkPincode(null, PincodeType.Purchase, pinCode)) == null || (singleSubscribeOn = singleCheckPincode.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) {
                return;
            }
            singleObserveOn.subscribe(new BiConsumer<Boolean, Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment$showPinCode$1$onPinCodeCorrect$1
                @Override // io.reactivex.functions.BiConsumer
                public final void accept(Boolean correct, Throwable th) {
                    dialog.dismiss();
                    Intrinsics.checkExpressionValueIsNotNull(correct, "correct");
                    if (correct.booleanValue()) {
                        VODDetailsFragment.this.requestPurchase(this.this$0.$item);
                        return;
                    }
                    FragmentActivity activity = VODDetailsFragment.this.getActivity();
                    if (!(activity instanceof BaseFragmentActivity)) {
                        activity = null;
                    }
                    BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) activity;
                    if (baseFragmentActivity != null) {
                        baseFragmentActivity.setLoaderVisible(false);
                    }
                    FragmentActivity activity2 = VODDetailsFragment.this.getActivity();
                    BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) (activity2 instanceof BaseFragmentActivity ? activity2 : null);
                    if (baseFragmentActivity2 != null) {
                        baseFragmentActivity2.setBlockInput(false);
                    }
                    Toast.makeText(dialog.getContext(), VODDetailsFragment.this.getString(R.string.dialog_code_error_message), 0).show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestPurchase(final Offer item) {
        Maybe<User> activeUser;
        Maybe<R> maybeFlatMap;
        Maybe maybeSubscribeOn;
        Maybe maybeObserveOn;
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        UserService userService = companion.getSingleton(context).getBOService().getUserService();
        if (userService == null || (activeUser = userService.getActiveUser()) == null || (maybeFlatMap = activeUser.flatMap((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.requestPurchase.1
            @Override // io.reactivex.functions.Function
            public final Maybe<PlaybackRight> apply(User it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Completable completableOrderOffer = it.orderOffer(item);
                VODDetailsViewModel.Result detailsResult = VODDetailsFragment.this.getDetailsResult();
                if (detailsResult == null) {
                    Intrinsics.throwNpe();
                }
                return completableOrderOffer.andThen(detailsResult.getMedia().getPlaybackRights().firstElement());
            }
        })) == 0 || (maybeSubscribeOn = maybeFlatMap.subscribeOn(Schedulers.io())) == null || (maybeObserveOn = maybeSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) {
            return;
        }
        maybeObserveOn.subscribe(new Consumer<PlaybackRight>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.requestPurchase.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(PlaybackRight it) {
                Log.d(VODDetailsFragment.this.getTAG(), "ordered item: " + item + ", playbackRight: " + it);
                ArrayObjectAdapter arrayObjectAdapter = VODDetailsFragment.this.adapterButton;
                Integer num = VODDetailsFragment.this.actionOfferIndex;
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                int iIntValue = num.intValue();
                String string = VODDetailsFragment.this.getString(R.string.details_action_play);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_play)");
                arrayObjectAdapter.replace(iIntValue, new HomeWidget(string, R.drawable.ic_picto_pg_play, new Intent(DetailsActionsKt.ACTION_PLAYBACK_VOD), 0, 8, null));
                FragmentActivity activity = VODDetailsFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
                FragmentActivity fragmentActivity = activity;
                VODDetailsViewModel.Result detailsResult = VODDetailsFragment.this.getDetailsResult();
                if (detailsResult == null) {
                    Intrinsics.throwNpe();
                }
                Media media = detailsResult.getMedia();
                VODDetailsViewModel.Result detailsResult2 = VODDetailsFragment.this.getDetailsResult();
                if (detailsResult2 == null) {
                    Intrinsics.throwNpe();
                }
                PlaybackActivityKt.displayResumePopupOrStartVODPlayback$default(fragmentActivity, media, detailsResult2.getRight(), VODDetailsFragment.this.getSelectedAudioLanguage(), VODDetailsFragment.this.getSelectedSubtitlesLanguage(), false, 32, null);
                MySelector mySelector = VODDetailsFragment.this.detailSelector;
                if (mySelector != null) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    mySelector.updateRentData(it);
                }
                FragmentActivity activity2 = VODDetailsFragment.this.getActivity();
                if (!(activity2 instanceof BaseFragmentActivity)) {
                    activity2 = null;
                }
                BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) activity2;
                if (baseFragmentActivity != null) {
                    baseFragmentActivity.setLoaderVisible(false);
                }
                FragmentActivity activity3 = VODDetailsFragment.this.getActivity();
                BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) (activity3 instanceof BaseFragmentActivity ? activity3 : null);
                if (baseFragmentActivity2 != null) {
                    baseFragmentActivity2.setBlockInput(false);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.requestPurchase.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.d(VODDetailsFragment.this.getTAG(), "ordered item error", th);
                if (th instanceof VolleyError) {
                    Throwable cause = th.getCause();
                    if (cause != null ? cause instanceof BackendErrorException : true) {
                        BackendErrorException backendErrorException = (BackendErrorException) th.getCause();
                        if (Intrinsics.areEqual(backendErrorException != null ? backendErrorException.getBackendCode() : null, "INSUFFICIENT_CREDIT")) {
                            Context context2 = VODDetailsFragment.this.getContext();
                            if (context2 == null) {
                                Intrinsics.throwNpe();
                            }
                            Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                            new PortalDialog.Builder(context2).setTitle(R.string.error_popup_title).setMessage(R.string.error_purchase_limit_permit).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.requestPurchase.3.1
                                @Override // android.content.DialogInterface.OnDismissListener
                                public final void onDismiss(DialogInterface dialogInterface) {
                                    DSLog.INSTANCE.a("Clicked on PositiveButton on ordered item error");
                                    FragmentActivity activity = VODDetailsFragment.this.getActivity();
                                    if (!(activity instanceof BaseFragmentActivity)) {
                                        activity = null;
                                    }
                                    BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) activity;
                                    if (baseFragmentActivity != null) {
                                        baseFragmentActivity.setLoaderVisible(false);
                                    }
                                    FragmentActivity activity2 = VODDetailsFragment.this.getActivity();
                                    BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) (activity2 instanceof BaseFragmentActivity ? activity2 : null);
                                    if (baseFragmentActivity2 != null) {
                                        baseFragmentActivity2.setBlockInput(false);
                                    }
                                }
                            }).setPositiveButton(R.string.error_popup_cancel, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.requestPurchase.3.2
                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i) {
                                    DSLog.INSTANCE.a("Clicked on PositiveButton on ordered item error");
                                    FragmentActivity activity = VODDetailsFragment.this.getActivity();
                                    if (!(activity instanceof BaseFragmentActivity)) {
                                        activity = null;
                                    }
                                    BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) activity;
                                    if (baseFragmentActivity != null) {
                                        baseFragmentActivity.setLoaderVisible(false);
                                    }
                                    FragmentActivity activity2 = VODDetailsFragment.this.getActivity();
                                    BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) (activity2 instanceof BaseFragmentActivity ? activity2 : null);
                                    if (baseFragmentActivity2 != null) {
                                        baseFragmentActivity2.setBlockInput(false);
                                    }
                                }
                            }).create().show();
                            return;
                        }
                    }
                }
                Context context3 = VODDetailsFragment.this.getContext();
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                new PortalDialog.Builder(context3).setTitle(R.string.error_popup_title).setMessage(R.string.VOD_error_purchase_popup_subtitle).setPositiveButton(R.string.error_popup_cancel, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.details.VODDetailsFragment.requestPurchase.3.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        DSLog.INSTANCE.a("Clicked on PositiveButton on ordered item error");
                        FragmentActivity activity = VODDetailsFragment.this.getActivity();
                        if (!(activity instanceof BaseFragmentActivity)) {
                            activity = null;
                        }
                        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) activity;
                        if (baseFragmentActivity != null) {
                            baseFragmentActivity.setLoaderVisible(false);
                        }
                        FragmentActivity activity2 = VODDetailsFragment.this.getActivity();
                        BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) (activity2 instanceof BaseFragmentActivity ? activity2 : null);
                        if (baseFragmentActivity2 != null) {
                            baseFragmentActivity2.setBlockInput(false);
                        }
                    }
                }).create().show();
            }
        });
    }

    public final String decodeToken(String contentId) throws JSONException {
        Intrinsics.checkParameterIsNotNull(contentId, "contentId");
        if (StringsKt.startsWith$default(contentId, "rightv", false, 2, (Object) null)) {
            return (String) StringsKt.split$default((CharSequence) contentId, new String[]{":"}, false, 0, 6, (Object) null).get(2);
        }
        byte[] bArrDecode = Base64.decode(contentId, 0);
        Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(contentId, Base64.DEFAULT)");
        String string = new JSONObject(new String(bArrDecode, Charsets.UTF_8)).getString(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
        Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject(String(Base64…).getString(\"content_id\")");
        return string;
    }
}
