package javax.jmdns;

import java.util.EventListener;

/* JADX INFO: loaded from: classes2.dex */
public interface ServiceTypeListener extends EventListener {
    void serviceTypeAdded(ServiceEvent serviceEvent);

    void subTypeForServiceTypeAdded(ServiceEvent serviceEvent);
}
