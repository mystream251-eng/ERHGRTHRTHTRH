package com.google.firebase.encoders.json;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class JsonDataEncoderBuilder$$Lambda$4 implements ValueEncoder {
    private static final JsonDataEncoderBuilder$$Lambda$4 instance = new JsonDataEncoderBuilder$$Lambda$4();

    private JsonDataEncoderBuilder$$Lambda$4() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public void encode(Object obj, ValueEncoderContext valueEncoderContext) throws EncodingException, IOException {
        JsonDataEncoderBuilder.lambda$static$1((Boolean) obj, valueEncoderContext);
    }
}
