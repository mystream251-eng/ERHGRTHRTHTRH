package com.apollographql.apollo.api.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class UnmodifiableMapBuilder<K, V> {
    private final Map<K, V> map;

    public UnmodifiableMapBuilder(int i) {
        this.map = new LinkedHashMap(i);
    }

    public UnmodifiableMapBuilder() {
        this.map = new LinkedHashMap();
    }

    public UnmodifiableMapBuilder<K, V> put(K k, V v) {
        this.map.put(k, v);
        return this;
    }

    public Map<K, V> build() {
        return Collections.unmodifiableMap(this.map);
    }
}
