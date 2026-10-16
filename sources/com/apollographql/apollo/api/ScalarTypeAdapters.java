package com.apollographql.apollo.api;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.internal.json.JsonWriter;
import com.apollographql.apollo.api.internal.json.Utils;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okio.Buffer;

/* JADX INFO: compiled from: ScalarTypeAdapters.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\f0\u0005\"\b\b\u0000\u0010\f*\u00020\u00012\u0006\u0010\r\u001a\u00020\u0004R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "", "customAdapters", "", "Lcom/apollographql/apollo/api/ScalarType;", "Lcom/apollographql/apollo/api/CustomTypeAdapter;", "(Ljava/util/Map;)V", "getCustomAdapters", "()Ljava/util/Map;", "customTypeAdapters", "", "adapterFor", ExifInterface.GPS_DIRECTION_TRUE, "scalarType", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class ScalarTypeAdapters {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final ScalarTypeAdapters DEFAULT = new ScalarTypeAdapters(MapsKt.emptyMap());
    private static final Map<String, CustomTypeAdapter<? extends Object>> DEFAULT_ADAPTERS = MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.plus(MapsKt.emptyMap(), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.lang.String", "kotlin.String"}, new Function1<CustomTypeValue<?>, String>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$1
        @Override // kotlin.jvm.functions.Function1
        public final String invoke(CustomTypeValue<?> value) throws IOException {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if ((value instanceof CustomTypeValue.GraphQLJsonList) || (value instanceof CustomTypeValue.GraphQLJsonObject)) {
                Buffer buffer = new Buffer();
                JsonWriter jsonWriterOf = JsonWriter.INSTANCE.of(buffer);
                try {
                    Utils.writeToJson(value.value, jsonWriterOf);
                    Unit unit = Unit.INSTANCE;
                    if (jsonWriterOf != null) {
                        jsonWriterOf.close();
                    }
                    return buffer.readUtf8();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (jsonWriterOf != null) {
                            try {
                                jsonWriterOf.close();
                            } catch (Throwable unused) {
                            }
                        }
                        throw th2;
                    }
                }
            }
            return String.valueOf(value.value);
        }
    })), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.lang.Boolean", "kotlin.Boolean", "boolean"}, new Function1<CustomTypeValue<?>, Boolean>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(CustomTypeValue<?> customTypeValue) {
            return Boolean.valueOf(invoke2(customTypeValue));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(CustomTypeValue<?> value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if (value instanceof CustomTypeValue.GraphQLBoolean) {
                return ((Boolean) ((CustomTypeValue.GraphQLBoolean) value).value).booleanValue();
            }
            if (value instanceof CustomTypeValue.GraphQLString) {
                return Boolean.parseBoolean((String) ((CustomTypeValue.GraphQLString) value).value);
            }
            throw new IllegalArgumentException("Can't decode: " + value + " into Boolean");
        }
    })), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.lang.Integer", "kotlin.Int", "int"}, new Function1<CustomTypeValue<?>, Integer>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$3
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Integer invoke(CustomTypeValue<?> customTypeValue) {
            return Integer.valueOf(invoke2(customTypeValue));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final int invoke2(CustomTypeValue<?> value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if (value instanceof CustomTypeValue.GraphQLNumber) {
                return ((Number) ((CustomTypeValue.GraphQLNumber) value).value).intValue();
            }
            if (value instanceof CustomTypeValue.GraphQLString) {
                return Integer.parseInt((String) ((CustomTypeValue.GraphQLString) value).value);
            }
            throw new IllegalArgumentException("Can't decode: " + value + " into Integer");
        }
    })), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.lang.Long", "kotlin.Long", "long"}, new Function1<CustomTypeValue<?>, Long>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$4
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Long invoke(CustomTypeValue<?> customTypeValue) {
            return Long.valueOf(invoke2(customTypeValue));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final long invoke2(CustomTypeValue<?> value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if (value instanceof CustomTypeValue.GraphQLNumber) {
                return ((Number) ((CustomTypeValue.GraphQLNumber) value).value).longValue();
            }
            if (value instanceof CustomTypeValue.GraphQLString) {
                return Long.parseLong((String) ((CustomTypeValue.GraphQLString) value).value);
            }
            throw new IllegalArgumentException("Can't decode: " + value + " into Long");
        }
    })), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.lang.Float", "kotlin.Float", "float"}, new Function1<CustomTypeValue<?>, Float>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$5
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Float invoke(CustomTypeValue<?> customTypeValue) {
            return Float.valueOf(invoke2(customTypeValue));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final float invoke2(CustomTypeValue<?> value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if (value instanceof CustomTypeValue.GraphQLNumber) {
                return ((Number) ((CustomTypeValue.GraphQLNumber) value).value).floatValue();
            }
            if (value instanceof CustomTypeValue.GraphQLString) {
                return Float.parseFloat((String) ((CustomTypeValue.GraphQLString) value).value);
            }
            throw new IllegalArgumentException("Can't decode: " + value + " into Float");
        }
    })), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.lang.Double", "kotlin.Double", "double"}, new Function1<CustomTypeValue<?>, Double>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$6
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Double invoke(CustomTypeValue<?> customTypeValue) {
            return Double.valueOf(invoke2(customTypeValue));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final double invoke2(CustomTypeValue<?> value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if (value instanceof CustomTypeValue.GraphQLNumber) {
                return ((Number) ((CustomTypeValue.GraphQLNumber) value).value).doubleValue();
            }
            if (value instanceof CustomTypeValue.GraphQLString) {
                return Double.parseDouble((String) ((CustomTypeValue.GraphQLString) value).value);
            }
            throw new IllegalArgumentException("Can't decode: " + value + " into Double");
        }
    })), MapsKt.mapOf(TuplesKt.to("com.apollographql.apollo.api.FileUpload", new CustomTypeAdapter<FileUpload>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$7
        @Override // com.apollographql.apollo.api.CustomTypeAdapter
        public /* bridge */ /* synthetic */ FileUpload decode(CustomTypeValue customTypeValue) {
            return decode((CustomTypeValue<?>) customTypeValue);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.apollographql.apollo.api.CustomTypeAdapter
        public FileUpload decode(CustomTypeValue<?> value) {
            String string;
            Intrinsics.checkParameterIsNotNull(value, "value");
            T t = value.value;
            if (t == 0 || (string = t.toString()) == null) {
                string = "";
            }
            return new FileUpload("", string);
        }

        @Override // com.apollographql.apollo.api.CustomTypeAdapter
        public CustomTypeValue<?> encode(FileUpload value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            return new CustomTypeValue.GraphQLString(value.getMimetype());
        }
    }))), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.util.Map", "kotlin.collections.Map"}, new Function1<CustomTypeValue<?>, Map<String, ? extends Object>>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$8
        @Override // kotlin.jvm.functions.Function1
        public final Map<String, Object> invoke(CustomTypeValue<?> value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if (value instanceof CustomTypeValue.GraphQLJsonObject) {
                return (Map) ((CustomTypeValue.GraphQLJsonObject) value).value;
            }
            throw new IllegalArgumentException("Can't decode: " + value + " into Map");
        }
    })), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.util.List", "kotlin.collections.List"}, new Function1<CustomTypeValue<?>, List<? extends Object>>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$9
        @Override // kotlin.jvm.functions.Function1
        public final List<Object> invoke(CustomTypeValue<?> value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            if (value instanceof CustomTypeValue.GraphQLJsonList) {
                return (List) ((CustomTypeValue.GraphQLJsonList) value).value;
            }
            throw new IllegalArgumentException("Can't decode: " + value + " into List");
        }
    })), INSTANCE.createDefaultScalarTypeAdapter(new String[]{"java.lang.Object", "kotlin.Any"}, new Function1<CustomTypeValue<?>, Object>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$DEFAULT_ADAPTERS$10
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(CustomTypeValue<?> value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            T t = value.value;
            if (t == 0) {
                Intrinsics.throwNpe();
            }
            return t;
        }
    }));
    private final Map<ScalarType, CustomTypeAdapter<?>> customAdapters;
    private final Map<String, CustomTypeAdapter<?>> customTypeAdapters;

    /* JADX WARN: Multi-variable type inference failed */
    public ScalarTypeAdapters(Map<ScalarType, ? extends CustomTypeAdapter<?>> customAdapters) {
        Intrinsics.checkParameterIsNotNull(customAdapters, "customAdapters");
        this.customAdapters = customAdapters;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(customAdapters.size()));
        for (Map.Entry entry : customAdapters.entrySet()) {
            linkedHashMap.put(((ScalarType) entry.getKey()).typeName(), entry.getValue());
        }
        this.customTypeAdapters = linkedHashMap;
    }

    public final Map<ScalarType, CustomTypeAdapter<?>> getCustomAdapters() {
        return this.customAdapters;
    }

    public final <T> CustomTypeAdapter<T> adapterFor(ScalarType scalarType) {
        Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
        CustomTypeAdapter<T> customTypeAdapter = (CustomTypeAdapter) this.customTypeAdapters.get(scalarType.typeName());
        if (customTypeAdapter == null) {
            customTypeAdapter = (CustomTypeAdapter) DEFAULT_ADAPTERS.get(scalarType.className());
        }
        if (customTypeAdapter != null) {
            return customTypeAdapter;
        }
        throw new IllegalArgumentException(("Can't map GraphQL type: `" + scalarType.typeName() + "` to: `" + scalarType.className() + "`. Did you forget to add a custom type adapter?").toString());
    }

    /* JADX INFO: compiled from: ScalarTypeAdapters.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JX\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00062\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u000b\"\u00020\u00072%\u0010\f\u001a!\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00010\rH\u0002¢\u0006\u0002\u0010\u0012R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u000e\u0012\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/apollographql/apollo/api/ScalarTypeAdapters$Companion;", "", "()V", "DEFAULT", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "DEFAULT_ADAPTERS", "", "", "Lcom/apollographql/apollo/api/CustomTypeAdapter;", "createDefaultScalarTypeAdapter", "classNames", "", "decode", "Lkotlin/Function1;", "Lcom/apollographql/apollo/api/CustomTypeValue;", "Lkotlin/ParameterName;", "name", "value", "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Map<String, CustomTypeAdapter<?>> createDefaultScalarTypeAdapter(String[] classNames, final Function1<? super CustomTypeValue<?>, ? extends Object> decode) {
            CustomTypeAdapter<Object> customTypeAdapter = new CustomTypeAdapter<Object>() { // from class: com.apollographql.apollo.api.ScalarTypeAdapters$Companion$createDefaultScalarTypeAdapter$adapter$1
                @Override // com.apollographql.apollo.api.CustomTypeAdapter
                public Object decode(CustomTypeValue<?> value) {
                    Intrinsics.checkParameterIsNotNull(value, "value");
                    return decode.invoke(value);
                }

                @Override // com.apollographql.apollo.api.CustomTypeAdapter
                public CustomTypeValue<?> encode(Object value) {
                    Intrinsics.checkParameterIsNotNull(value, "value");
                    return CustomTypeValue.INSTANCE.fromRawValue(value);
                }
            };
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(classNames.length), 16));
            for (String str : classNames) {
                Pair pair = TuplesKt.to(str, customTypeAdapter);
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            return linkedHashMap;
        }
    }
}
