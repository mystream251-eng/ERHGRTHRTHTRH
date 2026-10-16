package com.google.android.media.tv.companionlibrary.model;

/* JADX INFO: loaded from: classes2.dex */
public class Advertisement implements Comparable<Advertisement> {
    public static final int TYPE_VAST = 0;
    private String mRequestUrl;
    private long mStartTimeUtcMillis;
    private long mStopTimeUtcMillis;
    private int mType;

    private Advertisement() {
        this.mType = 0;
    }

    public long getStartTimeUtcMillis() {
        return this.mStartTimeUtcMillis;
    }

    public long getStopTimeUtcMillis() {
        return this.mStopTimeUtcMillis;
    }

    public int getType() {
        return this.mType;
    }

    public String getRequestUrl() {
        return this.mRequestUrl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Advertisement)) {
            return false;
        }
        Advertisement advertisement = (Advertisement) obj;
        if (this.mStartTimeUtcMillis == advertisement.getStartTimeUtcMillis() && this.mStopTimeUtcMillis == advertisement.getStopTimeUtcMillis() && this.mType == advertisement.getType()) {
            return this.mRequestUrl.equals(advertisement.getRequestUrl());
        }
        return false;
    }

    public int hashCode() {
        long j = this.mStartTimeUtcMillis;
        long j2 = this.mStopTimeUtcMillis;
        int i = ((((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + this.mType) * 31;
        String str = this.mRequestUrl;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "Advertisement{start=" + this.mStartTimeUtcMillis + ", stop=" + this.mStopTimeUtcMillis + ", type=" + this.mType + ", request-url=" + this.mRequestUrl + "}";
    }

    @Override // java.lang.Comparable
    public int compareTo(Advertisement advertisement) {
        int iCompare = Long.compare(getStartTimeUtcMillis(), advertisement.getStartTimeUtcMillis());
        return iCompare != 0 ? iCompare : Long.compare(getStopTimeUtcMillis(), advertisement.getStopTimeUtcMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyFrom(Advertisement advertisement) {
        if (this == advertisement) {
            return;
        }
        this.mStartTimeUtcMillis = advertisement.getStartTimeUtcMillis();
        this.mStopTimeUtcMillis = advertisement.getStopTimeUtcMillis();
        this.mType = advertisement.getType();
        this.mRequestUrl = advertisement.getRequestUrl();
    }

    public static final class Builder {
        private final Advertisement mAdvertisement;

        public Builder() {
            this.mAdvertisement = new Advertisement();
        }

        public Builder(Advertisement advertisement) {
            Advertisement advertisement2 = new Advertisement();
            this.mAdvertisement = advertisement2;
            advertisement2.copyFrom(advertisement);
        }

        public Builder setStartTimeUtcMillis(long j) {
            this.mAdvertisement.mStartTimeUtcMillis = j;
            return this;
        }

        public Builder setStopTimeUtcMillis(long j) {
            this.mAdvertisement.mStopTimeUtcMillis = j;
            return this;
        }

        public Builder setType(int i) {
            if (i == 0) {
                this.mAdvertisement.mType = 0;
                return this;
            }
            throw new IllegalStateException("Unsupported type: " + i);
        }

        public Builder setRequestUrl(String str) {
            this.mAdvertisement.mRequestUrl = str;
            return this;
        }

        public Advertisement build() {
            return this.mAdvertisement;
        }
    }
}
