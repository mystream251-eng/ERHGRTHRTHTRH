package com.dotscreen.portal.ui.screens.guide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class HardwareLayerAnimatorListenerAdapter extends AnimatorListenerAdapter {
    private boolean mLayerTypeChanged;
    private final View mView;

    public HardwareLayerAnimatorListenerAdapter(View view) {
        this.mView = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (this.mView.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
            this.mLayerTypeChanged = true;
            this.mView.setLayerType(2, null);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.mLayerTypeChanged) {
            this.mLayerTypeChanged = false;
            this.mView.setLayerType(0, null);
        }
    }
}
