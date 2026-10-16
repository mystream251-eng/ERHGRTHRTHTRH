package com.google.android.media.tv.companionlibrary.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.media.tv.TvContentRating;
import android.media.tv.TvContract;
import android.os.Build;
import android.text.TextUtils;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.media.tv.companionlibrary.utils.TvContractUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RecordedProgram {
    private static final boolean DEBUG = true;
    private static final int INVALID_INT_VALUE = -1;
    private static final int IS_SEARCHABLE = 1;
    public static final String[] PROJECTION = {"_id", TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE, "broadcast_genre", "canonical_genre", "channel_id", TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, "end_time_utc_millis", TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER, TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE, "input_id", "internal_provider_data", TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION, TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI, TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_BYTES, TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_URI, TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DURATION_MILLIS, TvContractCompat.RecordedPrograms.COLUMN_RECORDING_EXPIRE_TIME_UTC_MILLIS, "searchable", TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER, TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE, TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION, "start_time_utc_millis", TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI, "title", "version_number", TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT, TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH};
    private static final String TAG = "RecordedProgram";
    private String mAudioLanguages;
    private String[] mBroadcastGenres;
    private String[] mCanonicalGenres;
    private long mChannelId;
    private TvContentRating[] mContentRatings;
    private long mEndTimeUtcMillis;
    private String mEpisodeDisplayNumber;
    private String mEpisodeTitle;
    private int mId;
    private String mInputId;
    private byte[] mInternalProviderData;
    private String mLongDescription;
    private String mPosterArtUri;
    private long mRecordingDataBytes;
    private String mRecordingDataUri;
    private long mRecordingDurationMillis;
    private long mRecordingExpireTimeUtcMillis;
    private int mSearchable;
    private String mSeasonDisplayNumber;
    private String mSeasonTitle;
    private String mShortDescription;
    private long mStartTimeUtcMillis;
    private String mThumbnailUri;
    private String mTitle;
    private int mVersionNumber;
    private int mVideoHeight;
    private int mVideoWidth;

    private RecordedProgram() {
        this.mChannelId = -1L;
        this.mId = -1;
        this.mSearchable = 1;
        this.mStartTimeUtcMillis = -1L;
        this.mEndTimeUtcMillis = -1L;
        this.mRecordingDataBytes = -1L;
        this.mRecordingDurationMillis = -1L;
        this.mRecordingExpireTimeUtcMillis = -1L;
        this.mVideoHeight = -1;
        this.mVideoWidth = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyFrom(RecordedProgram recordedProgram) {
        if (this == recordedProgram) {
            return;
        }
        this.mAudioLanguages = recordedProgram.mAudioLanguages;
        this.mBroadcastGenres = recordedProgram.mBroadcastGenres;
        this.mCanonicalGenres = recordedProgram.mCanonicalGenres;
        this.mChannelId = recordedProgram.mChannelId;
        this.mContentRatings = recordedProgram.mContentRatings;
        this.mEpisodeDisplayNumber = recordedProgram.mEpisodeDisplayNumber;
        this.mEpisodeTitle = recordedProgram.mEpisodeTitle;
        this.mId = recordedProgram.mId;
        this.mInputId = recordedProgram.mInputId;
        this.mInternalProviderData = recordedProgram.mInternalProviderData;
        this.mLongDescription = recordedProgram.mLongDescription;
        this.mPosterArtUri = recordedProgram.mPosterArtUri;
        this.mRecordingDataBytes = recordedProgram.mRecordingDataBytes;
        this.mRecordingDataUri = recordedProgram.mRecordingDataUri;
        this.mRecordingDurationMillis = recordedProgram.mRecordingDurationMillis;
        this.mRecordingExpireTimeUtcMillis = recordedProgram.mRecordingExpireTimeUtcMillis;
        this.mSearchable = recordedProgram.mSearchable;
        this.mSeasonDisplayNumber = recordedProgram.mSeasonDisplayNumber;
        this.mSeasonTitle = recordedProgram.mSeasonTitle;
        this.mShortDescription = recordedProgram.mShortDescription;
        this.mStartTimeUtcMillis = recordedProgram.mStartTimeUtcMillis;
        this.mEndTimeUtcMillis = recordedProgram.mEndTimeUtcMillis;
        this.mThumbnailUri = recordedProgram.mThumbnailUri;
        this.mTitle = recordedProgram.mTitle;
        this.mVersionNumber = recordedProgram.mVersionNumber;
        this.mVideoHeight = recordedProgram.mVideoHeight;
        this.mVideoWidth = recordedProgram.mVideoWidth;
    }

    public String getAudioLanguages() {
        return this.mAudioLanguages;
    }

    public String[] getBroadcastGenres() {
        return this.mBroadcastGenres;
    }

    public String[] getCanonicalGenres() {
        return this.mCanonicalGenres;
    }

    public long getChannelId() {
        return this.mChannelId;
    }

    public TvContentRating[] getContentRatings() {
        return this.mContentRatings;
    }

    public String getEpisodeDisplayNumber() {
        return this.mEpisodeDisplayNumber;
    }

    public String getEpisodeTitle() {
        return this.mEpisodeTitle;
    }

    public int getId() {
        return this.mId;
    }

    public String getInputId() {
        return this.mInputId;
    }

    public InternalProviderData getInternalProviderData() {
        if (this.mInternalProviderData == null) {
            return null;
        }
        try {
            return new InternalProviderData(this.mInternalProviderData);
        } catch (InternalProviderData.ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String getLongDescription() {
        return this.mLongDescription;
    }

    public String getPosterArtUri() {
        return this.mPosterArtUri;
    }

    public boolean isSearchable() {
        return this.mSearchable == 1;
    }

    public long getRecordingDataBytes() {
        return this.mRecordingDataBytes;
    }

    public String getRecordingDataUri() {
        return this.mRecordingDataUri;
    }

    public long getRecordingDurationMillis() {
        return this.mRecordingDurationMillis;
    }

    public long getRecordingExpireTimeUtcMillis() {
        return this.mRecordingExpireTimeUtcMillis;
    }

    public String getSeasonDisplayNumber() {
        return this.mSeasonDisplayNumber;
    }

    public String getSeasonTitle() {
        return this.mSeasonTitle;
    }

    public String getShortDescription() {
        return this.mShortDescription;
    }

    public long getStartTimeUtcMillis() {
        return this.mStartTimeUtcMillis;
    }

    public long getEndTimeUtcMillis() {
        return this.mEndTimeUtcMillis;
    }

    public String getThumbnailUri() {
        return this.mThumbnailUri;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getVersionNumber() {
        return this.mVersionNumber;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof RecordedProgram)) {
            return false;
        }
        return Objects.equals(getAudioLanguages(), ((RecordedProgram) obj).getAudioLanguages());
    }

    public int hashCode() {
        return Objects.hash(getAudioLanguages());
    }

    public Program toProgram() {
        Program.Builder internalProviderData = new Program.Builder().setAudioLanguages(getAudioLanguages()).setBroadcastGenres(getBroadcastGenres()).setCanonicalGenres(getCanonicalGenres()).setChannelId(getChannelId()).setContentRatings(getContentRatings()).setDescription(getShortDescription()).setEpisodeTitle(getEpisodeTitle()).setLongDescription(getLongDescription()).setPosterArtUri(getPosterArtUri()).setVideoHeight(getVideoHeight()).setVideoWidth(getVideoWidth()).setSearchable(isSearchable()).setSeasonTitle(getSeasonTitle()).setStartTimeUtcMillis(getStartTimeUtcMillis()).setEndTimeUtcMillis(getStartTimeUtcMillis() + getRecordingDurationMillis()).setThumbnailUri(getThumbnailUri()).setTitle(getTitle()).setInternalProviderData(getInternalProviderData());
        if (getEpisodeDisplayNumber() != null) {
            internalProviderData.setEpisodeNumber(getEpisodeDisplayNumber(), Integer.parseInt(getEpisodeDisplayNumber()));
        }
        if (getSeasonDisplayNumber() != null) {
            internalProviderData.setSeasonNumber(getSeasonDisplayNumber(), Integer.parseInt(getSeasonDisplayNumber()));
        }
        return internalProviderData.build();
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        int i = this.mId;
        if (i != -1) {
            contentValues.put("_id", Integer.valueOf(i));
        } else {
            contentValues.putNull("_id");
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
            contentValues.putNull("broadcast_genre");
        }
        long j = this.mChannelId;
        if (j != -1) {
            contentValues.put("channel_id", Long.valueOf(j));
        } else {
            contentValues.putNull("channel_id");
        }
        long j2 = this.mEndTimeUtcMillis;
        if (j2 > -1) {
            contentValues.put("end_time_utc_millis", Long.valueOf(j2));
        } else {
            contentValues.putNull("end_time_utc_millis");
        }
        if (!TextUtils.isEmpty(this.mEpisodeDisplayNumber)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER, this.mEpisodeDisplayNumber);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER);
        }
        if (!TextUtils.isEmpty(this.mEpisodeTitle)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE, this.mEpisodeTitle);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE);
        }
        if (!TextUtils.isEmpty(this.mInputId)) {
            contentValues.put("input_id", this.mInputId);
        } else {
            contentValues.putNull("input_id");
        }
        byte[] bArr = this.mInternalProviderData;
        if (bArr != null && bArr.length > 0) {
            contentValues.put("internal_provider_data", bArr);
        } else {
            contentValues.putNull("internal_provider_data");
        }
        contentValues.putNull("internal_provider_flag1");
        contentValues.putNull("internal_provider_flag2");
        contentValues.putNull("internal_provider_flag3");
        contentValues.putNull("internal_provider_flag4");
        if (!TextUtils.isEmpty(this.mLongDescription)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION, this.mLongDescription);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION);
        }
        if (!TextUtils.isEmpty(this.mPosterArtUri)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI, this.mPosterArtUri);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI);
        }
        long j3 = this.mRecordingDataBytes;
        if (j3 > -1) {
            contentValues.put(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_BYTES, Long.valueOf(j3));
        } else {
            contentValues.putNull(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_BYTES);
        }
        if (!TextUtils.isEmpty(this.mRecordingDataUri)) {
            contentValues.put(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_URI, this.mRecordingDataUri);
        } else {
            contentValues.putNull(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_URI);
        }
        long j4 = this.mRecordingDurationMillis;
        if (j4 > -1) {
            contentValues.put(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DURATION_MILLIS, Long.valueOf(j4));
        } else {
            contentValues.putNull(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DURATION_MILLIS);
        }
        long j5 = this.mRecordingExpireTimeUtcMillis;
        if (j5 > -1) {
            contentValues.put(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_EXPIRE_TIME_UTC_MILLIS, Long.valueOf(j5));
        } else {
            contentValues.putNull(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_EXPIRE_TIME_UTC_MILLIS);
        }
        contentValues.put("searchable", Integer.valueOf(this.mSearchable));
        if (!TextUtils.isEmpty(this.mSeasonDisplayNumber)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER, this.mSeasonDisplayNumber);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER);
        }
        if (!TextUtils.isEmpty(this.mSeasonTitle)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE, this.mSeasonTitle);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE);
        }
        if (!TextUtils.isEmpty(this.mShortDescription)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION, this.mShortDescription);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION);
        }
        long j6 = this.mStartTimeUtcMillis;
        if (j6 > -1) {
            contentValues.put("start_time_utc_millis", Long.valueOf(j6));
        } else {
            contentValues.putNull("start_time_utc_millis");
        }
        if (!TextUtils.isEmpty(this.mThumbnailUri)) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI, this.mThumbnailUri);
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI);
        }
        if (!TextUtils.isEmpty(this.mTitle)) {
            contentValues.put("title", this.mTitle);
        } else {
            contentValues.putNull("title");
        }
        contentValues.put("version_number", Integer.valueOf(this.mVersionNumber));
        int i2 = this.mVideoHeight;
        if (i2 != -1) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT, Integer.valueOf(i2));
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT);
        }
        int i3 = this.mVideoWidth;
        if (i3 != -1) {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH, Integer.valueOf(i3));
        } else {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH);
        }
        return contentValues;
    }

    public static RecordedProgram fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        if (!cursor.isNull(0)) {
            builder.setId(cursor.getInt(0));
        }
        if (!cursor.isNull(1)) {
            builder.setAudioLanguages(cursor.getString(1));
        }
        if (!cursor.isNull(2)) {
            builder.setBroadcastGenres(TvContract.Programs.Genres.decode(cursor.getString(2)));
        }
        if (!cursor.isNull(3)) {
            builder.setCanonicalGenres(TvContract.Programs.Genres.decode(cursor.getString(3)));
        }
        if (!cursor.isNull(4)) {
            builder.setChannelId(cursor.getInt(4));
        }
        if (!cursor.isNull(5)) {
            builder.setContentRatings(TvContractUtils.stringToContentRatings(cursor.getString(5)));
        }
        if (!cursor.isNull(6)) {
            builder.setEndTimeUtcMillis(cursor.getLong(6));
        }
        if (!cursor.isNull(7)) {
            builder.setEpisodeDisplayNumber(cursor.getString(7), Integer.parseInt(cursor.getString(7)));
        }
        if (!cursor.isNull(8)) {
            builder.setEpisodeTitle(cursor.getString(8));
        }
        if (!cursor.isNull(9)) {
            builder.setInputId(cursor.getString(9));
        }
        if (!cursor.isNull(10)) {
            builder.setInternalProviderData(cursor.getBlob(10));
        }
        if (!cursor.isNull(11)) {
            builder.setLongDescription(cursor.getString(11));
        }
        if (!cursor.isNull(12)) {
            builder.setPosterArtUri(cursor.getString(12));
        }
        if (!cursor.isNull(13)) {
            builder.setRecordingDataBytes(cursor.getLong(13));
        }
        if (!cursor.isNull(14)) {
            builder.setRecordingDataUri(cursor.getString(14));
        }
        if (!cursor.isNull(15)) {
            builder.setRecordingDurationMillis(cursor.getLong(15));
        }
        if (!cursor.isNull(16)) {
            builder.setRecordingExpireTimeUtcMillis(cursor.getLong(16));
        }
        if (!cursor.isNull(17)) {
            builder.setSearchable(cursor.getInt(17) == 1);
        }
        if (!cursor.isNull(18)) {
            builder.setSeasonDisplayNumber(cursor.getString(18), Integer.parseInt(cursor.getString(18)));
        }
        if (!cursor.isNull(19)) {
            builder.setSeasonTitle(cursor.getString(19));
        }
        if (!cursor.isNull(20)) {
            builder.setShortDescription(cursor.getString(20));
        }
        if (!cursor.isNull(21)) {
            builder.setStartTimeUtcMillis(cursor.getLong(21));
        }
        if (!cursor.isNull(22)) {
            builder.setThumbnailUri(cursor.getString(22));
        }
        if (!cursor.isNull(23)) {
            builder.setTitle(cursor.getString(23));
        }
        if (!cursor.isNull(24)) {
            builder.setVersionNumber(cursor.getInt(24));
        }
        if (!cursor.isNull(25)) {
            builder.setVideoHeight(cursor.getInt(25));
        }
        if (!cursor.isNull(26)) {
            builder.setVideoWidth(cursor.getInt(26));
        }
        return builder.build();
    }

    public static class Builder {
        private RecordedProgram mRecordedProgram;

        public Builder() {
            this.mRecordedProgram = new RecordedProgram();
        }

        public Builder(RecordedProgram recordedProgram) {
            RecordedProgram recordedProgram2 = new RecordedProgram();
            this.mRecordedProgram = recordedProgram2;
            recordedProgram2.copyFrom(recordedProgram);
        }

        public Builder(Program program) {
            this.mRecordedProgram = new RecordedProgram();
            setAudioLanguages(program.getAudioLanguages());
            setBroadcastGenres(program.getBroadcastGenres());
            setCanonicalGenres(program.getCanonicalGenres());
            setChannelId(program.getChannelId());
            setContentRatings(program.getContentRatings());
            if (program.getEpisodeNumber() != null) {
                setEpisodeDisplayNumber(program.getEpisodeNumber(), Integer.parseInt(program.getEpisodeNumber()));
            }
            setEpisodeTitle(program.getEpisodeTitle());
            setInternalProviderData(program.getInternalProviderData());
            setLongDescription(program.getLongDescription());
            setPosterArtUri(program.getPosterArtUri());
            setSearchable(program.isSearchable());
            if (program.getSeasonNumber() != null) {
                setSeasonDisplayNumber(program.getSeasonNumber(), Integer.parseInt(program.getSeasonNumber()));
            }
            setSeasonTitle(program.getSeasonTitle());
            setShortDescription(program.getDescription());
            setStartTimeUtcMillis(program.getStartTimeUtcMillis());
            setEndTimeUtcMillis(program.getEndTimeUtcMillis());
            setThumbnailUri(program.getThumbnailUri());
            setTitle(program.getTitle());
            setVideoHeight(program.getVideoHeight());
            setVideoWidth(program.getVideoWidth());
        }

        public Builder setAudioLanguages(String str) {
            this.mRecordedProgram.mAudioLanguages = str;
            return this;
        }

        public Builder setBroadcastGenres(String[] strArr) {
            this.mRecordedProgram.mBroadcastGenres = strArr;
            return this;
        }

        public Builder setCanonicalGenres(String[] strArr) {
            this.mRecordedProgram.mCanonicalGenres = strArr;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder setChannelId(long j) {
            this.mRecordedProgram.mChannelId = j;
            return this;
        }

        public Builder setContentRatings(TvContentRating[] tvContentRatingArr) {
            this.mRecordedProgram.mContentRatings = tvContentRatingArr;
            return this;
        }

        public Builder setEndTimeUtcMillis(long j) {
            this.mRecordedProgram.mEndTimeUtcMillis = j;
            return this;
        }

        public Builder setEpisodeDisplayNumber(String str, int i) {
            if (Build.VERSION.SDK_INT >= 24) {
                this.mRecordedProgram.mEpisodeDisplayNumber = str;
            } else {
                this.mRecordedProgram.mEpisodeDisplayNumber = String.valueOf(i);
            }
            return this;
        }

        public Builder setEpisodeTitle(String str) {
            this.mRecordedProgram.mEpisodeTitle = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder setId(int i) {
            this.mRecordedProgram.mId = i;
            return this;
        }

        public Builder setInputId(String str) {
            this.mRecordedProgram.mInputId = str;
            return this;
        }

        public Builder setInternalProviderData(byte[] bArr) {
            this.mRecordedProgram.mInternalProviderData = bArr;
            return this;
        }

        public Builder setInternalProviderData(InternalProviderData internalProviderData) {
            if (internalProviderData != null) {
                this.mRecordedProgram.mInternalProviderData = internalProviderData.toString().getBytes();
            }
            return this;
        }

        public Builder setLongDescription(String str) {
            this.mRecordedProgram.mLongDescription = str;
            return this;
        }

        public Builder setPosterArtUri(String str) {
            this.mRecordedProgram.mPosterArtUri = str;
            return this;
        }

        public Builder setRecordingDataBytes(long j) {
            this.mRecordedProgram.mRecordingDataBytes = j;
            return this;
        }

        public Builder setRecordingDataUri(String str) {
            this.mRecordedProgram.mRecordingDataUri = str;
            return this;
        }

        public Builder setRecordingDurationMillis(long j) {
            this.mRecordedProgram.mRecordingDurationMillis = j;
            return this;
        }

        public Builder setRecordingExpireTimeUtcMillis(long j) {
            this.mRecordedProgram.mRecordingExpireTimeUtcMillis = j;
            return this;
        }

        public Builder setSearchable(boolean z) {
            this.mRecordedProgram.mSearchable = z ? 1 : 0;
            return this;
        }

        public Builder setSeasonDisplayNumber(String str, int i) {
            if (Build.VERSION.SDK_INT >= 24) {
                this.mRecordedProgram.mSeasonDisplayNumber = str;
            } else {
                this.mRecordedProgram.mSeasonDisplayNumber = String.valueOf(i);
            }
            return this;
        }

        public Builder setSeasonTitle(String str) {
            this.mRecordedProgram.mSeasonTitle = str;
            return this;
        }

        public Builder setShortDescription(String str) {
            this.mRecordedProgram.mShortDescription = str;
            return this;
        }

        public Builder setStartTimeUtcMillis(long j) {
            this.mRecordedProgram.mStartTimeUtcMillis = j;
            return this;
        }

        public Builder setThumbnailUri(String str) {
            this.mRecordedProgram.mThumbnailUri = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.mRecordedProgram.mTitle = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder setVersionNumber(int i) {
            this.mRecordedProgram.mVersionNumber = i;
            return this;
        }

        public Builder setVideoHeight(int i) {
            this.mRecordedProgram.mVideoHeight = i;
            return this;
        }

        public Builder setVideoWidth(int i) {
            this.mRecordedProgram.mVideoWidth = i;
            return this;
        }

        public RecordedProgram build() {
            RecordedProgram recordedProgram = new RecordedProgram();
            recordedProgram.copyFrom(this.mRecordedProgram);
            if (recordedProgram.getInputId() == null) {
                throw new IllegalArgumentException("This recorded program does not have an Input Id");
            }
            if (recordedProgram.getRecordingDurationMillis() == -1 && recordedProgram.getEndTimeUtcMillis() > 0) {
                recordedProgram.mRecordingDurationMillis = recordedProgram.getEndTimeUtcMillis() - recordedProgram.getStartTimeUtcMillis();
            }
            return recordedProgram;
        }
    }
}
