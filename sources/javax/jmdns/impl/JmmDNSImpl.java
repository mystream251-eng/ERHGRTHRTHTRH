package javax.jmdns.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.jmdns.JmDNS;
import javax.jmdns.JmmDNS;
import javax.jmdns.NetworkTopologyDiscovery;
import javax.jmdns.NetworkTopologyEvent;
import javax.jmdns.NetworkTopologyListener;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
public class JmmDNSImpl implements JmmDNS, NetworkTopologyListener, ServiceInfoImpl.Delegate {
    private static Logger logger = LoggerFactory.getLogger(JmmDNSImpl.class.getName());
    private final AtomicBoolean _closed;
    private final AtomicBoolean _isClosing;
    private final Set<NetworkTopologyListener> _networkListeners = Collections.synchronizedSet(new HashSet());
    private final ConcurrentMap<InetAddress, JmDNS> _knownMDNS = new ConcurrentHashMap();
    private final ConcurrentMap<String, ServiceInfo> _services = new ConcurrentHashMap(20);
    private final ExecutorService _listenerExecutor = Executors.newSingleThreadExecutor(new NamedThreadFactory("JmmDNS Listeners"));
    private final ExecutorService _jmDNSExecutor = Executors.newCachedThreadPool(new NamedThreadFactory("JmmDNS"));
    private final Timer _timer = new Timer("Multihomed mDNS.Timer", true);
    private final ConcurrentMap<String, List<ServiceListener>> _serviceListeners = new ConcurrentHashMap();
    private final Set<ServiceTypeListener> _typeListeners = Collections.synchronizedSet(new HashSet());
    private final Set<String> _serviceTypes = Collections.synchronizedSet(new HashSet());

