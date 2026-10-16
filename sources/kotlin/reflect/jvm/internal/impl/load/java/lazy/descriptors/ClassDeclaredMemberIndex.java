package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: DeclaredMemberIndex.kt */
/* JADX INFO: loaded from: classes3.dex */
public class ClassDeclaredMemberIndex implements DeclaredMemberIndex {
    private final Map<Name, JavaField> fields;
    private final JavaClass jClass;
    private final Function1<JavaMember, Boolean> memberFilter;
    private final Function1<JavaMethod, Boolean> methodFilter;
    private final Map<Name, List<JavaMethod>> methods;

    /* JADX WARN: Multi-variable type inference failed */
    public ClassDeclaredMemberIndex(JavaClass jClass, Function1<? super JavaMember, Boolean> memberFilter) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        Intrinsics.checkNotNullParameter(memberFilter, "memberFilter");
        this.jClass = jClass;
        this.memberFilter = memberFilter;
        this.methodFilter = new Function1<JavaMethod, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.ClassDeclaredMemberIndex$methodFilter$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(JavaMethod javaMethod) {
                return Boolean.valueOf(invoke2(javaMethod));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(JavaMethod m) {
                Intrinsics.checkNotNullParameter(m, "m");
                return ((Boolean) this.this$0.memberFilter.invoke(m)).booleanValue() && !DescriptorResolverUtils.isObjectMethodInInterface(m);
            }
        };
        Sequence sequenceFilter = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getMethods()), this.methodFilter);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : sequenceFilter) {
            Name name = ((JavaMethod) obj).getName();
            Object obj2 = linkedHashMap.get(name);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(name, obj2);
            }
            ((List) obj2).add(obj);
        }
        this.methods = linkedHashMap;
        Sequence sequenceFilter2 = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getFields()), this.memberFilter);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj3 : sequenceFilter2) {
            linkedHashMap2.put(((JavaField) obj3).getName(), obj3);
        }
        this.fields = linkedHashMap2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Collection<JavaMethod> findMethodsByName(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<JavaMethod> listEmptyList = this.methods.get(name);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Set<Name> getMethodNames() {
        Sequence sequenceFilter = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getMethods()), this.methodFilter);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = sequenceFilter.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((JavaMethod) it.next()).getName());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public JavaField findFieldByName(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.fields.get(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Set<Name> getFieldNames() {
        Sequence sequenceFilter = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getFields()), this.memberFilter);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = sequenceFilter.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((JavaField) it.next()).getName());
        }
        return linkedHashSet;
    }
}
