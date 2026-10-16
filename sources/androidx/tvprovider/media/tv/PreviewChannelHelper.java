package androidx.tvprovider.media.tv;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PreviewChannelHelper {
    private static final int DEFAULT_READ_TIMEOUT_MILLIS = 10000;
    private static final int DEFAULT_URL_CONNNECTION_TIMEOUT_MILLIS = 3000;
    private static final int INVALID_CONTENT_ID = -1;
    private static final String TAG = "PreviewChannelHelper";
    private final Context mContext;
    private final int mUrlConnectionTimeoutMillis;
    private final int mUrlReadTimeoutMillis;

    public PreviewChannelHelper(Context context) {
        this(context, 3000, 10000);
    }

    public PreviewChannelHelper(Context context, int i, int i2) {
        this.mContext = context;
        this.mUrlConnectionTimeoutMillis = i;
        this.mUrlReadTimeoutMillis = i2;
    }

    public long publishChannel(PreviewChannel previewChannel) throws IOException {
        try {
            Uri uriInsert = this.mContext.getContentResolver().insert(TvContractCompat.Channels.CONTENT_URI, previewChannel.toContentValues());
            if (uriInsert == null || uriInsert.equals(Uri.EMPTY)) {
                throw new NullPointerException("Channel insertion failed");
            }
            long id = ContentUris.parseId(uriInsert);
            if (addChannelLogo(id, previewChannel)) {
                return id;
            }
            deletePreviewChannel(id);
            throw new IOException("Failed to add logo, so channel (ID=" + id + ") was not created");
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1L;
        }
    }

    public long publishDefaultChannel(PreviewChannel previewChannel) throws IOException {
        long jPublishChannel = publishChannel(previewChannel);
        TvContractCompat.requestChannelBrowsable(this.mContext, jPublishChannel);
        return jPublishChannel;
    }

    public List<PreviewChannel> getAllChannels() {
        Cursor cursorQuery = this.mContext.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, PreviewChannel.Columns.PROJECTION, null, null, null);
        ArrayList arrayList = new ArrayList();
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            do {
                arrayList.add(PreviewChannel.fromCursor(cursorQuery));
            } while (cursorQuery.moveToNext());
        }
        return arrayList;
    }

    public PreviewChannel getPreviewChannel(long j) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(TvContractCompat.buildChannelUri(j), PreviewChannel.Columns.PROJECTION, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        return PreviewChannel.fromCursor(cursorQuery);
    }

    public void updatePreviewChannel(long j, PreviewChannel previewChannel) throws IOException {
        PreviewChannel previewChannel2 = getPreviewChannel(j);
        if (previewChannel2 != null && previewChannel2.hasAnyUpdatedValues(previewChannel)) {
            updatePreviewChannelInternal(j, previewChannel);
        }
        if (!previewChannel.isLogoChanged() || addChannelLogo(j, previewChannel)) {
            return;
        }
        throw new IOException("Fail to update channel (ID=" + j + ") logo.");
    }

    protected void updatePreviewChannelInternal(long j, PreviewChannel previewChannel) {
        this.mContext.getContentResolver().update(TvContractCompat.buildChannelUri(j), previewChannel.toContentValues(), null, null);
    }

    private boolean addChannelLogo(long j, PreviewChannel previewChannel) throws Throwable {
        boolean zCompress = false;
        if (!previewChannel.isLogoChanged()) {
            return false;
        }
        Bitmap logo = previewChannel.getLogo(this.mContext);
        if (logo == null) {
            logo = getLogoFromUri(previewChannel.getLogoUri());
        }
        try {
            OutputStream outputStreamOpenOutputStream = this.mContext.getContentResolver().openOutputStream(TvContractCompat.buildChannelLogoUri(j));
            try {
                zCompress = logo.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream);
                outputStreamOpenOutputStream.flush();
                if (outputStreamOpenOutputStream != null) {
                    outputStreamOpenOutputStream.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (outputStreamOpenOutputStream != null) {
                        try {
                            outputStreamOpenOutputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (SQLiteException | IOException | NullPointerException e) {
            Log.i(TAG, "Failed to add logo to the published channel (ID= " + j + ")", e);
        }
        return zCompress;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private Bitmap getLogoFromUri(Uri uri) throws Throwable {
        InputStream inputStream;
        Bitmap bitmapDecodeStream;
        String scheme = uri.normalizeScheme().getScheme();
        InputStream inputStream2 = null;
        try {
            if ("android.resource".equals(scheme) || "file".equals(scheme) || ToolboxAnalyticsModuleKt.KEY_CONTENT.equals(scheme)) {
                InputStream inputStreamOpenInputStream = this.mContext.getContentResolver().openInputStream(uri);
                try {
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                    inputStream2 = inputStreamOpenInputStream;
                } catch (IOException e) {
                    inputStream = inputStreamOpenInputStream;
                    e = e;
                    try {
                        Log.e(TAG, "Failed to get logo from the URI: " + uri, e);
                        if (inputStream == null) {
                            return null;
                        }
                        try {
                            inputStream.close();
                            return null;
                        } catch (IOException unused) {
                            return null;
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream2 = inputStream;
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream2 = inputStreamOpenInputStream;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    throw th;
                }
            } else {
                bitmapDecodeStream = downloadBitmap(uri);
            }
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException unused3) {
                }
            }
            return bitmapDecodeStream;
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    protected Bitmap downloadBitmap(Uri uri) throws Throwable {
        URLConnection uRLConnectionOpenConnection;
        InputStream inputStream = null;
        try {
            uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
            try {
                uRLConnectionOpenConnection.setConnectTimeout(this.mUrlConnectionTimeoutMillis);
                uRLConnectionOpenConnection.setReadTimeout(this.mUrlReadTimeoutMillis);
                inputStream = uRLConnectionOpenConnection.getInputStream();
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                if (uRLConnectionOpenConnection instanceof HttpURLConnection) {
                    ((HttpURLConnection) uRLConnectionOpenConnection).disconnect();
                }
                return bitmapDecodeStream;
            } catch (Throwable th) {
                th = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
                    throw th;
                }
                ((HttpURLConnection) uRLConnectionOpenConnection).disconnect();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            uRLConnectionOpenConnection = null;
        }
    }

    public void deletePreviewChannel(long j) {
        this.mContext.getContentResolver().delete(TvContractCompat.buildChannelUri(j), null, null);
    }

    public long publishPreviewProgram(PreviewProgram previewProgram) {
        try {
            return ContentUris.parseId(this.mContext.getContentResolver().insert(TvContractCompat.PreviewPrograms.CONTENT_URI, previewProgram.toContentValues()));
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1L;
        }
    }

    public PreviewProgram getPreviewProgram(long j) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(TvContractCompat.buildPreviewProgramUri(j), null, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        return PreviewProgram.fromCursor(cursorQuery);
    }

    public void updatePreviewProgram(long j, PreviewProgram previewProgram) {
        PreviewProgram previewProgram2 = getPreviewProgram(j);
        if (previewProgram2 == null || !previewProgram2.hasAnyUpdatedValues(previewProgram)) {
            return;
        }
        updatePreviewProgramInternal(j, previewProgram);
    }

    void updatePreviewProgramInternal(long j, PreviewProgram previewProgram) {
        this.mContext.getContentResolver().update(TvContractCompat.buildPreviewProgramUri(j), previewProgram.toContentValues(), null, null);
    }

    public void deletePreviewProgram(long j) {
        this.mContext.getContentResolver().delete(TvContractCompat.buildPreviewProgramUri(j), null, null);
    }

    public long publishWatchNextProgram(WatchNextProgram watchNextProgram) {
        try {
            return ContentUris.parseId(this.mContext.getContentResolver().insert(TvContractCompat.WatchNextPrograms.CONTENT_URI, watchNextProgram.toContentValues()));
        } catch (SecurityException e) {
            Log.e(TAG, "Your app's ability to insert data into the TvProvider may have been revoked.", e);
            return -1L;
        }
    }

    public WatchNextProgram getWatchNextProgram(long j) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(TvContractCompat.buildWatchNextProgramUri(j), null, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        return WatchNextProgram.fromCursor(cursorQuery);
    }

    public void updateWatchNextProgram(WatchNextProgram watchNextProgram, long j) {
        WatchNextProgram watchNextProgram2 = getWatchNextProgram(j);
        if (watchNextProgram2 == null || !watchNextProgram2.hasAnyUpdatedValues(watchNextProgram)) {
            return;
        }
        updateWatchNextProgram(j, watchNextProgram);
    }

    void updateWatchNextProgram(long j, WatchNextProgram watchNextProgram) {
        this.mContext.getContentResolver().update(TvContractCompat.buildWatchNextProgramUri(j), watchNextProgram.toContentValues(), null, null);
    }
}
