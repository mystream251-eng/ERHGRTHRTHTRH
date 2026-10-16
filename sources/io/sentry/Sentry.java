package io.sentry;

import io.sentry.cache.EnvelopeCache;
import io.sentry.config.PropertiesProviderFactory;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class Sentry {
    private static final boolean GLOBAL_HUB_DEFAULT_MODE = false;
    private static final ThreadLocal<IHub> currentHub = new ThreadLocal<>();
    private static volatile IHub mainHub = NoOpHub.getInstance();
    private static volatile boolean globalHubMode = false;

    public interface OptionsConfiguration<T extends SentryOptions> {
        void configure(T t);
    }

    private Sentry() {
    }

    public static IHub getCurrentHub() {
        if (globalHubMode) {
            return mainHub;
        }
        IHub iHub = currentHub.get();
        if (iHub != null) {
            return iHub;
        }
        IHub iHubM25clone = mainHub.m26clone();
        currentHub.set(iHubM25clone);
        return iHubM25clone;
    }

    public static void setCurrentHub(IHub iHub) {
        currentHub.set(iHub);
    }

    public static boolean isEnabled() {
        return getCurrentHub().isEnabled();
    }

    public static void init() {
        init((OptionsConfiguration<SentryOptions>) new OptionsConfiguration() { // from class: io.sentry.-$$Lambda$Sentry$NEC-zGTNdEG1rkjO4iWgcQQzKvU
            @Override // io.sentry.Sentry.OptionsConfiguration
            public final void configure(SentryOptions sentryOptions) {
                sentryOptions.setEnableExternalConfiguration(true);
            }
        }, false);
    }

    public static void init(final String str) {
        init((OptionsConfiguration<SentryOptions>) new OptionsConfiguration() { // from class: io.sentry.-$$Lambda$Sentry$Vwz5K293jbGZzoyIk4Rn8GG9_DE
            @Override // io.sentry.Sentry.OptionsConfiguration
            public final void configure(SentryOptions sentryOptions) {
                sentryOptions.setDsn(str);
            }
        });
    }

    public static <T extends SentryOptions> void init(OptionsContainer<T> optionsContainer, OptionsConfiguration<T> optionsConfiguration) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        init(optionsContainer, optionsConfiguration, false);
    }

    public static <T extends SentryOptions> void init(OptionsContainer<T> optionsContainer, OptionsConfiguration<T> optionsConfiguration, boolean z) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        T tCreateInstance = optionsContainer.createInstance();
        optionsConfiguration.configure(tCreateInstance);
        init(tCreateInstance, z);
    }

    public static void init(OptionsConfiguration<SentryOptions> optionsConfiguration) {
        init(optionsConfiguration, false);
    }

    public static void init(OptionsConfiguration<SentryOptions> optionsConfiguration, boolean z) {
        SentryOptions sentryOptions = new SentryOptions();
        optionsConfiguration.configure(sentryOptions);
        init(sentryOptions, z);
    }

    public static void init(SentryOptions sentryOptions) {
        init(sentryOptions, false);
    }

    private static synchronized void init(SentryOptions sentryOptions, boolean z) {
        if (isEnabled()) {
            sentryOptions.getLogger().log(SentryLevel.WARNING, "Sentry has been already initialized. Previous configuration will be overwritten.", new Object[0]);
        }
        if (initConfigurations(sentryOptions)) {
            sentryOptions.getLogger().log(SentryLevel.INFO, "GlobalHubMode: '%s'", String.valueOf(z));
            globalHubMode = z;
            IHub currentHub2 = getCurrentHub();
            mainHub = new Hub(sentryOptions);
            currentHub.set(mainHub);
            currentHub2.close();
            Iterator<Integration> it = sentryOptions.getIntegrations().iterator();
            while (it.hasNext()) {
                it.next().register(HubAdapter.getInstance(), sentryOptions);
            }
        }
    }

    private static boolean initConfigurations(SentryOptions sentryOptions) {
        if (sentryOptions.isEnableExternalConfiguration()) {
            sentryOptions.merge(SentryOptions.from(PropertiesProviderFactory.create(), sentryOptions.getLogger()));
        }
        String dsn = sentryOptions.getDsn();
        if (dsn == null) {
            throw new IllegalArgumentException("DSN is required. Use empty string to disable SDK.");
        }
        if (dsn.isEmpty()) {
            close();
            return false;
        }
        new Dsn(dsn);
        ILogger logger = sentryOptions.getLogger();
        if (sentryOptions.isDebug() && (logger instanceof NoOpLogger)) {
            sentryOptions.setLogger(new SystemOutLogger());
            logger = sentryOptions.getLogger();
        }
        logger.log(SentryLevel.INFO, "Initializing SDK with DSN: '%s'", sentryOptions.getDsn());
        if (sentryOptions.getOutboxPath() != null) {
            new File(sentryOptions.getOutboxPath()).mkdirs();
        } else {
            logger.log(SentryLevel.INFO, "No outbox dir path is defined in options.", new Object[0]);
        }
        if (sentryOptions.getCacheDirPath() != null && !sentryOptions.getCacheDirPath().isEmpty()) {
            new File(sentryOptions.getCacheDirPath()).mkdirs();
            sentryOptions.setEnvelopeDiskCache(EnvelopeCache.create(sentryOptions));
        }
        return true;
    }

    public static synchronized void close() {
        IHub currentHub2 = getCurrentHub();
        mainHub = NoOpHub.getInstance();
        currentHub2.close();
    }

    public static SentryId captureEvent(SentryEvent sentryEvent) {
        return getCurrentHub().captureEvent(sentryEvent);
    }

    public static SentryId captureEvent(SentryEvent sentryEvent, Object obj) {
        return getCurrentHub().captureEvent(sentryEvent, obj);
    }

    public static SentryId captureMessage(String str) {
        return getCurrentHub().captureMessage(str);
    }

    public static SentryId captureMessage(String str, SentryLevel sentryLevel) {
        return getCurrentHub().captureMessage(str, sentryLevel);
    }

    public static SentryId captureException(Throwable th) {
        return getCurrentHub().captureException(th);
    }

    public static SentryId captureException(Throwable th, Object obj) {
        return getCurrentHub().captureException(th, obj);
    }

    public static void captureUserFeedback(UserFeedback userFeedback) {
        getCurrentHub().captureUserFeedback(userFeedback);
    }

    public static void addBreadcrumb(Breadcrumb breadcrumb, Object obj) {
        getCurrentHub().addBreadcrumb(breadcrumb, obj);
    }

    public static void addBreadcrumb(Breadcrumb breadcrumb) {
        getCurrentHub().addBreadcrumb(breadcrumb);
    }

    public static void addBreadcrumb(String str) {
        getCurrentHub().addBreadcrumb(str);
    }

    public static void addBreadcrumb(String str, String str2) {
        getCurrentHub().addBreadcrumb(str, str2);
    }

    public static void setLevel(SentryLevel sentryLevel) {
        getCurrentHub().setLevel(sentryLevel);
    }

    public static void setTransaction(String str) {
        getCurrentHub().setTransaction(str);
    }

    public static void setUser(User user) {
        getCurrentHub().setUser(user);
    }

    public static void setFingerprint(List<String> list) {
        getCurrentHub().setFingerprint(list);
    }

    public static void clearBreadcrumbs() {
        getCurrentHub().clearBreadcrumbs();
    }

    public static void setTag(String str, String str2) {
        getCurrentHub().setTag(str, str2);
    }

    public static void removeTag(String str) {
        getCurrentHub().removeTag(str);
    }

    public static void setExtra(String str, String str2) {
        getCurrentHub().setExtra(str, str2);
    }

    public static void removeExtra(String str) {
        getCurrentHub().removeExtra(str);
    }

    public static SentryId getLastEventId() {
        return getCurrentHub().getLastEventId();
    }

    public static void pushScope() {
        if (globalHubMode) {
            return;
        }
        getCurrentHub().pushScope();
    }

    public static void popScope() {
        if (globalHubMode) {
            return;
        }
        getCurrentHub().popScope();
    }

    public static void withScope(ScopeCallback scopeCallback) {
        getCurrentHub().withScope(scopeCallback);
    }

    public static void configureScope(ScopeCallback scopeCallback) {
        getCurrentHub().configureScope(scopeCallback);
    }

    public static void bindClient(ISentryClient iSentryClient) {
        getCurrentHub().bindClient(iSentryClient);
    }

    public static void flush(long j) {
        getCurrentHub().flush(j);
    }

    public static void startSession() {
        getCurrentHub().startSession();
    }

    public static void endSession() {
        getCurrentHub().endSession();
    }

    public static ITransaction startTransaction(String str, String str2) {
        return getCurrentHub().startTransaction(str, str2);
    }

    public static ITransaction startTransaction(String str, String str2, boolean z) {
        return getCurrentHub().startTransaction(str, str2, z);
    }

    public static ITransaction startTransaction(String str, String str2, String str3) {
        return startTransaction(str, str2, str3, false);
    }

    public static ITransaction startTransaction(String str, String str2, String str3, boolean z) {
        ITransaction iTransactionStartTransaction = getCurrentHub().startTransaction(str, str2, z);
        iTransactionStartTransaction.setDescription(str3);
        return iTransactionStartTransaction;
    }

    public static ITransaction startTransaction(TransactionContext transactionContext) {
        return getCurrentHub().startTransaction(transactionContext);
    }

    public static ITransaction startTransaction(TransactionContext transactionContext, boolean z) {
        return getCurrentHub().startTransaction(transactionContext, z);
    }

    public static ITransaction startTransaction(String str, String str2, CustomSamplingContext customSamplingContext) {
        return getCurrentHub().startTransaction(str, str2, customSamplingContext);
    }

    public static ITransaction startTransaction(String str, String str2, CustomSamplingContext customSamplingContext, boolean z) {
        return getCurrentHub().startTransaction(str, str2, customSamplingContext, z);
    }

    public static ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext) {
        return getCurrentHub().startTransaction(transactionContext, customSamplingContext);
    }

    public static ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z) {
        return getCurrentHub().startTransaction(transactionContext, customSamplingContext, z);
    }

    public static ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z, Date date) {
        return getCurrentHub().startTransaction(transactionContext, customSamplingContext, z, date);
    }

    public static ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z, Date date, boolean z2, TransactionFinishedCallback transactionFinishedCallback) {
        return getCurrentHub().startTransaction(transactionContext, customSamplingContext, z, date, z2, transactionFinishedCallback);
    }

    public static SentryTraceHeader traceHeaders() {
        return getCurrentHub().traceHeaders();
    }

    public static ISpan getSpan() {
        return getCurrentHub().getSpan();
    }

    public static Boolean isCrashedLastRun() {
        return getCurrentHub().isCrashedLastRun();
    }
}
