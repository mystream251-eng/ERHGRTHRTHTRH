package com.android.tv.common.ui.setup;

import android.os.Bundle;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import com.android.tv.common.ui.setup.animation.FadeAndShortSlide;
import com.android.tv.common.ui.setup.animation.SetupAnimationHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public abstract class SetupFragment extends Fragment {
    public static final int FRAGMENT_ENTER_TRANSITION = 1;
    public static final int FRAGMENT_EXIT_TRANSITION = 2;
    public static final int FRAGMENT_REENTER_TRANSITION = 4;
    public static final int FRAGMENT_RETURN_TRANSITION = 8;
    private boolean mEnterTransitionRunning;
    private OnActionClickListener mOnActionClickListener;
    private Transition.TransitionListener mTransitionListener = new Transition.TransitionListener() { // from class: com.android.tv.common.ui.setup.SetupFragment.1
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
            SetupFragment.this.mEnterTransitionRunning = true;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            SetupFragment.this.mEnterTransitionRunning = false;
            SetupFragment.this.onEnterTransitionEnd();
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface FragmentTransitionType {
    }

    protected int[] getExcludedTargetIds() {
        return null;
    }

    protected abstract int getLayoutResourceId();

    protected int[] getParentIdsForDelay() {
        return null;
    }

    public int[] getSharedElementIds() {
        return null;
    }

    protected void onEnterTransitionEnd() {
    }

    protected boolean isEnterTransitionRunning() {
        return this.mEnterTransitionRunning;
    }

    public SetupFragment() {
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);
        enableFragmentTransition(15);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(getLayoutResourceId(), viewGroup, false);
        viewInflate.requestFocus();
        return viewInflate;
    }

    public OnActionClickListener getOnActionClickListener() {
        return this.mOnActionClickListener;
    }

    public void setOnActionClickListener(OnActionClickListener onActionClickListener) {
        this.mOnActionClickListener = onActionClickListener;
    }

    protected void setOnClickAction(View view, final String str, final int i) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.android.tv.common.ui.setup.SetupFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetupFragment.this.onActionClick(str, i);
            }
        });
    }

    protected void onActionClick(String str, int i) {
        SetupActionHelper.onActionClick(this, str, i);
    }

    @Override // androidx.fragment.app.Fragment
    public void setEnterTransition(Object obj) {
        super.setEnterTransition(obj);
        if (obj instanceof Transition) {
            ((Transition) obj).addListener(this.mTransitionListener);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setReenterTransition(Object obj) {
        super.setReenterTransition(obj);
        if (obj instanceof Transition) {
            ((Transition) obj).addListener(this.mTransitionListener);
        }
    }

    public void enableFragmentTransition(int i) {
        setEnterTransition((i & 1) == 0 ? null : createTransition(GravityCompat.END));
        setExitTransition((i & 2) == 0 ? null : createTransition(GravityCompat.START));
        setReenterTransition((i & 4) == 0 ? null : createTransition(GravityCompat.START));
        setReturnTransition((i & 8) != 0 ? createTransition(GravityCompat.END) : null);
    }

    public void setFragmentTransition(int i, int i2) {
        if (i == 1) {
            setEnterTransition(createTransition(i2));
            return;
        }
        if (i == 2) {
            setExitTransition(createTransition(i2));
        } else if (i == 4) {
            setReenterTransition(createTransition(i2));
        } else {
            if (i != 8) {
                return;
            }
            setReturnTransition(createTransition(i2));
        }
    }

    private Transition createTransition(int i) {
        return new SetupAnimationHelper.TransitionBuilder().setSlideEdge(i).setParentIdsForDelay(getParentIdsForDelay()).setExcludeIds(getExcludedTargetIds()).build();
    }

    public void setShortDistance(int i) {
        if ((i & 1) != 0) {
            Object enterTransition = getEnterTransition();
            if (enterTransition instanceof FadeAndShortSlide) {
                SetupAnimationHelper.setShortDistance((FadeAndShortSlide) enterTransition);
            }
        }
        if ((i & 2) != 0) {
            Object exitTransition = getExitTransition();
            if (exitTransition instanceof FadeAndShortSlide) {
                SetupAnimationHelper.setShortDistance((FadeAndShortSlide) exitTransition);
            }
        }
        if ((i & 4) != 0) {
            Object reenterTransition = getReenterTransition();
            if (reenterTransition instanceof FadeAndShortSlide) {
                SetupAnimationHelper.setShortDistance((FadeAndShortSlide) reenterTransition);
            }
        }
        if ((i & 8) != 0) {
            Object returnTransition = getReturnTransition();
            if (returnTransition instanceof FadeAndShortSlide) {
                SetupAnimationHelper.setShortDistance((FadeAndShortSlide) returnTransition);
            }
        }
    }
}
