package com.squareup.moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class ClassJsonAdapter<T> extends JsonAdapter<T> {
    public static final JsonAdapter.Factory FACTORY = new JsonAdapter.Factory() { // from class: com.squareup.moshi.ClassJsonAdapter.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        @Nullable
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            Class<?> rawType = Types.getRawType(type);
            if (rawType.isInterface() || rawType.isEnum()) {
                return null;
            }
            if (ClassJsonAdapter.isPlatformType(rawType) && !Types.isAllowedPlatformType(rawType)) {
                throw new IllegalArgumentException("Platform " + type + " annotated " + set + " requires explicit JsonAdapter to be registered");
            }
            if (!set.isEmpty()) {
                return null;
            }
            if (rawType.getEnclosingClass() != null && !Modifier.isStatic(rawType.getModifiers())) {
                if (rawType.getSimpleName().isEmpty()) {
                    throw new IllegalArgumentException("Cannot serialize anonymous class " + rawType.getName());
                }
                throw new IllegalArgumentException("Cannot serialize non-static nested class " + rawType.getName());
            }
            if (Modifier.isAbstract(rawType.getModifiers())) {
                throw new IllegalArgumentException("Cannot serialize abstract class " + rawType.getName());
            }
            ClassFactory classFactory = ClassFactory.get(rawType);
            TreeMap treeMap = new TreeMap();
            while (type != Object.class) {
                createFieldBindings(moshi, type, treeMap);
                type = Types.getGenericSuperclass(type);
            }
            return new ClassJsonAdapter(classFactory, treeMap).nullSafe();
        }

        private void createFieldBindings(Moshi moshi, Type type, Map<String, FieldBinding<?>> map) {
            Class<?> rawType = Types.getRawType(type);
            boolean zIsPlatformType = ClassJsonAdapter.isPlatformType(rawType);
            for (Field field : rawType.getDeclaredFields()) {
                if (includeField(zIsPlatformType, field.getModifiers())) {
                    JsonAdapter<T> jsonAdapterAdapter = moshi.adapter(Types.resolve(type, rawType, field.getGenericType()), Util.jsonAnnotations(field));
                    field.setAccessible(true);
                    Json json = (Json) field.getAnnotation(Json.class);
                    String strName = json != null ? json.name() : field.getName();
                    FieldBinding<?> fieldBinding = new FieldBinding<>(strName, field, jsonAdapterAdapter);
                    FieldBinding<?> fieldBindingPut = map.put(strName, fieldBinding);
                    if (fieldBindingPut != null) {
                        throw new IllegalArgumentException("Conflicting fields:\n    " + fieldBindingPut.field + "\n    " + fieldBinding.field);
                    }
                }
            }
        }

        private boolean includeField(boolean z, int i) {
            if (Modifier.isStatic(i) || Modifier.isTransient(i)) {
                return false;
            }
            return Modifier.isPublic(i) || Modifier.isProtected(i) || !z;
        }
    };
    private final ClassFactory<T> classFactory;
    private final FieldBinding<?>[] fieldsArray;
    private final JsonReader.Options options;

    static boolean isPlatformType(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("android.") || name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("kotlin.") || name.startsWith("scala.");
    }

    ClassJsonAdapter(ClassFactory<T> classFactory, Map<String, FieldBinding<?>> map) {
        this.classFactory = classFactory;
        this.fieldsArray = (FieldBinding[]) map.values().toArray(new FieldBinding[map.size()]);
        this.options = JsonReader.Options.of((String[]) map.keySet().toArray(new String[map.size()]));
    }

    @Override // com.squareup.moshi.JsonAdapter
    public T fromJson(JsonReader jsonReader) throws IOException {
        try {
            T tNewInstance = this.classFactory.newInstance();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    int iSelectName = jsonReader.selectName(this.options);
                    if (iSelectName == -1) {
                        jsonReader.nextName();
                        jsonReader.skipValue();
                    } else {
                        this.fieldsArray[iSelectName].read(jsonReader, tNewInstance);
                    }
                }
                jsonReader.endObject();
                return tNewInstance;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        } catch (IllegalAccessException unused2) {
            throw new AssertionError();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            if (targetException instanceof Error) {
                throw ((Error) targetException);
            }
            throw new RuntimeException(targetException);
        }
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter, T t) throws IOException {
        try {
            jsonWriter.beginObject();
            for (FieldBinding<?> fieldBinding : this.fieldsArray) {
                jsonWriter.name(fieldBinding.name);
                fieldBinding.write(jsonWriter, t);
            }
            jsonWriter.endObject();
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        }
    }

    public String toString() {
        return "JsonAdapter(" + this.classFactory + ")";
    }

    static class FieldBinding<T> {
        final JsonAdapter<T> adapter;
        final Field field;
        final String name;

        FieldBinding(String str, Field field, JsonAdapter<T> jsonAdapter) {
            this.name = str;
            this.field = field;
            this.adapter = jsonAdapter;
        }

        void read(JsonReader jsonReader, Object obj) throws IllegalAccessException, IOException {
            this.field.set(obj, this.adapter.fromJson(jsonReader));
        }

        void write(JsonWriter jsonWriter, Object obj) throws IllegalAccessException, IOException {
            this.adapter.toJson(jsonWriter, (T) this.field.get(obj));
        }
    }
}
