package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.sam.SamConversionResolverImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;

/* JADX INFO: compiled from: DeserializationComponentsForJava.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class DeserializationComponentsForJava {
    private final DeserializationComponents components;

    public DeserializationComponentsForJava(StorageManager storageManager, ModuleDescriptor moduleDescriptor, DeserializationConfiguration configuration, JavaClassDataFinder classDataFinder, BinaryClassAnnotationAndConstantLoaderImpl annotationAndConstantLoader, LazyJavaPackageFragmentProvider packageFragmentProvider, NotFoundClasses notFoundClasses, ErrorReporter errorReporter, LookupTracker lookupTracker, ContractDeserializer contractDeserializer, NewKotlinTypeChecker kotlinTypeChecker) {
        PlatformDependentDeclarationFilter settings;
        AdditionalClassPartsProvider settings2;
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(classDataFinder, "classDataFinder");
        Intrinsics.checkNotNullParameter(annotationAndConstantLoader, "annotationAndConstantLoader");
        Intrinsics.checkNotNullParameter(packageFragmentProvider, "packageFragmentProvider");
        Intrinsics.checkNotNullParameter(notFoundClasses, "notFoundClasses");
        Intrinsics.checkNotNullParameter(errorReporter, "errorReporter");
        Intrinsics.checkNotNullParameter(lookupTracker, "lookupTracker");
        Intrinsics.checkNotNullParameter(contractDeserializer, "contractDeserializer");
        Intrinsics.checkNotNullParameter(kotlinTypeChecker, "kotlinTypeChecker");
        KotlinBuiltIns builtIns = moduleDescriptor.getBuiltIns();
        JvmBuiltIns jvmBuiltIns = (JvmBuiltIns) (builtIns instanceof JvmBuiltIns ? builtIns : null);
        this.components = new DeserializationComponents(storageManager, moduleDescriptor, configuration, classDataFinder, annotationAndConstantLoader, packageFragmentProvider, LocalClassifierTypeSettings.Default.INSTANCE, errorReporter, lookupTracker, JavaFlexibleTypeDeserializer.INSTANCE, CollectionsKt.emptyList(), notFoundClasses, contractDeserializer, (jvmBuiltIns == null || (settings2 = jvmBuiltIns.getSettings()) == null) ? AdditionalClassPartsProvider.None.INSTANCE : settings2, (jvmBuiltIns == null || (settings = jvmBuiltIns.getSettings()) == null) ? PlatformDependentDeclarationFilter.NoPlatformDependent.INSTANCE : settings, JvmProtoBufUtil.INSTANCE.getEXTENSION_REGISTRY(), kotlinTypeChecker, new SamConversionResolverImpl(storageManager, CollectionsKt.emptyList()), null, 262144, null);
    }

    public final DeserializationComponents getComponents() {
        return this.components;
    }
}
