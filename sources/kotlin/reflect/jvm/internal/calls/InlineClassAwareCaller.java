package kotlin.reflect.jvm.internal.calls;

import androidx.core.app.NotificationCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: InlineClassAwareCaller.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0000\u0018\u0000*\f\b\u0000\u0010\u0001 \u0001*\u0004\u0018\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001cB#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016¢\u0006\u0002\u0010\u001bR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller;", "M", "Ljava/lang/reflect/Member;", "Lkotlin/reflect/jvm/internal/calls/Caller;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "caller", "isDefault", "", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;Lkotlin/reflect/jvm/internal/calls/Caller;Z)V", "data", "Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "member", "getMember", "()Ljava/lang/reflect/Member;", "parameterTypes", "", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", NotificationCompat.CATEGORY_CALL, "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "BoxUnboxData", "kotlin-reflection"}, k = 1, mv = {1, 4, 0})
public final class InlineClassAwareCaller<M extends Member> implements Caller<M> {
    private final Caller<M> caller;
    private final BoxUnboxData data;
    private final boolean isDefault;

    /* JADX WARN: Code duplicated, block: B:25:0x0075  */
    /* JADX WARN: Multi-variable type inference failed */
    public InlineClassAwareCaller(CallableMemberDescriptor descriptor, Caller<? extends M> caller, boolean z) {
        BoxUnboxData boxUnboxData;
        Class<?> inlineClass;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(caller, "caller");
        this.caller = caller;
        this.isDefault = z;
        InlineClassAwareCaller<M> inlineClassAwareCaller = this;
        KotlinType returnType = descriptor.getReturnType();
        Intrinsics.checkNotNull(returnType);
        Intrinsics.checkNotNullExpressionValue(returnType, "descriptor.returnType!!");
        Class<?> inlineClass2 = InlineClassAwareCallerKt.toInlineClass(returnType);
        Method boxMethod = inlineClass2 != null ? InlineClassAwareCallerKt.getBoxMethod(inlineClass2, descriptor) : null;
        if (InlineClassesUtilsKt.isGetterOfUnderlyingPropertyOfInlineClass(descriptor)) {
            boxUnboxData = new BoxUnboxData(IntRange.INSTANCE.getEMPTY(), new Method[0], boxMethod);
        } else {
            Caller<M> caller2 = inlineClassAwareCaller.caller;
            int i = -1;
            if (!(caller2 instanceof CallerImpl.Method.BoundStatic)) {
                if (descriptor instanceof ConstructorDescriptor) {
                    if (!(caller2 instanceof BoundCaller)) {
                        i = 0;
                    }
                } else if (descriptor.getDispatchReceiverParameter() == null || (inlineClassAwareCaller.caller instanceof BoundCaller)) {
                    i = 0;
                } else {
                    DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
                    Intrinsics.checkNotNullExpressionValue(containingDeclaration, "descriptor.containingDeclaration");
                    if (InlineClassesUtilsKt.isInlineClass(containingDeclaration)) {
                        i = 0;
                    } else {
                        i = 1;
                    }
                }
            }
            int i2 = inlineClassAwareCaller.isDefault ? 2 : 0;
            ArrayList arrayList = new ArrayList();
            ReceiverParameterDescriptor extensionReceiverParameter = descriptor.getExtensionReceiverParameter();
            KotlinType type = extensionReceiverParameter != null ? extensionReceiverParameter.getType() : null;
            if (type != null) {
                arrayList.add(type);
            } else if (descriptor instanceof ConstructorDescriptor) {
                ClassDescriptor constructedClass = ((ConstructorDescriptor) descriptor).getConstructedClass();
                Intrinsics.checkNotNullExpressionValue(constructedClass, "descriptor.constructedClass");
                if (constructedClass.isInner()) {
                    DeclarationDescriptor containingDeclaration2 = constructedClass.getContainingDeclaration();
                    if (containingDeclaration2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    }
                    arrayList.add(((ClassDescriptor) containingDeclaration2).getDefaultType());
                }
            } else {
                DeclarationDescriptor containingDeclaration3 = descriptor.getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(containingDeclaration3, "descriptor.containingDeclaration");
                if (containingDeclaration3 instanceof ClassDescriptor) {
                    ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration3;
                    if (classDescriptor.isInline()) {
                        arrayList.add(classDescriptor.getDefaultType());
                    }
                }
            }
            List<ValueParameterDescriptor> valueParameters = descriptor.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "descriptor.valueParameters");
            Iterator<T> it = valueParameters.iterator();
            while (it.hasNext()) {
                arrayList.add(((ValueParameterDescriptor) it.next()).getType());
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size() + i + i2;
            InlineClassAwareCaller<M> inlineClassAwareCaller2 = inlineClassAwareCaller;
            if (CallerKt.getArity(inlineClassAwareCaller2) != size) {
                throw new KotlinReflectionInternalError("Inconsistent number of parameters in the descriptor and Java reflection object: " + CallerKt.getArity(inlineClassAwareCaller2) + " != " + size + "\nCalling: " + descriptor + "\nParameter types: " + inlineClassAwareCaller.getParameterTypes() + ")\nDefault: " + inlineClassAwareCaller.isDefault);
            }
            IntRange intRangeUntil = RangesKt.until(Math.max(i, 0), arrayList2.size() + i);
            Method[] methodArr = new Method[size];
            for (int i3 = 0; i3 < size; i3++) {
                methodArr[i3] = (!intRangeUntil.contains(i3) || (inlineClass = InlineClassAwareCallerKt.toInlineClass((KotlinType) arrayList2.get(i3 - i))) == null) ? null : InlineClassAwareCallerKt.getUnboxMethod(inlineClass, descriptor);
            }
            boxUnboxData = new BoxUnboxData(intRangeUntil, methodArr, boxMethod);
        }
        this.data = boxUnboxData;
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    /* JADX INFO: renamed from: getMember */
    public M mo1260getMember() {
        return (M) this.caller.mo1260getMember();
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public Type getReturnType() {
        return this.caller.getReturnType();
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public List<Type> getParameterTypes() {
        return this.caller.getParameterTypes();
    }

    /* JADX INFO: compiled from: InlineClassAwareCaller.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003H\u0086\u0002J\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u0086\u0002¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "", "argumentRange", "Lkotlin/ranges/IntRange;", "unbox", "", "Ljava/lang/reflect/Method;", "box", "(Lkotlin/ranges/IntRange;[Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getArgumentRange", "()Lkotlin/ranges/IntRange;", "getBox", "()Ljava/lang/reflect/Method;", "getUnbox", "()[Ljava/lang/reflect/Method;", "[Ljava/lang/reflect/Method;", "component1", "component2", "component3", "kotlin-reflection"}, k = 1, mv = {1, 4, 0})
    private static final class BoxUnboxData {
        private final IntRange argumentRange;
        private final Method box;
        private final Method[] unbox;

        public BoxUnboxData(IntRange argumentRange, Method[] unbox, Method method) {
            Intrinsics.checkNotNullParameter(argumentRange, "argumentRange");
            Intrinsics.checkNotNullParameter(unbox, "unbox");
            this.argumentRange = argumentRange;
            this.unbox = unbox;
            this.box = method;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IntRange getArgumentRange() {
            return this.argumentRange;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Method[] getUnbox() {
            return this.unbox;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Method getBox() {
            return this.box;
        }
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public Object call(Object[] args) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke;
        Intrinsics.checkNotNullParameter(args, "args");
        BoxUnboxData boxUnboxData = this.data;
        IntRange argumentRange = boxUnboxData.getArgumentRange();
        Method[] unbox = boxUnboxData.getUnbox();
        Method box = boxUnboxData.getBox();
        Object[] objArrCopyOf = Arrays.copyOf(args, args.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        if (objArrCopyOf == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
        int first = argumentRange.getFirst();
        int last = argumentRange.getLast();
        if (first <= last) {
            while (true) {
                Method method = unbox[first];
                Object objDefaultPrimitiveValue = args[first];
                if (method != null) {
                    if (objDefaultPrimitiveValue != null) {
                        objDefaultPrimitiveValue = method.invoke(objDefaultPrimitiveValue, new Object[0]);
                    } else {
                        Class<?> returnType = method.getReturnType();
                        Intrinsics.checkNotNullExpressionValue(returnType, "method.returnType");
                        objDefaultPrimitiveValue = UtilKt.defaultPrimitiveValue(returnType);
                    }
                }
                objArrCopyOf[first] = objDefaultPrimitiveValue;
                if (first == last) {
                    break;
                }
                first++;
            }
        }
        Object objCall = this.caller.call(objArrCopyOf);
        return (box == null || (objInvoke = box.invoke(null, objCall)) == null) ? objCall : objInvoke;
    }
}
