package tv.broadpeak.smartlib.engine.player;

import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import java.util.HashMap;
import java.util.Iterator;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerManagerHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public JSContext f206a;
    public HashMap<String, Class> b = new HashMap<>();
    public JSObject c;

    public PlayerManagerHandler(JSContext jSContext) {
        this.f206a = jSContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(PlayerAdapterHandler playerAdapterHandler, Object obj, Object obj2) {
        playerAdapterHandler.attachPlayer(QuickJSUtils.create(this.f206a, "smartlib.PlayerAdapter"), obj, obj2);
    }

    public PlayerAdapterHandler attachPlayer(final Object obj, final Object obj2) {
        Iterator<Class> it = this.b.values().iterator();
        while (it.hasNext()) {
            try {
                final PlayerAdapterHandler playerAdapterHandler = (PlayerAdapterHandler) it.next().getConstructor(new Class[0]).newInstance(new Object[0]);
                if (playerAdapterHandler.checkPlayer(obj, obj2)) {
                    CoreEngine.getInstance().getCoreExecutor().execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.player.-$$Lambda$PlayerManagerHandler$CBl0MWsmGSjmbNFY2cxnq9vJYng
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.a(playerAdapterHandler, obj, obj2);
                        }
                    });
                    playerAdapterHandler.attachPlayerOnSameThread(obj, obj2);
                    return playerAdapterHandler;
                }
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (obj != null) {
            LoggerManager.getInstance().printWarnLogs("BpkPlayerManagerHandler", "Player type: " + obj.getClass().getName());
        } else {
            LoggerManager.getInstance().printWarnLogs("BpkPlayerManagerHandler", "Player type: null");
        }
        if (obj2 == null) {
            LoggerManager.getInstance().printWarnLogs("BpkPlayerManagerHandler", "Listener type: null");
            return null;
        }
        LoggerManager.getInstance().printWarnLogs("BpkPlayerManagerHandler", "Listener type: " + obj2.getClass().getName());
        return null;
    }

    public JSObject createJSObject() {
        JSObject jSObjectCreateJSObject = this.f206a.createJSObject();
        try {
            jSObjectCreateJSObject.setProperty("loadPlayerAdapters", this.f206a.createJSFunction(this, Method.create(JSValue.class, PlayerManagerHandler.class.getMethod("loadPlayerAdapters", new Class[0]))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSObjectCreateJSObject;
    }

    public JSValue loadPlayerAdapters() {
        this.c = this.f206a.createJSObject();
        try {
            this.b.put("exoplayer", Class.forName("tv.broadpeak.smartlib.player.ExoPlayerAdapter"));
            this.c.setProperty("exoplayer", this.f206a.createJSString("exoplayer"));
        } catch (Exception unused) {
        }
        try {
            this.b.put("exoplayersupport", Class.forName("tv.broadpeak.smartlib.player.ExoPlayerAdapterSupport"));
            this.c.setProperty("exoplayersupport", this.f206a.createJSString("exoplayersupport"));
        } catch (Exception unused2) {
        }
        try {
            this.b.put("exoplayersupport-2.12.0", Class.forName("tv.broadpeak.smartlib.player.ExoPlayerAdapterSupport2_12_0"));
            this.c.setProperty("exoplayersupport-2.12.0", this.f206a.createJSString("exoplayersupport-2.12.0"));
        } catch (Exception unused3) {
        }
        try {
            this.b.put("generic", Class.forName("tv.broadpeak.smartlib.player.GenericPlayerAdapter"));
            this.c.setProperty("generic", this.f206a.createJSString("generic"));
        } catch (Exception unused4) {
        }
        try {
            this.b.put("labgency", Class.forName("tv.broadpeak.smartlib.player.LabgencyPlayerAdapter"));
            this.c.setProperty("labgency", this.f206a.createJSString("labgency"));
        } catch (Exception unused5) {
        }
        try {
            this.b.put("visualon", Class.forName("tv.broadpeak.smartlib.player.VisualOnPlayerAdapter"));
            this.c.setProperty("visualon", this.f206a.createJSString("visualon"));
        } catch (Exception unused6) {
        }
        try {
            this.b.put("voplayer", Class.forName("tv.broadpeak.smartlib.player.VOPlayerAdapter"));
            this.c.setProperty("voplayer", this.f206a.createJSString("voplayer"));
        } catch (Exception unused7) {
        }
        try {
            this.b.put("kaltura", Class.forName("tv.broadpeak.smartlib.player.KalturaPlayerAdapter"));
            this.c.setProperty("kaltura", this.f206a.createJSString("kaltura"));
        } catch (Exception unused8) {
        }
        try {
            this.b.put("connectplayer", Class.forName("tv.broadpeak.smartlib.player.ConnectPlayerAdapter"));
            this.c.setProperty("connectplayer", this.f206a.createJSString("connectplayer"));
        } catch (Exception unused9) {
        }
        try {
            this.b.put("theoplayer", Class.forName("tv.broadpeak.smartlib.player.THEOPlayerAdapter"));
            this.c.setProperty("theoplayer", this.f206a.createJSString("theoplayer"));
        } catch (Exception unused10) {
        }
        return this.c;
    }
}
