package tv.broadpeak.smartlib.session.streaming;

import android.view.MotionEvent;
import android.view.View;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.smartlib.ad.AdFriendlyObstructionPurpose;
import tv.broadpeak.smartlib.ad.AdManager;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.CoreMemory;
import tv.broadpeak.smartlib.engine.executor.CoreExecutor;
import tv.broadpeak.smartlib.engine.player.PlayerAdapterHandler;

/* JADX INFO: loaded from: classes3.dex */
public class AbstractSession {
    public static String TAG = "BpkAbstractSession";
    public CoreExecutor mCoreExecutor = CoreEngine.getInstance().getCoreExecutor();
    public JSContext mJSContext = CoreEngine.getInstance().getJSContext();
    public JSObject mObject;
    public PlayerAdapterHandler mPlayerAdapter;

    public AbstractSession(JSObject jSObject) {
        this.mObject = jSObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(PlayerAdapterHandler playerAdapterHandler) {
        ((JSFunction) this.mObject.getProperty("attachPlayer").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{playerAdapterHandler != null ? playerAdapterHandler.getJSPlayerAdapter() : this.mJSContext.createJSUndefined(), this.mJSContext.createJSUndefined()});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        ((JSFunction) this.mObject.getProperty("unregisterFriendlyObstruction").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSNumber(CoreMemory.getInstance().register(view))});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(String str) {
        ((JSFunction) this.mObject.getProperty("setAdCustomReference").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(String str) {
        ((JSFunction) this.mObject.getProperty("setAdViewState").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str)});
    }

    public void activateAdvertising() {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$19h7R3zDkSe3GPoxo_Rjyb2oqsk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a();
            }
        });
    }

    public void adTouch(final MotionEvent motionEvent) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$DBqOLvMb3SZv-D857O6B4wAgCVA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(motionEvent);
            }
        });
    }

    public void adUserInteraction(final String str) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$1m1LuMTHurqyswvMf7D5K4KWfgg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(str);
            }
        });
    }

    public void attachPlayer(Object obj) {
        attachPlayer(obj, null);
    }

    public Object getListener() {
        return this.mPlayerAdapter;
    }

    public void registerAdFriendlyObstructionView(final View view, final AdFriendlyObstructionPurpose adFriendlyObstructionPurpose, final String str) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$PYiIak-5y-kW4uhllbqgAA6-hnA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(view, adFriendlyObstructionPurpose, str);
            }
        });
    }

    public void registerAdVerificationData(final String str) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$G3qSf96ewXIe7EsJ4uqRh3Dqmi4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(str);
            }
        });
    }

    public void setAdCustomReference(final String str) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$4yDCskopq3LIvKf4hqmspH8LIEU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c(str);
            }
        });
    }

    public void setAdEventsListener(final AdManager.AdEventsListener adEventsListener) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$P89y7Y8FNrK2FMWoexRqGujNQsc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(adEventsListener);
            }
        });
    }

    public void setAdParameter(final String str, final String str2) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$ybmjNyXjOGjh_3VbBWtt1Wls8v8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(str, str2);
            }
        });
    }

    public void setAdView(final View view) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$mvp4epxjS2aQ1U19U8XCENDIxeE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(view);
            }
        });
    }

    public void setAdViewState(final String str) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$Ap06nCvdvSvIZJjpBVZXq0moYng
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.d(str);
            }
        });
    }

    public void setCustomParameter(final String str, final String str2) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$kA-7ert6lcmISeyx--GIi7R1v38
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(str, str2);
            }
        });
    }

    public void setEventCallbackEnabled(final boolean z) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$SpAHepn8iMvuT3ocwbJpobJrpZw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(z);
            }
        });
    }

    public void setOption(final int i, final boolean z) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$zxLb1-_clcCkDITafshnxh7sraA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, z);
            }
        });
    }

    public void unregisterAllFriendlyObstructions() {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$Vct11JWJ26UAuzEwsMT3jmUPWbk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b();
            }
        });
    }

    public void unregisterFriendlyObstruction(final View view) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$_rdCV32_DoIawEHyLJIdsHVR6H4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(view);
            }
        });
    }

    public void attachPlayer(Object obj, Object obj2) {
        final PlayerAdapterHandler playerAdapterHandlerAttachPlayer = CoreEngine.getInstance().getPlayerManager().attachPlayer(obj, obj2);
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$Rn5QVIltNvLFMazDqulJbHvjX_E
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(playerAdapterHandlerAttachPlayer);
            }
        });
        PlayerAdapterHandler playerAdapterHandler = this.mPlayerAdapter;
        if (playerAdapterHandler != null) {
            playerAdapterHandler.detachPlayerOnSameThread();
        }
        this.mPlayerAdapter = playerAdapterHandlerAttachPlayer;
    }

    public void registerAdVerificationData(final String str, final String str2, final String str3) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$ROWzUF5h8nUUQncXIX54B9mLBFU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(str, str2, str3);
            }
        });
    }

    public void setOption(final int i, final int i2) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$6KXd0jWnETkJ3qj8BHWqMEhOpIU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, i2);
            }
        });
    }

    public void setOption(final int i, final String str) {
        this.mCoreExecutor.execute(new Runnable() { // from class: tv.broadpeak.smartlib.session.streaming.-$$Lambda$AbstractSession$lV0w6HRPnfR64q2_hSVSW6x6GpQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, boolean z) {
        ((JSFunction) this.mObject.getProperty("setOption").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSNumber(i), this.mJSContext.createJSBoolean(z)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b() {
        ((JSFunction) this.mObject.getProperty("unregisterAllFriendlyObstructions").cast(JSFunction.class)).invoke(this.mObject, new JSValue[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, int i2) {
        ((JSFunction) this.mObject.getProperty("setOption").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSNumber(i), this.mJSContext.createJSNumber(i2)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str) {
        ((JSFunction) this.mObject.getProperty("registerAdVerificationData").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, String str) {
        ((JSFunction) this.mObject.getProperty("setOption").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSNumber(i), this.mJSContext.createJSString(str)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, String str2) {
        ((JSFunction) this.mObject.getProperty("setCustomParameter").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str), this.mJSContext.createJSString(str2)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AdManager.AdEventsListener adEventsListener) {
        JSObject jSObjectCreateJSObject = this.mJSContext.createJSObject();
        try {
            JSContext jSContext = this.mJSContext;
            Class<?> cls = adEventsListener.getClass();
            Class<?> cls2 = Long.TYPE;
            jSObjectCreateJSObject.setProperty("onAdBreakBegin", jSContext.createJSFunction(adEventsListener, Method.create(Void.class, cls.getMethod("onAdBreakBegin", cls2, cls2))));
            jSObjectCreateJSObject.setProperty("onAdBegin", this.mJSContext.createJSFunction(adEventsListener, Method.create(Void.class, adEventsListener.getClass().getMethod("onAdBegin", cls2, cls2, String.class))));
            jSObjectCreateJSObject.setProperty("onAdSkippable", this.mJSContext.createJSFunction(adEventsListener, Method.create(Void.class, adEventsListener.getClass().getMethod("onAdSkippable", cls2, cls2))));
            jSObjectCreateJSObject.setProperty("onAdEnd", this.mJSContext.createJSFunction(adEventsListener, Method.create(Void.class, adEventsListener.getClass().getMethod("onAdEnd", new Class[0]))));
            jSObjectCreateJSObject.setProperty("onAdBreakEnd", this.mJSContext.createJSFunction(adEventsListener, Method.create(Void.class, adEventsListener.getClass().getMethod("onAdBreakEnd", new Class[0]))));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        ((JSFunction) this.mObject.getProperty("setAdEventsListener").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{jSObjectCreateJSObject});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        ((JSFunction) this.mObject.getProperty("activateAdvertising").cast(JSFunction.class)).invoke(this.mObject, new JSValue[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2) {
        ((JSFunction) this.mObject.getProperty("setAdParameter").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str), this.mJSContext.createJSString(str2)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        ((JSFunction) this.mObject.getProperty("setAdView").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSNumber(CoreMemory.getInstance().register(view))});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(View view, AdFriendlyObstructionPurpose adFriendlyObstructionPurpose, String str) {
        ((JSFunction) this.mObject.getProperty("registerAdFriendlyObstructionView").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSNumber(CoreMemory.getInstance().register(view)), this.mJSContext.createJSNumber(adFriendlyObstructionPurpose.ordinal()), this.mJSContext.createJSString(str)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2, String str3) {
        ((JSFunction) this.mObject.getProperty("registerAdVerificationData").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str), this.mJSContext.createJSString(str2), this.mJSContext.createJSString(str3)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str) {
        ((JSFunction) this.mObject.getProperty("adUserInteraction").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSString(str)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(MotionEvent motionEvent) {
        ((JSFunction) this.mObject.getProperty("adTouch").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSNumber(CoreMemory.getInstance().register(motionEvent))});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(boolean z) {
        ((JSFunction) this.mObject.getProperty("setEventCallbackEnabled").cast(JSFunction.class)).invoke(this.mObject, new JSValue[]{this.mJSContext.createJSBoolean(z)});
    }
}
