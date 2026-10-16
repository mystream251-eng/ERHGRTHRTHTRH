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
import com.dotscreen.bokit.fragment.ContentFragmentBase;
import com.dotscreen.bokit.type.CustomType;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class VODContentDetailsListQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "5107f91fbe3b4a4c3ce41175d27ce1840c2848073c10bbfe8f129dfe7c96e515";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODContentDetailsList($contentIds: [ID!]!) {\n  lookupContentList(ignoreUnknown: true, contentIds: $contentIds) {\n    __typename\n    ...ContentFragmentBase\n  }\n}\nfragment ContentFragmentBase on Content {\n  __typename\n  id\n  title\n  description\n  year\n  originalDuration\n  classifiers {\n    __typename\n    id\n    name\n    ... on ContentRating {\n      level\n    }\n  }\n  artworks {\n    __typename\n    source(size: {width: 1234, height: 1234})\n  }\n  externalIds {\n    __typename\n    scheme\n    identifier\n  }\n  offers(linear: false) {\n    __typename\n    items {\n      __typename\n      ... on TransactionalVOD {\n        streamId\n        name\n        price {\n          __typename\n          amount\n          currency\n        }\n      }\n      ... on SubscriptionVOD {\n        streamId\n        name\n      }\n      ... on CatchUpTV {\n        streamId\n        name\n      }\n      ... on ExternalOffer {\n        name\n        location\n        __typename\n      }\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODContentDetailsList";
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

    public VODContentDetailsListQuery(List<String> list) {
        Utils.checkNotNull(list, "contentIds == null");
        this.variables = new Variables(list);
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
        private List<String> contentIds;

        Builder() {
        }

        public Builder contentIds(List<String> list) {
            this.contentIds = list;
            return this;
        }

        public VODContentDetailsListQuery build() {
            Utils.checkNotNull(this.contentIds, "contentIds == null");
            return new VODContentDetailsListQuery(this.contentIds);
        }
    }

    public static final class Variables extends Operation.Variables {
        private final List<String> contentIds;
        private final transient Map<String, Object> valueMap;

        Variables(List<String> list) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.valueMap = linkedHashMap;
            this.contentIds = list;
            linkedHashMap.put("contentIds", list);
        }

        public List<String> contentIds() {
            return this.contentIds;
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(this.valueMap);
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public InputFieldMarshaller marshaller() {
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.Variables.1
                @Override // com.apollographql.apollo.api.internal.InputFieldMarshaller
                public void marshal(InputFieldWriter inputFieldWriter) throws IOException {
                    inputFieldWriter.writeList("contentIds", new InputFieldWriter.ListWriter() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.Variables.1.1
                        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListWriter
                        public void write(InputFieldWriter.ListItemWriter listItemWriter) throws IOException {
                            Iterator it = Variables.this.contentIds.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeCustom(CustomType.ID, (String) it.next());
                            }
                        }
                    });
                }
            };
        }
    }

    public static class Data implements Operation.Data {
        static final ResponseField[] $responseFields = {ResponseField.forList("lookupContentList", "lookupContentList", new UnmodifiableMapBuilder(2).put("ignoreUnknown", true).put("contentIds", new UnmodifiableMapBuilder(2).put("kind", "Variable").put(ResponseField.VARIABLE_NAME_KEY, "contentIds").build()).build(), true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final List<LookupContentList> lookupContentList;

        public Data(List<LookupContentList> list) {
            this.lookupContentList = list;
        }

        public List<LookupContentList> lookupContentList() {
            return this.lookupContentList;
        }

        @Override // com.apollographql.apollo.api.Operation.Data
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.Data.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeList(Data.$responseFields[0], Data.this.lookupContentList, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.Data.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((LookupContentList) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Data{lookupContentList=" + this.lookupContentList + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Data)) {
                return false;
            }
            List<LookupContentList> list = this.lookupContentList;
            List<LookupContentList> list2 = ((Data) obj).lookupContentList;
            if (list == null) {
                return list2 == null;
            }
            return list.equals(list2);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                List<LookupContentList> list = this.lookupContentList;
                this.$hashCode = 1000003 ^ (list == null ? 0 : list.hashCode());
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Data> {
            final LookupContentList.Mapper lookupContentListFieldMapper = new LookupContentList.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Data map(ResponseReader responseReader) {
                return new Data(responseReader.readList(Data.$responseFields[0], new ResponseReader.ListReader<LookupContentList>() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.Data.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LookupContentList read(ResponseReader.ListItemReader listItemReader) {
                        return (LookupContentList) listItemReader.readObject(new ResponseReader.ObjectReader<LookupContentList>() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.Data.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public LookupContentList read(ResponseReader responseReader2) {
                                return Mapper.this.lookupContentListFieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class LookupContentList {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        private final Fragments fragments;

        public LookupContentList(String str, Fragments fragments) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.fragments = (Fragments) Utils.checkNotNull(fragments, "fragments == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public Fragments fragments() {
            return this.fragments;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.LookupContentList.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(LookupContentList.$responseFields[0], LookupContentList.this.__typename);
                    LookupContentList.this.fragments.marshaller().marshal(responseWriter);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "LookupContentList{__typename=" + this.__typename + ", fragments=" + this.fragments + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LookupContentList)) {
                return false;
            }
            LookupContentList lookupContentList = (LookupContentList) obj;
            return this.__typename.equals(lookupContentList.__typename) && this.fragments.equals(lookupContentList.fragments);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.fragments.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static class Fragments {
            private volatile transient int $hashCode;
            private volatile transient boolean $hashCodeMemoized;
            private volatile transient String $toString;
            final ContentFragmentBase contentFragmentBase;

            public Fragments(ContentFragmentBase contentFragmentBase) {
                this.contentFragmentBase = (ContentFragmentBase) Utils.checkNotNull(contentFragmentBase, "contentFragmentBase == null");
            }

            public ContentFragmentBase contentFragmentBase() {
                return this.contentFragmentBase;
            }

            public ResponseFieldMarshaller marshaller() {
                return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.LookupContentList.Fragments.1
                    @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                    public void marshal(ResponseWriter responseWriter) {
                        responseWriter.writeFragment(Fragments.this.contentFragmentBase.marshaller());
                    }
                };
            }

            public String toString() {
                if (this.$toString == null) {
                    this.$toString = "Fragments{contentFragmentBase=" + this.contentFragmentBase + "}";
                }
                return this.$toString;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Fragments) {
                    return this.contentFragmentBase.equals(((Fragments) obj).contentFragmentBase);
                }
                return false;
            }

            public int hashCode() {
                if (!this.$hashCodeMemoized) {
                    this.$hashCode = 1000003 ^ this.contentFragmentBase.hashCode();
                    this.$hashCodeMemoized = true;
                }
                return this.$hashCode;
            }

            public static final class Mapper implements ResponseFieldMapper<Fragments> {
                static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Collections.emptyList())};
                final ContentFragmentBase.Mapper contentFragmentBaseFieldMapper = new ContentFragmentBase.Mapper();

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
                public Fragments map(ResponseReader responseReader) {
                    return new Fragments((ContentFragmentBase) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<ContentFragmentBase>() { // from class: com.dotscreen.bokit.VODContentDetailsListQuery.LookupContentList.Fragments.Mapper.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ContentFragmentBase read(ResponseReader responseReader2) {
                            return Mapper.this.contentFragmentBaseFieldMapper.map(responseReader2);
                        }
                    }));
                }
            }
        }

        public static final class Mapper implements ResponseFieldMapper<LookupContentList> {
            final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public LookupContentList map(ResponseReader responseReader) {
                return new LookupContentList(responseReader.readString(LookupContentList.$responseFields[0]), this.fragmentsFieldMapper.map(responseReader));
            }
        }
    }
}
