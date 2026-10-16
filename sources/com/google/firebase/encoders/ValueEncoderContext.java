package com.google.firebase.encoders;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface ValueEncoderContext {
    ValueEncoderContext add(double d) throws EncodingException, IOException;

    ValueEncoderContext add(int i) throws EncodingException, IOException;

    ValueEncoderContext add(long j) throws EncodingException, IOException;

    ValueEncoderContext add(String str) throws EncodingException, IOException;

    ValueEncoderContext add(boolean z) throws EncodingException, IOException;

    ValueEncoderContext add(byte[] bArr) throws EncodingException, IOException;
}
