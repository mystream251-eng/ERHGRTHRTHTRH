package com.dotscreen.farkit.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.CombineKt;

/* JADX INFO: compiled from: NetworkStateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001cB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000eH\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020\u0005J\n\u0010)\u001a\u0004\u0018\u00010*H\u0007J\n\u0010+\u001a\u0004\u0018\u00010,H\u0002J\u0006\u0010-\u001a\u00020#J\n\u0010.\u001a\u0004\u0018\u00010/H\u0007J\n\u00100\u001a\u0004\u0018\u000101H\u0007JÕ\u0001\u00102\u001a\u00020'2\u0006\u00103\u001a\u00020\u00052\b\b\u0002\u00104\u001a\u00020%2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020%2\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u000e2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>2\u001c\u0010@\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0B\u0012\u0006\u0012\u0004\u0018\u00010D0A2\u0006\u0010E\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u000e2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u000e2\u0006\u0010H\u001a\u00020I26\u0010J\u001a2\u0012\u0013\u0012\u00110 ¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110 ¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(O\u0012\u0004\u0012\u00020'0KH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010PJ\u0006\u0010Q\u001a\u00020\u0005J-\u0010R\u001a\u0016\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020%\u0012\u0006\u0012\u0004\u0018\u00010 0S2\u0006\u0010T\u001a\u00020 H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010UJ\r\u0010V\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010WJ\u0006\u0010X\u001a\u00020\u0005J \u0010Y\u001a\u00020'2\u0006\u0010Z\u001a\u00020\u00052\u0006\u0010[\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0005H\u0002J\u0012\u0010\\\u001a\u00020'2\b\u0010]\u001a\u0004\u0018\u00010\u0018H\u0016J\u0019\u0010^\u001a\u00020_2\u0006\u00108\u001a\u00020 H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010UJ(\u0010`\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00052\n\b\u0002\u0010a\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010/H\u0002R\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00060\u001aR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006d"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkStateManager;", "Landroid/telephony/PhoneStateListener;", "context", "Landroid/content/Context;", "enableFullReport", "", "(Landroid/content/Context;Z)V", "appStatus", "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/dotscreen/farkit/network/NetworkStatus;", "kotlin.jvm.PlatformType", "getAppStatus", "()Lio/reactivex/subjects/BehaviorSubject;", "checkCapacityDelay", "", "getContext", "()Landroid/content/Context;", "deviceStatus", "getDeviceStatus", "getEnableFullReport", "()Z", "manager", "Landroid/net/ConnectivityManager;", "mobileSignalStrength", "Landroid/telephony/SignalStrength;", "networkListener", "Lcom/dotscreen/farkit/network/NetworkStateManager$NetworkListener;", "pingDisposable", "Lio/reactivex/disposables/Disposable;", "pingJob", "Lkotlinx/coroutines/Job;", "convertMillisToDuration", "", "millis", "convertType", "Lcom/dotscreen/farkit/network/NetworkType;", "type", "", "forceUpdateStatus", "", "completeCheck", "getActiveNetwork", "Landroid/net/Network;", "getActiveNetworkInfo", "Landroid/net/NetworkInfo;", "getActiveNetworkType", "getCurrentActiveNetworkCapabilities", "Landroid/net/NetworkCapabilities;", "getCurrentLinkProperties", "Landroid/net/LinkProperties;", "handleNetworkError", "skipDelay", "iterationCount", "powerManager", "Landroid/os/PowerManager;", "randomId", ImagesContract.URL, "Ljava/net/URL;", "error", "", "errorTimestamp", "listeners", "", "Lcom/dotscreen/farkit/network/IConnectivityStateChanged;", "isBackendReachable", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lcom/dotscreen/farkit/network/BackendReachability;", "", "backendCheckInitialDelay", "backendRecheckMinDelay", "backendRecheckMaxDelay", "delayTimeUnit", "Ljava/util/concurrent/TimeUnit;", "breadcrumbHandler", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", NotificationsContract.COLUMN_TAG, "message", "(ZILandroid/os/PowerManager;ILjava/net/URL;Ljava/lang/Throwable;JLjava/util/List;Lkotlin/jvm/functions/Function1;JJLjava/lang/Long;Ljava/util/concurrent/TimeUnit;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasInternetCapability", "httpPing", "Lkotlin/Triple;", "link", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "internetIsReachable", "()Ljava/lang/Boolean;", "isValidated", "notifyInternetAvailability", "available", "networkType", "onSignalStrengthsChanged", "signalStrength", "ping", "Lcom/dotscreen/farkit/network/PingData;", "updateStatus", "network", "networkCapabilities", "NetworkListener", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class NetworkStateManager extends PhoneStateListener {
    private final BehaviorSubject<NetworkStatus> appStatus;
    private final long checkCapacityDelay;
    private final Context context;
    private final BehaviorSubject<NetworkStatus> deviceStatus;
    private final boolean enableFullReport;
    private final ConnectivityManager manager;
    private SignalStrength mobileSignalStrength;
    private final NetworkListener networkListener;
    private Disposable pingDisposable;
    private Job pingJob;

    /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u001c\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00132\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0019\u001a\u00020\u001a26\u0010\u001b\u001a2\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\"0\u001c2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u0014H\u0086@"}, d2 = {"handleNetworkError", "", "skipDelay", "", "iterationCount", "", "powerManager", "Landroid/os/PowerManager;", "randomId", ImagesContract.URL, "Ljava/net/URL;", "error", "", "errorTimestamp", "", "listeners", "", "Lcom/dotscreen/farkit/network/IConnectivityStateChanged;", "isBackendReachable", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lcom/dotscreen/farkit/network/BackendReachability;", "backendCheckInitialDelay", "backendRecheckMinDelay", "backendRecheckMaxDelay", "delayTimeUnit", "Ljava/util/concurrent/TimeUnit;", "breadcrumbHandler", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", NotificationsContract.COLUMN_TAG, "message", "", "continuation"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager", f = "NetworkStateManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {544, 578, 589, 607}, m = "handleNetworkError", n = {"this", "skipDelay", "iterationCount", "powerManager", "randomId", ImagesContract.URL, "error", "errorTimestamp", "listeners", "isBackendReachable", "backendCheckInitialDelay", "backendRecheckMinDelay", "backendRecheckMaxDelay", "delayTimeUnit", "breadcrumbHandler", "internalTag", "$fun$backendCheckLoop$2", "$fun$waitForInternetCapability$3", "this", "skipDelay", "iterationCount", "powerManager", "randomId", ImagesContract.URL, "error", "errorTimestamp", "listeners", "isBackendReachable", "backendCheckInitialDelay", "backendRecheckMinDelay", "backendRecheckMaxDelay", "delayTimeUnit", "breadcrumbHandler", "internalTag", "$fun$backendCheckLoop$2", "$fun$waitForInternetCapability$3", "latestStatus", "this", "skipDelay", "iterationCount", "powerManager", "randomId", ImagesContract.URL, "error", "errorTimestamp", "listeners", "isBackendReachable", "backendCheckInitialDelay", "backendRecheckMinDelay", "backendRecheckMaxDelay", "delayTimeUnit", "breadcrumbHandler", "internalTag", "$fun$backendCheckLoop$2", "$fun$waitForInternetCapability$3", "latestStatus", "this", "skipDelay", "iterationCount", "powerManager", "randomId", ImagesContract.URL, "error", "errorTimestamp", "listeners", "isBackendReachable", "backendCheckInitialDelay", "backendRecheckMinDelay", "backendRecheckMaxDelay", "delayTimeUnit", "breadcrumbHandler", "internalTag", "$fun$backendCheckLoop$2", "$fun$waitForInternetCapability$3", "networkType", "newStatus"}, s = {"L$0", "Z$0", "I$0", "L$1", "I$1", "L$2", "L$3", "J$0", "L$4", "L$5", "J$1", "J$2", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$0", "Z$0", "I$0", "L$1", "I$1", "L$2", "L$3", "J$0", "L$4", "L$5", "J$1", "J$2", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$0", "Z$0", "I$0", "L$1", "I$1", "L$2", "L$3", "J$0", "L$4", "L$5", "J$1", "J$2", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$0", "Z$0", "I$0", "L$1", "I$1", "L$2", "L$3", "J$0", "L$4", "L$5", "J$1", "J$2", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13"})
    static final class C02551 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C02551(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NetworkStateManager.this.handleNetworkError(false, 0, null, 0, null, null, 0L, null, null, 0L, 0L, null, null, null, this);
        }
    }

    public NetworkStateManager(Context context, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.enableFullReport = z;
        BehaviorSubject<NetworkStatus> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(NetworkStatus.NotConnected.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(behaviorSubjectCreateDefault, "BehaviorSubject.createDe…tworkStatus.NotConnected)");
        this.appStatus = behaviorSubjectCreateDefault;
        BehaviorSubject<NetworkStatus> behaviorSubjectCreateDefault2 = BehaviorSubject.createDefault(NetworkStatus.NotConnected.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(behaviorSubjectCreateDefault2, "BehaviorSubject.createDe…tworkStatus.NotConnected)");
        this.deviceStatus = behaviorSubjectCreateDefault2;
        Object systemService = this.context.getSystemService("connectivity");
        this.manager = (ConnectivityManager) (systemService instanceof ConnectivityManager ? systemService : null);
        this.networkListener = new NetworkListener();
        Object systemService2 = this.context.getSystemService("phone");
        if (systemService2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        ((TelephonyManager) systemService2).listen(this, 256);
        if (Build.VERSION.SDK_INT >= 24) {
            ConnectivityManager connectivityManager = this.manager;
            if (connectivityManager != null) {
                connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() { // from class: com.dotscreen.farkit.network.NetworkStateManager.1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onAvailable(Network network) {
                        super.onAvailable(network);
                        Log.d("NetworkStateManager", "NetworkCallback.onAvailable(network: " + network + ')');
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                        super.onCapabilitiesChanged(network, networkCapabilities);
                        Log.d("NetworkStateManager", "NetworkCallback.onCapabilitiesChanged(network: " + network + ", networkCapabilities: " + networkCapabilities + ')');
                        NetworkStateManager.this.updateStatus(false, network, networkCapabilities);
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                        super.onLinkPropertiesChanged(network, linkProperties);
                        Log.d("NetworkStateManager", "NetworkCallback.onLinkPropertiesChanged(network: " + network + ", linkProperties: " + linkProperties + ')');
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLosing(Network network, int maxMsToLive) {
                        super.onLosing(network, maxMsToLive);
                        Log.d("NetworkStateManager", "NetworkCallback.onLosing(network: " + network + ", maxMsToLive: " + maxMsToLive + ')');
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLost(Network network) {
                        super.onLost(network);
                        Log.d("NetworkStateManager", "NetworkCallback.onLost(network: " + network + ')');
                        NetworkStateManager.updateStatus$default(NetworkStateManager.this, false, null, null, 6, null);
                    }
                });
            }
        } else {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.context.registerReceiver(this.networkListener, intentFilter);
        }
        this.checkCapacityDelay = 1000L;
    }

    public /* synthetic */ NetworkStateManager(Context context, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? false : z);
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getEnableFullReport() {
        return this.enableFullReport;
    }

    public final BehaviorSubject<NetworkStatus> getAppStatus() {
        return this.appStatus;
    }

    public final BehaviorSubject<NetworkStatus> getDeviceStatus() {
        return this.deviceStatus;
    }

    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/farkit/network/NetworkStateManager$NetworkListener;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/farkit/network/NetworkStateManager;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "farkit_release"}, k = 1, mv = {1, 1, 16})
    private final class NetworkListener extends BroadcastReceiver {
        public NetworkListener() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            Log.d("NetworkStateManager", "onReceive: " + intent);
            if (intent != null && (extras = intent.getExtras()) != null) {
                Set<String> setKeySet = extras.keySet();
                Intrinsics.checkExpressionValueIsNotNull(setKeySet, "extras.keySet()");
                for (String str : setKeySet) {
                    Log.d("NetworkStateManager", "    \"" + str + "\" -> " + extras.get(str));
                }
            }
            NetworkStateManager.updateStatus$default(NetworkStateManager.this, false, null, null, 6, null);
        }
    }

    @Override // android.telephony.PhoneStateListener
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        Log.d("NetworkStateManager", "onSignalStrengthsChanged: " + signalStrength);
        this.mobileSignalStrength = signalStrength;
    }

    private final NetworkType convertType(int type) {
        if (type == 0) {
            return NetworkType.Mobile;
        }
        if (type == 1) {
            return NetworkType.Wifi;
        }
        if (type == 9) {
            return NetworkType.Ethernet;
        }
        return NetworkType.Unknown;
    }

    static /* synthetic */ void updateStatus$default(NetworkStateManager networkStateManager, boolean z, Network network, NetworkCapabilities networkCapabilities, int i, Object obj) {
        if ((i & 2) != 0) {
            network = (Network) null;
        }
        if ((i & 4) != 0) {
            networkCapabilities = (NetworkCapabilities) null;
        }
        networkStateManager.updateStatus(z, network, networkCapabilities);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateStatus(boolean completeCheck, Network network, NetworkCapabilities networkCapabilities) {
        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("NetworkStateManager", "updateStatus: network = " + network + " ; networkCapabilities = " + networkCapabilities);
            if (network != null && networkCapabilities != null) {
                notifyInternetAvailability(networkCapabilities.hasCapability(12), NetworkStateManagerKt.getActiveNetworkType(networkCapabilities), completeCheck);
                return;
            } else {
                this.deviceStatus.onNext(NetworkStatus.NotConnected.INSTANCE);
                return;
            }
        }
        ConnectivityManager connectivityManager = this.manager;
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        Log.d("NetworkStateManager", "updateStatus: networkInfo = " + activeNetworkInfo);
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.isConnected()) {
                notifyInternetAvailability(true, convertType(activeNetworkInfo.getSubtype()), completeCheck);
            } else {
                this.deviceStatus.onNext(NetworkStatus.NotConnected.INSTANCE);
            }
        }
    }

    private final void notifyInternetAvailability(boolean available, NetworkType networkType, boolean completeCheck) {
        Job job = this.pingJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (available) {
            if (completeCheck) {
                this.pingJob = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02561(networkType, null), 2, null);
                return;
            } else {
                this.deviceStatus.onNext(new NetworkStatus.Connected(networkType, null, 2, null));
                return;
            }
        }
        if (this.enableFullReport || completeCheck) {
            this.pingJob = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02572(networkType, null), 2, null);
        } else {
            this.deviceStatus.onNext(new NetworkStatus.ConnectedNoInternet(networkType, null, 2, null));
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1", f = "NetworkStateManager.kt", i = {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5}, l = {244, 245, 246, 247, 248, 680}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "timePingData", "$this$launch", "timePingData", "googlePingData", "$this$launch", "timePingData", "googlePingData", "xviewPingData", "$this$launch", "timePingData", "googlePingData", "xviewPingData", "httpCheckResultFlow", "$this$launch", "timePingData", "googlePingData", "xviewPingData", "httpCheckResultFlow", "httpsCheckResultFlow", "$this$collect$iv"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
    static final class C02561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ NetworkType $networkType;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02561(NetworkType networkType, Continuation continuation) {
            super(2, continuation);
            this.$networkType = networkType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02561 c02561 = NetworkStateManager.this.new C02561(this.$networkType, completion);
            c02561.p$ = (CoroutineScope) obj;
            return c02561;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x00b9 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x00ba  */
        /* JADX WARN: Code duplicated, block: B:21:0x00d5 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:24:0x00ee A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:25:0x00ef  */
        /* JADX WARN: Code duplicated, block: B:28:0x010f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x0146 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Flow flowFlowOf;
            Object objPing;
            CoroutineScope coroutineScope2;
            Flow flow;
            Flow flowFlowOf2;
            Flow flowFlowOf3;
            Object objHttpPing;
            CoroutineScope coroutineScope3;
            Flow flow2;
            Flow flow3;
            Flow flow4;
            Flow flowFlowOf4;
            Flow<List<ServerStatus>> flow5;
            FlowCollector<List<ServerStatus>> flowCollector;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.p$;
                    NetworkStateManager networkStateManager = NetworkStateManager.this;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    obj = networkStateManager.ping("time.android.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowFlowOf = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager2 = NetworkStateManager.this;
                    this.L$0 = coroutineScope;
                    this.L$1 = flowFlowOf;
                    this.label = 2;
                    objPing = networkStateManager2.ping("www.google.com", this);
                    if (objPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope2 = coroutineScope;
                    flow = flowFlowOf;
                    obj = objPing;
                    flowFlowOf2 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager3 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.label = 3;
                    obj = networkStateManager3.ping("xviewplus.com.mx", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowFlowOf3 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager4 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.L$3 = flowFlowOf3;
                    this.label = 4;
                    objHttpPing = networkStateManager4.httpPing("http://connectivitycheck.gstatic.com/generate_204", this);
                    if (objHttpPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope2;
                    flow2 = flowFlowOf2;
                    Flow flow6 = flow;
                    flow3 = flowFlowOf3;
                    obj = objHttpPing;
                    flow4 = flow6;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager5 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager5.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf5 = FlowKt.flowOf(obj);
                    final Flow[] flowArr = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf5};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to Connected");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.Connected(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf5;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 1:
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    flowFlowOf = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager6 = NetworkStateManager.this;
                    this.L$0 = coroutineScope;
                    this.L$1 = flowFlowOf;
                    this.label = 2;
                    objPing = networkStateManager6.ping("www.google.com", this);
                    if (objPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope2 = coroutineScope;
                    flow = flowFlowOf;
                    obj = objPing;
                    flowFlowOf2 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager7 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.label = 3;
                    obj = networkStateManager7.ping("xviewplus.com.mx", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowFlowOf3 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager8 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.L$3 = flowFlowOf3;
                    this.label = 4;
                    objHttpPing = networkStateManager8.httpPing("http://connectivitycheck.gstatic.com/generate_204", this);
                    if (objHttpPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope2;
                    flow2 = flowFlowOf2;
                    Flow flow7 = flow;
                    flow3 = flowFlowOf3;
                    obj = objHttpPing;
                    flow4 = flow7;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager9 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager9.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf6 = FlowKt.flowOf(obj);
                    final Flow[] flowArr2 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf6};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr2, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr2.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to Connected");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.Connected(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf6;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 2:
                    Flow flow8 = (Flow) this.L$1;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope2 = coroutineScope4;
                    flow = flow8;
                    flowFlowOf2 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager10 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.label = 3;
                    obj = networkStateManager10.ping("xviewplus.com.mx", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowFlowOf3 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager11 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.L$3 = flowFlowOf3;
                    this.label = 4;
                    objHttpPing = networkStateManager11.httpPing("http://connectivitycheck.gstatic.com/generate_204", this);
                    if (objHttpPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope2;
                    flow2 = flowFlowOf2;
                    Flow flow9 = flow;
                    flow3 = flowFlowOf3;
                    obj = objHttpPing;
                    flow4 = flow9;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager12 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager12.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf7 = FlowKt.flowOf(obj);
                    final Flow[] flowArr3 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf7};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr3, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr3.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to Connected");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.Connected(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf7;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 3:
                    flowFlowOf2 = (Flow) this.L$2;
                    flow = (Flow) this.L$1;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    flowFlowOf3 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager13 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.L$3 = flowFlowOf3;
                    this.label = 4;
                    objHttpPing = networkStateManager13.httpPing("http://connectivitycheck.gstatic.com/generate_204", this);
                    if (objHttpPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope2;
                    flow2 = flowFlowOf2;
                    Flow flow10 = flow;
                    flow3 = flowFlowOf3;
                    obj = objHttpPing;
                    flow4 = flow10;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager14 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager14.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf8 = FlowKt.flowOf(obj);
                    final Flow[] flowArr4 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf8};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr4, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr4.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to Connected");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.Connected(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf8;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 4:
                    Flow flow11 = (Flow) this.L$3;
                    Flow flow12 = (Flow) this.L$2;
                    Flow flow13 = (Flow) this.L$1;
                    CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope3 = coroutineScope5;
                    flow4 = flow13;
                    flow2 = flow12;
                    flow3 = flow11;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager15 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager15.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf9 = FlowKt.flowOf(obj);
                    final Flow[] flowArr5 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf9};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr5, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr5.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to Connected");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.Connected(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf9;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 5:
                    flowFlowOf4 = (Flow) this.L$4;
                    flow3 = (Flow) this.L$3;
                    flow2 = (Flow) this.L$2;
                    flow4 = (Flow) this.L$1;
                    coroutineScope3 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    Flow flowFlowOf10 = FlowKt.flowOf(obj);
                    final Flow[] flowArr6 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf10};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr6, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr6.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$1$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to Connected");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.Connected(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf10;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 6:
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2", f = "NetworkStateManager.kt", i = {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5}, l = {292, 293, 294, 295, 296, 680}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "timePingData", "$this$launch", "timePingData", "googlePingData", "$this$launch", "timePingData", "googlePingData", "xviewPingData", "$this$launch", "timePingData", "googlePingData", "xviewPingData", "httpCheckResultFlow", "$this$launch", "timePingData", "googlePingData", "xviewPingData", "httpCheckResultFlow", "httpsCheckResultFlow", "$this$collect$iv"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
    static final class C02572 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ NetworkType $networkType;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02572(NetworkType networkType, Continuation continuation) {
            super(2, continuation);
            this.$networkType = networkType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02572 c02572 = NetworkStateManager.this.new C02572(this.$networkType, completion);
            c02572.p$ = (CoroutineScope) obj;
            return c02572;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02572) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x00b9 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x00ba  */
        /* JADX WARN: Code duplicated, block: B:21:0x00d5 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:24:0x00ee A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:25:0x00ef  */
        /* JADX WARN: Code duplicated, block: B:28:0x010f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x0146 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Flow flowFlowOf;
            Object objPing;
            CoroutineScope coroutineScope2;
            Flow flow;
            Flow flowFlowOf2;
            Flow flowFlowOf3;
            Object objHttpPing;
            CoroutineScope coroutineScope3;
            Flow flow2;
            Flow flow3;
            Flow flow4;
            Flow flowFlowOf4;
            Flow<List<ServerStatus>> flow5;
            FlowCollector<List<ServerStatus>> flowCollector;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.p$;
                    NetworkStateManager networkStateManager = NetworkStateManager.this;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    obj = networkStateManager.ping("time.android.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowFlowOf = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager2 = NetworkStateManager.this;
                    this.L$0 = coroutineScope;
                    this.L$1 = flowFlowOf;
                    this.label = 2;
                    objPing = networkStateManager2.ping("www.google.com", this);
                    if (objPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope2 = coroutineScope;
                    flow = flowFlowOf;
                    obj = objPing;
                    flowFlowOf2 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager3 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.label = 3;
                    obj = networkStateManager3.ping("xviewplus.com.mx", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowFlowOf3 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager4 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.L$3 = flowFlowOf3;
                    this.label = 4;
                    objHttpPing = networkStateManager4.httpPing("http://connectivitycheck.gstatic.com/generate_204", this);
                    if (objHttpPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope2;
                    flow2 = flowFlowOf2;
                    Flow flow6 = flow;
                    flow3 = flowFlowOf3;
                    obj = objHttpPing;
                    flow4 = flow6;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager5 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager5.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf5 = FlowKt.flowOf(obj);
                    final Flow[] flowArr = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf5};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to ConnectedNoInternet");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.ConnectedNoInternet(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf5;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 1:
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    flowFlowOf = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager6 = NetworkStateManager.this;
                    this.L$0 = coroutineScope;
                    this.L$1 = flowFlowOf;
                    this.label = 2;
                    objPing = networkStateManager6.ping("www.google.com", this);
                    if (objPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope2 = coroutineScope;
                    flow = flowFlowOf;
                    obj = objPing;
                    flowFlowOf2 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager7 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.label = 3;
                    obj = networkStateManager7.ping("xviewplus.com.mx", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowFlowOf3 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager8 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.L$3 = flowFlowOf3;
                    this.label = 4;
                    objHttpPing = networkStateManager8.httpPing("http://connectivitycheck.gstatic.com/generate_204", this);
                    if (objHttpPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope2;
                    flow2 = flowFlowOf2;
                    Flow flow7 = flow;
                    flow3 = flowFlowOf3;
                    obj = objHttpPing;
                    flow4 = flow7;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager9 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager9.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf6 = FlowKt.flowOf(obj);
                    final Flow[] flowArr2 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf6};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr2, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr2.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to ConnectedNoInternet");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.ConnectedNoInternet(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf6;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 2:
                    Flow flow8 = (Flow) this.L$1;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope2 = coroutineScope4;
                    flow = flow8;
                    flowFlowOf2 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager10 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.label = 3;
                    obj = networkStateManager10.ping("xviewplus.com.mx", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowFlowOf3 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager11 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.L$3 = flowFlowOf3;
                    this.label = 4;
                    objHttpPing = networkStateManager11.httpPing("http://connectivitycheck.gstatic.com/generate_204", this);
                    if (objHttpPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope2;
                    flow2 = flowFlowOf2;
                    Flow flow9 = flow;
                    flow3 = flowFlowOf3;
                    obj = objHttpPing;
                    flow4 = flow9;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager12 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager12.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf7 = FlowKt.flowOf(obj);
                    final Flow[] flowArr3 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf7};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr3, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr3.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to ConnectedNoInternet");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.ConnectedNoInternet(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf7;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 3:
                    flowFlowOf2 = (Flow) this.L$2;
                    flow = (Flow) this.L$1;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    flowFlowOf3 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager13 = NetworkStateManager.this;
                    this.L$0 = coroutineScope2;
                    this.L$1 = flow;
                    this.L$2 = flowFlowOf2;
                    this.L$3 = flowFlowOf3;
                    this.label = 4;
                    objHttpPing = networkStateManager13.httpPing("http://connectivitycheck.gstatic.com/generate_204", this);
                    if (objHttpPing == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope2;
                    flow2 = flowFlowOf2;
                    Flow flow10 = flow;
                    flow3 = flowFlowOf3;
                    obj = objHttpPing;
                    flow4 = flow10;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager14 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager14.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf8 = FlowKt.flowOf(obj);
                    final Flow[] flowArr4 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf8};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr4, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr4.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to ConnectedNoInternet");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.ConnectedNoInternet(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf8;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 4:
                    Flow flow11 = (Flow) this.L$3;
                    Flow flow12 = (Flow) this.L$2;
                    Flow flow13 = (Flow) this.L$1;
                    CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope3 = coroutineScope5;
                    flow4 = flow13;
                    flow2 = flow12;
                    flow3 = flow11;
                    flowFlowOf4 = FlowKt.flowOf(obj);
                    NetworkStateManager networkStateManager15 = NetworkStateManager.this;
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.label = 5;
                    obj = networkStateManager15.httpPing("https://www.google.com", this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Flow flowFlowOf9 = FlowKt.flowOf(obj);
                    final Flow[] flowArr5 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf9};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr5, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr5.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to ConnectedNoInternet");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.ConnectedNoInternet(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf9;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 5:
                    flowFlowOf4 = (Flow) this.L$4;
                    flow3 = (Flow) this.L$3;
                    flow2 = (Flow) this.L$2;
                    flow4 = (Flow) this.L$1;
                    coroutineScope3 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    Flow flowFlowOf10 = FlowKt.flowOf(obj);
                    final Flow[] flowArr6 = {flow4, flow2, flow3, flowFlowOf4, flowFlowOf10};
                    flow5 = new Flow<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1
                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super List<ServerStatus>> flowCollector2, Continuation continuation) {
                            Object objCombineInternal = CombineKt.combineInternal(flowCollector2, flowArr6, new Function0<Object[]>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object[] invoke() {
                                    return new Object[flowArr6.length];
                                }
                            }, new AnonymousClass3(null, this), continuation);
                            return objCombineInternal == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3, reason: invalid class name */
                        /* JADX INFO: compiled from: Zip.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$5$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$unsafeFlow$4$lambda$2", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$$inlined$combine$3$3"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1$3", f = "NetworkStateManager.kt", i = {0, 0}, l = {325}, m = "invokeSuspend", n = {"$receiver", "it"}, s = {"L$0", "L$1"})
                        public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<ServerStatus>>, Object[], Continuation<? super Unit>, Object> {
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            int label;
                            private FlowCollector p$;
                            private Object[] p$0;
                            final /* synthetic */ NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public AnonymousClass3(Continuation continuation, NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1 networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1) {
                                super(3, continuation);
                                this.this$0 = networkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$combine$1;
                            }

                            public final Continuation<Unit> create(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation, this.this$0);
                                anonymousClass3.p$ = flowCollector;
                                anonymousClass3.p$0 = objArr;
                                return anonymousClass3;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(FlowCollector<? super List<ServerStatus>> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass3) create(flowCollector, objArr, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FlowCollector flowCollector = this.p$;
                                    Object[] objArr = this.p$0;
                                    Object obj2 = objArr[0];
                                    Object obj3 = objArr[1];
                                    Object obj4 = objArr[2];
                                    Object obj5 = objArr[3];
                                    Triple triple = (Triple) objArr[4];
                                    Triple triple2 = (Triple) obj5;
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((PingData) obj2).toServerStatus());
                                    arrayList.add(((PingData) obj3).toServerStatus());
                                    arrayList.add(((PingData) obj4).toServerStatus());
                                    int iIntValue = ((Number) triple2.getSecond()).intValue();
                                    String str = (String) triple2.getThird();
                                    if (str == null) {
                                        str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                                    }
                                    arrayList.add(new ServerStatus((String) triple2.getFirst(), 200 <= iIntValue && 299 >= iIntValue, null, Boxing.boxInt(iIntValue), MapsKt.mapOf(TuplesKt.to("date", str)), 4, null));
                                    int iIntValue2 = ((Number) triple.getSecond()).intValue();
                                    String str2 = (String) triple.getThird();
                                    if (str2 == null) {
                                        str2 = "Unknown error";
                                    }
                                    arrayList.add(new ServerStatus((String) triple.getFirst(), 200 <= iIntValue2 && 299 >= iIntValue2, null, Boxing.boxInt(iIntValue2), MapsKt.mapOf(TuplesKt.to("errorMessage", str2)), 4, null));
                                    Log.v("NetworkStateManager", "receive all pings: " + arrayList.size());
                                    this.L$0 = flowCollector;
                                    this.L$1 = objArr;
                                    this.label = 1;
                                    if (flowCollector.emit(arrayList, this) == coroutine_suspended) {
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
                        }
                    };
                    flowCollector = new FlowCollector<List<ServerStatus>>() { // from class: com.dotscreen.farkit.network.NetworkStateManager$notifyInternetAvailability$2$invokeSuspend$$inlined$collect$1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public Object emit(List<ServerStatus> list, Continuation continuation) {
                            Log.v("NetworkStateManager", "will change to ConnectedNoInternet");
                            NetworkStateManager.this.getDeviceStatus().onNext(new NetworkStatus.ConnectedNoInternet(this.this$0.$networkType, list));
                            return Unit.INSTANCE;
                        }
                    };
                    this.L$0 = coroutineScope3;
                    this.L$1 = flow4;
                    this.L$2 = flow2;
                    this.L$3 = flow3;
                    this.L$4 = flowFlowOf4;
                    this.L$5 = flowFlowOf10;
                    this.L$6 = flow5;
                    this.label = 6;
                    if (flow5.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 6:
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Network getActiveNetwork() {
        ConnectivityManager connectivityManager = this.manager;
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetwork();
        }
        return null;
    }

    private final NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = this.manager;
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public final Boolean internetIsReachable() {
        if (Build.VERSION.SDK_INT >= 23) {
            NetworkCapabilities currentActiveNetworkCapabilities = getCurrentActiveNetworkCapabilities();
            if (currentActiveNetworkCapabilities != null) {
                return Boolean.valueOf(currentActiveNetworkCapabilities.hasCapability(12));
            }
            return null;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return Boolean.valueOf(activeNetworkInfo.isConnected());
        }
        return null;
    }

    public final NetworkCapabilities getCurrentActiveNetworkCapabilities() {
        ConnectivityManager connectivityManager = this.manager;
        Network activeNetwork = connectivityManager != null ? connectivityManager.getActiveNetwork() : null;
        ConnectivityManager connectivityManager2 = this.manager;
        if (connectivityManager2 != null) {
            return connectivityManager2.getNetworkCapabilities(activeNetwork);
        }
        return null;
    }

    public final LinkProperties getCurrentLinkProperties() {
        ConnectivityManager connectivityManager = this.manager;
        if (connectivityManager != null) {
            return connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork());
        }
        return null;
    }

    public final NetworkType getActiveNetworkType() {
        NetworkType activeNetworkType;
        if (Build.VERSION.SDK_INT >= 23) {
            NetworkCapabilities currentActiveNetworkCapabilities = getCurrentActiveNetworkCapabilities();
            return (currentActiveNetworkCapabilities == null || (activeNetworkType = NetworkStateManagerKt.getActiveNetworkType(currentActiveNetworkCapabilities)) == null) ? NetworkType.Unknown : activeNetworkType;
        }
        ConnectivityManager connectivityManager = this.manager;
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null ? convertType(activeNetworkInfo.getType()) : NetworkType.Unknown;
    }

    public final boolean hasInternetCapability() {
        if (Build.VERSION.SDK_INT >= 23) {
            NetworkCapabilities currentActiveNetworkCapabilities = getCurrentActiveNetworkCapabilities();
            if (currentActiveNetworkCapabilities != null) {
                return currentActiveNetworkCapabilities.hasCapability(12);
            }
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }

    public final boolean isValidated() {
        NetworkCapabilities currentActiveNetworkCapabilities;
        if (Build.VERSION.SDK_INT < 23 || (currentActiveNetworkCapabilities = getCurrentActiveNetworkCapabilities()) == null) {
            return false;
        }
        return currentActiveNetworkCapabilities.hasCapability(16);
    }

    public static /* synthetic */ void forceUpdateStatus$default(NetworkStateManager networkStateManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        networkStateManager.forceUpdateStatus(z);
    }

    public final void forceUpdateStatus(boolean completeCheck) {
        if (Build.VERSION.SDK_INT >= 23) {
            updateStatus(completeCheck, getActiveNetwork(), getCurrentActiveNetworkCapabilities());
        } else {
            updateStatus$default(this, completeCheck, null, null, 6, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String convertMillisToDuration(long millis) {
        String str;
        long j = 1000;
        long j2 = millis % j;
        long j3 = 60;
        long j4 = (millis / j) % j3;
        long j5 = (millis / ((long) 60000)) % j3;
        long j6 = (millis / ((long) 3600000)) % ((long) 24);
        long j7 = millis / ((long) 86400000);
        if (j2 > 0) {
            str = j2 + " milliseconds";
        } else {
            str = "";
        }
        if (j4 > 0) {
            str = (j4 + " seconds") + ' ' + str;
        }
        if (j5 > 0) {
            str = (j5 + " minutes") + ' ' + str;
        }
        if (j6 > 0) {
            str = (j6 + " hours") + ' ' + str;
        }
        if (j7 <= 0) {
            return str;
        }
        return (j7 + " days") + ' ' + str;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x044c  */
    /* JADX WARN: Code duplicated, block: B:64:0x04ae A[LOOP:2: B:62:0x04a8->B:64:0x04ae, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x052b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:68:0x052c  */
    /* JADX WARN: Code duplicated, block: B:72:0x0587 A[LOOP:0: B:70:0x0581->B:72:0x0587, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:75:0x05fe A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:76:0x05ff  */
    /* JADX WARN: Code duplicated, block: B:78:0x063d  */
    /* JADX WARN: Code duplicated, block: B:79:0x0640  */
    /* JADX WARN: Code duplicated, block: B:7:0x0024  */
    /* JADX WARN: Code duplicated, block: B:82:0x065c  */
    /* JADX WARN: Code duplicated, block: B:84:0x0664  */
    /* JADX WARN: Code duplicated, block: B:86:0x0671  */
    /* JADX WARN: Code duplicated, block: B:90:0x06b0 A[LOOP:3: B:88:0x06aa->B:90:0x06b0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:93:0x071f A[RETURN] */
    /* JADX WARN: Instruction removed from duplicated block: B:61:0x044c, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v16, types: [T, com.dotscreen.farkit.network.NetworkStatus] */
    /* JADX WARN: Type inference failed for: r15v8, types: [T, com.dotscreen.farkit.network.NetworkStatus] */
    public final Object handleNetworkError(boolean z, int i, PowerManager powerManager, int i2, URL url, Throwable th, long j, List<? extends IConnectivityStateChanged> list, Function1<? super Continuation<? super BackendReachability>, ? extends Object> function1, long j2, long j3, Long l, TimeUnit timeUnit, Function2<? super String, ? super String, Unit> function2, Continuation<? super Unit> continuation) {
        C02551 c02551;
        Object obj;
        AnonymousClass2 anonymousClass2;
        String str;
        URL url2;
        PowerManager powerManager2;
        Throwable th2;
        List<? extends IConnectivityStateChanged> list2;
        Function1<? super Continuation<? super BackendReachability>, ? extends Object> function3;
        C02551 c02552;
        long j4;
        Long l2;
        AnonymousClass3 anonymousClass3;
        TimeUnit timeUnit2;
        Function2<? super String, ? super String, Unit> function4;
        NetworkStateManager networkStateManager;
        long j5;
        AnonymousClass2 anonymousClass4;
        boolean z2;
        int i3;
        String str2;
        int i4;
        long j6;
        long j7;
        NetworkStateManager networkStateManager2;
        String string;
        Object obj2;
        long j8;
        Long l3;
        AnonymousClass3 anonymousClass5;
        TimeUnit timeUnit3;
        Function2<? super String, ? super String, Unit> function5;
        String str3;
        NetworkStateManager networkStateManager3;
        Function1<? super Continuation<? super BackendReachability>, ? extends Object> function6;
        String str4;
        Throwable th3;
        AnonymousClass2 anonymousClass6;
        NetworkType activeNetworkType;
        String str5;
        NetworkStatus.Connected connected;
        Iterator it;
        Object obj3;
        NetworkStatus value;
        Ref.ObjectRef objectRef;
        Iterator it2;
        String str6;
        String str7;
        Throwable th4;
        Function1<? super Continuation<? super BackendReachability>, ? extends Object> function7;
        long j9;
        AnonymousClass2 anonymousClass7;
        AnonymousClass3 anonymousClass8;
        Object objInvoke;
        Object obj4;
        Object obj5;
        String str8;
        Function2<? super String, ? super String, Unit> function8;
        int i5;
        boolean z3;
        Ref.ObjectRef objectRef2;
        TimeUnit timeUnit4;
        Long l4;
        long j10;
        long j11;
        AnonymousClass2 anonymousClass9;
        AnonymousClass3 anonymousClass10;
        Iterator<T> it3;
        Object objInvoke2;
        Object obj6;
        if (continuation instanceof C02551) {
            c02551 = (C02551) continuation;
            if ((c02551.label & Integer.MIN_VALUE) != 0) {
                c02551.label -= Integer.MIN_VALUE;
            } else {
                c02551 = new C02551(continuation);
            }
        } else {
            c02551 = new C02551(continuation);
        }
        C02551 c02553 = c02551;
        Object obj7 = c02553.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i6 = c02553.label;
        if (i6 == 0) {
            ResultKt.throwOnFailure(obj7);
            obj = coroutine_suspended;
            anonymousClass2 = anonymousClass2;
            AnonymousClass2 anonymousClass11 = new AnonymousClass2(function2, "NetworkStateManager-handleNetworkError()", i2, i, function1, list, j, th, url, l, timeUnit, j3, powerManager, j2, null);
            AnonymousClass3 anonymousClass12 = new AnonymousClass3(function2, "NetworkStateManager-handleNetworkError()", i2, i, null);
            function2.invoke("NetworkStateManager-handleNetworkError()", "======================handleNetworkError(" + i + ") - (rid:" + i2 + '-' + i + ")======================");
            StringBuilder sb = new StringBuilder();
            str = "(rid:";
            sb.append(str);
            sb.append(i2);
            sb.append('-');
            sb.append(i);
            sb.append(") url: ");
            url2 = url;
            sb.append(url2);
            function2.invoke("NetworkStateManager-handleNetworkError()", sb.toString());
            function2.invoke("NetworkStateManager-handleNetworkError()", str + i2 + '-' + i + ") [error cause]: " + th.getCause());
            function2.invoke("NetworkStateManager-handleNetworkError()", str + i2 + '-' + i + ") [error message]: " + th.getMessage());
            if (z) {
                powerManager2 = powerManager;
                th2 = th;
                list2 = list;
                function3 = function1;
                str = str;
                c02552 = c02553;
                j4 = j3;
                l2 = l;
                anonymousClass3 = anonymousClass12;
                timeUnit2 = timeUnit;
                function4 = function2;
                networkStateManager = this;
                j5 = j2;
                anonymousClass4 = anonymousClass2;
                z2 = z;
                i3 = i2;
                str2 = "NetworkStateManager-handleNetworkError()";
                i4 = i;
                j6 = j;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(i2);
                sb2.append('-');
                sb2.append(i);
                sb2.append(") [delay]: ");
                if (i == 0) {
                    StringBuilder sb3 = new StringBuilder();
                    j7 = j2;
                    sb3.append(j7 / ((long) 1000));
                    sb3.append(" seconds (iteration 0)");
                    string = sb3.toString();
                    networkStateManager2 = this;
                } else {
                    j7 = j2;
                    StringBuilder sb4 = new StringBuilder();
                    networkStateManager2 = this;
                    sb4.append(networkStateManager2.checkCapacityDelay / ((long) 1000));
                    sb4.append(" seconds");
                    string = sb4.toString();
                }
                sb2.append(string);
                function2.invoke("NetworkStateManager-handleNetworkError()", sb2.toString());
                long j12 = i == 0 ? j7 : networkStateManager2.checkCapacityDelay;
                c02552 = c02553;
                c02552.L$0 = networkStateManager2;
                c02552.Z$0 = z;
                c02552.I$0 = i;
                powerManager2 = powerManager;
                c02552.L$1 = powerManager2;
                c02552.I$1 = i2;
                c02552.L$2 = url2;
                th2 = th;
                c02552.L$3 = th2;
                c02552.J$0 = j;
                list2 = list;
                c02552.L$4 = list2;
                function3 = function1;
                c02552.L$5 = function3;
                c02552.J$1 = j7;
                c02552.J$2 = j3;
                c02552.L$6 = l;
                c02552.L$7 = timeUnit;
                c02552.L$8 = function2;
                c02552.L$9 = "NetworkStateManager-handleNetworkError()";
                c02552.L$10 = anonymousClass2;
                c02552.L$11 = anonymousClass12;
                c02552.label = 1;
                obj2 = obj;
                if (DelayKt.delay(j12, c02552) == obj2) {
                    return obj2;
                }
                j8 = j3;
                l3 = l;
                anonymousClass5 = anonymousClass12;
                timeUnit3 = timeUnit;
                function5 = function2;
                str3 = "NetworkStateManager-handleNetworkError()";
                networkStateManager3 = networkStateManager2;
                z2 = z;
                j6 = j;
                i3 = i2;
                i4 = i;
            }
            boolean z4 = Build.VERSION.SDK_INT >= 20 ? !powerManager2.isScreenOn() : !powerManager2.isInteractive();
            StringBuilder sb5 = new StringBuilder();
            function6 = function3;
            str4 = str;
            sb5.append(str4);
            sb5.append(i3);
            th3 = th2;
            sb5.append('-');
            sb5.append(i4);
            sb5.append(") [stand by]: ");
            sb5.append(z4);
            function4.invoke(str2, sb5.toString());
            if (i4 != 0 && z4 && !(networkStateManager.appStatus.getValue() instanceof NetworkStatus.ConnectedNoBackendAccess)) {
                function4.invoke(str2, str4 + i3 + '-' + i4 + ") >>> STB is in standby (not degraded), stopping all operations");
                Iterator<T> it4 = list2.iterator();
                while (it4.hasNext()) {
                    ((IConnectivityStateChanged) it4.next()).doIfAllOperationsCancelled(new Date().getTime() - j6);
                }
                return Unit.INSTANCE;
            }
            function4.invoke(str2, str4 + i3 + '-' + i4 + ") - START ALGORITHM (tat tada da) -");
            function4.invoke(str2, str4 + i3 + '-' + i4 + ") STEP 1: Check Internet Capability");
            if (!networkStateManager.hasInternetCapability()) {
                function4.invoke(str2, str4 + i3 + '-' + i4 + ") >>> STB doesn't have internet");
                objectRef = new Ref.ObjectRef();
                objectRef.element = NetworkStatus.NotConnected.INSTANCE;
                function4.invoke(str2, str4 + i3 + '-' + i4 + ") >>> doIfNoInternet(" + ((NetworkStatus) objectRef.element) + ')');
                it2 = list2.iterator();
                while (it2.hasNext()) {
                    ((IConnectivityStateChanged) it2.next()).doIfNoInternet((NetworkStatus) objectRef.element, new Date().getTime() - j6);
                    it2 = it2;
                    str4 = str4;
                    str2 = str2;
                }
                str6 = str2;
                str7 = str4;
                networkStateManager.appStatus.onNext((NetworkStatus) objectRef.element);
                c02552.L$0 = networkStateManager;
                c02552.Z$0 = z2;
                c02552.I$0 = i4;
                c02552.L$1 = powerManager2;
                c02552.I$1 = i3;
                c02552.L$2 = url2;
                th4 = th3;
                c02552.L$3 = th4;
                c02552.J$0 = j6;
                c02552.L$4 = list2;
                function7 = function6;
                c02552.L$5 = function7;
                c02552.J$1 = j5;
                j9 = j4;
                c02552.J$2 = j9;
                c02552.L$6 = l2;
                c02552.L$7 = timeUnit2;
                c02552.L$8 = function4;
                c02552.L$9 = str6;
                anonymousClass7 = anonymousClass4;
                c02552.L$10 = anonymousClass7;
                anonymousClass8 = anonymousClass3;
                c02552.L$11 = anonymousClass8;
                c02552.L$12 = objectRef;
                c02552.label = 2;
                objInvoke = anonymousClass8.invoke((Continuation<? super Unit>) c02552);
                obj4 = obj;
                if (objInvoke == obj4) {
                    return obj4;
                }
                obj5 = obj4;
                str8 = str6;
                function8 = function4;
                i5 = i3;
                z3 = z2;
                objectRef2 = objectRef;
                timeUnit4 = timeUnit2;
                l4 = l2;
                j10 = j9;
                j11 = j5;
                anonymousClass9 = anonymousClass7;
                anonymousClass10 = anonymousClass8;
                Function1<? super Continuation<? super BackendReachability>, ? extends Object> function9 = function7;
                URL url3 = url2;
                Throwable th5 = th4;
                objectRef2.element = new NetworkStatus.Connected(networkStateManager.getActiveNetworkType(), null, 2, null);
                function8.invoke(str8, str7 + i5 + '-' + i4 + ") >>> (backend reachability unknown)");
                it3 = list2.iterator();
                while (it3.hasNext()) {
                    ((IConnectivityStateChanged) it3.next()).doIfInternetAvailable((NetworkStatus) objectRef2.element, networkStateManager.isValidated(), new Date().getTime() - j6);
                    list2 = list2;
                    str8 = str8;
                }
                networkStateManager.appStatus.onNext((NetworkStatus) objectRef2.element);
                c02552.L$0 = networkStateManager;
                c02552.Z$0 = z3;
                c02552.I$0 = i4;
                c02552.L$1 = powerManager2;
                c02552.I$1 = i5;
                c02552.L$2 = url3;
                c02552.L$3 = th5;
                c02552.J$0 = j6;
                c02552.L$4 = list2;
                c02552.L$5 = function9;
                c02552.J$1 = j11;
                c02552.J$2 = j10;
                c02552.L$6 = l4;
                c02552.L$7 = timeUnit4;
                c02552.L$8 = function8;
                c02552.L$9 = str8;
                AnonymousClass2 anonymousClass13 = anonymousClass9;
                c02552.L$10 = anonymousClass13;
                c02552.L$11 = anonymousClass10;
                c02552.L$12 = objectRef2;
                c02552.label = 3;
                objInvoke2 = anonymousClass13.invoke((Continuation<? super Unit>) c02552);
                obj6 = obj5;
                if (objInvoke2 == obj6) {
                    return obj6;
                }
            } else {
                String str9 = str2;
                long j13 = j4;
                AnonymousClass3 anonymousClass14 = anonymousClass3;
                anonymousClass6 = anonymousClass4;
                activeNetworkType = networkStateManager.getActiveNetworkType();
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str4);
                sb6.append(i3);
                sb6.append('-');
                sb6.append(i4);
                sb6.append(") >>> STB has internet. Network is : ");
                sb6.append(activeNetworkType);
                sb6.append(". (Backend reachability: ");
                if (networkStateManager.appStatus.getValue() instanceof NetworkStatus.ConnectedNoBackendAccess) {
                    str5 = "false";
                } else {
                    str5 = EnvironmentCompat.MEDIA_UNKNOWN;
                }
                sb6.append(str5);
                sb6.append(')');
                function4.invoke(str9, sb6.toString());
                if (networkStateManager.appStatus.getValue() instanceof NetworkStatus.ConnectedNoBackendAccess) {
                    value = networkStateManager.appStatus.getValue();
                    if (value == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(value, "appStatus.value!!");
                    connected = value;
                } else {
                    connected = new NetworkStatus.Connected(activeNetworkType, null, 2, null);
                }
                function4.invoke(str9, str4 + i3 + '-' + i4 + ") >>> doIfInternetAvailable(" + connected + ')');
                it = list2.iterator();
                while (it.hasNext()) {
                    ((IConnectivityStateChanged) it.next()).doIfInternetAvailable(connected, networkStateManager.isValidated(), new Date().getTime() - j6);
                    it = it;
                    function4 = function4;
                }
                networkStateManager.appStatus.onNext(connected);
                c02552.L$0 = networkStateManager;
                c02552.Z$0 = z2;
                c02552.I$0 = i4;
                c02552.L$1 = powerManager2;
                c02552.I$1 = i3;
                c02552.L$2 = url2;
                c02552.L$3 = th3;
                c02552.J$0 = j6;
                c02552.L$4 = list2;
                c02552.L$5 = function6;
                c02552.J$1 = j5;
                c02552.J$2 = j13;
                c02552.L$6 = l2;
                c02552.L$7 = timeUnit2;
                c02552.L$8 = function4;
                c02552.L$9 = str9;
                c02552.L$10 = anonymousClass6;
                c02552.L$11 = anonymousClass14;
                c02552.L$12 = activeNetworkType;
                c02552.L$13 = connected;
                c02552.label = 4;
                obj3 = obj;
                if (anonymousClass6.invoke((Continuation<? super Unit>) c02552) == obj3) {
                    return obj3;
                }
            }
            return Unit.INSTANCE;
        }
        if (i6 == 1) {
            AnonymousClass3 anonymousClass15 = (AnonymousClass3) c02553.L$11;
            AnonymousClass2 anonymousClass16 = (AnonymousClass2) c02553.L$10;
            String str10 = (String) c02553.L$9;
            Function2<? super String, ? super String, Unit> function10 = (Function2) c02553.L$8;
            TimeUnit timeUnit5 = (TimeUnit) c02553.L$7;
            Long l5 = (Long) c02553.L$6;
            long j14 = c02553.J$2;
            long j15 = c02553.J$1;
            Function1<? super Continuation<? super BackendReachability>, ? extends Object> function11 = (Function1) c02553.L$5;
            List<? extends IConnectivityStateChanged> list3 = (List) c02553.L$4;
            long j16 = c02553.J$0;
            Throwable th6 = (Throwable) c02553.L$3;
            URL url4 = (URL) c02553.L$2;
            int i7 = c02553.I$1;
            PowerManager powerManager3 = (PowerManager) c02553.L$1;
            int i8 = c02553.I$0;
            boolean z5 = c02553.Z$0;
            NetworkStateManager networkStateManager4 = (NetworkStateManager) c02553.L$0;
            ResultKt.throwOnFailure(obj7);
            anonymousClass5 = anonymousClass15;
            anonymousClass2 = anonymousClass16;
            networkStateManager3 = networkStateManager4;
            str3 = str10;
            str = "(rid:";
            c02552 = c02553;
            function5 = function10;
            l3 = l5;
            j8 = j14;
            function3 = function11;
            obj2 = coroutine_suspended;
            i3 = i7;
            z2 = z5;
            url2 = url4;
            j7 = j15;
            timeUnit3 = timeUnit5;
            list2 = list3;
            j6 = j16;
            th2 = th6;
            powerManager2 = powerManager3;
            i4 = i8;
        } else if (i6 != 2) {
            if (i6 == 3) {
            } else {
                if (i6 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            long j17 = c02553.J$2;
            long j18 = c02553.J$1;
            long j19 = c02553.J$0;
            int i9 = c02553.I$1;
            int i10 = c02553.I$0;
            boolean z6 = c02553.Z$0;
            ResultKt.throwOnFailure(obj7);
        } else {
            Ref.ObjectRef objectRef3 = (Ref.ObjectRef) c02553.L$12;
            AnonymousClass3 anonymousClass17 = (AnonymousClass3) c02553.L$11;
            AnonymousClass2 anonymousClass18 = (AnonymousClass2) c02553.L$10;
            String str11 = (String) c02553.L$9;
            Function2<? super String, ? super String, Unit> function12 = (Function2) c02553.L$8;
            TimeUnit timeUnit6 = (TimeUnit) c02553.L$7;
            Long l6 = (Long) c02553.L$6;
            long j20 = c02553.J$2;
            long j21 = c02553.J$1;
            Function1<? super Continuation<? super BackendReachability>, ? extends Object> function13 = (Function1) c02553.L$5;
            List<? extends IConnectivityStateChanged> list4 = (List) c02553.L$4;
            long j22 = c02553.J$0;
            Throwable th7 = (Throwable) c02553.L$3;
            URL url5 = (URL) c02553.L$2;
            int i11 = c02553.I$1;
            PowerManager powerManager4 = (PowerManager) c02553.L$1;
            int i12 = c02553.I$0;
            boolean z7 = c02553.Z$0;
            NetworkStateManager networkStateManager5 = (NetworkStateManager) c02553.L$0;
            ResultKt.throwOnFailure(obj7);
            j10 = j20;
            networkStateManager = networkStateManager5;
            str7 = "(rid:";
            obj5 = coroutine_suspended;
            j11 = j21;
            anonymousClass9 = anonymousClass18;
            str8 = str11;
            function8 = function12;
            timeUnit4 = timeUnit6;
            l4 = l6;
            objectRef2 = objectRef3;
            function7 = function13;
            list2 = list4;
            url2 = url5;
            powerManager2 = powerManager4;
            z3 = z7;
            th4 = th7;
            c02552 = c02553;
            i5 = i11;
            i4 = i12;
            anonymousClass10 = anonymousClass17;
            j6 = j22;
            Function1<? super Continuation<? super BackendReachability>, ? extends Object> function14 = function7;
            URL url6 = url2;
            Throwable th8 = th4;
            objectRef2.element = new NetworkStatus.Connected(networkStateManager.getActiveNetworkType(), null, 2, null);
            function8.invoke(str8, str7 + i5 + '-' + i4 + ") >>> (backend reachability unknown)");
            it3 = list2.iterator();
            while (it3.hasNext()) {
                ((IConnectivityStateChanged) it3.next()).doIfInternetAvailable((NetworkStatus) objectRef2.element, networkStateManager.isValidated(), new Date().getTime() - j6);
                list2 = list2;
                str8 = str8;
            }
            networkStateManager.appStatus.onNext((NetworkStatus) objectRef2.element);
            c02552.L$0 = networkStateManager;
            c02552.Z$0 = z3;
            c02552.I$0 = i4;
            c02552.L$1 = powerManager2;
            c02552.I$1 = i5;
            c02552.L$2 = url6;
            c02552.L$3 = th8;
            c02552.J$0 = j6;
            c02552.L$4 = list2;
            c02552.L$5 = function14;
            c02552.J$1 = j11;
            c02552.J$2 = j10;
            c02552.L$6 = l4;
            c02552.L$7 = timeUnit4;
            c02552.L$8 = function8;
            c02552.L$9 = str8;
            AnonymousClass2 anonymousClass19 = anonymousClass9;
            c02552.L$10 = anonymousClass19;
            c02552.L$11 = anonymousClass10;
            c02552.L$12 = objectRef2;
            c02552.label = 3;
            objInvoke2 = anonymousClass19.invoke((Continuation<? super Unit>) c02552);
            obj6 = obj5;
            if (objInvoke2 == obj6) {
                return obj6;
            }
        }
        return Unit.INSTANCE;
        obj = obj2;
        j4 = j8;
        l2 = l3;
        timeUnit2 = timeUnit3;
        function4 = function5;
        anonymousClass3 = anonymousClass5;
        str2 = str3;
        networkStateManager = networkStateManager3;
        anonymousClass4 = anonymousClass2;
        j5 = j7;
        if (Build.VERSION.SDK_INT >= 20) {
        }
        StringBuilder sb7 = new StringBuilder();
        function6 = function3;
        str4 = str;
        sb7.append(str4);
        sb7.append(i3);
        th3 = th2;
        sb7.append('-');
        sb7.append(i4);
        sb7.append(") [stand by]: ");
        sb7.append(z4);
        function4.invoke(str2, sb7.toString());
        if (i4 != 0) {
        }
        function4.invoke(str2, str4 + i3 + '-' + i4 + ") - START ALGORITHM (tat tada da) -");
        function4.invoke(str2, str4 + i3 + '-' + i4 + ") STEP 1: Check Internet Capability");
        if (!networkStateManager.hasInternetCapability()) {
            function4.invoke(str2, str4 + i3 + '-' + i4 + ") >>> STB doesn't have internet");
            objectRef = new Ref.ObjectRef();
            objectRef.element = NetworkStatus.NotConnected.INSTANCE;
            function4.invoke(str2, str4 + i3 + '-' + i4 + ") >>> doIfNoInternet(" + ((NetworkStatus) objectRef.element) + ')');
            it2 = list2.iterator();
            while (it2.hasNext()) {
                ((IConnectivityStateChanged) it2.next()).doIfNoInternet((NetworkStatus) objectRef.element, new Date().getTime() - j6);
                it2 = it2;
                str4 = str4;
                str2 = str2;
            }
            str6 = str2;
            str7 = str4;
            networkStateManager.appStatus.onNext((NetworkStatus) objectRef.element);
            c02552.L$0 = networkStateManager;
            c02552.Z$0 = z2;
            c02552.I$0 = i4;
            c02552.L$1 = powerManager2;
            c02552.I$1 = i3;
            c02552.L$2 = url2;
            th4 = th3;
            c02552.L$3 = th4;
            c02552.J$0 = j6;
            c02552.L$4 = list2;
            function7 = function6;
            c02552.L$5 = function7;
            c02552.J$1 = j5;
            j9 = j4;
            c02552.J$2 = j9;
            c02552.L$6 = l2;
            c02552.L$7 = timeUnit2;
            c02552.L$8 = function4;
            c02552.L$9 = str6;
            anonymousClass7 = anonymousClass4;
            c02552.L$10 = anonymousClass7;
            anonymousClass8 = anonymousClass3;
            c02552.L$11 = anonymousClass8;
            c02552.L$12 = objectRef;
            c02552.label = 2;
            objInvoke = anonymousClass8.invoke((Continuation<? super Unit>) c02552);
            obj4 = obj;
            if (objInvoke == obj4) {
                return obj4;
            }
            obj5 = obj4;
            str8 = str6;
            function8 = function4;
            i5 = i3;
            z3 = z2;
            objectRef2 = objectRef;
            timeUnit4 = timeUnit2;
            l4 = l2;
            j10 = j9;
            j11 = j5;
            anonymousClass9 = anonymousClass7;
            anonymousClass10 = anonymousClass8;
            Function1<? super Continuation<? super BackendReachability>, ? extends Object> function15 = function7;
            URL url7 = url2;
            Throwable th9 = th4;
            objectRef2.element = new NetworkStatus.Connected(networkStateManager.getActiveNetworkType(), null, 2, null);
            function8.invoke(str8, str7 + i5 + '-' + i4 + ") >>> (backend reachability unknown)");
            it3 = list2.iterator();
            while (it3.hasNext()) {
                ((IConnectivityStateChanged) it3.next()).doIfInternetAvailable((NetworkStatus) objectRef2.element, networkStateManager.isValidated(), new Date().getTime() - j6);
                list2 = list2;
                str8 = str8;
            }
            networkStateManager.appStatus.onNext((NetworkStatus) objectRef2.element);
            c02552.L$0 = networkStateManager;
            c02552.Z$0 = z3;
            c02552.I$0 = i4;
            c02552.L$1 = powerManager2;
            c02552.I$1 = i5;
            c02552.L$2 = url7;
            c02552.L$3 = th9;
            c02552.J$0 = j6;
            c02552.L$4 = list2;
            c02552.L$5 = function15;
            c02552.J$1 = j11;
            c02552.J$2 = j10;
            c02552.L$6 = l4;
            c02552.L$7 = timeUnit4;
            c02552.L$8 = function8;
            c02552.L$9 = str8;
            AnonymousClass2 anonymousClass110 = anonymousClass9;
            c02552.L$10 = anonymousClass110;
            c02552.L$11 = anonymousClass10;
            c02552.L$12 = objectRef2;
            c02552.label = 3;
            objInvoke2 = anonymousClass110.invoke((Continuation<? super Unit>) c02552);
            obj6 = obj5;
            if (objInvoke2 == obj6) {
                return obj6;
            }
        } else {
            String str12 = str2;
            long j110 = j4;
            AnonymousClass3 anonymousClass111 = anonymousClass3;
            anonymousClass6 = anonymousClass4;
            activeNetworkType = networkStateManager.getActiveNetworkType();
            StringBuilder sb8 = new StringBuilder();
            sb8.append(str4);
            sb8.append(i3);
            sb8.append('-');
            sb8.append(i4);
            sb8.append(") >>> STB has internet. Network is : ");
            sb8.append(activeNetworkType);
            sb8.append(". (Backend reachability: ");
            if (networkStateManager.appStatus.getValue() instanceof NetworkStatus.ConnectedNoBackendAccess) {
                str5 = "false";
            } else {
                str5 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            sb8.append(str5);
            sb8.append(')');
            function4.invoke(str12, sb8.toString());
            if (networkStateManager.appStatus.getValue() instanceof NetworkStatus.ConnectedNoBackendAccess) {
                value = networkStateManager.appStatus.getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value, "appStatus.value!!");
                connected = value;
            } else {
                connected = new NetworkStatus.Connected(activeNetworkType, null, 2, null);
            }
            function4.invoke(str12, str4 + i3 + '-' + i4 + ") >>> doIfInternetAvailable(" + connected + ')');
            it = list2.iterator();
            while (it.hasNext()) {
                ((IConnectivityStateChanged) it.next()).doIfInternetAvailable(connected, networkStateManager.isValidated(), new Date().getTime() - j6);
                it = it;
                function4 = function4;
            }
            networkStateManager.appStatus.onNext(connected);
            c02552.L$0 = networkStateManager;
            c02552.Z$0 = z2;
            c02552.I$0 = i4;
            c02552.L$1 = powerManager2;
            c02552.I$1 = i3;
            c02552.L$2 = url2;
            c02552.L$3 = th3;
            c02552.J$0 = j6;
            c02552.L$4 = list2;
            c02552.L$5 = function6;
            c02552.J$1 = j5;
            c02552.J$2 = j110;
            c02552.L$6 = l2;
            c02552.L$7 = timeUnit2;
            c02552.L$8 = function4;
            c02552.L$9 = str12;
            c02552.L$10 = anonymousClass6;
            c02552.L$11 = anonymousClass111;
            c02552.L$12 = activeNetworkType;
            c02552.L$13 = connected;
            c02552.label = 4;
            obj3 = obj;
            if (anonymousClass6.invoke((Continuation<? super Unit>) c02552) == obj3) {
                return obj3;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$2, reason: invalid class name */
    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"backendCheckLoop", "", "invoke", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$2", f = "NetworkStateManager.kt", i = {}, l = {468}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ long $backendCheckInitialDelay;
        final /* synthetic */ Long $backendRecheckMaxDelay;
        final /* synthetic */ long $backendRecheckMinDelay;
        final /* synthetic */ Function2 $breadcrumbHandler;
        final /* synthetic */ TimeUnit $delayTimeUnit;
        final /* synthetic */ Throwable $error;
        final /* synthetic */ long $errorTimestamp;
        final /* synthetic */ String $internalTag;
        final /* synthetic */ Function1 $isBackendReachable;
        final /* synthetic */ int $iterationCount;
        final /* synthetic */ List $listeners;
        final /* synthetic */ PowerManager $powerManager;
        final /* synthetic */ int $randomId;
        final /* synthetic */ URL $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Function2 function2, String str, int i, int i2, Function1 function1, List list, long j, Throwable th, URL url, Long l, TimeUnit timeUnit, long j2, PowerManager powerManager, long j3, Continuation continuation) {
            super(1, continuation);
            this.$breadcrumbHandler = function2;
            this.$internalTag = str;
            this.$randomId = i;
            this.$iterationCount = i2;
            this.$isBackendReachable = function1;
            this.$listeners = list;
            this.$errorTimestamp = j;
            this.$error = th;
            this.$url = url;
            this.$backendRecheckMaxDelay = l;
            this.$delayTimeUnit = timeUnit;
            this.$backendRecheckMinDelay = j2;
            this.$powerManager = powerManager;
            this.$backendCheckInitialDelay = j3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            return NetworkStateManager.this.new AnonymousClass2(this.$breadcrumbHandler, this.$internalTag, this.$randomId, this.$iterationCount, this.$isBackendReachable, this.$listeners, this.$errorTimestamp, this.$error, this.$url, this.$backendRecheckMaxDelay, this.$delayTimeUnit, this.$backendRecheckMinDelay, this.$powerManager, this.$backendCheckInitialDelay, completion);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: NetworkStateManager.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$2$1", f = "NetworkStateManager.kt", i = {0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {470, 502, 517}, m = "invokeSuspend", n = {"$this$withContext", "$this$withContext", "backendReachability", "newStatus", "delayDuration", "$this$withContext", "backendReachability", "newStatus", "delayDuration"}, s = {"L$0", "L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "J$0"})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            long J$0;
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            private CoroutineScope p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = AnonymousClass2.this.new AnonymousClass1(completion);
                anonymousClass1.p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:35:0x02fe A[RETURN] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope;
                Object objInvoke;
                long jRandom;
                NetworkStatus.ConnectedNoBackendAccess connectedNoBackendAccess;
                BackendReachability backendReachability;
                long j;
                CoroutineScope coroutineScope2;
                NetworkStateManager networkStateManager;
                int i;
                PowerManager powerManager;
                int i2;
                URL url;
                Throwable th;
                long j2;
                List<? extends IConnectivityStateChanged> list;
                Function1<? super Continuation<? super BackendReachability>, ? extends Object> function1;
                long j3;
                long j4;
                Long l;
                TimeUnit timeUnit;
                Function2<? super String, ? super String, Unit> function2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i3 = this.label;
                if (i3 == 0) {
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.p$;
                    AnonymousClass2.this.$breadcrumbHandler.invoke(AnonymousClass2.this.$internalTag, "(rid:" + AnonymousClass2.this.$randomId + '-' + AnonymousClass2.this.$iterationCount + ") STEP 2: Check Backend");
                    Function1 function3 = AnonymousClass2.this.$isBackendReachable;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    objInvoke = function3.invoke(this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i3 == 1) {
                        coroutineScope = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objInvoke = obj;
                    } else if (i3 == 2) {
                        long j5 = this.J$0;
                        NetworkStatus.ConnectedNoBackendAccess connectedNoBackendAccess2 = (NetworkStatus.ConnectedNoBackendAccess) this.L$2;
                        BackendReachability backendReachability2 = (BackendReachability) this.L$1;
                        CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        j = j5;
                        connectedNoBackendAccess = connectedNoBackendAccess2;
                        backendReachability = backendReachability2;
                        coroutineScope2 = coroutineScope3;
                        networkStateManager = NetworkStateManager.this;
                        i = AnonymousClass2.this.$iterationCount + 1;
                        powerManager = AnonymousClass2.this.$powerManager;
                        i2 = AnonymousClass2.this.$randomId;
                        url = AnonymousClass2.this.$url;
                        th = AnonymousClass2.this.$error;
                        j2 = AnonymousClass2.this.$errorTimestamp;
                        list = AnonymousClass2.this.$listeners;
                        function1 = AnonymousClass2.this.$isBackendReachable;
                        long j6 = j;
                        j3 = AnonymousClass2.this.$backendCheckInitialDelay;
                        j4 = AnonymousClass2.this.$backendRecheckMinDelay;
                        l = AnonymousClass2.this.$backendRecheckMaxDelay;
                        timeUnit = AnonymousClass2.this.$delayTimeUnit;
                        function2 = AnonymousClass2.this.$breadcrumbHandler;
                        this.L$0 = coroutineScope2;
                        this.L$1 = backendReachability;
                        this.L$2 = connectedNoBackendAccess;
                        this.J$0 = j6;
                        this.label = 3;
                        if (networkStateManager.handleNetworkError(false, i, powerManager, i2, url, th, j2, list, function1, j3, j4, l, timeUnit, function2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i3 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
                BackendReachability backendReachability3 = (BackendReachability) objInvoke;
                if (backendReachability3.getReachable()) {
                    AnonymousClass2.this.$breadcrumbHandler.invoke(AnonymousClass2.this.$internalTag, "(rid:" + AnonymousClass2.this.$randomId + '-' + AnonymousClass2.this.$iterationCount + ") >>> Backend is available");
                    NetworkStatus.Connected connected = new NetworkStatus.Connected(NetworkStateManager.this.getActiveNetworkType(), null, 2, null);
                    AnonymousClass2.this.$breadcrumbHandler.invoke(AnonymousClass2.this.$internalTag, "(rid:" + AnonymousClass2.this.$randomId + '-' + AnonymousClass2.this.$iterationCount + ") >>> doIfBackendOK(" + connected + ')');
                    Iterator it = AnonymousClass2.this.$listeners.iterator();
                    while (it.hasNext()) {
                        ((IConnectivityStateChanged) it.next()).doIfBackendOK(connected, new Date().getTime() - AnonymousClass2.this.$errorTimestamp);
                        NetworkStateManager.this.getAppStatus().onNext(connected);
                    }
                } else {
                    AnonymousClass2.this.$breadcrumbHandler.invoke(AnonymousClass2.this.$internalTag, "(rid:" + AnonymousClass2.this.$randomId + '-' + AnonymousClass2.this.$iterationCount + ") >>> Backend is not available");
                    NetworkStatus.ConnectedNoBackendAccess connectedNoBackendAccess3 = new NetworkStatus.ConnectedNoBackendAccess(NetworkStateManager.this.getActiveNetworkType(), AnonymousClass2.this.$error);
                    AnonymousClass2.this.$breadcrumbHandler.invoke(AnonymousClass2.this.$internalTag, "(rid:" + AnonymousClass2.this.$randomId + '-' + AnonymousClass2.this.$iterationCount + ") >>> doIfNoBackend(" + connectedNoBackendAccess3 + ')');
                    Iterator it2 = AnonymousClass2.this.$listeners.iterator();
                    while (it2.hasNext()) {
                        NetworkStatus.ConnectedNoBackendAccess connectedNoBackendAccess4 = connectedNoBackendAccess3;
                        ((IConnectivityStateChanged) it2.next()).doIfNoBackend(AnonymousClass2.this.$url, AnonymousClass2.this.$error, connectedNoBackendAccess3, new Date().getTime() - AnonymousClass2.this.$errorTimestamp, backendReachability3);
                        NetworkStateManager.this.getAppStatus().onNext(connectedNoBackendAccess4);
                        connectedNoBackendAccess3 = connectedNoBackendAccess4;
                    }
                    NetworkStatus.ConnectedNoBackendAccess connectedNoBackendAccess5 = connectedNoBackendAccess3;
                    if (AnonymousClass2.this.$backendRecheckMaxDelay == null) {
                        jRandom = AnonymousClass2.this.$delayTimeUnit.toMillis(AnonymousClass2.this.$backendRecheckMinDelay);
                    } else {
                        jRandom = RangesKt.random(new LongRange(AnonymousClass2.this.$delayTimeUnit.toMillis(AnonymousClass2.this.$backendRecheckMinDelay), AnonymousClass2.this.$delayTimeUnit.toMillis(AnonymousClass2.this.$backendRecheckMaxDelay.longValue())), Random.INSTANCE);
                    }
                    AnonymousClass2.this.$breadcrumbHandler.invoke(AnonymousClass2.this.$internalTag, "(rid:" + AnonymousClass2.this.$randomId + '-' + AnonymousClass2.this.$iterationCount + ") >>> Loop algorithm. Full recheck in : " + NetworkStateManager.this.convertMillisToDuration(jRandom));
                    this.L$0 = coroutineScope;
                    this.L$1 = backendReachability3;
                    this.L$2 = connectedNoBackendAccess5;
                    this.J$0 = jRandom;
                    this.label = 2;
                    if (DelayKt.delay(jRandom, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    connectedNoBackendAccess = connectedNoBackendAccess5;
                    backendReachability = backendReachability3;
                    j = jRandom;
                    coroutineScope2 = coroutineScope;
                    networkStateManager = NetworkStateManager.this;
                    i = AnonymousClass2.this.$iterationCount + 1;
                    powerManager = AnonymousClass2.this.$powerManager;
                    i2 = AnonymousClass2.this.$randomId;
                    url = AnonymousClass2.this.$url;
                    th = AnonymousClass2.this.$error;
                    j2 = AnonymousClass2.this.$errorTimestamp;
                    list = AnonymousClass2.this.$listeners;
                    function1 = AnonymousClass2.this.$isBackendReachable;
                    long j7 = j;
                    j3 = AnonymousClass2.this.$backendCheckInitialDelay;
                    j4 = AnonymousClass2.this.$backendRecheckMinDelay;
                    l = AnonymousClass2.this.$backendRecheckMaxDelay;
                    timeUnit = AnonymousClass2.this.$delayTimeUnit;
                    function2 = AnonymousClass2.this.$breadcrumbHandler;
                    this.L$0 = coroutineScope2;
                    this.L$1 = backendReachability;
                    this.L$2 = connectedNoBackendAccess;
                    this.J$0 = j7;
                    this.label = 3;
                    if (networkStateManager.handleNetworkError(false, i, powerManager, i2, url, th, j2, list, function1, j3, j4, l, timeUnit, function2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
                this.label = 1;
                if (BuildersKt.withContext(io2, anonymousClass1, this) == coroutine_suspended) {
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
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$3, reason: invalid class name */
    /* JADX INFO: compiled from: NetworkStateManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"waitForInternetCapability", "", "invoke", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$3", f = "NetworkStateManager.kt", i = {}, l = {524}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2 $breadcrumbHandler;
        final /* synthetic */ String $internalTag;
        final /* synthetic */ int $iterationCount;
        final /* synthetic */ int $randomId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Function2 function2, String str, int i, int i2, Continuation continuation) {
            super(1, continuation);
            this.$breadcrumbHandler = function2;
            this.$internalTag = str;
            this.$randomId = i;
            this.$iterationCount = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            return NetworkStateManager.this.new AnonymousClass3(this.$breadcrumbHandler, this.$internalTag, this.$randomId, this.$iterationCount, completion);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: NetworkStateManager.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.farkit.network.NetworkStateManager$handleNetworkError$3$1", f = "NetworkStateManager.kt", i = {0}, l = {528}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            int label;
            private CoroutineScope p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = AnonymousClass3.this.new AnonymousClass1(completion);
                anonymousClass1.p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope2 = this.p$;
                    AnonymousClass3.this.$breadcrumbHandler.invoke(AnonymousClass3.this.$internalTag, "(rid:" + AnonymousClass3.this.$randomId + '-' + AnonymousClass3.this.$iterationCount + ") >>> Waiting for internet capability");
                    coroutineScope = coroutineScope2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                while (!NetworkStateManager.this.hasInternetCapability()) {
                    CoroutineScopeKt.ensureActive(coroutineScope);
                    long j = NetworkStateManager.this.checkCapacityDelay;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (DelayKt.delay(j, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                AnonymousClass3.this.$breadcrumbHandler.invoke(AnonymousClass3.this.$internalTag, "(rid:" + AnonymousClass3.this.$randomId + '-' + AnonymousClass3.this.$iterationCount + ") >>> Internet capability came back. Network is : " + NetworkStateManager.this.getActiveNetworkType());
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
                this.label = 1;
                if (BuildersKt.withContext(io2, anonymousClass1, this) == coroutine_suspended) {
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
    }

    final /* synthetic */ Object ping(String str, Continuation<? super PingData> continuation) {
        PingData pingData;
        Log.v("NetworkStateManager", "will ping(ICMP) " + str);
        Runtime runtime = Runtime.getRuntime();
        try {
            Log.v("NetworkStateManager", "Ping will execute");
            Process process = runtime.exec("/system/bin/ping -c 1 " + str);
            Intrinsics.checkExpressionValueIsNotNull(process, "process");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            Ref.IntRef intRef = new Ref.IntRef();
            char[] cArr = new char[4096];
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                Integer numBoxInt = Boxing.boxInt(bufferedReader.read(cArr));
                intRef.element = numBoxInt.intValue();
                if (numBoxInt.intValue() > 0) {
                    stringBuffer.append(cArr, 0, intRef.element);
                } else {
                    bufferedReader.close();
                    int iWaitFor = process.waitFor();
                    Log.v("NetworkStateManager", "(ping) " + str + " mExitValue " + iWaitFor);
                    Log.v("NetworkStateManager", "(ping) " + str + " output: " + stringBuffer);
                    PingData.Companion companion = PingData.INSTANCE;
                    String string = stringBuffer.toString();
                    Intrinsics.checkExpressionValueIsNotNull(string, "output.toString()");
                    return companion.parsePingOutput(str, iWaitFor, string);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("NetworkStateManager", "(ping)  " + str + " IOException:" + e);
            pingData = new PingData(str, false, null, null, null, 28, null);
            return pingData;
        } catch (InterruptedException e2) {
            Log.v("NetworkStateManager", "(ping)  " + str + " Exception:" + e2);
            pingData = new PingData(str, false, null, null, null, 28, null);
            return pingData;
        }
    }

    final /* synthetic */ Object httpPing(String str, Continuation<? super Triple<String, Integer, String>> continuation) {
        String message;
        URL url = new URL(str);
        Log.v("NetworkStateManager", "\n httpPing will start " + url);
        try {
            URLConnection uRLConnectionOpenConnection = url.openConnection();
            if (uRLConnectionOpenConnection == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            Log.v("NetworkStateManager", "\nSent 'GET' request to URL : " + url + "; Response Code : " + httpURLConnection.getResponseCode());
            return new Triple(str, Boxing.boxInt(httpURLConnection.getResponseCode()), httpURLConnection.getHeaderField("Date"));
        } catch (IOException e) {
            Log.v("NetworkStateManager", "\n httpPing failed");
            Integer numBoxInt = Boxing.boxInt(-1);
            Throwable cause = e.getCause();
            if (cause == null || (message = cause.getMessage()) == null) {
                message = e.getMessage();
            }
            return new Triple(str, numBoxInt, message);
        }
    }
}
