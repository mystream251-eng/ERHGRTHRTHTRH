package com.apollographql.apollo.api.internal.json;

import androidx.exifinterface.media.ExifInterface;
import java.io.Closeable;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: compiled from: JsonReader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0001$J\b\u0010\f\u001a\u00020\u0000H&J\b\u0010\r\u001a\u00020\u0000H&J\b\u0010\u000e\u001a\u00020\u0000H&J\b\u0010\u000f\u001a\u00020\u0000H&J\b\u0010\u0010\u001a\u00020\u0011H&J\t\u0010\u0012\u001a\u00020\u0004H¦\u0002J\b\u0010\u0013\u001a\u00020\u0004H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u0011H&J\u0015\u0010\u001b\u001a\u0004\u0018\u0001H\u001c\"\u0004\b\u0000\u0010\u001cH&¢\u0006\u0002\u0010\u001dJ\n\u0010\u001e\u001a\u0004\u0018\u00010\u0011H&J\b\u0010\u001f\u001a\u00020 H&J\b\u0010!\u001a\u00020\"H&J\b\u0010#\u001a\u00020\"H&R\u0018\u0010\u0003\u001a\u00020\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\u00020\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006%"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/JsonReader;", "Ljava/io/Closeable;", "Lcom/apollographql/apollo/api/internal/json/Closeable;", "failOnUnknown", "", "getFailOnUnknown", "()Z", "setFailOnUnknown", "(Z)V", "lenient", "getLenient", "setLenient", "beginArray", "beginObject", "endArray", "endObject", "getPath", "", "hasNext", "nextBoolean", "nextDouble", "", "nextInt", "", "nextLong", "", "nextName", "nextNull", ExifInterface.GPS_DIRECTION_TRUE, "()Ljava/lang/Object;", "nextString", "peek", "Lcom/apollographql/apollo/api/internal/json/JsonReader$Token;", "promoteNameToValue", "", "skipValue", "Token", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface JsonReader extends Closeable {

    /* JADX INFO: compiled from: JsonReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/JsonReader$Token;", "", "(Ljava/lang/String;I)V", "BEGIN_ARRAY", "END_ARRAY", "BEGIN_OBJECT", "END_OBJECT", "NAME", "STRING", "NUMBER", "BOOLEAN", "NULL", "END_DOCUMENT", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    JsonReader beginArray() throws IOException;

    JsonReader beginObject() throws IOException;

    JsonReader endArray() throws IOException;

    JsonReader endObject() throws IOException;

    boolean getFailOnUnknown();

    boolean getLenient();

    String getPath();

    boolean hasNext() throws IOException;

    boolean nextBoolean() throws IOException;

    double nextDouble() throws IOException;

    int nextInt() throws IOException;

    long nextLong() throws IOException;

    String nextName() throws IOException;

    <T> T nextNull() throws IOException;

    String nextString() throws IOException;

    Token peek() throws IOException;

    void promoteNameToValue() throws IOException;

    void setFailOnUnknown(boolean z);

    void setLenient(boolean z);

    void skipValue() throws IOException;
}
