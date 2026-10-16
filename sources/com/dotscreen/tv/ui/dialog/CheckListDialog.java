package com.dotscreen.tv.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.android.tv.common.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CheckListDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0002\f\rB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/CheckListDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "selectedItem", "getSelectedItem", "()I", "setSelectedItem", "(I)V", "Builder", "OnItemClickedListener", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CheckListDialog extends Dialog {
    private int selectedItem;

    /* JADX INFO: compiled from: CheckListDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/CheckListDialog$OnItemClickedListener;", "", "onItemClicked", "", "dialog", "Lcom/dotscreen/tv/ui/dialog/CheckListDialog;", "itemIndex", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnItemClickedListener {
        void onItemClicked(CheckListDialog dialog, int itemIndex);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckListDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final int getSelectedItem() {
        return this.selectedItem;
    }

    public final void setSelectedItem(int i) {
        this.selectedItem = i;
    }

    /* JADX INFO: compiled from: CheckListDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\u0014\u0010\u0012\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006R\u0014\u0010\u0005\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/CheckListDialog$Builder;", "Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contentViewId", "", "getContentViewId", "()I", "itemList", "", "", "onItemClickedListener", "Lcom/dotscreen/tv/ui/dialog/CheckListDialog$OnItemClickedListener;", "selectedItem", "create", "Landroid/app/Dialog;", "createDialog", "setItemList", "items", "setOnItemClickedListener", "setSelectedItem", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class Builder extends PortalDialog.InternalBuilder<Builder> {
        private List<String> itemList;
        private OnItemClickedListener onItemClickedListener;
        private int selectedItem;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected int getContentViewId() {
            return R.layout.dialog_list;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected Dialog createDialog() {
            return new CheckListDialog(getContext(), R.style.AppTheme_DialogTranslucent);
        }

        public final Builder setItemList(List<String> items) {
            Intrinsics.checkParameterIsNotNull(items, "items");
            this.itemList = items;
            return this;
        }

        public final Builder setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
            Intrinsics.checkParameterIsNotNull(onItemClickedListener, "onItemClickedListener");
            this.onItemClickedListener = onItemClickedListener;
            return this;
        }

        public final Builder setSelectedItem(int selectedItem) {
            this.selectedItem = selectedItem;
            return this;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        public Dialog create() {
            RadioGroup radioGroup;
            Dialog dialogCreate = super.create();
            if (dialogCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.ui.dialog.CheckListDialog");
            }
            final CheckListDialog checkListDialog = (CheckListDialog) dialogCreate;
            checkListDialog.setSelectedItem(this.selectedItem);
            final List<String> list = this.itemList;
            if (list != null && (radioGroup = (RadioGroup) checkListDialog.findViewById(R.id.list_container)) != null) {
                int i = 0;
                for (String str : list) {
                    View viewInflate = checkListDialog.getLayoutInflater().inflate(R.layout.dialog_radio_button_item, (ViewGroup) null);
                    if (viewInflate == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.widget.RadioButton");
                    }
                    RadioButton radioButton = (RadioButton) viewInflate;
                    radioButton.setId(i);
                    radioButton.setText(str);
                    if (i == checkListDialog.getSelectedItem()) {
                        radioButton.requestFocus();
                    }
                    radioGroup.addView(radioButton, new ViewGroup.LayoutParams(-1, getContext().getResources().getDimensionPixelSize(R.dimen.dialog_list_item_height)));
                    i++;
                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dotscreen.tv.ui.dialog.CheckListDialog$Builder$create$$inlined$let$lambda$1
                    @Override // android.widget.RadioGroup.OnCheckedChangeListener
                    public final void onCheckedChanged(RadioGroup radioGroup2, int i2) {
                        checkListDialog.setSelectedItem(i2);
                        CheckListDialog.OnItemClickedListener onItemClickedListener = this.onItemClickedListener;
                        if (onItemClickedListener != null) {
                            onItemClickedListener.onItemClicked(checkListDialog, i2);
                        }
                    }
                });
                radioGroup.check(checkListDialog.getSelectedItem());
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(checkListDialog.getWindow().getAttributes());
            layoutParams.height = -1;
            layoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.right_popup_width);
            layoutParams.gravity = 5;
            Window window = checkListDialog.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "dialog.window");
            window.setAttributes(layoutParams);
            return checkListDialog;
        }
    }
}
