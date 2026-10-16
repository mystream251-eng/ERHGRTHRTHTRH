package javax.jmdns;

import java.util.EventListener;

/* JADX INFO: loaded from: classes2.dex */
public interface ServiceListener extends EventListener {
    void serviceAdded(ServiceEvent serviceEvent);

    void serviceRemoved(ServiceEvent serviceEvent);

    void serviceResolved(ServiceEvent serviceEvent);
}
