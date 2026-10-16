package com.dotscreen.bokit.fragment;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseReader;
import com.apollographql.apollo.api.internal.ResponseWriter;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import com.dotscreen.bokit.type.CurrencyCode;
import com.dotscreen.bokit.type.CustomType;
import com.dotscreen.bokit.type.IdentifierScheme;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ContentFragmentBase implements GraphqlFragment {
    static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("title", "title", null, false, Collections.emptyList()), ResponseField.forString(TvContractCompat.Channels.COLUMN_DESCRIPTION, TvContractCompat.Channels.COLUMN_DESCRIPTION, null, true, Collections.emptyList()), ResponseField.forInt("year", "year", null, true, Collections.emptyList()), ResponseField.forDouble("originalDuration", "originalDuration", null, true, Collections.emptyList()), ResponseField.forList("classifiers", "classifiers", null, false, Collections.emptyList()), ResponseField.forList("artworks", "artworks", null, false, Collections.emptyList()), ResponseField.forList("externalIds", "externalIds", null, false, Collections.emptyList()), ResponseField.forObject("offers", "offers", new UnmodifiableMapBuilder(1).put("linear", false).build(), false, Collections.emptyList())};
    public static final String FRAGMENT_DEFINITION = "fragment ContentFragmentBase on Content {\n  __typename\n  id\n  title\n  description\n  year\n  originalDuration\n  classifiers {\n    __typename\n    id\n    name\n    ... on ContentRating {\n      level\n    }\n  }\n  artworks {\n    __typename\n    source(size: {width: 1234, height: 1234})\n  }\n  externalIds {\n    __typename\n    scheme\n    identifier\n  }\n  offers(linear: false) {\n    __typename\n    items {\n      __typename\n      ... on TransactionalVOD {\n        streamId\n        name\n        price {\n          __typename\n          amount\n          currency\n        }\n      }\n      ... on SubscriptionVOD {\n        streamId\n        name\n      }\n      ... on CatchUpTV {\n        streamId\n        name\n      }\n      ... on ExternalOffer {\n        name\n        location\n        __typename\n      }\n    }\n  }\n}";
    private volatile transient int $hashCode;
    private volatile transient boolean $hashCodeMemoized;
    private volatile transient String $toString;
    final String __typename;
    final List<Artwork> artworks;
    final List<Classifier> classifiers;
    final String description;
    final List<ExternalId> externalIds;
    final String id;
    final Offers offers;
    final Double originalDuration;
    final String title;
    final Integer year;

    public ContentFragmentBase(String str, String str2, String str3, String str4, Integer num, Double d, List<Classifier> list, List<Artwork> list2, List<ExternalId> list3, Offers offers) {
        this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
        this.id = (String) Utils.checkNotNull(str2, "id == null");
        this.title = (String) Utils.checkNotNull(str3, "title == null");
        this.description = str4;
        this.year = num;
        this.originalDuration = d;
        this.classifiers = (List) Utils.checkNotNull(list, "classifiers == null");
        this.artworks = (List) Utils.checkNotNull(list2, "artworks == null");
        this.externalIds = (List) Utils.checkNotNull(list3, "externalIds == null");
        this.offers = (Offers) Utils.checkNotNull(offers, "offers == null");
    }

    public String __typename() {
        return this.__typename;
    }

    public String id() {
        return this.id;
    }

    public String title() {
        return this.title;
    }

    public String description() {
        return this.description;
    }

    public Integer year() {
        return this.year;
    }

    public Double originalDuration() {
        return this.originalDuration;
    }

    public List<Classifier> classifiers() {
        return this.classifiers;
    }

    public List<Artwork> artworks() {
        return this.artworks;
    }

    public List<ExternalId> externalIds() {
        return this.externalIds;
    }

    public Offers offers() {
        return this.offers;
    }

    @Override // com.apollographql.apollo.api.GraphqlFragment
    public ResponseFieldMarshaller marshaller() {
        return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.1
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
            public void marshal(ResponseWriter responseWriter) {
                responseWriter.writeString(ContentFragmentBase.$responseFields[0], ContentFragmentBase.this.__typename);
                responseWriter.writeCustom((ResponseField.CustomTypeField) ContentFragmentBase.$responseFields[1], ContentFragmentBase.this.id);
                responseWriter.writeString(ContentFragmentBase.$responseFields[2], ContentFragmentBase.this.title);
                responseWriter.writeString(ContentFragmentBase.$responseFields[3], ContentFragmentBase.this.description);
                responseWriter.writeInt(ContentFragmentBase.$responseFields[4], ContentFragmentBase.this.year);
                responseWriter.writeDouble(ContentFragmentBase.$responseFields[5], ContentFragmentBase.this.originalDuration);
                responseWriter.writeList(ContentFragmentBase.$responseFields[6], ContentFragmentBase.this.classifiers, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.1.1
                    @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                    public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            listItemWriter.writeObject(((Classifier) it.next()).marshaller());
                        }
                    }
                });
                responseWriter.writeList(ContentFragmentBase.$responseFields[7], ContentFragmentBase.this.artworks, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.1.2
                    @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                    public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            listItemWriter.writeObject(((Artwork) it.next()).marshaller());
                        }
                    }
                });
                responseWriter.writeList(ContentFragmentBase.$responseFields[8], ContentFragmentBase.this.externalIds, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.1.3
                    @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                    public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            listItemWriter.writeObject(((ExternalId) it.next()).marshaller());
                        }
                    }
                });
                responseWriter.writeObject(ContentFragmentBase.$responseFields[9], ContentFragmentBase.this.offers.marshaller());
            }
        };
    }

    public String toString() {
        if (this.$toString == null) {
            this.$toString = "ContentFragmentBase{__typename=" + this.__typename + ", id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", year=" + this.year + ", originalDuration=" + this.originalDuration + ", classifiers=" + this.classifiers + ", artworks=" + this.artworks + ", externalIds=" + this.externalIds + ", offers=" + this.offers + "}";
        }
        return this.$toString;
    }

    public boolean equals(Object obj) {
        String str;
        Integer num;
        Double d;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContentFragmentBase)) {
            return false;
        }
        ContentFragmentBase contentFragmentBase = (ContentFragmentBase) obj;
        return this.__typename.equals(contentFragmentBase.__typename) && this.id.equals(contentFragmentBase.id) && this.title.equals(contentFragmentBase.title) && ((str = this.description) != null ? str.equals(contentFragmentBase.description) : contentFragmentBase.description == null) && ((num = this.year) != null ? num.equals(contentFragmentBase.year) : contentFragmentBase.year == null) && ((d = this.originalDuration) != null ? d.equals(contentFragmentBase.originalDuration) : contentFragmentBase.originalDuration == null) && this.classifiers.equals(contentFragmentBase.classifiers) && this.artworks.equals(contentFragmentBase.artworks) && this.externalIds.equals(contentFragmentBase.externalIds) && this.offers.equals(contentFragmentBase.offers);
    }

    public int hashCode() {
        if (!this.$hashCodeMemoized) {
            int iHashCode = (((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.title.hashCode()) * 1000003;
            String str = this.description;
            int iHashCode2 = (iHashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
            Integer num = this.year;
            int iHashCode3 = (iHashCode2 ^ (num == null ? 0 : num.hashCode())) * 1000003;
            Double d = this.originalDuration;
            this.$hashCode = ((((((((iHashCode3 ^ (d != null ? d.hashCode() : 0)) * 1000003) ^ this.classifiers.hashCode()) * 1000003) ^ this.artworks.hashCode()) * 1000003) ^ this.externalIds.hashCode()) * 1000003) ^ this.offers.hashCode();
            this.$hashCodeMemoized = true;
        }
        return this.$hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<ContentFragmentBase> {
        final Classifier.Mapper classifierFieldMapper = new Classifier.Mapper();
        final Artwork.Mapper artworkFieldMapper = new Artwork.Mapper();
        final ExternalId.Mapper externalIdFieldMapper = new ExternalId.Mapper();
        final Offers.Mapper offersFieldMapper = new Offers.Mapper();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
        public ContentFragmentBase map(ResponseReader responseReader) {
            return new ContentFragmentBase(responseReader.readString(ContentFragmentBase.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) ContentFragmentBase.$responseFields[1]), responseReader.readString(ContentFragmentBase.$responseFields[2]), responseReader.readString(ContentFragmentBase.$responseFields[3]), responseReader.readInt(ContentFragmentBase.$responseFields[4]), responseReader.readDouble(ContentFragmentBase.$responseFields[5]), responseReader.readList(ContentFragmentBase.$responseFields[6], new ResponseReader.ListReader<Classifier>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Mapper.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                public Classifier read(ResponseReader.ListItemReader listItemReader) {
                    return (Classifier) listItemReader.readObject(new ResponseReader.ObjectReader<Classifier>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Mapper.1.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public Classifier read(ResponseReader responseReader2) {
                            return Mapper.this.classifierFieldMapper.map(responseReader2);
                        }
                    });
                }
            }), responseReader.readList(ContentFragmentBase.$responseFields[7], new ResponseReader.ListReader<Artwork>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Mapper.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                public Artwork read(ResponseReader.ListItemReader listItemReader) {
                    return (Artwork) listItemReader.readObject(new ResponseReader.ObjectReader<Artwork>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Mapper.2.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public Artwork read(ResponseReader responseReader2) {
                            return Mapper.this.artworkFieldMapper.map(responseReader2);
                        }
                    });
                }
            }), responseReader.readList(ContentFragmentBase.$responseFields[8], new ResponseReader.ListReader<ExternalId>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Mapper.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                public ExternalId read(ResponseReader.ListItemReader listItemReader) {
                    return (ExternalId) listItemReader.readObject(new ResponseReader.ObjectReader<ExternalId>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Mapper.3.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public ExternalId read(ResponseReader responseReader2) {
                            return Mapper.this.externalIdFieldMapper.map(responseReader2);
                        }
                    });
                }
            }), (Offers) responseReader.readObject(ContentFragmentBase.$responseFields[9], new ResponseReader.ObjectReader<Offers>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Mapper.4
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                public Offers read(ResponseReader responseReader2) {
                    return Mapper.this.offersFieldMapper.map(responseReader2);
                }
            }));
        }
    }

    public interface Classifier {
        String __typename();

        String id();

        ResponseFieldMarshaller marshaller();

        String name();

        public static final class Mapper implements ResponseFieldMapper<Classifier> {
            static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"ContentRating"})))};
            final AsContentRating.Mapper asContentRatingFieldMapper = new AsContentRating.Mapper();
            final AsClassifier.Mapper asClassifierFieldMapper = new AsClassifier.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Classifier map(ResponseReader responseReader) {
                AsContentRating asContentRating = (AsContentRating) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsContentRating>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsContentRating read(ResponseReader responseReader2) {
                        return Mapper.this.asContentRatingFieldMapper.map(responseReader2);
                    }
                });
                return asContentRating != null ? asContentRating : this.asClassifierFieldMapper.map(responseReader);
            }
        }
    }

    public static class AsContentRating implements Classifier {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList()), ResponseField.forInt("level", "level", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final int level;
        final String name;

        public AsContentRating(String str, String str2, String str3, int i) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
            this.level = i;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier
        public String name() {
            return this.name;
        }

        public int level() {
            return this.level;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.AsContentRating.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsContentRating.$responseFields[0], AsContentRating.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsContentRating.$responseFields[1], AsContentRating.this.id);
                    responseWriter.writeString(AsContentRating.$responseFields[2], AsContentRating.this.name);
                    responseWriter.writeInt(AsContentRating.$responseFields[3], Integer.valueOf(AsContentRating.this.level));
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsContentRating{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + ", level=" + this.level + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsContentRating)) {
                return false;
            }
            AsContentRating asContentRating = (AsContentRating) obj;
            return this.__typename.equals(asContentRating.__typename) && this.id.equals(asContentRating.id) && this.name.equals(asContentRating.name) && this.level == asContentRating.level;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.level;
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsContentRating> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsContentRating map(ResponseReader responseReader) {
                return new AsContentRating(responseReader.readString(AsContentRating.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsContentRating.$responseFields[1]), responseReader.readString(AsContentRating.$responseFields[2]), responseReader.readInt(AsContentRating.$responseFields[3]).intValue());
            }
        }
    }

    public static class AsClassifier implements Classifier {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public AsClassifier(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Classifier
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.AsClassifier.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsClassifier.$responseFields[0], AsClassifier.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsClassifier.$responseFields[1], AsClassifier.this.id);
                    responseWriter.writeString(AsClassifier.$responseFields[2], AsClassifier.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsClassifier{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsClassifier)) {
                return false;
            }
            AsClassifier asClassifier = (AsClassifier) obj;
            return this.__typename.equals(asClassifier.__typename) && this.id.equals(asClassifier.id) && this.name.equals(asClassifier.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsClassifier> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsClassifier map(ResponseReader responseReader) {
                return new AsClassifier(responseReader.readString(AsClassifier.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsClassifier.$responseFields[1]), responseReader.readString(AsClassifier.$responseFields[2]));
            }
        }
    }

    public static class Artwork {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("source", "source", new UnmodifiableMapBuilder(1).put("size", new UnmodifiableMapBuilder(2).put(ToolboxAnalyticsModuleKt.M_WIDTH, "1234").put(ToolboxAnalyticsModuleKt.M_HEIGHT, "1234").build()).build(), false, CustomType.URI, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String source;

        public Artwork(String str, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.source = (String) Utils.checkNotNull(str2, "source == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String source() {
            return this.source;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Artwork.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Artwork.$responseFields[0], Artwork.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Artwork.$responseFields[1], Artwork.this.source);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Artwork{__typename=" + this.__typename + ", source=" + this.source + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Artwork)) {
                return false;
            }
            Artwork artwork = (Artwork) obj;
            return this.__typename.equals(artwork.__typename) && this.source.equals(artwork.source);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.source.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Artwork> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Artwork map(ResponseReader responseReader) {
                return new Artwork(responseReader.readString(Artwork.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Artwork.$responseFields[1]));
            }
        }
    }

    public static class ExternalId {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("scheme", "scheme", null, false, Collections.emptyList()), ResponseField.forCustomType("identifier", "identifier", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String identifier;
        final IdentifierScheme scheme;

        public ExternalId(String str, IdentifierScheme identifierScheme, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.scheme = (IdentifierScheme) Utils.checkNotNull(identifierScheme, "scheme == null");
            this.identifier = (String) Utils.checkNotNull(str2, "identifier == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public IdentifierScheme scheme() {
            return this.scheme;
        }

        public String identifier() {
            return this.identifier;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.ExternalId.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(ExternalId.$responseFields[0], ExternalId.this.__typename);
                    responseWriter.writeString(ExternalId.$responseFields[1], ExternalId.this.scheme.rawValue());
                    responseWriter.writeCustom((ResponseField.CustomTypeField) ExternalId.$responseFields[2], ExternalId.this.identifier);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "ExternalId{__typename=" + this.__typename + ", scheme=" + this.scheme + ", identifier=" + this.identifier + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ExternalId)) {
                return false;
            }
            ExternalId externalId = (ExternalId) obj;
            return this.__typename.equals(externalId.__typename) && this.scheme.equals(externalId.scheme) && this.identifier.equals(externalId.identifier);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.scheme.hashCode()) * 1000003) ^ this.identifier.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<ExternalId> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public ExternalId map(ResponseReader responseReader) {
                String string = responseReader.readString(ExternalId.$responseFields[0]);
                String string2 = responseReader.readString(ExternalId.$responseFields[1]);
                return new ExternalId(string, string2 != null ? IdentifierScheme.safeValueOf(string2) : null, (String) responseReader.readCustomType((ResponseField.CustomTypeField) ExternalId.$responseFields[2]));
            }
        }
    }

    public static class Offers {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("items", "items", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Item> items;

        public Offers(String str, List<Item> list) {
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Offers.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Offers.$responseFields[0], Offers.this.__typename);
                    responseWriter.writeList(Offers.$responseFields[1], Offers.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Offers.1.1
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
                this.$toString = "Offers{__typename=" + this.__typename + ", items=" + this.items + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Offers)) {
                return false;
            }
            Offers offers = (Offers) obj;
            return this.__typename.equals(offers.__typename) && this.items.equals(offers.items);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.items.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Offers> {
            final Item.Mapper itemFieldMapper = new Item.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Offers map(ResponseReader responseReader) {
                return new Offers(responseReader.readString(Offers.$responseFields[0]), responseReader.readList(Offers.$responseFields[1], new ResponseReader.ListReader<Item>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Offers.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item read(ResponseReader.ListItemReader listItemReader) {
                        return (Item) listItemReader.readObject(new ResponseReader.ObjectReader<Item>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Offers.Mapper.1.1
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

    public interface Item {
        String __typename();

        ResponseFieldMarshaller marshaller();

        public static final class Mapper implements ResponseFieldMapper<Item> {
            static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"TransactionalVOD"}))), ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"SubscriptionVOD"}))), ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"CatchUpTV"}))), ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"ExternalOffer"})))};
            final AsTransactionalVOD.Mapper asTransactionalVODFieldMapper = new AsTransactionalVOD.Mapper();
            final AsSubscriptionVOD.Mapper asSubscriptionVODFieldMapper = new AsSubscriptionVOD.Mapper();
            final AsCatchUpTV.Mapper asCatchUpTVFieldMapper = new AsCatchUpTV.Mapper();
            final AsExternalOffer.Mapper asExternalOfferFieldMapper = new AsExternalOffer.Mapper();
            final AsOffer.Mapper asOfferFieldMapper = new AsOffer.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Item map(ResponseReader responseReader) {
                AsTransactionalVOD asTransactionalVOD = (AsTransactionalVOD) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsTransactionalVOD>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Item.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsTransactionalVOD read(ResponseReader responseReader2) {
                        return Mapper.this.asTransactionalVODFieldMapper.map(responseReader2);
                    }
                });
                if (asTransactionalVOD != null) {
                    return asTransactionalVOD;
                }
                AsSubscriptionVOD asSubscriptionVOD = (AsSubscriptionVOD) responseReader.readFragment($responseFields[1], new ResponseReader.ObjectReader<AsSubscriptionVOD>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Item.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsSubscriptionVOD read(ResponseReader responseReader2) {
                        return Mapper.this.asSubscriptionVODFieldMapper.map(responseReader2);
                    }
                });
                if (asSubscriptionVOD != null) {
                    return asSubscriptionVOD;
                }
                AsCatchUpTV asCatchUpTV = (AsCatchUpTV) responseReader.readFragment($responseFields[2], new ResponseReader.ObjectReader<AsCatchUpTV>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Item.Mapper.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsCatchUpTV read(ResponseReader responseReader2) {
                        return Mapper.this.asCatchUpTVFieldMapper.map(responseReader2);
                    }
                });
                if (asCatchUpTV != null) {
                    return asCatchUpTV;
                }
                AsExternalOffer asExternalOffer = (AsExternalOffer) responseReader.readFragment($responseFields[3], new ResponseReader.ObjectReader<AsExternalOffer>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Item.Mapper.4
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsExternalOffer read(ResponseReader responseReader2) {
                        return Mapper.this.asExternalOfferFieldMapper.map(responseReader2);
                    }
                });
                return asExternalOffer != null ? asExternalOffer : this.asOfferFieldMapper.map(responseReader);
            }
        }
    }

    public static class AsTransactionalVOD implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("streamId", "streamId", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList()), ResponseField.forObject("price", "price", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String name;
        final Price price;
        final String streamId;

        public AsTransactionalVOD(String str, String str2, String str3, Price price) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.streamId = (String) Utils.checkNotNull(str2, "streamId == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
            this.price = (Price) Utils.checkNotNull(price, "price == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public String __typename() {
            return this.__typename;
        }

        public String streamId() {
            return this.streamId;
        }

        public String name() {
            return this.name;
        }

        public Price price() {
            return this.price;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.AsTransactionalVOD.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsTransactionalVOD.$responseFields[0], AsTransactionalVOD.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsTransactionalVOD.$responseFields[1], AsTransactionalVOD.this.streamId);
                    responseWriter.writeString(AsTransactionalVOD.$responseFields[2], AsTransactionalVOD.this.name);
                    responseWriter.writeObject(AsTransactionalVOD.$responseFields[3], AsTransactionalVOD.this.price.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsTransactionalVOD{__typename=" + this.__typename + ", streamId=" + this.streamId + ", name=" + this.name + ", price=" + this.price + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsTransactionalVOD)) {
                return false;
            }
            AsTransactionalVOD asTransactionalVOD = (AsTransactionalVOD) obj;
            return this.__typename.equals(asTransactionalVOD.__typename) && this.streamId.equals(asTransactionalVOD.streamId) && this.name.equals(asTransactionalVOD.name) && this.price.equals(asTransactionalVOD.price);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.streamId.hashCode()) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.price.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsTransactionalVOD> {
            final Price.Mapper priceFieldMapper = new Price.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsTransactionalVOD map(ResponseReader responseReader) {
                return new AsTransactionalVOD(responseReader.readString(AsTransactionalVOD.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsTransactionalVOD.$responseFields[1]), responseReader.readString(AsTransactionalVOD.$responseFields[2]), (Price) responseReader.readObject(AsTransactionalVOD.$responseFields[3], new ResponseReader.ObjectReader<Price>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.AsTransactionalVOD.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Price read(ResponseReader responseReader2) {
                        return Mapper.this.priceFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Price {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forInt("amount", "amount", null, false, Collections.emptyList()), ResponseField.forString("currency", "currency", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final int amount;
        final CurrencyCode currency;

        public Price(String str, int i, CurrencyCode currencyCode) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.amount = i;
            this.currency = (CurrencyCode) Utils.checkNotNull(currencyCode, "currency == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public int amount() {
            return this.amount;
        }

        public CurrencyCode currency() {
            return this.currency;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.Price.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Price.$responseFields[0], Price.this.__typename);
                    responseWriter.writeInt(Price.$responseFields[1], Integer.valueOf(Price.this.amount));
                    responseWriter.writeString(Price.$responseFields[2], Price.this.currency.rawValue());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Price{__typename=" + this.__typename + ", amount=" + this.amount + ", currency=" + this.currency + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Price)) {
                return false;
            }
            Price price = (Price) obj;
            return this.__typename.equals(price.__typename) && this.amount == price.amount && this.currency.equals(price.currency);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.amount) * 1000003) ^ this.currency.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Price> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Price map(ResponseReader responseReader) {
                String string = responseReader.readString(Price.$responseFields[0]);
                int iIntValue = responseReader.readInt(Price.$responseFields[1]).intValue();
                String string2 = responseReader.readString(Price.$responseFields[2]);
                return new Price(string, iIntValue, string2 != null ? CurrencyCode.safeValueOf(string2) : null);
            }
        }
    }

    public static class AsSubscriptionVOD implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("streamId", "streamId", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String name;
        final String streamId;

        public AsSubscriptionVOD(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.streamId = (String) Utils.checkNotNull(str2, "streamId == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public String __typename() {
            return this.__typename;
        }

        public String streamId() {
            return this.streamId;
        }

        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.AsSubscriptionVOD.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsSubscriptionVOD.$responseFields[0], AsSubscriptionVOD.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsSubscriptionVOD.$responseFields[1], AsSubscriptionVOD.this.streamId);
                    responseWriter.writeString(AsSubscriptionVOD.$responseFields[2], AsSubscriptionVOD.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsSubscriptionVOD{__typename=" + this.__typename + ", streamId=" + this.streamId + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsSubscriptionVOD)) {
                return false;
            }
            AsSubscriptionVOD asSubscriptionVOD = (AsSubscriptionVOD) obj;
            return this.__typename.equals(asSubscriptionVOD.__typename) && this.streamId.equals(asSubscriptionVOD.streamId) && this.name.equals(asSubscriptionVOD.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.streamId.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsSubscriptionVOD> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsSubscriptionVOD map(ResponseReader responseReader) {
                return new AsSubscriptionVOD(responseReader.readString(AsSubscriptionVOD.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsSubscriptionVOD.$responseFields[1]), responseReader.readString(AsSubscriptionVOD.$responseFields[2]));
            }
        }
    }

    public static class AsCatchUpTV implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("streamId", "streamId", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String name;
        final String streamId;

        public AsCatchUpTV(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.streamId = (String) Utils.checkNotNull(str2, "streamId == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public String __typename() {
            return this.__typename;
        }

        public String streamId() {
            return this.streamId;
        }

        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.AsCatchUpTV.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsCatchUpTV.$responseFields[0], AsCatchUpTV.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsCatchUpTV.$responseFields[1], AsCatchUpTV.this.streamId);
                    responseWriter.writeString(AsCatchUpTV.$responseFields[2], AsCatchUpTV.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsCatchUpTV{__typename=" + this.__typename + ", streamId=" + this.streamId + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsCatchUpTV)) {
                return false;
            }
            AsCatchUpTV asCatchUpTV = (AsCatchUpTV) obj;
            return this.__typename.equals(asCatchUpTV.__typename) && this.streamId.equals(asCatchUpTV.streamId) && this.name.equals(asCatchUpTV.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.streamId.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsCatchUpTV> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsCatchUpTV map(ResponseReader responseReader) {
                return new AsCatchUpTV(responseReader.readString(AsCatchUpTV.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsCatchUpTV.$responseFields[1]), responseReader.readString(AsCatchUpTV.$responseFields[2]));
            }
        }
    }

    public static class AsExternalOffer implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList()), ResponseField.forCustomType("location", "location", null, false, CustomType.URI, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String location;
        final String name;

        public AsExternalOffer(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.name = (String) Utils.checkNotNull(str2, "name == null");
            this.location = (String) Utils.checkNotNull(str3, "location == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public String __typename() {
            return this.__typename;
        }

        public String name() {
            return this.name;
        }

        public String location() {
            return this.location;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.AsExternalOffer.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsExternalOffer.$responseFields[0], AsExternalOffer.this.__typename);
                    responseWriter.writeString(AsExternalOffer.$responseFields[1], AsExternalOffer.this.name);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) AsExternalOffer.$responseFields[2], AsExternalOffer.this.location);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsExternalOffer{__typename=" + this.__typename + ", name=" + this.name + ", location=" + this.location + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsExternalOffer)) {
                return false;
            }
            AsExternalOffer asExternalOffer = (AsExternalOffer) obj;
            return this.__typename.equals(asExternalOffer.__typename) && this.name.equals(asExternalOffer.name) && this.location.equals(asExternalOffer.location);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.location.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsExternalOffer> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsExternalOffer map(ResponseReader responseReader) {
                return new AsExternalOffer(responseReader.readString(AsExternalOffer.$responseFields[0]), responseReader.readString(AsExternalOffer.$responseFields[1]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsExternalOffer.$responseFields[2]));
            }
        }
    }

    public static class AsOffer implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;

        public AsOffer(String str) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentBase.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentBase.AsOffer.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsOffer.$responseFields[0], AsOffer.this.__typename);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsOffer{__typename=" + this.__typename + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof AsOffer) {
                return this.__typename.equals(((AsOffer) obj).__typename);
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

        public static final class Mapper implements ResponseFieldMapper<AsOffer> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsOffer map(ResponseReader responseReader) {
                return new AsOffer(responseReader.readString(AsOffer.$responseFields[0]));
            }
        }
    }
}
