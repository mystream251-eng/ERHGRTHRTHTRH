package com.dotscreen.portal.ui.screens.guide;

import android.content.res.Resources;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.dotscreen.megacable.portal.R;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimeListAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0015\u0016B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\tH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/TimeListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/dotscreen/portal/ui/screens/guide/TimeListAdapter$TimeViewHolder;", "res", "Landroid/content/res/Resources;", "(Landroid/content/res/Resources;)V", "mStartUtcMs", "", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "update", "startTimeMs", "Companion", "TimeViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TimeListAdapter extends RecyclerView.Adapter<TimeViewHolder> {
    private static final long TIME_UNIT_MS = TimeUnit.MINUTES.toMillis(30);
    private static int sRowHeaderOverlapping;
    private long mStartUtcMs;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return R.layout.program_guide_table_header_row_item;
    }

    public TimeListAdapter(Resources res) {
        Intrinsics.checkParameterIsNotNull(res, "res");
        if (sRowHeaderOverlapping == 0) {
            sRowHeaderOverlapping = Math.abs(res.getDimensionPixelOffset(R.dimen.program_guide_table_header_row_overlap));
        }
    }

    /* JADX INFO: compiled from: TimeListAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/TimeListAdapter$TimeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class TimeViewHolder extends RecyclerView.ViewHolder {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TimeViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        }
    }

    public final void update(long startTimeMs) {
        this.mStartUtcMs = startTimeMs;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(TimeViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        long j = this.mStartUtcMs;
        long j2 = TIME_UNIT_MS;
        long j3 = j + (((long) position) * j2);
        long j4 = j2 + j3;
        View view = holder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        View viewFindViewById = view.findViewById(R.id.time);
        if (viewFindViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        ((TextView) viewFindViewById).setText(DateFormat.getTimeFormat(view.getContext()).format(new Date(j3)));
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        layoutParams2.width = GuideUtils.INSTANCE.convertMillisToPixel(j3, j4);
        if (position == 0) {
            layoutParams2.setMarginStart(sRowHeaderOverlapping - (layoutParams2.width / 2));
        } else {
            layoutParams2.setMarginStart(0);
        }
        view.setLayoutParams(layoutParams2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return new TimeViewHolder(itemView);
    }
}
