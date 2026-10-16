package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassKind;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: StandardNames.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class StandardNames {
    public static final FqName ANNOTATION_PACKAGE_FQ_NAME;
    public static final FqName BUILT_INS_PACKAGE_FQ_NAME;
    public static final Set<FqName> BUILT_INS_PACKAGE_FQ_NAMES;
    public static final Name BUILT_INS_PACKAGE_NAME;
    public static final FqName COLLECTIONS_PACKAGE_FQ_NAME;
    public static final FqName CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL;
    public static final FqName CONTINUATION_INTERFACE_FQ_NAME_RELEASE;
    public static final FqName COROUTINES_INTRINSICS_PACKAGE_FQ_NAME_EXPERIMENTAL;
    public static final FqName COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL;
    public static final FqName COROUTINES_PACKAGE_FQ_NAME_RELEASE;
    public static final Name ENUM_VALUES;
    public static final Name ENUM_VALUE_OF;
    public static final StandardNames INSTANCE = new StandardNames();
    public static final FqName KOTLIN_REFLECT_FQ_NAME;
    public static final List<String> PREFIXES;
    public static final FqName RANGES_PACKAGE_FQ_NAME;
    public static final FqName RESULT_FQ_NAME;
    public static final FqName TEXT_PACKAGE_FQ_NAME;

    static {
        Name nameIdentifier = Name.identifier("values");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(\"values\")");
        ENUM_VALUES = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("valueOf");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "Name.identifier(\"valueOf\")");
        ENUM_VALUE_OF = nameIdentifier2;
        FqName fqName = new FqName("kotlin.coroutines");
        COROUTINES_PACKAGE_FQ_NAME_RELEASE = fqName;
        FqName fqNameChild = fqName.child(Name.identifier("experimental"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild, "COROUTINES_PACKAGE_FQ_NA…entifier(\"experimental\"))");
        COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL = fqNameChild;
        FqName fqNameChild2 = fqNameChild.child(Name.identifier("intrinsics"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild2, "COROUTINES_PACKAGE_FQ_NA…identifier(\"intrinsics\"))");
        COROUTINES_INTRINSICS_PACKAGE_FQ_NAME_EXPERIMENTAL = fqNameChild2;
        FqName fqNameChild3 = COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL.child(Name.identifier("Continuation"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild3, "COROUTINES_PACKAGE_FQ_NA…entifier(\"Continuation\"))");
        CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL = fqNameChild3;
        FqName fqNameChild4 = COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier("Continuation"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild4, "COROUTINES_PACKAGE_FQ_NA…entifier(\"Continuation\"))");
        CONTINUATION_INTERFACE_FQ_NAME_RELEASE = fqNameChild4;
        RESULT_FQ_NAME = new FqName("kotlin.Result");
        KOTLIN_REFLECT_FQ_NAME = new FqName("kotlin.reflect");
        PREFIXES = CollectionsKt.listOf((Object[]) new String[]{"KProperty", "KMutableProperty", "KFunction", "KSuspendFunction"});
        Name nameIdentifier3 = Name.identifier("kotlin");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier3, "Name.identifier(\"kotlin\")");
        BUILT_INS_PACKAGE_NAME = nameIdentifier3;
        FqName fqName2 = FqName.topLevel(nameIdentifier3);
        Intrinsics.checkNotNullExpressionValue(fqName2, "FqName.topLevel(BUILT_INS_PACKAGE_NAME)");
        BUILT_INS_PACKAGE_FQ_NAME = fqName2;
        FqName fqNameChild5 = fqName2.child(Name.identifier("annotation"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild5, "BUILT_INS_PACKAGE_FQ_NAM…identifier(\"annotation\"))");
        ANNOTATION_PACKAGE_FQ_NAME = fqNameChild5;
        FqName fqNameChild6 = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("collections"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild6, "BUILT_INS_PACKAGE_FQ_NAM…dentifier(\"collections\"))");
        COLLECTIONS_PACKAGE_FQ_NAME = fqNameChild6;
        FqName fqNameChild7 = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("ranges"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild7, "BUILT_INS_PACKAGE_FQ_NAM…ame.identifier(\"ranges\"))");
        RANGES_PACKAGE_FQ_NAME = fqNameChild7;
        FqName fqNameChild8 = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("text"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild8, "BUILT_INS_PACKAGE_FQ_NAM…(Name.identifier(\"text\"))");
        TEXT_PACKAGE_FQ_NAME = fqNameChild8;
        FqName fqName3 = BUILT_INS_PACKAGE_FQ_NAME;
        FqName fqNameChild9 = fqName3.child(Name.identifier("internal"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild9, "BUILT_INS_PACKAGE_FQ_NAM…e.identifier(\"internal\"))");
        BUILT_INS_PACKAGE_FQ_NAMES = SetsKt.setOf((Object[]) new FqName[]{fqName3, COLLECTIONS_PACKAGE_FQ_NAME, RANGES_PACKAGE_FQ_NAME, ANNOTATION_PACKAGE_FQ_NAME, KOTLIN_REFLECT_FQ_NAME, fqNameChild9, COROUTINES_PACKAGE_FQ_NAME_RELEASE});
    }

    private StandardNames() {
    }

    /* JADX INFO: compiled from: StandardNames.kt */
    public static final class FqNames {
        public static final FqNames INSTANCE;
        public static final FqNameUnsafe _boolean;
        public static final FqNameUnsafe _byte;
        public static final FqNameUnsafe _char;
        public static final FqNameUnsafe _double;
        public static final FqNameUnsafe _enum;
        public static final FqNameUnsafe _float;
        public static final FqNameUnsafe _int;
        public static final FqNameUnsafe _long;
        public static final FqNameUnsafe _short;
        public static final FqName annotation;
        public static final FqName annotationRetention;
        public static final FqName annotationTarget;
        public static final FqNameUnsafe any;
        public static final FqNameUnsafe array;
        public static final Map<FqNameUnsafe, PrimitiveType> arrayClassFqNameToPrimitiveType;
        public static final FqNameUnsafe charSequence;
        public static final FqNameUnsafe cloneable;
        public static final FqName collection;
        public static final FqName comparable;
        public static final FqName deprecated;
        public static final FqName deprecatedSinceKotlin;
        public static final FqName deprecationLevel;
        public static final FqName extensionFunctionType;
        public static final Map<FqNameUnsafe, PrimitiveType> fqNameToPrimitiveType;
        public static final FqNameUnsafe functionSupertype;
        public static final FqNameUnsafe intRange;
        public static final FqName iterable;
        public static final FqName iterator;
        public static final FqNameUnsafe kCallable;
        public static final FqNameUnsafe kClass;
        public static final FqNameUnsafe kDeclarationContainer;
        public static final FqNameUnsafe kMutableProperty0;
        public static final FqNameUnsafe kMutableProperty1;
        public static final FqNameUnsafe kMutableProperty2;
        public static final FqNameUnsafe kMutablePropertyFqName;
        public static final ClassId kProperty;
        public static final FqNameUnsafe kProperty0;
        public static final FqNameUnsafe kProperty1;
        public static final FqNameUnsafe kProperty2;
        public static final FqNameUnsafe kPropertyFqName;
        public static final FqName list;
        public static final FqName listIterator;
        public static final FqNameUnsafe longRange;
        public static final FqName map;
        public static final FqName mapEntry;
        public static final FqName mustBeDocumented;
        public static final FqName mutableCollection;
        public static final FqName mutableIterable;
        public static final FqName mutableIterator;
        public static final FqName mutableList;
        public static final FqName mutableListIterator;
        public static final FqName mutableMap;
        public static final FqName mutableMapEntry;
        public static final FqName mutableSet;
        public static final FqNameUnsafe nothing;
        public static final FqNameUnsafe number;
        public static final FqName parameterName;
        public static final Set<Name> primitiveArrayTypeShortNames;
        public static final Set<Name> primitiveTypeShortNames;
        public static final FqName publishedApi;
        public static final FqName repeatable;
        public static final FqName replaceWith;
        public static final FqName retention;
        public static final FqName set;
        public static final FqNameUnsafe string;
        public static final FqName suppress;
        public static final FqName target;
        public static final FqName throwable;
        public static final ClassId uByte;
        public static final FqName uByteFqName;
        public static final ClassId uInt;
        public static final FqName uIntFqName;
        public static final ClassId uLong;
        public static final FqName uLongFqName;
        public static final ClassId uShort;
        public static final FqName uShortFqName;
        public static final FqNameUnsafe unit;
        public static final FqName unsafeVariance;

        static {
            FqNames fqNames = new FqNames();
            INSTANCE = fqNames;
            any = fqNames.fqNameUnsafe("Any");
            nothing = fqNames.fqNameUnsafe("Nothing");
            cloneable = fqNames.fqNameUnsafe("Cloneable");
            suppress = fqNames.fqName("Suppress");
            unit = fqNames.fqNameUnsafe("Unit");
            charSequence = fqNames.fqNameUnsafe("CharSequence");
            string = fqNames.fqNameUnsafe("String");
            array = fqNames.fqNameUnsafe("Array");
            _boolean = fqNames.fqNameUnsafe("Boolean");
            _char = fqNames.fqNameUnsafe("Char");
            _byte = fqNames.fqNameUnsafe("Byte");
            _short = fqNames.fqNameUnsafe("Short");
            _int = fqNames.fqNameUnsafe("Int");
            _long = fqNames.fqNameUnsafe("Long");
            _float = fqNames.fqNameUnsafe("Float");
            _double = fqNames.fqNameUnsafe("Double");
            number = fqNames.fqNameUnsafe("Number");
            _enum = fqNames.fqNameUnsafe("Enum");
            functionSupertype = fqNames.fqNameUnsafe("Function");
            throwable = fqNames.fqName("Throwable");
            comparable = fqNames.fqName("Comparable");
            intRange = fqNames.rangesFqName("IntRange");
            longRange = fqNames.rangesFqName("LongRange");
            deprecated = fqNames.fqName("Deprecated");
            deprecatedSinceKotlin = fqNames.fqName("DeprecatedSinceKotlin");
            deprecationLevel = fqNames.fqName("DeprecationLevel");
            replaceWith = fqNames.fqName("ReplaceWith");
            extensionFunctionType = fqNames.fqName("ExtensionFunctionType");
            parameterName = fqNames.fqName("ParameterName");
            annotation = fqNames.fqName("Annotation");
            target = fqNames.annotationName("Target");
            annotationTarget = fqNames.annotationName("AnnotationTarget");
            annotationRetention = fqNames.annotationName("AnnotationRetention");
            retention = fqNames.annotationName("Retention");
            repeatable = fqNames.annotationName("Repeatable");
            mustBeDocumented = fqNames.annotationName("MustBeDocumented");
            unsafeVariance = fqNames.fqName("UnsafeVariance");
            publishedApi = fqNames.fqName("PublishedApi");
            iterator = fqNames.collectionsFqName("Iterator");
            iterable = fqNames.collectionsFqName("Iterable");
            collection = fqNames.collectionsFqName("Collection");
            list = fqNames.collectionsFqName("List");
            listIterator = fqNames.collectionsFqName("ListIterator");
            set = fqNames.collectionsFqName("Set");
            FqName fqNameCollectionsFqName = fqNames.collectionsFqName("Map");
            map = fqNameCollectionsFqName;
            FqName fqNameChild = fqNameCollectionsFqName.child(Name.identifier("Entry"));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "map.child(Name.identifier(\"Entry\"))");
            mapEntry = fqNameChild;
            mutableIterator = fqNames.collectionsFqName("MutableIterator");
            mutableIterable = fqNames.collectionsFqName("MutableIterable");
            mutableCollection = fqNames.collectionsFqName("MutableCollection");
            mutableList = fqNames.collectionsFqName("MutableList");
            mutableListIterator = fqNames.collectionsFqName("MutableListIterator");
            mutableSet = fqNames.collectionsFqName("MutableSet");
            FqName fqNameCollectionsFqName2 = fqNames.collectionsFqName("MutableMap");
            mutableMap = fqNameCollectionsFqName2;
            FqName fqNameChild2 = fqNameCollectionsFqName2.child(Name.identifier("MutableEntry"));
            Intrinsics.checkNotNullExpressionValue(fqNameChild2, "mutableMap.child(Name.identifier(\"MutableEntry\"))");
            mutableMapEntry = fqNameChild2;
            kClass = reflect("KClass");
            kCallable = reflect("KCallable");
            kProperty0 = reflect("KProperty0");
            kProperty1 = reflect("KProperty1");
            kProperty2 = reflect("KProperty2");
            kMutableProperty0 = reflect("KMutableProperty0");
            kMutableProperty1 = reflect("KMutableProperty1");
            kMutableProperty2 = reflect("KMutableProperty2");
            kPropertyFqName = reflect("KProperty");
            kMutablePropertyFqName = reflect("KMutableProperty");
            ClassId classId = ClassId.topLevel(kPropertyFqName.toSafe());
            Intrinsics.checkNotNullExpressionValue(classId, "ClassId.topLevel(kPropertyFqName.toSafe())");
            kProperty = classId;
            kDeclarationContainer = reflect("KDeclarationContainer");
            uByteFqName = fqNames.fqName("UByte");
            uShortFqName = fqNames.fqName("UShort");
            uIntFqName = fqNames.fqName("UInt");
            uLongFqName = fqNames.fqName("ULong");
            ClassId classId2 = ClassId.topLevel(uByteFqName);
            Intrinsics.checkNotNullExpressionValue(classId2, "ClassId.topLevel(uByteFqName)");
            uByte = classId2;
            ClassId classId3 = ClassId.topLevel(uShortFqName);
            Intrinsics.checkNotNullExpressionValue(classId3, "ClassId.topLevel(uShortFqName)");
            uShort = classId3;
            ClassId classId4 = ClassId.topLevel(uIntFqName);
            Intrinsics.checkNotNullExpressionValue(classId4, "ClassId.topLevel(uIntFqName)");
            uInt = classId4;
            ClassId classId5 = ClassId.topLevel(uLongFqName);
            Intrinsics.checkNotNullExpressionValue(classId5, "ClassId.topLevel(uLongFqName)");
            uLong = classId5;
            HashSet hashSetNewHashSetWithExpectedSize = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            for (PrimitiveType primitiveType : PrimitiveType.values()) {
                hashSetNewHashSetWithExpectedSize.add(primitiveType.getTypeName());
            }
            primitiveTypeShortNames = hashSetNewHashSetWithExpectedSize;
            HashSet hashSetNewHashSetWithExpectedSize2 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            for (PrimitiveType primitiveType2 : PrimitiveType.values()) {
                hashSetNewHashSetWithExpectedSize2.add(primitiveType2.getArrayTypeName());
            }
            primitiveArrayTypeShortNames = hashSetNewHashSetWithExpectedSize2;
            HashMap mapNewHashMapWithExpectedSize = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            for (PrimitiveType primitiveType3 : PrimitiveType.values()) {
                FqNames fqNames2 = INSTANCE;
                String strAsString = primitiveType3.getTypeName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "primitiveType.typeName.asString()");
                mapNewHashMapWithExpectedSize.put(fqNames2.fqNameUnsafe(strAsString), primitiveType3);
            }
            fqNameToPrimitiveType = mapNewHashMapWithExpectedSize;
            HashMap mapNewHashMapWithExpectedSize2 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            for (PrimitiveType primitiveType4 : PrimitiveType.values()) {
                FqNames fqNames3 = INSTANCE;
                String strAsString2 = primitiveType4.getArrayTypeName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString2, "primitiveType.arrayTypeName.asString()");
                mapNewHashMapWithExpectedSize2.put(fqNames3.fqNameUnsafe(strAsString2), primitiveType4);
            }
            arrayClassFqNameToPrimitiveType = mapNewHashMapWithExpectedSize2;
        }

        private FqNames() {
        }

        private final FqNameUnsafe fqNameUnsafe(String str) {
            FqNameUnsafe unsafe = fqName(str).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "fqName(simpleName).toUnsafe()");
            return unsafe;
        }

        private final FqName fqName(String str) {
            FqName fqNameChild = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(str));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "BUILT_INS_PACKAGE_FQ_NAM…e.identifier(simpleName))");
            return fqNameChild;
        }

        private final FqName collectionsFqName(String str) {
            FqName fqNameChild = StandardNames.COLLECTIONS_PACKAGE_FQ_NAME.child(Name.identifier(str));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "COLLECTIONS_PACKAGE_FQ_N…e.identifier(simpleName))");
            return fqNameChild;
        }

        private final FqNameUnsafe rangesFqName(String str) {
            FqNameUnsafe unsafe = StandardNames.RANGES_PACKAGE_FQ_NAME.child(Name.identifier(str)).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "RANGES_PACKAGE_FQ_NAME.c…r(simpleName)).toUnsafe()");
            return unsafe;
        }

        @JvmStatic
        public static final FqNameUnsafe reflect(String simpleName) {
            Intrinsics.checkNotNullParameter(simpleName, "simpleName");
            FqNameUnsafe unsafe = StandardNames.KOTLIN_REFLECT_FQ_NAME.child(Name.identifier(simpleName)).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "KOTLIN_REFLECT_FQ_NAME.c…r(simpleName)).toUnsafe()");
            return unsafe;
        }

        private final FqName annotationName(String str) {
            FqName fqNameChild = StandardNames.ANNOTATION_PACKAGE_FQ_NAME.child(Name.identifier(str));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "ANNOTATION_PACKAGE_FQ_NA…e.identifier(simpleName))");
            return fqNameChild;
        }
    }

    @JvmStatic
    public static final String getFunctionName(int i) {
        return "Function" + i;
    }

    @JvmStatic
    public static final ClassId getFunctionClassId(int i) {
        return new ClassId(BUILT_INS_PACKAGE_FQ_NAME, Name.identifier(getFunctionName(i)));
    }

    @JvmStatic
    public static final String getSuspendFunctionName(int i) {
        return FunctionClassKind.SuspendFunction.getClassNamePrefix() + i;
    }

    @JvmStatic
    public static final boolean isPrimitiveArray(FqNameUnsafe arrayFqName) {
        Intrinsics.checkNotNullParameter(arrayFqName, "arrayFqName");
        return FqNames.arrayClassFqNameToPrimitiveType.get(arrayFqName) != null;
    }

    @JvmStatic
    public static final FqName getPrimitiveFqName(PrimitiveType primitiveType) {
        Intrinsics.checkNotNullParameter(primitiveType, "primitiveType");
        FqName fqNameChild = BUILT_INS_PACKAGE_FQ_NAME.child(primitiveType.getTypeName());
        Intrinsics.checkNotNullExpressionValue(fqNameChild, "BUILT_INS_PACKAGE_FQ_NAM…d(primitiveType.typeName)");
        return fqNameChild;
    }
}
