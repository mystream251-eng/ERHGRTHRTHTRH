package com.dotscreen.portal.components;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Marker;

/* JADX INFO: compiled from: PinCodeView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001.B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\"\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\t2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\u000e\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u001aJ\u000e\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u000eJ\b\u0010+\u001a\u00020\"H\u0002J\u0006\u0010,\u001a\u00020\"J\u0006\u0010-\u001a\u00020\"R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/dotscreen/portal/components/PinCodeView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "TAG", "", "value", "", "error", "getError", "()Z", "setError", "(Z)V", "isComplete", "setComplete", "listeners", "", "Lcom/dotscreen/portal/components/PinCodeView$OnPinCodeCompleteStateChanged;", "pinCode", "getPinCode", "()Ljava/lang/String;", "setPinCode", "(Ljava/lang/String;)V", "selectorColor", "onFocusChanged", "", "gainFocus", "direction", "previouslyFocusedRect", "Landroid/graphics/Rect;", "setOnPinCodeCompleteStateChangedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setPrompt", "prompt", "updateCompleteState", "updateError", "updatePinCode", "OnPinCodeCompleteStateChanged", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PinCodeView extends LinearLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private boolean error;
    private boolean isComplete;
    private final List<OnPinCodeCompleteStateChanged> listeners;
    private String pinCode;
    private int selectorColor;

    /* JADX INFO: compiled from: PinCodeView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/dotscreen/portal/components/PinCodeView$OnPinCodeCompleteStateChanged;", "", "onPinCodeCompleteStateChanged", "", "isComplete", "", "pinCode", "", "view", "Lcom/dotscreen/portal/components/PinCodeView;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnPinCodeCompleteStateChanged {
        void onPinCodeCompleteStateChanged(boolean isComplete, String pinCode, PinCodeView view);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PinCodeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        String simpleName = PinCodeView.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "PinCodeView::class.java.simpleName");
        this.TAG = simpleName;
        this.listeners = new ArrayList();
        this.pinCode = "";
        View viewInflate = LinearLayout.inflate(getContext(), R.layout.view_pin_code, this);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
        if (value != null) {
            Integer color = value.getDesignManager().getColor("standard.item.selected.outline.color");
            if (color == null) {
                Intrinsics.throwNpe();
            }
            this.selectorColor = color.intValue();
        }
    }

    public /* synthetic */ PinCodeView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public final String getPinCode() {
        return this.pinCode;
    }

    public final void setPinCode(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.pinCode = value;
        if (getHandler() != null) {
            getHandler().post(new Runnable() { // from class: com.dotscreen.portal.components.PinCodeView$pinCode$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.updatePinCode();
                }
            });
        }
        setComplete(value.length() == 4);
    }

    public final boolean getError() {
        return this.error;
    }

    public final void setError(boolean z) {
        this.error = z;
        getHandler().post(new Runnable() { // from class: com.dotscreen.portal.components.PinCodeView$error$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.updateError();
            }
        });
    }

    private final void setComplete(boolean z) {
        this.isComplete = z;
        if (getHandler() != null) {
            getHandler().post(new Runnable() { // from class: com.dotscreen.portal.components.PinCodeView$isComplete$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.updateCompleteState();
                }
            });
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PinCodeView(Context context) {
        this(context, null, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PinCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PinCodeView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    public final void updateError() {
        TextView textView = (TextView) findViewById(R.id.error_message);
        if (this.error) {
            if (textView == null) {
                Intrinsics.throwNpe();
            }
            textView.setVisibility(0);
        } else {
            if (textView == null) {
                Intrinsics.throwNpe();
            }
            textView.setVisibility(8);
        }
    }

    public final void updatePinCode() {
        int color;
        int color2;
        int color3;
        int color4;
        TextView pin1 = (TextView) findViewById(R.id.pin1);
        TextView pin2 = (TextView) findViewById(R.id.pin2);
        TextView pin3 = (TextView) findViewById(R.id.pin3);
        TextView pin4 = (TextView) findViewById(R.id.pin4);
        Intrinsics.checkExpressionValueIsNotNull(pin1, "pin1");
        pin1.setText(this.pinCode.length() > 0 ? Marker.ANY_MARKER : "_");
        Intrinsics.checkExpressionValueIsNotNull(pin2, "pin2");
        pin2.setText(this.pinCode.length() >= 2 ? Marker.ANY_MARKER : "_");
        Intrinsics.checkExpressionValueIsNotNull(pin3, "pin3");
        pin3.setText(this.pinCode.length() >= 3 ? Marker.ANY_MARKER : "_");
        Intrinsics.checkExpressionValueIsNotNull(pin4, "pin4");
        pin4.setText(this.pinCode.length() >= 4 ? Marker.ANY_MARKER : "_");
        if (this.selectorColor == 0) {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            this.selectorColor = context.getResources().getColor(R.color.item_selector_main, null);
        }
        if ((this.pinCode.length() == 0) && hasFocus()) {
            color = this.selectorColor;
        } else {
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            color = context2.getResources().getColor(R.color.white, null);
        }
        pin1.setTextColor(color);
        if (this.pinCode.length() == 1 && hasFocus()) {
            color2 = this.selectorColor;
        } else {
            Context context3 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
            color2 = context3.getResources().getColor(R.color.white, null);
        }
        pin2.setTextColor(color2);
        if (this.pinCode.length() == 2 && hasFocus()) {
            color3 = this.selectorColor;
        } else {
            Context context4 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context4, "context");
            color3 = context4.getResources().getColor(R.color.white, null);
        }
        pin3.setTextColor(color3);
        if (this.pinCode.length() == 3 && hasFocus()) {
            color4 = this.selectorColor;
        } else {
            Context context5 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context5, "context");
            color4 = context5.getResources().getColor(R.color.white, null);
        }
        pin4.setTextColor(color4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCompleteState() {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((OnPinCodeCompleteStateChanged) it.next()).onPinCodeCompleteStateChanged(this.isComplete, this.pinCode, this);
        }
    }

    public final void setPrompt(String prompt) {
        Intrinsics.checkParameterIsNotNull(prompt, "prompt");
        View viewFindViewById = findViewById(R.id.prompt);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<TextView>(R.id.prompt)");
        ((TextView) viewFindViewById).setText(prompt);
    }

    public final void setOnPinCodeCompleteStateChangedListener(OnPinCodeCompleteStateChanged listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listeners.add(listener);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        updatePinCode();
    }
}
