package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ClassMapperLite;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractBinaryClassAnnotationAndConstantLoader<A, C> implements AnnotationAndConstantLoader<A, C> {
    public static final Companion Companion = new Companion(null);
    private static final Set<ClassId> SPECIAL_ANNOTATIONS;
    private final KotlinClassFinder kotlinClassFinder;
    private final MemoizedFunctionToNotNull<KotlinJvmBinaryClass, Storage<A, C>> storage;

    /* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    private enum PropertyRelatedElement {
        PROPERTY,
        BACKING_FIELD,
        DELEGATE_FIELD
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotatedCallableKind.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[AnnotatedCallableKind.PROPERTY_GETTER.ordinal()] = 1;
            $EnumSwitchMapping$0[AnnotatedCallableKind.PROPERTY_SETTER.ordinal()] = 2;
            $EnumSwitchMapping$0[AnnotatedCallableKind.PROPERTY.ordinal()] = 3;
        }
    }

    protected byte[] getCachedFileContent(KotlinJvmBinaryClass kotlinClass) {
        Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
        return null;
    }

    protected abstract KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotation(ClassId classId, SourceElement sourceElement, List<A> list);

    protected abstract C loadConstant(String str, Object obj);

    protected abstract A loadTypeAnnotation(ProtoBuf.Annotation annotation, NameResolver nameResolver);

    protected abstract C transformToUnsignedConstant(C c);

    public AbstractBinaryClassAnnotationAndConstantLoader(StorageManager storageManager, KotlinClassFinder kotlinClassFinder) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
        this.kotlinClassFinder = kotlinClassFinder;
        this.storage = storageManager.createMemoizedFunction(new Function1<KotlinJvmBinaryClass, Storage<? extends A, ? extends C>>() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$storage$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AbstractBinaryClassAnnotationAndConstantLoader.Storage<A, C> invoke(KotlinJvmBinaryClass kotlinClass) {
                Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
                return this.this$0.loadAnnotationsAndInitializers(kotlinClass);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotationIfNotSpecial(ClassId classId, SourceElement sourceElement, List<A> list) {
        if (SPECIAL_ANNOTATIONS.contains(classId)) {
            return null;
        }
        return loadAnnotation(classId, sourceElement, list);
    }

    private final KotlinJvmBinaryClass toBinaryClass(ProtoContainer.Class r3) {
        SourceElement source = r3.getSource();
        if (!(source instanceof KotlinJvmBinarySourceElement)) {
            source = null;
        }
        KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = (KotlinJvmBinarySourceElement) source;
        if (kotlinJvmBinarySourceElement != null) {
            return kotlinJvmBinarySourceElement.getBinaryClass();
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadClassAnnotations(ProtoContainer.Class container) {
        Intrinsics.checkNotNullParameter(container, "container");
        KotlinJvmBinaryClass binaryClass = toBinaryClass(container);
        if (binaryClass == null) {
            throw new IllegalStateException(("Class for loading annotations is not found: " + container.debugFqName()).toString());
        }
        final ArrayList arrayList = new ArrayList(1);
        binaryClass.loadClassAnnotations(new KotlinJvmBinaryClass.AnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadClassAnnotations.1
            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
            public void visitEnd() {
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
            public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId, SourceElement source) {
                Intrinsics.checkNotNullParameter(classId, "classId");
                Intrinsics.checkNotNullParameter(source, "source");
                return AbstractBinaryClassAnnotationAndConstantLoader.this.loadAnnotationIfNotSpecial(classId, source, arrayList);
            }
        }, getCachedFileContent(binaryClass));
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadCallableAnnotations(ProtoContainer container, MessageLite proto, AnnotatedCallableKind kind) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (kind == AnnotatedCallableKind.PROPERTY) {
            return loadPropertyAnnotations(container, (ProtoBuf.Property) proto, PropertyRelatedElement.PROPERTY);
        }
        MemberSignature callableSignature$default = getCallableSignature$default(this, proto, container.getNameResolver(), container.getTypeTable(), kind, false, 16, null);
        if (callableSignature$default == null) {
            return CollectionsKt.emptyList();
        }
        return findClassAndLoadMemberAnnotations$default(this, container, callableSignature$default, false, false, null, false, 60, null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadPropertyBackingFieldAnnotations(ProtoContainer container, ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return loadPropertyAnnotations(container, proto, PropertyRelatedElement.BACKING_FIELD);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadPropertyDelegateFieldAnnotations(ProtoContainer container, ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        return loadPropertyAnnotations(container, proto, PropertyRelatedElement.DELEGATE_FIELD);
    }

    private final List<A> loadPropertyAnnotations(ProtoContainer protoContainer, ProtoBuf.Property property, PropertyRelatedElement propertyRelatedElement) {
        Boolean bool = Flags.IS_CONST.get(property.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "Flags.IS_CONST.get(proto.flags)");
        boolean zBooleanValue = bool.booleanValue();
        boolean zIsMovedFromInterfaceCompanion = JvmProtoBufUtil.isMovedFromInterfaceCompanion(property);
        if (propertyRelatedElement == PropertyRelatedElement.PROPERTY) {
            MemberSignature propertySignature$default = getPropertySignature$default(this, property, protoContainer.getNameResolver(), protoContainer.getTypeTable(), false, true, false, 40, null);
            if (propertySignature$default == null) {
                return CollectionsKt.emptyList();
            }
            return findClassAndLoadMemberAnnotations$default(this, protoContainer, propertySignature$default, true, false, Boolean.valueOf(zBooleanValue), zIsMovedFromInterfaceCompanion, 8, null);
        }
        MemberSignature propertySignature$default2 = getPropertySignature$default(this, property, protoContainer.getNameResolver(), protoContainer.getTypeTable(), true, false, false, 48, null);
        if (propertySignature$default2 != null) {
            return StringsKt.contains$default((CharSequence) propertySignature$default2.getSignature(), (CharSequence) "$delegate", false, 2, (Object) null) != (propertyRelatedElement == PropertyRelatedElement.DELEGATE_FIELD) ? CollectionsKt.emptyList() : findClassAndLoadMemberAnnotations(protoContainer, propertySignature$default2, true, true, Boolean.valueOf(zBooleanValue), zIsMovedFromInterfaceCompanion);
        }
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadEnumEntryAnnotations(ProtoContainer container, ProtoBuf.EnumEntry proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        MemberSignature.Companion companion = MemberSignature.Companion;
        String string = container.getNameResolver().getString(proto.getName());
        String strAsString = ((ProtoContainer.Class) container).getClassId().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "(container as ProtoConta…Class).classId.asString()");
        return findClassAndLoadMemberAnnotations$default(this, container, companion.fromFieldNameAndDesc(string, ClassMapperLite.mapClass(strAsString)), false, false, null, false, 60, null);
    }

    static /* synthetic */ List findClassAndLoadMemberAnnotations$default(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3, int i, Object obj) {
        if (obj == null) {
            return abstractBinaryClassAnnotationAndConstantLoader.findClassAndLoadMemberAnnotations(protoContainer, memberSignature, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? (Boolean) null : bool, (i & 32) != 0 ? false : z3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findClassAndLoadMemberAnnotations");
    }

    private final List<A> findClassAndLoadMemberAnnotations(ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3) {
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers = findClassWithAnnotationsAndInitializers(protoContainer, getSpecialCaseContainerClass(protoContainer, z, z2, bool, z3));
        if (kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers == null) {
            return CollectionsKt.emptyList();
        }
        List<A> list = this.storage.invoke(kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers).getMemberAnnotations().get(memberSignature);
        return list != null ? list : CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadValueParameterAnnotations(ProtoContainer container, MessageLite callableProto, AnnotatedCallableKind kind, int i, ProtoBuf.ValueParameter proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(callableProto, "callableProto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(proto, "proto");
        MemberSignature callableSignature$default = getCallableSignature$default(this, callableProto, container.getNameResolver(), container.getTypeTable(), kind, false, 16, null);
        if (callableSignature$default != null) {
            return findClassAndLoadMemberAnnotations$default(this, container, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature$default, i + computeJvmParameterIndexShift(container, callableProto)), false, false, null, false, 60, null);
        }
        return CollectionsKt.emptyList();
    }

    private final int computeJvmParameterIndexShift(ProtoContainer protoContainer, MessageLite messageLite) {
        if (messageLite instanceof ProtoBuf.Function) {
            if (ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Function) messageLite)) {
                return 1;
            }
        } else if (messageLite instanceof ProtoBuf.Property) {
            if (ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Property) messageLite)) {
                return 1;
            }
        } else {
            if (!(messageLite instanceof ProtoBuf.Constructor)) {
                throw new UnsupportedOperationException("Unsupported message: " + messageLite.getClass());
            }
            if (protoContainer != null) {
                ProtoContainer.Class r4 = (ProtoContainer.Class) protoContainer;
                if (r4.getKind() == ProtoBuf.Class.Kind.ENUM_CLASS) {
                    return 2;
                }
                if (r4.isInner()) {
                    return 1;
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.ProtoContainer.Class");
            }
        }
        return 0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadExtensionReceiverParameterAnnotations(ProtoContainer container, MessageLite proto, AnnotatedCallableKind kind) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        MemberSignature callableSignature$default = getCallableSignature$default(this, proto, container.getNameResolver(), container.getTypeTable(), kind, false, 16, null);
        if (callableSignature$default != null) {
            return findClassAndLoadMemberAnnotations$default(this, container, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature$default, 0), false, false, null, false, 60, null);
        }
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadTypeAnnotations(ProtoBuf.Type proto, NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Object extension = proto.getExtension(JvmProtoBuf.typeAnnotation);
        Intrinsics.checkNotNullExpressionValue(extension, "proto.getExtension(JvmProtoBuf.typeAnnotation)");
        Iterable<ProtoBuf.Annotation> iterable = (Iterable) extension;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ProtoBuf.Annotation it : iterable) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            arrayList.add(loadTypeAnnotation(it, nameResolver));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public List<A> loadTypeParameterAnnotations(ProtoBuf.TypeParameter proto, NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Object extension = proto.getExtension(JvmProtoBuf.typeParameterAnnotation);
        Intrinsics.checkNotNullExpressionValue(extension, "proto.getExtension(JvmPr….typeParameterAnnotation)");
        Iterable<ProtoBuf.Annotation> iterable = (Iterable) extension;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ProtoBuf.Annotation it : iterable) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            arrayList.add(loadTypeAnnotation(it, nameResolver));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public C loadPropertyConstant(ProtoContainer container, ProtoBuf.Property proto, KotlinType expectedType) {
        C c;
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(expectedType, "expectedType");
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers = findClassWithAnnotationsAndInitializers(container, getSpecialCaseContainerClass(container, true, true, Flags.IS_CONST.get(proto.getFlags()), JvmProtoBufUtil.isMovedFromInterfaceCompanion(proto)));
        if (kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers != null) {
            MemberSignature callableSignature = getCallableSignature(proto, container.getNameResolver(), container.getTypeTable(), AnnotatedCallableKind.PROPERTY, kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers.getClassHeader().getMetadataVersion().isAtLeast(DeserializedDescriptorResolver.Companion.getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm()));
            if (callableSignature != null && (c = this.storage.invoke(kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers).getPropertyConstants().get(callableSignature)) != null) {
                return UnsignedTypes.isUnsignedType(expectedType) ? transformToUnsignedConstant(c) : c;
            }
        }
        return null;
    }

    private final KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers(ProtoContainer protoContainer, KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (kotlinJvmBinaryClass != null) {
            return kotlinJvmBinaryClass;
        }
        if (protoContainer instanceof ProtoContainer.Class) {
            return toBinaryClass((ProtoContainer.Class) protoContainer);
        }
        return null;
    }

    private final KotlinJvmBinaryClass getSpecialCaseContainerClass(ProtoContainer protoContainer, boolean z, boolean z2, Boolean bool, boolean z3) {
        ProtoContainer.Class outerClass;
        if (z) {
            if (bool == null) {
                throw new IllegalStateException(("isConst should not be null for property (container=" + protoContainer + ')').toString());
            }
            if (protoContainer instanceof ProtoContainer.Class) {
                ProtoContainer.Class r8 = (ProtoContainer.Class) protoContainer;
                if (r8.getKind() == ProtoBuf.Class.Kind.INTERFACE) {
                    KotlinClassFinder kotlinClassFinder = this.kotlinClassFinder;
                    ClassId classIdCreateNestedClassId = r8.getClassId().createNestedClassId(Name.identifier("DefaultImpls"));
                    Intrinsics.checkNotNullExpressionValue(classIdCreateNestedClassId, "container.classId.create…EFAULT_IMPLS_CLASS_NAME))");
                    return KotlinClassFinderKt.findKotlinClass(kotlinClassFinder, classIdCreateNestedClassId);
                }
            }
            if (bool.booleanValue() && (protoContainer instanceof ProtoContainer.Package)) {
                SourceElement source = protoContainer.getSource();
                if (!(source instanceof JvmPackagePartSource)) {
                    source = null;
                }
                JvmPackagePartSource jvmPackagePartSource = (JvmPackagePartSource) source;
                JvmClassName facadeClassName = jvmPackagePartSource != null ? jvmPackagePartSource.getFacadeClassName() : null;
                if (facadeClassName != null) {
                    KotlinClassFinder kotlinClassFinder2 = this.kotlinClassFinder;
                    String internalName = facadeClassName.getInternalName();
                    Intrinsics.checkNotNullExpressionValue(internalName, "facadeClassName.internalName");
                    ClassId classId = ClassId.topLevel(new FqName(StringsKt.replace$default(internalName, '/', '.', false, 4, (Object) null)));
                    Intrinsics.checkNotNullExpressionValue(classId, "ClassId.topLevel(FqName(…lName.replace('/', '.')))");
                    return KotlinClassFinderKt.findKotlinClass(kotlinClassFinder2, classId);
                }
            }
        }
        if (z2 && (protoContainer instanceof ProtoContainer.Class)) {
            ProtoContainer.Class r9 = (ProtoContainer.Class) protoContainer;
            if (r9.getKind() == ProtoBuf.Class.Kind.COMPANION_OBJECT && (outerClass = r9.getOuterClass()) != null && (outerClass.getKind() == ProtoBuf.Class.Kind.CLASS || outerClass.getKind() == ProtoBuf.Class.Kind.ENUM_CLASS || (z3 && (outerClass.getKind() == ProtoBuf.Class.Kind.INTERFACE || outerClass.getKind() == ProtoBuf.Class.Kind.ANNOTATION_CLASS)))) {
                return toBinaryClass(outerClass);
            }
        }
        if (!(protoContainer instanceof ProtoContainer.Package) || !(protoContainer.getSource() instanceof JvmPackagePartSource)) {
            return null;
        }
        SourceElement source2 = protoContainer.getSource();
        if (source2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource");
        }
        JvmPackagePartSource jvmPackagePartSource2 = (JvmPackagePartSource) source2;
        KotlinJvmBinaryClass knownJvmBinaryClass = jvmPackagePartSource2.getKnownJvmBinaryClass();
        return knownJvmBinaryClass != null ? knownJvmBinaryClass : KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, jvmPackagePartSource2.getClassId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Storage<A, C> loadAnnotationsAndInitializers(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        final HashMap map = new HashMap();
        final HashMap map2 = new HashMap();
        kotlinJvmBinaryClass.visitMembers(new KotlinJvmBinaryClass.MemberVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers.1
            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor
            public KotlinJvmBinaryClass.MethodAnnotationVisitor visitMethod(Name name, String desc) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(desc, "desc");
                MemberSignature.Companion companion = MemberSignature.Companion;
                String strAsString = name.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
                return new AnnotationVisitorForMethod(this, companion.fromMethodNameAndDesc(strAsString, desc));
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor
            public KotlinJvmBinaryClass.AnnotationVisitor visitField(Name name, String desc, Object obj) {
                Object objLoadConstant;
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(desc, "desc");
                MemberSignature.Companion companion = MemberSignature.Companion;
                String strAsString = name.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
                MemberSignature memberSignatureFromFieldNameAndDesc = companion.fromFieldNameAndDesc(strAsString, desc);
                if (obj != null && (objLoadConstant = AbstractBinaryClassAnnotationAndConstantLoader.this.loadConstant(desc, obj)) != null) {
                    map2.put(memberSignatureFromFieldNameAndDesc, objLoadConstant);
                }
                return new MemberAnnotationVisitor(this, memberSignatureFromFieldNameAndDesc);
            }

            /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1$AnnotationVisitorForMethod */
            /* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
            public final class AnnotationVisitorForMethod extends MemberAnnotationVisitor implements KotlinJvmBinaryClass.MethodAnnotationVisitor {
                final /* synthetic */ AnonymousClass1 this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnnotationVisitorForMethod(AnonymousClass1 anonymousClass1, MemberSignature signature) {
                    super(anonymousClass1, signature);
                    Intrinsics.checkNotNullParameter(signature, "signature");
                    this.this$0 = anonymousClass1;
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MethodAnnotationVisitor
                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitParameterAnnotation(int i, ClassId classId, SourceElement source) {
                    Intrinsics.checkNotNullParameter(classId, "classId");
                    Intrinsics.checkNotNullParameter(source, "source");
                    MemberSignature memberSignatureFromMethodSignatureAndParameterIndex = MemberSignature.Companion.fromMethodSignatureAndParameterIndex(getSignature(), i);
                    ArrayList arrayList = (List) map.get(memberSignatureFromMethodSignatureAndParameterIndex);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        map.put(memberSignatureFromMethodSignatureAndParameterIndex, arrayList);
                    }
                    return AbstractBinaryClassAnnotationAndConstantLoader.this.loadAnnotationIfNotSpecial(classId, source, arrayList);
                }
            }

            /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1$MemberAnnotationVisitor */
            /* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
            public class MemberAnnotationVisitor implements KotlinJvmBinaryClass.AnnotationVisitor {
                private final ArrayList<A> result;
                private final MemberSignature signature;
                final /* synthetic */ AnonymousClass1 this$0;

                public MemberAnnotationVisitor(AnonymousClass1 anonymousClass1, MemberSignature signature) {
                    Intrinsics.checkNotNullParameter(signature, "signature");
                    this.this$0 = anonymousClass1;
                    this.signature = signature;
                    this.result = new ArrayList<>();
                }

                protected final MemberSignature getSignature() {
                    return this.signature;
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId, SourceElement source) {
                    Intrinsics.checkNotNullParameter(classId, "classId");
                    Intrinsics.checkNotNullParameter(source, "source");
                    return AbstractBinaryClassAnnotationAndConstantLoader.this.loadAnnotationIfNotSpecial(classId, source, this.result);
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
                public void visitEnd() {
                    if (!this.result.isEmpty()) {
                        map.put(this.signature, this.result);
                    }
                }
            }
        }, getCachedFileContent(kotlinJvmBinaryClass));
        return new Storage<>(map, map2);
    }

    static /* synthetic */ MemberSignature getPropertySignature$default(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, ProtoBuf.Property property, NameResolver nameResolver, TypeTable typeTable, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if (obj == null) {
            return abstractBinaryClassAnnotationAndConstantLoader.getPropertySignature(property, nameResolver, typeTable, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? true : z3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPropertySignature");
    }

    private final MemberSignature getPropertySignature(ProtoBuf.Property property, NameResolver nameResolver, TypeTable typeTable, boolean z, boolean z2, boolean z3) {
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> propertySignature = JvmProtoBuf.propertySignature;
        Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
        JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(property, propertySignature);
        if (jvmPropertySignature != null) {
            if (z) {
                JvmMemberSignature.Field jvmFieldSignature = JvmProtoBufUtil.INSTANCE.getJvmFieldSignature(property, nameResolver, typeTable, z3);
                if (jvmFieldSignature != null) {
                    return MemberSignature.Companion.fromJvmMemberSignature(jvmFieldSignature);
                }
                return null;
            }
            if (z2 && jvmPropertySignature.hasSyntheticMethod()) {
                MemberSignature.Companion companion = MemberSignature.Companion;
                JvmProtoBuf.JvmMethodSignature syntheticMethod = jvmPropertySignature.getSyntheticMethod();
                Intrinsics.checkNotNullExpressionValue(syntheticMethod, "signature.syntheticMethod");
                return companion.fromMethod(nameResolver, syntheticMethod);
            }
        }
        return null;
    }

    static /* synthetic */ MemberSignature getCallableSignature$default(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, MessageLite messageLite, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind annotatedCallableKind, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCallableSignature");
        }
        if ((i & 16) != 0) {
            z = false;
        }
        return abstractBinaryClassAnnotationAndConstantLoader.getCallableSignature(messageLite, nameResolver, typeTable, annotatedCallableKind, z);
    }

    private final MemberSignature getCallableSignature(MessageLite messageLite, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind annotatedCallableKind, boolean z) {
        if (messageLite instanceof ProtoBuf.Constructor) {
            MemberSignature.Companion companion = MemberSignature.Companion;
            JvmMemberSignature.Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((ProtoBuf.Constructor) messageLite, nameResolver, typeTable);
            if (jvmConstructorSignature != null) {
                return companion.fromJvmMemberSignature(jvmConstructorSignature);
            }
            return null;
        }
        if (messageLite instanceof ProtoBuf.Function) {
            MemberSignature.Companion companion2 = MemberSignature.Companion;
            JvmMemberSignature.Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((ProtoBuf.Function) messageLite, nameResolver, typeTable);
            if (jvmMethodSignature != null) {
                return companion2.fromJvmMemberSignature(jvmMethodSignature);
            }
            return null;
        }
        if (!(messageLite instanceof ProtoBuf.Property)) {
            return null;
        }
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> propertySignature = JvmProtoBuf.propertySignature;
        Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
        JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull((GeneratedMessageLite.ExtendableMessage) messageLite, propertySignature);
        if (jvmPropertySignature == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[annotatedCallableKind.ordinal()];
        if (i == 1) {
            if (!jvmPropertySignature.hasGetter()) {
                return null;
            }
            MemberSignature.Companion companion3 = MemberSignature.Companion;
            JvmProtoBuf.JvmMethodSignature getter = jvmPropertySignature.getGetter();
            Intrinsics.checkNotNullExpressionValue(getter, "signature.getter");
            return companion3.fromMethod(nameResolver, getter);
        }
        if (i != 2) {
            if (i != 3) {
                return null;
            }
            return getPropertySignature((ProtoBuf.Property) messageLite, nameResolver, typeTable, true, true, z);
        }
        if (!jvmPropertySignature.hasSetter()) {
            return null;
        }
        MemberSignature.Companion companion4 = MemberSignature.Companion;
        JvmProtoBuf.JvmMethodSignature setter = jvmPropertySignature.getSetter();
        Intrinsics.checkNotNullExpressionValue(setter, "signature.setter");
        return companion4.fromMethod(nameResolver, setter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    static final class Storage<A, C> {
        private final Map<MemberSignature, List<A>> memberAnnotations;
        private final Map<MemberSignature, C> propertyConstants;

        /* JADX WARN: Multi-variable type inference failed */
        public Storage(Map<MemberSignature, ? extends List<? extends A>> memberAnnotations, Map<MemberSignature, ? extends C> propertyConstants) {
            Intrinsics.checkNotNullParameter(memberAnnotations, "memberAnnotations");
            Intrinsics.checkNotNullParameter(propertyConstants, "propertyConstants");
            this.memberAnnotations = memberAnnotations;
            this.propertyConstants = propertyConstants;
        }

        public final Map<MemberSignature, List<A>> getMemberAnnotations() {
            return this.memberAnnotations;
        }

        public final Map<MemberSignature, C> getPropertyConstants() {
            return this.propertyConstants;
        }
    }

    /* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List listListOf = CollectionsKt.listOf((Object[]) new FqName[]{JvmAnnotationNames.METADATA_FQ_NAME, JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION, JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION, new FqName("java.lang.annotation.Target"), new FqName("java.lang.annotation.Retention"), new FqName("java.lang.annotation.Documented")});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            arrayList.add(ClassId.topLevel((FqName) it.next()));
        }
        SPECIAL_ANNOTATIONS = CollectionsKt.toSet(arrayList);
    }
}
