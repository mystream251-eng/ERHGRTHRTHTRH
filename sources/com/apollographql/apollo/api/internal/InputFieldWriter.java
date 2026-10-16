package com.apollographql.apollo.api.internal;

import com.apollographql.apollo.api.ScalarType;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InputFieldWriter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0002#$J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\bJ\"\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H&J\u001f\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0010H&¢\u0006\u0002\u0010\u0011J$\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u0014H\u0016J\u001a\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H&J\u001f\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0019H&¢\u0006\u0002\u0010\u001aJ(\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u001cH&J\u001a\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u001eH&J\u001a\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010!H&J\u001a\u0010\"\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¨\u0006%"}, d2 = {"Lcom/apollographql/apollo/api/internal/InputFieldWriter;", "", "writeBoolean", "", "fieldName", "", "value", "", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "writeDouble", "", "(Ljava/lang/String;Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "writeList", "block", "Lkotlin/Function1;", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListItemWriter;", "listWriter", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter;", "writeLong", "", "(Ljava/lang/String;Ljava/lang/Long;)V", "writeMap", "", "writeNumber", "", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "writeString", "ListItemWriter", "ListWriter", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface InputFieldWriter {
    void writeBoolean(String fieldName, Boolean value) throws IOException;

    void writeCustom(String fieldName, ScalarType scalarType, Object value) throws IOException;

    void writeDouble(String fieldName, Double value) throws IOException;

    void writeInt(String fieldName, Integer value) throws IOException;

    void writeList(String fieldName, ListWriter listWriter) throws IOException;

    void writeList(String fieldName, Function1<? super ListItemWriter, Unit> block);

    void writeLong(String fieldName, Long value) throws IOException;

    void writeMap(String fieldName, Map<String, ? extends Object> value) throws IOException;

    void writeNumber(String fieldName, Number value) throws IOException;

    void writeObject(String fieldName, InputFieldMarshaller marshaller) throws IOException;

    void writeString(String fieldName, String value) throws IOException;

    /* JADX INFO: compiled from: InputFieldWriter.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static void writeList(InputFieldWriter inputFieldWriter, String fieldName, final Function1<? super ListItemWriter, Unit> block) throws IOException {
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(block, "block");
            inputFieldWriter.writeList(fieldName, new ListWriter() { // from class: com.apollographql.apollo.api.internal.InputFieldWriter.writeList.1
                @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListWriter
                public void write(ListItemWriter listItemWriter) {
                    Intrinsics.checkParameterIsNotNull(listItemWriter, "listItemWriter");
                    block.invoke(listItemWriter);
                }
            });
        }
    }

    /* JADX INFO: compiled from: InputFieldWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter;", "", "write", "", "listItemWriter", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListItemWriter;", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ListWriter {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        void write(ListItemWriter listItemWriter) throws IOException;

        /* JADX INFO: compiled from: InputFieldWriter.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0014\b\u0004\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0086\n¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter$Companion;", "", "()V", "invoke", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter;", "block", "Lkotlin/Function1;", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListItemWriter;", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }

            public final /* synthetic */ ListWriter invoke(final Function1<? super ListItemWriter, Unit> block) {
                Intrinsics.checkParameterIsNotNull(block, "block");
                return new ListWriter() { // from class: com.apollographql.apollo.api.internal.InputFieldWriter$ListWriter$Companion$invoke$1
                    @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListWriter
                    public void write(InputFieldWriter.ListItemWriter listItemWriter) {
                        Intrinsics.checkParameterIsNotNull(listItemWriter, "listItemWriter");
                        block.invoke(listItemWriter);
                    }
                };
            }
        }
    }

    /* JADX INFO: compiled from: InputFieldWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H&J\u0017\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010\fJ\u0017\u0010\r\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u000eH&¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u0012H\u0016J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H&J\u0017\u0010\u0015\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0016H&¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0019H&J\u0012\u0010\u001b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u001cH&J\u0012\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH&J\u0012\u0010 \u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u001aH&¨\u0006!"}, d2 = {"Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListItemWriter;", "", "writeBoolean", "", "value", "", "(Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "writeDouble", "", "(Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/Integer;)V", "writeList", "block", "Lkotlin/Function1;", "listWriter", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter;", "writeLong", "", "(Ljava/lang/Long;)V", "writeMap", "", "", "writeNumber", "", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "writeString", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ListItemWriter {
        void writeBoolean(Boolean value) throws IOException;

        void writeCustom(ScalarType scalarType, Object value) throws IOException;

        void writeDouble(Double value) throws IOException;

        void writeInt(Integer value) throws IOException;

        void writeList(ListWriter listWriter) throws IOException;

        void writeList(Function1<? super ListItemWriter, Unit> block);

        void writeLong(Long value) throws IOException;

        void writeMap(Map<String, ? extends Object> value) throws IOException;

        void writeNumber(Number value) throws IOException;

        void writeObject(InputFieldMarshaller marshaller) throws IOException;

        void writeString(String value) throws IOException;

        /* JADX INFO: compiled from: InputFieldWriter.kt */
        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public static final class DefaultImpls {
            public static void writeList(ListItemWriter listItemWriter, final Function1<? super ListItemWriter, Unit> block) throws IOException {
                Intrinsics.checkParameterIsNotNull(block, "block");
                listItemWriter.writeList(new ListWriter() { // from class: com.apollographql.apollo.api.internal.InputFieldWriter$ListItemWriter$writeList$1
                    @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListWriter
                    public void write(InputFieldWriter.ListItemWriter listItemWriter2) {
                        Intrinsics.checkParameterIsNotNull(listItemWriter2, "listItemWriter");
                        block.invoke(listItemWriter2);
                    }
                });
            }
        }
    }
}
