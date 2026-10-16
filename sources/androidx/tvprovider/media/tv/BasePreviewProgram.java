package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasePreviewProgram extends BaseProgram {
    private static final int ASPECT_RATIO_UNKNOWN = -1;
    private static final int AVAILABILITY_UNKNOWN = -1;
    private static final int INTERACTION_TYPE_UNKNOWN = -1;
    private static final int INVALID_INT_VALUE = -1;
    private static final long INVALID_LONG_VALUE = -1;
    private static final int IS_BROWSABLE = 1;
    private static final int IS_LIVE = 1;
    private static final int IS_TRANSIENT = 1;
    public static final String[] PROJECTION = getProjection();
    private static final int TYPE_UNKNOWN = -1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AspectRatio {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Availability {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface InteractionType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TvSeriesItemType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    BasePreviewProgram(Builder builder) {
        super(builder);
    }

    public String getInternalProviderId() {
        return this.mValues.getAsString("internal_provider_id");
    }

    public Uri getPreviewVideoUri() {
        String asString = this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_VIDEO_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public int getLastPlaybackPositionMillis() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_LAST_PLAYBACK_POSITION_MILLIS);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int getDurationMillis() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_DURATION_MILLIS);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public Uri getIntentUri() {
        String asString = this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_INTENT_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public Intent getIntent() throws URISyntaxException {
        String asString = this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_INTENT_URI);
        if (asString == null) {
            return null;
        }
        return Intent.parseUri(asString, 1);
    }

    public boolean isTransient() {
        Integer asInteger = this.mValues.getAsInteger("transient");
        return asInteger != null && asInteger.intValue() == 1;
    }

    public int getType() {
        Integer asInteger = this.mValues.getAsInteger("type");
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int getTvSeriesItemType() {
        return this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_TV_SERIES_ITEM_TYPE).intValue();
    }

    public int getPosterArtAspectRatio() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_POSTER_ART_ASPECT_RATIO);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int getThumbnailAspectRatio() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_THUMBNAIL_ASPECT_RATIO);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public Uri getLogoUri() {
        String asString = this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public int getAvailability() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_AVAILABILITY);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public String getStartingPrice() {
        return this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_STARTING_PRICE);
    }

    public String getOfferPrice() {
        return this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_OFFER_PRICE);
    }

    public String getReleaseDate() {
        return this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_RELEASE_DATE);
    }

    public int getItemCount() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_ITEM_COUNT);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public boolean isLive() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE);
        return asInteger != null && asInteger.intValue() == 1;
    }

    public int getInteractionType() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_TYPE);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public long getInteractionCount() {
        Long asLong = this.mValues.getAsLong(TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_COUNT);
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public String getAuthor() {
        return this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_AUTHOR);
    }

    public boolean isBrowsable() {
        Integer asInteger = this.mValues.getAsInteger("browsable");
        return asInteger != null && asInteger.intValue() == 1;
    }

    public String getContentId() {
        return this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
    }

    public String getLogoContentDescription() {
        return this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_CONTENT_DESCRIPTION);
    }

    public String getGenre() {
        return this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_GENRE);
    }

    public long getStartTimeUtcMillis() {
        Long asLong = this.mValues.getAsLong("start_time_utc_millis");
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public long getEndTimeUtcMillis() {
        Long asLong = this.mValues.getAsLong("end_time_utc_millis");
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public Uri getPreviewAudioUri() {
        String asString = this.mValues.getAsString(TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_AUDIO_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    @Override // androidx.tvprovider.media.tv.BaseProgram
    public boolean equals(Object obj) {
        if (obj instanceof BasePreviewProgram) {
            return this.mValues.equals(((BasePreviewProgram) obj).mValues);
        }
        return false;
    }

    @Override // androidx.tvprovider.media.tv.BaseProgram
    public ContentValues toContentValues() {
        return toContentValues(false);
    }

    public ContentValues toContentValues(boolean z) {
        ContentValues contentValues = super.toContentValues();
        if (Build.VERSION.SDK_INT < 26) {
            contentValues.remove("internal_provider_id");
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_VIDEO_URI);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_LAST_PLAYBACK_POSITION_MILLIS);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_DURATION_MILLIS);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_INTENT_URI);
            contentValues.remove("transient");
            contentValues.remove("type");
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_POSTER_ART_ASPECT_RATIO);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_THUMBNAIL_ASPECT_RATIO);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_URI);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_AVAILABILITY);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_STARTING_PRICE);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_OFFER_PRICE);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_RELEASE_DATE);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_ITEM_COUNT);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_COUNT);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_AUTHOR);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_CONTENT_DESCRIPTION);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_GENRE);
            contentValues.remove("start_time_utc_millis");
            contentValues.remove("end_time_utc_millis");
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_AUDIO_URI);
            contentValues.remove(TvContractCompat.PreviewProgramColumns.COLUMN_TV_SERIES_ITEM_TYPE);
        }
        if (Build.VERSION.SDK_INT < 26 || !z) {
            contentValues.remove("browsable");
        }
        return contentValues;
    }

    static void setFieldsFromCursor(Cursor cursor, Builder builder) {
        BaseProgram.setFieldsFromCursor(cursor, builder);
        if (Build.VERSION.SDK_INT >= 26) {
            int columnIndex = cursor.getColumnIndex("internal_provider_id");
            if (columnIndex >= 0 && !cursor.isNull(columnIndex)) {
                builder.setInternalProviderId(cursor.getString(columnIndex));
            }
            int columnIndex2 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_VIDEO_URI);
            if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
                builder.setPreviewVideoUri(Uri.parse(cursor.getString(columnIndex2)));
            }
            int columnIndex3 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_LAST_PLAYBACK_POSITION_MILLIS);
            if (columnIndex3 >= 0 && !cursor.isNull(columnIndex3)) {
                builder.setLastPlaybackPositionMillis(cursor.getInt(columnIndex3));
            }
            int columnIndex4 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_DURATION_MILLIS);
            if (columnIndex4 >= 0 && !cursor.isNull(columnIndex4)) {
                builder.setDurationMillis(cursor.getInt(columnIndex4));
            }
            int columnIndex5 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_INTENT_URI);
            if (columnIndex5 >= 0 && !cursor.isNull(columnIndex5)) {
                builder.setIntentUri(Uri.parse(cursor.getString(columnIndex5)));
            }
            int columnIndex6 = cursor.getColumnIndex("transient");
            if (columnIndex6 >= 0 && !cursor.isNull(columnIndex6)) {
                builder.setTransient(cursor.getInt(columnIndex6) == 1);
            }
            int columnIndex7 = cursor.getColumnIndex("type");
            if (columnIndex7 >= 0 && !cursor.isNull(columnIndex7)) {
                builder.setType(cursor.getInt(columnIndex7));
            }
            int columnIndex8 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_POSTER_ART_ASPECT_RATIO);
            if (columnIndex8 >= 0 && !cursor.isNull(columnIndex8)) {
                builder.setPosterArtAspectRatio(cursor.getInt(columnIndex8));
            }
            int columnIndex9 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_THUMBNAIL_ASPECT_RATIO);
            if (columnIndex9 >= 0 && !cursor.isNull(columnIndex9)) {
                builder.setThumbnailAspectRatio(cursor.getInt(columnIndex9));
            }
            int columnIndex10 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_URI);
            if (columnIndex10 >= 0 && !cursor.isNull(columnIndex10)) {
                builder.setLogoUri(Uri.parse(cursor.getString(columnIndex10)));
            }
            int columnIndex11 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_AVAILABILITY);
            if (columnIndex11 >= 0 && !cursor.isNull(columnIndex11)) {
                builder.setAvailability(cursor.getInt(columnIndex11));
            }
            int columnIndex12 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_STARTING_PRICE);
            if (columnIndex12 >= 0 && !cursor.isNull(columnIndex12)) {
                builder.setStartingPrice(cursor.getString(columnIndex12));
            }
            int columnIndex13 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_OFFER_PRICE);
            if (columnIndex13 >= 0 && !cursor.isNull(columnIndex13)) {
                builder.setOfferPrice(cursor.getString(columnIndex13));
            }
            int columnIndex14 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_RELEASE_DATE);
            if (columnIndex14 >= 0 && !cursor.isNull(columnIndex14)) {
                builder.setReleaseDate(cursor.getString(columnIndex14));
            }
            int columnIndex15 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_ITEM_COUNT);
            if (columnIndex15 >= 0 && !cursor.isNull(columnIndex15)) {
                builder.setItemCount(cursor.getInt(columnIndex15));
            }
            int columnIndex16 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE);
            if (columnIndex16 >= 0 && !cursor.isNull(columnIndex16)) {
                builder.setLive(cursor.getInt(columnIndex16) == 1);
            }
            int columnIndex17 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_TYPE);
            if (columnIndex17 >= 0 && !cursor.isNull(columnIndex17)) {
                builder.setInteractionType(cursor.getInt(columnIndex17));
            }
            int columnIndex18 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_COUNT);
            if (columnIndex18 >= 0 && !cursor.isNull(columnIndex18)) {
                builder.setInteractionCount(cursor.getInt(columnIndex18));
            }
            int columnIndex19 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_AUTHOR);
            if (columnIndex19 >= 0 && !cursor.isNull(columnIndex19)) {
                builder.setAuthor(cursor.getString(columnIndex19));
            }
            int columnIndex20 = cursor.getColumnIndex("browsable");
            if (columnIndex20 >= 0 && !cursor.isNull(columnIndex20)) {
                builder.setBrowsable(cursor.getInt(columnIndex20) == 1);
            }
            int columnIndex21 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
            if (columnIndex21 >= 0 && !cursor.isNull(columnIndex21)) {
                builder.setContentId(cursor.getString(columnIndex21));
            }
            int columnIndex22 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_CONTENT_DESCRIPTION);
            if (columnIndex22 >= 0 && !cursor.isNull(columnIndex22)) {
                builder.setLogoContentDescription(cursor.getString(columnIndex22));
            }
            int columnIndex23 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_GENRE);
            if (columnIndex23 >= 0 && !cursor.isNull(columnIndex23)) {
                builder.setGenre(cursor.getString(columnIndex23));
            }
            int columnIndex24 = cursor.getColumnIndex("start_time_utc_millis");
            if (columnIndex24 >= 0 && !cursor.isNull(columnIndex24)) {
                builder.setStartTimeUtcMillis(cursor.getLong(columnIndex24));
            }
            int columnIndex25 = cursor.getColumnIndex("end_time_utc_millis");
            if (columnIndex25 >= 0 && !cursor.isNull(columnIndex25)) {
                builder.setEndTimeUtcMillis(cursor.getLong(columnIndex25));
            }
            int columnIndex26 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_AUDIO_URI);
            if (columnIndex26 >= 0 && !cursor.isNull(columnIndex26)) {
                builder.setPreviewAudioUri(Uri.parse(cursor.getString(columnIndex26)));
            }
            int columnIndex27 = cursor.getColumnIndex(TvContractCompat.PreviewProgramColumns.COLUMN_TV_SERIES_ITEM_TYPE);
            if (columnIndex27 < 0 || cursor.isNull(columnIndex27)) {
                return;
            }
            builder.setTvSeriesItemType(cursor.getInt(columnIndex27));
        }
    }

    private static String[] getProjection() {
        return (String[]) CollectionUtils.concatAll(BaseProgram.PROJECTION, new String[]{"internal_provider_id", TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_VIDEO_URI, TvContractCompat.PreviewProgramColumns.COLUMN_LAST_PLAYBACK_POSITION_MILLIS, TvContractCompat.PreviewProgramColumns.COLUMN_DURATION_MILLIS, TvContractCompat.PreviewProgramColumns.COLUMN_INTENT_URI, "transient", "type", TvContractCompat.PreviewProgramColumns.COLUMN_POSTER_ART_ASPECT_RATIO, TvContractCompat.PreviewProgramColumns.COLUMN_THUMBNAIL_ASPECT_RATIO, TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_URI, TvContractCompat.PreviewProgramColumns.COLUMN_AVAILABILITY, TvContractCompat.PreviewProgramColumns.COLUMN_STARTING_PRICE, TvContractCompat.PreviewProgramColumns.COLUMN_OFFER_PRICE, TvContractCompat.PreviewProgramColumns.COLUMN_RELEASE_DATE, TvContractCompat.PreviewProgramColumns.COLUMN_ITEM_COUNT, TvContractCompat.PreviewProgramColumns.COLUMN_LIVE, TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_TYPE, TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_COUNT, TvContractCompat.PreviewProgramColumns.COLUMN_AUTHOR, "browsable", TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID, TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_CONTENT_DESCRIPTION, TvContractCompat.PreviewProgramColumns.COLUMN_GENRE, "start_time_utc_millis", "end_time_utc_millis", TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_AUDIO_URI, TvContractCompat.PreviewProgramColumns.COLUMN_TV_SERIES_ITEM_TYPE});
    }

    public static abstract class Builder<T extends Builder> extends BaseProgram.Builder<T> {
        private static final SimpleDateFormat sFormat;

        static {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sFormat = simpleDateFormat;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        }

        public Builder() {
        }

        public Builder(BasePreviewProgram basePreviewProgram) {
            this.mValues = new ContentValues(basePreviewProgram.mValues);
        }

        public T setInternalProviderId(String str) {
            this.mValues.put("internal_provider_id", str);
            return this;
        }

        public T setPreviewVideoUri(Uri uri) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_VIDEO_URI, uri == null ? null : uri.toString());
            return this;
        }

        public T setLastPlaybackPositionMillis(int i) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_LAST_PLAYBACK_POSITION_MILLIS, Integer.valueOf(i));
            return this;
        }

        public T setDurationMillis(int i) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_DURATION_MILLIS, Integer.valueOf(i));
            return this;
        }

        public T setIntentUri(Uri uri) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_INTENT_URI, uri == null ? null : uri.toString());
            return this;
        }

        public T setIntent(Intent intent) {
            return (T) setIntentUri(Uri.parse(intent.toUri(1)));
        }

        public T setTransient(boolean z) {
            this.mValues.put("transient", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public T setType(int i) {
            this.mValues.put("type", Integer.valueOf(i));
            return this;
        }

        public T setPosterArtAspectRatio(int i) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_POSTER_ART_ASPECT_RATIO, Integer.valueOf(i));
            return this;
        }

        public T setThumbnailAspectRatio(int i) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_THUMBNAIL_ASPECT_RATIO, Integer.valueOf(i));
            return this;
        }

        public T setLogoUri(Uri uri) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_URI, uri == null ? null : uri.toString());
            return this;
        }

        public T setAvailability(int i) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_AVAILABILITY, Integer.valueOf(i));
            return this;
        }

        public T setStartingPrice(String str) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_STARTING_PRICE, str);
            return this;
        }

        public T setOfferPrice(String str) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_OFFER_PRICE, str);
            return this;
        }

        public T setReleaseDate(String str) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_RELEASE_DATE, str);
            return this;
        }

        public T setReleaseDate(Date date) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_RELEASE_DATE, sFormat.format(date));
            return this;
        }

        public T setItemCount(int i) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_ITEM_COUNT, Integer.valueOf(i));
            return this;
        }

        public T setLive(boolean z) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_LIVE, Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public T setInteractionType(int i) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_TYPE, Integer.valueOf(i));
            return this;
        }

        public T setInteractionCount(long j) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_COUNT, Long.valueOf(j));
            return this;
        }

        public T setAuthor(String str) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_AUTHOR, str);
            return this;
        }

        public T setBrowsable(boolean z) {
            this.mValues.put("browsable", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public T setContentId(String str) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID, str);
            return this;
        }

        public T setLogoContentDescription(String str) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_LOGO_CONTENT_DESCRIPTION, str);
            return this;
        }

        public T setGenre(String str) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_GENRE, str);
            return this;
        }

        public T setStartTimeUtcMillis(long j) {
            this.mValues.put("start_time_utc_millis", Long.valueOf(j));
            return this;
        }

        public T setEndTimeUtcMillis(long j) {
            this.mValues.put("end_time_utc_millis", Long.valueOf(j));
            return this;
        }

        public T setPreviewAudioUri(Uri uri) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_PREVIEW_AUDIO_URI, uri == null ? null : uri.toString());
            return this;
        }

        public T setTvSeriesItemType(int i) {
            this.mValues.put(TvContractCompat.PreviewProgramColumns.COLUMN_TV_SERIES_ITEM_TYPE, Integer.valueOf(i));
            return this;
        }
    }
}
