package com.apollographql.apollo.api.internal;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResponseReader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003#$%J\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\tH&¢\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\rJ5\u0010\u000e\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\b0\u0010H\u0016¢\u0006\u0002\u0010\u0011J/\u0010\u000e\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\b0\u0013H&¢\u0006\u0002\u0010\u0014J\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0017J8\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u0001H\b\u0018\u00010\u0019\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u0002H\b0\u0010H\u0016J2\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u0001H\b\u0018\u00010\u0019\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\b0\u001cH&J\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u001fJ5\u0010 \u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\b0\u0010H\u0016¢\u0006\u0002\u0010\u0011J/\u0010 \u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\b0\u0013H&¢\u0006\u0002\u0010\u0014J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006&"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseReader;", "", "readBoolean", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Boolean;", "readCustomType", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;", "(Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;)Ljava/lang/Object;", "readDouble", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Double;", "readFragment", "block", "Lkotlin/Function1;", "(Lcom/apollographql/apollo/api/ResponseField;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "objectReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;", "(Lcom/apollographql/apollo/api/ResponseField;Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;)Ljava/lang/Object;", "readInt", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Integer;", "readList", "", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListItemReader;", "listReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListReader;", "readLong", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Long;", "readObject", "readString", "", "ListItemReader", "ListReader", "ObjectReader", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface ResponseReader {

    /* JADX INFO: compiled from: ResponseReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseReader$ListReader;", ExifInterface.GPS_DIRECTION_TRUE, "", "read", "reader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListItemReader;", "(Lcom/apollographql/apollo/api/internal/ResponseReader$ListItemReader;)Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ListReader<T> {
        T read(ListItemReader reader);
    }

    /* JADX INFO: compiled from: ResponseReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;", ExifInterface.GPS_DIRECTION_TRUE, "", "read", "reader", "Lcom/apollographql/apollo/api/internal/ResponseReader;", "(Lcom/apollographql/apollo/api/internal/ResponseReader;)Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ObjectReader<T> {
        T read(ResponseReader reader);
    }

    Boolean readBoolean(ResponseField field);

    <T> T readCustomType(ResponseField.CustomTypeField field);

    Double readDouble(ResponseField field);

    <T> T readFragment(ResponseField field, ObjectReader<T> objectReader);

    <T> T readFragment(ResponseField field, Function1<? super ResponseReader, ? extends T> block);

    Integer readInt(ResponseField field);

    <T> List<T> readList(ResponseField field, ListReader<T> listReader);

    <T> List<T> readList(ResponseField field, Function1<? super ListItemReader, ? extends T> block);

    Long readLong(ResponseField field);

    <T> T readObject(ResponseField field, ObjectReader<T> objectReader);

    <T> T readObject(ResponseField field, Function1<? super ResponseReader, ? extends T> block);

    String readString(ResponseField field);

    /* JADX INFO: compiled from: ResponseReader.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static <T> T readObject(ResponseReader responseReader, ResponseField field, final Function1<? super ResponseReader, ? extends T> block) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(block, "block");
            return (T) responseReader.readObject(field, new ObjectReader<T>() { // from class: com.apollographql.apollo.api.internal.ResponseReader.readObject.1
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                public T read(ResponseReader reader) {
                    Intrinsics.checkParameterIsNotNull(reader, "reader");
                    return (T) block.invoke(reader);
                }
            });
        }

        public static <T> T readFragment(ResponseReader responseReader, ResponseField field, final Function1<? super ResponseReader, ? extends T> block) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(block, "block");
            return (T) responseReader.readFragment(field, new ObjectReader<T>() { // from class: com.apollographql.apollo.api.internal.ResponseReader.readFragment.1
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                public T read(ResponseReader reader) {
                    Intrinsics.checkParameterIsNotNull(reader, "reader");
                    return (T) block.invoke(reader);
                }
            });
        }

        public static <T> List<T> readList(ResponseReader responseReader, ResponseField field, final Function1<? super ListItemReader, ? extends T> block) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(block, "block");
            return responseReader.readList(field, new ListReader<T>() { // from class: com.apollographql.apollo.api.internal.ResponseReader.readList.1
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                public T read(ListItemReader reader) {
                    Intrinsics.checkParameterIsNotNull(reader, "reader");
                    return (T) block.invoke(reader);
                }
            });
        }
    }

    /* JADX INFO: compiled from: ResponseReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u001f\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J.\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u000e\"\b\b\u0000\u0010\u0005*\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\u00050\u0010H\u0016J(\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u000e\"\b\b\u0000\u0010\u0005*\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J+\u0010\u0015\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u0002H\u00050\u0010H\u0016¢\u0006\u0002\u0010\u0017J%\u0010\u0015\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0019H&¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u001cH&¨\u0006\u001d"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseReader$ListItemReader;", "", "readBoolean", "", "readCustomType", ExifInterface.GPS_DIRECTION_TRUE, "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "(Lcom/apollographql/apollo/api/ScalarType;)Ljava/lang/Object;", "readDouble", "", "readInt", "", "readList", "", "block", "Lkotlin/Function1;", "listReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListReader;", "readLong", "", "readObject", "Lcom/apollographql/apollo/api/internal/ResponseReader;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "objectReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;", "(Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;)Ljava/lang/Object;", "readString", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ListItemReader {
        boolean readBoolean();

        <T> T readCustomType(ScalarType scalarType);

        double readDouble();

        int readInt();

        <T> List<T> readList(ListReader<T> listReader);

        <T> List<T> readList(Function1<? super ListItemReader, ? extends T> block);

        long readLong();

        <T> T readObject(ObjectReader<T> objectReader);

        <T> T readObject(Function1<? super ResponseReader, ? extends T> block);

        String readString();

        /* JADX INFO: compiled from: ResponseReader.kt */
        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public static final class DefaultImpls {
            public static <T> T readObject(ListItemReader listItemReader, final Function1<? super ResponseReader, ? extends T> block) {
                Intrinsics.checkParameterIsNotNull(block, "block");
                return (T) listItemReader.readObject(new ObjectReader<T>() { // from class: com.apollographql.apollo.api.internal.ResponseReader$ListItemReader$readObject$1
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public T read(ResponseReader reader) {
                        Intrinsics.checkParameterIsNotNull(reader, "reader");
                        return (T) block.invoke(reader);
                    }
                });
            }

            public static <T> List<T> readList(ListItemReader listItemReader, final Function1<? super ListItemReader, ? extends T> block) {
                Intrinsics.checkParameterIsNotNull(block, "block");
                return listItemReader.readList(new ListReader<T>() { // from class: com.apollographql.apollo.api.internal.ResponseReader$ListItemReader$readList$1
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public T read(ResponseReader.ListItemReader reader) {
                        Intrinsics.checkParameterIsNotNull(reader, "reader");
                        return (T) block.invoke(reader);
                    }
                });
            }
        }
    }
}
