package com.dotscreen.tv.common.utils;

import android.content.Context;
import android.util.Log;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.Profile;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.bokit.Terminal;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.analytics.dca.VoDcaController;
import com.viaccessorca.analytics.dca.VoDcaControllerListener;
import com.viaccessorca.analytics.dca.VoDcaInitParameters;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.rxkotlin.DisposableKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DCAInitComponent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 %2\u00020\u0001:\u0001%B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J6\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\b\u0010\u001a\u001a\u00020\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0016J\b\u0010\u001c\u001a\u00020\u0015H\u0016J\u0014\u0010\u001d\u001a\u00020\u00152\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\u0002J\u0014\u0010\u001f\u001a\u00020\u00152\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\u0002J\u001e\u0010 \u001a\u00020\u00152\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/dotscreen/tv/common/utils/DCAInitComponent;", "Lcom/viaccessorca/analytics/dca/VoDcaControllerListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "accountId", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getContext", "()Landroid/content/Context;", "isDCAinitialize", "", "liveUserService", "Lcom/dotscreen/tv/common/bokit/MegacableLiveTVService;", "megaUserService", "Lcom/dotscreen/tv/common/bokit/MegacableUserService;", ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, "initDCA", "", "applicationName", "applicationVersion", ImagesContract.URL, "onConfigurationReloaded", "onConfigurationReloadedFailed", "onInitCompleted", "onInitFailed", "setAccountId", "currentId", "setProfileId", "setUser", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/bokit/model/User;", Scopes.PROFILE, "Lcom/dotscreen/tv/common/bokit/Profile;", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DCAInitComponent implements VoDcaControllerListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static VoDcaInitParameters params = new VoDcaInitParameters();
    private final String TAG;
    private String accountId;
    private final CompositeDisposable compositeDisposable;
    private final Context context;
    private boolean isDCAinitialize;
    private MegacableLiveTVService liveUserService;
    private MegacableUserService megaUserService;
    private String profileId;

    public DCAInitComponent(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = DCAInitComponent.class.getSimpleName();
        BOService bOService = CommonUtils.getBOService(this.context);
        UserService userService = bOService != null ? bOService.getUserService() : null;
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        this.megaUserService = (MegacableUserService) userService;
        BOService bOService2 = CommonUtils.getBOService(this.context);
        LiveTVService liveTVService = bOService2 != null ? bOService2.getLiveTVService() : null;
        if (liveTVService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
        }
        this.liveUserService = (MegacableLiveTVService) liveTVService;
        this.compositeDisposable = new CompositeDisposable();
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: DCAInitComponent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/tv/common/utils/DCAInitComponent$Companion;", "", "()V", "params", "Lcom/viaccessorca/analytics/dca/VoDcaInitParameters;", "getParams", "()Lcom/viaccessorca/analytics/dca/VoDcaInitParameters;", "setParams", "(Lcom/viaccessorca/analytics/dca/VoDcaInitParameters;)V", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VoDcaInitParameters getParams() {
            return DCAInitComponent.params;
        }

        public final void setParams(VoDcaInitParameters voDcaInitParameters) {
            Intrinsics.checkParameterIsNotNull(voDcaInitParameters, "<set-?>");
            DCAInitComponent.params = voDcaInitParameters;
        }
    }

    public static /* synthetic */ void initDCA$default(DCAInitComponent dCAInitComponent, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 8) != 0) {
            str4 = (String) null;
        }
        String str6 = str4;
        if ((i & 16) != 0) {
            str5 = (String) null;
        }
        dCAInitComponent.initDCA(str, str2, str3, str6, str5);
    }

    public final void initDCA(final String applicationName, final String applicationVersion, final String url, String accountId, String profileId) {
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        Intrinsics.checkParameterIsNotNull(applicationVersion, "applicationVersion");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Log.v(this.TAG, "initDCA Requested", new Exception());
        this.accountId = accountId;
        this.profileId = profileId;
        Disposable disposableSubscribe = this.liveUserService.getModelExternalId().subscribe(new Consumer<String>() { // from class: com.dotscreen.tv.common.utils.DCAInitComponent.initDCA.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(String str) {
                Log.v(DCAInitComponent.this.TAG, "initDCA Started for " + str + " device");
                DCAInitComponent.INSTANCE.getParams().mListener = DCAInitComponent.this;
                DCAInitComponent.INSTANCE.getParams().mApplicationName = applicationName;
                DCAInitComponent.INSTANCE.getParams().mConfigurationUrl = url;
                DCAInitComponent.INSTANCE.getParams().mDeviceID = DCAInitComponent.this.megaUserService.getSerialNumber();
                if (Intrinsics.areEqual(DCAInitComponent.this.getContext().getPackageName(), "com.dotscreen.megacable.mobile")) {
                    VoDcaInitParameters params2 = DCAInitComponent.INSTANCE.getParams();
                    String username = new LoginHelper(DCAInitComponent.this.getContext()).getUsername();
                    params2.mDeviceID = username != null ? Terminal.INSTANCE.serialNumber(username, DCAInitComponent.this.getContext()) : null;
                }
                DCAInitComponent.INSTANCE.getParams().mApplicationVersion = applicationVersion;
                VoDcaInitParameters params3 = DCAInitComponent.INSTANCE.getParams();
                if (str != null) {
                    int iHashCode = str.hashCode();
                    if (iHashCode != 78607) {
                        if (iHashCode == 803262031 && str.equals("Android")) {
                            str = "OTT";
                        }
                    } else if (str.equals("OTT")) {
                        str = "OTT STB";
                    }
                }
                params3.mDeviceCategory = str;
                VoDcaController.initialize(DCAInitComponent.INSTANCE.getParams());
                DCAInitComponent.this.isDCAinitialize = true;
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.utils.DCAInitComponent.initDCA.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e("DCAInitComponent", "Init failed");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "liveUserService.modelExt…\"Init failed\")\n        })");
        DisposableKt.addTo(disposableSubscribe, this.compositeDisposable);
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaControllerListener
    public void onInitCompleted() {
        setUser$default(this, null, null, 3, null);
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaControllerListener
    public void onConfigurationReloaded() {
        setUser$default(this, null, null, 3, null);
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaControllerListener
    public void onInitFailed() {
        this.isDCAinitialize = false;
        Log.v(this.TAG, "DCA lib failed");
    }

    @Override // com.viaccessorca.analytics.dca.VoDcaControllerListener
    public void onConfigurationReloadedFailed() {
        this.isDCAinitialize = false;
        Log.v(this.TAG, "DCA reload failed");
    }

    static /* synthetic */ void setAccountId$default(DCAInitComponent dCAInitComponent, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        dCAInitComponent.setAccountId(str);
    }

    private final void setAccountId(String currentId) {
        if (currentId != null) {
            VoDcaController.getDcaController().setAccountID(currentId);
        } else {
            if (this.accountId != null) {
                VoDcaController.getDcaController().setAccountID(this.accountId);
                return;
            }
            Disposable disposableSubscribe = this.megaUserService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.utils.DCAInitComponent.setAccountId.1
                @Override // io.reactivex.functions.Function
                public final String apply(User it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getId();
                }
            }).switchIfEmpty(this.megaUserService.getAccountId()).subscribe(new Consumer<String>() { // from class: com.dotscreen.tv.common.utils.DCAInitComponent.setAccountId.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(String str) {
                    DCAInitComponent.this.accountId = str;
                    VoDcaController.getDcaController().setAccountID(DCAInitComponent.this.accountId);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.utils.DCAInitComponent.setAccountId.3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.e(DCAInitComponent.this.TAG, "Error during retrieve of current account id", th);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "megaUserService\n        …                       })");
            DisposableKt.addTo(disposableSubscribe, this.compositeDisposable);
        }
    }

    static /* synthetic */ void setProfileId$default(DCAInitComponent dCAInitComponent, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        dCAInitComponent.setProfileId(str);
    }

    private final void setProfileId(String currentId) {
        Log.v(this.TAG, "setProfileId(" + currentId + ')');
        try {
            if (currentId != null) {
                VoDcaController.getDcaController().setProfileID(currentId);
            } else if (this.profileId != null) {
                VoDcaController.getDcaController().setProfileID(this.profileId);
            } else {
                Disposable disposableSubscribe = this.megaUserService.getGetCurrentProfile().subscribe(new Consumer<Profile>() { // from class: com.dotscreen.tv.common.utils.DCAInitComponent.setProfileId.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Profile profile) {
                        Intrinsics.checkParameterIsNotNull(profile, "profile");
                        DCAInitComponent.this.profileId = profile.getId();
                        VoDcaController.getDcaController().setProfileID(DCAInitComponent.this.profileId);
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.utils.DCAInitComponent.setProfileId.2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        Log.e(DCAInitComponent.this.TAG, "Init Profile Id Failed", th);
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "megaUserService\n        …                       })");
                DisposableKt.addTo(disposableSubscribe, this.compositeDisposable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void setUser$default(DCAInitComponent dCAInitComponent, User user, Profile profile, int i, Object obj) {
        if ((i & 1) != 0) {
            user = (User) null;
        }
        if ((i & 2) != 0) {
            profile = (Profile) null;
        }
        dCAInitComponent.setUser(user, profile);
    }

    public final void setUser(User user, Profile profile) {
        String id = null;
        setAccountId(user != null ? user.getId() : null);
        if (user instanceof RightVUser) {
            id = ((RightVUser) user).getCompassId();
        } else if (profile != null) {
            id = profile.getId();
        }
        setProfileId(id);
    }
}
