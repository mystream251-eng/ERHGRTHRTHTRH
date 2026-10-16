package com.google.firebase.messaging;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzl implements ComponentFactory {
    static final ComponentFactory zza = new zzl();

    private zzl() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        FirebaseApp firebaseApp = (FirebaseApp) componentContainer.get(FirebaseApp.class);
        FirebaseInstanceId firebaseInstanceId = (FirebaseInstanceId) componentContainer.get(FirebaseInstanceId.class);
        UserAgentPublisher userAgentPublisher = (UserAgentPublisher) componentContainer.get(UserAgentPublisher.class);
        HeartBeatInfo heartBeatInfo = (HeartBeatInfo) componentContainer.get(HeartBeatInfo.class);
        FirebaseInstallationsApi firebaseInstallationsApi = (FirebaseInstallationsApi) componentContainer.get(FirebaseInstallationsApi.class);
        TransportFactory zzbVar = (TransportFactory) componentContainer.get(TransportFactory.class);
        if (zzbVar == null || !CCTDestination.LEGACY_INSTANCE.getSupportedEncodings().contains(Encoding.of("json"))) {
            zzbVar = new FirebaseMessagingRegistrar.zzb();
        }
        return new FirebaseMessaging(firebaseApp, firebaseInstanceId, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi, zzbVar);
    }
}
