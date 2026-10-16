package tv.broadpeak.smartlib.ad.pal;

import android.content.Context;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class AdPalManager16_3_4 extends AdPalManager {
    public static String TAG = "BpkAdPalManager16.3.4";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Class f183a;
    public Class b;
    public Object c;
    public Object d;

    public AdPalManager16_3_4(Context context) {
        super(context);
        try {
            Class<?> cls = Class.forName("com.google.ads.interactivemedia.pal.NonceGenerator");
            this.f183a = cls;
            this.c = cls.getConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            LoggerManager.getInstance().printErrorLogs(TAG, "Please add \"-keep class com.google.ads.interactivemedia.pal.** { *; }\" to your proguard rules.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object a(AdPalManager.AdPalManagerCallback adPalManagerCallback, Object obj, Method method, Object[] objArr) throws Throwable {
        if (method.getName().equals("onNonceGeneration")) {
            adPalManagerCallback.onPalSessionGeneration(new AdPalSession(this, (String) objArr[0]));
            return null;
        }
        if (!method.getName().equals("onNonceGenerationFailure")) {
            return null;
        }
        adPalManagerCallback.onPalSessionGenerationFailure((Exception) objArr[0]);
        return null;
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void generateAdPalSession() {
        try {
            this.c.getClass().getDeclaredMethod("generateNonce", new Class[0]).invoke(this.c, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void registerPalManagerCallback(final AdPalManager.AdPalManagerCallback adPalManagerCallback) {
        try {
            Class<?> cls = Class.forName("com.google.ads.interactivemedia.pal.NonceGenerator$NonceGeneratorCallback");
            this.b = cls;
            this.d = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{this.b}, new InvocationHandler() { // from class: tv.broadpeak.smartlib.ad.pal.-$$Lambda$AdPalManager16_3_4$4WLkrQGv_3MxS_2dwGTWVbrUeSE
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    return this.f$0.a(adPalManagerCallback, obj, method, objArr);
                }
            });
            this.c.getClass().getDeclaredMethod("registerNonceGeneratorCallback", this.b).invoke(this.c, this.d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void release() {
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void reset() {
        try {
            this.c.getClass().getDeclaredMethod("reset", new Class[0]).invoke(this.c, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setValue(String str, String str2) {
        try {
            this.c.getClass().getDeclaredMethod(str, String.class).invoke(this.c, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void unregisterPalManagerCallback(AdPalManager.AdPalManagerCallback adPalManagerCallback) {
        try {
            this.c.getClass().getDeclaredMethod("unregisterNonceGeneratorCallback", this.b).invoke(this.c, this.d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setValue(String str, String str2, String str3) {
        try {
            this.c.getClass().getDeclaredMethod(str, String.class, String.class).invoke(this.c, str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setValue(String str, int i) {
        try {
            this.c.getClass().getDeclaredMethod(str, Integer.TYPE).invoke(this.c, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // tv.broadpeak.smartlib.ad.pal.AdPalManager
    public void setValue(String str, boolean z) {
        try {
            this.c.getClass().getDeclaredMethod(str, Boolean.TYPE).invoke(this.c, Boolean.valueOf(z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
