package com.android.tv.common.ui.setup.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Property;
import android.view.View;
import com.android.tv.common.R;

/* JADX INFO: loaded from: classes.dex */
class TranslationAnimationCreator {
    TranslationAnimationCreator() {
    }

    static Animator createAnimation(View view, TransitionValues transitionValues, int i, float f, float f2, TimeInterpolator timeInterpolator, Transition transition) {
        float translationX = view.getTranslationX();
        Integer num = (Integer) transitionValues.view.getTag(R.id.transitionPosition);
        if (num != null) {
            f = (num.intValue() - i) + translationX;
        }
        int iRound = i + Math.round(f - translationX);
        view.setTranslationX(f);
        if (f == f2) {
            return null;
        }
        Path path = new Path();
        path.moveTo(f, 0.0f);
        path.lineTo(f2, 0.0f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_X, (Property<View, Float>) View.TRANSLATION_Y, path);
        TransitionPositionListener transitionPositionListener = new TransitionPositionListener(view, transitionValues.view, iRound, translationX);
        transition.addListener(transitionPositionListener);
        objectAnimatorOfFloat.addListener(transitionPositionListener);
        objectAnimatorOfFloat.addPauseListener(transitionPositionListener);
        objectAnimatorOfFloat.setInterpolator(timeInterpolator);
        return objectAnimatorOfFloat;
    }

    private static class TransitionPositionListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private final View mMovingView;
        private float mPausedX;
        private final int mStartX;
        private final float mTerminalX;
        private Integer mTransitionPosition;
        private final View mViewInHierarchy;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
        }

        private TransitionPositionListener(View view, View view2, int i, float f) {
            this.mMovingView = view;
            this.mViewInHierarchy = view2;
            this.mStartX = i - Math.round(view.getTranslationX());
            this.mTerminalX = f;
            Integer num = (Integer) this.mViewInHierarchy.getTag(R.id.transitionPosition);
            this.mTransitionPosition = num;
            if (num != null) {
                this.mViewInHierarchy.setTag(R.id.transitionPosition, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.mTransitionPosition = Integer.valueOf(Math.round(this.mStartX + this.mMovingView.getTranslationX()));
            this.mViewInHierarchy.setTag(R.id.transitionPosition, this.mTransitionPosition);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            this.mPausedX = this.mMovingView.getTranslationX();
            this.mMovingView.setTranslationX(this.mTerminalX);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            this.mMovingView.setTranslationX(this.mPausedX);
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            this.mMovingView.setTranslationX(this.mTerminalX);
        }
    }
}
