package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

/* JADX INFO: compiled from: KClassImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KTypeImpl;", "kotlin.jvm.PlatformType", ExifInterface.GPS_DIRECTION_TRUE, "", "invoke"}, k = 3, mv = {1, 4, 0})
final class KClassImpl$Data$supertypes$2 extends Lambda implements Function0<List<? extends KTypeImpl>> {
    final /* synthetic */ KClassImpl.Data this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    KClassImpl$Data$supertypes$2(KClassImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends KTypeImpl> invoke() {
        ClassKind kind;
        TypeConstructor typeConstructor = this.this$0.getDescriptor().getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "descriptor.typeConstructor");
        Collection<KotlinType> collectionMo1269getSupertypes = typeConstructor.mo1269getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collectionMo1269getSupertypes, "descriptor.typeConstructor.supertypes");
        ArrayList arrayList = new ArrayList(collectionMo1269getSupertypes.size());
        for (final KotlinType kotlinType : collectionMo1269getSupertypes) {
            Intrinsics.checkNotNullExpressionValue(kotlinType, "kotlinType");
            arrayList.add(new KTypeImpl(kotlinType, new Function0<Type>() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Type invoke() {
                    Type genericSuperclass;
                    ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = kotlinType.getConstructor().mo1268getDeclarationDescriptor();
                    if (!(classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassDescriptor)) {
                        throw new KotlinReflectionInternalError("Supertype not a class: " + classifierDescriptorMo1268getDeclarationDescriptor);
                    }
                    Class<?> javaClass = UtilKt.toJavaClass((ClassDescriptor) classifierDescriptorMo1268getDeclarationDescriptor);
                    if (javaClass == null) {
                        throw new KotlinReflectionInternalError("Unsupported superclass of " + this.this$0 + ": " + classifierDescriptorMo1268getDeclarationDescriptor);
                    }
                    if (Intrinsics.areEqual(KClassImpl.this.getJClass().getSuperclass(), javaClass)) {
                        genericSuperclass = KClassImpl.this.getJClass().getGenericSuperclass();
                    } else {
                        Class<?>[] interfaces = KClassImpl.this.getJClass().getInterfaces();
                        Intrinsics.checkNotNullExpressionValue(interfaces, "jClass.interfaces");
                        int iIndexOf = ArraysKt.indexOf(interfaces, javaClass);
                        if (iIndexOf < 0) {
                            throw new KotlinReflectionInternalError("No superclass of " + this.this$0 + " in Java reflection for " + classifierDescriptorMo1268getDeclarationDescriptor);
                        }
                        genericSuperclass = KClassImpl.this.getJClass().getGenericInterfaces()[iIndexOf];
                    }
                    Intrinsics.checkNotNullExpressionValue(genericSuperclass, "if (jClass.superclass ==…ex]\n                    }");
                    return genericSuperclass;
                }
            }));
        }
        ArrayList arrayList2 = arrayList;
        if (!KotlinBuiltIns.isSpecialClassWithNoSupertypes(this.this$0.getDescriptor())) {
            ArrayList arrayList3 = arrayList;
            boolean z = false;
            if ((arrayList3 instanceof Collection) && arrayList3.isEmpty()) {
                z = true;
                break;
            }
            Iterator it = arrayList3.iterator();
            do {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                ClassDescriptor classDescriptorForType = DescriptorUtils.getClassDescriptorForType(((KTypeImpl) it.next()).getType());
                Intrinsics.checkNotNullExpressionValue(classDescriptorForType, "DescriptorUtils.getClassDescriptorForType(it.type)");
                kind = classDescriptorForType.getKind();
                Intrinsics.checkNotNullExpressionValue(kind, "DescriptorUtils.getClass…ptorForType(it.type).kind");
            } while (kind == ClassKind.INTERFACE || kind == ClassKind.ANNOTATION_CLASS);
            if (z) {
                SimpleType anyType = DescriptorUtilsKt.getBuiltIns(this.this$0.getDescriptor()).getAnyType();
                Intrinsics.checkNotNullExpressionValue(anyType, "descriptor.builtIns.anyType");
                arrayList2.add(new KTypeImpl(anyType, new Function0<Type>() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.3
                    @Override // kotlin.jvm.functions.Function0
                    public final Type invoke() {
                        return Object.class;
                    }
                }));
            }
        }
        return CollectionsKt.compact(arrayList);
    }
}
