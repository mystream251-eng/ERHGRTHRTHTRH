package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* JADX INFO: compiled from: JvmBuiltIns.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JvmBuiltIns extends KotlinBuiltIns {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltIns.class), "settings", "getSettings()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltInsSettings;"))};
    private boolean isAdditionalBuiltInsFeatureSupported;
    private ModuleDescriptor ownerModuleDescriptor;
    private final NotNullLazyValue settings$delegate;

    /* JADX INFO: compiled from: JvmBuiltIns.kt */
    public enum Kind {
        FROM_DEPENDENCIES,
        FROM_CLASS_LOADER,
        FALLBACK
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Kind.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Kind.FROM_DEPENDENCIES.ordinal()] = 1;
            $EnumSwitchMapping$0[Kind.FROM_CLASS_LOADER.ordinal()] = 2;
            $EnumSwitchMapping$0[Kind.FALLBACK.ordinal()] = 3;
        }
    }

    public final JvmBuiltInsSettings getSettings() {
        return (JvmBuiltInsSettings) StorageKt.getValue(this.settings$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltIns(final StorageManager storageManager, Kind kind) {
        super(storageManager);
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(kind, "kind");
        this.isAdditionalBuiltInsFeatureSupported = true;
        this.settings$delegate = storageManager.createLazyValue(new Function0<JvmBuiltInsSettings>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns$settings$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final JvmBuiltInsSettings invoke() {
                ModuleDescriptorImpl builtInsModule = this.this$0.getBuiltInsModule();
                Intrinsics.checkNotNullExpressionValue(builtInsModule, "builtInsModule");
                return new JvmBuiltInsSettings(builtInsModule, storageManager, new Function0<ModuleDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns$settings$2.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final ModuleDescriptor invoke() {
                        ModuleDescriptor moduleDescriptor = JvmBuiltIns$settings$2.this.this$0.ownerModuleDescriptor;
                        if (moduleDescriptor != null) {
                            return moduleDescriptor;
                        }
                        throw new AssertionError("JvmBuiltins has not been initialized properly");
                    }
                }, new Function0<Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns$settings$2.2
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Boolean invoke() {
                        return Boolean.valueOf(invoke2());
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2() {
                        if (JvmBuiltIns$settings$2.this.this$0.ownerModuleDescriptor != null) {
                            return JvmBuiltIns$settings$2.this.this$0.isAdditionalBuiltInsFeatureSupported;
                        }
                        throw new AssertionError("JvmBuiltins has not been initialized properly");
                    }
                });
            }
        });
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i == 2) {
            createBuiltInsModule(false);
        } else {
            if (i != 3) {
                return;
            }
            createBuiltInsModule(true);
        }
    }

    public final void initialize(ModuleDescriptor moduleDescriptor, boolean z) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "moduleDescriptor");
        boolean z2 = this.ownerModuleDescriptor == null;
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("JvmBuiltins repeated initialization");
        }
        this.ownerModuleDescriptor = moduleDescriptor;
        this.isAdditionalBuiltInsFeatureSupported = z;
    }

    @Override // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    protected PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return getSettings();
    }

    @Override // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    protected AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return getSettings();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    public List<ClassDescriptorFactory> getClassDescriptorFactories() {
        Iterable<ClassDescriptorFactory> classDescriptorFactories = super.getClassDescriptorFactories();
        Intrinsics.checkNotNullExpressionValue(classDescriptorFactories, "super.getClassDescriptorFactories()");
        StorageManager storageManager = getStorageManager();
        Intrinsics.checkNotNullExpressionValue(storageManager, "storageManager");
        ModuleDescriptorImpl builtInsModule = getBuiltInsModule();
        Intrinsics.checkNotNullExpressionValue(builtInsModule, "builtInsModule");
        return CollectionsKt.plus(classDescriptorFactories, new JvmBuiltInClassDescriptorFactory(storageManager, builtInsModule, null, 4, null));
    }
}
