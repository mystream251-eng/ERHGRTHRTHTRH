package tv.broadpeak.smartlib.engine.executor;

import android.util.SparseArray;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.common.internal.ImagesContract;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.broadpeak.motorjs.JSContext;
import tv.broadpeak.motorjs.QuickJSUtils;
import tv.broadpeak.smartlib.ad.pal.AdPalSession;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.system.RequestHandler;

/* JADX INFO: loaded from: classes3.dex */
public class CoreWorker {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CoreEngine f192a;
    public JSContext b;
    public CoreExecutor c;
    public JSObject d;
    public final SparseArray<AsyncTask> e = new SparseArray<>();

    public class SyncTask extends AsyncTask {
        public SyncTask(int i, String str, long j, long j2) {
            super(i, str, j, j2);
        }

        @Override // tv.broadpeak.smartlib.engine.executor.CoreWorker.AsyncTask
        public void execute() {
            WorkerResult workerResult;
            if (this.mTimeout >= 0) {
                AsyncTask.TimeoutThread timeoutThread = new AsyncTask.TimeoutThread();
                this.mTimeoutThread = timeoutThread;
                timeoutThread.start();
            }
            try {
                long j = this.mDelay;
                if (j > 0) {
                    Thread.sleep(j);
                }
                if (this.mActive.get()) {
                    JSFunction jSFunction = (JSFunction) CoreWorker.this.d.getProperty("requestInfo");
                    CoreWorker coreWorker = CoreWorker.this;
                    JSObject jSObject = (JSObject) jSFunction.invoke(coreWorker.d, new JSValue[]{coreWorker.b.createJSNumber(this.mId)});
                    QuickJSUtils.executePendingJobs(CoreWorker.this.b);
                    this.mInfo = QuickJSUtils.toMap(CoreWorker.this.b, jSObject);
                }
                if (this.mActive.get()) {
                    try {
                        this.mResult = process(this.mCommand, this.mInfo, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (this.mActive.get() && (workerResult = this.mResult) != null) {
                        JSObject jSObject2 = workerResult.toJSObject(CoreWorker.this.b);
                        JSFunction jSFunction2 = (JSFunction) CoreWorker.this.d.getProperty("result").cast(JSFunction.class);
                        CoreWorker coreWorker2 = CoreWorker.this;
                        jSFunction2.invoke(coreWorker2.d, new JSValue[]{coreWorker2.b.createJSNumber(this.mId), jSObject2});
                        QuickJSUtils.executePendingJobs(CoreWorker.this.b);
                    }
                } else if (this.mActive.get()) {
                    JSObject jSObject3 = workerResult.toJSObject(CoreWorker.this.b);
                    JSFunction jSFunction3 = (JSFunction) CoreWorker.this.d.getProperty("result").cast(JSFunction.class);
                    CoreWorker coreWorker3 = CoreWorker.this;
                    jSFunction3.invoke(coreWorker3.d, new JSValue[]{coreWorker3.b.createJSNumber(this.mId), jSObject3});
                    QuickJSUtils.executePendingJobs(CoreWorker.this.b);
                }
            } catch (InterruptedException unused) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            synchronized (CoreWorker.this.e) {
                if (CoreWorker.this.e.get(this.mId) != null) {
                    CoreWorker.this.e.remove(this.mId);
                }
            }
            AsyncTask.TimeoutThread timeoutThread2 = this.mTimeoutThread;
            if (timeoutThread2 != null) {
                timeoutThread2.cancel();
            }
        }

        @Override // tv.broadpeak.smartlib.engine.executor.CoreWorker.AsyncTask, java.lang.Thread, java.lang.Runnable
        public void run() {
        }

        @Override // java.lang.Thread
        public void start() {
            execute();
        }
    }

    public interface WorkerResult {
        JSObject toJSObject(JSContext jSContext);
    }

    public interface WorkerTask {
        void cancel();
    }

    public CoreWorker(CoreEngine coreEngine) {
        this.f192a = coreEngine;
        this.b = coreEngine.getJSContext();
        this.c = this.f192a.getCoreExecutor();
        this.d = this.f192a.getCoreSingleton().getSingleton("JobManager");
        this.b.evaluate("CoreWorker = {};", "coreworker.js");
        JSObject jSObject = (JSObject) this.b.getGlobalObject().getProperty("CoreWorker");
        try {
            Class cls = Integer.TYPE;
            jSObject.setProperty("async", this.b.createJSFunction(this, Method.create(Void.class, CoreWorker.class.getMethod("async", cls, String.class, cls, cls))));
            jSObject.setProperty("sync", this.b.createJSFunction(this, Method.create(Void.class, CoreWorker.class.getMethod("sync", cls, String.class, cls, cls))));
            jSObject.setProperty("interrupt", this.b.createJSFunction(this, Method.create(Void.class, CoreWorker.class.getMethod("interrupt", cls))));
            jSObject.setProperty("cancel", this.b.createJSFunction(this, Method.create(Void.class, CoreWorker.class.getMethod("cancel", cls))));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void async(int i, String str, int i2, int i3) {
        AsyncTask asyncTask = new AsyncTask(i, str, i2, i3);
        asyncTask.start();
        synchronized (this.e) {
            cleanFinishedTasks();
            this.e.put(i, asyncTask);
        }
    }

    public void cancel(int i) {
        synchronized (this.e) {
            AsyncTask asyncTask = this.e.get(i);
            if (asyncTask != null) {
                asyncTask.cancel();
                this.e.remove(i);
            }
        }
    }

    public void cleanFinishedTasks() {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            SparseArray<AsyncTask> sparseArray = this.e;
            if (sparseArray.get(sparseArray.keyAt(size)).isFinished()) {
                this.e.removeAt(size);
            }
        }
    }

    public void interrupt(int i) {
        synchronized (this.e) {
            AsyncTask asyncTask = this.e.get(i);
            if (asyncTask != null) {
                asyncTask.interruptTask();
            }
        }
    }

    public void sync(int i, String str, int i2, int i3) {
        SyncTask syncTask = new SyncTask(i, str, i2, i3);
        syncTask.start();
        synchronized (this.e) {
            cleanFinishedTasks();
            this.e.put(i, syncTask);
        }
    }

    public class AsyncTask extends Thread {
        public String mCommand;
        public long mDelay;
        public int mId;
        public Map<String, Object> mInfo;
        public WorkerResult mResult;
        public WorkerTask mTask;
        public long mTimeout;
        public TimeoutThread mTimeoutThread;
        public AtomicBoolean mActive = new AtomicBoolean(true);
        public long mDate = System.currentTimeMillis();

        public class TimeoutThread extends Thread {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f194a = true;

            public TimeoutThread() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void a() {
                JSFunction jSFunction = (JSFunction) CoreWorker.this.d.getProperty("timeout").cast(JSFunction.class);
                AsyncTask asyncTask = AsyncTask.this;
                CoreWorker coreWorker = CoreWorker.this;
                jSFunction.invoke(coreWorker.d, new JSValue[]{coreWorker.b.createJSNumber(asyncTask.mId)});
                QuickJSUtils.executePendingJobs(CoreWorker.this.b);
            }

            public void cancel() {
                AsyncTask.this.mActive.set(false);
                this.f194a = false;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(AsyncTask.this.mTimeout);
                    if (this.f194a) {
                        cancel();
                        CoreWorker.this.c.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.executor.-$$Lambda$CoreWorker$AsyncTask$TimeoutThread$HjMGb5q4dn7nZOZhgC2f_wmm92c
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.a();
                            }
                        });
                    }
                } catch (InterruptedException unused) {
                }
            }
        }

