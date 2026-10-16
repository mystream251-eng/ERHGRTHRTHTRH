package javax.jmdns.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import javax.jmdns.impl.constants.DNSConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
class SocketListener extends Thread {
    static Logger logger = LoggerFactory.getLogger(SocketListener.class.getName());
    private final JmDNSImpl _jmDNSImpl;

    SocketListener(JmDNSImpl jmDNSImpl) {
        StringBuilder sb = new StringBuilder();
        sb.append("SocketListener(");
        sb.append(jmDNSImpl != null ? jmDNSImpl.getName() : "");
        sb.append(")");
        super(sb.toString());
        setDaemon(true);
        this._jmDNSImpl = jmDNSImpl;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            DatagramPacket datagramPacket = new DatagramPacket(new byte[DNSConstants.MAX_MSG_ABSOLUTE], DNSConstants.MAX_MSG_ABSOLUTE);
            while (!this._jmDNSImpl.isCanceling() && !this._jmDNSImpl.isCanceled()) {
                datagramPacket.setLength(DNSConstants.MAX_MSG_ABSOLUTE);
                this._jmDNSImpl.getSocket().receive(datagramPacket);
                if (this._jmDNSImpl.isCanceling() || this._jmDNSImpl.isCanceled() || this._jmDNSImpl.isClosing() || this._jmDNSImpl.isClosed()) {
                    break;
                }
                try {
                    if (!this._jmDNSImpl.getLocalHost().shouldIgnorePacket(datagramPacket)) {
                        DNSIncoming dNSIncoming = new DNSIncoming(datagramPacket);
                        if (dNSIncoming.isValidResponseCode()) {
                            if (logger.isTraceEnabled()) {
                                logger.trace("{}.run() JmDNS in:{}", getName(), dNSIncoming.print(true));
                            }
                            if (dNSIncoming.isQuery()) {
                                if (datagramPacket.getPort() != DNSConstants.MDNS_PORT) {
                                    this._jmDNSImpl.handleQuery(dNSIncoming, datagramPacket.getAddress(), datagramPacket.getPort());
                                }
                                this._jmDNSImpl.handleQuery(dNSIncoming, this._jmDNSImpl.getGroup(), DNSConstants.MDNS_PORT);
                            } else {
                                this._jmDNSImpl.handleResponse(dNSIncoming);
                            }
                        } else if (logger.isDebugEnabled()) {
                            logger.debug("{}.run() JmDNS in message with error code: {}", getName(), dNSIncoming.print(true));
                        }
                    }
                } catch (IOException e) {
                    logger.warn(getName() + ".run() exception ", (Throwable) e);
                }
            }
        } catch (IOException e2) {
            if (!this._jmDNSImpl.isCanceling() && !this._jmDNSImpl.isCanceled() && !this._jmDNSImpl.isClosing() && !this._jmDNSImpl.isClosed()) {
                logger.warn(getName() + ".run() exception ", (Throwable) e2);
                this._jmDNSImpl.recover();
            }
        }
        logger.trace("{}.run() exiting.", getName());
    }

    public JmDNSImpl getDns() {
        return this._jmDNSImpl;
    }
}
