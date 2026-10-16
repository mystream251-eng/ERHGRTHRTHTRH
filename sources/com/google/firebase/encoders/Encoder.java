package com.google.firebase.encoders;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface Encoder<TValue, TContext> {
    void encode(TValue tvalue, TContext tcontext) throws EncodingException, IOException;
}
