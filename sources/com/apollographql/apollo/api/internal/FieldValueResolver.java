package com.apollographql.apollo.api.internal;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.ResponseField;
import kotlin.Metadata;

/* JADX INFO: compiled from: FieldValueResolver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J%\u0010\u0003\u001a\u0004\u0018\u0001H\u0004\"\u0004\b\u0001\u0010\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo/api/internal/FieldValueResolver;", "R", "", "valueFor", ExifInterface.GPS_DIRECTION_TRUE, "recordSet", "field", "Lcom/apollographql/apollo/api/ResponseField;", "(Ljava/lang/Object;Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface FieldValueResolver<R> {
    <T> T valueFor(R recordSet, ResponseField field);
}
