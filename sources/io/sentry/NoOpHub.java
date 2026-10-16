package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class NoOpHub implements IHub {
    private static final NoOpHub instance = new NoOpHub();
    private final SentryOptions emptyOptions = SentryOptions.empty();

    @Override // io.sentry.IHub
    public void addBreadcrumb(Breadcrumb breadcrumb, Object obj) {
    }

    @Override // io.sentry.IHub
    public void bindClient(ISentryClient iSentryClient) {
    }

    @Override // io.sentry.IHub
    public void captureUserFeedback(UserFeedback userFeedback) {
    }

    @Override // io.sentry.IHub
    public void clearBreadcrumbs() {
    }

    @Override // io.sentry.IHub
    public void close() {
    }

    @Override // io.sentry.IHub
    public void configureScope(ScopeCallback scopeCallback) {
    }

    @Override // io.sentry.IHub
    public void endSession() {
    }

    @Override // io.sentry.IHub
    public void flush(long j) {
    }

    @Override // io.sentry.IHub
    public ISpan getSpan() {
        return null;
    }

    @Override // io.sentry.IHub
    public Boolean isCrashedLastRun() {
        return null;
    }

    @Override // io.sentry.IHub
    public boolean isEnabled() {
        return false;
    }

    @Override // io.sentry.IHub
    public void popScope() {
    }

    @Override // io.sentry.IHub
    public void pushScope() {
    }

    @Override // io.sentry.IHub
    public void removeExtra(String str) {
    }

    @Override // io.sentry.IHub
    public void removeTag(String str) {
    }

    @Override // io.sentry.IHub
    public void setExtra(String str, String str2) {
    }

    @Override // io.sentry.IHub
    public void setFingerprint(List<String> list) {
    }

    @Override // io.sentry.IHub
    public void setLevel(SentryLevel sentryLevel) {
    }

    @Override // io.sentry.IHub
    public void setSpanContext(Throwable th, ISpan iSpan, String str) {
    }

    @Override // io.sentry.IHub
    public void setTag(String str, String str2) {
    }

    @Override // io.sentry.IHub
    public void setTransaction(String str) {
    }

    @Override // io.sentry.IHub
    public void setUser(User user) {
    }

    @Override // io.sentry.IHub
    public void startSession() {
    }

    @Override // io.sentry.IHub
    public void withScope(ScopeCallback scopeCallback) {
    }

    private NoOpHub() {
    }

    public static NoOpHub getInstance() {
        return instance;
    }

    @Override // io.sentry.IHub
    public SentryId captureEvent(SentryEvent sentryEvent, Object obj) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.IHub
    public SentryId captureMessage(String str, SentryLevel sentryLevel) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.IHub
    public SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Object obj) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.IHub
    public SentryId captureException(Throwable th, Object obj) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.IHub
    public SentryId getLastEventId() {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.IHub
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public IHub m26clone() {
        return instance;
    }

    @Override // io.sentry.IHub
    public SentryId captureTransaction(SentryTransaction sentryTransaction, TraceState traceState, Object obj) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.IHub
    public ITransaction startTransaction(TransactionContext transactionContext) {
        return NoOpTransaction.getInstance();
    }

    @Override // io.sentry.IHub
    public ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z) {
        return NoOpTransaction.getInstance();
    }

    @Override // io.sentry.IHub
    public ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z, Date date) {
        return NoOpTransaction.getInstance();
    }

    @Override // io.sentry.IHub
    public ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z, Date date, boolean z2, TransactionFinishedCallback transactionFinishedCallback) {
        return NoOpTransaction.getInstance();
    }

    @Override // io.sentry.IHub
    public SentryTraceHeader traceHeaders() {
        return new SentryTraceHeader(SentryId.EMPTY_ID, SpanId.EMPTY_ID, true);
    }

    @Override // io.sentry.IHub
    public SentryOptions getOptions() {
        return this.emptyOptions;
    }
}
