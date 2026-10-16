package com.google.android.exoplayer2.drm;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineLicenseHelper<T extends ExoMediaCrypto> {
    private final ConditionVariable conditionVariable;
    private final DefaultDrmSessionManager<T> drmSessionManager;
    private final HandlerThread handlerThread;

    public static OfflineLicenseHelper<FrameworkMediaCrypto> newWidevineInstance(String str, HttpDataSource.Factory factory) throws UnsupportedDrmException {
        return newWidevineInstance(str, false, factory, null);
    }

    public static OfflineLicenseHelper<FrameworkMediaCrypto> newWidevineInstance(String str, boolean z, HttpDataSource.Factory factory) throws UnsupportedDrmException {
        return newWidevineInstance(str, z, factory, null);
    }

    public static OfflineLicenseHelper<FrameworkMediaCrypto> newWidevineInstance(String str, boolean z, HttpDataSource.Factory factory, HashMap<String, String> map) throws UnsupportedDrmException {
        return new OfflineLicenseHelper<>(C.WIDEVINE_UUID, FrameworkMediaDrm.newInstance(C.WIDEVINE_UUID), new HttpMediaDrmCallback(str, z, factory), map);
    }

    public OfflineLicenseHelper(UUID uuid, ExoMediaDrm<T> exoMediaDrm, MediaDrmCallback mediaDrmCallback, HashMap<String, String> map) {
        HandlerThread handlerThread = new HandlerThread("OfflineLicenseHelper");
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.conditionVariable = new ConditionVariable();
        DefaultDrmSessionEventListener defaultDrmSessionEventListener = new DefaultDrmSessionEventListener() { // from class: com.google.android.exoplayer2.drm.OfflineLicenseHelper.1
            @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
            public void onDrmKeysLoaded() {
                OfflineLicenseHelper.this.conditionVariable.open();
            }

            @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
            public void onDrmSessionManagerError(Exception exc) {
                OfflineLicenseHelper.this.conditionVariable.open();
            }

            @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
            public void onDrmKeysRestored() {
                OfflineLicenseHelper.this.conditionVariable.open();
            }

            @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener
            public void onDrmKeysRemoved() {
                OfflineLicenseHelper.this.conditionVariable.open();
            }
        };
        DefaultDrmSessionManager<T> defaultDrmSessionManager = new DefaultDrmSessionManager<>(uuid, exoMediaDrm, mediaDrmCallback, map);
        this.drmSessionManager = defaultDrmSessionManager;
        defaultDrmSessionManager.addListener(new Handler(this.handlerThread.getLooper()), defaultDrmSessionEventListener);
    }

    public synchronized byte[] getPropertyByteArray(String str) {
        return this.drmSessionManager.getPropertyByteArray(str);
    }

    public synchronized void setPropertyByteArray(String str, byte[] bArr) {
        this.drmSessionManager.setPropertyByteArray(str, bArr);
    }

    public synchronized String getPropertyString(String str) {
        return this.drmSessionManager.getPropertyString(str);
    }

    public synchronized void setPropertyString(String str, String str2) {
        this.drmSessionManager.setPropertyString(str, str2);
    }

    public synchronized byte[] downloadLicense(DrmInitData drmInitData) throws DrmSession.DrmSessionException {
        Assertions.checkArgument(drmInitData != null);
        return blockingKeyRequest(2, null, drmInitData);
    }

    public synchronized byte[] renewLicense(byte[] bArr) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(bArr);
        return blockingKeyRequest(2, bArr, null);
    }

    public synchronized void releaseLicense(byte[] bArr) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(bArr);
        blockingKeyRequest(3, bArr, null);
    }

    public synchronized Pair<Long, Long> getLicenseDurationRemainingSec(byte[] bArr) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(bArr);
        DrmSession<T> drmSessionOpenBlockingKeyRequest = openBlockingKeyRequest(1, bArr, null);
        DrmSession.DrmSessionException error = drmSessionOpenBlockingKeyRequest.getError();
        Pair<Long, Long> licenseDurationRemainingSec = WidevineUtil.getLicenseDurationRemainingSec(drmSessionOpenBlockingKeyRequest);
        this.drmSessionManager.releaseSession(drmSessionOpenBlockingKeyRequest);
        if (error == null) {
            return licenseDurationRemainingSec;
        }
        if (error.getCause() instanceof KeysExpiredException) {
            return Pair.create(0L, 0L);
        }
        throw error;
    }

    public void release() {
        this.handlerThread.quit();
    }

    private byte[] blockingKeyRequest(int i, byte[] bArr, DrmInitData drmInitData) throws DrmSession.DrmSessionException {
        DrmSession<T> drmSessionOpenBlockingKeyRequest = openBlockingKeyRequest(i, bArr, drmInitData);
        DrmSession.DrmSessionException error = drmSessionOpenBlockingKeyRequest.getError();
        byte[] offlineLicenseKeySetId = drmSessionOpenBlockingKeyRequest.getOfflineLicenseKeySetId();
        this.drmSessionManager.releaseSession(drmSessionOpenBlockingKeyRequest);
        if (error == null) {
            return offlineLicenseKeySetId;
        }
        throw error;
    }

    private DrmSession<T> openBlockingKeyRequest(int i, byte[] bArr, DrmInitData drmInitData) {
        this.drmSessionManager.setMode(i, bArr);
        this.conditionVariable.close();
        DrmSession<T> drmSessionAcquireSession = this.drmSessionManager.acquireSession(this.handlerThread.getLooper(), drmInitData);
        this.conditionVariable.block();
        return drmSessionAcquireSession;
    }
}
