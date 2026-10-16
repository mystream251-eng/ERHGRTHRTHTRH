package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class AudioFocusManager {
    private static final int AUDIO_FOCUS_STATE_HAVE_FOCUS = 1;
    private static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT = 2;
    private static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT_DUCK = 3;
    private static final int AUDIO_FOCUS_STATE_LOST_FOCUS = -1;
    private static final int AUDIO_FOCUS_STATE_NO_FOCUS = 0;
    public static final int PLAYER_COMMAND_DO_NOT_PLAY = -1;
    public static final int PLAYER_COMMAND_PLAY_WHEN_READY = 1;
    public static final int PLAYER_COMMAND_WAIT_FOR_CALLBACK = 0;
    private static final String TAG = "AudioFocusManager";
    private static final float VOLUME_MULTIPLIER_DEFAULT = 1.0f;
    private static final float VOLUME_MULTIPLIER_DUCK = 0.2f;
    private AudioAttributes audioAttributes;
    private AudioFocusRequest audioFocusRequest;
    private int audioFocusState;
    private final AudioManager audioManager;
    private int focusGain;
    private final AudioFocusListener focusListener;
    private final PlayerControl playerControl;
    private boolean rebuildAudioFocusRequest;
    private float volumeMultiplier = 1.0f;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerCommand {
    }

    public interface PlayerControl {
        void executePlayerCommand(int i);

        void setVolumeMultiplier(float f);
    }

    private int handleIdle(boolean z) {
        return z ? 1 : -1;
    }

    public AudioFocusManager(Context context, PlayerControl playerControl) {
        this.audioManager = context == null ? null : (AudioManager) context.getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.playerControl = playerControl;
        this.focusListener = new AudioFocusListener();
        this.audioFocusState = 0;
    }

    public float getVolumeMultiplier() {
        return this.volumeMultiplier;
    }

    public int setAudioAttributes(AudioAttributes audioAttributes, boolean z, int i) {
        if (this.audioAttributes == null && audioAttributes == null) {
            return z ? 1 : -1;
        }
        Assertions.checkNotNull(this.audioManager, "SimpleExoPlayer must be created with a context to handle audio focus.");
        if (!Util.areEqual(this.audioAttributes, audioAttributes)) {
            this.audioAttributes = audioAttributes;
            int iConvertAudioAttributesToFocusGain = convertAudioAttributesToFocusGain(audioAttributes);
            this.focusGain = iConvertAudioAttributesToFocusGain;
            Assertions.checkArgument(iConvertAudioAttributesToFocusGain == 1 || iConvertAudioAttributesToFocusGain == 0, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
            if (z && (i == 2 || i == 3)) {
                return requestAudioFocus();
            }
        }
        if (i == 1) {
            return handleIdle(z);
        }
        return handlePrepare(z);
    }

    public int handlePrepare(boolean z) {
        if (this.audioManager == null) {
            return 1;
        }
        if (z) {
            return requestAudioFocus();
        }
        return -1;
    }

    public int handleSetPlayWhenReady(boolean z, int i) {
        if (this.audioManager == null) {
            return 1;
        }
        if (z) {
            return i == 1 ? handleIdle(z) : requestAudioFocus();
        }
        abandonAudioFocus();
        return -1;
    }

    public void handleStop() {
        if (this.audioManager == null) {
            return;
        }
        abandonAudioFocus(true);
    }

    private int requestAudioFocus() {
        int iRequestAudioFocusDefault;
        if (this.focusGain == 0) {
            if (this.audioFocusState != 0) {
                abandonAudioFocus(true);
            }
            return 1;
        }
        if (this.audioFocusState == 0) {
            if (Util.SDK_INT >= 26) {
                iRequestAudioFocusDefault = requestAudioFocusV26();
            } else {
                iRequestAudioFocusDefault = requestAudioFocusDefault();
            }
            this.audioFocusState = iRequestAudioFocusDefault == 1 ? 1 : 0;
        }
        int i = this.audioFocusState;
        if (i == 0) {
            return -1;
        }
        return i == 2 ? 0 : 1;
    }

    private void abandonAudioFocus() {
        abandonAudioFocus(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void abandonAudioFocus(boolean z) {
        if (this.focusGain == 0 && this.audioFocusState == 0) {
            return;
        }
        if (this.focusGain != 1 || this.audioFocusState == -1 || z) {
            if (Util.SDK_INT >= 26) {
                abandonAudioFocusV26();
            } else {
                abandonAudioFocusDefault();
            }
            this.audioFocusState = 0;
        }
    }

    private int requestAudioFocusDefault() {
        return ((AudioManager) Assertions.checkNotNull(this.audioManager)).requestAudioFocus(this.focusListener, Util.getStreamTypeForAudioUsage(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).usage), this.focusGain);
    }

    private int requestAudioFocusV26() {
        if (this.audioFocusRequest == null || this.rebuildAudioFocusRequest) {
            this.audioFocusRequest = (this.audioFocusRequest == null ? new AudioFocusRequest.Builder(this.focusGain) : new AudioFocusRequest.Builder(this.audioFocusRequest)).setAudioAttributes(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).getAudioAttributesV21()).setWillPauseWhenDucked(willPauseWhenDucked()).setOnAudioFocusChangeListener(this.focusListener).build();
            this.rebuildAudioFocusRequest = false;
        }
        return ((AudioManager) Assertions.checkNotNull(this.audioManager)).requestAudioFocus(this.audioFocusRequest);
    }

    private void abandonAudioFocusDefault() {
        ((AudioManager) Assertions.checkNotNull(this.audioManager)).abandonAudioFocus(this.focusListener);
    }

    private void abandonAudioFocusV26() {
        if (this.audioFocusRequest != null) {
            ((AudioManager) Assertions.checkNotNull(this.audioManager)).abandonAudioFocusRequest(this.audioFocusRequest);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean willPauseWhenDucked() {
        AudioAttributes audioAttributes = this.audioAttributes;
        return audioAttributes != null && audioAttributes.contentType == 1;
    }

    private static int convertAudioAttributesToFocusGain(AudioAttributes audioAttributes) {
        if (audioAttributes == null) {
            return 0;
        }
        switch (audioAttributes.usage) {
            case 0:
                Log.w(TAG, "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                return 1;
            case 1:
            case 14:
                return 1;
            case 2:
            case 4:
                return 2;
            case 3:
                return 0;
            case 11:
                if (audioAttributes.contentType == 1) {
                    return 2;
                }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
                return 3;
            case 15:
            default:
                Log.w(TAG, "Unidentified audio usage: " + audioAttributes.usage);
                return 0;
            case 16:
                return Util.SDK_INT >= 19 ? 4 : 2;
        }
    }

    private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
        private AudioFocusListener() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i != -3) {
                if (i == -2) {
                    AudioFocusManager.this.audioFocusState = 2;
                } else if (i == -1) {
                    AudioFocusManager.this.audioFocusState = -1;
                } else if (i == 1) {
                    AudioFocusManager.this.audioFocusState = 1;
                } else {
                    Log.w(AudioFocusManager.TAG, "Unknown focus change type: " + i);
                    return;
                }
            } else if (AudioFocusManager.this.willPauseWhenDucked()) {
                AudioFocusManager.this.audioFocusState = 2;
            } else {
                AudioFocusManager.this.audioFocusState = 3;
            }
            int i2 = AudioFocusManager.this.audioFocusState;
            if (i2 == -1) {
                AudioFocusManager.this.playerControl.executePlayerCommand(-1);
                AudioFocusManager.this.abandonAudioFocus(true);
            } else if (i2 != 0) {
                if (i2 == 1) {
                    AudioFocusManager.this.playerControl.executePlayerCommand(1);
                } else if (i2 == 2) {
                    AudioFocusManager.this.playerControl.executePlayerCommand(0);
                } else if (i2 != 3) {
                    throw new IllegalStateException("Unknown audio focus state: " + AudioFocusManager.this.audioFocusState);
                }
            }
            float f = AudioFocusManager.this.audioFocusState == 3 ? 0.2f : 1.0f;
            if (AudioFocusManager.this.volumeMultiplier != f) {
                AudioFocusManager.this.volumeMultiplier = f;
                AudioFocusManager.this.playerControl.setVolumeMultiplier(f);
            }
        }
    }
}
