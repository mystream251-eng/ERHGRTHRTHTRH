package kotlin.reflect.jvm.internal.impl.builtins.functions;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FunctionClassKind.kt */
/* JADX INFO: loaded from: classes2.dex */
public enum FunctionClassKind {
    Function(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, "Function"),
    SuspendFunction(StandardNames.COROUTINES_PACKAGE_FQ_NAME_RELEASE, "SuspendFunction"),
    KFunction(StandardNames.KOTLIN_REFLECT_FQ_NAME, "KFunction"),
    KSuspendFunction(StandardNames.KOTLIN_REFLECT_FQ_NAME, "KSuspendFunction");

    public static final Companion Companion = new Companion(null);
    private final String classNamePrefix;
    private final FqName packageFqName;

    FunctionClassKind(FqName fqName, String str) {
        this.packageFqName = fqName;
        this.classNamePrefix = str;
    }

    public final String getClassNamePrefix() {
        return this.classNamePrefix;
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public final Name numberedClassName(int i) {
        Name nameIdentifier = Name.identifier(this.classNamePrefix + i);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(\"$classNamePrefix$arity\")");
        return nameIdentifier;
    }

    /* JADX INFO: compiled from: FunctionClassKind.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FunctionClassKind byClassNamePrefix(FqName packageFqName, String className) {
            Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
            Intrinsics.checkNotNullParameter(className, "className");
            for (FunctionClassKind functionClassKind : FunctionClassKind.values()) {
                if (Intrinsics.areEqual(functionClassKind.getPackageFqName(), packageFqName) && StringsKt.startsWith$default(className, functionClassKind.getClassNamePrefix(), false, 2, (Object) null)) {
                    return functionClassKind;
                }
            }
            return null;
        }

        /* JADX INFO: compiled from: FunctionClassKind.kt */
        public static final class KindWithArity {
            private final int arity;
            private final FunctionClassKind kind;

            public final FunctionClassKind component1() {
                return this.kind;
            }

            public final int component2() {
                return this.arity;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof KindWithArity)) {
                    return false;
                }
                KindWithArity kindWithArity = (KindWithArity) obj;
                return Intrinsics.areEqual(this.kind, kindWithArity.kind) && this.arity == kindWithArity.arity;
            }

            public int hashCode() {
                FunctionClassKind functionClassKind = this.kind;
                return ((functionClassKind != null ? functionClassKind.hashCode() : 0) * 31) + this.arity;
            }

            public String toString() {
                return "KindWithArity(kind=" + this.kind + ", arity=" + this.arity + ")";
            }

            public KindWithArity(FunctionClassKind kind, int i) {
                Intrinsics.checkNotNullParameter(kind, "kind");
                this.kind = kind;
                this.arity = i;
            }

            public final FunctionClassKind getKind() {
                return this.kind;
            }
        }

        public final KindWithArity parseClassName(String className, FqName packageFqName) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
            Companion companion = this;
            FunctionClassKind functionClassKindByClassNamePrefix = companion.byClassNamePrefix(packageFqName, className);
            if (functionClassKindByClassNamePrefix != null) {
                String strSubstring = className.substring(functionClassKindByClassNamePrefix.getClassNamePrefix().length());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
                Integer num = companion.toInt(strSubstring);
                if (num != null) {
                    return new KindWithArity(functionClassKindByClassNamePrefix, num.intValue());
                }
            }
            return null;
        }

        @JvmStatic
        public final FunctionClassKind getFunctionalClassKind(String className, FqName packageFqName) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
            KindWithArity className2 = parseClassName(className, packageFqName);
            if (className2 != null) {
                return className2.getKind();
            }
            return null;
        }

        private final Integer toInt(String str) {
            if (str.length() == 0) {
                return null;
            }
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                int iCharAt = str.charAt(i2) - '0';
                if (iCharAt < 0 || 9 < iCharAt) {
                    return null;
                }
                i = (i * 10) + iCharAt;
            }
            return Integer.valueOf(i);
        }
    }
}
