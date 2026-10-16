package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class DownloadAction {
    private static Deserializer[] defaultDeserializers;
    public final byte[] data;
    public final boolean isRemoveAction;
    public final String type;
    public final Uri uri;
    public final int version;

    public abstract Downloader createDownloader(DownloaderConstructorHelper downloaderConstructorHelper);

    protected abstract void writeToStream(DataOutputStream dataOutputStream) throws IOException;

    public static abstract class Deserializer {
        public final String type;
        public final int version;

        public abstract DownloadAction readFromStream(int i, DataInputStream dataInputStream) throws IOException;

        public Deserializer(String str, int i) {
            this.type = str;
            this.version = i;
        }
    }

    public static synchronized Deserializer[] getDefaultDeserializers() {
        int i;
        int i2;
        int i3;
        if (defaultDeserializers != null) {
            return defaultDeserializers;
        }
        Deserializer[] deserializerArr = new Deserializer[4];
        deserializerArr[0] = ProgressiveDownloadAction.DESERIALIZER;
        int i4 = 1;
        try {
            i = 2;
            try {
                deserializerArr[1] = getDeserializer(Class.forName("com.google.android.exoplayer2.source.dash.offline.DashDownloadAction"));
            } catch (Exception unused) {
                i4 = 2;
                i = i4;
            }
        } catch (Exception unused2) {
        }
        try {
            i2 = i + 1;
            try {
                deserializerArr[i] = getDeserializer(Class.forName("com.google.android.exoplayer2.source.hls.offline.HlsDownloadAction"));
            } catch (Exception unused3) {
                i = i2;
                i2 = i;
            }
        } catch (Exception unused4) {
        }
        try {
            i3 = i2 + 1;
            try {
                deserializerArr[i2] = getDeserializer(Class.forName("com.google.android.exoplayer2.source.smoothstreaming.offline.SsDownloadAction"));
            } catch (Exception unused5) {
                i2 = i3;
                i3 = i2;
            }
        } catch (Exception unused6) {
        }
        Deserializer[] deserializerArr2 = (Deserializer[]) Arrays.copyOf((Object[]) Assertions.checkNotNull(deserializerArr), i3);
        defaultDeserializers = deserializerArr2;
        return deserializerArr2;
    }

    public static DownloadAction deserializeFromStream(Deserializer[] deserializerArr, InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String utf = dataInputStream.readUTF();
        int i = dataInputStream.readInt();
        for (Deserializer deserializer : deserializerArr) {
            if (utf.equals(deserializer.type) && deserializer.version >= i) {
                return deserializer.readFromStream(i, dataInputStream);
            }
        }
        throw new DownloadException("No deserializer found for:" + utf + ", " + i);
    }

    public static void serializeToStream(DownloadAction downloadAction, OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF(downloadAction.type);
        dataOutputStream.writeInt(downloadAction.version);
        downloadAction.writeToStream(dataOutputStream);
        dataOutputStream.flush();
    }

    protected DownloadAction(String str, int i, Uri uri, boolean z, byte[] bArr) {
        this.type = str;
        this.version = i;
        this.uri = uri;
        this.isRemoveAction = z;
        this.data = bArr == null ? Util.EMPTY_BYTE_ARRAY : bArr;
    }

    public final byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            serializeToStream(this, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException();
        }
    }

    public boolean isSameMedia(DownloadAction downloadAction) {
        return this.uri.equals(downloadAction.uri);
    }

    public List<StreamKey> getKeys() {
        return Collections.emptyList();
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DownloadAction downloadAction = (DownloadAction) obj;
        return this.type.equals(downloadAction.type) && this.version == downloadAction.version && this.uri.equals(downloadAction.uri) && this.isRemoveAction == downloadAction.isRemoveAction && Arrays.equals(this.data, downloadAction.data);
    }

    public int hashCode() {
        return (((this.uri.hashCode() * 31) + (this.isRemoveAction ? 1 : 0)) * 31) + Arrays.hashCode(this.data);
    }

    private static Deserializer getDeserializer(Class<?> cls) throws IllegalAccessException, NoSuchFieldException {
        return (Deserializer) Assertions.checkNotNull(cls.getDeclaredField("DESERIALIZER").get(null));
    }
}
