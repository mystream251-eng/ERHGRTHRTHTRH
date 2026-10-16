package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: ModuleDescriptorImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
public interface ModuleDependencies {
    List<ModuleDescriptorImpl> getAllDependencies();

    List<ModuleDescriptorImpl> getExpectedByDependencies();

    Set<ModuleDescriptorImpl> getModulesWhoseInternalsAreVisible();
}
