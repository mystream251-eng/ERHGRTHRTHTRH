package com.dotscreen.portal.utils;

import android.content.Context;
import android.util.Log;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BootSequenceManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0002:;B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010-\u001a\b\u0012\u0004\u0012\u00020&0.J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u001aH\u0002J\u000e\u00102\u001a\u0002002\u0006\u00103\u001a\u00020\u0018J \u00104\u001a\u0002002\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020&J\u000e\u00108\u001a\u0002002\u0006\u00103\u001a\u00020\u0018J\n\u00109\u001a\u00020\u001a*\u00020\u001aR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b,\u0010\u001c¨\u0006<"}, d2 = {"Lcom/dotscreen/portal/utils/BootSequenceManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "currentState", "Lcom/dotscreen/portal/utils/BootSequenceManager$State;", "getCurrentState", "()Lcom/dotscreen/portal/utils/BootSequenceManager$State;", "setCurrentState", "(Lcom/dotscreen/portal/utils/BootSequenceManager$State;)V", "currentStep", "Lcom/dotscreen/portal/utils/BootSequenceManager$Steps;", "getCurrentStep", "()Lcom/dotscreen/portal/utils/BootSequenceManager$Steps;", "setCurrentStep", "(Lcom/dotscreen/portal/utils/BootSequenceManager$Steps;)V", "listeners", "", "Lcom/dotscreen/portal/utils/BootSequenceListener;", "maxTimeoutRetry", "", "getMaxTimeoutRetry", "()I", "setMaxTimeoutRetry", "(I)V", "minTimeoutRetry", "getMinTimeoutRetry", "setMinTimeoutRetry", "retryCount", "getRetryCount", "setRetryCount", "stepSuccess", "", "getStepSuccess", "()Z", "setStepSuccess", "(Z)V", "waitingDelay", "getWaitingDelay", "checkServerReachable", "Lio/reactivex/Single;", "notifyRetry", "", "delay", "registerBootSequenceListener", "bootSequenceListener", "setStep", "step", "state", "success", "unregisterBootSequenceListener", "length", "State", "Steps", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class BootSequenceManager {
    private final String TAG;
    private final Context context;
    private State currentState;
    private Steps currentStep;
    private final List<BootSequenceListener> listeners;
    private int maxTimeoutRetry;
    private int minTimeoutRetry;
    private int retryCount;
    private boolean stepSuccess;

    /* JADX INFO: compiled from: BootSequenceManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/utils/BootSequenceManager$State;", "", "(Ljava/lang/String;I)V", "WILL_START", "IN_PROGRESS", "FINISH", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum State {
        WILL_START,
        IN_PROGRESS,
        FINISH
    }

    /* JADX INFO: compiled from: BootSequenceManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/utils/BootSequenceManager$Steps;", "", "(Ljava/lang/String;I)V", "RETRIEVE_CONFIG", "RETRIEVE_FEATURES", "LOGIN_RIGHTV", "LOGIN_ANNONYMOUS_COMPASS", "GET_PROFILE_LIST_1", "GET_PROFILE_LIST_2", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum Steps {
        RETRIEVE_CONFIG,
        RETRIEVE_FEATURES,
        LOGIN_RIGHTV,
        LOGIN_ANNONYMOUS_COMPASS,
        GET_PROFILE_LIST_1,
        GET_PROFILE_LIST_2
    }

    public BootSequenceManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = BootSequenceManager.class.getSimpleName();
        this.listeners = new ArrayList();
        this.currentStep = Steps.RETRIEVE_CONFIG;
        this.currentState = State.WILL_START;
        this.minTimeoutRetry = 60;
        this.maxTimeoutRetry = 180;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Steps getCurrentStep() {
        return this.currentStep;
    }

    public final void setCurrentStep(Steps steps) {
        Intrinsics.checkParameterIsNotNull(steps, "<set-?>");
        this.currentStep = steps;
    }

    public final State getCurrentState() {
        return this.currentState;
    }

    public final void setCurrentState(State state) {
        Intrinsics.checkParameterIsNotNull(state, "<set-?>");
        this.currentState = state;
    }

    public final boolean getStepSuccess() {
        return this.stepSuccess;
    }

    public final void setStepSuccess(boolean z) {
        this.stepSuccess = z;
    }

    public final int getRetryCount() {
        return this.retryCount;
    }

    public final void setRetryCount(int i) {
        this.retryCount = i;
    }

    public final int getMinTimeoutRetry() {
        return this.minTimeoutRetry;
    }

    public final void setMinTimeoutRetry(int i) {
        this.minTimeoutRetry = i;
    }

    public final int getMaxTimeoutRetry() {
        return this.maxTimeoutRetry;
    }

    public final void setMaxTimeoutRetry(int i) {
        this.maxTimeoutRetry = i;
    }

    public final int getWaitingDelay() {
        Log.v(this.TAG, "Range : " + this.minTimeoutRetry + " | " + this.maxTimeoutRetry);
        int iRandom = RangesKt.random(new IntRange(this.minTimeoutRetry, this.maxTimeoutRetry), Random.INSTANCE);
        notifyRetry(iRandom);
        return iRandom;
    }

    public final void registerBootSequenceListener(BootSequenceListener bootSequenceListener) {
        Intrinsics.checkParameterIsNotNull(bootSequenceListener, "bootSequenceListener");
        this.listeners.add(bootSequenceListener);
    }

    public final void unregisterBootSequenceListener(BootSequenceListener bootSequenceListener) {
        Intrinsics.checkParameterIsNotNull(bootSequenceListener, "bootSequenceListener");
        this.listeners.remove(bootSequenceListener);
    }

    public static /* synthetic */ void setStep$default(BootSequenceManager bootSequenceManager, Steps steps, State state, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        bootSequenceManager.setStep(steps, state, z);
    }

    public final void setStep(Steps step, State state, boolean success) {
        Intrinsics.checkParameterIsNotNull(step, "step");
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.currentStep = step;
        this.currentState = state;
        this.stepSuccess = success;
        Log.v(this.TAG, "Step: " + this.currentStep + ", state: " + this.currentState + ", success: " + this.stepSuccess);
        if (success) {
            return;
        }
        Iterator<BootSequenceListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onStepFailed(this.currentStep, this.currentState);
        }
    }

    public final Single<Boolean> checkServerReachable() {
        UserService userService = CommonUtils.getBOService(this.context).getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        MegacableUserService megacableUserService = (MegacableUserService) userService;
        Log.v("BootSequenceManager", "will try to check backend accessibility");
        Single<Boolean> singleOnErrorReturn = Single.zip(megacableUserService.checkUIOptimizerStatus("readiness").map((Function) new Function<T, R>() { // from class: com.dotscreen.portal.utils.BootSequenceManager.checkServerReachable.1
            @Override // io.reactivex.functions.Function
            public final String apply(String it) throws Exception {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BootSequenceManager bootSequenceManager = BootSequenceManager.this;
                int retryCount = bootSequenceManager.getRetryCount();
                bootSequenceManager.setRetryCount(retryCount + 1);
                if (retryCount >= 2) {
                    return it;
                }
                throw new Exception("Test SERVER DOWN");
            }
        }), megacableUserService.checkRighTVStatus(), megacableUserService.checkCompassStatus(), new Function3<String, String, String, Boolean>() { // from class: com.dotscreen.portal.utils.BootSequenceManager.checkServerReachable.2
            @Override // io.reactivex.functions.Function3
            public /* bridge */ /* synthetic */ Boolean apply(String str, String str2, String str3) {
                return Boolean.valueOf(apply2(str, str2, str3));
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public final boolean apply2(String uiopStatus, String rightvStatus, String compassStatus) {
                Intrinsics.checkParameterIsNotNull(uiopStatus, "uiopStatus");
                Intrinsics.checkParameterIsNotNull(rightvStatus, "rightvStatus");
                Intrinsics.checkParameterIsNotNull(compassStatus, "compassStatus");
                Log.d(BootSequenceManager.this.TAG, "UIOP Status: " + uiopStatus + " | rightv Status: " + rightvStatus + " | compass Status: " + compassStatus);
                return true;
            }
        }).onErrorReturn(new Function<Throwable, Boolean>() { // from class: com.dotscreen.portal.utils.BootSequenceManager.checkServerReachable.3
            @Override // io.reactivex.functions.Function
            public /* bridge */ /* synthetic */ Boolean apply(Throwable th) {
                return Boolean.valueOf(apply2(th));
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public final boolean apply2(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.v("BootSequenceManager", "Check backend accessibility failed: " + it);
                Iterator it2 = BootSequenceManager.this.listeners.iterator();
                while (it2.hasNext()) {
                    ((BootSequenceListener) it2.next()).onStepFailed(BootSequenceManager.this.getCurrentStep(), BootSequenceManager.this.getCurrentState());
                }
                return false;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorReturn, "Single.zip(\n            …          false\n        }");
        return singleOnErrorReturn;
    }

    private final void notifyRetry(int delay) {
        Iterator<BootSequenceListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onStepFailed(delay);
        }
    }

    public final int length(int i) {
        if (i != 0) {
            return 1 + ((int) Math.log10(Math.abs(i)));
        }
        return 1;
    }
}
