package io.sentry;

import io.sentry.exception.SentryEnvelopeException;
import io.sentry.protocol.SentryTransaction;
import io.sentry.util.Objects;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryEnvelopeItem {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private byte[] data;
    private final Callable<byte[]> dataFactory;
    private final SentryEnvelopeItemHeader header;

    SentryEnvelopeItem(SentryEnvelopeItemHeader sentryEnvelopeItemHeader, byte[] bArr) {
        this.header = (SentryEnvelopeItemHeader) Objects.requireNonNull(sentryEnvelopeItemHeader, "SentryEnvelopeItemHeader is required.");
        this.data = bArr;
        this.dataFactory = null;
    }

    SentryEnvelopeItem(SentryEnvelopeItemHeader sentryEnvelopeItemHeader, Callable<byte[]> callable) {
        this.header = (SentryEnvelopeItemHeader) Objects.requireNonNull(sentryEnvelopeItemHeader, "SentryEnvelopeItemHeader is required.");
        this.dataFactory = (Callable) Objects.requireNonNull(callable, "DataFactory is required.");
        this.data = null;
    }

    public byte[] getData() throws Exception {
        Callable<byte[]> callable;
        if (this.data == null && (callable = this.dataFactory) != null) {
            this.data = callable.call();
        }
        return this.data;
    }

    public SentryEnvelopeItemHeader getHeader() {
        return this.header;
    }

    public static SentryEnvelopeItem fromSession(final ISerializer iSerializer, final Session session) throws IOException {
        Objects.requireNonNull(iSerializer, "ISerializer is required.");
        Objects.requireNonNull(session, "Session is required.");
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$AeToAkDEk7zkHtPq_e8ceJed2n0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromSession$0(iSerializer, session);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.Session, new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$3lcB3rxvEThjz7k6DEVATfHEuSk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, "application/json", null), (Callable<byte[]>) new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$46-c1KPXIDINWJhUxMM1np_6D_o
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromSession$0(ISerializer iSerializer, Session session) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
            try {
                iSerializer.serialize(session, bufferedWriter);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedWriter.close();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    bufferedWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public SentryEvent getEvent(ISerializer iSerializer) throws Exception {
        SentryEnvelopeItemHeader sentryEnvelopeItemHeader = this.header;
        if (sentryEnvelopeItemHeader == null || sentryEnvelopeItemHeader.getType() != SentryItemType.Event) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getData()), UTF_8));
        try {
            SentryEvent sentryEvent = (SentryEvent) iSerializer.deserialize(bufferedReader, SentryEvent.class);
            bufferedReader.close();
            return sentryEvent;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static SentryEnvelopeItem fromEvent(final ISerializer iSerializer, final SentryBaseEvent sentryBaseEvent) throws IOException {
        Objects.requireNonNull(iSerializer, "ISerializer is required.");
        Objects.requireNonNull(sentryBaseEvent, "SentryEvent is required.");
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$jxUHPkaLs3ntawrMEkpL6JjoX2Q
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromEvent$3(iSerializer, sentryBaseEvent);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.resolve(sentryBaseEvent), new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$t23M4jokC44fiqHGQ6sMi5rhekE
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, "application/json", null), (Callable<byte[]>) new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$Ca10OJ81isgsejMCl9wvrttHHUA
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromEvent$3(ISerializer iSerializer, SentryBaseEvent sentryBaseEvent) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
            try {
                iSerializer.serialize(sentryBaseEvent, bufferedWriter);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedWriter.close();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    bufferedWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public SentryTransaction getTransaction(ISerializer iSerializer) throws Exception {
        SentryEnvelopeItemHeader sentryEnvelopeItemHeader = this.header;
        if (sentryEnvelopeItemHeader == null || sentryEnvelopeItemHeader.getType() != SentryItemType.Transaction) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getData()), UTF_8));
        try {
            SentryTransaction sentryTransaction = (SentryTransaction) iSerializer.deserialize(bufferedReader, SentryTransaction.class);
            bufferedReader.close();
            return sentryTransaction;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static SentryEnvelopeItem fromUserFeedback(final ISerializer iSerializer, final UserFeedback userFeedback) {
        Objects.requireNonNull(iSerializer, "ISerializer is required.");
        Objects.requireNonNull(userFeedback, "UserFeedback is required.");
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$K6sCC0lt5v8cfXSR29mb44pT39U
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromUserFeedback$6(iSerializer, userFeedback);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.UserFeedback, new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$aTOwaUMUn5sWhYph7ZTiJc_4g4g
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, "application/json", null), (Callable<byte[]>) new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$VMJgxr772cgds-R9tfkB2pTuUFQ
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromUserFeedback$6(ISerializer iSerializer, UserFeedback userFeedback) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
            try {
                iSerializer.serialize(userFeedback, bufferedWriter);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedWriter.close();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    bufferedWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static SentryEnvelopeItem fromAttachment(final Attachment attachment, final long j) {
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$Mhto7tiDqIFy9fIwehC5NTfi8CM
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromAttachment$9(attachment, j);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.Attachment, (Callable<Integer>) new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$f7m5PjFMTGhnRj2xwbku-d8JHhY
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, attachment.getContentType(), attachment.getFilename(), attachment.getAttachmentType()), (Callable<byte[]>) new Callable() { // from class: io.sentry.-$$Lambda$SentryEnvelopeItem$wqxgIQhh_23hH7Vyp31tJlCgO-0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromAttachment$9(Attachment attachment, long j) throws Exception {
        if (attachment.getBytes() != null) {
            if (attachment.getBytes().length > j) {
                throw new SentryEnvelopeException(String.format("Dropping attachment with filename '%s', because the size of the passed bytes with %d bytes is bigger than the maximum allowed attachment size of %d bytes.", attachment.getFilename(), Integer.valueOf(attachment.getBytes().length), Long.valueOf(j)));
            }
            return attachment.getBytes();
        }
        if (attachment.getPathname() != null) {
            try {
                File file = new File(attachment.getPathname());
                if (!file.isFile()) {
                    throw new SentryEnvelopeException(String.format("Reading the attachment %s failed, because the file located at the path is not a file.", attachment.getPathname()));
                }
                if (!file.canRead()) {
                    throw new SentryEnvelopeException(String.format("Reading the attachment %s failed, because can't read the file.", attachment.getPathname()));
                }
                if (file.length() > j) {
                    throw new SentryEnvelopeException(String.format("Dropping attachment, because the size of the it located at '%s' with %d bytes is bigger than the maximum allowed attachment size of %d bytes.", attachment.getPathname(), Long.valueOf(file.length()), Long.valueOf(j)));
                }
                FileInputStream fileInputStream = new FileInputStream(attachment.getPathname());
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i = bufferedInputStream.read(bArr);
                                if (i != -1) {
                                    byteArrayOutputStream.write(bArr, 0, i);
                                } else {
                                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                                    byteArrayOutputStream.close();
                                    bufferedInputStream.close();
                                    fileInputStream.close();
                                    return byteArray;
                                }
                                try {
                                    bufferedInputStream.close();
                                } catch (Throwable th) {
                                    th.addSuppressed(th);
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                            }
                            throw th2;
                        }
                    } catch (Throwable th4) {
                        bufferedInputStream.close();
                        throw th4;
                    }
                } catch (Throwable th5) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th6) {
                        th5.addSuppressed(th6);
                    }
                    throw th5;
                }
            } catch (IOException | SecurityException unused) {
                throw new SentryEnvelopeException(String.format("Reading the attachment %s failed.", attachment.getPathname()));
            }
        }
        throw new SentryEnvelopeException(String.format("Couldn't attach the attachment %s.\nPlease check that either bytes or a path is set.", attachment.getFilename()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CachedItem {
        private byte[] bytes;
        private final Callable<byte[]> dataFactory;

        private static byte[] orEmptyArray(byte[] bArr) {
            return bArr != null ? bArr : new byte[0];
        }

        public CachedItem(Callable<byte[]> callable) {
            this.dataFactory = callable;
        }

        public byte[] getBytes() throws Exception {
            Callable<byte[]> callable;
            if (this.bytes == null && (callable = this.dataFactory) != null) {
                this.bytes = callable.call();
            }
            return orEmptyArray(this.bytes);
        }
    }
}
