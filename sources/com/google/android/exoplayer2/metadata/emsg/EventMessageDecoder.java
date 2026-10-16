package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class EventMessageDecoder implements MetadataDecoder {
    private static final String TAG = "EventMessageDecoder";

    @Override // com.google.android.exoplayer2.metadata.MetadataDecoder
    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        byte[] bArrArray = byteBuffer.array();
        int iLimit = byteBuffer.limit();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArrArray, iLimit);
        String str = (String) Assertions.checkNotNull(parsableByteArray.readNullTerminatedString());
        String str2 = (String) Assertions.checkNotNull(parsableByteArray.readNullTerminatedString());
        long unsignedInt = parsableByteArray.readUnsignedInt();
        long unsignedInt2 = parsableByteArray.readUnsignedInt();
        if (unsignedInt2 != 0) {
            Log.w(TAG, "Ignoring non-zero presentation_time_delta: " + unsignedInt2);
        }
        return new Metadata(new EventMessage(str, str2, Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000L, unsignedInt), parsableByteArray.readUnsignedInt(), Arrays.copyOfRange(bArrArray, parsableByteArray.getPosition(), iLimit)));
    }
}
