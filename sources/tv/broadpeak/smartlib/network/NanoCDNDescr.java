package tv.broadpeak.smartlib.network;

/* JADX INFO: loaded from: classes3.dex */
public class NanoCDNDescr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f213a;
    public int b;
    public int c;
    public double d;
    public String e;
    public int f;
    public int g;

    public NanoCDNDescr(String str, int i, int i2, double d, String str2, int i3, int i4) {
        this.f213a = str;
        this.b = i;
        this.c = i2;
        this.d = d;
        this.e = str2;
        this.f = i3;
        this.g = i4;
    }

    public String getHostIP() {
        return this.f213a;
    }

    public String getJsonAccountId() {
        return this.e;
    }

    public int getMaxNbSessions() {
        return this.c;
    }

    public int getMcastFrame() {
        return this.g;
    }

    public int getNbSessions() {
        return this.b;
    }

    public int getProfilePrefetched() {
        return this.f;
    }

    public double getRatio() {
        return this.d;
    }

    public String toString() {
        return "NanoCDNDescr{mHostIP='" + this.f213a + "', mNbSessions=" + this.b + ", mMaxNbSessions=" + this.c + ", mRatio=" + this.d + ", mJsonAccountId='" + this.e + "', mProfilePrefetched=" + this.f + ", mMcastFrame=" + this.g + '}';
    }
}
