package com.google.firebase;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public class FirebaseException extends Exception {
    @Deprecated
    protected FirebaseException() {
    }

    public FirebaseException(String str) {
        super(Preconditions.checkNotEmpty(str, "Detail message must not be empty"));
    }

    public FirebaseException(String str, Throwable th) {
        super(Preconditions.checkNotEmpty(str, "Detail message must not be empty"), th);
    }
}
