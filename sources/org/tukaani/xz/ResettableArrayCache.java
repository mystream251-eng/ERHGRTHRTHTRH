package org.tukaani.xz;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResettableArrayCache extends ArrayCache {
    private final ArrayCache arrayCache;
    private final List<byte[]> byteArrays;
    private final List<int[]> intArrays;

    public ResettableArrayCache(ArrayCache arrayCache) {
        ArrayList arrayList;
        this.arrayCache = arrayCache;
        if (arrayCache == ArrayCache.getDummyCache()) {
            arrayList = null;
            this.byteArrays = null;
        } else {
            this.byteArrays = new ArrayList();
            arrayList = new ArrayList();
        }
        this.intArrays = arrayList;
    }

    @Override // org.tukaani.xz.ArrayCache
    public byte[] getByteArray(int i, boolean z) {
        byte[] byteArray = this.arrayCache.getByteArray(i, z);
        List<byte[]> list = this.byteArrays;
        if (list != null) {
            synchronized (list) {
                this.byteArrays.add(byteArray);
            }
        }
        return byteArray;
    }

    @Override // org.tukaani.xz.ArrayCache
    public int[] getIntArray(int i, boolean z) {
        int[] intArray = this.arrayCache.getIntArray(i, z);
        List<int[]> list = this.intArrays;
        if (list != null) {
            synchronized (list) {
                this.intArrays.add(intArray);
            }
        }
        return intArray;
    }

    @Override // org.tukaani.xz.ArrayCache
    public void putArray(byte[] bArr) {
        List<byte[]> list = this.byteArrays;
        if (list != null) {
            synchronized (list) {
                int iLastIndexOf = this.byteArrays.lastIndexOf(bArr);
                if (iLastIndexOf != -1) {
                    this.byteArrays.remove(iLastIndexOf);
                }
            }
            this.arrayCache.putArray(bArr);
        }
    }

    @Override // org.tukaani.xz.ArrayCache
    public void putArray(int[] iArr) {
        List<int[]> list = this.intArrays;
        if (list != null) {
            synchronized (list) {
                int iLastIndexOf = this.intArrays.lastIndexOf(iArr);
                if (iLastIndexOf != -1) {
                    this.intArrays.remove(iLastIndexOf);
                }
            }
            this.arrayCache.putArray(iArr);
        }
    }

    public void reset() {
        List<byte[]> list = this.byteArrays;
        if (list != null) {
            synchronized (list) {
                for (int size = this.byteArrays.size() - 1; size >= 0; size--) {
                    this.arrayCache.putArray(this.byteArrays.get(size));
                }
                this.byteArrays.clear();
            }
            synchronized (this.intArrays) {
                for (int size2 = this.intArrays.size() - 1; size2 >= 0; size2--) {
                    this.arrayCache.putArray(this.intArrays.get(size2));
                }
                this.intArrays.clear();
            }
        }
    }
}
