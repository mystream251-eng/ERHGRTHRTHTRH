package com.dotscreen.tv.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.tv.common.R;
import com.dotscreen.farkit.debug.CheatCodeHelper;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DirectChannelAccessView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002KLB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010E\u001a\u00020\u0007H\u0002J\b\u0010F\u001a\u00020GH\u0014J\u000e\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020\u0007J\b\u0010J\u001a\u00020GH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R;\u0010\u000e\u001a#\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00130\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R(\u0010,\u001a\u0004\u0018\u00010+2\b\u0010*\u001a\u0004\u0018\u00010+@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u000102X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00107\u001a\u00020\n2\u0006\u0010*\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001e\u0010<\u001a\u00020\n2\u0006\u0010*\u001a\u00020\n@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b=\u0010;R\u000e\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010@\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010D¨\u0006M"}, d2 = {"Lcom/dotscreen/tv/ui/DirectChannelAccessView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "DEBUG", "", "TAG", "", "kotlin.jvm.PlatformType", "channelName", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "channelNumber", "Lio/reactivex/Maybe;", "getChannelName", "()Lkotlin/jvm/functions/Function1;", "setChannelName", "(Lkotlin/jvm/functions/Function1;)V", "channelNameTextView", "Landroid/widget/TextView;", "channelZappingNumberTextView", "cheatCodeHelper", "Lcom/dotscreen/farkit/debug/CheatCodeHelper;", "currentZappingNumber", "disposable", "Lio/reactivex/disposables/Disposable;", "internalHandler", "Landroid/os/Handler;", "internalRootView", "Landroid/view/View;", "onChannelSelectedListener", "Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnChannelSelectedListener;", "getOnChannelSelectedListener", "()Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnChannelSelectedListener;", "setOnChannelSelectedListener", "(Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnChannelSelectedListener;)V", "value", "Lcom/dotscreen/farkit/debug/CheatCodeHelper$OnTriggerListener;", "onCheatCodeListener", "getOnCheatCodeListener", "()Lcom/dotscreen/farkit/debug/CheatCodeHelper$OnTriggerListener;", "setOnCheatCodeListener", "(Lcom/dotscreen/farkit/debug/CheatCodeHelper$OnTriggerListener;)V", "onVisibilityChangedListener", "Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnVisibilityChangedListener;", "getOnVisibilityChangedListener", "()Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnVisibilityChangedListener;", "setOnVisibilityChangedListener", "(Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnVisibilityChangedListener;)V", "showTitleName", "getShowTitleName", "()Z", "setShowTitleName", "(Z)V", "visible", "setVisible", "zapRunnable", "Ljava/lang/Runnable;", "zappingNumberLength", "getZappingNumberLength", "()I", "setZappingNumberLength", "(I)V", "convertChannelNumber", "onDetachedFromWindow", "", "onKeyCode", "keyCode", "updateChannelNumber", "OnChannelSelectedListener", "OnVisibilityChangedListener", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DirectChannelAccessView extends FrameLayout {
    private final boolean DEBUG;
    private final String TAG;
    private Function1<? super Integer, ? extends Maybe<String>> channelName;
    private final TextView channelNameTextView;
    private final TextView channelZappingNumberTextView;
    private CheatCodeHelper cheatCodeHelper;
    private String currentZappingNumber;
    private Disposable disposable;
    private final Handler internalHandler;
    private final View internalRootView;
    private OnChannelSelectedListener onChannelSelectedListener;
    private CheatCodeHelper.OnTriggerListener onCheatCodeListener;
    private OnVisibilityChangedListener onVisibilityChangedListener;
    private boolean showTitleName;
    private boolean visible;
    private final Runnable zapRunnable;
    private int zappingNumberLength;

    /* JADX INFO: compiled from: DirectChannelAccessView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnChannelSelectedListener;", "", "onChannelSelected", "", "channelNumber", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnChannelSelectedListener {
        void onChannelSelected(int channelNumber);
    }

    /* JADX INFO: compiled from: DirectChannelAccessView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnVisibilityChangedListener;", "", "onVisibilityChanged", "", "visible", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnVisibilityChangedListener {
        void onVisibilityChanged(boolean visible);
    }

    public DirectChannelAccessView(Context context) {
        this(context, null, 0, 6, null);
    }

    public DirectChannelAccessView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DirectChannelAccessView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = DirectChannelAccessView.class.getSimpleName();
        this.DEBUG = true;
        this.zappingNumberLength = 4;
        this.channelName = new Function1<Integer, Maybe<String>>() { // from class: com.dotscreen.tv.ui.DirectChannelAccessView$channelName$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Maybe<String> invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Maybe<String> invoke(int i2) {
                Maybe<String> maybeEmpty = Maybe.empty();
                Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "Maybe.empty()");
                return maybeEmpty;
            }
        };
        this.internalHandler = new Handler();
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.direct_channel_access_view, this);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…hannel_access_view, this)");
        this.internalRootView = viewInflate;
        View viewFindViewById = findViewById(R.id.channel_zap_number);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.channel_zap_number)");
        this.channelZappingNumberTextView = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.channel_name);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.channel_name)");
        TextView textView = (TextView) viewFindViewById2;
        this.channelNameTextView = textView;
        textView.setText("");
        this.internalRootView.setVisibility(4);
        setClipChildren(false);
        this.showTitleName = true;
        this.zapRunnable = new Runnable() { // from class: com.dotscreen.tv.ui.DirectChannelAccessView$zapRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (this.this$0.DEBUG) {
                    Log.d(this.this$0.TAG, "zapRunnable()");
                }
                DirectChannelAccessView.OnChannelSelectedListener onChannelSelectedListener = this.this$0.getOnChannelSelectedListener();
                if (onChannelSelectedListener != null) {
                    onChannelSelectedListener.onChannelSelected(this.this$0.convertChannelNumber());
                }
                this.this$0.currentZappingNumber = (String) null;
                this.this$0.channelNameTextView.setText("");
                this.this$0.setVisible(false);
            }
        };
    }

    public /* synthetic */ DirectChannelAccessView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final int getZappingNumberLength() {
        return this.zappingNumberLength;
    }

    public final void setZappingNumberLength(int i) {
        this.zappingNumberLength = i;
    }

    public final Function1<Integer, Maybe<String>> getChannelName() {
        return this.channelName;
    }

    public final void setChannelName(Function1<? super Integer, ? extends Maybe<String>> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.channelName = function1;
    }

    public final OnChannelSelectedListener getOnChannelSelectedListener() {
        return this.onChannelSelectedListener;
    }

    public final void setOnChannelSelectedListener(OnChannelSelectedListener onChannelSelectedListener) {
        this.onChannelSelectedListener = onChannelSelectedListener;
    }

    public final OnVisibilityChangedListener getOnVisibilityChangedListener() {
        return this.onVisibilityChangedListener;
    }

    public final void setOnVisibilityChangedListener(OnVisibilityChangedListener onVisibilityChangedListener) {
        this.onVisibilityChangedListener = onVisibilityChangedListener;
    }

    public final CheatCodeHelper.OnTriggerListener getOnCheatCodeListener() {
        return this.onCheatCodeListener;
    }

    public final void setOnCheatCodeListener(CheatCodeHelper.OnTriggerListener onTriggerListener) {
        this.onCheatCodeListener = onTriggerListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVisible(final boolean z) {
        if (this.visible != z) {
            this.visible = z;
            this.internalHandler.post(new Runnable() { // from class: com.dotscreen.tv.ui.DirectChannelAccessView$visible$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.internalRootView.setVisibility(z ? 0 : 4);
                    DirectChannelAccessView.OnVisibilityChangedListener onVisibilityChangedListener = this.this$0.getOnVisibilityChangedListener();
                    if (onVisibilityChangedListener != null) {
                        onVisibilityChangedListener.onVisibilityChanged(z);
                    }
                }
            });
        }
    }

    public final boolean getShowTitleName() {
        return this.showTitleName;
    }

    public final void setShowTitleName(boolean z) {
        if (this.showTitleName != z) {
            this.showTitleName = z;
            this.channelNameTextView.setVisibility(z ? 0 : 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int convertChannelNumber() {
        String str = this.currentZappingNumber;
        if (str == null) {
            return 0;
        }
        try {
            return Integer.parseInt(StringsKt.trimStart(str, '0'));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    private final void updateChannelNumber() {
        if (this.DEBUG) {
            Log.d(this.TAG, ">>> updateChannelNumber()");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.currentZappingNumber);
        String str = this.currentZappingNumber;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        int i = this.zappingNumberLength;
        for (int length = str.length(); length < i; length++) {
            sb.append("-");
        }
        if (this.DEBUG) {
            Log.d(this.TAG, "  -> builder = " + ((Object) sb));
        }
        this.channelZappingNumberTextView.setText(sb.toString());
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = this.channelName.invoke(Integer.valueOf(convertChannelNumber())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.dotscreen.tv.ui.DirectChannelAccessView.updateChannelNumber.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(String str2) {
                if (DirectChannelAccessView.this.DEBUG) {
                    Log.d(DirectChannelAccessView.this.TAG, "    -> onSuccess: " + str2);
                }
                DirectChannelAccessView.this.channelNameTextView.setText(str2);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.tv.ui.DirectChannelAccessView.updateChannelNumber.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                if (DirectChannelAccessView.this.DEBUG) {
                    Log.w(DirectChannelAccessView.this.TAG, "    -> onError:", th);
                }
            }
        }, new Action() { // from class: com.dotscreen.tv.ui.DirectChannelAccessView.updateChannelNumber.3
            @Override // io.reactivex.functions.Action
            public final void run() {
                if (DirectChannelAccessView.this.DEBUG) {
                    Log.d(DirectChannelAccessView.this.TAG, "    -> onComplete");
                }
                DirectChannelAccessView.this.channelNameTextView.setText(DirectChannelAccessView.this.getResources().getText(R.string.dca_unknown_channel));
            }
        });
        if (this.DEBUG) {
            Log.d(this.TAG, "<<<< updateChannelNumber()");
        }
    }

    public final boolean onKeyCode(int keyCode) {
        if (this.DEBUG) {
            Log.d(this.TAG, "onKeyCode(" + keyCode + ')');
        }
        CheatCodeHelper cheatCodeHelper = this.cheatCodeHelper;
        if (cheatCodeHelper != null) {
            cheatCodeHelper.onKeyUp(keyCode, null);
        }
        if (7 > keyCode || 16 < keyCode) {
            if (keyCode == 23) {
                if (this.currentZappingNumber != null) {
                    this.internalHandler.removeCallbacks(this.zapRunnable);
                    this.zapRunnable.run();
                    return true;
                }
            } else if (keyCode == 4 && this.currentZappingNumber != null) {
                this.internalHandler.removeCallbacks(this.zapRunnable);
                this.currentZappingNumber = (String) null;
                this.channelNameTextView.setText("");
                setVisible(false);
                return true;
            }
            return false;
        }
        this.internalHandler.removeCallbacks(this.zapRunnable);
        if (this.DEBUG) {
            Log.d(this.TAG, "  -> number");
        }
        setVisible(true);
        String str = this.currentZappingNumber;
        if (str == null) {
            this.currentZappingNumber = String.valueOf(keyCode - 7);
            updateChannelNumber();
        } else {
            if (str == null) {
                Intrinsics.throwNpe();
            }
            if (str.length() < this.zappingNumberLength) {
                this.currentZappingNumber = Intrinsics.stringPlus(this.currentZappingNumber, String.valueOf(keyCode - 7));
                updateChannelNumber();
            }
        }
        this.internalHandler.postDelayed(this.zapRunnable, 3000L);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
