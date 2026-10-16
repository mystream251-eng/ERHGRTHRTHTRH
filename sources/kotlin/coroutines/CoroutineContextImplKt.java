package kotlin.coroutines;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0007¢\u0006\u0002\u0010\u0005\u001a\u0018\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007¨\u0006\b"}, d2 = {"getPolymorphicElement", ExifInterface.LONGITUDE_EAST, "Lkotlin/coroutines/CoroutineContext$Element;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Element;Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusPolymorphicKey", "Lkotlin/coroutines/CoroutineContext;", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
public final class CoroutineContextImplKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <E extends CoroutineContext.Element> E getPolymorphicElement(CoroutineContext.Element getPolymorphicElement, CoroutineContext.Key<E> key) {
        Intrinsics.checkNotNullParameter(getPolymorphicElement, "$this$getPolymorphicElement");
        Intrinsics.checkNotNullParameter(key, "key");
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            if (!abstractCoroutineContextKey.isSubKey$kotlin_stdlib(getPolymorphicElement.getKey())) {
                return null;
            }
            E e = (E) abstractCoroutineContextKey.tryCast$kotlin_stdlib(getPolymorphicElement);
            if (e instanceof CoroutineContext.Element) {
                return e;
            }
            return null;
        }
        if (getPolymorphicElement.getKey() == key) {
            return getPolymorphicElement;
        }
        return null;
    }

    public static final CoroutineContext minusPolymorphicKey(CoroutineContext.Element minusPolymorphicKey, CoroutineContext.Key<?> key) {
        CoroutineContext coroutineContext;
        CoroutineContext coroutineContext2;
        Intrinsics.checkNotNullParameter(minusPolymorphicKey, "$this$minusPolymorphicKey");
        Intrinsics.checkNotNullParameter(key, "key");
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            if (abstractCoroutineContextKey.isSubKey$kotlin_stdlib(minusPolymorphicKey.getKey()) && abstractCoroutineContextKey.tryCast$kotlin_stdlib(minusPolymorphicKey) != null) {
                coroutineContext2 = minusPolymorphicKey;
                coroutineContext2 = minusPolymorphicKey;
                coroutineContext2 = EmptyCoroutineContext.INSTANCE;
            }
            coroutineContext2 = minusPolymorphicKey;
            coroutineContext2 = minusPolymorphicKey;
            coroutineContext2 = minusPolymorphicKey;
            return coroutineContext2;
        }
        if (minusPolymorphicKey.getKey() == key) {
            coroutineContext = minusPolymorphicKey;
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        coroutineContext = minusPolymorphicKey;
        return coroutineContext;
    }
}
