package com.nytimes.android.external.cache;

/* JADX INFO: loaded from: classes2.dex */
public interface RemovalListener<K, V> {
    void onRemoval(RemovalNotification<K, V> removalNotification);
}
