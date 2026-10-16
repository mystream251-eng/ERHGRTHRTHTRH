package com.google.firebase.installations;

import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.firebase:firebase-installations-interop@@16.0.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface FirebaseInstallationsApi {
    Task<Void> delete();

    Task<String> getId();

    Task<InstallationTokenResult> getToken(boolean z);
}
