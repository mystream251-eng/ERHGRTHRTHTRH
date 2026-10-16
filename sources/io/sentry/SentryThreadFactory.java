package io.sentry;

import io.sentry.protocol.SentryStackFrame;
import io.sentry.protocol.SentryStackTrace;
import io.sentry.protocol.SentryThread;
import io.sentry.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class SentryThreadFactory {
    private final SentryOptions options;
    private final SentryStackTraceFactory sentryStackTraceFactory;

    public SentryThreadFactory(SentryStackTraceFactory sentryStackTraceFactory, SentryOptions sentryOptions) {
        this.sentryStackTraceFactory = (SentryStackTraceFactory) Objects.requireNonNull(sentryStackTraceFactory, "The SentryStackTraceFactory is required.");
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "The SentryOptions is required");
    }

    List<SentryThread> getCurrentThread() {
        HashMap map = new HashMap();
        Thread threadCurrentThread = Thread.currentThread();
        map.put(threadCurrentThread, threadCurrentThread.getStackTrace());
        return getCurrentThreads(map, null);
    }

    List<SentryThread> getCurrentThreads(List<Long> list) {
        return getCurrentThreads(Thread.getAllStackTraces(), list);
    }

    List<SentryThread> getCurrentThreads(Map<Thread, StackTraceElement[]> map, List<Long> list) {
        Thread threadCurrentThread = Thread.currentThread();
        if (map.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!map.containsKey(threadCurrentThread)) {
            map.put(threadCurrentThread, threadCurrentThread.getStackTrace());
        }
        for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
            Thread key = entry.getKey();
            arrayList.add(getSentryThread(key == threadCurrentThread || (list != null && list.contains(Long.valueOf(key.getId()))), entry.getValue(), entry.getKey()));
        }
        return arrayList;
    }

    private SentryThread getSentryThread(boolean z, StackTraceElement[] stackTraceElementArr, Thread thread) {
        SentryThread sentryThread = new SentryThread();
        sentryThread.setName(thread.getName());
        sentryThread.setPriority(Integer.valueOf(thread.getPriority()));
        sentryThread.setId(Long.valueOf(thread.getId()));
        sentryThread.setDaemon(Boolean.valueOf(thread.isDaemon()));
        sentryThread.setState(thread.getState().name());
        sentryThread.setCrashed(Boolean.valueOf(z));
        List<SentryStackFrame> stackFrames = this.sentryStackTraceFactory.getStackFrames(stackTraceElementArr);
        if (this.options.isAttachStacktrace() && stackFrames != null && !stackFrames.isEmpty()) {
            SentryStackTrace sentryStackTrace = new SentryStackTrace(stackFrames);
            sentryStackTrace.setSnapshot(true);
            sentryThread.setStacktrace(sentryStackTrace);
        }
        return sentryThread;
    }
}
