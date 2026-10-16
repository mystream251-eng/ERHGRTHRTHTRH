package com.android.tv.common;

import android.media.tv.TvContentRating;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class TvContentRatingCache implements MemoryManageable {
    private static final TvContentRatingCache INSTANCE = new TvContentRatingCache();
    private static final String TAG = "TvContentRatings";
    private final Map<String, TvContentRating[]> mRatingsMultiMap = new ArrayMap();
    private boolean isRatingMapUsed = false;

    public static final TvContentRatingCache getInstance() {
        return INSTANCE;
    }

    public TvContentRating[] getRatings(String str) {
        TvContentRating[] tvContentRatingArrStringToContentRatings;
        TvContentRating[] tvContentRatingArr;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        this.isRatingMapUsed = true;
        if (this.mRatingsMultiMap.containsKey(str)) {
            tvContentRatingArr = this.mRatingsMultiMap.get(str);
        } else {
            String strJoin = TextUtils.join(",", getSortedSetFromCsv(str));
            if (this.mRatingsMultiMap.containsKey(strJoin)) {
                tvContentRatingArrStringToContentRatings = this.mRatingsMultiMap.get(strJoin);
            } else {
                tvContentRatingArrStringToContentRatings = stringToContentRatings(str);
                this.mRatingsMultiMap.put(strJoin, tvContentRatingArrStringToContentRatings);
            }
            if (!strJoin.equals(str)) {
                this.mRatingsMultiMap.put(str, tvContentRatingArrStringToContentRatings);
            }
            tvContentRatingArr = tvContentRatingArrStringToContentRatings;
        }
        this.isRatingMapUsed = false;
        return tvContentRatingArr;
    }

    static TvContentRating[] stringToContentRatings(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Set<String> sortedSetFromCsv = getSortedSetFromCsv(str);
        ArrayList arrayList = new ArrayList();
        for (String str2 : sortedSetFromCsv) {
            try {
                arrayList.add(TvContentRating.unflattenFromString(str2));
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Can't parse the content rating: '" + str2 + "'", e);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return (TvContentRating[]) arrayList.toArray(new TvContentRating[arrayList.size()]);
    }

    private static Set<String> getSortedSetFromCsv(String str) {
        return toSortedSet(str.split("\\s*,\\s*"));
    }

    private static Set<String> toSortedSet(String[] strArr) {
        if (strArr.length == 0) {
            return Collections.EMPTY_SET;
        }
        if (strArr.length == 1) {
            return Collections.singleton(strArr[0]);
        }
        TreeSet treeSet = new TreeSet();
        Collections.addAll(treeSet, strArr);
        return treeSet;
    }

    public static String contentRatingsToString(TvContentRating[] tvContentRatingArr) {
        if (tvContentRatingArr == null || tvContentRatingArr.length == 0) {
            return null;
        }
        int length = tvContentRatingArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < tvContentRatingArr.length; i++) {
            strArr[i] = tvContentRatingArr[i].flattenToString();
        }
        if (length == 1) {
            return strArr[0];
        }
        return TextUtils.join(",", toSortedSet(strArr));
    }

    @Override // com.android.tv.common.MemoryManageable
    public void performTrimMemory(int i) {
        if (this.isRatingMapUsed) {
            return;
        }
        this.mRatingsMultiMap.clear();
    }

    private TvContentRatingCache() {
    }
}
