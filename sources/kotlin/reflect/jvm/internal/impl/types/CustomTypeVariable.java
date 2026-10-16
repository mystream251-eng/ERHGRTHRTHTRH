package kotlin.reflect.jvm.internal.impl.types;

/* JADX INFO: compiled from: TypeCapabilities.kt */
/* JADX INFO: loaded from: classes3.dex */
public interface CustomTypeVariable {
    boolean isTypeVariable();

    KotlinType substitutionResult(KotlinType kotlinType);
}
