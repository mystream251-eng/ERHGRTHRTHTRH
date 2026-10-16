package javax.jmdns.impl.tasks.state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DNSStateTask extends DNSTask {
    private DNSState _taskState;
    private final int _ttl;
    static Logger logger = LoggerFactory.getLogger(DNSStateTask.class.getName());
    private static int _defaultTTL = DNSConstants.DNS_TTL;

    protected abstract void advanceTask();

    protected abstract DNSOutgoing buildOutgoingForDNS(DNSOutgoing dNSOutgoing) throws IOException;

    protected abstract DNSOutgoing buildOutgoingForInfo(ServiceInfoImpl serviceInfoImpl, DNSOutgoing dNSOutgoing) throws IOException;

    protected abstract boolean checkRunCondition();

    protected abstract DNSOutgoing createOugoing();

    public abstract String getTaskDescription();

    protected abstract void recoverTask(Throwable th);

    public static int defaultTTL() {
        return _defaultTTL;
    }

    public static void setDefaultTTL(int i) {
        _defaultTTL = i;
    }

    public DNSStateTask(JmDNSImpl jmDNSImpl, int i) {
        super(jmDNSImpl);
        this._taskState = null;
        this._ttl = i;
    }

    public int getTTL() {
        return this._ttl;
    }

    protected void associate(DNSState dNSState) {
        synchronized (getDns()) {
            getDns().associateWithTask(this, dNSState);
        }
        Iterator<ServiceInfo> it = getDns().getServices().values().iterator();
        while (it.hasNext()) {
            ((ServiceInfoImpl) it.next()).associateWithTask(this, dNSState);
        }
    }

    protected void removeAssociation() {
        synchronized (getDns()) {
            getDns().removeAssociationWithTask(this);
        }
        Iterator<ServiceInfo> it = getDns().getServices().values().iterator();
        while (it.hasNext()) {
            ((ServiceInfoImpl) it.next()).removeAssociationWithTask(this);
        }
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        DNSOutgoing dNSOutgoingCreateOugoing = createOugoing();
        try {
            if (!checkRunCondition()) {
                cancel();
                return;
            }
            List<DNSStatefulObject> arrayList = new ArrayList<>();
            synchronized (getDns()) {
                if (getDns().isAssociatedWithTask(this, getTaskState())) {
                    logger.debug("{}.run() JmDNS {} {}", getName(), getTaskDescription(), getDns().getName());
                    arrayList.add(getDns());
                    dNSOutgoingCreateOugoing = buildOutgoingForDNS(dNSOutgoingCreateOugoing);
                }
            }
            Iterator<ServiceInfo> it = getDns().getServices().values().iterator();
            while (it.hasNext()) {
                ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) it.next();
                synchronized (serviceInfoImpl) {
                    if (serviceInfoImpl.isAssociatedWithTask(this, getTaskState())) {
                        logger.debug("{}.run() JmDNS {} {}", getName(), getTaskDescription(), serviceInfoImpl.getQualifiedName());
                        arrayList.add(serviceInfoImpl);
                        dNSOutgoingCreateOugoing = buildOutgoingForInfo(serviceInfoImpl, dNSOutgoingCreateOugoing);
                    }
                }
            }
            if (!dNSOutgoingCreateOugoing.isEmpty()) {
                logger.debug("{}.run() JmDNS {} #{}", getName(), getTaskDescription(), getTaskState());
                getDns().send(dNSOutgoingCreateOugoing);
                advanceObjectsState(arrayList);
                advanceTask();
                return;
            }
            advanceObjectsState(arrayList);
            cancel();
        } catch (Throwable th) {
            logger.warn(getName() + ".run() exception ", th);
            recoverTask(th);
        }
    }

    protected void advanceObjectsState(List<DNSStatefulObject> list) {
        if (list != null) {
            for (DNSStatefulObject dNSStatefulObject : list) {
                synchronized (dNSStatefulObject) {
                    dNSStatefulObject.advanceState(this);
                }
            }
        }
    }

    protected DNSState getTaskState() {
        return this._taskState;
    }

    protected void setTaskState(DNSState dNSState) {
        this._taskState = dNSState;
    }
}
