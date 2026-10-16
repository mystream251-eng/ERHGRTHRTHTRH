package com.apollographql.apollo.api;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX INFO: compiled from: CustomTypeAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0019\u0010\u0003\u001a\u00028\u00002\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H&¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo/api/CustomTypeAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "", "decode", "value", "Lcom/apollographql/apollo/api/CustomTypeValue;", "(Lcom/apollographql/apollo/api/CustomTypeValue;)Ljava/lang/Object;", "encode", "(Ljava/lang/Object;)Lcom/apollographql/apollo/api/CustomTypeValue;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface CustomTypeAdapter<T> {
    T decode(CustomTypeValue<?> value);

    CustomTypeValue<?> encode(T value);
}
