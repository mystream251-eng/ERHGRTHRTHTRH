package tv.broadpeak.smartlib.ad.pal;

import android.content.Context;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AdPalManager {
    public Object mConsentSettings;

    public interface AdPalManagerCallback {
        void onPalSessionGeneration(AdPalSession adPalSession);

        void onPalSessionGenerationFailure(Exception exc);
    }

    public AdPalManager(Context context) {
    }

    public abstract void generateAdPalSession();

    public abstract void registerPalManagerCallback(AdPalManagerCallback adPalManagerCallback);

    public abstract void release();

    public abstract void reset();

    public void sendAdClick(Object obj) {
    }

    public void sendAdImpression(Object obj) {
    }

    public void sendTouch(Object obj, MotionEvent motionEvent) {
    }

    public void setConsentSettings(Object obj) {
        this.mConsentSettings = obj;
    }

    public void setDescriptionURL(String str) {
        setValue("setDescriptionURL", str);
    }

    public void setOmidPartner(String str, String str2) {
        setValue("setOmidPartner", str, str2);
    }

    public void setOmidVersion(String str) {
        setValue("setOmidVersion", str);
    }

    public void setPlayerType(String str) {
        setValue("setPlayerType", str);
    }

    public void setPlayerVersion(String str) {
        setValue("setPlayerVersion", str);
    }

    public void setPpid(String str) {
        setValue("setPpid", str);
    }

    public abstract void setValue(String str, int i);

    public abstract void setValue(String str, String str2);

    public abstract void setValue(String str, String str2, String str3);

    public abstract void setValue(String str, boolean z);

    public void setVideoPlayerHeight(int i) {
        setValue("setVideoPlayerHeight", i);
    }

    public void setVideoPlayerWidth(int i) {
        setValue("setVideoPlayerWidth", i);
    }

    public void setWillAdAutoPlay(boolean z) {
        setValue("setWillAdAutoPlay", z);
    }

    public void setWillAdPlayMuted(boolean z) {
        setValue("setWillAdPlayMuted", z);
    }

    public abstract void unregisterPalManagerCallback(AdPalManagerCallback adPalManagerCallback);
}
