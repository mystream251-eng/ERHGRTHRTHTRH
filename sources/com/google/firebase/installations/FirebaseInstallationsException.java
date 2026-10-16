package com.google.firebase.installations;

import com.google.firebase.FirebaseException;

/* JADX INFO: compiled from: com.google.firebase:firebase-installations@@16.2.0 */
/* JADX INFO: loaded from: classes2.dex */
public class FirebaseInstallationsException extends FirebaseException {
    private final Status status;

    /* JADX INFO: compiled from: com.google.firebase:firebase-installations@@16.2.0 */
    public enum Status {
        BAD_CONFIG
    }

    public FirebaseInstallationsException(Status status) {
        this.status = status;
    }

    public FirebaseInstallationsException(String str, Status status) {
        super(str);
        this.status = status;
    }

    public FirebaseInstallationsException(String str, Status status, Throwable th) {
        super(str, th);
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }
}
