package tv.broadpeak.smartlib.engine.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.google.android.exoplayer2.util.MimeTypes;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;

/* JADX INFO: loaded from: classes3.dex */
public class AppStateManager extends BroadcastReceiver implements LifecycleObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f195a;
    public CoreEngine b;
    public boolean c;
    public AudioManager d;
    public ContentObserver e;
    public int f;
    public Object g;
    public ScheduledFuture h;
    public ScheduledExecutorService i = Executors.newSingleThreadScheduledExecutor();
    public String j;

    public class a extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f196a;

        public a(int i) {
            this.f196a = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(int i, JSObject jSObject) {
            ((JSFunction) jSObject.getProperty("notifyNetworkAvailable").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{AppStateManager.this.b.getJSContext().createJSNumber(i)});
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            CoreEngine coreEngine = AppStateManager.this.b;
            final int i = this.f196a;
            coreEngine.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$AppStateManager$a$hkudIonRVBZNM_ZM2mTlULNom90
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    this.f$0.a(i, jSObject);
                }
            });
        }
    }

    public class b extends Thread {
        public b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AppStateManager.this.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$k9D5fMIJO_vmk6dSsk5QG62fCMQ
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    ((JSFunction) jSObject.getProperty("notifyNetworkLost").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
                }
            });
        }
    }

    public class c extends Thread {
        public c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AppStateManager.this.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$RjPl5RNSbuk2YJHJ2Am90QjFMb8
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    ((JSFunction) jSObject.getProperty("notifyNetworkLost").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
                }
            });
        }
    }

    public class d extends ConnectivityManager.NetworkCallback {
        public d() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            AppStateManager appStateManager = AppStateManager.this;
            appStateManager.onReceive(appStateManager.f195a, null);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            AppStateManager appStateManager = AppStateManager.this;
            appStateManager.onReceive(appStateManager.f195a, null);
        }
    }

    public class e extends ContentObserver {
        public e(Handler handler) {
            super(handler);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0027  */
        /* JADX WARN: Code duplicated, block: B:7:0x001f A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:8:0x0021  */
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AppStateManager appStateManager;
            super.onChange(z);
            int streamVolume = AppStateManager.this.d.getStreamVolume(3);
            if (streamVolume > 1) {
                AppStateManager appStateManager2 = AppStateManager.this;
                if (appStateManager2.f <= 1) {
                    appStateManager2.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$VUOVk8Aubs11oFyM-ZzfvkGGkqk
                        @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                        public final void run(JSObject jSObject) {
                            ((JSFunction) jSObject.getProperty("notifyUnmute").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
                        }
                    });
                } else if (streamVolume <= 1) {
                    appStateManager = AppStateManager.this;
                    if (appStateManager.f > 1) {
                        appStateManager.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$bskgGaugSoot3fkIOzOAesCR210
                            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                            public final void run(JSObject jSObject) {
                                ((JSFunction) jSObject.getProperty("notifyMute").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
                            }
                        });
                    }
                }
            } else if (streamVolume <= 1) {
                appStateManager = AppStateManager.this;
                if (appStateManager.f > 1) {
                    appStateManager.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$bskgGaugSoot3fkIOzOAesCR210
                        @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                        public final void run(JSObject jSObject) {
                            ((JSFunction) jSObject.getProperty("notifyMute").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
                        }
                    });
                }
            }
            AppStateManager.this.f = streamVolume;
        }
    }

    public AppStateManager(Context context, CoreEngine coreEngine) {
        if (context.getApplicationContext() != null) {
            this.f195a = context.getApplicationContext();
        } else {
            this.f195a = context;
        }
        this.b = coreEngine;
        this.c = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b() {
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
    }

    /* JADX WARN: Code duplicated, block: B:56:0x010e  */
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public void c() {
        String str;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f195a.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            int i = 0;
            if (activeNetworkInfo != null && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED && ((str = this.j) == null || !str.equals(activeNetworkInfo.getTypeName()))) {
                for (NetworkInfo networkInfo : connectivityManager.getAllNetworkInfo()) {
                    if (networkInfo != null) {
                        LoggerManager.getInstance().printDebugLogs("BpkAppStateMgr", networkInfo.getTypeName() + "=" + networkInfo.getState());
                    }
                }
                LoggerManager.getInstance().printDebugLogs("BpkAppStateMgr", "Active interface: " + activeNetworkInfo.getTypeName() + "=" + activeNetworkInfo.getState());
                this.j = activeNetworkInfo.getTypeName();
            }
            if (activeNetworkInfo == null) {
                if (this.j != null) {
                    NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                    int length = allNetworkInfo.length;
                    while (i < length) {
                        NetworkInfo networkInfo2 = allNetworkInfo[i];
                        if (networkInfo2 != null) {
                            LoggerManager.getInstance().printDebugLogs("BpkAppStateMgr", networkInfo2.getTypeName() + "=" + networkInfo2.getState());
                        }
                        i++;
                    }
                    this.j = null;
                }
                new c().start();
                return;
            }
            if (activeNetworkInfo.getState() != NetworkInfo.State.CONNECTED) {
                if (activeNetworkInfo.getState() == NetworkInfo.State.DISCONNECTED) {
                    if (this.j != null) {
                        NetworkInfo[] allNetworkInfo2 = connectivityManager.getAllNetworkInfo();
                        int length2 = allNetworkInfo2.length;
                        while (i < length2) {
                            NetworkInfo networkInfo3 = allNetworkInfo2[i];
                            if (networkInfo3 != null) {
                                LoggerManager.getInstance().printDebugLogs("BpkAppStateMgr", networkInfo3.getTypeName() + "=" + networkInfo3.getState());
                            }
                            i++;
                        }
                        this.j = null;
                    }
                    new b().start();
                    return;
                }
                return;
            }
            NetworkInfo activeNetworkInfo2 = ((ConnectivityManager) this.f195a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo2 == null || activeNetworkInfo2.getState() != NetworkInfo.State.DISCONNECTED) {
                if (activeNetworkInfo2 == null || activeNetworkInfo2.getState() != NetworkInfo.State.CONNECTED) {
                    i = 99;
                } else if (activeNetworkInfo2.getType() == 1) {
                    WifiInfo connectionInfo = ((WifiManager) this.f195a.getSystemService("wifi")).getConnectionInfo();
                    if (connectionInfo == null || Build.VERSION.SDK_INT < 21) {
                        i = 10;
                    } else {
                        int frequency = connectionInfo.getFrequency();
                        if (frequency > 0 && frequency < 5000) {
                            i = 11;
                        } else if (frequency > 5000) {
                            i = 12;
                        } else {
                            i = 1;
                        }
                    }
                } else if (activeNetworkInfo2.getType() == 0) {
                    switch (activeNetworkInfo2.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                        case 16:
                            i = 21;
                            break;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                        case 17:
                            i = 22;
                            break;
                        case 13:
                        case 18:
                        case 19:
                            i = 23;
                            break;
                        case 20:
                            i = 24;
                            break;
                        default:
                            i = 20;
                            break;
                    }
                } else if (activeNetworkInfo2.getType() == 9) {
                    i = 30;
                } else {
                    i = 1;
                }
            }
            new a(i).start();
        }
    }

    public void init() {
        LoggerManager.getInstance().printDebugLogs("BpkAppStateMgr", "Initializing target app state manager...");
        if (this.c) {
            return;
        }
        this.g = null;
        registerNetworkObserver();
        registerVolumeObserver();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$AppStateManager$H9q72ERu0z4d42h6CH0vy0SDARM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a();
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f195a.getSystemService("connectivity");
        if (connectivityManager != null) {
            for (NetworkInfo networkInfo : connectivityManager.getAllNetworkInfo()) {
                if (networkInfo != null) {
                    LoggerManager.getInstance().printDebugLogs("BpkAppStateMgr", networkInfo.getTypeName() + "=" + networkInfo.getState());
                }
            }
        }
        this.c = true;
        this.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$UCf5s_81Axjx0n5UM18lHNl3I7k
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("bind").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMoveToBackground() {
        pauseNetworkObserver();
        this.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$ukFPCY7mR6StdUcwqlG2jyjbCVk
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("notifyBackground").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
        resumeNetworkObserver();
        this.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$-INEoSHEiJjeeKvjBaYJTQ9IR7s
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("notifyForeground").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
            }
        });
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        pauseNetworkObserver();
        c();
        resumeNetworkObserver();
    }

    public void pauseNetworkObserver() {
        ScheduledFuture scheduledFuture = this.h;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.h = null;
        }
    }

    public void registerNetworkObserver() {
        c();
        resumeNetworkObserver();
        if (Build.VERSION.SDK_INT >= 21) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f195a.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                this.g = new d();
                connectivityManager.registerNetworkCallback(builder.build(), (ConnectivityManager.NetworkCallback) this.g);
                return;
            }
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Context context = this.f195a;
        if (context != null) {
            context.registerReceiver(this, intentFilter);
        }
    }

    public void registerVolumeObserver() {
        AudioManager audioManager = (AudioManager) this.f195a.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.d = audioManager;
        int streamVolume = audioManager.getStreamVolume(3);
        if (streamVolume <= 1) {
            this.b.getSingleton("AppStateManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$zr9UzGFtMW7eWGHFEdpOdZnwVcs
                @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
                public final void run(JSObject jSObject) {
                    ((JSFunction) jSObject.getProperty("notifyMute").cast(JSFunction.class)).invoke(jSObject, new JSValue[0]);
                }
            });
        }
        this.f = streamVolume;
        this.e = new e(new Handler(Looper.getMainLooper()));
        this.f195a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.e);
    }

    public void release() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$AppStateManager$PH98zi3Yqk7i5wnLU3hKl2DmxAI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b();
            }
        });
        unregisterNetworkObserver();
        unregisterVolumeObserver();
        this.c = false;
    }

    public void resumeNetworkObserver() {
        pauseNetworkObserver();
        this.h = this.i.scheduleAtFixedRate(new Runnable() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$AppStateManager$ZydqEFBUfVp09_q3vLQsGyWCcM8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c();
            }
        }, 0L, 5, TimeUnit.SECONDS);
    }

    public void unregisterNetworkObserver() {
        ConnectivityManager connectivityManager;
        Context context = this.f195a;
        if (context != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.g != null && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
                    connectivityManager.unregisterNetworkCallback((ConnectivityManager.NetworkCallback) this.g);
                }
                this.g = null;
            } else {
                context.unregisterReceiver(this);
            }
        }
        pauseNetworkObserver();
    }

    public void unregisterVolumeObserver() {
        this.f195a.getContentResolver().unregisterContentObserver(this.e);
    }
}
