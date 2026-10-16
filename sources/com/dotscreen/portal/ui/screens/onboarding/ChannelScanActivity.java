package com.dotscreen.portal.ui.screens.onboarding;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import com.dotscreen.tvinput.SyncTVChannelsWorkerKt;
import io.sentry.protocol.App;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: ChannelScanActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000I\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\u00142\b\b\u0002\u0010\u0019\u001a\u00020\tJ\b\u0010\u001a\u001a\u00020\u0014H\u0014J\b\u0010\u001b\u001a\u00020\u0014H\u0014R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/ui/screens/onboarding/ChannelScanActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", App.TYPE, "Lcom/dotscreen/portal/PortalApplication;", "doScan", "", "handler", "Landroid/os/Handler;", "messageTextView", "Landroid/widget/TextView;", "progressBar", "Landroid/widget/ProgressBar;", "scanStatusReceiver", "com/dotscreen/portal/ui/screens/onboarding/ChannelScanActivity$scanStatusReceiver$1", "Lcom/dotscreen/portal/ui/screens/onboarding/ChannelScanActivity$scanStatusReceiver$1;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "noConnectionError", "onStart", "onStop", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ChannelScanActivity extends BaseFragmentActivity {
    private HashMap _$_findViewCache;
    private PortalApplication app;
    private TextView messageTextView;
    private ProgressBar progressBar;
    private final String TAG = ChannelScanActivity.class.getSimpleName();
    private boolean doScan = true;
    private final Handler handler = new Handler();
    private final ChannelScanActivity$scanStatusReceiver$1 scanStatusReceiver = new ChannelScanActivity$scanStatusReceiver$1(this);

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
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

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    public static final /* synthetic */ PortalApplication access$getApp$p(ChannelScanActivity channelScanActivity) {
        PortalApplication portalApplication = channelScanActivity.app;
        if (portalApplication == null) {
            Intrinsics.throwUninitializedPropertyAccessException(App.TYPE);
        }
        return portalApplication;
    }

    public static final /* synthetic */ TextView access$getMessageTextView$p(ChannelScanActivity channelScanActivity) {
        TextView textView = channelScanActivity.messageTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageTextView");
        }
        return textView;
    }

    public static final /* synthetic */ ProgressBar access$getProgressBar$p(ChannelScanActivity channelScanActivity) {
        ProgressBar progressBar = channelScanActivity.progressBar;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        return progressBar;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) applicationContext;
        this.app = portalApplication;
        if (portalApplication == null) {
            Intrinsics.throwUninitializedPropertyAccessException(App.TYPE);
        }
        portalApplication.setMustShowChannelScanning(false);
        PortalApplication portalApplication2 = this.app;
        if (portalApplication2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(App.TYPE);
        }
        portalApplication2.setChannelScanning(true);
        if (displaySerialNumberEditDialogIfNeeded()) {
            this.doScan = false;
            return;
        }
        setContentView(R.layout.activity_channel_scan);
        View viewFindViewById = findViewById(R.id.message);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.message)");
        this.messageTextView = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.progress);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.progress)");
        this.progressBar = (ProgressBar) viewFindViewById2;
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        if (intent.getAction() == null) {
            Log.v(this.TAG, "Set Network status listener");
            PortalApplication portalApplication3 = this.app;
            if (portalApplication3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(App.TYPE);
            }
            NetworkStatus value = portalApplication3.getNetworkStateManager().getDeviceStatus().getValue();
            if (value != null) {
                Log.d(this.TAG, "Instant NetworkStatus = " + value);
                if (value instanceof NetworkStatus.Connected) {
                    return;
                }
                onError(true);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.doScan) {
            registerReceiver(this.scanStatusReceiver, new IntentFilter(CommonUtils.ACTION_SCAN_STATUS_CHANGED));
            SyncTVChannelsWorkerKt.scheduleSyncingTVChannels$default(this, null, true, false, 10, null);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.doScan) {
            unregisterReceiver(this.scanStatusReceiver);
        }
    }

    public static /* synthetic */ void onError$default(ChannelScanActivity channelScanActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        channelScanActivity.onError(z);
    }

    public final void onError(boolean noConnectionError) {
        if (noConnectionError) {
            TextView textView = this.messageTextView;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageTextView");
            }
            textView.setText(getString(R.string.connectivity_issue));
            ProgressBar progressBar = this.progressBar;
            if (progressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            }
            progressBar.setVisibility(4);
            Toast.makeText(getApplicationContext(), R.string.noAvailable, 1).show();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
            return;
        }
        TextView textView2 = this.messageTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageTextView");
        }
        textView2.setText(getString(R.string.channel_scan_message_scan_error));
        ProgressBar progressBar2 = this.progressBar;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        progressBar2.setVisibility(4);
        Dialog dialogCreate = new PortalDialog.Builder(this).setMessage(R.string.channel_scan_error_title).setPositiveButton(R.string.channel_scan_error_retry, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.onboarding.ChannelScanActivity$onError$dialog$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SyncTVChannelsWorkerKt.scheduleSyncingTVChannels$default(this.this$0, null, true, false, 10, null);
            }
        }).setNegativeButton(R.string.channel_scan_error_skip, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.onboarding.ChannelScanActivity$onError$dialog$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.this$0.setResult(0);
                ChannelScanActivity.access$getApp$p(this.this$0).setChannelScanning(false);
                this.this$0.finish();
            }
        }).create();
        dialogCreate.setCancelable(false);
        dialogCreate.show();
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.onboarding.ChannelScanActivity$onError$1, reason: invalid class name */
    /* JADX INFO: compiled from: ChannelScanActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.ui.screens.onboarding.ChannelScanActivity$onError$1", f = "ChannelScanActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = ChannelScanActivity.this.new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws InterruptedException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Thread.sleep(3000L);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01381(null), 2, null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.onboarding.ChannelScanActivity$onError$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ChannelScanActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.portal.ui.screens.onboarding.ChannelScanActivity$onError$1$1", f = "ChannelScanActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01381 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01381(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01381 c01381 = AnonymousClass1.this.new C01381(completion);
                c01381.p$ = (CoroutineScope) obj;
                return c01381;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01381) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ChannelScanActivity.this.setResult(0);
                ChannelScanActivity.this.finish();
                return Unit.INSTANCE;
            }
        }
    }
}
