package com.dotscreen.portal.ui.screens.details;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.RightVBackendErrorException;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVServiceSubscriptionInfo;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EPGDetailsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "ls", "", "Lcom/dotscreen/tv/common/bokit/RightVServiceSubscriptionInfo;", "kotlin.jvm.PlatformType", "", "accept", "com/dotscreen/portal/ui/screens/details/EPGDetailsFragment$ActionBroadcastReceiver$onReceive$1$17"}, k = 3, mv = {1, 1, 16})
final class EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$6<T> implements Consumer<List<RightVServiceSubscriptionInfo>> {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ EPGDetailsFragment.ActionBroadcastReceiver this$0;

    EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$6(EPGDetailsFragment.ActionBroadcastReceiver actionBroadcastReceiver, Context context) {
        this.this$0 = actionBroadcastReceiver;
        this.$context$inlined = context;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(List<RightVServiceSubscriptionInfo> list) {
        Single<TVRecording> singleSubscribeOn;
        Single<TVRecording> singleObserveOn;
        Disposable disposableSubscribe;
        if (list.isEmpty()) {
            FragmentActivity activity = EPGDetailsFragment.this.getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
            new PortalDialog.Builder(activity).setTitle(R.string.package_service_not_subscribed_popup_title).setMessage(R.string.package_service_not_subscribed_popup_subtitle).create().show();
            return;
        }
        FragmentActivity activity2 = EPGDetailsFragment.this.getActivity();
        if (!(activity2 instanceof BaseFragmentActivity)) {
            activity2 = null;
        }
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) activity2;
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        FragmentActivity activity3 = EPGDetailsFragment.this.getActivity();
        BaseFragmentActivity baseFragmentActivity2 = (BaseFragmentActivity) (activity3 instanceof BaseFragmentActivity ? activity3 : null);
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
            PVRStorageMode pVRStorageMode = PVRStorageMode.Network;
            EPGDetailsViewModel.Result detailsResult = EPGDetailsFragment.this.getDetailsResult();
            if (detailsResult == null) {
                Intrinsics.throwNpe();
            }
            Single<TVRecording> singleScheduleAiring = pvrService.scheduleAiring(pVRStorageMode, detailsResult.getAiring(), Long.valueOf(this.$context$inlined.getResources().getInteger(R.integer.rte_order_recording_lead_duration)), Long.valueOf(this.$context$inlined.getResources().getInteger(R.integer.rte_order_recording_lag_duration)));
            if (singleScheduleAiring == null || (singleSubscribeOn = singleScheduleAiring.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe = singleObserveOn.subscribe(new Consumer<TVRecording>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$6.1
                @Override // io.reactivex.functions.Consumer
                public final void accept(TVRecording tVRecording) {
                    FragmentActivity activity4 = EPGDetailsFragment.this.getActivity();
                    if (!(activity4 instanceof BaseFragmentActivity)) {
                        activity4 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity3 = (BaseFragmentActivity) activity4;
                    if (baseFragmentActivity3 != null) {
                        baseFragmentActivity3.setLoaderVisible(false);
                    }
                    FragmentActivity activity5 = EPGDetailsFragment.this.getActivity();
                    BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) (activity5 instanceof BaseFragmentActivity ? activity5 : null);
                    if (baseFragmentActivity4 != null) {
                        baseFragmentActivity4.setBlockInput(false);
                    }
                    EPGDetailsViewModel.Result detailsResult2 = EPGDetailsFragment.this.getDetailsResult();
                    if (detailsResult2 == null) {
                        Intrinsics.throwNpe();
                    }
                    detailsResult2.setRecording(tVRecording);
                    EPGDetailsFragment.this.createOrUpdateActionsRow();
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$6.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable error) {
                    FragmentActivity activity4 = EPGDetailsFragment.this.getActivity();
                    if (!(activity4 instanceof BaseFragmentActivity)) {
                        activity4 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity3 = (BaseFragmentActivity) activity4;
                    if (baseFragmentActivity3 != null) {
                        baseFragmentActivity3.setLoaderVisible(false);
                    }
                    FragmentActivity activity5 = EPGDetailsFragment.this.getActivity();
                    if (!(activity5 instanceof BaseFragmentActivity)) {
                        activity5 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) activity5;
                    if (baseFragmentActivity4 != null) {
                        baseFragmentActivity4.setBlockInput(false);
                    }
                    if (error.getCause() instanceof RightVBackendErrorException) {
                        Throwable cause = error.getCause();
                        if (cause == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVBackendErrorException");
                        }
                        if (Intrinsics.areEqual(((RightVBackendErrorException) cause).getBackendCode(), "USER_NOT_SUBSCRIBED_TO_CHANNEL")) {
                            FragmentActivity activity6 = EPGDetailsFragment.this.getActivity();
                            if (activity6 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
                            }
                            new PortalDialog.Builder(activity6).setMessage(R.string.unset_channel_message).setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$.inlined.let.lambda.6.2.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i) {
                                    DSLog.INSTANCE.a("Clicked on SI on subscription dialog");
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("megacable://com.dotscreen.megacable.portal/rightvpackages?package_id=");
                                    EPGDetailsViewModel.Result detailsResult2 = EPGDetailsFragment.this.getDetailsResult();
                                    if (detailsResult2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    Channel channel = detailsResult2.getAiring().getChannel();
                                    if (channel == null) {
                                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                                    }
                                    sb.append(((RightVChannel) channel).getPackages().get(0));
                                    EPGDetailsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
                                    dialogInterface.dismiss();
                                }
                            }).setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$1$17$2$2
                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i) {
                                    DSLog.INSTANCE.a("Clicked on NO on subscription dialog");
                                    dialogInterface.dismiss();
                                }
                            }).create().show();
                            return;
                        }
                    }
                    FragmentActivity activity7 = EPGDetailsFragment.this.getActivity();
                    if (!(activity7 instanceof BaseFragmentActivity)) {
                        activity7 = null;
                    }
                    BaseFragmentActivity baseFragmentActivity5 = (BaseFragmentActivity) activity7;
                    if (baseFragmentActivity5 != null) {
                        Intrinsics.checkExpressionValueIsNotNull(error, "error");
                        BaseFragmentActivity.displayErrorMessage$default(baseFragmentActivity5, error, 0, 2, null);
                    }
                }
            })) == null) {
                return;
            }
            FragmentActivity activity4 = EPGDetailsFragment.this.getActivity();
            if (activity4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
            }
            LifecycleAwareDisposableKt.addTo(disposableSubscribe, ((BaseFragmentActivity) activity4).getDisposable());
        }
    }
}
