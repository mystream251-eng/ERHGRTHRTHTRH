package com.apollographql.apollo.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResponseField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 #2\u00020\u0001:\u0006\"#$%&'BK\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007J\u0013\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0007J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010\u0002\u001a\u00020\u0003H\u0007R\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lcom/apollographql/apollo/api/ResponseField;", "", "type", "Lcom/apollographql/apollo/api/ResponseField$Type;", "responseName", "", "fieldName", "arguments", "", "optional", "", "conditions", "", "Lcom/apollographql/apollo/api/ResponseField$Condition;", "(Lcom/apollographql/apollo/api/ResponseField$Type;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;ZLjava/util/List;)V", "getArguments", "()Ljava/util/Map;", "getConditions", "()Ljava/util/List;", "getFieldName", "()Ljava/lang/String;", "getOptional", "()Z", "getResponseName", "getType", "()Lcom/apollographql/apollo/api/ResponseField$Type;", "equals", "other", "hashCode", "", "resolveArgument", "name", "variables", "Lcom/apollographql/apollo/api/Operation$Variables;", "BooleanCondition", "Companion", "Condition", "CustomTypeField", "Type", "TypeNameCondition", "apollo-api"}, k = 1, mv = {1, 1, 16})
public class ResponseField {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String VARIABLE_IDENTIFIER_KEY = "kind";
    private static final String VARIABLE_IDENTIFIER_VALUE = "Variable";
    public static final String VARIABLE_NAME_KEY = "variableName";
    private final Map<String, Object> arguments;
    private final List<Condition> conditions;
    private final String fieldName;
    private final boolean optional;
    private final String responseName;
    private final Type type;

