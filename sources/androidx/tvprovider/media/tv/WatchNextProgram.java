package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class WatchNextProgram extends BasePreviewProgram {
    private static final int INVALID_INT_VALUE = -1;
    private static final long INVALID_LONG_VALUE = -1;
    public static final String[] PROJECTION = getProjection();
    public static final int WATCH_NEXT_TYPE_UNKNOWN = -1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface WatchNextType {
    }

    WatchNextProgram(Builder builder) {
        super(builder);
    }

    public int getWatchNextType() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public long getLastEngagementTimeUtcMillis() {
        Long asLong = this.mValues.getAsLong(TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS);
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    @Override // androidx.tvprovider.media.tv.BasePreviewProgram, androidx.tvprovider.media.tv.BaseProgram
    public boolean equals(Object obj) {
        if (obj instanceof WatchNextProgram) {
            return this.mValues.equals(((WatchNextProgram) obj).mValues);
        }
        return false;
    }

    public boolean hasAnyUpdatedValues(WatchNextProgram watchNextProgram) {
        for (String str : watchNextProgram.mValues.keySet()) {
            if (!Objects.deepEquals(watchNextProgram.mValues.get(str), this.mValues.get(str))) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.tvprovider.media.tv.BaseProgram
    public String toString() {
        return "WatchNextProgram{" + this.mValues.toString() + "}";
    }

    @Override // androidx.tvprovider.media.tv.BasePreviewProgram, androidx.tvprovider.media.tv.BaseProgram
    public ContentValues toContentValues() {
        return toContentValues(false);
    }

    @Override // androidx.tvprovider.media.tv.BasePreviewProgram
    public ContentValues toContentValues(boolean z) {
        ContentValues contentValues = super.toContentValues(z);
        if (Build.VERSION.SDK_INT < 26) {
            contentValues.remove(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE);
            contentValues.remove(TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS);
        }
        return contentValues;
    }

    public static WatchNextProgram fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        BasePreviewProgram.setFieldsFromCursor(cursor, (BasePreviewProgram.Builder) builder);
        int columnIndex = cursor.getColumnIndex(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE);
        if (columnIndex >= 0 && !cursor.isNull(columnIndex)) {
            builder.setWatchNextType(cursor.getInt(columnIndex));
        }
        int columnIndex2 = cursor.getColumnIndex(TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS);
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setLastEngagementTimeUtcMillis(cursor.getLong(columnIndex2));
        }
        return builder.build();
    }

    private static String[] getProjection() {
        return (String[]) CollectionUtils.concatAll(BasePreviewProgram.PROJECTION, new String[]{TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE, TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS});
    }

    public static final class Builder extends BasePreviewProgram.Builder<Builder> {
        public Builder() {
        }

        public Builder(WatchNextProgram watchNextProgram) {
            this.mValues = new ContentValues(watchNextProgram.mValues);
        }

        public Builder setWatchNextType(int i) {
            this.mValues.put(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE, Integer.valueOf(i));
            return this;
        }

        public Builder setLastEngagementTimeUtcMillis(long j) {
            this.mValues.put(TvContractCompat.WatchNextPrograms.COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS, Long.valueOf(j));
            return this;
        }

        public WatchNextProgram build() {
            return new WatchNextProgram(this);
        }
    }
}
