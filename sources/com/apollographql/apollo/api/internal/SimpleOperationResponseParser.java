package com.apollographql.apollo.api.internal;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.Error;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.json.BufferedSourceJsonReader;
import com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

/* JADX INFO: compiled from: SimpleOperationResponseParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0000\u0010\u0006*\u00020\u0007\"\u0004\b\u0001\u0010\u00052\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0005\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002JF\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0000\u0010\u0006*\u00020\u0007\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0005\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u001a\u0010\u0011\u001a\u00020\u0012*\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tH\u0002J\u001c\u0010\u0013\u001a\u00020\u0014*\u0012\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\tH\u0002¨\u0006\u0015"}, d2 = {"Lcom/apollographql/apollo/api/internal/SimpleOperationResponseParser;", "", "()V", "parse", "Lcom/apollographql/apollo/api/Response;", ExifInterface.LONGITUDE_WEST, "D", "Lcom/apollographql/apollo/api/Operation$Data;", "response", "", "", "operation", "Lcom/apollographql/apollo/api/Operation;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "source", "Lokio/BufferedSource;", "readError", "Lcom/apollographql/apollo/api/Error;", "readErrorLocation", "Lcom/apollographql/apollo/api/Error$Location;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class SimpleOperationResponseParser {
    public static final SimpleOperationResponseParser INSTANCE = new SimpleOperationResponseParser();

    private SimpleOperationResponseParser() {
    }

    @JvmStatic
    public static final <D extends Operation.Data, W> Response<W> parse(BufferedSource source, Operation<D, W, ?> operation, ScalarTypeAdapters scalarTypeAdapters) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, "source");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
        BufferedSourceJsonReader bufferedSourceJsonReader = new BufferedSourceJsonReader(source);
        try {
            BufferedSourceJsonReader bufferedSourceJsonReader2 = bufferedSourceJsonReader;
            bufferedSourceJsonReader2.beginObject();
            Map<String, ? extends Object> map = new ResponseJsonStreamReader(bufferedSourceJsonReader2).toMap();
            if (map == null) {
                map = MapsKt.emptyMap();
            }
            Response<W> response = INSTANCE.parse(map, operation, scalarTypeAdapters);
            bufferedSourceJsonReader.close();
            return response;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    bufferedSourceJsonReader.close();
                } catch (Throwable unused) {
                }
                throw th2;
            }
        }
    }

    private final <D extends Operation.Data, W> Response<W> parse(Map<String, ? extends Object> response, Operation<D, W, ?> operation, ScalarTypeAdapters scalarTypeAdapters) {
        D map;
        ArrayList arrayList;
        Object obj = response.get("data");
        if (!(obj instanceof Map)) {
            obj = null;
        }
        Map map2 = (Map) obj;
        if (map2 != null) {
            map = operation.responseFieldMapper().map(new SimpleResponseReader((Map<String, ? extends Object>) map2, operation.variables(), scalarTypeAdapters));
        } else {
            map = null;
        }
        Object obj2 = response.get("errors");
        if (!(obj2 instanceof List)) {
            obj2 = null;
        }
        List list = (List) obj2;
        if (list != null) {
            List list2 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList2.add(INSTANCE.readError((Map) it.next()));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        W wWrapData = operation.wrapData(map);
        Object obj3 = response.get("extensions");
        Map map3 = (Map) (obj3 instanceof Map ? obj3 : null);
        return new Response<>(operation, wWrapData, arrayList, null, false, map3 != null ? map3 : MapsKt.emptyMap(), null, 88, null);
    }

    private final Error readError(Map<String, ? extends Object> map) {
        ArrayList arrayListEmptyList = CollectionsKt.emptyList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (true) {
            String string = "";
            for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                int iHashCode = key.hashCode();
                if (iHashCode != -1197189282) {
                    if (iHashCode != 954925063 || !key.equals("message")) {
                        linkedHashMap.put(key, value);
                    } else if (value == null || (string = value.toString()) == null) {
                    }
                } else if (key.equals("locations")) {
                    if (!(value instanceof List)) {
                        value = null;
                    }
                    List list = (List) value;
                    if (list != null) {
                        List list2 = list;
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                        Iterator it = list2.iterator();
                        while (it.hasNext()) {
                            arrayList.add(INSTANCE.readErrorLocation((Map) it.next()));
                        }
                        arrayListEmptyList = arrayList;
                    } else {
                        arrayListEmptyList = CollectionsKt.emptyList();
                    }
                } else {
                    linkedHashMap.put(key, value);
                }
            }
            return new Error(string, arrayListEmptyList, linkedHashMap);
        }
    }

    private final Error.Location readErrorLocation(Map<String, ? extends Object> map) {
        long jLongValue;
        long jLongValue2 = -1;
        if (map != null) {
            jLongValue = -1;
            for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                int iHashCode = key.hashCode();
                if (iHashCode != -1354837162) {
                    if (iHashCode == 3321844 && key.equals("line")) {
                        if (value == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Number");
                        }
                        jLongValue2 = ((Number) value).longValue();
                    }
                } else if (!key.equals("column")) {
                    continue;
                } else {
                    if (value == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Number");
                    }
                    jLongValue = ((Number) value).longValue();
                }
            }
        } else {
            jLongValue = -1;
        }
        return new Error.Location(jLongValue2, jLongValue);
    }
}
