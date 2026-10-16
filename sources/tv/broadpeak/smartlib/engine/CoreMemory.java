package tv.broadpeak.smartlib.engine;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class CoreMemory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static CoreMemory f188a;
    public Set<a<Object>> b = new HashSet();
    public ReferenceQueue<Object> c = new ReferenceQueue<>();

    public static class a<T> extends WeakReference<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f189a;

        public a(T t, int i, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            this.f189a = i;
        }
    }

    public static CoreMemory getInstance() {
        if (f188a == null) {
            f188a = new CoreMemory();
        }
        return f188a;
    }

    public void clean() {
        while (true) {
            a aVar = (a) this.c.poll();
            if (aVar == null) {
                return;
            }
            if (this.b.contains(aVar)) {
                onRemove(aVar.f189a);
                this.b.remove(aVar);
            }
        }
    }

    public <T> T get(int i) {
        clean();
        for (a<Object> aVar : this.b) {
            if (aVar.f189a == i) {
                return (T) aVar.get();
            }
        }
        return null;
    }

    public void onRemove(int i) {
        LoggerManager.getInstance().printVerboseLogs("BpkCoreMemory", "id " + i + " removed");
    }

    public int register(Object obj) {
        clean();
        for (a<Object> aVar : this.b) {
            if (aVar.get() == obj) {
                LoggerManager.getInstance().printVerboseLogs("BpkCoreMemory", "id " + aVar.f189a + " already stored");
                return aVar.f189a;
            }
        }
        Random random = new Random();
        int iNextInt = random.nextInt(888889) + 111111;
        boolean z = true;
        while (z) {
            z = false;
            Iterator<a<Object>> it = this.b.iterator();
            while (it.hasNext()) {
                if (it.next().f189a == iNextInt) {
                    iNextInt = random.nextInt(888889) + 111111;
                    z = true;
                }
            }
        }
        LoggerManager.getInstance().printVerboseLogs("BpkCoreMemory", "id " + iNextInt + " registered");
        this.b.add(new a<>(obj, iNextInt, this.c));
        return iNextInt;
    }

    public void release() {
        Iterator<a<Object>> it = this.b.iterator();
        while (it.hasNext()) {
            onRemove(it.next().f189a);
        }
        this.b.clear();
    }

    public int size() {
        return this.b.size();
    }
}
