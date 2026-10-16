package com.apollographql.apollo.api;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomTypeValue.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0006*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0006\u0006\u0007\b\t\n\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004R\u0012\u0010\u0003\u001a\u00028\u00008\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0001\u0005\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo/api/CustomTypeValue;", ExifInterface.GPS_DIRECTION_TRUE, "", "value", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "Companion", "GraphQLBoolean", "GraphQLJsonList", "GraphQLJsonObject", "GraphQLNumber", "GraphQLString", "Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLString;", "Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLBoolean;", "Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLNumber;", "Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLJsonObject;", "Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLJsonList;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public abstract class CustomTypeValue<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public final T value;

    @JvmStatic
    public static final CustomTypeValue<?> fromRawValue(Object obj) {
        return INSTANCE.fromRawValue(obj);
    }

    private CustomTypeValue(T t) {
        this.value = t;
    }

    public /* synthetic */ CustomTypeValue(Object obj, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj);
    }

    /* JADX INFO: compiled from: CustomTypeValue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLString;", "Lcom/apollographql/apollo/api/CustomTypeValue;", "", "value", "(Ljava/lang/String;)V", "equals", "", "other", "", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class GraphQLString extends CustomTypeValue<String> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GraphQLString(String value) {
            super(value, null);
            Intrinsics.checkParameterIsNotNull(value, "value");
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GraphQLString) && !(Intrinsics.areEqual((String) this.value, (String) ((GraphQLString) other).value) ^ true);
        }

        public int hashCode() {
            return ((String) this.value).hashCode();
        }
    }

    /* JADX INFO: compiled from: CustomTypeValue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0096\u0002J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLBoolean;", "Lcom/apollographql/apollo/api/CustomTypeValue;", "", "value", "(Z)V", "equals", "other", "", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class GraphQLBoolean extends CustomTypeValue<Boolean> {
        public GraphQLBoolean(boolean z) {
            super(Boolean.valueOf(z), null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GraphQLBoolean) && ((Boolean) this.value).booleanValue() == ((Boolean) ((GraphQLBoolean) other).value).booleanValue();
        }

        public int hashCode() {
            return Boolean.hashCode(((Boolean) this.value).booleanValue());
        }
    }

    /* JADX INFO: compiled from: CustomTypeValue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLNumber;", "Lcom/apollographql/apollo/api/CustomTypeValue;", "", "value", "(Ljava/lang/Number;)V", "equals", "", "other", "", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class GraphQLNumber extends CustomTypeValue<Number> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GraphQLNumber(Number value) {
            super(value, null);
            Intrinsics.checkParameterIsNotNull(value, "value");
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GraphQLNumber) && !(Intrinsics.areEqual((Number) this.value, (Number) ((GraphQLNumber) other).value) ^ true);
        }

        public int hashCode() {
            return ((Number) this.value).hashCode();
        }
    }

    /* JADX INFO: compiled from: CustomTypeValue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLJsonObject;", "Lcom/apollographql/apollo/api/CustomTypeValue;", "", "", "", "value", "(Ljava/util/Map;)V", "equals", "", "other", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class GraphQLJsonObject extends CustomTypeValue<Map<String, ? extends Object>> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GraphQLJsonObject(Map<String, ? extends Object> value) {
            super(value, null);
            Intrinsics.checkParameterIsNotNull(value, "value");
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GraphQLJsonObject) && !(Intrinsics.areEqual((Map) this.value, (Map) ((GraphQLJsonObject) other).value) ^ true);
        }

        public int hashCode() {
            return this.value.hashCode();
        }
    }

    /* JADX INFO: compiled from: CustomTypeValue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo/api/CustomTypeValue$GraphQLJsonList;", "Lcom/apollographql/apollo/api/CustomTypeValue;", "", "", "value", "(Ljava/util/List;)V", "equals", "", "other", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class GraphQLJsonList extends CustomTypeValue<List<? extends Object>> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GraphQLJsonList(List<? extends Object> value) {
            super(value, null);
            Intrinsics.checkParameterIsNotNull(value, "value");
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GraphQLJsonList) && !(Intrinsics.areEqual((List) this.value, (List) ((GraphQLJsonList) other).value) ^ true);
        }

        public int hashCode() {
            return this.value.hashCode();
        }
    }

    /* JADX INFO: compiled from: CustomTypeValue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0007¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo/api/CustomTypeValue$Companion;", "", "()V", "fromRawValue", "Lcom/apollographql/apollo/api/CustomTypeValue;", "value", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final CustomTypeValue<?> fromRawValue(Object value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if (value instanceof Map) {
                return new GraphQLJsonObject((Map) value);
            }
            if (value instanceof List) {
                return new GraphQLJsonList((List) value);
            }
            if (value instanceof Boolean) {
                return new GraphQLBoolean(((Boolean) value).booleanValue());
            }
            return value instanceof Number ? new GraphQLNumber((Number) value) : new GraphQLString(value.toString());
        }
    }
}
