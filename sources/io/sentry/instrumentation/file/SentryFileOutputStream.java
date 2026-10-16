package io.sentry.instrumentation.file;

import io.sentry.HubAdapter;
import io.sentry.IHub;
import io.sentry.ISpan;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryFileOutputStream extends FileOutputStream {
    private final FileOutputStream delegate;
    private final FileIOSpanManager spanManager;

    public SentryFileOutputStream(String str) throws FileNotFoundException {
        this(str != null ? new File(str) : null, HubAdapter.getInstance());
    }

    public SentryFileOutputStream(String str, boolean z) throws FileNotFoundException {
        this(init(str != null ? new File(str) : null, z, null, HubAdapter.getInstance()));
    }

    public SentryFileOutputStream(File file) throws FileNotFoundException {
        this(file, HubAdapter.getInstance());
    }

    public SentryFileOutputStream(File file, boolean z) throws FileNotFoundException {
        this(init(file, z, null, HubAdapter.getInstance()));
    }

    public SentryFileOutputStream(FileDescriptor fileDescriptor) {
        this(init(fileDescriptor, null, HubAdapter.getInstance()), fileDescriptor);
    }

    SentryFileOutputStream(File file, IHub iHub) throws FileNotFoundException {
        this(init(file, false, null, iHub));
    }

    private SentryFileOutputStream(FileOutputStreamInitData fileOutputStreamInitData, FileDescriptor fileDescriptor) {
        super(fileDescriptor);
        this.spanManager = new FileIOSpanManager(fileOutputStreamInitData.span, fileOutputStreamInitData.file, fileOutputStreamInitData.isSendDefaultPii);
        this.delegate = fileOutputStreamInitData.delegate;
    }

    private SentryFileOutputStream(FileOutputStreamInitData fileOutputStreamInitData) throws FileNotFoundException {
        super(fileOutputStreamInitData.file, fileOutputStreamInitData.append);
        this.spanManager = new FileIOSpanManager(fileOutputStreamInitData.span, fileOutputStreamInitData.file, fileOutputStreamInitData.isSendDefaultPii);
        this.delegate = fileOutputStreamInitData.delegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FileOutputStreamInitData init(File file, boolean z, FileOutputStream fileOutputStream, IHub iHub) throws FileNotFoundException {
        ISpan iSpanStartSpan = FileIOSpanManager.startSpan(iHub, "file.write");
        if (fileOutputStream == null) {
            fileOutputStream = new FileOutputStream(file);
        }
        return new FileOutputStreamInitData(file, z, iSpanStartSpan, fileOutputStream, iHub.getOptions().isSendDefaultPii());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FileOutputStreamInitData init(FileDescriptor fileDescriptor, FileOutputStream fileOutputStream, IHub iHub) {
        ISpan iSpanStartSpan = FileIOSpanManager.startSpan(iHub, "file.write");
        if (fileOutputStream == null) {
            fileOutputStream = new FileOutputStream(fileDescriptor);
        }
        return new FileOutputStreamInitData(null, false, iSpanStartSpan, fileOutputStream, iHub.getOptions().isSendDefaultPii());
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream
    public void write(final int i) throws IOException {
        this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.-$$Lambda$SentryFileOutputStream$OpKrPQIimwV2QIV57Q0IEV8SHfw
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return this.f$0.lambda$write$0$SentryFileOutputStream(i);
            }
        });
    }

    public /* synthetic */ Integer lambda$write$0$SentryFileOutputStream(int i) throws IOException {
        this.delegate.write(i);
        return 1;
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream
    public void write(final byte[] bArr) throws IOException {
        this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.-$$Lambda$SentryFileOutputStream$c9i5Cmfihe3xDtj3bKz93FdGDMQ
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return this.f$0.lambda$write$1$SentryFileOutputStream(bArr);
            }
        });
    }

    public /* synthetic */ Integer lambda$write$1$SentryFileOutputStream(byte[] bArr) throws IOException {
        this.delegate.write(bArr);
        return Integer.valueOf(bArr.length);
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream
    public void write(final byte[] bArr, final int i, final int i2) throws IOException {
        this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.-$$Lambda$SentryFileOutputStream$oC2q7IyylRUI38X3gYyFX5q9IC4
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return this.f$0.lambda$write$2$SentryFileOutputStream(bArr, i, i2);
            }
        });
    }

    public /* synthetic */ Integer lambda$write$2$SentryFileOutputStream(byte[] bArr, int i, int i2) throws IOException {
        this.delegate.write(bArr, i, i2);
        return Integer.valueOf(i2);
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.spanManager.finish(this.delegate);
    }

    public static final class Factory {
        public static FileOutputStream create(FileOutputStream fileOutputStream, String str) throws FileNotFoundException {
            return new SentryFileOutputStream(SentryFileOutputStream.init(str != null ? new File(str) : null, false, fileOutputStream, HubAdapter.getInstance()));
        }

        public static FileOutputStream create(FileOutputStream fileOutputStream, String str, boolean z) throws FileNotFoundException {
            return new SentryFileOutputStream(SentryFileOutputStream.init(str != null ? new File(str) : null, z, fileOutputStream, HubAdapter.getInstance()));
        }

        public static FileOutputStream create(FileOutputStream fileOutputStream, File file) throws FileNotFoundException {
            return new SentryFileOutputStream(SentryFileOutputStream.init(file, false, fileOutputStream, HubAdapter.getInstance()));
        }

        public static FileOutputStream create(FileOutputStream fileOutputStream, File file, boolean z) throws FileNotFoundException {
            return new SentryFileOutputStream(SentryFileOutputStream.init(file, z, fileOutputStream, HubAdapter.getInstance()));
        }

        public static FileOutputStream create(FileOutputStream fileOutputStream, FileDescriptor fileDescriptor) {
            return new SentryFileOutputStream(SentryFileOutputStream.init(fileDescriptor, fileOutputStream, HubAdapter.getInstance()), fileDescriptor);
        }
    }
}
