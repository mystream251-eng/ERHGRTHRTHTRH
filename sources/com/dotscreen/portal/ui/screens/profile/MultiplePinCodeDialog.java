package com.dotscreen.portal.ui.screens.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.RecyclerView;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.components.PinCodeView;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0005\u000e\u000f\u0010\u0011\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "allPinCodeValues", "", "", "getAllPinCodeValues", "()Ljava/util/List;", "setAllPinCodeValues", "(Ljava/util/List;)V", "AdapterCompleteListener", "Builder", "EditPincodeType", "InputError", "NotMatchException", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MultiplePinCodeDialog extends PinCodeDialog {
    private List<String> allPinCodeValues;

    /* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$AdapterCompleteListener;", "", "onAdapterComplete", "", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "onPinCodeCompleteStateChanged", "isComplete", "", "pinCode", "", "view", "Lcom/dotscreen/portal/components/PinCodeView;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface AdapterCompleteListener {
        void onAdapterComplete(MultiplePinCodeDialog dialog);

        void onPinCodeCompleteStateChanged(boolean isComplete, String pinCode, PinCodeView view, MultiplePinCodeDialog dialog);
    }

    /* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$EditPincodeType;", "", "(Ljava/lang/String;I)V", "CreateProfile", "UpdateProfile", "UpdateParental", "UpdatePurchase", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum EditPincodeType {
        CreateProfile,
        UpdateProfile,
        UpdateParental,
        UpdatePurchase
    }

    /* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$InputError;", "", "(Ljava/lang/String;I)V", "WRONG_PIN_CODE", "PIN_CODE_NOT_MATCH", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public enum InputError {
        WRONG_PIN_CODE,
        PIN_CODE_NOT_MATCH
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiplePinCodeDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.allPinCodeValues = new ArrayList();
    }

    public final List<String> getAllPinCodeValues() {
        return this.allPinCodeValues;
    }

    public final void setAllPinCodeValues(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.allPinCodeValues = list;
    }

    /* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\u0003:;<B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020&H\u0014J\b\u0010(\u001a\u00020)H\u0002J \u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020,2\u000e\u0010-\u001a\n\u0018\u00010.j\u0004\u0018\u0001`/H\u0002J\u0010\u00100\u001a\u00020)2\u0006\u0010+\u001a\u00020,H\u0016J(\u00101\u001a\u00020)2\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u0002052\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u00106\u001a\u00020)2\u0006\u0010+\u001a\u00020,H\u0002J\u000e\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\u00012\u0006\u00109\u001a\u00020\u000eR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\n\"\u0004\b\u001d\u0010\fR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder;", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$Builder;", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$AdapterCompleteListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "completionStack", "", "", "getCompletionStack", "()Ljava/util/List;", "setCompletionStack", "(Ljava/util/List;)V", "contentViewId", "", "getContentViewId", "()I", "editPinCodeType", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$EditPincodeType;", "errorStack", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$InputError;", "itemCount", "getItemCount", "setItemCount", "(I)V", "pinCodePrompts", "", "", "getPinCodePrompts", "setPinCodePrompts", "userService", "Lcom/dotscreen/tv/common/bokit/MegacableUserService;", "verticalAdapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "verticalItemList", "Landroidx/leanback/widget/VerticalGridView;", "create", "Landroid/app/Dialog;", "createDialog", "createPinCodeTitles", "", "getErrorMessageAndResetInputs", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onAdapterComplete", "onPinCodeCompleteStateChanged", "isComplete", "pinCode", "view", "Lcom/dotscreen/portal/components/PinCodeView;", "resetAllInputs", "setEditPinCodeType", "type", NotificationsContract.COLUMN_COUNT, "MultipleCheckPinCodeTask", "PinCodeListAdapter", "PinCodeViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class Builder extends PinCodeDialog.Builder implements AdapterCompleteListener {
        private List<Boolean> completionStack;
        private EditPincodeType editPinCodeType;
        private final List<InputError> errorStack;
        private int itemCount;
        private List<String> pinCodePrompts;
        private final MegacableUserService userService;
        private RecyclerView.Adapter<RecyclerView.ViewHolder> verticalAdapter;
        private VerticalGridView verticalItemList;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[EditPincodeType.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[EditPincodeType.CreateProfile.ordinal()] = 1;
                $EnumSwitchMapping$0[EditPincodeType.UpdateParental.ordinal()] = 2;
                $EnumSwitchMapping$0[EditPincodeType.UpdateProfile.ordinal()] = 3;
                $EnumSwitchMapping$0[EditPincodeType.UpdatePurchase.ordinal()] = 4;
            }
        }

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.Builder, com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected int getContentViewId() {
            return R.layout.dialog_multiple_pin_code;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            UserService userService = ((PortalApplication) applicationContext).getBOService().getUserService();
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            this.userService = (MegacableUserService) userService;
            this.editPinCodeType = EditPincodeType.UpdateProfile;
            this.pinCodePrompts = new ArrayList();
            this.itemCount = 3;
            ArrayList arrayList = new ArrayList(3);
            for (int i = 0; i < 3; i++) {
                arrayList.add(false);
            }
            this.completionStack = arrayList;
            this.errorStack = new ArrayList();
        }

        protected final List<String> getPinCodePrompts() {
            return this.pinCodePrompts;
        }

        protected final void setPinCodePrompts(List<String> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.pinCodePrompts = list;
        }

        protected final int getItemCount() {
            return this.itemCount;
        }

        /* JADX INFO: renamed from: setItemCount, reason: collision with other method in class */
        protected final void m12setItemCount(int i) {
            this.itemCount = i;
        }

        public final List<Boolean> getCompletionStack() {
            return this.completionStack;
        }

        public final void setCompletionStack(List<Boolean> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.completionStack = list;
        }

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.Builder, com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected Dialog createDialog() {
            return new MultiplePinCodeDialog(getContext(), 2131951633);
        }

        public final PinCodeDialog.Builder setItemCount(int count) {
            this.itemCount = count;
            ArrayList arrayList = new ArrayList(count);
            for (int i = 0; i < count; i++) {
                arrayList.add(false);
            }
            this.completionStack = arrayList;
            return this;
        }

        public final PinCodeDialog.Builder setEditPinCodeType(EditPincodeType type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.editPinCodeType = type;
            if (type == EditPincodeType.CreateProfile) {
                return setItemCount(2);
            }
            return this;
        }

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.Builder, com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        public Dialog create() {
            Dialog dialogCreate = super.create(true);
            if (dialogCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog");
            }
            MultiplePinCodeDialog multiplePinCodeDialog = (MultiplePinCodeDialog) dialogCreate;
            createPinCodeTitles();
            int i = this.itemCount;
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add("");
            }
            multiplePinCodeDialog.setAllPinCodeValues(arrayList);
            ProgressBar progress = (ProgressBar) multiplePinCodeDialog.findViewById(R.id.progress);
            setDeleteImage((LinearLayout) multiplePinCodeDialog.findViewById(R.id.delete_message));
            setBottom_text((TextView) multiplePinCodeDialog.findViewById(R.id.bottom_text));
            Intrinsics.checkExpressionValueIsNotNull(progress, "progress");
            progress.setVisibility(4);
            this.verticalItemList = (VerticalGridView) multiplePinCodeDialog.findViewById(R.id.vertical_grid_of_choice);
            TextView it = (TextView) multiplePinCodeDialog.findViewById(R.id.informations);
            if (getInformations() != null) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                it.setText(getInformations());
                it.setVisibility(0);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                it.setVisibility(8);
            }
            multiplePinCodeDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog$Builder$create$3
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i3, KeyEvent event) {
                    View viewFindViewByPosition;
                    RecyclerView.LayoutManager layoutManager;
                    VerticalGridView verticalGridView = this.this$0.verticalItemList;
                    if (verticalGridView == null || (layoutManager = verticalGridView.getLayoutManager()) == null) {
                        viewFindViewByPosition = null;
                    } else {
                        VerticalGridView verticalGridView2 = this.this$0.verticalItemList;
                        viewFindViewByPosition = layoutManager.findViewByPosition(verticalGridView2 != null ? verticalGridView2.getSelectedPosition() : 0);
                    }
                    if (viewFindViewByPosition == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.components.PinCodeView");
                    }
                    PinCodeView pinCodeView = (PinCodeView) viewFindViewByPosition;
                    if ((pinCodeView.getPinCode().length() > 0) && pinCodeView.getPinCode().length() < 4 && i3 == 4) {
                        pinCodeView.setPinCode("");
                        return true;
                    }
                    if ((pinCodeView.getPinCode().length() > 0) && pinCodeView.getPinCode().length() < 4) {
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
                    if (event.getAction() == 1 && pinCodeView.getPinCode().length() < 4 && i3 >= 7 && i3 <= 16) {
                        pinCodeView.setPinCode(pinCodeView.getPinCode() + ((char) ((i3 - 7) + 48)));
                    }
                    return false;
                }
            });
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(multiplePinCodeDialog.getWindow().getAttributes());
            layoutParams.height = -1;
            layoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.right_popup_width);
            layoutParams.gravity = 5;
            Window window = multiplePinCodeDialog.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "dialog.window");
            window.setAttributes(layoutParams);
            PinCodeListAdapter pinCodeListAdapter = new PinCodeListAdapter(getContext(), multiplePinCodeDialog.getAllPinCodeValues(), this.pinCodePrompts, this, multiplePinCodeDialog);
            this.verticalAdapter = pinCodeListAdapter;
            VerticalGridView verticalGridView = this.verticalItemList;
            if (verticalGridView != null) {
                verticalGridView.setAdapter(pinCodeListAdapter);
            }
            return multiplePinCodeDialog;
        }

        private final void createPinCodeTitles() {
            Context context;
            List<String> listListOf;
            EditPincodeType editPincodeType = this.editPinCodeType;
            EditPincodeType editPincodeType2 = EditPincodeType.CreateProfile;
            int i = R.string.input_new_profile_pin_code;
            if (editPincodeType == editPincodeType2) {
                listListOf = CollectionsKt.listOf((Object[]) new String[]{getContext().getString(R.string.input_new_profile_pin_code), getContext().getString(R.string.validate_new_profile_pin_code)});
            } else {
                String[] strArr = new String[3];
                strArr[0] = getContext().getString(R.string.input_current_pin_code);
                if (this.editPinCodeType == EditPincodeType.UpdateProfile) {
                    context = getContext();
                } else if (this.editPinCodeType == EditPincodeType.UpdateParental) {
                    context = getContext();
                    i = R.string.edit_parental_pin_code;
                } else {
                    context = getContext();
                    i = R.string.edit_purchase_pin_code;
                }
                strArr[1] = context.getString(i);
                strArr[2] = getContext().getString(R.string.validate_new_profile_pin_code);
                listListOf = CollectionsKt.listOf((Object[]) strArr);
            }
            this.pinCodePrompts = listListOf;
        }

        /* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0016H\u0016J \u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0016R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder$PinCodeListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder$PinCodeViewHolder;", "Lcom/dotscreen/portal/components/PinCodeView$OnPinCodeCompleteStateChanged;", "mContext", "Landroid/content/Context;", "values", "", "", "prompts", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$AdapterCompleteListener;", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "(Landroid/content/Context;Ljava/util/List;Ljava/util/List;Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$AdapterCompleteListener;Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;)V", "datas", "getDialog", "()Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "getListener", "()Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$AdapterCompleteListener;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "viewType", "onPinCodeCompleteStateChanged", "isComplete", "", "pinCode", "view", "Lcom/dotscreen/portal/components/PinCodeView;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
        public static final class PinCodeListAdapter extends RecyclerView.Adapter<PinCodeViewHolder> implements PinCodeView.OnPinCodeCompleteStateChanged {
            private final List<String> datas;
            private final MultiplePinCodeDialog dialog;
            private final AdapterCompleteListener listener;
            private Context mContext;
            private final List<String> prompts;

            public PinCodeListAdapter(Context mContext, List<String> values, List<String> prompts, AdapterCompleteListener listener, MultiplePinCodeDialog dialog) {
                Intrinsics.checkParameterIsNotNull(mContext, "mContext");
                Intrinsics.checkParameterIsNotNull(values, "values");
                Intrinsics.checkParameterIsNotNull(prompts, "prompts");
                Intrinsics.checkParameterIsNotNull(listener, "listener");
                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                this.mContext = mContext;
                this.prompts = prompts;
                this.listener = listener;
                this.dialog = dialog;
                this.datas = values;
            }

            public final MultiplePinCodeDialog getDialog() {
                return this.dialog;
            }

            public final AdapterCompleteListener getListener() {
                return this.listener;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public PinCodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                Intrinsics.checkParameterIsNotNull(viewGroup, "viewGroup");
                return new PinCodeViewHolder(this.mContext, this);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return this.datas.size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(PinCodeViewHolder holder, int position) {
                Intrinsics.checkParameterIsNotNull(holder, "holder");
                View view = holder.itemView;
                if (view == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.components.PinCodeView");
                }
                ((PinCodeView) view).setPrompt(this.prompts.get(position));
                ((PinCodeView) holder.itemView).setPinCode(this.datas.get(position));
                ((PinCodeView) holder.itemView).updatePinCode();
            }

            @Override // com.dotscreen.portal.components.PinCodeView.OnPinCodeCompleteStateChanged
            public void onPinCodeCompleteStateChanged(boolean isComplete, String pinCode, PinCodeView view) {
                Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                Intrinsics.checkParameterIsNotNull(view, "view");
                this.listener.onPinCodeCompleteStateChanged(isComplete, pinCode, view, this.dialog);
            }
        }

        /* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder$PinCodeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/dotscreen/portal/components/PinCodeView$OnPinCodeCompleteStateChanged;", "(Landroid/content/Context;Lcom/dotscreen/portal/components/PinCodeView$OnPinCodeCompleteStateChanged;)V", "getContext", "()Landroid/content/Context;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
        public static final class PinCodeViewHolder extends RecyclerView.ViewHolder {
            private final Context context;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PinCodeViewHolder(Context context, PinCodeView.OnPinCodeCompleteStateChanged listener) {
                super(new PinCodeView(context));
                Intrinsics.checkParameterIsNotNull(context, "context");
                Intrinsics.checkParameterIsNotNull(listener, "listener");
                this.context = context;
                View itemView = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                itemView.setFocusable(true);
                View itemView2 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(itemView2, "itemView");
                itemView2.setFocusableInTouchMode(true);
                this.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog.Builder.PinCodeViewHolder.1
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View view, boolean z) {
                        Intrinsics.checkExpressionValueIsNotNull(view, "view");
                        view.setSelected(z);
                    }
                });
                View view = this.itemView;
                if (view == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.components.PinCodeView");
                }
                ((PinCodeView) view).setOnPinCodeCompleteStateChangedListener(listener);
                ((PinCodeView) this.itemView).updatePinCode();
            }

            public final Context getContext() {
                return this.context;
            }
        }

        @Override // com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog.AdapterCompleteListener
        public void onAdapterComplete(MultiplePinCodeDialog dialog) {
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            if (getCheckPinCode()) {
                new MultipleCheckPinCodeTask(this, dialog, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dialog.getAllPinCodeValues());
                return;
            }
            PinCodeDialog.OnPinCodeListener onPinCodeListener = getOnPinCodeListener();
            if (onPinCodeListener != null) {
                onPinCodeListener.onPinCodeCorrect(dialog, dialog.getAllPinCodeValues());
            }
        }

        @Override // com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog.AdapterCompleteListener
        public void onPinCodeCompleteStateChanged(boolean isComplete, String pinCode, PinCodeView view, MultiplePinCodeDialog dialog) {
            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            VerticalGridView verticalGridView = this.verticalItemList;
            Integer numValueOf = verticalGridView != null ? Integer.valueOf(verticalGridView.getSelectedPosition()) : null;
            if (numValueOf != null) {
                this.completionStack.set(numValueOf.intValue(), Boolean.valueOf(isComplete));
                dialog.getAllPinCodeValues().set(numValueOf.intValue(), pinCode);
                if (isComplete && numValueOf.intValue() < this.itemCount - 1) {
                    VerticalGridView verticalGridView2 = this.verticalItemList;
                    if (verticalGridView2 == null) {
                        Intrinsics.throwNpe();
                    }
                    verticalGridView2.setSelectedPosition(numValueOf.intValue() + 1);
                } else if (numValueOf.intValue() >= 1 && !this.completionStack.get(numValueOf.intValue() - 1).booleanValue()) {
                    VerticalGridView verticalGridView3 = this.verticalItemList;
                    if (verticalGridView3 == null) {
                        Intrinsics.throwNpe();
                    }
                    verticalGridView3.setSelectedPosition(numValueOf.intValue() - 1);
                }
            }
            if (this.completionStack.contains(false)) {
                return;
            }
            onAdapterComplete(dialog);
        }

        /* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0096\u0004\u0018\u00002.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b0\u00050\u0001B\u0015\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJG\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b\u0018\u00010\u00052\"\u0010\u0015\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0016\"\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u0017J&\u0010\u0018\u001a\u00020\u00192\u001c\u0010\u001a\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b\u0018\u00010\u0005H\u0014J\b\u0010\u001b\u001a\u00020\u0019H\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder$MultipleCheckPinCodeTask;", "Landroid/os/AsyncTask;", "", "", "Ljava/lang/Void;", "Lkotlin/Pair;", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "builder", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder;", "(Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder;Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder;)V", "getBuilder", "()Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$Builder;", "getDialog", "()Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "handler", "Landroid/os/Handler;", "doInBackground", "params", "", "([Ljava/util/List;)Lkotlin/Pair;", "onPostExecute", "", "result", "onPreExecute", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
        public class MultipleCheckPinCodeTask extends AsyncTask<List<? extends String>, Void, Pair<? extends Boolean, ? extends Exception>> {
            private final Builder builder;
            private final MultiplePinCodeDialog dialog;
            private final Handler handler;
            final /* synthetic */ Builder this$0;

            @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
            public final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EditPincodeType.values().length];
                    $EnumSwitchMapping$0 = iArr;
                    iArr[EditPincodeType.UpdateProfile.ordinal()] = 1;
                    $EnumSwitchMapping$0[EditPincodeType.UpdateParental.ordinal()] = 2;
                    $EnumSwitchMapping$0[EditPincodeType.UpdatePurchase.ordinal()] = 3;
                }
            }

            public MultipleCheckPinCodeTask(Builder builder, MultiplePinCodeDialog dialog, Builder builder2) {
                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                Intrinsics.checkParameterIsNotNull(builder2, "builder");
                this.this$0 = builder;
                this.dialog = dialog;
                this.builder = builder2;
                this.handler = new Handler();
            }

            @Override // android.os.AsyncTask
            public /* bridge */ /* synthetic */ Pair<? extends Boolean, ? extends Exception> doInBackground(List<? extends String>[] listArr) {
                return doInBackground2((List<String>[]) listArr);
            }

            public final Builder getBuilder() {
                return this.builder;
            }

            public final MultiplePinCodeDialog getDialog() {
                return this.dialog;
            }

            @Override // android.os.AsyncTask
            public /* bridge */ /* synthetic */ void onPostExecute(Pair<? extends Boolean, ? extends Exception> pair) {
                onPostExecute2((Pair<Boolean, ? extends Exception>) pair);
            }

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                super.onPreExecute();
                ProgressBar progressBar = (ProgressBar) this.dialog.findViewById(R.id.progress);
                if (progressBar != null) {
                    progressBar.setVisibility(0);
                    VerticalGridView verticalGridView = this.this$0.verticalItemList;
                    if (verticalGridView != null) {
                        verticalGridView.setVisibility(8);
                    }
                }
            }

            /* JADX INFO: renamed from: doInBackground, reason: avoid collision after fix types in other method */
            protected Pair<Boolean, Exception> doInBackground2(List<String>... params) {
                Single<JSONObject> singleUpdateProfilePinCode;
                Intrinsics.checkParameterIsNotNull(params, "params");
                try {
                    if (this.this$0.editPinCodeType != EditPincodeType.CreateProfile) {
                        List<String> list = params[0];
                        if (list == null) {
                            Intrinsics.throwNpe();
                        }
                        String str = list.get(1);
                        List<String> list2 = params[0];
                        if (list2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (Intrinsics.areEqual(str, list2.get(2))) {
                            Log.v(PinCodeDialog.INSTANCE.getTAG(), "Submitted pincode => " + params[0]);
                            int i = WhenMappings.$EnumSwitchMapping$0[this.this$0.editPinCodeType.ordinal()];
                            if (i == 1) {
                                MegacableUserService megacableUserService = this.this$0.userService;
                                List<String> list3 = params[0];
                                if (list3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                String str2 = list3.get(1);
                                List<String> list4 = params[0];
                                if (list4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                Single<String> singleJust = Single.just(list4.get(0));
                                Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(params[0]!![0])");
                                singleUpdateProfilePinCode = megacableUserService.updateProfilePinCode(str2, singleJust);
                            } else if (i == 2) {
                                MegacableUserService megacableUserService2 = this.this$0.userService;
                                List<String> list5 = params[0];
                                if (list5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                String str3 = list5.get(1);
                                List<String> list6 = params[0];
                                if (list6 == null) {
                                    Intrinsics.throwNpe();
                                }
                                Single<String> singleJust2 = Single.just(list6.get(0));
                                Intrinsics.checkExpressionValueIsNotNull(singleJust2, "Single.just(params[0]!![0])");
                                singleUpdateProfilePinCode = megacableUserService2.updateParentalPinCode(str3, singleJust2);
                            } else if (i == 3) {
                                MegacableUserService megacableUserService3 = this.this$0.userService;
                                List<String> list7 = params[0];
                                if (list7 == null) {
                                    Intrinsics.throwNpe();
                                }
                                String str4 = list7.get(1);
                                List<String> list8 = params[0];
                                if (list8 == null) {
                                    Intrinsics.throwNpe();
                                }
                                Single<String> singleJust3 = Single.just(list8.get(0));
                                Intrinsics.checkExpressionValueIsNotNull(singleJust3, "Single.just(params[0]!![0])");
                                singleUpdateProfilePinCode = megacableUserService3.updatePurchasePinCode(str4, singleJust3);
                            } else {
                                singleUpdateProfilePinCode = Single.error(new Exception("Unknown type of edit pincode"));
                                Intrinsics.checkExpressionValueIsNotNull(singleUpdateProfilePinCode, "Single.error(Exception(\"…n type of edit pincode\"))");
                            }
                            return new Pair<>(singleUpdateProfilePinCode.map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog$Builder$MultipleCheckPinCodeTask$doInBackground$1
                                public final boolean apply(JSONObject it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return true;
                                }

                                @Override // io.reactivex.functions.Function
                                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                                    return Boolean.valueOf(apply((JSONObject) obj));
                                }
                            }).blockingGet(), null);
                        }
                    }
                    if (this.this$0.editPinCodeType == EditPincodeType.CreateProfile) {
                        List<String> list9 = params[0];
                        if (list9 == null) {
                            Intrinsics.throwNpe();
                        }
                        String str5 = list9.get(0);
                        List<String> list10 = params[0];
                        if (list10 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (Intrinsics.areEqual(str5, list10.get(1))) {
                            MegacableUserService megacableUserService4 = this.this$0.userService;
                            List<String> list11 = params[0];
                            if (list11 == null) {
                                Intrinsics.throwNpe();
                            }
                            return new Pair<>(MegacableUserService.updateProfilePinCode$default(megacableUserService4, list11.get(0), null, 2, null).map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog$Builder$MultipleCheckPinCodeTask$doInBackground$2
                                public final boolean apply(JSONObject it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return true;
                                }

                                @Override // io.reactivex.functions.Function
                                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                                    return Boolean.valueOf(apply((JSONObject) obj));
                                }
                            }).blockingGet(), null);
                        }
                    }
                    String string = this.this$0.getContext().getString(R.string.error_pin_code_not_match);
                    Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…error_pin_code_not_match)");
                    return new Pair<>(false, new NotMatchException(string));
                } catch (Exception e) {
                    return new Pair<>(false, e);
                }
            }

            /* JADX INFO: renamed from: onPostExecute, reason: avoid collision after fix types in other method */
            protected void onPostExecute2(Pair<Boolean, ? extends Exception> result) {
                super.onPostExecute(result);
                if (result == null) {
                    Intrinsics.throwNpe();
                }
                if (result.getFirst().booleanValue()) {
                    PinCodeDialog.OnPinCodeListener onPinCodeListener = this.builder.getOnPinCodeListener();
                    if (onPinCodeListener != null) {
                        MultiplePinCodeDialog multiplePinCodeDialog = this.dialog;
                        onPinCodeListener.onPinCodeCorrect(multiplePinCodeDialog, multiplePinCodeDialog.getAllPinCodeValues());
                        return;
                    }
                    return;
                }
                this.handler.postDelayed(new MultiplePinCodeDialog$Builder$MultipleCheckPinCodeTask$onPostExecute$2(this, result), 3000L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getErrorMessageAndResetInputs(MultiplePinCodeDialog dialog, Exception exception) {
            String error;
            if (exception instanceof NotMatchException) {
                error = exception.getMessage();
                if (error == null) {
                    error = getContext().getString(R.string.error_pin_code_unknown);
                }
            } else {
                int i = WhenMappings.$EnumSwitchMapping$0[this.editPinCodeType.ordinal()];
                if (i == 1) {
                    error = getContext().getString(R.string.error_pin_code_not_match);
                } else {
                    if (i != 2 && i != 3 && i != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    error = getContext().getString(R.string.error_pin_code_wrong);
                }
            }
            resetAllInputs(dialog);
            Intrinsics.checkExpressionValueIsNotNull(error, "error");
            return error;
        }

        private final void resetAllInputs(MultiplePinCodeDialog dialog) {
            dialog.getAllPinCodeValues().clear();
            int i = this.itemCount;
            for (int i2 = 0; i2 < i; i2++) {
                dialog.getAllPinCodeValues().add("");
                this.completionStack.replaceAll(new UnaryOperator<Boolean>() { // from class: com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog$Builder$resetAllInputs$1
                    public final boolean apply(Boolean it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return false;
                    }

                    @Override // java.util.function.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return Boolean.valueOf(apply((Boolean) obj));
                    }
                });
            }
            RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = this.verticalAdapter;
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
            VerticalGridView verticalGridView = this.verticalItemList;
            if (verticalGridView != null) {
                verticalGridView.setSelectedPosition(0);
            }
        }
    }

    /* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog$NotMatchException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class NotMatchException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotMatchException(String message) {
            super(message);
            Intrinsics.checkParameterIsNotNull(message, "message");
        }
    }
}
