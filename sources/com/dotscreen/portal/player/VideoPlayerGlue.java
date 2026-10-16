package com.dotscreen.portal.player;

import android.content.Context;
import androidx.leanback.media.PlaybackDiplomaticoControlGlue;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.PlaybackControlsRow;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlayerGlue.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003234B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0006\u0010!\u001a\u00020\u001eJ\u0010\u0010\"\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$J\t\u0010%\u001a\u00020\u001eH\u0096\u0002J\u001a\u0010&\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\u0010\u0010*\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010+\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)H\u0014J\u0012\u0010,\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010-\u001a\u00020\u001eH\u0014J\b\u0010.\u001a\u00020\u001eH\u0016J\u0006\u0010/\u001a\u00020\u001eJ\u0010\u00100\u001a\u0002012\u0006\u0010\u001f\u001a\u00020 H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/dotscreen/portal/player/VideoPlayerGlue;", "Landroidx/leanback/media/PlaybackDiplomaticoControlGlue;", "Lcom/dotscreen/portal/player/VOPlayerAdapter;", "context", "Landroid/content/Context;", "playerAdapter", "mActionListener", "Lcom/dotscreen/portal/player/VideoPlayerGlue$OnActionClickedListener;", "mPlaybackInfoListener", "Lcom/dotscreen/portal/player/VideoPlayerGlue$PlaybackInfoListener;", "(Landroid/content/Context;Lcom/dotscreen/portal/player/VOPlayerAdapter;Lcom/dotscreen/portal/player/VideoPlayerGlue$OnActionClickedListener;Lcom/dotscreen/portal/player/VideoPlayerGlue$PlaybackInfoListener;)V", "TEN_SECONDS", "", "lastPosition", "Ljava/lang/Long;", "mFastForwardAction", "Landroidx/leanback/widget/PlaybackControlsRow$FastForwardAction;", "mRepeatAction", "Landroidx/leanback/widget/PlaybackControlsRow$RepeatAction;", "mRewindAction", "Landroidx/leanback/widget/PlaybackControlsRow$RewindAction;", "mSkipNextAction", "Landroidx/leanback/widget/PlaybackControlsRow$SkipNextAction;", "mSkipPreviousAction", "Landroidx/leanback/widget/PlaybackControlsRow$SkipPreviousAction;", "mThumbsDownAction", "Landroidx/leanback/widget/PlaybackControlsRow$ThumbsDownAction;", "mThumbsUpAction", "Landroidx/leanback/widget/PlaybackControlsRow$ThumbsUpAction;", "dispatchAction", "", "action", "Landroidx/leanback/widget/Action;", "fastForward", "fastForwardEnded", "value", "Lcom/dotscreen/portal/player/VideoPlayerGlue$OnFastForwardEndedListener;", "next", "notifyActionChanged", "Landroidx/leanback/widget/PlaybackControlsRow$MultiAction;", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "onActionClicked", "onCreatePrimaryActions", "onCreateSecondaryActions", "onUpdateProgress", "previous", "rewind", "shouldDispatchAction", "", "OnActionClickedListener", "OnFastForwardEndedListener", "PlaybackInfoListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VideoPlayerGlue extends PlaybackDiplomaticoControlGlue<VOPlayerAdapter> {
    private final long TEN_SECONDS;
    private Long lastPosition;
    private final OnActionClickedListener mActionListener;
    private final PlaybackControlsRow.FastForwardAction mFastForwardAction;
    private final PlaybackInfoListener mPlaybackInfoListener;
    private final PlaybackControlsRow.RepeatAction mRepeatAction;
    private final PlaybackControlsRow.RewindAction mRewindAction;
    private final PlaybackControlsRow.SkipNextAction mSkipNextAction;
    private final PlaybackControlsRow.SkipPreviousAction mSkipPreviousAction;
    private final PlaybackControlsRow.ThumbsDownAction mThumbsDownAction;
    private final PlaybackControlsRow.ThumbsUpAction mThumbsUpAction;

    /* JADX INFO: compiled from: VideoPlayerGlue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/player/VideoPlayerGlue$OnActionClickedListener;", "", "onNext", "", "onPrevious", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnActionClickedListener {
        void onNext();

        void onPrevious();
    }

    /* JADX INFO: compiled from: VideoPlayerGlue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/portal/player/VideoPlayerGlue$OnFastForwardEndedListener;", "", "onFastForwardEnded", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnFastForwardEndedListener {
        void onFastForwardEnded();
    }

    /* JADX INFO: compiled from: VideoPlayerGlue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/player/VideoPlayerGlue$PlaybackInfoListener;", "", "onCurrentPositionChanged", "", "currentPosition", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface PlaybackInfoListener {
        void onCurrentPositionChanged(long currentPosition);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoPlayerGlue(Context context, VOPlayerAdapter playerAdapter, OnActionClickedListener mActionListener, PlaybackInfoListener mPlaybackInfoListener) {
        super(context, playerAdapter);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(playerAdapter, "playerAdapter");
        Intrinsics.checkParameterIsNotNull(mActionListener, "mActionListener");
        Intrinsics.checkParameterIsNotNull(mPlaybackInfoListener, "mPlaybackInfoListener");
        this.mActionListener = mActionListener;
        this.mPlaybackInfoListener = mPlaybackInfoListener;
        this.TEN_SECONDS = TimeUnit.SECONDS.toMillis(10L);
        this.mRepeatAction = new PlaybackControlsRow.RepeatAction(context);
        this.mThumbsUpAction = new PlaybackControlsRow.ThumbsUpAction(context);
        this.mThumbsDownAction = new PlaybackControlsRow.ThumbsDownAction(context);
        this.mSkipPreviousAction = new PlaybackControlsRow.SkipPreviousAction(context);
        this.mSkipNextAction = new PlaybackControlsRow.SkipNextAction(context);
        this.mFastForwardAction = new PlaybackControlsRow.FastForwardAction(context);
        this.mRewindAction = new PlaybackControlsRow.RewindAction(context);
        this.mThumbsUpAction.setIndex(1);
        this.mThumbsDownAction.setIndex(1);
    }

    @Override // androidx.leanback.media.PlaybackDiplomaticoControlGlue, androidx.leanback.media.PlaybackBaseControlGlue
    protected void onCreatePrimaryActions(ArrayObjectAdapter adapter) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        super.onCreatePrimaryActions(adapter);
        adapter.add(this.mRewindAction);
        adapter.add(this.mFastForwardAction);
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue
    protected void onCreateSecondaryActions(ArrayObjectAdapter adapter) {
        super.onCreateSecondaryActions(adapter);
    }

    @Override // androidx.leanback.media.PlaybackDiplomaticoControlGlue, androidx.leanback.media.PlaybackBaseControlGlue
    protected void onUpdateProgress() {
        super.onUpdateProgress();
        Long l = this.lastPosition;
        if (l != null) {
            long currentPosition = getCurrentPosition();
            if (l != null && l.longValue() == currentPosition) {
                return;
            }
        }
        this.lastPosition = Long.valueOf(getCurrentPosition());
        this.mPlaybackInfoListener.onCurrentPositionChanged(getCurrentPosition());
    }

    @Override // androidx.leanback.media.PlaybackDiplomaticoControlGlue, androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.widget.OnActionClickedListener
    public void onActionClicked(Action action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        if (shouldDispatchAction(action)) {
            dispatchAction(action);
        } else {
            super.onActionClicked(action);
        }
    }

    private final boolean shouldDispatchAction(Action action) {
        return action == this.mRewindAction || action == this.mFastForwardAction || action == this.mThumbsDownAction || action == this.mThumbsUpAction || action == this.mRepeatAction;
    }

    private final void dispatchAction(Action action) {
        if (action == this.mRewindAction) {
            rewind();
            return;
        }
        if (action == this.mFastForwardAction) {
            fastForward();
            return;
        }
        if (action instanceof PlaybackControlsRow.MultiAction) {
            PlaybackControlsRow.MultiAction multiAction = (PlaybackControlsRow.MultiAction) action;
            multiAction.nextIndex();
            PlaybackControlsRow controlsRow = getControlsRow();
            Intrinsics.checkExpressionValueIsNotNull(controlsRow, "controlsRow");
            ObjectAdapter secondaryActionsAdapter = controlsRow.getSecondaryActionsAdapter();
            if (secondaryActionsAdapter != null) {
                notifyActionChanged(multiAction, (ArrayObjectAdapter) secondaryActionsAdapter);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.ArrayObjectAdapter");
        }
    }

    private final void notifyActionChanged(PlaybackControlsRow.MultiAction action, ArrayObjectAdapter adapter) {
        int iIndexOf;
        if (adapter == null || (iIndexOf = adapter.indexOf(action)) < 0) {
            return;
        }
        adapter.notifyArrayItemRangeChanged(iIndexOf, 1);
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    public void next() {
        this.mActionListener.onNext();
    }

    @Override // androidx.leanback.media.PlaybackBaseControlGlue, androidx.leanback.media.PlaybackGlue
    public void previous() {
        this.mActionListener.onPrevious();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void rewind() {
        long currentPosition = getCurrentPosition() - this.TEN_SECONDS;
        if (currentPosition < 0) {
            currentPosition = 0;
        }
        ((VOPlayerAdapter) getPlayerAdapter()).seekTo(currentPosition);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void fastForwardEnded(OnFastForwardEndedListener value) {
        VOPlayerAdapter vOPlayerAdapter = (VOPlayerAdapter) getPlayerAdapter();
        if (vOPlayerAdapter != null) {
            vOPlayerAdapter.setFastForwardEnded(value);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void fastForward() {
        if (getDuration() > -1) {
            long currentPosition = getCurrentPosition() + this.TEN_SECONDS;
            if (currentPosition > getDuration()) {
                currentPosition = getDuration();
            }
            ((VOPlayerAdapter) getPlayerAdapter()).seekTo(currentPosition);
        }
    }
}
