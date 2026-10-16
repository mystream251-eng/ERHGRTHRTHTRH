package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.PointerIconCompat;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: compiled from: TypeMappingMode.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeMappingMode {
    public static final TypeMappingMode CLASS_DECLARATION;
    public static final Companion Companion = new Companion(null);
    public static final TypeMappingMode DEFAULT;
    public static final TypeMappingMode DEFAULT_UAST;
    public static final TypeMappingMode GENERIC_ARGUMENT;
    public static final TypeMappingMode GENERIC_ARGUMENT_UAST;
    public static final TypeMappingMode RETURN_TYPE_BOXED;
    public static final TypeMappingMode SUPER_TYPE;
    public static final TypeMappingMode SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS;
    public static final TypeMappingMode VALUE_FOR_ANNOTATION;
    private final TypeMappingMode genericArgumentMode;
    private final TypeMappingMode genericContravariantArgumentMode;
    private final TypeMappingMode genericInvariantArgumentMode;
    private final boolean isForAnnotationParameter;
    private final boolean kotlinCollectionsToJavaCollections;
    private final boolean mapTypeAliases;
    private final boolean needInlineClassWrapping;
    private final boolean needPrimitiveBoxing;
    private final boolean skipDeclarationSiteWildcards;
    private final boolean skipDeclarationSiteWildcardsIfPossible;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            $EnumSwitchMapping$0[Variance.INVARIANT.ordinal()] = 2;
        }
    }

    private TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3, boolean z7) {
        this.needPrimitiveBoxing = z;
        this.needInlineClassWrapping = z2;
        this.isForAnnotationParameter = z3;
        this.skipDeclarationSiteWildcards = z4;
        this.skipDeclarationSiteWildcardsIfPossible = z5;
        this.genericArgumentMode = typeMappingMode;
        this.kotlinCollectionsToJavaCollections = z6;
        this.genericContravariantArgumentMode = typeMappingMode2;
        this.genericInvariantArgumentMode = typeMappingMode3;
        this.mapTypeAliases = z7;
    }

    public final boolean getNeedPrimitiveBoxing() {
        return this.needPrimitiveBoxing;
    }

    public final boolean getNeedInlineClassWrapping() {
        return this.needInlineClassWrapping;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* synthetic */ TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3, boolean z7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        boolean z8 = (i & 1) != 0 ? true : z;
        boolean z9 = (i & 2) != 0 ? true : z2;
        boolean z10 = (i & 4) != 0 ? false : z3;
        boolean z11 = (i & 8) != 0 ? false : z4;
        boolean z12 = (i & 16) != 0 ? false : z5;
        TypeMappingMode typeMappingMode4 = (i & 32) != 0 ? (TypeMappingMode) null : typeMappingMode;
        this(z8, z9, z10, z11, z12, typeMappingMode4, (i & 64) == 0 ? z6 : true, (i & 128) != 0 ? typeMappingMode4 : typeMappingMode2, (i & 256) != 0 ? typeMappingMode4 : typeMappingMode3, (i & 512) == 0 ? z7 : false);
    }

    public final boolean getKotlinCollectionsToJavaCollections() {
        return this.kotlinCollectionsToJavaCollections;
    }

    public final boolean getMapTypeAliases() {
        return this.mapTypeAliases;
    }

    /* JADX INFO: compiled from: TypeMappingMode.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        TypeMappingMode typeMappingMode = null;
        GENERIC_ARGUMENT = new TypeMappingMode(false, z, z2, z3, false, null, false, typeMappingMode, null, false, 1023, null);
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        TypeMappingMode typeMappingMode2 = null;
        GENERIC_ARGUMENT_UAST = new TypeMappingMode(z4, z5, z6, z7, false, null, false, typeMappingMode2, null, true, FrameMetricsAggregator.EVERY_DURATION, null);
        boolean z8 = false;
        boolean z9 = false;
        TypeMappingMode typeMappingMode3 = null;
        DefaultConstructorMarker defaultConstructorMarker = null;
        RETURN_TYPE_BOXED = new TypeMappingMode(z8, true, z, z2, z3, null, z9, typeMappingMode3, typeMappingMode, false, PointerIconCompat.TYPE_GRABBING, defaultConstructorMarker);
        boolean z10 = false;
        boolean z11 = false;
        TypeMappingMode typeMappingMode4 = null;
        boolean z12 = false;
        int i = 988;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        DEFAULT = new TypeMappingMode(z10, z4, z5, z6, z7, GENERIC_ARGUMENT, z11, typeMappingMode4, typeMappingMode2, z12, i, defaultConstructorMarker2);
        boolean z13 = false;
        DEFAULT_UAST = new TypeMappingMode(z8, z13, z, z2, z3, GENERIC_ARGUMENT_UAST, z9, typeMappingMode3, typeMappingMode, true, 476, defaultConstructorMarker);
        CLASS_DECLARATION = new TypeMappingMode(z10, true, z5, z6, z7, GENERIC_ARGUMENT, z11, typeMappingMode4, typeMappingMode2, z12, i, defaultConstructorMarker2);
        boolean z14 = false;
        SUPER_TYPE = new TypeMappingMode(z8, z13, z, true, z3, GENERIC_ARGUMENT, z9, typeMappingMode3, typeMappingMode, z14, 983, defaultConstructorMarker);
        SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS = new TypeMappingMode(z10, false, z5, true, z7, GENERIC_ARGUMENT, z11, typeMappingMode4, typeMappingMode2, z12, 919, defaultConstructorMarker2);
        VALUE_FOR_ANNOTATION = new TypeMappingMode(z8, z13, true, false, z3, GENERIC_ARGUMENT, z9, typeMappingMode3, typeMappingMode, z14, 984, defaultConstructorMarker);
    }

    public final TypeMappingMode toGenericArgumentMode(Variance effectiveVariance, boolean z) {
        Intrinsics.checkNotNullParameter(effectiveVariance, "effectiveVariance");
        if (!z || !this.isForAnnotationParameter) {
            int i = WhenMappings.$EnumSwitchMapping$0[effectiveVariance.ordinal()];
            if (i == 1) {
                TypeMappingMode typeMappingMode = this.genericContravariantArgumentMode;
                if (typeMappingMode != null) {
                    return typeMappingMode;
                }
            } else if (i == 2) {
                TypeMappingMode typeMappingMode2 = this.genericInvariantArgumentMode;
                if (typeMappingMode2 != null) {
                    return typeMappingMode2;
                }
            } else {
                TypeMappingMode typeMappingMode3 = this.genericArgumentMode;
                if (typeMappingMode3 != null) {
                    return typeMappingMode3;
                }
            }
        }
        return this;
    }

    public final TypeMappingMode wrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, true, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode, false, 512, null);
    }
}
