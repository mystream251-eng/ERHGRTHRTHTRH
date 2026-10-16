package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: PrimitiveType.kt */
/* JADX INFO: loaded from: classes2.dex */
public enum PrimitiveType {
    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");

    private final Lazy arrayTypeFqName$delegate;
    private final Name arrayTypeName;
    private final Lazy typeFqName$delegate;
    private final Name typeName;
    public static final Companion Companion = new Companion(null);
    public static final Set<PrimitiveType> NUMBER_TYPES = SetsKt.setOf((Object[]) new PrimitiveType[]{CHAR, BYTE, SHORT, INT, FLOAT, LONG, DOUBLE});

    public final FqName getArrayTypeFqName() {
        return (FqName) this.arrayTypeFqName$delegate.getValue();
    }

    public final FqName getTypeFqName() {
        return (FqName) this.typeFqName$delegate.getValue();
    }

    PrimitiveType(String str) {
        Name nameIdentifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(typeName)");
        this.typeName = nameIdentifier;
        Name nameIdentifier2 = Name.identifier(str + "Array");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "Name.identifier(\"${typeName}Array\")");
        this.arrayTypeName = nameIdentifier2;
        this.typeFqName$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<FqName>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType$typeFqName$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final FqName invoke() {
                FqName fqNameChild = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(this.this$0.getTypeName());
                Intrinsics.checkNotNullExpressionValue(fqNameChild, "StandardNames.BUILT_INS_…NAME.child(this.typeName)");
                return fqNameChild;
            }
        });
        this.arrayTypeFqName$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<FqName>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType$arrayTypeFqName$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final FqName invoke() {
                FqName fqNameChild = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(this.this$0.getArrayTypeName());
                Intrinsics.checkNotNullExpressionValue(fqNameChild, "StandardNames.BUILT_INS_…NAME.child(arrayTypeName)");
                return fqNameChild;
            }
        });
    }

    public final Name getTypeName() {
        return this.typeName;
    }

    public final Name getArrayTypeName() {
        return this.arrayTypeName;
    }

    /* JADX INFO: compiled from: PrimitiveType.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
