package com.dotscreen.portal.ui.screens.guide;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.common.utils.CommonUtils;

/* JADX INFO: loaded from: classes.dex */
public class ProgramListAdapter extends RecyclerView.Adapter<ProgramViewHolder> implements ProgramManager.TableEntriesUpdatedListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "ProgramListAdapter";
    private final String mBlockedProgramTitle;
    private String mChannelId;
    private final int mChannelIndex;
    private final String mNoInfoProgramTitle;
    private final ProgramManager mProgramManager;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return R.layout.program_guide_table_item;
    }

    public ProgramListAdapter(Resources resources, ProgramManager programManager, int i) {
        setHasStableIds(true);
        this.mProgramManager = programManager;
        this.mChannelIndex = i;
        this.mNoInfoProgramTitle = resources.getString(R.string.program_title_for_no_information);
        this.mBlockedProgramTitle = resources.getString(R.string.program_title_for_blocked_channel);
        onTableEntriesUpdated();
    }

    @Override // com.dotscreen.portal.ui.screens.guide.ProgramManager.TableEntriesUpdatedListener
    public void onTableEntriesUpdated() {
        Channel channel = this.mProgramManager.getChannel(this.mChannelIndex);
        if (channel == null) {
            return;
        }
        this.mChannelId = channel.getId();
        notifyDataSetChanged();
    }

    public ProgramManager getProgramManager() {
        return this.mProgramManager;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mProgramManager.getTableEntryCount(this.mChannelId);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ProgramViewHolder programViewHolder, int i) {
        TableEntry tableEntry = this.mProgramManager.getTableEntry(this.mChannelId, i);
        programViewHolder.onBind(tableEntry, getProgramManager(), tableEntry.getIsBlocked() ? this.mBlockedProgramTitle : this.mNoInfoProgramTitle);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(ProgramViewHolder programViewHolder) {
        programViewHolder.onUnbind();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ProgramViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProgramViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    public static class ProgramViewHolder extends RecyclerView.ViewHolder {
        public ProgramViewHolder(View view) {
            super(view);
        }

        public void onBind(TableEntry tableEntry, ProgramManager programManager, String str) {
            Log.d(ProgramListAdapter.TAG, "onBind. View = " + this.itemView + ", Entry = " + tableEntry);
            ((ProgramItemMegaCell) this.itemView).setValues(tableEntry, programManager.getMFromUtcMillis(), programManager.getMToUtcMillis(), str);
            if (CommonUtils.isNetflixChannel(tableEntry.getChannel())) {
                ((ProgramItemMegaCell) this.itemView).setPrevNextZapping(programManager.getSurroundChannelsZappingNumber(tableEntry.getChannel().getId()));
            }
        }

        public void onUnbind() {
            Log.d(ProgramListAdapter.TAG, "onunBind. View = " + this.itemView);
            ((ProgramItemMegaCell) this.itemView).clearValues();
        }
    }
}
