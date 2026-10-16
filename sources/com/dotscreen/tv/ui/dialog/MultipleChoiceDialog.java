package com.dotscreen.tv.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tv.common.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultipleChoiceDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "AbstractListAdapter", "Builder", "DefaultFocusableTextViewHolder", "DefaultTextView", "OnItemClickedListener", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MultipleChoiceDialog extends Dialog {

    /* JADX INFO: compiled from: MultipleChoiceDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001H&¨\u0006\t"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog$OnItemClickedListener;", "", "onItemClicked", "", "dialog", "Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog;", "itemIndex", "", "item", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnItemClickedListener {
        void onItemClicked(MultipleChoiceDialog dialog, int itemIndex, Object item);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipleChoiceDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX INFO: compiled from: MultipleChoiceDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0014J,\u0010\u0014\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u0015\"\n\b\u0001\u0010\u0016*\u0004\u0018\u00010\u00102\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u00160\u0018J\u0014\u0010\u0019\u001a\u00020\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\nJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rR\u0014\u0010\u0005\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog$Builder;", "Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contentViewId", "", "getContentViewId", "()I", "itemList", "", "", "onItemClickedListener", "Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog$OnItemClickedListener;", "verticalAdapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "create", "Landroid/app/Dialog;", "createDialog", "setAdapter", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "K", "adapter", "Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog$AbstractListAdapter;", "setItemList", "items", "", "setOnItemClickedListener", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class Builder extends PortalDialog.InternalBuilder<Builder> {
        private List<? extends Object> itemList;
        private OnItemClickedListener onItemClickedListener;
        private RecyclerView.Adapter<RecyclerView.ViewHolder> verticalAdapter;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.itemList = CollectionsKt.emptyList();
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected int getContentViewId() {
            return R.layout.dialog_multiple_button;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected Dialog createDialog() {
            return new MultipleChoiceDialog(getContext(), R.style.AppTheme_DialogTranslucent);
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        public Dialog create() {
            Dialog dialogCreate = super.create();
            if (dialogCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.ui.dialog.MultipleChoiceDialog");
            }
            MultipleChoiceDialog multipleChoiceDialog = (MultipleChoiceDialog) dialogCreate;
            VerticalGridView verticalItemList = (VerticalGridView) multipleChoiceDialog.findViewById(R.id.vertical_grid_of_choice);
            if (this.verticalAdapter == null) {
                this.verticalAdapter = new MultipleChoiceDialog$Builder$create$1(this, multipleChoiceDialog, getContext());
            }
            RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = this.verticalAdapter;
            if (adapter == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.ui.dialog.MultipleChoiceDialog.AbstractListAdapter<kotlin.Any?, *>");
            }
            ((AbstractListAdapter) adapter).setData(this.itemList);
            Intrinsics.checkExpressionValueIsNotNull(verticalItemList, "verticalItemList");
            verticalItemList.setAdapter(this.verticalAdapter);
            return multipleChoiceDialog;
        }

        public final <V, K extends RecyclerView.ViewHolder> Builder setAdapter(AbstractListAdapter<V, K> adapter) {
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            this.verticalAdapter = adapter;
            return this;
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
    }

    /* JADX INFO: compiled from: MultipleChoiceDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\n\b\u0001\u0010\u0002*\u0004\u0018\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\u001d\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010\u0012\u001a\u00020\u000eH&¢\u0006\u0002\u0010\u0013J\u001d\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH&¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u00020\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0007R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog$AbstractListAdapter;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "K", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getMContext", "()Landroid/content/Context;", "setMContext", "mData", "", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "viewType", "(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "setData", "data", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static abstract class AbstractListAdapter<V, K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {
        private Context mContext;
        private List<? extends V> mData;

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public abstract void onBindViewHolder(K holder, int position);

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public abstract K onCreateViewHolder(ViewGroup viewGroup, int viewType);

        public AbstractListAdapter(Context mContext) {
            Intrinsics.checkParameterIsNotNull(mContext, "mContext");
            this.mContext = mContext;
            this.mData = new ArrayList();
        }

        protected final Context getMContext() {
            return this.mContext;
        }

        protected final void setMContext(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "<set-?>");
            this.mContext = context;
        }

        public final void setData(List<? extends V> data) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            this.mData = data;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mData.size();
        }
    }

    /* JADX INFO: compiled from: MultipleChoiceDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog$DefaultFocusableTextViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class DefaultFocusableTextViewHolder extends RecyclerView.ViewHolder {
        private final Context context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultFocusableTextViewHolder(Context context) {
            super(new DefaultTextView(context, null, 0, 6, null));
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
            View itemView = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
            itemView.setFocusable(true);
            View itemView2 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(itemView2, "itemView");
            itemView2.setFocusableInTouchMode(true);
            this.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.dotscreen.tv.ui.dialog.MultipleChoiceDialog.DefaultFocusableTextViewHolder.1
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    Intrinsics.checkExpressionValueIsNotNull(view, "view");
                    view.setSelected(z);
                }
            });
        }

        public final Context getContext() {
            return this.context;
        }
    }

    /* JADX INFO: compiled from: MultipleChoiceDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/MultipleChoiceDialog$DefaultTextView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "", "text", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class DefaultTextView extends FrameLayout {
        private String text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultTextView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            Intrinsics.checkParameterIsNotNull(context, "context");
            FrameLayout.inflate(context, R.layout.text_choice_view, this);
        }

        public /* synthetic */ DefaultTextView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
        }

        public final String getText() {
            return this.text;
        }

        public final void setText(String str) {
            this.text = str;
            View viewFindViewById = findViewById(R.id.button_root);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "this.findViewById<TextView>(R.id.button_root)");
            ((TextView) viewFindViewById).setText(str);
        }
    }
}
