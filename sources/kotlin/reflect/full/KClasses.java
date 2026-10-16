package kotlin.reflect.full;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.KFunctionImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.DFS;

/* JADX INFO: compiled from: KClasses.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\r\u001a+\u0010S\u001a\u0002H\u001d\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00022\b\u0010T\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0002\u0010U\u001a!\u0010V\u001a\u0002H\u001d\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u0002H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010W\u001a\u000203*\u0006\u0012\u0002\b\u00030\u00022\n\u0010X\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0007\u001a\u001c\u0010Y\u001a\u000203*\u0006\u0012\u0002\b\u00030\u00022\n\u0010Z\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0007\u001a-\u0010[\u001a\u0004\u0018\u0001H\u001d\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00022\b\u0010T\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0002\u0010U\",\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"(\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006\"(\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u000e\"$\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0013\",\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\",\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006\"B\u0010\u001b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001c0\u0001\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\",\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0004\u001a\u0004\b\"\u0010\u0006\">\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u00030$0\u0001\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010\u0004\u001a\u0004\b&\u0010\u0006\",\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030(0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b)\u0010\u0004\u001a\u0004\b*\u0010\u0006\"\"\u0010+\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b,\u0010\u0004\u001a\u0004\b-\u0010.\",\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b0\u0010\u0004\u001a\u0004\b1\u0010\u0006\"\u001c\u00102\u001a\u000203*\u0006\u0012\u0002\b\u0003048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00105\"\u001c\u00106\u001a\u000203*\u0006\u0012\u0002\b\u0003048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00105\",\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b8\u0010\u0004\u001a\u0004\b9\u0010\u0006\"B\u0010:\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001c0\u0001\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b;\u0010\u0004\u001a\u0004\b<\u0010\u0006\",\u0010=\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b>\u0010\u0004\u001a\u0004\b?\u0010\u0006\">\u0010@\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u00030$0\u0001\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bA\u0010\u0004\u001a\u0004\bB\u0010\u0006\"6\u0010C\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u0015\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bD\u0010\u0004\u001a\u0004\bE\u0010F\",\u0010G\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bH\u0010\u0004\u001a\u0004\bI\u0010\u0006\",\u0010J\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030K0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bL\u0010\u0004\u001a\u0004\bM\u0010\u0006\",\u0010N\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020O*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bP\u0010\u0004\u001a\u0004\bQ\u0010R¨\u0006\\"}, d2 = {"allSuperclasses", "", "Lkotlin/reflect/KClass;", "getAllSuperclasses$annotations", "(Lkotlin/reflect/KClass;)V", "getAllSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/Collection;", "allSupertypes", "Lkotlin/reflect/KType;", "getAllSupertypes$annotations", "getAllSupertypes", "companionObject", "getCompanionObject$annotations", "getCompanionObject", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KClass;", "companionObjectInstance", "", "getCompanionObjectInstance$annotations", "getCompanionObjectInstance", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "declaredFunctions", "Lkotlin/reflect/KFunction;", "getDeclaredFunctions$annotations", "getDeclaredFunctions", "declaredMemberExtensionFunctions", "getDeclaredMemberExtensionFunctions$annotations", "getDeclaredMemberExtensionFunctions", "declaredMemberExtensionProperties", "Lkotlin/reflect/KProperty2;", ExifInterface.GPS_DIRECTION_TRUE, "getDeclaredMemberExtensionProperties$annotations", "getDeclaredMemberExtensionProperties", "declaredMemberFunctions", "getDeclaredMemberFunctions$annotations", "getDeclaredMemberFunctions", "declaredMemberProperties", "Lkotlin/reflect/KProperty1;", "getDeclaredMemberProperties$annotations", "getDeclaredMemberProperties", "declaredMembers", "Lkotlin/reflect/KCallable;", "getDeclaredMembers$annotations", "getDeclaredMembers", "defaultType", "getDefaultType$annotations", "getDefaultType", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KType;", "functions", "getFunctions$annotations", "getFunctions", "isExtension", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "(Lkotlin/reflect/jvm/internal/KCallableImpl;)Z", "isNotExtension", "memberExtensionFunctions", "getMemberExtensionFunctions$annotations", "getMemberExtensionFunctions", "memberExtensionProperties", "getMemberExtensionProperties$annotations", "getMemberExtensionProperties", "memberFunctions", "getMemberFunctions$annotations", "getMemberFunctions", "memberProperties", "getMemberProperties$annotations", "getMemberProperties", "primaryConstructor", "getPrimaryConstructor$annotations", "getPrimaryConstructor", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KFunction;", "staticFunctions", "getStaticFunctions$annotations", "getStaticFunctions", "staticProperties", "Lkotlin/reflect/KProperty0;", "getStaticProperties$annotations", "getStaticProperties", "superclasses", "", "getSuperclasses$annotations", "getSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/List;", "cast", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "createInstance", "isSubclassOf", "base", "isSuperclassOf", "derived", "safeCast", "kotlin-reflection"}, k = 2, mv = {1, 4, 0})
public final class KClasses {
    public static /* synthetic */ void getAllSuperclasses$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getAllSupertypes$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getCompanionObject$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getCompanionObjectInstance$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMemberExtensionFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMemberExtensionProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMemberFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMemberProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getDeclaredMembers$annotations(KClass kClass) {
    }

    @Deprecated(message = "This function creates a type which rarely makes sense for generic classes. For example, such type can only be used in signatures of members of that class. Use starProjectedType or createType() for clearer semantics.")
    public static /* synthetic */ void getDefaultType$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getMemberExtensionFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getMemberExtensionProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getMemberFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getMemberProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getPrimaryConstructor$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getStaticFunctions$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getStaticProperties$annotations(KClass kClass) {
    }

    public static /* synthetic */ void getSuperclasses$annotations(KClass kClass) {
    }

    public static final <T> KFunction<T> getPrimaryConstructor(KClass<T> primaryConstructor) {
        T next;
        Intrinsics.checkNotNullParameter(primaryConstructor, "$this$primaryConstructor");
        Iterator<T> it = ((KClassImpl) primaryConstructor).getConstructors().iterator();
        while (it.hasNext()) {
            next = it.next();
            KFunction kFunction = (KFunction) next;
            if (kFunction == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KFunctionImpl");
            }
            FunctionDescriptor descriptor = ((KFunctionImpl) kFunction).getDescriptor();
            if (descriptor == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ConstructorDescriptor");
            }
            if (((ConstructorDescriptor) descriptor).isPrimary()) {
                return (KFunction) next;
            }
        }
        next = null;
        return (KFunction) next;
    }

    public static final KClass<?> getCompanionObject(KClass<?> companionObject) {
        Object next;
        Intrinsics.checkNotNullParameter(companionObject, "$this$companionObject");
        Iterator<T> it = companionObject.getNestedClasses().iterator();
        while (it.hasNext()) {
            next = it.next();
            KClass kClass = (KClass) next;
            if (kClass == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
            }
            if (((KClassImpl) kClass).getDescriptor().isCompanionObject()) {
                return (KClass) next;
            }
        }
        next = null;
        return (KClass) next;
    }

    public static final Object getCompanionObjectInstance(KClass<?> companionObjectInstance) {
        Intrinsics.checkNotNullParameter(companionObjectInstance, "$this$companionObjectInstance");
        KClass<?> companionObject = getCompanionObject(companionObjectInstance);
        if (companionObject != null) {
            return companionObject.getObjectInstance();
        }
        return null;
    }

    public static final KType getDefaultType(final KClass<?> defaultType) {
        Intrinsics.checkNotNullParameter(defaultType, "$this$defaultType");
        SimpleType defaultType2 = ((KClassImpl) defaultType).getDescriptor().getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType2, "(this as KClassImpl<*>).descriptor.defaultType");
        return new KTypeImpl(defaultType2, new Function0<Type>() { // from class: kotlin.reflect.full.KClasses$defaultType$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Type invoke() {
                return ((KClassImpl) defaultType).getJClass();
            }
        });
    }

    public static final Collection<KCallable<?>> getDeclaredMembers(KClass<?> declaredMembers) {
        Intrinsics.checkNotNullParameter(declaredMembers, "$this$declaredMembers");
        return ((KClassImpl.Data) ((KClassImpl) declaredMembers).getData().invoke()).getDeclaredMembers();
    }

    public static final Collection<KFunction<?>> getFunctions(KClass<?> functions) {
        Intrinsics.checkNotNullParameter(functions, "$this$functions");
        Collection<KCallable<?>> members = functions.getMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : members) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getStaticFunctions(KClass<?> staticFunctions) {
        Intrinsics.checkNotNullParameter(staticFunctions, "$this$staticFunctions");
        Collection<KCallableImpl<?>> allStaticMembers = ((KClassImpl.Data) ((KClassImpl) staticFunctions).getData().invoke()).getAllStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allStaticMembers) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getMemberFunctions(KClass<?> memberFunctions) {
        Intrinsics.checkNotNullParameter(memberFunctions, "$this$memberFunctions");
        Collection<KCallableImpl<?>> allNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) memberFunctions).getData().invoke()).getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getMemberExtensionFunctions(KClass<?> memberExtensionFunctions) {
        Intrinsics.checkNotNullParameter(memberExtensionFunctions, "$this$memberExtensionFunctions");
        Collection<KCallableImpl<?>> allNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) memberExtensionFunctions).getData().invoke()).getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isExtension(kCallableImpl) && (kCallableImpl instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getDeclaredFunctions(KClass<?> declaredFunctions) {
        Intrinsics.checkNotNullParameter(declaredFunctions, "$this$declaredFunctions");
        Collection<KCallableImpl<?>> declaredMembers = ((KClassImpl.Data) ((KClassImpl) declaredFunctions).getData().invoke()).getDeclaredMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredMembers) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getDeclaredMemberFunctions(KClass<?> declaredMemberFunctions) {
        Intrinsics.checkNotNullParameter(declaredMemberFunctions, "$this$declaredMemberFunctions");
        Collection<KCallableImpl<?>> declaredNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) declaredMemberFunctions).getData().invoke()).getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KFunction<?>> getDeclaredMemberExtensionFunctions(KClass<?> declaredMemberExtensionFunctions) {
        Intrinsics.checkNotNullParameter(declaredMemberExtensionFunctions, "$this$declaredMemberExtensionFunctions");
        Collection<KCallableImpl<?>> declaredNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) declaredMemberExtensionFunctions).getData().invoke()).getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isExtension(kCallableImpl) && (kCallableImpl instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Collection<KProperty0<?>> getStaticProperties(KClass<?> staticProperties) {
        Intrinsics.checkNotNullParameter(staticProperties, "$this$staticProperties");
        Collection<KCallableImpl<?>> allStaticMembers = ((KClassImpl.Data) ((KClassImpl) staticProperties).getData().invoke()).getAllStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KProperty0)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final <T> Collection<KProperty1<T, ?>> getMemberProperties(KClass<T> memberProperties) {
        Intrinsics.checkNotNullParameter(memberProperties, "$this$memberProperties");
        Collection<KCallableImpl<?>> allNonStaticMembers = ((KClassImpl) memberProperties).getData().invoke().getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : allNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) t;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KProperty1)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static final <T> Collection<KProperty2<T, ?, ?>> getMemberExtensionProperties(KClass<T> memberExtensionProperties) {
        Intrinsics.checkNotNullParameter(memberExtensionProperties, "$this$memberExtensionProperties");
        Collection<KCallableImpl<?>> allNonStaticMembers = ((KClassImpl) memberExtensionProperties).getData().invoke().getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : allNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) t;
            if (isExtension(kCallableImpl) && (kCallableImpl instanceof KProperty2)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static final <T> Collection<KProperty1<T, ?>> getDeclaredMemberProperties(KClass<T> declaredMemberProperties) {
        Intrinsics.checkNotNullParameter(declaredMemberProperties, "$this$declaredMemberProperties");
        Collection<KCallableImpl<?>> declaredNonStaticMembers = ((KClassImpl) declaredMemberProperties).getData().invoke().getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : declaredNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) t;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KProperty1)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static final <T> Collection<KProperty2<T, ?, ?>> getDeclaredMemberExtensionProperties(KClass<T> declaredMemberExtensionProperties) {
        Intrinsics.checkNotNullParameter(declaredMemberExtensionProperties, "$this$declaredMemberExtensionProperties");
        Collection<KCallableImpl<?>> declaredNonStaticMembers = ((KClassImpl) declaredMemberExtensionProperties).getData().invoke().getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : declaredNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) t;
            if (isExtension(kCallableImpl) && (kCallableImpl instanceof KProperty2)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    private static final boolean isExtension(KCallableImpl<?> kCallableImpl) {
        return kCallableImpl.getDescriptor().getExtensionReceiverParameter() != null;
    }

    private static final boolean isNotExtension(KCallableImpl<?> kCallableImpl) {
        return !isExtension(kCallableImpl);
    }

    public static final List<KClass<?>> getSuperclasses(KClass<?> superclasses) {
        Intrinsics.checkNotNullParameter(superclasses, "$this$superclasses");
        List<KType> supertypes = superclasses.getSupertypes();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = supertypes.iterator();
        while (it.hasNext()) {
            KClassifier classifier = ((KType) it.next()).getClassifier();
            if (!(classifier instanceof KClass)) {
                classifier = null;
            }
            KClass kClass = (KClass) classifier;
            if (kClass != null) {
                arrayList.add(kClass);
            }
        }
        return arrayList;
    }

    public static final Collection<KType> getAllSupertypes(KClass<?> allSupertypes) {
        Intrinsics.checkNotNullParameter(allSupertypes, "$this$allSupertypes");
        Object objDfs = DFS.dfs(allSupertypes.getSupertypes(), new DFS.Neighbors<KType>() { // from class: kotlin.reflect.full.KClasses$allSupertypes$1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<KType> getNeighbors(KType kType) {
                KClassifier classifier = kType.getClassifier();
                if (!(classifier instanceof KClass)) {
                    classifier = null;
                }
                KClass kClass = (KClass) classifier;
                if (kClass == null) {
                    throw new KotlinReflectionInternalError("Supertype not a class: " + kType);
                }
                ArrayList supertypes = kClass.getSupertypes();
                if (!kType.getArguments().isEmpty()) {
                    if (kType != null) {
                        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(((KTypeImpl) kType).getType());
                        List<KType> list = supertypes;
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                        for (KType kType2 : list) {
                            if (kType2 != null) {
                                KotlinType kotlinTypeSubstitute = typeSubstitutorCreate.substitute(((KTypeImpl) kType2).getType(), Variance.INVARIANT);
                                if (kotlinTypeSubstitute != null) {
                                    Intrinsics.checkNotNullExpressionValue(kotlinTypeSubstitute, "substitutor.substitute((…: $supertype ($current)\")");
                                    arrayList.add(new KTypeImpl(kotlinTypeSubstitute, null, 2, null));
                                } else {
                                    throw new KotlinReflectionInternalError("Type substitution failed: " + kType2 + " (" + kType + ')');
                                }
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
                            }
                        }
                        supertypes = arrayList;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
                    }
                }
                return supertypes;
            }
        }, new DFS.VisitedWithSet(), new DFS.NodeHandlerWithListResult<KType, KType>() { // from class: kotlin.reflect.full.KClasses$allSupertypes$2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(KType current) {
                Intrinsics.checkNotNullParameter(current, "current");
                ((LinkedList) this.result).add(current);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(objDfs, "DFS.dfs(\n            sup…    }\n            }\n    )");
        return (Collection) objDfs;
    }

    public static final Collection<KClass<?>> getAllSuperclasses(KClass<?> allSuperclasses) {
        Intrinsics.checkNotNullParameter(allSuperclasses, "$this$allSuperclasses");
        Collection<KType> allSupertypes = getAllSupertypes(allSuperclasses);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(allSupertypes, 10));
        for (KType kType : allSupertypes) {
            KClassifier classifier = kType.getClassifier();
            if (!(classifier instanceof KClass)) {
                classifier = null;
            }
            KClass kClass = (KClass) classifier;
            if (kClass == null) {
                throw new KotlinReflectionInternalError("Supertype not a class: " + kType);
            }
            arrayList.add(kClass);
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: kotlin.reflect.full.KClasses$isSubclassOf$1, reason: invalid class name */
    /* JADX INFO: compiled from: KClasses.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    final /* synthetic */ class AnonymousClass1 extends PropertyReference1 {
        public static final KProperty1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "superclasses";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinPackage(KClasses.class, "kotlin-reflection");
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getSuperclasses(Lkotlin/reflect/KClass;)Ljava/util/List;";
        }

        @Override // kotlin.reflect.KProperty1
        public Object get(Object obj) {
            return KClasses.getSuperclasses((KClass) obj);
        }
    }

    public static final boolean isSubclassOf(KClass<?> isSubclassOf, final KClass<?> base) {
        Object obj;
        Intrinsics.checkNotNullParameter(isSubclassOf, "$this$isSubclassOf");
        Intrinsics.checkNotNullParameter(base, "base");
        if (!Intrinsics.areEqual(isSubclassOf, base)) {
            List listListOf = CollectionsKt.listOf(isSubclassOf);
            final KProperty1 kProperty1 = AnonymousClass1.INSTANCE;
            if (kProperty1 != null) {
                obj = kProperty1;
                obj = new DFS.Neighbors() { // from class: kotlin.reflect.full.KClasses$sam$org_jetbrains_kotlin_utils_DFS_Neighbors$0
                    @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
                    public final /* synthetic */ Iterable getNeighbors(Object obj2) {
                        return (Iterable) kProperty1.invoke(obj2);
                    }
                };
            }
            obj = kProperty1;
            Boolean boolIfAny = DFS.ifAny(listListOf, (DFS.Neighbors) obj, new Function1<KClass<?>, Boolean>() { // from class: kotlin.reflect.full.KClasses.isSubclassOf.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(KClass<?> kClass) {
                    return Boolean.valueOf(Intrinsics.areEqual(kClass, base));
                }
            });
            Intrinsics.checkNotNullExpressionValue(boolIfAny, "DFS.ifAny(listOf(this), …erclasses) { it == base }");
            if (!boolIfAny.booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isSuperclassOf(KClass<?> isSuperclassOf, KClass<?> derived) {
        Intrinsics.checkNotNullParameter(isSuperclassOf, "$this$isSuperclassOf");
        Intrinsics.checkNotNullParameter(derived, "derived");
        return isSubclassOf(derived, isSuperclassOf);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T cast(KClass<T> cast, Object obj) {
        Intrinsics.checkNotNullParameter(cast, "$this$cast");
        if (cast.isInstance(obj)) {
            if (obj != 0) {
                return obj;
            }
            throw new NullPointerException("null cannot be cast to non-null type T");
        }
        throw new TypeCastException("Value cannot be cast to " + cast.getQualifiedName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T safeCast(KClass<T> safeCast, Object obj) {
        Intrinsics.checkNotNullParameter(safeCast, "$this$safeCast");
        if (!safeCast.isInstance(obj)) {
            return null;
        }
        if (obj != 0) {
            return obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type T");
    }

    public static final <T> T createInstance(KClass<T> createInstance) {
        boolean z;
        Intrinsics.checkNotNullParameter(createInstance, "$this$createInstance");
        Iterator<T> it = createInstance.getConstructors().iterator();
        T t = null;
        boolean z2 = false;
        T t2 = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z2) {
                    break;
                }
                t = t2;
                break;
            }
            T next = it.next();
            List<KParameter> parameters = ((KFunction) next).getParameters();
            if (!(parameters instanceof Collection) || !parameters.isEmpty()) {
                Iterator<T> it2 = parameters.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    if (!((KParameter) it2.next()).isOptional()) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
                break;
            }
            if (z) {
                if (z2) {
                    break;
                }
                t2 = next;
                z2 = true;
            }
        }
        KFunction kFunction = (KFunction) t;
        if (kFunction == null) {
            throw new IllegalArgumentException("Class should have a single no-arg constructor: " + createInstance);
        }
        return (T) kFunction.callBy(MapsKt.emptyMap());
    }
}
