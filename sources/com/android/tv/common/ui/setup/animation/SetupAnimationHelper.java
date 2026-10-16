package com.android.tv.common.ui.setup.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.Property;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.GravityCompat;
import com.android.tv.common.R;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class SetupAnimationHelper {
    private static final float ANIMATION_TIME_SCALE = 1.0f;
    public static final long DELAY_BETWEEN_SIBLINGS_MS = applyAnimationTimeScale(33);
    private static long sFragmentTransitionDuration;
    private static int sFragmentTransitionLongDistance;
    private static int sFragmentTransitionShortDistance;
    private static boolean sInitialized;

    public static long applyAnimationTimeScale(long j) {
        return (long) (j * 1.0f);
    }

    private SetupAnimationHelper() {
    }

    public static void initialize(Context context) {
        sFragmentTransitionDuration = context.getResources().getInteger(R.integer.setup_fragment_transition_duration);
        sFragmentTransitionLongDistance = context.getResources().getDimensionPixelOffset(R.dimen.setup_fragment_transition_long_distance);
        sFragmentTransitionShortDistance = context.getResources().getDimensionPixelOffset(R.dimen.setup_fragment_transition_short_distance);
        sInitialized = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkInitialized() {
        if (!sInitialized) {
            throw new IllegalStateException("SetupAnimationHelper not initialized");
        }
    }

    public static class TransitionBuilder {
        private int[] mExcludeIds;
        private int[] mParentIdForDelay;
        private int mSlideEdge = GravityCompat.START;
        private int mDistance = SetupAnimationHelper.sFragmentTransitionLongDistance;
        private long mDuration = SetupAnimationHelper.sFragmentTransitionDuration;

        public TransitionBuilder() {
            SetupAnimationHelper.checkInitialized();
        }

        public TransitionBuilder setSlideEdge(int i) {
            this.mSlideEdge = i;
            return this;
        }

        public TransitionBuilder setDuration(long j) {
            this.mDuration = j;
            return this;
        }

        public TransitionBuilder setParentIdsForDelay(int[] iArr) {
            this.mParentIdForDelay = iArr;
            return this;
        }

        public TransitionBuilder setExcludeIds(int[] iArr) {
            this.mExcludeIds = iArr;
            return this;
        }

        public Transition build() {
            FadeAndShortSlide fadeAndShortSlide = new FadeAndShortSlide(this.mSlideEdge, this.mParentIdForDelay);
            fadeAndShortSlide.setDistance(this.mDistance);
            fadeAndShortSlide.setDuration(this.mDuration);
            int[] iArr = this.mExcludeIds;
            if (iArr != null) {
                for (int i : iArr) {
                    fadeAndShortSlide.excludeTarget(i, true);
                }
            }
            return fadeAndShortSlide;
        }
    }

    public static void setLongDistance(FadeAndShortSlide fadeAndShortSlide) {
        checkInitialized();
        fadeAndShortSlide.setDistance(sFragmentTransitionLongDistance);
    }

    public static void setShortDistance(FadeAndShortSlide fadeAndShortSlide) {
        checkInitialized();
        fadeAndShortSlide.setDistance(sFragmentTransitionShortDistance);
    }

    public static Animator applyAnimationTimeScale(Animator animator) {
        if (animator instanceof AnimatorSet) {
            Iterator<Animator> it = ((AnimatorSet) animator).getChildAnimations().iterator();
            while (it.hasNext()) {
                applyAnimationTimeScale(it.next());
            }
        }
        if (animator.getDuration() > 0) {
            animator.setDuration((long) (animator.getDuration() * 1.0f));
        }
        animator.setStartDelay((long) (animator.getStartDelay() * 1.0f));
        return animator;
    }

    public static Transition applyAnimationTimeScale(Transition transition) {
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            for (int i = 0; i < transitionCount; i++) {
                applyAnimationTimeScale(transitionSet.getTransitionAt(i));
            }
        }
        if (transition.getDuration() > 0) {
            transition.setDuration((long) (transition.getDuration() * 1.0f));
        }
        transition.setStartDelay((long) (transition.getStartDelay() * 1.0f));
        return transition;
    }

    public static ObjectAnimator createFrameAnimator(ImageView imageView, int[] iArr) {
        return createFrameAnimatorWithDelay(imageView, iArr, 0L);
    }

    public static ObjectAnimator createFrameAnimatorWithDelay(ImageView imageView, int[] iArr, long j) {
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(imageView, "imageResource", iArr);
        objectAnimatorOfInt.setDuration((iArr.length * 1000) / 60);
        objectAnimatorOfInt.setInterpolator(null);
        objectAnimatorOfInt.setStartDelay(j);
        objectAnimatorOfInt.setEvaluator(new TypeEvaluator<Integer>() { // from class: com.android.tv.common.ui.setup.animation.SetupAnimationHelper.1
            @Override // android.animation.TypeEvaluator
            public Integer evaluate(float f, Integer num, Integer num2) {
                return num;
            }
        });
        return objectAnimatorOfInt;
    }

    public static Animator createFadeOutAnimator(final View view, long j, boolean z) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f).setDuration(j);
        if (z) {
            duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.tv.common.ui.setup.animation.SetupAnimationHelper.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    view.setAlpha(1.0f);
                }
            });
        }
        return duration;
    }
}
