package com.dotscreen.tv.common.search;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LocalSearchProvider extends ContentProvider {
    protected static final boolean DEBUG = true;
    protected static final int DEFAULT_SEARCH_ACTION = 1;
    protected static final int DEFAULT_SEARCH_LIMIT = 10;
    protected static final String EXPECTED_PATH_PREFIX = "/search_suggest_query";
    public static final String LIVE_CONTENTS = "1";
    public static final String NO_LIVE_CONTENTS = "0";
    public static final int PROGRESS_PERCENTAGE_HIDE = -1;
    public static final String[] SEARCHABLE_COLUMNS = {"suggest_text_1", "suggest_text_2", "suggest_result_card_image", "suggest_intent_action", "suggest_intent_data", "suggest_content_type", "suggest_is_live", "suggest_video_width", "suggest_video_height", "suggest_duration", "suggest_production_year"};
    protected static final String SUGGEST_COLUMN_PROGRESS_BAR_PERCENTAGE = "progress_bar_percentage";
    protected static final String SUGGEST_PARAMETER_ACTION = "action";
    protected static final String TAG = "LocalSearchProvider";
    protected Context mContext = null;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    private Cursor createSuggestionsCursor(List<SearchResult> list) {
        MatrixCursor matrixCursor = new MatrixCursor(SEARCHABLE_COLUMNS, list.size());
        ArrayList arrayList = new ArrayList(SEARCHABLE_COLUMNS.length);
        for (SearchResult searchResult : list) {
            arrayList.clear();
            arrayList.add(searchResult.title);
            arrayList.add(searchResult.description);
            arrayList.add(searchResult.imageUri);
            arrayList.add(searchResult.intentAction);
            arrayList.add(searchResult.intentData);
            arrayList.add(searchResult.contentType);
            arrayList.add(searchResult.isLive ? LIVE_CONTENTS : NO_LIVE_CONTENTS);
            String strValueOf = null;
            arrayList.add(searchResult.videoWidth == 0 ? null : String.valueOf(searchResult.videoWidth));
            arrayList.add(searchResult.videoHeight == 0 ? null : String.valueOf(searchResult.videoHeight));
            if (searchResult.duration != 0) {
                strValueOf = String.valueOf(searchResult.duration);
            }
            arrayList.add(strValueOf);
            arrayList.add(String.valueOf(searchResult.progressPercentage));
            matrixCursor.addRow(arrayList);
        }
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        if (checkUriCorrect(uri)) {
            return "vnd.android.cursor.dir/vnd.android.search.suggest";
        }
        return null;
    }

    private static boolean checkUriCorrect(Uri uri) {
        return uri != null && uri.getPath().startsWith(EXPECTED_PATH_PREFIX);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    public static class SearchResult {
        public long channelId;
        public String channelNumber;
        public String contentType;
        public String description;
        public long duration;
        public String imageUri;
        public String intentAction;
        public String intentData;
        public boolean isLive;
        public int progressPercentage;
        public String title;
        public int videoHeight;
        public int videoWidth;

        public String toString() {
            return "channelId: " + this.channelId + ", channelNumber: " + this.channelNumber + ", title: " + this.title;
        }
    }
}
