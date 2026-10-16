package com.dotscreen.bokit;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer;
import com.apollographql.apollo.api.internal.QueryDocumentMinifier;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseReader;
import com.apollographql.apollo.api.internal.ResponseWriter;
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser;
import com.apollographql.apollo.api.internal.Utils;
import com.dotscreen.bokit.type.CustomType;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class VODTopLevelServicesQuery implements Query<Data, Data, Operation.Variables> {
    public static final String OPERATION_ID = "5e15d32bbb9c4b2e739223e1c10fb1a1b2e1d3a0a8542bd561700f270029c2e4";
    private final Operation.Variables variables = Operation.EMPTY_VARIABLES;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODTopLevelServices {\n  listTopLevelServices {\n    __typename\n    id\n    name\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODTopLevelServicesQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODTopLevelServices";
        }
    };

    @Override // com.apollographql.apollo.api.Operation
    public String operationId() {
        return OPERATION_ID;
    }

    @Override // com.apollographql.apollo.api.Operation
    public Data wrapData(Data data) {
        return data;
    }

    @Override // com.apollographql.apollo.api.Operation
    public String queryDocument() {
        return QUERY_DOCUMENT;
    }

    @Override // com.apollographql.apollo.api.Operation
    public Operation.Variables variables() {
        return this.variables;
    }

    @Override // com.apollographql.apollo.api.Operation
    public ResponseFieldMapper<Data> responseFieldMapper() {
        return new Data.Mapper();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // com.apollographql.apollo.api.Operation
    public OperationName name() {
        return OPERATION_NAME;
    }

    @Override // com.apollographql.apollo.api.Operation
    public Response<Data> parse(BufferedSource bufferedSource, ScalarTypeAdapters scalarTypeAdapters) throws IOException {
        return SimpleOperationResponseParser.parse(bufferedSource, this, scalarTypeAdapters);
    }

    @Override // com.apollographql.apollo.api.Operation
    public Response<Data> parse(ByteString byteString, ScalarTypeAdapters scalarTypeAdapters) throws IOException {
        return parse(new Buffer().write(byteString), scalarTypeAdapters);
    }

    @Override // com.apollographql.apollo.api.Operation
    public Response<Data> parse(BufferedSource bufferedSource) throws IOException {
        return parse(bufferedSource, ScalarTypeAdapters.DEFAULT);
    }

    @Override // com.apollographql.apollo.api.Operation
    public Response<Data> parse(ByteString byteString) throws IOException {
        return parse(byteString, ScalarTypeAdapters.DEFAULT);
    }

    @Override // com.apollographql.apollo.api.Operation
    public ByteString composeRequestBody(ScalarTypeAdapters scalarTypeAdapters) {
        return OperationRequestBodyComposer.compose(this, false, true, scalarTypeAdapters);
    }

    @Override // com.apollographql.apollo.api.Operation
    public ByteString composeRequestBody() {
        return OperationRequestBodyComposer.compose(this, false, true, ScalarTypeAdapters.DEFAULT);
    }

    @Override // com.apollographql.apollo.api.Operation
    public ByteString composeRequestBody(boolean z, boolean z2, ScalarTypeAdapters scalarTypeAdapters) {
        return OperationRequestBodyComposer.compose(this, z, z2, scalarTypeAdapters);
    }

    public static final class Builder {
        Builder() {
        }

        public VODTopLevelServicesQuery build() {
            return new VODTopLevelServicesQuery();
        }
    }

    public static class Data implements Operation.Data {
        static final ResponseField[] $responseFields = {ResponseField.forList("listTopLevelServices", "listTopLevelServices", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final List<ListTopLevelService> listTopLevelServices;

        public Data(List<ListTopLevelService> list) {
            this.listTopLevelServices = (List) Utils.checkNotNull(list, "listTopLevelServices == null");
        }

        public List<ListTopLevelService> listTopLevelServices() {
            return this.listTopLevelServices;
        }

        @Override // com.apollographql.apollo.api.Operation.Data
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODTopLevelServicesQuery.Data.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeList(Data.$responseFields[0], Data.this.listTopLevelServices, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODTopLevelServicesQuery.Data.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((ListTopLevelService) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Data{listTopLevelServices=" + this.listTopLevelServices + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Data) {
                return this.listTopLevelServices.equals(((Data) obj).listTopLevelServices);
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = 1000003 ^ this.listTopLevelServices.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Data> {
            final ListTopLevelService.Mapper listTopLevelServiceFieldMapper = new ListTopLevelService.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Data map(ResponseReader responseReader) {
                return new Data(responseReader.readList(Data.$responseFields[0], new ResponseReader.ListReader<ListTopLevelService>() { // from class: com.dotscreen.bokit.VODTopLevelServicesQuery.Data.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public ListTopLevelService read(ResponseReader.ListItemReader listItemReader) {
                        return (ListTopLevelService) listItemReader.readObject(new ResponseReader.ObjectReader<ListTopLevelService>() { // from class: com.dotscreen.bokit.VODTopLevelServicesQuery.Data.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public ListTopLevelService read(ResponseReader responseReader2) {
                                return Mapper.this.listTopLevelServiceFieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class ListTopLevelService {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public ListTopLevelService(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String id() {
            return this.id;
        }

        public String name() {
            return this.name;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODTopLevelServicesQuery.ListTopLevelService.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(ListTopLevelService.$responseFields[0], ListTopLevelService.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) ListTopLevelService.$responseFields[1], ListTopLevelService.this.id);
                    responseWriter.writeString(ListTopLevelService.$responseFields[2], ListTopLevelService.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "ListTopLevelService{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ListTopLevelService)) {
                return false;
            }
            ListTopLevelService listTopLevelService = (ListTopLevelService) obj;
            return this.__typename.equals(listTopLevelService.__typename) && this.id.equals(listTopLevelService.id) && this.name.equals(listTopLevelService.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<ListTopLevelService> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public ListTopLevelService map(ResponseReader responseReader) {
                return new ListTopLevelService(responseReader.readString(ListTopLevelService.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) ListTopLevelService.$responseFields[1]), responseReader.readString(ListTopLevelService.$responseFields[2]));
            }
        }
    }
}
