package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.leanback.R;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackTransportRowPresenter extends PlaybackRowPresenter {
    Presenter mDescriptionPresenter;
    OnActionClickedListener mOnActionClickedListener;
    ControlBarPresenter mPlaybackControlsPresenter;
    boolean mProgressColorSet;
    ControlBarPresenter mSecondaryControlsPresenter;
    boolean mSecondaryProgressColorSet;
    float mDefaultSeekIncrement = 0.01f;
    int mProgressColor = 0;
    int mSecondaryProgressColor = 0;
    private final ControlBarPresenter.OnControlSelectedListener mOnControlSelectedListener = new ControlBarPresenter.OnControlSelectedListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.1
        @Override // androidx.leanback.widget.ControlBarPresenter.OnControlSelectedListener
        public void onControlSelected(Presenter.ViewHolder viewHolder, Object obj, ControlBarPresenter.BoundData boundData) {
            ViewHolder viewHolder2 = ((BoundData) boundData).mRowViewHolder;
            if (viewHolder2.mSelectedViewHolder == viewHolder && viewHolder2.mSelectedItem == obj) {
                return;
            }
            viewHolder2.mSelectedViewHolder = viewHolder;
            viewHolder2.mSelectedItem = obj;
            viewHolder2.dispatchItemSelection();
        }
    };
    private final ControlBarPresenter.OnControlClickedListener mOnControlClickedListener = new ControlBarPresenter.OnControlClickedListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.2
        @Override // androidx.leanback.widget.ControlBarPresenter.OnControlClickedListener
        public void onControlClicked(Presenter.ViewHolder viewHolder, Object obj, ControlBarPresenter.BoundData boundData) {
            RowPresenter.ViewHolder viewHolder2 = ((BoundData) boundData).mRowViewHolder;
            if (viewHolder2.getOnItemViewClickedListener() != null) {
                viewHolder2.getOnItemViewClickedListener().onItemClicked(viewHolder, obj, viewHolder2, viewHolder2.getRow());
            }
            if (PlaybackTransportRowPresenter.this.mOnActionClickedListener == null || !(obj instanceof Action)) {
                return;
            }
            PlaybackTransportRowPresenter.this.mOnActionClickedListener.onActionClicked((Action) obj);
        }
    };

    static class BoundData extends PlaybackControlsPresenter.BoundData {
        ViewHolder mRowViewHolder;

        BoundData() {
        }
    }

    public class ViewHolder extends PlaybackRowPresenter.ViewHolder implements PlaybackSeekUi {
        BoundData mControlsBoundData;
        final ViewGroup mControlsDock;
        ControlBarPresenter.ViewHolder mControlsVh;
        final TextView mCurrentTime;
        long mCurrentTimeInMs;
        final ViewGroup mDescriptionDock;
        final Presenter.ViewHolder mDescriptionViewHolder;
        final ImageView mImageView;
        boolean mInSeek;
        final PlaybackControlsRow.OnPlaybackProgressCallback mListener;
        PlaybackControlsRow.PlayPauseAction mPlayPauseAction;
        long[] mPositions;
        int mPositionsLength;
        final SeekBar mProgressBar;
        BoundData mSecondaryBoundData;
        final ViewGroup mSecondaryControlsDock;
        ControlBarPresenter.ViewHolder mSecondaryControlsVh;
        long mSecondaryProgressInMs;
        PlaybackSeekUi.Client mSeekClient;
        PlaybackSeekDataProvider mSeekDataProvider;
        Object mSelectedItem;
        Presenter.ViewHolder mSelectedViewHolder;
        final StringBuilder mTempBuilder;
        int mThumbHeroIndex;
        PlaybackSeekDataProvider.ResultCallback mThumbResult;
        final ThumbsBar mThumbsBar;
        final TextView mTotalTime;
        long mTotalTimeInMs;

        void updateProgressInSeek(boolean z) {
            long j;
            long j2 = this.mCurrentTimeInMs;
            int i = this.mPositionsLength;
            long j3 = 0;
            if (i > 0) {
                int i2 = 0;
                int iBinarySearch = Arrays.binarySearch(this.mPositions, 0, i, j2);
                if (z) {
                    if (iBinarySearch >= 0) {
                        if (iBinarySearch < this.mPositionsLength - 1) {
                            i2 = iBinarySearch + 1;
                            j3 = this.mPositions[i2];
                        } else {
                            j3 = this.mTotalTimeInMs;
                            i2 = iBinarySearch;
                        }
                    } else {
                        int i3 = (-1) - iBinarySearch;
                        if (i3 <= this.mPositionsLength - 1) {
                            j = this.mPositions[i3];
                            i2 = i3;
                        } else {
                            j = this.mTotalTimeInMs;
                            if (i3 > 0) {
                                i2 = i3 - 1;
                            }
                        }
                        j3 = j;
                    }
                } else if (iBinarySearch < 0) {
                    int i4 = (-1) - iBinarySearch;
                    if (i4 > 0) {
                        i2 = i4 - 1;
                        j3 = this.mPositions[i2];
                    }
                } else if (iBinarySearch > 0) {
                    i2 = iBinarySearch - 1;
                    j3 = this.mPositions[i2];
                }
                updateThumbsInSeek(i2, z);
            } else {
                long defaultSeekIncrement = (long) (this.mTotalTimeInMs * PlaybackTransportRowPresenter.this.getDefaultSeekIncrement());
                if (!z) {
                    defaultSeekIncrement = -defaultSeekIncrement;
                }
                long j4 = j2 + defaultSeekIncrement;
                long j5 = this.mTotalTimeInMs;
                if (j4 > j5) {
                    j3 = j5;
                } else if (j4 >= 0) {
                    j3 = j4;
                }
            }
            this.mProgressBar.setProgress((int) ((j3 / this.mTotalTimeInMs) * 2.147483647E9d));
            this.mSeekClient.onSeekPositionChanged(j3);
        }

        /* JADX WARN: Code duplicated, block: B:30:0x0086 A[ADDED_TO_REGION, LOOP:0: B:30:0x0086->B:31:0x0088, LOOP_START, PHI: r7
  0x0086: PHI (r7v13 int) = (r7v12 int), (r7v14 int) binds: [B:29:0x0084, B:31:0x0088] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:31:0x0088 A[LOOP:0: B:30:0x0086->B:31:0x0088, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:32:0x0092 A[ADDED_TO_REGION, LOOP:3: B:32:0x0092->B:33:0x0094, LOOP_START, PHI: r5
  0x0092: PHI (r5v10 int) = (r5v9 int), (r5v11 int) binds: [B:29:0x0084, B:33:0x0094] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:33:0x0094 A[LOOP:3: B:32:0x0092->B:33:0x0094, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:36:0x00a6 A[LOOP:1: B:34:0x009e->B:36:0x00a6, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:39:0x00b3 A[LOOP:2: B:38:0x00b1->B:39:0x00b3, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:44:0x00ae A[EDGE_INSN: B:44:0x00ae->B:37:0x00ae BREAK  A[LOOP:1: B:34:0x009e->B:36:0x00a6], SYNTHETIC] */
        void updateThumbsInSeek(int i, boolean z) {
            int iMin;
            int i2;
            int i3;
            int i4;
            if (this.mThumbHeroIndex == i) {
                return;
            }
            int childCount = this.mThumbsBar.getChildCount();
            if (childCount < 0 || (childCount & 1) == 0) {
                throw new RuntimeException();
            }
            int i5 = childCount / 2;
            int i6 = 0;
            int iMax = Math.max(i - i5, 0);
            int iMin2 = Math.min(i + i5, this.mPositionsLength - 1);
            int i7 = this.mThumbHeroIndex;
            if (i7 >= 0) {
                z = i > i7;
                int iMax2 = Math.max(this.mThumbHeroIndex - i5, 0);
                int iMin3 = Math.min(this.mThumbHeroIndex + i5, this.mPositionsLength - 1);
                if (z) {
                    int iMax3 = Math.max(iMin3 + 1, iMax);
                    for (int i8 = iMax; i8 <= iMax3 - 1; i8++) {
                        ThumbsBar thumbsBar = this.mThumbsBar;
                        thumbsBar.setThumbBitmap((i8 - i) + i5, thumbsBar.getThumbBitmap((i8 - this.mThumbHeroIndex) + i5));
                    }
                    i2 = iMax3;
                } else {
                    iMin = Math.min(iMax2 - 1, iMin2);
                    for (int i9 = iMin2; i9 >= iMin + 1; i9--) {
                        ThumbsBar thumbsBar2 = this.mThumbsBar;
                        thumbsBar2.setThumbBitmap((i9 - i) + i5, thumbsBar2.getThumbBitmap((i9 - this.mThumbHeroIndex) + i5));
                    }
                    i2 = iMax;
                }
                this.mThumbHeroIndex = i;
                if (z) {
                    while (i2 <= iMin) {
                        this.mSeekDataProvider.getThumbnail(i2, this.mThumbResult);
                        i2++;
                    }
                } else {
                    while (iMin >= i2) {
                        this.mSeekDataProvider.getThumbnail(iMin, this.mThumbResult);
                        iMin--;
                    }
                }
                while (true) {
                    i3 = this.mThumbHeroIndex;
                    if (i6 < (i5 - i3) + iMax) {
                        break;
                    }
                    this.mThumbsBar.setThumbBitmap(i6, null);
                    i6++;
                }
                for (i4 = ((i5 + iMin2) - i3) + 1; i4 < childCount; i4++) {
                    this.mThumbsBar.setThumbBitmap(i4, null);
                }
            }
            i2 = iMax;
            iMin = iMin2;
            this.mThumbHeroIndex = i;
            if (z) {
                while (i2 <= iMin) {
                    this.mSeekDataProvider.getThumbnail(i2, this.mThumbResult);
                    i2++;
                }
            } else {
                while (iMin >= i2) {
                    this.mSeekDataProvider.getThumbnail(iMin, this.mThumbResult);
                    iMin--;
                }
            }
            while (true) {
                i3 = this.mThumbHeroIndex;
                if (i6 < (i5 - i3) + iMax) {
                    break;
                    break;
                } else {
                    this.mThumbsBar.setThumbBitmap(i6, null);
                    i6++;
                }
            }
            while (i4 < childCount) {
                this.mThumbsBar.setThumbBitmap(i4, null);
            }
        }

        boolean onForward() {
            if (!startSeek()) {
                return false;
            }
            updateProgressInSeek(true);
            return true;
        }

        boolean onBackward() {
            if (!startSeek()) {
                return false;
            }
            updateProgressInSeek(false);
            return true;
        }

        public ViewHolder(View view, Presenter presenter) {
            super(view);
            this.mTotalTimeInMs = Long.MIN_VALUE;
            this.mCurrentTimeInMs = Long.MIN_VALUE;
            this.mTempBuilder = new StringBuilder();
            this.mControlsBoundData = new BoundData();
            this.mSecondaryBoundData = new BoundData();
            this.mThumbHeroIndex = -1;
            this.mListener = new PlaybackControlsRow.OnPlaybackProgressCallback() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.1
                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onCurrentPositionChanged(PlaybackControlsRow playbackControlsRow, long j) {
                    ViewHolder.this.setCurrentPosition(j);
                }

                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onDurationChanged(PlaybackControlsRow playbackControlsRow, long j) {
                    ViewHolder.this.setTotalTime(j);
                }

                @Override // androidx.leanback.widget.PlaybackControlsRow.OnPlaybackProgressCallback
                public void onBufferedPositionChanged(PlaybackControlsRow playbackControlsRow, long j) {
                    ViewHolder.this.setBufferedPosition(j);
                }
            };
            this.mThumbResult = new PlaybackSeekDataProvider.ResultCallback() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.2
                @Override // androidx.leanback.widget.PlaybackSeekDataProvider.ResultCallback
                public void onThumbnailLoaded(Bitmap bitmap, int i) {
                    int childCount = i - (ViewHolder.this.mThumbHeroIndex - (ViewHolder.this.mThumbsBar.getChildCount() / 2));
                    if (childCount < 0 || childCount >= ViewHolder.this.mThumbsBar.getChildCount()) {
                        return;
                    }
                    ViewHolder.this.mThumbsBar.setThumbBitmap(childCount, bitmap);
                }
            };
            this.mImageView = (ImageView) view.findViewById(R.id.image);
            this.mDescriptionDock = (ViewGroup) view.findViewById(R.id.description_dock);
            this.mCurrentTime = (TextView) view.findViewById(R.id.current_time);
            this.mTotalTime = (TextView) view.findViewById(R.id.total_time);
            SeekBar seekBar = (SeekBar) view.findViewById(R.id.playback_progress);
            this.mProgressBar = seekBar;
            seekBar.setOnClickListener(new View.OnClickListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    PlaybackTransportRowPresenter.this.onProgressBarClicked(ViewHolder.this);
                }
            });
            this.mProgressBar.setOnKeyListener(new View.OnKeyListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.4
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view2, int i, KeyEvent keyEvent) {
                    if (i != 4) {
                        if (i != 66) {
                            if (i != 69) {
                                if (i != 81) {
                                    if (i != 111) {
                                        if (i != 89) {
                                            if (i != 90) {
                                                switch (i) {
                                                    case 19:
                                                    case 20:
                                                        return ViewHolder.this.mInSeek;
                                                    case 21:
                                                        break;
                                                    case 22:
                                                        break;
                                                    case 23:
                                                        break;
                                                    default:
                                                        return false;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (keyEvent.getAction() == 0) {
                                    ViewHolder.this.onForward();
                                }
                                return true;
                            }
                            if (keyEvent.getAction() == 0) {
                                ViewHolder.this.onBackward();
                            }
                            return true;
                        }
                        if (!ViewHolder.this.mInSeek) {
                            return false;
                        }
                        if (keyEvent.getAction() == 1) {
                            ViewHolder.this.stopSeek(false);
                        }
                        return true;
                    }
                    if (!ViewHolder.this.mInSeek) {
                        return false;
                    }
                    if (keyEvent.getAction() == 1) {
                        ViewHolder.this.stopSeek(Build.VERSION.SDK_INT < 21 || !ViewHolder.this.mProgressBar.isAccessibilityFocused());
                    }
                    return true;
                }
            });
            this.mProgressBar.setAccessibilitySeekListener(new SeekBar.AccessibilitySeekListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.ViewHolder.5
                @Override // androidx.leanback.widget.SeekBar.AccessibilitySeekListener
                public boolean onAccessibilitySeekForward() {
                    return ViewHolder.this.onForward();
                }

                @Override // androidx.leanback.widget.SeekBar.AccessibilitySeekListener
                public boolean onAccessibilitySeekBackward() {
                    return ViewHolder.this.onBackward();
                }
            });
            this.mProgressBar.setMax(Integer.MAX_VALUE);
            this.mControlsDock = (ViewGroup) view.findViewById(R.id.controls_dock);
            this.mSecondaryControlsDock = (ViewGroup) view.findViewById(R.id.secondary_controls_dock);
            Presenter.ViewHolder viewHolderOnCreateViewHolder = presenter == null ? null : presenter.onCreateViewHolder(this.mDescriptionDock);
            this.mDescriptionViewHolder = viewHolderOnCreateViewHolder;
            if (viewHolderOnCreateViewHolder != null) {
                this.mDescriptionDock.addView(viewHolderOnCreateViewHolder.view);
            }
            this.mThumbsBar = (ThumbsBar) view.findViewById(R.id.thumbs_row);
        }

        public final Presenter.ViewHolder getDescriptionViewHolder() {
            return this.mDescriptionViewHolder;
        }

        @Override // androidx.leanback.widget.PlaybackSeekUi
        public void setPlaybackSeekUiClient(PlaybackSeekUi.Client client) {
            this.mSeekClient = client;
        }

        boolean startSeek() {
            if (this.mInSeek) {
                return true;
            }
            PlaybackSeekUi.Client client = this.mSeekClient;
            if (client == null || !client.isSeekEnabled() || this.mTotalTimeInMs <= 0) {
                return false;
            }
            this.mInSeek = true;
            this.mSeekClient.onSeekStarted();
            PlaybackSeekDataProvider playbackSeekDataProvider = this.mSeekClient.getPlaybackSeekDataProvider();
            this.mSeekDataProvider = playbackSeekDataProvider;
            long[] seekPositions = playbackSeekDataProvider != null ? playbackSeekDataProvider.getSeekPositions() : null;
            this.mPositions = seekPositions;
            if (seekPositions != null) {
                int iBinarySearch = Arrays.binarySearch(seekPositions, this.mTotalTimeInMs);
                if (iBinarySearch >= 0) {
                    this.mPositionsLength = iBinarySearch + 1;
                } else {
                    this.mPositionsLength = (-1) - iBinarySearch;
                }
            } else {
                this.mPositionsLength = 0;
            }
            this.mControlsVh.view.setVisibility(8);
            this.mSecondaryControlsVh.view.setVisibility(4);
            this.mDescriptionViewHolder.view.setVisibility(4);
            this.mThumbsBar.setVisibility(0);
            return true;
        }

        void stopSeek(boolean z) {
            if (this.mInSeek) {
                this.mInSeek = false;
                this.mSeekClient.onSeekFinished(z);
                PlaybackSeekDataProvider playbackSeekDataProvider = this.mSeekDataProvider;
                if (playbackSeekDataProvider != null) {
                    playbackSeekDataProvider.reset();
                }
                this.mThumbHeroIndex = -1;
                this.mThumbsBar.clearThumbBitmaps();
                this.mSeekDataProvider = null;
                this.mPositions = null;
                this.mPositionsLength = 0;
                this.mControlsVh.view.setVisibility(0);
                this.mSecondaryControlsVh.view.setVisibility(0);
                this.mDescriptionViewHolder.view.setVisibility(0);
                this.mThumbsBar.setVisibility(4);
            }
        }

        void dispatchItemSelection() {
            if (isSelected()) {
                if (this.mSelectedViewHolder == null) {
                    if (getOnItemViewSelectedListener() != null) {
                        getOnItemViewSelectedListener().onItemSelected(null, null, this, getRow());
                    }
                } else if (getOnItemViewSelectedListener() != null) {
                    getOnItemViewSelectedListener().onItemSelected(this.mSelectedViewHolder, this.mSelectedItem, this, getRow());
                }
            }
        }

        Presenter getPresenter(boolean z) {
            ObjectAdapter secondaryActionsAdapter;
            if (z) {
                secondaryActionsAdapter = ((PlaybackControlsRow) getRow()).getPrimaryActionsAdapter();
            } else {
                secondaryActionsAdapter = ((PlaybackControlsRow) getRow()).getSecondaryActionsAdapter();
            }
            if (secondaryActionsAdapter == null) {
                return null;
            }
            if (secondaryActionsAdapter.getPresenterSelector() instanceof ControlButtonPresenterSelector) {
                return ((ControlButtonPresenterSelector) secondaryActionsAdapter.getPresenterSelector()).getSecondaryPresenter();
            }
            return secondaryActionsAdapter.getPresenter(secondaryActionsAdapter.size() > 0 ? secondaryActionsAdapter.get(0) : null);
        }

        public final TextView getDurationView() {
            return this.mTotalTime;
        }

        protected void onSetDurationLabel(long j) {
            if (this.mTotalTime != null) {
                PlaybackTransportRowPresenter.formatTime(j, this.mTempBuilder);
                this.mTotalTime.setText(this.mTempBuilder.toString());
            }
        }

        void setTotalTime(long j) {
            if (this.mTotalTimeInMs != j) {
                this.mTotalTimeInMs = j;
                onSetDurationLabel(j);
            }
        }

        public final TextView getCurrentPositionView() {
            return this.mCurrentTime;
        }

        protected void onSetCurrentPositionLabel(long j) {
            if (this.mCurrentTime != null) {
                PlaybackTransportRowPresenter.formatTime(j, this.mTempBuilder);
                this.mCurrentTime.setText(this.mTempBuilder.toString());
            }
        }

        void setCurrentPosition(long j) {
            if (j != this.mCurrentTimeInMs) {
                this.mCurrentTimeInMs = j;
                onSetCurrentPositionLabel(j);
            }
            if (this.mInSeek) {
                return;
            }
            long j2 = this.mTotalTimeInMs;
            this.mProgressBar.setProgress(j2 > 0 ? (int) ((this.mCurrentTimeInMs / j2) * 2.147483647E9d) : 0);
        }

        void setBufferedPosition(long j) {
            this.mSecondaryProgressInMs = j;
            this.mProgressBar.setSecondaryProgress((int) ((j / this.mTotalTimeInMs) * 2.147483647E9d));
        }
    }

    static void formatTime(long j, StringBuilder sb) {
        sb.setLength(0);
        if (j < 0) {
            sb.append("--");
            return;
        }
        long j2 = j / 1000;
        long j3 = j2 / 60;
        long j4 = j3 / 60;
        long j5 = j2 - (j3 * 60);
        long j6 = j3 - (60 * j4);
        if (j4 > 0) {
            sb.append(j4);
            sb.append(':');
            if (j6 < 10) {
                sb.append('0');
            }
        }
        sb.append(j6);
        sb.append(':');
        if (j5 < 10) {
            sb.append('0');
        }
        sb.append(j5);
    }

    public PlaybackTransportRowPresenter() {
        setHeaderPresenter(null);
        setSelectEffectEnabled(false);
        ControlBarPresenter controlBarPresenter = new ControlBarPresenter(R.layout.lb_control_bar);
        this.mPlaybackControlsPresenter = controlBarPresenter;
        controlBarPresenter.setDefaultFocusToMiddle(false);
        ControlBarPresenter controlBarPresenter2 = new ControlBarPresenter(R.layout.lb_control_bar);
        this.mSecondaryControlsPresenter = controlBarPresenter2;
        controlBarPresenter2.setDefaultFocusToMiddle(false);
        this.mPlaybackControlsPresenter.setOnControlSelectedListener(this.mOnControlSelectedListener);
        this.mSecondaryControlsPresenter.setOnControlSelectedListener(this.mOnControlSelectedListener);
        this.mPlaybackControlsPresenter.setOnControlClickedListener(this.mOnControlClickedListener);
        this.mSecondaryControlsPresenter.setOnControlClickedListener(this.mOnControlClickedListener);
    }

    public void setDescriptionPresenter(Presenter presenter) {
        this.mDescriptionPresenter = presenter;
    }

    public void setOnActionClickedListener(OnActionClickedListener onActionClickedListener) {
        this.mOnActionClickedListener = onActionClickedListener;
    }

    public OnActionClickedListener getOnActionClickedListener() {
        return this.mOnActionClickedListener;
    }

    public void setProgressColor(int i) {
        this.mProgressColor = i;
        this.mProgressColorSet = true;
    }

    public int getProgressColor() {
        return this.mProgressColor;
    }

    public void setSecondaryProgressColor(int i) {
        this.mSecondaryProgressColor = i;
        this.mSecondaryProgressColorSet = true;
    }

    public int getSecondaryProgressColor() {
        return this.mSecondaryProgressColor;
    }

    @Override // androidx.leanback.widget.PlaybackRowPresenter
    public void onReappear(RowPresenter.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        if (viewHolder2.view.hasFocus()) {
            viewHolder2.mProgressBar.requestFocus();
        }
    }

    private static int getDefaultProgressColor(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.playbackProgressPrimaryColor, typedValue, true)) {
            return context.getResources().getColor(typedValue.resourceId);
        }
        return context.getResources().getColor(R.color.lb_playback_progress_color_no_theme);
    }

    private static int getDefaultSecondaryProgressColor(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.playbackProgressSecondaryColor, typedValue, true)) {
            return context.getResources().getColor(typedValue.resourceId);
        }
        return context.getResources().getColor(R.color.lb_playback_progress_secondary_color_no_theme);
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lb_playback_transport_controls_row, viewGroup, false), this.mDescriptionPresenter);
        initRow(viewHolder);
        return viewHolder;
    }

    private void initRow(final ViewHolder viewHolder) {
        viewHolder.mControlsVh = (ControlBarPresenter.ViewHolder) this.mPlaybackControlsPresenter.onCreateViewHolder(viewHolder.mControlsDock);
        viewHolder.mProgressBar.setProgressColor(this.mProgressColorSet ? this.mProgressColor : getDefaultProgressColor(viewHolder.mControlsDock.getContext()));
        viewHolder.mProgressBar.setSecondaryProgressColor(this.mSecondaryProgressColorSet ? this.mSecondaryProgressColor : getDefaultSecondaryProgressColor(viewHolder.mControlsDock.getContext()));
        viewHolder.mControlsDock.addView(viewHolder.mControlsVh.view);
        viewHolder.mSecondaryControlsVh = (ControlBarPresenter.ViewHolder) this.mSecondaryControlsPresenter.onCreateViewHolder(viewHolder.mSecondaryControlsDock);
        viewHolder.mSecondaryControlsDock.addView(viewHolder.mSecondaryControlsVh.view);
        ((PlaybackTransportRowView) viewHolder.view.findViewById(R.id.transport_row)).setOnUnhandledKeyListener(new PlaybackTransportRowView.OnUnhandledKeyListener() { // from class: androidx.leanback.widget.PlaybackTransportRowPresenter.3
            @Override // androidx.leanback.widget.PlaybackTransportRowView.OnUnhandledKeyListener
            public boolean onUnhandledKey(KeyEvent keyEvent) {
                return viewHolder.getOnKeyListener() != null && viewHolder.getOnKeyListener().onKey(viewHolder.view, keyEvent.getKeyCode(), keyEvent);
            }
        });
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onBindRowViewHolder(RowPresenter.ViewHolder viewHolder, Object obj) {
        super.onBindRowViewHolder(viewHolder, obj);
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        PlaybackControlsRow playbackControlsRow = (PlaybackControlsRow) viewHolder2.getRow();
        if (playbackControlsRow.getItem() == null) {
            viewHolder2.mDescriptionDock.setVisibility(8);
        } else {
            viewHolder2.mDescriptionDock.setVisibility(0);
            if (viewHolder2.mDescriptionViewHolder != null) {
                this.mDescriptionPresenter.onBindViewHolder(viewHolder2.mDescriptionViewHolder, playbackControlsRow.getItem());
            }
        }
        if (playbackControlsRow.getImageDrawable() == null) {
            viewHolder2.mImageView.setVisibility(8);
        } else {
            viewHolder2.mImageView.setVisibility(0);
        }
        viewHolder2.mImageView.setImageDrawable(playbackControlsRow.getImageDrawable());
        viewHolder2.mControlsBoundData.adapter = playbackControlsRow.getPrimaryActionsAdapter();
        viewHolder2.mControlsBoundData.presenter = viewHolder2.getPresenter(true);
        viewHolder2.mControlsBoundData.mRowViewHolder = viewHolder2;
        this.mPlaybackControlsPresenter.onBindViewHolder(viewHolder2.mControlsVh, viewHolder2.mControlsBoundData);
        viewHolder2.mSecondaryBoundData.adapter = playbackControlsRow.getSecondaryActionsAdapter();
        viewHolder2.mSecondaryBoundData.presenter = viewHolder2.getPresenter(false);
        viewHolder2.mSecondaryBoundData.mRowViewHolder = viewHolder2;
        this.mSecondaryControlsPresenter.onBindViewHolder(viewHolder2.mSecondaryControlsVh, viewHolder2.mSecondaryBoundData);
        viewHolder2.setTotalTime(playbackControlsRow.getDuration());
        viewHolder2.setCurrentPosition(playbackControlsRow.getCurrentPosition());
        viewHolder2.setBufferedPosition(playbackControlsRow.getBufferedPosition());
        playbackControlsRow.setOnPlaybackProgressChangedListener(viewHolder2.mListener);
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onUnbindRowViewHolder(RowPresenter.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        PlaybackControlsRow playbackControlsRow = (PlaybackControlsRow) viewHolder2.getRow();
        if (viewHolder2.mDescriptionViewHolder != null) {
            this.mDescriptionPresenter.onUnbindViewHolder(viewHolder2.mDescriptionViewHolder);
        }
        this.mPlaybackControlsPresenter.onUnbindViewHolder(viewHolder2.mControlsVh);
        this.mSecondaryControlsPresenter.onUnbindViewHolder(viewHolder2.mSecondaryControlsVh);
        playbackControlsRow.setOnPlaybackProgressChangedListener(null);
        super.onUnbindRowViewHolder(viewHolder);
    }

    protected void onProgressBarClicked(ViewHolder viewHolder) {
        if (viewHolder != null) {
            if (viewHolder.mPlayPauseAction == null) {
                viewHolder.mPlayPauseAction = new PlaybackControlsRow.PlayPauseAction(viewHolder.view.getContext());
            }
            if (viewHolder.getOnItemViewClickedListener() != null) {
                viewHolder.getOnItemViewClickedListener().onItemClicked(viewHolder, viewHolder.mPlayPauseAction, viewHolder, viewHolder.getRow());
            }
            OnActionClickedListener onActionClickedListener = this.mOnActionClickedListener;
            if (onActionClickedListener != null) {
                onActionClickedListener.onActionClicked(viewHolder.mPlayPauseAction);
            }
        }
    }

    public void setDefaultSeekIncrement(float f) {
        this.mDefaultSeekIncrement = f;
    }

    public float getDefaultSeekIncrement() {
        return this.mDefaultSeekIncrement;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewSelected(RowPresenter.ViewHolder viewHolder, boolean z) {
        super.onRowViewSelected(viewHolder, z);
        if (z) {
            ((ViewHolder) viewHolder).dispatchItemSelection();
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewAttachedToWindow(RowPresenter.ViewHolder viewHolder) {
        super.onRowViewAttachedToWindow(viewHolder);
        Presenter presenter = this.mDescriptionPresenter;
        if (presenter != null) {
            presenter.onViewAttachedToWindow(((ViewHolder) viewHolder).mDescriptionViewHolder);
        }
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onRowViewDetachedFromWindow(RowPresenter.ViewHolder viewHolder) {
        super.onRowViewDetachedFromWindow(viewHolder);
        Presenter presenter = this.mDescriptionPresenter;
        if (presenter != null) {
            presenter.onViewDetachedFromWindow(((ViewHolder) viewHolder).mDescriptionViewHolder);
        }
    }
}
