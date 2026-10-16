package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;

/* JADX INFO: compiled from: AbstractSharedFlow.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\u0010\u0000\n\u0002\b\f\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\u000b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\n\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\"\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00018\u0000H\u0086\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0015\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/flow/internal/SharedFlowState;", ExifInterface.LATITUDE_SOUTH, "", "size", "<init>", "(I)V", "newSlots", "", "copyInto", "(Lkotlinx/coroutines/flow/internal/SharedFlowState;)V", "index", "get", "(I)Ljava/lang/Object;", "", "", "getBufferAt", "(J)Ljava/lang/Object;", "value", "set", "(ILjava/lang/Object;)V", "item", "setBufferAt", "(JLjava/lang/Object;)V", "I", "getSize", "()I", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
public final class SharedFlowState<S> {
    private AtomicReferenceArray arr;
    private final int size;

    public SharedFlowState(int i) {
        this.size = i;
        this.arr = new AtomicReferenceArray(this.size);
    }

    public final int getSize() {
        return this.size;
    }

    public final void set(int index, S value) {
        this.arr.set(index, value);
    }

    public final S get(int index) {
        return (S) this.arr.get(index);
    }

    public final Object getBufferAt(long index) {
        return get(((int) index) & (this.size - 1));
    }

    public final void setBufferAt(long index, S item) {
        this.arr.set(((int) index) & (this.size - 1), item);
    }

    public final void copyInto(SharedFlowState<S> newSlots) {
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            newSlots.arr.set(i2, get(i2));
        }
    }
}
