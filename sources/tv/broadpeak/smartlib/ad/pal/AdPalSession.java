package tv.broadpeak.smartlib.ad.pal;

import android.view.MotionEvent;
import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.CoreMemory;

/* JADX INFO: loaded from: classes3.dex */
public class AdPalSession {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AdPalManager f185a;
    public final String b;
    public final Object c;

    public AdPalSession(AdPalManager adPalManager, String str) {
        this.f185a = adPalManager;
        this.b = str;
        this.c = null;
    }

    public JSObject createJSObject(JSContext jSContext) {
        JSObject jSObjectCreate = QuickJSUtils.create(jSContext, "smartlib.AdPalSession");
        try {
            jSObjectCreate.setProperty("getNonce", jSContext.createJSFunction(this, Method.create(String.class, AdPalSession.class.getMethod("getNonce", new Class[0]))));
            jSObjectCreate.setProperty("sendAdClick", jSContext.createJSFunction(this, Method.create(Void.class, AdPalSession.class.getMethod("sendAdClick", new Class[0]))));
            jSObjectCreate.setProperty("sendAdImpression", jSContext.createJSFunction(this, Method.create(Void.class, AdPalSession.class.getMethod("sendAdImpression", new Class[0]))));
            jSObjectCreate.setProperty("sendTouch", jSContext.createJSFunction(this, Method.create(Void.class, AdPalSession.class.getMethod("sendTouch", JSValue.class))));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return jSObjectCreate;
    }

    public String getNonce() {
        return this.b;
    }

    public void sendAdClick() {
        this.f185a.sendAdClick(this.c);
    }

    public void sendAdImpression() {
        this.f185a.sendAdImpression(this.c);
    }

    public void sendTouch(JSValue jSValue) {
        this.f185a.sendTouch(this.c, jSValue instanceof JSNumber ? (MotionEvent) CoreMemory.getInstance().get(((JSNumber) jSValue.cast(JSNumber.class)).getInt()) : null);
    }

    public AdPalSession(AdPalManager adPalManager, String str, Object obj) {
        this.f185a = adPalManager;
        this.b = str;
        this.c = obj;
    }
}
