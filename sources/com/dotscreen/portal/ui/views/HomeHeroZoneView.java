package com.dotscreen.portal.ui.views;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.tv.TvContract;
import android.media.tv.TvInputManager;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.CollectionGridPresenter;
import androidx.leanback.widget.CollectionGridView;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.TitleViewAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVAiringTimeOfDay;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.OptionalCompat;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.components.NotificationView;
import com.dotscreen.portal.model.ApplicationWidget;
import com.dotscreen.portal.model.EPGViewModel;
import com.dotscreen.portal.model.HomeWidget;
import com.dotscreen.portal.model.OptimizerEPGViewModel;
import com.dotscreen.portal.ui.cards.EmptyCardPresenter;
import com.dotscreen.portal.ui.cards.HeroZoneAppCardPresenter;
import com.dotscreen.portal.ui.cards.LiveCardPresenter;
import com.dotscreen.portal.ui.cards.LoadingCardPresenter;
import com.dotscreen.portal.ui.cards.RefreshCard;
import com.dotscreen.portal.ui.cards.VODItemCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.profile.PinCodeDialog;
import com.dotscreen.portal.utils.ApplicationsManager;
import com.dotscreen.portal.utils.DebouncedLifecycleObserver;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.providers.LastChannelsProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt;
import com.dotscreen.tvinput.NoChannelFoundException;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.sentry.Sentry;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNullPointerException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: HomeHeroZoneView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0091\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u001f\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B%\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010-\u001a\u00020.H\u0002J\u001c\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001002\u0006\u00102\u001a\u00020\tH\u0016J\b\u00103\u001a\u00020+H\u0016J\u0006\u00104\u001a\u00020.J\b\u00105\u001a\u00020.H\u0014J\b\u00106\u001a\u00020.H\u0014J\b\u00107\u001a\u00020.H\u0016J\b\u00108\u001a\u00020.H\u0002J\b\u00109\u001a\u00020.H\u0002R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\b\u0012\u00060\u0017R\u00020\u0018\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/dotscreen/portal/ui/views/HomeHeroZoneView;", "Landroid/widget/RelativeLayout;", "Landroidx/leanback/widget/TitleViewAdapter$Provider;", "Lcom/dotscreen/portal/ui/cards/RefreshCard;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "alreadyRefreshed", "", "blend", "epgHandler", "Landroid/os/Handler;", "epgLiveData", "Landroidx/lifecycle/LiveData;", "Lcom/dotscreen/portal/model/EPGViewModel$Data;", "Lcom/dotscreen/portal/model/EPGViewModel;", "epgViewModel", "gridDock", "Landroid/widget/FrameLayout;", "gridViewHolder", "Landroidx/leanback/widget/CollectionGridPresenter$ViewHolder;", "lifecycleObserver", "com/dotscreen/portal/ui/views/HomeHeroZoneView$lifecycleObserver$1", "Lcom/dotscreen/portal/ui/views/HomeHeroZoneView$lifecycleObserver$1;", "liveCardTimerDisposable", "Lio/reactivex/disposables/Disposable;", "localHandler", "notifView", "Lcom/dotscreen/portal/components/NotificationView;", "getNotifView", "()Lcom/dotscreen/portal/components/NotificationView;", "refreshLiveCardRunnable", "Ljava/lang/Runnable;", "titleViewAdapter", "Landroidx/leanback/widget/TitleViewAdapter;", "userStatusDisposable", "createGrid", "", "focusSearch", "Landroid/view/View;", "focused", "direction", "getTitleViewAdapter", "invalidateData", "onAttachedToWindow", "onDetachedFromWindow", "refresh", "refreshLiveCard", "updateRecommendations", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HomeHeroZoneView extends RelativeLayout implements TitleViewAdapter.Provider, RefreshCard {
    private final String TAG;
    private HashMap _$_findViewCache;
    private ArrayObjectAdapter adapter;
    private boolean alreadyRefreshed;
    private final String blend;
    private final Handler epgHandler;
    private LiveData<EPGViewModel.Data> epgLiveData;
    private final EPGViewModel epgViewModel;
    private final FrameLayout gridDock;
    private CollectionGridPresenter.ViewHolder gridViewHolder;
    private final HomeHeroZoneView$lifecycleObserver$1 lifecycleObserver;
    private Disposable liveCardTimerDisposable;
    private final Handler localHandler;
    private final NotificationView notifView;
    private final Runnable refreshLiveCardRunnable;
    private final TitleViewAdapter titleViewAdapter;
    private Disposable userStatusDisposable;

    public HomeHeroZoneView(Context context) {
        this(context, null, 0, 6, null);
    }

    public HomeHeroZoneView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
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
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeHeroZoneView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"updateEPGData", "", "invoke"}, k = 3, mv = {1, 1, 16})
    static final class C04431 extends Lambda implements Function0<Unit> {
        final /* synthetic */ PortalApplicationSingletons $app;
        final /* synthetic */ Ref.ObjectRef $lastWatchedChannelUri;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04431(Ref.ObjectRef objectRef, PortalApplicationSingletons portalApplicationSingletons) {
            super(0);
            this.$lastWatchedChannelUri = objectRef;
            this.$app = portalApplicationSingletons;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            Log.d(HomeHeroZoneView.this.TAG, "lastWatchedChannelUri = " + ((Uri) this.$lastWatchedChannelUri.element));
            HomeHeroZoneView.this.epgHandler.removeCallbacksAndMessages(null);
            ArrayObjectAdapter arrayObjectAdapter = HomeHeroZoneView.this.adapter;
            if ((arrayObjectAdapter == null || arrayObjectAdapter.size() != 0) && !this.$app.getDegradedMode()) {
                Context context = HomeHeroZoneView.this.getContext();
                if (context == null) {
                    throw new TypeCastException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                }
                Lifecycle lifecycle = ((FragmentActivity) context).getLifecycle();
                Intrinsics.checkExpressionValueIsNotNull(lifecycle, "(context as FragmentActivity).lifecycle");
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C01481(null), 2, null);
                }
            }
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: HomeHeroZoneView.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$1$1", f = "HomeHeroZoneView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01481(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01481 c01481 = C04431.this.new C01481(completion);
                c01481.p$ = (CoroutineScope) obj;
                return c01481;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws IOException {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Channel channel = null;
                final RightVChannel rightVChannel = (RightVChannel) null;
                Context context = HomeHeroZoneView.this.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                Cursor cursorQuery = context.getContentResolver().query((Uri) C04431.this.$lastWatchedChannelUri.element, com.google.android.media.tv.companionlibrary.model.Channel.PROJECTION, null, null, null);
                if (cursorQuery != null) {
                    Cursor cursor = cursorQuery;
                    Throwable th = (Throwable) null;
                    try {
                        Cursor cursor2 = cursor;
                        if (cursor2.moveToFirst()) {
                            com.google.android.media.tv.companionlibrary.model.Channel tifChannel = com.google.android.media.tv.companionlibrary.model.Channel.fromCursor(cursor2);
                            Log.d(HomeHeroZoneView.this.TAG, "tifChannel = " + tifChannel);
                            Intrinsics.checkExpressionValueIsNotNull(tifChannel, "tifChannel");
                            Context context2 = HomeHeroZoneView.this.getContext();
                            if (context2 == null) {
                                Intrinsics.throwNpe();
                            }
                            Context applicationContext = context2.getApplicationContext();
                            if (applicationContext == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                            }
                            Channel optimizerChannel = TIFCompanionLibraryExtensionKt.getOptimizerChannel(tifChannel, ((PortalApplication) applicationContext).getBOService());
                            if (optimizerChannel instanceof RightVChannel) {
                                channel = optimizerChannel;
                            }
                            rightVChannel = (RightVChannel) channel;
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor, th);
                    } catch (Throwable th2) {
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(cursor, th2);
                            throw th3;
                        }
                    }
                }
                Log.d(HomeHeroZoneView.this.TAG, "watchedChannel = " + rightVChannel);
                if (rightVChannel != null) {
                    Disposable disposable = HomeHeroZoneView.this.liveCardTimerDisposable;
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    HomeHeroZoneView.this.liveCardTimerDisposable = Single.just(rightVChannel).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$1$1$2$1
                        @Override // io.reactivex.functions.Function
                        public final Single<OptionalCompat<TVAiring>> apply(RightVChannel it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.airing(TVAiringTimeOfDay.Now).map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$1$1$2$1.1
                                @Override // io.reactivex.functions.Function
                                public final OptionalCompat<TVAiring> apply(TVAiring it2) {
                                    Intrinsics.checkParameterIsNotNull(it2, "it2");
                                    return OptionalCompat.of(it2);
                                }
                            }).toSingle(OptionalCompat.empty());
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<OptionalCompat<TVAiring>>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$1$1$invokeSuspend$$inlined$let$lambda$1
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(OptionalCompat<TVAiring> newAiring) {
                            ArrayObjectAdapter arrayObjectAdapter;
                            ArrayObjectAdapter arrayObjectAdapter2;
                            Intrinsics.checkExpressionValueIsNotNull(newAiring, "newAiring");
                            if (newAiring.isPresent()) {
                                ArrayObjectAdapter arrayObjectAdapter3 = HomeHeroZoneView.this.adapter;
                                if ((arrayObjectAdapter3 == null || arrayObjectAdapter3.size() != 0) && (arrayObjectAdapter2 = HomeHeroZoneView.this.adapter) != null) {
                                    arrayObjectAdapter2.replace(0, newAiring.get());
                                }
                            } else {
                                ArrayObjectAdapter arrayObjectAdapter4 = HomeHeroZoneView.this.adapter;
                                if ((arrayObjectAdapter4 == null || arrayObjectAdapter4.size() != 0) && (arrayObjectAdapter = HomeHeroZoneView.this.adapter) != null) {
                                    arrayObjectAdapter.replace(0, new Pair((Uri) HomeHeroZoneView.C04431.this.$lastWatchedChannelUri.element, rightVChannel));
                                }
                            }
                            HomeHeroZoneView.this.epgHandler.postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$1$1$invokeSuspend$$inlined$let$lambda$1.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    HomeHeroZoneView.C04431.this.invoke2();
                                }
                            }, 5000L);
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$1$1$2$3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th4) {
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.dotscreen.portal.ui.views.HomeHeroZoneView$lifecycleObserver$1] */
    public HomeHeroZoneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = HomeHeroZoneView.class.getSimpleName();
        this.blend = "Launcher_Mosaic";
        this.localHandler = new Handler();
        this.epgHandler = new Handler();
        ViewModel viewModel = ViewModelProviders.of((FragmentActivity) context, new OptimizerEPGViewModel.Factory(context)).get(EPGViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of((c…EPGViewModel::class.java)");
        this.epgViewModel = (EPGViewModel) viewModel;
        this.titleViewAdapter = new TitleViewAdapter() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$titleViewAdapter$1
            @Override // androidx.leanback.widget.TitleViewAdapter
            public View getSearchAffordanceView() {
                return null;
            }
        };
        View.inflate(context, R.layout.view_home_herozone, this);
        View viewFindViewById = findViewById(R.id.grid_dock);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.grid_dock)");
        this.gridDock = (FrameLayout) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.notification_icon);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.notification_icon)");
        this.notifView = (NotificationView) viewFindViewById2;
        this.lifecycleObserver = new DebouncedLifecycleObserver() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$lifecycleObserver$1
            @Override // com.dotscreen.portal.utils.DebouncedLifecycleObserver
            public void onPause() {
                Log.d(this.this$0.TAG, "lifecycleObserver::onPause");
                Disposable disposable = this.this$0.liveCardTimerDisposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                this.this$0.epgHandler.removeCallbacksAndMessages(null);
                this.this$0.localHandler.removeCallbacksAndMessages(null);
                Disposable disposable2 = this.this$0.userStatusDisposable;
                if (disposable2 != null) {
                    disposable2.dispose();
                }
            }

            @Override // com.dotscreen.portal.utils.DebouncedLifecycleObserver
            public void onResume() {
                ArrayObjectAdapter arrayObjectAdapter;
                Log.d(this.this$0.TAG, "lifecycleObserver::onResume");
                ArrayObjectAdapter arrayObjectAdapter2 = this.this$0.adapter;
                if (arrayObjectAdapter2 == null || arrayObjectAdapter2.size() != 0) {
                    ArrayObjectAdapter arrayObjectAdapter3 = this.this$0.adapter;
                    if (!((arrayObjectAdapter3 != null ? arrayObjectAdapter3.get(0) : null) instanceof HomeWidget) && (arrayObjectAdapter = this.this$0.adapter) != null) {
                        arrayObjectAdapter.replace(0, new HomeWidget(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE, -1, null, 0, 8, null));
                    }
                }
                this.this$0.refreshLiveCard();
            }
        };
        this.refreshLiveCardRunnable = new Runnable() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCardRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.refreshLiveCard();
            }
        };
    }

    public /* synthetic */ HomeHeroZoneView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final NotificationView getNotifView() {
        return this.notifView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, android.net.Uri] */
    public final void refreshLiveCard() {
        ArrayObjectAdapter arrayObjectAdapter;
        Log.d(this.TAG, "refreshLiveCard...");
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        PortalApplicationSingletons singleton = companion.getSingleton(context);
        ArrayObjectAdapter arrayObjectAdapter2 = this.adapter;
        if (arrayObjectAdapter2 != null && arrayObjectAdapter2.size() == 0) {
            return;
        }
        Context context2 = getContext();
        if (context2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        }
        Lifecycle lifecycle = ((FragmentActivity) context2).getLifecycle();
        Intrinsics.checkExpressionValueIsNotNull(lifecycle, "(context as FragmentActivity).lifecycle");
        if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            return;
        }
        try {
            Object systemService = getContext().getSystemService("tv_input");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.media.tv.TvInputManager");
            }
            TvInputManager tvInputManager = (TvInputManager) systemService;
            Log.d(this.TAG, "   isSingleSessionActive = " + tvInputManager.isSingleSessionActive());
            if (tvInputManager.isSingleSessionActive()) {
                Log.d(this.TAG, "not ready to be refreshed");
                this.localHandler.removeCallbacks(this.refreshLiveCardRunnable);
                this.localHandler.postDelayed(this.refreshLiveCardRunnable, 1000L);
                ArrayObjectAdapter arrayObjectAdapter3 = this.adapter;
                if (arrayObjectAdapter3 == null || arrayObjectAdapter3.size() != 0) {
                    ArrayObjectAdapter arrayObjectAdapter4 = this.adapter;
                    if (((arrayObjectAdapter4 != null ? arrayObjectAdapter4.get(0) : null) instanceof HomeWidget) || (arrayObjectAdapter = this.adapter) == null) {
                        return;
                    }
                    arrayObjectAdapter.replace(0, new HomeWidget(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE, -1, null, 0, 8, null));
                    return;
                }
                return;
            }
            LiveData<EPGViewModel.Data> liveData = this.epgLiveData;
            if (liveData != null) {
                Context context3 = getContext();
                if (context3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                }
                liveData.removeObservers((FragmentActivity) context3);
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (Uri) 0;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass2(objectRef, singleton, new C04431(objectRef, singleton), null), 2, null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$2, reason: invalid class name */
    /* JADX INFO: compiled from: HomeHeroZoneView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$2", f = "HomeHeroZoneView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PortalApplicationSingletons $app;
        final /* synthetic */ Ref.ObjectRef $lastWatchedChannelUri;
        final /* synthetic */ C04431 $updateEPGData$1;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.ObjectRef objectRef, PortalApplicationSingletons portalApplicationSingletons, C04431 c04431, Continuation continuation) {
            super(2, continuation);
            this.$lastWatchedChannelUri = objectRef;
            this.$app = portalApplicationSingletons;
            this.$updateEPGData$1 = c04431;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = HomeHeroZoneView.this.new AnonymousClass2(this.$lastWatchedChannelUri, this.$app, this.$updateEPGData$1, completion);
            anonymousClass2.p$ = (CoroutineScope) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v8, types: [T, android.net.Uri] */
        /* JADX WARN: Type inference failed for: r5v14, types: [T, android.net.Uri] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            Object next;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = HomeHeroZoneView.this.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            LiveData<PortalApplication.Config> config = companion.getSingleton(context).getConfig();
            Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(context).config");
            PortalApplication.Config value = config.getValue();
            if (value != null && value.getUser() != null) {
                Context context2 = HomeHeroZoneView.this.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                ContentResolver contentResolver = context2.getContentResolver();
                Uri content_uri = LastChannelsProvider.INSTANCE.getCONTENT_URI();
                String str = LastChannelsProvider.INSTANCE.getCOLUMN_PROFILE_ID() + "=?";
                String[] strArr = new String[1];
                RightVUser user = value.getUser();
                if (user == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
                }
                strArr[0] = user.getCompassId();
                Cursor cursorQuery = contentResolver.query(content_uri, null, str, strArr, null);
                if (cursorQuery != null) {
                    Cursor cursor = cursorQuery;
                    Throwable th = (Throwable) null;
                    try {
                        Cursor cursor2 = cursor;
                        if (cursor2.moveToLast()) {
                            this.$lastWatchedChannelUri.element = TvContract.buildChannelUri(cursor2.getLong(1));
                            Context context3 = HomeHeroZoneView.this.getContext();
                            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
                            Cursor cursorQuery2 = context3.getContentResolver().query((Uri) this.$lastWatchedChannelUri.element, com.google.android.media.tv.companionlibrary.model.Channel.PROJECTION, null, null, null);
                            if (cursorQuery2 != null) {
                                Cursor cursor3 = cursorQuery2;
                                Throwable th2 = (Throwable) null;
                                try {
                                    Cursor cursor4 = cursor3;
                                    if (cursor4 != null && cursor4.getCount() > 0) {
                                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new HomeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$let$lambda$1(null, this), 2, null);
                                        Unit unit = Unit.INSTANCE;
                                        CloseableKt.closeFinally(cursor3, th2);
                                        CloseableKt.closeFinally(cursor, th);
                                        return unit;
                                    }
                                    Unit unit2 = Unit.INSTANCE;
                                    CloseableKt.closeFinally(cursor3, th2);
                                } catch (Throwable th3) {
                                    try {
                                        throw th3;
                                    } catch (Throwable th4) {
                                        CloseableKt.closeFinally(cursor3, th3);
                                        throw th4;
                                    }
                                }
                            }
                        }
                        Unit unit3 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor, th);
                    } catch (Throwable th5) {
                        try {
                            throw th5;
                        } catch (Throwable th6) {
                            CloseableKt.closeFinally(cursor, th5);
                            throw th6;
                        }
                    }
                }
            }
            try {
                Context context4 = HomeHeroZoneView.this.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context4, "context");
                Cursor cursorQuery3 = context4.getContentResolver().query(TvContract.Channels.CONTENT_URI, new String[]{"_id", "package_name", "input_id", TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER}, "(type != ?) AND (input_id IN " + CommonUtils.getAvailableTVInputIdsQueryParam(HomeHeroZoneView.this.getContext()) + ')', new String[]{TvContractCompat.Channels.TYPE_PREVIEW}, null);
                if (cursorQuery3 != null) {
                    Cursor cursor5 = cursorQuery3;
                    Throwable th7 = (Throwable) null;
                    try {
                        Cursor cursor6 = cursor5;
                        ArrayList arrayList = new ArrayList();
                        while (cursor6.moveToNext()) {
                            long j = cursor6.getLong(0);
                            String string = cursor6.getString(2);
                            String string2 = cursor6.getString(3);
                            Intrinsics.checkExpressionValueIsNotNull(string2, "c.getString(3)");
                            Integer intOrNull = StringsKt.toIntOrNull(string2);
                            if ((!Intrinsics.areEqual(string, CommonUtils.getInterctiveLiveInputId())) && intOrNull != null) {
                                arrayList.add(new Pair(Boxing.boxLong(j), intOrNull));
                            }
                        }
                        cursor6.close();
                        Iterator it = arrayList.iterator();
                        if (it.hasNext()) {
                            next = it.next();
                            if (it.hasNext()) {
                                Integer numBoxInt = Boxing.boxInt(((Number) ((Pair) next).getSecond()).intValue());
                                do {
                                    Object next2 = it.next();
                                    Integer numBoxInt2 = Boxing.boxInt(((Number) ((Pair) next2).getSecond()).intValue());
                                    if (numBoxInt.compareTo(numBoxInt2) > 0) {
                                        next = next2;
                                        numBoxInt = numBoxInt2;
                                    }
                                } while (it.hasNext());
                            }
                        } else {
                            next = null;
                        }
                        if (next == null) {
                            Intrinsics.throwNpe();
                        }
                        this.$lastWatchedChannelUri.element = TvContract.buildChannelUri(((Number) ((Pair) next).getFirst()).longValue());
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new HomeHeroZoneView$refreshLiveCard$2$invokeSuspend$$inlined$use$lambda$1(null, this), 2, null);
                        CloseableKt.closeFinally(cursor5, th7);
                    } catch (Throwable th8) {
                        try {
                            throw th8;
                        } catch (Throwable th9) {
                            CloseableKt.closeFinally(cursor5, th8);
                            throw th9;
                        }
                    }
                }
            } catch (KotlinNullPointerException unused) {
                ArrayObjectAdapter arrayObjectAdapter = HomeHeroZoneView.this.adapter;
                if ((arrayObjectAdapter == null || arrayObjectAdapter.size() != 0) && !CommonUtils.isTypeSTB(HomeHeroZoneView.this.getContext(), "OTT STB")) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass3(null), 2, null);
                }
                Log.e(HomeHeroZoneView.this.TAG, " No channel found scan need to be performed");
            } catch (Throwable th10) {
                Log.w(HomeHeroZoneView.this.TAG, "error", th10);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: HomeHeroZoneView.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.portal.ui.views.HomeHeroZoneView$refreshLiveCard$2$3", f = "HomeHeroZoneView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass3(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass3 anonymousClass3 = AnonymousClass2.this.new AnonymousClass3(completion);
                anonymousClass3.p$ = (CoroutineScope) obj;
                return anonymousClass3;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ArrayObjectAdapter arrayObjectAdapter = HomeHeroZoneView.this.adapter;
                if (arrayObjectAdapter != null) {
                    arrayObjectAdapter.replace(0, new NoChannelFoundException("Scan need to be performed"));
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        if (direction == 33) {
            return this.notifView;
        }
        if (direction == 17 || direction == 66) {
            if (Intrinsics.areEqual(focused, this.notifView)) {
                return this.notifView;
            }
        } else if (direction == 130 && Intrinsics.areEqual(focused, this.notifView)) {
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            if (companion.getSingleton(context).getDegradedMode()) {
                Context context2 = getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!CommonUtils.isDVBCSTB(context2)) {
                    Sentry.addBreadcrumb(this.TAG + " => focusSearch, degraded mode");
                }
            }
            return this.gridDock;
        }
        Sentry.addBreadcrumb(this.TAG + " => focusSearch(" + focused + ", " + direction + ')');
        return super.focusSearch(focused, direction);
    }

    public final void invalidateData() {
        createGrid();
    }

    private final void createGrid() {
        ArrayObjectAdapter arrayObjectAdapter;
        PackageManager packageManager;
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        final PortalApplicationSingletons singleton = companion.getSingleton(context);
        Log.d(this.TAG, "createGrid()");
        boolean z = true;
        final boolean z2 = false;
        if (this.adapter == null) {
            final LoadingCardPresenter loadingCardPresenter = new LoadingCardPresenter(null, null, 3, null);
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            Resources resources = context2.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            Integer numValueOf = Integer.valueOf((int) TypedValue.applyDimension(1, 489.0f, resources.getDisplayMetrics()));
            Context context3 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
            Resources resources2 = context3.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources2, "context.resources");
            final LiveCardPresenter liveCardPresenter = new LiveCardPresenter(numValueOf, Integer.valueOf((int) TypedValue.applyDimension(1, 275.0f, resources2.getDisplayMetrics())), this);
            Context context4 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context4, "context");
            Resources resources3 = context4.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources3, "context.resources");
            Integer numValueOf2 = Integer.valueOf((int) TypedValue.applyDimension(1, 86.33f, resources3.getDisplayMetrics()));
            Context context5 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context5, "context");
            Resources resources4 = context5.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources4, "context.resources");
            final VODItemCardPresenter vODItemCardPresenter = new VODItemCardPresenter(numValueOf2, Integer.valueOf((int) TypedValue.applyDimension(1, 129.5f, resources4.getDisplayMetrics())), null, false, null, false, 52, null);
            Context context6 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context6, "context");
            Resources resources5 = context6.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources5, "context.resources");
            Integer numValueOf3 = Integer.valueOf(((int) TypedValue.applyDimension(1, 172.66f, resources5.getDisplayMetrics())) + getResources().getDimensionPixelSize(R.dimen.lb_browse_item_vertical_spacing));
            Context context7 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context7, "context");
            Resources resources6 = context7.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources6, "context.resources");
            final VODItemCardPresenter vODItemCardPresenter2 = new VODItemCardPresenter(numValueOf3, Integer.valueOf((int) TypedValue.applyDimension(1, 129.5f, resources6.getDisplayMetrics())), null, false, null, true, 20, null);
            final HeroZoneAppCardPresenter heroZoneAppCardPresenter = new HeroZoneAppCardPresenter();
            Intent leanbackLaunchIntentForPackage = null;
            final EmptyCardPresenter emptyCardPresenter = new EmptyCardPresenter(null, null, 3, null);
            PresenterSelector presenterSelector = new PresenterSelector() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$createGrid$mySelector$1
                @Override // androidx.leanback.widget.PresenterSelector
                public Presenter getPresenter(Object item) {
                    if ((item instanceof TVAiring) || (item instanceof Uri)) {
                        return liveCardPresenter;
                    }
                    if (!(item instanceof Pair) && !(item instanceof NoChannelFoundException)) {
                        if (item instanceof ItemCollection) {
                            return vODItemCardPresenter2;
                        }
                        if (item instanceof BrowsableItem) {
                            return vODItemCardPresenter;
                        }
                        if (item instanceof ApplicationWidget) {
                            return heroZoneAppCardPresenter;
                        }
                        return item instanceof HomeWidget ? loadingCardPresenter : emptyCardPresenter;
                    }
                    return liveCardPresenter;
                }
            };
            final int i = 4;
            CollectionGridPresenter collectionGridPresenter = new CollectionGridPresenter(i, z2) { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$createGrid$presenter$1
                @Override // androidx.leanback.widget.CollectionGridPresenter
                protected CollectionGridPresenter.ViewHolder createGridViewHolder(ViewGroup parent) {
                    Intrinsics.checkParameterIsNotNull(parent, "parent");
                    View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lb_herozone_collection_grid, parent, false);
                    Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(pare…tion_grid, parent, false)");
                    View viewFindViewById = viewInflate.findViewById(R.id.browse_grid);
                    Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "root.findViewById(R.id.browse_grid)");
                    CollectionGridView collectionGridView = (CollectionGridView) viewFindViewById;
                    collectionGridView.setItemAnimator((RecyclerView.ItemAnimator) null);
                    return new CollectionGridPresenter.ViewHolder(collectionGridView);
                }
            };
            collectionGridPresenter.setShadowEnabled(true);
            collectionGridPresenter.setKeepChildForeground(false);
            collectionGridPresenter.enableChildRoundedCorners(true);
            this.adapter = new ArrayObjectAdapter(presenterSelector);
            if (!singleton.getDegradedMode()) {
                ArrayObjectAdapter arrayObjectAdapter2 = this.adapter;
                if (arrayObjectAdapter2 != null) {
                    arrayObjectAdapter2.add(new HomeWidget(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE, -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter3 = this.adapter;
                if (arrayObjectAdapter3 != null) {
                    arrayObjectAdapter3.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter4 = this.adapter;
                if (arrayObjectAdapter4 != null) {
                    arrayObjectAdapter4.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter5 = this.adapter;
                if (arrayObjectAdapter5 != null) {
                    arrayObjectAdapter5.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter6 = this.adapter;
                if (arrayObjectAdapter6 != null) {
                    arrayObjectAdapter6.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                Intent intentPatchInteractiveIntent = CommonUtils.patchInteractiveIntent(Intent.parseUri("http://www.netflix.com/browse?iid=c797fa94", 0));
                ArrayObjectAdapter arrayObjectAdapter7 = this.adapter;
                if (arrayObjectAdapter7 != null) {
                    arrayObjectAdapter7.add(new ApplicationWidget("Netflix", "com.netflix.ninja", "file:///android_asset/img/vodsup_netflix.png", intentPatchInteractiveIntent));
                }
                Context context8 = getContext();
                if (context8 != null && (packageManager = context8.getPackageManager()) != null) {
                    leanbackLaunchIntentForPackage = packageManager.getLeanbackLaunchIntentForPackage("com.amazon.amazonvideo.livingroom");
                }
                ArrayObjectAdapter arrayObjectAdapter8 = this.adapter;
                if (arrayObjectAdapter8 != null) {
                    arrayObjectAdapter8.add(new ApplicationWidget("Prime Video", "com.amazon.amazonvideo.livingroom", "file:///android_asset/img/prime_video.png", leanbackLaunchIntentForPackage));
                }
                ArrayObjectAdapter arrayObjectAdapter9 = this.adapter;
                if (arrayObjectAdapter9 != null) {
                    arrayObjectAdapter9.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter10 = this.adapter;
                if (arrayObjectAdapter10 != null) {
                    arrayObjectAdapter10.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter11 = this.adapter;
                if (arrayObjectAdapter11 != null) {
                    arrayObjectAdapter11.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter12 = this.adapter;
                if (arrayObjectAdapter12 != null) {
                    arrayObjectAdapter12.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter13 = this.adapter;
                if (arrayObjectAdapter13 != null) {
                    arrayObjectAdapter13.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter14 = this.adapter;
                if (arrayObjectAdapter14 != null) {
                    arrayObjectAdapter14.add(new HomeWidget("poster reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter15 = this.adapter;
                if (arrayObjectAdapter15 != null) {
                    arrayObjectAdapter15.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter16 = this.adapter;
                if (arrayObjectAdapter16 != null) {
                    arrayObjectAdapter16.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter17 = this.adapter;
                if (arrayObjectAdapter17 != null) {
                    arrayObjectAdapter17.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter18 = this.adapter;
                if (arrayObjectAdapter18 != null) {
                    arrayObjectAdapter18.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter19 = this.adapter;
                if (arrayObjectAdapter19 != null) {
                    arrayObjectAdapter19.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter20 = this.adapter;
                if (arrayObjectAdapter20 != null) {
                    arrayObjectAdapter20.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter21 = this.adapter;
                if (arrayObjectAdapter21 != null) {
                    arrayObjectAdapter21.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter22 = this.adapter;
                if (arrayObjectAdapter22 != null) {
                    arrayObjectAdapter22.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter23 = this.adapter;
                if (arrayObjectAdapter23 != null) {
                    arrayObjectAdapter23.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
                ArrayObjectAdapter arrayObjectAdapter24 = this.adapter;
                if (arrayObjectAdapter24 != null) {
                    arrayObjectAdapter24.add(new HomeWidget("still reco", -1, null, 0, 8, null));
                }
            } else {
                Context context9 = getContext();
                if (context9 == null) {
                    Intrinsics.throwNpe();
                }
                if (CommonUtils.isDVBCSTB(context9) && (arrayObjectAdapter = this.adapter) != null) {
                    arrayObjectAdapter.add(new HomeWidget(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE, -1, null, 0, 8, null));
                }
            }
            this.gridViewHolder = collectionGridPresenter.onCreateViewHolder((ViewGroup) this.gridDock);
            this.gridDock.removeAllViews();
            FrameLayout frameLayout = this.gridDock;
            CollectionGridPresenter.ViewHolder viewHolder = this.gridViewHolder;
            if (viewHolder == null) {
                Intrinsics.throwNpe();
            }
            frameLayout.addView(viewHolder.view);
            CollectionGridPresenter.ViewHolder viewHolder2 = this.gridViewHolder;
            if (viewHolder2 == null) {
                Intrinsics.throwNpe();
            }
            CollectionGridView gridView = viewHolder2.getGridView();
            if (gridView == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HomeHeroZoneCollectionGridView");
            }
            ((HomeHeroZoneCollectionGridView) gridView).setNumberOfPosterItems(10);
            CollectionGridPresenter.ViewHolder viewHolder3 = this.gridViewHolder;
            if (viewHolder3 == null) {
                Intrinsics.throwNpe();
            }
            CollectionGridView gridView2 = viewHolder3.getGridView();
            if (gridView2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HomeHeroZoneCollectionGridView");
            }
            ((HomeHeroZoneCollectionGridView) gridView2).setNumberOfStillItems(10);
            collectionGridPresenter.onBindViewHolder(this.gridViewHolder, this.adapter);
            collectionGridPresenter.setOnItemViewClickedListener(new OnItemViewClickedListener() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.createGrid.1
                @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
                public final void onItemClicked(Presenter.ViewHolder viewHolder4, Object obj, RowPresenter.ViewHolder viewHolder5, Row row) throws URISyntaxException {
                    if (obj instanceof TVAiring) {
                        HomeHeroZoneView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", CommonUtils.getLiveTvDeeplink(HomeHeroZoneView.this.getContext(), ((TVAiring) obj).getChannel(), (Boolean) true)));
                        return;
                    }
                    if (obj instanceof Uri) {
                        if (singleton.getBootWithoutInternet()) {
                            return;
                        }
                        HomeHeroZoneView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", (Uri) obj));
                        return;
                    }
                    if (obj instanceof Pair) {
                        if (singleton.getBootWithoutInternet()) {
                            return;
                        }
                        Object first = ((Pair) obj).getFirst();
                        if (first != null) {
                            HomeHeroZoneView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", (Uri) first));
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type android.net.Uri");
                    }
                    if (obj instanceof CatalogSection) {
                        Context context10 = HomeHeroZoneView.this.getContext();
                        if (context10 == null) {
                            Intrinsics.throwNpe();
                        }
                        Context applicationContext = context10.getApplicationContext();
                        if (applicationContext == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        VODCatalogService vodCatalogService = ((PortalApplication) applicationContext).getBOService().getVodCatalogService();
                        if (vodCatalogService == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
                        }
                        MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, (BrowsableItem) obj, null, HomeHeroZoneView.this.blend, null, 10, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.createGrid.1.1
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.createGrid.1.2
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                            }
                        });
                        StringBuilder sb = new StringBuilder();
                        sb.append("megacable://com.dotscreen.megacable.portal/collection?catalog_id=");
                        sb.append(Constants.INSTANCE.getHOME_RECO_ROOT_CATALOG_ID());
                        sb.append("&collection_id=");
                        CatalogSection catalogSection = (CatalogSection) obj;
                        sb.append(catalogSection.getId());
                        final Intent uri = Intent.parseUri(sb.toString(), 0);
                        ParentalRating parentalLevel = catalogSection.getParentalLevel();
                        if (parentalLevel != null && parentalLevel.getIsAdult()) {
                            Context context11 = HomeHeroZoneView.this.getContext();
                            if (context11 == null) {
                                Intrinsics.throwNpe();
                            }
                            PinCodeDialog.Builder checkPinCode = new PinCodeDialog.Builder(context11).setCheckPinCode(true);
                            Context context12 = HomeHeroZoneView.this.getContext();
                            if (context12 == null) {
                                Intrinsics.throwNpe();
                            }
                            Context applicationContext2 = context12.getApplicationContext();
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
                            checkPinCode.setUser(user).setPincodeType(PincodeType.Parental).setTitle(R.string.input_parental_pin_code).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.createGrid.1.3
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
                                    HomeHeroZoneView.this.getContext().startActivity(uri);
                                }
                            }).create().show();
                            return;
                        }
                        HomeHeroZoneView.this.getContext().startActivity(uri);
                        return;
                    }
                    if (obj instanceof BrowsableItem) {
                        Context context13 = HomeHeroZoneView.this.getContext();
                        if (context13 == null) {
                            Intrinsics.throwNpe();
                        }
                        Context applicationContext3 = context13.getApplicationContext();
                        if (applicationContext3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        VODCatalogService vodCatalogService2 = ((PortalApplication) applicationContext3).getBOService().getVodCatalogService();
                        if (vodCatalogService2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
                        }
                        BrowsableItem browsableItem = (BrowsableItem) obj;
                        MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService2, browsableItem, null, HomeHeroZoneView.this.blend, null, 10, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.createGrid.1.4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.createGrid.1.5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                            }
                        });
                        HomeHeroZoneView.this.getContext().startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + browsableItem.getId(), 0));
                        return;
                    }
                    if (obj instanceof ApplicationWidget) {
                        PortalApplication.Companion companion2 = PortalApplication.INSTANCE;
                        Context context14 = HomeHeroZoneView.this.getContext();
                        if (context14 == null) {
                            Intrinsics.throwNpe();
                        }
                        ApplicationsManager applicationsManager = companion2.getSingleton(context14).getApplicationsManager();
                        Context context15 = HomeHeroZoneView.this.getContext();
                        if (context15 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                        }
                        applicationsManager.launchApplication((Activity) context15, (ApplicationWidget) obj);
                    }
                }
            });
        } else if (singleton.getDegradedMode()) {
            Context context10 = getContext();
            if (context10 == null) {
                Intrinsics.throwNpe();
            }
            if (CommonUtils.isDVBCSTB(context10)) {
                CollectionGridPresenter.ViewHolder viewHolder4 = this.gridViewHolder;
                if (viewHolder4 == null) {
                    Intrinsics.throwNpe();
                }
                CollectionGridView gridView3 = viewHolder4.getGridView();
                if (gridView3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HomeHeroZoneCollectionGridView");
                }
                ((HomeHeroZoneCollectionGridView) gridView3).scrollToPosition(0);
                ArrayObjectAdapter arrayObjectAdapter25 = this.adapter;
                if (arrayObjectAdapter25 != null) {
                    if (arrayObjectAdapter25 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayObjectAdapter25.removeItems(1, arrayObjectAdapter25.size());
                }
                z = false;
            } else {
                ArrayObjectAdapter arrayObjectAdapter26 = this.adapter;
                if (arrayObjectAdapter26 != null) {
                    arrayObjectAdapter26.clear();
                }
            }
        }
        if (z) {
            refreshLiveCard();
        }
        if (!singleton.getDegradedMode()) {
            updateRecommendations();
        }
        if (singleton.getDegradedMode()) {
            Context context11 = getContext();
            if (context11 == null) {
                Intrinsics.throwNpe();
            }
            if (!CommonUtils.isDVBCSTB(context11)) {
                View viewFindViewById = findViewById(R.id.browse_frame);
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(8);
                    viewFindViewById.setFocusable(false);
                    viewFindViewById.setFocusableInTouchMode(false);
                    return;
                }
                return;
            }
        }
        View viewFindViewById2 = findViewById(R.id.browse_frame);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(0);
            viewFindViewById2.setFocusable(false);
            viewFindViewById2.setFocusableInTouchMode(false);
        }
    }

    private final void updateRecommendations() {
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        PortalApplicationSingletons singleton = companion.getSingleton(context);
        VODCatalogService vodCatalogService = singleton.getBOService().getVodCatalogService();
        if (vodCatalogService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
        }
        Disposable disposableSubscribe = Single.zip(((MegacableVODCatalogService) vodCatalogService).getRecommendations(this.blend, (26 & 2) != 0 ? (String) null : null, (26 & 4) != 0 ? (String) null : null, (26 & 8) != 0 ? 10 : 10, (26 & 16) != 0 ? false : true, (26 & 32) == 0 ? false : false).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.updateRecommendations.1
            @Override // io.reactivex.functions.Function
            public final Observable<BrowsableItem> apply(ItemCollection<BrowsableItem> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).switchIfEmpty(singleton.getBOService().getVodCatalogService().sectionById(Constants.INSTANCE.getVOD_ROOT_CATALOG_ID(), Constants.INSTANCE.getHOME_HIGHLIGHT_CATALOG_ID()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.updateRecommendations.2
            @Override // io.reactivex.functions.Function
            public final Observable<BrowsableItem> apply(CatalogSection it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).filter(new Predicate<BrowsableItem>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.updateRecommendations.3
            @Override // io.reactivex.functions.Predicate
            public final boolean test(BrowsableItem it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.externalId(ItemExternalIdScheme.Netflix) == null;
            }
        })).toList().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.updateRecommendations.4
            @Override // io.reactivex.functions.Function
            public final List<BrowsableItem> apply(List<BrowsableItem> vodRecos) {
                Intrinsics.checkParameterIsNotNull(vodRecos, "vodRecos");
                if (vodRecos.size() > 6 && vodRecos.size() % 2 == 1) {
                    vodRecos.remove(vodRecos.size() - 1);
                }
                return vodRecos;
            }
        }), singleton.getBOService().getVodCatalogService().rootSection(Constants.INSTANCE.getHOME_RECO_ROOT_CATALOG_ID()).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.updateRecommendations.5
            @Override // io.reactivex.functions.Function
            public final Observable<ItemCollection<BrowsableItem>> apply(CatalogSection it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getChildren();
            }
        }).toList(), new BiFunction<List<BrowsableItem>, List<ItemCollection<BrowsableItem>>, Pair<? extends List<? extends BrowsableItem>, ? extends List<? extends ItemCollection<BrowsableItem>>>>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.updateRecommendations.6
            @Override // io.reactivex.functions.BiFunction
            public /* bridge */ /* synthetic */ Pair<? extends List<? extends BrowsableItem>, ? extends List<? extends ItemCollection<BrowsableItem>>> apply(List<BrowsableItem> list, List<ItemCollection<BrowsableItem>> list2) {
                return apply2((List<? extends BrowsableItem>) list, (List<? extends ItemCollection<BrowsableItem>>) list2);
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public final Pair<List<BrowsableItem>, List<ItemCollection<BrowsableItem>>> apply2(List<? extends BrowsableItem> movieList, List<? extends ItemCollection<BrowsableItem>> collectionList) {
                Intrinsics.checkParameterIsNotNull(movieList, "movieList");
                Intrinsics.checkParameterIsNotNull(collectionList, "collectionList");
                return new Pair<>(movieList, collectionList);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends List<? extends BrowsableItem>, ? extends List<? extends ItemCollection<BrowsableItem>>>>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.updateRecommendations.7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Pair<? extends List<? extends BrowsableItem>, ? extends List<? extends ItemCollection<BrowsableItem>>> pair) {
                int i;
                int i2;
                int i3;
                ArrayObjectAdapter arrayObjectAdapter;
                if (HomeHeroZoneView.this.adapter != null) {
                    ArrayObjectAdapter arrayObjectAdapter2 = HomeHeroZoneView.this.adapter;
                    if (arrayObjectAdapter2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (arrayObjectAdapter2.size() > 0) {
                        int iMax = Integer.max(pair.getFirst().size(), 6);
                        CollectionGridPresenter.ViewHolder viewHolder = HomeHeroZoneView.this.gridViewHolder;
                        CollectionGridView gridView = viewHolder != null ? viewHolder.getGridView() : null;
                        if (gridView != null) {
                            ((HomeHeroZoneCollectionGridView) gridView).setNumberOfPosterItems(iMax);
                            CollectionGridPresenter.ViewHolder viewHolder2 = HomeHeroZoneView.this.gridViewHolder;
                            CollectionGridView gridView2 = viewHolder2 != null ? viewHolder2.getGridView() : null;
                            if (gridView2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HomeHeroZoneCollectionGridView");
                            }
                            ((HomeHeroZoneCollectionGridView) gridView2).setNumberOfStillItems(pair.getSecond().size());
                            if (!pair.getFirst().isEmpty()) {
                                ArrayObjectAdapter arrayObjectAdapter3 = HomeHeroZoneView.this.adapter;
                                if (arrayObjectAdapter3 != null) {
                                    arrayObjectAdapter3.replace(1, pair.getFirst().get(0 % pair.getFirst().size()));
                                    i3 = 1;
                                    i2 = 2;
                                } else {
                                    i2 = 1;
                                    i3 = 0;
                                }
                                ArrayObjectAdapter arrayObjectAdapter4 = HomeHeroZoneView.this.adapter;
                                if (arrayObjectAdapter4 != null) {
                                    arrayObjectAdapter4.replace(i2, pair.getFirst().get(i3 % pair.getFirst().size()));
                                    i2++;
                                    i3++;
                                }
                                ArrayObjectAdapter arrayObjectAdapter5 = HomeHeroZoneView.this.adapter;
                                if (arrayObjectAdapter5 != null) {
                                    arrayObjectAdapter5.replace(i2, pair.getFirst().get(i3 % pair.getFirst().size()));
                                    i2++;
                                    i3++;
                                }
                                ArrayObjectAdapter arrayObjectAdapter6 = HomeHeroZoneView.this.adapter;
                                if (arrayObjectAdapter6 != null) {
                                    arrayObjectAdapter6.replace(i2, pair.getFirst().get(i3 % pair.getFirst().size()));
                                    i2++;
                                    i3++;
                                }
                                i = i2 + 2;
                                while (i3 < iMax) {
                                    ArrayObjectAdapter arrayObjectAdapter7 = HomeHeroZoneView.this.adapter;
                                    if (i < (arrayObjectAdapter7 != null ? arrayObjectAdapter7.size() : iMax) && (arrayObjectAdapter = HomeHeroZoneView.this.adapter) != null) {
                                        arrayObjectAdapter.replace(i, pair.getFirst().get(i3 % pair.getFirst().size()));
                                        i++;
                                    }
                                    i3++;
                                }
                            } else {
                                i = 1;
                            }
                            ArrayObjectAdapter arrayObjectAdapter8 = HomeHeroZoneView.this.adapter;
                            if (arrayObjectAdapter8 != null) {
                                ArrayObjectAdapter arrayObjectAdapter9 = HomeHeroZoneView.this.adapter;
                                arrayObjectAdapter8.removeItems(i, arrayObjectAdapter9 != null ? arrayObjectAdapter9.size() : 0);
                            }
                            if (!pair.getFirst().isEmpty()) {
                                for (ItemCollection<BrowsableItem> itemCollection : pair.getSecond()) {
                                    ArrayObjectAdapter arrayObjectAdapter10 = HomeHeroZoneView.this.adapter;
                                    if (arrayObjectAdapter10 != null) {
                                        arrayObjectAdapter10.add(itemCollection);
                                    }
                                }
                                return;
                            }
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.views.HomeHeroZoneCollectionGridView");
                    }
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView.updateRecommendations.8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(HomeHeroZoneView.this.TAG, "error", th);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "Single.zip(\n            …error)\n                })");
        Context context2 = getContext();
        if (context2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) context2).getDisposable());
    }

    @Override // androidx.leanback.widget.TitleViewAdapter.Provider
    public TitleViewAdapter getTitleViewAdapter() {
        return this.titleViewAdapter;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LiveData<PortalApplication.Config> config = companion.getSingleton(context).getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "app.config");
        PortalApplication.Config value = config.getValue();
        if (value != null) {
            value.getDesignManager().getBitmap("standard.logo").subscribe(new Consumer<Bitmap>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$onAttachedToWindow$$inlined$let$lambda$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Bitmap bitmap) {
                    ImageView imageView = (ImageView) this.this$0.findViewById(R.id.logo);
                    if (imageView != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$onAttachedToWindow$1$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    th.printStackTrace();
                }
            }, new Action() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneView$onAttachedToWindow$1$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                }
            });
        }
        Context context2 = getContext();
        if (context2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        ((BaseFragmentActivity) context2).getLifecycle().addObserver(this.lifecycleObserver);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Disposable disposable = this.liveCardTimerDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.epgHandler.removeCallbacksAndMessages(null);
        this.localHandler.removeCallbacksAndMessages(null);
        Context context = getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        ((BaseFragmentActivity) context).getLifecycle().removeObserver(this.lifecycleObserver);
    }

    @Override // com.dotscreen.portal.ui.cards.RefreshCard
    public void refresh() {
        refreshLiveCard();
    }
}
