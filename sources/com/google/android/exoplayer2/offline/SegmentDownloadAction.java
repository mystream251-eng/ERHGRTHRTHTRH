package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class SegmentDownloadAction extends DownloadAction {
    public final List<StreamKey> keys;

    protected static abstract class SegmentDownloadActionDeserializer extends DownloadAction.Deserializer {
        protected abstract DownloadAction createDownloadAction(Uri uri, boolean z, byte[] bArr, List<StreamKey> list);

        public SegmentDownloadActionDeserializer(String str, int i) {
            super(str, i);
        }

        @Override // com.google.android.exoplayer2.offline.DownloadAction.Deserializer
        public final DownloadAction readFromStream(int i, DataInputStream dataInputStream) throws IOException {
            Uri uri = Uri.parse(dataInputStream.readUTF());
            boolean z = dataInputStream.readBoolean();
            byte[] bArr = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(bArr);
            int i2 = dataInputStream.readInt();
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < i2; i3++) {
                arrayList.add(readKey(i, dataInputStream));
            }
            return createDownloadAction(uri, z, bArr, arrayList);
        }

        protected StreamKey readKey(int i, DataInputStream dataInputStream) throws IOException {
            return new StreamKey(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt());
        }
    }

    protected SegmentDownloadAction(String str, int i, Uri uri, boolean z, byte[] bArr, List<StreamKey> list) {
        super(str, i, uri, z, bArr);
        if (z) {
            Assertions.checkArgument(list.isEmpty());
            this.keys = Collections.emptyList();
        } else {
            ArrayList arrayList = new ArrayList(list);
            Collections.sort(arrayList);
            this.keys = Collections.unmodifiableList(arrayList);
        }
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    public List<StreamKey> getKeys() {
        return this.keys;
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    public final void writeToStream(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.uri.toString());
        dataOutputStream.writeBoolean(this.isRemoveAction);
        dataOutputStream.writeInt(this.data.length);
        dataOutputStream.write(this.data);
        dataOutputStream.writeInt(this.keys.size());
        for (int i = 0; i < this.keys.size(); i++) {
            writeKey(dataOutputStream, this.keys.get(i));
        }
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj)) {
            return this.keys.equals(((SegmentDownloadAction) obj).keys);
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.offline.DownloadAction
    public int hashCode() {
        return (super.hashCode() * 31) + this.keys.hashCode();
    }

    private void writeKey(DataOutputStream dataOutputStream, StreamKey streamKey) throws IOException {
        dataOutputStream.writeInt(streamKey.periodIndex);
        dataOutputStream.writeInt(streamKey.groupIndex);
        dataOutputStream.writeInt(streamKey.trackIndex);
    }
}
