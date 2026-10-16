package io.sentry.instrumentation.file;

import io.sentry.IHub;
import io.sentry.ISpan;
import io.sentry.SpanStatus;
import io.sentry.util.Platform;
import io.sentry.util.StringUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class FileIOSpanManager {
    private long byteCount;
    private final ISpan currentSpan;
    private final File file;
    private final boolean isSendDefaultPii;
    private SpanStatus spanStatus = SpanStatus.OK;

    @FunctionalInterface
    interface FileIOCallable<T> {
        T call() throws IOException;
    }

    static ISpan startSpan(IHub iHub, String str) {
        ISpan span = iHub.getSpan();
        if (span != null) {
            return span.startChild(str);
        }
        return null;
    }

    FileIOSpanManager(ISpan iSpan, File file, boolean z) {
        this.currentSpan = iSpan;
        this.file = file;
        this.isSendDefaultPii = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    <T> T performIO(FileIOCallable<T> fileIOCallable) throws IOException {
        try {
            T tCall = fileIOCallable.call();
            if (tCall instanceof Integer) {
                int iIntValue = ((Integer) tCall).intValue();
                if (iIntValue != -1) {
                    this.byteCount += (long) iIntValue;
                }
            } else if (tCall instanceof Long) {
                long jLongValue = ((Long) tCall).longValue();
                if (jLongValue != -1) {
                    this.byteCount += jLongValue;
                }
            }
            return tCall;
        } catch (IOException e) {
            this.spanStatus = SpanStatus.INTERNAL_ERROR;
            ISpan iSpan = this.currentSpan;
            if (iSpan != null) {
                iSpan.setThrowable(e);
            }
            throw e;
        }
    }

    void finish(Closeable closeable) throws IOException {
        try {
            try {
                closeable.close();
                finishSpan();
            } catch (IOException e) {
                this.spanStatus = SpanStatus.INTERNAL_ERROR;
                if (this.currentSpan != null) {
                    this.currentSpan.setThrowable(e);
                }
                throw e;
            }
        } catch (Throwable th) {
            finishSpan();
            throw th;
        }
    }

    private void finishSpan() {
        if (this.currentSpan != null) {
            String strByteCountToString = StringUtils.byteCountToString(this.byteCount);
            if (this.file != null) {
                this.currentSpan.setDescription(this.file.getName() + " (" + strByteCountToString + ")");
                if (Platform.isAndroid() || this.isSendDefaultPii) {
                    this.currentSpan.setData("file.path", this.file.getAbsolutePath());
                }
            } else {
                this.currentSpan.setDescription(strByteCountToString);
            }
            this.currentSpan.setData("file.size", Long.valueOf(this.byteCount));
            this.currentSpan.finish(this.spanStatus);
        }
    }
}
