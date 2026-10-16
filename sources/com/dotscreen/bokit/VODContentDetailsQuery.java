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
import com.dotscreen.bokit.fragment.ContentFragmentDetails;
import com.dotscreen.bokit.type.CustomType;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class VODContentDetailsQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "dce853ad75553dd5f28b593fdfe92b17c36036409d9b78a07c8f095dd771a4fd";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODContentDetails($contentId: ID!) {\n  lookupContent(contentId: $contentId) {\n    __typename\n    ...ContentFragmentBase\n    ...ContentFragmentDetails\n  }\n}\nfragment ContentFragmentBase on Content {\n  __typename\n  id\n  title\n  description\n  year\n  originalDuration\n  classifiers {\n    __typename\n    id\n    name\n    ... on ContentRating {\n      level\n    }\n  }\n  artworks {\n    __typename\n    source(size: {width: 1234, height: 1234})\n  }\n  externalIds {\n    __typename\n    scheme\n    identifier\n  }\n  offers(linear: false) {\n    __typename\n    items {\n      __typename\n      ... on TransactionalVOD {\n        streamId\n        name\n        price {\n          __typename\n          amount\n          currency\n        }\n      }\n      ... on SubscriptionVOD {\n        streamId\n        name\n      }\n      ... on CatchUpTV {\n        streamId\n        name\n      }\n      ... on ExternalOffer {\n        name\n        location\n        __typename\n      }\n    }\n  }\n}\nfragment ContentFragmentDetails on Content {\n  __typename\n  previews {\n    __typename\n    description\n    streamId\n  }\n  countries {\n    __typename\n    name\n    isoCode\n  }\n  cast {\n    __typename\n    person {\n      __typename\n      id\n      name\n    }\n    roleType\n    role\n  }\n  crew {\n    __typename\n    person {\n      __typename\n      id\n      name\n    }\n    department\n    task\n  }\n  offers(linear: false) {\n    __typename\n    items {\n      __typename\n      audioLanguages\n      subtitlesLanguages\n    }\n  }\n  ... on Series {\n    seasons {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n    episodes {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n  }\n  ... on Season {\n    seasonNumber\n    episodes {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n    series {\n      __typename\n      id\n    }\n  }\n  ... on Episode {\n    episodeNumber\n    series {\n      __typename\n      id\n    }\n    season {\n      __typename\n      id\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODContentDetailsQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODContentDetails";
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

    public VODContentDetailsQuery(String str) {
        Utils.checkNotNull(str, "contentId == null");
        this.variables = new Variables(str);
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
        private String contentId;

        Builder() {
        }

        public Builder contentId(String str) {
            this.contentId = str;
            return this;
        }

        public VODContentDetailsQuery build() {
            Utils.checkNotNull(this.contentId, "contentId == null");
            return new VODContentDetailsQuery(this.contentId);
        }
    }

    public static final class Variables extends Operation.Variables {
        private final String contentId;
        private final transient Map<String, Object> valueMap;

        Variables(String str) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.valueMap = linkedHashMap;
            this.contentId = str;
            linkedHashMap.put("contentId", str);
        }

        public String contentId() {
            return this.contentId;
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(this.valueMap);
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public InputFieldMarshaller marshaller() {
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentDetailsQuery.Variables.1
                @Override // com.apollographql.apollo.api.internal.InputFieldMarshaller
                public void marshal(InputFieldWriter inputFieldWriter) throws IOException {
                    inputFieldWriter.writeCustom("contentId", CustomType.ID, Variables.this.contentId);
                }
            };
        }
    }

    public static class Data implements Operation.Data {
        static final ResponseField[] $responseFields = {ResponseField.forObject("lookupContent", "lookupContent", new UnmodifiableMapBuilder(1).put("contentId", new UnmodifiableMapBuilder(2).put("kind", "Variable").put(ResponseField.VARIABLE_NAME_KEY, "contentId").build()).build(), true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final LookupContent lookupContent;

        public Data(LookupContent lookupContent) {
            this.lookupContent = lookupContent;
        }

        public LookupContent lookupContent() {
            return this.lookupContent;
        }

        @Override // com.apollographql.apollo.api.Operation.Data
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentDetailsQuery.Data.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeObject(Data.$responseFields[0], Data.this.lookupContent != null ? Data.this.lookupContent.marshaller() : null);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Data{lookupContent=" + this.lookupContent + "}";
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
            LookupContent lookupContent = this.lookupContent;
            LookupContent lookupContent2 = ((Data) obj).lookupContent;
            if (lookupContent == null) {
                return lookupContent2 == null;
            }
            return lookupContent.equals(lookupContent2);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                LookupContent lookupContent = this.lookupContent;
                this.$hashCode = 1000003 ^ (lookupContent == null ? 0 : lookupContent.hashCode());
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Data> {
            final LookupContent.Mapper lookupContentFieldMapper = new LookupContent.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Data map(ResponseReader responseReader) {
                return new Data((LookupContent) responseReader.readObject(Data.$responseFields[0], new ResponseReader.ObjectReader<LookupContent>() { // from class: com.dotscreen.bokit.VODContentDetailsQuery.Data.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public LookupContent read(ResponseReader responseReader2) {
                        return Mapper.this.lookupContentFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class LookupContent {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        private final Fragments fragments;

        public LookupContent(String str, Fragments fragments) {
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentDetailsQuery.LookupContent.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(LookupContent.$responseFields[0], LookupContent.this.__typename);
                    LookupContent.this.fragments.marshaller().marshal(responseWriter);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "LookupContent{__typename=" + this.__typename + ", fragments=" + this.fragments + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LookupContent)) {
                return false;
            }
            LookupContent lookupContent = (LookupContent) obj;
            return this.__typename.equals(lookupContent.__typename) && this.fragments.equals(lookupContent.fragments);
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
            final ContentFragmentDetails contentFragmentDetails;

            public Fragments(ContentFragmentBase contentFragmentBase, ContentFragmentDetails contentFragmentDetails) {
                this.contentFragmentBase = (ContentFragmentBase) Utils.checkNotNull(contentFragmentBase, "contentFragmentBase == null");
                this.contentFragmentDetails = (ContentFragmentDetails) Utils.checkNotNull(contentFragmentDetails, "contentFragmentDetails == null");
            }

            public ContentFragmentBase contentFragmentBase() {
                return this.contentFragmentBase;
            }

            public ContentFragmentDetails contentFragmentDetails() {
                return this.contentFragmentDetails;
            }

            public ResponseFieldMarshaller marshaller() {
                return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentDetailsQuery.LookupContent.Fragments.1
                    @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                    public void marshal(ResponseWriter responseWriter) {
                        responseWriter.writeFragment(Fragments.this.contentFragmentBase.marshaller());
                        responseWriter.writeFragment(Fragments.this.contentFragmentDetails.marshaller());
                    }
                };
            }

            public String toString() {
                if (this.$toString == null) {
                    this.$toString = "Fragments{contentFragmentBase=" + this.contentFragmentBase + ", contentFragmentDetails=" + this.contentFragmentDetails + "}";
                }
                return this.$toString;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Fragments)) {
                    return false;
                }
                Fragments fragments = (Fragments) obj;
                return this.contentFragmentBase.equals(fragments.contentFragmentBase) && this.contentFragmentDetails.equals(fragments.contentFragmentDetails);
            }

            public int hashCode() {
                if (!this.$hashCodeMemoized) {
                    this.$hashCode = ((this.contentFragmentBase.hashCode() ^ 1000003) * 1000003) ^ this.contentFragmentDetails.hashCode();
                    this.$hashCodeMemoized = true;
                }
                return this.$hashCode;
            }

            public static final class Mapper implements ResponseFieldMapper<Fragments> {
                static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Collections.emptyList()), ResponseField.forFragment("__typename", "__typename", Collections.emptyList())};
                final ContentFragmentBase.Mapper contentFragmentBaseFieldMapper = new ContentFragmentBase.Mapper();
                final ContentFragmentDetails.Mapper contentFragmentDetailsFieldMapper = new ContentFragmentDetails.Mapper();

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
                public Fragments map(ResponseReader responseReader) {
                    return new Fragments((ContentFragmentBase) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<ContentFragmentBase>() { // from class: com.dotscreen.bokit.VODContentDetailsQuery.LookupContent.Fragments.Mapper.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ContentFragmentBase read(ResponseReader responseReader2) {
                            return Mapper.this.contentFragmentBaseFieldMapper.map(responseReader2);
                        }
                    }), (ContentFragmentDetails) responseReader.readFragment($responseFields[1], new ResponseReader.ObjectReader<ContentFragmentDetails>() { // from class: com.dotscreen.bokit.VODContentDetailsQuery.LookupContent.Fragments.Mapper.2
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ContentFragmentDetails read(ResponseReader responseReader2) {
                            return Mapper.this.contentFragmentDetailsFieldMapper.map(responseReader2);
                        }
                    }));
                }
            }
        }

        public static final class Mapper implements ResponseFieldMapper<LookupContent> {
            final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public LookupContent map(ResponseReader responseReader) {
                return new LookupContent(responseReader.readString(LookupContent.$responseFields[0]), this.fragmentsFieldMapper.map(responseReader));
            }
        }
    }
}
