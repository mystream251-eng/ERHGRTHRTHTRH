package com.android.tv.common.ui.setup.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.core.view.GravityCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FadeAndShortSlide extends Visibility {
    private static final int DEFAULT_DISTANCE = 200;
    private static final String PROPNAME_DELAY = "propname_delay";
    private static final String PROPNAME_SCREEN_POSITION = "android_fadeAndShortSlideTransition_screenPosition";
    private int mDistance;
    private Visibility mFade;
    private int[] mParentIdsForDelay;
    private CalculateSlide mSlideCalculator;
    private int mSlideEdge;
    private static final TimeInterpolator APPEAR_INTERPOLATOR = new DecelerateInterpolator();
    private static final TimeInterpolator DISAPPEAR_INTERPOLATOR = new AccelerateInterpolator();
    private static final CalculateSlide sCalculateStart = new CalculateSlide() { // from class: com.android.tv.common.ui.setup.animation.FadeAndShortSlide.1
        @Override // com.android.tv.common.ui.setup.animation.FadeAndShortSlide.CalculateSlide
        public float getGoneX(ViewGroup viewGroup, View view, int[] iArr, int i) {
            if (viewGroup.getLayoutDirection() == 1) {
                return view.getTranslationX() + i;
            }
            return view.getTranslationX() - i;
        }
    };
    private static final CalculateSlide sCalculateEnd = new CalculateSlide() { // from class: com.android.tv.common.ui.setup.animation.FadeAndShortSlide.2
        @Override // com.android.tv.common.ui.setup.animation.FadeAndShortSlide.CalculateSlide
        public float getGoneX(ViewGroup viewGroup, View view, int[] iArr, int i) {
            if (viewGroup.getLayoutDirection() == 1) {
                return view.getTranslationX() - i;
            }
            return view.getTranslationX() + i;
        }
    };
    private static final ViewPositionComparator sViewPositionComparator = new ViewPositionComparator();

    private static abstract class CalculateSlide {
        public abstract float getGoneX(ViewGroup viewGroup, View view, int[] iArr, int i);

        private CalculateSlide() {
        }
    }

    public FadeAndShortSlide() {
        this(GravityCompat.START);
    }

    public FadeAndShortSlide(int i) {
        this(i, null);
    }

    public FadeAndShortSlide(int i, int[] iArr) {
        this.mSlideCalculator = sCalculateEnd;
        this.mFade = new Fade();
        this.mDistance = 200;
        setSlideEdge(i);
        this.mParentIdsForDelay = iArr;
    }

    @Override // android.transition.Transition
    public void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
        super.setEpicenterCallback(epicenterCallback);
        this.mFade.setEpicenterCallback(epicenterCallback);
    }

    private void captureValues(TransitionValues transitionValues) {
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put(PROPNAME_SCREEN_POSITION, iArr);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x004c  */
    private int getDelayOrder(View view, boolean z) {
        View viewFindParentForDelay;
        if (this.mParentIdsForDelay == null || (viewFindParentForDelay = findParentForDelay(view)) == null || !(viewFindParentForDelay instanceof ViewGroup)) {
            return -1;
        }
        ArrayList arrayList = new ArrayList();
        getTransitionTargets((ViewGroup) viewFindParentForDelay, arrayList);
        sViewPositionComparator.mParentForDelay = viewFindParentForDelay;
        boolean z2 = true;
        sViewPositionComparator.mIsLtr = view.getLayoutDirection() == 0;
        ViewPositionComparator viewPositionComparator = sViewPositionComparator;
        boolean z3 = viewPositionComparator.mIsLtr;
        int i = GravityCompat.START;
        if (z3) {
            int i2 = this.mSlideEdge;
            if (z) {
                i = 8388613;
            }
            if (i2 != i) {
                z2 = false;
            }
        } else {
            int i3 = this.mSlideEdge;
            if (!z) {
                i = 8388613;
            }
            if (i3 != i) {
                z2 = false;
            }
        }
        viewPositionComparator.mToLeft = z2;
        Collections.sort(arrayList, sViewPositionComparator);
        return arrayList.indexOf(view);
    }

    private View findParentForDelay(View view) {
        if (isParentForDelay(view.getId())) {
            return view;
        }
        while (view.getParent() instanceof View) {
            view = (View) view.getParent();
            if (isParentForDelay(view.getId())) {
                return view;
            }
        }
        return null;
    }

    private boolean isParentForDelay(int i) {
        for (int i2 : this.mParentIdsForDelay) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001c  */
    private void getTransitionTargets(ViewGroup viewGroup, List<View> list) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                ViewGroup viewGroup2 = (ViewGroup) childAt;
                if (!viewGroup2.isTransitionGroup()) {
                    getTransitionTargets(viewGroup2, list);
                } else {
                    list.add(childAt);
                }
            } else {
                list.add(childAt);
            }
        }
    }

    @Override // android.transition.Visibility, android.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        this.mFade.captureStartValues(transitionValues);
        captureValues(transitionValues);
        int delayOrder = getDelayOrder(transitionValues.view, false);
        if (delayOrder > 0) {
            transitionValues.values.put(PROPNAME_DELAY, Long.valueOf(((long) delayOrder) * SetupAnimationHelper.DELAY_BETWEEN_SIBLINGS_MS));
        }
    }

    @Override // android.transition.Visibility, android.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        this.mFade.captureEndValues(transitionValues);
        captureValues(transitionValues);
        int delayOrder = getDelayOrder(transitionValues.view, true);
        if (delayOrder > 0) {
            transitionValues.values.put(PROPNAME_DELAY, Long.valueOf(((long) delayOrder) * SetupAnimationHelper.DELAY_BETWEEN_SIBLINGS_MS));
        }
    }

    public void setSlideEdge(int i) {
        this.mSlideEdge = i;
        if (i == 8388611) {
            this.mSlideCalculator = sCalculateStart;
        } else {
            if (i == 8388613) {
                this.mSlideCalculator = sCalculateEnd;
                return;
            }
            throw new IllegalArgumentException("Invalid slide direction");
        }
    }

    @Override // android.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues2.values.get(PROPNAME_SCREEN_POSITION);
        Animator animatorCreateAnimation = TranslationAnimationCreator.createAnimation(view, transitionValues2, iArr[0], this.mSlideCalculator.getGoneX(viewGroup, view, iArr, this.mDistance), view.getTranslationX(), APPEAR_INTERPOLATOR, this);
        if (animatorCreateAnimation == null) {
            return null;
        }
        this.mFade.setInterpolator(APPEAR_INTERPOLATOR);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorCreateAnimation).with(this.mFade.onAppear(viewGroup, view, transitionValues, transitionValues2));
        Long l = (Long) transitionValues2.values.get(PROPNAME_DELAY);
        if (l != null) {
            animatorSet.setStartDelay(l.longValue());
        }
        return animatorSet;
    }

    @Override // android.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, final View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues.values.get(PROPNAME_SCREEN_POSITION);
        Animator animatorCreateAnimation = TranslationAnimationCreator.createAnimation(view, transitionValues, iArr[0], view.getTranslationX(), this.mSlideCalculator.getGoneX(viewGroup, view, iArr, this.mDistance), DISAPPEAR_INTERPOLATOR, this);
        if (animatorCreateAnimation == null) {
            return null;
        }
        this.mFade.setInterpolator(DISAPPEAR_INTERPOLATOR);
        final Animator animatorOnDisappear = this.mFade.onDisappear(viewGroup, view, transitionValues, transitionValues2);
        if (animatorOnDisappear == null) {
            return null;
        }
        animatorOnDisappear.addListener(new AnimatorListenerAdapter() { // from class: com.android.tv.common.ui.setup.animation.FadeAndShortSlide.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animatorOnDisappear.removeListener(this);
                view.setAlpha(0.0f);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorCreateAnimation).with(animatorOnDisappear);
        Long l = (Long) transitionValues.values.get(PROPNAME_DELAY);
        if (l != null) {
            animatorSet.setStartDelay(l.longValue());
        }
        return animatorSet;
    }

    @Override // android.transition.Transition
    public Transition addListener(Transition.TransitionListener transitionListener) {
        this.mFade.addListener(transitionListener);
        return super.addListener(transitionListener);
    }

    @Override // android.transition.Transition
    public Transition removeListener(Transition.TransitionListener transitionListener) {
        this.mFade.removeListener(transitionListener);
        return super.removeListener(transitionListener);
    }

    @Override // android.transition.Transition
    public Transition clone() {
        FadeAndShortSlide fadeAndShortSlide = (FadeAndShortSlide) super.clone();
        fadeAndShortSlide.mFade = (Visibility) this.mFade.clone();
        return fadeAndShortSlide;
    }

    @Override // android.transition.Transition
    public Transition setDuration(long j) {
        long jApplyAnimationTimeScale = SetupAnimationHelper.applyAnimationTimeScale(j);
        this.mFade.setDuration(jApplyAnimationTimeScale);
        return super.setDuration(jApplyAnimationTimeScale);
    }

    public void setDistance(int i) {
        this.mDistance = i;
    }

    private static class ViewPositionComparator implements Comparator<View> {
        boolean mIsLtr;
        View mParentForDelay;
        boolean mToLeft;

        private ViewPositionComparator() {
        }

        @Override // java.util.Comparator
        public int compare(View view, View view2) {
            int relativeRight;
            int relativeRight2;
            if (this.mIsLtr) {
                relativeRight = getRelativeLeft(view, this.mParentForDelay);
                relativeRight2 = getRelativeLeft(view2, this.mParentForDelay);
            } else {
                relativeRight = getRelativeRight(view, this.mParentForDelay);
                relativeRight2 = getRelativeRight(view2, this.mParentForDelay);
            }
            if (this.mToLeft) {
                if (relativeRight > relativeRight2) {
                    return 1;
                }
                if (relativeRight < relativeRight2) {
                    return -1;
                }
            } else {
                if (relativeRight > relativeRight2) {
                    return -1;
                }
                if (relativeRight < relativeRight2) {
                    return 1;
                }
            }
            return Integer.compare(getRelativeTop(view, this.mParentForDelay), getRelativeTop(view2, this.mParentForDelay));
        }

        private int getRelativeLeft(View view, View view2) {
            int left = view.getLeft();
            for (ViewParent parent = view.getParent(); (parent instanceof View) && parent != view2; parent = parent.getParent()) {
                left += ((View) parent).getLeft();
            }
            return left;
        }

        private int getRelativeRight(View view, View view2) {
            int right = view.getRight();
            for (ViewParent parent = view.getParent(); (parent instanceof View) && parent != view2; parent = parent.getParent()) {
                right += ((View) parent).getLeft();
            }
            return right;
        }

        private int getRelativeTop(View view, View view2) {
            int top = view.getTop();
            for (ViewParent parent = view.getParent(); (parent instanceof View) && parent != view2; parent = parent.getParent()) {
                top += ((View) parent).getTop();
            }
            return top;
        }
    }
}
