package com.dotscreen.portal.utils.probes;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: Probe.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006J\u0013\u0010\n\u001a\u0004\u0018\u00018\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lcom/dotscreen/portal/utils/probes/Probe;", ExifInterface.GPS_DIRECTION_TRUE, "", "sentryTag", "", "voPlayerTag", "(Ljava/lang/String;Ljava/lang/String;)V", "getSentryTag", "()Ljava/lang/String;", "getVoPlayerTag", "launch", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class Probe<T> {
    private final String sentryTag;
    private final String voPlayerTag;

    public abstract Object launch(Continuation<? super T> continuation);

    public Probe(String str, String str2) {
        this.sentryTag = str;
        this.voPlayerTag = str2;
    }

    public final String getSentryTag() {
        return this.sentryTag;
    }

    public final String getVoPlayerTag() {
        return this.voPlayerTag;
    }
}
