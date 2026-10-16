package io.sentry.instrumentation.file;

import io.sentry.HubAdapter;
import io.sentry.IHub;
import io.sentry.ISpan;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryFileInputStream extends FileInputStream {
    private final FileInputStream delegate;
    private final FileIOSpanManager spanManager;

    public SentryFileInputStream(String str) throws FileNotFoundException {
        this(str != null ? new File(str) : null, HubAdapter.getInstance());
    }

    public SentryFileInputStream(File file) throws FileNotFoundException {
        this(file, HubAdapter.getInstance());
    }

    public SentryFileInputStream(FileDescriptor fileDescriptor) {
        this(fileDescriptor, HubAdapter.getInstance());
    }

    SentryFileInputStream(File file, IHub iHub) throws FileNotFoundException {
        this(init(file, (FileInputStream) null, iHub));
    }

    SentryFileInputStream(FileDescriptor fileDescriptor, IHub iHub) {
        this(init(fileDescriptor, (FileInputStream) null, iHub), fileDescriptor);
    }

    private SentryFileInputStream(FileInputStreamInitData fileInputStreamInitData, FileDescriptor fileDescriptor) {
        super(fileDescriptor);
        this.spanManager = new FileIOSpanManager(fileInputStreamInitData.span, fileInputStreamInitData.file, fileInputStreamInitData.isSendDefaultPii);
        this.delegate = fileInputStreamInitData.delegate;
    }

    private SentryFileInputStream(FileInputStreamInitData fileInputStreamInitData) throws FileNotFoundException {
        super(fileInputStreamInitData.file);
        this.spanManager = new FileIOSpanManager(fileInputStreamInitData.span, fileInputStreamInitData.file, fileInputStreamInitData.isSendDefaultPii);
        this.delegate = fileInputStreamInitData.delegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FileInputStreamInitData init(File file, FileInputStream fileInputStream, IHub iHub) throws FileNotFoundException {
        ISpan iSpanStartSpan = FileIOSpanManager.startSpan(iHub, "file.read");
        if (fileInputStream == null) {
            fileInputStream = new FileInputStream(file);
        }
        return new FileInputStreamInitData(file, iSpanStartSpan, fileInputStream, iHub.getOptions().isSendDefaultPii());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FileInputStreamInitData init(FileDescriptor fileDescriptor, FileInputStream fileInputStream, IHub iHub) {
        ISpan iSpanStartSpan = FileIOSpanManager.startSpan(iHub, "file.read");
        if (fileInputStream == null) {
            fileInputStream = new FileInputStream(fileDescriptor);
        }
        return new FileInputStreamInitData(null, iSpanStartSpan, fileInputStream, iHub.getOptions().isSendDefaultPii());
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read() throws IOException {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.-$$Lambda$SentryFileInputStream$tOQVR0RFO2LEyOyxBHDqpaFJTbI
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return this.f$0.lambda$read$0$SentryFileInputStream(atomicInteger);
            }
        });
        return atomicInteger.get();
    }

    public /* synthetic */ Integer lambda$read$0$SentryFileInputStream(AtomicInteger atomicInteger) throws IOException {
        int i = this.delegate.read();
        atomicInteger.set(i);
        return Integer.valueOf(i != -1 ? 1 : 0);
    }

    public /* synthetic */ Integer lambda$read$1$SentryFileInputStream(byte[] bArr) throws IOException {
        return Integer.valueOf(this.delegate.read(bArr));
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(final byte[] bArr) throws IOException {
        return ((Integer) this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.-$$Lambda$SentryFileInputStream$oyOduFqiv_2wfAYOPanks2L51Uk
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return this.f$0.lambda$read$1$SentryFileInputStream(bArr);
            }
        })).intValue();
    }

    public /* synthetic */ Integer lambda$read$2$SentryFileInputStream(byte[] bArr, int i, int i2) throws IOException {
        return Integer.valueOf(this.delegate.read(bArr, i, i2));
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(final byte[] bArr, final int i, final int i2) throws IOException {
        return ((Integer) this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.-$$Lambda$SentryFileInputStream$lLBfGS-fVAYAqEw-WzCjhPp4OVM
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return this.f$0.lambda$read$2$SentryFileInputStream(bArr, i, i2);
            }
        })).intValue();
    }

    public /* synthetic */ Long lambda$skip$3$SentryFileInputStream(long j) throws IOException {
        return Long.valueOf(this.delegate.skip(j));
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public long skip(final long j) throws IOException {
        return ((Long) this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.-$$Lambda$SentryFileInputStream$1EtB5RK7CphBG1nuxs2G8LSKutg
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return this.f$0.lambda$skip$3$SentryFileInputStream(j);
            }
        })).longValue();
    }

    @Override // java.io.FileInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.spanManager.finish(this.delegate);
    }

    public static final class Factory {
        public static FileInputStream create(FileInputStream fileInputStream, String str) throws FileNotFoundException {
            return new SentryFileInputStream(SentryFileInputStream.init(str != null ? new File(str) : null, fileInputStream, HubAdapter.getInstance()));
        }

        public static FileInputStream create(FileInputStream fileInputStream, File file) throws FileNotFoundException {
            return new SentryFileInputStream(SentryFileInputStream.init(file, fileInputStream, HubAdapter.getInstance()));
        }

        public static FileInputStream create(FileInputStream fileInputStream, FileDescriptor fileDescriptor) {
            return new SentryFileInputStream(SentryFileInputStream.init(fileDescriptor, fileInputStream, HubAdapter.getInstance()), fileDescriptor);
        }

        static FileInputStream create(FileInputStream fileInputStream, File file, IHub iHub) throws FileNotFoundException {
            return new SentryFileInputStream(SentryFileInputStream.init(file, fileInputStream, iHub));
        }
    }
}
