package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.DeserializedDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;

/* JADX INFO: compiled from: DeserializedMemberDescriptor.kt */
/* JADX INFO: loaded from: classes3.dex */
public interface DeserializedMemberDescriptor extends DeserializedDescriptor, MemberDescriptor, DescriptorWithContainerSource {

    /* JADX INFO: compiled from: DeserializedMemberDescriptor.kt */
    public enum CoroutinesCompatibilityMode {
        COMPATIBLE,
        NEEDS_WRAPPER,
        INCOMPATIBLE
    }

    DeserializedContainerSource getContainerSource();

    NameResolver getNameResolver();

    MessageLite getProto();

    TypeTable getTypeTable();

    VersionRequirementTable getVersionRequirementTable();

    List<VersionRequirement> getVersionRequirements();

    /* JADX INFO: compiled from: DeserializedMemberDescriptor.kt */
    public static final class DefaultImpls {
        public static List<VersionRequirement> getVersionRequirements(DeserializedMemberDescriptor deserializedMemberDescriptor) {
            return VersionRequirement.Companion.create(deserializedMemberDescriptor.getProto(), deserializedMemberDescriptor.getNameResolver(), deserializedMemberDescriptor.getVersionRequirementTable());
        }
    }
}
