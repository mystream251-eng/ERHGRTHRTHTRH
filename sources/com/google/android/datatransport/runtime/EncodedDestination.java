package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
public interface EncodedDestination extends Destination {
    Set<Encoding> getSupportedEncodings();
}
