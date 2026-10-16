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
import com.dotscreen.bokit.type.CrewDepartment;
import com.dotscreen.bokit.type.CustomType;
import com.dotscreen.bokit.type.LanguageCode;
import com.dotscreen.bokit.type.RoleCategory;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ContentFragmentDetails extends GraphqlFragment {
    public static final String FRAGMENT_DEFINITION = "fragment ContentFragmentDetails on Content {\n  __typename\n  previews {\n    __typename\n    description\n    streamId\n  }\n  countries {\n    __typename\n    name\n    isoCode\n  }\n  cast {\n    __typename\n    person {\n      __typename\n      id\n      name\n    }\n    roleType\n    role\n  }\n  crew {\n    __typename\n    person {\n      __typename\n      id\n      name\n    }\n    department\n    task\n  }\n  offers(linear: false) {\n    __typename\n    items {\n      __typename\n      audioLanguages\n      subtitlesLanguages\n    }\n  }\n  ... on Series {\n    seasons {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n    episodes {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n  }\n  ... on Season {\n    seasonNumber\n    episodes {\n      __typename\n      pageInfo {\n        __typename\n        total\n      }\n    }\n    series {\n      __typename\n      id\n    }\n  }\n  ... on Episode {\n    episodeNumber\n    series {\n      __typename\n      id\n    }\n    season {\n      __typename\n      id\n    }\n  }\n}";

    public interface Cast {
        String __typename();

        ResponseFieldMarshaller marshaller();

        Person person();

        String role();

        RoleCategory roleType();
    }

    public interface Country {
        String __typename();

        String isoCode();

        ResponseFieldMarshaller marshaller();

        String name();
    }

    public interface Crew {
        String __typename();

        CrewDepartment department();

        ResponseFieldMarshaller marshaller();

        Person1 person();

        String task();
    }

    public interface Item {
        String __typename();

        List<LanguageCode> audioLanguages();

        ResponseFieldMarshaller marshaller();

        List<LanguageCode> subtitlesLanguages();
    }

    public interface Offers {
        String __typename();

        List<? extends Item> items();

        ResponseFieldMarshaller marshaller();
    }

    public interface Person {
        String __typename();

        String id();

        ResponseFieldMarshaller marshaller();

        String name();
    }

    public interface Person1 {
        String __typename();

        String id();

        ResponseFieldMarshaller marshaller();

        String name();
    }

    public interface Preview {
        String __typename();

        String description();

        ResponseFieldMarshaller marshaller();

        String streamId();
    }

    String __typename();

    List<? extends Cast> cast();

    List<? extends Country> countries();

    List<? extends Crew> crew();

    @Override // com.apollographql.apollo.api.GraphqlFragment
    ResponseFieldMarshaller marshaller();

    Offers offers();

    List<? extends Preview> previews();

    public static final class Mapper implements ResponseFieldMapper<ContentFragmentDetails> {
        static final ResponseField[] $responseFields = {ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Series"}))), ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Season"}))), ResponseField.forFragment("__typename", "__typename", Arrays.asList(ResponseField.Condition.typeCondition(new String[]{"Episode"})))};
        final AsSeries.Mapper asSeriesFieldMapper = new AsSeries.Mapper();
        final AsSeason.Mapper asSeasonFieldMapper = new AsSeason.Mapper();
        final AsEpisode.Mapper asEpisodeFieldMapper = new AsEpisode.Mapper();
        final AsContent.Mapper asContentFieldMapper = new AsContent.Mapper();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
        public ContentFragmentDetails map(ResponseReader responseReader) {
            AsSeries asSeries = (AsSeries) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsSeries>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Mapper.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                public AsSeries read(ResponseReader responseReader2) {
                    return Mapper.this.asSeriesFieldMapper.map(responseReader2);
                }
            });
            if (asSeries != null) {
                return asSeries;
            }
            AsSeason asSeason = (AsSeason) responseReader.readFragment($responseFields[1], new ResponseReader.ObjectReader<AsSeason>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Mapper.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                public AsSeason read(ResponseReader responseReader2) {
                    return Mapper.this.asSeasonFieldMapper.map(responseReader2);
                }
            });
            if (asSeason != null) {
                return asSeason;
            }
            AsEpisode asEpisode = (AsEpisode) responseReader.readFragment($responseFields[2], new ResponseReader.ObjectReader<AsEpisode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Mapper.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                public AsEpisode read(ResponseReader responseReader2) {
                    return Mapper.this.asEpisodeFieldMapper.map(responseReader2);
                }
            });
            return asEpisode != null ? asEpisode : this.asContentFieldMapper.map(responseReader);
        }
    }

    public static class AsSeries implements ContentFragmentDetails {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("previews", "previews", null, false, Collections.emptyList()), ResponseField.forList("countries", "countries", null, false, Collections.emptyList()), ResponseField.forList("cast", "cast", null, false, Collections.emptyList()), ResponseField.forList("crew", "crew", null, false, Collections.emptyList()), ResponseField.forObject("offers", "offers", new UnmodifiableMapBuilder(1).put("linear", false).build(), false, Collections.emptyList()), ResponseField.forObject("seasons", "seasons", null, false, Collections.emptyList()), ResponseField.forObject("episodes", "episodes", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Cast1> cast;
        final List<Country1> countries;
        final List<Crew1> crew;
        final Episodes episodes;
        final Offers1 offers;
        final List<Preview1> previews;
        final Seasons seasons;

        public AsSeries(String str, List<Preview1> list, List<Country1> list2, List<Cast1> list3, List<Crew1> list4, Offers1 offers1, Seasons seasons, Episodes episodes) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.previews = (List) Utils.checkNotNull(list, "previews == null");
            this.countries = (List) Utils.checkNotNull(list2, "countries == null");
            this.cast = (List) Utils.checkNotNull(list3, "cast == null");
            this.crew = (List) Utils.checkNotNull(list4, "crew == null");
            this.offers = (Offers1) Utils.checkNotNull(offers1, "offers == null");
            this.seasons = (Seasons) Utils.checkNotNull(seasons, "seasons == null");
            this.episodes = (Episodes) Utils.checkNotNull(episodes, "episodes == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Preview1> previews() {
            return this.previews;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Country1> countries() {
            return this.countries;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Cast1> cast() {
            return this.cast;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Crew1> crew() {
            return this.crew;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public Offers1 offers() {
            return this.offers;
        }

        public Seasons seasons() {
            return this.seasons;
        }

        public Episodes episodes() {
            return this.episodes;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails, com.apollographql.apollo.api.GraphqlFragment
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsSeries.$responseFields[0], AsSeries.this.__typename);
                    responseWriter.writeList(AsSeries.$responseFields[1], AsSeries.this.previews, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Preview1) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsSeries.$responseFields[2], AsSeries.this.countries, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.1.2
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Country1) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsSeries.$responseFields[3], AsSeries.this.cast, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.1.3
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Cast1) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsSeries.$responseFields[4], AsSeries.this.crew, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.1.4
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Crew1) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeObject(AsSeries.$responseFields[5], AsSeries.this.offers.marshaller());
                    responseWriter.writeObject(AsSeries.$responseFields[6], AsSeries.this.seasons.marshaller());
                    responseWriter.writeObject(AsSeries.$responseFields[7], AsSeries.this.episodes.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsSeries{__typename=" + this.__typename + ", previews=" + this.previews + ", countries=" + this.countries + ", cast=" + this.cast + ", crew=" + this.crew + ", offers=" + this.offers + ", seasons=" + this.seasons + ", episodes=" + this.episodes + "}";
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
            return this.__typename.equals(asSeries.__typename) && this.previews.equals(asSeries.previews) && this.countries.equals(asSeries.countries) && this.cast.equals(asSeries.cast) && this.crew.equals(asSeries.crew) && this.offers.equals(asSeries.offers) && this.seasons.equals(asSeries.seasons) && this.episodes.equals(asSeries.episodes);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((((((((((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.previews.hashCode()) * 1000003) ^ this.countries.hashCode()) * 1000003) ^ this.cast.hashCode()) * 1000003) ^ this.crew.hashCode()) * 1000003) ^ this.offers.hashCode()) * 1000003) ^ this.seasons.hashCode()) * 1000003) ^ this.episodes.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsSeries> {
            final Preview1.Mapper preview1FieldMapper = new Preview1.Mapper();
            final Country1.Mapper country1FieldMapper = new Country1.Mapper();
            final Cast1.Mapper cast1FieldMapper = new Cast1.Mapper();
            final Crew1.Mapper crew1FieldMapper = new Crew1.Mapper();
            final Offers1.Mapper offers1FieldMapper = new Offers1.Mapper();
            final Seasons.Mapper seasonsFieldMapper = new Seasons.Mapper();
            final Episodes.Mapper episodesFieldMapper = new Episodes.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsSeries map(ResponseReader responseReader) {
                return new AsSeries(responseReader.readString(AsSeries.$responseFields[0]), responseReader.readList(AsSeries.$responseFields[1], new ResponseReader.ListReader<Preview1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Preview1 read(ResponseReader.ListItemReader listItemReader) {
                        return (Preview1) listItemReader.readObject(new ResponseReader.ObjectReader<Preview1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Preview1 read(ResponseReader responseReader2) {
                                return Mapper.this.preview1FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsSeries.$responseFields[2], new ResponseReader.ListReader<Country1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Country1 read(ResponseReader.ListItemReader listItemReader) {
                        return (Country1) listItemReader.readObject(new ResponseReader.ObjectReader<Country1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.2.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Country1 read(ResponseReader responseReader2) {
                                return Mapper.this.country1FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsSeries.$responseFields[3], new ResponseReader.ListReader<Cast1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Cast1 read(ResponseReader.ListItemReader listItemReader) {
                        return (Cast1) listItemReader.readObject(new ResponseReader.ObjectReader<Cast1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.3.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Cast1 read(ResponseReader responseReader2) {
                                return Mapper.this.cast1FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsSeries.$responseFields[4], new ResponseReader.ListReader<Crew1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.4
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Crew1 read(ResponseReader.ListItemReader listItemReader) {
                        return (Crew1) listItemReader.readObject(new ResponseReader.ObjectReader<Crew1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.4.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Crew1 read(ResponseReader responseReader2) {
                                return Mapper.this.crew1FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), (Offers1) responseReader.readObject(AsSeries.$responseFields[5], new ResponseReader.ObjectReader<Offers1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.5
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Offers1 read(ResponseReader responseReader2) {
                        return Mapper.this.offers1FieldMapper.map(responseReader2);
                    }
                }), (Seasons) responseReader.readObject(AsSeries.$responseFields[6], new ResponseReader.ObjectReader<Seasons>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.6
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Seasons read(ResponseReader responseReader2) {
                        return Mapper.this.seasonsFieldMapper.map(responseReader2);
                    }
                }), (Episodes) responseReader.readObject(AsSeries.$responseFields[7], new ResponseReader.ObjectReader<Episodes>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeries.Mapper.7
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Episodes read(ResponseReader responseReader2) {
                        return Mapper.this.episodesFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Preview1 implements Preview {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString(TvContractCompat.Channels.COLUMN_DESCRIPTION, TvContractCompat.Channels.COLUMN_DESCRIPTION, null, true, Collections.emptyList()), ResponseField.forCustomType("streamId", "streamId", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String description;
        final String streamId;

        public Preview1(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.description = str2;
            this.streamId = (String) Utils.checkNotNull(str3, "streamId == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String description() {
            return this.description;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String streamId() {
            return this.streamId;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Preview1.$responseFields[0], Preview1.this.__typename);
                    responseWriter.writeString(Preview1.$responseFields[1], Preview1.this.description);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Preview1.$responseFields[2], Preview1.this.streamId);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Preview1{__typename=" + this.__typename + ", description=" + this.description + ", streamId=" + this.streamId + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Preview1)) {
                return false;
            }
            Preview1 preview1 = (Preview1) obj;
            return this.__typename.equals(preview1.__typename) && ((str = this.description) != null ? str.equals(preview1.description) : preview1.description == null) && this.streamId.equals(preview1.streamId);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                String str = this.description;
                this.$hashCode = ((iHashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.streamId.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Preview1> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Preview1 map(ResponseReader responseReader) {
                return new Preview1(responseReader.readString(Preview1.$responseFields[0]), responseReader.readString(Preview1.$responseFields[1]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Preview1.$responseFields[2]));
            }
        }
    }

    public static class Country1 implements Country {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList()), ResponseField.forString("isoCode", "isoCode", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String isoCode;
        final String name;

        public Country1(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.name = (String) Utils.checkNotNull(str2, "name == null");
            this.isoCode = (String) Utils.checkNotNull(str3, "isoCode == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String isoCode() {
            return this.isoCode;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Country1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Country1.$responseFields[0], Country1.this.__typename);
                    responseWriter.writeString(Country1.$responseFields[1], Country1.this.name);
                    responseWriter.writeString(Country1.$responseFields[2], Country1.this.isoCode);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Country1{__typename=" + this.__typename + ", name=" + this.name + ", isoCode=" + this.isoCode + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Country1)) {
                return false;
            }
            Country1 country1 = (Country1) obj;
            return this.__typename.equals(country1.__typename) && this.name.equals(country1.name) && this.isoCode.equals(country1.isoCode);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.isoCode.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Country1> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Country1 map(ResponseReader responseReader) {
                return new Country1(responseReader.readString(Country1.$responseFields[0]), responseReader.readString(Country1.$responseFields[1]), responseReader.readString(Country1.$responseFields[2]));
            }
        }
    }

    public static class Cast1 implements Cast {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("person", "person", null, false, Collections.emptyList()), ResponseField.forString("roleType", "roleType", null, true, Collections.emptyList()), ResponseField.forString("role", "role", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Person2 person;
        final String role;
        final RoleCategory roleType;

        public Cast1(String str, Person2 person2, RoleCategory roleCategory, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.person = (Person2) Utils.checkNotNull(person2, "person == null");
            this.roleType = roleCategory;
            this.role = str2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public Person2 person() {
            return this.person;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public RoleCategory roleType() {
            return this.roleType;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public String role() {
            return this.role;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Cast1.$responseFields[0], Cast1.this.__typename);
                    responseWriter.writeObject(Cast1.$responseFields[1], Cast1.this.person.marshaller());
                    responseWriter.writeString(Cast1.$responseFields[2], Cast1.this.roleType != null ? Cast1.this.roleType.rawValue() : null);
                    responseWriter.writeString(Cast1.$responseFields[3], Cast1.this.role);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Cast1{__typename=" + this.__typename + ", person=" + this.person + ", roleType=" + this.roleType + ", role=" + this.role + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            RoleCategory roleCategory;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Cast1)) {
                return false;
            }
            Cast1 cast1 = (Cast1) obj;
            if (this.__typename.equals(cast1.__typename) && this.person.equals(cast1.person) && ((roleCategory = this.roleType) != null ? roleCategory.equals(cast1.roleType) : cast1.roleType == null)) {
                String str = this.role;
                String str2 = cast1.role;
                if (str == null) {
                    if (str2 == null) {
                        return true;
                    }
                } else if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.person.hashCode()) * 1000003;
                RoleCategory roleCategory = this.roleType;
                int iHashCode2 = (iHashCode ^ (roleCategory == null ? 0 : roleCategory.hashCode())) * 1000003;
                String str = this.role;
                this.$hashCode = iHashCode2 ^ (str != null ? str.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Cast1> {
            final Person2.Mapper person2FieldMapper = new Person2.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Cast1 map(ResponseReader responseReader) {
                String string = responseReader.readString(Cast1.$responseFields[0]);
                Person2 person2 = (Person2) responseReader.readObject(Cast1.$responseFields[1], new ResponseReader.ObjectReader<Person2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast1.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Person2 read(ResponseReader responseReader2) {
                        return Mapper.this.person2FieldMapper.map(responseReader2);
                    }
                });
                String string2 = responseReader.readString(Cast1.$responseFields[2]);
                return new Cast1(string, person2, string2 != null ? RoleCategory.safeValueOf(string2) : null, responseReader.readString(Cast1.$responseFields[3]));
            }
        }
    }

    public static class Person2 implements Person {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public Person2(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Person2.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Person2.$responseFields[0], Person2.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Person2.$responseFields[1], Person2.this.id);
                    responseWriter.writeString(Person2.$responseFields[2], Person2.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Person2{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person2)) {
                return false;
            }
            Person2 person2 = (Person2) obj;
            return this.__typename.equals(person2.__typename) && this.id.equals(person2.id) && this.name.equals(person2.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Person2> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Person2 map(ResponseReader responseReader) {
                return new Person2(responseReader.readString(Person2.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Person2.$responseFields[1]), responseReader.readString(Person2.$responseFields[2]));
            }
        }
    }

    public static class Crew1 implements Crew {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("person", "person", null, false, Collections.emptyList()), ResponseField.forString("department", "department", null, true, Collections.emptyList()), ResponseField.forString("task", "task", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final CrewDepartment department;
        final Person3 person;
        final String task;

        public Crew1(String str, Person3 person3, CrewDepartment crewDepartment, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.person = (Person3) Utils.checkNotNull(person3, "person == null");
            this.department = crewDepartment;
            this.task = str2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public Person3 person() {
            return this.person;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public CrewDepartment department() {
            return this.department;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public String task() {
            return this.task;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Crew1.$responseFields[0], Crew1.this.__typename);
                    responseWriter.writeObject(Crew1.$responseFields[1], Crew1.this.person.marshaller());
                    responseWriter.writeString(Crew1.$responseFields[2], Crew1.this.department != null ? Crew1.this.department.rawValue() : null);
                    responseWriter.writeString(Crew1.$responseFields[3], Crew1.this.task);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Crew1{__typename=" + this.__typename + ", person=" + this.person + ", department=" + this.department + ", task=" + this.task + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            CrewDepartment crewDepartment;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Crew1)) {
                return false;
            }
            Crew1 crew1 = (Crew1) obj;
            if (this.__typename.equals(crew1.__typename) && this.person.equals(crew1.person) && ((crewDepartment = this.department) != null ? crewDepartment.equals(crew1.department) : crew1.department == null)) {
                String str = this.task;
                String str2 = crew1.task;
                if (str == null) {
                    if (str2 == null) {
                        return true;
                    }
                } else if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.person.hashCode()) * 1000003;
                CrewDepartment crewDepartment = this.department;
                int iHashCode2 = (iHashCode ^ (crewDepartment == null ? 0 : crewDepartment.hashCode())) * 1000003;
                String str = this.task;
                this.$hashCode = iHashCode2 ^ (str != null ? str.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Crew1> {
            final Person3.Mapper person3FieldMapper = new Person3.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Crew1 map(ResponseReader responseReader) {
                String string = responseReader.readString(Crew1.$responseFields[0]);
                Person3 person3 = (Person3) responseReader.readObject(Crew1.$responseFields[1], new ResponseReader.ObjectReader<Person3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew1.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Person3 read(ResponseReader responseReader2) {
                        return Mapper.this.person3FieldMapper.map(responseReader2);
                    }
                });
                String string2 = responseReader.readString(Crew1.$responseFields[2]);
                return new Crew1(string, person3, string2 != null ? CrewDepartment.safeValueOf(string2) : null, responseReader.readString(Crew1.$responseFields[3]));
            }
        }
    }

    public static class Person3 implements Person1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public Person3(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Person3.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Person3.$responseFields[0], Person3.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Person3.$responseFields[1], Person3.this.id);
                    responseWriter.writeString(Person3.$responseFields[2], Person3.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Person3{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person3)) {
                return false;
            }
            Person3 person3 = (Person3) obj;
            return this.__typename.equals(person3.__typename) && this.id.equals(person3.id) && this.name.equals(person3.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Person3> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Person3 map(ResponseReader responseReader) {
                return new Person3(responseReader.readString(Person3.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Person3.$responseFields[1]), responseReader.readString(Person3.$responseFields[2]));
            }
        }
    }

    public static class Offers1 implements Offers {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("items", "items", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Item1> items;

        public Offers1(String str, List<Item1> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.items = (List) Utils.checkNotNull(list, "items == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public List<Item1> items() {
            return this.items;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Offers1.$responseFields[0], Offers1.this.__typename);
                    responseWriter.writeList(Offers1.$responseFields[1], Offers1.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers1.1.1
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
                this.$toString = "Offers1{__typename=" + this.__typename + ", items=" + this.items + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Offers1)) {
                return false;
            }
            Offers1 offers1 = (Offers1) obj;
            return this.__typename.equals(offers1.__typename) && this.items.equals(offers1.items);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.items.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Offers1> {
            final Item1.Mapper item1FieldMapper = new Item1.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Offers1 map(ResponseReader responseReader) {
                return new Offers1(responseReader.readString(Offers1.$responseFields[0]), responseReader.readList(Offers1.$responseFields[1], new ResponseReader.ListReader<Item1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers1.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item1 read(ResponseReader.ListItemReader listItemReader) {
                        return (Item1) listItemReader.readObject(new ResponseReader.ObjectReader<Item1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers1.Mapper.1.1
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

    public static class Item1 implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("audioLanguages", "audioLanguages", null, true, Collections.emptyList()), ResponseField.forList("subtitlesLanguages", "subtitlesLanguages", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<LanguageCode> audioLanguages;
        final List<LanguageCode> subtitlesLanguages;

        public Item1(String str, List<LanguageCode> list, List<LanguageCode> list2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.audioLanguages = list;
            this.subtitlesLanguages = list2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public List<LanguageCode> audioLanguages() {
            return this.audioLanguages;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public List<LanguageCode> subtitlesLanguages() {
            return this.subtitlesLanguages;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Item1.$responseFields[0], Item1.this.__typename);
                    responseWriter.writeList(Item1.$responseFields[1], Item1.this.audioLanguages, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item1.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString(((LanguageCode) it.next()).rawValue());
                            }
                        }
                    });
                    responseWriter.writeList(Item1.$responseFields[2], Item1.this.subtitlesLanguages, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item1.1.2
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString(((LanguageCode) it.next()).rawValue());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Item1{__typename=" + this.__typename + ", audioLanguages=" + this.audioLanguages + ", subtitlesLanguages=" + this.subtitlesLanguages + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            List<LanguageCode> list;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Item1)) {
                return false;
            }
            Item1 item1 = (Item1) obj;
            if (this.__typename.equals(item1.__typename) && ((list = this.audioLanguages) != null ? list.equals(item1.audioLanguages) : item1.audioLanguages == null)) {
                List<LanguageCode> list2 = this.subtitlesLanguages;
                List<LanguageCode> list3 = item1.subtitlesLanguages;
                if (list2 == null) {
                    if (list3 == null) {
                        return true;
                    }
                } else if (list2.equals(list3)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                List<LanguageCode> list = this.audioLanguages;
                int iHashCode2 = (iHashCode ^ (list == null ? 0 : list.hashCode())) * 1000003;
                List<LanguageCode> list2 = this.subtitlesLanguages;
                this.$hashCode = iHashCode2 ^ (list2 != null ? list2.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Item1> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Item1 map(ResponseReader responseReader) {
                return new Item1(responseReader.readString(Item1.$responseFields[0]), responseReader.readList(Item1.$responseFields[1], new ResponseReader.ListReader<LanguageCode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item1.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LanguageCode read(ResponseReader.ListItemReader listItemReader) {
                        return LanguageCode.safeValueOf(listItemReader.readString());
                    }
                }), responseReader.readList(Item1.$responseFields[2], new ResponseReader.ListReader<LanguageCode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item1.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LanguageCode read(ResponseReader.ListItemReader listItemReader) {
                        return LanguageCode.safeValueOf(listItemReader.readString());
                    }
                }));
            }
        }
    }

    public static class Seasons {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("pageInfo", "pageInfo", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final PageInfo pageInfo;

        public Seasons(String str, PageInfo pageInfo) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.pageInfo = (PageInfo) Utils.checkNotNull(pageInfo, "pageInfo == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public PageInfo pageInfo() {
            return this.pageInfo;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Seasons.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Seasons.$responseFields[0], Seasons.this.__typename);
                    responseWriter.writeObject(Seasons.$responseFields[1], Seasons.this.pageInfo.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Seasons{__typename=" + this.__typename + ", pageInfo=" + this.pageInfo + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Seasons)) {
                return false;
            }
            Seasons seasons = (Seasons) obj;
            return this.__typename.equals(seasons.__typename) && this.pageInfo.equals(seasons.pageInfo);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.pageInfo.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Seasons> {
            final PageInfo.Mapper pageInfoFieldMapper = new PageInfo.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Seasons map(ResponseReader responseReader) {
                return new Seasons(responseReader.readString(Seasons.$responseFields[0]), (PageInfo) responseReader.readObject(Seasons.$responseFields[1], new ResponseReader.ObjectReader<PageInfo>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Seasons.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public PageInfo read(ResponseReader responseReader2) {
                        return Mapper.this.pageInfoFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class PageInfo {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forInt("total", "total", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Integer total;

        public PageInfo(String str, Integer num) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.total = num;
        }

        public String __typename() {
            return this.__typename;
        }

        public Integer total() {
            return this.total;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.PageInfo.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(PageInfo.$responseFields[0], PageInfo.this.__typename);
                    responseWriter.writeInt(PageInfo.$responseFields[1], PageInfo.this.total);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "PageInfo{__typename=" + this.__typename + ", total=" + this.total + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PageInfo)) {
                return false;
            }
            PageInfo pageInfo = (PageInfo) obj;
            if (this.__typename.equals(pageInfo.__typename)) {
                Integer num = this.total;
                Integer num2 = pageInfo.total;
                if (num == null) {
                    if (num2 == null) {
                        return true;
                    }
                } else if (num.equals(num2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                Integer num = this.total;
                this.$hashCode = iHashCode ^ (num == null ? 0 : num.hashCode());
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<PageInfo> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public PageInfo map(ResponseReader responseReader) {
                return new PageInfo(responseReader.readString(PageInfo.$responseFields[0]), responseReader.readInt(PageInfo.$responseFields[1]));
            }
        }
    }

    public static class Episodes {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("pageInfo", "pageInfo", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final PageInfo1 pageInfo;

        public Episodes(String str, PageInfo1 pageInfo1) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.pageInfo = (PageInfo1) Utils.checkNotNull(pageInfo1, "pageInfo == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public PageInfo1 pageInfo() {
            return this.pageInfo;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Episodes.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Episodes.$responseFields[0], Episodes.this.__typename);
                    responseWriter.writeObject(Episodes.$responseFields[1], Episodes.this.pageInfo.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Episodes{__typename=" + this.__typename + ", pageInfo=" + this.pageInfo + "}";
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
            return this.__typename.equals(episodes.__typename) && this.pageInfo.equals(episodes.pageInfo);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.pageInfo.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Episodes> {
            final PageInfo1.Mapper pageInfo1FieldMapper = new PageInfo1.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Episodes map(ResponseReader responseReader) {
                return new Episodes(responseReader.readString(Episodes.$responseFields[0]), (PageInfo1) responseReader.readObject(Episodes.$responseFields[1], new ResponseReader.ObjectReader<PageInfo1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Episodes.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public PageInfo1 read(ResponseReader responseReader2) {
                        return Mapper.this.pageInfo1FieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class PageInfo1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forInt("total", "total", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Integer total;

        public PageInfo1(String str, Integer num) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.total = num;
        }

        public String __typename() {
            return this.__typename;
        }

        public Integer total() {
            return this.total;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.PageInfo1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(PageInfo1.$responseFields[0], PageInfo1.this.__typename);
                    responseWriter.writeInt(PageInfo1.$responseFields[1], PageInfo1.this.total);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "PageInfo1{__typename=" + this.__typename + ", total=" + this.total + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PageInfo1)) {
                return false;
            }
            PageInfo1 pageInfo1 = (PageInfo1) obj;
            if (this.__typename.equals(pageInfo1.__typename)) {
                Integer num = this.total;
                Integer num2 = pageInfo1.total;
                if (num == null) {
                    if (num2 == null) {
                        return true;
                    }
                } else if (num.equals(num2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                Integer num = this.total;
                this.$hashCode = iHashCode ^ (num == null ? 0 : num.hashCode());
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<PageInfo1> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public PageInfo1 map(ResponseReader responseReader) {
                return new PageInfo1(responseReader.readString(PageInfo1.$responseFields[0]), responseReader.readInt(PageInfo1.$responseFields[1]));
            }
        }
    }

    public static class AsSeason implements ContentFragmentDetails {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("previews", "previews", null, false, Collections.emptyList()), ResponseField.forList("countries", "countries", null, false, Collections.emptyList()), ResponseField.forList("cast", "cast", null, false, Collections.emptyList()), ResponseField.forList("crew", "crew", null, false, Collections.emptyList()), ResponseField.forObject("offers", "offers", new UnmodifiableMapBuilder(1).put("linear", false).build(), false, Collections.emptyList()), ResponseField.forInt("seasonNumber", "seasonNumber", null, true, Collections.emptyList()), ResponseField.forObject("episodes", "episodes", null, false, Collections.emptyList()), ResponseField.forObject("series", "series", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Cast2> cast;
        final List<Country2> countries;
        final List<Crew2> crew;
        final Episodes1 episodes;
        final Offers2 offers;
        final List<Preview2> previews;
        final Integer seasonNumber;
        final Series series;

        public AsSeason(String str, List<Preview2> list, List<Country2> list2, List<Cast2> list3, List<Crew2> list4, Offers2 offers2, Integer num, Episodes1 episodes1, Series series) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.previews = (List) Utils.checkNotNull(list, "previews == null");
            this.countries = (List) Utils.checkNotNull(list2, "countries == null");
            this.cast = (List) Utils.checkNotNull(list3, "cast == null");
            this.crew = (List) Utils.checkNotNull(list4, "crew == null");
            this.offers = (Offers2) Utils.checkNotNull(offers2, "offers == null");
            this.seasonNumber = num;
            this.episodes = (Episodes1) Utils.checkNotNull(episodes1, "episodes == null");
            this.series = (Series) Utils.checkNotNull(series, "series == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Preview2> previews() {
            return this.previews;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Country2> countries() {
            return this.countries;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Cast2> cast() {
            return this.cast;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Crew2> crew() {
            return this.crew;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public Offers2 offers() {
            return this.offers;
        }

        public Integer seasonNumber() {
            return this.seasonNumber;
        }

        public Episodes1 episodes() {
            return this.episodes;
        }

        public Series series() {
            return this.series;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails, com.apollographql.apollo.api.GraphqlFragment
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsSeason.$responseFields[0], AsSeason.this.__typename);
                    responseWriter.writeList(AsSeason.$responseFields[1], AsSeason.this.previews, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Preview2) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsSeason.$responseFields[2], AsSeason.this.countries, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.1.2
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Country2) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsSeason.$responseFields[3], AsSeason.this.cast, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.1.3
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Cast2) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsSeason.$responseFields[4], AsSeason.this.crew, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.1.4
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Crew2) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeObject(AsSeason.$responseFields[5], AsSeason.this.offers.marshaller());
                    responseWriter.writeInt(AsSeason.$responseFields[6], AsSeason.this.seasonNumber);
                    responseWriter.writeObject(AsSeason.$responseFields[7], AsSeason.this.episodes.marshaller());
                    responseWriter.writeObject(AsSeason.$responseFields[8], AsSeason.this.series.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsSeason{__typename=" + this.__typename + ", previews=" + this.previews + ", countries=" + this.countries + ", cast=" + this.cast + ", crew=" + this.crew + ", offers=" + this.offers + ", seasonNumber=" + this.seasonNumber + ", episodes=" + this.episodes + ", series=" + this.series + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            Integer num;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsSeason)) {
                return false;
            }
            AsSeason asSeason = (AsSeason) obj;
            return this.__typename.equals(asSeason.__typename) && this.previews.equals(asSeason.previews) && this.countries.equals(asSeason.countries) && this.cast.equals(asSeason.cast) && this.crew.equals(asSeason.crew) && this.offers.equals(asSeason.offers) && ((num = this.seasonNumber) != null ? num.equals(asSeason.seasonNumber) : asSeason.seasonNumber == null) && this.episodes.equals(asSeason.episodes) && this.series.equals(asSeason.series);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((((((((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.previews.hashCode()) * 1000003) ^ this.countries.hashCode()) * 1000003) ^ this.cast.hashCode()) * 1000003) ^ this.crew.hashCode()) * 1000003) ^ this.offers.hashCode()) * 1000003;
                Integer num = this.seasonNumber;
                this.$hashCode = ((((iHashCode ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.episodes.hashCode()) * 1000003) ^ this.series.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsSeason> {
            final Preview2.Mapper preview2FieldMapper = new Preview2.Mapper();
            final Country2.Mapper country2FieldMapper = new Country2.Mapper();
            final Cast2.Mapper cast2FieldMapper = new Cast2.Mapper();
            final Crew2.Mapper crew2FieldMapper = new Crew2.Mapper();
            final Offers2.Mapper offers2FieldMapper = new Offers2.Mapper();
            final Episodes1.Mapper episodes1FieldMapper = new Episodes1.Mapper();
            final Series.Mapper seriesFieldMapper = new Series.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsSeason map(ResponseReader responseReader) {
                return new AsSeason(responseReader.readString(AsSeason.$responseFields[0]), responseReader.readList(AsSeason.$responseFields[1], new ResponseReader.ListReader<Preview2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Preview2 read(ResponseReader.ListItemReader listItemReader) {
                        return (Preview2) listItemReader.readObject(new ResponseReader.ObjectReader<Preview2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Preview2 read(ResponseReader responseReader2) {
                                return Mapper.this.preview2FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsSeason.$responseFields[2], new ResponseReader.ListReader<Country2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Country2 read(ResponseReader.ListItemReader listItemReader) {
                        return (Country2) listItemReader.readObject(new ResponseReader.ObjectReader<Country2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.2.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Country2 read(ResponseReader responseReader2) {
                                return Mapper.this.country2FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsSeason.$responseFields[3], new ResponseReader.ListReader<Cast2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Cast2 read(ResponseReader.ListItemReader listItemReader) {
                        return (Cast2) listItemReader.readObject(new ResponseReader.ObjectReader<Cast2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.3.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Cast2 read(ResponseReader responseReader2) {
                                return Mapper.this.cast2FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsSeason.$responseFields[4], new ResponseReader.ListReader<Crew2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.4
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Crew2 read(ResponseReader.ListItemReader listItemReader) {
                        return (Crew2) listItemReader.readObject(new ResponseReader.ObjectReader<Crew2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.4.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Crew2 read(ResponseReader responseReader2) {
                                return Mapper.this.crew2FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), (Offers2) responseReader.readObject(AsSeason.$responseFields[5], new ResponseReader.ObjectReader<Offers2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.5
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Offers2 read(ResponseReader responseReader2) {
                        return Mapper.this.offers2FieldMapper.map(responseReader2);
                    }
                }), responseReader.readInt(AsSeason.$responseFields[6]), (Episodes1) responseReader.readObject(AsSeason.$responseFields[7], new ResponseReader.ObjectReader<Episodes1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.6
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Episodes1 read(ResponseReader responseReader2) {
                        return Mapper.this.episodes1FieldMapper.map(responseReader2);
                    }
                }), (Series) responseReader.readObject(AsSeason.$responseFields[8], new ResponseReader.ObjectReader<Series>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsSeason.Mapper.7
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Series read(ResponseReader responseReader2) {
                        return Mapper.this.seriesFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Preview2 implements Preview {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString(TvContractCompat.Channels.COLUMN_DESCRIPTION, TvContractCompat.Channels.COLUMN_DESCRIPTION, null, true, Collections.emptyList()), ResponseField.forCustomType("streamId", "streamId", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String description;
        final String streamId;

        public Preview2(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.description = str2;
            this.streamId = (String) Utils.checkNotNull(str3, "streamId == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String description() {
            return this.description;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String streamId() {
            return this.streamId;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview2.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Preview2.$responseFields[0], Preview2.this.__typename);
                    responseWriter.writeString(Preview2.$responseFields[1], Preview2.this.description);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Preview2.$responseFields[2], Preview2.this.streamId);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Preview2{__typename=" + this.__typename + ", description=" + this.description + ", streamId=" + this.streamId + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Preview2)) {
                return false;
            }
            Preview2 preview2 = (Preview2) obj;
            return this.__typename.equals(preview2.__typename) && ((str = this.description) != null ? str.equals(preview2.description) : preview2.description == null) && this.streamId.equals(preview2.streamId);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                String str = this.description;
                this.$hashCode = ((iHashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.streamId.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Preview2> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Preview2 map(ResponseReader responseReader) {
                return new Preview2(responseReader.readString(Preview2.$responseFields[0]), responseReader.readString(Preview2.$responseFields[1]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Preview2.$responseFields[2]));
            }
        }
    }

    public static class Country2 implements Country {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList()), ResponseField.forString("isoCode", "isoCode", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String isoCode;
        final String name;

        public Country2(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.name = (String) Utils.checkNotNull(str2, "name == null");
            this.isoCode = (String) Utils.checkNotNull(str3, "isoCode == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String isoCode() {
            return this.isoCode;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Country2.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Country2.$responseFields[0], Country2.this.__typename);
                    responseWriter.writeString(Country2.$responseFields[1], Country2.this.name);
                    responseWriter.writeString(Country2.$responseFields[2], Country2.this.isoCode);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Country2{__typename=" + this.__typename + ", name=" + this.name + ", isoCode=" + this.isoCode + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Country2)) {
                return false;
            }
            Country2 country2 = (Country2) obj;
            return this.__typename.equals(country2.__typename) && this.name.equals(country2.name) && this.isoCode.equals(country2.isoCode);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.isoCode.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Country2> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Country2 map(ResponseReader responseReader) {
                return new Country2(responseReader.readString(Country2.$responseFields[0]), responseReader.readString(Country2.$responseFields[1]), responseReader.readString(Country2.$responseFields[2]));
            }
        }
    }

    public static class Cast2 implements Cast {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("person", "person", null, false, Collections.emptyList()), ResponseField.forString("roleType", "roleType", null, true, Collections.emptyList()), ResponseField.forString("role", "role", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Person4 person;
        final String role;
        final RoleCategory roleType;

        public Cast2(String str, Person4 person4, RoleCategory roleCategory, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.person = (Person4) Utils.checkNotNull(person4, "person == null");
            this.roleType = roleCategory;
            this.role = str2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public Person4 person() {
            return this.person;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public RoleCategory roleType() {
            return this.roleType;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public String role() {
            return this.role;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast2.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Cast2.$responseFields[0], Cast2.this.__typename);
                    responseWriter.writeObject(Cast2.$responseFields[1], Cast2.this.person.marshaller());
                    responseWriter.writeString(Cast2.$responseFields[2], Cast2.this.roleType != null ? Cast2.this.roleType.rawValue() : null);
                    responseWriter.writeString(Cast2.$responseFields[3], Cast2.this.role);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Cast2{__typename=" + this.__typename + ", person=" + this.person + ", roleType=" + this.roleType + ", role=" + this.role + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            RoleCategory roleCategory;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Cast2)) {
                return false;
            }
            Cast2 cast2 = (Cast2) obj;
            if (this.__typename.equals(cast2.__typename) && this.person.equals(cast2.person) && ((roleCategory = this.roleType) != null ? roleCategory.equals(cast2.roleType) : cast2.roleType == null)) {
                String str = this.role;
                String str2 = cast2.role;
                if (str == null) {
                    if (str2 == null) {
                        return true;
                    }
                } else if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.person.hashCode()) * 1000003;
                RoleCategory roleCategory = this.roleType;
                int iHashCode2 = (iHashCode ^ (roleCategory == null ? 0 : roleCategory.hashCode())) * 1000003;
                String str = this.role;
                this.$hashCode = iHashCode2 ^ (str != null ? str.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Cast2> {
            final Person4.Mapper person4FieldMapper = new Person4.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Cast2 map(ResponseReader responseReader) {
                String string = responseReader.readString(Cast2.$responseFields[0]);
                Person4 person4 = (Person4) responseReader.readObject(Cast2.$responseFields[1], new ResponseReader.ObjectReader<Person4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast2.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Person4 read(ResponseReader responseReader2) {
                        return Mapper.this.person4FieldMapper.map(responseReader2);
                    }
                });
                String string2 = responseReader.readString(Cast2.$responseFields[2]);
                return new Cast2(string, person4, string2 != null ? RoleCategory.safeValueOf(string2) : null, responseReader.readString(Cast2.$responseFields[3]));
            }
        }
    }

    public static class Person4 implements Person {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public Person4(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Person4.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Person4.$responseFields[0], Person4.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Person4.$responseFields[1], Person4.this.id);
                    responseWriter.writeString(Person4.$responseFields[2], Person4.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Person4{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person4)) {
                return false;
            }
            Person4 person4 = (Person4) obj;
            return this.__typename.equals(person4.__typename) && this.id.equals(person4.id) && this.name.equals(person4.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Person4> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Person4 map(ResponseReader responseReader) {
                return new Person4(responseReader.readString(Person4.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Person4.$responseFields[1]), responseReader.readString(Person4.$responseFields[2]));
            }
        }
    }

    public static class Crew2 implements Crew {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("person", "person", null, false, Collections.emptyList()), ResponseField.forString("department", "department", null, true, Collections.emptyList()), ResponseField.forString("task", "task", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final CrewDepartment department;
        final Person5 person;
        final String task;

        public Crew2(String str, Person5 person5, CrewDepartment crewDepartment, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.person = (Person5) Utils.checkNotNull(person5, "person == null");
            this.department = crewDepartment;
            this.task = str2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public Person5 person() {
            return this.person;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public CrewDepartment department() {
            return this.department;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public String task() {
            return this.task;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew2.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Crew2.$responseFields[0], Crew2.this.__typename);
                    responseWriter.writeObject(Crew2.$responseFields[1], Crew2.this.person.marshaller());
                    responseWriter.writeString(Crew2.$responseFields[2], Crew2.this.department != null ? Crew2.this.department.rawValue() : null);
                    responseWriter.writeString(Crew2.$responseFields[3], Crew2.this.task);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Crew2{__typename=" + this.__typename + ", person=" + this.person + ", department=" + this.department + ", task=" + this.task + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            CrewDepartment crewDepartment;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Crew2)) {
                return false;
            }
            Crew2 crew2 = (Crew2) obj;
            if (this.__typename.equals(crew2.__typename) && this.person.equals(crew2.person) && ((crewDepartment = this.department) != null ? crewDepartment.equals(crew2.department) : crew2.department == null)) {
                String str = this.task;
                String str2 = crew2.task;
                if (str == null) {
                    if (str2 == null) {
                        return true;
                    }
                } else if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.person.hashCode()) * 1000003;
                CrewDepartment crewDepartment = this.department;
                int iHashCode2 = (iHashCode ^ (crewDepartment == null ? 0 : crewDepartment.hashCode())) * 1000003;
                String str = this.task;
                this.$hashCode = iHashCode2 ^ (str != null ? str.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Crew2> {
            final Person5.Mapper person5FieldMapper = new Person5.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Crew2 map(ResponseReader responseReader) {
                String string = responseReader.readString(Crew2.$responseFields[0]);
                Person5 person5 = (Person5) responseReader.readObject(Crew2.$responseFields[1], new ResponseReader.ObjectReader<Person5>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew2.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Person5 read(ResponseReader responseReader2) {
                        return Mapper.this.person5FieldMapper.map(responseReader2);
                    }
                });
                String string2 = responseReader.readString(Crew2.$responseFields[2]);
                return new Crew2(string, person5, string2 != null ? CrewDepartment.safeValueOf(string2) : null, responseReader.readString(Crew2.$responseFields[3]));
            }
        }
    }

    public static class Person5 implements Person1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public Person5(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Person5.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Person5.$responseFields[0], Person5.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Person5.$responseFields[1], Person5.this.id);
                    responseWriter.writeString(Person5.$responseFields[2], Person5.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Person5{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person5)) {
                return false;
            }
            Person5 person5 = (Person5) obj;
            return this.__typename.equals(person5.__typename) && this.id.equals(person5.id) && this.name.equals(person5.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Person5> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Person5 map(ResponseReader responseReader) {
                return new Person5(responseReader.readString(Person5.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Person5.$responseFields[1]), responseReader.readString(Person5.$responseFields[2]));
            }
        }
    }

    public static class Offers2 implements Offers {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("items", "items", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Item2> items;

        public Offers2(String str, List<Item2> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.items = (List) Utils.checkNotNull(list, "items == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public List<Item2> items() {
            return this.items;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers2.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Offers2.$responseFields[0], Offers2.this.__typename);
                    responseWriter.writeList(Offers2.$responseFields[1], Offers2.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers2.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Item2) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Offers2{__typename=" + this.__typename + ", items=" + this.items + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Offers2)) {
                return false;
            }
            Offers2 offers2 = (Offers2) obj;
            return this.__typename.equals(offers2.__typename) && this.items.equals(offers2.items);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.items.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Offers2> {
            final Item2.Mapper item2FieldMapper = new Item2.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Offers2 map(ResponseReader responseReader) {
                return new Offers2(responseReader.readString(Offers2.$responseFields[0]), responseReader.readList(Offers2.$responseFields[1], new ResponseReader.ListReader<Item2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers2.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item2 read(ResponseReader.ListItemReader listItemReader) {
                        return (Item2) listItemReader.readObject(new ResponseReader.ObjectReader<Item2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers2.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Item2 read(ResponseReader responseReader2) {
                                return Mapper.this.item2FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class Item2 implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("audioLanguages", "audioLanguages", null, true, Collections.emptyList()), ResponseField.forList("subtitlesLanguages", "subtitlesLanguages", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<LanguageCode> audioLanguages;
        final List<LanguageCode> subtitlesLanguages;

        public Item2(String str, List<LanguageCode> list, List<LanguageCode> list2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.audioLanguages = list;
            this.subtitlesLanguages = list2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public List<LanguageCode> audioLanguages() {
            return this.audioLanguages;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public List<LanguageCode> subtitlesLanguages() {
            return this.subtitlesLanguages;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item2.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Item2.$responseFields[0], Item2.this.__typename);
                    responseWriter.writeList(Item2.$responseFields[1], Item2.this.audioLanguages, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item2.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString(((LanguageCode) it.next()).rawValue());
                            }
                        }
                    });
                    responseWriter.writeList(Item2.$responseFields[2], Item2.this.subtitlesLanguages, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item2.1.2
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString(((LanguageCode) it.next()).rawValue());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Item2{__typename=" + this.__typename + ", audioLanguages=" + this.audioLanguages + ", subtitlesLanguages=" + this.subtitlesLanguages + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            List<LanguageCode> list;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Item2)) {
                return false;
            }
            Item2 item2 = (Item2) obj;
            if (this.__typename.equals(item2.__typename) && ((list = this.audioLanguages) != null ? list.equals(item2.audioLanguages) : item2.audioLanguages == null)) {
                List<LanguageCode> list2 = this.subtitlesLanguages;
                List<LanguageCode> list3 = item2.subtitlesLanguages;
                if (list2 == null) {
                    if (list3 == null) {
                        return true;
                    }
                } else if (list2.equals(list3)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                List<LanguageCode> list = this.audioLanguages;
                int iHashCode2 = (iHashCode ^ (list == null ? 0 : list.hashCode())) * 1000003;
                List<LanguageCode> list2 = this.subtitlesLanguages;
                this.$hashCode = iHashCode2 ^ (list2 != null ? list2.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Item2> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Item2 map(ResponseReader responseReader) {
                return new Item2(responseReader.readString(Item2.$responseFields[0]), responseReader.readList(Item2.$responseFields[1], new ResponseReader.ListReader<LanguageCode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item2.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LanguageCode read(ResponseReader.ListItemReader listItemReader) {
                        return LanguageCode.safeValueOf(listItemReader.readString());
                    }
                }), responseReader.readList(Item2.$responseFields[2], new ResponseReader.ListReader<LanguageCode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item2.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LanguageCode read(ResponseReader.ListItemReader listItemReader) {
                        return LanguageCode.safeValueOf(listItemReader.readString());
                    }
                }));
            }
        }
    }

    public static class Episodes1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("pageInfo", "pageInfo", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final PageInfo2 pageInfo;

        public Episodes1(String str, PageInfo2 pageInfo2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.pageInfo = (PageInfo2) Utils.checkNotNull(pageInfo2, "pageInfo == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public PageInfo2 pageInfo() {
            return this.pageInfo;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Episodes1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Episodes1.$responseFields[0], Episodes1.this.__typename);
                    responseWriter.writeObject(Episodes1.$responseFields[1], Episodes1.this.pageInfo.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Episodes1{__typename=" + this.__typename + ", pageInfo=" + this.pageInfo + "}";
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
            return this.__typename.equals(episodes1.__typename) && this.pageInfo.equals(episodes1.pageInfo);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.pageInfo.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Episodes1> {
            final PageInfo2.Mapper pageInfo2FieldMapper = new PageInfo2.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Episodes1 map(ResponseReader responseReader) {
                return new Episodes1(responseReader.readString(Episodes1.$responseFields[0]), (PageInfo2) responseReader.readObject(Episodes1.$responseFields[1], new ResponseReader.ObjectReader<PageInfo2>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Episodes1.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public PageInfo2 read(ResponseReader responseReader2) {
                        return Mapper.this.pageInfo2FieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class PageInfo2 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forInt("total", "total", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Integer total;

        public PageInfo2(String str, Integer num) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.total = num;
        }

        public String __typename() {
            return this.__typename;
        }

        public Integer total() {
            return this.total;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.PageInfo2.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(PageInfo2.$responseFields[0], PageInfo2.this.__typename);
                    responseWriter.writeInt(PageInfo2.$responseFields[1], PageInfo2.this.total);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "PageInfo2{__typename=" + this.__typename + ", total=" + this.total + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PageInfo2)) {
                return false;
            }
            PageInfo2 pageInfo2 = (PageInfo2) obj;
            if (this.__typename.equals(pageInfo2.__typename)) {
                Integer num = this.total;
                Integer num2 = pageInfo2.total;
                if (num == null) {
                    if (num2 == null) {
                        return true;
                    }
                } else if (num.equals(num2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                Integer num = this.total;
                this.$hashCode = iHashCode ^ (num == null ? 0 : num.hashCode());
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<PageInfo2> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public PageInfo2 map(ResponseReader responseReader) {
                return new PageInfo2(responseReader.readString(PageInfo2.$responseFields[0]), responseReader.readInt(PageInfo2.$responseFields[1]));
            }
        }
    }

    public static class Series {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;

        public Series(String str, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String id() {
            return this.id;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Series.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Series.$responseFields[0], Series.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Series.$responseFields[1], Series.this.id);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Series{__typename=" + this.__typename + ", id=" + this.id + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Series)) {
                return false;
            }
            Series series = (Series) obj;
            return this.__typename.equals(series.__typename) && this.id.equals(series.id);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Series> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Series map(ResponseReader responseReader) {
                return new Series(responseReader.readString(Series.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Series.$responseFields[1]));
            }
        }
    }

    public static class AsEpisode implements ContentFragmentDetails {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("previews", "previews", null, false, Collections.emptyList()), ResponseField.forList("countries", "countries", null, false, Collections.emptyList()), ResponseField.forList("cast", "cast", null, false, Collections.emptyList()), ResponseField.forList("crew", "crew", null, false, Collections.emptyList()), ResponseField.forObject("offers", "offers", new UnmodifiableMapBuilder(1).put("linear", false).build(), false, Collections.emptyList()), ResponseField.forInt("episodeNumber", "episodeNumber", null, true, Collections.emptyList()), ResponseField.forObject("series", "series", null, false, Collections.emptyList()), ResponseField.forObject("season", "season", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Cast3> cast;
        final List<Country3> countries;
        final List<Crew3> crew;
        final Integer episodeNumber;
        final Offers3 offers;
        final List<Preview3> previews;
        final Season season;
        final Series1 series;

        public AsEpisode(String str, List<Preview3> list, List<Country3> list2, List<Cast3> list3, List<Crew3> list4, Offers3 offers3, Integer num, Series1 series1, Season season) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.previews = (List) Utils.checkNotNull(list, "previews == null");
            this.countries = (List) Utils.checkNotNull(list2, "countries == null");
            this.cast = (List) Utils.checkNotNull(list3, "cast == null");
            this.crew = (List) Utils.checkNotNull(list4, "crew == null");
            this.offers = (Offers3) Utils.checkNotNull(offers3, "offers == null");
            this.episodeNumber = num;
            this.series = (Series1) Utils.checkNotNull(series1, "series == null");
            this.season = season;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Preview3> previews() {
            return this.previews;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Country3> countries() {
            return this.countries;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Cast3> cast() {
            return this.cast;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Crew3> crew() {
            return this.crew;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public Offers3 offers() {
            return this.offers;
        }

        public Integer episodeNumber() {
            return this.episodeNumber;
        }

        public Series1 series() {
            return this.series;
        }

        public Season season() {
            return this.season;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails, com.apollographql.apollo.api.GraphqlFragment
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsEpisode.$responseFields[0], AsEpisode.this.__typename);
                    responseWriter.writeList(AsEpisode.$responseFields[1], AsEpisode.this.previews, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Preview3) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsEpisode.$responseFields[2], AsEpisode.this.countries, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.1.2
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Country3) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsEpisode.$responseFields[3], AsEpisode.this.cast, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.1.3
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Cast3) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsEpisode.$responseFields[4], AsEpisode.this.crew, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.1.4
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Crew3) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeObject(AsEpisode.$responseFields[5], AsEpisode.this.offers.marshaller());
                    responseWriter.writeInt(AsEpisode.$responseFields[6], AsEpisode.this.episodeNumber);
                    responseWriter.writeObject(AsEpisode.$responseFields[7], AsEpisode.this.series.marshaller());
                    responseWriter.writeObject(AsEpisode.$responseFields[8], AsEpisode.this.season != null ? AsEpisode.this.season.marshaller() : null);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsEpisode{__typename=" + this.__typename + ", previews=" + this.previews + ", countries=" + this.countries + ", cast=" + this.cast + ", crew=" + this.crew + ", offers=" + this.offers + ", episodeNumber=" + this.episodeNumber + ", series=" + this.series + ", season=" + this.season + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            Integer num;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AsEpisode)) {
                return false;
            }
            AsEpisode asEpisode = (AsEpisode) obj;
            if (this.__typename.equals(asEpisode.__typename) && this.previews.equals(asEpisode.previews) && this.countries.equals(asEpisode.countries) && this.cast.equals(asEpisode.cast) && this.crew.equals(asEpisode.crew) && this.offers.equals(asEpisode.offers) && ((num = this.episodeNumber) != null ? num.equals(asEpisode.episodeNumber) : asEpisode.episodeNumber == null) && this.series.equals(asEpisode.series)) {
                Season season = this.season;
                Season season2 = asEpisode.season;
                if (season == null) {
                    if (season2 == null) {
                        return true;
                    }
                } else if (season.equals(season2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((((((((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.previews.hashCode()) * 1000003) ^ this.countries.hashCode()) * 1000003) ^ this.cast.hashCode()) * 1000003) ^ this.crew.hashCode()) * 1000003) ^ this.offers.hashCode()) * 1000003;
                Integer num = this.episodeNumber;
                int iHashCode2 = (((iHashCode ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.series.hashCode()) * 1000003;
                Season season = this.season;
                this.$hashCode = iHashCode2 ^ (season != null ? season.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsEpisode> {
            final Preview3.Mapper preview3FieldMapper = new Preview3.Mapper();
            final Country3.Mapper country3FieldMapper = new Country3.Mapper();
            final Cast3.Mapper cast3FieldMapper = new Cast3.Mapper();
            final Crew3.Mapper crew3FieldMapper = new Crew3.Mapper();
            final Offers3.Mapper offers3FieldMapper = new Offers3.Mapper();
            final Series1.Mapper series1FieldMapper = new Series1.Mapper();
            final Season.Mapper seasonFieldMapper = new Season.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsEpisode map(ResponseReader responseReader) {
                return new AsEpisode(responseReader.readString(AsEpisode.$responseFields[0]), responseReader.readList(AsEpisode.$responseFields[1], new ResponseReader.ListReader<Preview3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Preview3 read(ResponseReader.ListItemReader listItemReader) {
                        return (Preview3) listItemReader.readObject(new ResponseReader.ObjectReader<Preview3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Preview3 read(ResponseReader responseReader2) {
                                return Mapper.this.preview3FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsEpisode.$responseFields[2], new ResponseReader.ListReader<Country3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Country3 read(ResponseReader.ListItemReader listItemReader) {
                        return (Country3) listItemReader.readObject(new ResponseReader.ObjectReader<Country3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.2.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Country3 read(ResponseReader responseReader2) {
                                return Mapper.this.country3FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsEpisode.$responseFields[3], new ResponseReader.ListReader<Cast3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Cast3 read(ResponseReader.ListItemReader listItemReader) {
                        return (Cast3) listItemReader.readObject(new ResponseReader.ObjectReader<Cast3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.3.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Cast3 read(ResponseReader responseReader2) {
                                return Mapper.this.cast3FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsEpisode.$responseFields[4], new ResponseReader.ListReader<Crew3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.4
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Crew3 read(ResponseReader.ListItemReader listItemReader) {
                        return (Crew3) listItemReader.readObject(new ResponseReader.ObjectReader<Crew3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.4.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Crew3 read(ResponseReader responseReader2) {
                                return Mapper.this.crew3FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), (Offers3) responseReader.readObject(AsEpisode.$responseFields[5], new ResponseReader.ObjectReader<Offers3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.5
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Offers3 read(ResponseReader responseReader2) {
                        return Mapper.this.offers3FieldMapper.map(responseReader2);
                    }
                }), responseReader.readInt(AsEpisode.$responseFields[6]), (Series1) responseReader.readObject(AsEpisode.$responseFields[7], new ResponseReader.ObjectReader<Series1>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.6
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Series1 read(ResponseReader responseReader2) {
                        return Mapper.this.series1FieldMapper.map(responseReader2);
                    }
                }), (Season) responseReader.readObject(AsEpisode.$responseFields[8], new ResponseReader.ObjectReader<Season>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsEpisode.Mapper.7
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Season read(ResponseReader responseReader2) {
                        return Mapper.this.seasonFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Preview3 implements Preview {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString(TvContractCompat.Channels.COLUMN_DESCRIPTION, TvContractCompat.Channels.COLUMN_DESCRIPTION, null, true, Collections.emptyList()), ResponseField.forCustomType("streamId", "streamId", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String description;
        final String streamId;

        public Preview3(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.description = str2;
            this.streamId = (String) Utils.checkNotNull(str3, "streamId == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String description() {
            return this.description;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String streamId() {
            return this.streamId;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview3.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Preview3.$responseFields[0], Preview3.this.__typename);
                    responseWriter.writeString(Preview3.$responseFields[1], Preview3.this.description);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Preview3.$responseFields[2], Preview3.this.streamId);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Preview3{__typename=" + this.__typename + ", description=" + this.description + ", streamId=" + this.streamId + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Preview3)) {
                return false;
            }
            Preview3 preview3 = (Preview3) obj;
            return this.__typename.equals(preview3.__typename) && ((str = this.description) != null ? str.equals(preview3.description) : preview3.description == null) && this.streamId.equals(preview3.streamId);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                String str = this.description;
                this.$hashCode = ((iHashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.streamId.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Preview3> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Preview3 map(ResponseReader responseReader) {
                return new Preview3(responseReader.readString(Preview3.$responseFields[0]), responseReader.readString(Preview3.$responseFields[1]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Preview3.$responseFields[2]));
            }
        }
    }

    public static class Country3 implements Country {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList()), ResponseField.forString("isoCode", "isoCode", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String isoCode;
        final String name;

        public Country3(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.name = (String) Utils.checkNotNull(str2, "name == null");
            this.isoCode = (String) Utils.checkNotNull(str3, "isoCode == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String isoCode() {
            return this.isoCode;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Country3.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Country3.$responseFields[0], Country3.this.__typename);
                    responseWriter.writeString(Country3.$responseFields[1], Country3.this.name);
                    responseWriter.writeString(Country3.$responseFields[2], Country3.this.isoCode);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Country3{__typename=" + this.__typename + ", name=" + this.name + ", isoCode=" + this.isoCode + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Country3)) {
                return false;
            }
            Country3 country3 = (Country3) obj;
            return this.__typename.equals(country3.__typename) && this.name.equals(country3.name) && this.isoCode.equals(country3.isoCode);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.isoCode.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Country3> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Country3 map(ResponseReader responseReader) {
                return new Country3(responseReader.readString(Country3.$responseFields[0]), responseReader.readString(Country3.$responseFields[1]), responseReader.readString(Country3.$responseFields[2]));
            }
        }
    }

    public static class Cast3 implements Cast {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("person", "person", null, false, Collections.emptyList()), ResponseField.forString("roleType", "roleType", null, true, Collections.emptyList()), ResponseField.forString("role", "role", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Person6 person;
        final String role;
        final RoleCategory roleType;

        public Cast3(String str, Person6 person6, RoleCategory roleCategory, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.person = (Person6) Utils.checkNotNull(person6, "person == null");
            this.roleType = roleCategory;
            this.role = str2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public Person6 person() {
            return this.person;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public RoleCategory roleType() {
            return this.roleType;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public String role() {
            return this.role;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast3.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Cast3.$responseFields[0], Cast3.this.__typename);
                    responseWriter.writeObject(Cast3.$responseFields[1], Cast3.this.person.marshaller());
                    responseWriter.writeString(Cast3.$responseFields[2], Cast3.this.roleType != null ? Cast3.this.roleType.rawValue() : null);
                    responseWriter.writeString(Cast3.$responseFields[3], Cast3.this.role);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Cast3{__typename=" + this.__typename + ", person=" + this.person + ", roleType=" + this.roleType + ", role=" + this.role + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            RoleCategory roleCategory;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Cast3)) {
                return false;
            }
            Cast3 cast3 = (Cast3) obj;
            if (this.__typename.equals(cast3.__typename) && this.person.equals(cast3.person) && ((roleCategory = this.roleType) != null ? roleCategory.equals(cast3.roleType) : cast3.roleType == null)) {
                String str = this.role;
                String str2 = cast3.role;
                if (str == null) {
                    if (str2 == null) {
                        return true;
                    }
                } else if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.person.hashCode()) * 1000003;
                RoleCategory roleCategory = this.roleType;
                int iHashCode2 = (iHashCode ^ (roleCategory == null ? 0 : roleCategory.hashCode())) * 1000003;
                String str = this.role;
                this.$hashCode = iHashCode2 ^ (str != null ? str.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Cast3> {
            final Person6.Mapper person6FieldMapper = new Person6.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Cast3 map(ResponseReader responseReader) {
                String string = responseReader.readString(Cast3.$responseFields[0]);
                Person6 person6 = (Person6) responseReader.readObject(Cast3.$responseFields[1], new ResponseReader.ObjectReader<Person6>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast3.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Person6 read(ResponseReader responseReader2) {
                        return Mapper.this.person6FieldMapper.map(responseReader2);
                    }
                });
                String string2 = responseReader.readString(Cast3.$responseFields[2]);
                return new Cast3(string, person6, string2 != null ? RoleCategory.safeValueOf(string2) : null, responseReader.readString(Cast3.$responseFields[3]));
            }
        }
    }

    public static class Person6 implements Person {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public Person6(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Person6.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Person6.$responseFields[0], Person6.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Person6.$responseFields[1], Person6.this.id);
                    responseWriter.writeString(Person6.$responseFields[2], Person6.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Person6{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person6)) {
                return false;
            }
            Person6 person6 = (Person6) obj;
            return this.__typename.equals(person6.__typename) && this.id.equals(person6.id) && this.name.equals(person6.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Person6> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Person6 map(ResponseReader responseReader) {
                return new Person6(responseReader.readString(Person6.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Person6.$responseFields[1]), responseReader.readString(Person6.$responseFields[2]));
            }
        }
    }

    public static class Crew3 implements Crew {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("person", "person", null, false, Collections.emptyList()), ResponseField.forString("department", "department", null, true, Collections.emptyList()), ResponseField.forString("task", "task", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final CrewDepartment department;
        final Person7 person;
        final String task;

        public Crew3(String str, Person7 person7, CrewDepartment crewDepartment, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.person = (Person7) Utils.checkNotNull(person7, "person == null");
            this.department = crewDepartment;
            this.task = str2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public Person7 person() {
            return this.person;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public CrewDepartment department() {
            return this.department;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public String task() {
            return this.task;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew3.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Crew3.$responseFields[0], Crew3.this.__typename);
                    responseWriter.writeObject(Crew3.$responseFields[1], Crew3.this.person.marshaller());
                    responseWriter.writeString(Crew3.$responseFields[2], Crew3.this.department != null ? Crew3.this.department.rawValue() : null);
                    responseWriter.writeString(Crew3.$responseFields[3], Crew3.this.task);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Crew3{__typename=" + this.__typename + ", person=" + this.person + ", department=" + this.department + ", task=" + this.task + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            CrewDepartment crewDepartment;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Crew3)) {
                return false;
            }
            Crew3 crew3 = (Crew3) obj;
            if (this.__typename.equals(crew3.__typename) && this.person.equals(crew3.person) && ((crewDepartment = this.department) != null ? crewDepartment.equals(crew3.department) : crew3.department == null)) {
                String str = this.task;
                String str2 = crew3.task;
                if (str == null) {
                    if (str2 == null) {
                        return true;
                    }
                } else if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.person.hashCode()) * 1000003;
                CrewDepartment crewDepartment = this.department;
                int iHashCode2 = (iHashCode ^ (crewDepartment == null ? 0 : crewDepartment.hashCode())) * 1000003;
                String str = this.task;
                this.$hashCode = iHashCode2 ^ (str != null ? str.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Crew3> {
            final Person7.Mapper person7FieldMapper = new Person7.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Crew3 map(ResponseReader responseReader) {
                String string = responseReader.readString(Crew3.$responseFields[0]);
                Person7 person7 = (Person7) responseReader.readObject(Crew3.$responseFields[1], new ResponseReader.ObjectReader<Person7>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew3.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Person7 read(ResponseReader responseReader2) {
                        return Mapper.this.person7FieldMapper.map(responseReader2);
                    }
                });
                String string2 = responseReader.readString(Crew3.$responseFields[2]);
                return new Crew3(string, person7, string2 != null ? CrewDepartment.safeValueOf(string2) : null, responseReader.readString(Crew3.$responseFields[3]));
            }
        }
    }

    public static class Person7 implements Person1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public Person7(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Person7.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Person7.$responseFields[0], Person7.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Person7.$responseFields[1], Person7.this.id);
                    responseWriter.writeString(Person7.$responseFields[2], Person7.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Person7{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person7)) {
                return false;
            }
            Person7 person7 = (Person7) obj;
            return this.__typename.equals(person7.__typename) && this.id.equals(person7.id) && this.name.equals(person7.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Person7> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Person7 map(ResponseReader responseReader) {
                return new Person7(responseReader.readString(Person7.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Person7.$responseFields[1]), responseReader.readString(Person7.$responseFields[2]));
            }
        }
    }

    public static class Offers3 implements Offers {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("items", "items", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Item3> items;

        public Offers3(String str, List<Item3> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.items = (List) Utils.checkNotNull(list, "items == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public List<Item3> items() {
            return this.items;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers3.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Offers3.$responseFields[0], Offers3.this.__typename);
                    responseWriter.writeList(Offers3.$responseFields[1], Offers3.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers3.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Item3) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Offers3{__typename=" + this.__typename + ", items=" + this.items + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Offers3)) {
                return false;
            }
            Offers3 offers3 = (Offers3) obj;
            return this.__typename.equals(offers3.__typename) && this.items.equals(offers3.items);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.items.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Offers3> {
            final Item3.Mapper item3FieldMapper = new Item3.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Offers3 map(ResponseReader responseReader) {
                return new Offers3(responseReader.readString(Offers3.$responseFields[0]), responseReader.readList(Offers3.$responseFields[1], new ResponseReader.ListReader<Item3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers3.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item3 read(ResponseReader.ListItemReader listItemReader) {
                        return (Item3) listItemReader.readObject(new ResponseReader.ObjectReader<Item3>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers3.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Item3 read(ResponseReader responseReader2) {
                                return Mapper.this.item3FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class Item3 implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("audioLanguages", "audioLanguages", null, true, Collections.emptyList()), ResponseField.forList("subtitlesLanguages", "subtitlesLanguages", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<LanguageCode> audioLanguages;
        final List<LanguageCode> subtitlesLanguages;

        public Item3(String str, List<LanguageCode> list, List<LanguageCode> list2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.audioLanguages = list;
            this.subtitlesLanguages = list2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public List<LanguageCode> audioLanguages() {
            return this.audioLanguages;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public List<LanguageCode> subtitlesLanguages() {
            return this.subtitlesLanguages;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item3.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Item3.$responseFields[0], Item3.this.__typename);
                    responseWriter.writeList(Item3.$responseFields[1], Item3.this.audioLanguages, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item3.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString(((LanguageCode) it.next()).rawValue());
                            }
                        }
                    });
                    responseWriter.writeList(Item3.$responseFields[2], Item3.this.subtitlesLanguages, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item3.1.2
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString(((LanguageCode) it.next()).rawValue());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Item3{__typename=" + this.__typename + ", audioLanguages=" + this.audioLanguages + ", subtitlesLanguages=" + this.subtitlesLanguages + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            List<LanguageCode> list;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Item3)) {
                return false;
            }
            Item3 item3 = (Item3) obj;
            if (this.__typename.equals(item3.__typename) && ((list = this.audioLanguages) != null ? list.equals(item3.audioLanguages) : item3.audioLanguages == null)) {
                List<LanguageCode> list2 = this.subtitlesLanguages;
                List<LanguageCode> list3 = item3.subtitlesLanguages;
                if (list2 == null) {
                    if (list3 == null) {
                        return true;
                    }
                } else if (list2.equals(list3)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                List<LanguageCode> list = this.audioLanguages;
                int iHashCode2 = (iHashCode ^ (list == null ? 0 : list.hashCode())) * 1000003;
                List<LanguageCode> list2 = this.subtitlesLanguages;
                this.$hashCode = iHashCode2 ^ (list2 != null ? list2.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Item3> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Item3 map(ResponseReader responseReader) {
                return new Item3(responseReader.readString(Item3.$responseFields[0]), responseReader.readList(Item3.$responseFields[1], new ResponseReader.ListReader<LanguageCode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item3.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LanguageCode read(ResponseReader.ListItemReader listItemReader) {
                        return LanguageCode.safeValueOf(listItemReader.readString());
                    }
                }), responseReader.readList(Item3.$responseFields[2], new ResponseReader.ListReader<LanguageCode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item3.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LanguageCode read(ResponseReader.ListItemReader listItemReader) {
                        return LanguageCode.safeValueOf(listItemReader.readString());
                    }
                }));
            }
        }
    }

    public static class Series1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;

        public Series1(String str, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String id() {
            return this.id;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Series1.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Series1.$responseFields[0], Series1.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Series1.$responseFields[1], Series1.this.id);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Series1{__typename=" + this.__typename + ", id=" + this.id + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Series1)) {
                return false;
            }
            Series1 series1 = (Series1) obj;
            return this.__typename.equals(series1.__typename) && this.id.equals(series1.id);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Series1> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Series1 map(ResponseReader responseReader) {
                return new Series1(responseReader.readString(Series1.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Series1.$responseFields[1]));
            }
        }
    }

    public static class Season {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;

        public Season(String str, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String id() {
            return this.id;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Season.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Season.$responseFields[0], Season.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Season.$responseFields[1], Season.this.id);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Season{__typename=" + this.__typename + ", id=" + this.id + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Season)) {
                return false;
            }
            Season season = (Season) obj;
            return this.__typename.equals(season.__typename) && this.id.equals(season.id);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Season> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Season map(ResponseReader responseReader) {
                return new Season(responseReader.readString(Season.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Season.$responseFields[1]));
            }
        }
    }

    public static class AsContent implements ContentFragmentDetails {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("previews", "previews", null, false, Collections.emptyList()), ResponseField.forList("countries", "countries", null, false, Collections.emptyList()), ResponseField.forList("cast", "cast", null, false, Collections.emptyList()), ResponseField.forList("crew", "crew", null, false, Collections.emptyList()), ResponseField.forObject("offers", "offers", new UnmodifiableMapBuilder(1).put("linear", false).build(), false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Cast4> cast;
        final List<Country4> countries;
        final List<Crew4> crew;
        final Offers4 offers;
        final List<Preview4> previews;

        public AsContent(String str, List<Preview4> list, List<Country4> list2, List<Cast4> list3, List<Crew4> list4, Offers4 offers4) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.previews = (List) Utils.checkNotNull(list, "previews == null");
            this.countries = (List) Utils.checkNotNull(list2, "countries == null");
            this.cast = (List) Utils.checkNotNull(list3, "cast == null");
            this.crew = (List) Utils.checkNotNull(list4, "crew == null");
            this.offers = (Offers4) Utils.checkNotNull(offers4, "offers == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Preview4> previews() {
            return this.previews;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Country4> countries() {
            return this.countries;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Cast4> cast() {
            return this.cast;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public List<Crew4> crew() {
            return this.crew;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails
        public Offers4 offers() {
            return this.offers;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails, com.apollographql.apollo.api.GraphqlFragment
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AsContent.$responseFields[0], AsContent.this.__typename);
                    responseWriter.writeList(AsContent.$responseFields[1], AsContent.this.previews, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Preview4) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsContent.$responseFields[2], AsContent.this.countries, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.1.2
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Country4) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsContent.$responseFields[3], AsContent.this.cast, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.1.3
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Cast4) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeList(AsContent.$responseFields[4], AsContent.this.crew, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.1.4
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Crew4) it.next()).marshaller());
                            }
                        }
                    });
                    responseWriter.writeObject(AsContent.$responseFields[5], AsContent.this.offers.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AsContent{__typename=" + this.__typename + ", previews=" + this.previews + ", countries=" + this.countries + ", cast=" + this.cast + ", crew=" + this.crew + ", offers=" + this.offers + "}";
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
            return this.__typename.equals(asContent.__typename) && this.previews.equals(asContent.previews) && this.countries.equals(asContent.countries) && this.cast.equals(asContent.cast) && this.crew.equals(asContent.crew) && this.offers.equals(asContent.offers);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((((((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.previews.hashCode()) * 1000003) ^ this.countries.hashCode()) * 1000003) ^ this.cast.hashCode()) * 1000003) ^ this.crew.hashCode()) * 1000003) ^ this.offers.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AsContent> {
            final Preview4.Mapper preview4FieldMapper = new Preview4.Mapper();
            final Country4.Mapper country4FieldMapper = new Country4.Mapper();
            final Cast4.Mapper cast4FieldMapper = new Cast4.Mapper();
            final Crew4.Mapper crew4FieldMapper = new Crew4.Mapper();
            final Offers4.Mapper offers4FieldMapper = new Offers4.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AsContent map(ResponseReader responseReader) {
                return new AsContent(responseReader.readString(AsContent.$responseFields[0]), responseReader.readList(AsContent.$responseFields[1], new ResponseReader.ListReader<Preview4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Preview4 read(ResponseReader.ListItemReader listItemReader) {
                        return (Preview4) listItemReader.readObject(new ResponseReader.ObjectReader<Preview4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Preview4 read(ResponseReader responseReader2) {
                                return Mapper.this.preview4FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsContent.$responseFields[2], new ResponseReader.ListReader<Country4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Country4 read(ResponseReader.ListItemReader listItemReader) {
                        return (Country4) listItemReader.readObject(new ResponseReader.ObjectReader<Country4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.2.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Country4 read(ResponseReader responseReader2) {
                                return Mapper.this.country4FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsContent.$responseFields[3], new ResponseReader.ListReader<Cast4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Cast4 read(ResponseReader.ListItemReader listItemReader) {
                        return (Cast4) listItemReader.readObject(new ResponseReader.ObjectReader<Cast4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.3.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Cast4 read(ResponseReader responseReader2) {
                                return Mapper.this.cast4FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), responseReader.readList(AsContent.$responseFields[4], new ResponseReader.ListReader<Crew4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.4
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Crew4 read(ResponseReader.ListItemReader listItemReader) {
                        return (Crew4) listItemReader.readObject(new ResponseReader.ObjectReader<Crew4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.4.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Crew4 read(ResponseReader responseReader2) {
                                return Mapper.this.crew4FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }), (Offers4) responseReader.readObject(AsContent.$responseFields[5], new ResponseReader.ObjectReader<Offers4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.AsContent.Mapper.5
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Offers4 read(ResponseReader responseReader2) {
                        return Mapper.this.offers4FieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class Preview4 implements Preview {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString(TvContractCompat.Channels.COLUMN_DESCRIPTION, TvContractCompat.Channels.COLUMN_DESCRIPTION, null, true, Collections.emptyList()), ResponseField.forCustomType("streamId", "streamId", null, false, CustomType.ID, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String description;
        final String streamId;

        public Preview4(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.description = str2;
            this.streamId = (String) Utils.checkNotNull(str3, "streamId == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String description() {
            return this.description;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public String streamId() {
            return this.streamId;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Preview4.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Preview4.$responseFields[0], Preview4.this.__typename);
                    responseWriter.writeString(Preview4.$responseFields[1], Preview4.this.description);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Preview4.$responseFields[2], Preview4.this.streamId);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Preview4{__typename=" + this.__typename + ", description=" + this.description + ", streamId=" + this.streamId + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Preview4)) {
                return false;
            }
            Preview4 preview4 = (Preview4) obj;
            return this.__typename.equals(preview4.__typename) && ((str = this.description) != null ? str.equals(preview4.description) : preview4.description == null) && this.streamId.equals(preview4.streamId);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                String str = this.description;
                this.$hashCode = ((iHashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.streamId.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Preview4> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Preview4 map(ResponseReader responseReader) {
                return new Preview4(responseReader.readString(Preview4.$responseFields[0]), responseReader.readString(Preview4.$responseFields[1]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Preview4.$responseFields[2]));
            }
        }
    }

    public static class Country4 implements Country {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList()), ResponseField.forString("isoCode", "isoCode", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String isoCode;
        final String name;

        public Country4(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.name = (String) Utils.checkNotNull(str2, "name == null");
            this.isoCode = (String) Utils.checkNotNull(str3, "isoCode == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public String isoCode() {
            return this.isoCode;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Country
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Country4.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Country4.$responseFields[0], Country4.this.__typename);
                    responseWriter.writeString(Country4.$responseFields[1], Country4.this.name);
                    responseWriter.writeString(Country4.$responseFields[2], Country4.this.isoCode);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Country4{__typename=" + this.__typename + ", name=" + this.name + ", isoCode=" + this.isoCode + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Country4)) {
                return false;
            }
            Country4 country4 = (Country4) obj;
            return this.__typename.equals(country4.__typename) && this.name.equals(country4.name) && this.isoCode.equals(country4.isoCode);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.isoCode.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Country4> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Country4 map(ResponseReader responseReader) {
                return new Country4(responseReader.readString(Country4.$responseFields[0]), responseReader.readString(Country4.$responseFields[1]), responseReader.readString(Country4.$responseFields[2]));
            }
        }
    }

    public static class Cast4 implements Cast {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("person", "person", null, false, Collections.emptyList()), ResponseField.forString("roleType", "roleType", null, true, Collections.emptyList()), ResponseField.forString("role", "role", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final Person8 person;
        final String role;
        final RoleCategory roleType;

        public Cast4(String str, Person8 person8, RoleCategory roleCategory, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.person = (Person8) Utils.checkNotNull(person8, "person == null");
            this.roleType = roleCategory;
            this.role = str2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public Person8 person() {
            return this.person;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public RoleCategory roleType() {
            return this.roleType;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public String role() {
            return this.role;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast4.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Cast4.$responseFields[0], Cast4.this.__typename);
                    responseWriter.writeObject(Cast4.$responseFields[1], Cast4.this.person.marshaller());
                    responseWriter.writeString(Cast4.$responseFields[2], Cast4.this.roleType != null ? Cast4.this.roleType.rawValue() : null);
                    responseWriter.writeString(Cast4.$responseFields[3], Cast4.this.role);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Cast4{__typename=" + this.__typename + ", person=" + this.person + ", roleType=" + this.roleType + ", role=" + this.role + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            RoleCategory roleCategory;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Cast4)) {
                return false;
            }
            Cast4 cast4 = (Cast4) obj;
            if (this.__typename.equals(cast4.__typename) && this.person.equals(cast4.person) && ((roleCategory = this.roleType) != null ? roleCategory.equals(cast4.roleType) : cast4.roleType == null)) {
                String str = this.role;
                String str2 = cast4.role;
                if (str == null) {
                    if (str2 == null) {
                        return true;
                    }
                } else if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.person.hashCode()) * 1000003;
                RoleCategory roleCategory = this.roleType;
                int iHashCode2 = (iHashCode ^ (roleCategory == null ? 0 : roleCategory.hashCode())) * 1000003;
                String str = this.role;
                this.$hashCode = iHashCode2 ^ (str != null ? str.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Cast4> {
            final Person8.Mapper person8FieldMapper = new Person8.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Cast4 map(ResponseReader responseReader) {
                String string = responseReader.readString(Cast4.$responseFields[0]);
                Person8 person8 = (Person8) responseReader.readObject(Cast4.$responseFields[1], new ResponseReader.ObjectReader<Person8>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Cast4.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Person8 read(ResponseReader responseReader2) {
                        return Mapper.this.person8FieldMapper.map(responseReader2);
                    }
                });
                String string2 = responseReader.readString(Cast4.$responseFields[2]);
                return new Cast4(string, person8, string2 != null ? RoleCategory.safeValueOf(string2) : null, responseReader.readString(Cast4.$responseFields[3]));
            }
        }
    }

    public static class Person8 implements Person {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public Person8(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Person8.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Person8.$responseFields[0], Person8.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Person8.$responseFields[1], Person8.this.id);
                    responseWriter.writeString(Person8.$responseFields[2], Person8.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Person8{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person8)) {
                return false;
            }
            Person8 person8 = (Person8) obj;
            return this.__typename.equals(person8.__typename) && this.id.equals(person8.id) && this.name.equals(person8.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Person8> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Person8 map(ResponseReader responseReader) {
                return new Person8(responseReader.readString(Person8.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Person8.$responseFields[1]), responseReader.readString(Person8.$responseFields[2]));
            }
        }
    }

    public static class Crew4 implements Crew {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forObject("person", "person", null, false, Collections.emptyList()), ResponseField.forString("department", "department", null, true, Collections.emptyList()), ResponseField.forString("task", "task", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final CrewDepartment department;
        final Person9 person;
        final String task;

        public Crew4(String str, Person9 person9, CrewDepartment crewDepartment, String str2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.person = (Person9) Utils.checkNotNull(person9, "person == null");
            this.department = crewDepartment;
            this.task = str2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public Person9 person() {
            return this.person;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public CrewDepartment department() {
            return this.department;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public String task() {
            return this.task;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew4.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Crew4.$responseFields[0], Crew4.this.__typename);
                    responseWriter.writeObject(Crew4.$responseFields[1], Crew4.this.person.marshaller());
                    responseWriter.writeString(Crew4.$responseFields[2], Crew4.this.department != null ? Crew4.this.department.rawValue() : null);
                    responseWriter.writeString(Crew4.$responseFields[3], Crew4.this.task);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Crew4{__typename=" + this.__typename + ", person=" + this.person + ", department=" + this.department + ", task=" + this.task + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            CrewDepartment crewDepartment;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Crew4)) {
                return false;
            }
            Crew4 crew4 = (Crew4) obj;
            if (this.__typename.equals(crew4.__typename) && this.person.equals(crew4.person) && ((crewDepartment = this.department) != null ? crewDepartment.equals(crew4.department) : crew4.department == null)) {
                String str = this.task;
                String str2 = crew4.task;
                if (str == null) {
                    if (str2 == null) {
                        return true;
                    }
                } else if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.person.hashCode()) * 1000003;
                CrewDepartment crewDepartment = this.department;
                int iHashCode2 = (iHashCode ^ (crewDepartment == null ? 0 : crewDepartment.hashCode())) * 1000003;
                String str = this.task;
                this.$hashCode = iHashCode2 ^ (str != null ? str.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Crew4> {
            final Person9.Mapper person9FieldMapper = new Person9.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Crew4 map(ResponseReader responseReader) {
                String string = responseReader.readString(Crew4.$responseFields[0]);
                Person9 person9 = (Person9) responseReader.readObject(Crew4.$responseFields[1], new ResponseReader.ObjectReader<Person9>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Crew4.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Person9 read(ResponseReader responseReader2) {
                        return Mapper.this.person9FieldMapper.map(responseReader2);
                    }
                });
                String string2 = responseReader.readString(Crew4.$responseFields[2]);
                return new Crew4(string, person9, string2 != null ? CrewDepartment.safeValueOf(string2) : null, responseReader.readString(Crew4.$responseFields[3]));
            }
        }
    }

    public static class Person9 implements Person1 {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forString("name", "name", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final String name;

        public Person9(String str, String str2, String str3) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.name = (String) Utils.checkNotNull(str3, "name == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String id() {
            return this.id;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Person1
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Person9.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Person9.$responseFields[0], Person9.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Person9.$responseFields[1], Person9.this.id);
                    responseWriter.writeString(Person9.$responseFields[2], Person9.this.name);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Person9{__typename=" + this.__typename + ", id=" + this.id + ", name=" + this.name + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person9)) {
                return false;
            }
            Person9 person9 = (Person9) obj;
            return this.__typename.equals(person9.__typename) && this.id.equals(person9.id) && this.name.equals(person9.name);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.name.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Person9> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Person9 map(ResponseReader responseReader) {
                return new Person9(responseReader.readString(Person9.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Person9.$responseFields[1]), responseReader.readString(Person9.$responseFields[2]));
            }
        }
    }

    public static class Offers4 implements Offers {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("items", "items", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Item4> items;

        public Offers4(String str, List<Item4> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.items = (List) Utils.checkNotNull(list, "items == null");
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public List<Item4> items() {
            return this.items;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers4.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Offers4.$responseFields[0], Offers4.this.__typename);
                    responseWriter.writeList(Offers4.$responseFields[1], Offers4.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers4.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Item4) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Offers4{__typename=" + this.__typename + ", items=" + this.items + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Offers4)) {
                return false;
            }
            Offers4 offers4 = (Offers4) obj;
            return this.__typename.equals(offers4.__typename) && this.items.equals(offers4.items);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.items.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Offers4> {
            final Item4.Mapper item4FieldMapper = new Item4.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Offers4 map(ResponseReader responseReader) {
                return new Offers4(responseReader.readString(Offers4.$responseFields[0]), responseReader.readList(Offers4.$responseFields[1], new ResponseReader.ListReader<Item4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers4.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item4 read(ResponseReader.ListItemReader listItemReader) {
                        return (Item4) listItemReader.readObject(new ResponseReader.ObjectReader<Item4>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Offers4.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Item4 read(ResponseReader responseReader2) {
                                return Mapper.this.item4FieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class Item4 implements Item {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("audioLanguages", "audioLanguages", null, true, Collections.emptyList()), ResponseField.forList("subtitlesLanguages", "subtitlesLanguages", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<LanguageCode> audioLanguages;
        final List<LanguageCode> subtitlesLanguages;

        public Item4(String str, List<LanguageCode> list, List<LanguageCode> list2) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.audioLanguages = list;
            this.subtitlesLanguages = list2;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public List<LanguageCode> audioLanguages() {
            return this.audioLanguages;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public List<LanguageCode> subtitlesLanguages() {
            return this.subtitlesLanguages;
        }

        @Override // com.dotscreen.bokit.fragment.ContentFragmentDetails.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item4.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Item4.$responseFields[0], Item4.this.__typename);
                    responseWriter.writeList(Item4.$responseFields[1], Item4.this.audioLanguages, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item4.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString(((LanguageCode) it.next()).rawValue());
                            }
                        }
                    });
                    responseWriter.writeList(Item4.$responseFields[2], Item4.this.subtitlesLanguages, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item4.1.2
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeString(((LanguageCode) it.next()).rawValue());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Item4{__typename=" + this.__typename + ", audioLanguages=" + this.audioLanguages + ", subtitlesLanguages=" + this.subtitlesLanguages + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            List<LanguageCode> list;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Item4)) {
                return false;
            }
            Item4 item4 = (Item4) obj;
            if (this.__typename.equals(item4.__typename) && ((list = this.audioLanguages) != null ? list.equals(item4.audioLanguages) : item4.audioLanguages == null)) {
                List<LanguageCode> list2 = this.subtitlesLanguages;
                List<LanguageCode> list3 = item4.subtitlesLanguages;
                if (list2 == null) {
                    if (list3 == null) {
                        return true;
                    }
                } else if (list2.equals(list3)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                int iHashCode = (this.__typename.hashCode() ^ 1000003) * 1000003;
                List<LanguageCode> list = this.audioLanguages;
                int iHashCode2 = (iHashCode ^ (list == null ? 0 : list.hashCode())) * 1000003;
                List<LanguageCode> list2 = this.subtitlesLanguages;
                this.$hashCode = iHashCode2 ^ (list2 != null ? list2.hashCode() : 0);
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Item4> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Item4 map(ResponseReader responseReader) {
                return new Item4(responseReader.readString(Item4.$responseFields[0]), responseReader.readList(Item4.$responseFields[1], new ResponseReader.ListReader<LanguageCode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item4.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LanguageCode read(ResponseReader.ListItemReader listItemReader) {
                        return LanguageCode.safeValueOf(listItemReader.readString());
                    }
                }), responseReader.readList(Item4.$responseFields[2], new ResponseReader.ListReader<LanguageCode>() { // from class: com.dotscreen.bokit.fragment.ContentFragmentDetails.Item4.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public LanguageCode read(ResponseReader.ListItemReader listItemReader) {
                        return LanguageCode.safeValueOf(listItemReader.readString());
                    }
                }));
            }
        }
    }
}
