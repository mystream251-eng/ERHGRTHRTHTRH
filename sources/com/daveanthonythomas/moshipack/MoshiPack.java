package com.daveanthonythomas.moshipack;

import androidx.exifinterface.media.ExifInterface;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.KotlinJsonAdapterFactory;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.MsgpackReader;
import com.squareup.moshi.MsgpackWriter;
import com.squareup.moshi.MsgpackWriterOptions;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import okio.Buffer;
import okio.BufferedSource;

/* JADX INFO: compiled from: MoshiPack.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\f\u0018\u0000 /2\u00020\u0001:\u0001/B4\u0012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001eJ\u000e\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$J\u0016\u0010\"\u001a\n %*\u0004\u0018\u00010 0 2\u0006\u0010!\u001a\u00020\u001eJ\u001e\u0010&\u001a\u00020\u001e\"\u0006\b\u0000\u0010'\u0018\u00012\u0006\u0010(\u001a\u0002H'H\u0086\b¢\u0006\u0002\u0010)J\u001e\u0010*\u001a\u00020$\"\u0006\b\u0000\u0010'\u0018\u00012\u0006\u0010(\u001a\u0002H'H\u0086\b¢\u0006\u0002\u0010+J\u001e\u0010,\u001a\u0002H'\"\u0006\b\u0000\u0010'\u0018\u00012\u0006\u0010#\u001a\u00020$H\u0086\b¢\u0006\u0002\u0010-J\u001e\u0010,\u001a\u0002H'\"\u0006\b\u0000\u0010'\u0018\u00012\u0006\u0010!\u001a\u00020\u001eH\u0086\b¢\u0006\u0002\u0010.R\u001f\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0016\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0017\u0010\u000fR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u00060"}, d2 = {"Lcom/daveanthonythomas/moshipack/MoshiPack;", "", "builder", "Lkotlin/Function1;", "Lcom/squareup/moshi/Moshi$Builder;", "", "Lkotlin/ExtensionFunctionType;", "moshi", "Lcom/squareup/moshi/Moshi;", "writerOptions", "Lcom/squareup/moshi/MsgpackWriterOptions;", "(Lkotlin/jvm/functions/Function1;Lcom/squareup/moshi/Moshi;Lcom/squareup/moshi/MsgpackWriterOptions;)V", "jsonToMp", "Lcom/daveanthonythomas/moshipack/FormatInterchange;", "getJsonToMp", "()Lcom/daveanthonythomas/moshipack/FormatInterchange;", "jsonToMp$delegate", "Lkotlin/Lazy;", "getMoshi", "()Lcom/squareup/moshi/Moshi;", "setMoshi", "(Lcom/squareup/moshi/Moshi;)V", "mpToJson", "getMpToJson", "mpToJson$delegate", "getWriterOptions", "()Lcom/squareup/moshi/MsgpackWriterOptions;", "setWriterOptions", "(Lcom/squareup/moshi/MsgpackWriterOptions;)V", "jsonToMsgpack", "Lokio/BufferedSource;", "jsonString", "", "source", "msgpackToJson", "bytes", "", "kotlin.jvm.PlatformType", "pack", ExifInterface.GPS_DIRECTION_TRUE, "value", "(Ljava/lang/Object;)Lokio/BufferedSource;", "packToByteArray", "(Ljava/lang/Object;)[B", "unpack", "([B)Ljava/lang/Object;", "(Lokio/BufferedSource;)Ljava/lang/Object;", "Companion", "moshipack"}, k = 1, mv = {1, 1, 13})
public final class MoshiPack {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MoshiPack.class), "mpToJson", "getMpToJson()Lcom/daveanthonythomas/moshipack/FormatInterchange;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(MoshiPack.class), "jsonToMp", "getJsonToMp()Lcom/daveanthonythomas/moshipack/FormatInterchange;"))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Function1<? super Moshi.Builder, Unit> builder;

    /* JADX INFO: renamed from: jsonToMp$delegate, reason: from kotlin metadata */
    private final Lazy jsonToMp;
    private Moshi moshi;

    /* JADX INFO: renamed from: mpToJson$delegate, reason: from kotlin metadata */
    private final Lazy mpToJson;
    private MsgpackWriterOptions writerOptions;

    public MoshiPack() {
        this(null, null, null, 7, null);
    }

    private final FormatInterchange getJsonToMp() {
        Lazy lazy = this.jsonToMp;
        KProperty kProperty = $$delegatedProperties[1];
        return (FormatInterchange) lazy.getValue();
    }

    private final FormatInterchange getMpToJson() {
        Lazy lazy = this.mpToJson;
        KProperty kProperty = $$delegatedProperties[0];
        return (FormatInterchange) lazy.getValue();
    }

    public MoshiPack(Function1<? super Moshi.Builder, Unit> builder, Moshi moshi, MsgpackWriterOptions writerOptions) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        Intrinsics.checkParameterIsNotNull(moshi, "moshi");
        Intrinsics.checkParameterIsNotNull(writerOptions, "writerOptions");
        this.builder = builder;
        this.moshi = moshi;
        this.writerOptions = writerOptions;
        this.mpToJson = LazyKt.lazy(new Function0<FormatInterchange>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$mpToJson$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FormatInterchange invoke() {
                return new FormatInterchange(new Format.Msgpack(null, 1, null), new Format.Json());
            }
        });
        this.jsonToMp = LazyKt.lazy(new Function0<FormatInterchange>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$jsonToMp$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FormatInterchange invoke() {
                return new FormatInterchange(new Format.Json(), new Format.Msgpack(null, 1, null));
            }
        });
    }

    public /* synthetic */ MoshiPack(Function1 function1, Moshi moshi, MsgpackWriterOptions msgpackWriterOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        function1 = (i & 1) != 0 ? new Function1<Moshi.Builder, Unit>() { // from class: com.daveanthonythomas.moshipack.MoshiPack.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Moshi.Builder receiver$0) {
                Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Moshi.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }
        } : function1;
        if ((i & 2) != 0) {
            Moshi.Builder builder = new Moshi.Builder();
            function1.invoke(builder);
            moshi = builder.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
            Intrinsics.checkExpressionValueIsNotNull(moshi, "MoshiPack.moshi(builder)");
        }
        this(function1, moshi, (i & 4) != 0 ? new MsgpackWriterOptions(null, null, false, 7, null) : msgpackWriterOptions);
    }

    public final Moshi getMoshi() {
        return this.moshi;
    }

    public final void setMoshi(Moshi moshi) {
        Intrinsics.checkParameterIsNotNull(moshi, "<set-?>");
        this.moshi = moshi;
    }

    public final MsgpackWriterOptions getWriterOptions() {
        return this.writerOptions;
    }

    public final void setWriterOptions(MsgpackWriterOptions msgpackWriterOptions) {
        Intrinsics.checkParameterIsNotNull(msgpackWriterOptions, "<set-?>");
        this.writerOptions = msgpackWriterOptions;
    }

    /* JADX INFO: compiled from: MoshiPack.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bJ,\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b2\u0019\b\u0006\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\bJ\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0007\u001a\u00020\bJ \u0010\u0012\u001a\n \f*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bJ&\u0010\u0015\u001a\u00020\u0004\"\u0006\b\u0000\u0010\u0016\u0018\u00012\u0006\u0010\u0017\u001a\u0002H\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0086\b¢\u0006\u0002\u0010\u001aJ0\u0010\u0015\u001a\u00020\u0004\"\u0006\b\u0000\u0010\u0016\u0018\u00012\u0006\u0010\u0017\u001a\u0002H\u00162\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0086\b¢\u0006\u0002\u0010\u001bJC\u0010\u0015\u001a\u00020\u0004\"\u0006\b\u0000\u0010\u0016\u0018\u00012\u0006\u0010\u0017\u001a\u0002H\u00162\u0019\b\u0006\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0086\b¢\u0006\u0002\u0010\u001cJ&\u0010\u001d\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0016\u0018\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000bH\u0086\b¢\u0006\u0002\u0010\u001eJ9\u0010\u001d\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0016\u0018\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0019\b\u0006\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010\u001fJ&\u0010\u001d\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0016\u0018\u00012\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0086\b¢\u0006\u0002\u0010 J9\u0010\u001d\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0016\u0018\u00012\u0006\u0010\t\u001a\u00020\u00042\u0019\b\u0006\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"Lcom/daveanthonythomas/moshipack/MoshiPack$Companion;", "", "()V", "jsonToMsgpack", "Lokio/BufferedSource;", "jsonString", "", "writerOptions", "Lcom/squareup/moshi/MsgpackWriterOptions;", "source", "moshi", "Lcom/squareup/moshi/Moshi;", "kotlin.jvm.PlatformType", "builder", "Lkotlin/Function1;", "Lcom/squareup/moshi/Moshi$Builder;", "", "Lkotlin/ExtensionFunctionType;", "msgpackToJson", "bytes", "", "pack", ExifInterface.GPS_DIRECTION_TRUE, "value", "moshiPack", "Lcom/daveanthonythomas/moshipack/MoshiPack;", "(Ljava/lang/Object;Lcom/daveanthonythomas/moshipack/MoshiPack;)Lokio/BufferedSource;", "(Ljava/lang/Object;Lcom/squareup/moshi/Moshi;Lcom/squareup/moshi/MsgpackWriterOptions;)Lokio/BufferedSource;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lcom/squareup/moshi/MsgpackWriterOptions;)Lokio/BufferedSource;", "unpack", "([BLcom/squareup/moshi/Moshi;)Ljava/lang/Object;", "([BLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Lokio/BufferedSource;Lcom/squareup/moshi/Moshi;)Ljava/lang/Object;", "(Lokio/BufferedSource;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "moshipack"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final <T> BufferedSource pack(T value, MoshiPack moshiPack) throws IOException {
            Buffer buffer = new Buffer();
            Moshi moshi = moshiPack.getMoshi();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            JsonAdapter<T> jsonAdapterAdapter = moshi.adapter((Class) Object.class);
            MsgpackWriter msgpackWriter = new MsgpackWriter(buffer);
            msgpackWriter.setOptions(moshiPack.getWriterOptions());
            jsonAdapterAdapter.toJson(msgpackWriter, value);
            return buffer;
        }

        static /* synthetic */ BufferedSource pack$default(Companion companion, Object obj, Moshi moshi, MsgpackWriterOptions msgpackWriterOptions, int i, Object obj2) throws IOException {
            if ((i & 4) != 0) {
                msgpackWriterOptions = new MsgpackWriterOptions(null, null, false, 7, null);
            }
            Buffer buffer = new Buffer();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            JsonAdapter jsonAdapterAdapter = moshi.adapter(Object.class);
            MsgpackWriter msgpackWriter = new MsgpackWriter(buffer);
            msgpackWriter.setOptions(msgpackWriterOptions);
            jsonAdapterAdapter.toJson(msgpackWriter, obj);
            return buffer;
        }

        private final <T> BufferedSource pack(T value, Moshi moshi, MsgpackWriterOptions writerOptions) throws IOException {
            Buffer buffer = new Buffer();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            JsonAdapter<T> jsonAdapterAdapter = moshi.adapter((Class) Object.class);
            MsgpackWriter msgpackWriter = new MsgpackWriter(buffer);
            msgpackWriter.setOptions(writerOptions);
            jsonAdapterAdapter.toJson(msgpackWriter, value);
            return buffer;
        }

        static /* synthetic */ BufferedSource pack$default(Companion companion, Object obj, Function1 function1, MsgpackWriterOptions msgpackWriterOptions, int i, Object obj2) throws IOException {
            if ((i & 2) != 0) {
                function1 = new Function1<Moshi.Builder, Unit>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$pack$3
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Moshi.Builder receiver$0) {
                        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Moshi.Builder builder) {
                        invoke2(builder);
                        return Unit.INSTANCE;
                    }
                };
            }
            if ((i & 4) != 0) {
                msgpackWriterOptions = new MsgpackWriterOptions(null, null, false, 7, null);
            }
            Moshi.Builder builder = new Moshi.Builder();
            function1.invoke(builder);
            Moshi moshiBuild = builder.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
            Intrinsics.checkExpressionValueIsNotNull(moshiBuild, "moshi(builder)");
            Buffer buffer = new Buffer();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            JsonAdapter jsonAdapterAdapter = moshiBuild.adapter(Object.class);
            MsgpackWriter msgpackWriter = new MsgpackWriter(buffer);
            msgpackWriter.setOptions(msgpackWriterOptions);
            jsonAdapterAdapter.toJson(msgpackWriter, obj);
            return buffer;
        }

        private final <T> BufferedSource pack(T value, Function1<? super Moshi.Builder, Unit> builder, MsgpackWriterOptions writerOptions) throws IOException {
            Moshi.Builder builder2 = new Moshi.Builder();
            builder.invoke(builder2);
            Moshi moshiBuild = builder2.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
            Intrinsics.checkExpressionValueIsNotNull(moshiBuild, "moshi(builder)");
            Buffer buffer = new Buffer();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            JsonAdapter<T> jsonAdapterAdapter = moshiBuild.adapter((Class) Object.class);
            MsgpackWriter msgpackWriter = new MsgpackWriter(buffer);
            msgpackWriter.setOptions(writerOptions);
            jsonAdapterAdapter.toJson(msgpackWriter, value);
            return buffer;
        }

        private final <T> T unpack(BufferedSource source, Moshi moshi) throws IOException {
            Intrinsics.needClassReification();
            T tFromJson = moshi.adapter(new TypeReference<T>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$unpack$type$1
            }.getType()).fromJson(new MsgpackReader(source));
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return tFromJson;
        }

        static /* synthetic */ Object unpack$default(Companion companion, BufferedSource bufferedSource, Function1 function1, int i, Object obj) throws IOException {
            if ((i & 2) != 0) {
                function1 = new Function1<Moshi.Builder, Unit>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$unpack$1
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Moshi.Builder receiver$0) {
                        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Moshi.Builder builder) {
                        invoke2(builder);
                        return Unit.INSTANCE;
                    }
                };
            }
            Moshi.Builder builder = new Moshi.Builder();
            function1.invoke(builder);
            Moshi moshiBuild = builder.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
            Intrinsics.checkExpressionValueIsNotNull(moshiBuild, "moshi(builder)");
            Intrinsics.needClassReification();
            Object objFromJson = moshiBuild.adapter(new TypeReference<T>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$unpack$$inlined$unpack$2
            }.getType()).fromJson(new MsgpackReader(bufferedSource));
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return objFromJson;
        }

        private final <T> T unpack(BufferedSource source, Function1<? super Moshi.Builder, Unit> builder) throws IOException {
            Moshi.Builder builder2 = new Moshi.Builder();
            builder.invoke(builder2);
            Moshi moshiBuild = builder2.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
            Intrinsics.checkExpressionValueIsNotNull(moshiBuild, "moshi(builder)");
            Intrinsics.needClassReification();
            T tFromJson = moshiBuild.adapter(new TypeReference<T>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$unpack$$inlined$unpack$1
            }.getType()).fromJson(new MsgpackReader(source));
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return tFromJson;
        }

        private final <T> T unpack(byte[] bytes, Moshi moshi) throws IOException {
            Buffer buffer = new Buffer();
            buffer.write(bytes);
            Intrinsics.needClassReification();
            T tFromJson = moshi.adapter(new TypeReference<T>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$unpack$$inlined$unpack$3
            }.getType()).fromJson(new MsgpackReader(buffer));
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return tFromJson;
        }

        static /* synthetic */ Object unpack$default(Companion companion, byte[] bArr, Function1 function1, int i, Object obj) throws IOException {
            if ((i & 2) != 0) {
                function1 = new Function1<Moshi.Builder, Unit>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$unpack$3
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Moshi.Builder receiver$0) {
                        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Moshi.Builder builder) {
                        invoke2(builder);
                        return Unit.INSTANCE;
                    }
                };
            }
            Buffer buffer = new Buffer();
            buffer.write(bArr);
            Moshi.Builder builder = new Moshi.Builder();
            function1.invoke(builder);
            Moshi moshiBuild = builder.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
            Intrinsics.checkExpressionValueIsNotNull(moshiBuild, "moshi(builder)");
            Intrinsics.needClassReification();
            Object objFromJson = moshiBuild.adapter(new TypeReference<T>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$unpack$$inlined$unpack$7
            }.getType()).fromJson(new MsgpackReader(buffer));
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return objFromJson;
        }

        private final <T> T unpack(byte[] bytes, Function1<? super Moshi.Builder, Unit> builder) throws IOException {
            Buffer buffer = new Buffer();
            buffer.write(bytes);
            Moshi.Builder builder2 = new Moshi.Builder();
            builder.invoke(builder2);
            Moshi moshiBuild = builder2.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
            Intrinsics.checkExpressionValueIsNotNull(moshiBuild, "moshi(builder)");
            Intrinsics.needClassReification();
            T tFromJson = moshiBuild.adapter(new TypeReference<T>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$unpack$$inlined$unpack$5
            }.getType()).fromJson(new MsgpackReader(buffer));
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return tFromJson;
        }

        public static /* synthetic */ Moshi moshi$default(Companion companion, Function1 builder, int i, Object obj) {
            if ((i & 1) != 0) {
                builder = new Function1<Moshi.Builder, Unit>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$Companion$moshi$1
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Moshi.Builder receiver$0) {
                        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Moshi.Builder builder2) {
                        invoke2(builder2);
                        return Unit.INSTANCE;
                    }
                };
            }
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            Moshi.Builder builder2 = new Moshi.Builder();
            builder.invoke(builder2);
            return builder2.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
        }

        public final Moshi moshi(Function1<? super Moshi.Builder, Unit> builder) {
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            Moshi.Builder builder2 = new Moshi.Builder();
            builder.invoke(builder2);
            return builder2.add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
        }

        public static /* synthetic */ String msgpackToJson$default(Companion companion, byte[] bArr, MsgpackWriterOptions msgpackWriterOptions, int i, Object obj) {
            if ((i & 2) != 0) {
                msgpackWriterOptions = new MsgpackWriterOptions(null, null, false, 7, null);
            }
            return companion.msgpackToJson(bArr, msgpackWriterOptions);
        }

        public final String msgpackToJson(byte[] bytes, MsgpackWriterOptions writerOptions) {
            Intrinsics.checkParameterIsNotNull(bytes, "bytes");
            Intrinsics.checkParameterIsNotNull(writerOptions, "writerOptions");
            Buffer buffer = new Buffer();
            buffer.write(bytes);
            String strMsgpackToJson = msgpackToJson(buffer, writerOptions);
            Intrinsics.checkExpressionValueIsNotNull(strMsgpackToJson, "msgpackToJson(Buffer().a…(bytes) }, writerOptions)");
            return strMsgpackToJson;
        }

        public static /* synthetic */ String msgpackToJson$default(Companion companion, BufferedSource bufferedSource, MsgpackWriterOptions msgpackWriterOptions, int i, Object obj) {
            if ((i & 2) != 0) {
                msgpackWriterOptions = new MsgpackWriterOptions(null, null, false, 7, null);
            }
            return companion.msgpackToJson(bufferedSource, msgpackWriterOptions);
        }

        public final String msgpackToJson(BufferedSource source, MsgpackWriterOptions writerOptions) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            Intrinsics.checkParameterIsNotNull(writerOptions, "writerOptions");
            return new FormatInterchange(new Format.Msgpack(writerOptions), new Format.Json()).transform(source).readUtf8();
        }

        public static /* synthetic */ BufferedSource jsonToMsgpack$default(Companion companion, String str, MsgpackWriterOptions msgpackWriterOptions, int i, Object obj) {
            if ((i & 2) != 0) {
                msgpackWriterOptions = new MsgpackWriterOptions(null, null, false, 7, null);
            }
            return companion.jsonToMsgpack(str, msgpackWriterOptions);
        }

        public final BufferedSource jsonToMsgpack(String jsonString, MsgpackWriterOptions writerOptions) {
            Intrinsics.checkParameterIsNotNull(jsonString, "jsonString");
            Intrinsics.checkParameterIsNotNull(writerOptions, "writerOptions");
            Buffer buffer = new Buffer();
            buffer.writeUtf8(jsonString);
            return jsonToMsgpack$default(this, buffer, (MsgpackWriterOptions) null, 2, (Object) null);
        }

        public static /* synthetic */ BufferedSource jsonToMsgpack$default(Companion companion, BufferedSource bufferedSource, MsgpackWriterOptions msgpackWriterOptions, int i, Object obj) {
            if ((i & 2) != 0) {
                msgpackWriterOptions = new MsgpackWriterOptions(null, null, false, 7, null);
            }
            return companion.jsonToMsgpack(bufferedSource, msgpackWriterOptions);
        }

        public final BufferedSource jsonToMsgpack(BufferedSource source, MsgpackWriterOptions writerOptions) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            Intrinsics.checkParameterIsNotNull(writerOptions, "writerOptions");
            return new FormatInterchange(new Format.Json(), new Format.Msgpack(writerOptions)).transform(source);
        }
    }

    private final <T> T unpack(byte[] bytes) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(bytes);
        Moshi moshi = getMoshi();
        Intrinsics.needClassReification();
        T tFromJson = moshi.adapter(new TypeReference<T>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$unpack$$inlined$unpack$2
        }.getType()).fromJson(new MsgpackReader(buffer));
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return tFromJson;
    }

    private final <T> T unpack(BufferedSource source) throws IOException {
        Moshi moshi = getMoshi();
        Intrinsics.needClassReification();
        T tFromJson = moshi.adapter(new TypeReference<T>() { // from class: com.daveanthonythomas.moshipack.MoshiPack$unpack$$inlined$unpack$3
        }.getType()).fromJson(new MsgpackReader(source));
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return tFromJson;
    }

    public final String msgpackToJson(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        Buffer buffer = new Buffer();
        buffer.write(bytes);
        String strMsgpackToJson = msgpackToJson(buffer);
        Intrinsics.checkExpressionValueIsNotNull(strMsgpackToJson, "msgpackToJson(Buffer().apply { write(bytes) })");
        return strMsgpackToJson;
    }

    public final String msgpackToJson(BufferedSource source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        return getMpToJson().transform(source).readUtf8();
    }

    public final BufferedSource jsonToMsgpack(String jsonString) {
        Intrinsics.checkParameterIsNotNull(jsonString, "jsonString");
        Buffer buffer = new Buffer();
        buffer.writeUtf8(jsonString);
        return jsonToMsgpack(buffer);
    }

    public final BufferedSource jsonToMsgpack(BufferedSource source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        return getJsonToMp().transform(source);
    }

    private final <T> BufferedSource pack(T value) throws IOException {
        Buffer buffer = new Buffer();
        Moshi moshi = getMoshi();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        JsonAdapter<T> jsonAdapterAdapter = moshi.adapter((Class) Object.class);
        MsgpackWriter msgpackWriter = new MsgpackWriter(buffer);
        msgpackWriter.setOptions(getWriterOptions());
        jsonAdapterAdapter.toJson(msgpackWriter, value);
        return buffer;
    }

    private final <T> byte[] packToByteArray(T value) throws IOException {
        Buffer buffer = new Buffer();
        Moshi moshi = getMoshi();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        JsonAdapter<T> jsonAdapterAdapter = moshi.adapter((Class) Object.class);
        MsgpackWriter msgpackWriter = new MsgpackWriter(buffer);
        msgpackWriter.setOptions(getWriterOptions());
        jsonAdapterAdapter.toJson(msgpackWriter, value);
        byte[] byteArray = buffer.readByteArray();
        Intrinsics.checkExpressionValueIsNotNull(byteArray, "pack(value).readByteArray()");
        return byteArray;
    }
}
