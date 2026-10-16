package com.dotscreen.tv.common;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.farkit.network.RequestManager;
import kotlin.Metadata;

/* JADX INFO: compiled from: INetRequestProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/dotscreen/tv/common/INetRequestProvider;", "", "getImageLoader", "Lcom/android/volley/toolbox/ImageLoader;", "getLocalImageLoader", "getRequestManager", "Lcom/dotscreen/farkit/network/RequestManager;", "getRequestQueue", "Lcom/android/volley/RequestQueue;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public interface INetRequestProvider {
    ImageLoader getImageLoader();

    ImageLoader getLocalImageLoader();

    RequestManager getRequestManager();

    RequestQueue getRequestQueue();
}
