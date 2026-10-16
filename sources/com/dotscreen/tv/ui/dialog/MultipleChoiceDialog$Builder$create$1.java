package com.dotscreen.tv.ui.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultipleChoiceDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0016¨\u0006\r"}, d2 = {"com/dotscreen/tv/ui/dialog/MultipleChoiceDialog$Builder$create$1", "Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog$AbstractListAdapter;", "", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "viewType", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MultipleChoiceDialog$Builder$create$1 extends MultipleChoiceDialog.AbstractListAdapter<String, RecyclerView.ViewHolder> {
    final /* synthetic */ MultipleChoiceDialog $dialog;
    final /* synthetic */ MultipleChoiceDialog.Builder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MultipleChoiceDialog$Builder$create$1(MultipleChoiceDialog.Builder builder, MultipleChoiceDialog multipleChoiceDialog, Context context) {
        super(context);
        this.this$0 = builder;
        this.$dialog = multipleChoiceDialog;
    }

    @Override // com.dotscreen.tv.ui.dialog.MultipleChoiceDialog.AbstractListAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "viewGroup");
        return new MultipleChoiceDialog.DefaultFocusableTextViewHolder(this.this$0.getContext());
    }

    @Override // com.dotscreen.tv.ui.dialog.MultipleChoiceDialog.AbstractListAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        final Object obj = this.this$0.itemList.get(position);
        View view = holder.itemView;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.ui.dialog.MultipleChoiceDialog.DefaultTextView");
        }
        MultipleChoiceDialog.DefaultTextView defaultTextView = (MultipleChoiceDialog.DefaultTextView) view;
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        defaultTextView.setText((String) obj);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.tv.ui.dialog.MultipleChoiceDialog$Builder$create$1$onBindViewHolder$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MultipleChoiceDialog.OnItemClickedListener onItemClickedListener = this.this$0.this$0.onItemClickedListener;
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClicked(this.this$0.$dialog, position, obj);
                }
            }
        });
    }
}
