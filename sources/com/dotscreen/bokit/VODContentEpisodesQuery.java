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
public final class VODContentEpisodesQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "2c1f411092141aa61c7f5f4ddcaf9d0711a0a98379401cd2909c2e281c6339f0";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODContentEpisodes($contentId: ID!) {\n  lookupContent(contentId: $contentId) {\n    __typename\n    id\n    ... on Series {\n      episodes {\n        __typename\n        items {\n          __typename\n          ...ContentFragmentBase\n          ...ContentFragmentDetails\n        }\n      }\n    }\n    ... on Season {\n      episodes {\n        __typename\n        items {\n          __typename\n          ...ContentFragmentBase\n          ...ContentFragmentDetails\n        }\n      }\n    }\n  }\n}\nfragment ContentFragmentBase on Content {\n  __typename\n  id\n  title\n  description\n  year\n  originalDuration\n  classifiers {\n    __typename\n    id\n    name\n    ... on ContentRating {\n      level\n    }\n  }\n  artworks {\n    __typename\n    source(size: {width: 1234, height: 1234})\n  }\n  externalIds {\n    __typename\n    scheme\n    identifier\n  }\n  offers(linear: false) {\n    __typename\n    items {\n      __typename\n      ... on TransactionalVOD {\n        streamId\n        name\n        price {\n          __typename\n          amount\n          currency\n        }\n      }\n      ... on SubscriptionVOD {\n        streamId\n        name\n      }\n      ... on CatchUpTV {\n        streamId\n        name\n      }\n      ... on ExternalOffer {\n        name\n        location\n        __typename\n      }\n    }\n  }\n}\nfragment ContentFragmentDetails on Content {\n  __typename\n  previews {\n    __typename\n    description\n    streamId\n  }\n  countries {\n    __typename\n    name\n    isoCode\n  }\n  cast {\n    __typename\n    person {\n      __typename\n      id\n      name\n    }\n    roleType\n    role\n  }\n  crew {\n    __typename\n    person {\n      __typename\n      id\n      name\n    }\n    department\n    task\n  }\n  offers(linear: false) {\n    __typename\n    items {\n      __typename\n      audioLanguages\n      subtitlesLanguages\n    }\n  }\n  ... on Series {\n    seasons {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n    episodes {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n  }\n  ... on Season {\n    seasonNumber\n    episodes {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n    series {\n      __typename\n      id\n    }\n  }\n  ... on Episode {\n    episodeNumber\n    series {\n      __typename\n      id\n    }\n    season {\n      __typename\n      id\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODContentEpisodes";
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

    public VODContentEpisodesQuery(String str) {
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

        public VODContentEpisodesQuery build() {
            Utils.checkNotNull(this.contentId, "contentId == null");
            return new VODContentEpisodesQuery(this.contentId);
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
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Variables.1
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Data.1
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
                return new Data((LookupContent) responseReader.readObject(Data.$responseFields[0], new ResponseReader.ObjectReader<LookupContent>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Data.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public LookupContent read(ResponseReader responseReader2) {
                        return Mapper.this.lookupContentFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public interface LookupContent {
        String __typename();

        String id();

        ResponseFieldMarshaller marshaller();

        public static final class Mapper implements ResponseFieldMapper<LookupContent> {
            static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Series"}))), ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Season"})))};
            final AsSeries.Mapper asSeriesFieldMapper = new AsSeries.Mapper();
            final AsSeason.Mapper asSeasonFieldMapper = new AsSeason.Mapper();
            final AsContent.Mapper asContentFieldMapper = new AsContent.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public LookupContent map(ResponseReader responseReader) {
                AsSeries asSeries = (AsSeries) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsSeries>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsSeries read(ResponseReader responseReader2) {
                        return Mapper.this.asSeriesFieldMapper.map(responseReader2);
                    }
                });
                if (asSeries != null) {
                    return asSeries;
                }
                AsSeason asSeason = (AsSeason) responseReader.readFragment($responseFields[1], new ResponseReader.ObjectReader<AsSeason>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsSeason read(ResponseReader responseReader2) {
                        return Mapper.this.asSeasonFieldMapper.map(responseReader2);
                    }
                });
                return asSeason != null ? asSeason : this.asContentFieldMapper.map(responseReader);
            }
        }
    }

