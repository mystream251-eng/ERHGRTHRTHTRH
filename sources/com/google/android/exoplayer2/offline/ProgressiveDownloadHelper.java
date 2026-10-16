package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.source.TrackGroupArray;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ProgressiveDownloadHelper extends DownloadHelper {
    private final String customCacheKey;
    private final Uri uri;

    @Override // com.google.android.exoplayer2.offline.DownloadHelper
    public int getPeriodCount() {
        return 1;
    }

    @Override // com.google.android.exoplayer2.offline.DownloadHelper
    protected void prepareInternal() {
    }

    @Override // com.google.android.exoplayer2.offline.DownloadHelper
    public /* bridge */ /* synthetic */ DownloadAction getDownloadAction(byte[] bArr, List list) {
        return getDownloadAction(bArr, (List<TrackKey>) list);
    }

    public ProgressiveDownloadHelper(Uri uri) {
        this(uri, null);
    }

    public ProgressiveDownloadHelper(Uri uri, String str) {
        this.uri = uri;
        this.customCacheKey = str;
    }

    @Override // com.google.android.exoplayer2.offline.DownloadHelper
    public TrackGroupArray getTrackGroups(int i) {
        return TrackGroupArray.EMPTY;
    }

    @Override // com.google.android.exoplayer2.offline.DownloadHelper
    public ProgressiveDownloadAction getDownloadAction(byte[] bArr, List<TrackKey> list) {
        return ProgressiveDownloadAction.createDownloadAction(this.uri, bArr, this.customCacheKey);
    }

    @Override // com.google.android.exoplayer2.offline.DownloadHelper
    public ProgressiveDownloadAction getRemoveAction(byte[] bArr) {
        return ProgressiveDownloadAction.createRemoveAction(this.uri, bArr, this.customCacheKey);
    }
}
