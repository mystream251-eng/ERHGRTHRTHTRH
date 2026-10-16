package com.google.android.media.tv.companionlibrary.utils;

import android.content.Context;
import android.media.tv.TvContentRating;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.tvprovider.media.tv.TvContractCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class TvContractUtils {
    private static final boolean DEBUG = false;
    public static final int SOURCE_TYPE_HLS = 2;
    public static final int SOURCE_TYPE_HTTP_PROGRESSIVE = 3;
    public static final int SOURCE_TYPE_INVALID = -1;
    public static final int SOURCE_TYPE_MPEG_DASH = 0;
    public static final int SOURCE_TYPE_SS = 1;
    private static final String TAG = "TvContractUtils";
    private static final SparseArray<String> VIDEO_HEIGHT_TO_FORMAT_MAP;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        VIDEO_HEIGHT_TO_FORMAT_MAP = sparseArray;
        sparseArray.put(480, TvContractCompat.Channels.VIDEO_FORMAT_480P);
        VIDEO_HEIGHT_TO_FORMAT_MAP.put(576, TvContractCompat.Channels.VIDEO_FORMAT_576P);
        VIDEO_HEIGHT_TO_FORMAT_MAP.put(720, TvContractCompat.Channels.VIDEO_FORMAT_720P);
        VIDEO_HEIGHT_TO_FORMAT_MAP.put(1080, TvContractCompat.Channels.VIDEO_FORMAT_1080P);
        VIDEO_HEIGHT_TO_FORMAT_MAP.put(2160, TvContractCompat.Channels.VIDEO_FORMAT_2160P);
        VIDEO_HEIGHT_TO_FORMAT_MAP.put(4320, TvContractCompat.Channels.VIDEO_FORMAT_4320P);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:35:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:? A[SYNTHETIC] */
    public static void insertUrl(Context context, Uri uri, URL url) throws Throwable {
        OutputStream outputStream;
        InputStream inputStream = null;
        outputStreamOpenOutputStream = null;
        OutputStream outputStreamOpenOutputStream = null;
        inputStream = null;
        try {
            try {
                InputStream inputStreamOpenStream = url.openStream();
                try {
                    outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri);
                    copy(inputStreamOpenStream, outputStreamOpenOutputStream);
                    if (inputStreamOpenStream != null) {
                        try {
                            inputStreamOpenStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    if (outputStreamOpenOutputStream != null) {
                        outputStreamOpenOutputStream.close();
                    }
                } catch (IOException e) {
                    e = e;
                    outputStream = outputStreamOpenOutputStream;
                    inputStream = inputStreamOpenStream;
                    try {
                        Log.e(TAG, "Failed to write " + url + "  to " + uri, e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        if (outputStream == null) {
                        } else {
                            outputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                                throw th;
                            } catch (IOException unused4) {
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = outputStreamOpenOutputStream;
                    inputStream = inputStreamOpenStream;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                        throw th;
                    }
                    throw th;
                }
            } catch (IOException unused5) {
            }
        } catch (IOException e2) {
            e = e2;
            outputStream = null;
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
        }
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static TvContentRating[] stringToContentRatings(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split("\\s*,\\s*");
        int length = strArrSplit.length;
        TvContentRating[] tvContentRatingArr = new TvContentRating[length];
        for (int i = 0; i < length; i++) {
            tvContentRatingArr[i] = TvContentRating.unflattenFromString(strArrSplit[i]);
        }
        return tvContentRatingArr;
    }

    public static String contentRatingsToString(TvContentRating[] tvContentRatingArr) {
        if (tvContentRatingArr == null || tvContentRatingArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(tvContentRatingArr[0].flattenToString());
        for (int i = 1; i < tvContentRatingArr.length; i++) {
            sb.append(",");
            sb.append(tvContentRatingArr[i].flattenToString());
        }
        return sb.toString();
    }

    private TvContractUtils() {
    }

    public static class InsertLogosTask extends AsyncTask<Map<Uri, String>, Void, Void> {
        private final Context mContext;

        public InsertLogosTask(Context context) {
            this.mContext = context;
        }

        @Override // android.os.AsyncTask
        public Void doInBackground(Map<Uri, String>... mapArr) throws Throwable {
            for (Map<Uri, String> map : mapArr) {
                for (Uri uri : map.keySet()) {
                    try {
                        TvContractUtils.insertUrl(this.mContext, uri, new URL(map.get(uri)));
                    } catch (MalformedURLException e) {
                        Log.e(TvContractUtils.TAG, "Can't load " + map.get(uri), e);
                    }
                }
            }
            return null;
        }
    }
}
