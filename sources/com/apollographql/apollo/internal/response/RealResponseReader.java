package com.apollographql.apollo.internal.response;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.CustomTypeAdapter;
import com.apollographql.apollo.api.CustomTypeValue;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.FieldValueResolver;
import com.apollographql.apollo.api.internal.ResolveDelegate;
import com.apollographql.apollo.api.internal.ResponseReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RealResponseReader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001<B9\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\u0002\u0010\fJ\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0019H\u0002J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0017\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016¢\u0006\u0002\u0010\"J!\u0010#\u001a\u0004\u0018\u0001H$\"\b\b\u0001\u0010$*\u00020\u00192\u0006\u0010\u001c\u001a\u00020%H\u0016¢\u0006\u0002\u0010&J\u0017\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016¢\u0006\u0002\u0010)J/\u0010*\u001a\u0004\u0018\u0001H$\"\b\b\u0001\u0010$*\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H$0,H\u0016¢\u0006\u0002\u0010-J\u0017\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016¢\u0006\u0002\u00100J2\u00101\u001a\f\u0012\u0006\u0012\u0004\u0018\u0001H$\u0018\u000102\"\b\b\u0001\u0010$*\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H$04H\u0016J\u0017\u00105\u001a\u0004\u0018\u0001062\u0006\u0010\u001c\u001a\u00020\u001dH\u0016¢\u0006\u0002\u00107J/\u00108\u001a\u0004\u0018\u0001H$\"\b\b\u0001\u0010$*\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H$0,H\u0016¢\u0006\u0002\u0010-J\u0012\u00109\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010:\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u001a\u0010;\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0019H\u0002R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0005\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/apollographql/apollo/internal/response/RealResponseReader;", "R", "Lcom/apollographql/apollo/api/internal/ResponseReader;", "operationVariables", "Lcom/apollographql/apollo/api/Operation$Variables;", "recordSet", "fieldValueResolver", "Lcom/apollographql/apollo/api/internal/FieldValueResolver;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "resolveDelegate", "Lcom/apollographql/apollo/api/internal/ResolveDelegate;", "(Lcom/apollographql/apollo/api/Operation$Variables;Ljava/lang/Object;Lcom/apollographql/apollo/api/internal/FieldValueResolver;Lcom/apollographql/apollo/api/ScalarTypeAdapters;Lcom/apollographql/apollo/api/internal/ResolveDelegate;)V", "getFieldValueResolver$apollo_runtime", "()Lcom/apollographql/apollo/api/internal/FieldValueResolver;", "getOperationVariables", "()Lcom/apollographql/apollo/api/Operation$Variables;", "Ljava/lang/Object;", "getResolveDelegate$apollo_runtime", "()Lcom/apollographql/apollo/api/internal/ResolveDelegate;", "getScalarTypeAdapters$apollo_runtime", "()Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "variableValues", "", "", "", "checkValue", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "value", "didResolve", "readBoolean", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Boolean;", "readCustomType", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;", "(Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;)Ljava/lang/Object;", "readDouble", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Double;", "readFragment", "objectReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;", "(Lcom/apollographql/apollo/api/ResponseField;Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;)Ljava/lang/Object;", "readInt", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Integer;", "readList", "", "listReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListReader;", "readLong", "", "(Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Long;", "readObject", "readString", "shouldSkip", "willResolve", "ListItemReader", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
public final class RealResponseReader<R> implements ResponseReader {
    private final FieldValueResolver<R> fieldValueResolver;
    private final Operation.Variables operationVariables;
    private final R recordSet;
    private final ResolveDelegate<R> resolveDelegate;
    private final ScalarTypeAdapters scalarTypeAdapters;
    private final Map<String, Object> variableValues;

