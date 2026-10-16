package com.google.android.media.tv.companionlibrary.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.media.tv.TvContentRating;
import android.media.tv.TvContract;
import android.os.Build;
import android.text.TextUtils;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.media.tv.companionlibrary.utils.CollectionUtils;
import com.google.android.media.tv.companionlibrary.utils.TvContractUtils;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class Program implements Comparable<Program> {
    private static final int INVALID_INT_VALUE = -1;
    private static final long INVALID_LONG_VALUE = -1;
    private static final int IS_RECORDING_PROHIBITED = 1;
    private static final int IS_SEARCHABLE = 1;
    public static final String[] PROJECTION = getProjection();
    private String mAudioLanguages;
    private String[] mBroadcastGenres;
    private String[] mCanonicalGenres;
    private long mChannelId;
    private TvContentRating[] mContentRatings;
    private String mDescription;
    private long mEndTimeUtcMillis;
    private String mEpisodeNumber;
    private String mEpisodeTitle;
    private long mId;
    private byte[] mInternalProviderData;
    private String mLongDescription;
    private String mPosterArtUri;
    private int mRecordingProhibited;
    private int mSearchable;
    private String mSeasonNumber;
    private String mSeasonTitle;
    private long mStartTimeUtcMillis;
    private String mThumbnailUri;
    private String mTitle;
    private int mVideoHeight;
    private int mVideoWidth;

    private Program() {
        this.mChannelId = -1L;
        this.mId = -1L;
        this.mStartTimeUtcMillis = -1L;
        this.mEndTimeUtcMillis = -1L;
        this.mVideoWidth = -1;
        this.mVideoHeight = -1;
        this.mSearchable = 1;
    }

    public long getId() {
        return this.mId;
    }

    public long getChannelId() {
        return this.mChannelId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getEpisodeTitle() {
        return this.mEpisodeTitle;
    }

    public String getSeasonNumber() {
        return this.mSeasonNumber;
    }

    public String getEpisodeNumber() {
        return this.mEpisodeNumber;
    }

    public long getStartTimeUtcMillis() {
        return this.mStartTimeUtcMillis;
    }

    public long getEndTimeUtcMillis() {
        return this.mEndTimeUtcMillis;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getLongDescription() {
        return this.mLongDescription;
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public String[] getBroadcastGenres() {
        return this.mBroadcastGenres;
    }

    public String[] getCanonicalGenres() {
        return this.mCanonicalGenres;
    }

    public TvContentRating[] getContentRatings() {
        return this.mContentRatings;
    }

    public String getPosterArtUri() {
        return this.mPosterArtUri;
    }

    public String getThumbnailUri() {
        return this.mThumbnailUri;
    }

    public byte[] getInternalProviderDataByteArray() {
        return this.mInternalProviderData;
    }

    public InternalProviderData getInternalProviderData() {
        if (this.mInternalProviderData != null) {
            try {
                return new InternalProviderData(this.mInternalProviderData);
            } catch (InternalProviderData.ParseException unused) {
            }
        }
        return null;
    }

    public String getAudioLanguages() {
        return this.mAudioLanguages;
    }

    public boolean isRecordingProhibited() {
        return this.mRecordingProhibited == 1;
    }

    public boolean isSearchable() {
        return this.mSearchable == 1;
    }

    public String getSeasonTitle() {
        return this.mSeasonTitle;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mChannelId), Long.valueOf(this.mStartTimeUtcMillis), Long.valueOf(this.mEndTimeUtcMillis), this.mTitle, this.mEpisodeTitle, this.mDescription, this.mLongDescription, Integer.valueOf(this.mVideoWidth), Integer.valueOf(this.mVideoHeight), this.mPosterArtUri, this.mThumbnailUri, Integer.valueOf(Arrays.hashCode(this.mContentRatings)), Integer.valueOf(Arrays.hashCode(this.mCanonicalGenres)), this.mSeasonNumber, this.mEpisodeNumber);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Program)) {
            return false;
        }
        Program program = (Program) obj;
        return this.mChannelId == program.mChannelId && this.mStartTimeUtcMillis == program.mStartTimeUtcMillis && this.mEndTimeUtcMillis == program.mEndTimeUtcMillis && Objects.equals(this.mTitle, program.mTitle) && Objects.equals(this.mEpisodeTitle, program.mEpisodeTitle) && Objects.equals(this.mDescription, program.mDescription) && Objects.equals(this.mLongDescription, program.mLongDescription) && this.mVideoWidth == program.mVideoWidth && this.mVideoHeight == program.mVideoHeight && Objects.equals(this.mPosterArtUri, program.mPosterArtUri) && Objects.equals(this.mThumbnailUri, program.mThumbnailUri) && Arrays.equals(this.mInternalProviderData, program.mInternalProviderData) && Arrays.equals(this.mContentRatings, program.mContentRatings) && Arrays.equals(this.mCanonicalGenres, program.mCanonicalGenres) && Objects.equals(this.mSeasonNumber, program.mSeasonNumber) && Objects.equals(this.mEpisodeNumber, program.mEpisodeNumber);
    }

    @Override // java.lang.Comparable
    public int compareTo(Program program) {
        return Long.compare(this.mStartTimeUtcMillis, program.mStartTimeUtcMillis);
    }

    public String toString() {
        return "Program{id=" + this.mId + ", channelId=" + this.mChannelId + ", title=" + this.mTitle + ", episodeTitle=" + this.mEpisodeTitle + ", seasonNumber=" + this.mSeasonNumber + ", episodeNumber=" + this.mEpisodeNumber + ", startTimeUtcSec=" + this.mStartTimeUtcMillis + ", endTimeUtcSec=" + this.mEndTimeUtcMillis + ", videoWidth=" + this.mVideoWidth + ", videoHeight=" + this.mVideoHeight + ", contentRatings=" + Arrays.toString(this.mContentRatings) + ", posterArtUri=" + this.mPosterArtUri + ", thumbnailUri=" + this.mThumbnailUri + ", contentRatings=" + Arrays.toString(this.mContentRatings) + ", genres=" + Arrays.toString(this.mCanonicalGenres) + "}";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyFrom(Program program) {
        if (this == program) {
            return;
        }
        this.mId = program.mId;
        this.mChannelId = program.mChannelId;
        this.mTitle = program.mTitle;
        this.mEpisodeTitle = program.mEpisodeTitle;
        this.mSeasonNumber = program.mSeasonNumber;
        this.mEpisodeNumber = program.mEpisodeNumber;
        this.mStartTimeUtcMillis = program.mStartTimeUtcMillis;
        this.mEndTimeUtcMillis = program.mEndTimeUtcMillis;
        this.mDescription = program.mDescription;
        this.mLongDescription = program.mLongDescription;
        this.mVideoWidth = program.mVideoWidth;
        this.mVideoHeight = program.mVideoHeight;
        this.mPosterArtUri = program.mPosterArtUri;
        this.mThumbnailUri = program.mThumbnailUri;
        this.mBroadcastGenres = program.mBroadcastGenres;
        this.mCanonicalGenres = program.mCanonicalGenres;
        this.mContentRatings = program.mContentRatings;
        this.mAudioLanguages = program.mAudioLanguages;
        this.mRecordingProhibited = program.mRecordingProhibited;
        this.mSearchable = program.mSearchable;
        this.mSeasonTitle = program.mSeasonTitle;
        this.mInternalProviderData = program.mInternalProviderData;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        long j = this.mId;
        if (j != -1) {
            contentValues.put("_id", Long.valueOf(j));
        }
        long j2 = this.mChannelId;
        if (j2 != -1) {
            contentValues.put("channel_id", Long.valueOf(j2));
        } else {
            contentValues.putNull("channel_id");
        }
        if (!TextUtils.isEmpty(this.mTitle)) {
            contentValues.put("title", this.mTitle);
        } else {
            contentValues.putNull("title");
        }
        if (!TextUtils.isEmpty(this.mEpisodeTitle)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE, this.mEpisodeTitle);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE);
        }
        if (!TextUtils.isEmpty(this.mSeasonNumber) && Build.VERSION.SDK_INT >= 24) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER, this.mSeasonNumber);
        } else if (!TextUtils.isEmpty(this.mSeasonNumber) && Build.VERSION.SDK_INT < 24) {
            contentValues.put(TvContractCompat.Programs.COLUMN_SEASON_NUMBER, Integer.valueOf(Integer.parseInt(this.mSeasonNumber)));
        } else {
            contentValues.putNull(TvContractCompat.Programs.COLUMN_SEASON_NUMBER);
        }
        if (!TextUtils.isEmpty(this.mEpisodeNumber) && Build.VERSION.SDK_INT >= 24) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER, this.mEpisodeNumber);
        } else if (!TextUtils.isEmpty(this.mEpisodeNumber) && Build.VERSION.SDK_INT < 24) {
            contentValues.put(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER, Integer.valueOf(Integer.parseInt(this.mEpisodeNumber)));
        } else {
            contentValues.putNull(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER);
        }
        if (!TextUtils.isEmpty(this.mDescription)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION, this.mDescription);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION);
        }
        if (!TextUtils.isEmpty(this.mDescription)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION, this.mLongDescription);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION);
        }
        if (!TextUtils.isEmpty(this.mPosterArtUri)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI, this.mPosterArtUri);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI);
        }
        if (!TextUtils.isEmpty(this.mThumbnailUri)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI, this.mThumbnailUri);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI);
        }
        if (!TextUtils.isEmpty(this.mAudioLanguages)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE, this.mAudioLanguages);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE);
        }
        String[] strArr = this.mBroadcastGenres;
        if (strArr != null && strArr.length > 0) {
            contentValues.put("broadcast_genre", TvContract.Programs.Genres.encode(strArr));
        } else {
            contentValues.putNull("broadcast_genre");
        }
        String[] strArr2 = this.mCanonicalGenres;
        if (strArr2 != null && strArr2.length > 0) {
            contentValues.put("canonical_genre", TvContract.Programs.Genres.encode(strArr2));
        } else {
            contentValues.putNull("canonical_genre");
        }
        TvContentRating[] tvContentRatingArr = this.mContentRatings;
        if (tvContentRatingArr != null && tvContentRatingArr.length > 0) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, TvContractUtils.contentRatingsToString(tvContentRatingArr));
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING);
        }
        long j3 = this.mStartTimeUtcMillis;
        if (j3 != -1) {
            contentValues.put("start_time_utc_millis", Long.valueOf(j3));
        } else {
            contentValues.putNull("start_time_utc_millis");
        }
        long j4 = this.mEndTimeUtcMillis;
        if (j4 != -1) {
            contentValues.put("end_time_utc_millis", Long.valueOf(j4));
        } else {
            contentValues.putNull("end_time_utc_millis");
        }
        int i = this.mVideoWidth;
        if (i != -1) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH, Integer.valueOf(i));
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH);
        }
        int i2 = this.mVideoHeight;
        if (i2 != -1) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT, Integer.valueOf(i2));
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT);
        }
        byte[] bArr = this.mInternalProviderData;
        if (bArr != null && bArr.length > 0) {
            contentValues.put("internal_provider_data", bArr);
        } else {
            contentValues.putNull("internal_provider_data");
        }
        if (Build.VERSION.SDK_INT >= 23) {
            contentValues.put("searchable", Integer.valueOf(this.mSearchable));
        }
        if (!TextUtils.isEmpty(this.mSeasonTitle) && Build.VERSION.SDK_INT >= 24) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE, this.mSeasonTitle);
        } else if (Build.VERSION.SDK_INT >= 24) {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            contentValues.put(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED, Integer.valueOf(this.mRecordingProhibited));
        }
        return contentValues;
    }

    public static Program fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        if (!cursor.isNull(0)) {
            builder.setId(cursor.getLong(0));
        }
        if (!cursor.isNull(1)) {
            builder.setChannelId(cursor.getLong(1));
        }
        if (!cursor.isNull(2)) {
            builder.setTitle(cursor.getString(2));
        }
        if (!cursor.isNull(3)) {
            builder.setEpisodeTitle(cursor.getString(3));
        }
        if (Build.VERSION.SDK_INT >= 24) {
            if (!cursor.isNull(4)) {
                builder.setSeasonNumber(cursor.getString(4), -1);
            }
        } else if (!cursor.isNull(4)) {
            builder.setSeasonNumber(cursor.getInt(4));
        }
        if (Build.VERSION.SDK_INT >= 24) {
            if (!cursor.isNull(5)) {
                builder.setEpisodeNumber(cursor.getString(5), -1);
            }
        } else if (!cursor.isNull(5)) {
            builder.setEpisodeNumber(cursor.getInt(5));
        }
        if (!cursor.isNull(6)) {
            builder.setDescription(cursor.getString(6));
        }
        if (!cursor.isNull(7)) {
            builder.setLongDescription(cursor.getString(7));
        }
        if (!cursor.isNull(8)) {
            builder.setPosterArtUri(cursor.getString(8));
        }
        if (!cursor.isNull(9)) {
            builder.setThumbnailUri(cursor.getString(9));
        }
        if (!cursor.isNull(10)) {
            builder.setAudioLanguages(cursor.getString(10));
        }
        if (!cursor.isNull(11)) {
            builder.setBroadcastGenres(TvContract.Programs.Genres.decode(cursor.getString(11)));
        }
        if (!cursor.isNull(12)) {
            builder.setCanonicalGenres(TvContract.Programs.Genres.decode(cursor.getString(12)));
        }
        if (!cursor.isNull(13)) {
            builder.setContentRatings(TvContractUtils.stringToContentRatings(cursor.getString(13)));
        }
        if (!cursor.isNull(14)) {
            builder.setStartTimeUtcMillis(cursor.getLong(14));
        }
        if (!cursor.isNull(15)) {
            builder.setEndTimeUtcMillis(cursor.getLong(15));
        }
        if (!cursor.isNull(16)) {
            builder.setVideoWidth((int) cursor.getLong(16));
        }
        if (!cursor.isNull(17)) {
            builder.setVideoHeight((int) cursor.getLong(17));
        }
        int i = 18;
        if (!cursor.isNull(18)) {
            builder.setInternalProviderData(cursor.getBlob(18));
        }
        if (Build.VERSION.SDK_INT >= 23) {
            i = 19;
            if (!cursor.isNull(19)) {
                builder.setSearchable(cursor.getInt(19) == 1);
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            int i2 = i + 1;
            if (!cursor.isNull(i2)) {
                builder.setSeasonTitle(cursor.getString(i2));
            }
            int i3 = i2 + 1;
            if (!cursor.isNull(i3)) {
                builder.setRecordingProhibited(cursor.getInt(i3) == 1);
            }
        }
        return builder.build();
    }

    private static String[] getProjection() {
        String[] strArr = new String[19];
        strArr[0] = "_id";
        strArr[1] = "channel_id";
        strArr[2] = "title";
        strArr[3] = TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE;
        strArr[4] = Build.VERSION.SDK_INT >= 24 ? TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER : TvContractCompat.Programs.COLUMN_SEASON_NUMBER;
        strArr[5] = Build.VERSION.SDK_INT >= 24 ? TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER : TvContractCompat.Programs.COLUMN_EPISODE_NUMBER;
        strArr[6] = TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION;
        strArr[7] = TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION;
        strArr[8] = TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI;
        strArr[9] = TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI;
        strArr[10] = TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE;
        strArr[11] = "broadcast_genre";
        strArr[12] = "canonical_genre";
        strArr[13] = TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING;
        strArr[14] = "start_time_utc_millis";
        strArr[15] = "end_time_utc_millis";
        strArr[16] = TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH;
        strArr[17] = TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT;
        strArr[18] = "internal_provider_data";
        if (Build.VERSION.SDK_INT < 23) {
            return strArr;
        }
        String[] strArr2 = {"searchable"};
        if (Build.VERSION.SDK_INT >= 24) {
            return (String[]) CollectionUtils.concatAll(strArr, strArr2, new String[]{TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE, TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED});
        }
        return (String[]) CollectionUtils.concatAll(strArr, strArr2);
    }

    public static final class Builder {
        private final Program mProgram;

        public Builder() {
            this.mProgram = new Program();
        }

        public Builder(Program program) {
            Program program2 = new Program();
            this.mProgram = program2;
            program2.copyFrom(program);
        }

        public Builder(Channel channel) {
            Program program = new Program();
            this.mProgram = program;
            program.mChannelId = channel.getId();
            this.mProgram.mDescription = channel.getDescription();
            this.mProgram.mInternalProviderData = channel.getInternalProviderDataByteArray();
            this.mProgram.mThumbnailUri = channel.getChannelLogo();
            this.mProgram.mTitle = channel.getDisplayName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder setId(long j) {
            this.mProgram.mId = j;
            return this;
        }

        public Builder setChannelId(long j) {
            this.mProgram.mChannelId = j;
            return this;
        }

        public Builder setTitle(String str) {
            this.mProgram.mTitle = str;
            return this;
        }

        public Builder setEpisodeTitle(String str) {
            this.mProgram.mEpisodeTitle = str;
            return this;
        }

        public Builder setSeasonNumber(int i) {
            this.mProgram.mSeasonNumber = String.valueOf(i);
            return this;
        }

        public Builder setSeasonNumber(String str, int i) {
            if (Build.VERSION.SDK_INT >= 24) {
                this.mProgram.mSeasonNumber = str;
            } else {
                this.mProgram.mSeasonNumber = String.valueOf(i);
            }
            return this;
        }

        public Builder setEpisodeNumber(int i) {
            this.mProgram.mEpisodeNumber = String.valueOf(i);
            return this;
        }

        public Builder setEpisodeNumber(String str, int i) {
            if (Build.VERSION.SDK_INT >= 24) {
                this.mProgram.mEpisodeNumber = str;
            } else {
                this.mProgram.mEpisodeNumber = String.valueOf(i);
            }
            return this;
        }

        public Builder setStartTimeUtcMillis(long j) {
            this.mProgram.mStartTimeUtcMillis = j;
            return this;
        }

        public Builder setEndTimeUtcMillis(long j) {
            this.mProgram.mEndTimeUtcMillis = j;
            return this;
        }

        public Builder setDescription(String str) {
            this.mProgram.mDescription = str;
            return this;
        }

        public Builder setLongDescription(String str) {
            this.mProgram.mLongDescription = str;
            return this;
        }

        public Builder setVideoWidth(int i) {
            this.mProgram.mVideoWidth = i;
            return this;
        }

        public Builder setVideoHeight(int i) {
            this.mProgram.mVideoHeight = i;
            return this;
        }

        public Builder setContentRatings(TvContentRating[] tvContentRatingArr) {
            this.mProgram.mContentRatings = tvContentRatingArr;
            return this;
        }

        public Builder setPosterArtUri(String str) {
            this.mProgram.mPosterArtUri = str;
            return this;
        }

        public Builder setThumbnailUri(String str) {
            this.mProgram.mThumbnailUri = str;
            return this;
        }

        public Builder setBroadcastGenres(String[] strArr) {
            this.mProgram.mBroadcastGenres = strArr;
            return this;
        }

        public Builder setCanonicalGenres(String[] strArr) {
            this.mProgram.mCanonicalGenres = strArr;
            return this;
        }

        public Builder setInternalProviderData(byte[] bArr) {
            this.mProgram.mInternalProviderData = bArr;
            return this;
        }

        public Builder setInternalProviderData(InternalProviderData internalProviderData) {
            if (internalProviderData != null) {
                this.mProgram.mInternalProviderData = internalProviderData.toString().getBytes();
            }
            return this;
        }

        public Builder setAudioLanguages(String str) {
            this.mProgram.mAudioLanguages = str;
            return this;
        }

        public Builder setRecordingProhibited(boolean z) {
            this.mProgram.mRecordingProhibited = z ? 1 : 0;
            return this;
        }

        public Builder setSearchable(boolean z) {
            this.mProgram.mSearchable = z ? 1 : 0;
            return this;
        }

        public Builder setSeasonTitle(String str) {
            this.mProgram.mSeasonTitle = str;
            return this;
        }

        public Program build() {
            Program program = new Program();
            program.copyFrom(this.mProgram);
            if (this.mProgram.getStartTimeUtcMillis() < this.mProgram.getEndTimeUtcMillis()) {
                return program;
            }
            throw new IllegalArgumentException("This program must have defined start and end times");
        }
    }
}
