package tv.broadpeak.smartlib.engine.executor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class CoreExecutor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ExecutorService f191a;
    public final ArrayList<Runnable> b = new ArrayList<>();
    public Thread c;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Thread a() throws Exception {
        Thread threadCurrentThread = Thread.currentThread();
        this.c = threadCurrentThread;
        return threadCurrentThread;
    }

    public void execute(Runnable runnable) {
        ExecutorService executorService = this.f191a;
        if (executorService == null) {
            synchronized (this.b) {
                this.b.add(runnable);
            }
        } else {
            if (executorService.isShutdown()) {
                return;
            }
            Future<?> futureSubmit = this.f191a.submit(runnable);
            if (Thread.currentThread() != this.c) {
                try {
                    futureSubmit.get(60L, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void executeFirst(Runnable runnable) {
        if (this.f191a != null) {
            LoggerManager.getInstance().printErrorLogs("BpkCoreExecutor", "Cannot execute first when the executor is started");
            return;
        }
        synchronized (this.b) {
            this.b.add(0, runnable);
        }
    }

    public void start() {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.f191a = executorServiceNewSingleThreadExecutor;
        try {
            executorServiceNewSingleThreadExecutor.submit(new Callable() { // from class: tv.broadpeak.smartlib.engine.executor.-$$Lambda$CoreExecutor$zeTn-_TbtAqaQSI4_4ZCcnv9vh4
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.a();
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        synchronized (this.b) {
            Iterator<Runnable> it = this.b.iterator();
            while (it.hasNext()) {
                try {
                    this.f191a.submit(it.next()).get(60L, TimeUnit.SECONDS);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.b.clear();
        }
    }

    public void stop() {
        ExecutorService executorService = this.f191a;
        if (executorService != null) {
            executorService.shutdownNow();
        }
    }
}
