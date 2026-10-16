package com.dotscreen.bokit.fragment;

import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseReader;
import com.apollographql.apollo.api.internal.ResponseWriter;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import com.dotscreen.bokit.type.CustomType;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CatalogSectionFragment implements GraphqlFragment {
    static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList()), ResponseField.forList("artworks", "artworks", null, false, Collections.emptyList()), ResponseField.forList("classifiers", "classifiers", null, true, Collections.emptyList())};
    public static final String FRAGMENT_DEFINITION = "fragment CatalogSectionFragment on CatalogSection {\n  __typename\n  id\n  name\n  artworks {\n    __typename\n    source(size: {width: 1234, height: 1234})\n  }\n  classifiers {\n    __typename\n    id\n    name\n    ... on ContentRating {\n      level\n    }\n  }\n}";
    private volatile transient int $hashCode;
    private volatile transient boolean $hashCodeMemoized;
    private volatile transient String $toString;
    final String __typename;
    final List<Artwork> artworks;
    final List<Classifier> classifiers;
    final String id;
    final String name;

    public CatalogSectionFragment(String str, String str2, String str3, List<Artwork> list, List<Classifier> list2) {
        this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
        this.id = (String) Utils.checkNotNull(str2, "id == null");
        this.name = (String) Utils.checkNotNull(str3, "name == null");
        this.artworks = (List) Utils.checkNotNull(list, "artworks == null");
        this.classifiers = list2;
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

    public List<Artwork> artworks() {
        return this.artworks;
    }

    public List<Classifier> classifiers() {
        return this.classifiers;
    }

    @Override // com.apollographql.apollo.api.GraphqlFragment
    public ResponseFieldMarshaller marshaller() {
        return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.1
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
            public void marshal(ResponseWriter responseWriter) {
                responseWriter.writeString(CatalogSectionFragment.$responseFields[0], CatalogSectionFragment.this.__typename);
                responseWriter.writeCustom((ResponseField.CustomTypeField) CatalogSectionFragment.$responseFields[1], CatalogSectionFragment.this.id);
                responseWriter.writeString(CatalogSectionFragment.$responseFields[2], CatalogSectionFragment.this.name);
                responseWriter.writeList(CatalogSectionFragment.$responseFields[3], CatalogSectionFragment.this.artworks, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.1.1
                    @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                    public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            listItemWriter.writeObject(((Artwork) it.next()).marshaller());
                        }
                    }
                });
                responseWriter.writeList(CatalogSectionFragment.$responseFields[4], CatalogSectionFragment.this.classifiers, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.1.2
                    @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                    public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            listItemWriter.writeObject(((Classifier) it.next()).marshaller());
                        }
                    }
                });
            }
        };
    }

    public String toString() {
        if (this.$toString == null) {
            this.$toString = "CatalogSectionFragment{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + ", artworks=" + this.artworks + ", classifiers=" + this.classifiers + "}";
        }
        return this.$toString;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CatalogSectionFragment)) {
            return false;
        }
        CatalogSectionFragment catalogSectionFragment = (CatalogSectionFragment) obj;
        if (this.__typename.equals(catalogSectionFragment.__typename) && this.id.equals(catalogSectionFragment.id) && this.name.equals(catalogSectionFragment.name) && this.artworks.equals(catalogSectionFragment.artworks)) {
            List<Classifier> list = this.classifiers;
            List<Classifier> list2 = catalogSectionFragment.classifiers;
            if (list == null) {
                if (list2 == null) {
                    return true;
                }
            } else if (list.equals(list2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (!this.$hashCodeMemoized) {
            int iHashCode = (((((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.artworks.hashCode()) * 1000003;
            List<Classifier> list = this.classifiers;
            this.$hashCode = iHashCode ^ (list == null ? 0 : list.hashCode());
            this.$hashCodeMemoized = true;
        }
        return this.$hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<CatalogSectionFragment> {
        final Artwork.Mapper artworkFieldMapper = new Artwork.Mapper();
        final Classifier.Mapper classifierFieldMapper = new Classifier.Mapper();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
        public CatalogSectionFragment map(ResponseReader responseReader) {
            return new CatalogSectionFragment(responseReader.readString(CatalogSectionFragment.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) CatalogSectionFragment.$responseFields[1]), responseReader.readString(CatalogSectionFragment.$responseFields[2]), responseReader.readList(CatalogSectionFragment.$responseFields[3], new ResponseReader.ListReader<Artwork>() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.Mapper.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                public Artwork read(ResponseReader.ListItemReader listItemReader) {
                    return (Artwork) listItemReader.readObject(new ResponseReader.ObjectReader<Artwork>() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.Mapper.1.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public Artwork read(ResponseReader responseReader2) {
                            return Mapper.this.artworkFieldMapper.map(responseReader2);
                        }
                    });
                }
            }), responseReader.readList(CatalogSectionFragment.$responseFields[4], new ResponseReader.ListReader<Classifier>() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.Mapper.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                public Classifier read(ResponseReader.ListItemReader listItemReader) {
                    return (Classifier) listItemReader.readObject(new ResponseReader.ObjectReader<Classifier>() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.Mapper.2.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                        public Classifier read(ResponseReader responseReader2) {
                            return Mapper.this.classifierFieldMapper.map(responseReader2);
                        }
                    });
                }
            }));
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.Artwork.1
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
                AsContentRating asContentRating = (AsContentRating) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsContentRating>() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier.Mapper.1
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

        @Override // com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier
        public String name() {
            return this.name;
        }

        public int level() {
            return this.level;
        }

        @Override // com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.AsContentRating.1
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

        @Override // com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.CatalogSectionFragment.Classifier
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.CatalogSectionFragment.AsClassifier.1
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
}
