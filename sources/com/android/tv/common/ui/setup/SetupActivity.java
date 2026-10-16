package com.android.tv.common.ui.setup;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.android.tv.common.R;
import com.android.tv.common.WeakHandler;
import com.android.tv.common.ui.setup.animation.SetupAnimationHelper;

/* JADX INFO: loaded from: classes.dex */
public abstract class SetupActivity extends FragmentActivity implements OnActionClickListener {
    private static final int MSG_EXECUTE_ACTION = 1;
    private long mFragmentTransitionDuration;
    private boolean mShowInitialFragment = true;
    private final Handler mHandler = new SetupActivityHandler(this);

    protected void executeAction(String str, int i) {
    }

    protected abstract Fragment onCreateInitialFragment();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setup);
        this.mFragmentTransitionDuration = getResources().getInteger(R.integer.setup_fragment_transition_duration);
        if (bundle == null) {
            getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.tv.common.ui.setup.SetupActivity.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    SetupActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnPreDrawListener(this);
                    SetupActivity.this.showInitialFragment();
                    return true;
                }
            });
        } else {
            this.mShowInitialFragment = false;
        }
    }

    protected void showInitialFragment() {
        Fragment fragmentOnCreateInitialFragment;
        if (this.mShowInitialFragment && (fragmentOnCreateInitialFragment = onCreateInitialFragment()) != null) {
            showFragment(fragmentOnCreateInitialFragment, false);
            this.mShowInitialFragment = false;
        }
    }

    protected FragmentTransaction showFragment(Fragment fragment, boolean z) {
        int[] sharedElementIds;
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        if ((fragment instanceof SetupFragment) && (sharedElementIds = ((SetupFragment) fragment).getSharedElementIds()) != null && sharedElementIds.length > 0) {
            Transition transitionInflateTransition = TransitionInflater.from(this).inflateTransition(R.transition.transition_action_background);
            transitionInflateTransition.setDuration(getSharedElementTransitionDuration());
            SetupAnimationHelper.applyAnimationTimeScale(transitionInflateTransition);
            fragment.setSharedElementEnterTransition(transitionInflateTransition);
            fragment.setSharedElementReturnTransition(transitionInflateTransition);
            for (int i : sharedElementIds) {
                View viewFindViewById = findViewById(i);
                if (viewFindViewById != null) {
                    fragmentTransactionBeginTransaction.addSharedElement(viewFindViewById, viewFindViewById.getTransitionName());
                }
            }
        }
        String canonicalName = fragment.getClass().getCanonicalName();
        if (z) {
            fragmentTransactionBeginTransaction.addToBackStack(canonicalName);
        }
        fragmentTransactionBeginTransaction.replace(R.id.fragment_container, fragment, canonicalName).commit();
        return fragmentTransactionBeginTransaction;
    }

    @Override // com.android.tv.common.ui.setup.OnActionClickListener
    public void onActionClick(String str, int i) {
        if (this.mHandler.hasMessages(1)) {
            return;
        }
        executeAction(str, i);
    }

    protected void executeActionWithDelay(Runnable runnable, int i) {
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1, runnable), i);
    }

    private long getSharedElementTransitionDuration() {
        return (this.mFragmentTransitionDuration + SetupAnimationHelper.DELAY_BETWEEN_SIBLINGS_MS) * 2;
    }

    private static class SetupActivityHandler extends WeakHandler<SetupActivity> {
        SetupActivityHandler(SetupActivity setupActivity) {
            super(Looper.getMainLooper(), setupActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.tv.common.WeakHandler
        public void handleMessage(Message message, SetupActivity setupActivity) {
            if (message.what == 1) {
                ((Runnable) message.obj).run();
            }
        }
    }
}
