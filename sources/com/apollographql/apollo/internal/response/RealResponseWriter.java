package com.apollographql.apollo.internal.response;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.ResolveDelegate;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: RealResponseWriter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\u0018\u0000 72\u00020\u0001:\u0003789B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J*\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u000eH\u0002J\u0018\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0002J@\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u001a\u0010\u0015\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u00162\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u000eH\u0002J\"\u0010\u0013\u001a\u00020\u00142\u001a\u0010\u0015\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u0016JH\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\f\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\u001a\u0010\u0015\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u0016H\u0002JB\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\n2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u001a\u0010\u0015\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u0016H\u0002J\u001f\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0002\u0010!J\u001a\u0010\"\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020#2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000fH\u0016J\u001f\u0010$\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010%H\u0016¢\u0006\u0002\u0010&J\u0012\u0010'\u001a\u00020\u00142\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u001f\u0010*\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010+H\u0016¢\u0006\u0002\u0010,J4\u0010-\u001a\u00020\u0014\"\u0004\b\u0000\u0010.2\u0006\u0010\u001e\u001a\u00020\u00192\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H.\u0018\u00010\u00112\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H.00H\u0016J\u001f\u00101\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u000102H\u0016¢\u0006\u0002\u00103J\u001a\u00104\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u001a\u00105\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\u000fH\u0002J\u001a\u00106\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\tH\u0016R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/apollographql/apollo/internal/response/RealResponseWriter;", "Lcom/apollographql/apollo/api/internal/ResponseWriter;", "operationVariables", "Lcom/apollographql/apollo/api/Operation$Variables;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "(Lcom/apollographql/apollo/api/Operation$Variables;Lcom/apollographql/apollo/api/ScalarTypeAdapters;)V", "buffer", "", "", "Lcom/apollographql/apollo/internal/response/RealResponseWriter$FieldDescriptor;", "getBuffer", "()Ljava/util/Map;", "rawFieldValues", "", "", "rawListFieldValues", "", "values", "resolveFields", "", "delegate", "Lcom/apollographql/apollo/api/internal/ResolveDelegate;", "resolveListField", "listResponseField", "Lcom/apollographql/apollo/api/ResponseField;", "fieldValues", "resolveObjectFields", "fieldDescriptor", "writeBoolean", "field", "value", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Boolean;)V", "writeCustom", "Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;", "writeDouble", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Double;)V", "writeFragment", "marshaller", "Lcom/apollographql/apollo/api/internal/ResponseFieldMarshaller;", "writeInt", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Integer;)V", "writeList", ExifInterface.GPS_DIRECTION_TRUE, "listWriter", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListWriter;", "writeLong", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Long;)V", "writeObject", "writeScalarFieldValue", "writeString", "Companion", "FieldDescriptor", "ListItemWriter", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
public final class RealResponseWriter implements ResponseWriter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, FieldDescriptor> buffer;
    private final Operation.Variables operationVariables;
    private final ScalarTypeAdapters scalarTypeAdapters;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ResponseField.Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ResponseField.Type.OBJECT.ordinal()] = 1;
            $EnumSwitchMapping$0[ResponseField.Type.LIST.ordinal()] = 2;
        }
    }

    public RealResponseWriter(Operation.Variables operationVariables, ScalarTypeAdapters scalarTypeAdapters) {
        Intrinsics.checkParameterIsNotNull(operationVariables, "operationVariables");
        Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
        this.operationVariables = operationVariables;
        this.scalarTypeAdapters = scalarTypeAdapters;
        this.buffer = new LinkedHashMap();
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public <T> void writeList(ResponseField field, List<? extends T> list, Function2<? super List<? extends T>, ? super ResponseWriter.ListItemWriter, Unit> block) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ResponseWriter.DefaultImpls.writeList(this, field, list, block);
    }

    public final Map<String, FieldDescriptor> getBuffer() {
        return this.buffer;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeString(ResponseField field, String value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        writeScalarFieldValue(field, value);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeInt(ResponseField field, Integer value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        writeScalarFieldValue(field, value != null ? BigDecimal.valueOf(value.intValue()) : null);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeLong(ResponseField field, Long value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        writeScalarFieldValue(field, value != null ? BigDecimal.valueOf(value.longValue()) : null);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeDouble(ResponseField field, Double value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        writeScalarFieldValue(field, value != null ? BigDecimal.valueOf(value.doubleValue()) : null);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeBoolean(ResponseField field, Boolean value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        writeScalarFieldValue(field, value);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeCustom(ResponseField.CustomTypeField field, Object value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        writeScalarFieldValue(field, value != null ? this.scalarTypeAdapters.adapterFor(field.getScalarType()).encode(value).value : null);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeObject(ResponseField field, ResponseFieldMarshaller marshaller) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        INSTANCE.checkFieldValue(field, marshaller);
        if (marshaller == null) {
            this.buffer.put(field.getResponseName(), new FieldDescriptor(field, null));
            return;
        }
        RealResponseWriter realResponseWriter = new RealResponseWriter(this.operationVariables, this.scalarTypeAdapters);
        marshaller.marshal(realResponseWriter);
        this.buffer.put(field.getResponseName(), new FieldDescriptor(field, realResponseWriter.buffer));
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeFragment(ResponseFieldMarshaller marshaller) {
        if (marshaller != null) {
            marshaller.marshal(this);
        }
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public <T> void writeList(ResponseField field, List<? extends T> values, ResponseWriter.ListWriter<T> listWriter) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(listWriter, "listWriter");
        INSTANCE.checkFieldValue(field, values);
        if (values == null) {
            this.buffer.put(field.getResponseName(), new FieldDescriptor(field, null));
            return;
        }
        ArrayList arrayList = new ArrayList();
        listWriter.write(values, new ListItemWriter(this.operationVariables, this.scalarTypeAdapters, arrayList));
        this.buffer.put(field.getResponseName(), new FieldDescriptor(field, arrayList));
    }

    public final void resolveFields(ResolveDelegate<Map<String, Object>> delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        resolveFields(this.operationVariables, delegate, this.buffer);
    }

    private final void writeScalarFieldValue(ResponseField field, Object value) {
        INSTANCE.checkFieldValue(field, value);
        this.buffer.put(field.getResponseName(), new FieldDescriptor(field, value));
    }

    private final Map<String, Object> rawFieldValues(Map<String, FieldDescriptor> buffer) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, FieldDescriptor> entry : buffer.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue().getValue();
            if (value == null) {
                linkedHashMap.put(key, null);
            } else if (value instanceof Map) {
                linkedHashMap.put(key, rawFieldValues((Map) value));
            } else if (value instanceof List) {
                linkedHashMap.put(key, rawListFieldValues((List) value));
            } else {
                linkedHashMap.put(key, value);
            }
        }
        return linkedHashMap;
    }

    private final List<?> rawListFieldValues(List<?> values) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : values) {
            if (obj instanceof Map) {
                arrayList.add(rawFieldValues((Map) obj));
            } else if (obj instanceof List) {
                arrayList.add(rawListFieldValues((List) obj));
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final void resolveFields(Operation.Variables operationVariables, ResolveDelegate<Map<String, Object>> delegate, Map<String, FieldDescriptor> buffer) {
        Map<String, Object> mapRawFieldValues = rawFieldValues(buffer);
        for (String str : buffer.keySet()) {
            FieldDescriptor fieldDescriptor = buffer.get(str);
            Object obj = mapRawFieldValues.get(str);
            if (fieldDescriptor == null) {
                Intrinsics.throwNpe();
            }
            delegate.willResolve(fieldDescriptor.getField(), operationVariables, fieldDescriptor.getValue());
            int i = WhenMappings.$EnumSwitchMapping$0[fieldDescriptor.getField().getType().ordinal()];
            if (i == 1) {
                resolveObjectFields(fieldDescriptor, (Map) obj, delegate);
            } else if (i == 2) {
                resolveListField(fieldDescriptor.getField(), (List) fieldDescriptor.getValue(), (List) obj, delegate);
            } else if (obj == null) {
                delegate.didResolveNull();
            } else {
                delegate.didResolveScalar(obj);
            }
            delegate.didResolve(fieldDescriptor.getField(), operationVariables);
        }
    }

    private final void resolveObjectFields(FieldDescriptor fieldDescriptor, Map<String, ? extends Object> rawFieldValues, ResolveDelegate<Map<String, Object>> delegate) {
        delegate.willResolveObject(fieldDescriptor.getField(), rawFieldValues);
        Object value = fieldDescriptor.getValue();
        if (value == null) {
            delegate.didResolveNull();
        } else {
            resolveFields(this.operationVariables, delegate, (Map) value);
        }
        delegate.didResolveObject(fieldDescriptor.getField(), rawFieldValues);
    }

    private final void resolveListField(ResponseField listResponseField, List<?> fieldValues, List<?> rawFieldValues, ResolveDelegate<Map<String, Object>> delegate) {
        if (fieldValues == null) {
            delegate.didResolveNull();
            return;
        }
        int i = 0;
        for (Object obj : fieldValues) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            delegate.willResolveElement(i);
            if (obj instanceof Map) {
                if (rawFieldValues == null) {
                    Intrinsics.throwNpe();
                }
                delegate.willResolveObject(listResponseField, (Map) rawFieldValues.get(i));
                Operation.Variables variables = this.operationVariables;
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, com.apollographql.apollo.internal.response.RealResponseWriter.FieldDescriptor>");
                }
                resolveFields(variables, delegate, (Map) obj);
                delegate.didResolveObject(listResponseField, (Map) rawFieldValues.get(i));
            } else if (obj instanceof List) {
                List<?> list = (List) obj;
                if (rawFieldValues == null) {
                    Intrinsics.throwNpe();
                }
                resolveListField(listResponseField, list, (List) rawFieldValues.get(i), delegate);
            } else {
                if (rawFieldValues == null) {
                    Intrinsics.throwNpe();
                }
                delegate.didResolveScalar(rawFieldValues.get(i));
            }
            delegate.didResolveElement(i);
            i = i2;
        }
        if (rawFieldValues == null) {
            Intrinsics.throwNpe();
        }
        delegate.didResolveList(rawFieldValues);
    }

    /* JADX INFO: compiled from: RealResponseWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\u0017\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0016J\u0017\u0010\u0018\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001dJ,\u0010\u001e\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u001f2\u000e\u0010 \u001a\n\u0012\u0004\u0012\u0002H\u001f\u0018\u00010!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u001f0#H\u0016J\u0017\u0010$\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010%H\u0016¢\u0006\u0002\u0010&J\u0012\u0010'\u001a\u00020\u00112\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0012\u0010*\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010+H\u0016R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006,"}, d2 = {"Lcom/apollographql/apollo/internal/response/RealResponseWriter$ListItemWriter;", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListItemWriter;", "operationVariables", "Lcom/apollographql/apollo/api/Operation$Variables;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "accumulator", "", "", "(Lcom/apollographql/apollo/api/Operation$Variables;Lcom/apollographql/apollo/api/ScalarTypeAdapters;Ljava/util/List;)V", "getAccumulator", "()Ljava/util/List;", "getOperationVariables", "()Lcom/apollographql/apollo/api/Operation$Variables;", "getScalarTypeAdapters", "()Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "writeBoolean", "", "value", "", "(Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "writeDouble", "", "(Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/Integer;)V", "writeList", ExifInterface.GPS_DIRECTION_TRUE, "items", "", "listWriter", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListWriter;", "writeLong", "", "(Ljava/lang/Long;)V", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/ResponseFieldMarshaller;", "writeString", "", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
    private static final class ListItemWriter implements ResponseWriter.ListItemWriter {
        private final List<Object> accumulator;
        private final Operation.Variables operationVariables;
        private final ScalarTypeAdapters scalarTypeAdapters;

        public ListItemWriter(Operation.Variables operationVariables, ScalarTypeAdapters scalarTypeAdapters, List<Object> accumulator) {
            Intrinsics.checkParameterIsNotNull(operationVariables, "operationVariables");
            Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
            Intrinsics.checkParameterIsNotNull(accumulator, "accumulator");
            this.operationVariables = operationVariables;
            this.scalarTypeAdapters = scalarTypeAdapters;
            this.accumulator = accumulator;
        }

        public final List<Object> getAccumulator() {
            return this.accumulator;
        }

        public final Operation.Variables getOperationVariables() {
            return this.operationVariables;
        }

        public final ScalarTypeAdapters getScalarTypeAdapters() {
            return this.scalarTypeAdapters;
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public <T> void writeList(List<? extends T> list, Function2<? super List<? extends T>, ? super ResponseWriter.ListItemWriter, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            ResponseWriter.ListItemWriter.DefaultImpls.writeList(this, list, block);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeString(String value) {
            this.accumulator.add(value);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeInt(Integer value) {
            this.accumulator.add(value != null ? BigDecimal.valueOf(value.intValue()) : null);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeLong(Long value) {
            this.accumulator.add(value != null ? BigDecimal.valueOf(value.longValue()) : null);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeDouble(Double value) {
            this.accumulator.add(value != null ? BigDecimal.valueOf(value.doubleValue()) : null);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeBoolean(Boolean value) {
            this.accumulator.add(value);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeCustom(ScalarType scalarType, Object value) {
            Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
            this.accumulator.add(value != null ? this.scalarTypeAdapters.adapterFor(scalarType).encode(value).value : null);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeObject(ResponseFieldMarshaller marshaller) {
            RealResponseWriter realResponseWriter = new RealResponseWriter(this.operationVariables, this.scalarTypeAdapters);
            if (marshaller == null) {
                Intrinsics.throwNpe();
            }
            marshaller.marshal(realResponseWriter);
            this.accumulator.add(realResponseWriter.getBuffer());
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public <T> void writeList(List<? extends T> items, ResponseWriter.ListWriter<T> listWriter) {
            Intrinsics.checkParameterIsNotNull(listWriter, "listWriter");
            if (items == null) {
                this.accumulator.add(null);
                return;
            }
            ArrayList arrayList = new ArrayList();
            listWriter.write(items, new ListItemWriter(this.operationVariables, this.scalarTypeAdapters, arrayList));
            this.accumulator.add(arrayList);
        }
    }

    /* JADX INFO: compiled from: RealResponseWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/internal/response/RealResponseWriter$FieldDescriptor;", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "value", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Object;)V", "getField", "()Lcom/apollographql/apollo/api/ResponseField;", "getValue", "()Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
    public static final class FieldDescriptor {
        private final ResponseField field;
        private final Object value;

        public FieldDescriptor(ResponseField field, Object obj) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            this.field = field;
            this.value = obj;
        }

        public final ResponseField getField() {
            return this.field;
        }

        public final Object getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: RealResponseWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0002¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo/internal/response/RealResponseWriter$Companion;", "", "()V", "checkFieldValue", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "value", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void checkFieldValue(ResponseField field, Object value) {
            if (field.getOptional() || value != null) {
                return;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("Mandatory response field `%s` resolved with null value", Arrays.copyOf(new Object[]{field.getResponseName()}, 1));
            Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(format, *args)");
            throw new NullPointerException(str);
        }
    }
}
