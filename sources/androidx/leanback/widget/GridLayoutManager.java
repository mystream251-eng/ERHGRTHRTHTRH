package androidx.leanback.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.FocusFinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.collection.CircularIntArray;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class GridLayoutManager extends RecyclerView.LayoutManager {
    static final boolean DEBUG = false;
    static final int DEFAULT_MAX_PENDING_MOVES = 10;
    static final int MIN_MS_SMOOTH_SCROLL_MAIN_SCREEN = 30;
    private static final int NEXT_ITEM = 1;
    private static final int NEXT_ROW = 3;
    static final int PF_FAST_RELAYOUT = 4;
    static final int PF_FAST_RELAYOUT_UPDATED_SELECTED_POSITION = 8;
    static final int PF_FOCUS_OUT_END = 4096;
    static final int PF_FOCUS_OUT_FRONT = 2048;
    static final int PF_FOCUS_OUT_MASKS = 6144;
    static final int PF_FOCUS_OUT_SIDE_END = 16384;
    static final int PF_FOCUS_OUT_SIDE_MASKS = 24576;
    static final int PF_FOCUS_OUT_SIDE_START = 8192;
    static final int PF_FOCUS_SEARCH_DISABLED = 32768;
    static final int PF_FORCE_FULL_LAYOUT = 256;
    static final int PF_IN_LAYOUT_SEARCH_FOCUS = 16;
    static final int PF_IN_SELECTION = 32;
    static final int PF_LAYOUT_EATEN_IN_SLIDING = 128;
    static final int PF_LAYOUT_ENABLED = 512;
    static final int PF_PRUNE_CHILD = 65536;
    static final int PF_REVERSE_FLOW_MASK = 786432;
    static final int PF_REVERSE_FLOW_PRIMARY = 262144;
    static final int PF_REVERSE_FLOW_SECONDARY = 524288;
    static final int PF_ROW_SECONDARY_SIZE_REFRESH = 1024;
    static final int PF_SCROLL_ENABLED = 131072;
    static final int PF_SLIDING = 64;
    static final int PF_STAGE_LAYOUT = 1;
    static final int PF_STAGE_MASK = 3;
    static final int PF_STAGE_SCROLL = 2;
    private static final int PREV_ITEM = 0;
    private static final int PREV_ROW = 2;
    private static final String TAG = "GridLayoutManager";
    static final boolean TRACE = false;
    private static final Rect sTempRect = new Rect();
    static int[] sTwoInts = new int[2];
    final BaseGridView mBaseGridView;
    GridLinearSmoothScroller mCurrentSmoothScroller;
    int[] mDisappearingPositions;
    private int mExtraLayoutSpace;
    int mExtraLayoutSpaceInPreLayout;
    private FacetProviderAdapter mFacetProviderAdapter;
    private int mFixedRowSizeSecondary;
    Grid mGrid;
    private int mHorizontalSpacing;
    OnLayoutCompleteListener mLayoutCompleteListener;
    private int mMaxSizeSecondary;
    int mNumRows;
    PendingMoveSmoothScroller mPendingMoveSmoothScroller;
    int mPositionDeltaInPreLayout;
    private int mPrimaryScrollExtra;
    RecyclerView.Recycler mRecycler;
    private int[] mRowSizeSecondary;
    private int mRowSizeSecondaryRequested;
    int mScrollOffsetSecondary;
    private int mSizePrimary;
    private int mSpacingPrimary;
    private int mSpacingSecondary;
    RecyclerView.State mState;
    private int mVerticalSpacing;
    int mMaxPendingMoves = 10;
    int mOrientation = 0;
    private OrientationHelper mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
    final SparseIntArray mPositionToRowInPostLayout = new SparseIntArray();
    int mFlag = 221696;
    private OnChildSelectedListener mChildSelectedListener = null;
    private ArrayList<OnChildViewHolderSelectedListener> mChildViewHolderSelectedListeners = null;
    OnChildLaidOutListener mChildLaidOutListener = null;
    int mFocusPosition = -1;
    int mSubFocusPosition = 0;
    private int mFocusPositionOffset = 0;
    private int mGravity = 8388659;
    private int mNumRowsRequested = 1;
    private int mFocusScrollStrategy = 0;
    final WindowAlignment mWindowAlignment = new WindowAlignment();
    private final ItemAlignment mItemAlignment = new ItemAlignment();
    private int[] mMeasuredDimension = new int[2];
    final ViewsStateBundle mChildrenStates = new ViewsStateBundle();
    private final Runnable mRequestLayoutRunnable = new Runnable() { // from class: androidx.leanback.widget.GridLayoutManager.1
        @Override // java.lang.Runnable
        public void run() {
            GridLayoutManager.this.requestLayout();
        }
    };
    private Grid.Provider mGridProvider = new Grid.Provider() { // from class: androidx.leanback.widget.GridLayoutManager.2
        @Override // androidx.leanback.widget.Grid.Provider
        public int getMinIndex() {
            return GridLayoutManager.this.mPositionDeltaInPreLayout;
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public int getCount() {
            return GridLayoutManager.this.mState.getItemCount() + GridLayoutManager.this.mPositionDeltaInPreLayout;
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public int createItem(int i, boolean z, Object[] objArr, boolean z2) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            View viewForPosition = gridLayoutManager.getViewForPosition(i - gridLayoutManager.mPositionDeltaInPreLayout);
            LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
            layoutParams.setItemAlignmentFacet((ItemAlignmentFacet) GridLayoutManager.this.getFacet(GridLayoutManager.this.mBaseGridView.getChildViewHolder(viewForPosition), ItemAlignmentFacet.class));
            if (!layoutParams.isItemRemoved()) {
                if (z2) {
                    if (z) {
                        GridLayoutManager.this.addDisappearingView(viewForPosition);
                    } else {
                        GridLayoutManager.this.addDisappearingView(viewForPosition, 0);
                    }
                } else if (z) {
                    GridLayoutManager.this.addView(viewForPosition);
                } else {
                    GridLayoutManager.this.addView(viewForPosition, 0);
                }
                if (GridLayoutManager.this.mChildVisibility != -1) {
                    viewForPosition.setVisibility(GridLayoutManager.this.mChildVisibility);
                }
                if (GridLayoutManager.this.mPendingMoveSmoothScroller != null) {
                    GridLayoutManager.this.mPendingMoveSmoothScroller.consumePendingMovesBeforeLayout();
                }
                int subPositionByView = GridLayoutManager.this.getSubPositionByView(viewForPosition, viewForPosition.findFocus());
                if ((GridLayoutManager.this.mFlag & 3) != 1) {
                    if (i == GridLayoutManager.this.mFocusPosition && subPositionByView == GridLayoutManager.this.mSubFocusPosition && GridLayoutManager.this.mPendingMoveSmoothScroller == null) {
                        GridLayoutManager.this.dispatchChildSelected();
                    }
                } else if ((GridLayoutManager.this.mFlag & 4) == 0) {
                    if ((GridLayoutManager.this.mFlag & 16) == 0 && i == GridLayoutManager.this.mFocusPosition && subPositionByView == GridLayoutManager.this.mSubFocusPosition) {
                        GridLayoutManager.this.dispatchChildSelected();
                    } else if ((GridLayoutManager.this.mFlag & 16) != 0 && i >= GridLayoutManager.this.mFocusPosition && viewForPosition.hasFocusable()) {
                        GridLayoutManager.this.mFocusPosition = i;
                        GridLayoutManager.this.mSubFocusPosition = subPositionByView;
                        GridLayoutManager.this.mFlag &= -17;
                        GridLayoutManager.this.dispatchChildSelected();
                    }
                }
                GridLayoutManager.this.measureChild(viewForPosition);
            }
            objArr[0] = viewForPosition;
            return GridLayoutManager.this.mOrientation == 0 ? GridLayoutManager.this.getDecoratedMeasuredWidthWithMargin(viewForPosition) : GridLayoutManager.this.getDecoratedMeasuredHeightWithMargin(viewForPosition);
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public void addItem(Object obj, int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            View view = (View) obj;
            if (i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE) {
                i4 = !GridLayoutManager.this.mGrid.isReversedFlow() ? GridLayoutManager.this.mWindowAlignment.mainAxis().getPaddingMin() : GridLayoutManager.this.mWindowAlignment.mainAxis().getSize() - GridLayoutManager.this.mWindowAlignment.mainAxis().getPaddingMax();
            }
            if (!GridLayoutManager.this.mGrid.isReversedFlow()) {
                i6 = i2 + i4;
                i5 = i4;
            } else {
                i5 = i4 - i2;
                i6 = i4;
            }
            int rowStartSecondary = (GridLayoutManager.this.getRowStartSecondary(i3) + GridLayoutManager.this.mWindowAlignment.secondAxis().getPaddingMin()) - GridLayoutManager.this.mScrollOffsetSecondary;
            GridLayoutManager.this.mChildrenStates.loadView(view, i);
            GridLayoutManager.this.layoutChild(i3, view, i5, i6, rowStartSecondary);
            if (!GridLayoutManager.this.mState.isPreLayout()) {
                GridLayoutManager.this.updateScrollLimits();
            }
            if ((GridLayoutManager.this.mFlag & 3) != 1 && GridLayoutManager.this.mPendingMoveSmoothScroller != null) {
                GridLayoutManager.this.mPendingMoveSmoothScroller.consumePendingMovesAfterLayout();
            }
            if (GridLayoutManager.this.mChildLaidOutListener != null) {
                RecyclerView.ViewHolder childViewHolder = GridLayoutManager.this.mBaseGridView.getChildViewHolder(view);
                GridLayoutManager.this.mChildLaidOutListener.onChildLaidOut(GridLayoutManager.this.mBaseGridView, view, i, childViewHolder == null ? -1L : childViewHolder.getItemId());
            }
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public void removeItem(int i) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            View viewFindViewByPosition = gridLayoutManager.findViewByPosition(i - gridLayoutManager.mPositionDeltaInPreLayout);
            if ((GridLayoutManager.this.mFlag & 3) == 1) {
                GridLayoutManager gridLayoutManager2 = GridLayoutManager.this;
                gridLayoutManager2.detachAndScrapView(viewFindViewByPosition, gridLayoutManager2.mRecycler);
            } else {
                GridLayoutManager gridLayoutManager3 = GridLayoutManager.this;
                gridLayoutManager3.removeAndRecycleView(viewFindViewByPosition, gridLayoutManager3.mRecycler);
            }
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public int getEdge(int i) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            View viewFindViewByPosition = gridLayoutManager.findViewByPosition(i - gridLayoutManager.mPositionDeltaInPreLayout);
            return (GridLayoutManager.this.mFlag & 262144) != 0 ? GridLayoutManager.this.getViewMax(viewFindViewByPosition) : GridLayoutManager.this.getViewMin(viewFindViewByPosition);
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public int getSize(int i) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            return gridLayoutManager.getViewPrimarySize(gridLayoutManager.findViewByPosition(i - gridLayoutManager.mPositionDeltaInPreLayout));
        }
    };
    int mChildVisibility = -1;

    public static class OnLayoutCompleteListener {
        public void onLayoutCompleted(RecyclerView.State state) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return true;
    }

    static final class LayoutParams extends RecyclerView.LayoutParams {
        private int[] mAlignMultiple;
        private int mAlignX;
        private int mAlignY;
        private ItemAlignmentFacet mAlignmentFacet;
        int mBottomInset;
        int mLeftInset;
        int mRightInset;
        int mTopInset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((RecyclerView.LayoutParams) layoutParams);
        }

        int getAlignX() {
            return this.mAlignX;
        }

        int getAlignY() {
            return this.mAlignY;
        }

        int getOpticalLeft(View view) {
            return view.getLeft() + this.mLeftInset;
        }

        int getOpticalTop(View view) {
            return view.getTop() + this.mTopInset;
        }

        int getOpticalRight(View view) {
            return view.getRight() - this.mRightInset;
        }

        int getOpticalBottom(View view) {
            return view.getBottom() - this.mBottomInset;
        }

        int getOpticalWidth(View view) {
            return (view.getWidth() - this.mLeftInset) - this.mRightInset;
        }

        int getOpticalHeight(View view) {
            return (view.getHeight() - this.mTopInset) - this.mBottomInset;
        }

        int getOpticalLeftInset() {
            return this.mLeftInset;
        }

        int getOpticalRightInset() {
            return this.mRightInset;
        }

        int getOpticalTopInset() {
            return this.mTopInset;
        }

        int getOpticalBottomInset() {
            return this.mBottomInset;
        }

        void setAlignX(int i) {
            this.mAlignX = i;
        }

        void setAlignY(int i) {
            this.mAlignY = i;
        }

        void setItemAlignmentFacet(ItemAlignmentFacet itemAlignmentFacet) {
            this.mAlignmentFacet = itemAlignmentFacet;
        }

        ItemAlignmentFacet getItemAlignmentFacet() {
            return this.mAlignmentFacet;
        }

        void calculateItemAlignments(int i, View view) {
            ItemAlignmentFacet.ItemAlignmentDef[] alignmentDefs = this.mAlignmentFacet.getAlignmentDefs();
            int[] iArr = this.mAlignMultiple;
            if (iArr == null || iArr.length != alignmentDefs.length) {
                this.mAlignMultiple = new int[alignmentDefs.length];
            }
            for (int i2 = 0; i2 < alignmentDefs.length; i2++) {
                this.mAlignMultiple[i2] = ItemAlignmentFacetHelper.getAlignmentPosition(view, alignmentDefs[i2], i);
            }
            if (i == 0) {
                this.mAlignX = this.mAlignMultiple[0];
            } else {
                this.mAlignY = this.mAlignMultiple[0];
            }
        }

        int[] getAlignMultiple() {
            return this.mAlignMultiple;
        }

        void setOpticalInsets(int i, int i2, int i3, int i4) {
            this.mLeftInset = i;
            this.mTopInset = i2;
            this.mRightInset = i3;
            this.mBottomInset = i4;
        }
    }

    abstract class GridLinearSmoothScroller extends LinearSmoothScroller {
        boolean mSkipOnStopInternal;

        GridLinearSmoothScroller() {
            super(GridLayoutManager.this.mBaseGridView.getContext());
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void onStop() {
            super.onStop();
            if (!this.mSkipOnStopInternal) {
                onStopInternal();
            }
            if (GridLayoutManager.this.mCurrentSmoothScroller == this) {
                GridLayoutManager.this.mCurrentSmoothScroller = null;
            }
            if (GridLayoutManager.this.mPendingMoveSmoothScroller == this) {
                GridLayoutManager.this.mPendingMoveSmoothScroller = null;
            }
        }

        protected void onStopInternal() {
            View viewFindViewByPosition = findViewByPosition(getTargetPosition());
            if (viewFindViewByPosition == null) {
                if (getTargetPosition() >= 0) {
                    GridLayoutManager.this.scrollToSelection(getTargetPosition(), 0, false, 0);
                    return;
                }
                return;
            }
            if (GridLayoutManager.this.mFocusPosition != getTargetPosition()) {
                GridLayoutManager.this.mFocusPosition = getTargetPosition();
            }
            if (GridLayoutManager.this.hasFocus()) {
                GridLayoutManager.this.mFlag |= 32;
                viewFindViewByPosition.requestFocus();
                GridLayoutManager.this.mFlag &= -33;
            }
            GridLayoutManager.this.dispatchChildSelected();
            GridLayoutManager.this.dispatchChildSelectedAndPositioned();
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected int calculateTimeForScrolling(int i) {
            int iCalculateTimeForScrolling = super.calculateTimeForScrolling(i);
            if (GridLayoutManager.this.mWindowAlignment.mainAxis().getSize() <= 0) {
                return iCalculateTimeForScrolling;
            }
            float size = (30.0f / GridLayoutManager.this.mWindowAlignment.mainAxis().getSize()) * i;
            return ((float) iCalculateTimeForScrolling) < size ? (int) size : iCalculateTimeForScrolling;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int i;
            int i2;
            if (GridLayoutManager.this.getScrollPosition(view, null, GridLayoutManager.sTwoInts)) {
                if (GridLayoutManager.this.mOrientation == 0) {
                    i = GridLayoutManager.sTwoInts[0];
                    i2 = GridLayoutManager.sTwoInts[1];
                } else {
                    i = GridLayoutManager.sTwoInts[1];
                    i2 = GridLayoutManager.sTwoInts[0];
                }
                action.update(i, i2, calculateTimeForDeceleration((int) Math.sqrt((i * i) + (i2 * i2))), this.mDecelerateInterpolator);
            }
        }
    }

    final class PendingMoveSmoothScroller extends GridLinearSmoothScroller {
        static final int TARGET_UNDEFINED = -2;
        private int mPendingMoves;
        private final boolean mStaggeredGrid;

        PendingMoveSmoothScroller(int i, boolean z) {
            super();
            this.mPendingMoves = i;
            this.mStaggeredGrid = z;
            setTargetPosition(-2);
        }

        void increasePendingMoves() {
            if (this.mPendingMoves < GridLayoutManager.this.mMaxPendingMoves) {
                this.mPendingMoves++;
            }
        }

        void decreasePendingMoves() {
            if (this.mPendingMoves > (-GridLayoutManager.this.mMaxPendingMoves)) {
                this.mPendingMoves--;
            }
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x004f -> B:10:0x0015). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:14:0x0022
            	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
            	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
            	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
            */
        void consumePendingMovesBeforeLayout() {
            /*
                r4 = this;
                boolean r0 = r4.mStaggeredGrid
                if (r0 != 0) goto L76
                int r0 = r4.mPendingMoves
                if (r0 != 0) goto La
                goto L76
            La:
                r1 = 0
                if (r0 <= 0) goto L17
                androidx.leanback.widget.GridLayoutManager r0 = androidx.leanback.widget.GridLayoutManager.this
                int r0 = r0.mFocusPosition
                androidx.leanback.widget.GridLayoutManager r2 = androidx.leanback.widget.GridLayoutManager.this
                int r2 = r2.mNumRows
            L15:
                int r0 = r0 + r2
                goto L20
            L17:
                androidx.leanback.widget.GridLayoutManager r0 = androidx.leanback.widget.GridLayoutManager.this
                int r0 = r0.mFocusPosition
                androidx.leanback.widget.GridLayoutManager r2 = androidx.leanback.widget.GridLayoutManager.this
                int r2 = r2.mNumRows
            L1f:
                int r0 = r0 - r2
            L20:
                int r2 = r4.mPendingMoves
                if (r2 == 0) goto L59
                android.view.View r2 = r4.findViewByPosition(r0)
                if (r2 != 0) goto L2b
                goto L59
            L2b:
                androidx.leanback.widget.GridLayoutManager r3 = androidx.leanback.widget.GridLayoutManager.this
                boolean r3 = r3.canScrollTo(r2)
                if (r3 != 0) goto L34
                goto L4b
            L34:
                androidx.leanback.widget.GridLayoutManager r1 = androidx.leanback.widget.GridLayoutManager.this
                r1.mFocusPosition = r0
                androidx.leanback.widget.GridLayoutManager r1 = androidx.leanback.widget.GridLayoutManager.this
                r3 = 0
                r1.mSubFocusPosition = r3
                int r1 = r4.mPendingMoves
                if (r1 <= 0) goto L46
                int r1 = r1 + (-1)
                r4.mPendingMoves = r1
                goto L4a
            L46:
                int r1 = r1 + 1
                r4.mPendingMoves = r1
            L4a:
                r1 = r2
            L4b:
                int r2 = r4.mPendingMoves
                if (r2 <= 0) goto L54
                androidx.leanback.widget.GridLayoutManager r2 = androidx.leanback.widget.GridLayoutManager.this
                int r2 = r2.mNumRows
                goto L15
            L54:
                androidx.leanback.widget.GridLayoutManager r2 = androidx.leanback.widget.GridLayoutManager.this
                int r2 = r2.mNumRows
                goto L1f
            L59:
                if (r1 == 0) goto L76
                androidx.leanback.widget.GridLayoutManager r0 = androidx.leanback.widget.GridLayoutManager.this
                boolean r0 = r0.hasFocus()
                if (r0 == 0) goto L76
                androidx.leanback.widget.GridLayoutManager r0 = androidx.leanback.widget.GridLayoutManager.this
                int r2 = r0.mFlag
                r2 = r2 | 32
                r0.mFlag = r2
                r1.requestFocus()
                androidx.leanback.widget.GridLayoutManager r0 = androidx.leanback.widget.GridLayoutManager.this
                int r1 = r0.mFlag
                r1 = r1 & (-33)
                r0.mFlag = r1
            L76:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.PendingMoveSmoothScroller.consumePendingMovesBeforeLayout():void");
        }

        void consumePendingMovesAfterLayout() {
            int i;
            if (this.mStaggeredGrid && (i = this.mPendingMoves) != 0) {
                this.mPendingMoves = GridLayoutManager.this.processSelectionMoves(true, i);
            }
            int i2 = this.mPendingMoves;
            if (i2 == 0 || ((i2 > 0 && GridLayoutManager.this.hasCreatedLastItem()) || (this.mPendingMoves < 0 && GridLayoutManager.this.hasCreatedFirstItem()))) {
                setTargetPosition(GridLayoutManager.this.mFocusPosition);
                stop();
            }
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action action) {
            if (this.mPendingMoves == 0) {
                return;
            }
            super.updateActionForInterimTarget(action);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public PointF computeScrollVectorForPosition(int i) {
            if (this.mPendingMoves == 0) {
                return null;
            }
            int i2 = ((GridLayoutManager.this.mFlag & 262144) == 0 ? this.mPendingMoves >= 0 : this.mPendingMoves <= 0) ? 1 : -1;
            if (GridLayoutManager.this.mOrientation == 0) {
                return new PointF(i2, 0.0f);
            }
            return new PointF(0.0f, i2);
        }

        @Override // androidx.leanback.widget.GridLayoutManager.GridLinearSmoothScroller
        protected void onStopInternal() {
            super.onStopInternal();
            this.mPendingMoves = 0;
            View viewFindViewByPosition = findViewByPosition(getTargetPosition());
            if (viewFindViewByPosition != null) {
                GridLayoutManager.this.scrollToView(viewFindViewByPosition, true);
            }
        }
    }

    String getTag() {
        return "GridLayoutManager:" + this.mBaseGridView.getId();
    }

    public GridLayoutManager(BaseGridView baseGridView) {
        this.mBaseGridView = baseGridView;
        setItemPrefetchEnabled(false);
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            this.mOrientation = i;
            this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, i);
            this.mWindowAlignment.setOrientation(i);
            this.mItemAlignment.setOrientation(i);
            this.mFlag |= 256;
        }
    }

    /* JADX WARN: Code duplicated, block: B:6:0x000b  */
    public void onRtlPropertiesChanged(int i) {
        int i2;
        if (this.mOrientation == 0) {
            if (i == 1) {
                i2 = 262144;
            } else {
                i2 = 0;
            }
        } else if (i == 1) {
            i2 = 524288;
        } else {
            i2 = 0;
        }
        int i3 = this.mFlag;
        if ((PF_REVERSE_FLOW_MASK & i3) == i2) {
            return;
        }
        int i4 = i2 | (i3 & (-786433));
        this.mFlag = i4;
        this.mFlag = i4 | 256;
        this.mWindowAlignment.horizontal.setReversedFlow(i == 1);
    }

    public int getFocusScrollStrategy() {
        return this.mFocusScrollStrategy;
    }

    public void setFocusScrollStrategy(int i) {
        this.mFocusScrollStrategy = i;
    }

    public void setWindowAlignment(int i) {
        this.mWindowAlignment.mainAxis().setWindowAlignment(i);
    }

    public int getWindowAlignment() {
        return this.mWindowAlignment.mainAxis().getWindowAlignment();
    }

    public void setWindowAlignmentOffset(int i) {
        this.mWindowAlignment.mainAxis().setWindowAlignmentOffset(i);
    }

    public int getWindowAlignmentOffset() {
        return this.mWindowAlignment.mainAxis().getWindowAlignmentOffset();
    }

    public void setWindowAlignmentOffsetPercent(float f) {
        this.mWindowAlignment.mainAxis().setWindowAlignmentOffsetPercent(f);
    }

    public float getWindowAlignmentOffsetPercent() {
        return this.mWindowAlignment.mainAxis().getWindowAlignmentOffsetPercent();
    }

    public void setItemAlignmentOffset(int i) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffset(i);
        updateChildAlignments();
    }

    public int getItemAlignmentOffset() {
        return this.mItemAlignment.mainAxis().getItemAlignmentOffset();
    }

    public void setItemAlignmentOffsetWithPadding(boolean z) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffsetWithPadding(z);
        updateChildAlignments();
    }

    public boolean isItemAlignmentOffsetWithPadding() {
        return this.mItemAlignment.mainAxis().isItemAlignmentOffsetWithPadding();
    }

    public void setItemAlignmentOffsetPercent(float f) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffsetPercent(f);
        updateChildAlignments();
    }

    public float getItemAlignmentOffsetPercent() {
        return this.mItemAlignment.mainAxis().getItemAlignmentOffsetPercent();
    }

    public void setItemAlignmentViewId(int i) {
        this.mItemAlignment.mainAxis().setItemAlignmentViewId(i);
        updateChildAlignments();
    }

    public int getItemAlignmentViewId() {
        return this.mItemAlignment.mainAxis().getItemAlignmentViewId();
    }

    public void setFocusOutAllowed(boolean z, boolean z2) {
        this.mFlag = (z ? 2048 : 0) | (this.mFlag & (-6145)) | (z2 ? 4096 : 0);
    }

    public void setFocusOutSideAllowed(boolean z, boolean z2) {
        this.mFlag = (z ? 8192 : 0) | (this.mFlag & (-24577)) | (z2 ? 16384 : 0);
    }

    public void setNumRows(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.mNumRowsRequested = i;
    }

    public void setRowHeight(int i) {
        if (i >= 0 || i == -2) {
            this.mRowSizeSecondaryRequested = i;
            return;
        }
        throw new IllegalArgumentException("Invalid row height: " + i);
    }

    public void setItemSpacing(int i) {
        this.mHorizontalSpacing = i;
        this.mVerticalSpacing = i;
        this.mSpacingSecondary = i;
        this.mSpacingPrimary = i;
    }

    public void setVerticalSpacing(int i) {
        if (this.mOrientation == 1) {
            this.mVerticalSpacing = i;
            this.mSpacingPrimary = i;
        } else {
            this.mVerticalSpacing = i;
            this.mSpacingSecondary = i;
        }
    }

    public void setHorizontalSpacing(int i) {
        if (this.mOrientation == 0) {
            this.mHorizontalSpacing = i;
            this.mSpacingPrimary = i;
        } else {
            this.mHorizontalSpacing = i;
            this.mSpacingSecondary = i;
        }
    }

    public int getVerticalSpacing() {
        return this.mVerticalSpacing;
    }

    public int getHorizontalSpacing() {
        return this.mHorizontalSpacing;
    }

    public void setGravity(int i) {
        this.mGravity = i;
    }

    protected boolean hasDoneFirstLayout() {
        return this.mGrid != null;
    }

    public void setOnChildSelectedListener(OnChildSelectedListener onChildSelectedListener) {
        this.mChildSelectedListener = onChildSelectedListener;
    }

    public void setOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        if (onChildViewHolderSelectedListener == null) {
            this.mChildViewHolderSelectedListeners = null;
            return;
        }
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList == null) {
            this.mChildViewHolderSelectedListeners = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        this.mChildViewHolderSelectedListeners.add(onChildViewHolderSelectedListener);
    }

    public void addOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        if (this.mChildViewHolderSelectedListeners == null) {
            this.mChildViewHolderSelectedListeners = new ArrayList<>();
        }
        this.mChildViewHolderSelectedListeners.add(onChildViewHolderSelectedListener);
    }

    public void removeOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList != null) {
            arrayList.remove(onChildViewHolderSelectedListener);
        }
    }

    boolean hasOnChildViewHolderSelectedListener() {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        return arrayList != null && arrayList.size() > 0;
    }

    void fireOnChildViewHolderSelected(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            this.mChildViewHolderSelectedListeners.get(size).onChildViewHolderSelected(recyclerView, viewHolder, i, i2);
        }
    }

    void fireOnChildViewHolderSelectedAndPositioned(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            this.mChildViewHolderSelectedListeners.get(size).onChildViewHolderSelectedAndPositioned(recyclerView, viewHolder, i, i2);
        }
    }

    void setOnChildLaidOutListener(OnChildLaidOutListener onChildLaidOutListener) {
        this.mChildLaidOutListener = onChildLaidOutListener;
    }

    private int getAdapterPositionByView(View view) {
        LayoutParams layoutParams;
        if (view == null || (layoutParams = (LayoutParams) view.getLayoutParams()) == null || layoutParams.isItemRemoved()) {
            return -1;
        }
        return layoutParams.getViewAdapterPosition();
    }

    int getSubPositionByView(View view, View view2) {
        ItemAlignmentFacet itemAlignmentFacet;
        if (view != null && view2 != null && (itemAlignmentFacet = ((LayoutParams) view.getLayoutParams()).getItemAlignmentFacet()) != null) {
            ItemAlignmentFacet.ItemAlignmentDef[] alignmentDefs = itemAlignmentFacet.getAlignmentDefs();
            if (alignmentDefs.length > 1) {
                while (view2 != view) {
                    int id = view2.getId();
                    if (id != -1) {
                        for (int i = 1; i < alignmentDefs.length; i++) {
                            if (alignmentDefs[i].getItemAlignmentFocusViewId() == id) {
                                return i;
                            }
                        }
                    }
                    view2 = (View) view2.getParent();
                }
            }
        }
        return 0;
    }

    private int getAdapterPositionByIndex(int i) {
        return getAdapterPositionByView(getChildAt(i));
    }

    void dispatchChildSelected() {
        if (this.mChildSelectedListener != null || hasOnChildViewHolderSelectedListener()) {
            int i = this.mFocusPosition;
            View viewFindViewByPosition = i == -1 ? null : findViewByPosition(i);
            if (viewFindViewByPosition != null) {
                RecyclerView.ViewHolder childViewHolder = this.mBaseGridView.getChildViewHolder(viewFindViewByPosition);
                OnChildSelectedListener onChildSelectedListener = this.mChildSelectedListener;
                if (onChildSelectedListener != null) {
                    onChildSelectedListener.onChildSelected(this.mBaseGridView, viewFindViewByPosition, this.mFocusPosition, childViewHolder == null ? -1L : childViewHolder.getItemId());
                }
                fireOnChildViewHolderSelected(this.mBaseGridView, childViewHolder, this.mFocusPosition, this.mSubFocusPosition);
            } else {
                OnChildSelectedListener onChildSelectedListener2 = this.mChildSelectedListener;
                if (onChildSelectedListener2 != null) {
                    onChildSelectedListener2.onChildSelected(this.mBaseGridView, null, -1, -1L);
                }
                fireOnChildViewHolderSelected(this.mBaseGridView, null, -1, 0);
            }
            if ((this.mFlag & 3) == 1 || this.mBaseGridView.isLayoutRequested()) {
                return;
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).isLayoutRequested()) {
                    forceRequestLayout();
                    return;
                }
            }
        }
    }

    void dispatchChildSelectedAndPositioned() {
        if (hasOnChildViewHolderSelectedListener()) {
            int i = this.mFocusPosition;
            View viewFindViewByPosition = i == -1 ? null : findViewByPosition(i);
            if (viewFindViewByPosition != null) {
                fireOnChildViewHolderSelectedAndPositioned(this.mBaseGridView, this.mBaseGridView.getChildViewHolder(viewFindViewByPosition), this.mFocusPosition, this.mSubFocusPosition);
                return;
            }
            OnChildSelectedListener onChildSelectedListener = this.mChildSelectedListener;
            if (onChildSelectedListener != null) {
                onChildSelectedListener.onChildSelected(this.mBaseGridView, null, -1, -1L);
            }
            fireOnChildViewHolderSelectedAndPositioned(this.mBaseGridView, null, -1, 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0 || this.mNumRows > 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1 || this.mNumRows > 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            return new LayoutParams((RecyclerView.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    protected View getViewForPosition(int i) {
        return this.mRecycler.getViewForPosition(i);
    }

    final int getOpticalLeft(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalLeft(view);
    }

    final int getOpticalRight(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalRight(view);
    }

    final int getOpticalTop(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalTop(view);
    }

    final int getOpticalBottom(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalBottom(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedLeft(View view) {
        return super.getDecoratedLeft(view) + ((LayoutParams) view.getLayoutParams()).mLeftInset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedTop(View view) {
        return super.getDecoratedTop(view) + ((LayoutParams) view.getLayoutParams()).mTopInset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedRight(View view) {
        return super.getDecoratedRight(view) - ((LayoutParams) view.getLayoutParams()).mRightInset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedBottom(View view) {
        return super.getDecoratedBottom(view) - ((LayoutParams) view.getLayoutParams()).mBottomInset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        super.getDecoratedBoundsWithMargins(view, rect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        rect.left += layoutParams.mLeftInset;
        rect.top += layoutParams.mTopInset;
        rect.right -= layoutParams.mRightInset;
        rect.bottom -= layoutParams.mBottomInset;
    }

    int getViewMin(View view) {
        return this.mOrientationHelper.getDecoratedStart(view);
    }

    int getViewMax(View view) {
        return this.mOrientationHelper.getDecoratedEnd(view);
    }

    int getViewPrimarySize(View view) {
        getDecoratedBoundsWithMargins(view, sTempRect);
        return this.mOrientation == 0 ? sTempRect.width() : sTempRect.height();
    }

    private int getViewCenter(View view) {
        return this.mOrientation == 0 ? getViewCenterX(view) : getViewCenterY(view);
    }

    private int getAdjustedViewCenter(View view) {
        View viewFindFocus;
        if (view.hasFocus() && (viewFindFocus = view.findFocus()) != null && viewFindFocus != view) {
            return getAdjustedPrimaryAlignedScrollDistance(getViewCenter(view), view, viewFindFocus);
        }
        return getViewCenter(view);
    }

    private int getViewCenterSecondary(View view) {
        return this.mOrientation == 0 ? getViewCenterY(view) : getViewCenterX(view);
    }

    private int getViewCenterX(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.getOpticalLeft(view) + layoutParams.getAlignX();
    }

    private int getViewCenterY(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.getOpticalTop(view) + layoutParams.getAlignY();
    }

    private void saveContext(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mRecycler != null || this.mState != null) {
            Log.e(TAG, "Recycler information was not released, bug!");
        }
        this.mRecycler = recycler;
        this.mState = state;
        this.mPositionDeltaInPreLayout = 0;
        this.mExtraLayoutSpaceInPreLayout = 0;
    }

    private void leaveContext() {
        this.mRecycler = null;
        this.mState = null;
        this.mPositionDeltaInPreLayout = 0;
        this.mExtraLayoutSpaceInPreLayout = 0;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0076  */
    /* JADX WARN: Code duplicated, block: B:36:0x008b  */
    private boolean layoutInit() {
        Grid grid;
        int itemCount = this.mState.getItemCount();
        if (itemCount == 0) {
            this.mFocusPosition = -1;
            this.mSubFocusPosition = 0;
        } else {
            int i = this.mFocusPosition;
            if (i >= itemCount) {
                this.mFocusPosition = itemCount - 1;
                this.mSubFocusPosition = 0;
            } else if (i == -1 && itemCount > 0) {
                this.mFocusPosition = 0;
                this.mSubFocusPosition = 0;
            }
        }
        if (!this.mState.didStructureChange() && (grid = this.mGrid) != null && grid.getFirstVisibleIndex() >= 0 && (this.mFlag & 256) == 0 && this.mGrid.getNumRows() == this.mNumRows) {
            updateScrollController();
            updateSecondaryScrollLimits();
            this.mGrid.setSpacing(this.mSpacingPrimary);
            return true;
        }
        this.mFlag &= -257;
        Grid grid2 = this.mGrid;
        if (grid2 != null && this.mNumRows == grid2.getNumRows()) {
            if (((this.mFlag & 262144) != 0) != this.mGrid.isReversedFlow()) {
                Grid gridCreateGrid = Grid.createGrid(this.mNumRows);
                this.mGrid = gridCreateGrid;
                gridCreateGrid.setProvider(this.mGridProvider);
                this.mGrid.setReversedFlow((262144 & this.mFlag) != 0);
            }
        } else {
            Grid gridCreateGrid2 = Grid.createGrid(this.mNumRows);
            this.mGrid = gridCreateGrid2;
            gridCreateGrid2.setProvider(this.mGridProvider);
            this.mGrid.setReversedFlow((262144 & this.mFlag) != 0);
        }
        initScrollController();
        updateSecondaryScrollLimits();
        this.mGrid.setSpacing(this.mSpacingPrimary);
        detachAndScrapAttachedViews(this.mRecycler);
        this.mGrid.resetVisibleIndex();
        this.mWindowAlignment.mainAxis().invalidateScrollMin();
        this.mWindowAlignment.mainAxis().invalidateScrollMax();
        return false;
    }

    private int getRowSizeSecondary(int i) {
        int i2 = this.mFixedRowSizeSecondary;
        if (i2 != 0) {
            return i2;
        }
        int[] iArr = this.mRowSizeSecondary;
        if (iArr == null) {
            return 0;
        }
        return iArr[i];
    }

    int getRowStartSecondary(int i) {
        int rowSizeSecondary = 0;
        if ((this.mFlag & 524288) != 0) {
            for (int i2 = this.mNumRows - 1; i2 > i; i2--) {
                rowSizeSecondary += getRowSizeSecondary(i2) + this.mSpacingSecondary;
            }
            return rowSizeSecondary;
        }
        int rowSizeSecondary2 = 0;
        while (rowSizeSecondary < i) {
            rowSizeSecondary2 += getRowSizeSecondary(rowSizeSecondary) + this.mSpacingSecondary;
            rowSizeSecondary++;
        }
        return rowSizeSecondary2;
    }

    private int getSizeSecondary() {
        int i = (this.mFlag & 524288) != 0 ? 0 : this.mNumRows - 1;
        return getRowStartSecondary(i) + getRowSizeSecondary(i);
    }

    int getDecoratedMeasuredWidthWithMargin(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
    }

    int getDecoratedMeasuredHeightWithMargin(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private void measureScrapChild(int i, int i2, int i3, int[] iArr) {
        View viewForPosition = this.mRecycler.getViewForPosition(i);
        if (viewForPosition != null) {
            LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
            calculateItemDecorationsForChild(viewForPosition, sTempRect);
            viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin + sTempRect.left + sTempRect.right, layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + sTempRect.top + sTempRect.bottom, layoutParams.height));
            iArr[0] = getDecoratedMeasuredWidthWithMargin(viewForPosition);
            iArr[1] = getDecoratedMeasuredHeightWithMargin(viewForPosition);
            this.mRecycler.recycleView(viewForPosition);
        }
    }

    private boolean processRowSizeSecondary(boolean z) {
        int decoratedMeasuredWidthWithMargin;
        if (this.mFixedRowSizeSecondary != 0 || this.mRowSizeSecondary == null) {
            return false;
        }
        Grid grid = this.mGrid;
        CircularIntArray[] itemPositionsInRows = grid == null ? null : grid.getItemPositionsInRows();
        boolean z2 = false;
        int i = -1;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            CircularIntArray circularIntArray = itemPositionsInRows == null ? null : itemPositionsInRows[i2];
            int size = circularIntArray == null ? 0 : circularIntArray.size();
            int i3 = -1;
            for (int i4 = 0; i4 < size; i4 += 2) {
                int i5 = circularIntArray.get(i4 + 1);
                for (int i6 = circularIntArray.get(i4); i6 <= i5; i6++) {
                    View viewFindViewByPosition = findViewByPosition(i6 - this.mPositionDeltaInPreLayout);
                    if (viewFindViewByPosition != null) {
                        if (z) {
                            measureChild(viewFindViewByPosition);
                        }
                        if (this.mOrientation == 0) {
                            decoratedMeasuredWidthWithMargin = getDecoratedMeasuredHeightWithMargin(viewFindViewByPosition);
                        } else {
                            decoratedMeasuredWidthWithMargin = getDecoratedMeasuredWidthWithMargin(viewFindViewByPosition);
                        }
                        if (decoratedMeasuredWidthWithMargin > i3) {
                            i3 = decoratedMeasuredWidthWithMargin;
                        }
                    }
                }
            }
            int itemCount = this.mState.getItemCount();
            if (!this.mBaseGridView.hasFixedSize() && z && i3 < 0 && itemCount > 0) {
                if (i < 0) {
                    int i7 = this.mFocusPosition;
                    if (i7 < 0) {
                        i7 = 0;
                    } else if (i7 >= itemCount) {
                        i7 = itemCount - 1;
                    }
                    if (getChildCount() > 0) {
                        int layoutPosition = this.mBaseGridView.getChildViewHolder(getChildAt(0)).getLayoutPosition();
                        int layoutPosition2 = this.mBaseGridView.getChildViewHolder(getChildAt(getChildCount() - 1)).getLayoutPosition();
                        if (i7 >= layoutPosition && i7 <= layoutPosition2) {
                            i7 = i7 - layoutPosition <= layoutPosition2 - i7 ? layoutPosition - 1 : layoutPosition2 + 1;
                            if (i7 < 0 && layoutPosition2 < itemCount - 1) {
                                i7 = layoutPosition2 + 1;
                            } else if (i7 >= itemCount && layoutPosition > 0) {
                                i7 = layoutPosition - 1;
                            }
                        }
                    }
                    if (i7 >= 0 && i7 < itemCount) {
                        measureScrapChild(i7, View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0), this.mMeasuredDimension);
                        i = this.mOrientation == 0 ? this.mMeasuredDimension[1] : this.mMeasuredDimension[0];
                    }
                }
                if (i >= 0) {
                    i3 = i;
                }
            }
            if (i3 < 0) {
                i3 = 0;
            }
            int[] iArr = this.mRowSizeSecondary;
            if (iArr[i2] != i3) {
                iArr[i2] = i3;
                z2 = true;
            }
        }
        return z2;
    }

    private void updateRowSecondarySizeRefresh() {
        int i = (this.mFlag & (-1025)) | (processRowSizeSecondary(false) ? 1024 : 0);
        this.mFlag = i;
        if ((i & 1024) != 0) {
            forceRequestLayout();
        }
    }

    private void forceRequestLayout() {
        ViewCompat.postOnAnimation(this.mBaseGridView, this.mRequestLayoutRunnable);
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:47:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:50:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:51:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:55:0x00f3  */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int size;
        int size2;
        int mode;
        int paddingLeft;
        int paddingRight;
        int i3;
        int i4;
        int i5;
        saveContext(recycler, state);
        if (this.mOrientation == 0) {
            size2 = View.MeasureSpec.getSize(i);
            size = View.MeasureSpec.getSize(i2);
            mode = View.MeasureSpec.getMode(i2);
            paddingLeft = getPaddingTop();
            paddingRight = getPaddingBottom();
        } else {
            size = View.MeasureSpec.getSize(i);
            size2 = View.MeasureSpec.getSize(i2);
            mode = View.MeasureSpec.getMode(i);
            paddingLeft = getPaddingLeft();
            paddingRight = getPaddingRight();
        }
        int i6 = paddingLeft + paddingRight;
        this.mMaxSizeSecondary = size;
        int i7 = this.mRowSizeSecondaryRequested;
        if (i7 == -2) {
            int i8 = this.mNumRowsRequested;
            if (i8 == 0) {
                i8 = 1;
            }
            this.mNumRows = i8;
            this.mFixedRowSizeSecondary = 0;
            int[] iArr = this.mRowSizeSecondary;
            if (iArr == null || iArr.length != i8) {
                this.mRowSizeSecondary = new int[this.mNumRows];
            }
            if (this.mState.isPreLayout()) {
                updatePositionDeltaInPreLayout();
            }
            processRowSizeSecondary(true);
            if (mode == Integer.MIN_VALUE) {
                size = Math.min(getSizeSecondary() + i6, this.mMaxSizeSecondary);
            } else if (mode == 0) {
                size = getSizeSecondary() + i6;
            } else if (mode == 1073741824) {
                size = this.mMaxSizeSecondary;
            } else {
                throw new IllegalStateException("wrong spec");
            }
        } else if (mode == Integer.MIN_VALUE) {
            if (this.mNumRowsRequested != 0 && this.mRowSizeSecondaryRequested == 0) {
                this.mNumRows = 1;
                this.mFixedRowSizeSecondary = size - i6;
            } else {
                i3 = this.mNumRowsRequested;
                if (i3 == 0) {
                    int i9 = this.mRowSizeSecondaryRequested;
                    this.mFixedRowSizeSecondary = i9;
                    int i10 = this.mSpacingSecondary;
                    this.mNumRows = (size + i10) / (i9 + i10);
                } else {
                    i4 = this.mRowSizeSecondaryRequested;
                    if (i4 == 0) {
                        this.mNumRows = i3;
                        this.mFixedRowSizeSecondary = ((size - i6) - (this.mSpacingSecondary * (i3 - 1))) / i3;
                    } else {
                        this.mNumRows = i3;
                        this.mFixedRowSizeSecondary = i4;
                    }
                }
            }
            if (mode == Integer.MIN_VALUE) {
                int i11 = this.mFixedRowSizeSecondary;
                int i12 = this.mNumRows;
                i5 = (i11 * i12) + (this.mSpacingSecondary * (i12 - 1)) + i6;
                if (i5 < size) {
                    size = i5;
                }
            }
        } else if (mode == 0) {
            if (i7 == 0) {
                i7 = size - i6;
            }
            this.mFixedRowSizeSecondary = i7;
            int i13 = this.mNumRowsRequested;
            if (i13 == 0) {
                i13 = 1;
            }
            this.mNumRows = i13;
            size = (this.mFixedRowSizeSecondary * i13) + (this.mSpacingSecondary * (i13 - 1)) + i6;
        } else {
            if (mode != 1073741824) {
                throw new IllegalStateException("wrong spec");
            }
            if (this.mNumRowsRequested != 0) {
                i3 = this.mNumRowsRequested;
                if (i3 == 0) {
                    int i14 = this.mRowSizeSecondaryRequested;
                    this.mFixedRowSizeSecondary = i14;
                    int i15 = this.mSpacingSecondary;
                    this.mNumRows = (size + i15) / (i14 + i15);
                } else {
                    i4 = this.mRowSizeSecondaryRequested;
                    if (i4 == 0) {
                        this.mNumRows = i3;
                        this.mFixedRowSizeSecondary = ((size - i6) - (this.mSpacingSecondary * (i3 - 1))) / i3;
                    } else {
                        this.mNumRows = i3;
                        this.mFixedRowSizeSecondary = i4;
                    }
                }
            } else {
                i3 = this.mNumRowsRequested;
                if (i3 == 0) {
                    int i16 = this.mRowSizeSecondaryRequested;
                    this.mFixedRowSizeSecondary = i16;
                    int i17 = this.mSpacingSecondary;
                    this.mNumRows = (size + i17) / (i16 + i17);
                } else {
                    i4 = this.mRowSizeSecondaryRequested;
                    if (i4 == 0) {
                        this.mNumRows = i3;
                        this.mFixedRowSizeSecondary = ((size - i6) - (this.mSpacingSecondary * (i3 - 1))) / i3;
                    } else {
                        this.mNumRows = i3;
                        this.mFixedRowSizeSecondary = i4;
                    }
                }
            }
            if (mode == Integer.MIN_VALUE) {
                int i18 = this.mFixedRowSizeSecondary;
                int i19 = this.mNumRows;
                i5 = (i18 * i19) + (this.mSpacingSecondary * (i19 - 1)) + i6;
                if (i5 < size) {
                    size = i5;
                }
            }
        }
        if (this.mOrientation == 0) {
            setMeasuredDimension(size2, size);
        } else {
            setMeasuredDimension(size, size2);
        }
        leaveContext();
    }

    void measureChild(View view) {
        int iMakeMeasureSpec;
        int childMeasureSpec;
        int childMeasureSpec2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        calculateItemDecorationsForChild(view, sTempRect);
        int i = layoutParams.leftMargin + layoutParams.rightMargin + sTempRect.left + sTempRect.right;
        int i2 = layoutParams.topMargin + layoutParams.bottomMargin + sTempRect.top + sTempRect.bottom;
        if (this.mRowSizeSecondaryRequested == -2) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mFixedRowSizeSecondary, 1073741824);
        }
        if (this.mOrientation == 0) {
            childMeasureSpec = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), i, layoutParams.width);
            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(iMakeMeasureSpec, i2, layoutParams.height);
        } else {
            int childMeasureSpec3 = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), i2, layoutParams.height);
            childMeasureSpec = ViewGroup.getChildMeasureSpec(iMakeMeasureSpec, i, layoutParams.width);
            childMeasureSpec2 = childMeasureSpec3;
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    <E> E getFacet(RecyclerView.ViewHolder viewHolder, Class<? extends E> cls) {
        FacetProviderAdapter facetProviderAdapter;
        FacetProvider facetProvider;
        E e = viewHolder instanceof FacetProvider ? (E) ((FacetProvider) viewHolder).getFacet(cls) : null;
        return (e != null || (facetProviderAdapter = this.mFacetProviderAdapter) == null || (facetProvider = facetProviderAdapter.getFacetProvider(viewHolder.getItemViewType())) == null) ? e : (E) facetProvider.getFacet(cls);
    }

    void layoutChild(int i, View view, int i2, int i3, int i4) {
        int rowSizeSecondary;
        int i5;
        int decoratedMeasuredHeightWithMargin = this.mOrientation == 0 ? getDecoratedMeasuredHeightWithMargin(view) : getDecoratedMeasuredWidthWithMargin(view);
        int i6 = this.mFixedRowSizeSecondary;
        if (i6 > 0) {
            decoratedMeasuredHeightWithMargin = Math.min(decoratedMeasuredHeightWithMargin, i6);
        }
        int i7 = this.mGravity;
        int i8 = i7 & 112;
        int absoluteGravity = (this.mFlag & PF_REVERSE_FLOW_MASK) != 0 ? Gravity.getAbsoluteGravity(i7 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK, 1) : i7 & 7;
        if ((this.mOrientation != 0 || i8 != 48) && (this.mOrientation != 1 || absoluteGravity != 3)) {
            if ((this.mOrientation == 0 && i8 == 80) || (this.mOrientation == 1 && absoluteGravity == 5)) {
                rowSizeSecondary = getRowSizeSecondary(i) - decoratedMeasuredHeightWithMargin;
            } else if ((this.mOrientation == 0 && i8 == 16) || (this.mOrientation == 1 && absoluteGravity == 1)) {
                rowSizeSecondary = (getRowSizeSecondary(i) - decoratedMeasuredHeightWithMargin) / 2;
            }
            i4 += rowSizeSecondary;
        }
        if (this.mOrientation == 0) {
            i5 = decoratedMeasuredHeightWithMargin + i4;
        } else {
            int i9 = decoratedMeasuredHeightWithMargin + i4;
            int i10 = i4;
            i4 = i2;
            i2 = i10;
            i5 = i3;
            i3 = i9;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutDecoratedWithMargins(view, i2, i4, i3, i5);
        super.getDecoratedBoundsWithMargins(view, sTempRect);
        layoutParams.setOpticalInsets(i2 - sTempRect.left, i4 - sTempRect.top, sTempRect.right - i3, sTempRect.bottom - i5);
        updateChildAlignments(view);
    }

    private void updateChildAlignments(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.getItemAlignmentFacet() == null) {
            layoutParams.setAlignX(this.mItemAlignment.horizontal.getAlignmentPosition(view));
            layoutParams.setAlignY(this.mItemAlignment.vertical.getAlignmentPosition(view));
            return;
        }
        layoutParams.calculateItemAlignments(this.mOrientation, view);
        if (this.mOrientation == 0) {
            layoutParams.setAlignY(this.mItemAlignment.vertical.getAlignmentPosition(view));
        } else {
            layoutParams.setAlignX(this.mItemAlignment.horizontal.getAlignmentPosition(view));
        }
    }

    private void updateChildAlignments() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            updateChildAlignments(getChildAt(i));
        }
    }

    void setExtraLayoutSpace(int i) {
        int i2 = this.mExtraLayoutSpace;
        if (i2 == i) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("ExtraLayoutSpace must >= 0");
        }
        this.mExtraLayoutSpace = i;
        requestLayout();
    }

    int getExtraLayoutSpace() {
        return this.mExtraLayoutSpace;
    }

    private void removeInvisibleViewsAtEnd() {
        int i = this.mFlag;
        if ((65600 & i) == 65536) {
            this.mGrid.removeInvisibleItemsAtEnd(this.mFocusPosition, (i & 262144) != 0 ? -this.mExtraLayoutSpace : this.mSizePrimary + this.mExtraLayoutSpace);
        }
    }

    private void removeInvisibleViewsAtFront() {
        int i = this.mFlag;
        if ((65600 & i) == 65536) {
            this.mGrid.removeInvisibleItemsAtFront(this.mFocusPosition, (i & 262144) != 0 ? this.mSizePrimary + this.mExtraLayoutSpace : -this.mExtraLayoutSpace);
        }
    }

    private boolean appendOneColumnVisibleItems() {
        return this.mGrid.appendOneColumnVisibleItems();
    }

    void slideIn() {
        int i = this.mFlag;
        if ((i & 64) != 0) {
            int i2 = i & (-65);
            this.mFlag = i2;
            int i3 = this.mFocusPosition;
            if (i3 >= 0) {
                scrollToSelection(i3, this.mSubFocusPosition, true, this.mPrimaryScrollExtra);
            } else {
                this.mFlag = i2 & (-129);
                requestLayout();
            }
            int i4 = this.mFlag;
            if ((i4 & 128) != 0) {
                this.mFlag = i4 & (-129);
                if (this.mBaseGridView.getScrollState() != 0 || isSmoothScrolling()) {
                    this.mBaseGridView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: androidx.leanback.widget.GridLayoutManager.3
                        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                        public void onScrollStateChanged(RecyclerView recyclerView, int i5) {
                            if (i5 == 0) {
                                GridLayoutManager.this.mBaseGridView.removeOnScrollListener(this);
                                GridLayoutManager.this.requestLayout();
                            }
                        }
                    });
                } else {
                    requestLayout();
                }
            }
        }
    }

    int getSlideOutDistance() {
        int i;
        int left;
        int right;
        if (this.mOrientation == 1) {
            i = -getHeight();
            if (getChildCount() <= 0 || (left = getChildAt(0).getTop()) >= 0) {
                return i;
            }
        } else {
            if ((this.mFlag & 262144) != 0) {
                int width = getWidth();
                return (getChildCount() <= 0 || (right = getChildAt(0).getRight()) <= width) ? width : right;
            }
            i = -getWidth();
            if (getChildCount() <= 0 || (left = getChildAt(0).getLeft()) >= 0) {
                return i;
            }
        }
        return i + left;
    }

    boolean isSlidingChildViews() {
        return (this.mFlag & 64) != 0;
    }

    void slideOut() {
        int i = this.mFlag;
        if ((i & 64) != 0) {
            return;
        }
        this.mFlag = i | 64;
        if (getChildCount() == 0) {
            return;
        }
        if (this.mOrientation == 1) {
            this.mBaseGridView.smoothScrollBy(0, getSlideOutDistance(), new AccelerateDecelerateInterpolator());
        } else {
            this.mBaseGridView.smoothScrollBy(getSlideOutDistance(), 0, new AccelerateDecelerateInterpolator());
        }
    }

    private boolean prependOneColumnVisibleItems() {
        return this.mGrid.prependOneColumnVisibleItems();
    }

    private void appendVisibleItems() {
        this.mGrid.appendVisibleItems((this.mFlag & 262144) != 0 ? (-this.mExtraLayoutSpace) - this.mExtraLayoutSpaceInPreLayout : this.mSizePrimary + this.mExtraLayoutSpace + this.mExtraLayoutSpaceInPreLayout);
    }

    private void prependVisibleItems() {
        this.mGrid.prependVisibleItems((this.mFlag & 262144) != 0 ? this.mSizePrimary + this.mExtraLayoutSpace + this.mExtraLayoutSpaceInPreLayout : (-this.mExtraLayoutSpace) - this.mExtraLayoutSpaceInPreLayout);
    }

    private void fastRelayout() {
        Grid.Location location;
        int decoratedMeasuredHeightWithMargin;
        int childCount = getChildCount();
        int firstVisibleIndex = this.mGrid.getFirstVisibleIndex();
        this.mFlag &= -9;
        boolean z = false;
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (firstVisibleIndex == getAdapterPositionByView(childAt) && (location = this.mGrid.getLocation(firstVisibleIndex)) != null) {
                int rowStartSecondary = (getRowStartSecondary(location.row) + this.mWindowAlignment.secondAxis().getPaddingMin()) - this.mScrollOffsetSecondary;
                int viewMin = getViewMin(childAt);
                int viewPrimarySize = getViewPrimarySize(childAt);
                if (((LayoutParams) childAt.getLayoutParams()).viewNeedsUpdate()) {
                    this.mFlag |= 8;
                    detachAndScrapView(childAt, this.mRecycler);
                    childAt = getViewForPosition(firstVisibleIndex);
                    addView(childAt, i);
                }
                View view = childAt;
                measureChild(view);
                if (this.mOrientation == 0) {
                    decoratedMeasuredHeightWithMargin = getDecoratedMeasuredWidthWithMargin(view);
                } else {
                    decoratedMeasuredHeightWithMargin = getDecoratedMeasuredHeightWithMargin(view);
                }
                layoutChild(location.row, view, viewMin, viewMin + decoratedMeasuredHeightWithMargin, rowStartSecondary);
                if (viewPrimarySize == decoratedMeasuredHeightWithMargin) {
                    i++;
                    firstVisibleIndex++;
                }
            }
            z = true;
        }
        if (z) {
            int lastVisibleIndex = this.mGrid.getLastVisibleIndex();
            for (int i2 = childCount - 1; i2 >= i; i2--) {
                detachAndScrapView(getChildAt(i2), this.mRecycler);
            }
            this.mGrid.invalidateItemsAfter(firstVisibleIndex);
            if ((this.mFlag & 65536) != 0) {
                appendVisibleItems();
                int i3 = this.mFocusPosition;
                if (i3 >= 0 && i3 <= lastVisibleIndex) {
                    while (this.mGrid.getLastVisibleIndex() < this.mFocusPosition) {
                        this.mGrid.appendOneColumnVisibleItems();
                    }
                }
            } else {
                while (this.mGrid.appendOneColumnVisibleItems() && this.mGrid.getLastVisibleIndex() < lastVisibleIndex) {
                }
            }
        }
        updateScrollLimits();
        updateSecondaryScrollLimits();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void removeAndRecycleAllViews(RecyclerView.Recycler recycler) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            removeAndRecycleViewAt(childCount, recycler);
        }
    }

    private void focusToViewInLayout(boolean z, boolean z2, int i, int i2) {
        View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
        if (viewFindViewByPosition != null && z2) {
            scrollToView(viewFindViewByPosition, false, i, i2);
        }
        if (viewFindViewByPosition != null && z && !viewFindViewByPosition.hasFocus()) {
            viewFindViewByPosition.requestFocus();
            return;
        }
        if (z || this.mBaseGridView.hasFocus()) {
            return;
        }
        if (viewFindViewByPosition != null && viewFindViewByPosition.hasFocusable()) {
            this.mBaseGridView.focusableViewAvailable(viewFindViewByPosition);
        } else {
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                viewFindViewByPosition = getChildAt(i3);
                if (viewFindViewByPosition != null && viewFindViewByPosition.hasFocusable()) {
                    this.mBaseGridView.focusableViewAvailable(viewFindViewByPosition);
                    break;
                }
            }
        }
        if (z2 && viewFindViewByPosition != null && viewFindViewByPosition.hasFocus()) {
            scrollToView(viewFindViewByPosition, false, i, i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        OnLayoutCompleteListener onLayoutCompleteListener = this.mLayoutCompleteListener;
        if (onLayoutCompleteListener != null) {
            onLayoutCompleteListener.onLayoutCompleted(state);
        }
    }

    void updatePositionToRowMapInPostLayout() {
        Grid.Location location;
        this.mPositionToRowInPostLayout.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int oldPosition = this.mBaseGridView.getChildViewHolder(getChildAt(i)).getOldPosition();
            if (oldPosition >= 0 && (location = this.mGrid.getLocation(oldPosition)) != null) {
                this.mPositionToRowInPostLayout.put(oldPosition, location.row);
            }
        }
    }

    void fillScrapViewsInPostLayout() {
        List<RecyclerView.ViewHolder> scrapList = this.mRecycler.getScrapList();
        int size = scrapList.size();
        if (size == 0) {
            return;
        }
        int[] iArr = this.mDisappearingPositions;
        if (iArr == null || size > iArr.length) {
            int[] iArr2 = this.mDisappearingPositions;
            int length = iArr2 == null ? 16 : iArr2.length;
            while (length < size) {
                length <<= 1;
            }
            this.mDisappearingPositions = new int[length];
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int adapterPosition = scrapList.get(i2).getAdapterPosition();
            if (adapterPosition >= 0) {
                this.mDisappearingPositions[i] = adapterPosition;
                i++;
            }
        }
        if (i > 0) {
            Arrays.sort(this.mDisappearingPositions, 0, i);
            this.mGrid.fillDisappearingItems(this.mDisappearingPositions, i, this.mPositionToRowInPostLayout);
        }
        this.mPositionToRowInPostLayout.clear();
    }

    void updatePositionDeltaInPreLayout() {
        if (getChildCount() > 0) {
            this.mPositionDeltaInPreLayout = this.mGrid.getFirstVisibleIndex() - ((LayoutParams) getChildAt(0).getLayoutParams()).getViewLayoutPosition();
        } else {
            this.mPositionDeltaInPreLayout = 0;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int remainingScrollHorizontal;
        int remainingScrollVertical;
        int i;
        int i2;
        int i3;
        if (this.mNumRows != 0 && state.getItemCount() >= 0) {
            if ((this.mFlag & 64) != 0 && getChildCount() > 0) {
                this.mFlag |= 128;
                return;
            }
            int i4 = this.mFlag;
            if ((i4 & 512) == 0) {
                discardLayoutInfo();
                removeAndRecycleAllViews(recycler);
                return;
            }
            this.mFlag = (i4 & (-4)) | 1;
            saveContext(recycler, state);
            int iMax = Integer.MIN_VALUE;
            if (state.isPreLayout()) {
                updatePositionDeltaInPreLayout();
                int childCount = getChildCount();
                if (this.mGrid != null && childCount > 0) {
                    int iMin = Integer.MAX_VALUE;
                    int oldPosition = this.mBaseGridView.getChildViewHolder(getChildAt(0)).getOldPosition();
                    int oldPosition2 = this.mBaseGridView.getChildViewHolder(getChildAt(childCount - 1)).getOldPosition();
                    for (int i5 = 0; i5 < childCount; i5++) {
                        View childAt = getChildAt(i5);
                        LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                        int childAdapterPosition = this.mBaseGridView.getChildAdapterPosition(childAt);
                        if (layoutParams.isItemChanged() || layoutParams.isItemRemoved() || childAt.isLayoutRequested() || ((!childAt.hasFocus() && this.mFocusPosition == layoutParams.getViewAdapterPosition()) || ((childAt.hasFocus() && this.mFocusPosition != layoutParams.getViewAdapterPosition()) || childAdapterPosition < oldPosition || childAdapterPosition > oldPosition2))) {
                            iMin = Math.min(iMin, getViewMin(childAt));
                            iMax = Math.max(iMax, getViewMax(childAt));
                        }
                    }
                    if (iMax > iMin) {
                        this.mExtraLayoutSpaceInPreLayout = iMax - iMin;
                    }
                    appendVisibleItems();
                    prependVisibleItems();
                }
                this.mFlag &= -4;
                leaveContext();
                return;
            }
            if (state.willRunPredictiveAnimations()) {
                updatePositionToRowMapInPostLayout();
            }
            boolean z = !isSmoothScrolling() && this.mFocusScrollStrategy == 0;
            int i6 = this.mFocusPosition;
            if (i6 != -1 && (i3 = this.mFocusPositionOffset) != Integer.MIN_VALUE) {
                this.mFocusPosition = i6 + i3;
                this.mSubFocusPosition = 0;
            }
            this.mFocusPositionOffset = 0;
            View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
            int i7 = this.mFocusPosition;
            int i8 = this.mSubFocusPosition;
            boolean zHasFocus = this.mBaseGridView.hasFocus();
            Grid grid = this.mGrid;
            int firstVisibleIndex = grid != null ? grid.getFirstVisibleIndex() : -1;
            Grid grid2 = this.mGrid;
            int lastVisibleIndex = grid2 != null ? grid2.getLastVisibleIndex() : -1;
            if (this.mOrientation == 0) {
                remainingScrollVertical = state.getRemainingScrollHorizontal();
                remainingScrollHorizontal = state.getRemainingScrollVertical();
            } else {
                remainingScrollHorizontal = state.getRemainingScrollHorizontal();
                remainingScrollVertical = state.getRemainingScrollVertical();
            }
            if (layoutInit()) {
                this.mFlag |= 4;
                this.mGrid.setStart(this.mFocusPosition);
                fastRelayout();
            } else {
                int i9 = this.mFlag & (-5);
                this.mFlag = i9;
                this.mFlag = (zHasFocus ? 16 : 0) | (i9 & (-17));
                if (z && (firstVisibleIndex < 0 || (i = this.mFocusPosition) > lastVisibleIndex || i < firstVisibleIndex)) {
                    firstVisibleIndex = this.mFocusPosition;
                    lastVisibleIndex = firstVisibleIndex;
                }
                this.mGrid.setStart(firstVisibleIndex);
                if (lastVisibleIndex != -1) {
                    while (appendOneColumnVisibleItems() && findViewByPosition(lastVisibleIndex) == null) {
                    }
                }
            }
            while (true) {
                updateScrollLimits();
                int firstVisibleIndex2 = this.mGrid.getFirstVisibleIndex();
                int lastVisibleIndex2 = this.mGrid.getLastVisibleIndex();
                focusToViewInLayout(zHasFocus, z, -remainingScrollVertical, -remainingScrollHorizontal);
                appendVisibleItems();
                prependVisibleItems();
                if (this.mGrid.getFirstVisibleIndex() == firstVisibleIndex2 && this.mGrid.getLastVisibleIndex() == lastVisibleIndex2) {
                    break;
                }
            }
            removeInvisibleViewsAtFront();
            removeInvisibleViewsAtEnd();
            if (state.willRunPredictiveAnimations()) {
                fillScrapViewsInPostLayout();
            }
            int i10 = this.mFlag;
            if ((i10 & 1024) != 0) {
                this.mFlag = i10 & (-1025);
            } else {
                updateRowSecondarySizeRefresh();
            }
            if (((this.mFlag & 4) != 0 && ((i2 = this.mFocusPosition) != i7 || this.mSubFocusPosition != i8 || findViewByPosition(i2) != viewFindViewByPosition || (this.mFlag & 8) != 0)) || (this.mFlag & 20) == 16) {
                dispatchChildSelected();
            }
            dispatchChildSelectedAndPositioned();
            if ((this.mFlag & 64) != 0) {
                scrollDirectionPrimary(getSlideOutDistance());
            }
            this.mFlag &= -4;
            leaveContext();
        }
    }

    private void offsetChildrenSecondary(int i) {
        int childCount = getChildCount();
        int i2 = 0;
        if (this.mOrientation == 0) {
            while (i2 < childCount) {
                getChildAt(i2).offsetTopAndBottom(i);
                i2++;
            }
        } else {
            while (i2 < childCount) {
                getChildAt(i2).offsetLeftAndRight(i);
                i2++;
            }
        }
    }

    private void offsetChildrenPrimary(int i) {
        int childCount = getChildCount();
        int i2 = 0;
        if (this.mOrientation == 1) {
            while (i2 < childCount) {
                getChildAt(i2).offsetTopAndBottom(i);
                i2++;
            }
        } else {
            while (i2 < childCount) {
                getChildAt(i2).offsetLeftAndRight(i);
                i2++;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int iScrollDirectionSecondary;
        if ((this.mFlag & 512) == 0 || !hasDoneFirstLayout()) {
            return 0;
        }
        saveContext(recycler, state);
        this.mFlag = (this.mFlag & (-4)) | 2;
        if (this.mOrientation == 0) {
            iScrollDirectionSecondary = scrollDirectionPrimary(i);
        } else {
            iScrollDirectionSecondary = scrollDirectionSecondary(i);
        }
        leaveContext();
        this.mFlag &= -4;
        return iScrollDirectionSecondary;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int iScrollDirectionSecondary;
        if ((this.mFlag & 512) == 0 || !hasDoneFirstLayout()) {
            return 0;
        }
        this.mFlag = (this.mFlag & (-4)) | 2;
        saveContext(recycler, state);
        if (this.mOrientation == 1) {
            iScrollDirectionSecondary = scrollDirectionPrimary(i);
        } else {
            iScrollDirectionSecondary = scrollDirectionSecondary(i);
        }
        leaveContext();
        this.mFlag &= -4;
        return iScrollDirectionSecondary;
    }

    private int scrollDirectionPrimary(int i) {
        int minScroll;
        int i2 = this.mFlag;
        if ((i2 & 64) == 0 && (i2 & 3) != 1 && (i <= 0 ? !(i >= 0 || this.mWindowAlignment.mainAxis().isMinUnknown() || i >= (minScroll = this.mWindowAlignment.mainAxis().getMinScroll())) : !(this.mWindowAlignment.mainAxis().isMaxUnknown() || i <= (minScroll = this.mWindowAlignment.mainAxis().getMaxScroll())))) {
            i = minScroll;
        }
        if (i == 0) {
            return 0;
        }
        offsetChildrenPrimary(-i);
        if ((this.mFlag & 3) == 1) {
            updateScrollLimits();
            return i;
        }
        int childCount = getChildCount();
        if ((this.mFlag & 262144) == 0 ? i < 0 : i > 0) {
            prependVisibleItems();
        } else {
            appendVisibleItems();
        }
        boolean z = getChildCount() > childCount;
        int childCount2 = getChildCount();
        if ((262144 & this.mFlag) == 0 ? i < 0 : i > 0) {
            removeInvisibleViewsAtEnd();
        } else {
            removeInvisibleViewsAtFront();
        }
        if (z | (getChildCount() < childCount2)) {
            updateRowSecondarySizeRefresh();
        }
        this.mBaseGridView.invalidate();
        updateScrollLimits();
        return i;
    }

    private int scrollDirectionSecondary(int i) {
        if (i == 0) {
            return 0;
        }
        offsetChildrenSecondary(-i);
        this.mScrollOffsetSecondary += i;
        updateSecondaryScrollLimits();
        this.mBaseGridView.invalidate();
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        try {
            saveContext(null, state);
            if (this.mOrientation != 0) {
                i = i2;
            }
            if (getChildCount() != 0 && i != 0) {
                this.mGrid.collectAdjacentPrefetchPositions(i < 0 ? -this.mExtraLayoutSpace : this.mSizePrimary + this.mExtraLayoutSpace, i, layoutPrefetchRegistry);
            }
        } finally {
            leaveContext();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = this.mBaseGridView.mInitialPrefetchItemCount;
        if (i == 0 || i2 == 0) {
            return;
        }
        int iMax = Math.max(0, Math.min(this.mFocusPosition - ((i2 - 1) / 2), i - i2));
        for (int i3 = iMax; i3 < i && i3 < iMax + i2; i3++) {
            layoutPrefetchRegistry.addPosition(i3, 0);
        }
    }

    void updateScrollLimits() {
        int firstVisibleIndex;
        int lastVisibleIndex;
        int itemCount;
        int itemCount2;
        int viewCenter;
        int viewCenter2;
        if (this.mState.getItemCount() == 0) {
            return;
        }
        if ((this.mFlag & 262144) == 0) {
            firstVisibleIndex = this.mGrid.getLastVisibleIndex();
            itemCount2 = this.mState.getItemCount() - 1;
            lastVisibleIndex = this.mGrid.getFirstVisibleIndex();
            itemCount = 0;
        } else {
            firstVisibleIndex = this.mGrid.getFirstVisibleIndex();
            lastVisibleIndex = this.mGrid.getLastVisibleIndex();
            itemCount = this.mState.getItemCount() - 1;
            itemCount2 = 0;
        }
        if (firstVisibleIndex < 0 || lastVisibleIndex < 0) {
            return;
        }
        boolean z = firstVisibleIndex == itemCount2;
        boolean z2 = lastVisibleIndex == itemCount;
        if (z || !this.mWindowAlignment.mainAxis().isMaxUnknown() || z2 || !this.mWindowAlignment.mainAxis().isMinUnknown()) {
            int iFindRowMax = Integer.MAX_VALUE;
            if (z) {
                iFindRowMax = this.mGrid.findRowMax(true, sTwoInts);
                View viewFindViewByPosition = findViewByPosition(sTwoInts[1]);
                viewCenter = getViewCenter(viewFindViewByPosition);
                int[] alignMultiple = ((LayoutParams) viewFindViewByPosition.getLayoutParams()).getAlignMultiple();
                if (alignMultiple != null && alignMultiple.length > 0) {
                    viewCenter += alignMultiple[alignMultiple.length - 1] - alignMultiple[0];
                }
            } else {
                viewCenter = Integer.MAX_VALUE;
            }
            int iFindRowMin = Integer.MIN_VALUE;
            if (z2) {
                iFindRowMin = this.mGrid.findRowMin(false, sTwoInts);
                viewCenter2 = getViewCenter(findViewByPosition(sTwoInts[1]));
            } else {
                viewCenter2 = Integer.MIN_VALUE;
            }
            this.mWindowAlignment.mainAxis().updateMinMax(iFindRowMin, iFindRowMax, viewCenter2, viewCenter);
        }
    }

    private void updateSecondaryScrollLimits() {
        WindowAlignment.Axis axisSecondAxis = this.mWindowAlignment.secondAxis();
        int paddingMin = axisSecondAxis.getPaddingMin() - this.mScrollOffsetSecondary;
        int sizeSecondary = getSizeSecondary() + paddingMin;
        axisSecondAxis.updateMinMax(paddingMin, sizeSecondary, paddingMin, sizeSecondary);
    }

    private void initScrollController() {
        this.mWindowAlignment.reset();
        this.mWindowAlignment.horizontal.setSize(getWidth());
        this.mWindowAlignment.vertical.setSize(getHeight());
        this.mWindowAlignment.horizontal.setPadding(getPaddingLeft(), getPaddingRight());
        this.mWindowAlignment.vertical.setPadding(getPaddingTop(), getPaddingBottom());
        this.mSizePrimary = this.mWindowAlignment.mainAxis().getSize();
        this.mScrollOffsetSecondary = 0;
    }

    private void updateScrollController() {
        this.mWindowAlignment.horizontal.setSize(getWidth());
        this.mWindowAlignment.vertical.setSize(getHeight());
        this.mWindowAlignment.horizontal.setPadding(getPaddingLeft(), getPaddingRight());
        this.mWindowAlignment.vertical.setPadding(getPaddingTop(), getPaddingBottom());
        this.mSizePrimary = this.mWindowAlignment.mainAxis().getSize();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        setSelection(i, 0, false, 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        setSelection(i, 0, true, 0);
    }

    public void setSelection(int i, int i2) {
        setSelection(i, 0, false, i2);
    }

    public void setSelectionSmooth(int i) {
        setSelection(i, 0, true, 0);
    }

    public void setSelectionWithSub(int i, int i2, int i3) {
        setSelection(i, i2, false, i3);
    }

    public void setSelectionSmoothWithSub(int i, int i2) {
        setSelection(i, i2, true, 0);
    }

    public int getSelection() {
        return this.mFocusPosition;
    }

    public int getSubSelection() {
        return this.mSubFocusPosition;
    }

    public void setSelection(int i, int i2, boolean z, int i3) {
        if ((this.mFocusPosition == i || i == -1) && i2 == this.mSubFocusPosition && i3 == this.mPrimaryScrollExtra) {
            return;
        }
        scrollToSelection(i, i2, z, i3);
    }

    void scrollToSelection(int i, int i2, boolean z, int i3) {
        this.mPrimaryScrollExtra = i3;
        View viewFindViewByPosition = findViewByPosition(i);
        boolean z2 = !isSmoothScrolling();
        if (z2 && !this.mBaseGridView.isLayoutRequested() && viewFindViewByPosition != null && getAdapterPositionByView(viewFindViewByPosition) == i) {
            this.mFlag |= 32;
            scrollToView(viewFindViewByPosition, z);
            this.mFlag &= -33;
            return;
        }
        int i4 = this.mFlag;
        if ((i4 & 512) == 0 || (i4 & 64) != 0) {
            this.mFocusPosition = i;
            this.mSubFocusPosition = i2;
            this.mFocusPositionOffset = Integer.MIN_VALUE;
            return;
        }
        if (z && !this.mBaseGridView.isLayoutRequested()) {
            this.mFocusPosition = i;
            this.mSubFocusPosition = i2;
            this.mFocusPositionOffset = Integer.MIN_VALUE;
            if (!hasDoneFirstLayout()) {
                Log.w(getTag(), "setSelectionSmooth should not be called before first layout pass");
                return;
            }
            int iStartPositionSmoothScroller = startPositionSmoothScroller(i);
            if (iStartPositionSmoothScroller != this.mFocusPosition) {
                this.mFocusPosition = iStartPositionSmoothScroller;
                this.mSubFocusPosition = 0;
                return;
            }
            return;
        }
        if (!z2) {
            skipSmoothScrollerOnStopInternal();
            this.mBaseGridView.stopScroll();
        }
        if (!this.mBaseGridView.isLayoutRequested() && viewFindViewByPosition != null && getAdapterPositionByView(viewFindViewByPosition) == i) {
            this.mFlag |= 32;
            scrollToView(viewFindViewByPosition, z);
            this.mFlag &= -33;
        } else {
            this.mFocusPosition = i;
            this.mSubFocusPosition = i2;
            this.mFocusPositionOffset = Integer.MIN_VALUE;
            this.mFlag |= 256;
            requestLayout();
        }
    }

    int startPositionSmoothScroller(int i) {
        GridLinearSmoothScroller gridLinearSmoothScroller = new GridLinearSmoothScroller() { // from class: androidx.leanback.widget.GridLayoutManager.4
            @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
            public PointF computeScrollVectorForPosition(int i2) {
                if (getChildCount() == 0) {
                    return null;
                }
                GridLayoutManager gridLayoutManager = GridLayoutManager.this;
                boolean z = false;
                int position = gridLayoutManager.getPosition(gridLayoutManager.getChildAt(0));
                if ((GridLayoutManager.this.mFlag & 262144) == 0 ? i2 < position : i2 > position) {
                    z = true;
                }
                int i3 = z ? -1 : 1;
                if (GridLayoutManager.this.mOrientation == 0) {
                    return new PointF(i3, 0.0f);
                }
                return new PointF(0.0f, i3);
            }
        };
        gridLinearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(gridLinearSmoothScroller);
        return gridLinearSmoothScroller.getTargetPosition();
    }

    void skipSmoothScrollerOnStopInternal() {
        GridLinearSmoothScroller gridLinearSmoothScroller = this.mCurrentSmoothScroller;
        if (gridLinearSmoothScroller != null) {
            gridLinearSmoothScroller.mSkipOnStopInternal = true;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void startSmoothScroll(RecyclerView.SmoothScroller smoothScroller) {
        skipSmoothScrollerOnStopInternal();
        super.startSmoothScroll(smoothScroller);
        if (smoothScroller.isRunning() && (smoothScroller instanceof GridLinearSmoothScroller)) {
            GridLinearSmoothScroller gridLinearSmoothScroller = (GridLinearSmoothScroller) smoothScroller;
            this.mCurrentSmoothScroller = gridLinearSmoothScroller;
            if (gridLinearSmoothScroller instanceof PendingMoveSmoothScroller) {
                this.mPendingMoveSmoothScroller = (PendingMoveSmoothScroller) gridLinearSmoothScroller;
                return;
            } else {
                this.mPendingMoveSmoothScroller = null;
                return;
            }
        }
        this.mCurrentSmoothScroller = null;
        this.mPendingMoveSmoothScroller = null;
    }

    private void processPendingMovement(boolean z) {
        if (z) {
            if (hasCreatedLastItem()) {
                return;
            }
        } else if (hasCreatedFirstItem()) {
            return;
        }
        PendingMoveSmoothScroller pendingMoveSmoothScroller = this.mPendingMoveSmoothScroller;
        if (pendingMoveSmoothScroller == null) {
            this.mBaseGridView.stopScroll();
            PendingMoveSmoothScroller pendingMoveSmoothScroller2 = new PendingMoveSmoothScroller(z ? 1 : -1, this.mNumRows > 1);
            this.mFocusPositionOffset = 0;
            startSmoothScroll(pendingMoveSmoothScroller2);
            return;
        }
        if (z) {
            pendingMoveSmoothScroller.increasePendingMoves();
        } else {
            pendingMoveSmoothScroller.decreasePendingMoves();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        Grid grid;
        int i3;
        if (this.mFocusPosition != -1 && (grid = this.mGrid) != null && grid.getFirstVisibleIndex() >= 0 && (i3 = this.mFocusPositionOffset) != Integer.MIN_VALUE && i <= this.mFocusPosition + i3) {
            this.mFocusPositionOffset = i3 + i2;
        }
        this.mChildrenStates.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mFocusPositionOffset = 0;
        this.mChildrenStates.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        Grid grid;
        int i3;
        int i4;
        int i5;
        if (this.mFocusPosition != -1 && (grid = this.mGrid) != null && grid.getFirstVisibleIndex() >= 0 && (i3 = this.mFocusPositionOffset) != Integer.MIN_VALUE && i <= (i5 = (i4 = this.mFocusPosition) + i3)) {
            if (i + i2 > i5) {
                int i6 = i3 + (i - i5);
                this.mFocusPositionOffset = i6;
                this.mFocusPosition = i4 + i6;
                this.mFocusPositionOffset = Integer.MIN_VALUE;
            } else {
                this.mFocusPositionOffset = i3 - i2;
            }
        }
        this.mChildrenStates.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        int i4;
        int i5 = this.mFocusPosition;
        if (i5 != -1 && (i4 = this.mFocusPositionOffset) != Integer.MIN_VALUE) {
            int i6 = i5 + i4;
            if (i <= i6 && i6 < i + i3) {
                this.mFocusPositionOffset = i4 + (i2 - i);
            } else if (i < i6 && i2 > i6 - i3) {
                this.mFocusPositionOffset -= i3;
            } else if (i > i6 && i2 < i6) {
                this.mFocusPositionOffset += i3;
            }
        }
        this.mChildrenStates.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            this.mChildrenStates.remove(i);
            i++;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
        if ((this.mFlag & 32768) == 0 && getAdapterPositionByView(view) != -1 && (this.mFlag & 35) == 0) {
            scrollToView(view, view2, true);
        }
        return true;
    }

    public void getViewSelectedOffsets(View view, int[] iArr) {
        if (this.mOrientation == 0) {
            iArr[0] = getPrimaryAlignedScrollDistance(view);
            iArr[1] = getSecondaryScrollDistance(view);
        } else {
            iArr[1] = getPrimaryAlignedScrollDistance(view);
            iArr[0] = getSecondaryScrollDistance(view);
        }
    }

    private int getPrimaryAlignedScrollDistance(View view) {
        return this.mWindowAlignment.mainAxis().getScroll(getViewCenter(view));
    }

    private int getAdjustedPrimaryAlignedScrollDistance(int i, View view, View view2) {
        int subPositionByView = getSubPositionByView(view, view2);
        if (subPositionByView == 0) {
            return i;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return i + (layoutParams.getAlignMultiple()[subPositionByView] - layoutParams.getAlignMultiple()[0]);
    }

    private int getSecondaryScrollDistance(View view) {
        return this.mWindowAlignment.secondAxis().getScroll(getViewCenterSecondary(view));
    }

    void scrollToView(View view, boolean z) {
        scrollToView(view, view == null ? null : view.findFocus(), z);
    }

    void scrollToView(View view, boolean z, int i, int i2) {
        scrollToView(view, view == null ? null : view.findFocus(), z, i, i2);
    }

    private void scrollToView(View view, View view2, boolean z) {
        scrollToView(view, view2, z, 0, 0);
    }

    private void scrollToView(View view, View view2, boolean z, int i, int i2) {
        if ((this.mFlag & 64) != 0) {
            return;
        }
        int adapterPositionByView = getAdapterPositionByView(view);
        int subPositionByView = getSubPositionByView(view, view2);
        if (adapterPositionByView != this.mFocusPosition || subPositionByView != this.mSubFocusPosition) {
            this.mFocusPosition = adapterPositionByView;
            this.mSubFocusPosition = subPositionByView;
            this.mFocusPositionOffset = 0;
            if ((this.mFlag & 3) != 1) {
                dispatchChildSelected();
            }
            if (this.mBaseGridView.isChildrenDrawingOrderEnabledInternal()) {
                this.mBaseGridView.invalidate();
            }
        }
        if (view == null) {
            return;
        }
        if (!view.hasFocus() && this.mBaseGridView.hasFocus()) {
            view.requestFocus();
        }
        if ((this.mFlag & 131072) == 0 && z) {
            return;
        }
        if (!getScrollPosition(view, view2, sTwoInts) && i == 0 && i2 == 0) {
            return;
        }
        int[] iArr = sTwoInts;
        scrollGrid(iArr[0] + i, iArr[1] + i2, z);
    }

    boolean getScrollPosition(View view, View view2, int[] iArr) {
        int i = this.mFocusScrollStrategy;
        if (i != 1 && i != 2) {
            return getAlignedPosition(view, view2, iArr);
        }
        return getNoneAlignedPosition(view, iArr);
    }

    /* JADX WARN: Code duplicated, block: B:39:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ba A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x00bc  */
    private boolean getNoneAlignedPosition(View view, int[] iArr) {
        View viewFindViewByPosition;
        int i;
        int viewMax;
        int secondaryScrollDistance;
        int adapterPositionByView = getAdapterPositionByView(view);
        int viewMin = getViewMin(view);
        int viewMax2 = getViewMax(view);
        int paddingMin = this.mWindowAlignment.mainAxis().getPaddingMin();
        int clientSize = this.mWindowAlignment.mainAxis().getClientSize();
        int rowIndex = this.mGrid.getRowIndex(adapterPositionByView);
        View viewFindViewByPosition2 = null;
        if (viewMin < paddingMin) {
            if (this.mFocusScrollStrategy == 2) {
                View view2 = view;
                while (true) {
                    if (!prependOneColumnVisibleItems()) {
                        viewFindViewByPosition = null;
                        viewFindViewByPosition2 = view2;
                        break;
                    }
                    Grid grid = this.mGrid;
                    CircularIntArray circularIntArray = grid.getItemPositionsInRows(grid.getFirstVisibleIndex(), adapterPositionByView)[rowIndex];
                    View viewFindViewByPosition3 = findViewByPosition(circularIntArray.get(0));
                    if (viewMax2 - getViewMin(viewFindViewByPosition3) > clientSize) {
                        if (circularIntArray.size() <= 2) {
                            viewFindViewByPosition = null;
                            viewFindViewByPosition2 = viewFindViewByPosition3;
                            break;
                        }
                        viewFindViewByPosition = null;
                        viewFindViewByPosition2 = findViewByPosition(circularIntArray.get(2));
                        break;
                    }
                    view2 = viewFindViewByPosition3;
                }
            } else {
                viewFindViewByPosition = null;
                viewFindViewByPosition2 = view;
            }
        } else if (viewMax2 <= clientSize + paddingMin) {
            viewFindViewByPosition = null;
        } else if (this.mFocusScrollStrategy == 2) {
            do {
                Grid grid2 = this.mGrid;
                CircularIntArray circularIntArray2 = grid2.getItemPositionsInRows(adapterPositionByView, grid2.getLastVisibleIndex())[rowIndex];
                viewFindViewByPosition = findViewByPosition(circularIntArray2.get(circularIntArray2.size() - 1));
                if (getViewMax(viewFindViewByPosition) - viewMin > clientSize) {
                    viewFindViewByPosition = null;
                    break;
                }
            } while (appendOneColumnVisibleItems());
            if (viewFindViewByPosition == null) {
                viewFindViewByPosition2 = view;
            }
        } else {
            viewFindViewByPosition = view;
        }
        if (viewFindViewByPosition2 != null) {
            viewMax = getViewMin(viewFindViewByPosition2);
        } else {
            if (viewFindViewByPosition != null) {
                viewMax = getViewMax(viewFindViewByPosition);
                paddingMin += clientSize;
            } else {
                i = 0;
            }
            if (viewFindViewByPosition2 != null) {
                view = viewFindViewByPosition2;
            } else if (viewFindViewByPosition != null) {
                view = viewFindViewByPosition;
            }
            secondaryScrollDistance = getSecondaryScrollDistance(view);
            if (i != 0 && secondaryScrollDistance == 0) {
                return false;
            }
            iArr[0] = i;
            iArr[1] = secondaryScrollDistance;
            return true;
        }
        i = viewMax - paddingMin;
        if (viewFindViewByPosition2 != null) {
            view = viewFindViewByPosition2;
        } else if (viewFindViewByPosition != null) {
            view = viewFindViewByPosition;
        }
        secondaryScrollDistance = getSecondaryScrollDistance(view);
        if (i != 0) {
        }
        iArr[0] = i;
        iArr[1] = secondaryScrollDistance;
        return true;
    }

    private boolean getAlignedPosition(View view, View view2, int[] iArr) {
        int primaryAlignedScrollDistance = getPrimaryAlignedScrollDistance(view);
        if (view2 != null) {
            primaryAlignedScrollDistance = getAdjustedPrimaryAlignedScrollDistance(primaryAlignedScrollDistance, view, view2);
        }
        int secondaryScrollDistance = getSecondaryScrollDistance(view);
        int i = primaryAlignedScrollDistance + this.mPrimaryScrollExtra;
        if (i != 0 || secondaryScrollDistance != 0) {
            iArr[0] = i;
            iArr[1] = secondaryScrollDistance;
            return true;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        return false;
    }

    private void scrollGrid(int i, int i2, boolean z) {
        if ((this.mFlag & 3) == 1) {
            scrollDirectionPrimary(i);
            scrollDirectionSecondary(i2);
            return;
        }
        if (this.mOrientation != 0) {
            i2 = i;
            i = i2;
        }
        if (z) {
            this.mBaseGridView.smoothScrollBy(i, i2);
        } else {
            this.mBaseGridView.scrollBy(i, i2);
            dispatchChildSelectedAndPositioned();
        }
    }

    public void setPruneChild(boolean z) {
        if (((this.mFlag & 65536) != 0) != z) {
            this.mFlag = (this.mFlag & (-65537)) | (z ? 65536 : 0);
            if (z) {
                requestLayout();
            }
        }
    }

    public boolean getPruneChild() {
        return (this.mFlag & 65536) != 0;
    }

    public void setScrollEnabled(boolean z) {
        int i;
        if (((this.mFlag & 131072) != 0) != z) {
            int i2 = (this.mFlag & (-131073)) | (z ? 131072 : 0);
            this.mFlag = i2;
            if ((i2 & 131072) == 0 || this.mFocusScrollStrategy != 0 || (i = this.mFocusPosition) == -1) {
                return;
            }
            scrollToSelection(i, this.mSubFocusPosition, true, this.mPrimaryScrollExtra);
        }
    }

    public boolean isScrollEnabled() {
        return (this.mFlag & 131072) != 0;
    }

    private int findImmediateChildIndex(View view) {
        View viewFindContainingItemView;
        BaseGridView baseGridView = this.mBaseGridView;
        if (baseGridView == null || view == baseGridView || (viewFindContainingItemView = findContainingItemView(view)) == null) {
            return -1;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i) == viewFindContainingItemView) {
                return i;
            }
        }
        return -1;
    }

    void onFocusChanged(boolean z, int i, Rect rect) {
        if (!z) {
            return;
        }
        int i2 = this.mFocusPosition;
        while (true) {
            View viewFindViewByPosition = findViewByPosition(i2);
            if (viewFindViewByPosition == null) {
                return;
            }
            if (viewFindViewByPosition.getVisibility() == 0 && viewFindViewByPosition.hasFocusable()) {
                viewFindViewByPosition.requestFocus();
                return;
            }
            i2++;
        }
    }

    void setFocusSearchDisabled(boolean z) {
        this.mFlag = (z ? 32768 : 0) | (this.mFlag & (-32769));
    }

    boolean isFocusSearchDisabled() {
        return (this.mFlag & 32768) != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onInterceptFocusSearch(View view, int i) {
        View viewFindNextFocus;
        if ((this.mFlag & 32768) != 0) {
            return view;
        }
        FocusFinder focusFinder = FocusFinder.getInstance();
        View viewFindNextFocus2 = null;
        if (i == 2 || i == 1) {
            if (canScrollVertically()) {
                viewFindNextFocus2 = focusFinder.findNextFocus(this.mBaseGridView, view, i == 2 ? TsExtractor.TS_STREAM_TYPE_HDMV_DTS : 33);
            }
            if (canScrollHorizontally()) {
                viewFindNextFocus = focusFinder.findNextFocus(this.mBaseGridView, view, (getLayoutDirection() == 1) ^ (i == 2) ? 66 : 17);
            } else {
                viewFindNextFocus = viewFindNextFocus2;
            }
        } else {
            viewFindNextFocus = focusFinder.findNextFocus(this.mBaseGridView, view, i);
        }
        if (viewFindNextFocus != null) {
            return viewFindNextFocus;
        }
        if (this.mBaseGridView.getDescendantFocusability() == 393216) {
            return this.mBaseGridView.getParent().focusSearch(view, i);
        }
        int movement = getMovement(i);
        boolean z = this.mBaseGridView.getScrollState() != 0;
        if (movement == 1) {
            if (z || (this.mFlag & 4096) == 0) {
                viewFindNextFocus = view;
            }
            if ((this.mFlag & 131072) != 0 && !hasCreatedLastItem()) {
                processPendingMovement(true);
                viewFindNextFocus = view;
            }
        } else if (movement == 0) {
            if (z || (this.mFlag & 2048) == 0) {
                viewFindNextFocus = view;
            }
            if ((this.mFlag & 131072) != 0 && !hasCreatedFirstItem()) {
                processPendingMovement(false);
                viewFindNextFocus = view;
            }
        } else if (movement == 3) {
        }
        if (viewFindNextFocus != null) {
            return viewFindNextFocus;
        }
        View viewFocusSearch = this.mBaseGridView.getParent().focusSearch(view, i);
        if (viewFocusSearch != null) {
            return viewFocusSearch;
        }
        return view != null ? view : this.mBaseGridView;
    }

    boolean hasPreviousViewInSameRow(int i) {
        Grid grid = this.mGrid;
        if (grid != null && i != -1 && grid.getFirstVisibleIndex() >= 0) {
            if (this.mGrid.getFirstVisibleIndex() > 0) {
                return true;
            }
            int i2 = this.mGrid.getLocation(i).row;
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                int adapterPositionByIndex = getAdapterPositionByIndex(childCount);
                Grid.Location location = this.mGrid.getLocation(adapterPositionByIndex);
                if (location != null && location.row == i2 && adapterPositionByIndex < i) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:51:0x0096  */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
        int childCount;
        View childAt;
        if ((this.mFlag & 32768) != 0) {
            return true;
        }
        if (recyclerView.hasFocus()) {
            if (this.mPendingMoveSmoothScroller != null) {
                return true;
            }
            int movement = getMovement(i);
            int iFindImmediateChildIndex = findImmediateChildIndex(recyclerView.findFocus());
            int adapterPositionByIndex = getAdapterPositionByIndex(iFindImmediateChildIndex);
            View viewFindViewByPosition = adapterPositionByIndex == -1 ? null : findViewByPosition(adapterPositionByIndex);
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.addFocusables(arrayList, i, i2);
            }
            if (this.mGrid == null || getChildCount() == 0) {
                return true;
            }
            if ((movement == 3 || movement == 2) && this.mGrid.getNumRows() <= 1) {
                return true;
            }
            Grid grid = this.mGrid;
            int i3 = (grid == null || viewFindViewByPosition == null) ? -1 : grid.getLocation(adapterPositionByIndex).row;
            int size = arrayList.size();
            int i4 = (movement == 1 || movement == 3) ? 1 : -1;
            int childCount2 = i4 > 0 ? getChildCount() - 1 : 0;
            if (iFindImmediateChildIndex == -1) {
                childCount = i4 > 0 ? 0 : getChildCount() - 1;
            } else {
                childCount = iFindImmediateChildIndex + i4;
            }
            int i5 = childCount;
            while (true) {
                if (i4 <= 0) {
                    if (i5 < childCount2) {
                        break;
                    }
                    childAt = getChildAt(i5);
                    if (childAt.getVisibility() != 0) {
                    }
                    i5 += i4;
                } else {
                    if (i5 > childCount2) {
                        break;
                    }
                    childAt = getChildAt(i5);
                    if (childAt.getVisibility() != 0 && childAt.hasFocusable()) {
                        if (viewFindViewByPosition == null) {
                            childAt.addFocusables(arrayList, i, i2);
                            if (arrayList.size() > size) {
                                break;
                            }
                        } else {
                            int adapterPositionByIndex2 = getAdapterPositionByIndex(i5);
                            Grid.Location location = this.mGrid.getLocation(adapterPositionByIndex2);
                            if (location == null) {
                                continue;
                            } else if (movement == 1) {
                                if (location.row == i3 && adapterPositionByIndex2 > adapterPositionByIndex) {
                                    childAt.addFocusables(arrayList, i, i2);
                                    if (arrayList.size() > size) {
                                        break;
                                    }
                                }
                            } else if (movement == 0) {
                                if (location.row == i3 && adapterPositionByIndex2 < adapterPositionByIndex) {
                                    childAt.addFocusables(arrayList, i, i2);
                                    if (arrayList.size() > size) {
                                        break;
                                    }
                                }
                            } else if (movement == 3) {
                                if (location.row == i3) {
                                    continue;
                                } else {
                                    if (location.row < i3) {
                                        break;
                                    }
                                    childAt.addFocusables(arrayList, i, i2);
                                }
                            } else if (movement == 2 && location.row != i3) {
                                if (location.row > i3) {
                                    break;
                                }
                                childAt.addFocusables(arrayList, i, i2);
                            }
                        }
                    }
                    i5 += i4;
                }
            }
        } else {
            int size2 = arrayList.size();
            if (this.mFocusScrollStrategy != 0) {
                int paddingMin = this.mWindowAlignment.mainAxis().getPaddingMin();
                int clientSize = this.mWindowAlignment.mainAxis().getClientSize() + paddingMin;
                int childCount3 = getChildCount();
                for (int i6 = 0; i6 < childCount3; i6++) {
                    View childAt2 = getChildAt(i6);
                    if (childAt2.getVisibility() == 0 && getViewMin(childAt2) >= paddingMin && getViewMax(childAt2) <= clientSize) {
                        childAt2.addFocusables(arrayList, i, i2);
                    }
                }
                if (arrayList.size() == size2) {
                    int childCount4 = getChildCount();
                    for (int i7 = 0; i7 < childCount4; i7++) {
                        View childAt3 = getChildAt(i7);
                        if (childAt3.getVisibility() == 0) {
                            childAt3.addFocusables(arrayList, i, i2);
                        }
                    }
                }
            } else {
                View viewFindViewByPosition2 = findViewByPosition(this.mFocusPosition);
                if (viewFindViewByPosition2 != null) {
                    viewFindViewByPosition2.addFocusables(arrayList, i, i2);
                }
            }
            if (arrayList.size() == size2 && recyclerView.isFocusable()) {
                arrayList.add(recyclerView);
            }
        }
        return true;
    }

    boolean hasCreatedLastItem() {
        int itemCount = getItemCount();
        return itemCount == 0 || this.mBaseGridView.findViewHolderForAdapterPosition(itemCount - 1) != null;
    }

    boolean hasCreatedFirstItem() {
        return getItemCount() == 0 || this.mBaseGridView.findViewHolderForAdapterPosition(0) != null;
    }

    boolean isItemFullyVisible(int i) {
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.mBaseGridView.findViewHolderForAdapterPosition(i);
        return viewHolderFindViewHolderForAdapterPosition != null && viewHolderFindViewHolderForAdapterPosition.itemView.getLeft() >= 0 && viewHolderFindViewHolderForAdapterPosition.itemView.getRight() <= this.mBaseGridView.getWidth() && viewHolderFindViewHolderForAdapterPosition.itemView.getTop() >= 0 && viewHolderFindViewHolderForAdapterPosition.itemView.getBottom() <= this.mBaseGridView.getHeight();
    }

    boolean canScrollTo(View view) {
        return view.getVisibility() == 0 && (!hasFocus() || view.hasFocusable());
    }

    boolean gridOnRequestFocusInDescendants(RecyclerView recyclerView, int i, Rect rect) {
        int i2 = this.mFocusScrollStrategy;
        if (i2 != 1 && i2 != 2) {
            return gridOnRequestFocusInDescendantsAligned(recyclerView, i, rect);
        }
        return gridOnRequestFocusInDescendantsUnaligned(recyclerView, i, rect);
    }

    private boolean gridOnRequestFocusInDescendantsAligned(RecyclerView recyclerView, int i, Rect rect) {
        View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
        if (viewFindViewByPosition != null) {
            return viewFindViewByPosition.requestFocus(i, rect);
        }
        return false;
    }

    private boolean gridOnRequestFocusInDescendantsUnaligned(RecyclerView recyclerView, int i, Rect rect) {
        int i2;
        int i3;
        int childCount = getChildCount();
        int i4 = -1;
        if ((i & 2) != 0) {
            i4 = childCount;
            i2 = 0;
            i3 = 1;
        } else {
            i2 = childCount - 1;
            i3 = -1;
        }
        int paddingMin = this.mWindowAlignment.mainAxis().getPaddingMin();
        int clientSize = this.mWindowAlignment.mainAxis().getClientSize() + paddingMin;
        while (i2 != i4) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && getViewMin(childAt) >= paddingMin && getViewMax(childAt) <= clientSize && childAt.requestFocus(i, rect)) {
                return true;
            }
            i2 += i3;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0035, code lost:
    
        if (r10 != 130) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003d, code lost:
    
        if ((r9.mFlag & 524288) == 0) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0043, code lost:
    
        if ((r9.mFlag & 524288) == 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
    
        if (r10 != 130) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getMovement(int r10) {
        /*
            r9 = this;
            int r0 = r9.mOrientation
            r1 = 130(0x82, float:1.82E-43)
            r2 = 66
            r3 = 33
            r4 = 0
            r5 = 3
            r6 = 2
            r7 = 17
            r8 = 1
            if (r0 != 0) goto L2b
            r0 = 262144(0x40000, float:3.67342E-40)
            if (r10 == r7) goto L25
            if (r10 == r3) goto L23
            if (r10 == r2) goto L1d
            if (r10 == r1) goto L1b
            goto L46
        L1b:
            r4 = r5
            goto L47
        L1d:
            int r10 = r9.mFlag
            r10 = r10 & r0
            if (r10 != 0) goto L47
            goto L38
        L23:
            r4 = r6
            goto L47
        L25:
            int r10 = r9.mFlag
            r10 = r10 & r0
            if (r10 != 0) goto L38
            goto L47
        L2b:
            if (r0 != r8) goto L46
            r0 = 524288(0x80000, float:7.34684E-40)
            if (r10 == r7) goto L40
            if (r10 == r3) goto L47
            if (r10 == r2) goto L3a
            if (r10 == r1) goto L38
            goto L46
        L38:
            r4 = r8
            goto L47
        L3a:
            int r10 = r9.mFlag
            r10 = r10 & r0
            if (r10 != 0) goto L23
            goto L1b
        L40:
            int r10 = r9.mFlag
            r10 = r10 & r0
            if (r10 != 0) goto L1b
            goto L23
        L46:
            r4 = r7
        L47:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.getMovement(int):int");
    }

    int getChildDrawingOrder(RecyclerView recyclerView, int i, int i2) {
        int iIndexOfChild;
        View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
        if (viewFindViewByPosition != null && i2 >= (iIndexOfChild = recyclerView.indexOfChild(viewFindViewByPosition))) {
            return i2 < i + (-1) ? ((iIndexOfChild + i) - 1) - i2 : iIndexOfChild;
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        if (adapter != null) {
            discardLayoutInfo();
            this.mFocusPosition = -1;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.clear();
        }
        if (adapter2 instanceof FacetProviderAdapter) {
            this.mFacetProviderAdapter = (FacetProviderAdapter) adapter2;
        } else {
            this.mFacetProviderAdapter = null;
        }
        super.onAdapterChanged(adapter, adapter2);
    }

    private void discardLayoutInfo() {
        this.mGrid = null;
        this.mRowSizeSecondary = null;
        this.mFlag &= -1025;
    }

    public void setLayoutEnabled(boolean z) {
        if (((this.mFlag & 512) != 0) != z) {
            this.mFlag = (this.mFlag & (-513)) | (z ? 512 : 0);
            requestLayout();
        }
    }

    void setChildrenVisibility(int i) {
        this.mChildVisibility = i;
        if (i != -1) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                getChildAt(i2).setVisibility(this.mChildVisibility);
            }
        }
    }

    static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.leanback.widget.GridLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        Bundle childStates;
        int index;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.index);
            parcel.writeBundle(this.childStates);
        }

        SavedState(Parcel parcel) {
            this.childStates = Bundle.EMPTY;
            this.index = parcel.readInt();
            this.childStates = parcel.readBundle(GridLayoutManager.class.getClassLoader());
        }

        SavedState() {
            this.childStates = Bundle.EMPTY;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.index = getSelection();
        Bundle bundleSaveAsBundle = this.mChildrenStates.saveAsBundle();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int adapterPositionByView = getAdapterPositionByView(childAt);
            if (adapterPositionByView != -1) {
                bundleSaveAsBundle = this.mChildrenStates.saveOnScreenView(bundleSaveAsBundle, childAt, adapterPositionByView);
            }
        }
        savedState.childStates = bundleSaveAsBundle;
        return savedState;
    }

    void onChildRecycled(RecyclerView.ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition != -1) {
            this.mChildrenStates.saveOffscreenView(viewHolder.itemView, adapterPosition);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mFocusPosition = savedState.index;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.loadFromBundle(savedState.childStates);
            this.mFlag |= 256;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Grid grid;
        if (this.mOrientation == 0 && (grid = this.mGrid) != null) {
            return grid.getNumRows();
        }
        return super.getRowCountForAccessibility(recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Grid grid;
        if (this.mOrientation == 1 && (grid = this.mGrid) != null) {
            return grid.getNumRows();
        }
        return super.getColumnCountForAccessibility(recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (this.mGrid == null || !(layoutParams instanceof LayoutParams)) {
            return;
        }
        int viewAdapterPosition = ((LayoutParams) layoutParams).getViewAdapterPosition();
        int rowIndex = viewAdapterPosition >= 0 ? this.mGrid.getRowIndex(viewAdapterPosition) : -1;
        if (rowIndex < 0) {
            return;
        }
        int numRows = viewAdapterPosition / this.mGrid.getNumRows();
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(rowIndex, 1, numRows, 1, false, false));
        } else {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(numRows, 1, rowIndex, 1, false, false));
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002e  */
    /* JADX WARN: Code duplicated, block: B:17:0x0030  */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean performAccessibilityAction(RecyclerView.Recycler recycler, RecyclerView.State state, int i, Bundle bundle) {
        if (!isScrollEnabled()) {
            return true;
        }
        saveContext(recycler, state);
        boolean z = (this.mFlag & 262144) != 0;
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.mOrientation == 0) {
                if (i == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT.getId()) {
                    if (z) {
                        i = 4096;
                    } else {
                        i = 8192;
                    }
                } else if (i == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT.getId()) {
                    if (z) {
                        i = 8192;
                    } else {
                        i = 4096;
                    }
                }
            } else if (i == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP.getId()) {
                i = 8192;
            } else if (i == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN.getId()) {
                i = 4096;
            }
        }
        if (i == 4096) {
            processPendingMovement(true);
            processSelectionMoves(false, 1);
        } else if (i == 8192) {
            processPendingMovement(false);
            processSelectionMoves(false, -1);
        }
        leaveContext();
        return true;
    }

    int processSelectionMoves(boolean z, int i) {
        Grid grid = this.mGrid;
        if (grid == null) {
            return i;
        }
        int i2 = this.mFocusPosition;
        int rowIndex = i2 != -1 ? grid.getRowIndex(i2) : -1;
        View view = null;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount && i != 0; i3++) {
            int i4 = i > 0 ? i3 : (childCount - 1) - i3;
            View childAt = getChildAt(i4);
            if (canScrollTo(childAt)) {
                int adapterPositionByIndex = getAdapterPositionByIndex(i4);
                int rowIndex2 = this.mGrid.getRowIndex(adapterPositionByIndex);
                if (rowIndex == -1) {
                    i2 = adapterPositionByIndex;
                    view = childAt;
                    rowIndex = rowIndex2;
                } else if (rowIndex2 == rowIndex && ((i > 0 && adapterPositionByIndex > i2) || (i < 0 && adapterPositionByIndex < i2))) {
                    i = i > 0 ? i - 1 : i + 1;
                    i2 = adapterPositionByIndex;
                    view = childAt;
                }
            }
        }
        if (view != null) {
            if (z) {
                if (hasFocus()) {
                    this.mFlag |= 32;
                    view.requestFocus();
                    this.mFlag &= -33;
                }
                this.mFocusPosition = i2;
                this.mSubFocusPosition = 0;
            } else {
                scrollToView(view, true);
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        saveContext(recycler, state);
        int itemCount = state.getItemCount();
        boolean z = (this.mFlag & 262144) != 0;
        if (itemCount > 1 && !isItemFullyVisible(0)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (this.mOrientation == 0) {
                    accessibilityNodeInfoCompat.addAction(z ? AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT : AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT);
                } else {
                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
                }
            } else {
                accessibilityNodeInfoCompat.addAction(8192);
            }
            accessibilityNodeInfoCompat.setScrollable(true);
        }
        if (itemCount > 1 && !isItemFullyVisible(itemCount - 1)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (this.mOrientation == 0) {
                    accessibilityNodeInfoCompat.addAction(z ? AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT : AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT);
                } else {
                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
                }
            } else {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            accessibilityNodeInfoCompat.setScrollable(true);
        }
        accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        leaveContext();
    }
}
