package com.google.android.datatransport.runtime;

import com.google.android.datatransport.TransportScheduleCallback;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
interface TransportInternal {
    void send(SendRequest sendRequest, TransportScheduleCallback transportScheduleCallback);
}
