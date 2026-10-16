package io.sentry.config;

import io.sentry.SystemOutLogger;
import java.util.ArrayList;
import java.util.Properties;

/* JADX INFO: loaded from: classes2.dex */
public final class PropertiesProviderFactory {
    public static PropertiesProvider create() {
        Properties propertiesLoad;
        Properties propertiesLoad2;
        SystemOutLogger systemOutLogger = new SystemOutLogger();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SystemPropertyPropertiesProvider());
        arrayList.add(new EnvironmentVariablePropertiesProvider());
        String property = System.getProperty("sentry.properties.file");
        if (property != null && (propertiesLoad2 = new FilesystemPropertiesLoader(property, systemOutLogger).load()) != null) {
            arrayList.add(new SimplePropertiesProvider(propertiesLoad2));
        }
        String str = System.getenv("SENTRY_PROPERTIES_FILE");
        if (str != null && (propertiesLoad = new FilesystemPropertiesLoader(str, systemOutLogger).load()) != null) {
            arrayList.add(new SimplePropertiesProvider(propertiesLoad));
        }
        Properties propertiesLoad3 = new ClasspathPropertiesLoader(systemOutLogger).load();
        if (propertiesLoad3 != null) {
            arrayList.add(new SimplePropertiesProvider(propertiesLoad3));
        }
        Properties propertiesLoad4 = new FilesystemPropertiesLoader("sentry.properties", systemOutLogger).load();
        if (propertiesLoad4 != null) {
            arrayList.add(new SimplePropertiesProvider(propertiesLoad4));
        }
        return new CompositePropertiesProvider(arrayList);
    }
}
