package io.sentry;

import io.sentry.protocol.Contexts;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import io.sentry.util.Objects;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryTracer implements ITransaction {
    private final List<Span> children;
    private final Contexts contexts;
    private final SentryId eventId;
    private FinishStatus finishStatus;
    private final IHub hub;
    private String name;
    private Request request;
    private final Span root;
    private TraceState traceState;
    private final TransactionFinishedCallback transactionFinishedCallback;
    private final boolean waitForChildren;

    public SentryTracer(TransactionContext transactionContext, IHub iHub) {
        this(transactionContext, iHub, null);
    }

    public SentryTracer(TransactionContext transactionContext, IHub iHub, boolean z, TransactionFinishedCallback transactionFinishedCallback) {
        this(transactionContext, iHub, null, z, transactionFinishedCallback);
    }

    SentryTracer(TransactionContext transactionContext, IHub iHub, Date date) {
        this(transactionContext, iHub, date, false, null);
    }

    SentryTracer(TransactionContext transactionContext, IHub iHub, Date date, boolean z, TransactionFinishedCallback transactionFinishedCallback) {
        this.eventId = new SentryId();
        this.children = new CopyOnWriteArrayList();
        this.contexts = new Contexts();
        this.finishStatus = FinishStatus.NOT_FINISHED;
        Objects.requireNonNull(transactionContext, "context is required");
        Objects.requireNonNull(iHub, "hub is required");
        this.root = new Span(transactionContext, this, iHub, date);
        this.name = transactionContext.getName();
        this.hub = iHub;
        this.waitForChildren = z;
        this.transactionFinishedCallback = transactionFinishedCallback;
    }

    public List<Span> getChildren() {
        return this.children;
    }

    public Date getStartTimestamp() {
        return this.root.getStartTimestamp();
    }

    public Date getTimestamp() {
        return this.root.getTimestamp();
    }

    ISpan startChild(SpanId spanId, String str, String str2) {
        ISpan iSpanCreateChild = createChild(spanId, str);
        iSpanCreateChild.setDescription(str2);
        return iSpanCreateChild;
    }

    ISpan startChild(SpanId spanId, String str, String str2, Date date) {
        return createChild(spanId, str, str2, date);
    }

    private ISpan createChild(SpanId spanId, String str) {
        return createChild(spanId, str, null, null);
    }

    private ISpan createChild(SpanId spanId, String str, String str2, Date date) {
        Objects.requireNonNull(spanId, "parentSpanId is required");
        Objects.requireNonNull(str, "operation is required");
        Span span = new Span(this.root.getTraceId(), spanId, this, str, this.hub, date, new SpanFinishedCallback() { // from class: io.sentry.-$$Lambda$SentryTracer$f3vcQARxHmoGXt8ELM-5aZrdklg
            @Override // io.sentry.SpanFinishedCallback
            public final void execute(Span span2) {
                this.f$0.lambda$createChild$0$SentryTracer(span2);
            }
        });
        span.setDescription(str2);
        this.children.add(span);
        return span;
    }

    public /* synthetic */ void lambda$createChild$0$SentryTracer(Span span) {
        FinishStatus finishStatus = this.finishStatus;
        if (finishStatus.isFinishing) {
            finish(finishStatus.spanStatus);
        }
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(String str) {
        return startChild(str, (String) null);
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(String str, String str2, Date date) {
        return createChild(str, str2, date);
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(String str, String str2) {
        return createChild(str, str2, null);
    }

    private ISpan createChild(String str, String str2, Date date) {
        if (this.children.size() < this.hub.getOptions().getMaxSpans()) {
            return this.root.startChild(str, str2, date);
        }
        this.hub.getOptions().getLogger().log(SentryLevel.WARNING, "Span operation: %s, description: %s dropped due to limit reached. Returning NoOpSpan.", str, str2);
        return NoOpSpan.getInstance();
    }

    @Override // io.sentry.ISpan
    public SentryTraceHeader toSentryTrace() {
        return this.root.toSentryTrace();
    }

    @Override // io.sentry.ISpan
    public void finish() {
        finish(getStatus());
    }

    @Override // io.sentry.ISpan
    public void finish(SpanStatus spanStatus) {
        this.finishStatus = FinishStatus.finishing(spanStatus);
        if (this.root.isFinished()) {
            return;
        }
        if (!this.waitForChildren || hasAllChildrenFinished()) {
            this.root.finish(this.finishStatus.spanStatus);
            Date timestamp = this.root.getTimestamp();
            if (timestamp == null) {
                this.hub.getOptions().getLogger().log(SentryLevel.WARNING, "Root span - op: %s, description: %s - has no timestamp set, when finishing unfinished spans.", this.root.getOperation(), this.root.getDescription());
                timestamp = DateUtils.getCurrentDateTime();
            }
            for (Span span : this.children) {
                if (!span.isFinished()) {
                    span.finish(SpanStatus.DEADLINE_EXCEEDED, timestamp);
                }
            }
            this.hub.configureScope(new ScopeCallback() { // from class: io.sentry.-$$Lambda$SentryTracer$fsRFT3NJh4Ac7U27cI9laNkiO_A
                @Override // io.sentry.ScopeCallback
                public final void run(Scope scope) {
                    this.f$0.lambda$finish$2$SentryTracer(scope);
                }
            });
            SentryTransaction sentryTransaction = new SentryTransaction(this);
            TransactionFinishedCallback transactionFinishedCallback = this.transactionFinishedCallback;
            if (transactionFinishedCallback != null) {
                transactionFinishedCallback.execute(this);
            }
            this.hub.captureTransaction(sentryTransaction, traceState());
        }
    }

    public /* synthetic */ void lambda$finish$2$SentryTracer(final Scope scope) {
        scope.withTransaction(new Scope.IWithTransaction() { // from class: io.sentry.-$$Lambda$SentryTracer$9mivLnl9DMJbdRbldck18pIk3Oc
            @Override // io.sentry.Scope.IWithTransaction
            public final void accept(ITransaction iTransaction) {
                this.f$0.lambda$finish$1$SentryTracer(scope, iTransaction);
            }
        });
    }

    public /* synthetic */ void lambda$finish$1$SentryTracer(Scope scope, ITransaction iTransaction) {
        if (iTransaction == this) {
            scope.clearTransaction();
        }
    }

    @Override // io.sentry.ISpan
    public TraceState traceState() {
        TraceState traceState;
        if (!this.hub.getOptions().isTraceSampling()) {
            return null;
        }
        synchronized (this) {
            if (this.traceState == null) {
                final AtomicReference atomicReference = new AtomicReference();
                this.hub.configureScope(new ScopeCallback() { // from class: io.sentry.-$$Lambda$SentryTracer$_kmJUs3D51c4mCuNE8H2TCakJL8
                    @Override // io.sentry.ScopeCallback
                    public final void run(Scope scope) {
                        atomicReference.set(scope.getUser());
                    }
                });
                this.traceState = new TraceState(this, (User) atomicReference.get(), this.hub.getOptions());
            }
            traceState = this.traceState;
        }
        return traceState;
    }

    @Override // io.sentry.ISpan
    public TraceStateHeader toTraceStateHeader() {
        TraceState traceState = traceState();
        if (!this.hub.getOptions().isTraceSampling() || traceState == null) {
            return null;
        }
        return TraceStateHeader.fromTraceState(traceState, this.hub.getOptions().getSerializer(), this.hub.getOptions().getLogger());
    }

    private boolean hasAllChildrenFinished() {
        ArrayList arrayList = new ArrayList(this.children);
        if (arrayList.isEmpty()) {
            return true;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((Span) it.next()).isFinished()) {
                return false;
            }
        }
        return true;
    }

    @Override // io.sentry.ISpan
    public void setOperation(String str) {
        this.root.setOperation(str);
    }

    @Override // io.sentry.ISpan
    public String getOperation() {
        return this.root.getOperation();
    }

    @Override // io.sentry.ISpan
    public void setDescription(String str) {
        this.root.setDescription(str);
    }

    @Override // io.sentry.ISpan
    public String getDescription() {
        return this.root.getDescription();
    }

    @Override // io.sentry.ISpan
    public void setStatus(SpanStatus spanStatus) {
        this.root.setStatus(spanStatus);
    }

    @Override // io.sentry.ISpan
    public SpanStatus getStatus() {
        return this.root.getStatus();
    }

    @Override // io.sentry.ISpan
    public void setThrowable(Throwable th) {
        this.root.setThrowable(th);
    }

    @Override // io.sentry.ISpan
    public Throwable getThrowable() {
        return this.root.getThrowable();
    }

    @Override // io.sentry.ISpan
    public SpanContext getSpanContext() {
        return this.root.getSpanContext();
    }

    @Override // io.sentry.ISpan
    public void setTag(String str, String str2) {
        this.root.setTag(str, str2);
    }

    @Override // io.sentry.ISpan
    public String getTag(String str) {
        return this.root.getTag(str);
    }

    @Override // io.sentry.ISpan
    public boolean isFinished() {
        return this.root.isFinished();
    }

    @Override // io.sentry.ISpan
    public void setData(String str, Object obj) {
        this.root.setData(str, obj);
    }

    @Override // io.sentry.ISpan
    public Object getData(String str) {
        return this.root.getData(str);
    }

    public Map<String, Object> getData() {
        return this.root.getData();
    }

    @Override // io.sentry.ITransaction
    public Boolean isSampled() {
        return this.root.isSampled();
    }

    @Override // io.sentry.ITransaction
    public void setName(String str) {
        this.name = str;
    }

    @Override // io.sentry.ITransaction
    public String getName() {
        return this.name;
    }

    @Override // io.sentry.ITransaction
    @Deprecated
    public void setRequest(Request request) {
        this.request = request;
    }

    @Override // io.sentry.ITransaction
    @Deprecated
    public Request getRequest() {
        return this.request;
    }

    @Override // io.sentry.ITransaction
    @Deprecated
    public Contexts getContexts() {
        return this.contexts;
    }

    @Override // io.sentry.ITransaction
    public List<Span> getSpans() {
        return this.children;
    }

    @Override // io.sentry.ITransaction
    public Span getLatestActiveSpan() {
        ArrayList arrayList = new ArrayList(this.children);
        if (arrayList.isEmpty()) {
            return null;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (!((Span) arrayList.get(size)).isFinished()) {
                return (Span) arrayList.get(size);
            }
        }
        return null;
    }

    @Override // io.sentry.ITransaction
    public SentryId getEventId() {
        return this.eventId;
    }

    Span getRoot() {
        return this.root;
    }

    private static final class FinishStatus {
        static final FinishStatus NOT_FINISHED = notFinished();
        private final boolean isFinishing;
        private final SpanStatus spanStatus;

        static FinishStatus finishing(SpanStatus spanStatus) {
            return new FinishStatus(true, spanStatus);
        }

        private static FinishStatus notFinished() {
            return new FinishStatus(false, null);
        }

        private FinishStatus(boolean z, SpanStatus spanStatus) {
            this.isFinishing = z;
            this.spanStatus = spanStatus;
        }
    }
}
