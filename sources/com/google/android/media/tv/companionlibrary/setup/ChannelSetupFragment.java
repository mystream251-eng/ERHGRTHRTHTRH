package com.google.android.media.tv.companionlibrary.setup;

import android.animation.LayoutTransition;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.media.tv.companionlibrary.R;
import com.google.android.media.tv.companionlibrary.sync.EpgSyncJobService;
import com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class ChannelSetupFragment extends Fragment implements SyncStatusBroadcastReceiver.SyncListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "ScanFragment";
    private ChannelAdapter mAdapter;
    private ImageView mBadge;
    private Button mCancelButton;
    private View mChannelHolder;
    private View mChannelScanLayout;
    private ProgressBar mProgressBar;
    private TextView mScanningMessage;
    private BroadcastReceiver mSyncStatusChangedReceiver;
    private TextView mTitle;

    public abstract String getInputId();

    @Override // com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver.SyncListener
    public void onScanError(int i) {
    }

    @Override // com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver.SyncListener
    public abstract void onScanFinished();

    public abstract void onScanStarted();

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(getLayoutResourceId(), viewGroup, false);
        viewInflate.requestFocus();
        this.mProgressBar = (ProgressBar) viewInflate.findViewById(R.id.tune_progress);
        this.mScanningMessage = (TextView) viewInflate.findViewById(R.id.tune_description);
        this.mTitle = (TextView) viewInflate.findViewById(R.id.tune_title);
        this.mBadge = (ImageView) viewInflate.findViewById(R.id.tune_icon);
        this.mChannelHolder = viewInflate.findViewById(R.id.channel_holder);
        this.mCancelButton = (Button) viewInflate.findViewById(R.id.tune_cancel);
        ListView listView = (ListView) viewInflate.findViewById(R.id.channel_list);
        ChannelAdapter channelAdapter = new ChannelAdapter();
        this.mAdapter = channelAdapter;
        listView.setAdapter((ListAdapter) channelAdapter);
        listView.setOnItemClickListener(null);
        ViewGroup viewGroup2 = (ViewGroup) viewInflate.findViewById(R.id.progress_holder);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.enableTransitionType(4);
        viewGroup2.setLayoutTransition(layoutTransition);
        this.mCancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.media.tv.companionlibrary.setup.ChannelSetupFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChannelSetupFragment.this.finishScan();
            }
        });
        this.mSyncStatusChangedReceiver = new SyncStatusBroadcastReceiver(getInputId(), this);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mSyncStatusChangedReceiver, new IntentFilter(EpgSyncJobService.ACTION_SYNC_STATUS_CHANGED));
        this.mChannelScanLayout = viewInflate;
        setChannelListVisibility(false);
        setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        onScanStarted();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mSyncStatusChangedReceiver);
    }

    public int getLayoutResourceId() {
        return R.layout.tif_channel_setup;
    }

    public void setBackgroundColor(int i) {
        this.mChannelScanLayout.findViewById(R.id.channel_setup_layout).setBackgroundColor(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishScan() {
        this.mCancelButton.setEnabled(false);
        onScanFinished();
    }

    public void setDescription(CharSequence charSequence) {
        this.mScanningMessage.setText(charSequence);
    }

    public void setDescription(int i) {
        this.mScanningMessage.setText(i);
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle.setText(charSequence);
    }

    public void setTitle(int i) {
        this.mTitle.setText(i);
    }

    public void setBadge(Drawable drawable) {
        this.mBadge.setImageDrawable(drawable);
    }

    public void setBadge(Bitmap bitmap) {
        this.mBadge.setImageBitmap(bitmap);
    }

    public void setButtonText(CharSequence charSequence) {
        this.mCancelButton.setText(charSequence);
    }

    public void setButtonText(int i) {
        this.mCancelButton.setText(i);
    }

    public void setChannelListVisibility(boolean z) {
        this.mChannelHolder.setVisibility(z ? 0 : 8);
    }

    @Override // com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver.SyncListener
    public void onScannedChannel(CharSequence charSequence, CharSequence charSequence2) {
        this.mAdapter.add(new Pair<>(charSequence.toString(), charSequence2.toString()));
    }

    @Override // com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver.SyncListener
    public void onScanStepCompleted(int i, int i2) {
        if (i2 > 0) {
            this.mProgressBar.setIndeterminate(false);
            this.mProgressBar.setMax(i2);
            this.mProgressBar.setProgress(i);
        }
    }
}
