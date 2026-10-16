package io.sentry.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface PropertiesProvider {
    Map<String, String> getMap(String str);

    String getProperty(String str);

    default List<String> getList(String str) {
        String property = getProperty(str);
        return property != null ? Arrays.asList(property.split(",")) : Collections.emptyList();
    }

    default String getProperty(String str, String str2) {
        String property = getProperty(str);
        return property != null ? property : str2;
    }

    default Boolean getBooleanProperty(String str) {
        String property = getProperty(str);
        if (property != null) {
            return Boolean.valueOf(property);
        }
        return null;
    }

    default Double getDoubleProperty(String str) {
        String property = getProperty(str);
        if (property != null) {
            return Double.valueOf(property);
        }
        return null;
    }
}
