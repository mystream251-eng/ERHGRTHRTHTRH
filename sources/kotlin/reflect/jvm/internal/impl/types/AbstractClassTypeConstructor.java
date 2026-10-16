package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractClassTypeConstructor extends AbstractTypeConstructor implements TypeConstructor {
    private int hashCode;

    /* JADX WARN: Code duplicated, block: B:21:0x002f  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 3 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 3 || i == 4) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor";
        } else if (i == 2) {
            objArr[0] = "descriptor";
        } else if (i == 3 || i == 4) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor";
        } else {
            objArr[0] = "storageManager";
        }
        if (i == 1) {
            objArr[1] = "getBuiltIns";
        } else if (i == 3 || i == 4) {
            objArr[1] = "getAdditionalNeighboursInSupertypeGraph";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor";
        }
        if (i != 1) {
            if (i == 2) {
                objArr[2] = "hasMeaningfulFqName";
            } else if (i != 3 && i != 4) {
                objArr[2] = "<init>";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 3 && i != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* JADX INFO: renamed from: getDeclarationDescriptor */
    public abstract ClassDescriptor mo1268getDeclarationDescriptor();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractClassTypeConstructor(StorageManager storageManager) {
        super(storageManager);
        if (storageManager == null) {
            $$$reportNull$$$0(0);
        }
        this.hashCode = 0;
    }

    public final int hashCode() {
        int iIdentityHashCode;
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        ClassDescriptor classDescriptorMo1268getDeclarationDescriptor = mo1268getDeclarationDescriptor();
        if (hasMeaningfulFqName(classDescriptorMo1268getDeclarationDescriptor)) {
            iIdentityHashCode = DescriptorUtils.getFqName(classDescriptorMo1268getDeclarationDescriptor).hashCode();
        } else {
            iIdentityHashCode = System.identityHashCode(this);
        }
        this.hashCode = iIdentityHashCode;
        return iIdentityHashCode;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = DescriptorUtilsKt.getBuiltIns(mo1268getDeclarationDescriptor());
        if (builtIns == null) {
            $$$reportNull$$$0(1);
        }
        return builtIns;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeConstructor) || obj.hashCode() != hashCode()) {
            return false;
        }
        TypeConstructor typeConstructor = (TypeConstructor) obj;
        if (typeConstructor.getParameters().size() != getParameters().size()) {
            return false;
        }
        ClassDescriptor classDescriptorMo1268getDeclarationDescriptor = mo1268getDeclarationDescriptor();
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = typeConstructor.mo1268getDeclarationDescriptor();
        if (hasMeaningfulFqName(classDescriptorMo1268getDeclarationDescriptor) && ((classifierDescriptorMo1268getDeclarationDescriptor == null || hasMeaningfulFqName(classifierDescriptorMo1268getDeclarationDescriptor)) && (classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassDescriptor))) {
            return areFqNamesEqual(classDescriptorMo1268getDeclarationDescriptor, (ClassDescriptor) classifierDescriptorMo1268getDeclarationDescriptor);
        }
        return false;
    }

    private static boolean areFqNamesEqual(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        if (!classDescriptor.getName().equals(classDescriptor2.getName())) {
            return false;
        }
        DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
        for (DeclarationDescriptor containingDeclaration2 = classDescriptor2.getContainingDeclaration(); containingDeclaration != null && containingDeclaration2 != null; containingDeclaration2 = containingDeclaration2.getContainingDeclaration()) {
            if (containingDeclaration instanceof ModuleDescriptor) {
                return containingDeclaration2 instanceof ModuleDescriptor;
            }
            if (containingDeclaration2 instanceof ModuleDescriptor) {
                return false;
            }
            if (containingDeclaration instanceof PackageFragmentDescriptor) {
                return (containingDeclaration2 instanceof PackageFragmentDescriptor) && ((PackageFragmentDescriptor) containingDeclaration).getFqName().equals(((PackageFragmentDescriptor) containingDeclaration2).getFqName());
            }
            if ((containingDeclaration2 instanceof PackageFragmentDescriptor) || !containingDeclaration.getName().equals(containingDeclaration2.getName())) {
                return false;
            }
            containingDeclaration = containingDeclaration.getContainingDeclaration();
        }
        return true;
    }

    private static boolean hasMeaningfulFqName(ClassifierDescriptor classifierDescriptor) {
        if (classifierDescriptor == null) {
            $$$reportNull$$$0(2);
        }
        return (ErrorUtils.isError(classifierDescriptor) || DescriptorUtils.isLocal(classifierDescriptor)) ? false : true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    protected Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean z) {
        DeclarationDescriptor containingDeclaration = mo1268getDeclarationDescriptor().getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            List listEmptyList = Collections.emptyList();
            if (listEmptyList == null) {
                $$$reportNull$$$0(3);
            }
            return listEmptyList;
        }
        SmartList smartList = new SmartList();
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        smartList.add(classDescriptor.getDefaultType());
        ClassDescriptor classDescriptorMo1261getCompanionObjectDescriptor = classDescriptor.mo1261getCompanionObjectDescriptor();
        if (z && classDescriptorMo1261getCompanionObjectDescriptor != null) {
            smartList.add(classDescriptorMo1261getCompanionObjectDescriptor.getDefaultType());
        }
        return smartList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    protected KotlinType defaultSupertypeIfEmpty() {
        if (KotlinBuiltIns.isSpecialClassWithNoSupertypes(mo1268getDeclarationDescriptor())) {
            return null;
        }
        return getBuiltIns().getAnyType();
    }
}
