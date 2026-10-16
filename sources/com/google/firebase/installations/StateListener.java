package com.google.firebase.installations;

import com.google.firebase.installations.local.PersistedInstallationEntry;

/* JADX INFO: compiled from: com.google.firebase:firebase-installations@@16.2.0 */
/* JADX INFO: loaded from: classes2.dex */
interface StateListener {
    boolean onException(PersistedInstallationEntry persistedInstallationEntry, Exception exc);

    boolean onStateReached(PersistedInstallationEntry persistedInstallationEntry);
}
