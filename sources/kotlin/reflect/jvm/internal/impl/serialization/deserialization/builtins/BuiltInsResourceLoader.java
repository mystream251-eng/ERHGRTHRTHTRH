package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BuiltInsResourceLoader.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class BuiltInsResourceLoader {
    public final InputStream loadResource(String path) {
        InputStream resourceAsStream;
        Intrinsics.checkNotNullParameter(path, "path");
        ClassLoader classLoader = getClass().getClassLoader();
        return (classLoader == null || (resourceAsStream = classLoader.getResourceAsStream(path)) == null) ? ClassLoader.getSystemResourceAsStream(path) : resourceAsStream;
    }
}
