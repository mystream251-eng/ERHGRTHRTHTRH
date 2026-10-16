package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: compiled from: ArrayBufferState.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u0003J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001J\"\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0017H\u0086\b¢\u0006\u0002\u0010\u0018R$\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0084\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/channels/ArrayBufferState;", "", "initialBufferSize", "", "(I)V", "buffer", "", "getBuffer", "()[Ljava/lang/Object;", "setBuffer", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "bufferSize", "getBufferSize", "()I", "getBufferAt", "index", "setBufferAt", "", "value", "withLock", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public class ArrayBufferState {
    private Object[] buffer;

    public ArrayBufferState(int i) {
        this.buffer = new Object[i];
    }

    protected final Object[] getBuffer() {
        return this.buffer;
    }

    protected final void setBuffer(Object[] objArr) {
        this.buffer = objArr;
    }

    public final int getBufferSize() {
        return this.buffer.length;
    }

    public final Object getBufferAt(int index) {
        return this.buffer[index];
    }

    public final void setBufferAt(int index, Object value) {
        this.buffer[index] = value;
    }

    public final <T> T withLock(Function0<? extends T> block) {
        T tInvoke;
        synchronized (this) {
            try {
                tInvoke = block.invoke();
                InlineMarker.finallyStart(1);
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
        return tInvoke;
    }
}
