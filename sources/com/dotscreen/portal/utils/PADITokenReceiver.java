package com.dotscreen.portal.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.util.Log;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.security.GeneralSecurityException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PADITokenReceiver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0017J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/portal/utils/PADITokenReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "padiDevicePorting", "Lcom/dotscreen/portal/utils/PADIDevicePorting;", "getPADIToken", "Lio/reactivex/Single;", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "pdi", "getPDI", "onReceive", "", "sendErrorResponse", "error", "sendTokenResponse", "token", "verifyNinjaSignature", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PADITokenReceiver extends BroadcastReceiver {
    private final String TAG = PADITokenReceiver.class.getSimpleName();
    private PADIDevicePorting padiDevicePorting;

    public static final /* synthetic */ PADIDevicePorting access$getPadiDevicePorting$p(PADITokenReceiver pADITokenReceiver) {
        PADIDevicePorting pADIDevicePorting = pADITokenReceiver.padiDevicePorting;
        if (pADIDevicePorting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("padiDevicePorting");
        }
        return pADIDevicePorting;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        if (PADITokenReceiverKt.DEBUG) {
            Log.i(this.TAG, "onReceive, " + intent);
        }
        if (verifyNinjaSignature(context)) {
            this.padiDevicePorting = new PADIDevicePorting(context);
            getPDI(context).flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.utils.PADITokenReceiver.onReceive.1
                @Override // io.reactivex.functions.Function
                public final Single<String> apply(String it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return PADITokenReceiver.this.getPADIToken(context, intent, it);
                }
            }).subscribe(new Consumer<String>() { // from class: com.dotscreen.portal.utils.PADITokenReceiver.onReceive.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(String token) {
                    PADITokenReceiver pADITokenReceiver = PADITokenReceiver.this;
                    Context context2 = context;
                    Intrinsics.checkExpressionValueIsNotNull(token, "token");
                    pADITokenReceiver.sendTokenResponse(context2, token);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.utils.PADITokenReceiver.onReceive.3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    if (PADITokenReceiverKt.DEBUG) {
                        Log.w(PADITokenReceiver.this.TAG, "API error", th);
                    }
                    String code = th instanceof MegacableUserService.NetflixPDIError ? ((MegacableUserService.NetflixPDIError) th).getCode() : null;
                    PADITokenReceiver pADITokenReceiver = PADITokenReceiver.this;
                    Context context2 = context;
                    if (code == null) {
                        code = "UnknownError";
                    }
                    pADITokenReceiver.sendErrorResponse(context2, code);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendTokenResponse(Context context, String token) {
        if (PADITokenReceiverKt.DEBUG) {
            Log.i(this.TAG, "sendTokenResponse: " + token);
        }
        Intent intent = new Intent("com.netflix.ninja.intent.action.PADI_TOKEN_RESPONSE");
        intent.putExtra("token", token);
        intent.setPackage("com.netflix.ninja");
        if (verifyNinjaSignature(context)) {
            context.sendBroadcast(intent, "com.netflix.ninja.permission.TOKEN");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendErrorResponse(Context context, String error) {
        if (PADITokenReceiverKt.DEBUG) {
            Log.w(this.TAG, "sendErrorResponse: " + error);
        }
        Intent intent = new Intent("com.netflix.ninja.intent.action.PADI_TOKEN_RESPONSE");
        intent.putExtra("error_code", error);
        intent.setPackage("com.netflix.ninja");
        if (verifyNinjaSignature(context)) {
            context.sendBroadcast(intent, "com.netflix.ninja.permission.TOKEN");
        }
    }

    private final Single<String> getPDI(Context context) {
        PADIDevicePorting pADIDevicePorting = this.padiDevicePorting;
        if (pADIDevicePorting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("padiDevicePorting");
        }
        if (pADIDevicePorting.getPartnerDeviceIdentifier() != null) {
            if (PADITokenReceiverKt.DEBUG) {
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("PDI retrieved from FS: ");
                PADIDevicePorting pADIDevicePorting2 = this.padiDevicePorting;
                if (pADIDevicePorting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("padiDevicePorting");
                }
                sb.append(pADIDevicePorting2.getPartnerDeviceIdentifier());
                Log.i(str, sb.toString());
            }
            PADIDevicePorting pADIDevicePorting3 = this.padiDevicePorting;
            if (pADIDevicePorting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("padiDevicePorting");
            }
            Single<String> singleJust = Single.just(pADIDevicePorting3.getPartnerDeviceIdentifier());
            Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(padiDevicePo…artnerDeviceIdentifier())");
            return singleJust;
        }
        Object applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        UserService userService = ((PortalApplicationSingletons) applicationContext).getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        Single<String> singleDoAfterSuccess = ((MegacableUserService) userService).getNetflixPDI().doAfterSuccess(new Consumer<String>() { // from class: com.dotscreen.portal.utils.PADITokenReceiver.getPDI.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(String pdi) throws GeneralSecurityException, IOException {
                if (PADITokenReceiverKt.DEBUG) {
                    Log.i(PADITokenReceiver.this.TAG, "PDI retrieved from API: " + pdi);
                }
                PADIDevicePorting pADIDevicePortingAccess$getPadiDevicePorting$p = PADITokenReceiver.access$getPadiDevicePorting$p(PADITokenReceiver.this);
                Intrinsics.checkExpressionValueIsNotNull(pdi, "pdi");
                pADIDevicePortingAccess$getPadiDevicePorting$p.savePartnerDeviceIdentifier(pdi);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleDoAfterSuccess, "userService.getNetflixPD…tifier(pdi)\n            }");
        return singleDoAfterSuccess;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Single<String> getPADIToken(Context context, Intent intent, String pdi) {
        String esn = intent.getStringExtra("esn");
        String[] intents = intent.getStringArrayExtra("intents");
        if (PADITokenReceiverKt.DEBUG) {
            Log.i(this.TAG, "esn = " + esn);
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("intents = ");
            Intrinsics.checkExpressionValueIsNotNull(intents, "intents");
            sb.append(ArraysKt.toList(intents));
            Log.i(str, sb.toString());
        }
        Object applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        UserService userService = ((PortalApplicationSingletons) applicationContext).getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        Intrinsics.checkExpressionValueIsNotNull(esn, "esn");
        Intrinsics.checkExpressionValueIsNotNull(intents, "intents");
        return ((MegacableUserService) userService).getNetflixPADIToken(pdi, esn, intents);
    }

    private final boolean verifyNinjaSignature(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo("com.netflix.ninja", 134217728);
                Intrinsics.checkExpressionValueIsNotNull(packageInfo, "packageManager.getPackag…GET_SIGNING_CERTIFICATES)");
                if (packageInfo.signingInfo != null) {
                    SigningInfo signingInfo = packageInfo.signingInfo;
                    Intrinsics.checkExpressionValueIsNotNull(signingInfo, "pkgInfo.signingInfo");
                    Signature[] signingCertificateHistory = signingInfo.getSigningCertificateHistory();
                    if (signingCertificateHistory.length == 1 && Intrinsics.areEqual("30820256308201bf02044c0e78d2300d06092a864886f70d01010405003072310b3009060355040613025553311330110603550408130a43616c69666f726e696131123010060355040713094c6f73204761746f7331163014060355040a130d4e6574666c69782c20496e632e310c300a060355040b1303505044311430120603550403130b505044204275696c646572301e170d3130303630383137303733305a170d3337313032343137303733305a3072310b3009060355040613025553311330110603550408130a43616c69666f726e696131123010060355040713094c6f73204761746f7331163014060355040a130d4e6574666c69782c20496e632e310c300a060355040b1303505044311430120603550403130b505044204275696c64657230819f300d06092a864886f70d010101050003818d0030818902818100aa5844ff2ec469558bc9d5c3218216f1e08384693e89cc4cba54219a142c29b665fc8cc07e289b7c63ce75763ced5edfa3d6a8b4127c471d50996dcf19cec1ba00920d0a98f2425fc0184dcb2c415f417643a46827281de57e3a41a8a5f4bcb47b6c34538f2cd2c425250aaa4083c0a80fc4ef8cf97bf346307b3b644d9d254f0203010001300d06092a864886f70d01010405000381810010aed77de8fbb96e1f023208c74718a6424118e19d4457b5541cc18d7c52ffc6c4fcaa5bbfd0274084fe3e129a3cdd653c9d5e30ac097e7f57b6d62e3e03e8e243b34d3ecbdcdec088fe89064953fc5327bfab68347b2d757444a3c3722b3fec07edf7195b24efc6e2a7b3a85ac857a3fc7b43735aabe23cd2355c3b27f3f36a", signingCertificateHistory[0].toCharsString())) {
                        if (PADITokenReceiverKt.DEBUG) {
                            Log.i(this.TAG, "Ninja signature verification successful.");
                        }
                        return true;
                    }
                    if (signingCertificateHistory.length == 1 && Intrinsics.areEqual("308201dd30820146020101300d06092a864886f70d010105050030373116301406035504030c0d416e64726f69642044656275673110300e060355040a0c07416e64726f6964310b3009060355040613025553301e170d3136303832323233353633325a170d3436303831353233353633325a30373116301406035504030c0d416e64726f69642044656275673110300e060355040a0c07416e64726f6964310b300906035504061302555330819f300d06092a864886f70d010101050003818d003081890281810096a50f1e8c402fdb9d56ffe00b7341b0b6d8d527dd5667aff38ea3331970bea5cebd860e26964374a54fed63c8b3e6483ae33158401b8dc5c26aeb8790ff9254523a76e1254bc6c47629d7066eb0fa8b38c3dba6e953bc6cfd2a5646483ac0248bc5014cfcf77f178b1bbe3187db342c6e7eac3710c84c3fb5168a4e3b5385090203010001300d06092a864886f70d0101050500038181002aa2c63b31d0e5e75778699a85607004a28348d6781eb04b52b046f49e84925889fff1e43600e232c3ac79137644c29c7ff4108fbcd7b968e725c8bfe3738d15967093409550cd3fa08c8d9a58c27d8612977007d2dba0c1fbf61cf12f97a873d10805b708aa79b61f04f0434fd5bcb128e7a87c67f73ae7712d37c4289d8e30", signingCertificateHistory[0].toCharsString())) {
                        if (PADITokenReceiverKt.DEBUG) {
                            Log.i(this.TAG, "Ninja(Debug) signature verification successful.");
                        }
                        return true;
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (PADITokenReceiverKt.DEBUG) {
            Log.e(this.TAG, "Signature verification failure");
        }
        return false;
    }
}
