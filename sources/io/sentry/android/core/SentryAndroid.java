package io.sentry.android.core;

import android.content.Context;
import android.os.SystemClock;
import io.sentry.DateUtils;
import io.sentry.ILogger;
import io.sentry.OptionsContainer;
import io.sentry.Sentry;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryAndroid {
    private static final Date appStartTime = DateUtils.getCurrentDateTime();
    private static final long appStart = SystemClock.uptimeMillis();

    static /* synthetic */ void lambda$init$0(SentryAndroidOptions sentryAndroidOptions) {
    }

    private SentryAndroid() {
    }

    public static void init(Context context) {
        init(context, new AndroidLogger());
    }

    public static void init(Context context, ILogger iLogger) {
        init(context, iLogger, new Sentry.OptionsConfiguration() { // from class: io.sentry.android.core.-$$Lambda$SentryAndroid$1tDpu4BrZ9ZUeZ0tE52kgN3Zx10
            @Override // io.sentry.Sentry.OptionsConfiguration
            public final void configure(SentryOptions sentryOptions) {
                SentryAndroid.lambda$init$0((SentryAndroidOptions) sentryOptions);
            }
        });
    }

    public static void init(Context context, Sentry.OptionsConfiguration<SentryAndroidOptions> optionsConfiguration) {
        init(context, new AndroidLogger(), optionsConfiguration);
    }

    public static synchronized void init(final Context context, final ILogger iLogger, final Sentry.OptionsConfiguration<SentryAndroidOptions> optionsConfiguration) {
        AppStartState.getInstance().setAppStartTime(appStart, appStartTime);
        try {
            try {
                Sentry.init(OptionsContainer.create(SentryAndroidOptions.class), new Sentry.OptionsConfiguration() { // from class: io.sentry.android.core.-$$Lambda$SentryAndroid$g99QaqNwcEFxja-3CMUNWZ36_g4
                    @Override // io.sentry.Sentry.OptionsConfiguration
                    public final void configure(SentryOptions sentryOptions) {
                        SentryAndroid.lambda$init$1(context, iLogger, optionsConfiguration, (SentryAndroidOptions) sentryOptions);
                    }
                }, true);
            } catch (InstantiationException e) {
                iLogger.log(SentryLevel.FATAL, "Fatal error during SentryAndroid.init(...)", e);
                throw new RuntimeException("Failed to initialize Sentry's SDK", e);
            } catch (InvocationTargetException e2) {
                iLogger.log(SentryLevel.FATAL, "Fatal error during SentryAndroid.init(...)", e2);
                throw new RuntimeException("Failed to initialize Sentry's SDK", e2);
            }
        } catch (IllegalAccessException e3) {
            iLogger.log(SentryLevel.FATAL, "Fatal error during SentryAndroid.init(...)", e3);
            throw new RuntimeException("Failed to initialize Sentry's SDK", e3);
        } catch (NoSuchMethodException e4) {
            iLogger.log(SentryLevel.FATAL, "Fatal error during SentryAndroid.init(...)", e4);
            throw new RuntimeException("Failed to initialize Sentry's SDK", e4);
        }
    }

    static /* synthetic */ void lambda$init$1(Context context, ILogger iLogger, Sentry.OptionsConfiguration optionsConfiguration, SentryAndroidOptions sentryAndroidOptions) {
        AndroidOptionsInitializer.init(sentryAndroidOptions, context, iLogger);
        optionsConfiguration.configure(sentryAndroidOptions);
    }
}
