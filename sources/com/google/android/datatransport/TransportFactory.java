package com.google.android.datatransport;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-api@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
public interface TransportFactory {
    <T> Transport<T> getTransport(String str, Class<T> cls, Encoding encoding, Transformer<T, byte[]> transformer);

    @Deprecated
    <T> Transport<T> getTransport(String str, Class<T> cls, Transformer<T, byte[]> transformer);
}
