package com.dotscreen.farkit.debug;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: CheatCodeHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0019B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0018\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/dotscreen/farkit/debug/CheatCodeHelper;", "", "triggerKeySequence", "", "", "onTriggerListener", "Lcom/dotscreen/farkit/debug/CheatCodeHelper$OnTriggerListener;", "([Ljava/lang/Integer;Lcom/dotscreen/farkit/debug/CheatCodeHelper$OnTriggerListener;)V", "DEBUG", "", "TAG", "", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "position", "resetRunnable", "Ljava/lang/Runnable;", "[Ljava/lang/Integer;", "onKeyDown", "", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyUp", "OnTriggerListener", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class CheatCodeHelper {
    private final boolean DEBUG;
    private final String TAG;
    private final Handler handler;
    private final OnTriggerListener onTriggerListener;
    private int position;
    private final Runnable resetRunnable;
    private final Integer[] triggerKeySequence;

    /* JADX INFO: compiled from: CheatCodeHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/farkit/debug/CheatCodeHelper$OnTriggerListener;", "", "onCheatCodetriggered", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public interface OnTriggerListener {
        void onCheatCodetriggered();
    }

    public final void onKeyDown(int keyCode, KeyEvent event) {
    }

    public CheatCodeHelper(Integer[] triggerKeySequence, OnTriggerListener onTriggerListener) {
        Intrinsics.checkParameterIsNotNull(triggerKeySequence, "triggerKeySequence");
        Intrinsics.checkParameterIsNotNull(onTriggerListener, "onTriggerListener");
        this.triggerKeySequence = triggerKeySequence;
        this.onTriggerListener = onTriggerListener;
        this.TAG = CheatCodeHelper.class.getSimpleName();
        this.handler = new Handler();
        this.resetRunnable = new Runnable() { // from class: com.dotscreen.farkit.debug.CheatCodeHelper$resetRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (this.this$0.DEBUG) {
                    Log.d(this.this$0.TAG, "Reset cheat code input");
                }
                this.this$0.position = 0;
            }
        };
    }

    public final void onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == this.triggerKeySequence[this.position].intValue()) {
            if (this.DEBUG) {
                Log.d(this.TAG, "Next key detected");
            }
            this.position++;
            this.handler.removeCallbacks(this.resetRunnable);
            if (this.position == this.triggerKeySequence.length) {
                if (this.DEBUG) {
                    Log.d(this.TAG, "All keys detected !!");
                }
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
                this.position = 0;
                return;
            }
            this.handler.postDelayed(this.resetRunnable, 1000L);
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.debug.CheatCodeHelper$onKeyUp$1, reason: invalid class name */
    /* JADX INFO: compiled from: CheatCodeHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.farkit.debug.CheatCodeHelper$onKeyUp$1", f = "CheatCodeHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = CheatCodeHelper.this.new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CheatCodeHelper.this.onTriggerListener.onCheatCodetriggered();
            return Unit.INSTANCE;
        }
    }
}
