package com.apollographql.apollo.response;

import com.apollographql.apollo.api.Error;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Operation.Data;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.api.internal.json.BufferedSourceJsonReader;
import com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader;
import com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer;
import com.apollographql.apollo.internal.field.MapFieldValueResolver;
import com.apollographql.apollo.internal.response.RealResponseReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okio.BufferedSource;

/* JADX INFO: loaded from: classes.dex */
public final class OperationResponseParser<D extends Operation.Data, W> {
    final Operation<D, W, ?> operation;
    final ResponseFieldMapper responseFieldMapper;
    final ResponseNormalizer<Map<String, Object>> responseNormalizer;
    final ScalarTypeAdapters scalarTypeAdapters;

    public OperationResponseParser(Operation<D, W, ?> operation, ResponseFieldMapper responseFieldMapper, ScalarTypeAdapters scalarTypeAdapters) {
        this(operation, responseFieldMapper, scalarTypeAdapters, ResponseNormalizer.NO_OP_NORMALIZER);
    }

    public OperationResponseParser(Operation<D, W, ?> operation, ResponseFieldMapper responseFieldMapper, ScalarTypeAdapters scalarTypeAdapters, ResponseNormalizer<Map<String, Object>> responseNormalizer) {
        this.operation = operation;
        this.responseFieldMapper = responseFieldMapper;
        this.scalarTypeAdapters = scalarTypeAdapters;
        this.responseNormalizer = responseNormalizer;
    }

    public Response<W> parse(Map<String, Object> map) {
        List list;
        Utils.checkNotNull(map, "payload == null");
        this.responseNormalizer.willResolveRootQuery((Operation<?, ?, ?>) this.operation);
        Map map2 = (Map) map.get("data");
        ArrayList arrayList = null;
        Operation.Data data = map2 != null ? (Operation.Data) this.responseFieldMapper.map(new RealResponseReader(this.operation.variables(), map2, new MapFieldValueResolver(), this.scalarTypeAdapters, this.responseNormalizer)) : null;
        if (map.containsKey("errors") && (list = (List) map.get("errors")) != null) {
            arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(parseError((Map) it.next()));
            }
        }
        return Response.builder(this.operation).data(this.operation.wrapData(data)).errors(arrayList).dependentKeys(this.responseNormalizer.dependentKeys()).extensions((Map) map.get("extensions")).build();
    }

    public Response<W> parse(BufferedSource bufferedSource) throws Throwable {
        this.responseNormalizer.willResolveRootQuery((Operation<?, ?, ?>) this.operation);
        BufferedSourceJsonReader bufferedSourceJsonReader = null;
        Operation.Data data = null;
        try {
            BufferedSourceJsonReader bufferedSourceJsonReader2 = new BufferedSourceJsonReader(bufferedSource);
            try {
                bufferedSourceJsonReader2.beginObject();
                ResponseJsonStreamReader responseJsonStreamReader = new ResponseJsonStreamReader(bufferedSourceJsonReader2);
                List<Error> responseErrors = null;
                Map<String, ? extends Object> map = null;
                while (responseJsonStreamReader.hasNext()) {
                    String strNextName = responseJsonStreamReader.nextName();
                    if ("data".equals(strNextName)) {
                        data = (Operation.Data) responseJsonStreamReader.nextObject(true, new ResponseJsonStreamReader.ObjectReader<Object>() { // from class: com.apollographql.apollo.response.OperationResponseParser.1
                            @Override // com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader.ObjectReader
                            public Object read(ResponseJsonStreamReader responseJsonStreamReader2) throws IOException {
                                return OperationResponseParser.this.responseFieldMapper.map(new RealResponseReader(OperationResponseParser.this.operation.variables(), responseJsonStreamReader2.toMap(), new MapFieldValueResolver(), OperationResponseParser.this.scalarTypeAdapters, OperationResponseParser.this.responseNormalizer));
                            }
                        });
                    } else if ("errors".equals(strNextName)) {
                        responseErrors = readResponseErrors(responseJsonStreamReader);
                    } else if ("extensions".equals(strNextName)) {
                        map = (Map) responseJsonStreamReader.nextObject(true, new ResponseJsonStreamReader.ObjectReader<Map<String, Object>>() { // from class: com.apollographql.apollo.response.OperationResponseParser.2
                            @Override // com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader.ObjectReader
                            public Map<String, Object> read(ResponseJsonStreamReader responseJsonStreamReader2) throws IOException {
                                return responseJsonStreamReader2.toMap();
                            }
                        });
                    } else {
                        responseJsonStreamReader.skipNext();
                    }
                }
                bufferedSourceJsonReader2.endObject();
                Response<W> responseBuild = Response.builder(this.operation).data(this.operation.wrapData(data)).errors(responseErrors).dependentKeys(this.responseNormalizer.dependentKeys()).extensions(map).build();
                bufferedSourceJsonReader2.close();
                return responseBuild;
            } catch (Throwable th) {
                th = th;
                bufferedSourceJsonReader = bufferedSourceJsonReader2;
                if (bufferedSourceJsonReader != null) {
                    bufferedSourceJsonReader.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private List<Error> readResponseErrors(ResponseJsonStreamReader responseJsonStreamReader) throws IOException {
        return responseJsonStreamReader.nextList(true, new ResponseJsonStreamReader.ListReader<Error>() { // from class: com.apollographql.apollo.response.OperationResponseParser.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader.ListReader
            public Error read(ResponseJsonStreamReader responseJsonStreamReader2) throws IOException {
                return (Error) responseJsonStreamReader2.nextObject(true, new ResponseJsonStreamReader.ObjectReader<Error>() { // from class: com.apollographql.apollo.response.OperationResponseParser.3.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader.ObjectReader
                    public Error read(ResponseJsonStreamReader responseJsonStreamReader3) throws IOException {
                        return OperationResponseParser.parseError(responseJsonStreamReader3.toMap());
                    }
                });
            }
        });
    }

    public static Error parseError(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        HashMap map2 = new HashMap();
        while (true) {
            String string = "";
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("message".equals(entry.getKey())) {
                    Object value = entry.getValue();
                    if (value != null) {
                        string = value.toString();
                    }
                } else if ("locations".equals(entry.getKey())) {
                    List list = (List) entry.getValue();
                    if (list != null) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add(parseErrorLocation((Map) it.next()));
                        }
                    }
                } else if (entry.getValue() != null) {
                    map2.put(entry.getKey(), entry.getValue());
                }
            }
            return new Error(string, arrayList, map2);
        }
    }

    private static Error.Location parseErrorLocation(Map<String, Object> map) {
        long jLongValue;
        long jLongValue2 = -1;
        if (map != null) {
            jLongValue = -1;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("line".equals(entry.getKey())) {
                    jLongValue2 = ((Number) entry.getValue()).longValue();
                } else if ("column".equals(entry.getKey())) {
                    jLongValue = ((Number) entry.getValue()).longValue();
                }
            }
        } else {
            jLongValue = -1;
        }
        return new Error.Location(jLongValue2, jLongValue);
    }
}
