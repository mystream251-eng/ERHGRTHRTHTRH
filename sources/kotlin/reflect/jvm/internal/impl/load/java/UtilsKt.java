package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.NumberWithRadix;
import kotlin.reflect.jvm.internal.impl.utils.NumbersKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class UtilsKt {
    public static final JavaDefaultValue lexicalCastFrom(KotlinType lexicalCastFrom, String value) {
        Object doubleOrNull;
        Intrinsics.checkNotNullParameter(lexicalCastFrom, "$this$lexicalCastFrom");
        Intrinsics.checkNotNullParameter(value, "value");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = lexicalCastFrom.getConstructor().mo1268getDeclarationDescriptor();
        JavaDefaultValue enumEntry = null;
        if (classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassDescriptor) {
            ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo1268getDeclarationDescriptor;
            if (classDescriptor.getKind() == ClassKind.ENUM_CLASS) {
                MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
                Name nameIdentifier = Name.identifier(value);
                Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(value)");
                ClassifierDescriptor contributedClassifier = unsubstitutedInnerClassesScope.mo1270getContributedClassifier(nameIdentifier, NoLookupLocation.FROM_BACKEND);
                if (contributedClassifier instanceof ClassDescriptor) {
                    ClassDescriptor classDescriptor2 = (ClassDescriptor) contributedClassifier;
                    if (classDescriptor2.getKind() == ClassKind.ENUM_ENTRY) {
                        enumEntry = new EnumEntry(classDescriptor2);
                    }
                }
                return enumEntry;
            }
        }
        KotlinType kotlinTypeMakeNotNullable = TypeUtilsKt.makeNotNullable(lexicalCastFrom);
        NumberWithRadix numberWithRadixExtractRadix = NumbersKt.extractRadix(value);
        String strComponent1 = numberWithRadixExtractRadix.component1();
        int iComponent2 = numberWithRadixExtractRadix.component2();
        try {
            if (KotlinBuiltIns.isBoolean(kotlinTypeMakeNotNullable)) {
                doubleOrNull = Boolean.valueOf(Boolean.parseBoolean(value));
            } else if (KotlinBuiltIns.isChar(kotlinTypeMakeNotNullable)) {
                doubleOrNull = StringsKt.singleOrNull(value);
            } else if (KotlinBuiltIns.isByte(kotlinTypeMakeNotNullable)) {
                doubleOrNull = StringsKt.toByteOrNull(strComponent1, iComponent2);
            } else if (KotlinBuiltIns.isShort(kotlinTypeMakeNotNullable)) {
                doubleOrNull = StringsKt.toShortOrNull(strComponent1, iComponent2);
            } else if (KotlinBuiltIns.isInt(kotlinTypeMakeNotNullable)) {
                doubleOrNull = StringsKt.toIntOrNull(strComponent1, iComponent2);
            } else if (KotlinBuiltIns.isLong(kotlinTypeMakeNotNullable)) {
                doubleOrNull = StringsKt.toLongOrNull(strComponent1, iComponent2);
            } else if (KotlinBuiltIns.isFloat(kotlinTypeMakeNotNullable)) {
                doubleOrNull = StringsKt.toFloatOrNull(value);
            } else if (KotlinBuiltIns.isDouble(kotlinTypeMakeNotNullable)) {
                doubleOrNull = StringsKt.toDoubleOrNull(value);
            } else {
                doubleOrNull = KotlinBuiltIns.isString(kotlinTypeMakeNotNullable) ? null : null;
            }
        } catch (IllegalArgumentException unused) {
        }
        return doubleOrNull != null ? new Constant(doubleOrNull) : null;
    }

    public static final DescriptorVisibility toDescriptorVisibility(Visibility toDescriptorVisibility) {
        Intrinsics.checkNotNullParameter(toDescriptorVisibility, "$this$toDescriptorVisibility");
        DescriptorVisibility descriptorVisibility = JavaDescriptorVisibilities.toDescriptorVisibility(toDescriptorVisibility);
        Intrinsics.checkNotNullExpressionValue(descriptorVisibility, "JavaDescriptorVisibiliti…escriptorVisibility(this)");
        return descriptorVisibility;
    }
}
