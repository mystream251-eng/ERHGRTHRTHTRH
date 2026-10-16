package com.dotscreen.tv.common.recording;

import android.content.ContentUris;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.net.Uri;
import androidx.tvprovider.media.tv.TvContractCompat;
import java.util.Comparator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Recording {
    public static final int ID_NOT_SET = -1;
    public static final String[] PROJECTION = {"_id", "channel_id", "title", "start_time_utc_millis", "end_time_utc_millis", TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION, TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION, TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI, TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI};
    public static final Comparator<Recording> START_TIME_THEN_ID_COMPARATOR = new Comparator<Recording>() { // from class: com.dotscreen.tv.common.recording.Recording.1
        @Override // java.util.Comparator
        public int compare(Recording recording, Recording recording2) {
            int iCompare = Long.compare(recording.getStartTimeUtcMillis(), recording2.getStartTimeUtcMillis());
            return iCompare != 0 ? iCompare : Long.compare(recording.mId, recording2.mId);
        }
    };
    private final long mChannelID;
    private final long mEndTimeUtcMillis;
    private final long mId;
    private final String mLongDescription;
    private final Uri mPosterArt;
    private final String mShortDescription;
    private final long mStartTimeUtcMillis;
    private final Uri mThumbnail;
    private final String mTitle;

    public static final Recording fromCursor(Cursor cursor) {
        return builder().setId(cursor.getLong(0)).setChannelID(cursor.getLong(1)).setTitle(cursor.getString(2)).setStartTimeUtcMillis(cursor.getLong(3)).setEndTimeUtcMillis(cursor.getLong(4)).setShortDescription(cursor.getString(5)).setLongDescription(cursor.getString(6)).setPosterArt(cursor.getString(7)).setThumbnail(cursor.getString(8)).build();
    }

    public static class Builder {
        private long mChannelID;
        private long mEndTimeUtcMillis;
        private long mId = -1;
        private String mLongDescription;
        private Uri mPosterArt;
        private String mShortDescription;
        private long mStartTimeUtcMillis;
        private Uri mThumbnail;
        private String mTitle;

        public Builder setId(long j) {
            this.mId = j;
            return this;
        }

        public Builder setChannelID(long j) {
            this.mChannelID = j;
            return this;
        }

        public Builder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public Builder setStartTimeUtcMillis(long j) {
            this.mStartTimeUtcMillis = j;
            return this;
        }

        public Builder setEndTimeUtcMillis(long j) {
            this.mEndTimeUtcMillis = j;
            return this;
        }

        public Builder setShortDescription(String str) {
            this.mShortDescription = str;
            return this;
        }

        public Builder setLongDescription(String str) {
            this.mLongDescription = str;
            return this;
        }

        private Uri toUri(String str) {
            if (str == null) {
                return null;
            }
            try {
                return Uri.parse(str);
            } catch (Exception unused) {
                return null;
            }
        }

        public Builder setPosterArt(String str) {
            return setPosterArt(toUri(str));
        }

        public Builder setPosterArt(Uri uri) {
            this.mPosterArt = uri;
            return this;
        }

        public Builder setThumbnail(String str) {
            return setThumbnail(toUri(str));
        }

        public Builder setThumbnail(Uri uri) {
            this.mThumbnail = uri;
            return this;
        }

        public Recording build() {
            return new Recording(this.mId, this.mChannelID, this.mTitle, this.mStartTimeUtcMillis, this.mEndTimeUtcMillis, this.mShortDescription, this.mLongDescription, this.mPosterArt, this.mThumbnail);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder buildFrom(Recording recording) {
        return builder().setId(recording.getId()).setChannelID(recording.getChannelID()).setTitle(recording.getTitle()).setStartTimeUtcMillis(recording.getStartTimeUtcMillis()).setEndTimeUtcMillis(recording.getEndTimeUtcMillis()).setShortDescription(recording.getShortDescription()).setLongDescription(recording.getLongDescription()).setPosterArt(recording.getPosterArt()).setThumbnail(recording.getThumbnail());
    }

    private Recording(long j, long j2, String str, long j3, long j4, String str2, String str3, Uri uri, Uri uri2) {
        this.mId = j;
        this.mChannelID = j2;
        this.mTitle = str;
        this.mStartTimeUtcMillis = j3;
        this.mEndTimeUtcMillis = j4;
        this.mShortDescription = str2;
        this.mLongDescription = str3;
        this.mPosterArt = uri;
        this.mThumbnail = uri2;
    }

    public long getChannelID() {
        return this.mChannelID;
    }

    public long getEndTimeUtcMillis() {
        return this.mEndTimeUtcMillis;
    }

    public long getId() {
        return this.mId;
    }

    public String getLongDescription() {
        return this.mLongDescription;
    }

    public Uri getPosterArt() {
        return this.mPosterArt;
    }

    public String getShortDescription() {
        return this.mShortDescription;
    }

    public long getStartTimeUtcMillis() {
        return this.mStartTimeUtcMillis;
    }

    public Uri getThumbnail() {
        return this.mThumbnail;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Uri getUri() {
        return ContentUris.withAppendedId(TvContract.RecordedPrograms.CONTENT_URI, this.mId);
    }

    public boolean isLive() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mStartTimeUtcMillis <= jCurrentTimeMillis) {
            long j = this.mEndTimeUtcMillis;
            if (j >= jCurrentTimeMillis || j == -1) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Recording recording = (Recording) obj;
        return Objects.equals(Long.valueOf(this.mId), Long.valueOf(recording.mId)) && Objects.equals(Long.valueOf(this.mChannelID), Long.valueOf(recording.mChannelID)) && Objects.equals(Long.valueOf(this.mStartTimeUtcMillis), Long.valueOf(recording.mStartTimeUtcMillis)) && Objects.equals(Long.valueOf(this.mEndTimeUtcMillis), Long.valueOf(recording.mEndTimeUtcMillis)) && Objects.equals(this.mTitle, recording.mTitle) && Objects.equals(this.mShortDescription, recording.mShortDescription) && Objects.equals(this.mLongDescription, recording.mLongDescription) && Objects.equals(this.mPosterArt, recording.mPosterArt) && Objects.equals(this.mThumbnail, recording.mThumbnail);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mId));
    }

    public String toString() {
        return "RecordedProgram[" + this.mId + "]{ mChannelID='" + this.mChannelID + "', mTitle='" + this.mTitle + "', mStartTimeUtcMillis=" + this.mStartTimeUtcMillis + ", mEndTimeUtcMillis=" + this.mEndTimeUtcMillis + ", mShortDescription='" + this.mShortDescription + "', mLongDescription='" + this.mLongDescription + "', mPosterArt=" + this.mPosterArt + ", mThumbnail=" + this.mThumbnail + '}';
    }

    private static String safeToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }
}
