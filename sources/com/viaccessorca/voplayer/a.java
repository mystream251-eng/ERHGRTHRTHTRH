package com.viaccessorca.voplayer;

import com.viaccessorca.common.VOUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final UUID e = new UUID(0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte[] f114a = null;
    private byte[] b = null;
    private byte[] c = null;
    private byte[] d = null;

    private byte[] a(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[0];
        if (byteBuffer == null) {
            return bArr;
        }
        byte[] bArr2 = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr2);
        return bArr2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        byte[] bArr = this.c;
        if (bArr == null) {
            bArr = this.f114a;
        }
        byte[] bArr2 = this.d;
        if (bArr2 == null) {
            bArr2 = this.b;
        }
        byte[] bArr3 = aVar.c;
        if (bArr3 == null) {
            bArr3 = aVar.f114a;
        }
        byte[] bArr4 = aVar.d;
        if (bArr4 == null) {
            bArr4 = aVar.b;
        }
        if (bArr2 == null || VOUtils.bytesToUUID(bArr2).equals(e) || bArr4 == null || VOUtils.bytesToUUID(bArr4).equals(e) || !Arrays.equals(bArr2, bArr4)) {
            return (bArr == null || bArr3 == null || !Arrays.equals(bArr, bArr3)) ? false : true;
        }
        return true;
    }

    public byte[] getAudioHeader() {
        return this.f114a;
    }

    public byte[] getAudioKID() {
        return this.b;
    }

    public byte[] getVideoHeader() {
        return this.c;
    }

    public byte[] getVideoKID() {
        return this.d;
    }

    public void setAudioHeader(ByteBuffer byteBuffer) {
        this.f114a = a(byteBuffer);
    }

    public void setAudioKID(ByteBuffer byteBuffer) {
        this.b = a(byteBuffer);
    }

    public void setVideoHeader(ByteBuffer byteBuffer) {
        this.c = a(byteBuffer);
    }

    public void setVideoKID(ByteBuffer byteBuffer) {
        this.d = a(byteBuffer);
    }

    public String toString() {
        return ((("\nmAudioHeader:" + VOUtils.bytesToHex(this.f114a)) + "\nmAudioKID:" + VOUtils.bytesToUUID(this.b)) + "\nmVideoHeader:" + VOUtils.bytesToHex(this.c)) + "\nmVideoKID:" + VOUtils.bytesToUUID(this.d);
    }
}
