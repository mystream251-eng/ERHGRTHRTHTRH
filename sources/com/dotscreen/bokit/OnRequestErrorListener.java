package com.dotscreen.bokit;

import com.google.android.gms.common.internal.ImagesContract;
import java.net.URL;
import kotlin.Metadata;

/* JADX INFO: compiled from: OnRequestErrorListener.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/dotscreen/bokit/OnRequestErrorListener;", "", "onRequestError", "", "error", "", ImagesContract.URL, "Ljava/net/URL;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public interface OnRequestErrorListener {
    void onRequestError(Throwable error, URL url);
}
