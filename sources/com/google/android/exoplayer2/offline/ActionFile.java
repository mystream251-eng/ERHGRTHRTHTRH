package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class ActionFile {
    static final int VERSION = 0;
    private final File actionFile;
    private final AtomicFile atomicFile;

    public ActionFile(File file) {
        this.actionFile = file;
        this.atomicFile = new AtomicFile(file);
    }

    public DownloadAction[] load(DownloadAction.Deserializer... deserializerArr) throws IOException {
        if (!this.actionFile.exists()) {
            return new DownloadAction[0];
        }
        try {
            InputStream inputStreamOpenRead = this.atomicFile.openRead();
            DataInputStream dataInputStream = new DataInputStream(inputStreamOpenRead);
            int i = dataInputStream.readInt();
            if (i > 0) {
                throw new IOException("Unsupported action file version: " + i);
            }
            int i2 = dataInputStream.readInt();
            DownloadAction[] downloadActionArr = new DownloadAction[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                downloadActionArr[i3] = DownloadAction.deserializeFromStream(deserializerArr, dataInputStream);
            }
            Util.closeQuietly(inputStreamOpenRead);
            return downloadActionArr;
        } catch (Throwable th) {
            Util.closeQuietly((Closeable) null);
            throw th;
        }
    }

    public void store(DownloadAction... downloadActionArr) throws Throwable {
        DataOutputStream dataOutputStream = null;
        try {
            DataOutputStream dataOutputStream2 = new DataOutputStream(this.atomicFile.startWrite());
            try {
                dataOutputStream2.writeInt(0);
                dataOutputStream2.writeInt(downloadActionArr.length);
                for (DownloadAction downloadAction : downloadActionArr) {
                    DownloadAction.serializeToStream(downloadAction, dataOutputStream2);
                }
                this.atomicFile.endWrite(dataOutputStream2);
                Util.closeQuietly((Closeable) null);
            } catch (Throwable th) {
                th = th;
                dataOutputStream = dataOutputStream2;
                Util.closeQuietly(dataOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
