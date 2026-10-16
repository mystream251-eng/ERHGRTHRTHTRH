package com.google.android.exoplayer2.audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: loaded from: classes.dex */
public final class AudioCapabilitiesReceiver {
    AudioCapabilities audioCapabilities;
    private final Context context;
    private final Handler handler;
    private final Listener listener;
    private final BroadcastReceiver receiver;

    public interface Listener {
        void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities);
    }

    public AudioCapabilitiesReceiver(Context context, Listener listener) {
        this(context, null, listener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AudioCapabilitiesReceiver(Context context, Handler handler, Listener listener) {
        this.context = (Context) Assertions.checkNotNull(context);
        this.handler = handler;
        this.listener = (Listener) Assertions.checkNotNull(listener);
        this.receiver = Util.SDK_INT >= 21 ? new HdmiAudioPlugBroadcastReceiver() : null;
    }

    public AudioCapabilities register() {
        Intent intentRegisterReceiver = null;
        if (this.receiver != null) {
            IntentFilter intentFilter = new IntentFilter("android.media.action.HDMI_AUDIO_PLUG");
            Handler handler = this.handler;
            if (handler != null) {
                intentRegisterReceiver = this.context.registerReceiver(this.receiver, intentFilter, null, handler);
            } else {
                intentRegisterReceiver = this.context.registerReceiver(this.receiver, intentFilter);
            }
        }
        AudioCapabilities capabilities = AudioCapabilities.getCapabilities(intentRegisterReceiver);
        this.audioCapabilities = capabilities;
        return capabilities;
    }

    public void unregister() {
        BroadcastReceiver broadcastReceiver = this.receiver;
        if (broadcastReceiver != null) {
            this.context.unregisterReceiver(broadcastReceiver);
        }
    }

    private final class HdmiAudioPlugBroadcastReceiver extends BroadcastReceiver {
        private HdmiAudioPlugBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (isInitialStickyBroadcast()) {
                return;
            }
            AudioCapabilities capabilities = AudioCapabilities.getCapabilities(intent);
            if (capabilities.equals(AudioCapabilitiesReceiver.this.audioCapabilities)) {
                return;
            }
            AudioCapabilitiesReceiver.this.audioCapabilities = capabilities;
            AudioCapabilitiesReceiver.this.listener.onAudioCapabilitiesChanged(capabilities);
        }
    }
}
