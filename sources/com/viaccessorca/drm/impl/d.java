package com.viaccessorca.drm.impl;

import android.content.Context;
import android.net.Uri;
import com.viaccessorca.drm.VOCommonDrmAgent;
import com.viaccessorca.drm.VOMediaDrmAgent;
import com.viaccessorca.drm.VOPlayreadyAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOSoapException;
import com.viaccessorca.exceptions.VOStatusCode;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends DrmAgent implements VOPlayreadyAgent {
    public d(Context context, VOPlayer vOPlayer) {
        super(context, DrmAgent.EDrmType.DRM_TYPE_PLAYREADY, vOPlayer);
        try {
            DrmAgent.initialize(context);
        } catch (VOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void d() throws VOException {
        try {
            c();
            Context context = DrmHelperUtils.getContext();
            byte[] bArr = new byte[8000];
            int i = context.getAssets().open("PlayReady/provisioning.dat").read(bArr);
            File dir = context.getDir("DxDrm", 0);
            FileOutputStream fileOutputStream = new FileOutputStream(dir + "/provisioning.dat");
            fileOutputStream.write(bArr, 0, i);
            fileOutputStream.close();
            VOStatusCode vOStatusCodePerformLocalPersonalization = JniDrmManager.PerformLocalPersonalization(dir.toString());
            if (vOStatusCodePerformLocalPersonalization != VOStatusCode.SUCCESS) {
                DrmHelperUtils.throwVOException(vOStatusCodePerformLocalPersonalization);
            }
        } catch (IOException unused) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void enableMultiDrmSession() {
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public synchronized void executeInitiator(Uri uri) throws VOException {
        c();
        if (uri == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
        }
        String string = uri.toString();
        if (!string.startsWith("http://") && !string.startsWith("https://")) {
            executeInitiator(string);
            return;
        }
        JniDrmUserMessage jniDrmUserMessage = new JniDrmUserMessage();
        VOStatusCode vOStatusCodeExecuteInitiatorUrl = JniDrmManager.ExecuteInitiatorUrl(string, generateCookiesHeader(super.getLicenseAcquisitionCookies()), jniDrmUserMessage);
        if (vOStatusCodeExecuteInitiatorUrl != VOStatusCode.SUCCESS) {
            if (jniDrmUserMessage.isSet()) {
                throw new VOSoapException(vOStatusCodeExecuteInitiatorUrl, jniDrmUserMessage.getSoapMessage(), jniDrmUserMessage.getCustomData(), jniDrmUserMessage.getRedirectUrl());
            }
            DrmHelperUtils.throwVOException(vOStatusCodeExecuteInitiatorUrl);
        }
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public synchronized void executeInitiator(String str) throws VOException {
        c();
        if (str == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
        }
        if (!str.startsWith("http://") && !str.startsWith("https://")) {
            if (!new File(str).exists()) {
                throw new VOException(VOStatusCode.ERROR_FILE_ACCESS);
            }
            JniDrmUserMessage jniDrmUserMessage = new JniDrmUserMessage();
            VOStatusCode vOStatusCodeExecuteInitiatorFile = JniDrmManager.ExecuteInitiatorFile(str, generateCookiesHeader(super.getLicenseAcquisitionCookies()), jniDrmUserMessage);
            if (vOStatusCodeExecuteInitiatorFile != VOStatusCode.SUCCESS) {
                if (jniDrmUserMessage.isSet()) {
                    throw new VOSoapException(vOStatusCodeExecuteInitiatorFile, jniDrmUserMessage.getSoapMessage(), jniDrmUserMessage.getCustomData(), jniDrmUserMessage.getRedirectUrl());
                }
                DrmHelperUtils.throwVOException(vOStatusCodeExecuteInitiatorFile);
            }
            return;
        }
        executeInitiator(Uri.parse(str));
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public synchronized VOCommonDrmAgent.VODrmChallenge generateInitiatorChallenge(String str) throws VOException {
        Map.Entry<VOStatusCode, Integer> entryGenerateChallengeFromInitiator;
        c();
        entryGenerateChallengeFromInitiator = JniDrmManager.GenerateChallengeFromInitiator(this.c, str);
        if (entryGenerateChallengeFromInitiator == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGenerateChallengeFromInitiator.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return new b(entryGenerateChallengeFromInitiator.getValue().intValue());
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public int getMaxSecurityLevel() {
        return getSecurityLevel();
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public String getMaxSecurityLevelString() {
        return DrmAgent.buildSecurityLevelProperty(getMaxSecurityLevel());
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public int getSecurityLevel() {
        return 1;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public String getSecurityLevelString() {
        return DrmAgent.buildSecurityLevelProperty(getSecurityLevel());
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public String getVersion() throws VOException {
        return DrmAgent.isSupported(this.c, this.f10a) ? "PK 2.5.0" : "N/A";
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public boolean isSupported() {
        return DrmAgent.isSupported(DrmAgent.EDrmType.DRM_TYPE_PLAYREADY, this.f10a);
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void performPersonalization() throws VOException {
        String personalizationServerUrl = super.getPersonalizationServerUrl();
        if (personalizationServerUrl == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE, "Personalization server URL null");
        }
        if (personalizationServerUrl.equals("debug://localpersonalization")) {
            d();
        } else {
            super.performPersonalization();
        }
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseAcquisitionType(VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType) {
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseDurationThreshold(long j) {
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseValidityThreshold(long j) {
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public synchronized void setPersonalizationCustomData(String str) {
        super.setPersonalizationPublicData(str);
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setSecurityLevel(int i) {
    }
}
