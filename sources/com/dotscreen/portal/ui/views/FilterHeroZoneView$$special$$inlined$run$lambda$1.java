package com.dotscreen.portal.ui.views;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.ui.dialog.CheckListDialog;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterHeroZoneView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/dotscreen/portal/ui/views/FilterHeroZoneView$1$1"}, k = 3, mv = {1, 1, 16})
final class FilterHeroZoneView$$special$$inlined$run$lambda$1 implements View.OnClickListener {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ FontTextView $this_run;
    final /* synthetic */ FilterHeroZoneView this$0;

    FilterHeroZoneView$$special$$inlined$run$lambda$1(FontTextView fontTextView, FilterHeroZoneView filterHeroZoneView, Context context) {
        this.$this_run = fontTextView;
        this.this$0 = filterHeroZoneView;
        this.$context$inlined = context;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.this$0.getChannelGenres() == null) {
            return;
        }
        final List listMutableListOf = CollectionsKt.mutableListOf(this.$context$inlined.getString(R.string.epg_filter_all));
        List<String> channelGenres = this.this$0.getChannelGenres();
        if (channelGenres == null) {
            Intrinsics.throwNpe();
        }
        listMutableListOf.addAll(channelGenres);
        Context context = this.$context$inlined;
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
        ((Activity) context).runOnUiThread(new Runnable() { // from class: com.dotscreen.portal.ui.views.FilterHeroZoneView$$special$$inlined$run$lambda$1.1
            @Override // java.lang.Runnable
            public final void run() {
                CheckListDialog.Builder itemList = new CheckListDialog.Builder(FilterHeroZoneView$$special$$inlined$run$lambda$1.this.$context$inlined).setTitle(R.string.dialog_select_category_title).setItemList(listMutableListOf);
                List<String> channelGenres2 = FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.getChannelGenres();
                if (channelGenres2 == null) {
                    Intrinsics.throwNpe();
                }
                String currentGenre = FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.getCurrentGenre();
                if (currentGenre == null) {
                    currentGenre = "";
                }
                itemList.setSelectedItem(channelGenres2.indexOf(currentGenre) + 1).setOnItemClickedListener(new CheckListDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.views.FilterHeroZoneView$$special$.inlined.run.lambda.1.1.1
                    @Override // com.dotscreen.tv.ui.dialog.CheckListDialog.OnItemClickedListener
                    public void onItemClicked(CheckListDialog dialog, int itemIndex) {
                        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                        DSLog.INSTANCE.a("Clicked on a Filter ");
                        if (itemIndex == 0) {
                            FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.setCurrentGenre((String) null);
                            FilterHeroZoneView.Companion.OnGenreChangedListener changeListener = FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.getChangeListener();
                            if (changeListener != null) {
                                changeListener.onGenreChanged(FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.getCurrentGenre());
                            }
                            FilterHeroZoneView$$special$$inlined$run$lambda$1.this.$this_run.setText(FilterHeroZoneView$$special$$inlined$run$lambda$1.this.$context$inlined.getString(R.string.epg_tab_filter, FilterHeroZoneView$$special$$inlined$run$lambda$1.this.$context$inlined.getString(R.string.epg_filter_all)));
                        } else {
                            FilterHeroZoneView filterHeroZoneView = FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0;
                            List<String> channelGenres3 = FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.getChannelGenres();
                            if (channelGenres3 == null) {
                                Intrinsics.throwNpe();
                            }
                            filterHeroZoneView.setCurrentGenre(channelGenres3.get(itemIndex - 1));
                            FilterHeroZoneView.Companion.OnGenreChangedListener changeListener2 = FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.getChangeListener();
                            if (changeListener2 != null) {
                                changeListener2.onGenreChanged(FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.getCurrentGenre());
                            }
                            FilterHeroZoneView$$special$$inlined$run$lambda$1.this.$this_run.setText(FilterHeroZoneView$$special$$inlined$run$lambda$1.this.$context$inlined.getString(R.string.epg_tab_filter, FilterHeroZoneView$$special$$inlined$run$lambda$1.this.this$0.getCurrentGenre()));
                        }
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });
    }
}
