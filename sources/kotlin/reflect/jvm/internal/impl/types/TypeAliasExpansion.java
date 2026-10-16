package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

/* JADX INFO: compiled from: TypeAliasExpansion.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeAliasExpansion {
    public static final Companion Companion = new Companion(null);
    private final List<TypeProjection> arguments;
    private final TypeAliasDescriptor descriptor;
    private final Map<TypeParameterDescriptor, TypeProjection> mapping;
    private final TypeAliasExpansion parent;

    /* JADX WARN: Multi-variable type inference failed */
    private TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List<? extends TypeProjection> list, Map<TypeParameterDescriptor, ? extends TypeProjection> map) {
        this.parent = typeAliasExpansion;
        this.descriptor = typeAliasDescriptor;
        this.arguments = list;
        this.mapping = map;
    }

    public /* synthetic */ TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List list, Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeAliasExpansion, typeAliasDescriptor, list, map);
    }

    public final TypeAliasDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final List<TypeProjection> getArguments() {
        return this.arguments;
    }

    public final TypeProjection getReplacement(TypeConstructor constructor) {
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = constructor.mo1268getDeclarationDescriptor();
        if (classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return this.mapping.get(classifierDescriptorMo1268getDeclarationDescriptor);
        }
        return null;
    }

    public final boolean isRecursion(TypeAliasDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (!Intrinsics.areEqual(this.descriptor, descriptor)) {
            TypeAliasExpansion typeAliasExpansion = this.parent;
            if (!(typeAliasExpansion != null ? typeAliasExpansion.isRecursion(descriptor) : false)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: TypeAliasExpansion.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TypeAliasExpansion create(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List<? extends TypeProjection> arguments) {
            Intrinsics.checkNotNullParameter(typeAliasDescriptor, "typeAliasDescriptor");
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            TypeConstructor typeConstructor = typeAliasDescriptor.getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor, "typeAliasDescriptor.typeConstructor");
            List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "typeAliasDescriptor.typeConstructor.parameters");
            List<TypeParameterDescriptor> list = parameters;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (TypeParameterDescriptor it : list) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                arrayList.add(it.getOriginal());
            }
            return new TypeAliasExpansion(typeAliasExpansion, typeAliasDescriptor, arguments, MapsKt.toMap(CollectionsKt.zip(arrayList, arguments)), null);
        }
    }
}
