package org.tukaani.xz;

import androidx.core.app.FrameMetricsAggregator;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class BasicArrayCache extends ArrayCache {
    private static final int CACHEABLE_SIZE_MIN = 32768;
    private static final int ELEMENTS_PER_STACK = 512;
    private static final int STACKS_MAX = 32;
    private final CacheMap<byte[]> byteArrayCache = new CacheMap<>();
    private final CacheMap<int[]> intArrayCache = new CacheMap<>();

    private static class CacheMap<T> extends LinkedHashMap<Integer, CyclicStack<Reference<T>>> {
        private static final long serialVersionUID = 1;

        public CacheMap() {
            super(64, 0.75f, true);
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<Integer, CyclicStack<Reference<T>>> entry) {
            return size() > 32;
        }
    }

    private static class CyclicStack<T> {
        private final T[] elements;
        private int pos;

        private CyclicStack() {
            this.elements = (T[]) new Object[512];
            this.pos = 0;
        }

        public synchronized T pop() {
            T t;
            t = this.elements[this.pos];
            this.elements[this.pos] = null;
            this.pos = (this.pos - 1) & FrameMetricsAggregator.EVERY_DURATION;
            return t;
        }

        public synchronized void push(T t) {
            int i = (this.pos + 1) & FrameMetricsAggregator.EVERY_DURATION;
            this.pos = i;
            this.elements[i] = t;
        }
    }

    private static final class LazyHolder {
        static final BasicArrayCache INSTANCE = new BasicArrayCache();

        private LazyHolder() {
        }
    }

    private static <T> T getArray(CacheMap<T> cacheMap, int i) {
        CyclicStack<Reference<T>> cyclicStack;
        T t;
        if (i < 32768) {
            return null;
        }
        synchronized (cacheMap) {
            cyclicStack = cacheMap.get(Integer.valueOf(i));
        }
        if (cyclicStack == null) {
            return null;
        }
        do {
            Reference<T> referencePop = cyclicStack.pop();
            if (referencePop == null) {
                return null;
            }
            t = referencePop.get();
        } while (t == null);
        return t;
    }

    public static BasicArrayCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static <T> void putArray(CacheMap<T> cacheMap, T t, int i) {
        CyclicStack<Reference<T>> cyclicStack;
        if (i < 32768) {
            return;
        }
        synchronized (cacheMap) {
            cyclicStack = cacheMap.get(Integer.valueOf(i));
            if (cyclicStack == null) {
                cyclicStack = new CyclicStack<>();
                cacheMap.put(Integer.valueOf(i), cyclicStack);
            }
        }
        cyclicStack.push(new SoftReference(t));
    }

    @Override // org.tukaani.xz.ArrayCache
    public byte[] getByteArray(int i, boolean z) {
        byte[] bArr = (byte[]) getArray(this.byteArrayCache, i);
        if (bArr == null) {
            return new byte[i];
        }
        if (!z) {
            return bArr;
        }
        Arrays.fill(bArr, (byte) 0);
        return bArr;
    }

    @Override // org.tukaani.xz.ArrayCache
    public int[] getIntArray(int i, boolean z) {
        int[] iArr = (int[]) getArray(this.intArrayCache, i);
        if (iArr == null) {
            return new int[i];
        }
        if (!z) {
            return iArr;
        }
        Arrays.fill(iArr, 0);
        return iArr;
    }

    @Override // org.tukaani.xz.ArrayCache
    public void putArray(byte[] bArr) {
        putArray(this.byteArrayCache, bArr, bArr.length);
    }

    @Override // org.tukaani.xz.ArrayCache
    public void putArray(int[] iArr) {
        putArray(this.intArrayCache, iArr, iArr.length);
    }
}
