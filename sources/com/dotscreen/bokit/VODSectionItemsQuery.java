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
public final class VODSectionItemsQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "7e0576da58f49eacd738f41b37ceccc5b2a14c3c1ba0ad380ac05c7ddd6b28e4";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODSectionItems($catalogId: ID!, $sectionId: ID!) {\n  lookupService(serviceId: $catalogId) {\n    __typename\n    ... on ContentCatalog {\n      section(sectionId: $sectionId) {\n        __typename\n        id\n        contents {\n          __typename\n          items {\n            __typename\n            ...ContentFragmentBase\n          }\n        }\n      }\n    }\n  }\n}\nfragment ContentFragmentBase on Content {\n  __typename\n  id\n  title\n  description\n  year\n  originalDuration\n  classifiers {\n    __typename\n    id\n    name\n    ... on ContentRating {\n      level\n    }\n  }\n  artworks {\n    __typename\n    source(size: {width: 1234, height: 1234})\n  }\n  externalIds {\n    __typename\n    scheme\n    identifier\n  }\n  offers(linear: false) {\n    __typename\n    items {\n      __typename\n      ... on TransactionalVOD {\n        streamId\n        name\n        price {\n          __typename\n          amount\n          currency\n        }\n      }\n      ... on SubscriptionVOD {\n        streamId\n        name\n      }\n      ... on CatchUpTV {\n        streamId\n        name\n      }\n      ... on ExternalOffer {\n        name\n        location\n        __typename\n      }\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODSectionItems";
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

    public VODSectionItemsQuery(String str, String str2) {
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

        public VODSectionItemsQuery build() {
            Utils.checkNotNull(this.catalogId, "catalogId == null");
            Utils.checkNotNull(this.sectionId, "sectionId == null");
            return new VODSectionItemsQuery(this.catalogId, this.sectionId);
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
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Variables.1
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Data.1
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
                return new Data((LookupService) responseReader.readObject(Data.$responseFields[0], new ResponseReader.ObjectReader<LookupService>() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Data.Mapper.1
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
                AsContentCatalog asContentCatalog = (AsContentCatalog) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsContentCatalog>() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.LookupService.Mapper.1
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

        @Override // com.dotscreen.bokit.VODSectionItemsQuery.LookupService
        public String __typename() {
            return this.__typename;
        }

        public Section section() {
            return this.section;
        }

        @Override // com.dotscreen.bokit.VODSectionItemsQuery.LookupService
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.AsContentCatalog.1
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
                return new AsContentCatalog(responseReader.readString(AsContentCatalog.$responseFields[0]), (Section) responseReader.readObject(AsContentCatalog.$responseFields[1], new ResponseReader.ObjectReader<Section>() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.AsContentCatalog.Mapper.1
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
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forObject("contents", "contents", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Contents contents;
        final String id;

        public Section(String str, String str2, Contents contents) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.contents = (Contents) Utils.checkNotNull(contents, "contents == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String id() {
            return this.id;
        }

        public Contents contents() {
            return this.contents;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Section.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Section.$responseFields[0], Section.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Section.$responseFields[1], Section.this.id);
                    responseWriter.writeObject(Section.$responseFields[2], Section.this.contents.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Section{__typename=" + this.__typename + ", id=" + this.id + ", contents=" + this.contents + "}";
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
            return this.__typename.equals(section.__typename) && this.id.equals(section.id) && this.contents.equals(section.contents);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.contents.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Section> {
            final Contents.Mapper contentsFieldMapper = new Contents.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Section map(ResponseReader responseReader) {
                return new Section(responseReader.readString(Section.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Section.$responseFields[1]), (Contents) responseReader.readObject(Section.$responseFields[2], new ResponseReader.ObjectReader<Contents>() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Section.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Contents read(ResponseReader responseReader2) {
                        return Mapper.this.contentsFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Contents {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("items", "items", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Item> items;

        public Contents(String str, List<Item> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.items = (List) Utils.checkNotNull(list, "items == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public List<Item> items() {
            return this.items;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Contents.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Contents.$responseFields[0], Contents.this.__typename);
                    responseWriter.writeList(Contents.$responseFields[1], Contents.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Contents.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Item) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Contents{__typename=" + this.__typename + ", items=" + this.items + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Contents)) {
                return false;
            }
            Contents contents = (Contents) obj;
            return this.__typename.equals(contents.__typename) && this.items.equals(contents.items);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.items.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Contents> {
            final Item.Mapper itemFieldMapper = new Item.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Contents map(ResponseReader responseReader) {
                return new Contents(responseReader.readString(Contents.$responseFields[0]), responseReader.readList(Contents.$responseFields[1], new ResponseReader.ListReader<Item>() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Contents.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item read(ResponseReader.ListItemReader listItemReader) {
                        return (Item) listItemReader.readObject(new ResponseReader.ObjectReader<Item>() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Contents.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Item read(ResponseReader responseReader2) {
                                return Mapper.this.itemFieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        private final Fragments fragments;

        public Item(String str, Fragments fragments) {
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Item.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Item.$responseFields[0], Item.this.__typename);
                    Item.this.fragments.marshaller().marshal(responseWriter);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Item{__typename=" + this.__typename + ", fragments=" + this.fragments + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Item)) {
                return false;
            }
            Item item = (Item) obj;
            return this.__typename.equals(item.__typename) && this.fragments.equals(item.fragments);
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
                return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Item.Fragments.1
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
                    return new Fragments((ContentFragmentBase) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<ContentFragmentBase>() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.Item.Fragments.Mapper.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ContentFragmentBase read(ResponseReader responseReader2) {
                            return Mapper.this.contentFragmentBaseFieldMapper.map(responseReader2);
                        }
                    }));
                }
            }
        }

        public static final class Mapper implements ResponseFieldMapper<Item> {
            final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Item map(ResponseReader responseReader) {
                return new Item(responseReader.readString(Item.$responseFields[0]), this.fragmentsFieldMapper.map(responseReader));
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

        @Override // com.dotscreen.bokit.VODSectionItemsQuery.LookupService
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.VODSectionItemsQuery.LookupService
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODSectionItemsQuery.AsService.1
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
