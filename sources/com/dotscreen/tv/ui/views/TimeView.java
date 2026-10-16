package com.dotscreen.tv.ui.views;

import android.content.Context;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.android.tv.common.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimeView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000*\u0002\u0012\u0015\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\u0017\u001a\u00020\u0018H\u0014R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/dotscreen/tv/ui/views/TimeView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "hourTextView", "Landroid/widget/TextView;", "internalHandler", "Landroid/os/Handler;", "lifecycleObserver", "com/dotscreen/tv/ui/views/TimeView$lifecycleObserver$1", "Lcom/dotscreen/tv/ui/views/TimeView$lifecycleObserver$1;", "updateTime", "com/dotscreen/tv/ui/views/TimeView$updateTime$1", "Lcom/dotscreen/tv/ui/views/TimeView$updateTime$1;", "onDetachedFromWindow", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TimeView extends LinearLayout {
    private TextView hourTextView;
    private final Handler internalHandler;
    private final TimeView$lifecycleObserver$1 lifecycleObserver;
    private final TimeView$updateTime$1 updateTime;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.dotscreen.tv.ui.views.TimeView$lifecycleObserver$1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.dotscreen.tv.ui.views.TimeView$updateTime$1] */
    public TimeView(final Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.internalHandler = new Handler();
        this.lifecycleObserver = new DefaultLifecycleObserver() { // from class: com.dotscreen.tv.ui.views.TimeView$lifecycleObserver$1
            @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
            public void onPause(LifecycleOwner owner) {
                Intrinsics.checkParameterIsNotNull(owner, "owner");
                this.this$0.internalHandler.removeCallbacks(this.this$0.updateTime);
            }

            @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
            public void onResume(LifecycleOwner owner) {
                Intrinsics.checkParameterIsNotNull(owner, "owner");
                this.this$0.internalHandler.post(this.this$0.updateTime);
            }
        };
        this.updateTime = new Runnable() { // from class: com.dotscreen.tv.ui.views.TimeView$updateTime$1
            @Override // java.lang.Runnable
            public void run() {
                TextView textView;
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.is24HourFormat(context) ? "HH:mm" : "hh:mm", Locale.getDefault());
                Intrinsics.checkExpressionValueIsNotNull(cal, "cal");
                String str = simpleDateFormat.format(cal.getTime());
                TextView textView2 = this.this$0.hourTextView;
                if ((!Intrinsics.areEqual(textView2 != null ? textView2.getText() : null, str)) && (textView = this.this$0.hourTextView) != null) {
                    textView.setText(str);
                }
                long j = ((long) (60 - cal.get(13))) * 1000;
                Log.d("TimeView", "next time upate in " + j + "ms");
                StringBuilder sb = new StringBuilder();
                sb.append("context : ");
                sb.append(context);
                Log.d("TimeView", sb.toString());
                this.this$0.internalHandler.postDelayed(this, j);
            }
        };
        View viewInflate = View.inflate(getContext(), R.layout.view_time, this);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        int color = context.obtainStyledAttributes(attributeSet, R.styleable.TimeView, i, i2).getColor(R.styleable.TimeView_textColor, -1);
        TextView textView = (TextView) findViewById(R.id.time_hour);
        this.hourTextView = textView;
        if (textView != null) {
            textView.setTextColor(color);
        }
        ((FragmentActivity) context).getLifecycle().addObserver(this.lifecycleObserver);
    }

    public /* synthetic */ TimeView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TimeView(Context context) {
        this(context, null, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TimeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TimeView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Context context = getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        }
        ((FragmentActivity) context).getLifecycle().removeObserver(this.lifecycleObserver);
    }
}
