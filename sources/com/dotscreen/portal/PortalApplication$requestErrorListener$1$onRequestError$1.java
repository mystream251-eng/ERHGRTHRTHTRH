package com.dotscreen.portal;

import android.os.PowerManager;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.network.BackendReachability;
import com.dotscreen.farkit.network.IConnectivityStateChanged;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.network.UnreachabilityReason;
import com.dotscreen.portal.utils.String_ExtensionKt;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.utils.CommonUtils;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: PortalApplication.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.dotscreen.portal.PortalApplication$requestErrorListener$1$onRequestError$1", f = "PortalApplication.kt", i = {0}, l = {946}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
final class PortalApplication$requestErrorListener$1$onRequestError$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Throwable $error;
    final /* synthetic */ int $randomId;
    final /* synthetic */ URL $url;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ PortalApplication$requestErrorListener$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PortalApplication$requestErrorListener$1$onRequestError$1(PortalApplication$requestErrorListener$1 portalApplication$requestErrorListener$1, int i, URL url, Throwable th, Continuation continuation) {
        super(2, continuation);
        this.this$0 = portalApplication$requestErrorListener$1;
        this.$randomId = i;
        this.$url = url;
        this.$error = th;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PortalApplication$requestErrorListener$1$onRequestError$1 portalApplication$requestErrorListener$1$onRequestError$1 = new PortalApplication$requestErrorListener$1$onRequestError$1(this.this$0, this.$randomId, this.$url, this.$error, completion);
        portalApplication$requestErrorListener$1$onRequestError$1.p$ = (CoroutineScope) obj;
        return portalApplication$requestErrorListener$1$onRequestError$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PortalApplication$requestErrorListener$1$onRequestError$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            NetworkStateManager networkStateManagerAccess$getNetworkStateManager$p = PortalApplication.access$getNetworkStateManager$p(this.this$0.this$0);
            boolean z = this.this$0.this$0.getErrorDuringBootSequence() != null;
            Object systemService = this.this$0.this$0.getApplicationContext().getSystemService("power");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
            }
            int i2 = this.$randomId;
            URL url = this.$url;
            Throwable th = this.$error;
            long epochMilli = Instant.now().toEpochMilli();
            List listListOf = CollectionsKt.listOf((Object[]) new IConnectivityStateChanged[]{this.this$0.this$0.getConnectivityStatusReportManager(), new IConnectivityStateChanged() { // from class: com.dotscreen.portal.PortalApplication$requestErrorListener$1$onRequestError$1.1
                @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
                public void doIfAllOperationsCancelled(long totalTimeElapsed) {
                    PortalApplication portalApplication = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                    String TAG = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    portalApplication.dropBreadcrumb(TAG, "doIfAllOperationsCancelled() - time elapsed since request error: " + String_ExtensionKt.convertMillisToDuration(totalTimeElapsed));
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.ignoreRequestErrors = false;
                }

                @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
                public void doIfInternetAvailable(NetworkStatus status, boolean isNetworkValidated, long totalTimeElapsed) {
                    Intrinsics.checkParameterIsNotNull(status, "status");
                    PortalApplication portalApplication = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                    String TAG = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    portalApplication.dropBreadcrumb(TAG, "doIfInternetAvailable() - time elapsed since request error: " + String_ExtensionKt.convertMillisToDuration(totalTimeElapsed));
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.showDebugToastMessage("doIfInternetAvailable");
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.connectedToInternet = true;
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.broadcastNewNetworkStatus(status);
                    if (isNetworkValidated && (status instanceof NetworkStatus.ConnectedNoBackendAccess) && ((NetworkStatus.ConnectedNoBackendAccess) status).getReason() == UnreachabilityReason.SSLCannotBeValidated) {
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.showDebugToastMessage("Stuck in SSL error loop");
                        PortalApplication portalApplication2 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                        String TAG2 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                        portalApplication2.dropBreadcrumb(TAG2, "Stuck in SSL error loop");
                        PortalApplication portalApplication3 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                        String TAG3 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                        portalApplication3.dropBreadcrumb(TAG3, "Application will be relaunched");
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.restartApplicationWithPopupOrPlanForLater();
                    }
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.updateFloatingStatusView(status);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
                public void doIfBackendOK(NetworkStatus status, long totalTimeElapsed) {
                    Intrinsics.checkParameterIsNotNull(status, "status");
                    PortalApplication portalApplication = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                    String TAG = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    portalApplication.dropBreadcrumb(TAG, "doIfBackendOK() - time elapsed since request error: " + String_ExtensionKt.convertMillisToDuration(totalTimeElapsed));
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.showDebugToastMessage("doIfBackendOK");
                    if (PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.isDegradedModeFeatureEnabled) {
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.degradedMode = false;
                    }
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.backendAvailable = true;
                    PortalApplication.Config config = (PortalApplication.Config) PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.configLiveData.getValue();
                    if ((config != null ? config.getUser() : null) == null) {
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.showDebugToastMessage("User login failed, Relaunching Megacable Portal");
                        PortalApplication portalApplication2 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                        String TAG2 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                        portalApplication2.dropBreadcrumb(TAG2, "Application will be relaunched");
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.restartApplicationWithPopupOrPlanForLater();
                    }
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.broadcastNewNetworkStatus(status);
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.ignoreRequestErrors = false;
                    if (PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.shouldRefreshView) {
                        PortalApplication portalApplication3 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                        String TAG3 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                        portalApplication3.dropBreadcrumb(TAG3, "Current activity will be recreated");
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.shouldRefreshView = false;
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.restartApplicationWithPopupOrPlanForLater();
                    }
                    Job job = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.checkNetworkJob;
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.checkNetworkJob = (Job) null;
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.updateFloatingStatusView(status);
                }

                @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
                public void doIfNoInternet(NetworkStatus status, long totalTimeElapsed) {
                    Intrinsics.checkParameterIsNotNull(status, "status");
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.noInternetProbesFeature.execute();
                    PortalApplication portalApplication = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                    String TAG = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    portalApplication.dropBreadcrumb(TAG, "doIfNoInternet() - time elapsed since request error: " + String_ExtensionKt.convertMillisToDuration(totalTimeElapsed));
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.showDebugToastMessage("doIfNoInternet");
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.connectedToInternet = false;
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.backendAvailable = false;
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.shouldRefreshView = true;
                    if (PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.isDegradedModeFeatureEnabled) {
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.closeLiveApplication(!CommonUtils.isDVBCSTB(PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.getApplicationContext()));
                        if (PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.degradedMode || (CommonUtils.isDVBCSTB(PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.getApplicationContext()) && !PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.portalIsInForeground)) {
                            if (!PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.degradedMode && CommonUtils.isDVBCSTB(PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.getApplicationContext()) && !PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.portalIsInForeground) {
                                PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.appShouldDegradeOnResume = true;
                            }
                        } else {
                            PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.refreshHome();
                        }
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.degradedMode = true;
                    }
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.broadcastNewNetworkStatus(status);
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.updateFloatingStatusView(status);
                }

                @Override // com.dotscreen.farkit.network.IConnectivityStateChanged
                public void doIfNoBackend(URL url2, Throwable error, NetworkStatus.ConnectedNoBackendAccess status, long totalTimeElapsed, BackendReachability reachability) {
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    Intrinsics.checkParameterIsNotNull(status, "status");
                    Intrinsics.checkParameterIsNotNull(reachability, "reachability");
                    PortalApplication portalApplication = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                    String TAG = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    portalApplication.dropBreadcrumb(TAG, "doIfNoBackend() - time elapsed since request error: " + String_ExtensionKt.convertMillisToDuration(totalTimeElapsed));
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.showDebugToastMessage("doIfNoBackend");
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.backendAvailable = false;
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.shouldRefreshView = true;
                    if (PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.isDegradedModeFeatureEnabled) {
                        PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.closeLiveApplication(!CommonUtils.isDVBCSTB(PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.getApplicationContext()));
                        if (PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.degradedMode || (CommonUtils.isDVBCSTB(PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.getApplicationContext()) && !PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.portalIsInForeground)) {
                            if (!PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.degradedMode && CommonUtils.isDVBCSTB(PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.getApplicationContext()) && !PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.portalIsInForeground) {
                                PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.appShouldDegradeOnResume = true;
                            }
                        } else {
                            PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.refreshHome();
                        }
                    }
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.degradedMode = true;
                    NetworkStatus.ConnectedNoBackendAccess connectedNoBackendAccess = status;
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.broadcastNewNetworkStatus(connectedNoBackendAccess);
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.updateFloatingStatusView(connectedNoBackendAccess);
                }
            }});
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
            long j = this.this$0.this$0.backendCheckInitialDelay;
            Long lBoxLong = Boxing.boxLong(3L);
            TimeUnit timeUnit = TimeUnit.MINUTES;
            Function2<String, String, Unit> function2 = new Function2<String, String, Unit>() { // from class: com.dotscreen.portal.PortalApplication$requestErrorListener$1$onRequestError$1.3
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                    invoke2(str, str2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String tag, String message) {
                    Intrinsics.checkParameterIsNotNull(tag, "tag");
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.dropBreadcrumb(tag, message);
                }
            };
            this.L$0 = coroutineScope;
            this.label = 1;
            if (networkStateManagerAccess$getNetworkStateManager$p.handleNetworkError(z, (2 & 2) != 0 ? 0 : 0, (PowerManager) systemService, i2, url, th, epochMilli, listListOf, anonymousClass2, j, 1L, (2 & 2048) != 0 ? (Long) null : lBoxLong, timeUnit, function2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.PortalApplication$requestErrorListener$1$onRequestError$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lcom/dotscreen/farkit/network/BackendReachability;", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.PortalApplication$requestErrorListener$1$onRequestError$1$2", f = "PortalApplication.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super BackendReachability>, Object> {
        int label;

        AnonymousClass2(Continuation continuation) {
            super(1, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            return PortalApplication$requestErrorListener$1$onRequestError$1.this.new AnonymousClass2(completion);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super BackendReachability> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                long time = new Date().getTime();
                BOService bOService = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.boService;
                UserService userService = bOService != null ? bOService.getUserService() : null;
                if (userService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                BackendReachability backendReachabilityBlockingGet = ((MegacableUserService) userService).backendIsReachable().blockingGet();
                long time2 = new Date().getTime();
                PortalApplication portalApplication = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                String TAG = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                StringBuilder sb = new StringBuilder();
                sb.append("Backend check completed in : ");
                long j = time2 - time;
                sb.append(String_ExtensionKt.convertMillisToDuration(j));
                portalApplication.dropBreadcrumb(TAG, sb.toString());
                PortalApplication portalApplication2 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0;
                String TAG2 = PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                portalApplication2.dropBreadcrumb(TAG2, "Backend reachability details: " + CollectionsKt.joinToString$default(backendReachabilityBlockingGet.getBackends(), null, null, null, 0, null, new Function1<BackendReachability.BackendStatus, String>() { // from class: com.dotscreen.portal.PortalApplication.requestErrorListener.1.onRequestError.1.2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(BackendReachability.BackendStatus it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return "\n  -  " + String_ExtensionKt.removeLineReturns(it.toString());
                    }
                }, 31, null));
                PortalApplication$requestErrorListener$1$onRequestError$1.this.this$0.this$0.showDebugToastMessage("Backend check completed in : " + String_ExtensionKt.convertMillisToDuration(j));
                return backendReachabilityBlockingGet;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
