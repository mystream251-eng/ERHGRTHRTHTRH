package com.apollographql.apollo.api.internal.json;

import androidx.exifinterface.media.ExifInterface;
import java.io.Closeable;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: use.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u0002H\u0001\"\u0010\b\u0000\u0010\u0002*\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004\"\u0004\b\u0001\u0010\u0001*\u0002H\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0006H\u0086\b¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"use", "R", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/io/Closeable;", "Lcom/apollographql/apollo/api/internal/json/Closeable;", "block", "Lkotlin/Function1;", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "apollo-api"}, k = 2, mv = {1, 1, 16})
public final class UseKt {
    public static final <T extends Closeable, R> R use(T t, Function1<? super T, ? extends R> block) throws IOException {
        Intrinsics.checkParameterIsNotNull(block, "block");
        try {
            R rInvoke = block.invoke(t);
            InlineMarker.finallyStart(1);
            if (t != null) {
                t.close();
            }
            InlineMarker.finallyEnd(1);
            return rInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                if (t != null) {
                    try {
                        t.close();
                    } catch (Throwable unused) {
                    }
                }
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }
}
