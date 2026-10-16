package com.google.android.media.tv.companionlibrary.model;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.text.TextUtils;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.media.tv.companionlibrary.utils.CollectionUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class Channel {
    private static final long INVALID_CHANNEL_ID = -1;
    private static final int INVALID_INTEGER_VALUE = -1;
    private static final int IS_SEARCHABLE = 1;
    public static final String[] PROJECTION = getProjection();
    private int mAppLinkColor;
    private String mAppLinkIconUri;
    private String mAppLinkIntentUri;
    private String mAppLinkPosterArtUri;
    private String mAppLinkText;
    private String mChannelLogo;
    private String mDescription;
    private String mDisplayName;
    private String mDisplayNumber;
    private long mId;
    private String mInputId;
    private byte[] mInternalProviderData;
    private String mNetworkAffiliation;
    private long mOriginalNetworkId;
    private String mPackageName;
    private int mSearchable;
    private int mServiceId;
    private String mServiceType;
    private int mTransportStreamId;
    private String mType;
    private String mVideoFormat;

    private Channel() {
        this.mId = -1L;
        this.mOriginalNetworkId = -1L;
        this.mServiceType = TvContractCompat.Channels.SERVICE_TYPE_AUDIO_VIDEO;
    }

    public long getId() {
        return this.mId;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getInputId() {
        return this.mInputId;
    }

    public String getType() {
        return this.mType;
    }

    public String getDisplayNumber() {
        return this.mDisplayNumber;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getVideoFormat() {
        return this.mVideoFormat;
    }

    public long getOriginalNetworkId() {
        return this.mOriginalNetworkId;
    }

    public int getTransportStreamId() {
        return this.mTransportStreamId;
    }

    public int getServiceId() {
        return this.mServiceId;
    }

    public String getAppLinkText() {
        return this.mAppLinkText;
    }

    public int getAppLinkColor() {
        return this.mAppLinkColor;
    }

    public String getAppLinkIconUri() {
        return this.mAppLinkIconUri;
    }

    public String getAppLinkPosterArtUri() {
        return this.mAppLinkPosterArtUri;
    }

    public String getAppLinkIntentUri() {
        return this.mAppLinkIntentUri;
    }

    public String getChannelLogo() {
        return this.mChannelLogo;
    }

    public String getNetworkAffiliation() {
        return this.mNetworkAffiliation;
    }

    public boolean isSearchable() {
        return this.mSearchable == 1;
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

    public byte[] getInternalProviderDataByteArray() {
        return this.mInternalProviderData;
    }

    public String getServiceType() {
        return this.mServiceType;
    }

    public String toString() {
        return "Channel{id=" + this.mId + ", packageName=" + this.mPackageName + ", inputId=" + this.mInputId + ", originalNetworkId=" + this.mOriginalNetworkId + ", type=" + this.mType + ", displayNumber=" + this.mDisplayNumber + ", displayName=" + this.mDisplayName + ", description=" + this.mDescription + ", channelLogo=" + this.mChannelLogo + ", videoFormat=" + this.mVideoFormat + ", appLinkText=" + this.mAppLinkText + "}";
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        long j = this.mId;
        if (j != -1) {
            contentValues.put("_id", Long.valueOf(j));
        }
        if (!TextUtils.isEmpty(this.mPackageName)) {
            contentValues.put("package_name", this.mPackageName);
        } else {
            contentValues.putNull("package_name");
        }
        if (!TextUtils.isEmpty(this.mInputId)) {
            contentValues.put("input_id", this.mInputId);
        } else {
            contentValues.putNull("input_id");
        }
        if (!TextUtils.isEmpty(this.mType)) {
            contentValues.put("type", this.mType);
        } else {
            contentValues.putNull("type");
        }
        if (!TextUtils.isEmpty(this.mDisplayNumber)) {
            contentValues.put(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER, this.mDisplayNumber);
        } else {
            contentValues.putNull(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER);
        }
        if (!TextUtils.isEmpty(this.mDisplayName)) {
            contentValues.put(TvContractCompat.Channels.COLUMN_DISPLAY_NAME, this.mDisplayName);
        } else {
            contentValues.putNull(TvContractCompat.Channels.COLUMN_DISPLAY_NAME);
        }
        if (!TextUtils.isEmpty(this.mDescription)) {
            contentValues.put(TvContractCompat.Channels.COLUMN_DESCRIPTION, this.mDescription);
        } else {
            contentValues.putNull(TvContractCompat.Channels.COLUMN_DESCRIPTION);
        }
        if (!TextUtils.isEmpty(this.mVideoFormat)) {
            contentValues.put(TvContractCompat.Channels.COLUMN_VIDEO_FORMAT, this.mVideoFormat);
        } else {
            contentValues.putNull(TvContractCompat.Channels.COLUMN_VIDEO_FORMAT);
        }
        byte[] bArr = this.mInternalProviderData;
        if (bArr != null && bArr.length > 0) {
            contentValues.put("internal_provider_data", bArr);
        } else {
            contentValues.putNull("internal_provider_data");
        }
        contentValues.put(TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID, Long.valueOf(this.mOriginalNetworkId));
        contentValues.put(TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID, Integer.valueOf(this.mTransportStreamId));
        contentValues.put(TvContractCompat.Channels.COLUMN_SERVICE_ID, Integer.valueOf(this.mServiceId));
        contentValues.put(TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION, this.mNetworkAffiliation);
        contentValues.put("searchable", Integer.valueOf(this.mSearchable));
        contentValues.put(TvContractCompat.Channels.COLUMN_SERVICE_TYPE, this.mServiceType);
        if (Build.VERSION.SDK_INT > 22) {
            contentValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_COLOR, Integer.valueOf(this.mAppLinkColor));
            if (!TextUtils.isEmpty(this.mAppLinkText)) {
                contentValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_TEXT, this.mAppLinkText);
            } else {
                contentValues.putNull(TvContractCompat.Channels.COLUMN_APP_LINK_TEXT);
            }
            if (!TextUtils.isEmpty(this.mAppLinkIconUri)) {
                contentValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_ICON_URI, this.mAppLinkIconUri);
            } else {
                contentValues.putNull(TvContractCompat.Channels.COLUMN_APP_LINK_ICON_URI);
            }
            if (!TextUtils.isEmpty(this.mAppLinkPosterArtUri)) {
                contentValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI, this.mAppLinkPosterArtUri);
            } else {
                contentValues.putNull(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI);
            }
            if (!TextUtils.isEmpty(this.mAppLinkIntentUri)) {
                contentValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI, this.mAppLinkIntentUri);
            } else {
                contentValues.putNull(TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI);
            }
        }
        return contentValues;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyFrom(Channel channel) {
        if (this == channel) {
            return;
        }
        this.mId = channel.mId;
        this.mPackageName = channel.mPackageName;
        this.mInputId = channel.mInputId;
        this.mType = channel.mType;
        this.mDisplayNumber = channel.mDisplayNumber;
        this.mDisplayName = channel.mDisplayName;
        this.mDescription = channel.mDescription;
        this.mVideoFormat = channel.mVideoFormat;
        this.mOriginalNetworkId = channel.mOriginalNetworkId;
        this.mTransportStreamId = channel.mTransportStreamId;
        this.mServiceId = channel.mServiceId;
        this.mAppLinkText = channel.mAppLinkText;
        this.mAppLinkColor = channel.mAppLinkColor;
        this.mAppLinkIconUri = channel.mAppLinkIconUri;
        this.mAppLinkPosterArtUri = channel.mAppLinkPosterArtUri;
        this.mAppLinkIntentUri = channel.mAppLinkIntentUri;
        this.mChannelLogo = channel.mChannelLogo;
        this.mInternalProviderData = channel.mInternalProviderData;
        this.mNetworkAffiliation = channel.mNetworkAffiliation;
        this.mSearchable = channel.mSearchable;
        this.mServiceType = channel.mServiceType;
    }

    public static Channel fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        if (!cursor.isNull(0)) {
            builder.setId(cursor.getLong(0));
        }
        if (!cursor.isNull(1)) {
            builder.setDescription(cursor.getString(1));
        }
        if (!cursor.isNull(2)) {
            builder.setDisplayName(cursor.getString(2));
        }
        if (!cursor.isNull(3)) {
            builder.setDisplayNumber(cursor.getString(3));
        }
        if (!cursor.isNull(4)) {
            builder.setInputId(cursor.getString(4));
        }
        if (!cursor.isNull(5)) {
            builder.setInternalProviderData(cursor.getBlob(5));
        }
        if (!cursor.isNull(6)) {
            builder.setNetworkAffiliation(cursor.getString(6));
        }
        if (!cursor.isNull(7)) {
            builder.setOriginalNetworkId(cursor.getLong(7));
        }
        if (!cursor.isNull(8)) {
            builder.setPackageName(cursor.getString(8));
        }
        if (!cursor.isNull(9)) {
            builder.setSearchable(cursor.getInt(9) == 1);
        }
        if (!cursor.isNull(10)) {
            builder.setServiceId(cursor.getInt(10));
        }
        if (!cursor.isNull(11)) {
            builder.setServiceType(cursor.getString(11));
        }
        if (!cursor.isNull(12)) {
            builder.setTransportStreamId(cursor.getInt(12));
        }
        if (!cursor.isNull(13)) {
            builder.setType(cursor.getString(13));
        }
        if (!cursor.isNull(14)) {
            builder.setVideoFormat(cursor.getString(14));
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!cursor.isNull(15)) {
                builder.setAppLinkColor(cursor.getInt(15));
            }
            if (!cursor.isNull(16)) {
                builder.setAppLinkIconUri(cursor.getString(16));
            }
            if (!cursor.isNull(17)) {
                builder.setAppLinkIntentUri(cursor.getString(17));
            }
            if (!cursor.isNull(18)) {
                builder.setAppLinkPosterArtUri(cursor.getString(18));
            }
            if (!cursor.isNull(19)) {
                builder.setAppLinkText(cursor.getString(19));
            }
        }
        return builder.build();
    }

    private static String[] getProjection() {
        String[] strArr = {"_id", TvContractCompat.Channels.COLUMN_DESCRIPTION, TvContractCompat.Channels.COLUMN_DISPLAY_NAME, TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER, "input_id", "internal_provider_data", TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION, TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID, "package_name", "searchable", TvContractCompat.Channels.COLUMN_SERVICE_ID, TvContractCompat.Channels.COLUMN_SERVICE_TYPE, TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID, "type", TvContractCompat.Channels.COLUMN_VIDEO_FORMAT};
        return Build.VERSION.SDK_INT >= 23 ? (String[]) CollectionUtils.concatAll(strArr, new String[]{TvContractCompat.Channels.COLUMN_APP_LINK_COLOR, TvContractCompat.Channels.COLUMN_APP_LINK_ICON_URI, TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI, TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI, TvContractCompat.Channels.COLUMN_APP_LINK_TEXT}) : strArr;
    }

    public static final class Builder {
        private final Channel mChannel;

        public Builder() {
            this.mChannel = new Channel();
        }

        public Builder(Channel channel) {
            Channel channel2 = new Channel();
            this.mChannel = channel2;
            channel2.copyFrom(channel);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder setId(long j) {
            this.mChannel.mId = j;
            return this;
        }

        public Builder setPackageName(String str) {
            this.mChannel.mPackageName = str;
            return this;
        }

        public Builder setInputId(String str) {
            this.mChannel.mInputId = str;
            return this;
        }

        public Builder setType(String str) {
            this.mChannel.mType = str;
            return this;
        }

        public Builder setDisplayNumber(String str) {
            this.mChannel.mDisplayNumber = str;
            return this;
        }

        public Builder setDisplayName(String str) {
            this.mChannel.mDisplayName = str;
            return this;
        }

        public Builder setDescription(String str) {
            this.mChannel.mDescription = str;
            return this;
        }

        public Builder setChannelLogo(String str) {
            this.mChannel.mChannelLogo = str;
            return this;
        }

        public Builder setVideoFormat(String str) {
            this.mChannel.mVideoFormat = str;
            return this;
        }

        public Builder setOriginalNetworkId(long j) {
            this.mChannel.mOriginalNetworkId = j;
            return this;
        }

        public Builder setTransportStreamId(int i) {
            this.mChannel.mTransportStreamId = i;
            return this;
        }

        public Builder setServiceId(int i) {
            this.mChannel.mServiceId = i;
            return this;
        }

        public Builder setInternalProviderData(byte[] bArr) {
            this.mChannel.mInternalProviderData = bArr;
            return this;
        }

        public Builder setInternalProviderData(String str) {
            this.mChannel.mInternalProviderData = str.getBytes();
            return this;
        }

        public Builder setInternalProviderData(InternalProviderData internalProviderData) {
            if (internalProviderData != null) {
                this.mChannel.mInternalProviderData = internalProviderData.toString().getBytes();
            }
            return this;
        }

        public Builder setAppLinkText(String str) {
            this.mChannel.mAppLinkText = str;
            return this;
        }

        public Builder setAppLinkColor(int i) {
            this.mChannel.mAppLinkColor = i;
            return this;
        }

        public Builder setAppLinkIconUri(String str) {
            this.mChannel.mAppLinkIconUri = str;
            return this;
        }

        public Builder setAppLinkPosterArtUri(String str) {
            this.mChannel.mAppLinkPosterArtUri = str;
            return this;
        }

        public Builder setAppLinkIntent(Intent intent) {
            return setAppLinkIntentUri(intent.toUri(1));
        }

        public Builder setAppLinkIntentUri(String str) {
            this.mChannel.mAppLinkIntentUri = str;
            return this;
        }

        public Builder setNetworkAffiliation(String str) {
            this.mChannel.mNetworkAffiliation = str;
            return this;
        }

        public Builder setSearchable(boolean z) {
            this.mChannel.mSearchable = z ? 1 : 0;
            return this;
        }

        public Builder setServiceType(String str) {
            this.mChannel.mServiceType = str;
            return this;
        }

        public Channel build() {
            Channel channel = new Channel();
            channel.copyFrom(this.mChannel);
            if (channel.getOriginalNetworkId() != -1) {
                return channel;
            }
            throw new IllegalArgumentException("This channel must have a valid original network id");
        }
    }
}
