package com.viaccessorca.drm;

import android.net.Uri;
import com.viaccessorca.exceptions.VOException;

/* JADX INFO: loaded from: classes2.dex */
public interface VOPlayreadyAgent extends VOMediaDrmAgent {
    void acquireRights(String str) throws VOException;

    void deleteRights(String str) throws VOException;

    void executeInitiator(Uri uri) throws VOException;

    void executeInitiator(String str) throws VOException;

    VOCommonDrmAgent.VODrmChallenge generateInitiatorChallenge(String str) throws VOException;

    VOCommonDrmAgent.VODrmChallenge generateLicenseAcquisitionChallenge() throws VOException;

    VOCommonDrmAgent.VODrmChallenge generateLicenseAcquisitionChallenge(String str, String str2) throws VOException;

    VOCommonDrmAgent.VODrmChallenge generateTrustedTimeChallenge() throws VOException;

    VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo(String str) throws VOException;

    void setPersonalizationAppVersion(String str) throws VOException;

    void setPersonalizationCustomData(String str);

    boolean verifyRights() throws VOException;

    boolean verifyRights(String str) throws VOException;
}
