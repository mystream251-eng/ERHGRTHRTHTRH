package com.apollographql.apollo.api.cache.http;

import java.io.IOException;
import kotlin.Metadata;
import okio.Sink;

/* JADX INFO: compiled from: HttpCacheRecordEditor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo/api/cache/http/HttpCacheRecordEditor;", "", "abort", "", "bodySink", "Lokio/Sink;", "commit", "headerSink", "apollo-http-cache-api"}, k = 1, mv = {1, 1, 16})
public interface HttpCacheRecordEditor {
    void abort() throws IOException;

    Sink bodySink();

    void commit() throws IOException;

    Sink headerSink();
}
