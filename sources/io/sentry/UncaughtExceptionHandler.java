package io.sentry;

/* JADX INFO: loaded from: classes2.dex */
interface UncaughtExceptionHandler {
    Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler();

    void setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler);

    public static final class Adapter implements UncaughtExceptionHandler {
        private static final Adapter INSTANCE = new Adapter();

        static UncaughtExceptionHandler getInstance() {
            return INSTANCE;
        }

        private Adapter() {
        }

        @Override // io.sentry.UncaughtExceptionHandler
        public Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
            return Thread.getDefaultUncaughtExceptionHandler();
        }

        @Override // io.sentry.UncaughtExceptionHandler
        public void setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
    }
}
