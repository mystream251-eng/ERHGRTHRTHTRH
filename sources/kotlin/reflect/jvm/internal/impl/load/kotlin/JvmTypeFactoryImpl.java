package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.exifinterface.media.ExifInterface;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: methodSignatureMapping.kt */
/* JADX INFO: loaded from: classes3.dex */
final class JvmTypeFactoryImpl implements JvmTypeFactory<JvmType> {
    public static final JvmTypeFactoryImpl INSTANCE = new JvmTypeFactoryImpl();

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PrimitiveType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PrimitiveType.BOOLEAN.ordinal()] = 1;
            $EnumSwitchMapping$0[PrimitiveType.CHAR.ordinal()] = 2;
            $EnumSwitchMapping$0[PrimitiveType.BYTE.ordinal()] = 3;
            $EnumSwitchMapping$0[PrimitiveType.SHORT.ordinal()] = 4;
            $EnumSwitchMapping$0[PrimitiveType.INT.ordinal()] = 5;
            $EnumSwitchMapping$0[PrimitiveType.FLOAT.ordinal()] = 6;
            $EnumSwitchMapping$0[PrimitiveType.LONG.ordinal()] = 7;
            $EnumSwitchMapping$0[PrimitiveType.DOUBLE.ordinal()] = 8;
        }
    }

    private JvmTypeFactoryImpl() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public JvmType boxType(JvmType possiblyPrimitiveType) {
        Intrinsics.checkNotNullParameter(possiblyPrimitiveType, "possiblyPrimitiveType");
        if (!(possiblyPrimitiveType instanceof JvmType.Primitive)) {
            return possiblyPrimitiveType;
        }
        JvmType.Primitive primitive = (JvmType.Primitive) possiblyPrimitiveType;
        if (primitive.getJvmPrimitiveType() == null) {
            return possiblyPrimitiveType;
        }
        JvmClassName jvmClassNameByFqNameWithoutInnerClasses = JvmClassName.byFqNameWithoutInnerClasses(primitive.getJvmPrimitiveType().getWrapperFqName());
        Intrinsics.checkNotNullExpressionValue(jvmClassNameByFqNameWithoutInnerClasses, "JvmClassName.byFqNameWit…mitiveType.wrapperFqName)");
        String internalName = jvmClassNameByFqNameWithoutInnerClasses.getInternalName();
        Intrinsics.checkNotNullExpressionValue(internalName, "JvmClassName.byFqNameWit…apperFqName).internalName");
        return createObjectType(internalName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public JvmType createFromString(String representation) {
        JvmPrimitiveType jvmPrimitiveType;
        Intrinsics.checkNotNullParameter(representation, "representation");
        String str = representation;
        boolean z = false;
        boolean z2 = str.length() > 0;
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("empty string as JvmType");
        }
        char cCharAt = representation.charAt(0);
        JvmPrimitiveType[] jvmPrimitiveTypeArrValues = JvmPrimitiveType.values();
        int length = jvmPrimitiveTypeArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                jvmPrimitiveType = null;
                break;
            }
            jvmPrimitiveType = jvmPrimitiveTypeArrValues[i];
            if (jvmPrimitiveType.getDesc().charAt(0) == cCharAt) {
                break;
            }
            i++;
        }
        if (jvmPrimitiveType != null) {
            return new JvmType.Primitive(jvmPrimitiveType);
        }
        if (cCharAt == 'V') {
            return new JvmType.Primitive(null);
        }
        if (cCharAt == '[') {
            String strSubstring = representation.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return new JvmType.Array(createFromString(strSubstring));
        }
        if (cCharAt == 'L' && StringsKt.endsWith$default((CharSequence) str, ';', false, 2, (Object) null)) {
            z = true;
        }
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Type that is not primitive nor array should be Object, but '" + representation + "' was found");
        }
        String strSubstring2 = representation.substring(1, representation.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return new JvmType.Object(strSubstring2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public JvmType createPrimitiveType(PrimitiveType primitiveType) {
        JvmType.Primitive bOOLEAN$descriptors_jvm;
        Intrinsics.checkNotNullParameter(primitiveType, "primitiveType");
        switch (WhenMappings.$EnumSwitchMapping$0[primitiveType.ordinal()]) {
            case 1:
                bOOLEAN$descriptors_jvm = JvmType.Companion.getBOOLEAN$descriptors_jvm();
                break;
            case 2:
                bOOLEAN$descriptors_jvm = JvmType.Companion.getCHAR$descriptors_jvm();
                break;
            case 3:
                bOOLEAN$descriptors_jvm = JvmType.Companion.getBYTE$descriptors_jvm();
                break;
            case 4:
                bOOLEAN$descriptors_jvm = JvmType.Companion.getSHORT$descriptors_jvm();
                break;
            case 5:
                bOOLEAN$descriptors_jvm = JvmType.Companion.getINT$descriptors_jvm();
                break;
            case 6:
                bOOLEAN$descriptors_jvm = JvmType.Companion.getFLOAT$descriptors_jvm();
                break;
            case 7:
                bOOLEAN$descriptors_jvm = JvmType.Companion.getLONG$descriptors_jvm();
                break;
            case 8:
                bOOLEAN$descriptors_jvm = JvmType.Companion.getDOUBLE$descriptors_jvm();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return bOOLEAN$descriptors_jvm;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public JvmType createObjectType(String internalName) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        return new JvmType.Object(internalName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public String toString(JvmType type) {
        String desc;
        Intrinsics.checkNotNullParameter(type, "type");
        if (type instanceof JvmType.Array) {
            return "[" + toString(((JvmType.Array) type).getElementType());
        }
        if (type instanceof JvmType.Primitive) {
            JvmPrimitiveType jvmPrimitiveType = ((JvmType.Primitive) type).getJvmPrimitiveType();
            if (jvmPrimitiveType == null || (desc = jvmPrimitiveType.getDesc()) == null) {
                desc = ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
            }
            Intrinsics.checkNotNullExpressionValue(desc, "type.jvmPrimitiveType?.desc ?: \"V\"");
            return desc;
        }
        if (!(type instanceof JvmType.Object)) {
            throw new NoWhenBranchMatchedException();
        }
        return "L" + ((JvmType.Object) type).getInternalName() + ";";
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public JvmType getJavaLangClassType() {
        return createObjectType("java/lang/Class");
    }
}
