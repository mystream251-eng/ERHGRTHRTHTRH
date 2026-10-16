package net.oregan.sparq.dtvsupportlibraryaidl;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class DVBTunerMetrics {
    public static final Parcelable.Creator<DVBTunerMetrics> CREATOR = new Parcelable.Creator<DVBTunerMetrics>() { // from class: net.oregan.sparq.dtvsupportlibraryaidl.DVBTunerMetrics.1
        @Override // android.os.Parcelable.Creator
        public DVBTunerMetrics createFromParcel(Parcel parcel) {
            return new DVBTunerMetrics(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DVBTunerMetrics[] newArray(int i) {
            return new DVBTunerMetrics[i];
        }
    };
    public int BER;
    public int SNR;
    public int cableAnnex;
    public int errorPackets;
    public int frequencyKHz;
    public int qamMode;
    public boolean signalLocked;
    public int signalQuality;
    public int signalStrength;
    public int signalStrengthdBm;
    public int symbolRateKsymSec;

    public int describeContents() {
        return 0;
    }

    public DVBTunerMetrics() {
    }

    private DVBTunerMetrics(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.signalLocked ? 1 : 0);
        parcel.writeInt(this.frequencyKHz);
        parcel.writeInt(this.qamMode);
        parcel.writeInt(this.cableAnnex);
        parcel.writeInt(this.symbolRateKsymSec);
        parcel.writeInt(this.signalStrength);
        parcel.writeInt(this.signalQuality);
        parcel.writeInt(this.SNR);
        parcel.writeInt(this.errorPackets);
        parcel.writeInt(this.BER);
        parcel.writeInt(this.signalStrengthdBm);
    }

    public void readFromParcel(Parcel parcel) {
        this.signalLocked = parcel.readInt() != 0;
        this.frequencyKHz = parcel.readInt();
        this.qamMode = parcel.readInt();
        this.cableAnnex = parcel.readInt();
        this.symbolRateKsymSec = parcel.readInt();
        this.signalStrength = parcel.readInt();
        this.signalQuality = parcel.readInt();
        this.SNR = parcel.readInt();
        this.errorPackets = parcel.readInt();
        this.BER = parcel.readInt();
        this.signalStrengthdBm = parcel.readInt();
    }
}
