package com.apollographql.apollo.internal.field;

import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.internal.FieldValueResolver;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MapFieldValueResolver implements FieldValueResolver<Map<String, Object>> {
    @Override // com.apollographql.apollo.api.internal.FieldValueResolver
    public <T> T valueFor(Map<String, Object> map, ResponseField responseField) {
        return (T) map.get(responseField.getResponseName());
    }
}
