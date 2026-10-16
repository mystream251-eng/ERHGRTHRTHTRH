package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class ProgressiveDownloadAction extends DownloadAction {
    private static final int VERSION = 0;
    private final String customCacheKey;
    private static final String TYPE = "progressive";
    public static final DownloadAction.Deserializer DESERIALIZER = new DownloadAction.Deserializer(TYPE, 0) { // from class: com.google.android.exoplayer2.offline.ProgressiveDownloadAction.1
        @Override // com.google.android.exoplayer2.offline.DownloadAction.Deserializer
        public ProgressiveDownloadAction readFromStream(int i, DataInputStream dataInputStream) throws IOException {
            Uri uri = Uri.parse(dataInputStream.readUTF());
            boolean z = dataInputStream.readBoolean();
            byte[] bArr = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(bArr);
            return new ProgressiveDownloadAction(uri, z, bArr, dataInputStream.readBoolean() ? dataInputStream.readUTF() : null);
        }
    };

    public static ProgressiveDownloadAction createDownloadAction(Uri uri, byte[] bArr, String str) {
        return new ProgressiveDownloadAction(uri, false, bArr, str);
    }

    public static ProgressiveDownloadAction createRemoveAction(Uri uri, byte[] bArr, String str) {
        return new ProgressiveDownloadAction(uri, true, bArr, str);
    }

    @Deprecated
    public ProgressiveDownloadAction(Uri uri, boolean z, byte[] bArr, String str) {
        super(TYPE, 0, uri, z, bArr);
        this.customCacheKey = str;
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    public ProgressiveDownloader createDownloader(DownloaderConstructorHelper downloaderConstructorHelper) {
        return new ProgressiveDownloader(this.uri, this.customCacheKey, downloaderConstructorHelper);
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    protected void writeToStream(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.uri.toString());
        dataOutputStream.writeBoolean(this.isRemoveAction);
        dataOutputStream.writeInt(this.data.length);
        dataOutputStream.write(this.data);
        boolean z = this.customCacheKey != null;
        dataOutputStream.writeBoolean(z);
        if (z) {
            dataOutputStream.writeUTF(this.customCacheKey);
        }
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    public boolean isSameMedia(DownloadAction downloadAction) {
        return (downloadAction instanceof ProgressiveDownloadAction) && getCacheKey().equals(((ProgressiveDownloadAction) downloadAction).getCacheKey());
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj)) {
            return Util.areEqual(this.customCacheKey, ((ProgressiveDownloadAction) obj).customCacheKey);
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        String str = this.customCacheKey;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    private String getCacheKey() {
        String str = this.customCacheKey;
        return str != null ? str : CacheUtil.generateKey(this.uri);
    }
}
