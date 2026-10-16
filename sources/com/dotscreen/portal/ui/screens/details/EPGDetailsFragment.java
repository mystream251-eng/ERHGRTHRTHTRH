package com.dotscreen.portal.ui.screens.details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.tv.TvTrackInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DiffCallback;
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
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.model.TVRecordingStatus;
import com.dotscreen.bokit.services.LiveTVService;
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
import com.dotscreen.portal.ui.RoundListRowPresenter;
import com.dotscreen.portal.ui.ViewRow;
import com.dotscreen.portal.ui.cards.DetailsActionCardPresenter;
import com.dotscreen.portal.ui.cards.HomePresenterSelector;
import com.dotscreen.portal.ui.screens.BaseDetailsFragment;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.ISwimlanesFragment;
import com.dotscreen.portal.utils.MegaNotificationPublisher;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: EPGDetailsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u000e\u0018\u00002\u00020\u00012\u00020\u0002:\u0005RSTUVB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010C\u001a\u00020DH\u0002J\b\u0010E\u001a\u00020DH\u0002J\b\u0010F\u001a\u00020DH\u0002J\b\u0010G\u001a\u00020$H\u0014J\b\u0010H\u001a\u00020$H\u0014J\b\u0010I\u001a\u00020$H\u0002J\b\u0010J\u001a\u00020DH\u0002J\u0012\u0010K\u001a\u00020D2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\b\u0010N\u001a\u00020DH\u0016J\b\u0010O\u001a\u00020DH\u0016J\b\u0010P\u001a\u00020DH\u0002J\b\u0010Q\u001a\u00020DH\u0002R\u001c\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0,X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0010\u00100\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00101\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00106\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\b\"\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\b\"\u0004\b<\u00109R\u001a\u0010=\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0010\u0010B\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment;", "Lcom/dotscreen/portal/ui/screens/BaseDetailsFragment;", "Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "actionBroadcastReceiver", "Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment$ActionBroadcastReceiver;", "actionsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "actionsDiffCallback", "com/dotscreen/portal/ui/screens/details/EPGDetailsFragment$actionsDiffCallback$1", "Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment$actionsDiffCallback$1;", "audioTracks", "", "Landroid/media/tv/TvTrackInfo;", "getAudioTracks", "()Ljava/util/List;", "setAudioTracks", "(Ljava/util/List;)V", "captionTracks", "getCaptionTracks", "setCaptionTracks", "defaultSelectedAction", "", "value", "Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Result;", "detailsResult", "getDetailsResult", "()Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Result;", "setDetailsResult", "(Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Result;)V", "fromAdultBrowsing", "", "getFromAdultBrowsing", "()Z", "setFromAdultBrowsing", "(Z)V", "isLive", "isReminder", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "nextAiringsListRow", "rowsAdapter", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "selectedAudioTrackId", "getSelectedAudioTrackId", "setSelectedAudioTrackId", "(Ljava/lang/String;)V", "selectedCaptionTrackId", "getSelectedCaptionTrackId", "setSelectedCaptionTrackId", "startY", "getStartY", "()I", "setStartY", "(I)V", "topMoviesListRow", "createOrUpdateActionsRow", "", "createRecommendationsRows", "createRows", "hasMultipleAudios", "hasMultipleSubtitles", "hasReminder", "nextAirings", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "setupEventListeners", "setupUIElements", "ActionBroadcastReceiver", "ActionListRow", "ItemViewClickedListener", "ItemViewSelectedListener", "MySelector", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class EPGDetailsFragment extends BaseDetailsFragment implements ISwimlanesFragment {
    private HashMap _$_findViewCache;
    private ActionBroadcastReceiver actionBroadcastReceiver;
    private ArrayObjectAdapter actionsAdapter;
    private List<TvTrackInfo> audioTracks;
    private List<TvTrackInfo> captionTracks;
    private int defaultSelectedAction;
    private EPGDetailsViewModel.Result detailsResult;
    private boolean fromAdultBrowsing;
    private boolean isLive;
    private boolean isReminder;
    private ListRow nextAiringsListRow;
    private ArrayObjectAdapter rowsAdapter;
    private String selectedAudioTrackId;
    private String selectedCaptionTrackId;
    private int startY;
    private ListRow topMoviesListRow;
    private final EPGDetailsFragment$actionsDiffCallback$1 actionsDiffCallback = new DiffCallback<HomeWidget>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$actionsDiffCallback$1
        @Override // androidx.leanback.widget.DiffCallback
        public boolean areItemsTheSame(HomeWidget oldItem, HomeWidget newItem) {
            Intrinsics.checkParameterIsNotNull(oldItem, "oldItem");
            Intrinsics.checkParameterIsNotNull(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getTitle(), newItem.getTitle());
        }

        @Override // androidx.leanback.widget.DiffCallback
        public boolean areContentsTheSame(HomeWidget oldItem, HomeWidget newItem) {
            Intrinsics.checkParameterIsNotNull(oldItem, "oldItem");
            Intrinsics.checkParameterIsNotNull(newItem, "newItem");
            return oldItem.getIcon() == newItem.getIcon();
        }
    };
    private final String TAG = EPGDetailsFragment.class.getSimpleName();
    private final SparseArray<ListRow> listRows = new SparseArray<>();

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

    public final List<TvTrackInfo> getAudioTracks() {
        return this.audioTracks;
    }

    public final void setAudioTracks(List<TvTrackInfo> list) {
        this.audioTracks = list;
    }

    public final String getSelectedAudioTrackId() {
        return this.selectedAudioTrackId;
    }

    public final void setSelectedAudioTrackId(String str) {
        this.selectedAudioTrackId = str;
    }

    public final List<TvTrackInfo> getCaptionTracks() {
        return this.captionTracks;
    }

    public final void setCaptionTracks(List<TvTrackInfo> list) {
        this.captionTracks = list;
    }

    public final String getSelectedCaptionTrackId() {
        return this.selectedCaptionTrackId;
    }

    public final void setSelectedCaptionTrackId(String str) {
        this.selectedCaptionTrackId = str;
    }

    public final boolean getFromAdultBrowsing() {
        return this.fromAdultBrowsing;
    }

    public final void setFromAdultBrowsing(boolean z) {
        this.fromAdultBrowsing = z;
    }

    public final EPGDetailsViewModel.Result getDetailsResult() {
        return this.detailsResult;
    }

    public final void setDetailsResult(EPGDetailsViewModel.Result result) {
        this.detailsResult = result;
        if (result == null) {
            Intrinsics.throwNpe();
        }
        this.isLive = result.getAiring().isLive();
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        UserService userService = companion.getSingleton(context).getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        Disposable disposableSubscribe = ((MegacableUserService) userService).getReminderString().map((Function) new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$detailsResult$1
            @Override // io.reactivex.functions.Function
            public final String apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                long time = new Date().getTime();
                try {
                    JSONArray jSONArray = new JSONArray(it);
                    JSONArray jSONArray2 = new JSONArray(it);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        Object obj = jSONArray.get(i);
                        if (obj == null) {
                            throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                        }
                        JSONObject jSONObject = (JSONObject) obj;
                        long j = jSONObject.getLong("startDate") * ((long) 1000);
                        if (j < time) {
                            MegaNotificationPublisher.Companion companion2 = MegaNotificationPublisher.INSTANCE;
                            Context context2 = this.this$0.getContext();
                            if (context2 == null) {
                                Intrinsics.throwNpe();
                            }
                            Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                            String string = jSONObject.getString("title");
                            Intrinsics.checkExpressionValueIsNotNull(string, "tempObject.getString(\"title\")");
                            companion2.removeNotification(context2, string, j);
                            jSONArray2.remove(i);
                        }
                    }
                    if (!Intrinsics.areEqual(jSONArray2.toString(), jSONArray.toString())) {
                        PortalApplication.Companion companion3 = PortalApplication.INSTANCE;
                        Context context3 = this.this$0.getContext();
                        if (context3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
                        UserService userService2 = companion3.getSingleton(context3).getBOService().getUserService();
                        if (userService2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                        }
                        String string2 = jSONArray2.toString();
                        Intrinsics.checkExpressionValueIsNotNull(string2, "_tempjson2.toString()");
                        ((MegacableUserService) userService2).updateReminder(string2).subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$detailsResult$1.1
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(JSONObject jSONObject2) {
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$detailsResult$1.2
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                            }
                        });
                    }
                    return jSONArray2.toString();
                } catch (Exception unused) {
                    return "[]";
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$detailsResult$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        Object obj = jSONArray.get(i);
                        if (obj == null) {
                            throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                        }
                        JSONObject jSONObject = (JSONObject) obj;
                        long j = jSONObject.getLong("startDate");
                        String string = jSONObject.getString("channelId");
                        EPGDetailsViewModel.Result detailsResult = this.this$0.getDetailsResult();
                        if (detailsResult == null) {
                            Intrinsics.throwNpe();
                        }
                        if (Intrinsics.areEqual(string, detailsResult.getAiring().getChannel().getId())) {
                            EPGDetailsViewModel.Result detailsResult2 = this.this$0.getDetailsResult();
                            if (detailsResult2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (j == detailsResult2.getAiring().getStartDate().getTime() / ((long) 1000)) {
                                this.this$0.isReminder = true;
                                this.this$0.createRows();
                                return;
                            }
                        }
                    }
                    this.this$0.createRows();
                } catch (Exception unused) {
                    this.this$0.createRows();
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$detailsResult$3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                this.this$0.createRows();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(PortalApplication.getSi…  }\n                    )");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUIElements();
        setupEventListeners();
    }

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onResume() {
        nextAirings();
        if (this.actionBroadcastReceiver == null) {
            this.actionBroadcastReceiver = new ActionBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(DetailsActionsKt.ACTION_SYNOPSIS);
            intentFilter.addAction(DetailsActionsKt.ACTION_AUDIO);
            intentFilter.addAction(DetailsActionsKt.ACTION_SUBTITLES);
            intentFilter.addAction(DetailsActionsKt.ACTION_PLAYBACK_CATCHUP);
            intentFilter.addAction(DetailsActionsKt.ACTION_PLAYBACK_STARTOVER);
            intentFilter.addAction(DetailsActionsKt.ACTION_PLAYBACK_RECORDING);
            intentFilter.addAction(DetailsActionsKt.ACTION_WATCH_CHANNEL);
            intentFilter.addAction(DetailsActionsKt.ACTION_DELETE_RECORDING);
            intentFilter.addAction(DetailsActionsKt.ACTION_FAVORITES_CHANNEL_ADD);
            intentFilter.addAction(DetailsActionsKt.ACTION_FAVORITES_CHANNEL_REMOVE);
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
        super.onResume();
    }

    @Override // androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
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
        DetailsActionCardPresenter detailsActionCardPresenter = new DetailsActionCardPresenter();
        setRowsAdapter(new ArrayObjectAdapter(new MySelector()));
        ArrayObjectAdapter rowsAdapter = getRowsAdapter();
        if (rowsAdapter != null) {
            rowsAdapter.add(new ViewRow());
        }
        this.actionsAdapter = new ArrayObjectAdapter(detailsActionCardPresenter);
        createOrUpdateActionsRow();
        ArrayObjectAdapter rowsAdapter2 = getRowsAdapter();
        if (rowsAdapter2 != null) {
            ArrayObjectAdapter arrayObjectAdapter = this.actionsAdapter;
            if (arrayObjectAdapter == null) {
                Intrinsics.throwNpe();
            }
            rowsAdapter2.add(new ActionListRow(this, null, arrayObjectAdapter));
        }
        nextAirings();
        createRecommendationsRows();
        setAdapter(getRowsAdapter());
        setSelectedPosition(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:32:0x0060  */
    /* JADX WARN: Code duplicated, block: B:34:0x0064  */
    /* JADX WARN: Code duplicated, block: B:36:0x0068  */
    /* JADX WARN: Code duplicated, block: B:39:0x0075  */
    /* JADX WARN: Code duplicated, block: B:41:0x0079  */
    /* JADX WARN: Code duplicated, block: B:44:0x0082  */
    public final void createOrUpdateActionsRow() {
        EPGDetailsViewModel.Result result;
        EPGDetailsViewModel.Result result2;
        ArrayList arrayList = new ArrayList();
        if (this.isLive) {
            EPGDetailsViewModel.Result result3 = this.detailsResult;
            if (result3 == null) {
                Intrinsics.throwNpe();
            }
            if (result3.getStartOverMedia() != null) {
                arrayList.add(DetailsActionsKt.ACTION_PLAYBACK_STARTOVER);
            }
        }
        EPGDetailsViewModel.Result result4 = this.detailsResult;
        if (result4 == null) {
            Intrinsics.throwNpe();
        }
        if (result4.getRecording() != null) {
            EPGDetailsViewModel.Result result5 = this.detailsResult;
            if (result5 == null) {
                Intrinsics.throwNpe();
            }
            TVRecording recording = result5.getRecording();
            if (recording == null) {
                Intrinsics.throwNpe();
            }
            if (recording.getStatus() != TVRecordingStatus.Done) {
                EPGDetailsViewModel.Result result6 = this.detailsResult;
                if (result6 == null) {
                    Intrinsics.throwNpe();
                }
                TVRecording recording2 = result6.getRecording();
                if (recording2 == null) {
                    Intrinsics.throwNpe();
                }
                if (recording2.getStatus() != TVRecordingStatus.Deleted) {
                    if (!this.isLive) {
                        result = this.detailsResult;
                        if (result == null) {
                            Intrinsics.throwNpe();
                        }
                        if (result.getAiring().isPast()) {
                            result2 = this.detailsResult;
                            if (result2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (result2.getCatchupMedia() != null) {
                                arrayList.add(DetailsActionsKt.ACTION_PLAYBACK_CATCHUP);
                            }
                        }
                    }
                    arrayList.add(DetailsActionsKt.ACTION_WATCH_CHANNEL);
                }
            }
            arrayList.add(DetailsActionsKt.ACTION_PLAYBACK_RECORDING);
        } else {
            if (!this.isLive) {
                result = this.detailsResult;
                if (result == null) {
                    Intrinsics.throwNpe();
                }
                if (result.getAiring().isPast()) {
                    result2 = this.detailsResult;
                    if (result2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (result2.getCatchupMedia() != null) {
                        arrayList.add(DetailsActionsKt.ACTION_PLAYBACK_CATCHUP);
                    }
                }
            }
            arrayList.add(DetailsActionsKt.ACTION_WATCH_CHANNEL);
        }
        EPGDetailsViewModel.Result result7 = this.detailsResult;
        if (result7 == null) {
            Intrinsics.throwNpe();
        }
        if (result7.getProgram().getSummary() != null) {
            arrayList.add(DetailsActionsKt.ACTION_SYNOPSIS);
        }
        EPGDetailsViewModel.Result result8 = this.detailsResult;
        if (result8 == null) {
            Intrinsics.throwNpe();
        }
        boolean zIsRecordable = result8.isRecordable();
        String str = DetailsActionsKt.ACTION_RECORDING_CANCEL;
        if (zIsRecordable) {
            EPGDetailsViewModel.Result result9 = this.detailsResult;
            if (result9 == null) {
                Intrinsics.throwNpe();
            }
            if (result9.getRecording() != null) {
                arrayList.add(DetailsActionsKt.ACTION_RECORDING_CANCEL);
            } else {
                arrayList.add(DetailsActionsKt.ACTION_RECORDING_SCHEDULE);
            }
        } else {
            EPGDetailsViewModel.Result result10 = this.detailsResult;
            if (result10 == null) {
                Intrinsics.throwNpe();
            }
            if (result10.getRecording() != null) {
                EPGDetailsViewModel.Result result11 = this.detailsResult;
                if (result11 == null) {
                    Intrinsics.throwNpe();
                }
                if (result11.getAiring().isPast()) {
                    str = DetailsActionsKt.ACTION_DELETE_RECORDING;
                }
                arrayList.add(str);
            }
        }
        EPGDetailsViewModel.Result result12 = this.detailsResult;
        if (result12 == null) {
            Intrinsics.throwNpe();
        }
        if (result12.getChannelIsInFavorites()) {
            arrayList.add(DetailsActionsKt.ACTION_FAVORITES_CHANNEL_REMOVE);
        } else {
            arrayList.add(DetailsActionsKt.ACTION_FAVORITES_CHANNEL_ADD);
        }
        if (hasReminder()) {
            if (!this.isReminder) {
                arrayList.add(DetailsActionsKt.ACTION_REMINDER_ADD);
            } else {
                arrayList.add(DetailsActionsKt.ACTION_REMINDER_REMOVE);
            }
        }
        if (hasMultipleAudios()) {
            arrayList.add(DetailsActionsKt.ACTION_AUDIO);
        }
        if (hasMultipleSubtitles()) {
            arrayList.add(DetailsActionsKt.ACTION_SUBTITLES);
        }
        ArrayObjectAdapter arrayObjectAdapter = this.actionsAdapter;
        if (arrayObjectAdapter != null) {
            ArrayList<String> arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            for (String str2 : arrayList2) {
                HomeWidget.Companion companion = HomeWidget.INSTANCE;
                Context context = getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                arrayList3.add(companion.getAction(context, str2));
            }
            arrayObjectAdapter.setItems(arrayList3, this.actionsDiffCallback);
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseDetailsFragment
    protected boolean hasMultipleAudios() {
        List<TvTrackInfo> list = this.audioTracks;
        return (list != null ? list.size() : 0) > 1;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseDetailsFragment
    protected boolean hasMultipleSubtitles() {
        List<TvTrackInfo> list = this.captionTracks;
        return (list != null ? list.size() : 0) > 0;
    }

    private final boolean hasReminder() {
        TVAiring airing;
        Date startDate;
        EPGDetailsViewModel.Result result = this.detailsResult;
        if (result != null) {
            if (result == null) {
                Intrinsics.throwNpe();
            }
            if (!result.getAiring().getChannel().getGenres().contains("ADULTOS")) {
                Constants constants = Constants.INSTANCE;
                EPGDetailsViewModel.Result result2 = this.detailsResult;
                if (result2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!constants.itemIsAdult(result2.getAiring())) {
                    EPGDetailsViewModel.Result result3 = this.detailsResult;
                    if (((result3 == null || (airing = result3.getAiring()) == null || (startDate = airing.getStartDate()) == null) ? 0L : startDate.getTime()) > new Date().getTime()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int getStartY() {
        return this.startY;
    }

    public final void setStartY(int i) {
        this.startY = i;
    }

    private final void nextAirings() {
        TVAiring airing;
        Channel channel;
        Single<List<TVAiring>> list;
        Single<List<TVAiring>> singleSubscribeOn;
        Single<List<TVAiring>> singleObserveOn;
        Disposable disposableSubscribe;
        TVAiring airing2;
        TVAiring airing3;
        TVAiring airing4;
        ArrayObjectAdapter rowsAdapter = getRowsAdapter();
        int iMin = Math.min(2, rowsAdapter != null ? rowsAdapter.size() : -1);
        HeaderItem headerItem = new HeaderItem(0L, getString(R.string.details_epg_next_swimlanes));
        ArrayList arrayList = new ArrayList(1);
        for (int i = 0; i < 1; i++) {
            arrayList.add(new LoadingWidget());
        }
        ArrayList arrayList2 = arrayList;
        Constants constants = Constants.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(this, iMin, headerItem, arrayList2, null, null, Constants.createPresenter$default(constants, context, null, null, 4, null), false, null, 216, null);
        String tag = getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append(" Date : ");
        EPGDetailsViewModel.Result result = this.detailsResult;
        Date endDate = null;
        sb.append((result == null || (airing4 = result.getAiring()) == null) ? null : airing4.getEndDate());
        Log.v(tag, sb.toString());
        EPGDetailsViewModel.Result result2 = this.detailsResult;
        if (result2 == null || (airing = result2.getAiring()) == null || (channel = airing.getChannel()) == null) {
            return;
        }
        EPGDetailsViewModel.Result result3 = this.detailsResult;
        Date endDate2 = (result3 == null || (airing3 = result3.getAiring()) == null) ? null : airing3.getEndDate();
        if (endDate2 == null) {
            Intrinsics.throwNpe();
        }
        EPGDetailsViewModel.Result result4 = this.detailsResult;
        if (result4 != null && (airing2 = result4.getAiring()) != null) {
            endDate = airing2.getEndDate();
        }
        if (endDate == null) {
            Intrinsics.throwNpe();
        }
        Observable<TVAiring> observableAirings = channel.airings(endDate2, new Date(endDate.getTime() + ((long) 21600000)));
        if (observableAirings == null || (list = observableAirings.toList()) == null || (singleSubscribeOn = list.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe = singleObserveOn.subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.nextAirings.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(List<TVAiring> list2) {
                Log.d(EPGDetailsFragment.this.getTAG(), "airings = " + list2);
                EPGDetailsFragment ePGDetailsFragment = EPGDetailsFragment.this;
                ArrayObjectAdapter rowsAdapter2 = ePGDetailsFragment.getRowsAdapter();
                ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(ePGDetailsFragment, Math.min(2, rowsAdapter2 != null ? rowsAdapter2.size() : -1), new HeaderItem(0L, EPGDetailsFragment.this.getString(R.string.details_epg_next_swimlanes)), list2, null, null, new HomePresenterSelector(null, null, null, null, null, null, null, 127, null), false, null, 216, null);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.nextAirings.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                th.printStackTrace();
            }
        })) == null) {
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
    }

    private final void createRecommendationsRows() {
        EPGDetailsViewModel.Result result = this.detailsResult;
        if (result == null) {
            Intrinsics.throwNpe();
        }
        Disposable disposableSubscribe = result.getProgram().getOffers().flatMapMaybe((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.1
            @Override // io.reactivex.functions.Function
            public final Maybe<String> apply(Map<String, ? extends Object> offer) {
                Intrinsics.checkParameterIsNotNull(offer, "offer");
                Log.v(EPGDetailsFragment.this.getTAG(), "CreateRecommendationRows => Offers of the attached program => " + offer);
                Object obj = offer.get("rightv");
                if (!(obj instanceof Map)) {
                    obj = null;
                }
                Map map = (Map) obj;
                if (map != null && map.get("program_external_id") != null) {
                    Object obj2 = map.get("program_external_id");
                    if (obj2 != null) {
                        return Maybe.just((String) obj2);
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                return Maybe.empty();
            }
        }).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.2
            @Override // io.reactivex.functions.Function
            public final Maybe<ItemCollection<TVAiring>> apply(String contentId) {
                Intrinsics.checkParameterIsNotNull(contentId, "contentId");
                PortalApplication.Companion companion = PortalApplication.INSTANCE;
                Context context = EPGDetailsFragment.this.getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                LiveTVService liveTVService = companion.getSingleton(context).getBOService().getLiveTVService();
                if (liveTVService != null) {
                    return ((MegacableLiveTVService) liveTVService).getRecommendations("EPG_Related", contentId, EPGDetailsFragment.this.getFromAdultBrowsing()).toMaybe();
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.3
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<TVAiring>, Integer>> apply(final ItemCollection<TVAiring> col) {
                Single<R> map;
                Intrinsics.checkParameterIsNotNull(col, "col");
                Single<Integer> itemsCount = col.getItemsCount();
                return (itemsCount == null || (map = itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.3.1
                    @Override // io.reactivex.functions.Function
                    public final Pair<ItemCollection<TVAiring>, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(col, it);
                    }
                })) == null) ? Single.just(new Pair(col, 0)) : map;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<TVAiring>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.4
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
                    ArrayObjectAdapter rowsAdapter = EPGDetailsFragment.this.getRowsAdapter();
                    final int iMin = Math.min(3, rowsAdapter != null ? rowsAdapter.size() : -1);
                    EPGDetailsFragment ePGDetailsFragment = EPGDetailsFragment.this;
                    HeaderItem headerItem = new HeaderItem(0L, EPGDetailsFragment.this.getString(R.string.details_epg_related_reco_swimlanes));
                    Integer second = pair.getSecond();
                    Intrinsics.checkExpressionValueIsNotNull(second, "pair.second");
                    int iIntValue = second.intValue();
                    ArrayList arrayList = new ArrayList(iIntValue);
                    for (int i = 0; i < iIntValue; i++) {
                        arrayList.add(new LoadingWidget());
                    }
                    ArrayList arrayList2 = arrayList;
                    Constants constants = Constants.INSTANCE;
                    Context context = EPGDetailsFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(ePGDetailsFragment, iMin, headerItem, arrayList2, null, null, Constants.createPresenter$default(constants, context, null, null, 4, null), false, null, 216, null);
                    Single list = pair.getFirst().getItems().toList();
                    if (list == null || (singleSubscribeOn = list.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe2 = singleObserveOn.subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.4.2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<TVAiring> list2) {
                            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(EPGDetailsFragment.this, iMin, new HeaderItem(0L, EPGDetailsFragment.this.getString(R.string.details_epg_related_reco_swimlanes)), list2, null, null, null, false, null, 248, null);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.4.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            th.printStackTrace();
                        }
                    })) == null) {
                        return;
                    }
                    FragmentActivity activity = EPGDetailsFragment.this.getActivity();
                    if (activity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity).getDisposable());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                th.printStackTrace();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "detailsResult!!.program.…race()\n                })");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity).getDisposable());
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
        Disposable disposableSubscribe2 = MegacableLiveTVService.getRecommendations$default((MegacableLiveTVService) liveTVService, "EPG_LIVE_Perso", null, this.fromAdultBrowsing, 2, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.6
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<TVAiring>, Integer>> apply(final ItemCollection<TVAiring> col) {
                Single<R> map;
                Intrinsics.checkParameterIsNotNull(col, "col");
                Single<Integer> itemsCount = col.getItemsCount();
                return (itemsCount == null || (map = itemsCount.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.6.1
                    @Override // io.reactivex.functions.Function
                    public final Pair<ItemCollection<TVAiring>, Integer> apply(Integer it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(col, it);
                    }
                })) == null) ? Single.just(new Pair(col, 0)) : map;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<TVAiring>, ? extends Integer>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.7
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
                    ArrayObjectAdapter rowsAdapter = EPGDetailsFragment.this.getRowsAdapter();
                    final int iMin = Math.min(4, rowsAdapter != null ? rowsAdapter.size() : -1);
                    EPGDetailsFragment ePGDetailsFragment = EPGDetailsFragment.this;
                    HeaderItem headerItem = new HeaderItem(0L, EPGDetailsFragment.this.getString(R.string.details_epg_reco_swimlanes));
                    Integer second = pair.getSecond();
                    Intrinsics.checkExpressionValueIsNotNull(second, "pair.second");
                    int iIntValue = second.intValue();
                    ArrayList arrayList = new ArrayList(iIntValue);
                    for (int i = 0; i < iIntValue; i++) {
                        arrayList.add(new LoadingWidget());
                    }
                    ArrayList arrayList2 = arrayList;
                    Constants constants = Constants.INSTANCE;
                    Context context2 = EPGDetailsFragment.this.getContext();
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
                    ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(ePGDetailsFragment, iMin, headerItem, arrayList2, null, null, constants.createPresenter(context2, Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), Boolean.valueOf(EPGDetailsFragment.this.getFromAdultBrowsing())), false, null, 216, null);
                    Single list = pair.getFirst().getItems().toList();
                    if (list == null || (singleSubscribeOn = list.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe3 = singleObserveOn.subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.7.2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(List<TVAiring> list2) {
                            ISwimlanesFragment.DefaultImpls.createOrUpdateRow$default(EPGDetailsFragment.this, iMin, new HeaderItem(0L, EPGDetailsFragment.this.getString(R.string.details_epg_reco_swimlanes)), list2, null, null, null, false, null, 248, null);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.7.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            th.printStackTrace();
                        }
                    })) == null) {
                        return;
                    }
                    FragmentActivity activity2 = EPGDetailsFragment.this.getActivity();
                    if (activity2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe3, ((BaseFragmentActivity) activity2).getDisposable());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.createRecommendationsRows.8
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

    /* JADX INFO: compiled from: EPGDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment$ItemViewClickedListener;", "Landroidx/leanback/widget/OnItemViewClickedListener;", "(Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment;)V", "onItemClicked", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "registerClick", "Lcom/dotscreen/bokit/model/BrowsableItem;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        public ItemViewClickedListener() {
        }

        @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            String action;
            if (item instanceof BrowsableItem) {
                registerClick((BrowsableItem) item);
            }
            if (item instanceof TVAiring) {
                DSLog dSLog = DSLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("Clicked on the ");
                TVAiring tVAiring = (TVAiring) item;
                sb.append(tVAiring.getTitle());
                sb.append(" TVAiring item ");
                dSLog.a(sb.toString());
                EPGDetailsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + tVAiring.getChannel().getId() + "&start=" + tVAiring.getStartDate().getTime())));
                return;
            }
            if (item instanceof HomeWidget) {
                DSLog dSLog2 = DSLog.INSTANCE;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Clicked on the ");
                HomeWidget homeWidget = (HomeWidget) item;
                sb2.append(homeWidget.getTitle());
                sb2.append(" HomeWidget ");
                dSLog2.a(sb2.toString());
                Intent intent = homeWidget.getIntent();
                if (intent == null || (action = intent.getAction()) == null) {
                    return;
                }
                if (StringsKt.startsWith$default(action, "com.dotscreen.megacable.portal", false, 2, (Object) null)) {
                    Context context = EPGDetailsFragment.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    LocalBroadcastManager.getInstance(context).sendBroadcast(homeWidget.getIntent());
                    return;
                }
                FragmentActivity activity = EPGDetailsFragment.this.getActivity();
                if (activity != null) {
                    activity.startActivity(homeWidget.getIntent());
                    return;
                }
                return;
            }
            if (item instanceof BaseWidget) {
                BaseWidget baseWidget = (BaseWidget) item;
                if (baseWidget.getIntent() != null) {
                    DSLog.INSTANCE.a("Clicked on the " + baseWidget.getTitle() + " BaseWidget ");
                    EPGDetailsFragment.this.startActivity(baseWidget.getIntent());
                    return;
                }
                return;
            }
            if (item instanceof Movie) {
                DSLog dSLog3 = DSLog.INSTANCE;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Clicked on the ");
                Movie movie = (Movie) item;
                sb3.append(movie.getTitle());
                sb3.append(" BaseWidget ");
                dSLog3.a(sb3.toString());
                EPGDetailsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + movie.getId())));
            }
        }

        private final void registerClick(BrowsableItem item) {
            Context context = EPGDetailsFragment.this.getContext();
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
            MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, item, null, null, null, 14, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ItemViewClickedListener$registerClick$1
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ItemViewClickedListener$registerClick$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            });
        }
    }

    /* JADX INFO: compiled from: EPGDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment$ItemViewSelectedListener;", "Landroidx/leanback/widget/OnItemViewSelectedListener;", "(Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment;)V", "onItemSelected", "", "itemViewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "rowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "row", "Landroidx/leanback/widget/Row;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        }

        public ItemViewSelectedListener() {
        }
    }

    /* JADX INFO: compiled from: EPGDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B!\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment$ActionListRow;", "Landroidx/leanback/widget/ListRow;", "header", "Landroidx/leanback/widget/HeaderItem;", "adapter", "Landroidx/leanback/widget/ObjectAdapter;", "(Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment;Landroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "id", "", "(Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment;JLandroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V", "(Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment;Landroidx/leanback/widget/ObjectAdapter;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private final class ActionListRow extends ListRow {
        final /* synthetic */ EPGDetailsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(EPGDetailsFragment ePGDetailsFragment, HeaderItem headerItem, ObjectAdapter adapter) {
            super(headerItem, adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = ePGDetailsFragment;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(EPGDetailsFragment ePGDetailsFragment, long j, HeaderItem headerItem, ObjectAdapter adapter) {
            super(j, headerItem, adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = ePGDetailsFragment;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActionListRow(EPGDetailsFragment ePGDetailsFragment, ObjectAdapter adapter) {
            super(adapter);
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.this$0 = ePGDetailsFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: EPGDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002*\u0001\t\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010H\u0016¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment$MySelector;", "Landroidx/leanback/widget/PresenterSelector;", "(Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment;)V", "actionListRowPresenter", "Landroidx/leanback/widget/ListRowPresenter;", "listRowPresenter", "synopsisView", "Lcom/dotscreen/portal/ui/screens/details/ProgramDetailsView;", "viewPresenter", "com/dotscreen/portal/ui/screens/details/EPGDetailsFragment$MySelector$viewPresenter$1", "Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment$MySelector$viewPresenter$1;", "getPresenter", "Landroidx/leanback/widget/Presenter;", "item", "", "getPresenters", "", "()[Landroidx/leanback/widget/Presenter;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    final class MySelector extends PresenterSelector {
        private final ListRowPresenter actionListRowPresenter;
        private final ListRowPresenter listRowPresenter;
        private ProgramDetailsView synopsisView;
        private final EPGDetailsFragment$MySelector$viewPresenter$1 viewPresenter;

        public MySelector() {
            Context context = EPGDetailsFragment.this.getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            this.listRowPresenter = new RoundListRowPresenter(context, 4, false, 4, null);
            final int i = 4;
            this.actionListRowPresenter = new ListRowPresenter(i) { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$MySelector$actionListRowPresenter$1
                @Override // androidx.leanback.widget.Presenter
                public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item, List<Object> payloads) {
                    super.onBindViewHolder(viewHolder, item, payloads);
                }

                @Override // androidx.leanback.widget.ListRowPresenter, androidx.leanback.widget.RowPresenter
                protected void onSelectLevelChanged(RowPresenter.ViewHolder holder) {
                    if (EPGDetailsFragment.this.getContext() == null) {
                        return;
                    }
                    float fraction = EPGDetailsFragment.this.getResources().getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1);
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
            this.viewPresenter = new EPGDetailsFragment$MySelector$viewPresenter$1(this, R.layout.row_program_details);
            this.listRowPresenter.setShadowEnabled(true);
            this.listRowPresenter.enableChildRoundedCorners(true);
            this.listRowPresenter.setSelectEffectEnabled(true);
            this.actionListRowPresenter.setShadowEnabled(false);
            this.actionListRowPresenter.enableChildRoundedCorners(false);
            this.actionListRowPresenter.setSelectEffectEnabled(false);
        }

        @Override // androidx.leanback.widget.PresenterSelector
        public Presenter getPresenter(Object item) {
            Intrinsics.checkParameterIsNotNull(item, "item");
            if (item instanceof ActionListRow) {
                return this.actionListRowPresenter;
            }
            if (item instanceof ViewRow) {
                return this.viewPresenter;
            }
            return this.listRowPresenter;
        }

        @Override // androidx.leanback.widget.PresenterSelector
        public Presenter[] getPresenters() {
            return new Presenter[]{this.listRowPresenter, this.actionListRowPresenter, this.viewPresenter};
        }
    }

    /* JADX INFO: compiled from: EPGDetailsFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment$ActionBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/portal/ui/screens/details/EPGDetailsFragment;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class ActionBroadcastReceiver extends BroadcastReceiver {
        public ActionBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:173:0x04b6, code lost:
        
            if (r2.equals(com.dotscreen.portal.ui.screens.details.DetailsActionsKt.ACTION_DELETE_RECORDING) != false) goto L174;
         */
        /* JADX WARN: Code restructure failed: missing block: B:174:0x04b8, code lost:
        
            r2 = new com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$7(r20, r21);
            r1 = r20.this$0.getDetailsResult();
         */
        /* JADX WARN: Code restructure failed: missing block: B:175:0x04c3, code lost:
        
            if (r1 != null) goto L177;
         */
        /* JADX WARN: Code restructure failed: missing block: B:176:0x04c5, code lost:
        
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:177:0x04c8, code lost:
        
            r1 = r1.getRecording();
         */
        /* JADX WARN: Code restructure failed: missing block: B:178:0x04cc, code lost:
        
            if (r1 != null) goto L180;
         */
        /* JADX WARN: Code restructure failed: missing block: B:179:0x04ce, code lost:
        
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:181:0x04d7, code lost:
        
            if (r1.getStatus() != com.dotscreen.bokit.model.TVRecordingStatus.Done) goto L186;
         */
        /* JADX WARN: Code restructure failed: missing block: B:182:0x04d9, code lost:
        
            r3 = r20.this$0.getActivity();
         */
        /* JADX WARN: Code restructure failed: missing block: B:183:0x04e1, code lost:
        
            if (r3 != null) goto L185;
         */
        /* JADX WARN: Code restructure failed: missing block: B:184:0x04e3, code lost:
        
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:185:0x04e6, code lost:
        
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, "activity!!");
            new com.dotscreen.tv.ui.dialog.PortalDialog.Builder(r3).setTitle(com.dotscreen.megacable.portal.R.string.dialog_delete_recording_title).setMessage(com.dotscreen.megacable.portal.R.string.dialog_delete_recording_message).setPositiveButton(com.dotscreen.megacable.portal.R.string.dialog_recording_no, com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$1$20.INSTANCE).setNegativeButton(com.dotscreen.megacable.portal.R.string.dialog_recording_yes, new com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$8(r2)).create().show();
            r1 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:186:0x0528, code lost:
        
            r2.invoke2();
            r1 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:398:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:399:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0113, code lost:
        
            if (r2.equals(com.dotscreen.portal.ui.screens.details.DetailsActionsKt.ACTION_RECORDING_CANCEL) != false) goto L174;
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v88, types: [T, org.json.JSONObject] */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onReceive(final android.content.Context r21, android.content.Intent r22) throws org.json.JSONException {
            /*
                Method dump skipped, instruction units count: 2452
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment.ActionBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }
}
