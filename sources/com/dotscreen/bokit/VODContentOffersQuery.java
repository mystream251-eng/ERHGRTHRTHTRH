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
import com.dotscreen.bokit.type.CurrencyCode;
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
public final class VODContentOffersQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "f59d2c53b49473631dc9c3497adaea02f093876bec3897b0cea9f47ba480074a";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODContentOffers($contentId: ID!) {\n  lookupContent(contentId: $contentId) {\n    __typename\n    id\n    offers(linear: false) {\n      __typename\n      items {\n        __typename\n        ... on TransactionalVOD {\n          streamId\n          name\n          price {\n            __typename\n            amount\n            currency\n          }\n        }\n        ... on SubscriptionVOD {\n          streamId\n          name\n        }\n        ... on CatchUpTV {\n          streamId\n          name\n        }\n        ... on ExternalOffer {\n          name\n          location\n          __typename\n        }\n      }\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODContentOffersQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODContentOffers";
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

    public VODContentOffersQuery(String str) {
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

        public VODContentOffersQuery build() {
            Utils.checkNotNull(this.contentId, "contentId == null");
            return new VODContentOffersQuery(this.contentId);
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
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Variables.1
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Data.1
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
                return new Data((LookupContent) responseReader.readObject(Data.$responseFields[0], new ResponseReader.ObjectReader<LookupContent>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Data.Mapper.1
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
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.emptyList()), ResponseField.forObject("offers", "offers", new UnmodifiableMapBuilder(1).put("linear", false).build(), false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String id;
        final Offers offers;

        public LookupContent(String str, String str2, Offers offers) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.id = (String) Utils.checkNotNull(str2, "id == null");
            this.offers = (Offers) Utils.checkNotNull(offers, "offers == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public String id() {
            return this.id;
        }

        public Offers offers() {
            return this.offers;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.LookupContent.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(LookupContent.$responseFields[0], LookupContent.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) LookupContent.$responseFields[1], LookupContent.this.id);
                    responseWriter.writeObject(LookupContent.$responseFields[2], LookupContent.this.offers.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "LookupContent{__typename=" + this.__typename + ", id=" + this.id + ", offers=" + this.offers + "}";
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
            return this.__typename.equals(lookupContent.__typename) && this.id.equals(lookupContent.id) && this.offers.equals(lookupContent.offers);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.offers.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<LookupContent> {
            final Offers.Mapper offersFieldMapper = new Offers.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public LookupContent map(ResponseReader responseReader) {
                return new LookupContent(responseReader.readString(LookupContent.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) LookupContent.$responseFields[1]), (Offers) responseReader.readObject(LookupContent.$responseFields[2], new ResponseReader.ObjectReader<Offers>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.LookupContent.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public Offers read(ResponseReader responseReader2) {
                        return Mapper.this.offersFieldMapper.map(responseReader2);
                    }
                }));
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Offers.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Offers.$responseFields[0], Offers.this.__typename);
                    responseWriter.writeList(Offers.$responseFields[1], Offers.this.items, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Offers.1.1
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
                return new Offers(responseReader.readString(Offers.$responseFields[0]), responseReader.readList(Offers.$responseFields[1], new ResponseReader.ListReader<Item>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Offers.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Item read(ResponseReader.ListItemReader listItemReader) {
                        return (Item) listItemReader.readObject(new ResponseReader.ObjectReader<Item>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Offers.Mapper.1.1
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
                AsTransactionalVOD asTransactionalVOD = (AsTransactionalVOD) responseReader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsTransactionalVOD>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Item.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsTransactionalVOD read(ResponseReader responseReader2) {
                        return Mapper.this.asTransactionalVODFieldMapper.map(responseReader2);
                    }
                });
                if (asTransactionalVOD != null) {
                    return asTransactionalVOD;
                }
                AsSubscriptionVOD asSubscriptionVOD = (AsSubscriptionVOD) responseReader.readFragment($responseFields[1], new ResponseReader.ObjectReader<AsSubscriptionVOD>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Item.Mapper.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsSubscriptionVOD read(ResponseReader responseReader2) {
                        return Mapper.this.asSubscriptionVODFieldMapper.map(responseReader2);
                    }
                });
                if (asSubscriptionVOD != null) {
                    return asSubscriptionVOD;
                }
                AsCatchUpTV asCatchUpTV = (AsCatchUpTV) responseReader.readFragment($responseFields[2], new ResponseReader.ObjectReader<AsCatchUpTV>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Item.Mapper.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AsCatchUpTV read(ResponseReader responseReader2) {
                        return Mapper.this.asCatchUpTVFieldMapper.map(responseReader2);
                    }
                });
                if (asCatchUpTV != null) {
                    return asCatchUpTV;
                }
                AsExternalOffer asExternalOffer = (AsExternalOffer) responseReader.readFragment($responseFields[3], new ResponseReader.ObjectReader<AsExternalOffer>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Item.Mapper.4
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

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
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

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.AsTransactionalVOD.1
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
                return new AsTransactionalVOD(responseReader.readString(AsTransactionalVOD.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) AsTransactionalVOD.$responseFields[1]), responseReader.readString(AsTransactionalVOD.$responseFields[2]), (Price) responseReader.readObject(AsTransactionalVOD.$responseFields[3], new ResponseReader.ObjectReader<Price>() { // from class: com.dotscreen.bokit.VODContentOffersQuery.AsTransactionalVOD.Mapper.1
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
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.Price.1
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

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public String __typename() {
            return this.__typename;
        }

        public String streamId() {
            return this.streamId;
        }

        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.AsSubscriptionVOD.1
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

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public String __typename() {
            return this.__typename;
        }

        public String streamId() {
            return this.streamId;
        }

        public String name() {
            return this.name;
        }

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.AsCatchUpTV.1
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

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public String __typename() {
            return this.__typename;
        }

        public String name() {
            return this.name;
        }

        public String location() {
            return this.location;
        }

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.AsExternalOffer.1
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

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public String __typename() {
            return this.__typename;
        }

        @Override // com.dotscreen.bokit.VODContentOffersQuery.Item
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODContentOffersQuery.AsOffer.1
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
