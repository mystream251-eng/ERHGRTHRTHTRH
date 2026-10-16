package com.google.android.media.tv.companionlibrary.setup;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.media.tv.companionlibrary.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
final class ChannelAdapter extends BaseAdapter {
    private final ArrayList<Pair<String, String>> mChannels = new ArrayList<>();

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return false;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mChannels.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mChannels.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if (view == null) {
            view = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.tif_channel_list, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.channel_name)).setText((CharSequence) this.mChannels.get(i).first);
        ((TextView) view.findViewById(R.id.channel_num)).setText((CharSequence) this.mChannels.get(i).second);
        return view;
    }

    public void add(Pair<String, String> pair) {
        this.mChannels.add(pair);
        notifyDataSetChanged();
    }
}
