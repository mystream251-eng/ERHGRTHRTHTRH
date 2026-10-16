package kotlinx.coroutines.channels;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: ArrayChannelState.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\u0004¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/channels/ArrayChannelState;", "Lkotlinx/coroutines/channels/ArrayBufferState;", "initialBufferSize", "", "(I)V", TtmlNode.TAG_HEAD, "getHead", "()I", "setHead", "size", "getSize", "setSize", "ensureCapacity", "", "currentSize", "capacity", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public final class ArrayChannelState extends ArrayBufferState {
    private int head;
    private int size;

    public ArrayChannelState(int i) {
        super(i);
    }

    public final int getHead() {
        return this.head;
    }

    public final void setHead(int i) {
        this.head = i;
    }

    public final int getSize() {
        return this.size;
    }

    public final void setSize(int i) {
        this.size = i;
    }

    public final void ensureCapacity(int currentSize, int capacity) {
        if (currentSize < getBuffer().length) {
            return;
        }
        Object[] objArr = new Object[Math.min(getBuffer().length * 2, capacity)];
        for (int i = 0; i < currentSize; i++) {
            objArr[i] = getBuffer()[(this.head + i) % getBuffer().length];
        }
        setBuffer(objArr);
        this.head = 0;
    }
}
