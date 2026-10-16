package com.squareup.moshi;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
final class Util {
    public static final Set<Annotation> NO_ANNOTATIONS = Collections.emptySet();

    private Util() {
    }

    public static boolean typesMatch(Type type, Type type2) {
        return type.equals(type2);
    }

    public static Set<? extends Annotation> jsonAnnotations(AnnotatedElement annotatedElement) {
        return jsonAnnotations(annotatedElement.getAnnotations());
    }

    public static Set<? extends Annotation> jsonAnnotations(Annotation[] annotationArr) {
        LinkedHashSet linkedHashSet = null;
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.add(annotation);
            }
        }
        return linkedHashSet != null ? Collections.unmodifiableSet(linkedHashSet) : NO_ANNOTATIONS;
    }

    public static boolean isAnnotationPresent(Set<? extends Annotation> set, Class<? extends Annotation> cls) {
        if (set.isEmpty()) {
            return false;
        }
        Iterator<? extends Annotation> it = set.iterator();
        while (it.hasNext()) {
            if (it.next().annotationType() == cls) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNullable(Annotation[] annotationArr) {
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().getSimpleName().equals("Nullable")) {
                return true;
            }
        }
        return false;
    }
}
