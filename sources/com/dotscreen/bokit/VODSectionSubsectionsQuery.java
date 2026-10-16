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
import com.dotscreen.bokit.fragment.CatalogSectionFragment;
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
public final class VODSectionSubsectionsQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "ba21fd28287e94bff0d08d225d817e34633c79738ed422ba7a3aa3e12ee5caf6";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODSectionSubsections($catalogId: ID!, $sectionId: ID!) {\n  lookupService(serviceId: $catalogId) {\n    __typename\n    ... on ContentCatalog {\n      section(sectionId: $sectionId) {\n        __typename\n        subsections {\n          __typename\n          ...CatalogSectionFragment\n        }\n      }\n    }\n  }\n}\nfragment CatalogSectionFragment on CatalogSection {\n  __typename\n  id\n  name\n  artworks {\n    __typename\n    source(size: {width: 1234, height: 1234})\n  }\n  classifiers {\n    __typename\n    id\n    name\n    ... on ContentRating {\n      level\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODSectionSubsections";
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

    public VODSectionSubsectionsQuery(String str, String str2) {
        Utils.checkNotNull(str, "catalogId == null");
        Utils.checkNotNull(str2, "sectionId == null");
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
        private String sectionId;

        Builder() {
        }

        public Builder catalogId(String str) {
            this.catalogId = str;
            return this;
        }

        public Builder sectionId(String str) {
            this.sectionId = str;
            return this;
        }

        public VODSectionSubsectionsQuery build() {
            Utils.checkNotNull(this.catalogId, "catalogId == null");
            Utils.checkNotNull(this.sectionId, "sectionId == null");
            return new VODSectionSubsectionsQuery(this.catalogId, this.sectionId);
        }
    }

    public static final class Variables extends Operation.Variables {
        private final String catalogId;
        private final String sectionId;
        private final transient Map<String, Object> valueMap;

        Variables(String str, String str2) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.valueMap = linkedHashMap;
            this.catalogId = str;
            this.sectionId = str2;
            linkedHashMap.put("catalogId", str);
            this.valueMap.put("sectionId", str2);
        }

        public String catalogId() {
            return this.catalogId;
        }

        public String sectionId() {
            return this.sectionId;
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(this.valueMap);
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public InputFieldMarshaller marshaller() {
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Variables.1
                @Override // com.apollographql.apollo.api.internal.InputFieldMarshaller
                public void marshal(InputFieldWriter inputFieldWriter) throws IOException {
                    inputFieldWriter.writeCustom("catalogId", CustomType.ID, Variables.this.catalogId);
                    inputFieldWriter.writeCustom("sectionId", CustomType.ID, Variables.this.sectionId);
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Data.1
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
                return new Data((LookupService) responseReader.readObject(Data.$responseFields[0], new ResponseReader.ObjectReader<LookupService>() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Data.Mapper.1
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
                AsContentCatalog asContentCatalog = (AsContentCatalog) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsContentCatalog>() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.LookupService.Mapper.1
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

        @Override // com.dotscreen.bokit.VODSectionSubsectionsQuery.LookupService
        public String __typename() {
            return this.__typename;
        }

        public Section section() {
            return this.section;
        }

        @Override // com.dotscreen.bokit.VODSectionSubsectionsQuery.LookupService
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.AsContentCatalog.1
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
                return new AsContentCatalog(responseReader.readString(AsContentCatalog.$responseFields[0]), (Section) responseReader.readObject(AsContentCatalog.$responseFields[1], new ResponseReader.ObjectReader<Section>() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.AsContentCatalog.Mapper.1
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
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("subsections", "subsections", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Subsection> subsections;

        public Section(String str, List<Subsection> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.subsections = (List) Utils.checkNotNull(list, "subsections == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public List<Subsection> subsections() {
            return this.subsections;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Section.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Section.$responseFields[0], Section.this.__typename);
                    responseWriter.writeList(Section.$responseFields[1], Section.this.subsections, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Section.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Subsection) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Section{__typename=" + this.__typename + ", subsections=" + this.subsections + "}";
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
            return this.__typename.equals(section.__typename) && this.subsections.equals(section.subsections);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.subsections.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Section> {
            final Subsection.Mapper subsectionFieldMapper = new Subsection.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Section map(ResponseReader responseReader) {
                return new Section(responseReader.readString(Section.$responseFields[0]), responseReader.readList(Section.$responseFields[1], new ResponseReader.ListReader<Subsection>() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Section.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Subsection read(ResponseReader.ListItemReader listItemReader) {
                        return (Subsection) listItemReader.readObject(new ResponseReader.ObjectReader<Subsection>() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Section.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Subsection read(ResponseReader responseReader2) {
                                return Mapper.this.subsectionFieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class Subsection {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        private final Fragments fragments;

        public Subsection(String str, Fragments fragments) {
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Subsection.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Subsection.$responseFields[0], Subsection.this.__typename);
                    Subsection.this.fragments.marshaller().marshal(responseWriter);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Subsection{__typename=" + this.__typename + ", fragments=" + this.fragments + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Subsection)) {
                return false;
            }
            Subsection subsection = (Subsection) obj;
            return this.__typename.equals(subsection.__typename) && this.fragments.equals(subsection.fragments);
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
                return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Subsection.Fragments.1
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
                    return new Fragments((CatalogSectionFragment) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<CatalogSectionFragment>() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.Subsection.Fragments.Mapper.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public CatalogSectionFragment read(ResponseReader responseReader2) {
                            return Mapper.this.catalogSectionFragmentFieldMapper.map(responseReader2);
                        }
                    }));
                }
            }
        }

        public static final class Mapper implements ResponseFieldMapper<Subsection> {
            final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Subsection map(ResponseReader responseReader) {
                return new Subsection(responseReader.readString(Subsection.$responseFields[0]), this.fragmentsFieldMapper.map(responseReader));
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

        @Override // com.dotscreen.bokit.VODSectionSubsectionsQuery.LookupService
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.VODSectionSubsectionsQuery.LookupService
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionSubsectionsQuery.AsService.1
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
