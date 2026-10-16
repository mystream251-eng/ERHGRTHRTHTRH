package io.sentry.instrumentation.file;

import io.sentry.ISpan;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes2.dex */
final class FileInputStreamInitData {
    final FileInputStream delegate;
    final File file;
    final boolean isSendDefaultPii;
    final ISpan span;

    FileInputStreamInitData(File file, ISpan iSpan, FileInputStream fileInputStream, boolean z) {
        this.file = file;
        this.span = iSpan;
        this.delegate = fileInputStream;
        this.isSendDefaultPii = z;
    }
}
