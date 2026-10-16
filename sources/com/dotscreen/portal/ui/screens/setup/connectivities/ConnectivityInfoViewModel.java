package com.dotscreen.portal.ui.screens.setup.connectivities;

import android.app.Application;
import android.net.NetworkCapabilities;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.network.NetworkType;
import com.dotscreen.farkit.network.PingData;
import com.dotscreen.farkit.network.ServerStatus;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.google.android.exoplayer2.util.MimeTypes;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectivityInfoViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001@B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u00109\u001a\u00020:J\b\u0010;\u001a\u00020:H\u0002J\b\u0010<\u001a\u00020:H\u0002J\b\u0010=\u001a\u00020:H\u0002J\b\u0010>\u001a\u00020:H\u0002J\b\u0010?\u001a\u00020:H\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u000eR \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000eR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010\fR \u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000eR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR \u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\f\"\u0004\b'\u0010\u000eR\u000e\u0010(\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\f\"\u0004\b,\u0010\u000eR \u0010-\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\f\"\u0004\b/\u0010\u000eR\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R \u00106\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\f\"\u0004\b8\u0010\u000e¨\u0006A"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/connectivities/ConnectivityInfoViewModel;", "Landroidx/lifecycle/AndroidViewModel;", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "(Landroid/app/Application;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "compassStatusLiveData", "Landroidx/lifecycle/MutableLiveData;", "getCompassStatusLiveData", "()Landroidx/lifecycle/MutableLiveData;", "setCompassStatusLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "connectivityCheckDateLiveData", "getConnectivityCheckDateLiveData", "setConnectivityCheckDateLiveData", "connectivityCheckServerLiveData", "getConnectivityCheckServerLiveData", "setConnectivityCheckServerLiveData", "connectivityStatusLiveData", "getConnectivityStatusLiveData", "setConnectivityStatusLiveData", "googleICMPLiveData", "getGoogleICMPLiveData", "setGoogleICMPLiveData", "hasInternetLiveData", "getHasInternetLiveData", "httpsGoogleServerLiveData", "getHttpsGoogleServerLiveData", "setHttpsGoogleServerLiveData", "isValidatedConnectionLiveData", "rightVStatusLiveData", "getRightVStatusLiveData", "setRightVStatusLiveData", "strLoading", "strNotAvailable", "timeAndroidServerStatusLiveData", "getTimeAndroidServerStatusLiveData", "setTimeAndroidServerStatusLiveData", "uiopStatusLiveData", "getUiopStatusLiveData", "setUiopStatusLiveData", "userService", "Lcom/dotscreen/tv/common/bokit/MegacableUserService;", "getUserService", "()Lcom/dotscreen/tv/common/bokit/MegacableUserService;", "setUserService", "(Lcom/dotscreen/tv/common/bokit/MegacableUserService;)V", "xviewplusServerLiveData", "getXviewplusServerLiveData", "setXviewplusServerLiveData", "check", "", "checkCompass", "checkConnectivity", "checkRightV", "checkUIOP", "onCleared", "NetworkState", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ConnectivityInfoViewModel extends AndroidViewModel {
    private final String TAG;
    private MutableLiveData<String> compassStatusLiveData;
    private final CompositeDisposable compositeDisposable;
    private MutableLiveData<String> connectivityCheckDateLiveData;
    private MutableLiveData<String> connectivityCheckServerLiveData;
    private MutableLiveData<String> connectivityStatusLiveData;
    private MutableLiveData<String> googleICMPLiveData;
    private final MutableLiveData<String> hasInternetLiveData;
    private MutableLiveData<String> httpsGoogleServerLiveData;
    private final MutableLiveData<String> isValidatedConnectionLiveData;
    private MutableLiveData<String> rightVStatusLiveData;
    private final String strLoading;
    private final String strNotAvailable;
    private MutableLiveData<String> timeAndroidServerStatusLiveData;
    private MutableLiveData<String> uiopStatusLiveData;
    private MegacableUserService userService;
    private MutableLiveData<String> xviewplusServerLiveData;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[NetworkType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[NetworkType.Ethernet.ordinal()] = 1;
            $EnumSwitchMapping$0[NetworkType.Wifi.ordinal()] = 2;
            $EnumSwitchMapping$0[NetworkType.Mobile.ordinal()] = 3;
            int[] iArr2 = new int[NetworkType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[NetworkType.Ethernet.ordinal()] = 1;
            $EnumSwitchMapping$1[NetworkType.Wifi.ordinal()] = 2;
            $EnumSwitchMapping$1[NetworkType.Mobile.ordinal()] = 3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectivityInfoViewModel(Application application) {
        super(application);
        Intrinsics.checkParameterIsNotNull(application, "application");
        String string = getApplication().getString(R.string.settings_connectivity_not_available);
        Intrinsics.checkExpressionValueIsNotNull(string, "getApplication<Applicati…nnectivity_not_available)");
        this.strNotAvailable = string;
        String string2 = getApplication().getString(R.string.settings_connectivity_loading);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getApplication<Applicati…ngs_connectivity_loading)");
        this.strLoading = string2;
        Application application2 = getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application2, "getApplication<PortalApplication>()");
        UserService userService = ((PortalApplication) application2).getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        this.userService = (MegacableUserService) userService;
        this.connectivityStatusLiveData = new MutableLiveData<>(this.strLoading);
        this.uiopStatusLiveData = new MutableLiveData<>(this.strLoading);
        this.compassStatusLiveData = new MutableLiveData<>(this.strLoading);
        this.rightVStatusLiveData = new MutableLiveData<>(this.strLoading);
        this.timeAndroidServerStatusLiveData = new MutableLiveData<>(this.strLoading);
        this.googleICMPLiveData = new MutableLiveData<>(this.strLoading);
        this.xviewplusServerLiveData = new MutableLiveData<>(this.strLoading);
        this.connectivityCheckServerLiveData = new MutableLiveData<>(this.strLoading);
        this.httpsGoogleServerLiveData = new MutableLiveData<>(this.strLoading);
        this.connectivityCheckDateLiveData = new MutableLiveData<>(this.strLoading);
        this.hasInternetLiveData = new MutableLiveData<>(this.strLoading);
        this.isValidatedConnectionLiveData = new MutableLiveData<>(this.strLoading);
        this.compositeDisposable = new CompositeDisposable();
        this.TAG = "ConnectivityViewModel";
        Application application3 = getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application3, "getApplication<PortalApplication>()");
        this.compositeDisposable.add(((PortalApplication) application3).getNetworkStateManager().getDeviceStatus().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NetworkStatus>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$disposable$1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // io.reactivex.functions.Consumer
            public final void accept(NetworkStatus networkStatus) {
                String strValueOf;
                Integer statusCode;
                String strValueOf2;
                Integer statusCode2;
                String resolvedIp;
                String resolvedIp2;
                if (!(networkStatus instanceof NetworkStatus.Connected) && (!(networkStatus instanceof NetworkStatus.ConnectedNoInternet) || !(!networkStatus.getServerStatus().isEmpty()))) {
                    this.this$0.getTimeAndroidServerStatusLiveData().setValue(this.this$0.strNotAvailable);
                    this.this$0.getGoogleICMPLiveData().setValue(this.this$0.strNotAvailable);
                    this.this$0.getXviewplusServerLiveData().setValue(this.this$0.strNotAvailable);
                    this.this$0.getConnectivityCheckServerLiveData().setValue(this.this$0.strNotAvailable);
                    this.this$0.getConnectivityCheckDateLiveData().setValue(this.this$0.strNotAvailable);
                    this.this$0.getHttpsGoogleServerLiveData().setValue(this.this$0.strNotAvailable);
                    return;
                }
                Log.e(this.this$0.getTAG(), "We have " + networkStatus.getServerStatus().size() + " server(s)");
                for (ServerStatus serverStatus : networkStatus.getServerStatus()) {
                    Log.e(this.this$0.getTAG(), "server: " + serverStatus);
                    String url = serverStatus.getUrl();
                    switch (url.hashCode()) {
                        case -1632447086:
                            if (url.equals("https://www.google.com")) {
                                MutableLiveData<String> httpsGoogleServerLiveData = this.this$0.getHttpsGoogleServerLiveData();
                                if (serverStatus.getStatusCode() == null || ((statusCode = serverStatus.getStatusCode()) != null && statusCode.intValue() == -1)) {
                                    strValueOf = serverStatus.getExtra().get("errorMessage");
                                } else {
                                    Integer statusCode3 = serverStatus.getStatusCode();
                                    strValueOf = String.valueOf(statusCode3 != null ? statusCode3.intValue() : -1);
                                }
                                httpsGoogleServerLiveData.setValue(strValueOf);
                            }
                            break;
                        case -1161062878:
                            if (url.equals("http://connectivitycheck.gstatic.com/generate_204")) {
                                MutableLiveData<String> connectivityCheckServerLiveData = this.this$0.getConnectivityCheckServerLiveData();
                                if (serverStatus.getStatusCode() == null || ((statusCode2 = serverStatus.getStatusCode()) != null && statusCode2.intValue() == -1)) {
                                    strValueOf2 = serverStatus.getExtra().get("errorMessage");
                                } else {
                                    Integer statusCode4 = serverStatus.getStatusCode();
                                    strValueOf2 = String.valueOf(statusCode4 != null ? statusCode4.intValue() : -1);
                                }
                                connectivityCheckServerLiveData.setValue(strValueOf2);
                                this.this$0.getConnectivityCheckDateLiveData().setValue(serverStatus.getExtra().get("date"));
                            }
                            break;
                        case -702889725:
                            if (url.equals("www.google.com")) {
                                MutableLiveData<String> googleICMPLiveData = this.this$0.getGoogleICMPLiveData();
                                PingData pingData = serverStatus.getPingData();
                                if (pingData == null || (resolvedIp = pingData.getResolvedIp()) == null) {
                                    resolvedIp = this.this$0.strNotAvailable;
                                }
                                googleICMPLiveData.setValue(resolvedIp);
                            }
                            break;
                        case -269283359:
                            if (url.equals("time.android.com")) {
                                MutableLiveData<String> timeAndroidServerStatusLiveData = this.this$0.getTimeAndroidServerStatusLiveData();
                                PingData pingData2 = serverStatus.getPingData();
                                if (pingData2 == null || (resolvedIp2 = pingData2.getResolvedIp()) == null) {
                                    resolvedIp2 = this.this$0.strNotAvailable;
                                }
                                timeAndroidServerStatusLiveData.setValue(resolvedIp2);
                            }
                            break;
                        case 1238683855:
                            if (url.equals("xviewplus.com.mx")) {
                                PingData pingData3 = serverStatus.getPingData();
                                if ((pingData3 != null ? pingData3.getResolvedIp() : null) == null) {
                                    this.this$0.getXviewplusServerLiveData().setValue(this.this$0.strNotAvailable);
                                } else {
                                    this.this$0.getXviewplusServerLiveData().setValue("OK");
                                }
                            }
                            break;
                    }
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$disposable$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(this.this$0.getTAG(), "Error: ", th);
            }
        }, new Action() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$disposable$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }));
        Application application4 = getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application4, "getApplication<PortalApplication>()");
        ((PortalApplication) application4).getNetworkStateManager().forceUpdateStatus(true);
        check();
    }

    public final MegacableUserService getUserService() {
        return this.userService;
    }

    public final void setUserService(MegacableUserService megacableUserService) {
        Intrinsics.checkParameterIsNotNull(megacableUserService, "<set-?>");
        this.userService = megacableUserService;
    }

    public final MutableLiveData<String> getConnectivityStatusLiveData() {
        return this.connectivityStatusLiveData;
    }

    public final void setConnectivityStatusLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.connectivityStatusLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getUiopStatusLiveData() {
        return this.uiopStatusLiveData;
    }

    public final void setUiopStatusLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.uiopStatusLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getCompassStatusLiveData() {
        return this.compassStatusLiveData;
    }

    public final void setCompassStatusLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.compassStatusLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getRightVStatusLiveData() {
        return this.rightVStatusLiveData;
    }

    public final void setRightVStatusLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.rightVStatusLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getTimeAndroidServerStatusLiveData() {
        return this.timeAndroidServerStatusLiveData;
    }

    public final void setTimeAndroidServerStatusLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.timeAndroidServerStatusLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getGoogleICMPLiveData() {
        return this.googleICMPLiveData;
    }

    public final void setGoogleICMPLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.googleICMPLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getXviewplusServerLiveData() {
        return this.xviewplusServerLiveData;
    }

    public final void setXviewplusServerLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.xviewplusServerLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getConnectivityCheckServerLiveData() {
        return this.connectivityCheckServerLiveData;
    }

    public final void setConnectivityCheckServerLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.connectivityCheckServerLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getHttpsGoogleServerLiveData() {
        return this.httpsGoogleServerLiveData;
    }

    public final void setHttpsGoogleServerLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.httpsGoogleServerLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getConnectivityCheckDateLiveData() {
        return this.connectivityCheckDateLiveData;
    }

    public final void setConnectivityCheckDateLiveData(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.connectivityCheckDateLiveData = mutableLiveData;
    }

    public final MutableLiveData<String> getHasInternetLiveData() {
        return this.hasInternetLiveData;
    }

    public final MutableLiveData<String> isValidatedConnectionLiveData() {
        return this.isValidatedConnectionLiveData;
    }

    public final CompositeDisposable getCompositeDisposable() {
        return this.compositeDisposable;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void check() {
        Log.e(this.TAG, "Request check...");
        checkCompass();
        checkUIOP();
        checkRightV();
        checkConnectivity();
    }

    private final void checkCompass() {
        Disposable disposableSubscribe = this.userService.checkCompassStatus().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkCompass$disposable$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(String str) {
                this.this$0.getCompassStatusLiveData().postValue("OK");
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkCompass$disposable$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                this.this$0.getCompassStatusLiveData().postValue("Error");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "userService.checkCompass…      }\n                )");
        this.compositeDisposable.add(disposableSubscribe);
    }

    private final void checkRightV() {
        Disposable disposableSubscribe = this.userService.checkRighTVStatus().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkRightV$disposable$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(String str) {
                this.this$0.getRightVStatusLiveData().postValue("OK");
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkRightV$disposable$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                this.this$0.getRightVStatusLiveData().postValue("Error");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "userService.checkRighTVS…      }\n                )");
        this.compositeDisposable.add(disposableSubscribe);
    }

    private final void checkUIOP() {
        Disposable disposableSubscribe = this.userService.checkUIOptimizerStatus("readiness").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkUIOP$disposable$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(String str) {
                this.this$0.getUiopStatusLiveData().postValue("OK");
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkUIOP$disposable$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                this.this$0.getUiopStatusLiveData().postValue("Error");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "userService.checkUIOptim…      }\n                )");
        this.compositeDisposable.add(disposableSubscribe);
    }

    private final void checkConnectivity() {
        this.compositeDisposable.add(Observable.interval(0L, 1L, TimeUnit.SECONDS).flatMapSingle((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkConnectivity$disposable$1
            @Override // io.reactivex.functions.Function
            public final Single<ConnectivityInfoViewModel.NetworkState> apply(Long it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkConnectivity$disposable$1.1
                    @Override // io.reactivex.SingleOnSubscribe
                    public final void subscribe(SingleEmitter<ConnectivityInfoViewModel.NetworkState> observer) {
                        String status;
                        Intrinsics.checkParameterIsNotNull(observer, "observer");
                        Application application = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication();
                        Intrinsics.checkExpressionValueIsNotNull(application, "getApplication<PortalApplication>()");
                        NetworkStateManager networkStateManager = ((PortalApplication) application).getNetworkStateManager();
                        ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_no_connexion);
                        boolean zAreEqual = Intrinsics.areEqual((Object) networkStateManager.internetIsReachable(), (Object) true);
                        NetworkCapabilities currentActiveNetworkCapabilities = networkStateManager.getCurrentActiveNetworkCapabilities();
                        Log.d("Settings", "networkCapabilities = " + currentActiveNetworkCapabilities);
                        if (zAreEqual) {
                            Log.d("Settings", "network = " + networkStateManager.getActiveNetwork());
                            if (currentActiveNetworkCapabilities != null && currentActiveNetworkCapabilities.hasCapability(12)) {
                                int i = ConnectivityInfoViewModel.WhenMappings.$EnumSwitchMapping$0[networkStateManager.getActiveNetworkType().ordinal()];
                                if (i == 1) {
                                    status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_ethernet);
                                } else if (i == 2) {
                                    status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_wifi);
                                } else if (i != 3) {
                                    status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.strNotAvailable;
                                } else {
                                    status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_mobile);
                                }
                            } else {
                                int i2 = ConnectivityInfoViewModel.WhenMappings.$EnumSwitchMapping$1[networkStateManager.getActiveNetworkType().ordinal()];
                                if (i2 == 1) {
                                    status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_ethernet_no_internet);
                                } else if (i2 == 2) {
                                    status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_wifi_no_internet);
                                } else if (i2 == 3) {
                                    status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_mobile_no_internet);
                                } else {
                                    status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_no_internet);
                                }
                            }
                        } else {
                            status = ConnectivityInfoViewModel$checkConnectivity$disposable$1.this.this$0.getApplication().getString(R.string.settings_connectivity_no_internet);
                        }
                        Intrinsics.checkExpressionValueIsNotNull(status, "status");
                        observer.onSuccess(new ConnectivityInfoViewModel.NetworkState(status, zAreEqual, currentActiveNetworkCapabilities));
                    }
                });
            }
        }).distinctUntilChanged().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NetworkState>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkConnectivity$disposable$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(ConnectivityInfoViewModel.NetworkState networkState) {
                this.this$0.getConnectivityStatusLiveData().postValue(networkState.getStatus());
                this.this$0.getHasInternetLiveData().setValue(networkState.getHasInternet() ? this.this$0.getApplication().getString(R.string.dialog_yes) : this.this$0.getApplication().getString(R.string.dialog_no));
                this.this$0.isValidatedConnectionLiveData().setValue(networkState.getIsValidated() ? this.this$0.getApplication().getString(R.string.dialog_yes) : this.this$0.getApplication().getString(R.string.dialog_no));
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkConnectivity$disposable$3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                this.this$0.getConnectivityStatusLiveData().postValue("Error");
                this.this$0.getHasInternetLiveData().setValue(this.this$0.strNotAvailable);
                this.this$0.isValidatedConnectionLiveData().setValue(this.this$0.strNotAvailable);
            }
        }, new Action() { // from class: com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel$checkConnectivity$disposable$4
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }));
    }

    /* JADX INFO: compiled from: ConnectivityInfoViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0012\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/connectivities/ConnectivityInfoViewModel$NetworkState;", "", NotificationCompat.CATEGORY_STATUS, "", "isConnected", "", "capabilities", "Landroid/net/NetworkCapabilities;", "(Ljava/lang/String;ZLandroid/net/NetworkCapabilities;)V", "getCapabilities", "()Landroid/net/NetworkCapabilities;", "hasInternet", "getHasInternet", "()Z", "setHasInternet", "(Z)V", "isVPNConnection", "setVPNConnection", "isValidated", "setValidated", "getStatus", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class NetworkState {
        private final NetworkCapabilities capabilities;
        private boolean hasInternet;
        private final boolean isConnected;
        private boolean isVPNConnection;
        private boolean isValidated;
        private final String status;

        public static /* synthetic */ NetworkState copy$default(NetworkState networkState, String str, boolean z, NetworkCapabilities networkCapabilities, int i, Object obj) {
            if ((i & 1) != 0) {
                str = networkState.status;
            }
            if ((i & 2) != 0) {
                z = networkState.isConnected;
            }
            if ((i & 4) != 0) {
                networkCapabilities = networkState.capabilities;
            }
            return networkState.copy(str, z, networkCapabilities);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsConnected() {
            return this.isConnected;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final NetworkCapabilities getCapabilities() {
            return this.capabilities;
        }

        public final NetworkState copy(String status, boolean isConnected, NetworkCapabilities capabilities) {
            Intrinsics.checkParameterIsNotNull(status, "status");
            return new NetworkState(status, isConnected, capabilities);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NetworkState)) {
                return false;
            }
            NetworkState networkState = (NetworkState) other;
            return Intrinsics.areEqual(this.status, networkState.status) && this.isConnected == networkState.isConnected && Intrinsics.areEqual(this.capabilities, networkState.capabilities);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v4, types: [int] */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4 */
        public int hashCode() {
            String str = this.status;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            boolean z = this.isConnected;
            ?? r2 = z;
            if (z) {
                r2 = 1;
            }
            int i = (iHashCode + r2) * 31;
            NetworkCapabilities networkCapabilities = this.capabilities;
            return i + (networkCapabilities != null ? networkCapabilities.hashCode() : 0);
        }

        public String toString() {
            return "NetworkState(status=" + this.status + ", isConnected=" + this.isConnected + ", capabilities=" + this.capabilities + ")";
        }

        public NetworkState(String status, boolean z, NetworkCapabilities networkCapabilities) {
            Intrinsics.checkParameterIsNotNull(status, "status");
            this.status = status;
            this.isConnected = z;
            this.capabilities = networkCapabilities;
            if (networkCapabilities != null) {
                this.isValidated = networkCapabilities.hasCapability(16);
                this.hasInternet = this.capabilities.hasCapability(12);
                this.isVPNConnection = this.capabilities.hasCapability(15);
            }
        }

        public final NetworkCapabilities getCapabilities() {
            return this.capabilities;
        }

        public final String getStatus() {
            return this.status;
        }

        public final boolean isConnected() {
            return this.isConnected;
        }

        /* JADX INFO: renamed from: isVPNConnection, reason: from getter */
        public final boolean getIsVPNConnection() {
            return this.isVPNConnection;
        }

        public final void setVPNConnection(boolean z) {
            this.isVPNConnection = z;
        }

        /* JADX INFO: renamed from: isValidated, reason: from getter */
        public final boolean getIsValidated() {
            return this.isValidated;
        }

        public final void setValidated(boolean z) {
            this.isValidated = z;
        }

        public final boolean getHasInternet() {
            return this.hasInternet;
        }

        public final void setHasInternet(boolean z) {
            this.hasInternet = z;
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        this.compositeDisposable.dispose();
        super.onCleared();
    }
}
