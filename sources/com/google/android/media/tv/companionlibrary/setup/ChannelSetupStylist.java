package com.google.android.media.tv.companionlibrary.setup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.leanback.widget.GuidanceStylist;
import com.google.android.media.tv.companionlibrary.R;

/* JADX INFO: loaded from: classes2.dex */
public class ChannelSetupStylist extends GuidanceStylist {
    private ChannelAdapter mAdapter;
    private ProgressBar mProgressBar;

    @Override // androidx.leanback.widget.GuidanceStylist
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, GuidanceStylist.Guidance guidance) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, guidance);
        this.mProgressBar = (ProgressBar) viewOnCreateView.findViewById(R.id.tif_tune_progress);
        ListView listView = (ListView) viewOnCreateView.findViewById(R.id.tif_channel_list);
        ChannelAdapter channelAdapter = new ChannelAdapter();
        this.mAdapter = channelAdapter;
        listView.setAdapter((ListAdapter) channelAdapter);
        listView.setOnItemClickListener(null);
        return viewOnCreateView;
    }

    @Override // androidx.leanback.widget.GuidanceStylist
    public int onProvideLayoutId() {
        return R.layout.tif_channel_setup_guidance;
    }

    public ProgressBar getProgressBar() {
        return this.mProgressBar;
    }

    public ChannelAdapter getChannelAdapter() {
        return this.mAdapter;
    }
}
