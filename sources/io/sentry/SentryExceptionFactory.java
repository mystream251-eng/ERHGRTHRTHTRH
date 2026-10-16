package io.sentry;

import io.sentry.exception.ExceptionMechanismException;
import io.sentry.protocol.Mechanism;
import io.sentry.protocol.SentryException;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.protocol.SentryStackTrace;
import io.sentry.util.Objects;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class SentryExceptionFactory {
    private final SentryStackTraceFactory sentryStackTraceFactory;

    public SentryExceptionFactory(SentryStackTraceFactory sentryStackTraceFactory) {
        this.sentryStackTraceFactory = (SentryStackTraceFactory) Objects.requireNonNull(sentryStackTraceFactory, "The SentryStackTraceFactory is required.");
    }

    List<SentryException> getSentryExceptions(Throwable th) {
        return getSentryExceptions(extractExceptionQueue(th));
    }

    private List<SentryException> getSentryExceptions(Deque<SentryException> deque) {
        return new ArrayList(deque);
    }

    private SentryException getSentryException(Throwable th, Mechanism mechanism, Thread thread, boolean z) {
        Package r0 = th.getClass().getPackage();
        String name = th.getClass().getName();
        SentryException sentryException = new SentryException();
        String message = th.getMessage();
        if (r0 != null) {
            name = name.replace(r0.getName() + ".", "");
        }
        String name2 = r0 != null ? r0.getName() : null;
        List<SentryStackFrame> stackFrames = this.sentryStackTraceFactory.getStackFrames(th.getStackTrace());
        if (stackFrames != null && !stackFrames.isEmpty()) {
            SentryStackTrace sentryStackTrace = new SentryStackTrace(stackFrames);
            if (z) {
                sentryStackTrace.setSnapshot(true);
            }
            sentryException.setStacktrace(sentryStackTrace);
        }
        if (thread != null) {
            sentryException.setThreadId(Long.valueOf(thread.getId()));
        }
        sentryException.setType(name);
        sentryException.setMechanism(mechanism);
        sentryException.setModule(name2);
        sentryException.setValue(message);
        return sentryException;
    }

    Deque<SentryException> extractExceptionQueue(Throwable th) {
        Thread threadCurrentThread;
        boolean zIsSnapshot;
        Mechanism exceptionMechanism;
        ArrayDeque arrayDeque = new ArrayDeque();
        HashSet hashSet = new HashSet();
        while (th != null && hashSet.add(th)) {
            if (th instanceof ExceptionMechanismException) {
                ExceptionMechanismException exceptionMechanismException = (ExceptionMechanismException) th;
                exceptionMechanism = exceptionMechanismException.getExceptionMechanism();
                Throwable throwable = exceptionMechanismException.getThrowable();
                threadCurrentThread = exceptionMechanismException.getThread();
                zIsSnapshot = exceptionMechanismException.isSnapshot();
                th = throwable;
            } else {
                threadCurrentThread = Thread.currentThread();
                zIsSnapshot = false;
                exceptionMechanism = null;
            }
            arrayDeque.addFirst(getSentryException(th, exceptionMechanism, threadCurrentThread, zIsSnapshot));
            th = th.getCause();
        }
        return arrayDeque;
    }
}
