package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'UBYTE' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:422)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:351)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: UnsignedType.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class UnsignedType {
    private static final /* synthetic */ UnsignedType[] $VALUES;
    public static final UnsignedType UBYTE;
    public static final UnsignedType UINT;
    public static final UnsignedType ULONG;
    public static final UnsignedType USHORT;
    private final ClassId arrayClassId;
    private final ClassId classId;
    private final Name typeName;

    public static UnsignedType valueOf(String str) {
        return (UnsignedType) Enum.valueOf(UnsignedType.class, str);
    }

    public static UnsignedType[] values() {
        return (UnsignedType[]) $VALUES.clone();
    }

    private UnsignedType(String str, int i, ClassId classId) {
        super(str, i);
        this.classId = classId;
        Name shortClassName = classId.getShortClassName();
        Intrinsics.checkNotNullExpressionValue(shortClassName, "classId.shortClassName");
        this.typeName = shortClassName;
        this.arrayClassId = new ClassId(this.classId.getPackageFqName(), Name.identifier(this.typeName.asString() + "Array"));
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    static {
        ClassId classIdFromString = ClassId.fromString("kotlin/UByte");
        Intrinsics.checkNotNullExpressionValue(classIdFromString, "ClassId.fromString(\"kotlin/UByte\")");
        UnsignedType unsignedType = new UnsignedType("UBYTE", 0, classIdFromString);
        UBYTE = unsignedType;
        ClassId classIdFromString2 = ClassId.fromString("kotlin/UShort");
        Intrinsics.checkNotNullExpressionValue(classIdFromString2, "ClassId.fromString(\"kotlin/UShort\")");
        UnsignedType unsignedType2 = new UnsignedType("USHORT", 1, classIdFromString2);
        USHORT = unsignedType2;
        ClassId classIdFromString3 = ClassId.fromString("kotlin/UInt");
        Intrinsics.checkNotNullExpressionValue(classIdFromString3, "ClassId.fromString(\"kotlin/UInt\")");
        UnsignedType unsignedType3 = new UnsignedType("UINT", 2, classIdFromString3);
        UINT = unsignedType3;
        ClassId classIdFromString4 = ClassId.fromString("kotlin/ULong");
        Intrinsics.checkNotNullExpressionValue(classIdFromString4, "ClassId.fromString(\"kotlin/ULong\")");
        UnsignedType unsignedType4 = new UnsignedType("ULONG", 3, classIdFromString4);
        ULONG = unsignedType4;
        $VALUES = new UnsignedType[]{unsignedType, unsignedType2, unsignedType3, unsignedType4};
    }

    public final Name getTypeName() {
        return this.typeName;
    }

    public final ClassId getArrayClassId() {
        return this.arrayClassId;
    }
}
