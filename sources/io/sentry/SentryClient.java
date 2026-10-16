package io.sentry;

import io.sentry.hints.DiskFlushNotification;
import io.sentry.protocol.Contexts;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.transport.ITransport;
import io.sentry.util.ApplyScopeUtils;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryClient implements ISentryClient {
    static final String SENTRY_PROTOCOL_VERSION = "7";
    private final SentryOptions options;
    private final SecureRandom random;
    private final ITransport transport;
    private final SortBreadcrumbsByDate sortBreadcrumbsByDate = new SortBreadcrumbsByDate();
    private boolean enabled = true;

    @Override // io.sentry.ISentryClient
    public boolean isEnabled() {
        return this.enabled;
    }

    /* JADX WARN: Multi-variable type inference failed */
    SentryClient(SentryOptions sentryOptions) {
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "SentryOptions is required.");
        ITransportFactory transportFactory = sentryOptions.getTransportFactory();
        if (transportFactory instanceof NoOpTransportFactory) {
            transportFactory = new AsyncHttpTransportFactory();
            sentryOptions.setTransportFactory(transportFactory);
        }
        this.transport = transportFactory.create(sentryOptions, new RequestDetailsResolver(sentryOptions).resolve());
        this.random = sentryOptions.getSampleRate() != null ? new SecureRandom() : null;
    }

    private boolean shouldApplyScopeData(SentryBaseEvent sentryBaseEvent, Object obj) {
        if (ApplyScopeUtils.shouldApplyScopeData(obj)) {
            return true;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Event was cached so not applying scope: %s", sentryBaseEvent.getEventId());
        return false;
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureEvent(SentryEvent sentryEvent, Scope scope, Object obj) {
        Session sessionUpdateSessionData;
        Objects.requireNonNull(sentryEvent, "SentryEvent is required.");
        this.options.getLogger().log(SentryLevel.DEBUG, "Capturing event: %s", sentryEvent.getEventId());
        if (shouldApplyScopeData(sentryEvent, obj) && (sentryEvent = applyScope(sentryEvent, scope, obj)) == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Event was dropped by applyScope", new Object[0]);
            return SentryId.EMPTY_ID;
        }
        SentryEvent sentryEventProcessEvent = processEvent(sentryEvent, obj, this.options.getEventProcessors());
        TraceState traceState = null;
        if (sentryEventProcessEvent != null) {
            sessionUpdateSessionData = updateSessionData(sentryEventProcessEvent, obj, scope);
            if (!sample()) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Event %s was dropped due to sampling decision.", sentryEventProcessEvent.getEventId());
                sentryEventProcessEvent = null;
            }
        } else {
            sessionUpdateSessionData = null;
        }
        if (sentryEventProcessEvent != null) {
            if (sentryEventProcessEvent.getThrowable() != null && this.options.containsIgnoredExceptionForType(sentryEventProcessEvent.getThrowable())) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Event was dropped as the exception %s is ignored", sentryEventProcessEvent.getThrowable().getClass());
                return SentryId.EMPTY_ID;
            }
            sentryEventProcessEvent = executeBeforeSend(sentryEventProcessEvent, obj);
            if (sentryEventProcessEvent == null) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Event was dropped by beforeSend", new Object[0]);
            }
        }
        SentryId eventId = SentryId.EMPTY_ID;
        if (sentryEventProcessEvent != null && sentryEventProcessEvent.getEventId() != null) {
            eventId = sentryEventProcessEvent.getEventId();
        }
        if (scope != null) {
            try {
                if (scope.getTransaction() != null) {
                    traceState = scope.getTransaction().traceState();
                }
            } catch (IOException e) {
                this.options.getLogger().log(SentryLevel.WARNING, e, "Capturing event %s failed.", eventId);
                return SentryId.EMPTY_ID;
            }
        }
        SentryEnvelope sentryEnvelopeBuildEnvelope = buildEnvelope(sentryEventProcessEvent, getAttachmentsFromScope(scope), sessionUpdateSessionData, traceState);
        if (sentryEnvelopeBuildEnvelope == null) {
            return eventId;
        }
        this.transport.send(sentryEnvelopeBuildEnvelope, obj);
        return eventId;
    }

    private List<Attachment> getAttachmentsFromScope(Scope scope) {
        if (scope != null) {
            return scope.getAttachments();
        }
        return null;
    }

    private SentryEnvelope buildEnvelope(SentryBaseEvent sentryBaseEvent, List<Attachment> list, Session session, TraceState traceState) throws IOException {
        SentryId eventId;
        ArrayList arrayList = new ArrayList();
        if (sentryBaseEvent != null) {
            arrayList.add(SentryEnvelopeItem.fromEvent(this.options.getSerializer(), sentryBaseEvent));
            eventId = sentryBaseEvent.getEventId();
        } else {
            eventId = null;
        }
        if (session != null) {
            arrayList.add(SentryEnvelopeItem.fromSession(this.options.getSerializer(), session));
        }
        if (list != null) {
            Iterator<Attachment> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(SentryEnvelopeItem.fromAttachment(it.next(), this.options.getMaxAttachmentSize()));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new SentryEnvelope(new SentryEnvelopeHeader(eventId, this.options.getSdkVersion(), traceState), arrayList);
    }

    private SentryEvent processEvent(SentryEvent sentryEvent, Object obj, List<EventProcessor> list) {
        for (EventProcessor eventProcessor : list) {
            try {
                sentryEvent = eventProcessor.process(sentryEvent, obj);
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, th, "An exception occurred while processing event by processor: %s", eventProcessor.getClass().getName());
            }
            if (sentryEvent == null) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Event was dropped by a processor: %s", eventProcessor.getClass().getName());
                break;
            }
        }
        return sentryEvent;
    }

    private SentryTransaction processTransaction(SentryTransaction sentryTransaction, Object obj, List<EventProcessor> list) {
        for (EventProcessor eventProcessor : list) {
            try {
                sentryTransaction = eventProcessor.process(sentryTransaction, obj);
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, th, "An exception occurred while processing transaction by processor: %s", eventProcessor.getClass().getName());
            }
            if (sentryTransaction == null) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Transaction was dropped by a processor: %s", eventProcessor.getClass().getName());
                break;
            }
        }
        return sentryTransaction;
    }

    @Override // io.sentry.ISentryClient
    public void captureUserFeedback(UserFeedback userFeedback) {
        Objects.requireNonNull(userFeedback, "SentryEvent is required.");
        if (SentryId.EMPTY_ID.equals(userFeedback.getEventId())) {
            this.options.getLogger().log(SentryLevel.WARNING, "Capturing userFeedback without a Sentry Id.", new Object[0]);
            return;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Capturing userFeedback: %s", userFeedback.getEventId());
        try {
            this.transport.send(buildEnvelope(userFeedback));
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.WARNING, e, "Capturing user feedback %s failed.", userFeedback.getEventId());
        }
    }

    private SentryEnvelope buildEnvelope(UserFeedback userFeedback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(SentryEnvelopeItem.fromUserFeedback(this.options.getSerializer(), userFeedback));
        return new SentryEnvelope(new SentryEnvelopeHeader(userFeedback.getEventId(), this.options.getSdkVersion()), arrayList);
    }

    Session updateSessionData(final SentryEvent sentryEvent, final Object obj, Scope scope) {
        if (ApplyScopeUtils.shouldApplyScopeData(obj)) {
            if (scope != null) {
                return scope.withSession(new Scope.IWithSession() { // from class: io.sentry.-$$Lambda$SentryClient$95WLIRfJlDw7w3H0oQsRkrt03kA
                    @Override // io.sentry.Scope.IWithSession
                    public final void accept(Session session) {
                        this.f$0.lambda$updateSessionData$0$SentryClient(sentryEvent, obj, session);
                    }
                });
            }
            this.options.getLogger().log(SentryLevel.INFO, "Scope is null on client.captureEvent", new Object[0]);
        }
        return null;
    }

    public /* synthetic */ void lambda$updateSessionData$0$SentryClient(SentryEvent sentryEvent, Object obj, Session session) {
        if (session != null) {
            String str = null;
            Session.State state = sentryEvent.isCrashed() ? Session.State.Crashed : null;
            boolean z = Session.State.Crashed == state || sentryEvent.isErrored();
            if (sentryEvent.getRequest() != null && sentryEvent.getRequest().getHeaders() != null && sentryEvent.getRequest().getHeaders().containsKey("user-agent")) {
                str = sentryEvent.getRequest().getHeaders().get("user-agent");
            }
            if (session.update(state, str, z) && (obj instanceof DiskFlushNotification)) {
                session.end();
                return;
            }
            return;
        }
        this.options.getLogger().log(SentryLevel.INFO, "Session is null on scope.withSession", new Object[0]);
    }

    @Override // io.sentry.ISentryClient
    public void captureSession(Session session, Object obj) {
        Objects.requireNonNull(session, "Session is required.");
        if (session.getRelease() == null || session.getRelease().isEmpty()) {
            this.options.getLogger().log(SentryLevel.WARNING, "Sessions can't be captured without setting a release.", new Object[0]);
            return;
        }
        try {
            captureEnvelope(SentryEnvelope.from(this.options.getSerializer(), session, this.options.getSdkVersion()), obj);
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to capture session.", e);
        }
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Object obj) {
        Objects.requireNonNull(sentryEnvelope, "SentryEnvelope is required.");
        try {
            this.transport.send(sentryEnvelope, obj);
            SentryId eventId = sentryEnvelope.getHeader().getEventId();
            return eventId != null ? eventId : SentryId.EMPTY_ID;
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to capture envelope.", e);
            return SentryId.EMPTY_ID;
        }
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureTransaction(SentryTransaction sentryTransaction, TraceState traceState, Scope scope, Object obj) {
        Objects.requireNonNull(sentryTransaction, "Transaction is required.");
        this.options.getLogger().log(SentryLevel.DEBUG, "Capturing transaction: %s", sentryTransaction.getEventId());
        SentryId eventId = SentryId.EMPTY_ID;
        if (sentryTransaction.getEventId() != null) {
            eventId = sentryTransaction.getEventId();
        }
        if (shouldApplyScopeData(sentryTransaction, obj)) {
            sentryTransaction = (SentryTransaction) applyScope(sentryTransaction, scope);
            if (sentryTransaction != null && scope != null) {
                sentryTransaction = processTransaction(sentryTransaction, obj, scope.getEventProcessors());
            }
            if (sentryTransaction == null) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Transaction was dropped by applyScope", new Object[0]);
            }
        }
        if (sentryTransaction != null) {
            sentryTransaction = processTransaction(sentryTransaction, obj, this.options.getEventProcessors());
        }
        if (sentryTransaction == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Transaction was dropped by Event processors.", new Object[0]);
            return SentryId.EMPTY_ID;
        }
        try {
            SentryEnvelope sentryEnvelopeBuildEnvelope = buildEnvelope(sentryTransaction, filterForTransaction(getAttachmentsFromScope(scope)), null, traceState);
            if (sentryEnvelopeBuildEnvelope != null) {
                this.transport.send(sentryEnvelopeBuildEnvelope, obj);
            } else {
                eventId = SentryId.EMPTY_ID;
            }
            return eventId;
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.WARNING, e, "Capturing transaction %s failed.", eventId);
            return SentryId.EMPTY_ID;
        }
    }

    private List<Attachment> filterForTransaction(List<Attachment> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Attachment attachment : list) {
            if (attachment.isAddToTransactions()) {
                arrayList.add(attachment);
            }
        }
        return arrayList;
    }

    private SentryEvent applyScope(SentryEvent sentryEvent, Scope scope, Object obj) {
        if (scope == null) {
            return sentryEvent;
        }
        applyScope(sentryEvent, scope);
        if (sentryEvent.getTransaction() == null) {
            sentryEvent.setTransaction(scope.getTransactionName());
        }
        if (sentryEvent.getFingerprints() == null) {
            sentryEvent.setFingerprints(scope.getFingerprint());
        }
        if (scope.getLevel() != null) {
            sentryEvent.setLevel(scope.getLevel());
        }
        ISpan span = scope.getSpan();
        if (sentryEvent.getContexts().getTrace() == null && span != null) {
            sentryEvent.getContexts().setTrace(span.getSpanContext());
        }
        return processEvent(sentryEvent, obj, scope.getEventProcessors());
    }

    private <T extends SentryBaseEvent> T applyScope(T t, Scope scope) {
        if (scope != null) {
            if (t.getRequest() == null) {
                t.setRequest(scope.getRequest());
            }
            if (t.getUser() == null) {
                t.setUser(scope.getUser());
            }
            if (t.getTags() == null) {
                t.setTags(new HashMap(scope.getTags()));
            } else {
                for (Map.Entry<String, String> entry : scope.getTags().entrySet()) {
                    if (!t.getTags().containsKey(entry.getKey())) {
                        t.getTags().put(entry.getKey(), entry.getValue());
                    }
                }
            }
            if (t.getBreadcrumbs() == null) {
                t.setBreadcrumbs(new ArrayList(scope.getBreadcrumbs()));
            } else {
                sortBreadcrumbsByDate(t, scope.getBreadcrumbs());
            }
            if (t.getExtras() == null) {
                t.setExtras(new HashMap(scope.getExtras()));
            } else {
                for (Map.Entry<String, Object> entry2 : scope.getExtras().entrySet()) {
                    if (!t.getExtras().containsKey(entry2.getKey())) {
                        t.getExtras().put(entry2.getKey(), entry2.getValue());
                    }
                }
            }
            Contexts contexts = t.getContexts();
            for (Map.Entry<String, Object> entry3 : new Contexts(scope.getContexts()).entrySet()) {
                if (!contexts.containsKey(entry3.getKey())) {
                    contexts.put(entry3.getKey(), entry3.getValue());
                }
            }
        }
        return t;
    }

    private void sortBreadcrumbsByDate(SentryBaseEvent sentryBaseEvent, Collection<Breadcrumb> collection) {
        List<Breadcrumb> breadcrumbs = sentryBaseEvent.getBreadcrumbs();
        if (breadcrumbs == null || collection.isEmpty()) {
            return;
        }
        breadcrumbs.addAll(collection);
        Collections.sort(breadcrumbs, this.sortBreadcrumbsByDate);
    }

    private SentryEvent executeBeforeSend(SentryEvent sentryEvent, Object obj) {
        SentryOptions.BeforeSendCallback beforeSend = this.options.getBeforeSend();
        if (beforeSend == null) {
            return sentryEvent;
        }
        try {
            return beforeSend.execute(sentryEvent, obj);
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "The BeforeSend callback threw an exception. It will be added as breadcrumb and continue.", th);
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setMessage("BeforeSend callback failed.");
            breadcrumb.setCategory("SentryClient");
            breadcrumb.setLevel(SentryLevel.ERROR);
            if (th.getMessage() != null) {
                breadcrumb.setData("sentry:message", th.getMessage());
            }
            sentryEvent.addBreadcrumb(breadcrumb);
            return sentryEvent;
        }
    }

    @Override // io.sentry.ISentryClient
    public void close() {
        this.options.getLogger().log(SentryLevel.INFO, "Closing SentryClient.", new Object[0]);
        try {
            flush(this.options.getShutdownTimeout());
            this.transport.close();
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.WARNING, "Failed to close the connection to the Sentry Server.", e);
        }
        for (EventProcessor eventProcessor : this.options.getEventProcessors()) {
            if (eventProcessor instanceof Closeable) {
                try {
                    ((Closeable) eventProcessor).close();
                } catch (IOException e2) {
                    this.options.getLogger().log(SentryLevel.WARNING, "Failed to close the event processor {}.", eventProcessor, e2);
                }
            }
        }
        this.enabled = false;
    }

    @Override // io.sentry.ISentryClient
    public void flush(long j) {
        this.transport.flush(j);
    }

    private boolean sample() {
        return this.options.getSampleRate() == null || this.random == null || this.options.getSampleRate().doubleValue() >= this.random.nextDouble();
    }

    private static final class SortBreadcrumbsByDate implements Comparator<Breadcrumb> {
        private SortBreadcrumbsByDate() {
        }

        @Override // java.util.Comparator
        public int compare(Breadcrumb breadcrumb, Breadcrumb breadcrumb2) {
            return breadcrumb.getTimestamp().compareTo(breadcrumb2.getTimestamp());
        }
    }
}
