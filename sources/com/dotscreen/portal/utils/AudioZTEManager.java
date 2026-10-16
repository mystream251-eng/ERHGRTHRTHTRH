package com.dotscreen.portal.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import androidx.core.app.NotificationCompat;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.SharedPreferencesHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import megacable.settings.aidl.AudioInterface;

/* JADX INFO: compiled from: AudioZTEManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u001c\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/utils/AudioZTEManager;", "Landroid/content/ServiceConnection;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "audioService", "Lmegacable/settings/aidl/AudioInterface;", "getAudioService", "()Lmegacable/settings/aidl/AudioInterface;", "setAudioService", "(Lmegacable/settings/aidl/AudioInterface;)V", "getContext", "()Landroid/content/Context;", "closeService", "", "connectToAudioService", "onServiceConnected", "name", "Landroid/content/ComponentName;", NotificationCompat.CATEGORY_SERVICE, "Landroid/os/IBinder;", "onServiceDisconnected", "seAudioToPcm", "setDigitalAudioFormat", "audioFormat", "", "setPcmifNotChecked", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class AudioZTEManager implements ServiceConnection {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AudioInterface audioService;
    private final Context context;

    public AudioZTEManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public final AudioInterface getAudioService() {
        return this.audioService;
    }

    public final void setAudioService(AudioInterface audioInterface) {
        this.audioService = audioInterface;
    }

    public final void connectToAudioService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("megacable.settings.aidl", "megacable.settings.aidl.AudioService"));
        this.context.bindService(intent, this, 1);
    }

    private final void setPcmifNotChecked(Context context) throws RemoteException {
        SharedPreferencesHelper companion = SharedPreferencesHelper.INSTANCE.getInstance(context);
        if (companion == null || companion.retrieveBoolean(AudioZTEManagerKt.KEY_PCM_SETTED)) {
            return;
        }
        seAudioToPcm(context);
    }

    public final void seAudioToPcm(Context context) throws RemoteException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        AudioInterface audioInterface = this.audioService;
        if (audioInterface != null) {
            audioInterface.setDigitalAudioFormat(AudioZTEManagerKt.DIGITAL_SOUND_PCM);
        }
        SharedPreferencesHelper companion = SharedPreferencesHelper.INSTANCE.getInstance(context);
        if (companion != null) {
            companion.store(AudioZTEManagerKt.KEY_PCM_SETTED, true);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        this.audioService = (AudioInterface) null;
    }

    public final void closeService() {
        this.context.unbindService(this);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) throws RemoteException {
        this.audioService = AudioInterface.Stub.asInterface(service);
        setPcmifNotChecked(this.context);
    }

    public final void setDigitalAudioFormat(String audioFormat) throws RemoteException {
        Intrinsics.checkParameterIsNotNull(audioFormat, "audioFormat");
        AudioInterface audioInterface = this.audioService;
        if (audioInterface != null) {
            audioInterface.setDigitalAudioFormat(audioFormat);
        }
        closeService();
    }

    /* JADX INFO: compiled from: AudioZTEManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/utils/AudioZTEManager$Companion;", "", "()V", "isZtePhase2", "", "isZtePlatform", "isZtePlatform2", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isZtePhase2() {
            String version = SystemProperties.get("ro.product.version.software", "N/A");
            if (!(!Intrinsics.areEqual(version, "N/A"))) {
                return false;
            }
            Intrinsics.checkExpressionValueIsNotNull(version, "version");
            return Integer.parseInt((String) StringsKt.split$default((CharSequence) version, new String[]{"."}, false, 0, 6, (Object) null).get(1)) >= 64;
        }

        public final boolean isZtePlatform() {
            return CommonUtils.getCurrentPlatform() == 3 || CommonUtils.getCurrentPlatform() == 5;
        }

        public final boolean isZtePlatform2() {
            return CommonUtils.getCurrentPlatform() == 5;
        }
    }
}
