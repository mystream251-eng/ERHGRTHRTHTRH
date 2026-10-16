package tv.broadpeak.smartlib.ad.pal;

import android.content.Context;
import android.view.MotionEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class AdPalManager17_1_0 extends AdPalManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f184a;
    public Class b;
    public Class c;
    public Class d;
    public Class e;
    public Class f;
    public Object g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public Integer o;
    public Integer p;
    public Boolean q;
    public Boolean r;
    public final ArrayList<AdPalManager.AdPalManagerCallback> s;

    public AdPalManager17_1_0(Context context) {
        super(context);
        this.f184a = context;
        this.s = new ArrayList<>();
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        try {
            this.b = Class.forName("com.google.ads.interactivemedia.pal.ConsentSettings");
            this.c = Class.forName("com.google.ads.interactivemedia.pal.NonceLoader");
            this.d = Class.forName("com.google.ads.interactivemedia.pal.NonceRequest");
            this.e = Class.forName("com.google.android.gms.tasks.OnSuccessListener");
            this.f = Class.forName("com.google.android.gms.tasks.OnFailureListener");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object b(Object obj, Method method, Object[] objArr) throws Throwable {
        if (!method.getName().equals("onFailure") || !(objArr[0] instanceof Exception)) {
            return null;
        }
        Iterator<AdPalManager.AdPalManagerCallback> it = this.s.iterator();
        while (it.hasNext()) {
            it.next().onPalSessionGenerationFailure((Exception) objArr[0]);
        }
        return null;
    }

    public final void a(Object obj, String str, String str2) {
        if (str2 == null) {
            return;
        }
        try {
            obj.getClass().getMethod(str, String.class).invoke(obj, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void generateAdPalSession() {
        if (this.mConsentSettings == null || this.g == null) {
            LoggerManager.getInstance().printErrorLogs("BpkAdPalManager17.1.0", "Cannot generate a nonce, please set a ConsentSettings through AdManager.getInstance().setConsentSettings(...)");
        }
        try {
            Object objInvoke = this.d.getMethod("builder", new Class[0]).invoke(this.d, new Object[0]);
            a(objInvoke, "descriptionURL", this.h);
            a(objInvoke, "omidVersion", this.k);
            a(objInvoke, "omidPartnerName", this.i);
            a(objInvoke, "omidPartnerVersion", this.j);
            a(objInvoke, "playerType", this.l);
            a(objInvoke, "playerVersion", this.m);
            a(objInvoke, "ppid", this.n);
            a(objInvoke, "videoPlayerHeight", this.o);
            a(objInvoke, "videoPlayerWidth", this.p);
            a(objInvoke, "willAdAutoPlay", this.q);
            a(objInvoke, "willAdPlayMuted", this.r);
            Object objInvoke2 = objInvoke.getClass().getMethod("build", new Class[0]).invoke(objInvoke, new Object[0]);
            Object objInvoke3 = this.c.getMethod("loadNonceManager", this.d).invoke(this.g, objInvoke2);
            Object objNewProxyInstance = Proxy.newProxyInstance(this.e.getClassLoader(), new Class[]{this.e}, new InvocationHandler() { // from class: tv.broadpeak.smartlib.ad.pal.-$$Lambda$AdPalManager17_1_0$zGwHDftkxRFecXxHmnKI_aXFvIc
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    return this.f$0.a(obj, method, objArr);
                }
            });
            Object objNewProxyInstance2 = Proxy.newProxyInstance(this.f.getClassLoader(), new Class[]{this.f}, new InvocationHandler() { // from class: tv.broadpeak.smartlib.ad.pal.-$$Lambda$AdPalManager17_1_0$XMKyxmTOSSL4sjm_jxuYZPFNyB0
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    return this.f$0.b(obj, method, objArr);
                }
            });
            objInvoke3.getClass().getMethod("addOnSuccessListener", this.e).invoke(objInvoke3, objNewProxyInstance);
            objInvoke3.getClass().getMethod("addOnFailureListener", this.f).invoke(objInvoke3, objNewProxyInstance2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void registerPalManagerCallback(AdPalManager.AdPalManagerCallback adPalManagerCallback) {
        LoggerManager.getInstance().printDebugLogs("BpkAdPalManager17.1.0", "Registering nonce generator callback");
        this.s.add(adPalManagerCallback);
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void release() {
        this.s.clear();
        if (this.g != null) {
            try {
                this.c.getMethod("release", new Class[0]).invoke(this.g, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void reset() {
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void sendAdClick(Object obj) {
        if (obj != null) {
            try {
                obj.getClass().getMethod("sendAdClick", new Class[0]).invoke(obj, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void sendAdImpression(Object obj) {
        if (obj != null) {
            try {
                obj.getClass().getMethod("sendAdImpression", new Class[0]).invoke(obj, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void sendTouch(Object obj, MotionEvent motionEvent) {
        if (obj != null) {
            try {
                obj.getClass().getMethod("sendTouch", MotionEvent.class).invoke(obj, motionEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setConsentSettings(Object obj) {
        super.setConsentSettings(obj);
        try {
            if (this.g != null) {
                this.c.getMethod("release", new Class[0]).invoke(this.g, new Object[0]);
            }
            this.g = this.c.getConstructor(Context.class, this.b).newInstance(this.f184a, this.mConsentSettings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setDescriptionURL(String str) {
        this.h = str;
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setOmidPartner(String str, String str2) {
        this.i = str;
        this.j = str2;
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setOmidVersion(String str) {
        this.k = str;
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setPlayerType(String str) {
        this.l = str;
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setPlayerVersion(String str) {
        this.m = str;
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setPpid(String str) {
        this.n = str;
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setValue(String str, int i) {
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setValue(String str, String str2) {
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setValue(String str, String str2, String str3) {
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setValue(String str, boolean z) {
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setVideoPlayerHeight(int i) {
        this.o = Integer.valueOf(i);
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setVideoPlayerWidth(int i) {
        this.p = Integer.valueOf(i);
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setWillAdAutoPlay(boolean z) {
        this.q = Boolean.valueOf(z);
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setWillAdPlayMuted(boolean z) {
        this.r = Boolean.valueOf(z);
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void unregisterPalManagerCallback(AdPalManager.AdPalManagerCallback adPalManagerCallback) {
        LoggerManager.getInstance().printDebugLogs("BpkAdPalManager17.1.0", "Unregistering nonce generator callback");
        this.s.remove(adPalManagerCallback);
    }

    public final void a(Object obj, String str, Integer num) {
        if (num == null) {
            return;
        }
        try {
            obj.getClass().getMethod(str, Integer.class).invoke(obj, num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(Object obj, String str, Boolean bool) {
        if (bool == null) {
            return;
        }
        try {
            obj.getClass().getMethod(str, Boolean.class).invoke(obj, bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object a(Object obj, Method method, Object[] objArr) throws Throwable {
        if (!method.getName().equals("onSuccess")) {
            return null;
        }
        try {
            Object obj2 = objArr[0];
            String str = (String) obj2.getClass().getMethod("getNonce", new Class[0]).invoke(obj2, new Object[0]);
            Iterator<AdPalManager.AdPalManagerCallback> it = this.s.iterator();
            while (it.hasNext()) {
                it.next().onPalSessionGeneration(new AdPalSession(this, str, obj2));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
