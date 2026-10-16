package com.apollographql.apollo.api.internal;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResponseWriter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002'(J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H&J\u001f\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u001f\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0012H&¢\u0006\u0002\u0010\u0013Jf\u0010\u0014\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u0002H\u0015\u0018\u00010\u00172>\u0010\u0018\u001a:\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u0002H\u0015\u0018\u00010\u0017¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00030\u0019H\u0016J4\u0010\u0014\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u0002H\u0015\u0018\u00010\u00172\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00150 H&J\u001f\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\"H&¢\u0006\u0002\u0010#J\u001a\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u001a\u0010%\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010&H&¨\u0006)"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseWriter;", "", "writeBoolean", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "value", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Boolean;)V", "writeCustom", "Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;", "writeDouble", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Double;)V", "writeFragment", "marshaller", "Lcom/apollographql/apollo/api/internal/ResponseFieldMarshaller;", "writeInt", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Integer;)V", "writeList", ExifInterface.GPS_DIRECTION_TRUE, "values", "", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "items", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListItemWriter;", "listItemWriter", "listWriter", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListWriter;", "writeLong", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Long;)V", "writeObject", "writeString", "", "ListItemWriter", "ListWriter", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface ResponseWriter {

    /* JADX INFO: compiled from: ResponseWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J \u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseWriter$ListWriter;", ExifInterface.GPS_DIRECTION_TRUE, "", "write", "", "items", "", "listItemWriter", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListItemWriter;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ListWriter<T> {
        void write(List<? extends T> items, ListItemWriter listItemWriter);
    }

    void writeBoolean(ResponseField field, Boolean value);

    void writeCustom(ResponseField.CustomTypeField field, Object value);

    void writeDouble(ResponseField field, Double value);

    void writeFragment(ResponseFieldMarshaller marshaller);

    void writeInt(ResponseField field, Integer value);

    <T> void writeList(ResponseField field, List<? extends T> values, ListWriter<T> listWriter);

    <T> void writeList(ResponseField field, List<? extends T> values, Function2<? super List<? extends T>, ? super ListItemWriter, Unit> block);

    void writeLong(ResponseField field, Long value);

    void writeObject(ResponseField field, ResponseFieldMarshaller marshaller);

    void writeString(ResponseField field, String value);

    /* JADX INFO: compiled from: ResponseWriter.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static <T> void writeList(ResponseWriter responseWriter, ResponseField field, List<? extends T> list, final Function2<? super List<? extends T>, ? super ListItemWriter, Unit> block) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(block, "block");
            responseWriter.writeList(field, list, new ListWriter<T>() { // from class: com.apollographql.apollo.api.internal.ResponseWriter.writeList.1
                @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                public void write(List<? extends T> items, ListItemWriter listItemWriter) {
                    Intrinsics.checkParameterIsNotNull(listItemWriter, "listItemWriter");
                    block.invoke(items, listItemWriter);
                }
            });
        }
    }

    /* JADX INFO: compiled from: ResponseWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H&J\u0017\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010\fJ\u0017\u0010\r\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u000eH&¢\u0006\u0002\u0010\u000fJ^\u0010\u0010\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\u0011\u0018\u00010\u00132>\u0010\u0014\u001a:\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u0002H\u0011\u0018\u00010\u0013¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00030\u0015H\u0016J,\u0010\u0010\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\u0011\u0018\u00010\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00110\u001aH&J\u0017\u0010\u001b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u001cH&¢\u0006\u0002\u0010\u001dJ\u0012\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010 H&J\u0012\u0010!\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\"H&¨\u0006#"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseWriter$ListItemWriter;", "", "writeBoolean", "", "value", "", "(Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "writeDouble", "", "(Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/Integer;)V", "writeList", ExifInterface.GPS_DIRECTION_TRUE, "items", "", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "listItemWriter", "listWriter", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListWriter;", "writeLong", "", "(Ljava/lang/Long;)V", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/ResponseFieldMarshaller;", "writeString", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ListItemWriter {
        void writeBoolean(Boolean value);

        void writeCustom(ScalarType scalarType, Object value);

        void writeDouble(Double value);

        void writeInt(Integer value);

        <T> void writeList(List<? extends T> items, ListWriter<T> listWriter);

        <T> void writeList(List<? extends T> items, Function2<? super List<? extends T>, ? super ListItemWriter, Unit> block);

        void writeLong(Long value);

        void writeObject(ResponseFieldMarshaller marshaller);

        void writeString(String value);

        /* JADX INFO: compiled from: ResponseWriter.kt */
        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public static final class DefaultImpls {
            public static <T> void writeList(ListItemWriter listItemWriter, List<? extends T> list, final Function2<? super List<? extends T>, ? super ListItemWriter, Unit> block) {
                Intrinsics.checkParameterIsNotNull(block, "block");
                listItemWriter.writeList(list, new ListWriter<T>() { // from class: com.apollographql.apollo.api.internal.ResponseWriter$ListItemWriter$writeList$1
                    @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                    public void write(List<? extends T> items, ResponseWriter.ListItemWriter listItemWriter2) {
                        Intrinsics.checkParameterIsNotNull(listItemWriter2, "listItemWriter");
                        block.invoke(items, listItemWriter2);
                    }
                });
            }
        }
    }
}
