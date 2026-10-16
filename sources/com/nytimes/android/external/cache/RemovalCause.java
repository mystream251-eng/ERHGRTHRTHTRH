package com.nytimes.android.external.cache;

/* JADX INFO: loaded from: classes2.dex */
public enum RemovalCause {
    EXPLICIT { // from class: com.nytimes.android.external.cache.RemovalCause.1
        @Override // com.nytimes.android.external.cache.RemovalCause
        boolean wasEvicted() {
            return false;
        }
    },
    REPLACED { // from class: com.nytimes.android.external.cache.RemovalCause.2
        @Override // com.nytimes.android.external.cache.RemovalCause
        boolean wasEvicted() {
            return false;
        }
    },
    COLLECTED { // from class: com.nytimes.android.external.cache.RemovalCause.3
        @Override // com.nytimes.android.external.cache.RemovalCause
        boolean wasEvicted() {
            return true;
        }
    },
    EXPIRED { // from class: com.nytimes.android.external.cache.RemovalCause.4
        @Override // com.nytimes.android.external.cache.RemovalCause
        boolean wasEvicted() {
            return true;
        }
    },
    SIZE { // from class: com.nytimes.android.external.cache.RemovalCause.5
        @Override // com.nytimes.android.external.cache.RemovalCause
        boolean wasEvicted() {
            return true;
        }
    };

    abstract boolean wasEvicted();
}
