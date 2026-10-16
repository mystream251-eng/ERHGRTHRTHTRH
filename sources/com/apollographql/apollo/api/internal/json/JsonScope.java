package com.apollographql.apollo.api.internal.json;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

/* JADX INFO: compiled from: JsonScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00122\u0006\u0010\u0013\u001a\u00020\u0010¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/JsonScope;", "", "()V", "CLOSED", "", "DANGLING_NAME", "EMPTY_ARRAY", "EMPTY_DOCUMENT", "EMPTY_OBJECT", "NONEMPTY_ARRAY", "NONEMPTY_DOCUMENT", "NONEMPTY_OBJECT", "getPath", "", "stackSize", "stack", "", "pathNames", "", "pathIndices", "(I[I[Ljava/lang/String;[I)Ljava/lang/String;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class JsonScope {
    public static final int CLOSED = 8;
    public static final int DANGLING_NAME = 4;
    public static final int EMPTY_ARRAY = 1;
    public static final int EMPTY_DOCUMENT = 6;
    public static final int EMPTY_OBJECT = 3;
    public static final JsonScope INSTANCE = new JsonScope();
    public static final int NONEMPTY_ARRAY = 2;
    public static final int NONEMPTY_DOCUMENT = 7;
    public static final int NONEMPTY_OBJECT = 5;

    private JsonScope() {
    }

    public final String getPath(int stackSize, int[] stack, String[] pathNames, int[] pathIndices) {
        Intrinsics.checkParameterIsNotNull(stack, "stack");
        Intrinsics.checkParameterIsNotNull(pathNames, "pathNames");
        Intrinsics.checkParameterIsNotNull(pathIndices, "pathIndices");
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        for (int i = 0; i < stackSize; i++) {
            int i2 = stack[i];
            if (i2 == 1 || i2 == 2) {
                sb.append('[');
                sb.append(pathIndices[i]);
                sb.append(']');
            } else if (i2 == 3 || i2 == 4 || i2 == 5) {
                sb.append('.');
                if (pathNames[i] != null) {
                    sb.append(pathNames[i]);
                }
            }
        }
        String string = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "result.toString()");
        return string;
    }
}
