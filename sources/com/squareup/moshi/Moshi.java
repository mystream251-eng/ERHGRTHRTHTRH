package com.squareup.moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class Moshi {
    static final List<JsonAdapter.Factory> BUILT_IN_FACTORIES;
    private final List<JsonAdapter.Factory> factories;
    private final ThreadLocal<List<DeferredAdapter<?>>> reentrantCalls = new ThreadLocal<>();
    private final Map<Object, JsonAdapter<?>> adapterCache = new LinkedHashMap();

    static {
        ArrayList arrayList = new ArrayList(5);
        BUILT_IN_FACTORIES = arrayList;
        arrayList.add(StandardJsonAdapters.FACTORY);
        BUILT_IN_FACTORIES.add(CollectionJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(MapJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(ArrayJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(ClassJsonAdapter.FACTORY);
    }

    Moshi(Builder builder) {
        ArrayList arrayList = new ArrayList(builder.factories.size() + BUILT_IN_FACTORIES.size());
        arrayList.addAll(builder.factories);
        arrayList.addAll(BUILT_IN_FACTORIES);
        this.factories = Collections.unmodifiableList(arrayList);
    }

    public <T> JsonAdapter<T> adapter(Type type) {
        return adapter(type, Util.NO_ANNOTATIONS);
    }

    public <T> JsonAdapter<T> adapter(Class<T> cls) {
        return adapter(cls, Util.NO_ANNOTATIONS);
    }

    public <T> JsonAdapter<T> adapter(Type type, Class<? extends Annotation> cls) {
        return adapter(type, Collections.singleton(Types.createJsonQualifierImplementation(cls)));
    }

    public <T> JsonAdapter<T> adapter(Type type, Set<? extends Annotation> set) {
        Type typeCanonicalize = Types.canonicalize(type);
        Object objCacheKey = cacheKey(typeCanonicalize, set);
        synchronized (this.adapterCache) {
            JsonAdapter<T> jsonAdapter = (JsonAdapter) this.adapterCache.get(objCacheKey);
            if (jsonAdapter != null) {
                return jsonAdapter;
            }
            List arrayList = this.reentrantCalls.get();
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    DeferredAdapter deferredAdapter = (DeferredAdapter) arrayList.get(i);
                    if (deferredAdapter.cacheKey.equals(objCacheKey)) {
                        return deferredAdapter;
                    }
                }
            } else {
                arrayList = new ArrayList();
                this.reentrantCalls.set((List<DeferredAdapter<?>>) arrayList);
            }
            DeferredAdapter deferredAdapter2 = new DeferredAdapter(objCacheKey);
            arrayList.add(deferredAdapter2);
            try {
                int size2 = this.factories.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    JsonAdapter.AnonymousClass4 anonymousClass4 = (JsonAdapter<T>) this.factories.get(i2).create(typeCanonicalize, set, this);
                    if (anonymousClass4 != null) {
                        deferredAdapter2.ready(anonymousClass4);
                        synchronized (this.adapterCache) {
                            this.adapterCache.put(objCacheKey, anonymousClass4);
                        }
                        arrayList.remove(arrayList.size() - 1);
                        if (arrayList.isEmpty()) {
                            this.reentrantCalls.remove();
                        }
                        return anonymousClass4;
                    }
                }
                arrayList.remove(arrayList.size() - 1);
                if (arrayList.isEmpty()) {
                    this.reentrantCalls.remove();
                }
                throw new IllegalArgumentException("No JsonAdapter for " + typeCanonicalize + " annotated " + set);
            } catch (Throwable th) {
                arrayList.remove(arrayList.size() - 1);
                if (arrayList.isEmpty()) {
                    this.reentrantCalls.remove();
                }
                throw th;
            }
        }
    }

    public <T> JsonAdapter<T> nextAdapter(JsonAdapter.Factory factory, Type type, Set<? extends Annotation> set) {
        Type typeCanonicalize = Types.canonicalize(type);
        int iIndexOf = this.factories.indexOf(factory);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Unable to skip past unknown factory " + factory);
        }
        int size = this.factories.size();
        for (int i = iIndexOf + 1; i < size; i++) {
            JsonAdapter<T> jsonAdapter = (JsonAdapter<T>) this.factories.get(i).create(typeCanonicalize, set, this);
            if (jsonAdapter != null) {
                return jsonAdapter;
            }
        }
        throw new IllegalArgumentException("No next JsonAdapter for " + typeCanonicalize + " annotated " + set);
    }

    public Builder newBuilder() {
        return new Builder().addAll(this.factories.subList(0, this.factories.size() - BUILT_IN_FACTORIES.size()));
    }

    private Object cacheKey(Type type, Set<? extends Annotation> set) {
        return set.isEmpty() ? type : Arrays.asList(type, set);
    }

    public static final class Builder {
        final List<JsonAdapter.Factory> factories = new ArrayList();

        public <T> Builder add(final Type type, final JsonAdapter<T> jsonAdapter) {
            if (type == null) {
                throw new IllegalArgumentException("type == null");
            }
            if (jsonAdapter == null) {
                throw new IllegalArgumentException("jsonAdapter == null");
            }
            return add(new JsonAdapter.Factory() { // from class: com.squareup.moshi.Moshi.Builder.1
                @Override // com.squareup.moshi.JsonAdapter.Factory
                @Nullable
                public JsonAdapter<?> create(Type type2, Set<? extends Annotation> set, Moshi moshi) {
                    if (set.isEmpty() && Util.typesMatch(type, type2)) {
                        return jsonAdapter;
                    }
                    return null;
                }
            });
        }

        public <T> Builder add(final Type type, final Class<? extends Annotation> cls, final JsonAdapter<T> jsonAdapter) {
            if (type == null) {
                throw new IllegalArgumentException("type == null");
            }
            if (cls == null) {
                throw new IllegalArgumentException("annotation == null");
            }
            if (jsonAdapter == null) {
                throw new IllegalArgumentException("jsonAdapter == null");
            }
            if (!cls.isAnnotationPresent(JsonQualifier.class)) {
                throw new IllegalArgumentException(cls + " does not have @JsonQualifier");
            }
            if (cls.getDeclaredMethods().length > 0) {
                throw new IllegalArgumentException("Use JsonAdapter.Factory for annotations with elements");
            }
            return add(new JsonAdapter.Factory() { // from class: com.squareup.moshi.Moshi.Builder.2
                @Override // com.squareup.moshi.JsonAdapter.Factory
                @Nullable
                public JsonAdapter<?> create(Type type2, Set<? extends Annotation> set, Moshi moshi) {
                    if (Util.typesMatch(type, type2) && set.size() == 1 && Util.isAnnotationPresent(set, cls)) {
                        return jsonAdapter;
                    }
                    return null;
                }
            });
        }

        public Builder add(JsonAdapter.Factory factory) {
            if (factory == null) {
                throw new IllegalArgumentException("factory == null");
            }
            this.factories.add(factory);
            return this;
        }

        public Builder add(Object obj) {
            if (obj == null) {
                throw new IllegalArgumentException("adapter == null");
            }
            return add((JsonAdapter.Factory) AdapterMethodsFactory.get(obj));
        }

        Builder addAll(List<JsonAdapter.Factory> list) {
            this.factories.addAll(list);
            return this;
        }

        public Moshi build() {
            return new Moshi(this);
        }
    }

    private static class DeferredAdapter<T> extends JsonAdapter<T> {

        @Nullable
        Object cacheKey;

        @Nullable
        private JsonAdapter<T> delegate;

        DeferredAdapter(Object obj) {
            this.cacheKey = obj;
        }

        void ready(JsonAdapter<T> jsonAdapter) {
            this.delegate = jsonAdapter;
            this.cacheKey = null;
        }

        @Override // com.squareup.moshi.JsonAdapter
        public T fromJson(JsonReader jsonReader) throws IOException {
            JsonAdapter<T> jsonAdapter = this.delegate;
            if (jsonAdapter == null) {
                throw new IllegalStateException("Type adapter isn't ready");
            }
            return jsonAdapter.fromJson(jsonReader);
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, T t) throws IOException {
            JsonAdapter<T> jsonAdapter = this.delegate;
            if (jsonAdapter == null) {
                throw new IllegalStateException("Type adapter isn't ready");
            }
            jsonAdapter.toJson(jsonWriter, t);
        }
    }
}
