package com.dotscreen.portal.ui.screens.details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.SparseArray;
import android.view.View;
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
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.model.CatchupDetailsViewModel;
import com.dotscreen.portal.model.HomeWidget;
import com.dotscreen.portal.model.LoadingWidget;
import com.dotscreen.portal.ui.RoundListRowPresenter;
import com.dotscreen.portal.ui.ViewRow;
import com.dotscreen.portal.ui.cards.DetailsActionCardPresenter;
import com.dotscreen.portal.ui.screens.BaseDetailsFragment;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.ISwimlanesFragment;
import com.dotscreen.portal.ui.screens.player.PlaybackActivityKt;
import com.dotscreen.portal.utils.PortalUtils;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.ui.dialog.CheckListDialog;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: CatchupDetailsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u0002:\u0005NOPQRB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010>\u001a\u00020?H\u0002J\b\u0010@\u001a\u00020?H\u0002J\u000e\u0010A\u001a\u00020\u00052\u0006\u0010B\u001a\u00020\u0005J\b\u0010C\u001a\u00020\u001fH\u0014J\b\u0010D\u001a\u00020\u001fH\u0014J\b\u0010E\u001a\u00020\u001fH\u0002J\u0012\u0010F\u001a\u00020?2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\b\u0010I\u001a\u00020?H\u0016J\b\u0010J\u001a\u00020?H\u0016J\b\u0010K\u001a\u00020?H\u0016J\b\u0010L\u001a\u00020?H\u0002J\b\u0010M\u001a\u00020?H\u0002R\u001c\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0012\u0010\u000e\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\b\u0012\u00060\u0005j\u0002`\u0013\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R(\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0010\u0010)\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010/\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\b\"\u0004\b1\u00102R\"\u00103\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\b\"\u0004\b5\u00102R\u001a\u00106\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001d\u0010;\u001a\u000e\u0012\b\u0012\u00060\u0005j\u0002`\u0013\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b<\u0010\u0015R\u0010\u0010=\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment;", "Lcom/dotscreen/portal/ui/screens/BaseDetailsFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "actionBroadcastReceiver", "Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$ActionBroadcastReceiver;", "actionRecordIndex", "", "Ljava/lang/Integer;", "actionWatchIndex", "actionsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "audioLanguages", "", "Lcom/dotscreen/bokit/model/LanguageCode;", "getAudioLanguages", "()[Ljava/lang/String;", "value", "Lcom/dotscreen/portal/model/CatchupDetailsViewModel$Result;", "detailsResult", "getDetailsResult", "()Lcom/dotscreen/portal/model/CatchupDetailsViewModel$Result;", "setDetailsResult", "(Lcom/dotscreen/portal/model/CatchupDetailsViewModel$Result;)V", "fragmentAlignment", "fromAdultBrowsing", "", "getFromAdultBrowsing", "()Z", "setFromAdultBrowsing", "(Z)V", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "nextAiringsListRow", "rowsAdapter", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "selectedAudioLanguage", "getSelectedAudioLanguage", "setSelectedAudioLanguage", "(Ljava/lang/String;)V", "selectedSubtitlesLanguage", "getSelectedSubtitlesLanguage", "setSelectedSubtitlesLanguage", "startY", "getStartY", "()I", "setStartY", "(I)V", "subtitlesLanguages", "getSubtitlesLanguages", "topMoviesListRow", "createRecommendationsRows", "", "createRows", "decodeToken", "contentId", "hasMultipleAudios", "hasMultipleSubtitles", "hasReminder", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStart", "onStop", "setupEventListeners", "setupUIElements", "ActionBroadcastReceiver", "ActionListRow", "ItemViewClickedListener", "ItemViewSelectedListener", "MySelector", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupDetailsFragment extends BaseDetailsFragment implements ISwimlanesFragment {
    private HashMap _$_findViewCache;
    private ActionBroadcastReceiver actionBroadcastReceiver;
    private Integer actionRecordIndex;
    private Integer actionWatchIndex;
    private ArrayObjectAdapter actionsAdapter;
    private CatchupDetailsViewModel.Result detailsResult;
    private Integer fragmentAlignment;
    private boolean fromAdultBrowsing;
    private ListRow nextAiringsListRow;
    private ArrayObjectAdapter rowsAdapter;
    private String selectedAudioLanguage;
    private String selectedSubtitlesLanguage;
    private int startY;
    private ListRow topMoviesListRow;
    private final String TAG = CatchupDetailsFragment.class.getSimpleName();
    private final SparseArray<ListRow> listRows = new SparseArray<>();

    private final boolean hasReminder() {
        return false;
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

    public final CatchupDetailsViewModel.Result getDetailsResult() {
        return this.detailsResult;
    }

    public final void setDetailsResult(CatchupDetailsViewModel.Result result) {
        this.detailsResult = result;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment$detailsResult$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.createRows();
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUIElements();
        setupEventListeners();
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
            intentFilter.addAction(DetailsActionsKt.ACTION_PLAYBACK_CATCHUP);
            intentFilter.addAction(DetailsActionsKt.ACTION_PLAYBACK_STARTOVER);
            intentFilter.addAction(DetailsActionsKt.ACTION_PLAYBACK_RECORDING);
            intentFilter.addAction(DetailsActionsKt.ACTION_FAVORITES_ADD);
            intentFilter.addAction(DetailsActionsKt.ACTION_FAVORITES_REMOVE);
            intentFilter.addAction(DetailsActionsKt.ACTION_REMINDER_ADD);
            intentFilter.addAction(DetailsActionsKt.ACTION_REMINDER_REMOVE);
            intentFilter.addAction(DetailsActionsKt.ACTION_RECORDING_SCHEDULE);
            intentFilter.addAction(DetailsActionsKt.ACTION_RECORDING_CANCEL);
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
        ArrayObjectAdapter arrayObjectAdapter;
        ArrayObjectAdapter arrayObjectAdapter2;
        ArrayObjectAdapter arrayObjectAdapter3;
        setRowsAdapter(new ArrayObjectAdapter(new MySelector()));
        ArrayObjectAdapter rowsAdapter = getRowsAdapter();
        if (rowsAdapter != null) {
            rowsAdapter.add(new ViewRow());
        }
        ArrayObjectAdapter arrayObjectAdapter4 = new ArrayObjectAdapter(new DetailsActionCardPresenter());
        this.actionsAdapter = arrayObjectAdapter4;
        this.actionWatchIndex = arrayObjectAdapter4 != null ? Integer.valueOf(arrayObjectAdapter4.size()) : null;
        ArrayObjectAdapter arrayObjectAdapter5 = this.actionsAdapter;
        if (arrayObjectAdapter5 != null) {
            String string = getString(R.string.details_action_play);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_play)");
            arrayObjectAdapter5.add(new HomeWidget(string, R.drawable.ic_picto_pg_play, new Intent(DetailsActionsKt.ACTION_PLAYBACK_CATCHUP), 0, 8, null));
        }
        CatchupDetailsViewModel.Result result = this.detailsResult;
        if (result == null) {
            Intrinsics.throwNpe();
        }
        if (result.getMedia().getSummary() != null && (arrayObjectAdapter3 = this.actionsAdapter) != null) {
            String string2 = getString(R.string.details_action_synopsis);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.details_action_synopsis)");
            arrayObjectAdapter3.add(new HomeWidget(string2, R.drawable.ic_synopsis, new Intent(DetailsActionsKt.ACTION_SYNOPSIS), 0, 8, null));
        }
        if (hasMultipleAudios() && (arrayObjectAdapter2 = this.actionsAdapter) != null) {
            String string3 = getString(R.string.details_action_audio);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.details_action_audio)");
            arrayObjectAdapter2.add(new HomeWidget(string3, R.drawable.ic_pictos_pg_lan, new Intent(DetailsActionsKt.ACTION_AUDIO), 0, 8, null));
        }
        if (hasMultipleSubtitles() && (arrayObjectAdapter = this.actionsAdapter) != null) {
            String string4 = getString(R.string.details_action_subtitle);
            Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.details_action_subtitle)");
            arrayObjectAdapter.add(new HomeWidget(string4, R.drawable.ic_picto_pg_sub, new Intent(DetailsActionsKt.ACTION_SUBTITLES), 0, 8, null));
        }
        ArrayObjectAdapter rowsAdapter2 = getRowsAdapter();
        if (rowsAdapter2 != null) {
            ArrayObjectAdapter arrayObjectAdapter6 = this.actionsAdapter;
            if (arrayObjectAdapter6 == null) {
                Intrinsics.throwNpe();
            }
            rowsAdapter2.add(new ActionListRow(this, null, arrayObjectAdapter6));
        }
        createRecommendationsRows();
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
        CatchupDetailsViewModel.Result result = this.detailsResult;
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
        CatchupDetailsViewModel.Result result = this.detailsResult;
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

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    public final String decodeToken(String contentId) {
        Intrinsics.checkParameterIsNotNull(contentId, "contentId");
        try {
            if (StringsKt.startsWith$default(contentId, "rightv", false, 2, (Object) null)) {
                return (String) StringsKt.split$default((CharSequence) contentId, new String[]{":"}, false, 0, 6, (Object) null).get(2);
            }
            byte[] bArrDecode = Base64.decode(contentId, 0);
            Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(contentId, Base64.DEFAULT)");
            String string = new JSONObject(new String(bArrDecode, Charsets.UTF_8)).getString(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
            Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject(String(Base64…).getString(\"content_id\")");
            return string;
        } catch (Exception unused) {
            return contentId;
        }
    }

    private final void createRecommendationsRows() {
        CatchupDetailsViewModel.Result result = this.detailsResult;
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
        LiveTVService liveTVService = companion.getSingleton(context).getBOService().getLiveTVService();
        if (liveTVService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
        Disposable disposableSubscribe = ((MegacableLiveTVService) liveTVService).getRecommendations("EPG_Related", strDecodeToken, this.fromAdultBrowsing).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.1
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<TVAiring>, Integer>> apply(final ItemCollection<TVAiring> col) {
                Single<R> map;
                Intrinsics.checkParameterIsNotNull(col, "col");
                Single<Integer> itemsCount = col.getItemsCount();
                return (itemsCount == null || (map = itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.1.1
                    @Override // io.reactivex.functions.Function
                    public final Pair<ItemCollection<TVAiring>, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(col, it);
                    }
                })) == null) ? Single.just(new Pair(col, 0)) : map;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<TVAiring>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.2
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Pair<? extends ItemCollection<TVAiring>, ? extends Integer> pair) {
                accept2((Pair<? extends ItemCollection<TVAiring>, Integer>) pair);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(Pair<? extends ItemCollection<TVAiring>, Integer> pair) {
                Single<T> singleSubscribeOn;
                Single<T> singleObserveOn;
                Disposable disposableSubscribe2;
                if (Intrinsics.compare(pair.getSecond().intValue(), 0) > 0) {
                    ArrayObjectAdapter rowsAdapter = CatchupDetailsFragment.this.getRowsAdapter();
                    final int iMin = Math.min(3, rowsAdapter != null ? rowsAdapter.size() : -1);
                    CatchupDetailsFragment catchupDetailsFragment = CatchupDetailsFragment.this;
                    HeaderItem headerItem = new HeaderItem(0L, CatchupDetailsFragment.this.getString(R.string.details_epg_related_reco_swimlanes));
                    Integer second = pair.getSecond();
                    Intrinsics.checkExpressionValueIsNotNull(second, "pair.second");
                    int iIntValue = second.intValue();
                    ArrayList arrayList = new ArrayList(iIntValue);
                    for (int i = 0; i < iIntValue; i++) {
                        arrayList.add(new LoadingWidget());
                    }
                    ArrayList arrayList2 = arrayList;
                    Constants constants = Constants.INSTANCE;
                    Context context2 = CatchupDetailsFragment.this.getContext();
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(catchupDetailsFragment, iMin, headerItem, arrayList2, null, null, constants.createPresenter(context2, null, Boolean.valueOf(CatchupDetailsFragment.this.getFromAdultBrowsing())), false, null, 216, null);
                    Single list = pair.getFirst().getItems().toList();
                    if (list == null || (singleSubscribeOn = list.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe2 = singleObserveOn.subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.2.2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<TVAiring> list2) {
                            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(CatchupDetailsFragment.this, iMin, new HeaderItem(0L, CatchupDetailsFragment.this.getString(R.string.details_epg_related_reco_swimlanes)), list2, null, null, null, false, null, 248, null);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.2.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            th.printStackTrace();
                        }
                    })) == null) {
                        return;
                    }
                    FragmentActivity activity = CatchupDetailsFragment.this.getActivity();
                    if (activity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity).getDisposable());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                th.printStackTrace();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(PortalApplication.getSi…race()\n                })");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
        PortalApplication.Companion companion2 = PortalApplication.INSTANCE;
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        LiveTVService liveTVService2 = companion2.getSingleton(context2).getBOService().getLiveTVService();
        if (liveTVService2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
        Disposable disposableSubscribe2 = MegacableLiveTVService.getRecommendations$default((MegacableLiveTVService) liveTVService2, "EPG_LIVE_Perso", null, this.fromAdultBrowsing, 2, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.4
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<TVAiring>, Integer>> apply(final ItemCollection<TVAiring> col) {
                Single<R> map;
                Intrinsics.checkParameterIsNotNull(col, "col");
                Single<Integer> itemsCount = col.getItemsCount();
                return (itemsCount == null || (map = itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.4.1
                    @Override // io.reactivex.functions.Function
                    public final Pair<ItemCollection<TVAiring>, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(col, it);
                    }
                })) == null) ? Single.just(new Pair(col, 0)) : map;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<TVAiring>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.5
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Pair<? extends ItemCollection<TVAiring>, ? extends Integer> pair) {
                accept2((Pair<? extends ItemCollection<TVAiring>, Integer>) pair);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(Pair<? extends ItemCollection<TVAiring>, Integer> pair) {
                Single<T> singleSubscribeOn;
                Single<T> singleObserveOn;
                Disposable disposableSubscribe3;
                if (Intrinsics.compare(pair.getSecond().intValue(), 0) > 0) {
                    ArrayObjectAdapter rowsAdapter = CatchupDetailsFragment.this.getRowsAdapter();
                    final int iMin = Math.min(4, rowsAdapter != null ? rowsAdapter.size() : -1);
                    CatchupDetailsFragment catchupDetailsFragment = CatchupDetailsFragment.this;
                    HeaderItem headerItem = new HeaderItem(0L, CatchupDetailsFragment.this.getString(R.string.details_epg_reco_swimlanes));
                    Integer second = pair.getSecond();
                    Intrinsics.checkExpressionValueIsNotNull(second, "pair.second");
                    int iIntValue = second.intValue();
                    ArrayList arrayList = new ArrayList(iIntValue);
                    for (int i = 0; i < iIntValue; i++) {
                        arrayList.add(new LoadingWidget());
                    }
                    ArrayList arrayList2 = arrayList;
                    Constants constants = Constants.INSTANCE;
                    Context context3 = CatchupDetailsFragment.this.getContext();
                    if (context3 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(catchupDetailsFragment, iMin, headerItem, arrayList2, null, null, constants.createPresenter(context3, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), Boolean.valueOf(CatchupDetailsFragment.this.getFromAdultBrowsing())), false, null, 216, null);
                    Single list = pair.getFirst().getItems().toList();
                    if (list == null || (singleSubscribeOn = list.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe3 = singleObserveOn.subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.5.2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<TVAiring> list2) {
                            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(CatchupDetailsFragment.this, iMin, new HeaderItem(0L, CatchupDetailsFragment.this.getString(R.string.details_epg_reco_swimlanes)), list2, null, null, null, false, null, 248, null);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.5.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            th.printStackTrace();
                        }
                    })) == null) {
                        return;
                    }
                    FragmentActivity activity2 = CatchupDetailsFragment.this.getActivity();
                    if (activity2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe3, ((BaseFragmentActivity) activity2).getDisposable());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment.createRecommendationsRows.6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                th.printStackTrace();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe2, "(PortalApplication.getSi…race()\n                })");
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity2).getDisposable());
    }

    private final void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    /* JADX INFO: compiled from: CatchupDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            String action;
            if (item instanceof TVAiring) {
                StringBuilder sb = new StringBuilder();
                sb.append("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=");
                TVAiring tVAiring = (TVAiring) item;
                sb.append(tVAiring.getChannel().getId());
                sb.append("&start=");
                sb.append(tVAiring.getStartDate().getTime());
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
                FragmentActivity activity = CatchupDetailsFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                activity.startActivity(intent);
                return;
            }
            if (item instanceof HomeWidget) {
                HomeWidget homeWidget = (HomeWidget) item;
                Intent intent2 = homeWidget.getIntent();
                if (intent2 == null || (action = intent2.getAction()) == null) {
                    return;
                }
                if (StringsKt.startsWith$default(action, "com.dotscreen.megacable.portal", false, 2, (Object) null)) {
                    Context context = CatchupDetailsFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    LocalBroadcastManager.getInstance(context).sendBroadcast(homeWidget.getIntent());
                    return;
                }
                FragmentActivity activity2 = CatchupDetailsFragment.this.getActivity();
                if (activity2 != null) {
                    activity2.startActivity(homeWidget.getIntent());
                    return;
                }
                return;
            }
            if (item instanceof BaseWidget) {
                BaseWidget baseWidget = (BaseWidget) item;
                if (baseWidget.getIntent() != null) {
                    CatchupDetailsFragment.this.startActivity(baseWidget.getIntent());
                    return;
                }
                return;
            }
            if (item instanceof Movie) {
                CatchupDetailsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + ((Movie) item).getId())));
            }
        }
    }

    /* JADX INFO: compiled from: CatchupDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        }

        public ItemViewSelectedListener() {
        }
    }

    /* JADX INFO: compiled from: CatchupDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B!\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$ActionListRow;", "Landroidx/leanback/widget/ListRow;", "header", "Landroidx/leanback/widget/HeaderItem;", "adapter", "Landroidx/leanback/widget/ObjectAdapter;", "(Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment;Landroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "id", "", "(Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment;JLandroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "(Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment;Landroidx/leanback/widget/ObjectAdapter;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ActionListRow extends ListRow {
        final /* synthetic */ CatchupDetailsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(CatchupDetailsFragment catchupDetailsFragment, HeaderItem headerItem, ObjectAdapter adapter) {
            super(headerItem, adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = catchupDetailsFragment;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(CatchupDetailsFragment catchupDetailsFragment, long j, HeaderItem headerItem, ObjectAdapter adapter) {
            super(j, headerItem, adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = catchupDetailsFragment;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(CatchupDetailsFragment catchupDetailsFragment, ObjectAdapter adapter) {
            super(adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = catchupDetailsFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CatchupDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002*\u0001\t\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010H\u0016¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$MySelector;", "Landroidx/leanback/widget/PresenterSelector;", "(Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment;)V", "actionListRowPresenter", "Landroidx/leanback/widget/ListRowPresenter;", "listRowPresenter", "synopsisView", "Lcom/dotscreen/portal/ui/screens/details/ProgramDetailsView;", "viewPresenter", "com/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$MySelector$viewPresenter$1", "Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$MySelector$viewPresenter$1;", "getPresenter", "Landroidx/leanback/widget/Presenter;", "item", "", "getPresenters", "", "()[Landroidx/leanback/widget/Presenter;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    final class MySelector extends PresenterSelector {
        private final ListRowPresenter actionListRowPresenter;
        private final ListRowPresenter listRowPresenter;
        private ProgramDetailsView synopsisView;
        private final CatchupDetailsFragment$MySelector$viewPresenter$1 viewPresenter;

        public MySelector() {
            Context context = CatchupDetailsFragment.this.getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            this.listRowPresenter = new RoundListRowPresenter(context, 4, false, 4, null);
            final int i = 4;
            this.actionListRowPresenter = new ListRowPresenter(i) { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment$MySelector$actionListRowPresenter$1
                @Override // androidx.leanback.widget.ListRowPresenter, androidx.leanback.widget.RowPresenter
                protected void onSelectLevelChanged(RowPresenter.ViewHolder holder) {
                    if (CatchupDetailsFragment.this.getContext() == null) {
                        return;
                    }
                    float fraction = CatchupDetailsFragment.this.getResources().getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1);
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
            this.viewPresenter = new CatchupDetailsFragment$MySelector$viewPresenter$1(this, R.layout.row_program_details);
            this.listRowPresenter.setShadowEnabled(true);
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
    }

    /* JADX INFO: compiled from: CatchupDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$ActionBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/portal/ui/screens/details/CatchupDetailsFragment;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class ActionBroadcastReceiver extends BroadcastReceiver {
        public ActionBroadcastReceiver() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            String action;
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            switch (action.hashCode()) {
                case -1149279759:
                    if (action.equals(DetailsActionsKt.ACTION_AUDIO)) {
                        ArrayList arrayList = new ArrayList();
                        String[] audioLanguages = CatchupDetailsFragment.this.getAudioLanguages();
                        if (audioLanguages != null) {
                            for (String str : audioLanguages) {
                                if (context == null) {
                                    Intrinsics.throwNpe();
                                }
                                String languageName = PortalUtils.getLanguageName(context, str);
                                Intrinsics.checkExpressionValueIsNotNull(languageName, "PortalUtils.getLanguageName(context!!, it)");
                                arrayList.add(languageName);
                            }
                        }
                        FragmentActivity activity = CatchupDetailsFragment.this.getActivity();
                        if (activity == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
                        CheckListDialog.Builder itemList = new CheckListDialog.Builder(activity).setTitle(R.string.dialog_select_audio_track_title).setItemList(arrayList);
                        String[] audioLanguages2 = CatchupDetailsFragment.this.getAudioLanguages();
                        itemList.setSelectedItem(Math.max(0, audioLanguages2 != null ? ArraysKt.indexOf(audioLanguages2, CatchupDetailsFragment.this.getSelectedAudioLanguage()) : 0)).setOnItemClickedListener(new CheckListDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$1
                            @Override // com.dotscreen.tv.ui.dialog.CheckListDialog.OnItemClickedListener
                            public void onItemClicked(CheckListDialog dialog, int itemIndex) {
                                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                                CatchupDetailsFragment catchupDetailsFragment = CatchupDetailsFragment.this;
                                String[] audioLanguages3 = CatchupDetailsFragment.this.getAudioLanguages();
                                catchupDetailsFragment.setSelectedAudioLanguage(audioLanguages3 != null ? audioLanguages3[itemIndex] : null);
                            }
                        }).create().show();
                    }
                    break;
                case -264272073:
                    if (action.equals(DetailsActionsKt.ACTION_PLAYBACK_CATCHUP)) {
                        FragmentActivity activity2 = CatchupDetailsFragment.this.getActivity();
                        if (activity2 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(activity2, "activity!!");
                        FragmentActivity fragmentActivity = activity2;
                        CatchupDetailsViewModel.Result detailsResult = CatchupDetailsFragment.this.getDetailsResult();
                        if (detailsResult == null) {
                            Intrinsics.throwNpe();
                        }
                        Media media = detailsResult.getMedia();
                        CatchupDetailsViewModel.Result detailsResult2 = CatchupDetailsFragment.this.getDetailsResult();
                        if (detailsResult2 == null) {
                            Intrinsics.throwNpe();
                        }
                        PlaybackActivityKt.displayResumePopupOrStartVODPlayback$default(fragmentActivity, media, detailsResult2.getRight(), CatchupDetailsFragment.this.getSelectedAudioLanguage(), CatchupDetailsFragment.this.getSelectedSubtitlesLanguage(), false, 32, null);
                    }
                    break;
                case 1573285878:
                    if (action.equals(DetailsActionsKt.ACTION_SUBTITLES)) {
                        ArrayList arrayList2 = new ArrayList();
                        String string = CatchupDetailsFragment.this.getString(R.string.dialog_select_caption_none);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.dialog_select_caption_none)");
                        arrayList2.add(string);
                        String[] subtitlesLanguages = CatchupDetailsFragment.this.getSubtitlesLanguages();
                        if (subtitlesLanguages != null) {
                            for (String str2 : subtitlesLanguages) {
                                if (context == null) {
                                    Intrinsics.throwNpe();
                                }
                                String languageName2 = PortalUtils.getLanguageName(context, str2);
                                Intrinsics.checkExpressionValueIsNotNull(languageName2, "PortalUtils.getLanguageName(context!!, it)");
                                arrayList2.add(languageName2);
                            }
                        }
                        FragmentActivity activity3 = CatchupDetailsFragment.this.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(activity3, "activity!!");
                        CheckListDialog.Builder itemList2 = new CheckListDialog.Builder(activity3).setTitle(R.string.dialog_select_caption_track_title).setItemList(arrayList2);
                        String[] subtitlesLanguages2 = CatchupDetailsFragment.this.getSubtitlesLanguages();
                        itemList2.setSelectedItem(Math.max(0, (subtitlesLanguages2 != null ? ArraysKt.indexOf(subtitlesLanguages2, CatchupDetailsFragment.this.getSelectedSubtitlesLanguage()) : -1) + 1)).setOnItemClickedListener(new CheckListDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$2
                            @Override // com.dotscreen.tv.ui.dialog.CheckListDialog.OnItemClickedListener
                            public void onItemClicked(CheckListDialog dialog, int itemIndex) {
                                String[] subtitlesLanguages3;
                                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                                CatchupDetailsFragment catchupDetailsFragment = CatchupDetailsFragment.this;
                                String str3 = null;
                                if (itemIndex != 0 && (subtitlesLanguages3 = CatchupDetailsFragment.this.getSubtitlesLanguages()) != null) {
                                    str3 = subtitlesLanguages3[itemIndex - 1];
                                }
                                catchupDetailsFragment.setSelectedSubtitlesLanguage(str3);
                            }
                        }).create().show();
                    }
                    break;
                case 2138790265:
                    if (action.equals(DetailsActionsKt.ACTION_SYNOPSIS)) {
                        FragmentActivity activity4 = CatchupDetailsFragment.this.getActivity();
                        if (activity4 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(activity4, "activity!!");
                        CastingDialog.Builder builder = new CastingDialog.Builder(activity4);
                        CatchupDetailsViewModel.Result detailsResult3 = CatchupDetailsFragment.this.getDetailsResult();
                        builder.setContent(detailsResult3 != null ? detailsResult3.getMedia() : null).create().show();
                    }
                    break;
            }
        }
    }
}
