package kotlin.reflect.jvm.internal.impl.types.model;

/* JADX INFO: compiled from: TypeSystemContext.kt */
/* JADX INFO: loaded from: classes3.dex */
public enum TypeVariance {
    IN("in"),
    OUT("out"),
    INV("");

    private final String presentation;

    TypeVariance(String str) {
        this.presentation = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.presentation;
    }
}