        public AsyncTask(int i, String str, long j, long j2) {
            this.mId = i;
            this.mCommand = str;
            this.mDelay = j;
            this.mTimeout = j2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ JSObject a(AdPalSession adPalSession, JSContext jSContext) {
            JSObject jSObjectCreateJSObject = CoreWorker.this.b.createJSObject();
            try {
                if (adPalSession != null) {
                    jSObjectCreateJSObject.setProperty("adPalSession", adPalSession.createJSObject(CoreWorker.this.b));
                } else {
                    jSObjectCreateJSObject.setProperty("adPalSession", CoreWorker.this.b.createJSUndefined());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jSObjectCreateJSObject;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            JSObject jSObject = this.mResult.toJSObject(CoreWorker.this.b);
            JSFunction jSFunction = (JSFunction) CoreWorker.this.d.getProperty("result").cast(JSFunction.class);
            CoreWorker coreWorker = CoreWorker.this;
            jSFunction.invoke(coreWorker.d, new JSValue[]{coreWorker.b.createJSNumber(this.mId), jSObject});
            QuickJSUtils.executePendingJobs(CoreWorker.this.b);
        }

        public void cancel() {
            this.mActive.set(false);
            interrupt();
            TimeoutThread timeoutThread = this.mTimeoutThread;
            if (timeoutThread != null) {
                timeoutThread.cancel();
                this.mTimeoutThread.interrupt();
            }
        }

        public void execute() {
            if (this.mTimeout >= 0) {
                TimeoutThread timeoutThread = new TimeoutThread();
                this.mTimeoutThread = timeoutThread;
                timeoutThread.start();
            }
            try {
                long j = this.mDelay;
                if (j > 0) {
                    Thread.sleep(j);
                }
                if (this.mActive.get() && requestInfoNeeded(this.mCommand)) {
                    CoreWorker.this.c.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.executor.-$$Lambda$CoreWorker$AsyncTask$soVe5cS_JVzIF6CuDp5TLOB0f5w
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.a();
                        }
                    });
                }
                if (this.mActive.get()) {
                    try {
                        this.mResult = process(this.mCommand, this.mInfo, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (this.mActive.get() && this.mResult != null) {
                        CoreWorker.this.c.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.executor.-$$Lambda$CoreWorker$AsyncTask$5X0T5xHXi8RjmCIhIPmow9G4lSY
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.b();
                            }
                        });
                    }
                } else if (this.mActive.get()) {
                    CoreWorker.this.c.execute(new Runnable() { // from class: tv.broadpeak.smartlib.engine.executor.-$$Lambda$CoreWorker$AsyncTask$5X0T5xHXi8RjmCIhIPmow9G4lSY
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.b();
                        }
                    });
                }
            } catch (InterruptedException unused) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            synchronized (CoreWorker.this.e) {
                if (CoreWorker.this.e.get(this.mId) != null) {
                    CoreWorker.this.e.remove(this.mId);
                }
            }
            TimeoutThread timeoutThread2 = this.mTimeoutThread;
            if (timeoutThread2 != null) {
                timeoutThread2.cancel();
            }
        }

        public void interruptTask() {
            WorkerTask workerTask = this.mTask;
            if (workerTask != null) {
                workerTask.cancel();
            }
        }

        public boolean isFinished() {
            return (this.mActive.get() && isAlive() && System.currentTimeMillis() - this.mDate <= (this.mDelay + this.mTimeout) + DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS) ? false : true;
        }

        /* JADX WARN: Code duplicated, block: B:19:0x0038  */
        public WorkerResult process(String str, Map<String, Object> map, boolean z) {
            byte b;
            str.hashCode();
            int iHashCode = str.hashCode();
            if (iHashCode != -989767814) {
                if (iHashCode != 102230) {
                    if (iHashCode == 3446944 && str.equals("post")) {
                        b = 2;
                    } else {
                        b = -1;
                    }
                } else if (str.equals("get")) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (str.equals("waitNonce")) {
                b = 0;
            } else {
                b = -1;
            }
            if (b == 0) {
                final AdPalSession adPalSessionGenerateAdPalSession = CoreWorker.this.f192a.getInternalAdManager().generateAdPalSession(z);
                return new WorkerResult() { // from class: tv.broadpeak.smartlib.engine.executor.-$$Lambda$CoreWorker$AsyncTask$adw7Ar9fws28RVqVo1gLfyQxC-8
                    @Override // tv.broadpeak.smartlib.engine.executor.CoreWorker.WorkerResult
                    public final JSObject toJSObject(JSContext jSContext) {
                        return this.f$0.a(adPalSessionGenerateAdPalSession, jSContext);
                    }
                };
            }
            if (b == 1) {
                RequestHandler.GetRequest getRequestCreateGetRequest = CoreWorker.this.f192a.getRequestHandler().createGetRequest((String) map.get(ImagesContract.URL), (Map) map.get("headers"), (int) ((Long) map.get("timeout")).longValue());
                this.mTask = getRequestCreateGetRequest;
                return getRequestCreateGetRequest.execute();
            }
            if (b != 2) {
                return new RequestHandler.RequestResult(null, null, -1, "");
            }
            RequestHandler.PostRequest postRequestCreatePostRequest = CoreWorker.this.f192a.getRequestHandler().createPostRequest((String) map.get(ImagesContract.URL), (Map) map.get("headers"), (String) map.get(TtmlNode.TAG_BODY), (int) ((Long) map.get("timeout")).longValue());
            this.mTask = postRequestCreatePostRequest;
            return postRequestCreatePostRequest.execute();
        }

        public boolean requestInfoNeeded(String str) {
            str.hashCode();
            return str.equals("get") || str.equals("post");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            execute();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a() {
            JSFunction jSFunction = (JSFunction) CoreWorker.this.d.getProperty("requestInfo").cast(JSFunction.class);
            CoreWorker coreWorker = CoreWorker.this;
            JSObject jSObject = (JSObject) jSFunction.invoke(coreWorker.d, new JSValue[]{coreWorker.b.createJSNumber(this.mId)}).cast(JSObject.class);
            QuickJSUtils.executePendingJobs(CoreWorker.this.b);
            this.mInfo = QuickJSUtils.toMap(CoreWorker.this.b, jSObject);
        }
    }
}
