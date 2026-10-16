package kotlin.reflect.jvm.internal;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KDeclarationContainerImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\n\b \u0018\u0000 <2\u00020\u0001:\u0003<=>B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\f\u001a\u00020\r2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0014\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00152\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0016\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00152\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0013J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00042\u0006\u0010\u0019\u001a\u00020\"H&J\u0012\u0010#\u001a\u0004\u0018\u00010 2\u0006\u0010$\u001a\u00020%H&J\"\u0010&\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0\u00042\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0004J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020 0\u00042\u0006\u0010\u0019\u001a\u00020\"H&J\u001a\u0010-\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0.2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0014\u0010/\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J$\u00100\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00101\u001a\u00020%2\u0006\u00102\u001a\u00020%H\u0002JE\u00103\u001a\u0004\u0018\u00010\u0018*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0019\u001a\u00020\u00112\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t052\n\u00106\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u00107\u001a\u00020\u0013H\u0002¢\u0006\u0002\u00108J(\u00109\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0015*\u0006\u0012\u0002\b\u00030\t2\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0.H\u0002J=\u0010:\u001a\u0004\u0018\u00010\u0018*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0019\u001a\u00020\u00112\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t052\n\u00106\u001a\u0006\u0012\u0002\b\u00030\tH\u0002¢\u0006\u0002\u0010;R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006?"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "()V", "constructorDescriptors", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "()Ljava/util/Collection;", "methodOwner", "Ljava/lang/Class;", "getMethodOwner", "()Ljava/lang/Class;", "addParametersAndMasks", "", "result", "", "desc", "", "isConstructor", "", "findConstructorBySignature", "Ljava/lang/reflect/Constructor;", "findDefaultConstructor", "findDefaultMethod", "Ljava/lang/reflect/Method;", "name", "isMember", "findFunctionDescriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "signature", "findMethodBySignature", "findPropertyDescriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getLocalProperty", "index", "", "getMembers", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "scope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "belonginess", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess;", "getProperties", "loadParameterTypes", "", "loadReturnType", "parseType", "begin", TtmlNode.END, "lookupMethod", "parameterTypes", "", "returnType", "isStaticDefault", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Ljava/lang/Class;Z)Ljava/lang/reflect/Method;", "tryGetConstructor", "tryGetMethod", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Ljava/lang/Class;)Ljava/lang/reflect/Method;", "Companion", "Data", "MemberBelonginess", "kotlin-reflection"}, k = 1, mv = {1, 4, 0})
public abstract class KDeclarationContainerImpl implements ClassBasedDeclarationContainer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Class<?> DEFAULT_CONSTRUCTOR_MARKER = Class.forName("kotlin.jvm.internal.DefaultConstructorMarker");
    private static final Regex LOCAL_PROPERTY_SIGNATURE = new Regex("<v#(\\d+)>");

    public abstract Collection<ConstructorDescriptor> getConstructorDescriptors();

    public abstract Collection<FunctionDescriptor> getFunctions(Name name);

    public abstract PropertyDescriptor getLocalProperty(int index);

    public abstract Collection<PropertyDescriptor> getProperties(Name name);

    /* JADX INFO: compiled from: KDeclarationContainerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b¦\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data;", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;)V", "moduleData", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeModuleData;", "moduleData$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "kotlin-reflection"}, k = 1, mv = {1, 4, 0})
    public abstract class Data {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "moduleData", "getModuleData()Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeModuleData;"))};

        /* JADX INFO: renamed from: moduleData$delegate, reason: from kotlin metadata */
        private final ReflectProperties.LazySoftVal moduleData = ReflectProperties.lazySoft(new Function0<RuntimeModuleData>() { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$Data$moduleData$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final RuntimeModuleData invoke() {
                return ModuleByClassLoaderKt.getOrCreateModule(KDeclarationContainerImpl.this.getJClass());
            }
        });

        /* JADX WARN: Multi-variable type inference failed */
        public final RuntimeModuleData getModuleData() {
            return (RuntimeModuleData) this.moduleData.getValue(this, $$delegatedProperties[0]);
        }

        public Data() {
        }
    }

    protected Class<?> getMethodOwner() {
        Class<?> wrapperByPrimitive = ReflectClassUtilKt.getWrapperByPrimitive(getJClass());
        return wrapperByPrimitive != null ? wrapperByPrimitive : getJClass();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0057  */
    protected final Collection<KCallableImpl<?>> getMembers(MemberScope scope, MemberBelonginess belonginess) {
        KCallableImpl kCallableImpl;
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(belonginess, "belonginess");
        CreateKCallableVisitor createKCallableVisitor = new CreateKCallableVisitor(this) { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$getMembers$visitor$1
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
            public KCallableImpl<?> visitConstructorDescriptor(ConstructorDescriptor descriptor, Unit data) {
                Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                Intrinsics.checkNotNullParameter(data, "data");
                throw new IllegalStateException("No constructors should appear here: " + descriptor);
            }
        };
        Collection<DeclarationDescriptor> contributedDescriptors$default = ResolutionScope.DefaultImpls.getContributedDescriptors$default(scope, null, null, 3, null);
        ArrayList arrayList = new ArrayList();
        for (DeclarationDescriptor declarationDescriptor : contributedDescriptors$default) {
            if (declarationDescriptor instanceof CallableMemberDescriptor) {
                CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) declarationDescriptor;
                if ((!Intrinsics.areEqual(callableMemberDescriptor.getVisibility(), DescriptorVisibilities.INVISIBLE_FAKE)) && belonginess.accept(callableMemberDescriptor)) {
                    kCallableImpl = (KCallableImpl) declarationDescriptor.accept(createKCallableVisitor, Unit.INSTANCE);
                } else {
                    kCallableImpl = null;
                }
            } else {
                kCallableImpl = null;
            }
            if (kCallableImpl != null) {
                arrayList.add(kCallableImpl);
            }
        }
        return CollectionsKt.toList(arrayList);
    }

    /* JADX INFO: compiled from: KDeclarationContainerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0084\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess;", "", "(Ljava/lang/String;I)V", "accept", "", "member", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "DECLARED", "INHERITED", "kotlin-reflection"}, k = 1, mv = {1, 4, 0})
    protected enum MemberBelonginess {
        DECLARED,
        INHERITED;

        public final boolean accept(CallableMemberDescriptor member) {
            Intrinsics.checkNotNullParameter(member, "member");
            CallableMemberDescriptor.Kind kind = member.getKind();
            Intrinsics.checkNotNullExpressionValue(kind, "member.kind");
            return kind.isReal() == (this == DECLARED);
        }
    }

    public final PropertyDescriptor findPropertyDescriptor(String name, String signature) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        MatchResult matchResultMatchEntire = LOCAL_PROPERTY_SIGNATURE.matchEntire(signature);
        if (matchResultMatchEntire != null) {
            String str = matchResultMatchEntire.getDestructured().getMatch().getGroupValues().get(1);
            PropertyDescriptor localProperty = getLocalProperty(Integer.parseInt(str));
            if (localProperty != null) {
                return localProperty;
            }
            throw new KotlinReflectionInternalError("Local property #" + str + " not found in " + getJClass());
        }
        Name nameIdentifier = Name.identifier(name);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(name)");
        Collection<PropertyDescriptor> properties = getProperties(nameIdentifier);
        ArrayList arrayList = new ArrayList();
        for (Object obj : properties) {
            if (Intrinsics.areEqual(RuntimeTypeMapper.INSTANCE.mapPropertySignature((PropertyDescriptor) obj).getString(), signature)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            throw new KotlinReflectionInternalError("Property '" + name + "' (JVM signature: " + signature + ") not resolved in " + this);
        }
        if (arrayList2.size() == 1) {
            return (PropertyDescriptor) CollectionsKt.single((List) arrayList2);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : arrayList2) {
            DescriptorVisibility visibility = ((PropertyDescriptor) obj2).getVisibility();
            Object obj3 = linkedHashMap.get(visibility);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                linkedHashMap.put(visibility, obj3);
            }
            ((List) obj3).add(obj2);
        }
        Collection collectionValues = MapsKt.toSortedMap(linkedHashMap, new Comparator<DescriptorVisibility>() { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$findPropertyDescriptor$mostVisibleProperties$2
            @Override // java.util.Comparator
            public final int compare(DescriptorVisibility descriptorVisibility, DescriptorVisibility descriptorVisibility2) {
                Integer numCompare = DescriptorVisibilities.compare(descriptorVisibility, descriptorVisibility2);
                if (numCompare != null) {
                    return numCompare.intValue();
                }
                return 0;
            }
        }).values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "properties\n             …                }).values");
        List mostVisibleProperties = (List) CollectionsKt.last(collectionValues);
        if (mostVisibleProperties.size() == 1) {
            Intrinsics.checkNotNullExpressionValue(mostVisibleProperties, "mostVisibleProperties");
            return (PropertyDescriptor) CollectionsKt.first(mostVisibleProperties);
        }
        Name nameIdentifier2 = Name.identifier(name);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "Name.identifier(name)");
        String strJoinToString$default = CollectionsKt.joinToString$default(getProperties(nameIdentifier2), "\n", null, null, 0, null, new Function1<PropertyDescriptor, CharSequence>() { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(PropertyDescriptor descriptor) {
                Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                return DescriptorRenderer.DEBUG_TEXT.render(descriptor) + " | " + RuntimeTypeMapper.INSTANCE.mapPropertySignature(descriptor).getString();
            }
        }, 30, null);
        StringBuilder sb = new StringBuilder();
        sb.append("Property '");
        sb.append(name);
        sb.append("' (JVM signature: ");
        sb.append(signature);
        sb.append(") not resolved in ");
        sb.append(this);
        sb.append(':');
        sb.append(strJoinToString$default.length() == 0 ? " no members found" : '\n' + strJoinToString$default);
        throw new KotlinReflectionInternalError(sb.toString());
    }

    public final FunctionDescriptor findFunctionDescriptor(String name, String signature) {
        List functions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        if (Intrinsics.areEqual(name, "<init>")) {
            functions = CollectionsKt.toList(getConstructorDescriptors());
        } else {
            Name nameIdentifier = Name.identifier(name);
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(name)");
            functions = getFunctions(nameIdentifier);
        }
        Collection<FunctionDescriptor> collection = functions;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (Intrinsics.areEqual(RuntimeTypeMapper.INSTANCE.mapSignature((FunctionDescriptor) obj).get_signature(), signature)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.size() != 1) {
            String strJoinToString$default = CollectionsKt.joinToString$default(collection, "\n", null, null, 0, null, new Function1<FunctionDescriptor, CharSequence>() { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$findFunctionDescriptor$allMembers$1
                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(FunctionDescriptor descriptor) {
                    Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                    return DescriptorRenderer.DEBUG_TEXT.render(descriptor) + " | " + RuntimeTypeMapper.INSTANCE.mapSignature(descriptor).get_signature();
                }
            }, 30, null);
            StringBuilder sb = new StringBuilder();
            sb.append("Function '");
            sb.append(name);
            sb.append("' (JVM signature: ");
            sb.append(signature);
            sb.append(") not resolved in ");
            sb.append(this);
            sb.append(':');
            sb.append(strJoinToString$default.length() == 0 ? " no members found" : '\n' + strJoinToString$default);
            throw new KotlinReflectionInternalError(sb.toString());
        }
        return (FunctionDescriptor) CollectionsKt.single((List) arrayList2);
    }

    private final Method lookupMethod(Class<?> cls, String str, Class<?>[] clsArr, Class<?> cls2, boolean z) {
        Method methodLookupMethod;
        if (z) {
            clsArr[0] = cls;
        }
        Method methodTryGetMethod = tryGetMethod(cls, str, clsArr, cls2);
        if (methodTryGetMethod != null) {
            return methodTryGetMethod;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && (methodLookupMethod = lookupMethod(superclass, str, clsArr, cls2, z)) != null) {
            return methodLookupMethod;
        }
        for (Class<?> superInterface : cls.getInterfaces()) {
            Method methodLookupMethod2 = lookupMethod(superInterface, str, clsArr, cls2, z);
            if (methodLookupMethod2 != null) {
                return methodLookupMethod2;
            }
            if (z) {
                Class<?> clsTryLoadClass = ReflectJavaClassFinderKt.tryLoadClass(ReflectClassUtilKt.getSafeClassLoader(superInterface), superInterface.getName() + "$DefaultImpls");
                if (clsTryLoadClass != null) {
                    Intrinsics.checkNotNullExpressionValue(superInterface, "superInterface");
                    clsArr[0] = superInterface;
                    Method methodTryGetMethod2 = tryGetMethod(clsTryLoadClass, str, clsArr, cls2);
                    if (methodTryGetMethod2 != null) {
                        return methodTryGetMethod2;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0055  */
    private final Method tryGetMethod(Class<?> cls, String str, Class<?>[] clsArr, Class<?> cls2) {
        boolean z;
        try {
            Method result = cls.getDeclaredMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            Intrinsics.checkNotNullExpressionValue(result, "result");
            if (Intrinsics.areEqual(result.getReturnType(), cls2)) {
                return result;
            }
            Method[] declaredMethods = cls.getDeclaredMethods();
            Intrinsics.checkNotNullExpressionValue(declaredMethods, "declaredMethods");
            for (Method method : declaredMethods) {
                Intrinsics.checkNotNullExpressionValue(method, "method");
                if (Intrinsics.areEqual(method.getName(), str) && Intrinsics.areEqual(method.getReturnType(), cls2)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Intrinsics.checkNotNull(parameterTypes);
                    if (Arrays.equals(parameterTypes, clsArr)) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
                if (z) {
                    return method;
                }
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private final Constructor<?> tryGetConstructor(Class<?> cls, List<? extends Class<?>> list) {
        try {
            Object[] array = list.toArray(new Class[0]);
            if (array != null) {
                Class[] clsArr = (Class[]) array;
                return cls.getDeclaredConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length));
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final Method findMethodBySignature(String name, String desc) {
        Method methodLookupMethod;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desc, "desc");
        if (Intrinsics.areEqual(name, "<init>")) {
            return null;
        }
        Object[] array = loadParameterTypes(desc).toArray(new Class[0]);
        if (array != null) {
            Class<?>[] clsArr = (Class[]) array;
            Class<?> clsLoadReturnType = loadReturnType(desc);
            Method methodLookupMethod2 = lookupMethod(getMethodOwner(), name, clsArr, clsLoadReturnType, false);
            if (methodLookupMethod2 != null) {
                return methodLookupMethod2;
            }
            if (!getMethodOwner().isInterface() || (methodLookupMethod = lookupMethod(Object.class, name, clsArr, clsLoadReturnType, false)) == null) {
                return null;
            }
            return methodLookupMethod;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final Method findDefaultMethod(String name, String desc, boolean isMember) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desc, "desc");
        if (Intrinsics.areEqual(name, "<init>")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (isMember) {
            arrayList.add(getJClass());
        }
        addParametersAndMasks(arrayList, desc, false);
        Class<?> methodOwner = getMethodOwner();
        String str = name + "$default";
        Object[] array = arrayList.toArray(new Class[0]);
        if (array != null) {
            return lookupMethod(methodOwner, str, (Class[]) array, loadReturnType(desc), isMember);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final Constructor<?> findConstructorBySignature(String desc) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        return tryGetConstructor(getJClass(), loadParameterTypes(desc));
    }

    public final Constructor<?> findDefaultConstructor(String desc) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Class<?> jClass = getJClass();
        ArrayList arrayList = new ArrayList();
        addParametersAndMasks(arrayList, desc, true);
        Unit unit = Unit.INSTANCE;
        return tryGetConstructor(jClass, arrayList);
    }

    private final void addParametersAndMasks(List<Class<?>> result, String desc, boolean isConstructor) {
        Class<?> DEFAULT_CONSTRUCTOR_MARKER2;
        List<Class<?>> listLoadParameterTypes = loadParameterTypes(desc);
        result.addAll(listLoadParameterTypes);
        int size = ((listLoadParameterTypes.size() + 32) - 1) / 32;
        for (int i = 0; i < size; i++) {
            Class<?> cls = Integer.TYPE;
            Intrinsics.checkNotNullExpressionValue(cls, "Integer.TYPE");
            result.add(cls);
        }
        if (isConstructor) {
            DEFAULT_CONSTRUCTOR_MARKER2 = DEFAULT_CONSTRUCTOR_MARKER;
            Intrinsics.checkNotNullExpressionValue(DEFAULT_CONSTRUCTOR_MARKER2, "DEFAULT_CONSTRUCTOR_MARKER");
        } else {
            DEFAULT_CONSTRUCTOR_MARKER2 = Object.class;
        }
        result.add(DEFAULT_CONSTRUCTOR_MARKER2);
    }

    private final List<Class<?>> loadParameterTypes(String desc) {
        int iIndexOf$default;
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (desc.charAt(i) != ')') {
            int i2 = i;
            while (desc.charAt(i2) == '[') {
                i2++;
            }
            char cCharAt = desc.charAt(i2);
            if (StringsKt.contains$default((CharSequence) "VZCBSIFJD", cCharAt, false, 2, (Object) null)) {
                iIndexOf$default = i2 + 1;
            } else if (cCharAt == 'L') {
                iIndexOf$default = StringsKt.indexOf$default((CharSequence) desc, ';', i, false, 4, (Object) null) + 1;
            } else {
                throw new KotlinReflectionInternalError("Unknown type prefix in the method signature: " + desc);
            }
            arrayList.add(parseType(desc, i, iIndexOf$default));
            i = iIndexOf$default;
        }
        return arrayList;
    }

    private final Class<?> parseType(String desc, int begin, int end) throws ClassNotFoundException {
        Class<?> clsLoadClass;
        char cCharAt = desc.charAt(begin);
        if (cCharAt == 'F') {
            clsLoadClass = Float.TYPE;
        } else if (cCharAt == 'L') {
            ClassLoader safeClassLoader = ReflectClassUtilKt.getSafeClassLoader(getJClass());
            int i = begin + 1;
            int i2 = end - 1;
            if (desc == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = desc.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            clsLoadClass = safeClassLoader.loadClass(StringsKt.replace$default(strSubstring, '/', '.', false, 4, (Object) null));
        } else if (cCharAt == 'S') {
            clsLoadClass = Short.TYPE;
        } else if (cCharAt == 'V') {
            clsLoadClass = Void.TYPE;
        } else if (cCharAt == 'I') {
            clsLoadClass = Integer.TYPE;
        } else if (cCharAt == 'J') {
            clsLoadClass = Long.TYPE;
        } else if (cCharAt == 'Z') {
            clsLoadClass = Boolean.TYPE;
        } else if (cCharAt == '[') {
            clsLoadClass = ReflectClassUtilKt.createArrayType(parseType(desc, begin + 1, end));
        } else {
            switch (cCharAt) {
                case 'B':
                    clsLoadClass = Byte.TYPE;
                    break;
                case 'C':
                    clsLoadClass = Character.TYPE;
                    break;
                case 'D':
                    clsLoadClass = Double.TYPE;
                    break;
                default:
                    throw new KotlinReflectionInternalError("Unknown type prefix in the method signature: " + desc);
            }
        }
        Intrinsics.checkNotNullExpressionValue(clsLoadClass, "when (desc[begin]) {\n   …nature: $desc\")\n        }");
        return clsLoadClass;
    }

    private final Class<?> loadReturnType(String desc) {
        return parseType(desc, StringsKt.indexOf$default((CharSequence) desc, ')', 0, false, 6, (Object) null) + 1, desc.length());
    }

    /* JADX INFO: compiled from: KDeclarationContainerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0002\b\u0003 \u0005*\b\u0012\u0002\b\u0003\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Companion;", "", "()V", "DEFAULT_CONSTRUCTOR_MARKER", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "LOCAL_PROPERTY_SIGNATURE", "Lkotlin/text/Regex;", "getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection", "()Lkotlin/text/Regex;", "kotlin-reflection"}, k = 1, mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Regex getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection() {
            return KDeclarationContainerImpl.LOCAL_PROPERTY_SIGNATURE;
        }
    }
}