    public static class AsSeries implements LookupContent {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forObject("episodes", "episodes", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Episodes episodes;
        final String id;

        public AsSeries(String str, String str2, Episodes episodes) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.episodes = (Episodes) Utils.checkNotNull(episodes, "episodes == null");
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public String id() {
            return this.id;
        }

        public Episodes episodes() {
            return this.episodes;
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.AsSeries.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsSeries.$responseFields[0], AsSeries.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsSeries.$responseFields[1], AsSeries.this.id);
                    responseWriter.writeObject(AsSeries.$responseFields[2], AsSeries.this.episodes.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsSeries{__typename=" + this.__typename + ", id=" + this.id + ", episodes=" + this.episodes + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsSeries)) {
                return false;
            }
            AsSeries asSeries = (AsSeries) obj;
            return this.__typename.equals(asSeries.__typename) && this.id.equals(asSeries.id) && this.episodes.equals(asSeries.episodes);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.episodes.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsSeries> {
            final Episodes.Mapper episodesFieldMapper = new Episodes.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsSeries map(ResponseReader responseReader) {
                return new AsSeries(responseReader.readString(AsSeries.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsSeries.$responseFields[1]), (Episodes) responseReader.readObject(AsSeries.$responseFields[2], new ResponseReader.ObjectReader<Episodes>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.AsSeries.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Episodes read(ResponseReader responseReader2) {
                        return Mapper.this.episodesFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Episodes {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("items", "items", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Item> items;

        public Episodes(String str, List<Item> list) {
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Episodes.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Episodes.$responseFields[0], Episodes.this.__typename);
                    responseWriter.writeList(Episodes.$responseFields[1], Episodes.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Episodes.1.1
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
                this.$toString = "Episodes{__typename=" + this.__typename + ", items=" + this.items + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Episodes)) {
                return false;
            }
            Episodes episodes = (Episodes) obj;
            return this.__typename.equals(episodes.__typename) && this.items.equals(episodes.items);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.items.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Episodes> {
            final Item.Mapper itemFieldMapper = new Item.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Episodes map(ResponseReader responseReader) {
                return new Episodes(responseReader.readString(Episodes.$responseFields[0]), responseReader.readList(Episodes.$responseFields[1], new ResponseReader.ListReader<Item>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Episodes.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item read(ResponseReader.ListItemReader listItemReader) {
                        return (Item) listItemReader.readObject(new ResponseReader.ObjectReader<Item>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Episodes.Mapper.1.1
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Item.1
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
            final ContentFragmentDetails contentFragmentDetails;

            public Fragments(ContentFragmentBase contentFragmentBase, ContentFragmentDetails contentFragmentDetails) {
                this.contentFragmentBase = contentFragmentBase;
                this.contentFragmentDetails = contentFragmentDetails;
            }

            public ContentFragmentBase contentFragmentBase() {
                return this.contentFragmentBase;
            }

            public ContentFragmentDetails contentFragmentDetails() {
                return this.contentFragmentDetails;
            }

            public ResponseFieldMarshaller marshaller() {
                return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Item.Fragments.1
                    @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                    public void marshal(ResponseWriter responseWriter) {
                        ContentFragmentBase contentFragmentBase = Fragments.this.contentFragmentBase;
                        if (contentFragmentBase != null) {
                            responseWriter.writeFragment(contentFragmentBase.marshaller());
                        }
                        ContentFragmentDetails contentFragmentDetails = Fragments.this.contentFragmentDetails;
                        if (contentFragmentDetails != null) {
                            responseWriter.writeFragment(contentFragmentDetails.marshaller());
                        }
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
                ContentFragmentBase contentFragmentBase = this.contentFragmentBase;
                if (contentFragmentBase != null ? contentFragmentBase.equals(fragments.contentFragmentBase) : fragments.contentFragmentBase == null) {
                    ContentFragmentDetails contentFragmentDetails = this.contentFragmentDetails;
                    ContentFragmentDetails contentFragmentDetails2 = fragments.contentFragmentDetails;
                    if (contentFragmentDetails == null) {
                        if (contentFragmentDetails2 == null) {
                            return true;
                        }
                    } else if (contentFragmentDetails.equals(contentFragmentDetails2)) {
                        return true;
                    }
                }
                return false;
            }

            public int hashCode() {
                if (!this.$hashCodeMemoized) {
                    ContentFragmentBase contentFragmentBase = this.contentFragmentBase;
                    int iHashCode = ((contentFragmentBase == null ? 0 : contentFragmentBase.hashCode()) ^ 1000003) * 1000003;
                    ContentFragmentDetails contentFragmentDetails = this.contentFragmentDetails;
                    this.$hashCode = iHashCode ^ (contentFragmentDetails != null ? contentFragmentDetails.hashCode() : 0);
                    this.$hashCodeMemoized = true;
                }
                return this.$hashCode;
            }

            public static final class Mapper implements ResponseFieldMapper<Fragments> {
                static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Season", "Series", "Episode", "SportEvent", "Movie", "GenericContent"}))), ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Season", "Series", "Episode", "SportEvent", "Movie", "GenericContent"})))};
                final ContentFragmentBase.Mapper contentFragmentBaseFieldMapper = new ContentFragmentBase.Mapper();
                final ContentFragmentDetails.Mapper contentFragmentDetailsFieldMapper = new ContentFragmentDetails.Mapper();

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
                public Fragments map(ResponseReader responseReader) {
                    return new Fragments((ContentFragmentBase) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<ContentFragmentBase>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Item.Fragments.Mapper.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ContentFragmentBase read(ResponseReader responseReader2) {
                            return Mapper.this.contentFragmentBaseFieldMapper.map(responseReader2);
                        }
                    }), (ContentFragmentDetails) responseReader.readFragment($responseFields[1], new ResponseReader.ObjectReader<ContentFragmentDetails>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Item.Fragments.Mapper.2
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ContentFragmentDetails read(ResponseReader responseReader2) {
                            return Mapper.this.contentFragmentDetailsFieldMapper.map(responseReader2);
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

    public static class AsSeason implements LookupContent {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forObject("episodes", "episodes", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Episodes1 episodes;
        final String id;

        public AsSeason(String str, String str2, Episodes1 episodes1) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.episodes = (Episodes1) Utils.checkNotNull(episodes1, "episodes == null");
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public String id() {
            return this.id;
        }

        public Episodes1 episodes() {
            return this.episodes;
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.AsSeason.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsSeason.$responseFields[0], AsSeason.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsSeason.$responseFields[1], AsSeason.this.id);
                    responseWriter.writeObject(AsSeason.$responseFields[2], AsSeason.this.episodes.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsSeason{__typename=" + this.__typename + ", id=" + this.id + ", episodes=" + this.episodes + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsSeason)) {
                return false;
            }
            AsSeason asSeason = (AsSeason) obj;
            return this.__typename.equals(asSeason.__typename) && this.id.equals(asSeason.id) && this.episodes.equals(asSeason.episodes);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.episodes.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsSeason> {
            final Episodes1.Mapper episodes1FieldMapper = new Episodes1.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsSeason map(ResponseReader responseReader) {
                return new AsSeason(responseReader.readString(AsSeason.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsSeason.$responseFields[1]), (Episodes1) responseReader.readObject(AsSeason.$responseFields[2], new ResponseReader.ObjectReader<Episodes1>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.AsSeason.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Episodes1 read(ResponseReader responseReader2) {
                        return Mapper.this.episodes1FieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Episodes1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("items", "items", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Item1> items;

        public Episodes1(String str, List<Item1> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.items = (List) Utils.checkNotNull(list, "items == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public List<Item1> items() {
            return this.items;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Episodes1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Episodes1.$responseFields[0], Episodes1.this.__typename);
                    responseWriter.writeList(Episodes1.$responseFields[1], Episodes1.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Episodes1.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Item1) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Episodes1{__typename=" + this.__typename + ", items=" + this.items + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Episodes1)) {
                return false;
            }
            Episodes1 episodes1 = (Episodes1) obj;
            return this.__typename.equals(episodes1.__typename) && this.items.equals(episodes1.items);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.items.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Episodes1> {
            final Item1.Mapper item1FieldMapper = new Item1.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Episodes1 map(ResponseReader responseReader) {
                return new Episodes1(responseReader.readString(Episodes1.$responseFields[0]), responseReader.readList(Episodes1.$responseFields[1], new ResponseReader.ListReader<Item1>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Episodes1.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item1 read(ResponseReader.ListItemReader listItemReader) {
                        return (Item1) listItemReader.readObject(new ResponseReader.ObjectReader<Item1>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Episodes1.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Item1 read(ResponseReader responseReader2) {
                                return Mapper.this.item1FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class Item1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        private final Fragments fragments;

        public Item1(String str, Fragments fragments) {
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Item1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Item1.$responseFields[0], Item1.this.__typename);
                    Item1.this.fragments.marshaller().marshal(responseWriter);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Item1{__typename=" + this.__typename + ", fragments=" + this.fragments + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Item1)) {
                return false;
            }
            Item1 item1 = (Item1) obj;
            return this.__typename.equals(item1.__typename) && this.fragments.equals(item1.fragments);
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
                this.contentFragmentBase = contentFragmentBase;
                this.contentFragmentDetails = contentFragmentDetails;
            }

            public ContentFragmentBase contentFragmentBase() {
                return this.contentFragmentBase;
            }

            public ContentFragmentDetails contentFragmentDetails() {
                return this.contentFragmentDetails;
            }

            public ResponseFieldMarshaller marshaller() {
                return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Item1.Fragments.1
                    @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                    public void marshal(ResponseWriter responseWriter) {
                        ContentFragmentBase contentFragmentBase = Fragments.this.contentFragmentBase;
                        if (contentFragmentBase != null) {
                            responseWriter.writeFragment(contentFragmentBase.marshaller());
                        }
                        ContentFragmentDetails contentFragmentDetails = Fragments.this.contentFragmentDetails;
                        if (contentFragmentDetails != null) {
                            responseWriter.writeFragment(contentFragmentDetails.marshaller());
                        }
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
                ContentFragmentBase contentFragmentBase = this.contentFragmentBase;
                if (contentFragmentBase != null ? contentFragmentBase.equals(fragments.contentFragmentBase) : fragments.contentFragmentBase == null) {
                    ContentFragmentDetails contentFragmentDetails = this.contentFragmentDetails;
                    ContentFragmentDetails contentFragmentDetails2 = fragments.contentFragmentDetails;
                    if (contentFragmentDetails == null) {
                        if (contentFragmentDetails2 == null) {
                            return true;
                        }
                    } else if (contentFragmentDetails.equals(contentFragmentDetails2)) {
                        return true;
                    }
                }
                return false;
            }

            public int hashCode() {
                if (!this.$hashCodeMemoized) {
                    ContentFragmentBase contentFragmentBase = this.contentFragmentBase;
                    int iHashCode = ((contentFragmentBase == null ? 0 : contentFragmentBase.hashCode()) ^ 1000003) * 1000003;
                    ContentFragmentDetails contentFragmentDetails = this.contentFragmentDetails;
                    this.$hashCode = iHashCode ^ (contentFragmentDetails != null ? contentFragmentDetails.hashCode() : 0);
                    this.$hashCodeMemoized = true;
                }
                return this.$hashCode;
            }

            public static final class Mapper implements ResponseFieldMapper<Fragments> {
                static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Season", "Series", "Episode", "SportEvent", "Movie", "GenericContent"}))), ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Season", "Series", "Episode", "SportEvent", "Movie", "GenericContent"})))};
                final ContentFragmentBase.Mapper contentFragmentBaseFieldMapper = new ContentFragmentBase.Mapper();
                final ContentFragmentDetails.Mapper contentFragmentDetailsFieldMapper = new ContentFragmentDetails.Mapper();

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
                public Fragments map(ResponseReader responseReader) {
                    return new Fragments((ContentFragmentBase) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<ContentFragmentBase>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Item1.Fragments.Mapper.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ContentFragmentBase read(ResponseReader responseReader2) {
                            return Mapper.this.contentFragmentBaseFieldMapper.map(responseReader2);
                        }
                    }), (ContentFragmentDetails) responseReader.readFragment($responseFields[1], new ResponseReader.ObjectReader<ContentFragmentDetails>() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.Item1.Fragments.Mapper.2
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ContentFragmentDetails read(ResponseReader responseReader2) {
                            return Mapper.this.contentFragmentDetailsFieldMapper.map(responseReader2);
                        }
                    }));
                }
            }
        }

        public static final class Mapper implements ResponseFieldMapper<Item1> {
            final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Item1 map(ResponseReader responseReader) {
                return new Item1(responseReader.readString(Item1.$responseFields[0]), this.fragmentsFieldMapper.map(responseReader));
            }
        }
    }

    public static class AsContent implements LookupContent {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;

        public AsContent(String str, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.VODContentEpisodesQuery.LookupContent
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentEpisodesQuery.AsContent.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsContent.$responseFields[0], AsContent.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsContent.$responseFields[1], AsContent.this.id);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsContent{__typename=" + this.__typename + ", id=" + this.id + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsContent)) {
                return false;
            }
            AsContent asContent = (AsContent) obj;
            return this.__typename.equals(asContent.__typename) && this.id.equals(asContent.id);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsContent> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsContent map(ResponseReader responseReader) {
                return new AsContent(responseReader.readString(AsContent.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsContent.$responseFields[1]));
            }
        }
    }
}
