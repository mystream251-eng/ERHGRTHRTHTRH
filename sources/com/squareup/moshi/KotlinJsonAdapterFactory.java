package com.squareup.moshi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;

/* JADX INFO: compiled from: KotlinJsonAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/squareup/moshi/KotlinJsonAdapterFactory;", "Lcom/squareup/moshi/JsonAdapter$Factory;", "()V", "create", "Lcom/squareup/moshi/JsonAdapter;", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "moshi", "Lcom/squareup/moshi/Moshi;", "moshi-kotlin"}, k = 1, mv = {1, 1, 5})
public final class KotlinJsonAdapterFactory implements JsonAdapter.Factory {
    @Override // com.squareup.moshi.JsonAdapter.Factory
    public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
        KFunction primaryConstructor;
        String name;
        Object next;
        String name2;
        Object next2;
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(moshi, "moshi");
        if (!annotations.isEmpty()) {
            return null;
        }
        Class<?> rawType = Types.getRawType(type);
        if (rawType.isEnum() || !rawType.isAnnotationPresent(KotlinJsonAdapterKt.KOTLIN_METADATA) || ClassJsonAdapter.isPlatformType(rawType) || (primaryConstructor = KClasses.getPrimaryConstructor(JvmClassMappingKt.getKotlinClass(rawType))) == null) {
            return null;
        }
        List<KParameter> parameters = primaryConstructor.getParameters();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(parameters, 10)), 16));
        for (Object obj : parameters) {
            linkedHashMap.put(((KParameter) obj).getName(), obj);
        }
        KCallablesJvm.setAccessible(primaryConstructor, true);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (KProperty1 kProperty1 : KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(rawType))) {
            Field javaField = ReflectJvmMapping.getJavaField(kProperty1);
            if (!Modifier.isTransient(javaField != null ? javaField.getModifiers() : 0)) {
                KParameter kParameter = (KParameter) linkedHashMap.get(kProperty1.getName());
                if ((kProperty1 instanceof KMutableProperty1) || kParameter != null) {
                    KCallablesJvm.setAccessible(kProperty1, true);
                    List<Annotation> annotations2 = kProperty1.getAnnotations();
                    Iterator<T> it = kProperty1.getAnnotations().iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!(((Annotation) next) instanceof Json));
                    Json json = (Json) next;
                    if (kParameter != null) {
                        annotations2 = CollectionsKt.plus((Collection) annotations2, (Iterable) kParameter.getAnnotations());
                        if (json == null) {
                            Iterator<T> it2 = kParameter.getAnnotations().iterator();
                            do {
                                if (!it2.hasNext()) {
                                    next2 = null;
                                    break;
                                }
                                next2 = it2.next();
                            } while (!(((Annotation) next2) instanceof Json));
                            json = (Json) next2;
                        }
                    }
                    if (json == null || (name2 = json.name()) == null) {
                        name2 = kProperty1.getName();
                    }
                    Type javaType = ReflectJvmMapping.getJavaType(kProperty1.getReturnType());
                    List<Annotation> list = annotations2;
                    if (list == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                    }
                    List<Annotation> list2 = list;
                    Object[] array = list2.toArray(new Annotation[list2.size()]);
                    if (array == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    JsonAdapter adapter = moshi.adapter(javaType, Util.jsonAnnotations((Annotation[]) array));
                    LinkedHashMap linkedHashMap3 = linkedHashMap2;
                    String name3 = kProperty1.getName();
                    Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                    if (kProperty1 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KProperty1<kotlin.Any, kotlin.Any?>");
                    }
                    linkedHashMap3.put(name3, new KotlinJsonAdapter.Binding(name2, adapter, kProperty1, kParameter));
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (KParameter kParameter2 : primaryConstructor.getParameters()) {
            KotlinJsonAdapter.Binding binding = (KotlinJsonAdapter.Binding) TypeIntrinsics.asMutableMap(linkedHashMap2).remove(kParameter2.getName());
            if (binding == null && !kParameter2.isOptional()) {
                throw new IllegalArgumentException("No property for required constructor " + kParameter2);
            }
            arrayList.add(binding);
        }
        Collection collectionValues = linkedHashMap2.values();
        Intrinsics.checkExpressionValueIsNotNull(collectionValues, "bindingsByName.values");
        CollectionsKt.addAll(arrayList, collectionValues);
        ArrayList<KotlinJsonAdapter.Binding> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (KotlinJsonAdapter.Binding binding2 : arrayList2) {
            if (binding2 == null || (name = binding2.getName()) == null) {
                name = "\u0000";
            }
            arrayList3.add(name);
        }
        ArrayList arrayList4 = arrayList3;
        Object[] array2 = arrayList4.toArray(new String[arrayList4.size()]);
        if (array2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        String[] strArr = (String[]) array2;
        JsonReader.Options options = JsonReader.Options.of((String[]) Arrays.copyOf(strArr, strArr.length));
        Intrinsics.checkExpressionValueIsNotNull(options, "options");
        return new KotlinJsonAdapter(primaryConstructor, arrayList, options).nullSafe();
    }
}
