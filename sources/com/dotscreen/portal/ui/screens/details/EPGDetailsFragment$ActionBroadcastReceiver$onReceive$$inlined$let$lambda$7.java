package com.dotscreen.portal.ui.screens.details;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: EPGDetailsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"deleteRecording", "", "invoke", "com/dotscreen/portal/ui/screens/details/EPGDetailsFragment$ActionBroadcastReceiver$onReceive$1$19"}, k = 3, mv = {1, 1, 16})
final class EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$7 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ EPGDetailsFragment.ActionBroadcastReceiver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$7(EPGDetailsFragment.ActionBroadcastReceiver actionBroadcastReceiver, Context context) {
        super(0);
        this.this$0 = actionBroadcastReceiver;
        this.$context$inlined = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        Completable completableSubscribeOn;
        Completable completableObserveOn;
        Disposable disposableSubscribe;
        FragmentActivity activity = EPGDetailsFragment.this.getActivity();
        if (!(activity instanceof BaseFragmentActivity)) {
            activity = null;
        }
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) activity;
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        FragmentActivity activity2 = EPGDetailsFragment.this.getActivity();
        BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) (activity2 instanceof BaseFragmentActivity ? activity2 : null);
        if (baseFragmentActivity2 != null) {
            baseFragmentActivity2.setBlockInput(true);
        }
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = this.$context$inlined;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        PVRService pvrService = companion.getSingleton(context).getBOService().getPvrService();
        if (pvrService != null) {
            EPGDetailsViewModel.Result detailsResult = EPGDetailsFragment.this.getDetailsResult();
            if (detailsResult == null) {
                Intrinsics.throwNpe();
            }
            TVRecording recording = detailsResult.getRecording();
            if (recording == null) {
                Intrinsics.throwNpe();
            }
            Completable completableDeleteRecording = pvrService.deleteRecording(recording);
            if (completableDeleteRecording == null || (completableSubscribeOn = completableDeleteRecording.subscribeOn(Schedulers.io())) == null || (completableObserveOn = completableSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe = completableObserveOn.subscribe(new Action() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$7.1
                @Override // io.reactivex.functions.Action
                public final void run() {
                    FragmentActivity activity3 = EPGDetailsFragment.this.getActivity();
                    if (!(activity3 instanceof BaseFragmentActivity)) {
                        activity3 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity3 = (BaseFragmentActivity) activity3;
                    if (baseFragmentActivity3 != null) {
                        baseFragmentActivity3.setLoaderVisible(false);
                    }
                    FragmentActivity activity4 = EPGDetailsFragment.this.getActivity();
                    if (!(activity4 instanceof BaseFragmentActivity)) {
                        activity4 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) activity4;
                    if (baseFragmentActivity4 != null) {
                        baseFragmentActivity4.setBlockInput(false);
                    }
                    EPGDetailsViewModel.Result detailsResult2 = EPGDetailsFragment.this.getDetailsResult();
                    if (detailsResult2 == null) {
                        Intrinsics.throwNpe();
                    }
                    detailsResult2.setRecording((TVRecording) null);
                    EPGDetailsFragment.this.createOrUpdateActionsRow();
                    EPGDetailsViewModel.Result detailsResult3 = EPGDetailsFragment.this.getDetailsResult();
                    if (detailsResult3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!detailsResult3.getAiring().isLive()) {
                        EPGDetailsFragment.this.createOrUpdateActionsRow();
                        return;
                    }
                    EPGDetailsViewModel.Result detailsResult4 = EPGDetailsFragment.this.getDetailsResult();
                    if (detailsResult4 == null) {
                        Intrinsics.throwNpe();
                    }
                    Disposable disposableSubscribe2 = detailsResult4.getAiring().getChannel().airing(new Date()).toSingle().flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$1$19$1$1
                        @Override // io.reactivex.functions.Function
                        public final Single<Pair<TVAiring, Boolean>> apply(TVAiring it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return Single.zip(Single.just(it), it.isRecordable(PVRStorageMode.Network), new BiFunction<TVAiring, Boolean, Pair<? extends TVAiring, ? extends Boolean>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$1$19$1$1.1
                                @Override // io.reactivex.functions.BiFunction
                                public /* bridge */ /* synthetic */ Pair<? extends TVAiring, ? extends Boolean> apply(TVAiring tVAiring, Boolean bool) {
                                    return apply(tVAiring, bool.booleanValue());
                                }

                                public final Pair<TVAiring, Boolean> apply(TVAiring airing, boolean z) {
                                    Intrinsics.checkParameterIsNotNull(airing, "airing");
                                    return new Pair<>(airing, Boolean.valueOf(z));
                                }
                            });
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends TVAiring, ? extends Boolean>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$.inlined.let.lambda.7.1.1
                        @Override // io.reactivex.functions.Consumer
                        public /* bridge */ /* synthetic */ void accept(Pair<? extends TVAiring, ? extends Boolean> pair) {
                            accept2((Pair<? extends TVAiring, Boolean>) pair);
                        }

                        /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
                        public final void accept2(Pair<? extends TVAiring, Boolean> pair) {
                            if (pair.getSecond().booleanValue()) {
                                EPGDetailsViewModel.Result detailsResult5 = EPGDetailsFragment.this.getDetailsResult();
                                if (detailsResult5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                detailsResult5.setAiring(pair.getFirst());
                                EPGDetailsFragment.this.createOrUpdateActionsRow();
                            }
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$.inlined.let.lambda.7.1.2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th) {
                            EPGDetailsFragment.this.createOrUpdateActionsRow();
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe2, "detailsResult!!.airing.c…                       })");
                    FragmentActivity activity5 = EPGDetailsFragment.this.getActivity();
                    if (activity5 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
                    }
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe2, ((BaseFragmentActivity) activity5).getDisposable());
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$7.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable error) {
                    FragmentActivity activity3 = EPGDetailsFragment.this.getActivity();
                    if (!(activity3 instanceof BaseFragmentActivity)) {
                        activity3 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity3 = (BaseFragmentActivity) activity3;
                    if (baseFragmentActivity3 != null) {
                        baseFragmentActivity3.setLoaderVisible(false);
                    }
                    FragmentActivity activity4 = EPGDetailsFragment.this.getActivity();
                    if (!(activity4 instanceof BaseFragmentActivity)) {
                        activity4 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) activity4;
                    if (baseFragmentActivity4 != null) {
                        baseFragmentActivity4.setBlockInput(false);
                    }
                    FragmentActivity activity5 = EPGDetailsFragment.this.getActivity();
                    if (!(activity5 instanceof BaseFragmentActivity)) {
                        activity5 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity5 = (BaseFragmentActivity) activity5;
                    if (baseFragmentActivity5 != null) {
                        Intrinsics.checkExpressionValueIsNotNull(error, "error");
                        BaseFragmentActivity.displayErrorMessage$default(baseFragmentActivity5, error, 0, 2, null);
                    }
                }
            })) == null) {
                return;
            }
            FragmentActivity activity3 = EPGDetailsFragment.this.getActivity();
            if (activity3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
            LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity3).getDisposable());
        }
    }
}
