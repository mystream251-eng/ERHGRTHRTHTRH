package com.apollographql.apollo.request;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class RequestHeaders {
    public static final RequestHeaders NONE = new RequestHeaders(Collections.emptyMap());
    private final Map<String, String> headerMap;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private final Map<String, String> headerMap = new LinkedHashMap();

        public Builder addHeader(String str, String str2) {
            this.headerMap.put(str, str2);
            return this;
        }

        public Builder addHeaders(Map<String, String> map) {
            this.headerMap.putAll(map);
            return this;
        }

        public RequestHeaders build() {
            return new RequestHeaders(this.headerMap);
        }
    }

    public Builder toBuilder() {
        Builder builder = builder();
        builder.addHeaders(this.headerMap);
        return builder;
    }

    RequestHeaders(Map<String, String> map) {
        this.headerMap = map;
    }

    public Set<String> headers() {
        return this.headerMap.keySet();
    }

    public String headerValue(String str) {
        return this.headerMap.get(str);
    }

    public boolean hasHeader(String str) {
        return this.headerMap.containsKey(str);
    }
}