    public JmmDNSImpl() {
        new NetworkChecker(this, NetworkTopologyDiscovery.Factory.getInstance()).start(this._timer);
        this._isClosing = new AtomicBoolean(false);
        this._closed = new AtomicBoolean(false);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this._isClosing.compareAndSet(false, true)) {
            logger.debug("Cancelling JmmDNS: {}", this);
            this._timer.cancel();
            this._listenerExecutor.shutdown();
            this._jmDNSExecutor.shutdown();
            ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool(new NamedThreadFactory("JmmDNS.close"));
            try {
                for (final JmDNS jmDNS : getDNS()) {
                    executorServiceNewCachedThreadPool.submit(new Runnable() { // from class: javax.jmdns.impl.JmmDNSImpl.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                jmDNS.close();
                            } catch (IOException unused) {
                            }
                        }
                    });
                }
                executorServiceNewCachedThreadPool.shutdown();
                try {
                    executorServiceNewCachedThreadPool.awaitTermination(5000L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    logger.warn("Exception ", (Throwable) e);
                }
                this._knownMDNS.clear();
                this._services.clear();
                this._serviceListeners.clear();
                this._typeListeners.clear();
                this._serviceTypes.clear();
                this._closed.set(true);
                JmmDNS.Factory.close();
            } catch (Throwable th) {
                executorServiceNewCachedThreadPool.shutdown();
                throw th;
            }
        }
    }

    @Override // javax.jmdns.JmmDNS
    public String[] getNames() {
        HashSet hashSet = new HashSet();
        for (JmDNS jmDNS : getDNS()) {
            hashSet.add(jmDNS.getName());
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    @Override // javax.jmdns.JmmDNS
    public String[] getHostNames() {
        HashSet hashSet = new HashSet();
        for (JmDNS jmDNS : getDNS()) {
            hashSet.add(jmDNS.getHostName());
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    @Override // javax.jmdns.JmmDNS
    public InetAddress[] getInetAddresses() throws IOException {
        HashSet hashSet = new HashSet();
        for (JmDNS jmDNS : getDNS()) {
            hashSet.add(jmDNS.getInetAddress());
        }
        return (InetAddress[]) hashSet.toArray(new InetAddress[hashSet.size()]);
    }

    @Override // javax.jmdns.JmmDNS
    public JmDNS[] getDNS() {
        JmDNS[] jmDNSArr;
        synchronized (this._knownMDNS) {
            jmDNSArr = (JmDNS[]) this._knownMDNS.values().toArray(new JmDNS[this._knownMDNS.size()]);
        }
        return jmDNSArr;
    }

    @Override // javax.jmdns.JmmDNS
    @Deprecated
    public InetAddress[] getInterfaces() throws IOException {
        HashSet hashSet = new HashSet();
        for (JmDNS jmDNS : getDNS()) {
            hashSet.add(jmDNS.getInterface());
        }
        return (InetAddress[]) hashSet.toArray(new InetAddress[hashSet.size()]);
    }

    @Override // javax.jmdns.JmmDNS
    public ServiceInfo[] getServiceInfos(String str, String str2) {
        return getServiceInfos(str, str2, false, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    @Override // javax.jmdns.JmmDNS
    public ServiceInfo[] getServiceInfos(String str, String str2, long j) {
        return getServiceInfos(str, str2, false, j);
    }

    @Override // javax.jmdns.JmmDNS
    public ServiceInfo[] getServiceInfos(String str, String str2, boolean z) {
        return getServiceInfos(str, str2, z, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    @Override // javax.jmdns.JmmDNS
    public ServiceInfo[] getServiceInfos(final String str, final String str2, final boolean z, final long j) {
        JmDNS[] dns = getDNS();
        HashSet hashSet = new HashSet(dns.length);
        if (dns.length > 0) {
            ArrayList arrayList = new ArrayList(dns.length);
            for (final JmDNS jmDNS : dns) {
                arrayList.add(new Callable<ServiceInfo>() { // from class: javax.jmdns.impl.JmmDNSImpl.2
                    @Override // java.util.concurrent.Callable
                    public ServiceInfo call() throws Exception {
                        return jmDNS.getServiceInfo(str, str2, z, j);
                    }
                });
            }
            ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(arrayList.size(), new NamedThreadFactory("JmmDNS.getServiceInfos"));
            try {
                List<Future> listEmptyList = Collections.emptyList();
                try {
                    listEmptyList = executorServiceNewFixedThreadPool.invokeAll(arrayList, j + 100, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    logger.debug("Interrupted ", (Throwable) e);
                    Thread.currentThread().interrupt();
                }
                for (Future future : listEmptyList) {
                    if (!future.isCancelled()) {
                        try {
                            try {
                                ServiceInfo serviceInfo = (ServiceInfo) future.get();
                                if (serviceInfo != null) {
                                    hashSet.add(serviceInfo);
                                }
                            } catch (ExecutionException e2) {
                                logger.warn("Exception ", (Throwable) e2);
                            }
                        } catch (InterruptedException e3) {
                            logger.debug("Interrupted ", (Throwable) e3);
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                executorServiceNewFixedThreadPool.shutdown();
            } catch (Throwable th) {
                executorServiceNewFixedThreadPool.shutdown();
                throw th;
            }
        }
        return (ServiceInfo[]) hashSet.toArray(new ServiceInfo[hashSet.size()]);
    }

    @Override // javax.jmdns.JmmDNS
    public void requestServiceInfo(String str, String str2) {
        requestServiceInfo(str, str2, false, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    @Override // javax.jmdns.JmmDNS
    public void requestServiceInfo(String str, String str2, boolean z) {
        requestServiceInfo(str, str2, z, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    @Override // javax.jmdns.JmmDNS
    public void requestServiceInfo(String str, String str2, long j) {
        requestServiceInfo(str, str2, false, j);
    }

    @Override // javax.jmdns.JmmDNS
    public void requestServiceInfo(final String str, final String str2, final boolean z, final long j) {
        for (final JmDNS jmDNS : getDNS()) {
            this._jmDNSExecutor.submit(new Runnable() { // from class: javax.jmdns.impl.JmmDNSImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    jmDNS.requestServiceInfo(str, str2, z, j);
                }
            });
        }
    }

    @Override // javax.jmdns.JmmDNS
    public void addServiceTypeListener(ServiceTypeListener serviceTypeListener) throws IOException {
        this._typeListeners.add(serviceTypeListener);
        for (JmDNS jmDNS : getDNS()) {
            jmDNS.addServiceTypeListener(serviceTypeListener);
        }
    }

    @Override // javax.jmdns.JmmDNS
    public void removeServiceTypeListener(ServiceTypeListener serviceTypeListener) {
        this._typeListeners.remove(serviceTypeListener);
        for (JmDNS jmDNS : getDNS()) {
            jmDNS.removeServiceTypeListener(serviceTypeListener);
        }
    }

    @Override // javax.jmdns.JmmDNS
    public void addServiceListener(String str, ServiceListener serviceListener) {
        String lowerCase = str.toLowerCase();
        List<ServiceListener> list = this._serviceListeners.get(lowerCase);
        if (list == null) {
            this._serviceListeners.putIfAbsent(lowerCase, new LinkedList());
            list = this._serviceListeners.get(lowerCase);
        }
        if (list != null) {
            synchronized (list) {
                if (!list.contains(serviceListener)) {
                    list.add(serviceListener);
                }
            }
        }
        for (JmDNS jmDNS : getDNS()) {
            jmDNS.addServiceListener(str, serviceListener);
        }
    }

    @Override // javax.jmdns.JmmDNS
    public void removeServiceListener(String str, ServiceListener serviceListener) {
        String lowerCase = str.toLowerCase();
        List<ServiceListener> list = this._serviceListeners.get(lowerCase);
        if (list != null) {
            synchronized (list) {
                list.remove(serviceListener);
                if (list.isEmpty()) {
                    this._serviceListeners.remove(lowerCase, list);
                }
            }
        }
        for (JmDNS jmDNS : getDNS()) {
            jmDNS.removeServiceListener(str, serviceListener);
        }
    }

    @Override // javax.jmdns.impl.ServiceInfoImpl.Delegate
    public void textValueUpdated(ServiceInfo serviceInfo, byte[] bArr) {
        JmDNS[] dns = getDNS();
        synchronized (this._services) {
            for (JmDNS jmDNS : dns) {
                ServiceInfo serviceInfo2 = ((JmDNSImpl) jmDNS).getServices().get(serviceInfo.getQualifiedName());
                if (serviceInfo2 != null) {
                    serviceInfo2.setText(bArr);
                } else {
                    logger.warn("We have a mDNS that does not know about the service info being updated.");
                }
            }
        }
    }

    @Override // javax.jmdns.JmmDNS
    public void registerService(ServiceInfo serviceInfo) throws IOException {
        JmDNS[] dns = getDNS();
        synchronized (this._services) {
            for (JmDNS jmDNS : dns) {
                jmDNS.registerService(serviceInfo.clone());
            }
            ((ServiceInfoImpl) serviceInfo).setDelegate(this);
            this._services.put(serviceInfo.getQualifiedName(), serviceInfo);
        }
    }

    @Override // javax.jmdns.JmmDNS
    public void unregisterService(ServiceInfo serviceInfo) {
        JmDNS[] dns = getDNS();
        synchronized (this._services) {
            this._services.remove(serviceInfo.getQualifiedName());
            for (JmDNS jmDNS : dns) {
                jmDNS.unregisterService(serviceInfo);
            }
            ((ServiceInfoImpl) serviceInfo).setDelegate(null);
        }
    }

    @Override // javax.jmdns.JmmDNS
    public void unregisterAllServices() {
        JmDNS[] dns = getDNS();
        synchronized (this._services) {
            this._services.clear();
            for (JmDNS jmDNS : dns) {
                jmDNS.unregisterAllServices();
            }
        }
    }

    @Override // javax.jmdns.JmmDNS
    public void registerServiceType(String str) {
        this._serviceTypes.add(str);
        for (JmDNS jmDNS : getDNS()) {
            jmDNS.registerServiceType(str);
        }
    }

    @Override // javax.jmdns.JmmDNS
    public ServiceInfo[] list(String str) {
        return list(str, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    @Override // javax.jmdns.JmmDNS
    public ServiceInfo[] list(final String str, final long j) {
        JmDNS[] dns = getDNS();
        HashSet hashSet = new HashSet(dns.length * 5);
        if (dns.length > 0) {
            ArrayList arrayList = new ArrayList(dns.length);
            for (final JmDNS jmDNS : dns) {
                arrayList.add(new Callable<List<ServiceInfo>>() { // from class: javax.jmdns.impl.JmmDNSImpl.4
                    @Override // java.util.concurrent.Callable
                    public List<ServiceInfo> call() throws Exception {
                        return Arrays.asList(jmDNS.list(str, j));
                    }
                });
            }
            ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(arrayList.size(), new NamedThreadFactory("JmmDNS.list"));
            try {
                List<Future> listEmptyList = Collections.emptyList();
                try {
                    listEmptyList = executorServiceNewFixedThreadPool.invokeAll(arrayList, 100 + j, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    logger.debug("Interrupted ", (Throwable) e);
                    Thread.currentThread().interrupt();
                }
                for (Future future : listEmptyList) {
                    if (!future.isCancelled()) {
                        try {
                            hashSet.addAll((Collection) future.get());
                        } catch (InterruptedException e2) {
                            logger.debug("Interrupted ", (Throwable) e2);
                            Thread.currentThread().interrupt();
                        } catch (ExecutionException e3) {
                            logger.warn("Exception ", (Throwable) e3);
                        }
                    }
                }
                executorServiceNewFixedThreadPool.shutdown();
            } catch (Throwable th) {
                executorServiceNewFixedThreadPool.shutdown();
                throw th;
            }
        }
        return (ServiceInfo[]) hashSet.toArray(new ServiceInfo[hashSet.size()]);
    }

    @Override // javax.jmdns.JmmDNS
    public Map<String, ServiceInfo[]> listBySubtype(String str) {
        return listBySubtype(str, DNSConstants.SERVICE_INFO_TIMEOUT);
    }

    @Override // javax.jmdns.JmmDNS
    public Map<String, ServiceInfo[]> listBySubtype(String str, long j) {
        HashMap map = new HashMap(5);
        for (ServiceInfo serviceInfo : list(str, j)) {
            String subtype = serviceInfo.getSubtype();
            if (!map.containsKey(subtype)) {
                map.put(subtype, new ArrayList(10));
            }
            ((List) map.get(subtype)).add(serviceInfo);
        }
        HashMap map2 = new HashMap(map.size());
        for (Map.Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            List list = (List) entry.getValue();
            map2.put(str2, list.toArray(new ServiceInfo[list.size()]));
        }
        return map2;
    }

    @Override // javax.jmdns.JmmDNS
    public void addNetworkTopologyListener(NetworkTopologyListener networkTopologyListener) {
        this._networkListeners.add(networkTopologyListener);
    }

    @Override // javax.jmdns.JmmDNS
    public void removeNetworkTopologyListener(NetworkTopologyListener networkTopologyListener) {
        this._networkListeners.remove(networkTopologyListener);
    }

    @Override // javax.jmdns.JmmDNS
    public NetworkTopologyListener[] networkListeners() {
        Set<NetworkTopologyListener> set = this._networkListeners;
        return (NetworkTopologyListener[]) set.toArray(new NetworkTopologyListener[set.size()]);
    }

    @Override // javax.jmdns.NetworkTopologyListener
    public void inetAddressAdded(NetworkTopologyEvent networkTopologyEvent) {
        InetAddress inetAddress = networkTopologyEvent.getInetAddress();
        try {
            if (this._knownMDNS.containsKey(inetAddress)) {
                return;
            }
            synchronized (this._knownMDNS) {
                if (!this._knownMDNS.containsKey(inetAddress)) {
                    final JmDNS jmDNSCreate = JmDNS.create(inetAddress);
                    if (this._knownMDNS.putIfAbsent(inetAddress, jmDNSCreate) == null) {
                        final Set<String> set = this._serviceTypes;
                        final Collection<ServiceInfo> collectionValues = this._services.values();
                        final Set<ServiceTypeListener> set2 = this._typeListeners;
                        final ConcurrentMap<String, List<ServiceListener>> concurrentMap = this._serviceListeners;
                        this._jmDNSExecutor.submit(new Runnable() { // from class: javax.jmdns.impl.JmmDNSImpl.5
                            @Override // java.lang.Runnable
                            public void run() {
                                Iterator it = set.iterator();
                                while (it.hasNext()) {
                                    jmDNSCreate.registerServiceType((String) it.next());
                                }
                                Iterator it2 = collectionValues.iterator();
                                while (it2.hasNext()) {
                                    try {
                                        jmDNSCreate.registerService(((ServiceInfo) it2.next()).clone());
                                    } catch (IOException unused) {
                                    }
                                }
                                Iterator it3 = set2.iterator();
                                while (it3.hasNext()) {
                                    try {
                                        jmDNSCreate.addServiceTypeListener((ServiceTypeListener) it3.next());
                                    } catch (IOException unused2) {
                                    }
                                }
                                for (Map.Entry entry : concurrentMap.entrySet()) {
                                    String str = (String) entry.getKey();
                                    List list = (List) entry.getValue();
                                    synchronized (list) {
                                        Iterator it4 = list.iterator();
                                        while (it4.hasNext()) {
                                            jmDNSCreate.addServiceListener(str, (ServiceListener) it4.next());
                                        }
                                    }
                                }
                            }
                        });
                        final NetworkTopologyEventImpl networkTopologyEventImpl = new NetworkTopologyEventImpl(jmDNSCreate, inetAddress);
                        for (final NetworkTopologyListener networkTopologyListener : networkListeners()) {
                            this._listenerExecutor.submit(new Runnable() { // from class: javax.jmdns.impl.JmmDNSImpl.6
                                @Override // java.lang.Runnable
                                public void run() {
                                    networkTopologyListener.inetAddressAdded(networkTopologyEventImpl);
                                }
                            });
                        }
                    } else {
                        jmDNSCreate.close();
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("Unexpected unhandled exception: " + e);
        }
    }

    @Override // javax.jmdns.NetworkTopologyListener
    public void inetAddressRemoved(NetworkTopologyEvent networkTopologyEvent) {
        InetAddress inetAddress = networkTopologyEvent.getInetAddress();
        try {
            if (this._knownMDNS.containsKey(inetAddress)) {
                synchronized (this._knownMDNS) {
                    try {
                        if (this._knownMDNS.containsKey(inetAddress)) {
                            JmDNS jmDNSRemove = this._knownMDNS.remove(inetAddress);
                            jmDNSRemove.close();
                            final NetworkTopologyEventImpl networkTopologyEventImpl = new NetworkTopologyEventImpl(jmDNSRemove, inetAddress);
                            for (final NetworkTopologyListener networkTopologyListener : networkListeners()) {
                                this._listenerExecutor.submit(new Runnable() { // from class: javax.jmdns.impl.JmmDNSImpl.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        networkTopologyListener.inetAddressRemoved(networkTopologyEventImpl);
                                    }
                                });
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("Unexpected unhandled exception: " + e);
        }
    }

    static class NetworkChecker extends TimerTask {
        private static Logger logger1 = LoggerFactory.getLogger(NetworkChecker.class.getName());
        private Set<InetAddress> _knownAddresses = Collections.synchronizedSet(new HashSet());
        private final NetworkTopologyListener _mmDNS;
        private final NetworkTopologyDiscovery _topology;

        public NetworkChecker(NetworkTopologyListener networkTopologyListener, NetworkTopologyDiscovery networkTopologyDiscovery) {
            this._mmDNS = networkTopologyListener;
            this._topology = networkTopologyDiscovery;
        }

        public void start(Timer timer) {
            run();
            timer.schedule(this, 10000L, 10000L);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                InetAddress[] inetAddresses = this._topology.getInetAddresses();
                HashSet hashSet = new HashSet(inetAddresses.length);
                for (InetAddress inetAddress : inetAddresses) {
                    hashSet.add(inetAddress);
                    if (!this._knownAddresses.contains(inetAddress)) {
                        this._mmDNS.inetAddressAdded(new NetworkTopologyEventImpl(this._mmDNS, inetAddress));
                    }
                }
                for (InetAddress inetAddress2 : this._knownAddresses) {
                    if (!hashSet.contains(inetAddress2)) {
                        this._mmDNS.inetAddressRemoved(new NetworkTopologyEventImpl(this._mmDNS, inetAddress2));
                    }
                }
                this._knownAddresses = hashSet;
            } catch (Exception e) {
                logger1.warn("Unexpected unhandled exception: " + e);
            }
        }
    }
}
