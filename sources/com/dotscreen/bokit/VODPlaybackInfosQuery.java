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
public final class VODPlaybackInfosQuery implements Query<Data, Data, Variables> {
    public static final String OPERATION_ID = "3adb119aaf31bb1a7b1bcc9ee35363fec01a2907f0b6d8ce6a7475a0ad89e256";
    private final Variables variables;
    public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify("query VODPlaybackInfos($streamId: ID!, $ticketId: ID) {\n  authorizeStream(streamId: $streamId, ticket: $ticketId) {\n    __typename\n    options {\n      __typename\n      source\n      contentType\n      privateData\n    }\n  }\n}");
    public static final OperationName OPERATION_NAME = new OperationName() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.1
        @Override // com.apollographql.apollo.api.OperationName
        public String name() {
            return "VODPlaybackInfos";
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

    public VODPlaybackInfosQuery(String str, Input<String> input) {
        Utils.checkNotNull(str, "streamId == null");
        Utils.checkNotNull(input, "ticketId == null");
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
        private String streamId;
        private Input<String> ticketId = Input.absent();

        Builder() {
        }

        public Builder streamId(String str) {
            this.streamId = str;
            return this;
        }

        public Builder ticketId(String str) {
            this.ticketId = Input.fromNullable(str);
            return this;
        }

        public Builder ticketIdInput(Input<String> input) {
            this.ticketId = (Input) Utils.checkNotNull(input, "ticketId == null");
            return this;
        }

        public VODPlaybackInfosQuery build() {
            Utils.checkNotNull(this.streamId, "streamId == null");
            return new VODPlaybackInfosQuery(this.streamId, this.ticketId);
        }
    }

    public static final class Variables extends Operation.Variables {
        private final String streamId;
        private final Input<String> ticketId;
        private final transient Map<String, Object> valueMap;

        Variables(String str, Input<String> input) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.valueMap = linkedHashMap;
            this.streamId = str;
            this.ticketId = input;
            linkedHashMap.put("streamId", str);
            if (input.defined) {
                this.valueMap.put("ticketId", input.value);
            }
        }

        public String streamId() {
            return this.streamId;
        }

        public Input<String> ticketId() {
            return this.ticketId;
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(this.valueMap);
        }

        @Override // com.apollographql.apollo.api.Operation.Variables
        public InputFieldMarshaller marshaller() {
            return new InputFieldMarshaller() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.Variables.1
                @Override // com.apollographql.apollo.api.internal.InputFieldMarshaller
                public void marshal(InputFieldWriter inputFieldWriter) throws IOException {
                    inputFieldWriter.writeCustom("streamId", CustomType.ID, Variables.this.streamId);
                    if (Variables.this.ticketId.defined) {
                        inputFieldWriter.writeCustom("ticketId", CustomType.ID, Variables.this.ticketId.value != 0 ? Variables.this.ticketId.value : null);
                    }
                }
            };
        }
    }

    public static class Data implements Operation.Data {
        static final ResponseField[] $responseFields = {ResponseField.forObject("authorizeStream", "authorizeStream", new UnmodifiableMapBuilder(2).put("streamId", new UnmodifiableMapBuilder(2).put("kind", "Variable").put(ResponseField.VARIABLE_NAME_KEY, "streamId").build()).put("ticket", new UnmodifiableMapBuilder(2).put("kind", "Variable").put(ResponseField.VARIABLE_NAME_KEY, "ticketId").build()).build(), false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final AuthorizeStream authorizeStream;

        public Data(AuthorizeStream authorizeStream) {
            this.authorizeStream = (AuthorizeStream) Utils.checkNotNull(authorizeStream, "authorizeStream == null");
        }

        public AuthorizeStream authorizeStream() {
            return this.authorizeStream;
        }

        @Override // com.apollographql.apollo.api.Operation.Data
        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.Data.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeObject(Data.$responseFields[0], Data.this.authorizeStream.marshaller());
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Data{authorizeStream=" + this.authorizeStream + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Data) {
                return this.authorizeStream.equals(((Data) obj).authorizeStream);
            }
            return false;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = 1000003 ^ this.authorizeStream.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Data> {
            final AuthorizeStream.Mapper authorizeStreamFieldMapper = new AuthorizeStream.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Data map(ResponseReader responseReader) {
                return new Data((AuthorizeStream) responseReader.readObject(Data.$responseFields[0], new ResponseReader.ObjectReader<AuthorizeStream>() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.Data.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                    public AuthorizeStream read(ResponseReader responseReader2) {
                        return Mapper.this.authorizeStreamFieldMapper.map(responseReader2);
                    }
                }));
            }
        }
    }

    public static class AuthorizeStream {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forList("options", "options", null, false, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final List<Option> options;

        public AuthorizeStream(String str, List<Option> list) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.options = (List) Utils.checkNotNull(list, "options == null");
        }

        public String __typename() {
            return this.__typename;
        }

        public List<Option> options() {
            return this.options;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.AuthorizeStream.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(AuthorizeStream.$responseFields[0], AuthorizeStream.this.__typename);
                    responseWriter.writeList(AuthorizeStream.$responseFields[1], AuthorizeStream.this.options, new ResponseWriter.ListWriter() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.AuthorizeStream.1.1
                        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListWriter
                        public void write(List list, ResponseWriter.ListItemWriter listItemWriter) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                listItemWriter.writeObject(((Option) it.next()).marshaller());
                            }
                        }
                    });
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "AuthorizeStream{__typename=" + this.__typename + ", options=" + this.options + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthorizeStream)) {
                return false;
            }
            AuthorizeStream authorizeStream = (AuthorizeStream) obj;
            return this.__typename.equals(authorizeStream.__typename) && this.options.equals(authorizeStream.options);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = ((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.options.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<AuthorizeStream> {
            final Option.Mapper optionFieldMapper = new Option.Mapper();

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public AuthorizeStream map(ResponseReader responseReader) {
                return new AuthorizeStream(responseReader.readString(AuthorizeStream.$responseFields[0]), responseReader.readList(AuthorizeStream.$responseFields[1], new ResponseReader.ListReader<Option>() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.AuthorizeStream.Mapper.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.apollographql.apollo.api.internal.ResponseReader.ListReader
                    public Option read(ResponseReader.ListItemReader listItemReader) {
                        return (Option) listItemReader.readObject(new ResponseReader.ObjectReader<Option>() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.AuthorizeStream.Mapper.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.apollographql.apollo.api.internal.ResponseReader.ObjectReader
                            public Option read(ResponseReader responseReader2) {
                                return Mapper.this.optionFieldMapper.map(responseReader2);
                            }
                        });
                    }
                }));
            }
        }
    }

    public static class Option {
        static final ResponseField[] $responseFields = {ResponseField.forString("__typename", "__typename", null, false, Collections.emptyList()), ResponseField.forCustomType("source", "source", null, false, CustomType.URI, Collections.emptyList()), ResponseField.forCustomType("contentType", "contentType", null, false, CustomType.MEDIATYPE, Collections.emptyList()), ResponseField.forString("privateData", "privateData", null, true, Collections.emptyList())};
        private volatile transient int $hashCode;
        private volatile transient boolean $hashCodeMemoized;
        private volatile transient String $toString;
        final String __typename;
        final String contentType;
        final String privateData;
        final String source;

        public Option(String str, String str2, String str3, String str4) {
            this.__typename = (String) Utils.checkNotNull(str, "__typename == null");
            this.source = (String) Utils.checkNotNull(str2, "source == null");
            this.contentType = (String) Utils.checkNotNull(str3, "contentType == null");
            this.privateData = str4;
        }

        public String __typename() {
            return this.__typename;
        }

        public String source() {
            return this.source;
        }

        public String contentType() {
            return this.contentType;
        }

        public String privateData() {
            return this.privateData;
        }

        public ResponseFieldMarshaller marshaller() {
            return new ResponseFieldMarshaller() { // from class: com.dotscreen.bokit.VODPlaybackInfosQuery.Option.1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMarshaller
                public void marshal(ResponseWriter responseWriter) {
                    responseWriter.writeString(Option.$responseFields[0], Option.this.__typename);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Option.$responseFields[1], Option.this.source);
                    responseWriter.writeCustom((ResponseField.CustomTypeField) Option.$responseFields[2], Option.this.contentType);
                    responseWriter.writeString(Option.$responseFields[3], Option.this.privateData);
                }
            };
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Option{__typename=" + this.__typename + ", source=" + this.source + ", contentType=" + this.contentType + ", privateData=" + this.privateData + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Option)) {
                return false;
            }
            Option option = (Option) obj;
            if (this.__typename.equals(option.__typename) && this.source.equals(option.source) && this.contentType.equals(option.contentType)) {
                String str = this.privateData;
                String str2 = option.privateData;
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
                int iHashCode = (((((this.__typename.hashCode() ^ 1000003) * 1000003) ^ this.source.hashCode()) * 1000003) ^ this.contentType.hashCode()) * 1000003;
                String str = this.privateData;
                this.$hashCode = iHashCode ^ (str == null ? 0 : str.hashCode());
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }

        public static final class Mapper implements ResponseFieldMapper<Option> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
            public Option map(ResponseReader responseReader) {
                return new Option(responseReader.readString(Option.$responseFields[0]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Option.$responseFields[1]), (String) responseReader.readCustomType((ResponseField.CustomTypeField) Option.$responseFields[2]), responseReader.readString(Option.$responseFields[3]));
            }
        }
    }
}
