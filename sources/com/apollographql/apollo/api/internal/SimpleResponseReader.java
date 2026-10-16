package com.apollographql.apollo.api.internal;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.CustomTypeValue;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Typography;

/* JADX INFO: compiled from: SimpleResponseReader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001:\u00011B-\b\u0016\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB;\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\fJ'\u0010\r\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u0001H\u000eH\u0002¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\u0004\u0018\u0001H\u0017\"\b\b\u0000\u0010\u0017*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0018H\u0016¢\u0006\u0002\u0010\u0019J\u0017\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u001cJ/\u0010\u001d\u001a\u0004\u0018\u0001H\u0017\"\b\b\u0000\u0010\u0017*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001fH\u0016¢\u0006\u0002\u0010 J\u0017\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010#J2\u0010$\u001a\f\u0012\u0006\u0012\u0004\u0018\u0001H\u0017\u0018\u00010%\"\b\b\u0000\u0010\u0017*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u00170'H\u0016J\u0017\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010*J/\u0010+\u001a\u0004\u0018\u0001H\u0017\"\b\b\u0000\u0010\u0017*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001fH\u0016¢\u0006\u0002\u0010 J\u0012\u0010,\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010-\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J6\u0010.\u001a\u0004\u0018\u0001H\u0017\"\u0006\b\u0000\u0010\u0017\u0018\u00012\u0014\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0082\b¢\u0006\u0002\u00100R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/apollographql/apollo/api/internal/SimpleResponseReader;", "Lcom/apollographql/apollo/api/internal/ResponseReader;", "recordSet", "", "", "", "variables", "Lcom/apollographql/apollo/api/Operation$Variables;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "(Ljava/util/Map;Lcom/apollographql/apollo/api/Operation$Variables;Lcom/apollographql/apollo/api/ScalarTypeAdapters;)V", "variableValues", "(Ljava/util/Map;Ljava/util/Map;Lcom/apollographql/apollo/api/ScalarTypeAdapters;)V", "checkValue", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "field", "Lcom/apollographql/apollo/api/ResponseField;", "value", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Object;)Ljava/lang/Object;", "readBoolean", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Boolean;", "readCustomType", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;", "(Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;)Ljava/lang/Object;", "readDouble", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Double;", "readFragment", "objectReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;", "(Lcom/apollographql/apollo/api/ResponseField;Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;)Ljava/lang/Object;", "readInt", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Integer;", "readList", "", "listReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListReader;", "readLong", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Long;", "readObject", "readString", "shouldSkip", "valueFor", "map", "(Ljava/util/Map;Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Object;", "ListItemReader", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class SimpleResponseReader implements ResponseReader {
    private final Map<String, Object> recordSet;
    private final ScalarTypeAdapters scalarTypeAdapters;
    private final Map<String, Object> variableValues;

    private SimpleResponseReader(Map<String, ? extends Object> map, Map<String, ? extends Object> map2, ScalarTypeAdapters scalarTypeAdapters) {
        this.recordSet = map;
        this.variableValues = map2;
        this.scalarTypeAdapters = scalarTypeAdapters;
    }

    public /* synthetic */ SimpleResponseReader(Map map, Map map2, ScalarTypeAdapters scalarTypeAdapters, DefaultConstructorMarker defaultConstructorMarker) {
        this((Map<String, ? extends Object>) map, (Map<String, ? extends Object>) map2, scalarTypeAdapters);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> T readFragment(ResponseField field, Function1<? super ResponseReader, ? extends T> block) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return (T) ResponseReader.DefaultImpls.readFragment(this, field, block);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> List<T> readList(ResponseField field, Function1<? super ResponseReader.ListItemReader, ? extends T> block) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return ResponseReader.DefaultImpls.readList(this, field, block);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> T readObject(ResponseField field, Function1<? super ResponseReader, ? extends T> block) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return (T) ResponseReader.DefaultImpls.readObject(this, field, block);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SimpleResponseReader(Map<String, ? extends Object> recordSet, Operation.Variables variables, ScalarTypeAdapters scalarTypeAdapters) {
        this(recordSet, (Map<String, ? extends Object>) variables.valueMap(), scalarTypeAdapters);
        Intrinsics.checkParameterIsNotNull(recordSet, "recordSet");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public String readString(ResponseField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Object obj = null;
        if (shouldSkip(field)) {
            return null;
        }
        Object obj2 = this.recordSet.get(field.getResponseName());
        if (obj2 != null) {
            if (!(obj2 instanceof String)) {
                throw new ClassCastException("The value for \"" + field.getResponseName() + "\" expected to be of type \"" + Reflection.getOrCreateKotlinClass(String.class).getSimpleName() + "\" but was \"" + Reflection.getOrCreateKotlinClass(obj2.getClass()).getSimpleName() + Typography.quote);
            }
            obj = obj2;
        }
        return (String) checkValue(field, (String) obj);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public Integer readInt(ResponseField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (shouldSkip(field)) {
            return null;
        }
        Object obj = this.recordSet.get(field.getResponseName());
        if (obj == null) {
            obj = null;
        } else if (!(obj instanceof BigDecimal)) {
            throw new ClassCastException("The value for \"" + field.getResponseName() + "\" expected to be of type \"" + Reflection.getOrCreateKotlinClass(BigDecimal.class).getSimpleName() + "\" but was \"" + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() + Typography.quote);
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        checkValue(field, bigDecimal);
        if (bigDecimal != null) {
            return Integer.valueOf(bigDecimal.intValue());
        }
        return null;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public Long readLong(ResponseField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (shouldSkip(field)) {
            return null;
        }
        Object obj = this.recordSet.get(field.getResponseName());
        if (obj == null) {
            obj = null;
        } else if (!(obj instanceof BigDecimal)) {
            throw new ClassCastException("The value for \"" + field.getResponseName() + "\" expected to be of type \"" + Reflection.getOrCreateKotlinClass(BigDecimal.class).getSimpleName() + "\" but was \"" + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() + Typography.quote);
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        checkValue(field, bigDecimal);
        if (bigDecimal != null) {
            return Long.valueOf(bigDecimal.longValue());
        }
        return null;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public Double readDouble(ResponseField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (shouldSkip(field)) {
            return null;
        }
        Object obj = this.recordSet.get(field.getResponseName());
        if (obj == null) {
            obj = null;
        } else if (!(obj instanceof BigDecimal)) {
            throw new ClassCastException("The value for \"" + field.getResponseName() + "\" expected to be of type \"" + Reflection.getOrCreateKotlinClass(BigDecimal.class).getSimpleName() + "\" but was \"" + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() + Typography.quote);
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        checkValue(field, bigDecimal);
        if (bigDecimal != null) {
            return Double.valueOf(bigDecimal.doubleValue());
        }
        return null;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public Boolean readBoolean(ResponseField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Object obj = null;
        if (shouldSkip(field)) {
            return null;
        }
        Object obj2 = this.recordSet.get(field.getResponseName());
        if (obj2 != null) {
            if (!(obj2 instanceof Boolean)) {
                throw new ClassCastException("The value for \"" + field.getResponseName() + "\" expected to be of type \"" + Reflection.getOrCreateKotlinClass(Boolean.class).getSimpleName() + "\" but was \"" + Reflection.getOrCreateKotlinClass(obj2.getClass()).getSimpleName() + Typography.quote);
            }
            obj = obj2;
        }
        return (Boolean) checkValue(field, (Boolean) obj);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> T readObject(ResponseField field, ResponseReader.ObjectReader<T> objectReader) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(objectReader, "objectReader");
        if (shouldSkip(field)) {
            return null;
        }
        Object obj = this.recordSet.get(field.getResponseName());
        if (obj == null) {
            obj = null;
        } else if (!(obj instanceof Map)) {
            throw new ClassCastException("The value for \"" + field.getResponseName() + "\" expected to be of type \"" + Reflection.getOrCreateKotlinClass(Map.class).getSimpleName() + "\" but was \"" + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() + Typography.quote);
        }
        Map map = (Map) obj;
        checkValue(field, map);
        if (map != null) {
            return objectReader.read(new SimpleResponseReader((Map<String, ? extends Object>) map, (Map<String, ? extends Object>) this.variableValues, this.scalarTypeAdapters));
        }
        return null;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> List<T> readList(ResponseField field, ResponseReader.ListReader<T> listReader) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(listReader, "listReader");
        if (shouldSkip(field)) {
            return null;
        }
        Object obj = this.recordSet.get(field.getResponseName());
        if (obj == null) {
            obj = null;
        } else if (!(obj instanceof List)) {
            throw new ClassCastException("The value for \"" + field.getResponseName() + "\" expected to be of type \"" + Reflection.getOrCreateKotlinClass(List.class).getSimpleName() + "\" but was \"" + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() + Typography.quote);
        }
        List list = (List) obj;
        checkValue(field, list);
        if (list == null) {
            return null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            T next = it.next();
            arrayList.add(next != null ? listReader.read(new ListItemReader(this, field, next)) : null);
        }
        return arrayList;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> T readCustomType(ResponseField.CustomTypeField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        ResponseField.CustomTypeField customTypeField = field;
        if (shouldSkip(customTypeField)) {
            return null;
        }
        Object obj = this.recordSet.get(customTypeField.getResponseName());
        if (obj == null) {
            obj = null;
        }
        checkValue(customTypeField, obj);
        if (obj != null) {
            return this.scalarTypeAdapters.adapterFor(field.getScalarType()).decode(CustomTypeValue.INSTANCE.fromRawValue(obj));
        }
        return null;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> T readFragment(ResponseField field, ResponseReader.ObjectReader<T> objectReader) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(objectReader, "objectReader");
        if (shouldSkip(field)) {
            return null;
        }
        Object obj = this.recordSet.get(field.getResponseName());
        if (obj == null) {
            obj = null;
        } else if (!(obj instanceof String)) {
            throw new ClassCastException("The value for \"" + field.getResponseName() + "\" expected to be of type \"" + Reflection.getOrCreateKotlinClass(String.class).getSimpleName() + "\" but was \"" + Reflection.getOrCreateKotlinClass(obj.getClass()).getSimpleName() + Typography.quote);
        }
        String str = (String) obj;
        checkValue(field, str);
        if (str == null) {
            return null;
        }
        List<ResponseField.Condition> conditions = field.getConditions();
        ArrayList arrayList = new ArrayList();
        for (ResponseField.Condition condition : conditions) {
            if (!(condition instanceof ResponseField.TypeNameCondition)) {
                condition = null;
            }
            ResponseField.TypeNameCondition typeNameCondition = (ResponseField.TypeNameCondition) condition;
            if (typeNameCondition != null) {
                arrayList.add(typeNameCondition);
            }
        }
        ArrayList arrayList2 = arrayList;
        boolean z = true;
        if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
            Iterator<T> it = arrayList2.iterator();
            while (it.hasNext()) {
                if (!((ResponseField.TypeNameCondition) it.next()).getTypeNames().contains(str)) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return objectReader.read(this);
        }
        return null;
    }

    private final boolean shouldSkip(ResponseField field) {
        for (ResponseField.Condition condition : field.getConditions()) {
            if (condition instanceof ResponseField.BooleanCondition) {
                ResponseField.BooleanCondition booleanCondition = (ResponseField.BooleanCondition) condition;
                Object obj = this.variableValues.get(booleanCondition.getVariableName());
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                }
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                if (booleanCondition.getInverted()) {
                    if (zBooleanValue) {
                        return true;
                    }
                } else if (!zBooleanValue) {
                    return true;
                }
            }
        }
        return false;
    }

    private final <V> V checkValue(ResponseField field, V value) {
        if (field.getOptional() || value != null) {
            return value;
        }
        throw new NullPointerException("corrupted response reader, expected non null value for " + field.getFieldName());
    }

    /* JADX INFO: compiled from: SimpleResponseReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u001f\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J(\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\n0\u0013\"\b\b\u0000\u0010\n*\u00020\u00052\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\n0\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J%\u0010\u0018\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\n0\u001aH\u0016¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/apollographql/apollo/api/internal/SimpleResponseReader$ListItemReader;", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListItemReader;", "field", "Lcom/apollographql/apollo/api/ResponseField;", "value", "", "(Lcom/apollographql/apollo/api/internal/SimpleResponseReader;Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Object;)V", "readBoolean", "", "readCustomType", ExifInterface.GPS_DIRECTION_TRUE, "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "(Lcom/apollographql/apollo/api/ScalarType;)Ljava/lang/Object;", "readDouble", "", "readInt", "", "readList", "", "listReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListReader;", "readLong", "", "readObject", "objectReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;", "(Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;)Ljava/lang/Object;", "readString", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    private final class ListItemReader implements ResponseReader.ListItemReader {
        private final ResponseField field;
        final /* synthetic */ SimpleResponseReader this$0;
        private final Object value;

        public ListItemReader(SimpleResponseReader simpleResponseReader, ResponseField field, Object value) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.this$0 = simpleResponseReader;
            this.field = field;
            this.value = value;
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public <T> List<T> readList(Function1<? super ResponseReader.ListItemReader, ? extends T> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return ResponseReader.ListItemReader.DefaultImpls.readList(this, block);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public <T> T readObject(Function1<? super ResponseReader, ? extends T> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return (T) ResponseReader.ListItemReader.DefaultImpls.readObject(this, block);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public String readString() {
            Object obj = this.value;
            if (obj != null) {
                return (String) obj;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public int readInt() {
            Object obj = this.value;
            if (obj != null) {
                return ((BigDecimal) obj).intValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type com.apollographql.apollo.api.BigDecimal /* = java.math.BigDecimal */");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public long readLong() {
            Object obj = this.value;
            if (obj != null) {
                return ((BigDecimal) obj).longValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type com.apollographql.apollo.api.BigDecimal /* = java.math.BigDecimal */");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public double readDouble() {
            Object obj = this.value;
            if (obj != null) {
                return ((BigDecimal) obj).doubleValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type com.apollographql.apollo.api.BigDecimal /* = java.math.BigDecimal */");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public boolean readBoolean() {
            Object obj = this.value;
            if (obj != null) {
                return ((Boolean) obj).booleanValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public <T> T readCustomType(ScalarType scalarType) {
            Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
            return this.this$0.scalarTypeAdapters.adapterFor(scalarType).decode(CustomTypeValue.INSTANCE.fromRawValue(this.value));
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public <T> T readObject(ResponseReader.ObjectReader<T> objectReader) {
            Intrinsics.checkParameterIsNotNull(objectReader, "objectReader");
            Object obj = this.value;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
            }
            return objectReader.read(new SimpleResponseReader((Map) obj, this.this$0.variableValues, this.this$0.scalarTypeAdapters, null));
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public <T> List<T> readList(ResponseReader.ListReader<T> listReader) {
            Intrinsics.checkParameterIsNotNull(listReader, "listReader");
            Object obj = this.value;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<*>");
            }
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                T next = it.next();
                arrayList.add(next != null ? listReader.read(new ListItemReader(this.this$0, this.field, next)) : null);
            }
            return arrayList;
        }
    }

    private final /* synthetic */ <T> T valueFor(Map<String, ? extends Object> map, ResponseField field) {
        T t = (T) map.get(field.getResponseName());
        if (t == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        if (t instanceof Object) {
            return t;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The value for \"");
        sb.append(field.getResponseName());
        sb.append("\" expected to be of type \"");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        sb.append(Reflection.getOrCreateKotlinClass(Object.class).getSimpleName());
        sb.append("\" but was \"");
        sb.append(Reflection.getOrCreateKotlinClass(t.getClass()).getSimpleName());
        sb.append(Typography.quote);
        throw new ClassCastException(sb.toString());
    }
}
