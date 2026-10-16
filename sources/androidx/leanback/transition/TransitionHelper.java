package androidx.leanback.transition;

import android.animation.TimeInterpolator;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TransitionHelper {
    public static final int FADE_IN = 1;
    public static final int FADE_OUT = 2;

    public static boolean systemSupportsEntranceTransitions() {
        return Build.VERSION.SDK_INT >= 21;
    }

    private static class TransitionStub {
        ArrayList<TransitionListener> mTransitionListeners;

        TransitionStub() {
        }
    }

    public static Object getSharedElementEnterTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getSharedElementEnterTransition();
        }
        return null;
    }

    public static void setSharedElementEnterTransition(Window window, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setSharedElementEnterTransition((Transition) obj);
        }
    }

    public static Object getSharedElementReturnTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getSharedElementReturnTransition();
        }
        return null;
    }

    public static void setSharedElementReturnTransition(Window window, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setSharedElementReturnTransition((Transition) obj);
        }
    }

    public static Object getSharedElementExitTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getSharedElementExitTransition();
        }
        return null;
    }

    public static Object getSharedElementReenterTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getSharedElementReenterTransition();
        }
        return null;
    }

    public static Object getEnterTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getEnterTransition();
        }
        return null;
    }

    public static void setEnterTransition(Window window, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setEnterTransition((Transition) obj);
        }
    }

    public static Object getReturnTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getReturnTransition();
        }
        return null;
    }

    public static void setReturnTransition(Window window, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setReturnTransition((Transition) obj);
        }
    }

    public static Object getExitTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getExitTransition();
        }
        return null;
    }

    public static Object getReenterTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getReenterTransition();
        }
        return null;
    }

    public static Object createScene(ViewGroup viewGroup, Runnable runnable) {
        if (Build.VERSION.SDK_INT < 19) {
            return runnable;
        }
        Scene scene = new Scene(viewGroup);
        scene.setEnterAction(runnable);
        return scene;
    }

    public static Object createChangeBounds(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            CustomChangeBounds customChangeBounds = new CustomChangeBounds();
            customChangeBounds.setReparent(z);
            return customChangeBounds;
        }
        return new TransitionStub();
    }

    public static Object createChangeTransform() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ChangeTransform();
        }
        return new TransitionStub();
    }

    public static void setChangeBoundsStartDelay(Object obj, View view, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((CustomChangeBounds) obj).setStartDelay(view, i);
        }
    }

    public static void setChangeBoundsStartDelay(Object obj, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((CustomChangeBounds) obj).setStartDelay(i, i2);
        }
    }

    public static void setChangeBoundsStartDelay(Object obj, String str, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((CustomChangeBounds) obj).setStartDelay(str, i);
        }
    }

    public static void setChangeBoundsDefaultStartDelay(Object obj, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((CustomChangeBounds) obj).setDefaultStartDelay(i);
        }
    }

    public static Object createTransitionSet(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.setOrdering(z ? 1 : 0);
            return transitionSet;
        }
        return new TransitionStub();
    }

    public static Object createSlide(int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            SlideKitkat slideKitkat = new SlideKitkat();
            slideKitkat.setSlideEdge(i);
            return slideKitkat;
        }
        return new TransitionStub();
    }

    public static Object createScale() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ChangeTransform();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return new Scale();
        }
        return new TransitionStub();
    }

    public static void addTransition(Object obj, Object obj2) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((TransitionSet) obj).addTransition((Transition) obj2);
        }
    }

    public static void exclude(Object obj, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).excludeTarget(i, z);
        }
    }

    public static void exclude(Object obj, View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).excludeTarget(view, z);
        }
    }

    public static void excludeChildren(Object obj, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).excludeChildren(i, z);
        }
    }

    public static void excludeChildren(Object obj, View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).excludeChildren(view, z);
        }
    }

    public static void include(Object obj, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).addTarget(i);
        }
    }

    public static void include(Object obj, View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).addTarget(view);
        }
    }

    public static void setStartDelay(Object obj, long j) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).setStartDelay(j);
        }
    }

    public static void setDuration(Object obj, long j) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).setDuration(j);
        }
    }

    public static Object createAutoTransition() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new AutoTransition();
        }
        return new TransitionStub();
    }

    public static Object createFadeTransition(int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            return new Fade(i);
        }
        return new TransitionStub();
    }

    public static void addTransitionListener(Object obj, final TransitionListener transitionListener) {
        if (transitionListener == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            transitionListener.mImpl = new Transition.TransitionListener() { // from class: androidx.leanback.transition.TransitionHelper.1
                @Override // android.transition.Transition.TransitionListener
                public void onTransitionStart(Transition transition) {
                    transitionListener.onTransitionStart(transition);
                }

                @Override // android.transition.Transition.TransitionListener
                public void onTransitionResume(Transition transition) {
                    transitionListener.onTransitionResume(transition);
                }

                @Override // android.transition.Transition.TransitionListener
                public void onTransitionPause(Transition transition) {
                    transitionListener.onTransitionPause(transition);
                }

                @Override // android.transition.Transition.TransitionListener
                public void onTransitionEnd(Transition transition) {
                    transitionListener.onTransitionEnd(transition);
                }

                @Override // android.transition.Transition.TransitionListener
                public void onTransitionCancel(Transition transition) {
                    transitionListener.onTransitionCancel(transition);
                }
            };
            ((Transition) obj).addListener((Transition.TransitionListener) transitionListener.mImpl);
        } else {
            TransitionStub transitionStub = (TransitionStub) obj;
            if (transitionStub.mTransitionListeners == null) {
                transitionStub.mTransitionListeners = new ArrayList<>();
            }
            transitionStub.mTransitionListeners.add(transitionListener);
        }
    }

    public static void removeTransitionListener(Object obj, TransitionListener transitionListener) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (transitionListener == null || transitionListener.mImpl == null) {
                return;
            }
            ((Transition) obj).removeListener((Transition.TransitionListener) transitionListener.mImpl);
            transitionListener.mImpl = null;
            return;
        }
        TransitionStub transitionStub = (TransitionStub) obj;
        if (transitionStub.mTransitionListeners != null) {
            transitionStub.mTransitionListeners.remove(transitionListener);
        }
    }

    public static void runTransition(Object obj, Object obj2) {
        if (Build.VERSION.SDK_INT >= 19) {
            TransitionManager.go((Scene) obj, (Transition) obj2);
            return;
        }
        TransitionStub transitionStub = (TransitionStub) obj2;
        if (transitionStub != null && transitionStub.mTransitionListeners != null) {
            int size = transitionStub.mTransitionListeners.size();
            for (int i = 0; i < size; i++) {
                transitionStub.mTransitionListeners.get(i).onTransitionStart(obj2);
            }
        }
        Runnable runnable = (Runnable) obj;
        if (runnable != null) {
            runnable.run();
        }
        if (transitionStub == null || transitionStub.mTransitionListeners == null) {
            return;
        }
        int size2 = transitionStub.mTransitionListeners.size();
        for (int i2 = 0; i2 < size2; i2++) {
            transitionStub.mTransitionListeners.get(i2).onTransitionEnd(obj2);
        }
    }

    public static void setInterpolator(Object obj, Object obj2) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).setInterpolator((TimeInterpolator) obj2);
        }
    }

    public static void addTarget(Object obj, View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Transition) obj).addTarget(view);
        }
    }

    public static Object createDefaultInterpolator(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, 17563663);
        }
        return null;
    }

    public static Object loadTransition(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            return TransitionInflater.from(context).inflateTransition(i);
        }
        return new TransitionStub();
    }

    public static void setEnterTransition(Fragment fragment, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            fragment.setEnterTransition((Transition) obj);
        }
    }

    public static void setExitTransition(Fragment fragment, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            fragment.setExitTransition((Transition) obj);
        }
    }

    public static void setSharedElementEnterTransition(Fragment fragment, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            fragment.setSharedElementEnterTransition((Transition) obj);
        }
    }

    public static void addSharedElement(FragmentTransaction fragmentTransaction, View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            fragmentTransaction.addSharedElement(view, str);
        }
    }

    public static Object createFadeAndShortSlide(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new FadeAndShortSlide(i);
        }
        return new TransitionStub();
    }

    public static Object createFadeAndShortSlide(int i, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            FadeAndShortSlide fadeAndShortSlide = new FadeAndShortSlide(i);
            fadeAndShortSlide.setDistance(f);
            return fadeAndShortSlide;
        }
        return new TransitionStub();
    }

    public static void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
        }
    }

    public static void setTransitionGroup(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            viewGroup.setTransitionGroup(z);
        }
    }

    public static void setEpicenterCallback(Object obj, final TransitionEpicenterCallback transitionEpicenterCallback) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (transitionEpicenterCallback == null) {
                ((Transition) obj).setEpicenterCallback(null);
            } else {
                ((Transition) obj).setEpicenterCallback(new Transition.EpicenterCallback() { // from class: androidx.leanback.transition.TransitionHelper.2
                    @Override // android.transition.Transition.EpicenterCallback
                    public Rect onGetEpicenter(Transition transition) {
                        return transitionEpicenterCallback.onGetEpicenter(transition);
                    }
                });
            }
        }
    }

    private TransitionHelper() {
    }
}
