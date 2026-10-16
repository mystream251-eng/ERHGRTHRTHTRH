package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IHub {
    void addBreadcrumb(Breadcrumb breadcrumb, Object obj);

    void bindClient(ISentryClient iSentryClient);

    SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Object obj);

    SentryId captureEvent(SentryEvent sentryEvent, Object obj);

    SentryId captureException(Throwable th, Object obj);

    SentryId captureMessage(String str, SentryLevel sentryLevel);

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceState traceState, Object obj);

    void captureUserFeedback(UserFeedback userFeedback);

    void clearBreadcrumbs();

    IHub clone();

    void close();

    void configureScope(ScopeCallback scopeCallback);

    void endSession();

    void flush(long j);

    SentryId getLastEventId();

    SentryOptions getOptions();

    ISpan getSpan();

    Boolean isCrashedLastRun();

    boolean isEnabled();

    void popScope();

    void pushScope();

    void removeExtra(String str);

    void removeTag(String str);

    void setExtra(String str, String str2);

    void setFingerprint(List<String> list);

    void setLevel(SentryLevel sentryLevel);

    void setSpanContext(Throwable th, ISpan iSpan, String str);

    void setTag(String str, String str2);

    void setTransaction(String str);

    void setUser(User user);

    void startSession();

    ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z);

    ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z, Date date);

    ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z, Date date, boolean z2, TransactionFinishedCallback transactionFinishedCallback);

    SentryTraceHeader traceHeaders();

    void withScope(ScopeCallback scopeCallback);

    default SentryId captureEvent(SentryEvent sentryEvent) {
        return captureEvent(sentryEvent, null);
    }

    default SentryId captureMessage(String str) {
        return captureMessage(str, SentryLevel.INFO);
    }

    default SentryId captureEnvelope(SentryEnvelope sentryEnvelope) {
        return captureEnvelope(sentryEnvelope, null);
    }

    default SentryId captureException(Throwable th) {
        return captureException(th, null);
    }

    default void addBreadcrumb(Breadcrumb breadcrumb) {
        addBreadcrumb(breadcrumb, (Object) null);
    }

    default void addBreadcrumb(String str) {
        addBreadcrumb(new Breadcrumb(str));
    }

    default void addBreadcrumb(String str, String str2) {
        Breadcrumb breadcrumb = new Breadcrumb(str);
        breadcrumb.setCategory(str2);
        addBreadcrumb(breadcrumb);
    }

    default SentryId captureTransaction(SentryTransaction sentryTransaction, Object obj) {
        return captureTransaction(sentryTransaction, null, obj);
    }

    default SentryId captureTransaction(SentryTransaction sentryTransaction, TraceState traceState) {
        return captureTransaction(sentryTransaction, traceState, null);
    }

    default ITransaction startTransaction(TransactionContext transactionContext) {
        return startTransaction(transactionContext, false);
    }

    default ITransaction startTransaction(TransactionContext transactionContext, boolean z) {
        return startTransaction(transactionContext, (CustomSamplingContext) null, z);
    }

    default ITransaction startTransaction(String str, String str2, CustomSamplingContext customSamplingContext) {
        return startTransaction(str, str2, customSamplingContext, false);
    }

    default ITransaction startTransaction(String str, String str2, CustomSamplingContext customSamplingContext, boolean z) {
        return startTransaction(new TransactionContext(str, str2), customSamplingContext, z);
    }

    default ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext) {
        return startTransaction(transactionContext, customSamplingContext, false);
    }

    default ITransaction startTransaction(String str, String str2) {
        return startTransaction(str, str2, (CustomSamplingContext) null);
    }

    default ITransaction startTransaction(String str, String str2, Date date, boolean z, TransactionFinishedCallback transactionFinishedCallback) {
        return startTransaction(new TransactionContext(str, str2), null, false, date, z, transactionFinishedCallback);
    }

    default ITransaction startTransaction(String str, String str2, boolean z) {
        return startTransaction(str, str2, (CustomSamplingContext) null, z);
    }
}
