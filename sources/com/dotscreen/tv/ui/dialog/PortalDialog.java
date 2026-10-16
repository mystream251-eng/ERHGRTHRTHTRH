package com.dotscreen.tv.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.exifinterface.media.ExifInterface;
import com.android.tv.common.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PortalDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/PortalDialog;", "", "()V", "Builder", "InternalBuilder", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PortalDialog {

    /* JADX INFO: compiled from: PortalDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/PortalDialog$Builder;", "Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Builder extends InternalBuilder<Builder> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
        }
    }

    /* JADX INFO: compiled from: PortalDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0016\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010H\u001a\u00020IH\u0016J\b\u0010J\u001a\u00020IH\u0014J\u0013\u0010K\u001a\u00028\u00002\u0006\u0010L\u001a\u00020\u0011¢\u0006\u0002\u0010MJ\u0013\u0010\u001b\u001a\u00028\u00002\u0006\u0010N\u001a\u00020\u0007¢\u0006\u0002\u0010OJ\u0013\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u0018¢\u0006\u0002\u0010PJ\u001b\u0010Q\u001a\u00028\u00002\u0006\u0010N\u001a\u00020\u00072\u0006\u0010R\u001a\u00020!¢\u0006\u0002\u0010SJ\u001b\u0010Q\u001a\u00028\u00002\u0006\u0010T\u001a\u00020\u00182\u0006\u0010R\u001a\u00020!¢\u0006\u0002\u0010UJ\u001b\u0010V\u001a\u00028\u00002\u0006\u0010N\u001a\u00020\u00072\u0006\u0010R\u001a\u00020!¢\u0006\u0002\u0010SJ\u001b\u0010V\u001a\u00028\u00002\u0006\u0010T\u001a\u00020\u00182\u0006\u0010R\u001a\u00020!¢\u0006\u0002\u0010UJ\u0013\u00100\u001a\u00028\u00002\u0006\u0010W\u001a\u00020-¢\u0006\u0002\u0010XJ\u0013\u00106\u001a\u00028\u00002\u0006\u0010W\u001a\u000203¢\u0006\u0002\u0010YJ\u001b\u0010Z\u001a\u00028\u00002\u0006\u0010N\u001a\u00020\u00072\u0006\u0010R\u001a\u00020!¢\u0006\u0002\u0010SJ\u001b\u0010Z\u001a\u00028\u00002\u0006\u0010T\u001a\u00020\u00182\u0006\u0010R\u001a\u00020!¢\u0006\u0002\u0010UJ\u0013\u0010[\u001a\u00028\u00002\u0006\u0010\\\u001a\u00020\u0011¢\u0006\u0002\u0010MJ\u0013\u0010B\u001a\u00028\u00002\u0006\u0010>\u001a\u00020?¢\u0006\u0002\u0010]J\u0013\u0010G\u001a\u00028\u00002\u0006\u0010N\u001a\u00020\u0007¢\u0006\u0002\u0010OJ\u0013\u0010G\u001a\u00028\u00002\u0006\u0010E\u001a\u00020\u0018¢\u0006\u0002\u0010PR\u0014\u0010\u0006\u001a\u00020\u00078TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0011X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0018X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010\u0018X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010\u001cR\u001c\u0010)\u001a\u0004\u0018\u00010!X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010#\"\u0004\b+\u0010%R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u000103X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001c\u00108\u001a\u0004\u0018\u00010\u0018X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001a\"\u0004\b:\u0010\u001cR\u001c\u0010;\u001a\u0004\u0018\u00010!X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010#\"\u0004\b=\u0010%R\u001e\u0010>\u001a\u0004\u0018\u00010?X\u0084\u000e¢\u0006\u0010\n\u0002\u0010D\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001c\u0010E\u001a\u0004\u0018\u00010\u0018X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u001a\"\u0004\bG\u0010\u001c¨\u0006^"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", ExifInterface.GPS_DIRECTION_TRUE, "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contentViewId", "", "getContentViewId", "()I", "getContext", "()Landroid/content/Context;", "dismissRunnable", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "isCancelable", "", "()Z", "setCancelable", "(Z)V", "isPurchasing", "setPurchasing", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "negativeButtonLabel", "getNegativeButtonLabel", "setNegativeButtonLabel", "negativeButtonOnClickListener", "Landroid/content/DialogInterface$OnClickListener;", "getNegativeButtonOnClickListener", "()Landroid/content/DialogInterface$OnClickListener;", "setNegativeButtonOnClickListener", "(Landroid/content/DialogInterface$OnClickListener;)V", "neutralButtonLabel", "getNeutralButtonLabel", "setNeutralButtonLabel", "neutralButtonOnClickListener", "getNeutralButtonOnClickListener", "setNeutralButtonOnClickListener", "onCancelListener", "Landroid/content/DialogInterface$OnCancelListener;", "getOnCancelListener", "()Landroid/content/DialogInterface$OnCancelListener;", "setOnCancelListener", "(Landroid/content/DialogInterface$OnCancelListener;)V", "onDismissListener", "Landroid/content/DialogInterface$OnDismissListener;", "getOnDismissListener", "()Landroid/content/DialogInterface$OnDismissListener;", "setOnDismissListener", "(Landroid/content/DialogInterface$OnDismissListener;)V", "positiveButtonLabel", "getPositiveButtonLabel", "setPositiveButtonLabel", "positiveButtonOnClickListener", "getPositiveButtonOnClickListener", "setPositiveButtonOnClickListener", "timeOut", "", "getTimeOut", "()Ljava/lang/Long;", "setTimeOut", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "title", "getTitle", "setTitle", "create", "Landroid/app/Dialog;", "createDialog", "setBackMessage", "isToCancel", "(Z)Ljava/lang/Object;", "id", "(I)Ljava/lang/Object;", "(Ljava/lang/String;)Ljava/lang/Object;", "setNegativeButton", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "(ILandroid/content/DialogInterface$OnClickListener;)Ljava/lang/Object;", "label", "(Ljava/lang/String;Landroid/content/DialogInterface$OnClickListener;)Ljava/lang/Object;", "setNeutralButton", "lister", "(Landroid/content/DialogInterface$OnCancelListener;)Ljava/lang/Object;", "(Landroid/content/DialogInterface$OnDismissListener;)Ljava/lang/Object;", "setPositiveButton", "setProgress", "isCalling", "(J)Ljava/lang/Object;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class InternalBuilder<T> {
        private final Context context;
        private Runnable dismissRunnable;
        private final Handler handler;
        private boolean isCancelable;
        private boolean isPurchasing;
        private String message;
        private String negativeButtonLabel;
        private DialogInterface.OnClickListener negativeButtonOnClickListener;
        private String neutralButtonLabel;
        private DialogInterface.OnClickListener neutralButtonOnClickListener;
        private DialogInterface.OnCancelListener onCancelListener;
        private DialogInterface.OnDismissListener onDismissListener;
        private String positiveButtonLabel;
        private DialogInterface.OnClickListener positiveButtonOnClickListener;
        private Long timeOut;
        private String title;

        public InternalBuilder(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
            this.isCancelable = true;
            this.handler = new Handler();
        }

        protected final Context getContext() {
            return this.context;
        }

        protected final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: setTitle, reason: collision with other method in class */
        protected final void m19setTitle(String str) {
            this.title = str;
        }

        protected final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: setMessage, reason: collision with other method in class */
        protected final void m16setMessage(String str) {
            this.message = str;
        }

        /* JADX INFO: renamed from: isPurchasing, reason: from getter */
        protected final boolean getIsPurchasing() {
            return this.isPurchasing;
        }

        protected final void setPurchasing(boolean z) {
            this.isPurchasing = z;
        }

        /* JADX INFO: renamed from: isCancelable, reason: from getter */
        protected final boolean getIsCancelable() {
            return this.isCancelable;
        }

        protected final void setCancelable(boolean z) {
            this.isCancelable = z;
        }

        protected final String getPositiveButtonLabel() {
            return this.positiveButtonLabel;
        }

        protected final void setPositiveButtonLabel(String str) {
            this.positiveButtonLabel = str;
        }

        protected final DialogInterface.OnClickListener getPositiveButtonOnClickListener() {
            return this.positiveButtonOnClickListener;
        }

        protected final void setPositiveButtonOnClickListener(DialogInterface.OnClickListener onClickListener) {
            this.positiveButtonOnClickListener = onClickListener;
        }

        protected final String getNeutralButtonLabel() {
            return this.neutralButtonLabel;
        }

        protected final void setNeutralButtonLabel(String str) {
            this.neutralButtonLabel = str;
        }

        protected final DialogInterface.OnClickListener getNeutralButtonOnClickListener() {
            return this.neutralButtonOnClickListener;
        }

        protected final void setNeutralButtonOnClickListener(DialogInterface.OnClickListener onClickListener) {
            this.neutralButtonOnClickListener = onClickListener;
        }

        protected final String getNegativeButtonLabel() {
            return this.negativeButtonLabel;
        }

        protected final void setNegativeButtonLabel(String str) {
            this.negativeButtonLabel = str;
        }

        protected final DialogInterface.OnClickListener getNegativeButtonOnClickListener() {
            return this.negativeButtonOnClickListener;
        }

        protected final void setNegativeButtonOnClickListener(DialogInterface.OnClickListener onClickListener) {
            this.negativeButtonOnClickListener = onClickListener;
        }

        protected final Long getTimeOut() {
            return this.timeOut;
        }

        protected final void setTimeOut(Long l) {
            this.timeOut = l;
        }

        protected final DialogInterface.OnDismissListener getOnDismissListener() {
            return this.onDismissListener;
        }

        /* JADX INFO: renamed from: setOnDismissListener, reason: collision with other method in class */
        protected final void m18setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
        }

        protected final DialogInterface.OnCancelListener getOnCancelListener() {
            return this.onCancelListener;
        }

        /* JADX INFO: renamed from: setOnCancelListener, reason: collision with other method in class */
        protected final void m17setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
        }

        protected int getContentViewId() {
            if (this.neutralButtonLabel != null) {
                return R.layout.dialog_3_buttons;
            }
            return R.layout.dialog_2_buttons;
        }

        public final T setTitle(String title) {
            Intrinsics.checkParameterIsNotNull(title, "title");
            this.title = title;
            return this;
        }

        public final T setTitle(int id) {
            this.title = this.context.getString(id);
            return this;
        }

        public final T setMessage(String message) {
            Intrinsics.checkParameterIsNotNull(message, "message");
            this.message = message;
            return this;
        }

        public final T setMessage(int id) {
            this.message = this.context.getString(id);
            return this;
        }

        public final T setProgress(boolean isCalling) {
            this.isPurchasing = isCalling;
            return this;
        }

        public final T setPositiveButton(String label, DialogInterface.OnClickListener listener) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            Intrinsics.checkParameterIsNotNull(listener, "listener");
            this.positiveButtonLabel = label;
            this.positiveButtonOnClickListener = listener;
            return this;
        }

        public final T setPositiveButton(int id, DialogInterface.OnClickListener listener) {
            Intrinsics.checkParameterIsNotNull(listener, "listener");
            this.positiveButtonLabel = this.context.getString(id);
            this.positiveButtonOnClickListener = listener;
            return this;
        }

        public final T setNeutralButton(String label, DialogInterface.OnClickListener listener) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            Intrinsics.checkParameterIsNotNull(listener, "listener");
            this.neutralButtonLabel = label;
            this.neutralButtonOnClickListener = listener;
            return this;
        }

        public final T setNeutralButton(int id, DialogInterface.OnClickListener listener) {
            Intrinsics.checkParameterIsNotNull(listener, "listener");
            this.neutralButtonLabel = this.context.getString(id);
            this.neutralButtonOnClickListener = listener;
            return this;
        }

        public final T setNegativeButton(String label, DialogInterface.OnClickListener listener) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            Intrinsics.checkParameterIsNotNull(listener, "listener");
            this.negativeButtonLabel = label;
            this.negativeButtonOnClickListener = listener;
            return this;
        }

        public final T setNegativeButton(int id, DialogInterface.OnClickListener listener) {
            Intrinsics.checkParameterIsNotNull(listener, "listener");
            this.negativeButtonLabel = this.context.getString(id);
            this.negativeButtonOnClickListener = listener;
            return this;
        }

        public final T setOnDismissListener(DialogInterface.OnDismissListener lister) {
            Intrinsics.checkParameterIsNotNull(lister, "lister");
            this.onDismissListener = lister;
            return this;
        }

        public final T setOnCancelListener(DialogInterface.OnCancelListener lister) {
            Intrinsics.checkParameterIsNotNull(lister, "lister");
            this.onCancelListener = lister;
            return this;
        }

        public final T setBackMessage(boolean isToCancel) {
            this.isCancelable = isToCancel;
            return this;
        }

        public final T setTimeOut(long timeOut) {
            this.timeOut = Long.valueOf(timeOut);
            return this;
        }

        protected Dialog createDialog() {
            return new Dialog(this.context, R.style.AppTheme_DialogTranslucent);
        }

        public Dialog create() {
            final Dialog dialogCreateDialog = createDialog();
            dialogCreateDialog.setContentView(getContentViewId());
            TextView textView = (TextView) dialogCreateDialog.findViewById(R.id.title);
            if (textView != null) {
                if (TextUtils.isEmpty(this.title)) {
                    textView.setVisibility(8);
                } else {
                    textView.setText(this.title);
                    textView.setVisibility(0);
                }
            }
            TextView textView2 = (TextView) dialogCreateDialog.findViewById(R.id.message);
            if (textView2 != null) {
                if (TextUtils.isEmpty(this.message)) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setText(this.message);
                    textView2.setVisibility(0);
                }
            }
            ProgressBar progressBar = (ProgressBar) dialogCreateDialog.findViewById(R.id.progress);
            if (progressBar != null) {
                if (this.isPurchasing) {
                    progressBar.setVisibility(0);
                } else {
                    progressBar.setVisibility(8);
                }
            }
            TextView textView3 = (TextView) dialogCreateDialog.findViewById(R.id.positive_button);
            if (textView3 != null) {
                if (TextUtils.isEmpty(this.positiveButtonLabel)) {
                    textView3.setVisibility(8);
                } else {
                    textView3.setText(this.positiveButtonLabel);
                    textView3.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.tv.ui.dialog.PortalDialog$InternalBuilder$create$$inlined$let$lambda$1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            dialogCreateDialog.dismiss();
                            DialogInterface.OnClickListener positiveButtonOnClickListener = this.this$0.getPositiveButtonOnClickListener();
                            if (positiveButtonOnClickListener != null) {
                                positiveButtonOnClickListener.onClick(dialogCreateDialog, -1);
                            }
                        }
                    });
                    textView3.setVisibility(0);
                }
            }
            TextView textView4 = (TextView) dialogCreateDialog.findViewById(R.id.neutral_button);
            if (textView4 != null) {
                if (TextUtils.isEmpty(this.neutralButtonLabel)) {
                    textView4.setVisibility(8);
                } else {
                    textView4.setText(this.neutralButtonLabel);
                    textView4.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.tv.ui.dialog.PortalDialog$InternalBuilder$create$$inlined$let$lambda$2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            dialogCreateDialog.dismiss();
                            DialogInterface.OnClickListener neutralButtonOnClickListener = this.this$0.getNeutralButtonOnClickListener();
                            if (neutralButtonOnClickListener != null) {
                                neutralButtonOnClickListener.onClick(dialogCreateDialog, -3);
                            }
                        }
                    });
                    textView4.setVisibility(0);
                }
            }
            TextView textView5 = (TextView) dialogCreateDialog.findViewById(R.id.negative_button);
            if (textView5 != null) {
                if (TextUtils.isEmpty(this.negativeButtonLabel)) {
                    textView5.setVisibility(8);
                } else {
                    textView5.setText(this.negativeButtonLabel);
                    textView5.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.tv.ui.dialog.PortalDialog$InternalBuilder$create$$inlined$let$lambda$3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            dialogCreateDialog.dismiss();
                            DialogInterface.OnClickListener negativeButtonOnClickListener = this.this$0.getNegativeButtonOnClickListener();
                            if (negativeButtonOnClickListener != null) {
                                negativeButtonOnClickListener.onClick(dialogCreateDialog, -2);
                            }
                        }
                    });
                    textView5.setVisibility(0);
                }
            }
            dialogCreateDialog.setCancelable(this.isCancelable);
            if (this.isCancelable) {
                dialogCreateDialog.setOnCancelListener(this.onCancelListener);
            }
            Long l = this.timeOut;
            if (l != null) {
                long jLongValue = l.longValue();
                dialogCreateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.dotscreen.tv.ui.dialog.PortalDialog$InternalBuilder$create$$inlined$let$lambda$4
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        this.this$0.handler.removeCallbacks(this.this$0.dismissRunnable);
                        DialogInterface.OnDismissListener onDismissListener = this.this$0.getOnDismissListener();
                        if (onDismissListener != null) {
                            onDismissListener.onDismiss(dialogCreateDialog);
                        }
                    }
                });
                Runnable runnable = new Runnable() { // from class: com.dotscreen.tv.ui.dialog.PortalDialog$InternalBuilder$create$$inlined$let$lambda$5
                    @Override // java.lang.Runnable
                    public final void run() {
                        dialogCreateDialog.dismiss();
                    }
                };
                this.dismissRunnable = runnable;
                this.handler.postDelayed(runnable, jLongValue);
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            Window window = dialogCreateDialog.getWindow();
            layoutParams.copyFrom(window != null ? window.getAttributes() : null);
            layoutParams.height = -1;
            layoutParams.width = this.context.getResources().getDimensionPixelSize(R.dimen.right_popup_width);
            layoutParams.gravity = GravityCompat.END;
            Window window2 = dialogCreateDialog.getWindow();
            if (window2 != null) {
                window2.setAttributes(layoutParams);
            }
            return dialogCreateDialog;
        }
    }
}
