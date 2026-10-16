package com.viaccessorca.voplayer;

import android.graphics.Bitmap;
import android.os.Handler;
import com.viaccessorca.common.VOUtils;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VOAdBannerManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private VOPlayer.AdBannerParameters f52a;
    private VOSurfaceView b = null;
    private Bitmap c = null;
    private VOAdBannerInfo d = null;
    private VOAdBannerDimensionsInfo e = null;
    Handler f = new Handler();
    private Runnable g;

    public class VOAdBannerDimensionsInfo {
        public int videoHeight = 0;
        public int videoWidth = 0;
        public int videoTop = 0;
        public int videoLeft = 0;
        public int bannerHeight = 0;
        public int bannerWidth = 0;
        public int bannerTop = 0;
        public int bannerLeft = 0;

        public VOAdBannerDimensionsInfo(VOAdBannerManager vOAdBannerManager) {
        }
    }

    public class VOAdBannerInfo {
        public String adId = null;
        public String mediaURL = null;
        public String adType = null;
        public String actionType = null;
        public String link = null;
        public String impression = null;

        public VOAdBannerInfo(VOAdBannerManager vOAdBannerManager) {
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VOAdBannerManager vOAdBannerManager = VOAdBannerManager.this;
            vOAdBannerManager.f.postDelayed(vOAdBannerManager.g, VOAdBannerManager.this.f52a.duration * 1000);
            VOAdBannerManager.this.a();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jsonFromUrl;
            try {
                try {
                    jsonFromUrl = VOUtils.readJsonFromUrl(VOAdBannerManager.this.f52a.requestURL);
                } catch (JSONException unused) {
                    return;
                }
            } catch (IOException unused2) {
                jsonFromUrl = null;
            }
            if (jsonFromUrl != null) {
                JSONArray jSONArray = jsonFromUrl.getJSONObject("data").getJSONArray("data");
                if (jSONArray.length() > 0) {
                    JSONObject jSONObject = jSONArray.getJSONObject(0);
                    VOAdBannerManager.this.d = new VOAdBannerInfo(VOAdBannerManager.this);
                    VOAdBannerManager.this.d.adId = jSONObject.getString("id");
                    VOAdBannerManager.this.d.mediaURL = jSONObject.getString("mediaURL");
                    if (jSONObject.has("adtype")) {
                        VOAdBannerManager.this.d.adType = jSONObject.getString("adtype");
                    }
                    if (jSONObject.has("actiontype")) {
                        VOAdBannerManager.this.d.actionType = jSONObject.getString("actiontype");
                    }
                    if (jSONObject.has("link")) {
                        VOAdBannerManager.this.d.link = jSONObject.getString("link");
                    }
                    if (jSONObject.has("impression")) {
                        VOAdBannerManager.this.d.impression = jSONObject.getString("impression");
                    }
                    VOAdBannerManager.this.b();
                }
            }
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                VOAdBannerManager.this.c = VOUtils.readBitmapFromUrl(VOAdBannerManager.this.d.mediaURL);
                VOAdBannerManager.this.b.a(VOAdBannerManager.this.c);
                if (VOAdBannerManager.this.f52a.eventListener != null) {
                    VOAdBannerManager.this.f52a.eventListener.onAdBannerEvent(VOPlayer.AdBannerParameters.AdBannerEventType.AD_BANNER_EVENT_CHANGED, VOAdBannerManager.this.d);
                }
                if (VOAdBannerManager.this.d.impression != null) {
                    VOUtils.sendHTTPRequest(VOAdBannerManager.this.d.impression, VOUtils.HTTP_GET, null, null, true);
                }
            } catch (IOException unused) {
            }
        }
    }

    public VOAdBannerManager(VOPlayer.AdBannerParameters adBannerParameters) {
        this.f52a = null;
        a aVar = new a();
        this.g = aVar;
        this.f52a = adBannerParameters;
        if (adBannerParameters.visibility) {
            if (adBannerParameters.duration > 0) {
                this.f.post(aVar);
            } else {
                a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        VOPlayer.AdBannerParameters adBannerParameters = this.f52a;
        if (adBannerParameters == null || adBannerParameters.requestURL == null) {
            return;
        }
        new Thread(new b()).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        VOAdBannerInfo vOAdBannerInfo = this.d;
        if (vOAdBannerInfo == null || this.b == null || vOAdBannerInfo.mediaURL == null) {
            return;
        }
        new Thread(new c()).start();
    }

    public void eventClickedLink() {
        VOAdBannerInfo vOAdBannerInfo;
        VOPlayer.AdBannerParameters.OnAdBannerEventListener onAdBannerEventListener = this.f52a.eventListener;
        if (onAdBannerEventListener == null || (vOAdBannerInfo = this.d) == null) {
            return;
        }
        onAdBannerEventListener.onAdBannerEvent(VOPlayer.AdBannerParameters.AdBannerEventType.AD_BANNER_EVENT_CLICKED, vOAdBannerInfo);
    }

    public void eventClickedReport() {
        VOAdBannerInfo vOAdBannerInfo;
        VOPlayer.AdBannerParameters.OnAdBannerEventListener onAdBannerEventListener = this.f52a.eventListener;
        if (onAdBannerEventListener == null || (vOAdBannerInfo = this.d) == null) {
            return;
        }
        onAdBannerEventListener.onAdBannerEvent(VOPlayer.AdBannerParameters.AdBannerEventType.AD_BANNER_EVENT_REPORTED, vOAdBannerInfo);
    }

    public void eventDimensionsUpdate(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.e == null) {
            this.e = new VOAdBannerDimensionsInfo(this);
        }
        VOAdBannerDimensionsInfo vOAdBannerDimensionsInfo = this.e;
        if (i5 == vOAdBannerDimensionsInfo.bannerHeight && i8 == vOAdBannerDimensionsInfo.bannerLeft && i7 == vOAdBannerDimensionsInfo.bannerTop && i6 == vOAdBannerDimensionsInfo.bannerWidth && i == vOAdBannerDimensionsInfo.videoHeight && i4 == vOAdBannerDimensionsInfo.videoLeft && i3 == vOAdBannerDimensionsInfo.videoTop && i2 == vOAdBannerDimensionsInfo.videoWidth) {
            return;
        }
        VOAdBannerDimensionsInfo vOAdBannerDimensionsInfo2 = this.e;
        vOAdBannerDimensionsInfo2.videoHeight = i;
        vOAdBannerDimensionsInfo2.videoWidth = i2;
        vOAdBannerDimensionsInfo2.videoTop = i3;
        vOAdBannerDimensionsInfo2.videoLeft = i4;
        vOAdBannerDimensionsInfo2.bannerHeight = i5;
        vOAdBannerDimensionsInfo2.bannerWidth = i6;
        vOAdBannerDimensionsInfo2.bannerTop = i7;
        vOAdBannerDimensionsInfo2.bannerLeft = i8;
        VOPlayer.AdBannerParameters.OnAdBannerEventListener onAdBannerEventListener = this.f52a.eventListener;
        if (onAdBannerEventListener != null) {
            onAdBannerEventListener.onAdBannerEvent(VOPlayer.AdBannerParameters.AdBannerEventType.AD_BANNER_EVENT_DIMENSIONS_CHANGED, vOAdBannerDimensionsInfo2);
        }
    }

    public boolean isClosable() {
        return this.f52a.closable;
    }

    public void reset() {
        this.f.removeMessages(0);
    }

    public void setVideoView(VOSurfaceView vOSurfaceView) {
        this.b = vOSurfaceView;
        if (vOSurfaceView == null) {
            return;
        }
        vOSurfaceView.enableAdBanner(true);
        vOSurfaceView.setAdBannerManager(this);
    }
}
