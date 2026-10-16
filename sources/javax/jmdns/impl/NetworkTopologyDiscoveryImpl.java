package javax.jmdns.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import javax.jmdns.NetworkTopologyDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkTopologyDiscoveryImpl implements NetworkTopologyDiscovery {
    private static final Logger logger = LoggerFactory.getLogger(NetworkTopologyDiscoveryImpl.class.getName());

    @Override // javax.jmdns.NetworkTopologyDiscovery
    public void lockInetAddress(InetAddress inetAddress) {
    }

    @Override // javax.jmdns.NetworkTopologyDiscovery
    public void unlockInetAddress(InetAddress inetAddress) {
    }

    @Override // javax.jmdns.NetworkTopologyDiscovery
    public InetAddress[] getInetAddresses() {
        HashSet hashSet = new HashSet();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    logger.trace("Found NetworkInterface/InetAddress: {} -- {}", networkInterfaceNextElement, inetAddressNextElement);
                    if (useInetAddress(networkInterfaceNextElement, inetAddressNextElement)) {
                        hashSet.add(inetAddressNextElement);
                    }
                }
            }
        } catch (SocketException e) {
            logger.warn("Error while fetching network interfaces addresses: " + e);
        }
        return (InetAddress[]) hashSet.toArray(new InetAddress[hashSet.size()]);
    }

    @Override // javax.jmdns.NetworkTopologyDiscovery
    public boolean useInetAddress(NetworkInterface networkInterface, InetAddress inetAddress) {
        try {
            return networkInterface.isUp() && networkInterface.supportsMulticast() && !networkInterface.isLoopback();
        } catch (Exception unused) {
            return false;
        }
    }
}
