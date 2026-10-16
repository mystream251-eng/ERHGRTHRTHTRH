package com.viaccessorca.drm;

import com.viaccessorca.exceptions.VOException;

/* JADX INFO: loaded from: classes2.dex */
public interface VOViaccessAgent extends VOMediaDrmAgent {
    public static final int UndefinedMaturityRatingLevel = -1;

    void acquireRights(String str) throws VOException;

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    void deleteRights() throws VOException;

    void deleteRights(String str) throws VOException;

    VOCommonDrmAgent.VODrmChallenge generateTrustedTimeChallenge() throws VOException;

    int getContentMaturityRatingThreshold() throws VOException;

    int getMaturityRatingMaxAuthorizedThreshold() throws VOException;

    int getMaturityRatingThreshold() throws VOException;

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo() throws VOException;

    VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo(String str) throws VOException;

    void setMaturityRatingThreshold(int i) throws VOException;

    void setPersonalizationPrivateCustomData(String str);

    void setPersonalizationPublicData(String str);

    void unlockSession();

    boolean verifyRights() throws VOException;

    boolean verifyRights(String str) throws VOException;
}
