package io.sentry;

import io.sentry.cache.EnvelopeCache;
import io.sentry.hints.Flushable;
import io.sentry.hints.Retryable;
import io.sentry.util.LogUtils;
import io.sentry.util.Objects;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class EnvelopeSender extends DirectoryProcessor implements IEnvelopeSender {
    private final IHub hub;
    private final ILogger logger;
    private final ISerializer serializer;

    @Override // io.sentry.DirectoryProcessor
    public /* bridge */ /* synthetic */ void processDirectory(File file) {
        super.processDirectory(file);
    }

    public EnvelopeSender(IHub iHub, ISerializer iSerializer, ILogger iLogger, long j) {
        super(iLogger, j);
        this.hub = (IHub) Objects.requireNonNull(iHub, "Hub is required.");
        this.serializer = (ISerializer) Objects.requireNonNull(iSerializer, "Serializer is required.");
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "Logger is required.");
    }

    @Override // io.sentry.DirectoryProcessor
    protected void processFile(File file, Object obj) {
        if (!file.isFile()) {
            this.logger.log(SentryLevel.DEBUG, "'%s' is not a file.", file.getAbsolutePath());
            return;
        }
        if (!isRelevantFileName(file.getName())) {
            this.logger.log(SentryLevel.DEBUG, "File '%s' doesn't match extension expected.", file.getAbsolutePath());
            return;
        }
        try {
            if (!file.getParentFile().canWrite()) {
                this.logger.log(SentryLevel.WARNING, "File '%s' cannot be deleted so it will not be processed.", file.getAbsolutePath());
                return;
            }
            try {
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    try {
                        SentryEnvelope sentryEnvelopeDeserializeEnvelope = this.serializer.deserializeEnvelope(bufferedInputStream);
                        if (sentryEnvelopeDeserializeEnvelope == null) {
                            this.logger.log(SentryLevel.ERROR, "Failed to deserialize cached envelope %s", file.getAbsolutePath());
                        } else {
                            this.hub.captureEnvelope(sentryEnvelopeDeserializeEnvelope, obj);
                        }
                        if (!(obj instanceof Flushable)) {
                            LogUtils.logIfNotFlushable(this.logger, obj);
                        } else if (!((Flushable) obj).waitFlush()) {
                            this.logger.log(SentryLevel.WARNING, "Timed out waiting for envelope submission.", new Object[0]);
                        }
                        bufferedInputStream.close();
                        if (obj instanceof Retryable) {
                            if (((Retryable) obj).isRetry()) {
                                this.logger.log(SentryLevel.INFO, "File not deleted since retry was marked. %s.", file.getAbsolutePath());
                                return;
                            } else {
                                safeDelete(file, "after trying to capture it");
                                this.logger.log(SentryLevel.DEBUG, "Deleted file %s.", file.getAbsolutePath());
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            bufferedInputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    this.logger.log(SentryLevel.ERROR, th3, "Failed to capture cached envelope %s", file.getAbsolutePath());
                    if (obj instanceof Retryable) {
                        ((Retryable) obj).setRetry(false);
                        this.logger.log(SentryLevel.INFO, th3, "File '%s' won't retry.", file.getAbsolutePath());
                    } else {
                        LogUtils.logIfNotRetryable(this.logger, obj);
                    }
                    if (obj instanceof Retryable) {
                        if (((Retryable) obj).isRetry()) {
                            this.logger.log(SentryLevel.INFO, "File not deleted since retry was marked. %s.", file.getAbsolutePath());
                            return;
                        } else {
                            safeDelete(file, "after trying to capture it");
                            this.logger.log(SentryLevel.DEBUG, "Deleted file %s.", file.getAbsolutePath());
                            return;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                this.logger.log(SentryLevel.ERROR, e, "File '%s' cannot be found.", file.getAbsolutePath());
                if (obj instanceof Retryable) {
                    if (((Retryable) obj).isRetry()) {
                        this.logger.log(SentryLevel.INFO, "File not deleted since retry was marked. %s.", file.getAbsolutePath());
                        return;
                    } else {
                        safeDelete(file, "after trying to capture it");
                        this.logger.log(SentryLevel.DEBUG, "Deleted file %s.", file.getAbsolutePath());
                        return;
                    }
                }
            } catch (IOException e2) {
                this.logger.log(SentryLevel.ERROR, e2, "I/O on file '%s' failed.", file.getAbsolutePath());
                if (obj instanceof Retryable) {
                    if (((Retryable) obj).isRetry()) {
                        this.logger.log(SentryLevel.INFO, "File not deleted since retry was marked. %s.", file.getAbsolutePath());
                        return;
                    } else {
                        safeDelete(file, "after trying to capture it");
                        this.logger.log(SentryLevel.DEBUG, "Deleted file %s.", file.getAbsolutePath());
                        return;
                    }
                }
            }
            LogUtils.logIfNotRetryable(this.logger, obj);
        } catch (Throwable th4) {
            if (!(obj instanceof Retryable)) {
                LogUtils.logIfNotRetryable(this.logger, obj);
            } else if (((Retryable) obj).isRetry()) {
                this.logger.log(SentryLevel.INFO, "File not deleted since retry was marked. %s.", file.getAbsolutePath());
            } else {
                safeDelete(file, "after trying to capture it");
                this.logger.log(SentryLevel.DEBUG, "Deleted file %s.", file.getAbsolutePath());
            }
            throw th4;
        }
    }

    @Override // io.sentry.DirectoryProcessor
    protected boolean isRelevantFileName(String str) {
        return str.endsWith(EnvelopeCache.SUFFIX_ENVELOPE_FILE);
    }

    @Override // io.sentry.IEnvelopeSender
    public void processEnvelopeFile(String str, Object obj) {
        Objects.requireNonNull(str, "Path is required.");
        processFile(new File(str), obj);
    }

    private void safeDelete(File file, String str) {
        try {
            if (file.delete()) {
                return;
            }
            this.logger.log(SentryLevel.ERROR, "Failed to delete '%s' %s", file.getAbsolutePath(), str);
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, th, "Failed to delete '%s' %s", file.getAbsolutePath(), str);
        }
    }
}
