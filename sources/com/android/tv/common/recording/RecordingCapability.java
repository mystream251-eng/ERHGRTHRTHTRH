package com.android.tv.common.recording;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class RecordingCapability implements Parcelable {
    public static final Parcelable.Creator<RecordingCapability> CREATOR = new Parcelable.Creator<RecordingCapability>() { // from class: com.android.tv.common.recording.RecordingCapability.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecordingCapability createFromParcel(Parcel parcel) {
            return new RecordingCapability(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecordingCapability[] newArray(int i) {
            return new RecordingCapability[i];
        }
    };
    public final String inputId;
    public final int maxConcurrentPlayingSessions;
    public final int maxConcurrentSessionsOfAllTypes;
    public final int maxConcurrentTunedSessions;
    public final boolean playbackWhileRecording;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private RecordingCapability(String str, int i, int i2, int i3, boolean z) {
        this.inputId = str;
        this.maxConcurrentTunedSessions = i;
        this.maxConcurrentPlayingSessions = i2;
        this.maxConcurrentSessionsOfAllTypes = i3;
        this.playbackWhileRecording = z;
    }

    protected RecordingCapability(Parcel parcel) {
        this.inputId = parcel.readString();
        this.maxConcurrentTunedSessions = parcel.readInt();
        this.maxConcurrentPlayingSessions = parcel.readInt();
        this.maxConcurrentSessionsOfAllTypes = parcel.readInt();
        this.playbackWhileRecording = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.inputId);
        parcel.writeInt(this.maxConcurrentTunedSessions);
        parcel.writeInt(this.maxConcurrentPlayingSessions);
        parcel.writeInt(this.maxConcurrentSessionsOfAllTypes);
        parcel.writeByte(this.playbackWhileRecording ? (byte) 1 : (byte) 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecordingCapability)) {
            return false;
        }
        RecordingCapability recordingCapability = (RecordingCapability) obj;
        return Objects.equals(Integer.valueOf(this.maxConcurrentTunedSessions), Integer.valueOf(recordingCapability.maxConcurrentTunedSessions)) && Objects.equals(Integer.valueOf(this.maxConcurrentPlayingSessions), Integer.valueOf(recordingCapability.maxConcurrentPlayingSessions)) && Objects.equals(Integer.valueOf(this.maxConcurrentSessionsOfAllTypes), Integer.valueOf(recordingCapability.maxConcurrentSessionsOfAllTypes)) && Objects.equals(Boolean.valueOf(this.playbackWhileRecording), Boolean.valueOf(recordingCapability.playbackWhileRecording)) && Objects.equals(this.inputId, recordingCapability.inputId);
    }

    public int hashCode() {
        return Objects.hash(this.inputId);
    }

    public String toString() {
        return "RecordingCapability{inputId='" + this.inputId + "', maxConcurrentTunedSessions=" + this.maxConcurrentTunedSessions + ", maxConcurrentPlayingSessions=" + this.maxConcurrentPlayingSessions + ", maxConcurrentSessionsOfAllTypes=" + this.maxConcurrentSessionsOfAllTypes + ", playbackWhileRecording=" + this.playbackWhileRecording + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String mInputId;
        private int mMaxConcurrentPlayingSessions;
        private int mMaxConcurrentSessionsOfAllTypes;
        private int mMaxConcurrentTunedSessions;
        private boolean mPlaybackWhileRecording;

        public Builder setInputId(String str) {
            this.mInputId = str;
            return this;
        }

        public Builder setMaxConcurrentTunedSessions(int i) {
            this.mMaxConcurrentTunedSessions = i;
            return this;
        }

        public Builder setMaxConcurrentPlayingSessions(int i) {
            this.mMaxConcurrentPlayingSessions = i;
            return this;
        }

        public Builder setMaxConcurrentSessionsOfAllTypes(int i) {
            this.mMaxConcurrentSessionsOfAllTypes = i;
            return this;
        }

        public Builder setPlaybackWhileRecording(boolean z) {
            this.mPlaybackWhileRecording = z;
            return this;
        }

        public RecordingCapability build() {
            return new RecordingCapability(this.mInputId, this.mMaxConcurrentTunedSessions, this.mMaxConcurrentPlayingSessions, this.mMaxConcurrentSessionsOfAllTypes, this.mPlaybackWhileRecording);
        }
    }
}
