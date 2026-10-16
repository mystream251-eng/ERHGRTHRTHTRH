package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.slf4j.Marker;

/* JADX INFO: compiled from: RawType.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class RawTypeImpl extends FlexibleType implements RawType {

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl$render$1, reason: invalid class name */
    /* JADX INFO: compiled from: RawType.kt */
    static final class AnonymousClass1 extends Lambda implements Function2<String, String, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Boolean invoke(String str, String str2) {
            return Boolean.valueOf(invoke2(str, str2));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(String first, String second) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            return Intrinsics.areEqual(first, StringsKt.removePrefix(second, (CharSequence) "out ")) || Intrinsics.areEqual(second, Marker.ANY_MARKER);
        }
    }

    private RawTypeImpl(SimpleType simpleType, SimpleType simpleType2, boolean z) {
        super(simpleType, simpleType2);
        if (z) {
            return;
        }
        boolean zIsSubtypeOf = KotlinTypeChecker.DEFAULT.isSubtypeOf(simpleType, simpleType2);
        if (!_Assertions.ENABLED || zIsSubtypeOf) {
            return;
        }
        throw new AssertionError("Lower bound " + simpleType + " of a flexible type must be a subtype of the upper bound " + simpleType2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RawTypeImpl(SimpleType lowerBound, SimpleType upperBound) {
        this(lowerBound, upperBound, false);
        Intrinsics.checkNotNullParameter(lowerBound, "lowerBound");
        Intrinsics.checkNotNullParameter(upperBound, "upperBound");
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public SimpleType getDelegate() {
        return getLowerBound();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = getConstructor().mo1268getDeclarationDescriptor();
        if (!(classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassDescriptor)) {
            classifierDescriptorMo1268getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo1268getDeclarationDescriptor;
        if (classDescriptor == null) {
            throw new IllegalStateException(("Incorrect classifier: " + getConstructor().mo1268getDeclarationDescriptor()).toString());
        }
        MemberScope memberScope = classDescriptor.getMemberScope(RawSubstitution.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(memberScope, "classDescriptor.getMemberScope(RawSubstitution)");
        return memberScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public RawTypeImpl replaceAnnotations(Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return new RawTypeImpl(getLowerBound().replaceAnnotations(newAnnotations), getUpperBound().replaceAnnotations(newAnnotations));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public RawTypeImpl makeNullableAsSpecified(boolean z) {
        return new RawTypeImpl(getLowerBound().makeNullableAsSpecified(z), getUpperBound().makeNullableAsSpecified(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public String render(final DescriptorRenderer renderer, DescriptorRendererOptions options) {
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        Intrinsics.checkNotNullParameter(options, "options");
        AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
        Function1<KotlinType, List<? extends String>> function1 = new Function1<KotlinType, List<? extends String>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl.render.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<String> invoke(KotlinType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                List<TypeProjection> arguments = type.getArguments();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
                Iterator<T> it = arguments.iterator();
                while (it.hasNext()) {
                    arrayList.add(renderer.renderTypeProjection((TypeProjection) it.next()));
                }
                return arrayList;
            }
        };
        AnonymousClass3 anonymousClass3 = AnonymousClass3.INSTANCE;
        String strRenderType = renderer.renderType(getLowerBound());
        String strRenderType2 = renderer.renderType(getUpperBound());
        if (options.getDebugMode()) {
            return "raw (" + strRenderType + ".." + strRenderType2 + ')';
        }
        if (getUpperBound().getArguments().isEmpty()) {
            return renderer.renderFlexibleType(strRenderType, strRenderType2, TypeUtilsKt.getBuiltIns(this));
        }
        List<String> listInvoke = function1.invoke((KotlinType) getLowerBound());
        List<String> listInvoke2 = function1.invoke((KotlinType) getUpperBound());
        List<String> list = listInvoke;
        String strJoinToString$default = CollectionsKt.joinToString$default(list, ", ", null, null, 0, null, new Function1<String, CharSequence>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl$render$newArgs$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return "(raw) " + it;
            }
        }, 30, null);
        List<Pair> listZip = CollectionsKt.zip(list, listInvoke2);
        boolean z = true;
        if (!(listZip instanceof Collection) || !listZip.isEmpty()) {
            for (Pair pair : listZip) {
                if (!AnonymousClass1.INSTANCE.invoke2((String) pair.getFirst(), (String) pair.getSecond())) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            strRenderType2 = anonymousClass3.invoke(strRenderType2, strJoinToString$default);
        }
        String strInvoke = anonymousClass3.invoke(strRenderType, strJoinToString$default);
        return Intrinsics.areEqual(strInvoke, strRenderType2) ? strInvoke : renderer.renderFlexibleType(strInvoke, strRenderType2, TypeUtilsKt.getBuiltIns(this));
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl$render$3, reason: invalid class name */
    /* JADX INFO: compiled from: RawType.kt */
    static final class AnonymousClass3 extends Lambda implements Function2<String, String, String> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String replaceArgs, String newArgs) {
            Intrinsics.checkNotNullParameter(replaceArgs, "$this$replaceArgs");
            Intrinsics.checkNotNullParameter(newArgs, "newArgs");
            if (!StringsKt.contains$default((CharSequence) replaceArgs, Typography.less, false, 2, (Object) null)) {
                return replaceArgs;
            }
            return StringsKt.substringBefore$default(replaceArgs, Typography.less, (String) null, 2, (Object) null) + Typography.less + newArgs + Typography.greater + StringsKt.substringAfterLast$default(replaceArgs, Typography.greater, (String) null, 2, (Object) null);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    public FlexibleType refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        KotlinType kotlinTypeRefineType = kotlinTypeRefiner.refineType(getLowerBound());
        if (kotlinTypeRefineType == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        }
        SimpleType simpleType = (SimpleType) kotlinTypeRefineType;
        KotlinType kotlinTypeRefineType2 = kotlinTypeRefiner.refineType(getUpperBound());
        if (kotlinTypeRefineType2 != null) {
            return new RawTypeImpl(simpleType, (SimpleType) kotlinTypeRefineType2, true);
        }
        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }
}
