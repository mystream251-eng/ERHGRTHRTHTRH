package com.dotscreen.portal.ui.screens.details;

import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.model.TVRecordingStatus;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.player.PlaybackActivityKt;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.RightVServiceSubscriptionInfo;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EPGDetailsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "ls", "", "Lcom/dotscreen/tv/common/bokit/RightVServiceSubscriptionInfo;", "kotlin.jvm.PlatformType", "", "accept", "com/dotscreen/portal/ui/screens/details/EPGDetailsFragment$ActionBroadcastReceiver$onReceive$1$14"}, k = 3, mv = {1, 1, 16})
final class EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$5<T> implements Consumer<List<RightVServiceSubscriptionInfo>> {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ EPGDetailsFragment.ActionBroadcastReceiver this$0;

    EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$5(EPGDetailsFragment.ActionBroadcastReceiver actionBroadcastReceiver, Context context) {
        this.this$0 = actionBroadcastReceiver;
        this.$context$inlined = context;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(List<RightVServiceSubscriptionInfo> list) {
        if (list.isEmpty()) {
            FragmentActivity activity = EPGDetailsFragment.this.getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
            new PortalDialog.Builder(activity).setTitle(R.string.package_service_not_subscribed_popup_title).setMessage(R.string.package_service_not_subscribed_popup_subtitle).create().show();
            return;
        }
        EPGDetailsViewModel.Result detailsResult = EPGDetailsFragment.this.getDetailsResult();
        if (detailsResult == null) {
            Intrinsics.throwNpe();
        }
        TVRecording recording = detailsResult.getRecording();
        if (recording == null) {
            Intrinsics.throwNpe();
        }
        if (recording.getStatus() == TVRecordingStatus.Deleted) {
            Context context = EPGDetailsFragment.this.getContext();
            Context context2 = EPGDetailsFragment.this.getContext();
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            Toast.makeText(context, context2.getString(R.string.error_content_expired), 1).show();
            return;
        }
        FragmentActivity activity2 = EPGDetailsFragment.this.getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity2, "activity!!");
        FragmentActivity fragmentActivity = activity2;
        EPGDetailsViewModel.Result detailsResult2 = EPGDetailsFragment.this.getDetailsResult();
        if (detailsResult2 == null) {
            Intrinsics.throwNpe();
        }
        TVRecording recording2 = detailsResult2.getRecording();
        if (recording2 == null) {
            Intrinsics.throwNpe();
        }
        PlaybackActivityKt.displayResumePopupOrStartPVRPlayback(fragmentActivity, recording2.getId(), new Function0<Unit>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$5.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                EPGDetailsViewModel.Result detailsResult3 = EPGDetailsFragment.this.getDetailsResult();
                if (detailsResult3 == null) {
                    Intrinsics.throwNpe();
                }
                detailsResult3.getProgram().getOffers().filter(new Predicate<Map<String, ? extends Object>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$1$14$1$1
                    @Override // io.reactivex.functions.Predicate
                    public final boolean test(Map<String, ? extends Object> it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return it.containsKey("rightv");
                    }
                }).flatMapCompletable(new Function<Map<String, ? extends Object>, CompletableSource>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsFragment$ActionBroadcastReceiver$onReceive$.inlined.let.lambda.5.1.1
                    @Override // io.reactivex.functions.Function
                    public final CompletableSource apply(Map<String, ? extends Object> offer) {
                        Intrinsics.checkParameterIsNotNull(offer, "offer");
                        Object obj = offer.get("rightv");
                        if (!(obj instanceof Map)) {
                            obj = null;
                        }
                        Map map = (Map) obj;
                        String str = map != null ? (String) map.get("program_external_id") : null;
                        if (str != null) {
                            PortalApplication.Companion companion = PortalApplication.INSTANCE;
                            Context context3 = EPGDetailsFragment$ActionBroadcastReceiver$onReceive$$inlined$let$lambda$5.this.$context$inlined;
                            if (context3 == null) {
                                Intrinsics.throwNpe();
                            }
                            LiveTVService liveTVService = companion.getSingleton(context3).getBOService().getLiveTVService();
                            if (liveTVService == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                            }
                            return ((MegacableLiveTVService) liveTVService).registerPlayProgramEvent(str);
                        }
                        return Completable.complete();
                    }
                }).subscribe();
            }
        });
    }
}
