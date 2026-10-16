package tv.broadpeak.smartlib.engine.manager;

import android.content.Context;
import android.net.wifi.WifiManager;
import java.io.IOException;
import java.net.InetAddress;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

/* JADX INFO: loaded from: classes3.dex */
public class InternalMdnsManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f201a;
    public String b;
    public DiscoveryListener c;
    public InternalMdnsRunner d;

    public interface DiscoveryListener {
        void onDiscoveryStarted(String str);

        void onDiscoveryStopped(String str);

        void onServiceFound(ServiceInfo serviceInfo);

        void onServiceLost(ServiceInfo serviceInfo);

        void onStartDiscoveryFailed(String str, int i);

        void onStopDiscoveryFailed(String str, int i);
    }

    public static class InternalMdnsRunner extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f202a = true;
        public Context b;
        public String c;
        public WifiManager.MulticastLock d;
        public JmDNS e;
        public DiscoveryListener f;
        public ServiceListener g;

        public class a implements ServiceListener {
            public a() {
            }

            @Override // javax.jmdns.ServiceListener
            public void serviceAdded(ServiceEvent serviceEvent) {
            }

            @Override // javax.jmdns.ServiceListener
            public void serviceRemoved(ServiceEvent serviceEvent) {
                InternalMdnsRunner.this.f.onServiceLost(serviceEvent.getInfo());
            }

            @Override // javax.jmdns.ServiceListener
            public void serviceResolved(ServiceEvent serviceEvent) {
                InternalMdnsRunner.this.f.onServiceFound(serviceEvent.getInfo());
            }
        }

        public InternalMdnsRunner(Context context, String str, DiscoveryListener discoveryListener) {
            this.b = context;
            this.c = str;
            this.f = discoveryListener;
        }

        public final void a() {
            JmDNS jmDNS = this.e;
            if (jmDNS != null) {
                jmDNS.unregisterAllServices();
                ServiceListener serviceListener = this.g;
                if (serviceListener != null) {
                    this.e.removeServiceListener(this.c, serviceListener);
                }
                try {
                    this.e.close();
                } catch (IOException unused) {
                    this.f.onStopDiscoveryFailed(this.c, 0);
                }
            }
            WifiManager.MulticastLock multicastLock = this.d;
            if (multicastLock != null) {
                multicastLock.release();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            WifiManager.MulticastLock multicastLockCreateMulticastLock = ((WifiManager) this.b.getApplicationContext().getSystemService("wifi")).createMulticastLock("SmartLib.DNSLock");
            this.d = multicastLockCreateMulticastLock;
            multicastLockCreateMulticastLock.setReferenceCounted(true);
            this.d.acquire();
            try {
                LoggerManager.getInstance().printDebugLogs("BpkInternalMdnsManager", "Discovery runner started " + hashCode());
                for (int i = 0; i < 20; i++) {
                    try {
                        this.e = JmDNS.create(InetAddress.getByName("0.0.0.0"), "");
                        break;
                    } catch (IOException unused) {
                        try {
                            Thread.sleep(50L);
                        } catch (Exception unused2) {
                        }
                    }
                }
                if (this.e == null) {
                    LoggerManager.getInstance().printDebugLogs("BpkInternalMdnsManager", "Discovery runner start failed " + hashCode());
                    a();
                    stopRunner();
                    this.f.onStartDiscoveryFailed(this.c, 0);
                    return;
                }
                if (!this.f202a) {
                    a();
                    return;
                }
                this.f.onDiscoveryStarted(this.c);
                a aVar = new a();
                this.g = aVar;
                this.e.addServiceListener(this.c, aVar);
                while (this.f202a) {
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception unused3) {
                    }
                }
                a();
                LoggerManager.getInstance().printDebugLogs("BpkInternalMdnsManager", "Discovery runner stopped " + hashCode());
                this.f.onDiscoveryStopped(this.c);
            } catch (Exception unused4) {
                a();
            }
        }

        public void startRunner() {
            start();
        }

        public void stopRunner() {
            this.f202a = false;
        }
    }

    public InternalMdnsManager(Context context, String str) {
        this.f201a = context;
        this.b = str;
    }

    public void discoverServices(DiscoveryListener discoveryListener) {
        if (this.c == null) {
            this.c = discoveryListener;
            InternalMdnsRunner internalMdnsRunner = this.d;
            if (internalMdnsRunner != null) {
                internalMdnsRunner.stopRunner();
            }
            InternalMdnsRunner internalMdnsRunner2 = new InternalMdnsRunner(this.f201a, this.b, discoveryListener);
            this.d = internalMdnsRunner2;
            internalMdnsRunner2.startRunner();
        }
    }

    public void stopServiceDiscovery(DiscoveryListener discoveryListener) {
        InternalMdnsRunner internalMdnsRunner = this.d;
        if (internalMdnsRunner != null) {
            internalMdnsRunner.stopRunner();
            this.d.interrupt();
        }
    }
}
