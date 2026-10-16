package com.apollographql.apollo.api.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\b\u0010\u0005\u001a\u0004\u0018\u0001H\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\u0006\u0010\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/api/internal/Utils;", "", "()V", "__checkNotNull", ExifInterface.GPS_DIRECTION_TRUE, "reference", "checkNotNull", "(Ljava/lang/Object;)Ljava/lang/Object;", "errorMessage", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class Utils {
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    @JvmStatic
    public static final <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    @JvmStatic
    public static final <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }
}
