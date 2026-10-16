package javax.jmdns.impl.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes2.dex */
public class NamedThreadFactory implements ThreadFactory {
    private final ThreadFactory _delegate = Executors.defaultThreadFactory();
    private final String _namePrefix;

    public NamedThreadFactory(String str) {
        this._namePrefix = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this._delegate.newThread(runnable);
        threadNewThread.setName(this._namePrefix + ' ' + threadNewThread.getName());
        return threadNewThread;
    }
}
