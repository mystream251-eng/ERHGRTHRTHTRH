package io.sentry.android.core.internal.gestures;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
interface ViewTargetSelector {
    boolean select(View view);

    default boolean skipChildren() {
        return false;
    }
}
