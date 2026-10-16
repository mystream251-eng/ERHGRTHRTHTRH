package com.dotscreen.portal.search;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.apps.tvsearch.operator.IAssistantOperatorService;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.util.Arrays;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AssistantOperatorAuthService extends Service {
    private static final String KATNISS_PACKAGE_NAME = "com.google.android.katniss";
    private static String mAccessToken = "1234";
    private static String mDeviceModelId = "XViewMegacableATV";
    private static final byte[] KATNISS_RELEASE_SIGNATURE_BYTES = extractKey("0\u0082\u0003½0\u0082\u0002¥ \u0003\u0002\u0001\u0002\u0002\t\u0000¯\u0018\u001f3Èf\u0015_0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0005\u0005\u00000u1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\f\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\f\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\f\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\f\u0007Android1\u00110\u000f\u0006\u0003U\u0004\u0003\f\btvsearch0\u001e\u0017\r140715161615Z\u0017\r411130161615Z0u1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\f\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\f\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\f\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\f\u0007Android1\u00110\u000f\u0006\u0003U\u0004\u0003\f\btvsearch0\u0082\u0001\"0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\u000f\u00000\u0082\u0001\n\u0002\u0082\u0001\u0001\u0000¸\u0097þU\u0094Þ\u0090q5ö\u0095\u001c\u009c\f±\u0012ÊèìuPûÖü\u0002Å¾w®É\u0094ÈÔ5\u0006\u001fõ 2«åHyÉuÚi [æ\u0005ß3\u000ek\u0094?\u0001ôªUï\u009c\u001bËï \u0010:RS\u0094ÂdGî©|\u0012\u0010\\nípÐ\u0001\u0019>5è\u0099\u0005ÅÍcÃ{s)§Ð¦ëj-×Ø\u0099D1\u0005ôEFò¯¾«Æ¼_P¢^±b\u009bsv§Ì\u0095\u0015ñ^\u0012U\u007fGú\u0011\u001bm¬\u0089J\u000b\u0086ÕL~Åïs\u008cÀL\u001b\u0084P7j³1\u0087u-\u0001-\u009a\u009c\u000b\f\u0088\u001dJÛ\u0088*\nÏpÐ\u009aN\u0082ó\u0012\u0097g\u001aôm®\u009aåtI\u0006ö¯\u0002¤rK»½lÌÞaô=}ðW\u009aà\u0019;É9á\u00adâ¼\u0000º·¸,ùä\u0015_ãmÞÃániãÔ\u008a¬8!¦9\u008fðÎá®\u0017\u0002\u0003\u0001\u0000\u0001£P0N0\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014öóðP\u0090\u001a\u009aX\u0015\u0090²RÍët!²£âC0\u001f\u0006\u0003U\u001d#\u0004\u00180\u0016\u0080\u0014öóðP\u0090\u001a\u009aX\u0015\u0090²RÍët!²£âC0\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001ÿ0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0005\u0005\u0000\u0003\u0082\u0001\u0001\u0000¬ç¶\"RI\u009fÛ©\t\u0015\u0084\u008boë\u0084\u008e\u009fs\u0085u\u0086ºèZ\u001a¾\u0001ë¯\u000b<Ê_¬%ÙA¦\u0082\u009e`êk,\u0090h4\néiô\u0001hf}\u0015\u0014©\u0095L\u008dk\u0080\u009eÑ\u009f\u001fzz6^\u0001\u009d×º\u001c\u0012\t\u0088\u0000\u0010=:J2\u0006¸À¢K:rÎ²Ä\u001duþØ&Zë\u00000Ñ\tÚóßi2-E\u0019Ú¢Ö]¶!2è\u009d\u0094\u008aÉ7z\u0013§x,uÕ\u008f\u0017ú)\\GW%\u0012Éj½Ý®\u0080e\u0084\u001cGÚí\u0012«\u0093\nßs\u0010eñ\\yáVÛÀB 11\u0099yµ¥ð/×°´\u00adÿw\u0004\u0019¯=+Yç\u008eÒ ,\u0095GöÓG|\u0089=\u0001Ø¡uÃD\u0098\u008d\u009c\u008cvÙÇyÈÜäöà7\u0091//À\"\u008cÔ°gj§.\u0005\u001be\u000e\u000f7ì\u0087ú©P5[J#WÂ\u009b");
    private static final byte[] KATNISS_DEBUG_SIGNATURE_BYTES = extractKey("0\u0082\u0003½0\u0082\u0002¥ \u0003\u0002\u0001\u0002\u0002\t\u0000\u0090\u0084ô\u009a\u009cn-Ó0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0005\u0005\u00000u1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\f\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\f\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\f\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\f\u0007Android1\u00110\u000f\u0006\u0003U\u0004\u0003\f\btvsearch0\u001e\u0017\r140715161554Z\u0017\r411130161554Z0u1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\f\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\f\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\f\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\f\u0007Android1\u00110\u000f\u0006\u0003U\u0004\u0003\f\btvsearch0\u0082\u0001\"0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\u000f\u00000\u0082\u0001\n\u0002\u0082\u0001\u0001\u0000ãi\u0017Ù¸q\u001bTãFt\n\u007fÝp,¢©§\u009b\u001f~\u0004\u0002ÕPÏõ\u001f\u0000UyH\tîÿ\u0091«?Cñ\u00941u\u0090\u0083]âóænl\u0005\u0003gòã^¬kÞÎ\u008f1\u0089\u0081ûoÈëÓÌ\u001b\u0004%Ù\u0018Ô\u0086ã8\u0097Là]´^¹µÐÕ¬lv#&\u001e½«¿\u009cªv\u0015\u009c¸ú\u009e\u007f\u009bêª.Ov\u0018>Ç\u0099qd'ÆÁ\u0081ÏÚ{ù}i´_\u001cõÊÇÏ_!Ð÷cÉM{½\u008b^µóÜ\u0085_\u0004æ¼>Ü}òª±é,\u0084É}g$\u0010¯ü\u0088\u001e8dÿ\u0011,å¾j\u001e±2.»\u0097}¿â\t\u0019ÓÚh\rSû\u0091)U¾%0Nà~\t\u0089\u009dNþ\u0083À\u0091ÀþkVP¸â\u009b>%#Ñ;]ùKåN\u0012\n¯\u009cQ\u0084¼\u009fýX)¨pp£uº{ðÕ§\u0002\u0003\u0001\u0000\u0001£P0N0\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014\u001dÉqßóDh[\u009a\u008d0î\u0090EÒeðÀ<Ö0\u001f\u0006\u0003U\u001d#\u0004\u00180\u0016\u0080\u0014\u001dÉqßóDh[\u009a\u008d0î\u0090EÒeðÀ<Ö0\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001ÿ0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0005\u0005\u0000\u0003\u0082\u0001\u0001\u0000\u009e\u0001áÿ\u0003õ\u009b/\u009cT~\u0014ÆcÍ7\u0012W¬\u0006\u001c©\u000bö=4ù\u0098çÞv\u0007§{6²[b\u0090Ãð³\u001e©©\u009aÕ\u0089\u000b:[·\u0014)\u0081Cnê.ìWÕ\u0095)DuÐp.#\u0002|>\b\u0010Òi¬ü\u0012ò\u009c\u0006\u0097\u0011¤äR~ñ\u0007\u0084&»0É\u0015\u008aë×¤xù\u0018çR¶uZ0Qg°±!ã\u0092º\u000b%ZC\u00ad\u0099b´Ìw\u0000¦B\u0018:\u008c\f\u0007m\u0097÷\u0098\u008a\u0098\u000eµ\u008b\u0006\u0015Ë$Ð\u0093\u0001\n\u0080£\nóSÎ\bêËC¹úçzG¹¿ÁÃ/DÉ\u0005\u0093O#ñzâ\u0089\u008a'JÏ\u0016ég¨¶þu®&\\~\u001cê\u0019\u000fu\u0019òL\u0000ß^×\u0004C\u0002gãs>¬x·\u0097óñÚ£$lJ\u0018Ò[ÇjJUR\u0015TåóKµ\u0086tþùÜ -mkµ\u0010eå;");
    private static final Signature KATNISS_DEBUG_SIGNATURE = new Signature(KATNISS_DEBUG_SIGNATURE_BYTES);
    private static final Signature KATNISS_RELEASE_SIGNATURE = new Signature(KATNISS_RELEASE_SIGNATURE_BYTES);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return getStub();
    }

    public IBinder getStub() {
        return new IAssistantOperatorService.Stub() { // from class: com.dotscreen.portal.search.AssistantOperatorAuthService.1
            @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
            public String getAccessToken() throws RemoteException {
                if (!AssistantOperatorAuthService.this.isValidCaller()) {
                    throw new RemoteException();
                }
                Log.e("SLSLSL", "getAccessToken() = " + AssistantOperatorAuthService.mAccessToken);
                return AssistantOperatorAuthService.mAccessToken;
            }

            @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
            public String getDeviceModelId() throws RemoteException {
                if (!AssistantOperatorAuthService.this.isValidCaller()) {
                    throw new RemoteException();
                }
                Log.e("SLSLSL", "getDeviceModelId() = " + AssistantOperatorAuthService.mDeviceModelId);
                return AssistantOperatorAuthService.mDeviceModelId;
            }

            @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
            public String getCustomContext() throws RemoteException {
                Log.e("SLSLSL", "getCustomContext()");
                String strReplace = "";
                for (HttpCookie httpCookie : PortalApplication.INSTANCE.getSingleton(AssistantOperatorAuthService.this).getRequestManager().getCookieManager().getCookieStore().getCookies()) {
                    if (httpCookie.getName().equals("compass-identity")) {
                        httpCookie.getValue().replace("compass-identity=", "");
                    } else if (httpCookie.getName().contains("identity")) {
                        strReplace = httpCookie.getValue().replace("identity=", "");
                    }
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("AccessType", "");
                    jSONObject2.put("AppVersion", "");
                    jSONObject2.put("DeviceId", "");
                    jSONObject2.put("DeviceModel", Build.MODEL);
                    jSONObject2.put("STBCustomName", "");
                    jSONObject2.put("SVODList", "");
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("BouquetId", CommonUtils.getCurrentBouquetName());
                    jSONObject3.put("DTTTuned", true);
                    jSONObject3.put("imageBaseURL", "");
                    jSONObject3.put("Language", "SP");
                    jSONObject3.put("ParentalRating", "");
                    jSONObject3.put("Recommendations", "");
                    jSONObject3.put("TimeZone", "Peninsula");
                    jSONObject3.put("VoicePortal", true);
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("COMPASS_COOKIE", "");
                    jSONObject4.put("CustomProfile", "");
                    jSONObject4.put("ProfileType", "");
                    jSONObject4.put("RTV_COOKIE", strReplace);
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("OSDContext", "");
                    jSONObject5.put("SUBCONTEXT", new JSONObject());
                    jSONObject4.put("STBStatus", jSONObject5);
                    jSONObject.put("CustomerInfo", jSONObject2);
                    jSONObject.put("CustomerSettings", jSONObject3);
                    jSONObject.put("SessionInfo", jSONObject4);
                    jSONObject.put("uniqueId", "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jSONObject.toString();
            }
        };
    }

    public String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidCaller() {
        boolean z = false;
        if (!getPackageManager().getNameForUid(Binder.getCallingUid()).equals(KATNISS_PACKAGE_NAME)) {
            return false;
        }
        try {
            Signature[] signatureArr = getPackageManager().getPackageInfo(getPackageManager().getNameForUid(Binder.getCallingUid()), 64).signatures;
            int length = signatureArr.length;
            int i = 0;
            boolean z2 = false;
            while (i < length) {
                try {
                    byte[] byteArray = signatureArr[i].toByteArray();
                    if (!Arrays.equals(byteArray, KATNISS_RELEASE_SIGNATURE_BYTES) && !Arrays.equals(byteArray, KATNISS_DEBUG_SIGNATURE_BYTES)) {
                        return false;
                    }
                    i++;
                    z2 = true;
                } catch (PackageManager.NameNotFoundException e) {
                    e = e;
                    z = z2;
                    e.printStackTrace();
                    return z;
                }
            }
            return z2;
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
        }
    }

    private static byte[] extractKey(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