    public RealResponseReader(Operation.Variables operationVariables, R r, FieldValueResolver<R> fieldValueResolver, ScalarTypeAdapters scalarTypeAdapters, ResolveDelegate<R> resolveDelegate) {
        Intrinsics.checkParameterIsNotNull(operationVariables, "operationVariables");
        Intrinsics.checkParameterIsNotNull(fieldValueResolver, "fieldValueResolver");
        Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
        Intrinsics.checkParameterIsNotNull(resolveDelegate, "resolveDelegate");
        this.operationVariables = operationVariables;
        this.recordSet = r;
        this.fieldValueResolver = fieldValueResolver;
        this.scalarTypeAdapters = scalarTypeAdapters;
        this.resolveDelegate = resolveDelegate;
        this.variableValues = operationVariables.valueMap();
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

    public final Operation.Variables getOperationVariables() {
        return this.operationVariables;
    }

    public final FieldValueResolver<R> getFieldValueResolver$apollo_runtime() {
        return this.fieldValueResolver;
    }

    /* JADX INFO: renamed from: getScalarTypeAdapters$apollo_runtime, reason: from getter */
    public final ScalarTypeAdapters getScalarTypeAdapters() {
        return this.scalarTypeAdapters;
    }

    public final ResolveDelegate<R> getResolveDelegate$apollo_runtime() {
        return this.resolveDelegate;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public String readString(ResponseField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (shouldSkip(field)) {
            return null;
        }
        String str = (String) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(field, str);
        willResolve(field, str);
        if (str == null) {
            this.resolveDelegate.didResolveNull();
        } else {
            this.resolveDelegate.didResolveScalar(str);
        }
        didResolve(field);
        return str;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public Integer readInt(ResponseField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (shouldSkip(field)) {
            return null;
        }
        BigDecimal bigDecimal = (BigDecimal) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(field, bigDecimal);
        willResolve(field, bigDecimal);
        if (bigDecimal == null) {
            this.resolveDelegate.didResolveNull();
        } else {
            this.resolveDelegate.didResolveScalar(bigDecimal);
        }
        didResolve(field);
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
        BigDecimal bigDecimal = (BigDecimal) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(field, bigDecimal);
        willResolve(field, bigDecimal);
        if (bigDecimal == null) {
            this.resolveDelegate.didResolveNull();
        } else {
            this.resolveDelegate.didResolveScalar(bigDecimal);
        }
        didResolve(field);
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
        BigDecimal bigDecimal = (BigDecimal) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(field, bigDecimal);
        willResolve(field, bigDecimal);
        if (bigDecimal == null) {
            this.resolveDelegate.didResolveNull();
        } else {
            this.resolveDelegate.didResolveScalar(bigDecimal);
        }
        didResolve(field);
        if (bigDecimal != null) {
            return Double.valueOf(bigDecimal.doubleValue());
        }
        return null;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public Boolean readBoolean(ResponseField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (shouldSkip(field)) {
            return null;
        }
        Boolean bool = (Boolean) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(field, bool);
        willResolve(field, bool);
        if (bool == null) {
            this.resolveDelegate.didResolveNull();
        } else {
            this.resolveDelegate.didResolveScalar(bool);
        }
        didResolve(field);
        return bool;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> T readObject(ResponseField field, ResponseReader.ObjectReader<T> objectReader) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(objectReader, "objectReader");
        T t = null;
        if (shouldSkip(field)) {
            return null;
        }
        Object objValueFor = this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(field, objValueFor);
        willResolve(field, objValueFor);
        this.resolveDelegate.willResolveObject(field, (R) objValueFor);
        if (objValueFor == null) {
            this.resolveDelegate.didResolveNull();
        } else {
            t = objectReader.read(new RealResponseReader(this.operationVariables, objValueFor, this.fieldValueResolver, this.scalarTypeAdapters, this.resolveDelegate));
        }
        this.resolveDelegate.didResolveObject(field, (R) objValueFor);
        didResolve(field);
        return t;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> List<T> readList(ResponseField field, ResponseReader.ListReader<T> listReader) {
        ArrayList arrayList;
        T t;
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(listReader, "listReader");
        if (shouldSkip(field)) {
            return null;
        }
        List<?> list = (List) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(field, list);
        willResolve(field, list);
        if (list == null) {
            this.resolveDelegate.didResolveNull();
            arrayList = null;
        } else {
            List<?> list2 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            int i = 0;
            for (T t2 : list2) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                this.resolveDelegate.willResolveElement(i);
                if (t2 == null) {
                    this.resolveDelegate.didResolveNull();
                    t = null;
                } else {
                    t = listReader.read(new ListItemReader(this, field, t2));
                }
                this.resolveDelegate.didResolveElement(i);
                arrayList2.add(t);
                i = i2;
            }
            arrayList = arrayList2;
            this.resolveDelegate.didResolveList(list);
        }
        didResolve(field);
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return null;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> T readCustomType(ResponseField.CustomTypeField field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        ResponseField.CustomTypeField customTypeField = field;
        T tDecode = null;
        if (shouldSkip(customTypeField)) {
            return null;
        }
        Object objValueFor = this.fieldValueResolver.valueFor(this.recordSet, customTypeField);
        checkValue(customTypeField, objValueFor);
        willResolve(customTypeField, objValueFor);
        if (objValueFor == null) {
            this.resolveDelegate.didResolveNull();
        } else {
            tDecode = this.scalarTypeAdapters.adapterFor(field.getScalarType()).decode(CustomTypeValue.INSTANCE.fromRawValue(objValueFor));
            checkValue(customTypeField, tDecode);
            this.resolveDelegate.didResolveScalar(objValueFor);
        }
        didResolve(customTypeField);
        return tDecode;
    }

    @Override // com.apollographql.apollo.api.internal.ResponseReader
    public <T> T readFragment(ResponseField field, ResponseReader.ObjectReader<T> objectReader) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(objectReader, "objectReader");
        if (shouldSkip(field)) {
            return null;
        }
        String str = (String) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(field, str);
        willResolve(field, str);
        if (str == null) {
            this.resolveDelegate.didResolveNull();
            didResolve(field);
            return null;
        }
        this.resolveDelegate.didResolveScalar(str);
        didResolve(field);
        if (field.getType() != ResponseField.Type.FRAGMENT) {
            return null;
        }
        for (ResponseField.Condition condition : field.getConditions()) {
            if ((condition instanceof ResponseField.TypeNameCondition) && !((ResponseField.TypeNameCondition) condition).getTypeNames().contains(str)) {
                return null;
            }
        }
        return objectReader.read(this);
    }

    private final boolean shouldSkip(ResponseField field) {
        for (ResponseField.Condition condition : field.getConditions()) {
            if (condition instanceof ResponseField.BooleanCondition) {
                ResponseField.BooleanCondition booleanCondition = (ResponseField.BooleanCondition) condition;
                Boolean bool = (Boolean) this.variableValues.get(booleanCondition.getVariableName());
                if (booleanCondition.getInverted()) {
                    if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                        return true;
                    }
                } else if (Intrinsics.areEqual((Object) bool, (Object) false)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void willResolve(ResponseField field, Object value) {
        this.resolveDelegate.willResolve(field, this.operationVariables, value);
    }

    private final void didResolve(ResponseField field) {
        this.resolveDelegate.didResolve(field, this.operationVariables);
    }

    private final void checkValue(ResponseField field, Object value) {
        if (field.getOptional() || value != null) {
            return;
        }
        throw new IllegalStateException(("corrupted response reader, expected non null value for " + field.getFieldName()).toString());
    }

    /* JADX INFO: compiled from: RealResponseReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u001f\u0010\t\u001a\u0002H\n\"\b\b\u0001\u0010\n*\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J(\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\n0\u0013\"\b\b\u0001\u0010\n*\u00020\u00052\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\n0\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J%\u0010\u0018\u001a\u0002H\n\"\b\b\u0001\u0010\n*\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\n0\u001aH\u0016¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/apollographql/apollo/internal/response/RealResponseReader$ListItemReader;", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListItemReader;", "field", "Lcom/apollographql/apollo/api/ResponseField;", "value", "", "(Lcom/apollographql/apollo/internal/response/RealResponseReader;Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Object;)V", "readBoolean", "", "readCustomType", ExifInterface.GPS_DIRECTION_TRUE, "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "(Lcom/apollographql/apollo/api/ScalarType;)Ljava/lang/Object;", "readDouble", "", "readInt", "", "readList", "", "listReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ListReader;", "readLong", "", "readObject", "objectReader", "Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;", "(Lcom/apollographql/apollo/api/internal/ResponseReader$ObjectReader;)Ljava/lang/Object;", "readString", "", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
    private final class ListItemReader implements ResponseReader.ListItemReader {
        private final ResponseField field;
        final /* synthetic */ RealResponseReader this$0;
        private final Object value;

        public ListItemReader(RealResponseReader realResponseReader, ResponseField field, Object value) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.this$0 = realResponseReader;
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
            this.this$0.getResolveDelegate$apollo_runtime().didResolveScalar(this.value);
            Object obj = this.value;
            if (obj != null) {
                return (String) obj;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public int readInt() {
            this.this$0.getResolveDelegate$apollo_runtime().didResolveScalar(this.value);
            Object obj = this.value;
            if (obj != null) {
                return ((BigDecimal) obj).intValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type com.apollographql.apollo.api.BigDecimal /* = java.math.BigDecimal */");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public long readLong() {
            this.this$0.getResolveDelegate$apollo_runtime().didResolveScalar(this.value);
            Object obj = this.value;
            if (obj != null) {
                return ((BigDecimal) obj).longValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type com.apollographql.apollo.api.BigDecimal /* = java.math.BigDecimal */");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public double readDouble() {
            this.this$0.getResolveDelegate$apollo_runtime().didResolveScalar(this.value);
            Object obj = this.value;
            if (obj != null) {
                return ((BigDecimal) obj).doubleValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type com.apollographql.apollo.api.BigDecimal /* = java.math.BigDecimal */");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public boolean readBoolean() {
            this.this$0.getResolveDelegate$apollo_runtime().didResolveScalar(this.value);
            Object obj = this.value;
            if (obj != null) {
                return ((Boolean) obj).booleanValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public <T> T readCustomType(ScalarType scalarType) {
            Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
            CustomTypeAdapter<T> customTypeAdapterAdapterFor = this.this$0.getScalarTypeAdapters().adapterFor(scalarType);
            this.this$0.getResolveDelegate$apollo_runtime().didResolveScalar(this.value);
            return customTypeAdapterAdapterFor.decode(CustomTypeValue.INSTANCE.fromRawValue(this.value));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public <T> T readObject(ResponseReader.ObjectReader<T> objectReader) {
            Intrinsics.checkParameterIsNotNull(objectReader, "objectReader");
            Object obj = this.value;
            this.this$0.getResolveDelegate$apollo_runtime().willResolveObject(this.field, obj);
            T t = objectReader.read(new RealResponseReader(this.this$0.getOperationVariables(), obj, this.this$0.getFieldValueResolver$apollo_runtime(), this.this$0.getScalarTypeAdapters(), this.this$0.getResolveDelegate$apollo_runtime()));
            this.this$0.getResolveDelegate$apollo_runtime().didResolveObject(this.field, obj);
            return t;
        }

        @Override // com.apollographql.apollo.api.internal.ResponseReader.ListItemReader
        public <T> List<T> readList(ResponseReader.ListReader<T> listReader) {
            T t;
            Intrinsics.checkParameterIsNotNull(listReader, "listReader");
            Object obj = this.value;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<*>");
            }
            List<?> list = (List) obj;
            List<?> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            int i = 0;
            for (T t2 : list2) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                this.this$0.getResolveDelegate$apollo_runtime().willResolveElement(i);
                if (t2 == null) {
                    this.this$0.getResolveDelegate$apollo_runtime().didResolveNull();
                    t = null;
                } else {
                    t = listReader.read(new ListItemReader(this.this$0, this.field, t2));
                }
                this.this$0.getResolveDelegate$apollo_runtime().didResolveElement(i);
                arrayList.add(t);
                i = i2;
            }
            this.this$0.getResolveDelegate$apollo_runtime().didResolveList(list);
            List<T> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            Intrinsics.checkExpressionValueIsNotNull(listUnmodifiableList, "Collections.unmodifiableList(result)");
            return listUnmodifiableList;
        }
    }
}
