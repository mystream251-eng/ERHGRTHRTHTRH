package com.google.android.media.tv.companionlibrary.setup;

import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.leanback.app.GuidedStepSupportFragment;
import androidx.leanback.widget.GuidanceStylist;
import androidx.leanback.widget.GuidedAction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.tv.common.TvCommonConstants;
import com.google.android.media.tv.companionlibrary.R;
import com.google.android.media.tv.companionlibrary.sync.EpgSyncJobService;
import com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ChannelSetupStepSupportFragment<J extends EpgSyncJobService> extends GuidedStepSupportFragment implements SyncStatusBroadcastReceiver.SyncListener {
    private static final long FULL_SYNC_FREQUENCY_MILLIS = 86400000;
    private static final long FULL_SYNC_WINDOW_SEC = 1209600000;
    private static final String TAG = "ChannelSetupStep";
    private ChannelSetupStylist mChannelSetupStylist;
    private String mInputId;
    private SyncStatusBroadcastReceiver mSyncStatusChangedReceiver;

    public abstract Class<J> getEpgSyncJobServiceClass();

    public long getFullSyncFrequencyMillis() {
        return 86400000L;
    }

    public long getFullSyncWindowSec() {
        return FULL_SYNC_WINDOW_SEC;
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mInputId = getActivity().getIntent().getStringExtra(TvCommonConstants.EXTRA_INPUT_ID);
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public ChannelSetupStylist onCreateGuidanceStylist() {
        ChannelSetupStylist channelSetupStylist = new ChannelSetupStylist();
        this.mChannelSetupStylist = channelSetupStylist;
        return channelSetupStylist;
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public GuidanceStylist.Guidance onCreateGuidance(Bundle bundle) {
        return new GuidanceStylist.Guidance(getString(R.string.tif_channel_setup_title), getString(R.string.tif_channel_setup_description), null, null);
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public void onCreateActions(List<GuidedAction> list, Bundle bundle) {
        list.add(new GuidedAction.Builder(getContext()).id(-5L).title(R.string.tif_channel_setup_cancel).build());
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public ChannelSetupStylist getGuidanceStylist() {
        return this.mChannelSetupStylist;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        ProgressBar progressBar = this.mChannelSetupStylist.getProgressBar();
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        this.mSyncStatusChangedReceiver = new SyncStatusBroadcastReceiver(this.mInputId, this);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mSyncStatusChangedReceiver, new IntentFilter(EpgSyncJobService.ACTION_SYNC_STATUS_CHANGED));
        startScan();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mSyncStatusChangedReceiver);
    }

    private void startScan() {
        EpgSyncJobService.cancelAllSyncRequests(getContext());
        EpgSyncJobService.requestImmediateSync(getContext(), this.mInputId, new ComponentName(getContext(), (Class<?>) getEpgSyncJobServiceClass()));
        SharedPreferences.Editor editorEdit = getActivity().getSharedPreferences(EpgSyncJobService.PREFERENCE_EPG_SYNC, 0).edit();
        editorEdit.putString(EpgSyncJobService.BUNDLE_KEY_INPUT_ID, this.mInputId);
        editorEdit.apply();
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public void onGuidedActionClicked(GuidedAction guidedAction) {
        if (guidedAction.getId() == -6) {
            getActivity().setResult(-1);
            finishGuidedStepSupportFragments();
        } else if (guidedAction.getId() == -5) {
            getActivity().setResult(0);
            getActivity().finishAfterTransition();
        } else {
            Log.w(TAG, "Unexpected action " + guidedAction);
        }
    }

    @Override // com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver.SyncListener
    public void onScanStepCompleted(int i, int i2) {
        ProgressBar progressBar = this.mChannelSetupStylist.getProgressBar();
        if (i2 <= 0 || progressBar == null) {
            return;
        }
        progressBar.setIndeterminate(false);
        progressBar.setMax(i2);
        progressBar.setProgress(i);
    }

    @Override // com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver.SyncListener
    public void onScannedChannel(CharSequence charSequence, CharSequence charSequence2) {
        ChannelAdapter channelAdapter = this.mChannelSetupStylist.getChannelAdapter();
        if (channelAdapter != null) {
            channelAdapter.add(Pair.create(charSequence.toString(), charSequence2.toString()));
        }
    }

    @Override // com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver.SyncListener
    public void onScanFinished() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new GuidedAction.Builder(getContext()).id(-6L).title(R.string.lb_guidedaction_finish_title).build());
        setActions(arrayList);
        setupPeriodicSync();
    }

    private void setupPeriodicSync() {
        EpgSyncJobService.cancelAllSyncRequests(getActivity());
        EpgSyncJobService.setUpPeriodicSync(getActivity(), this.mInputId, new ComponentName((Context) getActivity(), (Class<?>) getEpgSyncJobServiceClass()), getFullSyncFrequencyMillis(), getFullSyncWindowSec());
    }

    @Override // com.google.android.media.tv.companionlibrary.sync.SyncStatusBroadcastReceiver.SyncListener
    public void onScanError(int i) {
        String string;
        if (i == 1) {
            string = getString(R.string.tif_channel_scan_canceled);
        } else if (i == 3) {
            string = getString(R.string.tif_channel_error_no_programs);
        } else if (i == 4) {
            string = getString(R.string.tif_channel_error_no_channels);
        } else {
            string = getString(R.string.tif_channel_error_unknown);
        }
        Toast.makeText(getContext(), string, 1).show();
    }
}
