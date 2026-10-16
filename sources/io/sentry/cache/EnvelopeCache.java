package io.sentry.cache;

import io.sentry.DateUtils;
import io.sentry.SentryCrashLastRunState;
import io.sentry.SentryEnvelope;
import io.sentry.SentryEnvelopeItem;
import io.sentry.SentryItemType;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.Session;
import io.sentry.hints.DiskFlushNotification;
import io.sentry.hints.SessionEnd;
import io.sentry.hints.SessionStart;
import io.sentry.transport.NoOpEnvelopeCache;
import io.sentry.util.Objects;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class EnvelopeCache extends CacheStrategy implements IEnvelopeCache {
    public static final String CRASH_MARKER_FILE = "last_crash";
    public static final String NATIVE_CRASH_MARKER_FILE = ".sentry-native/last_crash";
    public static final String PREFIX_CURRENT_SESSION_FILE = "session";
    static final String SUFFIX_CURRENT_SESSION_FILE = ".json";
    public static final String SUFFIX_ENVELOPE_FILE = ".envelope";
    private final Map<SentryEnvelope, String> fileNameMap;

    public static IEnvelopeCache create(SentryOptions sentryOptions) {
        String cacheDirPath = sentryOptions.getCacheDirPath();
        int maxCacheItems = sentryOptions.getMaxCacheItems();
        if (cacheDirPath == null) {
            sentryOptions.getLogger().log(SentryLevel.WARNING, "maxCacheItems is null, returning NoOpEnvelopeCache", new Object[0]);
            return NoOpEnvelopeCache.getInstance();
        }
        return new EnvelopeCache(sentryOptions, cacheDirPath, maxCacheItems);
    }

    private EnvelopeCache(SentryOptions sentryOptions, String str, int i) {
        super(sentryOptions, str, i);
        this.fileNameMap = new WeakHashMap();
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0104  */
    /* JADX WARN: Code duplicated, block: B:50:0x011a  */
    /* JADX WARN: Code duplicated, block: B:52:0x012d  */
    /* JADX WARN: Code duplicated, block: B:54:0x0142  */
    /* JADX WARN: Code duplicated, block: B:59:0x0167  */
    /* JADX WARN: Code duplicated, block: B:61:0x017d  */
    /* JADX WARN: Code duplicated, block: B:63:0x0199  */
    /* JADX WARN: Code duplicated, block: B:74:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [io.sentry.SentryCrashLastRunState] */
    /* JADX WARN: Type inference failed for: r1v16, types: [io.sentry.ILogger] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    @Override // io.sentry.cache.IEnvelopeCache
    public void store(SentryEnvelope sentryEnvelope, Object obj) {
        File envelopeFile;
        ?? r6;
        ?? r7;
        File file;
        ?? r8;
        ?? r9;
        Objects.requireNonNull(sentryEnvelope, "Envelope is required.");
        rotateCacheIfNeeded(allEnvelopeFiles());
        File currentSessionFile = getCurrentSessionFile();
        if ((obj instanceof SessionEnd) && !currentSessionFile.delete()) {
            this.options.getLogger().log(SentryLevel.WARNING, "Current envelope doesn't exist.", new Object[0]);
        }
        if (obj instanceof SessionStart) {
            if (currentSessionFile.exists()) {
                ?? r10 = new Object[0];
                this.options.getLogger().log(SentryLevel.WARNING, "Current session is not ended, we'd need to end it.", r10);
                try {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(currentSessionFile), UTF_8));
                        try {
                            Session session = (Session) this.serializer.deserialize(bufferedReader, Session.class);
                            if (session == null) {
                                this.options.getLogger().log(SentryLevel.ERROR, "Stream from path %s resulted in a null envelope.", currentSessionFile.getAbsolutePath());
                                r9 = 0;
                            } else {
                                File file2 = new File(this.options.getCacheDirPath(), NATIVE_CRASH_MARKER_FILE);
                                Date date = null;
                                if (file2.exists()) {
                                    this.options.getLogger().log(SentryLevel.INFO, "Crash marker file exists, last Session is gonna be Crashed.", new Object[0]);
                                    Date timestampFromCrashMarkerFile = getTimestampFromCrashMarkerFile(file2);
                                    try {
                                        if (!file2.delete()) {
                                            this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete the crash marker file. %s.", file2.getAbsolutePath());
                                        }
                                        session.update(Session.State.Crashed, null, true);
                                        r10 = 1;
                                        date = timestampFromCrashMarkerFile;
                                    } catch (Throwable th) {
                                        th = th;
                                        r10 = 1;
                                        try {
                                            bufferedReader.close();
                                        } catch (Throwable th2) {
                                            th.addSuppressed(th2);
                                        }
                                        throw th;
                                    }
                                } else {
                                    r10 = 0;
                                }
                                try {
                                    session.end(date);
                                    SentryEnvelope sentryEnvelopeFrom = SentryEnvelope.from(this.serializer, session, this.options.getSdkVersion());
                                    writeEnvelopeToDisk(getEnvelopeFile(sentryEnvelopeFrom), sentryEnvelopeFrom);
                                    r9 = r10;
                                } catch (Throwable th3) {
                                    th = th3;
                                    bufferedReader.close();
                                    throw th;
                                }
                            }
                            bufferedReader.close();
                            r8 = r9;
                        } catch (Throwable th4) {
                            th = th4;
                            r10 = 0;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        this.options.getLogger().log(SentryLevel.ERROR, "Error processing session.", th);
                        r8 = r10;
                        r6 = r8;
                        if (!currentSessionFile.delete()) {
                            this.options.getLogger().log(SentryLevel.WARNING, "Failed to delete the current session file.", new Object[0]);
                            r6 = r8;
                        }
                        updateCurrentSession(currentSessionFile, sentryEnvelope);
                        r7 = r6;
                        if (r6 == 0) {
                            file = new File(this.options.getCacheDirPath(), CRASH_MARKER_FILE);
                            if (file.exists()) {
                                this.options.getLogger().log(SentryLevel.INFO, "Crash marker file exists, crashedLastRun will return true.", new Object[0]);
                                if (!file.delete()) {
                                    r7 = r6;
                                    this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete the crash marker file. %s.", file.getAbsolutePath());
                                }
                                r7 = r6;
                                r7 = 1;
                            }
                        }
                        r7 = r6;
                        SentryCrashLastRunState.getInstance().setCrashedLastRun(r7);
                        envelopeFile = getEnvelopeFile(sentryEnvelope);
                        if (envelopeFile.exists()) {
                            this.options.getLogger().log(SentryLevel.WARNING, "Not adding Envelope to offline storage because it already exists: %s", envelopeFile.getAbsolutePath());
                            return;
                        }
                        this.options.getLogger().log(SentryLevel.DEBUG, "Adding Envelope to offline storage: %s", envelopeFile.getAbsolutePath());
                        writeEnvelopeToDisk(envelopeFile, sentryEnvelope);
                        if (obj instanceof DiskFlushNotification) {
                            writeCrashMarkerFile();
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    r10 = 0;
                    this.options.getLogger().log(SentryLevel.ERROR, "Error processing session.", th);
                    r8 = r10;
                }
                r6 = r8;
                if (!currentSessionFile.delete()) {
                    this.options.getLogger().log(SentryLevel.WARNING, "Failed to delete the current session file.", new Object[0]);
                    r6 = r8;
                }
            } else {
                r6 = 0;
            }
            updateCurrentSession(currentSessionFile, sentryEnvelope);
            r7 = r6;
            if (r6 == 0) {
                file = new File(this.options.getCacheDirPath(), CRASH_MARKER_FILE);
                if (file.exists()) {
                    this.options.getLogger().log(SentryLevel.INFO, "Crash marker file exists, crashedLastRun will return true.", new Object[0]);
                    if (!file.delete()) {
                        r7 = r6;
                        this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete the crash marker file. %s.", file.getAbsolutePath());
                    }
                    r7 = r6;
                    r7 = 1;
                }
            }
            r7 = r6;
            SentryCrashLastRunState.getInstance().setCrashedLastRun(r7);
        }
        envelopeFile = getEnvelopeFile(sentryEnvelope);
        if (envelopeFile.exists()) {
            this.options.getLogger().log(SentryLevel.WARNING, "Not adding Envelope to offline storage because it already exists: %s", envelopeFile.getAbsolutePath());
            return;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Adding Envelope to offline storage: %s", envelopeFile.getAbsolutePath());
        writeEnvelopeToDisk(envelopeFile, sentryEnvelope);
        if (obj instanceof DiskFlushNotification) {
            writeCrashMarkerFile();
        }
    }

    private void writeCrashMarkerFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(this.options.getCacheDirPath(), CRASH_MARKER_FILE));
            try {
                fileOutputStream.write(DateUtils.getTimestamp(DateUtils.getCurrentDateTime()).getBytes(UTF_8));
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            this.options.getLogger().log(SentryLevel.ERROR, "Error writing the crash marker file to the disk", th3);
        }
    }

    private Date getTimestampFromCrashMarkerFile(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
            try {
                String line = bufferedReader.readLine();
                this.options.getLogger().log(SentryLevel.DEBUG, "Crash marker file has %s timestamp.", line);
                Date dateTime = DateUtils.getDateTime(line);
                bufferedReader.close();
                return dateTime;
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Error reading the crash marker file.", e);
            return null;
        } catch (IllegalArgumentException e2) {
            this.options.getLogger().log(SentryLevel.ERROR, e2, "Error converting the crash timestamp.", new Object[0]);
            return null;
        }
    }

    private void updateCurrentSession(File file, SentryEnvelope sentryEnvelope) {
        Iterable<SentryEnvelopeItem> items = sentryEnvelope.getItems();
        if (items.iterator().hasNext()) {
            SentryEnvelopeItem next = items.iterator().next();
            if (SentryItemType.Session.equals(next.getHeader().getType())) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(next.getData()), UTF_8));
                    try {
                        Session session = (Session) this.serializer.deserialize(bufferedReader, Session.class);
                        if (session == null) {
                            this.options.getLogger().log(SentryLevel.ERROR, "Item of type %s returned null by the parser.", next.getHeader().getType());
                        } else {
                            writeSessionToDisk(file, session);
                        }
                        bufferedReader.close();
                        return;
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    this.options.getLogger().log(SentryLevel.ERROR, "Item failed to process.", th3);
                    return;
                }
            }
            this.options.getLogger().log(SentryLevel.INFO, "Current envelope has a different envelope type %s", next.getHeader().getType());
            return;
        }
        this.options.getLogger().log(SentryLevel.INFO, "Current envelope %s is empty", file.getAbsolutePath());
    }

    private void writeEnvelopeToDisk(File file, SentryEnvelope sentryEnvelope) {
        if (file.exists()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Overwriting envelope to offline storage: %s", file.getAbsolutePath());
            if (!file.delete()) {
                this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete: %s", file.getAbsolutePath());
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                this.serializer.serialize(sentryEnvelope, fileOutputStream);
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            this.options.getLogger().log(SentryLevel.ERROR, th3, "Error writing Envelope %s to offline storage", file.getAbsolutePath());
        }
    }

    private void writeSessionToDisk(File file, Session session) {
        if (file.exists()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Overwriting session to offline storage: %s", session.getSessionId());
            if (!file.delete()) {
                this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete: %s", file.getAbsolutePath());
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, UTF_8));
                try {
                    this.serializer.serialize(session, bufferedWriter);
                    bufferedWriter.close();
                    fileOutputStream.close();
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
                    fileOutputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (Throwable th5) {
            this.options.getLogger().log(SentryLevel.ERROR, th5, "Error writing Session to offline storage: %s", session.getSessionId());
        }
    }

    @Override // io.sentry.cache.IEnvelopeCache
    public void discard(SentryEnvelope sentryEnvelope) {
        Objects.requireNonNull(sentryEnvelope, "Envelope is required.");
        File envelopeFile = getEnvelopeFile(sentryEnvelope);
        if (envelopeFile.exists()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Discarding envelope from cache: %s", envelopeFile.getAbsolutePath());
            if (envelopeFile.delete()) {
                return;
            }
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete envelope: %s", envelopeFile.getAbsolutePath());
            return;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Envelope was not cached: %s", envelopeFile.getAbsolutePath());
    }

    private synchronized File getEnvelopeFile(SentryEnvelope sentryEnvelope) {
        String string;
        String str;
        if (this.fileNameMap.containsKey(sentryEnvelope)) {
            str = this.fileNameMap.get(sentryEnvelope);
        } else {
            if (sentryEnvelope.getHeader().getEventId() != null) {
                string = sentryEnvelope.getHeader().getEventId().toString();
            } else {
                string = UUID.randomUUID().toString();
            }
            String str2 = string + SUFFIX_ENVELOPE_FILE;
            this.fileNameMap.put(sentryEnvelope, str2);
            str = str2;
        }
        return new File(this.directory.getAbsolutePath(), str);
    }

    private File getCurrentSessionFile() {
        return new File(this.directory.getAbsolutePath(), "session.json");
    }

    @Override // java.lang.Iterable
    public Iterator<SentryEnvelope> iterator() {
        File[] fileArrAllEnvelopeFiles = allEnvelopeFiles();
        ArrayList arrayList = new ArrayList(fileArrAllEnvelopeFiles.length);
        for (File file : fileArrAllEnvelopeFiles) {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    arrayList.add(this.serializer.deserializeEnvelope(bufferedInputStream));
                    bufferedInputStream.close();
                } catch (Throwable th) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Envelope file '%s' disappeared while converting all cached files to envelopes.", file.getAbsolutePath());
            } catch (IOException e) {
                this.options.getLogger().log(SentryLevel.ERROR, String.format("Error while reading cached envelope from file %s", file.getAbsolutePath()), e);
            }
        }
        return arrayList.iterator();
    }

    private File[] allEnvelopeFiles() {
        File[] fileArrListFiles;
        return (!isDirectoryValid() || (fileArrListFiles = this.directory.listFiles(new FilenameFilter() { // from class: io.sentry.cache.-$$Lambda$EnvelopeCache$7-eIJ0Vkpi1WSS08a1w-qss3p1M
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return str.endsWith(EnvelopeCache.SUFFIX_ENVELOPE_FILE);
            }
        })) == null) ? new File[0] : fileArrListFiles;
    }
}
