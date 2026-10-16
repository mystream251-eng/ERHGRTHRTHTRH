package kotlin.reflect.full;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;

/* JADX INFO: compiled from: KCallables.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001a9\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00022\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00130\u0012\"\u0004\u0018\u00010\u0013H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a7\u0010\u0015\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00022\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0016H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0007\"$\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"$\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"extensionReceiverParameter", "Lkotlin/reflect/KParameter;", "Lkotlin/reflect/KCallable;", "getExtensionReceiverParameter$annotations", "(Lkotlin/reflect/KCallable;)V", "getExtensionReceiverParameter", "(Lkotlin/reflect/KCallable;)Lkotlin/reflect/KParameter;", "instanceParameter", "getInstanceParameter$annotations", "getInstanceParameter", "valueParameters", "", "getValueParameters$annotations", "getValueParameters", "(Lkotlin/reflect/KCallable;)Ljava/util/List;", "callSuspend", "R", "args", "", "", "(Lkotlin/reflect/KCallable;[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callSuspendBy", "", "(Lkotlin/reflect/KCallable;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findParameterByName", "name", "", "kotlin-reflection"}, k = 2, mv = {1, 4, 0})
public final class KCallables {

    /* JADX INFO: renamed from: kotlin.reflect.full.KCallables$callSuspend$1, reason: invalid class name */
    /* JADX INFO: compiled from: KCallables.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005\"\u0004\u0018\u00010\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087@"}, d2 = {"callSuspend", "", "R", "Lkotlin/reflect/KCallable;", "args", "", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlin.reflect.full.KCallables", f = "KCallables.kt", i = {0, 0}, l = {55}, m = "callSuspend", n = {"$this$callSuspend", "args"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KCallables.callSuspend(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlin.reflect.full.KCallables$callSuspendBy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KCallables.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087@"}, d2 = {"callSuspendBy", "", "R", "Lkotlin/reflect/KCallable;", "args", "", "Lkotlin/reflect/KParameter;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlin.reflect.full.KCallables", f = "KCallables.kt", i = {0, 0, 0}, l = {73}, m = "callSuspendBy", n = {"$this$callSuspendBy", "args", "kCallable"}, s = {"L$0", "L$1", "L$2"})
    static final class C06621 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C06621(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KCallables.callSuspendBy(null, null, this);
        }
    }

    public static /* synthetic */ void getExtensionReceiverParameter$annotations(KCallable kCallable) {
    }

    public static /* synthetic */ void getInstanceParameter$annotations(KCallable kCallable) {
    }

    public static /* synthetic */ void getValueParameters$annotations(KCallable kCallable) {
    }

    public static final KParameter getInstanceParameter(KCallable<?> instanceParameter) {
        Intrinsics.checkNotNullParameter(instanceParameter, "$this$instanceParameter");
        Object obj = null;
        boolean z = false;
        Object obj2 = null;
        for (Object obj3 : instanceParameter.getParameters()) {
            if (((KParameter) obj3).getKind() == KParameter.Kind.INSTANCE) {
                if (z) {
                    return (KParameter) obj;
                }
                obj2 = obj3;
                z = true;
            }
        }
        if (z) {
            obj = obj2;
        }
        return (KParameter) obj;
    }

    public static final KParameter getExtensionReceiverParameter(KCallable<?> extensionReceiverParameter) {
        Intrinsics.checkNotNullParameter(extensionReceiverParameter, "$this$extensionReceiverParameter");
        Object obj = null;
        boolean z = false;
        Object obj2 = null;
        for (Object obj3 : extensionReceiverParameter.getParameters()) {
            if (((KParameter) obj3).getKind() == KParameter.Kind.EXTENSION_RECEIVER) {
                if (z) {
                    return (KParameter) obj;
                }
                obj2 = obj3;
                z = true;
            }
        }
        if (z) {
            obj = obj2;
        }
        return (KParameter) obj;
    }

    public static final List<KParameter> getValueParameters(KCallable<?> valueParameters) {
        Intrinsics.checkNotNullParameter(valueParameters, "$this$valueParameters");
        List<KParameter> parameters = valueParameters.getParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : parameters) {
            if (((KParameter) obj).getKind() == KParameter.Kind.VALUE) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final KParameter findParameterByName(KCallable<?> findParameterByName, String name) {
        Intrinsics.checkNotNullParameter(findParameterByName, "$this$findParameterByName");
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = null;
        boolean z = false;
        Object obj2 = null;
        for (Object obj3 : findParameterByName.getParameters()) {
            if (Intrinsics.areEqual(((KParameter) obj3).getName(), name)) {
                if (z) {
                    return (KParameter) obj;
                }
                z = true;
                obj2 = obj3;
            }
        }
        if (z) {
            obj = obj2;
        }
        return (KParameter) obj;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <R> Object callSuspend(KCallable<? extends R> kCallable, Object[] objArr, Continuation<? super R> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objCall = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objCall);
            if (!kCallable.isSuspend()) {
                return kCallable.call(Arrays.copyOf(objArr, objArr.length));
            }
            if (!(kCallable instanceof KFunction)) {
                throw new IllegalArgumentException("Cannot callSuspend on a property " + kCallable + ": suspend properties are not supported yet");
            }
            anonymousClass1.L$0 = kCallable;
            anonymousClass1.L$1 = objArr;
            anonymousClass1.label = 1;
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.addSpread(objArr);
            spreadBuilder.add(anonymousClass2);
            objCall = kCallable.call(spreadBuilder.toArray(new Object[spreadBuilder.size()]));
            if (objCall == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(anonymousClass2);
            }
            if (objCall == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kCallable = (KCallable) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objCall);
        }
        return (!Intrinsics.areEqual(kCallable.getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(Unit.class)) || kCallable.getReturnType().getIsMarkedNullable()) ? objCall : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, kotlin.reflect.jvm.internal.KCallableImpl] */
    public static final <R> Object callSuspendBy(KCallable<? extends R> kCallable, Map<KParameter, ? extends Object> map, Continuation<? super R> continuation) throws IllegalCallableAccessException {
        C06621 c06621;
        if (continuation instanceof C06621) {
            c06621 = (C06621) continuation;
            if ((c06621.label & Integer.MIN_VALUE) != 0) {
                c06621.label -= Integer.MIN_VALUE;
            } else {
                c06621 = new C06621(continuation);
            }
        } else {
            c06621 = new C06621(continuation);
        }
        Object objCallDefaultMethod$kotlin_reflection = c06621.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06621.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objCallDefaultMethod$kotlin_reflection);
            if (!kCallable.isSuspend()) {
                return kCallable.callBy(map);
            }
            if (!(kCallable instanceof KFunction)) {
                throw new IllegalArgumentException("Cannot callSuspendBy on a property " + kCallable + ": suspend properties are not supported yet");
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ?? AsKCallableImpl = UtilKt.asKCallableImpl(kCallable);
            if (AsKCallableImpl == 0) {
                throw new KotlinReflectionInternalError("This callable does not support a default call: " + kCallable);
            }
            objectRef.element = AsKCallableImpl;
            c06621.L$0 = kCallable;
            c06621.L$1 = map;
            c06621.L$2 = objectRef;
            c06621.label = 1;
            C06621 c06622 = c06621;
            objCallDefaultMethod$kotlin_reflection = ((KCallableImpl) objectRef.element).callDefaultMethod$kotlin_reflection(map, c06622);
            if (objCallDefaultMethod$kotlin_reflection == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(c06622);
            }
            if (objCallDefaultMethod$kotlin_reflection == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kCallable = (KCallable) c06621.L$0;
            ResultKt.throwOnFailure(objCallDefaultMethod$kotlin_reflection);
        }
        return (!Intrinsics.areEqual(kCallable.getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(Unit.class)) || kCallable.getReturnType().getIsMarkedNullable()) ? objCallDefaultMethod$kotlin_reflection : Unit.INSTANCE;
    }
}