    /* JADX INFO: compiled from: ResponseField.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/api/ResponseField$Type;", "", "(Ljava/lang/String;I)V", "STRING", "INT", "LONG", "DOUBLE", "BOOLEAN", "ENUM", "OBJECT", "LIST", "CUSTOM", "FRAGMENT", "FRAGMENTS", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public enum Type {
        STRING,
        INT,
        LONG,
        DOUBLE,
        BOOLEAN,
        ENUM,
        OBJECT,
        LIST,
        CUSTOM,
        FRAGMENT,
        FRAGMENTS
    }

    @JvmStatic
    public static final ResponseField forBoolean(String str, String str2, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list) {
        return INSTANCE.forBoolean(str, str2, map, z, list);
    }

    @JvmStatic
    public static final CustomTypeField forCustomType(String str, String str2, Map<String, ? extends Object> map, boolean z, ScalarType scalarType, List<? extends Condition> list) {
        return INSTANCE.forCustomType(str, str2, map, z, scalarType, list);
    }

    @JvmStatic
    public static final ResponseField forDouble(String str, String str2, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list) {
        return INSTANCE.forDouble(str, str2, map, z, list);
    }

    @JvmStatic
    public static final ResponseField forEnum(String str, String str2, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list) {
        return INSTANCE.forEnum(str, str2, map, z, list);
    }

    @JvmStatic
    public static final ResponseField forFragment(String str, String str2, List<? extends Condition> list) {
        return INSTANCE.forFragment(str, str2, list);
    }

    @JvmStatic
    public static final ResponseField forInt(String str, String str2, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list) {
        return INSTANCE.forInt(str, str2, map, z, list);
    }

    @JvmStatic
    public static final ResponseField forList(String str, String str2, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list) {
        return INSTANCE.forList(str, str2, map, z, list);
    }

    @JvmStatic
    public static final ResponseField forLong(String str, String str2, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list) {
        return INSTANCE.forLong(str, str2, map, z, list);
    }

    @JvmStatic
    public static final ResponseField forObject(String str, String str2, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list) {
        return INSTANCE.forObject(str, str2, map, z, list);
    }

    @JvmStatic
    public static final ResponseField forString(String str, String str2, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list) {
        return INSTANCE.forString(str, str2, map, z, list);
    }

    @JvmStatic
    public static final boolean isArgumentValueVariableType(Map<String, ? extends Object> map) {
        return INSTANCE.isArgumentValueVariableType(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseField(Type type, String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean z, List<? extends Condition> conditions) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(responseName, "responseName");
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        Intrinsics.checkParameterIsNotNull(conditions, "conditions");
        this.type = type;
        this.responseName = responseName;
        this.fieldName = fieldName;
        this.arguments = arguments;
        this.optional = z;
        this.conditions = conditions;
    }

    public final Type getType() {
        return this.type;
    }

    public final String getResponseName() {
        return this.responseName;
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public final Map<String, Object> getArguments() {
        return this.arguments;
    }

    public final boolean getOptional() {
        return this.optional;
    }

    public final List<Condition> getConditions() {
        return this.conditions;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    public final Type type() {
        return this.type;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "responseName", imports = {}))
    public final String responseName() {
        return this.responseName;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "fieldName", imports = {}))
    /* JADX INFO: renamed from: fieldName, reason: from getter */
    public final String getFieldName() {
        return this.fieldName;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "arguments", imports = {}))
    public final Map<String, Object> arguments() {
        return this.arguments;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "optional", imports = {}))
    public final boolean optional() {
        return this.optional;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "conditions", imports = {}))
    public final List<Condition> conditions() {
        return this.conditions;
    }

    public final Object resolveArgument(String name, Operation.Variables variables) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        Map<String, Object> mapValueMap = variables.valueMap();
        Object obj = this.arguments.get(name);
        if (!(obj instanceof Map)) {
            return obj;
        }
        Map<String, ? extends Object> map = (Map) obj;
        if (INSTANCE.isArgumentValueVariableType(map)) {
            return mapValueMap.get(String.valueOf(map.get(VARIABLE_NAME_KEY)));
        }
        return null;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResponseField)) {
            return false;
        }
        ResponseField responseField = (ResponseField) other;
        return (this.type != responseField.type || (Intrinsics.areEqual(this.responseName, responseField.responseName) ^ true) || (Intrinsics.areEqual(this.fieldName, responseField.fieldName) ^ true) || (Intrinsics.areEqual(this.arguments, responseField.arguments) ^ true) || this.optional != responseField.optional || (Intrinsics.areEqual(this.conditions, responseField.conditions) ^ true)) ? false : true;
    }

    public int hashCode() {
        return (((((((((this.type.hashCode() * 31) + this.responseName.hashCode()) * 31) + this.fieldName.hashCode()) * 31) + this.arguments.hashCode()) * 31) + Boolean.hashCode(this.optional)) * 31) + this.conditions.hashCode();
    }

    /* JADX INFO: compiled from: ResponseField.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001BO\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0007R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;", "Lcom/apollographql/apollo/api/ResponseField;", "responseName", "", "fieldName", "arguments", "", "", "optional", "", "conditions", "", "Lcom/apollographql/apollo/api/ResponseField$Condition;", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;ZLjava/util/List;Lcom/apollographql/apollo/api/ScalarType;)V", "getScalarType", "()Lcom/apollographql/apollo/api/ScalarType;", "equals", "other", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class CustomTypeField extends ResponseField {
        private final ScalarType scalarType;

        public final ScalarType getScalarType() {
            return this.scalarType;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CustomTypeField(String responseName, String fieldName, Map<String, ? extends Object> map, boolean z, List<? extends Condition> list, ScalarType scalarType) {
            super(Type.CUSTOM, responseName, fieldName, map == null ? MapsKt.emptyMap() : map, z, list == null ? CollectionsKt.emptyList() : list);
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
            this.scalarType = scalarType;
        }

        @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "scalarType", imports = {}))
        public final ScalarType scalarType() {
            return this.scalarType;
        }

        @Override // com.apollographql.apollo.api.ResponseField
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof CustomTypeField) && super.equals(other) && !(Intrinsics.areEqual(this.scalarType, ((CustomTypeField) other).scalarType) ^ true);
        }

        @Override // com.apollographql.apollo.api.ResponseField
        public int hashCode() {
            return (super.hashCode() * 31) + this.scalarType.hashCode();
        }
    }

    /* JADX INFO: compiled from: ResponseField.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0000¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo/api/ResponseField$Condition;", "", "()V", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static class Condition {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JvmStatic
        public static final BooleanCondition booleanCondition(String str, boolean z) {
            return INSTANCE.booleanCondition(str, z);
        }

        @JvmStatic
        public static final TypeNameCondition typeCondition(String[] strArr) {
            return INSTANCE.typeCondition(strArr);
        }

        /* JADX INFO: compiled from: ResponseField.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001b\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fH\u0007¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/api/ResponseField$Condition$Companion;", "", "()V", "booleanCondition", "Lcom/apollographql/apollo/api/ResponseField$BooleanCondition;", ResponseField.VARIABLE_NAME_KEY, "", "inverted", "", "typeCondition", "Lcom/apollographql/apollo/api/ResponseField$TypeNameCondition;", "types", "", "([Ljava/lang/String;)Lcom/apollographql/apollo/api/ResponseField$TypeNameCondition;", "apollo-api"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final TypeNameCondition typeCondition(String[] types) {
                Intrinsics.checkParameterIsNotNull(types, "types");
                return new TypeNameCondition(CollectionsKt.listOf(Arrays.copyOf(types, types.length)));
            }

            @JvmStatic
            public final BooleanCondition booleanCondition(String variableName, boolean inverted) {
                Intrinsics.checkParameterIsNotNull(variableName, "variableName");
                return new BooleanCondition(variableName, inverted);
            }
        }
    }

    /* JADX INFO: compiled from: ResponseField.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/api/ResponseField$TypeNameCondition;", "Lcom/apollographql/apollo/api/ResponseField$Condition;", "typeNames", "", "", "(Ljava/util/List;)V", "getTypeNames", "()Ljava/util/List;", "equals", "", "other", "", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class TypeNameCondition extends Condition {
        private final List<String> typeNames;

        public final List<String> getTypeNames() {
            return this.typeNames;
        }

        public TypeNameCondition(List<String> typeNames) {
            Intrinsics.checkParameterIsNotNull(typeNames, "typeNames");
            this.typeNames = typeNames;
        }

        @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "typeNames", imports = {}))
        public final List<String> typeNames() {
            return this.typeNames;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TypeNameCondition) && !(Intrinsics.areEqual(this.typeNames, ((TypeNameCondition) other).typeNames) ^ true);
        }

        public int hashCode() {
            return this.typeNames.hashCode();
        }
    }

    /* JADX INFO: compiled from: ResponseField.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010\u0002\u001a\u00020\u0003H\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo/api/ResponseField$BooleanCondition;", "Lcom/apollographql/apollo/api/ResponseField$Condition;", ResponseField.VARIABLE_NAME_KEY, "", "inverted", "", "(Ljava/lang/String;Z)V", "getInverted", "()Z", "getVariableName", "()Ljava/lang/String;", "equals", "other", "", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class BooleanCondition extends Condition {
        private final boolean inverted;
        private final String variableName;

        public final String getVariableName() {
            return this.variableName;
        }

        public final boolean getInverted() {
            return this.inverted;
        }

        public BooleanCondition(String variableName, boolean z) {
            Intrinsics.checkParameterIsNotNull(variableName, "variableName");
            this.variableName = variableName;
            this.inverted = z;
        }

        @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = ResponseField.VARIABLE_NAME_KEY, imports = {}))
        public final String variableName() {
            return this.variableName;
        }

        @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "inverted", imports = {}))
        public final boolean inverted() {
            return this.inverted;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BooleanCondition)) {
                return false;
            }
            BooleanCondition booleanCondition = (BooleanCondition) other;
            return !(Intrinsics.areEqual(this.variableName, booleanCondition.variableName) ^ true) && this.inverted == booleanCondition.inverted;
        }

        public int hashCode() {
            return (this.variableName.hashCode() * 31) + Boolean.hashCode(this.inverted);
        }
    }

    /* JADX INFO: compiled from: ResponseField.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JH\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007JP\u0010\u0012\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007JH\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007JH\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007J(\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007JH\u0010\u0019\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007JH\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007JH\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007JH\u0010\u001c\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007JH\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007J\u001e\u0010\u001e\u001a\u00020\u000e2\u0014\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/apollographql/apollo/api/ResponseField$Companion;", "", "()V", "VARIABLE_IDENTIFIER_KEY", "", "VARIABLE_IDENTIFIER_VALUE", "VARIABLE_NAME_KEY", "forBoolean", "Lcom/apollographql/apollo/api/ResponseField;", "responseName", "fieldName", "arguments", "", "optional", "", "conditions", "", "Lcom/apollographql/apollo/api/ResponseField$Condition;", "forCustomType", "Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "forDouble", "forEnum", "forFragment", "forInt", "forList", "forLong", "forObject", "forString", "isArgumentValueVariableType", "objectMap", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ResponseField forString(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.STRING;
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, map, optional, conditions);
        }

        @JvmStatic
        public final ResponseField forInt(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.INT;
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, map, optional, conditions);
        }

        @JvmStatic
        public final ResponseField forLong(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.LONG;
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, map, optional, conditions);
        }

        @JvmStatic
        public final ResponseField forDouble(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.DOUBLE;
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, map, optional, conditions);
        }

        @JvmStatic
        public final ResponseField forBoolean(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.BOOLEAN;
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, map, optional, conditions);
        }

        @JvmStatic
        public final ResponseField forEnum(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.ENUM;
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, map, optional, conditions);
        }

        @JvmStatic
        public final ResponseField forObject(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.OBJECT;
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, map, optional, conditions);
        }

        @JvmStatic
        public final ResponseField forList(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.LIST;
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, map, optional, conditions);
        }

        @JvmStatic
        public final CustomTypeField forCustomType(String responseName, String fieldName, Map<String, ? extends Object> arguments, boolean optional, ScalarType scalarType, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
            if (arguments == null) {
                arguments = MapsKt.emptyMap();
            }
            Map<String, ? extends Object> map = arguments;
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new CustomTypeField(responseName, fieldName, map, optional, conditions, scalarType);
        }

        @JvmStatic
        public final ResponseField forFragment(String responseName, String fieldName, List<? extends Condition> conditions) {
            Intrinsics.checkParameterIsNotNull(responseName, "responseName");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Type type = Type.FRAGMENT;
            Map mapEmptyMap = MapsKt.emptyMap();
            if (conditions == null) {
                conditions = CollectionsKt.emptyList();
            }
            return new ResponseField(type, responseName, fieldName, mapEmptyMap, false, conditions);
        }

        @JvmStatic
        public final boolean isArgumentValueVariableType(Map<String, ? extends Object> objectMap) {
            Intrinsics.checkParameterIsNotNull(objectMap, "objectMap");
            return objectMap.containsKey(ResponseField.VARIABLE_IDENTIFIER_KEY) && Intrinsics.areEqual(objectMap.get(ResponseField.VARIABLE_IDENTIFIER_KEY), ResponseField.VARIABLE_IDENTIFIER_VALUE) && objectMap.containsKey(ResponseField.VARIABLE_NAME_KEY);
        }
    }
}
