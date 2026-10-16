package com.android.tv.common.recording;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.net.Uri;
import android.text.TextUtils;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.android.tv.common.R;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class RecordedProgram {
    public static final int ID_NOT_SET = -1;
    public static final String[] PROJECTION = {"_id", "input_id", "channel_id", "title", TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER, TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE, TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER, TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE, "start_time_utc_millis", "end_time_utc_millis", "broadcast_genre", "canonical_genre", TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION, TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION, TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH, TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT, TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE, TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI, TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI, "searchable", TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_URI, TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_BYTES, TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DURATION_MILLIS, TvContractCompat.RecordedPrograms.COLUMN_RECORDING_EXPIRE_TIME_UTC_MILLIS, "internal_provider_data", "internal_provider_flag1", "internal_provider_flag2", "internal_provider_flag3", "internal_provider_flag4", "version_number"};
    public static final Comparator<RecordedProgram> START_TIME_THEN_ID_COMPARATOR = new Comparator<RecordedProgram>() { // from class: com.android.tv.common.recording.RecordedProgram.1
        @Override // java.util.Comparator
        public int compare(RecordedProgram recordedProgram, RecordedProgram recordedProgram2) {
            int iCompare = Long.compare(recordedProgram.getStartTimeUtcMillis(), recordedProgram2.getStartTimeUtcMillis());
            return iCompare != 0 ? iCompare : Long.compare(recordedProgram.mId, recordedProgram2.mId);
        }
    };
    private final String mAudioLanguage;
    private final String[] mBroadcastGenres;
    private final String[] mCanonicalGenres;
    private final long mChannelId;
    private final String mContentRating;
    private final long mDataBytes;
    private final Uri mDataUri;
    private final long mDurationMillis;
    private final long mEndTimeUtcMillis;
    private final String mEpisodeNumber;
    private final String mEpisodeTitle;
    private final long mExpireTimeUtcMillis;
    private final long mId;
    private final String mInputId;
    private final byte[] mInternalProviderData;
    private final int mInternalProviderFlag1;
    private final int mInternalProviderFlag2;
    private final int mInternalProviderFlag3;
    private final int mInternalProviderFlag4;
    private final String mLongDescription;
    private final Uri mPosterArt;
    private final boolean mSearchable;
    private final String mSeasonNumber;
    private final String mSeasonTitle;
    private final String mShortDescription;
    private final long mStartTimeUtcMillis;
    private final Uri mThumbnail;
    private final String mTitle;
    private final int mVersionNumber;
    private final int mVideoHeight;
    private final int mVideoWidth;

    public static final RecordedProgram fromCursor(Cursor cursor) {
        return builder().setId(cursor.getLong(0)).setInputId(cursor.getString(1)).setChannelId(cursor.getLong(2)).setTitle(cursor.getString(3)).setSeasonNumber(cursor.getString(4)).setSeasonTitle(cursor.getString(5)).setEpisodeNumber(cursor.getString(6)).setEpisodeTitle(cursor.getString(7)).setStartTimeUtcMillis(cursor.getLong(8)).setEndTimeUtcMillis(cursor.getLong(9)).setBroadcastGenres(cursor.getString(10)).setCanonicalGenres(cursor.getString(11)).setShortDescription(cursor.getString(12)).setLongDescription(cursor.getString(13)).setVideoWidth(cursor.getInt(14)).setVideoHeight(cursor.getInt(15)).setAudioLanguage(cursor.getString(16)).setContentRating(cursor.getString(17)).setPosterArt(cursor.getString(18)).setThumbnail(cursor.getString(19)).setSearchable(cursor.getInt(20) == 1).setDataUri(cursor.getString(21)).setDataBytes(cursor.getLong(22)).setDurationMillis(cursor.getLong(23)).setExpireTimeUtcMillis(cursor.getLong(24)).setInternalProviderData(cursor.getBlob(25)).setInternalProviderFlag1(cursor.getInt(26)).setInternalProviderFlag2(cursor.getInt(27)).setInternalProviderFlag3(cursor.getInt(28)).setInternalProviderFlag4(cursor.getInt(29)).setVersionNumber(cursor.getInt(30)).build();
    }

    public static ContentValues toValues(RecordedProgram recordedProgram) {
        ContentValues contentValues = new ContentValues();
        long j = recordedProgram.mId;
        if (j != -1) {
            contentValues.put("_id", Long.valueOf(j));
        }
        contentValues.put("input_id", recordedProgram.mInputId);
        contentValues.put("channel_id", Long.valueOf(recordedProgram.mChannelId));
        contentValues.put("title", recordedProgram.mTitle);
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SEASON_DISPLAY_NUMBER, recordedProgram.mSeasonNumber);
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SEASON_TITLE, recordedProgram.mSeasonTitle);
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_EPISODE_DISPLAY_NUMBER, recordedProgram.mEpisodeNumber);
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_EPISODE_TITLE, recordedProgram.mTitle);
        contentValues.put("start_time_utc_millis", Long.valueOf(recordedProgram.mStartTimeUtcMillis));
        contentValues.put("end_time_utc_millis", Long.valueOf(recordedProgram.mEndTimeUtcMillis));
        contentValues.put("broadcast_genre", safeEncode(recordedProgram.mBroadcastGenres));
        contentValues.put("canonical_genre", safeEncode(recordedProgram.mCanonicalGenres));
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_SHORT_DESCRIPTION, recordedProgram.mShortDescription);
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_LONG_DESCRIPTION, recordedProgram.mLongDescription);
        int i = recordedProgram.mVideoWidth;
        if (i == 0) {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH);
        } else {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_VIDEO_WIDTH, Integer.valueOf(i));
        }
        int i2 = recordedProgram.mVideoHeight;
        if (i2 == 0) {
            contentValues.putNull(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT);
        } else {
            contentValues.put(TvContractCompat.ProgramColumns.COLUMN_VIDEO_HEIGHT, Integer.valueOf(i2));
        }
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_AUDIO_LANGUAGE, recordedProgram.mAudioLanguage);
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_CONTENT_RATING, recordedProgram.mContentRating);
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_POSTER_ART_URI, safeToString(recordedProgram.mPosterArt));
        contentValues.put(TvContractCompat.ProgramColumns.COLUMN_THUMBNAIL_URI, safeToString(recordedProgram.mThumbnail));
        contentValues.put("searchable", Integer.valueOf(recordedProgram.mSearchable ? 1 : 0));
        contentValues.put(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_URI, safeToString(recordedProgram.mDataUri));
        contentValues.put(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DATA_BYTES, Long.valueOf(recordedProgram.mDataBytes));
        contentValues.put(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_DURATION_MILLIS, Long.valueOf(recordedProgram.mDurationMillis));
        contentValues.put(TvContractCompat.RecordedPrograms.COLUMN_RECORDING_EXPIRE_TIME_UTC_MILLIS, Long.valueOf(recordedProgram.mExpireTimeUtcMillis));
        contentValues.put("internal_provider_data", recordedProgram.mInternalProviderData);
        contentValues.put("internal_provider_flag1", Integer.valueOf(recordedProgram.mInternalProviderFlag1));
        contentValues.put("internal_provider_flag2", Integer.valueOf(recordedProgram.mInternalProviderFlag2));
        contentValues.put("internal_provider_flag3", Integer.valueOf(recordedProgram.mInternalProviderFlag3));
        contentValues.put("internal_provider_flag4", Integer.valueOf(recordedProgram.mInternalProviderFlag4));
        contentValues.put("version_number", Integer.valueOf(recordedProgram.mVersionNumber));
        return contentValues;
    }

    public static class Builder {
        private String mAudioLanguage;
        private String[] mBroadcastGenres;
        private String[] mCanonicalGenres;
        private long mChannelId;
        private String mContentRating;
        private long mDataBytes;
        private Uri mDataUri;
        private long mDurationMillis;
        private long mEndTimeUtcMillis;
        private String mEpisodeNumber;
        private String mEpisodeTitle;
        private long mExpireTimeUtcMillis;
        private String mInputId;
        private byte[] mInternalProviderData;
        private int mInternalProviderFlag1;
        private int mInternalProviderFlag2;
        private int mInternalProviderFlag3;
        private int mInternalProviderFlag4;
        private String mLongDescription;
        private Uri mPosterArt;
        private String mSeasonNumber;
        private String mSeasonTitle;
        private String mShortDescription;
        private long mStartTimeUtcMillis;
        private Uri mThumbnail;
        private String mTitle;
        private int mVersionNumber;
        private int mVideoHeight;
        private int mVideoWidth;
        private long mId = -1;
        private boolean mSearchable = true;

        public Builder setId(long j) {
            this.mId = j;
            return this;
        }

        public Builder setInputId(String str) {
            this.mInputId = str;
            return this;
        }

        public Builder setChannelId(long j) {
            this.mChannelId = j;
            return this;
        }

        public Builder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public Builder setSeasonNumber(String str) {
            this.mSeasonNumber = str;
            return this;
        }

        public Builder setSeasonTitle(String str) {
            this.mSeasonTitle = str;
            return this;
        }

        public Builder setEpisodeNumber(String str) {
            this.mEpisodeNumber = str;
            return this;
        }

        public Builder setEpisodeTitle(String str) {
            this.mEpisodeTitle = str;
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

        public Builder setBroadcastGenres(String str) {
            if (TextUtils.isEmpty(str)) {
                this.mBroadcastGenres = null;
                return this;
            }
            return setBroadcastGenres(TvContract.Programs.Genres.decode(str));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder setBroadcastGenres(String[] strArr) {
            this.mBroadcastGenres = strArr;
            return this;
        }

        public Builder setCanonicalGenres(String str) {
            if (TextUtils.isEmpty(str)) {
                this.mCanonicalGenres = null;
                return this;
            }
            return setCanonicalGenres(TvContract.Programs.Genres.decode(str));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder setCanonicalGenres(String[] strArr) {
            this.mCanonicalGenres = strArr;
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

        public Builder setVideoWidth(int i) {
            this.mVideoWidth = i;
            return this;
        }

        public Builder setVideoHeight(int i) {
            this.mVideoHeight = i;
            return this;
        }

        public Builder setAudioLanguage(String str) {
            this.mAudioLanguage = str;
            return this;
        }

        public Builder setContentRating(String str) {
            this.mContentRating = str;
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

        public Builder setSearchable(boolean z) {
            this.mSearchable = z;
            return this;
        }

        public Builder setDataUri(String str) {
            return setDataUri(toUri(str));
        }

        public Builder setDataUri(Uri uri) {
            this.mDataUri = uri;
            return this;
        }

        public Builder setDataBytes(long j) {
            this.mDataBytes = j;
            return this;
        }

        public Builder setDurationMillis(long j) {
            this.mDurationMillis = j;
            return this;
        }

        public Builder setExpireTimeUtcMillis(long j) {
            this.mExpireTimeUtcMillis = j;
            return this;
        }

        public Builder setInternalProviderData(byte[] bArr) {
            this.mInternalProviderData = bArr;
            return this;
        }

        public Builder setInternalProviderFlag1(int i) {
            this.mInternalProviderFlag1 = i;
            return this;
        }

        public Builder setInternalProviderFlag2(int i) {
            this.mInternalProviderFlag2 = i;
            return this;
        }

        public Builder setInternalProviderFlag3(int i) {
            this.mInternalProviderFlag3 = i;
            return this;
        }

        public Builder setInternalProviderFlag4(int i) {
            this.mInternalProviderFlag4 = i;
            return this;
        }

        public Builder setVersionNumber(int i) {
            this.mVersionNumber = i;
            return this;
        }

        public RecordedProgram build() {
            return new RecordedProgram(this.mId, this.mInputId, this.mChannelId, this.mTitle, this.mSeasonNumber, this.mSeasonTitle, this.mEpisodeNumber, this.mEpisodeTitle, this.mStartTimeUtcMillis, this.mEndTimeUtcMillis, this.mBroadcastGenres, this.mCanonicalGenres, this.mShortDescription, this.mLongDescription, this.mVideoWidth, this.mVideoHeight, this.mAudioLanguage, this.mContentRating, this.mPosterArt, this.mThumbnail, this.mSearchable, this.mDataUri, this.mDataBytes, this.mDurationMillis, this.mExpireTimeUtcMillis, this.mInternalProviderData, this.mInternalProviderFlag1, this.mInternalProviderFlag2, this.mInternalProviderFlag3, this.mInternalProviderFlag4, this.mVersionNumber);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder buildFrom(RecordedProgram recordedProgram) {
        return builder().setId(recordedProgram.getId()).setInputId(recordedProgram.getInputId()).setChannelId(recordedProgram.getChannelId()).setTitle(recordedProgram.getTitle()).setSeasonNumber(recordedProgram.getSeasonNumber()).setSeasonTitle(recordedProgram.getSeasonTitle()).setEpisodeNumber(recordedProgram.getEpisodeNumber()).setEpisodeTitle(recordedProgram.getEpisodeTitle()).setStartTimeUtcMillis(recordedProgram.getStartTimeUtcMillis()).setEndTimeUtcMillis(recordedProgram.getEndTimeUtcMillis()).setBroadcastGenres(recordedProgram.getBroadcastGenres()).setCanonicalGenres(recordedProgram.getCanonicalGenres()).setShortDescription(recordedProgram.getShortDescription()).setLongDescription(recordedProgram.getLongDescription()).setVideoWidth(recordedProgram.getVideoWidth()).setVideoHeight(recordedProgram.getVideoHeight()).setAudioLanguage(recordedProgram.getAudioLanguage()).setContentRating(recordedProgram.getContentRating()).setPosterArt(recordedProgram.getPosterArt()).setThumbnail(recordedProgram.getThumbnail()).setSearchable(recordedProgram.isSearchable()).setInternalProviderData(recordedProgram.getInternalProviderData()).setInternalProviderFlag1(recordedProgram.getInternalProviderFlag1()).setInternalProviderFlag2(recordedProgram.getInternalProviderFlag2()).setInternalProviderFlag3(recordedProgram.getInternalProviderFlag3()).setInternalProviderFlag4(recordedProgram.getInternalProviderFlag4()).setVersionNumber(recordedProgram.getVersionNumber());
    }

    private RecordedProgram(long j, String str, long j2, String str2, String str3, String str4, String str5, String str6, long j3, long j4, String[] strArr, String[] strArr2, String str7, String str8, int i, int i2, String str9, String str10, Uri uri, Uri uri2, boolean z, Uri uri3, long j5, long j6, long j7, byte[] bArr, int i3, int i4, int i5, int i6, int i7) {
        this.mId = j;
        this.mInputId = str;
        this.mChannelId = j2;
        this.mTitle = str2;
        this.mSeasonNumber = str3;
        this.mSeasonTitle = str4;
        this.mEpisodeNumber = str5;
        this.mEpisodeTitle = str6;
        this.mStartTimeUtcMillis = j3;
        this.mEndTimeUtcMillis = j4;
        this.mBroadcastGenres = strArr;
        this.mCanonicalGenres = strArr2;
        this.mShortDescription = str7;
        this.mLongDescription = str8;
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        this.mAudioLanguage = str9;
        this.mContentRating = str10;
        this.mPosterArt = uri;
        this.mThumbnail = uri2;
        this.mSearchable = z;
        this.mDataUri = uri3;
        this.mDataBytes = j5;
        this.mDurationMillis = j6;
        this.mExpireTimeUtcMillis = j7;
        this.mInternalProviderData = bArr;
        this.mInternalProviderFlag1 = i3;
        this.mInternalProviderFlag2 = i4;
        this.mInternalProviderFlag3 = i5;
        this.mInternalProviderFlag4 = i6;
        this.mVersionNumber = i7;
    }

    public String getAudioLanguage() {
        return this.mAudioLanguage;
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

    public String getContentRating() {
        return this.mContentRating;
    }

    public Uri getDataUri() {
        return this.mDataUri;
    }

    public long getDataBytes() {
        return this.mDataBytes;
    }

    public long getDurationMillis() {
        return this.mDurationMillis;
    }

    public long getEndTimeUtcMillis() {
        return this.mEndTimeUtcMillis;
    }

    public String getEpisodeNumber() {
        return this.mEpisodeNumber;
    }

    public String getEpisodeTitle() {
        return this.mEpisodeTitle;
    }

    public String getEpisodeDisplayTitle(Context context) {
        return (TextUtils.isEmpty(this.mSeasonNumber) || TextUtils.isEmpty(this.mEpisodeNumber) || TextUtils.isEmpty(this.mEpisodeTitle)) ? this.mEpisodeTitle : String.format(context.getResources().getString(R.string.episode_format), this.mSeasonNumber, this.mEpisodeNumber, this.mEpisodeTitle);
    }

    public long getExpireTimeUtcMillis() {
        return this.mExpireTimeUtcMillis;
    }

    public long getId() {
        return this.mId;
    }

    public String getInputId() {
        return this.mInputId;
    }

    public byte[] getInternalProviderData() {
        return this.mInternalProviderData;
    }

    public int getInternalProviderFlag1() {
        return this.mInternalProviderFlag1;
    }

    public int getInternalProviderFlag2() {
        return this.mInternalProviderFlag2;
    }

    public int getInternalProviderFlag3() {
        return this.mInternalProviderFlag3;
    }

    public int getInternalProviderFlag4() {
        return this.mInternalProviderFlag4;
    }

    public String getLongDescription() {
        return this.mLongDescription;
    }

    public Uri getPosterArt() {
        return this.mPosterArt;
    }

    public boolean isSearchable() {
        return this.mSearchable;
    }

    public String getSeasonNumber() {
        return this.mSeasonNumber;
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

    public Uri getThumbnail() {
        return this.mThumbnail;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Uri getUri() {
        return ContentUris.withAppendedId(TvContract.RecordedPrograms.CONTENT_URI, this.mId);
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
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RecordedProgram recordedProgram = (RecordedProgram) obj;
        return Objects.equals(Long.valueOf(this.mId), Long.valueOf(recordedProgram.mId)) && Objects.equals(Long.valueOf(this.mChannelId), Long.valueOf(recordedProgram.mChannelId)) && Objects.equals(this.mSeasonNumber, recordedProgram.mSeasonNumber) && Objects.equals(this.mSeasonTitle, recordedProgram.mSeasonTitle) && Objects.equals(this.mEpisodeNumber, recordedProgram.mEpisodeNumber) && Objects.equals(Long.valueOf(this.mStartTimeUtcMillis), Long.valueOf(recordedProgram.mStartTimeUtcMillis)) && Objects.equals(Long.valueOf(this.mEndTimeUtcMillis), Long.valueOf(recordedProgram.mEndTimeUtcMillis)) && Objects.equals(Integer.valueOf(this.mVideoWidth), Integer.valueOf(recordedProgram.mVideoWidth)) && Objects.equals(Integer.valueOf(this.mVideoHeight), Integer.valueOf(recordedProgram.mVideoHeight)) && Objects.equals(Boolean.valueOf(this.mSearchable), Boolean.valueOf(recordedProgram.mSearchable)) && Objects.equals(Long.valueOf(this.mDataBytes), Long.valueOf(recordedProgram.mDataBytes)) && Objects.equals(Long.valueOf(this.mDurationMillis), Long.valueOf(recordedProgram.mDurationMillis)) && Objects.equals(Long.valueOf(this.mExpireTimeUtcMillis), Long.valueOf(recordedProgram.mExpireTimeUtcMillis)) && Objects.equals(Integer.valueOf(this.mInternalProviderFlag1), Integer.valueOf(recordedProgram.mInternalProviderFlag1)) && Objects.equals(Integer.valueOf(this.mInternalProviderFlag2), Integer.valueOf(recordedProgram.mInternalProviderFlag2)) && Objects.equals(Integer.valueOf(this.mInternalProviderFlag3), Integer.valueOf(recordedProgram.mInternalProviderFlag3)) && Objects.equals(Integer.valueOf(this.mInternalProviderFlag4), Integer.valueOf(recordedProgram.mInternalProviderFlag4)) && Objects.equals(Integer.valueOf(this.mVersionNumber), Integer.valueOf(recordedProgram.mVersionNumber)) && Objects.equals(this.mTitle, recordedProgram.mTitle) && Objects.equals(this.mEpisodeTitle, recordedProgram.mEpisodeTitle) && Arrays.equals(this.mBroadcastGenres, recordedProgram.mBroadcastGenres) && Arrays.equals(this.mCanonicalGenres, recordedProgram.mCanonicalGenres) && Objects.equals(this.mShortDescription, recordedProgram.mShortDescription) && Objects.equals(this.mLongDescription, recordedProgram.mLongDescription) && Objects.equals(this.mAudioLanguage, recordedProgram.mAudioLanguage) && Objects.equals(this.mContentRating, recordedProgram.mContentRating) && Objects.equals(this.mPosterArt, recordedProgram.mPosterArt) && Objects.equals(this.mThumbnail, recordedProgram.mThumbnail);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mId));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RecordedProgram[");
        sb.append(this.mId);
        sb.append("]{ mInputId=");
        sb.append(this.mInputId);
        sb.append(", mChannelId='");
        sb.append(this.mChannelId);
        sb.append('\'');
        sb.append(", mTitle='");
        sb.append(this.mTitle);
        sb.append('\'');
        sb.append(", mEpisodeNumber=");
        sb.append(this.mEpisodeNumber);
        sb.append(", mEpisodeTitle='");
        sb.append(this.mEpisodeTitle);
        sb.append('\'');
        sb.append(", mStartTimeUtcMillis=");
        sb.append(this.mStartTimeUtcMillis);
        sb.append(", mEndTimeUtcMillis=");
        sb.append(this.mEndTimeUtcMillis);
        sb.append(", mBroadcastGenres=");
        String[] strArr = this.mBroadcastGenres;
        sb.append(strArr != null ? Arrays.toString(strArr) : "null");
        sb.append(", mCanonicalGenres=");
        String[] strArr2 = this.mCanonicalGenres;
        sb.append(strArr2 != null ? Arrays.toString(strArr2) : "null");
        sb.append(", mShortDescription='");
        sb.append(this.mShortDescription);
        sb.append('\'');
        sb.append(", mLongDescription='");
        sb.append(this.mLongDescription);
        sb.append('\'');
        sb.append(", mVideoHeight=");
        sb.append(this.mVideoHeight);
        sb.append(", mVideoWidth=");
        sb.append(this.mVideoWidth);
        sb.append(", mAudioLanguage='");
        sb.append(this.mAudioLanguage);
        sb.append('\'');
        sb.append(", mContentRating='");
        sb.append(this.mContentRating);
        sb.append('\'');
        sb.append(", mPosterArt=");
        sb.append(this.mPosterArt);
        sb.append(", mThumbnail=");
        sb.append(this.mThumbnail);
        sb.append(", mSearchable=");
        sb.append(this.mSearchable);
        sb.append(", mDataUri=");
        sb.append(this.mDataUri);
        sb.append(", mDataBytes=");
        sb.append(this.mDataBytes);
        sb.append(", mDurationMillis=");
        sb.append(this.mDurationMillis);
        sb.append(", mExpireTimeUtcMillis=");
        sb.append(this.mExpireTimeUtcMillis);
        sb.append(", mInternalProviderData.length=");
        byte[] bArr = this.mInternalProviderData;
        sb.append(bArr != null ? Integer.valueOf(bArr.length) : "null");
        sb.append(", mInternalProviderFlag1=");
        sb.append(this.mInternalProviderFlag1);
        sb.append(", mInternalProviderFlag2=");
        sb.append(this.mInternalProviderFlag2);
        sb.append(", mInternalProviderFlag3=");
        sb.append(this.mInternalProviderFlag3);
        sb.append(", mInternalProviderFlag4=");
        sb.append(this.mInternalProviderFlag4);
        sb.append(", mSeasonNumber=");
        sb.append(this.mSeasonNumber);
        sb.append(", mSeasonTitle=");
        sb.append(this.mSeasonTitle);
        sb.append(", mVersionNumber=");
        sb.append(this.mVersionNumber);
        sb.append('}');
        return sb.toString();
    }

    private static String safeToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private static String safeEncode(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        return TvContract.Programs.Genres.encode(strArr);
    }
}
