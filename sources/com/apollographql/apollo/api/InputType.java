package com.apollographql.apollo.api;

import com.apollographql.apollo.api.internal.InputFieldMarshaller;
import kotlin.Metadata;

/* JADX INFO: compiled from: InputType.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo/api/InputType;", "", "marshaller", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface InputType {
    InputFieldMarshaller marshaller();
}
