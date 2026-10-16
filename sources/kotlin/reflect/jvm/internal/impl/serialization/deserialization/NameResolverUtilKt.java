package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: NameResolverUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class NameResolverUtilKt {
    public static final ClassId getClassId(NameResolver getClassId, int i) {
        Intrinsics.checkNotNullParameter(getClassId, "$this$getClassId");
        ClassId classIdFromString = ClassId.fromString(getClassId.getQualifiedClassName(i), getClassId.isLocalClassName(i));
        Intrinsics.checkNotNullExpressionValue(classIdFromString, "ClassId.fromString(getQu… isLocalClassName(index))");
        return classIdFromString;
    }

    public static final Name getName(NameResolver getName, int i) {
        Intrinsics.checkNotNullParameter(getName, "$this$getName");
        Name nameGuessByFirstCharacter = Name.guessByFirstCharacter(getName.getString(i));
        Intrinsics.checkNotNullExpressionValue(nameGuessByFirstCharacter, "Name.guessByFirstCharacter(getString(index))");
        return nameGuessByFirstCharacter;
    }
}
