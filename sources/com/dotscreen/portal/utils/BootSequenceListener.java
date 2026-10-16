package com.dotscreen.portal.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: BootSequenceManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/dotscreen/portal/utils/BootSequenceListener;", "", "onStepFailed", "", "step", "Lcom/dotscreen/portal/utils/BootSequenceManager$Steps;", "state", "Lcom/dotscreen/portal/utils/BootSequenceManager$State;", "delay", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public interface BootSequenceListener {
    void onStepFailed(int delay);

    void onStepFailed(BootSequenceManager.Steps step, BootSequenceManager.State state);
}
