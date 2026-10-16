package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: compiled from: CapturedTypeConstructor.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class CapturedTypeConstructorKt {
    public static final KotlinType createCapturedType(TypeProjection typeProjection) {
        Intrinsics.checkNotNullParameter(typeProjection, "typeProjection");
        return new CapturedType(typeProjection, null, false, null, 14, null);
    }

    public static final boolean isCaptured(KotlinType isCaptured) {
        Intrinsics.checkNotNullParameter(isCaptured, "$this$isCaptured");
        return isCaptured.getConstructor() instanceof CapturedTypeConstructor;
    }

    public static /* synthetic */ TypeSubstitution wrapWithCapturingSubstitution$default(TypeSubstitution typeSubstitution, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return wrapWithCapturingSubstitution(typeSubstitution, z);
    }

    public static final TypeSubstitution wrapWithCapturingSubstitution(final TypeSubstitution wrapWithCapturingSubstitution, final boolean z) {
        Intrinsics.checkNotNullParameter(wrapWithCapturingSubstitution, "$this$wrapWithCapturingSubstitution");
        if (wrapWithCapturingSubstitution instanceof IndexedParametersSubstitution) {
            IndexedParametersSubstitution indexedParametersSubstitution = (IndexedParametersSubstitution) wrapWithCapturingSubstitution;
            TypeParameterDescriptor[] parameters = indexedParametersSubstitution.getParameters();
            List<Pair> listZip = ArraysKt.zip(indexedParametersSubstitution.getArguments(), indexedParametersSubstitution.getParameters());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listZip, 10));
            for (Pair pair : listZip) {
                arrayList.add(createCapturedIfNeeded((TypeProjection) pair.getFirst(), (TypeParameterDescriptor) pair.getSecond()));
            }
            Object[] array = arrayList.toArray(new TypeProjection[0]);
            if (array != null) {
                return new IndexedParametersSubstitution(parameters, (TypeProjection[]) array, z);
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return new DelegatedTypeSubstitution(wrapWithCapturingSubstitution) { // from class: kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt.wrapWithCapturingSubstitution.2
            @Override // kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution, kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public boolean approximateContravariantCapturedTypes() {
                return z;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution, kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            /* JADX INFO: renamed from: get */
            public TypeProjection mo1273get(KotlinType key) {
                Intrinsics.checkNotNullParameter(key, "key");
                TypeProjection typeProjectionMo1273get = super.mo1273get(key);
                if (typeProjectionMo1273get == null) {
                    return null;
                }
                ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = key.getConstructor().mo1268getDeclarationDescriptor();
                return CapturedTypeConstructorKt.createCapturedIfNeeded(typeProjectionMo1273get, (TypeParameterDescriptor) (classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeParameterDescriptor ? classifierDescriptorMo1268getDeclarationDescriptor : null));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TypeProjection createCapturedIfNeeded(final TypeProjection typeProjection, TypeParameterDescriptor typeParameterDescriptor) {
        TypeProjectionImpl typeProjectionImpl;
        if (typeParameterDescriptor == null || typeProjection.getProjectionKind() == Variance.INVARIANT) {
            return typeProjection;
        }
        if (typeParameterDescriptor.getVariance() == typeProjection.getProjectionKind()) {
            if (typeProjection.isStarProjection()) {
                StorageManager storageManager = LockBasedStorageManager.NO_LOCKS;
                Intrinsics.checkNotNullExpressionValue(storageManager, "LockBasedStorageManager.NO_LOCKS");
                typeProjectionImpl = new TypeProjectionImpl(new LazyWrappedType(storageManager, new Function0<KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt.createCapturedIfNeeded.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final KotlinType invoke() {
                        KotlinType type = typeProjection.getType();
                        Intrinsics.checkNotNullExpressionValue(type, "this@createCapturedIfNeeded.type");
                        return type;
                    }
                }));
            } else {
                typeProjectionImpl = new TypeProjectionImpl(typeProjection.getType());
            }
            return typeProjectionImpl;
        }
        return new TypeProjectionImpl(createCapturedType(typeProjection));
    }
}
