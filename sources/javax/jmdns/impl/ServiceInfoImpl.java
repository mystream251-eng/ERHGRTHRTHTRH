package javax.jmdns.impl;

import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import javax.jmdns.impl.util.ByteWrangler;
import kotlin.UByte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceInfoImpl extends ServiceInfo implements DNSListener, DNSStatefulObject {
    private static Logger logger = LoggerFactory.getLogger(ServiceInfoImpl.class.getName());
    private String _application;
    private Delegate _delegate;
    private String _domain;
    private final Set<Inet4Address> _ipv4Addresses;
    private final Set<Inet6Address> _ipv6Addresses;
    private transient String _key;
    private String _name;
    private boolean _needTextAnnouncing;
    private boolean _persistent;
    private int _port;
    private int _priority;
    private Map<String, byte[]> _props;
    private String _protocol;
    private String _server;
    private final ServiceInfoState _state;
    private String _subtype;
    private byte[] _text;
    private int _weight;

    public interface Delegate {
        void textValueUpdated(ServiceInfo serviceInfo, byte[] bArr);
    }

    private static final class ServiceInfoState extends DNSStatefulObject.DefaultImplementation {
        private static final long serialVersionUID = 1104131034952196820L;
        private final ServiceInfoImpl _info;

        public ServiceInfoState(ServiceInfoImpl serviceInfoImpl) {
            this._info = serviceInfoImpl;
        }

        @Override // javax.jmdns.impl.DNSStatefulObject.DefaultImplementation
        protected void setTask(DNSTask dNSTask) {
            super.setTask(dNSTask);
            if (this._task == null && this._info.needTextAnnouncing()) {
                lock();
                try {
                    if (this._task == null && this._info.needTextAnnouncing()) {
                        if (this._state.isAnnounced()) {
                            setState(DNSState.ANNOUNCING_1);
                            if (getDns() != null) {
                                getDns().startAnnouncer();
                            }
                        }
                        this._info.setNeedTextAnnouncing(false);
                    }
                } finally {
                    unlock();
                }
            }
        }

        @Override // javax.jmdns.impl.DNSStatefulObject.DefaultImplementation
        public void setDns(JmDNSImpl jmDNSImpl) {
            super.setDns(jmDNSImpl);
        }
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i, int i2, int i3, boolean z, String str4) {
        this(decodeQualifiedNameMap(str, str2, str3), i, i2, i3, z, (byte[]) null);
        try {
            this._text = ByteWrangler.encodeText(str4);
            this._server = str4;
        } catch (IOException e) {
            throw new RuntimeException("Unexpected exception: " + e);
        }
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i, int i2, int i3, boolean z, Map<String, ?> map) {
        this(decodeQualifiedNameMap(str, str2, str3), i, i2, i3, z, ByteWrangler.textFromProperties(map));
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i, int i2, int i3, boolean z, byte[] bArr) {
        this(decodeQualifiedNameMap(str, str2, str3), i, i2, i3, z, bArr);
    }

    public ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i, int i2, int i3, boolean z, Map<String, ?> map2) {
        this(map, i, i2, i3, z, ByteWrangler.textFromProperties(map2));
    }

    ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i, int i2, int i3, boolean z, String str) {
        this(map, i, i2, i3, z, (byte[]) null);
        try {
            this._text = ByteWrangler.encodeText(str);
            this._server = str;
        } catch (IOException e) {
            throw new RuntimeException("Unexpected exception: " + e);
        }
    }

    ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i, int i2, int i3, boolean z, byte[] bArr) {
        Map<ServiceInfo.Fields, String> mapCheckQualifiedNameMap = checkQualifiedNameMap(map);
        this._domain = mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Domain);
        this._protocol = mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Protocol);
        this._application = mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Application);
        this._name = mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Instance);
        this._subtype = mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Subtype);
        this._port = i;
        this._weight = i2;
        this._priority = i3;
        this._text = bArr;
        setNeedTextAnnouncing(false);
        this._state = new ServiceInfoState(this);
        this._persistent = z;
        this._ipv4Addresses = Collections.synchronizedSet(new LinkedHashSet());
        this._ipv6Addresses = Collections.synchronizedSet(new LinkedHashSet());
    }

    ServiceInfoImpl(ServiceInfo serviceInfo) {
        this._ipv4Addresses = Collections.synchronizedSet(new LinkedHashSet());
        this._ipv6Addresses = Collections.synchronizedSet(new LinkedHashSet());
        if (serviceInfo != null) {
            this._domain = serviceInfo.getDomain();
            this._protocol = serviceInfo.getProtocol();
            this._application = serviceInfo.getApplication();
            this._name = serviceInfo.getName();
            this._subtype = serviceInfo.getSubtype();
            this._port = serviceInfo.getPort();
            this._weight = serviceInfo.getWeight();
            this._priority = serviceInfo.getPriority();
            this._text = serviceInfo.getTextBytes();
            this._persistent = serviceInfo.isPersistent();
            for (Inet6Address inet6Address : serviceInfo.getInet6Addresses()) {
                this._ipv6Addresses.add(inet6Address);
            }
            for (Inet4Address inet4Address : serviceInfo.getInet4Addresses()) {
                this._ipv4Addresses.add(inet4Address);
            }
        }
        this._state = new ServiceInfoState(this);
    }

    public static Map<ServiceInfo.Fields, String> decodeQualifiedNameMap(String str, String str2, String str3) {
        Map<ServiceInfo.Fields, String> mapDecodeQualifiedNameMapForType = decodeQualifiedNameMapForType(str);
        mapDecodeQualifiedNameMapForType.put(ServiceInfo.Fields.Instance, str2);
        mapDecodeQualifiedNameMapForType.put(ServiceInfo.Fields.Subtype, str3);
        return checkQualifiedNameMap(mapDecodeQualifiedNameMapForType);
    }

    public static Map<ServiceInfo.Fields, String> decodeQualifiedNameMapForType(String str) {
        String strRemoveSeparators;
        String strSubstring;
        String str2;
        int iIndexOf;
        String strSubstring2;
        String strSubstring3;
        String strSubstring4;
        String lowerCase = str.toLowerCase();
        String strRemoveSeparators2 = "";
        if (lowerCase.contains("in-addr.arpa") || lowerCase.contains("ip6.arpa")) {
            int iIndexOf2 = lowerCase.contains("in-addr.arpa") ? lowerCase.indexOf("in-addr.arpa") : lowerCase.indexOf("ip6.arpa");
            strRemoveSeparators = removeSeparators(str.substring(0, iIndexOf2));
            strSubstring = str.substring(iIndexOf2);
        } else {
            if (!lowerCase.contains("_") && lowerCase.contains(".")) {
                int iIndexOf3 = lowerCase.indexOf(46);
                strRemoveSeparators = removeSeparators(str.substring(0, iIndexOf3));
                strSubstring = removeSeparators(str.substring(iIndexOf3));
            } else {
                if ((!lowerCase.startsWith("_") || lowerCase.startsWith("_services")) && (iIndexOf = lowerCase.indexOf("._")) > 0) {
                    strSubstring2 = str.substring(0, iIndexOf);
                    int i = iIndexOf + 1;
                    if (i < lowerCase.length()) {
                        strSubstring3 = lowerCase.substring(i);
                        str = str.substring(i);
                    } else {
                        strSubstring3 = lowerCase;
                    }
                } else {
                    strSubstring3 = lowerCase;
                    strSubstring2 = "";
                }
                int iLastIndexOf = strSubstring3.lastIndexOf("._");
                if (iLastIndexOf > 0) {
                    int i2 = iLastIndexOf + 2;
                    strSubstring4 = str.substring(i2, strSubstring3.indexOf(46, i2));
                } else {
                    strSubstring4 = "";
                }
                if (strSubstring4.length() > 0) {
                    int iIndexOf4 = strSubstring3.indexOf("_" + strSubstring4.toLowerCase() + ".");
                    int length = strSubstring4.length() + iIndexOf4 + 2;
                    int length2 = strSubstring3.length() - (strSubstring3.endsWith(".") ? 1 : 0);
                    String strSubstring5 = length2 > length ? str.substring(length, length2) : "";
                    if (iIndexOf4 > 0) {
                        lowerCase = str.substring(0, iIndexOf4 - 1);
                        strSubstring = strSubstring5;
                    } else {
                        strSubstring = strSubstring5;
                        lowerCase = "";
                    }
                } else {
                    strSubstring = "";
                }
                int iIndexOf5 = lowerCase.toLowerCase().indexOf("._sub");
                if (iIndexOf5 > 0) {
                    strRemoveSeparators2 = removeSeparators(lowerCase.substring(0, iIndexOf5));
                    lowerCase = lowerCase.substring(iIndexOf5 + 5);
                }
                strRemoveSeparators = strSubstring2;
                String str3 = strRemoveSeparators2;
                strRemoveSeparators2 = strSubstring4;
                str2 = str3;
            }
            HashMap map = new HashMap(5);
            map.put(ServiceInfo.Fields.Domain, removeSeparators(strSubstring));
            map.put(ServiceInfo.Fields.Protocol, strRemoveSeparators2);
            map.put(ServiceInfo.Fields.Application, removeSeparators(lowerCase));
            map.put(ServiceInfo.Fields.Instance, strRemoveSeparators);
            map.put(ServiceInfo.Fields.Subtype, str2);
            return map;
        }
        lowerCase = "";
        str2 = lowerCase;
        HashMap map2 = new HashMap(5);
        map2.put(ServiceInfo.Fields.Domain, removeSeparators(strSubstring));
        map2.put(ServiceInfo.Fields.Protocol, strRemoveSeparators2);
        map2.put(ServiceInfo.Fields.Application, removeSeparators(lowerCase));
        map2.put(ServiceInfo.Fields.Instance, strRemoveSeparators);
        map2.put(ServiceInfo.Fields.Subtype, str2);
        return map2;
    }

    protected static Map<ServiceInfo.Fields, String> checkQualifiedNameMap(Map<ServiceInfo.Fields, String> map) {
        HashMap map2 = new HashMap(5);
        boolean zContainsKey = map.containsKey(ServiceInfo.Fields.Domain);
        String str = ImagesContract.LOCAL;
        String str2 = zContainsKey ? map.get(ServiceInfo.Fields.Domain) : ImagesContract.LOCAL;
        if (str2 != null && str2.length() != 0) {
            str = str2;
        }
        map2.put(ServiceInfo.Fields.Domain, removeSeparators(str));
        String str3 = "tcp";
        String str4 = map.containsKey(ServiceInfo.Fields.Protocol) ? map.get(ServiceInfo.Fields.Protocol) : "tcp";
        if (str4 != null && str4.length() != 0) {
            str3 = str4;
        }
        map2.put(ServiceInfo.Fields.Protocol, removeSeparators(str3));
        String str5 = "";
        String str6 = map.containsKey(ServiceInfo.Fields.Application) ? map.get(ServiceInfo.Fields.Application) : "";
        if (str6 == null || str6.length() == 0) {
            str6 = "";
        }
        map2.put(ServiceInfo.Fields.Application, removeSeparators(str6));
        String str7 = map.containsKey(ServiceInfo.Fields.Instance) ? map.get(ServiceInfo.Fields.Instance) : "";
        if (str7 == null || str7.length() == 0) {
            str7 = "";
        }
        map2.put(ServiceInfo.Fields.Instance, removeSeparators(str7));
        String str8 = map.containsKey(ServiceInfo.Fields.Subtype) ? map.get(ServiceInfo.Fields.Subtype) : "";
        if (str8 != null && str8.length() != 0) {
            str5 = str8;
        }
        map2.put(ServiceInfo.Fields.Subtype, removeSeparators(str5));
        return map2;
    }

    private static String removeSeparators(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        if (strTrim.startsWith(".")) {
            strTrim = strTrim.substring(1);
        }
        if (strTrim.startsWith("_")) {
            strTrim = strTrim.substring(1);
        }
        return strTrim.endsWith(".") ? strTrim.substring(0, strTrim.length() - 1) : strTrim;
    }

    @Override // javax.jmdns.ServiceInfo
    public String getType() {
        String str;
        String domain = getDomain();
        String protocol = getProtocol();
        String application = getApplication();
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (application.length() > 0) {
            str = "_" + application + ".";
        } else {
            str = "";
        }
        sb.append(str);
        if (protocol.length() > 0) {
            str2 = "_" + protocol + ".";
        }
        sb.append(str2);
        sb.append(domain);
        sb.append(".");
        return sb.toString();
    }

    @Override // javax.jmdns.ServiceInfo
    public String getTypeWithSubtype() {
        String str;
        String subtype = getSubtype();
        StringBuilder sb = new StringBuilder();
        if (subtype.length() > 0) {
            str = "_" + subtype + "._sub.";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(getType());
        return sb.toString();
    }

    @Override // javax.jmdns.ServiceInfo
    public String getName() {
        String str = this._name;
        return str != null ? str : "";
    }

    @Override // javax.jmdns.ServiceInfo
    public String getKey() {
        if (this._key == null) {
            this._key = getQualifiedName().toLowerCase();
        }
        return this._key;
    }

    void setName(String str) {
        this._name = str;
        this._key = null;
    }

    @Override // javax.jmdns.ServiceInfo
    public String getQualifiedName() {
        String str;
        String str2;
        String domain = getDomain();
        String protocol = getProtocol();
        String application = getApplication();
        String name = getName();
        StringBuilder sb = new StringBuilder();
        String str3 = "";
        if (name.length() > 0) {
            str = name + ".";
        } else {
            str = "";
        }
        sb.append(str);
        if (application.length() > 0) {
            str2 = "_" + application + ".";
        } else {
            str2 = "";
        }
        sb.append(str2);
        if (protocol.length() > 0) {
            str3 = "_" + protocol + ".";
        }
        sb.append(str3);
        sb.append(domain);
        sb.append(".");
        return sb.toString();
    }

    @Override // javax.jmdns.ServiceInfo
    public String getServer() {
        String str = this._server;
        return str != null ? str : "";
    }

    void setServer(String str) {
        this._server = str;
    }

    @Override // javax.jmdns.ServiceInfo
    @Deprecated
    public String getHostAddress() {
        String[] hostAddresses = getHostAddresses();
        return hostAddresses.length > 0 ? hostAddresses[0] : "";
    }

    @Override // javax.jmdns.ServiceInfo
    public String[] getHostAddresses() {
        Inet4Address[] inet4Addresses = getInet4Addresses();
        Inet6Address[] inet6Addresses = getInet6Addresses();
        String[] strArr = new String[inet4Addresses.length + inet6Addresses.length];
        for (int i = 0; i < inet4Addresses.length; i++) {
            strArr[i] = inet4Addresses[i].getHostAddress();
        }
        for (int i2 = 0; i2 < inet6Addresses.length; i2++) {
            strArr[inet4Addresses.length + i2] = "[" + inet6Addresses[i2].getHostAddress() + "]";
        }
        return strArr;
    }

    void addAddress(Inet4Address inet4Address) {
        this._ipv4Addresses.add(inet4Address);
    }

    void addAddress(Inet6Address inet6Address) {
        this._ipv6Addresses.add(inet6Address);
    }

    @Override // javax.jmdns.ServiceInfo
    @Deprecated
    public InetAddress getAddress() {
        return getInetAddress();
    }

    @Override // javax.jmdns.ServiceInfo
    @Deprecated
    public InetAddress getInetAddress() {
        InetAddress[] inetAddresses = getInetAddresses();
        if (inetAddresses.length > 0) {
            return inetAddresses[0];
        }
        return null;
    }

    @Override // javax.jmdns.ServiceInfo
    @Deprecated
    public Inet4Address getInet4Address() {
        Inet4Address[] inet4Addresses = getInet4Addresses();
        if (inet4Addresses.length > 0) {
            return inet4Addresses[0];
        }
        return null;
    }

    @Override // javax.jmdns.ServiceInfo
    @Deprecated
    public Inet6Address getInet6Address() {
        Inet6Address[] inet6Addresses = getInet6Addresses();
        if (inet6Addresses.length > 0) {
            return inet6Addresses[0];
        }
        return null;
    }

    @Override // javax.jmdns.ServiceInfo
    public InetAddress[] getInetAddresses() {
        ArrayList arrayList = new ArrayList(this._ipv4Addresses.size() + this._ipv6Addresses.size());
        arrayList.addAll(this._ipv4Addresses);
        arrayList.addAll(this._ipv6Addresses);
        return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
    }

    @Override // javax.jmdns.ServiceInfo
    public Inet4Address[] getInet4Addresses() {
        Set<Inet4Address> set = this._ipv4Addresses;
        return (Inet4Address[]) set.toArray(new Inet4Address[set.size()]);
    }

    @Override // javax.jmdns.ServiceInfo
    public Inet6Address[] getInet6Addresses() {
        Set<Inet6Address> set = this._ipv6Addresses;
        return (Inet6Address[]) set.toArray(new Inet6Address[set.size()]);
    }

    @Override // javax.jmdns.ServiceInfo
    public int getPort() {
        return this._port;
    }

    @Override // javax.jmdns.ServiceInfo
    public int getPriority() {
        return this._priority;
    }

    @Override // javax.jmdns.ServiceInfo
    public int getWeight() {
        return this._weight;
    }

    @Override // javax.jmdns.ServiceInfo
    public byte[] getTextBytes() {
        byte[] bArr = this._text;
        return (bArr == null || bArr.length <= 0) ? ByteWrangler.EMPTY_TXT : bArr;
    }

    @Override // javax.jmdns.ServiceInfo
    @Deprecated
    public String getTextString() {
        Iterator<Map.Entry<String, byte[]>> it = getProperties().entrySet().iterator();
        if (!it.hasNext()) {
            return "";
        }
        Map.Entry<String, byte[]> next = it.next();
        byte[] value = next.getValue();
        if (value != null && value.length > 0) {
            return next.getKey() + "=" + ByteWrangler.readUTF(value);
        }
        return next.getKey();
    }

    @Override // javax.jmdns.ServiceInfo
    @Deprecated
    public String getURL() {
        return getURL("http");
    }

    @Override // javax.jmdns.ServiceInfo
    public String[] getURLs() {
        return getURLs("http");
    }

    @Override // javax.jmdns.ServiceInfo
    @Deprecated
    public String getURL(String str) {
        String[] uRLs = getURLs(str);
        if (uRLs.length > 0) {
            return uRLs[0];
        }
        return str + "://null:" + getPort();
    }

    @Override // javax.jmdns.ServiceInfo
    public String[] getURLs(String str) {
        InetAddress[] inetAddresses = getInetAddresses();
        ArrayList arrayList = new ArrayList(inetAddresses.length);
        for (InetAddress inetAddress : inetAddresses) {
            String hostAddress = inetAddress.getHostAddress();
            if (inetAddress instanceof Inet6Address) {
                hostAddress = "[" + hostAddress + "]";
            }
            String string = str + "://" + hostAddress + ":" + getPort();
            String propertyString = getPropertyString("path");
            if (propertyString != null) {
                if (propertyString.indexOf("://") >= 0) {
                    string = propertyString;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    if (!propertyString.startsWith("/")) {
                        propertyString = "/" + propertyString;
                    }
                    sb.append(propertyString);
                    string = sb.toString();
                }
            }
            arrayList.add(string);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // javax.jmdns.ServiceInfo
    public synchronized byte[] getPropertyBytes(String str) {
        return getProperties().get(str);
    }

    @Override // javax.jmdns.ServiceInfo
    public synchronized String getPropertyString(String str) {
        byte[] bArr = getProperties().get(str);
        if (bArr == null) {
            return null;
        }
        if (bArr == ByteWrangler.NO_VALUE) {
            return "true";
        }
        return ByteWrangler.readUTF(bArr, 0, bArr.length);
    }

    @Override // javax.jmdns.ServiceInfo
    public Enumeration<String> getPropertyNames() {
        Map<String, byte[]> properties = getProperties();
        return new Vector(properties != null ? properties.keySet() : Collections.emptySet()).elements();
    }

    @Override // javax.jmdns.ServiceInfo
    public String getApplication() {
        String str = this._application;
        return str != null ? str : "";
    }

    @Override // javax.jmdns.ServiceInfo
    public String getDomain() {
        String str = this._domain;
        return str != null ? str : ImagesContract.LOCAL;
    }

    @Override // javax.jmdns.ServiceInfo
    public String getProtocol() {
        String str = this._protocol;
        return str != null ? str : "tcp";
    }

    @Override // javax.jmdns.ServiceInfo
    public String getSubtype() {
        String str = this._subtype;
        return str != null ? str : "";
    }

    @Override // javax.jmdns.ServiceInfo
    public Map<ServiceInfo.Fields, String> getQualifiedNameMap() {
        HashMap map = new HashMap(5);
        map.put(ServiceInfo.Fields.Domain, getDomain());
        map.put(ServiceInfo.Fields.Protocol, getProtocol());
        map.put(ServiceInfo.Fields.Application, getApplication());
        map.put(ServiceInfo.Fields.Instance, getName());
        map.put(ServiceInfo.Fields.Subtype, getSubtype());
        return map;
    }

    synchronized Map<String, byte[]> getProperties() {
        if (this._props == null && getTextBytes() != null) {
            Hashtable hashtable = new Hashtable();
            try {
                ByteWrangler.readProperties(hashtable, getTextBytes());
            } catch (Exception e) {
                logger.warn("Malformed TXT Field ", (Throwable) e);
            }
            this._props = hashtable;
        }
        return this._props != null ? this._props : Collections.emptyMap();
    }

    @Override // javax.jmdns.impl.DNSListener
    public void updateRecord(DNSCache dNSCache, long j, DNSEntry dNSEntry) {
        boolean zHandleUpdateRecord;
        if (!(dNSEntry instanceof DNSRecord)) {
            logger.trace("DNSEntry is not of type 'DNSRecord' but of type {}", dNSEntry == null ? "null" : dNSEntry.getClass().getSimpleName());
            return;
        }
        DNSRecord dNSRecord = (DNSRecord) dNSEntry;
        if (dNSRecord.isExpired(j)) {
            zHandleUpdateRecord = handleExpiredRecord(dNSRecord);
        } else {
            zHandleUpdateRecord = handleUpdateRecord(dNSCache, j, dNSRecord);
        }
        if (zHandleUpdateRecord) {
            JmDNSImpl dns = getDns();
            if (dns != null) {
                if (hasData()) {
                    dns.handleServiceResolved(new ServiceEventImpl(dns, getType(), getName(), clone()));
                }
            } else {
                logger.debug("JmDNS not available.");
            }
        }
        synchronized (this) {
            notifyAll();
        }
    }

    /* JADX INFO: renamed from: javax.jmdns.impl.ServiceInfoImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSRecordType;

        static {
            int[] iArr = new int[DNSRecordType.values().length];
            $SwitchMap$javax$jmdns$impl$constants$DNSRecordType = iArr;
            try {
                iArr[DNSRecordType.TYPE_A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_AAAA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_SRV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_TXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_PTR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private boolean handleExpiredRecord(DNSRecord dNSRecord) {
        int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecord.getRecordType().ordinal()];
        if (i == 1 || i == 2) {
            if (!dNSRecord.getName().equalsIgnoreCase(getServer())) {
                return false;
            }
            DNSRecord.Address address = (DNSRecord.Address) dNSRecord;
            if (DNSRecordType.TYPE_A.equals(dNSRecord.getRecordType())) {
                Inet4Address inet4Address = (Inet4Address) address.getAddress();
                if (this._ipv4Addresses.remove(inet4Address)) {
                    logger.debug("Removed expired IPv4: {}", inet4Address);
                    return true;
                }
                logger.debug("Expired IPv4 not in this service: {}", inet4Address);
                return false;
            }
            Inet6Address inet6Address = (Inet6Address) address.getAddress();
            if (this._ipv6Addresses.remove(inet6Address)) {
                logger.debug("Removed expired IPv6: {}", inet6Address);
                return true;
            }
            logger.debug("Expired IPv6 not in this service: {}", inet6Address);
            return false;
        }
        logger.trace("Unhandled expired record: {}", dNSRecord);
        return false;
    }

    private boolean handleUpdateRecord(DNSCache dNSCache, long j, DNSRecord dNSRecord) {
        int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecord.getRecordType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        if (!dNSRecord.getName().equalsIgnoreCase(getQualifiedName())) {
                            return false;
                        }
                        this._text = ((DNSRecord.Text) dNSRecord).getText();
                        this._props = null;
                    } else {
                        if (i != 5 || getSubtype().length() != 0 || dNSRecord.getSubtype().length() == 0) {
                            return false;
                        }
                        this._subtype = dNSRecord.getSubtype();
                    }
                } else {
                    if (!dNSRecord.getName().equalsIgnoreCase(getQualifiedName())) {
                        return false;
                    }
                    DNSRecord.Service service = (DNSRecord.Service) dNSRecord;
                    String str = this._server;
                    boolean z = str == null || !str.equalsIgnoreCase(service.getServer());
                    this._server = service.getServer();
                    this._port = service.getPort();
                    this._weight = service.getWeight();
                    this._priority = service.getPriority();
                    if (z) {
                        this._ipv4Addresses.clear();
                        this._ipv6Addresses.clear();
                        Iterator<? extends DNSEntry> it = dNSCache.getDNSEntryList(this._server, DNSRecordType.TYPE_A, DNSRecordClass.CLASS_IN).iterator();
                        while (it.hasNext()) {
                            updateRecord(dNSCache, j, it.next());
                        }
                        Iterator<? extends DNSEntry> it2 = dNSCache.getDNSEntryList(this._server, DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_IN).iterator();
                        while (it2.hasNext()) {
                            updateRecord(dNSCache, j, it2.next());
                        }
                        return false;
                    }
                }
            } else {
                if (!dNSRecord.getName().equalsIgnoreCase(getServer())) {
                    return false;
                }
                DNSRecord.Address address = (DNSRecord.Address) dNSRecord;
                if (!(address.getAddress() instanceof Inet6Address)) {
                    return false;
                }
                if (!this._ipv6Addresses.add((Inet6Address) address.getAddress())) {
                    return false;
                }
            }
        } else {
            if (!dNSRecord.getName().equalsIgnoreCase(getServer())) {
                return false;
            }
            DNSRecord.Address address2 = (DNSRecord.Address) dNSRecord;
            if (!(address2.getAddress() instanceof Inet4Address)) {
                return false;
            }
            if (!this._ipv4Addresses.add((Inet4Address) address2.getAddress())) {
                return false;
            }
        }
        return true;
    }

    @Override // javax.jmdns.ServiceInfo
    public synchronized boolean hasData() {
        return getServer() != null && hasInetAddress() && getTextBytes() != null && getTextBytes().length > 0;
    }

    private final boolean hasInetAddress() {
        return this._ipv4Addresses.size() > 0 || this._ipv6Addresses.size() > 0;
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean advanceState(DNSTask dNSTask) {
        return this._state.advanceState(dNSTask);
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean revertState() {
        return this._state.revertState();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean cancelState() {
        return this._state.cancelState();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean closeState() {
        return this._state.closeState();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean recoverState() {
        return this._state.recoverState();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public void removeAssociationWithTask(DNSTask dNSTask) {
        this._state.removeAssociationWithTask(dNSTask);
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this._state.associateWithTask(dNSTask, dNSState);
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this._state.isAssociatedWithTask(dNSTask, dNSState);
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean isProbing() {
        return this._state.isProbing();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean isAnnouncing() {
        return this._state.isAnnouncing();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean isAnnounced() {
        return this._state.isAnnounced();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean isCanceling() {
        return this._state.isCanceling();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean isCanceled() {
        return this._state.isCanceled();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean isClosing() {
        return this._state.isClosing();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean isClosed() {
        return this._state.isClosed();
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean waitForAnnounced(long j) {
        return this._state.waitForAnnounced(j);
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean waitForCanceled(long j) {
        return this._state.waitForCanceled(j);
    }

    public int hashCode() {
        return getQualifiedName().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ServiceInfoImpl) && getQualifiedName().equals(((ServiceInfoImpl) obj).getQualifiedName());
    }

    @Override // javax.jmdns.ServiceInfo
    public String getNiceTextString() {
        StringBuilder sb = new StringBuilder();
        int length = getTextBytes().length;
        for (int i = 0; i < length; i++) {
            if (i >= 200) {
                sb.append("...");
                break;
            }
            int i2 = getTextBytes()[i] & UByte.MAX_VALUE;
            if (i2 < 32 || i2 > 127) {
                sb.append("\\0");
                sb.append(Integer.toString(i2, 8));
            } else {
                sb.append((char) i2);
            }
        }
        return sb.toString();
    }

    @Override // javax.jmdns.ServiceInfo
    public ServiceInfoImpl clone() {
        ServiceInfoImpl serviceInfoImpl = new ServiceInfoImpl(getQualifiedNameMap(), this._port, this._weight, this._priority, this._persistent, this._text);
        for (Inet6Address inet6Address : getInet6Addresses()) {
            serviceInfoImpl._ipv6Addresses.add(inet6Address);
        }
        for (Inet4Address inet4Address : getInet4Addresses()) {
            serviceInfoImpl._ipv4Addresses.add(inet4Address);
        }
        return serviceInfoImpl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(getClass().getSimpleName());
        sb.append('@');
        sb.append(System.identityHashCode(this));
        sb.append(" name: '");
        if (getName().length() > 0) {
            sb.append(getName());
            sb.append('.');
        }
        sb.append(getTypeWithSubtype());
        sb.append("' address: '");
        InetAddress[] inetAddresses = getInetAddresses();
        if (inetAddresses.length > 0) {
            for (InetAddress inetAddress : inetAddresses) {
                sb.append(inetAddress);
                sb.append(':');
                sb.append(getPort());
                sb.append(' ');
            }
        } else {
            sb.append("(null):");
            sb.append(getPort());
        }
        sb.append("' status: '");
        sb.append(this._state.toString());
        sb.append(isPersistent() ? "' is persistent," : "',");
        if (hasData()) {
            sb.append(" has data");
        } else {
            sb.append(" has NO data");
        }
        if (getTextBytes().length > 0) {
            Map<String, byte[]> properties = getProperties();
            if (!properties.isEmpty()) {
                for (Map.Entry<String, byte[]> entry : properties.entrySet()) {
                    String utf = ByteWrangler.readUTF(entry.getValue());
                    sb.append("\n\t");
                    sb.append(entry.getKey());
                    sb.append(": ");
                    sb.append(utf);
                }
            } else {
                sb.append(", empty");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public Collection<DNSRecord> answers(DNSRecordClass dNSRecordClass, boolean z, int i, HostInfo hostInfo) {
        ArrayList arrayList = new ArrayList();
        if (dNSRecordClass == DNSRecordClass.CLASS_ANY || dNSRecordClass == DNSRecordClass.CLASS_IN) {
            if (getSubtype().length() > 0) {
                arrayList.add(new DNSRecord.Pointer(getTypeWithSubtype(), DNSRecordClass.CLASS_IN, false, i, getQualifiedName()));
            }
            arrayList.add(new DNSRecord.Pointer(getType(), DNSRecordClass.CLASS_IN, false, i, getQualifiedName()));
            arrayList.add(new DNSRecord.Service(getQualifiedName(), DNSRecordClass.CLASS_IN, z, i, this._priority, this._weight, this._port, hostInfo.getName()));
            arrayList.add(new DNSRecord.Text(getQualifiedName(), DNSRecordClass.CLASS_IN, z, i, getTextBytes()));
        }
        return arrayList;
    }

    @Override // javax.jmdns.ServiceInfo
    public void setText(byte[] bArr) throws IllegalStateException {
        synchronized (this) {
            this._text = bArr;
            this._props = null;
            setNeedTextAnnouncing(true);
        }
    }

    @Override // javax.jmdns.ServiceInfo
    public void setText(Map<String, ?> map) throws IllegalStateException {
        setText(ByteWrangler.textFromProperties(map));
    }

    void _setText(byte[] bArr) {
        this._text = bArr;
        this._props = null;
    }

    public void setDns(JmDNSImpl jmDNSImpl) {
        this._state.setDns(jmDNSImpl);
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public JmDNSImpl getDns() {
        return this._state.getDns();
    }

    @Override // javax.jmdns.ServiceInfo
    public boolean isPersistent() {
        return this._persistent;
    }

    public void setNeedTextAnnouncing(boolean z) {
        this._needTextAnnouncing = z;
        if (z) {
            this._state.setTask(null);
        }
    }

    public boolean needTextAnnouncing() {
        return this._needTextAnnouncing;
    }

    Delegate getDelegate() {
        return this._delegate;
    }

    void setDelegate(Delegate delegate) {
        this._delegate = delegate;
    }

    @Override // javax.jmdns.ServiceInfo
    public boolean hasSameAddresses(ServiceInfo serviceInfo) {
        if (serviceInfo == null) {
            return false;
        }
        if (serviceInfo instanceof ServiceInfoImpl) {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) serviceInfo;
            return this._ipv4Addresses.size() == serviceInfoImpl._ipv4Addresses.size() && this._ipv6Addresses.size() == serviceInfoImpl._ipv6Addresses.size() && this._ipv4Addresses.equals(serviceInfoImpl._ipv4Addresses) && this._ipv6Addresses.equals(serviceInfoImpl._ipv6Addresses);
        }
        InetAddress[] inetAddresses = getInetAddresses();
        InetAddress[] inetAddresses2 = serviceInfo.getInetAddresses();
        return inetAddresses.length == inetAddresses2.length && new HashSet(Arrays.asList(inetAddresses)).equals(new HashSet(Arrays.asList(inetAddresses2)));
    }
}
