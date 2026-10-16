package com.dotscreen.portal.ui.screens.setup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import com.dotscreen.farkit.updater.UpdateManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdaterActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\b2\u000e\u0010\t\u001a\n \u0004*\u0004\u0018\u00010\n0\nH\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroidx/leanback/widget/Presenter$ViewHolder;", "kotlin.jvm.PlatformType", "item", "", "<anonymous parameter 2>", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "<anonymous parameter 3>", "Landroidx/leanback/widget/Row;", "onItemClicked"}, k = 3, mv = {1, 1, 16})
final class UpdaterActivity$onCreate$listener$1 implements OnItemViewClickedListener {
    final /* synthetic */ UpdaterActivity this$0;

    UpdaterActivity$onCreate$listener$1(UpdaterActivity updaterActivity) {
        this.this$0 = updaterActivity;
    }

    @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
    public final void onItemClicked(Presenter.ViewHolder viewHolder, final Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        if (obj instanceof UpdateManager.UpdateItem) {
            UpdateManager.UpdateItem updateItem = (UpdateManager.UpdateItem) obj;
            this.this$0.updateItem = updateItem;
            final AlertDialog alertDialogShow = new AlertDialog.Builder(this.this$0).setTitle(updateItem.getTitle()).setMessage("Apply update ?").setPositiveButton("No", new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity$onCreate$listener$1$alertDialog$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                }
            }).setNegativeButton("Yes", new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity$onCreate$listener$1$alertDialog$2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.this$0.this$0.cancelable = UpdaterActivity.access$getUpdateManager$p(this.this$0.this$0).download(((UpdateManager.UpdateItem) obj).getPackageName(), ((UpdateManager.UpdateItem) obj).getVersionCode());
                }
            }).show();
            alertDialogShow.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.dotscreen.portal.ui.screens.setup.UpdaterActivity$onCreate$listener$1.1
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    Button button = alertDialogShow.getButton(-1);
                    Intrinsics.checkExpressionValueIsNotNull(button, "button");
                    button.setFocusable(true);
                    button.setFocusableInTouchMode(true);
                    button.requestFocus();
                }
            });
        }
    }
}
