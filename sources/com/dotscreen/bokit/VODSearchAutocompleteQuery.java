package com.dotscreen.bokit;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.InputFieldMarshaller;
import com.apollographql.apollo.api.internal.InputFieldWriter;
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer;
import com.apollographql.apollo.api.internal.QueryDocumentMinifier;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseReader;
import com.apollographql.apollo.api.internal.ResponseWriter;
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import com.dotscreen.bokit.type.CustomType;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class VODSearchAutocompleteQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "0a2db2d986574da358faad165e0339158add13e8fafacec30a2c4fa51210ad0e";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODSearchAutocomplete($catalogId: ID!, $term: String!) {\n  lookupService(serviceId: $catalogId) {\n    __typename\n    ... on ContentCatalog {\n      contentAutocomplete(term: $term)\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODSearchAutocomplete";
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

    public VODSearchAutocompleteQuery(String str, String str2) {
        Utils.checkNotNull(str, "catalogId == null");
        Utils.checkNotNull(str2, "term == null");
        this.variables = new Variables(str, str2);
    }

    @Override // com.apollographql.apollo.api.Operation
    public String queryDocument() {
        return QUERY_DOCUMENT;
    }

    @Override // com.apollographql.apollo.api.Operation
    public Variables variables() {
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
        private String catalogId;
        private String term;

        Builder() {
        }

        public Builder catalogId(String str) {
            this.catalogId = str;
            return this;
        }

        public Builder term(String str) {
            this.term = str;
            return this;
        }

        public VODSearchAutocompleteQuery build() {
            Utils.checkNotNull(this.catalogId, "catalogId == null");
            Utils.checkNotNull(this.term, "term == null");
            return new VODSearchAutocompleteQuery(this.catalogId, this.term);
        }
    }

    public static final class Variables extends Operation.Variables {
        private final String catalogId;
        private final String term;
        private final transient Map<String, Object> valueMap;

        Variables(String str, String str2) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.valueMap = linkedHashMap;
            this.catalogId = str;
            this.term = str2;
            linkedHashMap.put("catalogId", str);
            this.valueMap.put("term", str2);
        }

        public String catalogId() {
            return this.catalogId;
        }

        public String term() {
            return this.term;
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(this.valueMap);
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public InputFieldMarshaller marshaller() {
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.Variables.1
                @Override // com.apollographql.apollo.api.internal.InputFieldMarshaller
                public void marshal(InputFieldWriter inputFieldWriter) throws IOException {
                    inputFieldWriter.writeCustom("catalogId", CustomType.ID, Variables.this.catalogId);
                    inputFieldWriter.writeString("term", Variables.this.term);
                }
            };
        }
    }

    public static class Data implements Operation.Data {
        static final ResponseField[] $responseFields = {ResponseField.forObject("lookupService", "lookupService", new UnmodifiableMapBuilder(1).put("serviceId", new UnmodifiableMapBuilder(2).put("kind", "Variable").put(ResponseField.VARIABLE_NAME_KEY, "catalogId").build()).build(), false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final LookupService lookupService;

        public Data(LookupService lookupService) {
            this.lookupService = (LookupService) Utils.checkNotNull(lookupService, "lookupService == null");
        }

        public LookupService lookupService() {
            return this.lookupService;
        }

        @Override // com.apollographql.apollo.api.Operation.Data
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.Data.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeObject(Data.$responseFields[0], Data.this.lookupService.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Data{lookupService=" + this.lookupService + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Data) {
                return this.lookupService.equals(((Data) obj).lookupService);
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = 1000003 ^ this.lookupService.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Data> {
            final LookupService.Mapper lookupServiceFieldMapper = new LookupService.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Data map(ResponseReader responseReader) {
                return new Data((LookupService) responseReader.readObject(Data.$responseFields[0], new ResponseReader.ObjectReader<LookupService>() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.Data.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public LookupService read(ResponseReader responseReader2) {
                        return Mapper.this.lookupServiceFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public interface LookupService {
        String __typename();

        ResponseFieldMarshaller marshaller();

        public static final class Mapper implements ResponseFieldMapper<LookupService> {
            static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"ContentCatalog"})))};
            final AsContentCatalog.Mapper asContentCatalogFieldMapper = new AsContentCatalog.Mapper();
            final AsService.Mapper asServiceFieldMapper = new AsService.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public LookupService map(ResponseReader responseReader) {
                AsContentCatalog asContentCatalog = (AsContentCatalog) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsContentCatalog>() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.LookupService.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsContentCatalog read(ResponseReader responseReader2) {
                        return Mapper.this.asContentCatalogFieldMapper.map(responseReader2);
                    }
                });
                return asContentCatalog != null ? asContentCatalog : this.asServiceFieldMapper.map(responseReader);
            }
        }
    }

    public static class AsContentCatalog implements LookupService {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("contentAutocomplete", "contentAutocomplete", new UnmodifiableMapBuilder(1).put("term", new UnmodifiableMapBuilder(2).put("kind", "Variable").put(ResponseField.VARIABLE_NAME_KEY, "term").build()).build(), false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<String> contentAutocomplete;

        public AsContentCatalog(String str, List<String> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.contentAutocomplete = (List) Utils.checkNotNull(list, "contentAutocomplete == null");
        }

        @Override // com.dotscreen.bokit.VODSearchAutocompleteQuery.LookupService
        public String __typename() {
            return this.__typename;
        }

        public List<String> contentAutocomplete() {
            return this.contentAutocomplete;
        }

        @Override // com.dotscreen.bokit.VODSearchAutocompleteQuery.LookupService
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.AsContentCatalog.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsContentCatalog.$responseFields[0], AsContentCatalog.this.__typename);
                    responseWriter.writeList(AsContentCatalog.$responseFields[1], AsContentCatalog.this.contentAutocomplete, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.AsContentCatalog.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString((String) it.next());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsContentCatalog{__typename=" + this.__typename + ", contentAutocomplete=" + this.contentAutocomplete + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsContentCatalog)) {
                return false;
            }
            AsContentCatalog asContentCatalog = (AsContentCatalog) obj;
            return this.__typename.equals(asContentCatalog.__typename) && this.contentAutocomplete.equals(asContentCatalog.contentAutocomplete);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.contentAutocomplete.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsContentCatalog> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsContentCatalog map(ResponseReader responseReader) {
                return new AsContentCatalog(responseReader.readString(AsContentCatalog.$responseFields[0]), responseReader.readList(AsContentCatalog.$responseFields[1], new ResponseReader.ListReader<String>() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.AsContentCatalog.Mapper.1
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public String read(ResponseReader.ListItemReader listItemReader) {
                        return listItemReader.readString();
                    }
                }));
            }
        }
    }

    public static class AsService implements LookupService {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;

        public AsService(String str) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
        }

        @Override // com.dotscreen.bokit.VODSearchAutocompleteQuery.LookupService
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.VODSearchAutocompleteQuery.LookupService
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSearchAutocompleteQuery.AsService.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsService.$responseFields[0], AsService.this.__typename);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsService{__typename=" + this.__typename + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof AsService) {
                return this.__typename.equals(((AsService) obj).__typename);
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = 1000003 ^ this.__typename.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsService> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsService map(ResponseReader responseReader) {
                return new AsService(responseReader.readString(AsService.$responseFields[0]));
            }
        }
    }
}
