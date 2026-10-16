package com.dotscreen.portal.ui.screens.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Marker;

/* JADX INFO: compiled from: PinCodeDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u001e2\u00020\u0001:\u0003\u001d\u001e\u001fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015¨\u0006 "}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "value", "", "error", "getError", "()Z", "setError", "(Z)V", "handler", "Landroid/os/Handler;", "", "pinCode", "getPinCode", "()Ljava/lang/String;", "setPinCode", "(Ljava/lang/String;)V", "selectorColor", "title", "getTitle", "setTitle", "updateError", "", "updatePinCode", "Builder", "Companion", "OnPinCodeListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class PinCodeDialog extends Dialog {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = PinCodeDialog.class.getSimpleName();
    private boolean error;
    private final Handler handler;
    private String pinCode;
    private int selectorColor;
    private String title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PinCodeDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.handler = new Handler();
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
        this.pinCode = "";
    }

    protected final String getTitle() {
        return this.title;
    }

    protected final void setTitle(String str) {
        this.title = str;
    }

    /* JADX INFO: compiled from: PinCodeDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return PinCodeDialog.TAG;
        }
    }

    public final String getPinCode() {
        return this.pinCode;
    }

    public final void setPinCode(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.pinCode = value;
        this.handler.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.profile.PinCodeDialog$pinCode$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.updatePinCode();
            }
        });
    }

    public final boolean getError() {
        return this.error;
    }

    public final void setError(boolean z) {
        this.error = z;
        this.handler.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.profile.PinCodeDialog$error$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.updateError();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
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
        pin1.setText(this.pinCode.length() >= 1 ? Marker.ANY_MARKER : "_");
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
        if (this.pinCode.length() == 0) {
            color = this.selectorColor;
        } else {
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            color = context2.getResources().getColor(R.color.white, null);
        }
        pin1.setTextColor(color);
        if (this.pinCode.length() == 1) {
            color2 = this.selectorColor;
        } else {
            Context context3 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
            color2 = context3.getResources().getColor(R.color.white, null);
        }
        pin2.setTextColor(color2);
        if (this.pinCode.length() == 2) {
            color3 = this.selectorColor;
        } else {
            Context context4 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context4, "context");
            color3 = context4.getResources().getColor(R.color.white, null);
        }
        pin3.setTextColor(color3);
        if (this.pinCode.length() == 3) {
            color4 = this.selectorColor;
        } else {
            Context context5 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context5, "context");
            color4 = context5.getResources().getColor(R.color.white, null);
        }
        pin4.setTextColor(color4);
    }

    /* JADX INFO: compiled from: PinCodeDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J0\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0018\u00010\fj\u0004\u0018\u0001`\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;", "", "onPinCodeCorrect", "", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "pinCode", "", "pinCodes", "", "onPinCodeInvalid", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnPinCodeListener {
        void onPinCodeCorrect(PinCodeDialog dialog, String pinCode);

        void onPinCodeCorrect(PinCodeDialog dialog, List<String> pinCodes);

        void onPinCodeInvalid(PinCodeDialog dialog, String pinCode);

        void onPinCodeInvalid(PinCodeDialog dialog, List<String> pinCodes, Exception error);

        /* JADX INFO: compiled from: PinCodeDialog.kt */
        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public static final class DefaultImpls {
            public static void onPinCodeCorrect(OnPinCodeListener onPinCodeListener, PinCodeDialog dialog, List<String> pinCodes) {
                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
            }

            public static void onPinCodeInvalid(OnPinCodeListener onPinCodeListener, PinCodeDialog dialog, String pinCode) {
                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
            }

            public static void onPinCodeInvalid(OnPinCodeListener onPinCodeListener, PinCodeDialog dialog, List<String> pinCodes, Exception exc) {
                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
            }

            public static /* synthetic */ void onPinCodeInvalid$default(OnPinCodeListener onPinCodeListener, PinCodeDialog pinCodeDialog, List list, Exception exc, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onPinCodeInvalid");
                }
                if ((i & 4) != 0) {
                    exc = (Exception) null;
                }
                onPinCodeListener.onPinCodeInvalid(pinCodeDialog, list, exc);
            }
        }
    }

    /* JADX INFO: compiled from: PinCodeDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001>B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010/\u001a\u000200H\u0016J\u000e\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\fJ\b\u00102\u001a\u000200H\u0014J\u0006\u00103\u001a\u00020\u0000J\u0018\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u001fH\u0016J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u0012J\u000e\u00109\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u001fJ\u000e\u0010)\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&J\u000e\u0010<\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\b\"\u0004\b\u001d\u0010\nR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$Builder;", "Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bottom_text", "Landroid/widget/TextView;", "getBottom_text", "()Landroid/widget/TextView;", "setBottom_text", "(Landroid/widget/TextView;)V", "checkPinCode", "", "getCheckPinCode", "()Z", "setCheckPinCode", "(Z)V", "contentViewId", "", "getContentViewId", "()I", "deleteImage", "Landroid/widget/LinearLayout;", "getDeleteImage", "()Landroid/widget/LinearLayout;", "setDeleteImage", "(Landroid/widget/LinearLayout;)V", "errorView", "getErrorView", "setErrorView", "informations", "", "getInformations", "()Ljava/lang/String;", "setInformations", "(Ljava/lang/String;)V", "messageVisibility", "onPinCodeListener", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;", "getOnPinCodeListener", "()Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;", "setOnPinCodeListener", "(Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;)V", "pincodeType", "Lcom/dotscreen/bokit/services/PincodeType;", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/bokit/model/User;", "create", "Landroid/app/Dialog;", "fromChild", "createDialog", "hideDefaultMessage", "onPinCode", "", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "pinCode", "setInformation", "id", "message", "setPincodeType", "setUser", "CheckPinCodeTask", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class Builder extends PortalDialog.InternalBuilder<Builder> {
        private TextView bottom_text;
        private boolean checkPinCode;
        private LinearLayout deleteImage;
        private TextView errorView;
        private String informations;
        private boolean messageVisibility;
        private OnPinCodeListener onPinCodeListener;
        private PincodeType pincodeType;
        private User user;

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected int getContentViewId() {
            return R.layout.dialog_pin_code;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.messageVisibility = true;
            this.pincodeType = PincodeType.General;
            this.checkPinCode = true;
        }

        protected final OnPinCodeListener getOnPinCodeListener() {
            return this.onPinCodeListener;
        }

        /* JADX INFO: renamed from: setOnPinCodeListener, reason: collision with other method in class */
        protected final void m14setOnPinCodeListener(OnPinCodeListener onPinCodeListener) {
            this.onPinCodeListener = onPinCodeListener;
        }

        protected final boolean getCheckPinCode() {
            return this.checkPinCode;
        }

        /* JADX INFO: renamed from: setCheckPinCode, reason: collision with other method in class */
        protected final void m13setCheckPinCode(boolean z) {
            this.checkPinCode = z;
        }

        protected final TextView getErrorView() {
            return this.errorView;
        }

        protected final void setErrorView(TextView textView) {
            this.errorView = textView;
        }

        protected final LinearLayout getDeleteImage() {
            return this.deleteImage;
        }

        protected final void setDeleteImage(LinearLayout linearLayout) {
            this.deleteImage = linearLayout;
        }

        protected final TextView getBottom_text() {
            return this.bottom_text;
        }

        protected final void setBottom_text(TextView textView) {
            this.bottom_text = textView;
        }

        protected final String getInformations() {
            return this.informations;
        }

        protected final void setInformations(String str) {
            this.informations = str;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected Dialog createDialog() {
            return new PinCodeDialog(getContext(), 2131951633);
        }

        public final Builder setUser(User user) {
            Intrinsics.checkParameterIsNotNull(user, "user");
            this.user = user;
            return this;
        }

        public final Builder setPincodeType(PincodeType pincodeType) {
            Intrinsics.checkParameterIsNotNull(pincodeType, "pincodeType");
            this.pincodeType = pincodeType;
            return this;
        }

        public final Builder setOnPinCodeListener(OnPinCodeListener onPinCodeListener) {
            Intrinsics.checkParameterIsNotNull(onPinCodeListener, "onPinCodeListener");
            this.onPinCodeListener = onPinCodeListener;
            return this;
        }

        public final Builder setInformation(String message) {
            Intrinsics.checkParameterIsNotNull(message, "message");
            this.informations = message;
            return this;
        }

        public final Builder setInformation(int id) {
            this.informations = getContext().getString(id);
            return this;
        }

        public final Builder hideDefaultMessage() {
            this.messageVisibility = false;
            return this;
        }

        public final Builder setCheckPinCode(boolean checkPinCode) {
            this.checkPinCode = checkPinCode;
            return this;
        }

        public void onPinCode(PinCodeDialog dialog, String pinCode) {
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
            if (this.checkPinCode) {
                new CheckPinCodeTask(this, dialog, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pinCode);
                return;
            }
            OnPinCodeListener onPinCodeListener = this.onPinCodeListener;
            if (onPinCodeListener != null) {
                onPinCodeListener.onPinCodeCorrect(dialog, pinCode);
            }
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        public Dialog create() {
            Dialog dialogCreate = super.create();
            if (dialogCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.PinCodeDialog");
            }
            PinCodeDialog pinCodeDialog = (PinCodeDialog) dialogCreate;
            ProgressBar progress = (ProgressBar) pinCodeDialog.findViewById(R.id.progress);
            this.deleteImage = (LinearLayout) pinCodeDialog.findViewById(R.id.delete_message);
            this.bottom_text = (TextView) pinCodeDialog.findViewById(R.id.bottom_text);
            TextView textView = (TextView) pinCodeDialog.findViewById(R.id.message);
            if (textView == null) {
                Intrinsics.throwNpe();
            }
            textView.setVisibility(this.messageVisibility ? 0 : 8);
            Intrinsics.checkExpressionValueIsNotNull(progress, "progress");
            progress.setVisibility(4);
            pinCodeDialog.setPinCode("");
            TextView it = (TextView) pinCodeDialog.findViewById(R.id.informations);
            if (this.informations != null) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                it.setText(this.informations);
                it.setVisibility(0);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                it.setVisibility(8);
            }
            pinCodeDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.dotscreen.portal.ui.screens.profile.PinCodeDialog$Builder$create$2
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent event) {
                    if (dialogInterface != null) {
                        PinCodeDialog pinCodeDialog2 = (PinCodeDialog) dialogInterface;
                        int length = pinCodeDialog2.getPinCode().length();
                        if (1 <= length && 3 >= length && i == 4) {
                            Intrinsics.checkExpressionValueIsNotNull(event, "event");
                            if (event.getAction() == 1) {
                                pinCodeDialog2.setPinCode("");
                                pinCodeDialog2.setError(false);
                                return true;
                            }
                        }
                        if ((pinCodeDialog2.getPinCode().length() == 0) && i == 4) {
                            Intrinsics.checkExpressionValueIsNotNull(event, "event");
                            if (event.getAction() == 1) {
                                pinCodeDialog2.cancel();
                                return false;
                            }
                        }
                        int length2 = pinCodeDialog2.getPinCode().length();
                        if (1 <= length2 && 3 >= length2) {
                            TextView bottom_text = this.this$0.getBottom_text();
                            if (bottom_text == null) {
                                Intrinsics.throwNpe();
                            }
                            bottom_text.setText(this.this$0.getContext().getString(R.string.back_to_clear));
                        } else {
                            TextView bottom_text2 = this.this$0.getBottom_text();
                            if (bottom_text2 == null) {
                                Intrinsics.throwNpe();
                            }
                            bottom_text2.setText(this.this$0.getContext().getString(R.string.back_to_close));
                        }
                        Intrinsics.checkExpressionValueIsNotNull(event, "event");
                        if (event.getAction() != 1 || pinCodeDialog2.getPinCode().length() >= 4) {
                            return false;
                        }
                        if (i >= 7 && i <= 16) {
                            pinCodeDialog2.setPinCode(pinCodeDialog2.getPinCode() + ((char) ((i - 7) + 48)));
                        }
                        if (pinCodeDialog2.getPinCode().length() == 4) {
                            this.this$0.onPinCode(pinCodeDialog2, pinCodeDialog2.getPinCode());
                        }
                        return true;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.PinCodeDialog");
                }
            });
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            Window window = pinCodeDialog.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "dialog.window");
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.height = -1;
            layoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.right_popup_width);
            layoutParams.gravity = 5;
            Window window2 = pinCodeDialog.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window2, "dialog.window");
            window2.setAttributes(layoutParams);
            return pinCodeDialog;
        }

        public final Dialog create(boolean fromChild) {
            return super.create();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: compiled from: PinCodeDialog.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ%\u0010\u0010\u001a\u00020\u00042\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0012\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004H\u0014¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0015H\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$Builder$CheckPinCodeTask;", "Landroid/os/AsyncTask;", "", "Ljava/lang/Void;", "", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "builder", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$Builder;", "(Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$Builder;Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$Builder;)V", "getBuilder", "()Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$Builder;", "getDialog", "()Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "handler", "Landroid/os/Handler;", "doInBackground", "p0", "", "([Ljava/lang/String;)Ljava/lang/Boolean;", "onPostExecute", "", "result", "(Ljava/lang/Boolean;)V", "onPreExecute", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
        final class CheckPinCodeTask extends AsyncTask<String, Void, Boolean> {
            private final Builder builder;
            private final PinCodeDialog dialog;
            private final Handler handler;
            final /* synthetic */ Builder this$0;

            public CheckPinCodeTask(Builder builder, PinCodeDialog dialog, Builder builder2) {
                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                Intrinsics.checkParameterIsNotNull(builder2, "builder");
                this.this$0 = builder;
                this.dialog = dialog;
                this.builder = builder2;
                this.handler = new Handler();
            }

            public final Builder getBuilder() {
                return this.builder;
            }

            public final PinCodeDialog getDialog() {
                return this.dialog;
            }

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                super.onPreExecute();
                ProgressBar progressBar = (ProgressBar) this.dialog.findViewById(R.id.progress);
                if (progressBar != null) {
                    progressBar.setVisibility(0);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(String... p0) {
                boolean zAreEqual;
                Intrinsics.checkParameterIsNotNull(p0, "p0");
                boolean z = false;
                try {
                    if (this.this$0.user != null) {
                        User user = this.this$0.user;
                        if (user == null) {
                            Intrinsics.throwNpe();
                        }
                        PincodeType pincodeType = this.this$0.pincodeType;
                        String str = p0[0];
                        if (str == null) {
                            Intrinsics.throwNpe();
                        }
                        Boolean boolBlockingGet = user.checkPincode(pincodeType, str).blockingGet();
                        Intrinsics.checkExpressionValueIsNotNull(boolBlockingGet, "user!!.checkPincode(pinc…e, p0[0]!!).blockingGet()");
                        zAreEqual = boolBlockingGet.booleanValue();
                    } else {
                        Log.w(PinCodeDialog.INSTANCE.getTAG(), "CasManager is null, uses 'demo' pin code '1234'");
                        zAreEqual = Intrinsics.areEqual(p0[0], "1234");
                    }
                    z = zAreEqual;
                } catch (Exception unused) {
                }
                return Boolean.valueOf(z);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result == null) {
                    Intrinsics.throwNpe();
                }
                if (result.booleanValue()) {
                    OnPinCodeListener onPinCodeListener = this.builder.getOnPinCodeListener();
                    if (onPinCodeListener != null) {
                        PinCodeDialog pinCodeDialog = this.dialog;
                        onPinCodeListener.onPinCodeCorrect(pinCodeDialog, pinCodeDialog.getPinCode());
                        return;
                    }
                    return;
                }
                this.handler.postDelayed(new PinCodeDialog$Builder$CheckPinCodeTask$onPostExecute$2(this), 3000L);
            }
        }
    }
}
