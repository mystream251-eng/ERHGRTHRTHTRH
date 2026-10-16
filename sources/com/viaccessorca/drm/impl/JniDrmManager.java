package com.viaccessorca.drm.impl;

import android.content.Context;
import androidx.core.content.PermissionChecker;
import com.viaccessorca.drm.VOCommonDrmAgent;
import com.viaccessorca.exceptions.VOStatusCode;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class JniDrmManager {
    JniDrmManager() {
    }

    public static final VOStatusCode AcquireRights(DrmAgent.EDrmType eDrmType, String str, String str2, String str3, String str4, String str5, String str6, JniDrmUserMessage jniDrmUserMessage) {
        return RDJFREMzMzJCNTRENDRDM0IyMUJDRDJFNDVDMzUxNEE(eDrmType, str, str2, str3, str4, str5, str6, jniDrmUserMessage);
    }

    public static final VOStatusCode AcquireRightsByData(DrmAgent.EDrmType eDrmType, byte[] bArr, String str, String str2, String str3, String str4, String str5, JniDrmUserMessage jniDrmUserMessage) {
        return Mzk3MUJCQTRBQ0NGNDFDREIzNjk2RkQyNjVDODkzOEI(eDrmType, bArr, str, str2, str3, str4, str5, jniDrmUserMessage);
    }

    public static final VOStatusCode AcquireRightsByHandler(int i, String str, String str2, String str3, String str4, String str5, JniDrmUserMessage jniDrmUserMessage) {
        return REJFNTVBNDg1NUVBNDZENDlFM0U0NzIxMzQyNTA3NTI(i, str, str2, str3, str4, str5, jniDrmUserMessage);
    }

    public static final VOStatusCode CheckDrmProtection(DrmAgent.EDrmType eDrmType, String str) {
        return RDczOEZGNTFGNDZBNDRFNUJFNzI3QUExMkIyOTFDOEM(eDrmType, str);
    }

    public static final VOStatusCode CheckDrmProtectionByData(DrmAgent.EDrmType eDrmType, byte[] bArr) {
        return MzgxODU4RDgwNjlENDc0MUI2RDBENjI2Rjg0MEQ2ODM(eDrmType, bArr);
    }

    public static final VOStatusCode DeleteDrmAssets(DrmAgent.EDrmType eDrmType) {
        return ODFGQzQ4QUVGODM3NEVCRTkyMjY4QkQ4MTA5MzFEMTQ(eDrmType);
    }

    public static final VOStatusCode DeletePersonalization(DrmAgent.EDrmType eDrmType) {
        return QTYxNTZFNkJFMTNFNEJDQ0FFMEVFMTQyODgyODU0MEQ(eDrmType);
    }

    public static final VOStatusCode DeleteRights(DrmAgent.EDrmType eDrmType, String str) {
        return MEQwQUVBQkYyRDI1NDAwMDgwNTNENDYwMEM5Nzk3Rjk(eDrmType, str);
    }

    public static final VOStatusCode DeleteRightsByData(DrmAgent.EDrmType eDrmType, byte[] bArr) {
        return RjhBNTlFRjg2NUQ5NDc1RjlBQTlBQjI2QzFFQkI4Mzk(eDrmType, bArr);
    }

    public static final VOStatusCode DeleteRightsByHandler(int i) {
        return N0JEOUYxQ0JDNTI4NDE1NjhENDMxNkQzOEYyRTU2NEQ(i);
    }

    public static final void DisposeChallenge(int i) {
        Mzc3MDU1RkFBRkQ3NDMzQzlGMzIxQkQ3MUMyQUNBMzM(i);
    }

    public static final void DisposeContentHandler(int i) {
        OUExRUI1RDY5QzhCNEJDRTk4MkFBNUI1RjBGQTg5Mzk(i);
    }

    public static final VOStatusCode ExecuteInitiatorBuffer(byte[] bArr, int i, String str, JniDrmUserMessage jniDrmUserMessage) {
        return MUNEODMzNDg2RjUxNEM1RkJFMTIzNkM1NEM0RTUzMzA(bArr, i, str, jniDrmUserMessage);
    }

    public static final VOStatusCode ExecuteInitiatorFile(String str, String str2, JniDrmUserMessage jniDrmUserMessage) {
        return RUY2RjZCNjc5MUZDNERBQTlFMEJBNzA4NEM2NEY5ODA(str, str2, jniDrmUserMessage);
    }

    public static final VOStatusCode ExecuteInitiatorUrl(String str, String str2, JniDrmUserMessage jniDrmUserMessage) {
        return MUQzRjg4QkI3OEQxNDMxNzk2N0I3RUQzQTc1RUZGMDU(str, str2, jniDrmUserMessage);
    }

    public static final Map.Entry<VOStatusCode, Integer> GenerateChallengeFromInitiator(DrmAgent.EDrmType eDrmType, String str) {
        return M0MwOEU4MTk2MjgzNDEwODlCRTEzMDM2RUVGQTQ0MTk(eDrmType, str);
    }

    public static final Map.Entry<VOStatusCode, Integer> GenerateLicenseChallengeFromContent(DrmAgent.EDrmType eDrmType, String str, String str2) {
        return OEI3ODU3NUZFQjhCNDAyMjk4OUIwRjAxQUI4QkRDNDk(eDrmType, str, str2);
    }

    public static final Map.Entry<VOStatusCode, Integer> GenerateLicenseChallengeFromDrmHeader(DrmAgent.EDrmType eDrmType, String str) {
        return OUYwMzZERUQxQTBBNENBQkE2RDJCRDgxRUNBNkEyNjQ(eDrmType, str);
    }

    public static final Map.Entry<VOStatusCode, Integer> GeneratePersonalizationChallenge(DrmAgent.EDrmType eDrmType, String str, String str2, String str3, String str4) {
        return ODVENEQ3N0E3QjU5NDg3OUFBQjUzREUzMjc5QTIwRDQ(eDrmType, str, str2, str3, str4);
    }

    public static final Map.Entry<VOStatusCode, Integer> GenerateTrustedTimeChallenge(DrmAgent.EDrmType eDrmType) {
        return NzgxRTk3NTE2RUU0NEVDN0FCNTg2MzU4NzA2NTQ0OUE(eDrmType);
    }

    public static final Map.Entry<VOStatusCode, DrmAgent.EDrmType[]> GetAvailableDrmSchemes() {
        return QTMxMjlCN0ZFRjM5NDhCRDhFQzk1NEI2ODIxRDQyRDk();
    }

    public static final Map.Entry<VOStatusCode, String> GetChallengeServerUrl(int i) {
        return MUQ4NzEyQTlDOTc2NDg5Q0FFQUI5ODIzOTIyNzRFOUQ(i);
    }

    public static final Map.Entry<VOStatusCode, String> GetChallengeSoapAction(int i) {
        return MUE1MUNBNEUxQkQ2NEVBREE1NkY1MkJBMTEyOTZCQjI(i);
    }

    public static final Map.Entry<VOStatusCode, String> GetChallengeString(int i) {
        return NUFBODA3RUQ4QzdGNEJBNjkwMjJFODFFMjI0NDNCNUI(i);
    }

    public static final Map.Entry<VOStatusCode, Integer> GetContentMaturityRatingThreshold(DrmAgent.EDrmType eDrmType, String str) {
        return MDBGNjg5OTI1RTk5NEI1ODlGQ0FCNUVFMzY0MEY1MzI(eDrmType, str);
    }

    public static final Map.Entry<VOStatusCode, Integer> GetContentMaturityRatingThresholdByData(DrmAgent.EDrmType eDrmType, byte[] bArr) {
        return OEU2MjU3RjA1NjFGNDY0M0E3NjFDRjI4MDZBNTZBMUY(eDrmType, bArr);
    }

    public static final Map.Entry<VOStatusCode, Integer> GetContentMaturityRatingThresholdByHandler(int i) {
        return MjdCMTEyMUEyNEU1NDkxMzlDRERGMTQ2MkFDQjMyQkI(i);
    }

    public static final Map.Entry<VOStatusCode, String> GetDeviceUniqueID(VOPlayer.EUniqueIdentifierType eUniqueIdentifierType) {
        return QzNGOEFBNzNGNUMwNDc3RUI4RjQ3Njk5OTE1Q0U0RkU(eUniqueIdentifierType);
    }

    public static final Map.Entry<VOStatusCode, String> GetDrmVersion() {
        return RDAzM0M1NkJFNkY4NDYxOEJDRDZCMzEzQkEzMDE5MDg();
    }

    public static final Map.Entry<VOStatusCode, Integer> GetMaturityRatingMaxAuthorizedThreshold(DrmAgent.EDrmType eDrmType) {
        return NTM5MzI4MDI0Q0I5NEUyRUE5RDlBODBCRjE1MUZENEQ(eDrmType);
    }

    public static final Map.Entry<VOStatusCode, Integer> GetMaturityRatingThreshold(DrmAgent.EDrmType eDrmType) {
        return RDZCQUMxNDU3MEU4NDEzRTg2Nzg1OTA0QTNFNENFRjQ(eDrmType);
    }

    public static final String GetOPID() {
        return OTcyOTIwNEM3RkU1NDZGMkE4MkNBNDU3RDg2QTFCRjY();
    }

    public static final Map.Entry<VOStatusCode, VOCommonDrmAgent.VODrmRightsInfo> GetRightsObjectAtIndexByHandler(DrmAgent.EDrmType eDrmType, int i, int i2) {
        return ODJBRTk3MDZBRkFGNDRDOEJCRjUyODlBQUFCMERFQzU(eDrmType, i, i2);
    }

    public static final Map.Entry<VOStatusCode, Integer> GetRightsObjectCountByHandler(int i) {
        return QzBDRURDODE1RDEyNEIzNTlGQTRCMDJFMUMwQjk1MDg(i);
    }

    public static final VOStatusCode HandlePlaybackStart() {
        return QTRCMjNDMUQ5Qjg4NEZGNEE5QzIzMEM0RjM4NDEyOUE();
    }

    public static final boolean Init(boolean z) {
        a();
        return NTYzM0RCNEY3MEIzNDQ1ODhFQUJGREZEQ0QzNTU5NkU(z);
    }

    private static final native Map.Entry<VOStatusCode, Integer> M0MwOEU4MTk2MjgzNDEwODlCRTEzMDM2RUVGQTQ0MTk(DrmAgent.EDrmType eDrmType, String str);

    private static final native Map.Entry<VOStatusCode, Integer> MDBGNjg5OTI1RTk5NEI1ODlGQ0FCNUVFMzY0MEY1MzI(DrmAgent.EDrmType eDrmType, String str);

    private static final native VOStatusCode MEQwQUVBQkYyRDI1NDAwMDgwNTNENDYwMEM5Nzk3Rjk(DrmAgent.EDrmType eDrmType, String str);

    private static final native VOStatusCode MTgyMzg3RTJFRkYyNEE5Q0EwRDFEMEZBOUM5NTY2Mjg(int i);

    private static final native Map.Entry<VOStatusCode, String> MUE1MUNBNEUxQkQ2NEVBREE1NkY1MkJBMTEyOTZCQjI(int i);

    private static final native VOStatusCode MUNEODMzNDg2RjUxNEM1RkJFMTIzNkM1NEM0RTUzMzA(byte[] bArr, int i, String str, JniDrmUserMessage jniDrmUserMessage);

    private static final native Map.Entry<VOStatusCode, String> MUQ4NzEyQTlDOTc2NDg5Q0FFQUI5ODIzOTIyNzRFOUQ(int i);

    private static final native int MUQ5MkVDMzEzNjNCNEExRjk1MjFCMkY3NkQ5NTVBNzk(String str, String str2);

    private static final native VOStatusCode MUQzRjg4QkI3OEQxNDMxNzk2N0I3RUQzQTc1RUZGMDU(String str, String str2, JniDrmUserMessage jniDrmUserMessage);

    private static final native Map.Entry<VOStatusCode, Integer> MjdCMTEyMUEyNEU1NDkxMzlDRERGMTQ2MkFDQjMyQkI(int i);

    private static final native void Mzc3MDU1RkFBRkQ3NDMzQzlGMzIxQkQ3MUMyQUNBMzM(int i);

    private static final native VOStatusCode MzgxODU4RDgwNjlENDc0MUI2RDBENjI2Rjg0MEQ2ODM(DrmAgent.EDrmType eDrmType, byte[] bArr);

    private static final native VOStatusCode Mzk3MUJCQTRBQ0NGNDFDREIzNjk2RkQyNjVDODkzOEI(DrmAgent.EDrmType eDrmType, byte[] bArr, String str, String str2, String str3, String str4, String str5, JniDrmUserMessage jniDrmUserMessage);

    private static final native VOStatusCode Mzk5MjI1MTYzMzU3NEEyRDhFMTI1QzFFNDVBNzEwRjY(String str);

    private static final native VOStatusCode N0JEOUYxQ0JDNTI4NDE1NjhENDMxNkQzOEYyRTU2NEQ(int i);

    private static final native Map.Entry<VOStatusCode, Integer> NTBGOTA4RjQ2QzY4NDY3NEFENTMwQTYxQjgxNzRGNzU(DrmAgent.EDrmType eDrmType, String str);

    private static final native Map.Entry<VOStatusCode, Integer> NTM5MzI4MDI0Q0I5NEUyRUE5RDlBODBCRjE1MUZENEQ(DrmAgent.EDrmType eDrmType);

    private static final native Map.Entry<VOStatusCode, Integer> NTQyQkQ4NTcwQzhDNEZDNUE4MzhGNjBGOEJEMzgxRTk(DrmAgent.EDrmType eDrmType, byte[] bArr);

    private static final native boolean NTYzM0RCNEY3MEIzNDQ1ODhFQUJGREZEQ0QzNTU5NkU(boolean z);

    private static final native VOStatusCode NTc2MDNGRjU2QkJGNDNENkIxNzlGMzg2RDk2NDBGOTI();

    private static final native Map.Entry<VOStatusCode, String> NUFBODA3RUQ4QzdGNEJBNjkwMjJFODFFMjI0NDNCNUI(int i);

    public static final VOStatusCode NetworkConnectionChanged(boolean z) {
        return RjkwNzk3RDE1MzgxNEUyNDhDQjc2MUZCNjdDMzBCMDY(z);
    }

    private static final native VOStatusCode NjlBQTBBMEQ2QjBFNDgyNjg3MTY5RjFENTNENUFERTE(DrmAgent.EDrmType eDrmType, int i);

    private static final native VOStatusCode NkU1MTFGRkU2OTdCNDhENTg4RjY0QUY2RDdGMEREMzc(DrmAgent.EDrmType eDrmType, String str);

    private static final native Map.Entry<VOStatusCode, Integer> NzgxRTk3NTE2RUU0NEVDN0FCNTg2MzU4NzA2NTQ0OUE(DrmAgent.EDrmType eDrmType);

    private static final native int ODFENjM2OEFGNEY2NDQ4NDk4OTczMjNBMEJGMzA0RkY(boolean z, Context context, String str);

    private static final native VOStatusCode ODFGQzQ4QUVGODM3NEVCRTkyMjY4QkQ4MTA5MzFEMTQ(DrmAgent.EDrmType eDrmType);

    private static final native Map.Entry<VOStatusCode, VOCommonDrmAgent.VODrmRightsInfo> ODJBRTk3MDZBRkFGNDRDOEJCRjUyODlBQUFCMERFQzU(DrmAgent.EDrmType eDrmType, int i, int i2);

    private static final native Map.Entry<VOStatusCode, Integer> ODM2NkFBOTY5MUQwNDY3NThFQTIwMkRCOTRGRDIxRDQ(int i, String str);

    private static final native Map.Entry<VOStatusCode, Integer> ODVENEQ3N0E3QjU5NDg3OUFBQjUzREUzMjc5QTIwRDQ(DrmAgent.EDrmType eDrmType, String str, String str2, String str3, String str4);

    private static final native Map.Entry<VOStatusCode, Integer> OEI3ODU3NUZFQjhCNDAyMjk4OUIwRjAxQUI4QkRDNDk(DrmAgent.EDrmType eDrmType, String str, String str2);

    private static final native Map.Entry<VOStatusCode, Integer> OEU2MjU3RjA1NjFGNDY0M0E3NjFDRjI4MDZBNTZBMUY(DrmAgent.EDrmType eDrmType, byte[] bArr);

    private static final native VOStatusCode OTEzQTlCQUM1RDQ2NEM0RTg5MjYxMTM2MjUzNzcxODE(int i);

    private static final native String OTcyOTIwNEM3RkU1NDZGMkE4MkNBNDU3RDg2QTFCRjY();

    private static final native void OUExRUI1RDY5QzhCNEJDRTk4MkFBNUI1RjBGQTg5Mzk(int i);

    private static final native Map.Entry<VOStatusCode, Integer> OUYwMzZERUQxQTBBNENBQkE2RDJCRDgxRUNBNkEyNjQ(DrmAgent.EDrmType eDrmType, String str);

    public static final Map.Entry<VOStatusCode, Integer> OpenDrmContentByData(DrmAgent.EDrmType eDrmType, byte[] bArr) {
        return NTQyQkQ4NTcwQzhDNEZDNUE4MzhGNjBGOEJEMzgxRTk(eDrmType, bArr);
    }

    public static final Map.Entry<VOStatusCode, Integer> OpenDrmContentByPath(DrmAgent.EDrmType eDrmType, String str) {
        return NTBGOTA4RjQ2QzY4NDY3NEFENTMwQTYxQjgxNzRGNzU(eDrmType, str);
    }

    public static final VOStatusCode PerformLocalPersonalization(String str) {
        return Mzk5MjI1MTYzMzU3NEEyRDhFMTI1QzFFNDVBNzEwRjY(str);
    }

    public static final VOStatusCode PerformPersonalization(DrmAgent.EDrmType eDrmType, String str, String str2, String str3, String str4, String str5) {
        return QkM2ODBENzc1REUwNDY2MDk0QTY3NzBEMDk0MjE1QzE(eDrmType, str, str2, str3, str4, str5);
    }

    public static final VOStatusCode PrepareRightsObjectIterationByHandler(int i) {
        return OTEzQTlCQUM1RDQ2NEM0RTg5MjYxMTM2MjUzNzcxODE(i);
    }

    public static final Map.Entry<VOStatusCode, Integer> ProcessServerResponse(int i, String str) {
        return ODM2NkFBOTY5MUQwNDY3NThFQTIwMkRCOTRGRDIxRDQ(i, str);
    }

    private static final native VOStatusCode QTEzNzZCOTAxN0MxNDVCOEI4QjgwRUQ1OEE4MEQzREE(int i);

    private static final native Map.Entry<VOStatusCode, DrmAgent.EDrmType[]> QTMxMjlCN0ZFRjM5NDhCRDhFQzk1NEI2ODIxRDQyRDk();

    private static final native VOStatusCode QTQ5NTAxMUEwMDJCNDVBMkE4QTMxMDlENEE5NjJFREU();

    private static final native VOStatusCode QTRCMjNDMUQ5Qjg4NEZGNEE5QzIzMEM0RjM4NDEyOUE();

    private static final native VOStatusCode QTYxNTZFNkJFMTNFNEJDQ0FFMEVFMTQyODgyODU0MEQ(DrmAgent.EDrmType eDrmType);

    private static final native VOStatusCode QkM2ODBENzc1REUwNDY2MDk0QTY3NzBEMDk0MjE1QzE(DrmAgent.EDrmType eDrmType, String str, String str2, String str3, String str4, String str5);

    private static final native VOStatusCode QzA3QjE2RTU2Mjk2NEZDNTgzOTRGNkRGNThDQzcxQTY(DrmAgent.EDrmType eDrmType, byte[] bArr);

    private static final native Map.Entry<VOStatusCode, Integer> QzBDRURDODE1RDEyNEIzNTlGQTRCMDJFMUMwQjk1MDg(int i);

    private static final native Map.Entry<VOStatusCode, String> QzNGOEFBNzNGNUMwNDc3RUI4RjQ3Njk5OTE1Q0U0RkU(VOPlayer.EUniqueIdentifierType eUniqueIdentifierType);

    private static final native Map.Entry<VOStatusCode, String> RDAzM0M1NkJFNkY4NDYxOEJDRDZCMzEzQkEzMDE5MDg();

    private static final native VOStatusCode RDJFREMzMzJCNTRENDRDM0IyMUJDRDJFNDVDMzUxNEE(DrmAgent.EDrmType eDrmType, String str, String str2, String str3, String str4, String str5, String str6, JniDrmUserMessage jniDrmUserMessage);

    private static final native Map.Entry<VOStatusCode, Integer> RDZCQUMxNDU3MEU4NDEzRTg2Nzg1OTA0QTNFNENFRjQ(DrmAgent.EDrmType eDrmType);

    private static final native VOStatusCode RDczOEZGNTFGNDZBNDRFNUJFNzI3QUExMkIyOTFDOEM(DrmAgent.EDrmType eDrmType, String str);

    private static final native VOStatusCode REJFNTVBNDg1NUVBNDZENDlFM0U0NzIxMzQyNTA3NTI(int i, String str, String str2, String str3, String str4, String str5, JniDrmUserMessage jniDrmUserMessage);

    private static final native VOStatusCode RUY2RjZCNjc5MUZDNERBQTlFMEJBNzA4NEM2NEY5ODA(String str, String str2, JniDrmUserMessage jniDrmUserMessage);

    private static final native VOStatusCode RjNBNTMwMTJCODRCNDQyOEEyQjQ4NTY4RTc1N0JENDc(DrmAgent.EDrmType eDrmType);

    private static final native VOStatusCode RjNDQzk0NjFBRkFFNDBGOTkwMDcyMzc5NjYzRDBCOUQ(DrmAgent.EDrmFrameworkConfigurationKey eDrmFrameworkConfigurationKey, int i);

    private static final native VOStatusCode RjhBNTlFRjg2NUQ5NDc1RjlBQTlBQjI2QzFFQkI4Mzk(DrmAgent.EDrmType eDrmType, byte[] bArr);

    private static final native VOStatusCode RjkwNzk3RDE1MzgxNEUyNDhDQjc2MUZCNjdDMzBCMDY(boolean z);

    private static final native VOStatusCode RjlENjA4MTM2MDdDNDc1NDgzOUIzQzQ2OTFDNEYzNzc(DrmAgent.EDrmFrameworkConfigurationKey eDrmFrameworkConfigurationKey, String str);

    public static final VOStatusCode SetConfigurationWithInteger(DrmAgent.EDrmFrameworkConfigurationKey eDrmFrameworkConfigurationKey, int i) {
        return RjNDQzk0NjFBRkFFNDBGOTkwMDcyMzc5NjYzRDBCOUQ(eDrmFrameworkConfigurationKey, i);
    }

    public static final VOStatusCode SetConfigurationWithString(DrmAgent.EDrmFrameworkConfigurationKey eDrmFrameworkConfigurationKey, String str) {
        return RjlENjA4MTM2MDdDNDc1NDgzOUIzQzQ2OTFDNEYzNzc(eDrmFrameworkConfigurationKey, str);
    }

    public static final int SetEventKey(String str, String str2) {
        return MUQ5MkVDMzEzNjNCNEExRjk1MjFCMkY3NkQ5NTVBNzk(str, str2);
    }

    public static final VOStatusCode SetMaturityRatingThreshold(DrmAgent.EDrmType eDrmType, int i) {
        return NjlBQTBBMEQ2QjBFNDgyNjg3MTY5RjFENTNENUFERTE(eDrmType, i);
    }

    public static final VOStatusCode TerminateRightsObjectIterationByHandler(int i) {
        return MTgyMzg3RTJFRkYyNEE5Q0EwRDFEMEZBOUM5NTY2Mjg(i);
    }

    public static final VOStatusCode VerifyPersonalization(DrmAgent.EDrmType eDrmType) {
        return RjNBNTMwMTJCODRCNDQyOEEyQjQ4NTY4RTc1N0JENDc(eDrmType);
    }

    public static final VOStatusCode VerifyRights(DrmAgent.EDrmType eDrmType, String str) {
        return NkU1MTFGRkU2OTdCNDhENTg4RjY0QUY2RDdGMEREMzc(eDrmType, str);
    }

    public static final VOStatusCode VerifyRightsByData(DrmAgent.EDrmType eDrmType, byte[] bArr) {
        return QzA3QjE2RTU2Mjk2NEZDNTgzOTRGNkRGNThDQzcxQTY(eDrmType, bArr);
    }

    public static final VOStatusCode VerifyRightsByHandler(int i) {
        return QTEzNzZCOTAxN0MxNDVCOEI4QjgwRUQ1OEE4MEQzREE(i);
    }

    private static void a() {
        if (8888 == System.currentTimeMillis()) {
            try {
                JniCDxJniHttpAgent jniCDxJniHttpAgent = new JniCDxJniHttpAgent();
                JniECspDrmStatus jniECspDrmStatus = JniECspDrmStatus.CSP_DRM_SUCCESS;
                DrmAgent.EDrmType eDrmType = DrmAgent.EDrmType.DRM_TYPE_WIDEVINE;
                DrmRightsInfo drmRightsInfo = new DrmRightsInfo(DrmAgent.EDrmType.DRM_TYPE_WIDEVINE, VOCommonDrmAgent.VODrmRightsInfo.EDrmRightsInfo.m329t7p6390828478446, false, null, null, false, 0L, false, 0L, 0L);
                VOCommonDrmAgent.VODrmRightsInfo.EDrmRightsInfo eDrmRightsInfo = VOCommonDrmAgent.VODrmRightsInfo.EDrmRightsInfo.m329t7p6390828478446;
                VOCommonDrmAgent.VODrmRightsInfo[] rightsInfo = new o(null, null).getRightsInfo();
                PermissionChecker.checkPermission(null, null, 0, 0, null);
                rightsInfo.toString();
                jniECspDrmStatus.toString();
                eDrmType.toString();
                eDrmRightsInfo.toString();
                drmRightsInfo.toString();
                PlatformUtils.m2yugdva7tpoe4A5();
                PlatformUtils.m2yugdva7tpoe4A6();
                PlatformUtils.m2yugdva7tpoe4A3();
                PlatformUtils.m2yugdva7tpoe4A7();
                PlatformUtils.m2yugdva7tpoe4A4();
                jniCDxJniHttpAgent.toString();
            } catch (Exception unused) {
            }
        }
    }

    public static final int checkSelfPermission(boolean z, Context context, String str) {
        return ODFENjM2OEFGNEY2NDQ4NDk4OTczMjNBMEJGMzA0RkY(z, context, str);
    }

    public static final VOStatusCode isInitialized() {
        return NTc2MDNGRjU2QkJGNDNENkIxNzlGMzg2RDk2NDBGOTI();
    }

    public static final VOStatusCode isOsTampered() {
        return QTQ5NTAxMUEwMDJCNDVBMkE4QTMxMDlENEE5NjJFREU();
    }
}
