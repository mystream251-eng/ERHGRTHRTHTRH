package com.dotscreen.bokit;

import com.apollographql.apollo.api.Input;
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
import com.dotscreen.bokit.fragment.CatalogSectionFragment;
import com.dotscreen.bokit.type.CustomType;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class VODCatalogSectionQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "11334fa0295d1f013db47ea911f05ae2dd438ddbd46021bb1eeaf84cb2e4c3ca";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODCatalogSection($catalogId: ID!, $sectionId: ID) {\n  lookupService(serviceId: $catalogId) {\n    __typename\n    ... on ContentCatalog {\n      section(sectionId: $sectionId) {\n        __typename\n        ...CatalogSectionFragment\n      }\n    }\n  }\n}\nfragment CatalogSectionFragment on CatalogSection {\n  __typename\n  id\n  name\n  artworks {\n    __typename\n    source(size: {width: 1234, height: 1234})\n  }\n  classifiers {\n    __typename\n    id\n    name\n    ... on ContentRating {\n      level\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODCatalogSection";
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

    public VODCatalogSectionQuery(String str, Input<String> input) {
        Utils.checkNotNull(str, "catalogId == null");
        Utils.checkNotNull(input, "sectionId == null");
        this.variables = new Variables(str, input);
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
        private Input<String> sectionId = Input.absent();

        Builder() {
        }

        public Builder catalogId(String str) {
            this.catalogId = str;
            return this;
        }

        public Builder sectionId(String str) {
            this.sectionId = Input.fromNullable(str);
            return this;
        }

        public Builder sectionIdInput(Input<String> input) {
            this.sectionId = (Input) Utils.checkNotNull(input, "sectionId == null");
            return this;
        }

        public VODCatalogSectionQuery build() {
            Utils.checkNotNull(this.catalogId, "catalogId == null");
            return new VODCatalogSectionQuery(this.catalogId, this.sectionId);
        }
    }

    public static final class Variables extends Operation.Variables {
        private final String catalogId;
        private final Input<String> sectionId;
        private final transient Map<String, Object> valueMap;

        Variables(String str, Input<String> input) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.valueMap = linkedHashMap;
            this.catalogId = str;
            this.sectionId = input;
            linkedHashMap.put("catalogId", str);
            if (input.defined) {
                this.valueMap.put("sectionId", input.value);
            }
        }

        public String catalogId() {
            return this.catalogId;
        }

        public Input<String> sectionId() {
            return this.sectionId;
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(this.valueMap);
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public InputFieldMarshaller marshaller() {
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.Variables.1
                @Override // com.apollographql.apollo.api.internal.InputFieldMarshaller
                public void marshal(InputFieldWriter inputFieldWriter) throws IOException {
                    inputFieldWriter.writeCustom("catalogId", CustomType.ID, Variables.this.catalogId);
                    if (Variables.this.sectionId.defined) {
                        inputFieldWriter.writeCustom("sectionId", CustomType.ID, Variables.this.sectionId.value != 0 ? Variables.this.sectionId.value : null);
                    }
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.Data.1
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
                return new Data((LookupService) responseReader.readObject(Data.$responseFields[0], new ResponseReader.ObjectReader<LookupService>() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.Data.Mapper.1
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
                AsContentCatalog asContentCatalog = (AsContentCatalog) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsContentCatalog>() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.LookupService.Mapper.1
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
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("section", "section", new UnmodifiableMapBuilder(1).put("sectionId", new UnmodifiableMapBuilder(2).put("kind", "Variable").put(ResponseField.VARIABLE_NAME_KEY, "sectionId").build()).build(), false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Section section;

        public AsContentCatalog(String str, Section section) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.section = (Section) Utils.checkNotNull(section, "section == null");
        }

        @Override // com.dotscreen.bokit.VODCatalogSectionQuery.LookupService
        public String __typename() {
            return this.__typename;
        }

        public Section section() {
            return this.section;
        }

        @Override // com.dotscreen.bokit.VODCatalogSectionQuery.LookupService
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.AsContentCatalog.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsContentCatalog.$responseFields[0], AsContentCatalog.this.__typename);
                    responseWriter.writeObject(AsContentCatalog.$responseFields[1], AsContentCatalog.this.section.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsContentCatalog{__typename=" + this.__typename + ", section=" + this.section + "}";
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
            return this.__typename.equals(asContentCatalog.__typename) && this.section.equals(asContentCatalog.section);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.section.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsContentCatalog> {
            final Section.Mapper sectionFieldMapper = new Section.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsContentCatalog map(ResponseReader responseReader) {
                return new AsContentCatalog(responseReader.readString(AsContentCatalog.$responseFields[0]), (Section) responseReader.readObject(AsContentCatalog.$responseFields[1], new ResponseReader.ObjectReader<Section>() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.AsContentCatalog.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Section read(ResponseReader responseReader2) {
                        return Mapper.this.sectionFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Section {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        private final Fragments fragments;

        public Section(String str, Fragments fragments) {
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.Section.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Section.$responseFields[0], Section.this.__typename);
                    Section.this.fragments.marshaller().marshal(responseWriter);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Section{__typename=" + this.__typename + ", fragments=" + this.fragments + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Section)) {
                return false;
            }
            Section section = (Section) obj;
            return this.__typename.equals(section.__typename) && this.fragments.equals(section.fragments);
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
            final CatalogSectionFragment catalogSectionFragment;

            public Fragments(CatalogSectionFragment catalogSectionFragment) {
                this.catalogSectionFragment = (CatalogSectionFragment) Utils.checkNotNull(catalogSectionFragment, "catalogSectionFragment == null");
            }

            public CatalogSectionFragment catalogSectionFragment() {
                return this.catalogSectionFragment;
            }

            public ResponseFieldMarshaller marshaller() {
                return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.Section.Fragments.1
                    @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                    public void marshal(ResponseWriter responseWriter) {
                        responseWriter.writeFragment(Fragments.this.catalogSectionFragment.marshaller());
                    }
                };
            }

            public String toString() {
                if (this.$toString == null) {
                    this.$toString = "Fragments{catalogSectionFragment=" + this.catalogSectionFragment + "}";
                }
                return this.$toString;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Fragments) {
                    return this.catalogSectionFragment.equals(((Fragments) obj).catalogSectionFragment);
                }
                return false;
            }

            public int hashCode() {
                if (!this.$hashCodeMemoized) {
                    this.$hashCode = 1000003 ^ this.catalogSectionFragment.hashCode();
                    this.$hashCodeMemoized = true;
                }
                return this.$hashCode;
            }

            public static final class Mapper implements ResponseFieldMapper<Fragments> {
                static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Collections.emptyList())};
                final CatalogSectionFragment.Mapper catalogSectionFragmentFieldMapper = new CatalogSectionFragment.Mapper();

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
                public Fragments map(ResponseReader responseReader) {
                    return new Fragments((CatalogSectionFragment) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<CatalogSectionFragment>() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.Section.Fragments.Mapper.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public CatalogSectionFragment read(ResponseReader responseReader2) {
                            return Mapper.this.catalogSectionFragmentFieldMapper.map(responseReader2);
                        }
                    }));
                }
            }
        }

        public static final class Mapper implements ResponseFieldMapper<Section> {
            final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Section map(ResponseReader responseReader) {
                return new Section(responseReader.readString(Section.$responseFields[0]), this.fragmentsFieldMapper.map(responseReader));
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

        @Override // com.dotscreen.bokit.VODCatalogSectionQuery.LookupService
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.VODCatalogSectionQuery.LookupService
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODCatalogSectionQuery.AsService.1
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
