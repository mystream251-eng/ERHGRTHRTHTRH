package com.apollographql.apollo.cache.normalized.internal;

import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.internal.InputFieldMarshaller;
import com.apollographql.apollo.api.internal.InputFieldWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SortedInputFieldMapWriter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\bJ\u001f\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u000eJ\"\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0016J\u001f\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0002\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001f\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001dJ(\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\bH\u0016J\u001a\u0010\u001f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010 H\u0016J\u001a\u0010!\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u001a\u0010$\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0016R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/SortedInputFieldMapWriter;", "Lcom/apollographql/apollo/api/internal/InputFieldWriter;", "()V", "buffer", "", "", "", "map", "", "writeBoolean", "", "fieldName", "value", "", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "writeDouble", "", "(Ljava/lang/String;Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "writeList", "listWriter", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter;", "writeLong", "", "(Ljava/lang/String;Ljava/lang/Long;)V", "writeMap", "writeNumber", "", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "writeString", "ListItemWriter", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class SortedInputFieldMapWriter implements InputFieldWriter {
    private final Map<String, Object> buffer = new LinkedHashMap();

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeList(String fieldName, Function1<? super InputFieldWriter.ListItemWriter, Unit> block) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        Intrinsics.checkParameterIsNotNull(block, "block");
        InputFieldWriter.DefaultImpls.writeList(this, fieldName, block);
    }

    public final Map<String, Object> map() {
        return MapsKt.toMap(CollectionsKt.sortedWith(MapsKt.toList(this.buffer), new Comparator<T>() { // from class: com.apollographql.apollo.cache.normalized.internal.SortedInputFieldMapWriter$map$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((String) ((Pair) t).getFirst(), (String) ((Pair) t2).getFirst());
            }
        }));
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeString(String fieldName, String value) {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        this.buffer.put(fieldName, value);
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeInt(String fieldName, Integer value) {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        this.buffer.put(fieldName, value);
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeLong(String fieldName, Long value) {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        this.buffer.put(fieldName, value);
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeDouble(String fieldName, Double value) {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        this.buffer.put(fieldName, value);
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeNumber(String fieldName, Number value) {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        this.buffer.put(fieldName, value);
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeBoolean(String fieldName, Boolean value) {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        this.buffer.put(fieldName, value);
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeCustom(String fieldName, ScalarType scalarType, Object value) {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
        this.buffer.put(fieldName, value);
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeObject(String fieldName, InputFieldMarshaller marshaller) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (marshaller == null) {
            this.buffer.put(fieldName, null);
            return;
        }
        SortedInputFieldMapWriter sortedInputFieldMapWriter = new SortedInputFieldMapWriter();
        marshaller.marshal(sortedInputFieldMapWriter);
        this.buffer.put(fieldName, sortedInputFieldMapWriter.map());
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeList(String fieldName, InputFieldWriter.ListWriter listWriter) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (listWriter == null) {
            this.buffer.put(fieldName, null);
            return;
        }
        ListItemWriter listItemWriter = new ListItemWriter();
        listWriter.write(listItemWriter);
        this.buffer.put(fieldName, listItemWriter.getList());
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeMap(String fieldName, Map<String, ? extends Object> value) {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        this.buffer.put(fieldName, value);
    }

    /* JADX INFO: compiled from: SortedInputFieldMapWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0017\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\u0017\u0010\u0011\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0002\u0010\u0016J\u0012\u0010\u0017\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0017\u0010\u001a\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u001bH\u0016¢\u0006\u0002\u0010\u001cJ \u0010\u001d\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u001eH\u0016J\u0012\u0010 \u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010%\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u001fH\u0016R%\u0010\u0003\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006&"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/SortedInputFieldMapWriter$ListItemWriter;", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListItemWriter;", "()V", "list", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "writeBoolean", "", "value", "", "(Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "writeDouble", "", "(Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/Integer;)V", "writeList", "listWriter", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter;", "writeLong", "", "(Ljava/lang/Long;)V", "writeMap", "", "", "writeNumber", "", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "writeString", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
    private static class ListItemWriter implements InputFieldWriter.ListItemWriter {
        private final ArrayList<Object> list = new ArrayList<>();

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeList(Function1<? super InputFieldWriter.ListItemWriter, Unit> block) throws IOException {
            Intrinsics.checkParameterIsNotNull(block, "block");
            InputFieldWriter.ListItemWriter.DefaultImpls.writeList(this, block);
        }

        public final ArrayList<Object> getList() {
            return this.list;
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeString(String value) {
            if (value != null) {
                this.list.add(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeInt(Integer value) {
            if (value != null) {
                this.list.add(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeLong(Long value) {
            if (value != null) {
                this.list.add(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeDouble(Double value) {
            if (value != null) {
                this.list.add(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeNumber(Number value) {
            if (value != null) {
                this.list.add(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeBoolean(Boolean value) {
            if (value != null) {
                this.list.add(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeCustom(ScalarType scalarType, Object value) {
            Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
            if (value != null) {
                this.list.add(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeObject(InputFieldMarshaller marshaller) throws IOException {
            if (marshaller != null) {
                SortedInputFieldMapWriter sortedInputFieldMapWriter = new SortedInputFieldMapWriter();
                marshaller.marshal(sortedInputFieldMapWriter);
                this.list.add(sortedInputFieldMapWriter.map());
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeList(InputFieldWriter.ListWriter listWriter) throws IOException {
            if (listWriter != null) {
                ListItemWriter listItemWriter = new ListItemWriter();
                listWriter.write(listItemWriter);
                this.list.add(listItemWriter.list);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeMap(Map<String, ? extends Object> value) {
            if (value != null) {
                this.list.add(value);
            }
        }
    }
}
