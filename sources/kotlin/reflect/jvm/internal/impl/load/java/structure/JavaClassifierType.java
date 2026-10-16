package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;

/* JADX INFO: compiled from: javaTypes.kt */
/* JADX INFO: loaded from: classes3.dex */
public interface JavaClassifierType extends JavaAnnotationOwner, JavaType {
    JavaClassifier getClassifier();

    String getClassifierQualifiedName();

    String getPresentableText();

    List<JavaType> getTypeArguments();

    boolean isRaw();
}
