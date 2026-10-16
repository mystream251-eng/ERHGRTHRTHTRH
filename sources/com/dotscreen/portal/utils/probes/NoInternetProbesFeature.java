package com.dotscreen.portal.utils.probes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.network.PingData;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.portal.BuildConfig;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.utils.Coroutines_ExtensionsKt;
import com.dotscreen.portal.utils.String_ExtensionKt;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.DeviceInfo;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.voplayer.VOPlayer;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.sentry.protocol.App;
import io.sentry.protocol.SentryId;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.json.JSONObject;

/* JADX INFO: compiled from: NoInternetProbesFeature.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\u0017\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\u0019J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002J\u0006\u0010!\u001a\u00020\u0019J\u0011\u0010\"\u001a\u00020\u0019H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010#J\u0006\u0010$\u001a\u00020\u0019R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lcom/dotscreen/portal/utils/probes/NoInternetProbesFeature;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", App.TYPE, "Lcom/dotscreen/portal/PortalApplicationSingletons;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "paused", "", "probes", "", "Lcom/dotscreen/portal/utils/probes/Probe;", "", "started", "uptimeDone", "execute", "", "getResponseCode", "", ImagesContract.URL, "(Ljava/lang/String;)Ljava/lang/Integer;", ToolboxAnalyticsModuleKt.EVENT_PAUSE, "ping", "Lcom/dotscreen/farkit/network/PingData;", ToolboxAnalyticsModuleKt.EVENT_RESUME, "run", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NoInternetProbesFeature implements CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0;
    private final String TAG;
    private final PortalApplicationSingletons app;
    private final Context context;
    private Job job;
    private boolean paused;
    private final List<Probe<? extends Object>> probes;
    private boolean started;
    private boolean uptimeDone;

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public NoInternetProbesFeature(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.$$delegate_0 = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.context = context;
        this.TAG = NoInternetProbesFeature.class.getSimpleName();
        Object obj = this.context;
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        this.app = (PortalApplicationSingletons) obj;
        final String str = "device.serial";
        final String str2 = "SERIAL_NUMBER";
        final String str3 = "device.voPlayerID";
        final String str4 = "VO_PLAYER_ID";
        final String str5 = "device.appVersion";
        final String str6 = "APP_VERSION";
        final String str7 = "device.firmwareVersion";
        final String str8 = "FIRMWARE_VERSION";
        final String str9 = "device.model";
        final String str10 = "DEVICE_MODEL";
        final String str11 = null;
        final String str12 = "ERROR_TYPE";
        final String str13 = "device.localIP";
        final String str14 = "LOCAL_IP";
        final String str15 = "device.publicIP";
        final String str16 = "PUBLIC_IP";
        final String str17 = "device.connectionType";
        final String str18 = "CONNECTION_TYPE";
        final String str19 = "device.dns";
        final String str20 = "DNS";
        final String str21 = "device.timestamp";
        final String str22 = "TIMESTAMP";
        final String str23 = "ping.ip.cloudflare";
        final String str24 = "PING_IP_CLOUDFLARE_DNS";
        final String str25 = "ping.ip.fwVoVdr";
        final String str26 = "PING_IP_FW_VO_VDR";
        final String str27 = "ping.ip.google";
        final String str28 = "PING_IP_GOOGLE_DNS";
        final String str29 = "ping.ip.megacable";
        final String str30 = "PING_IP_MEGACABLE";
        final String str31 = "ping.fqdn.cloudflare";
        final String str32 = "PING_FQDN_CLOUDFLARE_DNS";
        final String str33 = "ping.fqdn.xviewplus";
        final String str34 = "PING_FQDN_XVIEWPLUS";
        final String str35 = "ping.fqdn.googleTime";
        final String str36 = "PING_FQDN_GOOGLE_TIME";
        final String str37 = "request.http.megacable";
        final String str38 = "REQ_HTTP_MEGA";
        final String str39 = "request.http.orange";
        final String str40 = "REQ_HTTP_ORANGE";
        final String str41 = "request.https.megacable";
        final String str42 = "REQ_HTTPS_MEGA";
        final String str43 = "request.https.compassIsAlive";
        final String str44 = "REQ_HTTPS_COMPASS_ISALIVE";
        final String str45 = "request.https.rteFacadeIsAlive";
        final String str46 = "REQ_HTTPS_RTEFACADE_ISALIVE";
        final String str47 = "request.https.uiopReadiness";
        final String str48 = "REQ_HTTPS_UIOP_READINESS";
        this.probes = CollectionsKt.listOf((Object[]) new Probe[]{new Probe<String>(str, str2) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$1
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                try {
                    return Build.getSerial();
                } catch (Exception unused) {
                    return null;
                }
            }
        }, new Probe<String>(str3, str4) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$2
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) throws VOException {
                String uniqueIdentifier = VOPlayer.getUniqueIdentifier(this.this$0.context);
                Intrinsics.checkExpressionValueIsNotNull(uniqueIdentifier, "VOPlayer.getUniqueIdentifier(context)");
                return String_ExtensionKt.hash256(uniqueIdentifier);
            }
        }, new Probe<String>(str5, str6) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$3
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                return BuildConfig.VERSION_NAME;
            }
        }, new Probe<String>(str7, str8) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$4
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                return DeviceInfo.INSTANCE.getFirmwareVersionName();
            }
        }, new Probe<String>(str9, str10) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$5
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                return DeviceInfo.INSTANCE.getDeviceModel();
            }
        }, new Probe<String>(str11, str12) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$6
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                return "INTERNET_CHECK_EVENT";
            }
        }, new Probe<String>(str13, str14) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$7
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                try {
                    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    Intrinsics.checkExpressionValueIsNotNull(networkInterfaces, "NetworkInterface.getNetworkInterfaces()");
                    String hostAddress = (String) null;
                    while (networkInterfaces.hasMoreElements()) {
                        NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                        Intrinsics.checkExpressionValueIsNotNull(networkInterfaceNextElement, "en.nextElement()");
                        Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                        Intrinsics.checkExpressionValueIsNotNull(inetAddresses, "intf.inetAddresses");
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddressNextElement = inetAddresses.nextElement();
                            Intrinsics.checkExpressionValueIsNotNull(inetAddressNextElement, "enumIpAddr.nextElement()");
                            InetAddress inetAddress = inetAddressNextElement;
                            if (!inetAddress.isLoopbackAddress()) {
                                if (inetAddress instanceof Inet4Address) {
                                    hostAddress = inetAddress.getHostAddress();
                                } else if (inetAddress instanceof Inet6Address) {
                                    hostAddress = inetAddress.getHostAddress();
                                }
                            }
                        }
                    }
                    return hostAddress;
                } catch (SocketException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }, new Probe<String>(str15, str16) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$8
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) throws Throwable {
                Single jSONObject$default;
                Single singleTimeout;
                Single singleSubscribeOn;
                Single singleObserveOn;
                SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
                final SafeContinuation safeContinuation2 = safeContinuation;
                RequestManager requestManager = this.this$0.app.getRequestManager();
                if (requestManager != null && (jSONObject$default = RequestManager.getJSONObject$default(requestManager, 0, new URL("https://api.ipify.org/?format=json"), null, 4, null)) != null && (singleTimeout = jSONObject$default.timeout(10L, TimeUnit.SECONDS)) != null && (singleSubscribeOn = singleTimeout.subscribeOn(Schedulers.io())) != null && (singleObserveOn = singleSubscribeOn.observeOn(Schedulers.io())) != null) {
                    singleObserveOn.subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$8$launch$2$1
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(JSONObject jSONObject) {
                            try {
                                Continuation continuation2 = safeContinuation2;
                                String string = jSONObject.getString("ip");
                                Result.Companion companion = Result.INSTANCE;
                                continuation2.resumeWith(Result.m37constructorimpl(string));
                            } catch (Exception e) {
                                Continuation continuation3 = safeContinuation2;
                                Result.Companion companion2 = Result.INSTANCE;
                                continuation3.resumeWith(Result.m37constructorimpl(ResultKt.createFailure(e)));
                            }
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$8$launch$2$2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable e) {
                            Continuation continuation2 = safeContinuation2;
                            Intrinsics.checkExpressionValueIsNotNull(e, "e");
                            Result.Companion companion = Result.INSTANCE;
                            continuation2.resumeWith(Result.m37constructorimpl(ResultKt.createFailure(e)));
                        }
                    });
                }
                Object orThrow = safeContinuation.getOrThrow();
                if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return orThrow;
            }
        }, new Probe<String>(str17, str18) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$9
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                NetworkStatus value = this.this$0.app.getNetworkStateManager().getAppStatus().getValue();
                if (value instanceof NetworkStatus.Connected) {
                    return ((NetworkStatus.Connected) value).getType().getVoName();
                }
                if (value instanceof NetworkStatus.ConnectedNoBackendAccess) {
                    return ((NetworkStatus.ConnectedNoBackendAccess) value).getType().getVoName();
                }
                return value instanceof NetworkStatus.ConnectedNoInternet ? ((NetworkStatus.ConnectedNoInternet) value).getType().getVoName() : "NONE";
            }
        }, new Probe<String>(str19, str20) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$10
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                Network[] allNetworks;
                try {
                    Object systemService = this.this$0.context.getSystemService("connectivity");
                    if (!(systemService instanceof ConnectivityManager)) {
                        systemService = null;
                    }
                    ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                    String hostAddress = (String) null;
                    if (connectivityManager != null && (allNetworks = connectivityManager.getAllNetworks()) != null) {
                        for (Network network : allNetworks) {
                            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                            Intrinsics.checkExpressionValueIsNotNull(networkInfo, "networkInfo");
                            if (networkInfo.isConnected()) {
                                LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                                Intrinsics.checkExpressionValueIsNotNull(linkProperties, "linkProperties");
                                List<InetAddress> dnsServers = linkProperties.getDnsServers();
                                Intrinsics.checkExpressionValueIsNotNull(dnsServers, "linkProperties.dnsServers");
                                for (InetAddress inetAddress : dnsServers) {
                                    if (inetAddress instanceof Inet4Address) {
                                        hostAddress = ((Inet4Address) inetAddress).getHostAddress();
                                    }
                                }
                            }
                        }
                    }
                    return hostAddress;
                } catch (SocketException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }, new Probe<String>(str21, str22) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$11
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super String> continuation) {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneOffset.UTC).format(Instant.now());
            }
        }, new Probe<Boolean>(str23, str24) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$12
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Boolean> continuation) {
                return Boxing.boxBoolean(this.this$0.ping("1.1.1.1").getSuccess());
            }
        }, new Probe<Boolean>(str25, str26) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$13
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Boolean> continuation) {
                return Boxing.boxBoolean(this.this$0.ping("185.145.78.1").getSuccess());
            }
        }, new Probe<Boolean>(str27, str28) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$14
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Boolean> continuation) {
                return Boxing.boxBoolean(this.this$0.ping("8.8.8.8").getSuccess());
            }
        }, new Probe<Boolean>(str29, str30) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$15
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Boolean> continuation) {
                return Boxing.boxBoolean(this.this$0.ping("189.199.76.3").getSuccess());
            }
        }, new Probe<Boolean>(str31, str32) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$16
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Boolean> continuation) {
                return Boxing.boxBoolean(this.this$0.ping("dns.cloudflare.com").getSuccess());
            }
        }, new Probe<Boolean>(str33, str34) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$17
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Boolean> continuation) {
                return Boxing.boxBoolean(this.this$0.ping("xviewplus.com.mx").getSuccess());
            }
        }, new Probe<Boolean>(str35, str36) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$18
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Boolean> continuation) {
                return Boxing.boxBoolean(this.this$0.ping("time.google.com").getSuccess());
            }
        }, new Probe<Integer>(str37, str38) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$19
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Integer> continuation) {
                return this.this$0.getResponseCode("http://xviewplus.com.mx");
            }
        }, new Probe<Integer>(str39, str40) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$20
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Integer> continuation) {
                return this.this$0.getResponseCode("http://www.orange.com");
            }
        }, new Probe<Integer>(str41, str42) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$21
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Integer> continuation) {
                return this.this$0.getResponseCode("https://xviewplus.com.mx");
            }
        }, new Probe<Integer>(str43, str44) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$22
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Integer> continuation) {
                return this.this$0.getResponseCode("https://xviewplus.com.mx/compass/IsAlive");
            }
        }, new Probe<Integer>(str45, str46) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$23
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Integer> continuation) {
                return this.this$0.getResponseCode("https://xviewplus.com.mx/RTEFacade/IsAlive");
            }
        }, new Probe<Integer>(str47, str48) { // from class: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$probes$24
            @Override // com.dotscreen.portal.utils.probes.Probe
            public Object launch(Continuation<? super Integer> continuation) {
                return this.this$0.getResponseCode("https://xviewplus.com.mx/api/readiness");
            }
        }});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PingData ping(String url) {
        PingData pingData;
        try {
            Process process = Runtime.getRuntime().exec("/system/bin/ping -c 1 " + url);
            Intrinsics.checkExpressionValueIsNotNull(process, "process");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            Ref.IntRef intRef = new Ref.IntRef();
            char[] cArr = new char[4096];
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                int i = bufferedReader.read(cArr);
                intRef.element = i;
                if (i > 0) {
                    stringBuffer.append(cArr, 0, intRef.element);
                } else {
                    bufferedReader.close();
                    int iWaitFor = process.waitFor();
                    Log.v(this.TAG, "(ping) " + url + " mExitValue " + iWaitFor);
                    Log.v(this.TAG, "(ping) " + url + " output: " + stringBuffer);
                    PingData.Companion companion = PingData.INSTANCE;
                    String string = stringBuffer.toString();
                    Intrinsics.checkExpressionValueIsNotNull(string, "output.toString()");
                    return companion.parsePingOutput(url, iWaitFor, string);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v(this.TAG, "(ping)  " + url + " IOException:" + e);
            pingData = new PingData(url, false, null, null, null, 28, null);
            return pingData;
        } catch (InterruptedException e2) {
            Log.v(this.TAG, "(ping)  " + url + " Exception:" + e2);
            pingData = new PingData(url, false, null, null, null, 28, null);
            return pingData;
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$getResponseCode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NoInternetProbesFeature.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.utils.probes.NoInternetProbesFeature$getResponseCode$1", f = "NoInternetProbesFeature.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ String $url;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04471(String str, Continuation continuation) {
            super(2, continuation);
            this.$url = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04471 c04471 = NoInternetProbesFeature.this.new C04471(this.$url, completion);
            c04471.p$ = (CoroutineScope) obj;
            return c04471;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C04471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            Integer numBoxInt;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                URLConnection request = new URL(this.$url).openConnection();
                Log.v(NoInternetProbesFeature.this.TAG, "(http request) connection opened with " + this.$url);
                Intrinsics.checkExpressionValueIsNotNull(request, "request");
                request.setConnectTimeout(10000);
                request.setReadTimeout(10000);
                request.connect();
                Log.v(NoInternetProbesFeature.this.TAG, "(http request) connection established with " + this.$url);
                if (request instanceof HttpURLConnection) {
                    numBoxInt = Boxing.boxInt(((HttpURLConnection) request).getResponseCode());
                } else {
                    numBoxInt = request instanceof HttpsURLConnection ? Boxing.boxInt(((HttpsURLConnection) request).getResponseCode()) : null;
                }
                Log.v(NoInternetProbesFeature.this.TAG, "(http request) get response => " + numBoxInt + " for : " + this.$url);
                return numBoxInt;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Integer getResponseCode(String url) {
        return (Integer) BuildersKt__BuildersKt.runBlocking$default(null, new C04471(url, null), 1, null);
    }

    public final void start() {
        Log.v(this.TAG, "start()");
        if (!this.started) {
            BuildersKt__Builders_commonKt.launch$default(this, null, null, new C04481(null), 3, null);
        }
        this.started = true;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$start$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NoInternetProbesFeature.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.utils.probes.NoInternetProbesFeature$start$1", f = "NoInternetProbesFeature.kt", i = {0}, l = {310}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C04481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        C04481(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04481 c04481 = NoInternetProbesFeature.this.new C04481(completion);
            c04481.p$ = (CoroutineScope) obj;
            return c04481;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                long j = NoInternetProbesFeatureKt.UPTIME_DELAY;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (Coroutines_ExtensionsKt.randomizedDelay(j, 0.1d, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            NoInternetProbesFeature.this.uptimeDone = true;
            NoInternetProbesFeature.this.execute();
            return Unit.INSTANCE;
        }
    }

    public final void execute() {
        Log.v(this.TAG, "execute() ready:" + this.uptimeDone);
        if (this.uptimeDone) {
            Job job = this.job;
            Job jobLaunch$default = null;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            if (this.app.getFeaturesManager().isFeatureEnabled("no_internet_probes")) {
                Log.v(this.TAG, "Will try to run");
                jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(this, null, null, new AnonymousClass1(null), 3, null);
            } else {
                Log.v(this.TAG, "Will never run");
            }
            this.job = jobLaunch$default;
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$execute$1, reason: invalid class name */
    /* JADX INFO: compiled from: NoInternetProbesFeature.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.utils.probes.NoInternetProbesFeature$execute$1", f = "NoInternetProbesFeature.kt", i = {0, 1, 2}, l = {327, 329, 331}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$0"})
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
            AnonymousClass1 anonymousClass1 = NoInternetProbesFeature.this.new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0037  */
        /* JADX WARN: Code duplicated, block: B:17:0x004e A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:20:0x005c  */
        /* JADX WARN: Code duplicated, block: B:25:0x0079 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:29:0x006a A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:31:? A[LOOP:0: B:18:0x004f->B:31:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0077 -> B:13:0x0031). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:25:0x0079
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L2a
                if (r1 == r4) goto L21
                if (r1 == r3) goto L21
                if (r1 != r2) goto L19
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L30
            L19:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L21:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r11)
                r11 = r10
                goto L4f
            L2a:
                kotlin.ResultKt.throwOnFailure(r11)
                kotlinx.coroutines.CoroutineScope r11 = r10.p$
                r1 = r11
            L30:
                r11 = r10
            L31:
                boolean r5 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)
                if (r5 == 0) goto L7a
                com.dotscreen.portal.utils.probes.NoInternetProbesFeature r5 = com.dotscreen.portal.utils.probes.NoInternetProbesFeature.this
                java.lang.String r5 = com.dotscreen.portal.utils.probes.NoInternetProbesFeature.access$getTAG$p(r5)
                java.lang.String r6 = "Will run"
                android.util.Log.v(r5, r6)
                com.dotscreen.portal.utils.probes.NoInternetProbesFeature r5 = com.dotscreen.portal.utils.probes.NoInternetProbesFeature.this
                r11.L$0 = r1
                r11.label = r4
                java.lang.Object r5 = r5.run(r11)
                if (r5 != r0) goto L4f
                return r0
            L4f:
                com.dotscreen.portal.utils.probes.NoInternetProbesFeature r5 = com.dotscreen.portal.utils.probes.NoInternetProbesFeature.this
                boolean r5 = com.dotscreen.portal.utils.probes.NoInternetProbesFeature.access$getPaused$p(r5)
                r6 = 4591870180066957722(0x3fb999999999999a, double:0.1)
                if (r5 == 0) goto L6b
                long r8 = com.dotscreen.portal.utils.probes.NoInternetProbesFeatureKt.access$getPAUSE_DELAY$p()
                r11.L$0 = r1
                r11.label = r3
                java.lang.Object r5 = com.dotscreen.portal.utils.Coroutines_ExtensionsKt.randomizedDelay(r8, r6, r11)
                if (r5 != r0) goto L4f
                return r0
            L6b:
                long r8 = com.dotscreen.portal.utils.probes.NoInternetProbesFeatureKt.access$getREPEAT_DELAY$p()
                r11.L$0 = r1
                r11.label = r2
                java.lang.Object r5 = com.dotscreen.portal.utils.Coroutines_ExtensionsKt.randomizedDelay(r8, r6, r11)
                if (r5 != r0) goto L31
                return r0
            L7a:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.portal.utils.probes.NoInternetProbesFeature.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void pause() {
        if (!this.started || this.paused) {
            return;
        }
        this.paused = true;
    }

    public final void resume() {
        if (this.paused) {
            this.paused = false;
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.utils.probes.NoInternetProbesFeature$run$2, reason: invalid class name */
    /* JADX INFO: compiled from: NoInternetProbesFeature.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00070\u0001¢\u0006\u0002\b\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Lio/sentry/protocol/SentryId;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.utils.probes.NoInternetProbesFeature$run$2", f = "NoInternetProbesFeature.kt", i = {0, 0, 0, 0, 0, 0}, l = {364}, m = "invokeSuspend", n = {"$this$coroutineScope", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "pair"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SentryId>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        private CoroutineScope p$;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = NoInternetProbesFeature.this.new AnonymousClass2(completion);
            anonymousClass2.p$ = (CoroutineScope) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SentryId> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x00ac  */
        /* JADX WARN: Code duplicated, block: B:17:0x00d4 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x00d5  */
        /* JADX WARN: Code duplicated, block: B:22:0x00e4  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00d5 -> B:19:0x00df). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 742
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.portal.utils.probes.NoInternetProbesFeature.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    final /* synthetic */ Object run(Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }
}
