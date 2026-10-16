package com.viaccessorca.drm.impl;

import android.content.Context;
import android.net.Uri;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.drm.VOCommonDrmAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOSoapException;
import com.viaccessorca.exceptions.VOStatusCode;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DrmAgent implements VOCommonDrmAgent {
    private static k q = null;
    private static boolean r = false;
    protected static String s = null;
    private static int t = 2;
    private static int u = 1000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected Context f10a;
    protected VOPlayer b;
    protected EDrmType c;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i = null;
    private String j = null;
    private String[] k = null;
    private String[] l = null;
    private byte[] m = null;
    protected int n = -1;
    protected boolean o = false;
    protected String p = null;

    public enum EDrmFrameworkConfigurationKey {
        DRM_KEY_NETWORK_TIMEOUT,
        DRM_KEY_VODRM_HEAD_END_URL,
        DRM_KEY_HOUSE_HOLD
    }

    public enum EDrmType {
        DRM_TYPE_UNKNOWN("UNKNOWN"),
        DRM_TYPE_PLAYREADY("PLAYREADY"),
        DRM_TYPE_WIDEVINE("WIDEVINE"),
        DRM_TYPE_VODRM("VODRM"),
        DRM_TYPE_VERIMATRIX("VERIMATRIX");


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f11a;

        EDrmType(String str) {
            this.f11a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.f11a;
        }
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f12a;
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[EDrmType.values().length];
            c = iArr;
            try {
                iArr[EDrmType.DRM_TYPE_VODRM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[EDrmType.DRM_TYPE_WIDEVINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[EDrmType.DRM_TYPE_PLAYREADY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[EDrmType.DRM_TYPE_VERIMATRIX.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[EDrmFrameworkConfigurationKey.values().length];
            b = iArr2;
            try {
                iArr2[EDrmFrameworkConfigurationKey.DRM_KEY_NETWORK_TIMEOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[EDrmFrameworkConfigurationKey.DRM_KEY_VODRM_HEAD_END_URL.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[EDrmFrameworkConfigurationKey.DRM_KEY_HOUSE_HOLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[VOStatusCode.values().length];
            f12a = iArr3;
            try {
                iArr3[VOStatusCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f12a[VOStatusCode.ERROR_DRM_PROVISIONING_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f12a[VOStatusCode.ERROR_DRM_CONTENT_NOT_RECOGNIZED.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f12a[VOStatusCode.ERROR_DRM_NO_RIGHTS.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f12a[VOStatusCode.ERROR_SECURITY_OS_TAMPERED.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    static {
        if (VOUtils.SDK_INT > 17) {
            k kVarA = k.a();
            q = kVarA;
            int i = t;
            int i2 = u;
            kVarA.a(i, i2, i2);
        }
    }

    protected DrmAgent(Context context, EDrmType eDrmType, VOPlayer vOPlayer) {
        this.f10a = null;
        this.b = null;
        this.c = EDrmType.DRM_TYPE_UNKNOWN;
        this.f10a = context;
        this.c = eDrmType;
        this.b = vOPlayer;
    }

    private static void a(Context context) throws VOException {
        initialize(context);
        try {
            DrmHelperUtils.checkPermissions();
        } catch (VOException e) {
            if (a(e.getStatus())) {
                throw e;
            }
        }
    }

    private static final boolean a(EDrmType eDrmType, VOPlayer.EDrmType eDrmType2) {
        int i = a.c[eDrmType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4 && eDrmType2 == VOPlayer.EDrmType.VERIMATRIX) {
                        return true;
                    }
                } else if (eDrmType2 == VOPlayer.EDrmType.PLAYREADY) {
                    return true;
                }
            } else if (eDrmType2 == VOPlayer.EDrmType.WIDEVINE) {
                return true;
            }
        } else if (eDrmType2 == VOPlayer.EDrmType.VODRM) {
            return true;
        }
        return false;
    }

    private static boolean a(VOStatusCode vOStatusCode) {
        if (vOStatusCode != VOStatusCode.ERROR_SECURITY_MISSING_PERMISSION) {
            return true;
        }
        Map.Entry<VOStatusCode, String> entryGetDrmVersion = JniDrmManager.GetDrmVersion();
        if (entryGetDrmVersion == null || entryGetDrmVersion.getValue() == null) {
            return false;
        }
        return entryGetDrmVersion.getValue().contains("_PR_") || entryGetDrmVersion.getValue().contains("_WVPR_") || entryGetDrmVersion.getValue().contains("_PRWV_");
    }

    private synchronized void b(int i) throws VOException {
        String strGenerateCookiesHeader = generateCookiesHeader(this.k);
        String strGenerateCustomHeaders = generateCustomHeaders(this.l);
        JniDrmUserMessage jniDrmUserMessage = new JniDrmUserMessage();
        VOStatusCode vOStatusCodeAcquireRightsByHandler = JniDrmManager.AcquireRightsByHandler(i, this.h, this.i, this.j, strGenerateCookiesHeader, strGenerateCustomHeaders, jniDrmUserMessage);
        if (vOStatusCodeAcquireRightsByHandler != VOStatusCode.SUCCESS) {
            if (jniDrmUserMessage.isSet()) {
                throw new VOSoapException(vOStatusCodeAcquireRightsByHandler, jniDrmUserMessage.getSoapMessage(), jniDrmUserMessage.getCustomData(), jniDrmUserMessage.getRedirectUrl());
            }
            DrmHelperUtils.throwVOException(vOStatusCodeAcquireRightsByHandler);
        }
    }

    public static String buildSecurityLevelProperty(int i) {
        if (5 <= i) {
            return "L1";
        }
        return 1 == i ? "L3" : "N/A";
    }

    private synchronized void c(int i) throws VOException {
        VOStatusCode vOStatusCodeDeleteRightsByHandler = JniDrmManager.DeleteRightsByHandler(i);
        if (vOStatusCodeDeleteRightsByHandler != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(vOStatusCodeDeleteRightsByHandler);
        }
    }

    public static void configureFramework(Context context, EDrmFrameworkConfigurationKey eDrmFrameworkConfigurationKey, Object obj) throws VOException {
        VOStatusCode vOStatusCodeSetConfigurationWithString;
        a(context);
        int i = a.b[eDrmFrameworkConfigurationKey.ordinal()];
        if (i == 1) {
            if (!(obj instanceof Integer)) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            int iIntValue = ((Integer) obj).intValue();
            if (iIntValue < 5) {
                iIntValue = 5;
            }
            if (iIntValue > 300) {
                iIntValue = 300;
            }
            DrmHelperUtils.setHttpTimeoutInSec(iIntValue);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            if (!(obj instanceof String)) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            vOStatusCodeSetConfigurationWithString = JniDrmManager.SetConfigurationWithString(eDrmFrameworkConfigurationKey, (String) obj);
            if (vOStatusCodeSetConfigurationWithString == VOStatusCode.SUCCESS) {
                return;
            }
        } else {
            if (!(obj instanceof String)) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            vOStatusCodeSetConfigurationWithString = JniDrmManager.SetConfigurationWithString(eDrmFrameworkConfigurationKey, (String) obj);
            if (vOStatusCodeSetConfigurationWithString == VOStatusCode.SUCCESS) {
                return;
            }
        }
        DrmHelperUtils.throwVOException(vOStatusCodeSetConfigurationWithString);
    }

    public static int convertSecurityLevelProperty(String str) {
        int i;
        try {
            i = Integer.parseInt(str.split("L")[1]);
        } catch (Exception unused) {
            i = 0;
        }
        if (i != 1) {
            return i != 3 ? 0 : 1;
        }
        return 5;
    }

    private VOCommonDrmAgent.VODrmRightsInfo[] d(int i) throws VOException {
        VOCommonDrmAgent.VODrmRightsInfo[] vODrmRightsInfoArr;
        VOStatusCode vOStatusCodePrepareRightsObjectIterationByHandler = JniDrmManager.PrepareRightsObjectIterationByHandler(i);
        if (vOStatusCodePrepareRightsObjectIterationByHandler != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(vOStatusCodePrepareRightsObjectIterationByHandler);
        }
        Map.Entry<VOStatusCode, Integer> entryGetRightsObjectCountByHandler = JniDrmManager.GetRightsObjectCountByHandler(i);
        if (entryGetRightsObjectCountByHandler == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGetRightsObjectCountByHandler.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        int iIntValue = entryGetRightsObjectCountByHandler.getValue().intValue();
        if (iIntValue > 0) {
            vODrmRightsInfoArr = new VOCommonDrmAgent.VODrmRightsInfo[iIntValue];
            for (int i2 = 0; i2 < iIntValue; i2++) {
                Map.Entry<VOStatusCode, VOCommonDrmAgent.VODrmRightsInfo> entryGetRightsObjectAtIndexByHandler = JniDrmManager.GetRightsObjectAtIndexByHandler(this.c, i, i2);
                if (entryGetRightsObjectAtIndexByHandler == null || entryGetRightsObjectAtIndexByHandler.getKey() != VOStatusCode.SUCCESS) {
                    DrmHelperUtils.throwVOException(entryGetRightsObjectAtIndexByHandler.getKey());
                }
                vODrmRightsInfoArr[i2] = entryGetRightsObjectAtIndexByHandler.getValue();
            }
        } else {
            vODrmRightsInfoArr = null;
        }
        JniDrmManager.TerminateRightsObjectIterationByHandler(i);
        return vODrmRightsInfoArr;
    }

    private synchronized boolean e(int i) throws VOException {
        VOStatusCode vOStatusCodeVerifyRightsByHandler;
        vOStatusCodeVerifyRightsByHandler = JniDrmManager.VerifyRightsByHandler(i);
        int i2 = a.f12a[vOStatusCodeVerifyRightsByHandler.ordinal()];
        if (i2 != 1 && i2 != 4) {
            DrmHelperUtils.throwVOException(vOStatusCodeVerifyRightsByHandler);
        }
        return vOStatusCodeVerifyRightsByHandler == VOStatusCode.SUCCESS;
    }

    public static String getDeviceID(Context context, VOPlayer.EUniqueIdentifierType eUniqueIdentifierType) throws VOException {
        a(context);
        Map.Entry<VOStatusCode, String> entryGetDeviceUniqueID = JniDrmManager.GetDeviceUniqueID(eUniqueIdentifierType);
        if (entryGetDeviceUniqueID == null) {
            k kVar = q;
            int i = t;
            int i2 = u;
            kVar.a(i, i2, i2);
            entryGetDeviceUniqueID = JniDrmManager.GetDeviceUniqueID(eUniqueIdentifierType);
            if (entryGetDeviceUniqueID == null) {
                throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
            }
        }
        VOStatusCode key = entryGetDeviceUniqueID.getKey();
        if (a.f12a[key.ordinal()] != 1) {
            DrmHelperUtils.throwVOException(key);
        }
        return entryGetDeviceUniqueID.getValue();
    }

    public static String getDrmVersion(Context context) throws VOException {
        a(context);
        Map.Entry<VOStatusCode, String> entryGetDrmVersion = JniDrmManager.GetDrmVersion();
        if (entryGetDrmVersion == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGetDrmVersion.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return entryGetDrmVersion.getValue();
    }

    public static void initialize(Context context) throws VOException {
        synchronized (DrmAgent.class) {
            if (context == null) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            if (r) {
                return;
            }
            try {
                try {
                    Context applicationContext = context.getApplicationContext();
                    try {
                        DrmHelperUtils.moveAssetFileInApplicationFolder(applicationContext, "quickplayer.lic", null);
                    } catch (VOException unused) {
                    }
                    DrmHelperUtils.setContext(applicationContext);
                    JniDrmManager.Init(DrmHelperUtils.useAndroidXDependencies());
                    try {
                        DrmHelperUtils.checkPermissions(applicationContext, true);
                    } catch (VOException e) {
                        if (a(e.getStatus())) {
                            throw e;
                        }
                    }
                    DrmBroadcastReceiver.m2yugdva7tpoe4A2();
                    s = JniDrmManager.GetOPID();
                    r = true;
                } catch (Exception unused2) {
                    throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
                }
            } catch (VOException e2) {
                throw e2;
            }
        }
    }

    public static boolean isOsTampered(Context context) throws VOException {
        a(context);
        VOStatusCode vOStatusCodeIsOsTampered = JniDrmManager.isOsTampered();
        int i = a.f12a[vOStatusCodeIsOsTampered.ordinal()];
        if (i != 1 && i != 5) {
            DrmHelperUtils.throwVOException(vOStatusCodeIsOsTampered);
        }
        return vOStatusCodeIsOsTampered == VOStatusCode.ERROR_SECURITY_OS_TAMPERED;
    }

    public static final boolean isSupported(EDrmType eDrmType, Context context) {
        try {
            ArrayList<Map.Entry<VOPlayer.EDrmType, String>> dRMAgentCapabilities = new VOPlayer(context).getDRMAgentCapabilities(context);
            for (int i = 0; i < dRMAgentCapabilities.size(); i++) {
                Map.Entry<VOPlayer.EDrmType, String> entry = dRMAgentCapabilities.get(i);
                if (a(eDrmType, entry.getKey()) && entry.getValue() != null && "None" != entry.getValue() && VOPlaybackSessionReport.UNKNOWN_SVALUE != entry.getValue()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean useAndroidXDependency() {
        return DrmHelperUtils.useAndroidXDependencies();
    }

    protected synchronized int a(String str) throws VOException {
        Map.Entry<VOStatusCode, Integer> entryOpenDrmContentByPath;
        c();
        if (str == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
        }
        entryOpenDrmContentByPath = JniDrmManager.OpenDrmContentByPath(this.c, DrmHelperUtils.getLocalPath(this, str));
        if (entryOpenDrmContentByPath == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryOpenDrmContentByPath.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return entryOpenDrmContentByPath.getValue().intValue();
    }

    protected synchronized int a(byte[] bArr) throws VOException {
        Map.Entry<VOStatusCode, Integer> entryOpenDrmContentByData;
        c();
        if (bArr == null || bArr.length < 1) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
        }
        entryOpenDrmContentByData = JniDrmManager.OpenDrmContentByData(this.c, bArr);
        if (entryOpenDrmContentByData == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryOpenDrmContentByData.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return entryOpenDrmContentByData.getValue().intValue();
    }

    protected synchronized void a() {
        a(this.n);
        this.n = -1;
    }

    protected synchronized void a(int i) {
        if (i != -1) {
            JniDrmManager.DisposeContentHandler(i);
        }
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void acquireRights() throws VOException {
        if (this.n == -1) {
            b();
        }
        b(this.n);
    }

    public synchronized void acquireRights(String str) throws VOException {
        int iA;
        try {
            try {
                iA = a(str);
                try {
                    b(iA);
                    a(iA);
                } catch (Throwable th) {
                    th = th;
                    a(iA);
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            iA = -1;
        }
    }

    public synchronized void acquireRights(byte[] bArr) throws VOException {
        int iA;
        try {
            try {
                iA = a(bArr);
                try {
                    b(iA);
                    a(iA);
                } catch (Throwable th) {
                    th = th;
                    a(iA);
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            iA = -1;
        }
    }

    protected synchronized void b() throws VOException {
        int iA;
        a();
        byte[] bArr = this.m;
        if (bArr != null) {
            iA = a(bArr);
        } else {
            VOPlayer vOPlayer = this.b;
            if (vOPlayer == null) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            iA = a(vOPlayer.getStreamUri());
        }
        this.n = iA;
    }

    protected void c() throws VOException {
        a(this.f10a);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void deletePersonalization() throws VOException {
        c();
        a();
        VOStatusCode vOStatusCodeDeletePersonalization = JniDrmManager.DeletePersonalization(this.c);
        if (vOStatusCodeDeletePersonalization != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(vOStatusCodeDeletePersonalization);
        }
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void deleteRights() throws VOException {
        if (this.n == -1) {
            b();
        }
        try {
            c(this.n);
            a();
        } catch (Throwable th) {
            a();
            throw th;
        }
    }

    public synchronized void deleteRights(String str) throws VOException {
        int iA;
        try {
            try {
                iA = a(str);
                try {
                    c(iA);
                    a(iA);
                } catch (Throwable th) {
                    th = th;
                    a(iA);
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            iA = -1;
        }
    }

    public synchronized void deleteRights(byte[] bArr) throws VOException {
        int iA;
        try {
            try {
                iA = a(bArr);
                try {
                    c(iA);
                    a(iA);
                } catch (Throwable th) {
                    th = th;
                    a(iA);
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            iA = -1;
        }
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void deleteStore() throws VOException {
        c();
        a();
        VOStatusCode vOStatusCodeDeleteDrmAssets = JniDrmManager.DeleteDrmAssets(this.c);
        if (vOStatusCodeDeleteDrmAssets != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(vOStatusCodeDeleteDrmAssets);
        }
    }

    protected void finalize() {
        release();
    }

    public synchronized String generateCookiesHeader(String[] strArr) {
        if (strArr != null) {
            if (strArr.length >= 1) {
                String strConcat = "";
                for (int i = 0; i < strArr.length; i++) {
                    strArr[i] = strArr[i] == null ? "" : strArr[i];
                    strConcat = strConcat.concat(strArr[i] + "; ");
                }
                return strConcat;
            }
        }
        return null;
    }

    public synchronized String generateCustomHeaders(String[] strArr) {
        if (strArr != null) {
            if (strArr.length >= 1) {
                String strConcat = "";
                for (int i = 0; i < strArr.length; i++) {
                    strArr[i] = strArr[i] == null ? "" : strArr[i];
                    strConcat = strConcat.concat(strArr[i] + "\r\n");
                }
                return strConcat;
            }
        }
        return null;
    }

    public synchronized VOCommonDrmAgent.VODrmChallenge generateLicenseAcquisitionChallenge() throws VOException {
        return null;
    }

    public synchronized VOCommonDrmAgent.VODrmChallenge generateLicenseAcquisitionChallenge(String str, String str2) throws VOException {
        Map.Entry<VOStatusCode, Integer> entryGenerateLicenseChallengeFromContent;
        c();
        entryGenerateLicenseChallengeFromContent = JniDrmManager.GenerateLicenseChallengeFromContent(this.c, str, str2);
        if (entryGenerateLicenseChallengeFromContent == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGenerateLicenseChallengeFromContent.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return new b(entryGenerateLicenseChallengeFromContent.getValue().intValue());
    }

    public synchronized VOCommonDrmAgent.VODrmChallenge generateLicenseChallengeFromDrmHeader(String str) throws VOException {
        Map.Entry<VOStatusCode, Integer> entryGenerateLicenseChallengeFromDrmHeader;
        c();
        entryGenerateLicenseChallengeFromDrmHeader = JniDrmManager.GenerateLicenseChallengeFromDrmHeader(this.c, str);
        if (entryGenerateLicenseChallengeFromDrmHeader == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGenerateLicenseChallengeFromDrmHeader.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return new b(entryGenerateLicenseChallengeFromDrmHeader.getValue().intValue());
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized VOCommonDrmAgent.VODrmChallenge generatePersonalizationChallenge() throws VOException {
        Map.Entry<VOStatusCode, Integer> entryGeneratePersonalizationChallenge;
        c();
        entryGeneratePersonalizationChallenge = JniDrmManager.GeneratePersonalizationChallenge(this.c, this.e, this.f, this.g, null);
        if (entryGeneratePersonalizationChallenge == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGeneratePersonalizationChallenge.getKey();
        if (a.f12a[key.ordinal()] != 1) {
            DrmHelperUtils.throwVOException(key);
        }
        return new b(entryGeneratePersonalizationChallenge.getValue().intValue());
    }

    public synchronized VOCommonDrmAgent.VODrmChallenge generateTrustedTimeChallenge() throws VOException {
        Map.Entry<VOStatusCode, Integer> entryGenerateTrustedTimeChallenge;
        c();
        entryGenerateTrustedTimeChallenge = JniDrmManager.GenerateTrustedTimeChallenge(this.c);
        if (entryGenerateTrustedTimeChallenge == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGenerateTrustedTimeChallenge.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return new b(entryGenerateTrustedTimeChallenge.getValue().intValue());
    }

    public byte[] getDrmHeader() {
        return this.m;
    }

    public String[] getLicenseAcquisitionCookies() {
        return this.k;
    }

    public String getLicenseAcquisitionCustomData() {
        return this.h;
    }

    public String[] getLicenseAcquisitionCustomHeaders() {
        return this.l;
    }

    public String getLicenseAcquisitionURL() {
        return this.i;
    }

    public String getLicenseAcquisitionURLParameters() {
        return this.j;
    }

    public String getPersonalizationAppVersion() {
        return this.e;
    }

    public String getPersonalizationPrivateCustomData() {
        return this.g;
    }

    public String getPersonalizationPublicData() {
        return this.f;
    }

    public String getPersonalizationServerUrl() {
        return this.d;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo() throws VOException {
        if (this.n == -1) {
            b();
        }
        return d(this.n);
    }

    public VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo(String str) throws Throwable {
        int iA;
        try {
            iA = a(str);
            try {
                VOCommonDrmAgent.VODrmRightsInfo[] vODrmRightsInfoArrD = d(iA);
                a(iA);
                return vODrmRightsInfoArrD;
            } catch (Throwable th) {
                th = th;
                a(iA);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            iA = -1;
        }
    }

    public VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo(byte[] bArr) throws Throwable {
        int iA;
        try {
            iA = a(bArr);
            try {
                VOCommonDrmAgent.VODrmRightsInfo[] vODrmRightsInfoArrD = d(iA);
                a(iA);
                return vODrmRightsInfoArrD;
            } catch (Throwable th) {
                th = th;
                a(iA);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            iA = -1;
        }
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public String getVersion() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized boolean isDrmContent() throws VOException {
        byte[] bArr = this.m;
        if (bArr != null) {
            return isDrmContent(bArr);
        }
        VOPlayer vOPlayer = this.b;
        if (vOPlayer == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS, "isDrmContent setDataSource first ");
        }
        return isDrmContent(vOPlayer.getStreamUri());
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized boolean isDrmContent(String str) throws VOException {
        VOStatusCode vOStatusCodeCheckDrmProtection;
        c();
        vOStatusCodeCheckDrmProtection = JniDrmManager.CheckDrmProtection(this.c, DrmHelperUtils.getLocalPath(this, str));
        int i = a.f12a[vOStatusCodeCheckDrmProtection.ordinal()];
        if (i != 1 && i != 3) {
            DrmHelperUtils.throwVOException(vOStatusCodeCheckDrmProtection);
        }
        return vOStatusCodeCheckDrmProtection == VOStatusCode.SUCCESS;
    }

    public synchronized boolean isDrmContent(byte[] bArr) throws VOException {
        VOStatusCode vOStatusCodeCheckDrmProtectionByData;
        c();
        vOStatusCodeCheckDrmProtectionByData = JniDrmManager.CheckDrmProtectionByData(this.c, bArr);
        int i = a.f12a[vOStatusCodeCheckDrmProtectionByData.ordinal()];
        if (i != 1 && i != 3) {
            DrmHelperUtils.throwVOException(vOStatusCodeCheckDrmProtectionByData);
        }
        return vOStatusCodeCheckDrmProtectionByData == VOStatusCode.SUCCESS;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void performPersonalization() throws VOException {
        c();
        String str = this.d;
        if (str == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE, "Personalization server URL null");
        }
        String lowerCase = Uri.parse(str).getScheme().toLowerCase(Locale.US);
        if (!"http".equals(lowerCase) && !"https".equals(lowerCase) && !"mms".equals(lowerCase) && !"rstp".equals(lowerCase) && !"file".equals(lowerCase)) {
            this.d = "http://" + this.d;
        }
        VOStatusCode vOStatusCodePerformPersonalization = JniDrmManager.PerformPersonalization(this.c, this.e, this.d, this.f, this.g, null);
        if (a.f12a[vOStatusCodePerformPersonalization.ordinal()] != 1) {
            DrmHelperUtils.throwVOException(vOStatusCodePerformPersonalization);
        }
    }

    public void release() {
        DrmHelperUtils.deleteContentCache(this);
        a();
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setContentIdentifier(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    public synchronized void setDrmHeader(byte[] bArr) {
        this.m = bArr;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setLicenseAcquisitionCookies(String[] strArr) {
        this.k = strArr;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setLicenseAcquisitionCustomData(String str) {
        this.h = str;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setLicenseAcquisitionCustomHeaders(String[] strArr) {
        this.l = strArr;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setLicenseAcquisitionURL(String str) {
        this.i = str;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setLicenseAcquisitionURLParameters(String str) {
        this.j = str;
    }

    public synchronized void setPersonalizationAppVersion(String str) throws VOException {
        this.e = str;
    }

    public synchronized void setPersonalizationPrivateCustomData(String str) {
        this.g = str;
    }

    public synchronized void setPersonalizationPublicData(String str) {
        this.f = str;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setPersonalizationURL(String str) throws VOException {
        try {
            if (str == null) {
                throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
            }
            this.d = str;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void useVOSecurityGateway(boolean z) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized boolean verifyPersonalization() throws VOException {
        VOStatusCode vOStatusCodeVerifyPersonalization;
        c();
        vOStatusCodeVerifyPersonalization = JniDrmManager.VerifyPersonalization(this.c);
        int i = a.f12a[vOStatusCodeVerifyPersonalization.ordinal()];
        if (i != 1 && i != 2) {
            DrmHelperUtils.throwVOException(vOStatusCodeVerifyPersonalization);
        }
        return vOStatusCodeVerifyPersonalization == VOStatusCode.SUCCESS;
    }

    public synchronized boolean verifyRights() throws VOException {
        boolean zE;
        if (this.n == -1) {
            b();
        }
        try {
            zE = e(this.n);
            a();
        } catch (Throwable th) {
            a();
            throw th;
        }
        return zE;
    }

    public synchronized boolean verifyRights(String str) throws VOException {
        int iA;
        boolean zE;
        try {
            try {
                iA = a(str);
                try {
                    zE = e(iA);
                    a(iA);
                } catch (Throwable th) {
                    th = th;
                    a(iA);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                iA = -1;
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return zE;
    }

    public synchronized boolean verifyRights(byte[] bArr) throws VOException {
        int iA;
        boolean zE;
        try {
            try {
                iA = a(bArr);
                try {
                    zE = e(iA);
                    a(iA);
                } catch (Throwable th) {
                    th = th;
                    a(iA);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                iA = -1;
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return zE;
    }
}
