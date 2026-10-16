package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Iterator;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;

/* JADX INFO: compiled from: ClassDeserializer.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ClassDeserializer {
    private final Function1<ClassKey, ClassDescriptor> classes;
    private final DeserializationComponents components;
    public static final Companion Companion = new Companion(null);
    private static final Set<ClassId> BLACK_LIST = SetsKt.setOf(ClassId.topLevel(StandardNames.FqNames.cloneable.toSafe()));

    public ClassDeserializer(DeserializationComponents components) {
        Intrinsics.checkNotNullParameter(components, "components");
        this.components = components;
        this.classes = components.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<ClassKey, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer$classes$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ClassDescriptor invoke(ClassDeserializer.ClassKey key) {
                Intrinsics.checkNotNullParameter(key, "key");
                return this.this$0.createClass(key);
            }
        });
    }

    public static /* synthetic */ ClassDescriptor deserializeClass$default(ClassDeserializer classDeserializer, ClassId classId, ClassData classData, int i, Object obj) {
        if ((i & 2) != 0) {
            classData = (ClassData) null;
        }
        return classDeserializer.deserializeClass(classId, classData);
    }

    public final ClassDescriptor deserializeClass(ClassId classId, ClassData classData) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        return this.classes.invoke(new ClassKey(classId, classData));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:38:0x00b6  */
    public final ClassDescriptor createClass(ClassKey classKey) {
        Object next;
        DeserializationContext deserializationContextCreateContext;
        boolean z;
        ClassId classId = classKey.getClassId();
        Iterator<ClassDescriptorFactory> it = this.components.getFictitiousClassDescriptorFactories().iterator();
        while (it.hasNext()) {
            ClassDescriptor classDescriptorCreateClass = it.next().createClass(classId);
            if (classDescriptorCreateClass != null) {
                return classDescriptorCreateClass;
            }
        }
        if (BLACK_LIST.contains(classId)) {
            return null;
        }
        ClassData classData = classKey.getClassData();
        if (classData == null) {
            classData = this.components.getClassDataFinder().findClassData(classId);
        }
        if (classData != null) {
            NameResolver nameResolverComponent1 = classData.component1();
            ProtoBuf.Class classComponent2 = classData.component2();
            BinaryVersion binaryVersionComponent3 = classData.component3();
            SourceElement sourceElementComponent4 = classData.component4();
            ClassId outerClassId = classId.getOuterClassId();
            if (outerClassId != null) {
                ClassDescriptor classDescriptorDeserializeClass$default = deserializeClass$default(this, outerClassId, null, 2, null);
                if (!(classDescriptorDeserializeClass$default instanceof DeserializedClassDescriptor)) {
                    classDescriptorDeserializeClass$default = null;
                }
                DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) classDescriptorDeserializeClass$default;
                if (deserializedClassDescriptor == null) {
                    return null;
                }
                Name shortClassName = classId.getShortClassName();
                Intrinsics.checkNotNullExpressionValue(shortClassName, "classId.shortClassName");
                if (!deserializedClassDescriptor.hasNestedClass$deserialization(shortClassName)) {
                    return null;
                }
                deserializationContextCreateContext = deserializedClassDescriptor.getC();
            } else {
                PackageFragmentProvider packageFragmentProvider = this.components.getPackageFragmentProvider();
                FqName packageFqName = classId.getPackageFqName();
                Intrinsics.checkNotNullExpressionValue(packageFqName, "classId.packageFqName");
                Iterator<T> it2 = PackageFragmentProviderKt.packageFragments(packageFragmentProvider, packageFqName).iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                    PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) next;
                    if (packageFragmentDescriptor instanceof DeserializedPackageFragment) {
                        Name shortClassName2 = classId.getShortClassName();
                        Intrinsics.checkNotNullExpressionValue(shortClassName2, "classId.shortClassName");
                        if (((DeserializedPackageFragment) packageFragmentDescriptor).hasTopLevelClass(shortClassName2)) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } else {
                        z = true;
                    }
                } while (!z);
                PackageFragmentDescriptor packageFragmentDescriptor2 = (PackageFragmentDescriptor) next;
                if (packageFragmentDescriptor2 != null) {
                    DeserializationComponents deserializationComponents = this.components;
                    ProtoBuf.TypeTable typeTable = classComponent2.getTypeTable();
                    Intrinsics.checkNotNullExpressionValue(typeTable, "classProto.typeTable");
                    TypeTable typeTable2 = new TypeTable(typeTable);
                    VersionRequirementTable.Companion companion = VersionRequirementTable.Companion;
                    ProtoBuf.VersionRequirementTable versionRequirementTable = classComponent2.getVersionRequirementTable();
                    Intrinsics.checkNotNullExpressionValue(versionRequirementTable, "classProto.versionRequirementTable");
                    deserializationContextCreateContext = deserializationComponents.createContext(packageFragmentDescriptor2, nameResolverComponent1, typeTable2, companion.create(versionRequirementTable), binaryVersionComponent3, null);
                }
            }
            return new DeserializedClassDescriptor(deserializationContextCreateContext, classComponent2, nameResolverComponent1, binaryVersionComponent3, sourceElementComponent4);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ClassDeserializer.kt */
    static final class ClassKey {
        private final ClassData classData;
        private final ClassId classId;

        public ClassKey(ClassId classId, ClassData classData) {
            Intrinsics.checkNotNullParameter(classId, "classId");
            this.classId = classId;
            this.classData = classData;
        }

        public final ClassData getClassData() {
            return this.classData;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ClassKey) && Intrinsics.areEqual(this.classId, ((ClassKey) obj).classId);
        }

        public int hashCode() {
            return this.classId.hashCode();
        }
    }

    /* JADX INFO: compiled from: ClassDeserializer.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Set<ClassId> getBLACK_LIST() {
            return ClassDeserializer.BLACK_LIST;
        }
    }
}
